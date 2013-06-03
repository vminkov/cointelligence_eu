package eu.cointelligence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-06-02T22:47:10.118+0300")
@StaticMetamodel(StatementStake.class)
public class StatementStake_ {
	public static volatile SingularAttribute<StatementStake, Long> id;
	public static volatile SingularAttribute<StatementStake, Statement> statement;
	public static volatile SingularAttribute<StatementStake, Long> sharesCount;
	public static volatile SingularAttribute<StatementStake, Account> account;
}
