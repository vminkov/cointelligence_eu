package eu.cointelligence.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_TRANSACTION")
public class Transaction {

	@Basic
	private long amount;
	@Basic
	private long priceAtTrade;
	@Basic
	private String state;
	@Basic
	private String orderType;
	@OneToOne
	private Statement statement;
	@Basic
	private Boolean checkedByMarketMaker;
	@ManyToOne
	private Account account;
	@Id
	// @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "TIMESTAMP_FIELD")
	private java.sql.Timestamp timestamp;

	public Transaction() {
	}

	public Transaction(Account account, Statement statement, String orderType,
			Long amount) {
		setAccount(account);
		setStatement(statement);
		setPriceAtTrade(statement.getCurrentValue());
		setOrderType(orderType);
		setAmount(amount.longValue());
		timestamp = new Timestamp(System.currentTimeMillis());
		setId(UUID.randomUUID().getLeastSignificantBits());
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

	public void setCheckedByMarketMaker(Boolean param) {
		this.checkedByMarketMaker = param;
	}

	public Boolean getCheckedByMarketMaker() {
		return checkedByMarketMaker;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account param) {
		this.account = param;
	}

	private void setId(Long param) {
		this.id = param;
	}

	public Long getId() {
		return id;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}