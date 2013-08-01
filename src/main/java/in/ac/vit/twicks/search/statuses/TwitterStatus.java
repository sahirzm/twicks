/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.ac.vit.twicks.search.statuses;

import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.search.fetchers.Sources;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *
 * @author
 */
@Entity
@DiscriminatorValue("twitter")
public class TwitterStatus extends Status {

    @NotNull
    private long retweetCount;

    public TwitterStatus() {
        this.setSource(Sources.TWITTER);
    }

    public TwitterStatus(String statusId, String text, Date timestamp,
            Product product) {
        super(statusId, text, timestamp, product);
        this.setSource(Sources.TWITTER);
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(long l) {
        this.retweetCount = l;
    }
}