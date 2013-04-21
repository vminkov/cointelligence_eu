package eu.cointelligence.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import eu.cointelligence.model.Account;
import eu.cointelligence.model.Statement;
import eu.cointelligence.model.Transaction;
import eu.cointelligence.model.User;

@Path("/trade")
public class Trader {

	private static Trader instance;
	private static TraderThread traderThread;
	private static List<User> users;

	// this thread is not needed for now
	private class TraderThread extends Thread {
		@Override
		public void run() {

		}
	}

	public Trader() {
		if (traderThread == null) {
			traderThread = new TraderThread();
			traderThread.start();
		}
	}

	public Trader getInstance() {
		if (instance == null) {
			instance = new Trader();
		}

		return instance;
	}

	// TODO: javadoc
	public boolean buy(Long userId, Long statementId, Long wantedQuantity) {
		User user = findUserById(userId);
		if (user == null || wantedQuantity == null) {
			return false;
		}
		Long statementPrice = MarketMaker.getInstance().getPriceForStatement(
				statementId);
		Long availableCash = user.getAccount().getCointels();
		if ((statementPrice == null || availableCash == null)
				|| (availableCash <= statementPrice * wantedQuantity)) {
			return false;
		}
		Account account = user.getAccount();
		Statement statement = MarketMaker.findStatementById(statementId);

		// do the action
		Long currentOwnedQuantity = user.getAccount()
				.getStatementsInPossession().get(statement);
		Long newOwnedQuantity;
		if (currentOwnedQuantity == null) {
			newOwnedQuantity = wantedQuantity;
		} else {
			newOwnedQuantity = currentOwnedQuantity + wantedQuantity;
		}
		account.getStatementsInPossession().put(statement, newOwnedQuantity);
		account.setCointels(availableCash - (statementPrice * wantedQuantity));

		MarketMaker.getInstance().addLog(
				new Transaction(user.getAccount(), statement, TradingAction.BUY
						.toString(), wantedQuantity));
		return true;
	}

	// TODO: javadoc
	public boolean sell(Long userId, Long statementId, Long quantity) {
		User user = findUserById(userId);
		if (user == null || quantity == null) {
			return false;
		}
		Long statementPrice = MarketMaker.getInstance().getPriceForStatement(
				statementId);
		Account account = user.getAccount();
		Statement statement = MarketMaker.findStatementById(statementId);
		Long ownedQuantity = account.getStatementsInPossession().get(statement);

		if ((statementPrice == null || ownedQuantity == null)
				|| (ownedQuantity < quantity)) {
			return false;
		}

		// do the action
		Long newOwnedQuantity;
		newOwnedQuantity = ownedQuantity - quantity;
		if (newOwnedQuantity > 0) {
			account.getStatementsInPossession()
					.put(statement, newOwnedQuantity);
		} else {
			// XXX: might be left as 0?
			account.getStatementsInPossession().remove(statement);
		}
		account.setCointels(account.getCointels() + (statementPrice * quantity));

		MarketMaker.getInstance().addLog(
				new Transaction(user.getAccount(), statement,
						TradingAction.SELL.toString(), quantity));
		return true;
	}

	@GET
	@QueryParam("/{userId}")
//	@Produces("application/json")
	public Response checkBankAccount(@QueryParam("userId") final Long userId) {
//		User user = findUserById(userId);
//		if (user == null) {
//			return null;
//		}
//		return user.getAccount();
	return Response.status(200).entity(userId.toString()).build();
	}

	private static User findUserById(Long userId) {
		if (userId == null || "".equals(userId)) {
			return null;
		}
		for (User user : users) {
			if (user.getId() == userId) {
				return user;
			}
		}
		return null;
	}
}
