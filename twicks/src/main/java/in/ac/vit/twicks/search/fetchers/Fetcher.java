/**
 * 
 */
package in.ac.vit.twicks.search.fetchers;

import in.ac.vit.twicks.search.statuses.Status;

import java.util.List;
import java.util.Map;

/**
 * @author sahir
 *
 */
public interface Fetcher {
	public List<Status> fetch(int productId, String keywords);

	public void initialize(String startTimeStamp, String endTimeStamp,
			Map<Integer, String> products);
}
