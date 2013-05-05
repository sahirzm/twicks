/**
 * This is a generic status class that stores id of the post, text and timestamp
 * of the status updates. Additional sources need to extend this class and add
 * specific properties related to that source.
 *
 */
package in.ac.vit.twicks.search.statuses;

import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.search.fetchers.Sources;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author sahir
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
public abstract class Status implements Serializable {

    @Id
    @SequenceGenerator(name = "STATUS_ID_GENERATOR", sequenceName = "status_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STATUS_ID_GENERATOR")
    private Long id;
    @NotNull
    @Size(min = 3)
    private String statusId;
    @ManyToOne
    @NotNull
    private Product product;
    @NotNull
    @Size(min = 1)
    private String text;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date timestamp;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date createdOn;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Sources source;
    @NotNull
    private int score;
    private double latitude;
    private double longitude;
    private String country;
    private String languageCode;

    public Status() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Status(String statusId, String text, Date timestamp, Product product) {
        super();
        this.text = text;
        this.timestamp = timestamp;
        this.product = product;
        this.statusId = statusId;
    }

    /**
     * Returns the id of the status provided by the source
     *
     * @return - id of the status
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Returns the product id to which this status belongs
     *
     * @return - product Id
     */
    public Product getProduct() {
        return this.product;
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
    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSource(Sources source) {
        this.source = source;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public abstract String toString();

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}