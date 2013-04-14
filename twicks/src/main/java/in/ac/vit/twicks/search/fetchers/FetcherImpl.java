/**
 * This class is used to fetch data from different soruces. Additional sources
 *  can be add by extending this class.
 */
package in.ac.vit.twicks.search.fetchers;

import in.ac.vit.twicks.datastorage.service.api.StatusService;
import in.ac.vit.twicks.search.statuses.Status;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

/**
 * @author sahir
 * 
 */
public abstract class FetcherImpl implements Fetcher {
	private String endTimeStamp;
	private Map<Integer, String> products;
	private String startTimestamp;
	
	@Inject
	private StatusService statusService;

	public FetcherImpl() {

	}

	/**
	 * This method needs to be implemented by specific fetcher classes.
	 * 
	 * @param productId
	 *            - Id of the product to which this status belongs.
	 * @param keywords
	 *            - List of keywords that are related to this product.
	 * 
	 * @return List of status
	 */
	@Override
	public abstract List<Status> fetch(int productId, String keywords);

	protected String getEndTimeStamp() {
		return this.endTimeStamp;
	}

	protected String getStartTimeStamp() {
		return this.startTimestamp;
	}

	protected StatusService getStatusService() {
		return this.statusService;
	}

	public void initialize(String startTimeStamp, String endTimeStamp,
			Map<Integer, String> products) {
		this.startTimestamp = startTimeStamp;
		this.endTimeStamp = endTimeStamp;
		this.products = products;
	}

	@Override
	public void run() {
		Set<Integer> keys = this.products.keySet();
		Iterator<Integer> it = keys.iterator();
		while (it.hasNext()) {
			Integer key = it.next();
			List<Status> statuses = this.fetch(key, this.products.get(key));
			for (Status status : statuses) {
				this.getStatusService().storeStatus(status);
			}
		}
	}

}