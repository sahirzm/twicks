/**
 * 
 */
package in.ac.vit.twicks.search.fetchers;

import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.search.statuses.FacebookStatus;
import in.ac.vit.twicks.search.statuses.Status;

import java.util.ArrayList;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.Post;

/**
 * @author sahir
 * 
 */
public class FacebookFetcher extends FetcherImpl implements Fetcher {

	@Override
	public List<Status> fetch(Product product) {
		String api_key = "149980745161224";
		String secret = "31f767db8deeb2a723eaf691c49966e6";
		FacebookClient facebookClient;
		String token;
		List<Status> feeds = new ArrayList<>();
		try {
			AccessToken accessToken = new DefaultFacebookClient()
					.obtainAppAccessToken(api_key, secret);
			token = accessToken.getAccessToken();
			facebookClient = new DefaultFacebookClient(token);
			Connection<Post> publicSearch = facebookClient.fetchConnection(
					"search", Post.class,
					Parameter.with("since", this.getStartTimeStamp()),
					Parameter.with("until", this.getEndTimeStamp()),
					Parameter.with("q", product.getKeywords()));

			while (publicSearch != null) {
				for (int i = 0; i < publicSearch.getData().size(); i++) {
					FacebookStatus status = new FacebookStatus();
					Post post = publicSearch.getData().get(i);
					status.setStatusId(post.getId());
					status.setLikes(post.getLikesCount());
					status.setCreatedOn(new java.util.Date());
					status.setProduct(product);
					status.setText(post.getMessage());
					status.setTimestamp(post.getCreatedTime());
					feeds.add(status);
				}
				publicSearch = publicSearch.hasNext() ? facebookClient
						.fetchConnectionPage(publicSearch.getNextPageUrl(),
								Post.class) : null;
			}

		} catch (Exception e) {
		}
		return feeds;
	}

}
