package in.ac.vit.twicks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-19T15:49:05.596+0530")
@StaticMetamodel(Product.class)
public class Product_ {
	public static volatile SingularAttribute<Product, Integer> id;
	public static volatile SingularAttribute<Product, Date> createdon;
	public static volatile SingularAttribute<Product, String> keywords;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, Company> company;
	public static volatile ListAttribute<Product, Result> results;
}
