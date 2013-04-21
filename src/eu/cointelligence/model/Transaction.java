package eu.cointelligence.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import eu.cointelligence.model.Statement;

@Entity
@Table(name = "T_TRANSACTION")
public class Transaction {

	@OneToOne
	private Account account;
	@Basic
	private long amount;
	@Basic
	private Long priceAtTrade;
	@Basic
	private String state;
	@Basic
	private String orderType;
	@OneToOne
	private Statement statement;
	@Id
	private Long id;
	
	public Transaction(){}
	
	public Transaction(Account account, Statement statement, String orderType, Long amount) {
		setAccount(account);
		setStatement(statement);
		setOrderType(orderType);
		setAmount(amount);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account param) {
		this.account = param;
	}

	public void setAmount(long param) {
		this.amount = param;
	}

	public long getAmount() {
		return amount;
	}

	public void setPriceAtTrade(Long param) {
		this.priceAtTrade = param;
	}

	public Long getPriceAtTrade() {
		return priceAtTrade;
	}

	public void setState(String param) {
		this.state = param;
	}

	public String getState() {
		return state;
	}

	public void setOrderType(String param) {
		this.orderType = param;
	}

	public String getOrderType() {
		return orderType;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement param) {
		this.statement = param;
	}

	public void setId(Long param) {
		this.id = param;
	}

	public Long getId() {
		return id;
	}

}