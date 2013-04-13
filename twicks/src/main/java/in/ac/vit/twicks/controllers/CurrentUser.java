package in.ac.vit.twicks.controllers;

import in.ac.vit.twicks.entities.User;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named("currentUser")
public class CurrentUser implements Serializable {

	private User user;
	private String username;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isLoggedIn() {
		return this.getUser() != null;
	}

	public boolean isUserRole(String role) {
		return this.getUser() == null ? false : this.getUser()
				.getRole().equals(role) ? true : false;
	}

	public String getName() {
		return this.getUser().getFirstname() + " "
				+ this.getUser().getLastname();
	}

}
