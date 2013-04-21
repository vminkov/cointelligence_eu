package eu.cointelligence.controller;

import java.util.List;
import java.util.Map;

import eu.cointelligence.model.Statement;
import eu.cointelligence.model.Transaction;

public class MarketMaker {
	private static MarketMaker instance;
	private static MarketMakerThread marketMakerThread;
	private static Map<Statement, Long> statementsAndPrices;
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

	private MarketMaker() {
		if (MarketMaker.marketMakerThread == null) {
			MarketMaker.marketMakerThread = new MarketMakerThread();
			MarketMaker.marketMakerThread.start();
		}
	}

	// singleton
	// TODO javadoc
	public static MarketMaker getInstance() {
		if (instance == null) {
			instance = new MarketMaker();
		}

		return instance;
	}

	// TODO javadoc
	public Map<Statement, Long> getStatementsAndPrices() {
		return MarketMaker.statementsAndPrices;
	}

	public Long getPriceForStatement(Long statementId) {
		Statement statement = findStatementById(statementId);
		if (statement == null) {
			return null;
		}

		return statement.getCurrentValue();
	}

	public static Statement findStatementById(Long statementId) {
		if (statementId == null || "".equals(statementId)) {
			return null;
		}

		for (Statement statement : statementsAndPrices.keySet()) {
			if (statement.getId().equals(statementId)) {
				return statement;
			}
		}
		return null;
	}

	public boolean addLog(Transaction log) {
		if (log != null) {
			return logs.add(log);
		}
		return false;
	}

	public boolean addStatement(Statement statement) {
		if (statement == null) {
			return false;
		}
		MarketMaker.statementsAndPrices.put(statement, Constants.DEFAULT_PRICE);
		return true;
	}

	private static void recomputePrices() {
		synchronized (logs) {
			PricingAlgorithmFactory.getPricingAlgorithm().recalculate(
					statementsAndPrices, logs);
		}
	}
}