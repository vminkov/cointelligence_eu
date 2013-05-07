package eu.cointelligence.controller;

import java.util.List;
import java.util.Map;

import eu.cointelligence.model.Statement;
import eu.cointelligence.model.Transaction;

public class DummyMarketMaker implements IMarketMaker {
	private static IMarketMaker instance;
	private static MarketMakerThread marketMakerThread;
	private static Map<Long, Long> statementsAndPrices;
	private static List<Transaction> logs;

	private class MarketMakerThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				recomputePrices();
			}
		}
	}

	private DummyMarketMaker() {
		if (DummyMarketMaker.marketMakerThread == null) {
			
			System.out.println("THIS SHOULD NOT BE INSTATIATED!!!");
//			DummyMarketMaker.marketMakerThread = new MarketMakerThread();
//			DummyMarketMaker.marketMakerThread.start();
		}
	}

	// singleton
	// TODO javadoc
	public static IMarketMaker getInstance() {
		if (instance == null) {
			instance = new DummyMarketMaker();
		}

		return instance;
	}

	// TODO javadoc
	/* (non-Javadoc)
	 * @see eu.cointelligence.controller.IMarketMaker#getStatementsAndPrices()
	 */
	@Override
	public Map<Long, Long> getStatementsAndPrices() {
		return DummyMarketMaker.statementsAndPrices;
	}

	/* (non-Javadoc)
	 * @see eu.cointelligence.controller.IMarketMaker#getPriceForStatement(java.lang.Long)
	 */
	@Override
	public Long getPriceForStatement(Long statementId) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see eu.cointelligence.controller.IMarketMaker#addLog(eu.cointelligence.model.Transaction)
	 */
	@Override
	public boolean addLog(Transaction log) {
		if (log != null) {
			return logs.add(log);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see eu.cointelligence.controller.IMarketMaker#addStatement(eu.cointelligence.model.Statement)
	 */
	@Override
	public boolean addStatement(Statement statement) {
		if (statement == null) {
			return false;
		}
		DummyMarketMaker.statementsAndPrices.put(statement.getId(), Constants.DEFAULT_PRICE);
		return true;
	}

	 public void recomputePrices() {
		synchronized (logs) {
			PricingAlgorithmFactory.getPricingAlgorithm().recalculate(
					statementsAndPrices, logs);
		}
	}
}