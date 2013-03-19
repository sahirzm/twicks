package in.ac.vit.twicks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-19T15:49:05.600+0530")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> address;
	public static volatile SingularAttribute<User, Date> createdon;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SingularAttribute<User, Date> lastloggedin;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile SingularAttribute<User, String> middlename;
	public static volatile SingularAttribute<User, String> mobileno;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> role;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, Company> company;
}
