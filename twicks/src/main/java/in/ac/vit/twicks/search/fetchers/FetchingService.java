/** 
 * This is an EJB Service which will be activated 
 * after regular time interval to fetch data from all data sources
 * 
 */
package in.ac.vit.twicks.search.fetchers;

import java.util.HashMap;
import java.util.Iterator;
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
	private Logger logger = Logger.getLogger(getClass());

	@Schedule(hour="*")
	public void startFetchers() {
		// TODO: get list of products with keywords and timestamps

		Map<Integer, String> products = new HashMap<>();
		String startTimeStamp = "";
		String endTimeStamp = "";

		Iterator<Fetcher> it = fetchers.iterator();
		ExecutorService threads = Executors.newFixedThreadPool(5);
		logger.info("Starting to fetch data from " + startTimeStamp + " to "
				+ endTimeStamp);
		while (it.hasNext()) {
			Fetcher fetcher = it.next();
			fetcher.initialize(startTimeStamp, endTimeStamp, products);
			threads.execute(fetcher);
		}
		try {
			while (!threads.awaitTermination(5, TimeUnit.MINUTES))
				;
			// TODO Fire Event here saying data fetching complete
		} catch (InterruptedException e) {
			logger.error("Fetching Service interrupted. " + e.getMessage());
		}
	}
}