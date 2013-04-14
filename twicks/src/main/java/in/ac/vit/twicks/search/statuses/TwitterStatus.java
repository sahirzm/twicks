/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.ac.vit.twicks.search.statuses;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import in.ac.vit.twicks.search.fetchers.Sources;

import com.mongodb.BasicDBObject;

/**
 * 
 * @author
 */
@Entity
@DiscriminatorValue("twitter")
public class TwitterStatus extends Status {

	private String isolanguagecode;

	public TwitterStatus() {
	}

	public TwitterStatus(BasicDBObject obj) {
		this.setId(obj.getInt("id"));
		this.setStatusId(obj.getString("statusId"));
		this.setIsoLanguageCode(obj.getString("isoLanguageCode"));
		this.setProductId(obj.getInt("productId"));
		this.setSource(Sources.valueOf(obj.getString("source")));
		this.setText(obj.getString("text"));
		this.setTimestamp(obj.getString("timestamp"));
	}

	public TwitterStatus(String statusId, String text, String timestamp,
			Integer productId, String isoLanguageCode) {
		super(statusId, text, timestamp, productId);
		this.isolanguagecode = isoLanguageCode;
	}

	@Override
	public BasicDBObject getDBObject() {
		BasicDBObject obj = new BasicDBObject();
		obj.append("id", this.getId());
		obj.append("text", this.getText());
		obj.append("timestamp", this.getTimestamp());
		obj.append("isoLanguageCode", this.getIsoLanguageCode());
		obj.append("source", this.getSource());
		obj.append("productId", this.getProductId());
		return obj;
	}

	public String getIsoLanguageCode() {
		return this.isolanguagecode;
	}

	public void setIsoLanguageCode(String isolanguagecode) {
		this.isolanguagecode = isolanguagecode;
	}

	@Override
	public String toString() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void declareSource() {
		this.setSource(Sources.TWITTER);
	}

}