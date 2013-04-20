package controller;

import java.util.List;
import java.util.Map;

import entity.LogBean;
import entity.StatementBean;
/**
 * Singleton
 * TODO javadoc
 * @author vminkov
 *
 */
public class MarketMaker {
	private static MarketMaker instance;
	private static MarketMakerThread bankerThread;
	private static Map<StatementBean, Double> statementsAndPrices;
	private static List<LogBean> logs;
	
	private class MarketMakerThread extends Thread{
		@Override
		public void run(){
			while(true){
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				recomputePrices();
			}
		}
	}
	
	private MarketMaker(){
		if(MarketMaker.bankerThread == null){
			MarketMaker.bankerThread = new MarketMakerThread();
			MarketMaker.bankerThread.start();
		}
	}
	//singleton
	//TODO javadoc
	public static MarketMaker getInstance(){
		if(instance == null){
			instance = new MarketMaker();
		}
		
		return instance;
	}
	//TODO javadoc
	public Map<StatementBean, Double> getStatementsAndPrices(){
		return MarketMaker.statementsAndPrices;
	}
	
	public Double getPriceForStatement(String statementId){
		StatementBean statement = findStatementById(statementId);
		if(statement == null){
			return null;
		}
		
		return statement.getPrice();
	}
	
	public static StatementBean findStatementById(String statementId) {
		if(statementId == null || "".equals(statementId)){
			return null;
		}
		
		for(StatementBean statement : statementsAndPrices.keySet()){
			if(statement.getId().equals(statementId)){
				return statement;
			}
		}
		return null;
	}
	public boolean addLog(LogBean log){
		if(log != null){
			return logs.add(log);		
		}
		return false;
	}
	
	public boolean addStatement(StatementBean statement){
		if(statement == null){
			return false;
		}
		MarketMaker.statementsAndPrices.put(statement, Constants.DEFAULT_PRICE);
		return true;
	}
	
	private static void recomputePrices(){
		synchronized (logs) {
			PricingAlgorithmFactory.getPricingAlgorithm().recalculate(statementsAndPrices, logs);
		}
	}
}
