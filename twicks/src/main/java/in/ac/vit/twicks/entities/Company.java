package in.ac.vit.twicks.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the company database table.
 * 
 */
@Entity
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "COMPANY_ID_GENERATOR", sequenceName = "COMPANY_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_ID_GENERATOR")
	private Integer id;

	private String address;

	private Date createdon;

	private String name;

	@Temporal(TemporalType.DATE)
	private Date subscriptiondate;

	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "company")
	private List<Product> products;

	// bi-directional many-to-one association to Result
	@OneToMany(mappedBy = "company")
	private List<Result> results;

	// bi-directional many-to-one association to TwicksUser
	@OneToMany(mappedBy = "company")
	private List<User> twicksUsers;

	public Company() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getSubscriptiondate() {
		return this.subscriptiondate;
	}

	public void setSubscriptiondate(Date subscriptiondate) {
		this.subscriptiondate = subscriptiondate;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCompany(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCompany(null);

		return product;
	}

	public List<Result> getResults() {
		return this.results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public Result addResult(Result result) {
		getResults().add(result);
		result.setCompany(this);

		return result;
	}

	public Result removeResult(Result result) {
		getResults().remove(result);
		result.setCompany(null);
		return result;
	}

	public List<User> getTwicksUsers() {
		return this.twicksUsers;
	}

	public void setTwicksUsers(List<User> twicksUsers) {
		this.twicksUsers = twicksUsers;
	}

	public User addTwicksUser(User twicksUser) {
		getTwicksUsers().add(twicksUser);
		twicksUser.setCompany(this);

		return twicksUser;
	}

	public User removeTwicksUser(User twicksUser) {
		getTwicksUsers().remove(twicksUser);
		twicksUser.setCompany(null);

		return twicksUser;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", address=" + address + ", createdon="
				+ createdon + ", name=" + name + ", subscriptiondate="
				+ subscriptiondate + ", products=" + products + ", results="
				+ results + ", twicksUsers=" + twicksUsers + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((createdon == null) ? 0 : createdon.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((results == null) ? 0 : results.hashCode());
		result = prime
				* result
				+ ((subscriptiondate == null) ? 0 : subscriptiondate.hashCode());
		result = prime * result
				+ ((twicksUsers == null) ? 0 : twicksUsers.hashCode());
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
		if (!(obj instanceof Company)) {
			return false;
		}
		Company other = (Company) obj;
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