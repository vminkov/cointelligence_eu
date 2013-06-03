package eu.cointelligence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-06-03T05:34:14.279+0300")
@StaticMetamodel(ShortSell.class)
public class ShortSell_ {
	public static volatile SingularAttribute<ShortSell, Long> id;
	public static volatile SingularAttribute<ShortSell, Account> account;
	public static volatile SingularAttribute<ShortSell, Transaction> transaction;
	public static volatile SingularAttribute<ShortSell, Long> amount;
}
