package eu.cointelligence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-06-02T22:41:03.408+0300")
@StaticMetamodel(Account.class)
public class Account_ {
	public static volatile SingularAttribute<Account, Long> cointels;
	public static volatile CollectionAttribute<Account, StatementStake> statementsStakes;
	public static volatile SingularAttribute<Account, Long> id;
	public static volatile CollectionAttribute<Account, Idea> ideas;
	public static volatile CollectionAttribute<Account, Transaction> transactions;
	public static volatile CollectionAttribute<Account, ShortSell> shortSells;
}
