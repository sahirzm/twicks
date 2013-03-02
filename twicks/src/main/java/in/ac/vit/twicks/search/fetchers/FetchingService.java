/**
 * This is an EJB Service which will be activated
 * after regular time interval to fetch data from all data sources
 * 
 */
package in.ac.vit.twicks.search.fetchers;

import in.ac.vit.twicks.datastorage.service.api.ProductService;
import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.entities.Production;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
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
	@Any
	private Instance<Fetcher> fetchers;
	private Logger logger = Logger.getLogger(this.getClass());

	@Inject
	@Production
	private ProductService productService;

	@Schedule(hour = "*")
	public void startFetchers() {

		Map<Integer, String> products = new HashMap<>();
		List<Product> activeProducts = this.getProductService().getAllToFetch();
		for (Product product : activeProducts) {
			products.put(product.getId(), product.getKeywords());
		}
		Calendar calendar = Calendar.getInstance();

		String startTimeStamp = calendar.getTimeInMillis() + "";
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		String endTimeStamp = calendar.getTimeInMillis() + "";

		Iterator<Fetcher> it = this.fetchers.iterator();
		ExecutorService threads = Executors.newFixedThreadPool(5);
		this.logger.info("Starting to fetch data from " + startTimeStamp
				+ " to " + endTimeStamp);
		while (it.hasNext()) {
			Fetcher fetcher = it.next();
			fetcher.initialize(startTimeStamp, endTimeStamp, products);
			threads.execute(fetcher);
		}
		try {
			while (!threads.awaitTermination(5, TimeUnit.MINUTES)) {
				;
				// TODO Fire Event here saying data fetching complete
			}
		} catch (InterruptedException e) {
			this.logger
			.error("Fetching Service interrupted. " + e.getMessage());
		}
	}

	protected ProductService getProductService() {
		return this.productService;
	}

}