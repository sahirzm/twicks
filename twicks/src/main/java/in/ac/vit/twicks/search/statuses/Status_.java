package in.ac.vit.twicks.search.statuses;

import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.search.fetchers.Sources;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-04-14T21:45:19.773+0530")
@StaticMetamodel(Status.class)
public class Status_ {
	public static volatile SingularAttribute<Status, Integer> id;
	public static volatile SingularAttribute<Status, String> statusId;
	public static volatile SingularAttribute<Status, String> text;
	public static volatile SingularAttribute<Status, Date> timestamp;
	public static volatile SingularAttribute<Status, Date> createdOn;
	public static volatile SingularAttribute<Status, Sources> source;
	public static volatile SingularAttribute<Status, Product> product;
}
