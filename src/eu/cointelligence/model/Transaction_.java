package eu.cointelligence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-06-01T15:39:02.241+0300")
@StaticMetamodel(Transaction.class)
public class Transaction_ {
	public static volatile SingularAttribute<Transaction, Long> amount;
	public static volatile SingularAttribute<Transaction, Long> priceAtTrade;
	public static volatile SingularAttribute<Transaction, String> state;
	public static volatile SingularAttribute<Transaction, String> orderType;
	public static volatile SingularAttribute<Transaction, Statement> statement;
	public static volatile SingularAttribute<Transaction, Boolean> checkedByMarketMaker;
	public static volatile SingularAttribute<Transaction, Account> account;
	public static volatile SingularAttribute<Transaction, Long> id;
}
