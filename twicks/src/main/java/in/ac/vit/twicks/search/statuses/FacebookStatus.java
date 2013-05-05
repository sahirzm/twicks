/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.ac.vit.twicks.search.statuses;

import in.ac.vit.twicks.search.fetchers.Sources;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author kishan
 */
@Entity
@DiscriminatorValue("facebook")
public class FacebookStatus extends Status {

    private long likes;

    public FacebookStatus() {
        this.setSource(Sources.FACEBOOK);
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }
}