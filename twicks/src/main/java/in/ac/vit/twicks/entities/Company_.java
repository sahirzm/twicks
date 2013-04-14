package in.ac.vit.twicks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-04-14T15:40:03.651+0530")
@StaticMetamodel(Company.class)
public class Company_ {
	public static volatile SingularAttribute<Company, Integer> id;
	public static volatile SingularAttribute<Company, String> address;
	public static volatile SingularAttribute<Company, Date> createdon;
	public static volatile SingularAttribute<Company, String> name;
	public static volatile SingularAttribute<Company, Date> subscriptionDate;
	public static volatile ListAttribute<Company, Product> products;
	public static volatile ListAttribute<Company, Result> results;
	public static volatile ListAttribute<Company, User> twicksUsers;
}
