package eu.cointelligence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-06-11T17:30:03.926+0300")
@StaticMetamodel(Statement.class)
public class Statement_ {
	public static volatile SingularAttribute<Statement, String> title;
	public static volatile SingularAttribute<Statement, String> description1;
	public static volatile SingularAttribute<Statement, String> description2;
	public static volatile SingularAttribute<Statement, String> description3;
	public static volatile SingularAttribute<Statement, String> description4;
	public static volatile SingularAttribute<Statement, Boolean> voteStarted;
	public static volatile SingularAttribute<Statement, Long> currentValue;
	public static volatile SingularAttribute<Statement, Long> id;
}
