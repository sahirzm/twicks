/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.ac.vit.twicks.search.statuses;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.mongodb.BasicDBObject;

import in.ac.vit.twicks.search.fetchers.Sources;

/**
 * 
 * @author kishan
 */
@Entity
@DiscriminatorValue("facebook")
public class FacebookStatus extends Status {

	private Long likes;

	@Override
	protected void declareSource() {
		this.setSource(Sources.FACEBOOK);
	}

	@Override
	public String toString() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	@Override
	public BasicDBObject getDBObject() {
		// TODO Auto-generated method stub
		return null;
	}

}