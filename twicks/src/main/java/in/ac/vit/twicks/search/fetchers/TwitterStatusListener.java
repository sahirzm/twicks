/**
 * 
 */
package in.ac.vit.twicks.search.fetchers;

import org.apache.log4j.Logger;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 * @author sahir
 * 
 */
public class TwitterStatusListener implements StatusListener {

	protected Logger log = Logger.getLogger(getClass());

	@Override
	public void onException(Exception ex) {
		this.log.fatal(ex.getMessage());
	}

	@Override
	public void onStatus(Status status) {
		System.out.println("@" + status.getUser().getScreenName() + " - "
				+ status.getText());
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		System.out.println("Got a status deletion notice id:"
				+ statusDeletionNotice.getStatusId());

	}

	@Override
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		System.out.println("Got track limitation notice:"
				+ numberOfLimitedStatuses);
	}

	@Override
	public void onScrubGeo(long userId, long upToStatusId) {
		System.out.println("Got scrub_geo event userId:" + userId
				+ " upToStatusId:" + upToStatusId);

	}

	@Override
	public void onStallWarning(StallWarning warning) {
		System.out.println("Got stall warning:" + warning);
	}

}
