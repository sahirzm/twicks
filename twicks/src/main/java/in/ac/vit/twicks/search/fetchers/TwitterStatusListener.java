/**
 *
 */
package in.ac.vit.twicks.search.fetchers;

import java.util.Map;

import in.ac.vit.twicks.dataanalysis.naivebayes.Classifier;
import in.ac.vit.twicks.datastorage.service.api.StatusService;
import in.ac.vit.twicks.entities.Product;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
    private StatusService statusService;
    private Classifier classifier;
    private Map<String, Product> keywords;
    private Executor exec = Executors.newFixedThreadPool(10);

    public TwitterStatusListener(StatusService statusService,
            Classifier classifier, Map<String, Product> keywords) {
        this.statusService = statusService;
        this.classifier = classifier;
        this.keywords = keywords;
    }

    @Override
    public void onException(Exception ex) {
        this.log.fatal(ex.getMessage());
    }

    @Override
    public void onStatus(Status status) {
        TwitterStatusThread thread = new TwitterStatusThread(status,
                statusService, classifier, keywords);
        exec.execute(thread);
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        log.warn("Got a status deletion notice id:"
                + statusDeletionNotice.getStatusId());

    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        log.warn("Got track limitation notice:" + numberOfLimitedStatuses);
    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {
        System.out.println("Got scrub_geo event userId:" + userId
                + " upToStatusId:" + upToStatusId);

    }

    @Override
    public void onStallWarning(StallWarning warning) {
        log.warn("Got stall warning:" + warning);
    }
}