package eu.cointelligence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-04-28T13:13:43.046+0300")
@StaticMetamodel(Account.class)
public class Account_ {
	public static volatile MapAttribute<Account, Statement, Long> statementsInPossession;
	public static volatile SingularAttribute<Account, Long> cointels;
	public static volatile SingularAttribute<Account, Long> id;
}
