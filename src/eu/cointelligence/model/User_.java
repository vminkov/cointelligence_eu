package eu.cointelligence.model;

import eu.cointelligence.controller.entity.Gender;
import eu.cointelligence.controller.users.UserRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-05-25T17:04:30.185+0300")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> fullName;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, String> passwordHash;
	public static volatile SingularAttribute<User, UserRole> role;
	public static volatile SingularAttribute<User, Integer> age;
	public static volatile SingularAttribute<User, Gender> gender;
	public static volatile SingularAttribute<User, String> department;
	public static volatile SingularAttribute<User, Account> account;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> email;
}
