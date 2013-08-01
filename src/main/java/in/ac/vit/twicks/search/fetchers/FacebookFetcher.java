/**
 *
 */
package in.ac.vit.twicks.search.fetchers;

import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.search.statuses.FacebookStatus;
import in.ac.vit.twicks.search.statuses.Status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.Location;
import com.restfb.types.Place;
import com.restfb.types.Post;

/**
 * @author sahir,kishan
 * 
 */
public class FacebookFetcher extends FetcherImpl implements Fetcher {

	private transient Logger log = Logger.getLogger(this.getClass());

	@Override
	public List<Status> fetch(Product product) {
		String api_key = "149980745161224";
		String secret = "31f767db8deeb2a723eaf691c49966e6";
		FacebookClient facebookClient;
		String token;
		List<Status> feeds = new ArrayList<>();

		AccessToken accessToken = new DefaultFacebookClient()
				.obtainAppAccessToken(api_key, secret);
		token = accessToken.getAccessToken();
		facebookClient = new DefaultFacebookClient(token);
		String[] keywords = product.getKeywords().split(",");
		long starttimestamp = Long.parseLong(this.getStartTimeStamp());
		Date startd = new Date(starttimestamp);
		this.log.debug("No of Keywords are " + keywords.length);
		for (String keyword : keywords) {
			if (StringUtils.isEmpty(keyword)) {
				continue;
			}
			Connection<Post> publicSearch = facebookClient.fetchConnection(
					"search", Post.class,
					// Parameter.with("since", this.getStartTimeStamp()),
					// Parameter.with("until", this.getEndTimeStamp()),
					Parameter.with("q", keyword.trim()));
			this.log.debug("Fetching for keyword " + keyword + " => count "
					+ publicSearch.getData().size());
			boolean breakcheck = false;

			while (publicSearch != null) {
				for (int i = 0; i < publicSearch.getData().size(); i++) {
					FacebookStatus status = new FacebookStatus();
					Post post = publicSearch.getData().get(i);

					if (startd.after(post.getCreatedTime())) {
						this.log.info("Breaking facebook fetcher as obtained required posts");
						breakcheck = true;
						break;
					}
					Place place = post.getPlace();
					if (place != null) {
						Location location = post.getPlace().getLocation();
						if (location != null) {
							status.setCountry(location.getCountry());
							status.setLatitude(location.getLatitude());
							status.setLongitude(location.getLongitude());
						}
					}
					status.setStatusId(post.getId());
					status.setLikes(post.getLikesCount() != null ? post
							.getLikesCount() : 0);
					status.setCreatedOn(new java.util.Date());
					status.setProduct(product);
					status.setText(post.getMessage());
					status.setTimestamp(post.getCreatedTime());
					feeds.add(status);
				}
				if (breakcheck) {
					break;
				}
				publicSearch = publicSearch.hasNext() ? facebookClient
						.fetchConnectionPage(publicSearch.getNextPageUrl(),
								Post.class) : null;
			}
		}

		return feeds;
	}
}