package eu.cointelligence.model;

import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_SHORTSELL")
public class ShortSell {
	
	
	@Id
	private long id;
	@ManyToOne 
	private Account account;
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public ShortSell(){
		id = UUID.randomUUID().getLeastSignificantBits();
	}


	@OneToOne
	private Transaction transaction; 
	//TODO: better name
	@Basic
	private long amount;
	
	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public Transaction getTransaction() {
	    return transaction;
	}

	public void setTransaction(Transaction param) {
	    this.transaction = param;
	}

}