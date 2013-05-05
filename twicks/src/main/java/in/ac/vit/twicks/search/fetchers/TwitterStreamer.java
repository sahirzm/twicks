/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.ac.vit.twicks.search.fetchers;

import in.ac.vit.twicks.dataanalysis.naivebayes.Classifier;
import in.ac.vit.twicks.datastorage.service.api.ProductService;
import in.ac.vit.twicks.datastorage.service.api.StatusService;
import in.ac.vit.twicks.entities.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/**
 *
 * @author sahir
 */
@Singleton
public class TwitterStreamer {

    @Inject
    private Classifier classifier;
    @Inject
    private ProductService productService;
    @Inject
    private StatusService statusService;
    private transient Logger log = Logger.getLogger(getClass());
    private TwitterStream twitterStream;

    public void startStreaming() {
        log.info("Starting twitter streaming...");
        twitterStream = new TwitterStreamFactory().getInstance();
        List<Product> activeProducts = this.productService.getAllToFetch();
        Map<String, Product> keywords = new HashMap<>();
        Set<String> keys = keywords.keySet();
        for (Product p : activeProducts) {
            String[] words = p.getKeywords().split(",");
            for (String word : words) {
                keywords.put(word.trim(), p);
            }
        }
        String[] track = keys.toArray(new String[0]);
        FilterQuery query = new FilterQuery();
        query.track(track);
        TwitterStatusListener listener = new TwitterStatusListener(
                statusService, classifier, keywords);
        twitterStream.addListener(listener);
        if (track.length == 0) {
            log.warn("Twitter streaming not started as no products found...");
        } else {
            twitterStream.filter(query);
            log.info("Twitter Streaming successfully started...");
        }
    }

    public void cleanUp() {
        log.info("cleaning up");
        if (twitterStream != null) {
            twitterStream.cleanUp();
        }
    }

    public void refreshProductList() {
        if (twitterStream != null) {
        } else {
            log.warn("Twitter stream is null so cannot add product...");
        }
    }
}
