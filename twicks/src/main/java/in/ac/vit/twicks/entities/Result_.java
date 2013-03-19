package in.ac.vit.twicks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-19T15:49:05.598+0530")
@StaticMetamodel(Result.class)
public class Result_ {
	public static volatile SingularAttribute<Result, Integer> id;
	public static volatile SingularAttribute<Result, Date> createdon;
	public static volatile SingularAttribute<Result, Integer> feedcount;
	public static volatile SingularAttribute<Result, Date> hour;
	public static volatile SingularAttribute<Result, Double> rating;
	public static volatile SingularAttribute<Result, Integer> source;
	public static volatile SingularAttribute<Result, Company> company;
	public static volatile SingularAttribute<Result, Product> product;
}
