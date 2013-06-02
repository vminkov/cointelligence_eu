package eu.cointelligence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-06-02T06:30:43.659+0300")
@StaticMetamodel(ShortSell.class)
public class ShortSell_ {
	public static volatile SingularAttribute<ShortSell, Long> id;
	public static volatile SingularAttribute<ShortSell, Transaction> transaction;
	public static volatile SingularAttribute<ShortSell, Long> amount;
}
