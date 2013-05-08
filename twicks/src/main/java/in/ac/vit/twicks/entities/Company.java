package in.ac.vit.twicks.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "companiesBySubDate", query = "SELECT c FROM Company c WHERE c.subscriptionDate >= :date") })
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "COMPANY_ID_GENERATOR", sequenceName = "company_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_ID_GENERATOR")
	private Integer id;
	private String address;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdon;
	@NotNull
	@Size(min = 2)
	private String name;
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date subscriptionDate;
	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "company")
	private List<Product> products;
	// bi-directional many-to-one association to TwicksUser
	@OneToMany(mappedBy = "company")
	private List<User> twicksUsers;

	public Company() {
	}

	public Product addProduct(Product product) {
		this.getProducts().add(product);
		product.setCompany(this);

		return product;
	}

	public User addTwicksUser(User twicksUser) {
		this.getTwicksUsers().add(twicksUser);
		twicksUser.setCompany(this);

		return twicksUser;
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
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String getAddress() {
		return this.address;
	}

	public Date getCreatedon() {
		return this.createdon;
	}

	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public Date getSubscriptionDate() {
		return this.subscriptionDate;
	}

	public List<User> getTwicksUsers() {
		return this.twicksUsers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.address == null) ? 0 : this.address.hashCode());
		result = (prime * result)
				+ ((this.createdon == null) ? 0 : this.createdon.hashCode());
		result = (prime * result)
				+ ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result)
				+ ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result)
				+ ((this.products == null) ? 0 : this.products.hashCode());
		result = (prime * result)
				+ ((this.subscriptionDate == null) ? 0 : this.subscriptionDate
						.hashCode());
		result = (prime * result)
				+ ((this.twicksUsers == null) ? 0 : this.twicksUsers.hashCode());
		return result;
	}

	public Product removeProduct(Product product) {
		this.getProducts().remove(product);
		product.setCompany(null);

		return product;
	}

	public User removeTwicksUser(User twicksUser) {
		this.getTwicksUsers().remove(twicksUser);
		twicksUser.setCompany(null);

		return twicksUser;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setSubscriptionDate(Date subscriptiondate) {
		this.subscriptionDate = subscriptiondate;
	}

	public void setTwicksUsers(List<User> twicksUsers) {
		this.twicksUsers = twicksUsers;
	}

	@Override
	public String toString() {
		return "Company [id=" + this.id + ", address=" + this.address
				+ ", createdon=" + this.createdon + ", name=" + this.name
				+ ", subscriptiondate=" + this.subscriptionDate + ", products="
				+ this.products + ", twicksUsers=" + this.twicksUsers + "]";
	}
}