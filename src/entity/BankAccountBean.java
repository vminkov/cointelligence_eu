package entity;

import java.util.HashMap;
import java.util.Map;

public class BankAccountBean {
	private Double cash;
	private Map<StatementBean, Integer> features;
	
	public BankAccountBean(Double initialCash){
		this.setCash(initialCash);
		this.features = new HashMap<StatementBean, Integer>();
	}
	/**
	 * @return the cash
	 */
	public Double getCash() {
		return cash;
	}
	/**
	 * @param cash the cash to set
	 */
	public void setCash(Double cash) {
		this.cash = cash;
	}
	/**
	 * @return the features
	 */
	public Map<StatementBean, Integer> getFeatures() {
		return features;
	}
}
