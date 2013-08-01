package in.ac.vit.twicks.controllers;

import static in.ac.vit.twicks.entities.UserRoles.ADMIN;
import static in.ac.vit.twicks.entities.UserRoles.CUSTOMER;

import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@ApplicationScoped
public class ApplicationMenus {
	private MenuBar mainMenu = new MenuBar(null, "nav", null);

	public ApplicationMenus() {

		mainMenu.addMenu(new Menu("Home", "/homepage.do", null));
		Menu createMenu = new Menu("Create", "#", Arrays.asList(ADMIN),
				"dropdown");
		createMenu.setHrefClass("dropdown-toggle");
		MenuBar createBar = new MenuBar(null, "dropdown-menu", null);
		createBar.addMenu(new Menu("Company", Arrays.asList(ADMIN),
				"loadForm('/company/create.do','#content')"));
		createBar.addMenu(new Menu("Product", Arrays.asList(ADMIN),
				"loadForm('/product/create.do','#content')"));
		createBar.addMenu(new Menu("User", Arrays.asList(ADMIN),
				"loadForm('/user/create.do','#content')"));
		createMenu.setMenuBar(createBar);
		mainMenu.addMenu(createMenu);
		Menu listMenu = new Menu("List", "#", Arrays.asList(ADMIN, CUSTOMER),
				"dropdown");
		listMenu.setHrefClass("dropdown-toggle");
		MenuBar listBar = new MenuBar(null, "dropdown-menu", null);
		listBar.addMenu(new Menu("Company", Arrays.asList(ADMIN),
				"loadForm('/company/list.do','#content')"));
		listBar.addMenu(new Menu("Product", Arrays.asList(ADMIN, CUSTOMER),
				"loadForm('/product/list.do','#content')"));
		listBar.addMenu(new Menu("User", Arrays.asList(ADMIN, CUSTOMER),
				"loadForm('/user/list.do','#content')"));
		listMenu.setMenuBar(listBar);
		mainMenu.addMenu(listMenu);
		mainMenu.addMenu(new Menu("About Us", "/aboutus.do", null));
		mainMenu.addMenu(new Menu("Contact Us", "/contactus.do", null));
	}

	@Named("mainMenu")
	@Produces
	public MenuBar getMainMenu() {
		return this.mainMenu;
	}

}
