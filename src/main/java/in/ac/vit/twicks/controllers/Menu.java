package in.ac.vit.twicks.controllers;

import in.ac.vit.twicks.entities.UserRoles;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Menu {

	private String name;
	private String link;
	private List<String> roles;
	private String styleClass;
	private MenuBar menuBar;
	private String onClick;
	private String hrefClass;
	
	public Menu(String name, String link, List<String> roles) {
		super();
		this.name = name;
		this.link = link;
		this.roles = roles;
	}

	public Menu(String name, String link, List<String> roles, String styleClass) {
		super();
		this.name = name;
		this.link = link;
		this.roles = roles;
		this.styleClass = styleClass;
	}

	public Menu(String name, List<String> roles, String styleClass,
			String onClick) {
		super();
		this.name = name;
		this.roles = roles;
		this.styleClass = styleClass;
		this.onClick = onClick;
	}

	public Menu(String name, List<String> roles, String onClick) {
		super();
		this.name = name;
		this.roles = roles;
		this.onClick = onClick;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
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

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public int getType() {
		return this.link == null || this.link.equals("") ? 2 : 1;
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public boolean hasMenuBar() {
		return this.getMenuBar() == null
				|| this.getMenuBar().getMenus().size() == 0 ? false : true;
	}

	public String getHrefClass() {
		return hrefClass;
	}

	public void setHrefClass(String hrefClass) {
		this.hrefClass = hrefClass;
	}

}
