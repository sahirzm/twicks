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

import org.apache.log4j.Logger;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.Location;
import com.restfb.types.Place;
import com.restfb.types.Post;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author sahir,kishan
 *
 */
public class FacebookFetcher extends FetcherImpl implements Fetcher {

    private transient Logger log = Logger.getLogger(getClass());

    @Override
    public List<Status> fetch(Product product) {
        String api_key = "149980745161224";
        String secret = "31f767db8deeb2a723eaf691c49966e6";
        FacebookClient facebookClient;
        String token;
        List<Status> feeds = new ArrayList<>();
        try {
            AccessToken accessToken = new DefaultFacebookClient().obtainAppAccessToken(api_key, secret);
            token = accessToken.getAccessToken();
            facebookClient = new DefaultFacebookClient(token);
            Connection<Post> publicSearch = facebookClient.fetchConnection(
                    "search", Post.class,
                    Parameter.with("since", this.getStartTimeStamp()),
                    Parameter.with("until", this.getEndTimeStamp()),
                    Parameter.with("q", product.getKeywords()));
            
            boolean breakcheck = false;
            long starttimestamp = Long.parseLong(this.getStartTimeStamp());
            Date startd = new Date(starttimestamp);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(starttimestamp);
            while (publicSearch != null) {
                for (int i = 0; i < publicSearch.getData().size(); i++) {
                    FacebookStatus status = new FacebookStatus();
                    Post post = publicSearch.getData().get(i);

                    if (startd.after(post.getCreatedTime())) {
                        breakcheck = true;
                        break;
                    }
                    Location location = post.getPlace().getLocation();
                    if (location != null) {
                        status.setCountry(location.getCountry());
                        status.setLatitude(location.getLatitude());
                        status.setLongitude(location.getLongitude());
                    }
                    status.setStatusId(post.getId());
                    status.setLikes(post.getLikesCount());
                    status.setCreatedOn(new java.util.Date());
                    status.setProduct(product);
                    status.setText(post.getMessage());
                    status.setTimestamp(post.getCreatedTime());
                    feeds.add(status);
                }
                if (breakcheck) {
                    break;
                }
                publicSearch = publicSearch.hasNext() ? facebookClient.fetchConnectionPage(publicSearch.getNextPageUrl(),
                        Post.class) : null;
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return feeds;
    }
}