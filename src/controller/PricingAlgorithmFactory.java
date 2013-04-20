package controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import entity.LogBean;
import entity.StatementBean;
import entity.TradingAction;

public class PricingAlgorithmFactory {
	
	//TODO: polish it
	public static IPricingAlgorithm getPricingAlgorithm(){
		//a dumb algorithm
		return new IPricingAlgorithm() {
			
			@Override
			public Map<StatementBean, Double> recalculate(
					Map<StatementBean, Double> oldPrices, List<LogBean> logs) {
				
				Iterator<LogBean> iterator = logs.iterator();
				int last10Counter = logs.size() - 10;
				
				while(last10Counter > 0){
					iterator.next();
					last10Counter--;
				}
				
				while(iterator.hasNext()) {
					LogBean log = iterator.next();
									
					StatementBean statement = log.getStatement();
					Double price = oldPrices.get(statement);
					
					if(log.getActionType().equals(TradingAction.BUY) && price < 1.0){
						price += 0.01;
						oldPrices.put(statement, price);
					}else if(log.getActionType().equals(TradingAction.SELL) && price > 0.0){
						price -= 0.01;
						oldPrices.put(statement, price);
					}
				}
				return oldPrices;
			}
		};
	}
}
