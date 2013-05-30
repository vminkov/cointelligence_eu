package eu.cointelligence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-05-28T20:53:25.443+0300")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> fullName;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, String> passwordHash;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> roleString;
	public static volatile SingularAttribute<User, Integer> age;
	public static volatile SingularAttribute<User, String> genderString;
	public static volatile SingularAttribute<User, String> department;
	public static volatile SingularAttribute<User, Account> account;
}
