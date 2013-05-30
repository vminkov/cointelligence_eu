package eu.cointelligence.controller.entity.beans;

public class Order {

	private long id;
	private long itemPrice;
	private String amount;
	private String type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setItemPrice(long param) {
		this.itemPrice = param;
	}

	public long getItemPrice() {
		return itemPrice;
	}

	public void setTradedCount(String param) {
		this.amount = param;
	}

	public String getTradedCount() {
		return amount;
	}

	public void setType(String param) {
		this.type = param;
	}

	public String getType() {
		return type;
	}

}