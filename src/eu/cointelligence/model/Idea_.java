package eu.cointelligence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-06-02T04:43:24.468+0300")
@StaticMetamodel(Idea.class)
public class Idea_ {
	public static volatile SingularAttribute<Idea, Long> id;
	public static volatile SingularAttribute<Idea, String> title;
	public static volatile SingularAttribute<Idea, String> description;
	public static volatile SingularAttribute<Idea, String> company;
	public static volatile SingularAttribute<Idea, User> user;
}
