package entity;

public class StatementBean {
	private String statementText;
	private String id;
	private Double price;
	/**
	 * @return the statementText
	 */
	public String getStatementText() {
		return statementText;
	}
	/**
	 * @param statementText the statementText to set
	 */
	public void setStatementText(String statementText) {
		this.statementText = statementText;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the initialPrice
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param ïrice the initialPrice to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
}
