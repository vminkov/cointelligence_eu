package eu.cointelligence.model;

import javax.persistence.*;
import java.util.Map;
import eu.cointelligence.model.Transaction;

@Entity
@Table(name = "T_SHORTSELL")
public class ShortSell {
	
	
	@Id
	private long id;
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

	public void setId(long id) {
		this.id = id;
	}

	public Transaction getTransaction() {
	    return transaction;
	}

	public void setTransaction(Transaction param) {
	    this.transaction = param;
	}

}