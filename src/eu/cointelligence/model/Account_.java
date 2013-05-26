package eu.cointelligence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-05-14T05:11:22.802+0300")
@StaticMetamodel(Account.class)
public class Account_ {
	public static volatile SingularAttribute<Account, Long> cointels;
	public static volatile MapAttribute<Account, String, Long> statementsInPossession;
	public static volatile SingularAttribute<Account, String> id;
}
