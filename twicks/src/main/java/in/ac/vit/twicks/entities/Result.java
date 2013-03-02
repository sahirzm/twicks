package in.ac.vit.twicks.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the result database table.
 * 
 */
@Entity
public class Result implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "RESULT_ID_GENERATOR", sequenceName = "RESULT_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESULT_ID_GENERATOR")
	private Integer id;

	private Date createdon;

	private Integer feedcount;

	private Date hour;

	private Double rating;

	private Integer source;

	// bi-directional many-to-one association to Company
	@ManyToOne
	private Company company;

	// bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	public Result() {

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public Integer getFeedcount() {
		return this.feedcount;
	}

	public void setFeedcount(Integer feedcount) {
		this.feedcount = feedcount;
	}

	public Date getHour() {
		return this.hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public Double getRating() {
		return this.rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Result [id=" + id + ", createdon=" + createdon + ", feedcount="
				+ feedcount + ", hour=" + hour + ", rating=" + rating
				+ ", source=" + source + ", company=" + company + ", product="
				+ product + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((createdon == null) ? 0 : createdon.hashCode());
		result = prime * result
				+ ((feedcount == null) ? 0 : feedcount.hashCode());
		result = prime * result + ((hour == null) ? 0 : hour.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Result)) {
			return false;
		}
		Result other = (Result) obj;

		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}