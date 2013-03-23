/**
 * This is a generic status class that stores id of the post, text and timestamp of
 * the status updates. Additional sources need to extend this class and add specific
 * properties related to that source.
 * 
 */
package in.ac.vit.twicks.search.statuses;

import in.ac.vit.twicks.search.fetchers.Sources;

import com.mongodb.BasicDBObject;

/**
 * @author sahir
 * 
 */
public abstract class Status {

	private String id;
	private Integer productId;
	private Sources source;
	private String text;
	private String timestamp;

	public Status() {
		this.declareSource();
	}

	public Status(String id, String text, String timestamp, Integer productId) {
		super();
		this.id = id;
		this.text = text;
		this.timestamp = timestamp;
		this.productId = productId;
		this.declareSource();
	}

	public abstract BasicDBObject getDBObject();

	/**
	 * Returns the id of the status provided by the source
	 * 
	 * @return - id of the status
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Returns the product id to which this status belongs
	 * 
	 * @return - product Id
	 */
	public Integer getProductId() {
		return this.productId;
	}

	/**
	 * Returns the source from where data is fetched.
	 * 
	 * @return - Source
	 */
	public Sources getSource() {
		return this.source;
	}

	/**
	 * Returns the id of data source
	 * 
	 * @return - id of data soruce
	 */
	public int getSourceId() {
		return this.getSource().ordinal();
	}

	/**
	 * Returns the text of the status update
	 * 
	 * @return - text of the status
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Returns the time when status was updated.
	 * 
	 * @return - timestamp of the status update
	 */
	public String getTimestamp() {
		return this.timestamp;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setSource(Sources source) {
		this.source = source;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public abstract String toString();
	/**
	 * Set the Source by implementing this method
	 * 
	 */
	protected abstract void declareSource();
}
