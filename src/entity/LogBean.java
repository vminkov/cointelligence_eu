package entity;

public class LogBean {
	private TradingAction actionType;
	private StatementBean statement;
	private Integer quantity;
	
	public LogBean(StatementBean statement, TradingAction action, Integer quantity){
		this.statement = statement;
		this.actionType = action;
		this.quantity = quantity;
	}
	/**
	 * @return the actionType
	 */
	public TradingAction getActionType() {
		return actionType;
	}
	/**
	 * @return the statement
	 */
	public StatementBean getStatement() {
		return statement;
	}	
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
}
