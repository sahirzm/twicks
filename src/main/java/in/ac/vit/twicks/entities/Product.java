package in.ac.vit.twicks.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "productsByCompanyId", query = "SELECT p FROM Product p where p.company.id = :companyId") })
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "PRODUCT_ID_GENERATOR", sequenceName = "product_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ID_GENERATOR")
	private Integer id;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdon;
	@NotNull
	@Size(min = 2)
	private String keywords;
	@NotNull
	@Size(min = 2)
	private String name;
	// bi-directional many-to-one association to Company
	@ManyToOne
	@NotNull
	private Company company;

	public Product() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}

		return true;
	}

	public Company getCompany() {
		return this.company;
	}

	public Date getCreatedon() {
		return this.createdon;
	}

	public Integer getId() {
		return this.id;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.company == null) ? 0 : this.company.hashCode());
		result = (prime * result)
				+ ((this.createdon == null) ? 0 : this.createdon.hashCode());
		result = (prime * result)
				+ ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result)
				+ ((this.keywords == null) ? 0 : this.keywords.hashCode());
		result = (prime * result)
				+ ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Product [id=" + this.id + ", createdon=" + this.createdon
				+ ", keywords=" + this.keywords + ", name=" + this.name
				+ ", company=" + this.company + "]";
	}
}