package controller;

import java.util.List;

import entity.BankAccountBean;
import entity.LogBean;
import entity.StatementBean;
import entity.TradingAction;
import entity.UserBean;

public class Banker {
	private static Banker instance;
	private static BankerThread bankerThread;
	private static List<UserBean> users;
	
	//this thread is not needed for now
	private class BankerThread extends Thread{
		@Override
		public void run(){
			
		}
	}
	
	private Banker(){
		if(bankerThread == null){
			bankerThread = new BankerThread();
			bankerThread.start();
		}
	}
	
	public Banker getInstance(){
		if(instance == null){
			instance = new Banker();
		}
		
		return instance;
	}
	
	//TODO: javadoc
	public boolean buy(String userId, String statementId, Integer wantedQuantity){
		UserBean user = findUserById(userId);
		if (user == null || wantedQuantity == null) {
			return false;
		}
		Double statementPrice = MarketMaker.getInstance().getPriceForStatement(statementId);
		Double availableCash = user.getBankAccount().getCash();
		if((statementPrice == null || availableCash == null) ||
				(availableCash <= statementPrice * wantedQuantity)){
			return false;
		}
		BankAccountBean account = user.getBankAccount();
		StatementBean statement = MarketMaker.findStatementById(statementId);
		
		//do the action
		Integer currentOwnedQuantity = user.getBankAccount().getFeatures().get(statement);
		Integer newOwnedQuantity;
		if(currentOwnedQuantity == null){
			newOwnedQuantity = wantedQuantity;
		}
		else{
			newOwnedQuantity = currentOwnedQuantity + wantedQuantity;
		}
		account.getFeatures().put(statement, newOwnedQuantity);
		account.setCash(availableCash - (statementPrice * wantedQuantity));
		
		MarketMaker.getInstance().addLog(new LogBean(statement, TradingAction.BUY, wantedQuantity));
		return true;
	}
	
	//TODO: javadoc
	public boolean sell(String userId, String statementId, Integer quantity){
		UserBean user = findUserById(userId);
		if (user == null || quantity == null) {
			return false;
		}
		Double statementPrice = MarketMaker.getInstance().getPriceForStatement(statementId);
		BankAccountBean account = user.getBankAccount();
		StatementBean statement = MarketMaker.findStatementById(statementId);
		Integer ownedQuantity = account.getFeatures().get(statement);
		
		if((statementPrice == null || ownedQuantity == null ) || (ownedQuantity < quantity)){
			return false;
		}
		
		//do the action
		Integer newOwnedQuantity;
		newOwnedQuantity = ownedQuantity - quantity;
		if(newOwnedQuantity > 0){
			account.getFeatures().put(statement, newOwnedQuantity);
		}else{
//			XXX: might be left as 0?
			account.getFeatures().remove(statement);
		}
		account.setCash(account.getCash() + (statementPrice * quantity));
		
		MarketMaker.getInstance().addLog(new LogBean(statement, TradingAction.SELL, quantity));
		return true;	
	}
	
	public BankAccountBean checkBankAccount(String userId){
		UserBean user = findUserById(userId);
		if(user == null){
			return null;
		}
		return user.getBankAccount();
	}
	
	private static UserBean findUserById(String userId){
		if(userId == null || "".equals(userId)){
			return null;
		}
		for(UserBean user : users){
			if(user.getId().equals(userId)){
				return user;
			}
		}
		return null;
	}
}
