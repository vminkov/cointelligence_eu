package eu.cointelligence.controller;

import java.util.List;

import eu.cointelligence.controller.entity.TradingAction;
import eu.cointelligence.model.Account;
import eu.cointelligence.model.Statement;
import eu.cointelligence.model.Transaction;
import eu.cointelligence.model.User;

public class Trader implements ITraderService {

	private static ITraderService instance;
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

	public ITraderService getInstance() {
		if (instance == null) {
			instance = new Trader();
		}

		return instance;
	}

	// TODO: javadoc
	/* (non-Javadoc)
	 * @see eu.cointelligence.controller.ITraderService#buy(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public boolean buy(Long userId, Long statementId, Long wantedQuantity) {
		
		return false;
	}

	// TODO: javadoc
	/* (non-Javadoc)
	 * @see eu.cointelligence.controller.ITraderService#sell(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public boolean sell(Long userId, Long statementId, Long quantity) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see eu.cointelligence.controller.ITraderService#checkBankAccount(java.lang.Long)
	 */
	@Override
	public Account checkBankAccount(Long userId) {
		return null;
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
