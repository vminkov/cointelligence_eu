package eu.cointelligence.controller;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.resource.spi.SecurityException;
import javax.ws.rs.FormParam;

import eu.cointelligence.controller.dao.ShortSellsDao;
import eu.cointelligence.controller.dao.StatementStakesDao;
import eu.cointelligence.controller.dao.StatementsDao;
import eu.cointelligence.controller.dao.TransactionsDao;
import eu.cointelligence.controller.entity.TradingAction;
import eu.cointelligence.controller.entity.beans.StatementBean;
import eu.cointelligence.controller.users.IUserManager;
import eu.cointelligence.controller.users.UserRole;
import eu.cointelligence.controller.users.exceptions.NoSuchUserException;
import eu.cointelligence.controller.users.exceptions.WrongPasswordException;
import eu.cointelligence.model.Account;
import eu.cointelligence.model.ShortSell;
import eu.cointelligence.model.Statement;
import eu.cointelligence.model.StatementStake;
import eu.cointelligence.model.Transaction;
import eu.cointelligence.model.User;

@Singleton
public class Trader implements ITrader {
	@EJB
	private ShortSellsDao shortSellsDao;

	@EJB
	private IUserManager userManager;

	@EJB
	private IMarketMaker marketMaker;

	@EJB
	private StatementsDao statementsDao;

	@EJB
	private TransactionsDao transactionsDao;

	@EJB
	private StatementStakesDao stakesDao;

	@Override
	public String buy(String username, String password, Long statementId,
			Long wantedQuantity) {

		User user = authenticateUser(username, password);
		if (user == null || wantedQuantity == null) {
			return Constants.WRONG_CREDENTIALS_MESSAGE;
		}

		Statement statement = statementsDao.find(statementId);
		if (statement == null)
			return Constants.STATEMENT_INVALID_MESSAGE;

		Long statementPrice = statement.getCurrentValue();
		Account account = user.getAccount();
		Long availableCash = account.getCointels();
		if ((statementPrice == null || availableCash == null)
				|| (availableCash <= statementPrice * wantedQuantity)) {
			return Constants.COINTELS_NOT_ENOUGH;
		}

		// do the action
		Long currentOwnedQuantity = account.getStatementsInPossession().get(
				statement.getId());
		Long newOwnedQuantity;
		if (currentOwnedQuantity == null) {
			newOwnedQuantity = wantedQuantity;
		} else {
			newOwnedQuantity = currentOwnedQuantity + wantedQuantity;
		}
		updateStatementStake(account, statement, newOwnedQuantity);

		account.setCointels(availableCash - (statementPrice * wantedQuantity));
		Transaction transaction = new Transaction(account, statement,
				TradingAction.BUY.toString(), wantedQuantity);
		Transaction saved = transactionsDao.create(transaction);

		// TODO: make it a database transaction
		marketMaker.addLog(saved);
		return "true";
	}

	private void updateStatementStake(Account account, Statement statement,
			Long newOwnedQuantity) {
		Collection<StatementStake> statementStake = account.getStatementStake();
		for (StatementStake st : statementStake) {
			if (statement.getId().equals(st.getStatement().getId())) {
				st.setSharesCount(newOwnedQuantity);
				return;
			}
		}

		// else
		StatementStake saved = stakesDao.create(new StatementStake());
		saved.setStatement(statement);
		saved.setSharesCount(newOwnedQuantity);
		saved.setAccount(account);
	}

	@Override
	public String sell(String username, String password, Long statementId,
			Long quantity) {
		User user = authenticateUser(username, password);
		if (user == null || quantity == null) {
			return Constants.WRONG_CREDENTIALS_MESSAGE;
		}
		Statement statement = statementsDao.find(statementId);
		if (statement == null)
			return Constants.STATEMENT_INVALID_MESSAGE;

		Long statementPrice = statement.getCurrentValue();

		Account account = user.getAccount();
		Long ownedQuantity = account.getStatementsInPossession().get(
				statement.getId());

		if ((statementPrice == null || ownedQuantity == null)
				|| (ownedQuantity < quantity)) {
			return Constants.FEATURES_NOT_ENOUGH;
		}

		// do the action
		Long newOwnedQuantity;
		newOwnedQuantity = ownedQuantity - quantity;
		if (newOwnedQuantity > 0) {
			account.getStatementsInPossession().put(statement.getId(),
					newOwnedQuantity);
		} else {
			// XXX: might be left as 0?
			account.getStatementsInPossession().remove(statement.getId());
		}
		account.setCointels(account.getCointels() + (statementPrice * quantity));
		updateStatementStake(account, statement, newOwnedQuantity);

		Transaction transaction = new Transaction(user.getAccount(), statement,
				TradingAction.SELL.toString(), quantity);
		userManager.updateUserInfo(user);

		marketMaker.addLog(transaction);
		return "true";
	}

	@Override
	public Account checkBankAccount(String username, String password) {
		Account account = null;
		User user = authenticateUser(username, password);

		if (user != null) {
			account = user.getAccount();
		}

		return account;
	}

	@Override
	public String shortSell(String username, String password, Long statementId,
			Long quantity, Long minutes) {

		User user = authenticateUser(username, password);
		if (user == null || quantity == null) {
			return Constants.WRONG_CREDENTIALS_MESSAGE;
		}

		Statement statement = statementsDao.find(statementId);
		if (statement == null)
			return Constants.STATEMENT_INVALID_MESSAGE;

		Long statementPrice = statement.getCurrentValue();
		if ((statementPrice == null)) {
			return Constants.STATEMENT_INVALID_MESSAGE;
		}

		Account account = user.getAccount();
		Long cointels = account.getCointels();

		// if the user does not have enough to buy them later at highest price
		if (cointels < quantity * Constants.MAX_PRICE_FOR_A_STATMENT) {
			return Constants.COINTELS_NOT_ENOUGH;
		}

		// no problems to proceed to transaction then
		account.setCointels(cointels
				- (quantity * Constants.MAX_PRICE_FOR_A_STATMENT));
		Transaction transaction = new Transaction(user.getAccount(), statement,
				TradingAction.SHORTSELL.toString(), quantity);
		this.transactionsDao.create(transaction);
		ShortSell sell = this.shortSellsDao.create(new ShortSell());

		sell.setAmount(quantity);
		sell.setTransaction(transaction);
		sell.setAccount(account);

		// userManager.updateUserInfo(user);

		marketMaker.addLog(transaction);
		return "true";
	}

	private User authenticateUser(String username, String password) {
		User user = null;
		try {
			user = this.userManager.loginWithCookie(username, password,
					UserRole.USER);
		} catch (NoSuchUserException | WrongPasswordException
				| SecurityException e) {
			// nothing
		}
		return user;
	}

	@Override
	public List<Statement> getStatements() {
		return this.statementsDao.getAll();
	}

	@Override
	public Statement getStatementById(Long statementId) {
		return this.statementsDao.find(statementId);
	}

	@Override
	public boolean submitStatement(String username, String password,
			StatementBean statementBean) {

		System.out.println(statementBean);
		User user = authenticateUser(username, password);
		if (user == null) {
			return false;
		}

		if (statementBean == null)
			return false;

		if (statementBean.getTitle() == null
				|| statementBean.getDescription() == null
				|| "".equals(statementBean.getTitle())
				|| "".equals(statementBean.getDescription())) {
			return false;
		}

		if (statementBean.getDuedate() == null) {
			return false;
		}
		if (statementBean.getVoteStarted() == null) {
			statementBean.setVoteStarted(true);
		}

		Statement statement = this.statementsDao.create(new Statement());
		statement.setTitle(statementBean.getTitle());
		statement.setDescription(statementBean.getDescription());
		statement.setVoteStarted(statementBean.getVoteStarted());
		statement.setCurrentValue(Constants.DEFAULT_PRICE);

		return true;
	}

}
