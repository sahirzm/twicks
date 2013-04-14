/**
 * 
 */
package in.ac.vit.twicks.search.fetchers;

import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.search.statuses.Status;

import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * @author sahir
 * 
 */
@Stateless
public class TwitterFetcher extends FetcherImpl implements Fetcher {

	private Logger log = Logger.getLogger(this.getClass());
	@Override
	public List<Status> fetch(Product product) {
		log.info("Twitter Fetcher Started");
		Twitter twitter = new TwitterFactory().getInstance();
		long sinceId = 123456;
		try {
			Query query = new Query("samsung");
			query.setCount(200);
			query.setSince("2013-01-01");
			query.setUntil("2013-03-03");
			query.setSinceId(sinceId);
			QueryResult result = twitter.search(query);
			while (result.getCount() != 0) {
				System.out.println("Count : " + result.getTweets().size());
				for (twitter4j.Status tweet : result.getTweets()) {
					System.out.println("@" + tweet.getId() + ":"
							+ tweet.getText() + ":" + tweet.getCreatedAt());
					sinceId = tweet.getId();
				}
				query.setSinceId(sinceId);
				result = twitter.search(query);
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
