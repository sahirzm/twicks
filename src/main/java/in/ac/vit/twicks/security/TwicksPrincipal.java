package in.ac.vit.twicks.security;

import in.ac.vit.twicks.datastorage.service.api.UserService;
import in.ac.vit.twicks.entities.User;
import in.ac.vit.twicks.entities.UserRoles;

import java.security.Principal;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public class TwicksPrincipal implements Principal {
	private String name;
	private User user;
	private UserService userService;
	private String fullname;
	private transient Logger log = Logger.getLogger(this.getClass());

	public TwicksPrincipal(String name) {
		this.name = name;
		InitialContext ic;
		if (!(name.equals(UserRoles.ADMIN) || name.equals(UserRoles.CUSTOMER))) {
			try {
				ic = new InitialContext();
				this.userService = (UserService) ic
						.lookup("java:app/twicks/UserServiceImpl");

				this.user = this.userService.getByUsername(name);
				this.fullname = this.user.fullName();
			} catch (NamingException e) {
				this.log.error("Cannot lookup UserServiceImpl", e);
			}
		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	public User getUser() {
		return this.user;
	}

	public String getFullName() {
		return this.fullname;
	}
}