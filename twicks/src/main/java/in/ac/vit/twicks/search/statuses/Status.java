/**
 * This is a generic status class that stores id of the post, text and timestamp of
 * the status updates. Additional sources need to extend this class and add specific
 * properties related to that source.
 * 
 */
package in.ac.vit.twicks.search.statuses;

import in.ac.vit.twicks.search.fetchers.Sources;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

import com.mongodb.BasicDBObject;

/**
 * @author sahir
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
public abstract class Status {

	@Id
	@SequenceGenerator(name = "STATUS_ID_GENERATOR", sequenceName = "STATUS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STATUS_ID_GENERATOR")
	private Integer id;
	private String statusId;
	private Integer productId;
	private String text;
	private String timestamp;
	private Date createdOn;
	@Enumerated(EnumType.STRING)
	private Sources source;

	public Status() {
		this.declareSource();
	}

	public Status(String statusId, String text, String timestamp,
			Integer productId) {
		super();
		this.text = text;
		this.timestamp = timestamp;
		this.productId = productId;
		this.statusId = statusId;
		this.declareSource();
	}

	public abstract BasicDBObject getDBObject();

	/**
	 * Returns the id of the status provided by the source
	 * 
	 * @return - id of the status
	 */
	public Integer getId() {
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
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
