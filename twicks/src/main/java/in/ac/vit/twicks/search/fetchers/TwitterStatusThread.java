package in.ac.vit.twicks.search.fetchers;

import in.ac.vit.twicks.dataanalysis.naivebayes.Classifier;
import in.ac.vit.twicks.datastorage.service.api.StatusService;
import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.search.statuses.TwitterStatus;
import java.util.*;
import org.apache.log4j.Logger;
import twitter4j.GeoLocation;
import twitter4j.Place;
import twitter4j.Status;

public class TwitterStatusThread extends Thread {

    private StatusService statusService;
    private Status status;
    private Classifier classifier;
    private Map<String, Product> keywords;
    private transient Logger log = Logger.getLogger(getClass());

    public TwitterStatusThread(Status status, StatusService statusService,
            Classifier classifier, Map<String, Product> keywords) {
        this.statusService = statusService;
        this.status = status;
        this.classifier = classifier;
        this.keywords = keywords;
    }

    @Override
    public void run() {

        // create new twitter status
        TwitterStatus tStatus = new TwitterStatus();
        tStatus.setCreatedOn(new Date());
        tStatus.setLanguageCode(status.getIsoLanguageCode());
        Place place = status.getPlace();
        if (place != null) {
            tStatus.setCountry(place.getCountry());
        }

        GeoLocation geo = status.getGeoLocation();
        if (geo != null) {
            tStatus.setLatitude(geo.getLatitude());
            tStatus.setLongitude(geo.getLongitude());
        }

        tStatus.setRetweetCount(status.getRetweetCount());
        tStatus.setStatusId(status.getId() + "");
        tStatus.setText(status.getText());
        tStatus.setTimestamp(status.getCreatedAt());

        // search for product tweet belongs and assign productId
        Set<String> keys = keywords.keySet();
        for (String key : keys) {
            if (tStatus.getText().toLowerCase().contains(key.toLowerCase())) {
                tStatus.setProduct(keywords.get(key));
                break;
            }
        }

        if (tStatus.getProduct() == null) {
            log.info("No Matching product found ==> " + tStatus.getText());
            return;
        }
        // classify tweet
        List<in.ac.vit.twicks.search.statuses.Status> statuses = new ArrayList<>(
                1);
        statuses.add(tStatus);
        statuses = this.classifier.classify(statuses);
        // persist the tweet
        this.statusService.storeStatus(statuses.get(0));
    }
}