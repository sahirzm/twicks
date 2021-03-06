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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * The persistent class for the twicks_user database table.
 * 
 */
@Entity
@Table(name = "twicks_user")
@NamedQueries({ @NamedQuery(name = "user.findByUsername", query = "SELECT u FROM User u WHERE u.username=:username") })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TWICKS_USER_ID_GENERATOR", sequenceName = "twicks_user_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TWICKS_USER_ID_GENERATOR")
	private Integer id;

	private String address;
	@NotNull
	private Date createdon;
	@NotNull
	@Email
	@Size(min = 2)
	private String email;
	@NotNull
	@Size(min = 2)
	private String firstname;

	private Date lastloggedin;

	@NotNull
	@Size(min = 2)
	private String lastname;

	private String middlename;

	@NotNull
	@Pattern(regexp = "^[0-9]{4}(-[0-9]{3}){2}$", message = "invalid format XXXX-XXX-XXX")
	private String mobileno;

	@NotNull
	@Size(min = 6)
	private String password;

	@NotNull
	private String role;

	@NotNull
	@Size(min = 3)
	private String username;

	// bi-directional many-to-one association to Company
	@ManyToOne
	private Company company;

	public User() {
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Date getLastloggedin() {
		return this.lastloggedin;
	}

	public void setLastloggedin(Date lastloggedin) {
		this.lastloggedin = lastloggedin;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getMobileno() {
		return this.mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "User [id=" + this.id + ", address=" + this.address
				+ ", createdon=" + this.createdon + ", email=" + this.email
				+ ", firstname=" + this.firstname + ", lastloggedin="
				+ this.lastloggedin + ", lastname=" + this.lastname
				+ ", middlename=" + this.middlename + ", mobileno="
				+ this.mobileno + ", password=" + this.password + ", role="
				+ this.role + ", username=" + this.username + ", company="
				+ this.company + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.address == null) ? 0 : this.address.hashCode());
		result = (prime * result)
				+ ((this.company == null) ? 0 : this.company.hashCode());
		result = (prime * result)
				+ ((this.createdon == null) ? 0 : this.createdon.hashCode());
		result = (prime * result)
				+ ((this.email == null) ? 0 : this.email.hashCode());
		result = (prime * result)
				+ ((this.firstname == null) ? 0 : this.firstname.hashCode());
		result = (prime * result)
				+ ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result)
				+ ((this.lastloggedin == null) ? 0 : this.lastloggedin
						.hashCode());
		result = (prime * result)
				+ ((this.lastname == null) ? 0 : this.lastname.hashCode());
		result = (prime * result)
				+ ((this.middlename == null) ? 0 : this.middlename.hashCode());
		result = (prime * result)
				+ ((this.mobileno == null) ? 0 : this.mobileno.hashCode());
		result = (prime * result)
				+ ((this.password == null) ? 0 : this.password.hashCode());
		result = (prime * result)
				+ ((this.role == null) ? 0 : this.role.hashCode());
		result = (prime * result)
				+ ((this.username == null) ? 0 : this.username.hashCode());
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
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String fullName() {
		return this.firstname + " " + this.lastname;
	}

}