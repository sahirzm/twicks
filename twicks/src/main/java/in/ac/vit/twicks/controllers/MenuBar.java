package in.ac.vit.twicks.controllers;

import in.ac.vit.twicks.entities.UserRoles;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class MenuBar {
	private List<Menu> menus;
	private String styleClass;
	private List<String> roles;

	public MenuBar(List<Menu> menus, String styleClass, List<String> roles) {
		super();
		this.menus = menus;
		this.styleClass = styleClass;
		this.roles = roles;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public void addMenu(Menu menu) {
		if (this.menus == null) {
			this.menus = new ArrayList<>();
		}
		this.menus.add(menu);
	}

	public boolean isAllowed(HttpServletRequest request) {
		Principal user = request.getUserPrincipal();
		if (this.getRoles() == null || this.getRoles().size() == 0) {
			return true;
		} else if (user == null) {
			if (this.getRoles().indexOf(UserRoles.ANONYMOUS) >= 0) {
				return true;
			}
		} else {
			for (String role : this.getRoles()) {
				if (request.isUserInRole(role)) {
					return true;
				}
			}
		}
		return false;
	}
}
