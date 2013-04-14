package in.ac.vit.twicks.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the product database table.
 * 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "productsByCompanyId", query = "SELECT p FROM Product p where p.company.id = :companyId") })
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PRODUCT_ID_GENERATOR", sequenceName = "product_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ID_GENERATOR")
	private Integer id;

	private Date createdon;

	private String keywords;

	private String name;

	// bi-directional many-to-one association to Company
	@ManyToOne
	private Company company;

	// bi-directional many-to-one association to Result
	@OneToMany(mappedBy = "product")
	private List<Result> results;

	public Product() {
	}

	public Result addResult(Result result) {
		this.getResults().add(result);
		result.setProduct(this);

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

	public List<Result> getResults() {
		return this.results;
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
		result = (prime * result)
				+ ((this.results == null) ? 0 : this.results.hashCode());
		return result;
	}

	public Result removeResult(Result result) {
		this.getResults().remove(result);
		result.setProduct(null);

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

	public void setResults(List<Result> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "Product [id=" + this.id + ", createdon=" + this.createdon
				+ ", keywords=" + this.keywords + ", name=" + this.name
				+ ", company=" + this.company + ", results=" + this.results
				+ "]";
	}

}