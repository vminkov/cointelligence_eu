package eu.cointelligence.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import eu.cointelligence.controller.dao.StatementsDao;
import eu.cointelligence.controller.dao.UsersDao;
import eu.cointelligence.controller.entity.TradingAction;
import eu.cointelligence.model.Account;
import eu.cointelligence.model.Statement;
import eu.cointelligence.model.Transaction;
import eu.cointelligence.model.User;

@Stateless
@Path("/trader")
public class TraderService implements ITraderService {
	
	@EJB
	private UsersDao usersDao;
	
	@EJB
	private IMarketMaker marketMaker;
	
	@EJB
	private StatementsDao statementsDao;
	
	
	@Override
	@POST
	@Path("/buy")
	@Produces("application/json")
	public boolean buy(@FormParam("userId") String userId,
			@FormParam("statementId") String statementId,
			@FormParam("quantity") Long wantedQuantity) {
		
		User user = findUserById(userId);
		if (user == null || wantedQuantity == null) {
			return false;
		}
		
		Statement statement = statementsDao.find(statementId);
		Long statementPrice = statement.getCurrentValue();
		Account account = user.getAccount();
		Long availableCash = account.getCointels();
		if ((statementPrice == null || availableCash == null)
				|| (availableCash <= statementPrice * wantedQuantity)) {
			return false;
		}

		// do the action
		Long currentOwnedQuantity = account.getStatementsInPossession().get(statement.getId());
		Long newOwnedQuantity;
		if (currentOwnedQuantity == null) {
			newOwnedQuantity = wantedQuantity;
		} else {
			newOwnedQuantity = currentOwnedQuantity + wantedQuantity;
		}
		account.getStatementsInPossession().put(statement.getId(), newOwnedQuantity);
		account.setCointels(availableCash - (statementPrice * wantedQuantity));
		usersDao.update(user);
		//TODO: make it a database transaction
		marketMaker.addLog(
				new Transaction(account, statement, TradingAction.BUY
						.toString(), wantedQuantity));
		return true;
	}

	@Override
	@POST
	@Path("/sell")
	@Produces("application/json")
	public boolean sell(@FormParam("userId") String userId,
			@FormParam("statementId") String statementId,
			@FormParam("quantity") Long quantity) {

		User user = findUserById(userId);
		if (user == null || quantity == null) {
			return false;
		}
		Statement statement = statementsDao.find(statementId);
		
		Long statementPrice = statement.getCurrentValue();
		
		
		Account account = user.getAccount();
		Long ownedQuantity = account.getStatementsInPossession().get(statement.getId());

		if ((statementPrice == null || ownedQuantity == null)
				|| (ownedQuantity < quantity)) {
			return false;
		}

		// do the action
		Long newOwnedQuantity;
		newOwnedQuantity = ownedQuantity - quantity;
		if (newOwnedQuantity > 0) {
			account.getStatementsInPossession()
					.put(statement.getId(), newOwnedQuantity);
		} else {
			// XXX: might be left as 0?
			account.getStatementsInPossession().remove(statement.getId());
		}
		account.setCointels(account.getCointels() + (statementPrice * quantity));
		usersDao.update(user);
		
		marketMaker.addLog(
				new Transaction(user.getAccount(), statement,
						TradingAction.SELL.toString(), quantity));
		return true;
	}

	@Override
	@GET
	@Path("/check")
	@Produces("application/json")
	public Account checkBankAccount(@QueryParam("userId") String userId) {
		 Account ac = findUserById(userId).getAccount();
		 return ac;
	}

	private User findUserById(String userId) {
		if (userId == null || "".equals(userId)) {
			return null;
		}
		
		return this.usersDao.find(userId);
	}
}
