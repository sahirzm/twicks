/**
 * This is an EJB Service which will be activated after regular time interval to
 * fetch data from all data sources
 *
 */
package in.ac.vit.twicks.search.fetchers;

import in.ac.vit.twicks.datastorage.service.api.ProductService;
import in.ac.vit.twicks.entities.Product;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.apache.log4j.Logger;

/**
 * @author sahir
 * 
 */
@Stateless
public class FetchingService {

	@Inject
	private Instance<Fetcher> fetchers;
	private Logger log = Logger.getLogger(this.getClass());
	@Inject
	private ProductService productService;

	protected ProductService getProductService() {
		return this.productService;
	}

	@Schedule(hour = "*")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void startFetchers() {
		this.log.info("Starting fetcher services...");

		this.log.info("Getting list of products...");
		List<Product> activeProducts = this.getProductService().getAllToFetch();

		Calendar calendar = Calendar.getInstance();

		String endTimeStamp = calendar.getTimeInMillis() + "";
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		String startTimeStamp = calendar.getTimeInMillis() + "";

		Iterator<Fetcher> it = this.fetchers.iterator();
		ExecutorService threads = Executors.newFixedThreadPool(5);
		this.log.info("Starting to fetch data from " + startTimeStamp + " to "
				+ endTimeStamp);
		while (it.hasNext()) {
			Fetcher fetcher = it.next();
			this.log.info("initializing fetcher " + fetcher.getClass());
			fetcher.initialize(startTimeStamp, endTimeStamp, activeProducts);
			threads.submit(fetcher);
		}
		try {
			threads.shutdown();
			while (!threads.awaitTermination(5, TimeUnit.MINUTES)) {
				;

			}
			// TODO Fire Event here saying data fetching complete
			this.log.info("Fetching Complete event fired...");
		} catch (InterruptedException e) {
			this.log.error("Fetching Service interrupted. " + e.getMessage());
		}
	}
}