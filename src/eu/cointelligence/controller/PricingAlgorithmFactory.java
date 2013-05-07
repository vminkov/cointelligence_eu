package eu.cointelligence.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import eu.cointelligence.controller.entity.TradingAction;
import eu.cointelligence.model.Statement;
import eu.cointelligence.model.Transaction;

public class PricingAlgorithmFactory {

	// TODO: polish it
	public static IPricingAlgorithm getPricingAlgorithm() {
		// a dumb algorithm
		return new IPricingAlgorithm() {

			@Override
			public Map<Long, Long> recalculate(
					Map<Long, Long> oldPrices, List<Transaction> logs) {

				Iterator<Transaction> iterator = logs.iterator();
//				int last10Counter = logs.size() - 10;
//
//				while (last10Counter > 0) {
//					iterator.next();
//					last10Counter--;
//				}

				while (iterator.hasNext()) {
					Transaction log = iterator.next();
					if(log.getCheckedByMarketMaker() != null && log.getCheckedByMarketMaker())
						continue;
					
					log.setCheckedByMarketMaker(new Boolean(true));

					Statement statement = log.getStatement();
					Long price = oldPrices.get(statement.getId());

					if (TradingAction.BUY.toString().equals(log.getOrderType())
							&& price < 100) {
						price += 1;
					} else if (TradingAction.SELL.toString().equals(log.getOrderType())
							&& price > 0) {
						price -= 1;
					}
					oldPrices.put(statement.getId(), price);
					
					
				}
				return oldPrices;
			}
		};
	}
}