package eu.cointelligence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-04-28T13:13:43.070+0300")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> fullName;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, String> passwordHash;
	public static volatile SingularAttribute<User, String> role;
	public static volatile SingularAttribute<User, Account> account;
	public static volatile SingularAttribute<User, Long> id;
}
