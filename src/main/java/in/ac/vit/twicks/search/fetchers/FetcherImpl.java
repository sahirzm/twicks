/**
 * This class is used to fetch data from different soruces. Additional sources
 *  can be add by extending this class.
 */
package in.ac.vit.twicks.search.fetchers;

import in.ac.vit.twicks.dataanalysis.naivebayes.Classifier;
import in.ac.vit.twicks.datastorage.service.api.StatusService;
import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.search.statuses.Status;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

/**
 * @author sahir
 * 
 */
public abstract class FetcherImpl implements Fetcher {
	private String endTimeStamp;
	private List<Product> products;
	private String startTimestamp;
	private transient Logger log = Logger.getLogger(this.getClass());

	@Inject
	private StatusService statusService;
	@Inject
	private Classifier classifier;

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
	public abstract List<Status> fetch(Product product);

	protected String getEndTimeStamp() {
		return this.endTimeStamp;
	}

	protected String getStartTimeStamp() {
		return this.startTimestamp;
	}

	protected StatusService getStatusService() {
		return this.statusService;
	}

	@Override
	public void initialize(String startTimeStamp, String endTimeStamp,
			List<Product> products) {
		this.startTimestamp = startTimeStamp;
		this.endTimeStamp = endTimeStamp;
		this.products = products;
	}

	@Override
	public void run() {
		for (Product p : this.products) {
			List<Status> statuses = this.fetch(p);
			statuses = this.classifier.classify(statuses);
			this.log.info("Persisting statuses size = " + statuses.size());
			for (Status status : statuses) {
				this.getStatusService().storeStatus(status);
			}
		}
	}

}