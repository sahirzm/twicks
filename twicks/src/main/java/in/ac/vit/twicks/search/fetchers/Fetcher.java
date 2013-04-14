/**
 * 
 */
package in.ac.vit.twicks.search.fetchers;

import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.search.statuses.Status;

import java.util.List;

/**
 * @author sahir
 *
 */
public interface Fetcher extends Runnable {
	public List<Status> fetch(Product product);

	public void initialize(String startTimeStamp, String endTimeStamp,
			List<Product> products);

}
