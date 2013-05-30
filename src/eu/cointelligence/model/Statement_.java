package eu.cointelligence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-05-28T20:53:25.314+0300")
@StaticMetamodel(Statement.class)
public class Statement_ {
	public static volatile SingularAttribute<Statement, String> title;
	public static volatile SingularAttribute<Statement, String> description;
	public static volatile SingularAttribute<Statement, String> ownersGroup;
	public static volatile SingularAttribute<Statement, Boolean> voteStarted;
	public static volatile SingularAttribute<Statement, Long> currentValue;
	public static volatile SingularAttribute<Statement, Long> id;
}
