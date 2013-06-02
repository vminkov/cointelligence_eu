package eu.cointelligence.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.resource.spi.SecurityException;

import eu.cointelligence.controller.dao.StatementsDao;
import eu.cointelligence.controller.entity.TradingAction;
import eu.cointelligence.controller.users.IUserManager;
import eu.cointelligence.controller.users.UserRole;
import eu.cointelligence.controller.users.exceptions.NoSuchUserException;
import eu.cointelligence.controller.users.exceptions.WrongPasswordException;
import eu.cointelligence.model.Account;
import eu.cointelligence.model.ShortSell;
import eu.cointelligence.model.Statement;
import eu.cointelligence.model.Transaction;
import eu.cointelligence.model.User;

@Singleton
public class Trader implements ITrader {

	@EJB
	private IUserManager userManager;

	@EJB
	private IMarketMaker marketMaker;

	@EJB
	private StatementsDao statementsDao;

	@Override
	public boolean buy(String username, String password, Long statementId,
			Long wantedQuantity) {

		User user = authenticateUser(username, password);
		if (user == null || wantedQuantity == null) {
			return false;
		}

		Statement statement = statementsDao.find(statementId);
		if(statement == null) 
			return false;
		
		Long statementPrice = statement.getCurrentValue();
		Account account = user.getAccount();
		Long availableCash = account.getCointels();
		if ((statementPrice == null || availableCash == null)
				|| (availableCash <= statementPrice * wantedQuantity)) {
			return false;
		}

		// do the action
		Long currentOwnedQuantity = account.getSharesForStatement(
				statement.getId());
		Long newOwnedQuantity;
		if (currentOwnedQuantity == null) {
			newOwnedQuantity = wantedQuantity;
		} else {
			newOwnedQuantity = currentOwnedQuantity + wantedQuantity;
		}
		account.updateStatementStake(statement, newOwnedQuantity);
		account.setCointels(availableCash - (statementPrice * wantedQuantity));
		userManager.updateUserInfo(user);
		// TODO: make it a database transaction
		marketMaker.addLog(new Transaction(account, statement,
				TradingAction.BUY.toString(), wantedQuantity));
		return true;
	}

	@Override
	public boolean sell(String username, String password, Long statementId, Long quantity) {
		User user = authenticateUser(username, password);
		if (user == null || quantity == null) {
			return false;
		}
		Statement statement = statementsDao.find(statementId);
		if(statement == null) 
			return false;
		
		Long statementPrice = statement.getCurrentValue();

		Account account = user.getAccount();
		Long ownedQuantity = account.getStatementsInPossession().get(
				statement.getId());

		if ((statementPrice == null || ownedQuantity == null)
				|| (ownedQuantity < quantity)) {
			return false;
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
		userManager.updateUserInfo(user);

		marketMaker.addLog(new Transaction(user.getAccount(), statement,
				TradingAction.SELL.toString(), quantity));
		return true;
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
	public boolean shortSell(String username, String password, Long statementId,
			Long quantity, Long minutes) {
		
		User user = authenticateUser(username, password);
		if (user == null || quantity == null) {
			return false;
		}
		
		Statement statement = statementsDao.find(statementId);
		if(statement == null) 
			return false;
		
		Long statementPrice = statement.getCurrentValue();
		if ((statementPrice == null)){
			return false;
		}

		Account account = user.getAccount();
		Long cointels = account.getCointels();
		
		//if the user does not have enough to buy them later at highest price
		if(cointels < quantity * Constants.MAX_PRICE_FOR_A_STATMENT) {
			return false;
		}
		
		//no problems to proceed to transaction then
//		Long ownedQuantity = account.getShortSellsCountForStatement(
//				statement.getId());
//
//		Long newOwnedQuantity;
//		if(ownedQuantity == null){
//			newOwnedQuantity = quantity;
//		} else {
//			newOwnedQuantity = ownedQuantity  + quantity;
//		}
		
		// do the action
		ShortSell sell = new ShortSell();
		Transaction transaction = new Transaction(user.getAccount(), statement,
				TradingAction.SHORTSELL.toString(), quantity);
		sell.setAmount(quantity);
		sell.setTransaction(transaction);

		account.getShortSells().add(sell);

		account.setCointels(account.getCointels() - (quantity * Constants.MAX_PRICE_FOR_A_STATMENT));
		userManager.updateUserInfo(user);

		marketMaker.addLog(transaction);
		return true;
	}

	private User authenticateUser(String username, String password) {
		User user = null;
		try {
			user = this.userManager.loginWithCookie(username, password, UserRole.USER);
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
	
	
}
