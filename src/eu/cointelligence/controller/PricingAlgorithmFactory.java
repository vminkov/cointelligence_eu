package eu.cointelligence.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import eu.cointelligence.model.Statement;
import eu.cointelligence.model.Transaction;

public class PricingAlgorithmFactory {

	// TODO: polish it
	public static IPricingAlgorithm getPricingAlgorithm() {
		// a dumb algorithm
		return new IPricingAlgorithm() {

			@Override
			public Map<Statement, Long> recalculate(
					Map<Statement, Long> oldPrices, List<Transaction> logs) {

				Iterator<Transaction> iterator = logs.iterator();
				int last10Counter = logs.size() - 10;

				while (last10Counter > 0) {
					iterator.next();
					last10Counter--;
				}

				while (iterator.hasNext()) {
					Transaction log = iterator.next();

					Statement statement = log.getStatement();
					Long price = oldPrices.get(statement);

					if (log.getOrderType().equals(TradingAction.BUY.toString())
							&& price < 100) {
						price += 1;
						oldPrices.put(statement, price);
					} else if (log.getOrderType().equals(TradingAction.SELL.toString())
							&& price > 0) {
						price -= 1;
						oldPrices.put(statement, price);
					}
				}
				return oldPrices;
			}
		};
	}
}