package in.ac.vit.twicks.controllers;

import in.ac.vit.twicks.datastorage.service.api.UserService;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@WebServlet(urlPatterns = "/login.do")
public class LoginController extends HttpServlet {
	protected transient Logger log = Logger.getLogger(getClass());
	@Inject
	private UserService userService;
	@Inject
	private CurrentUser currentUser;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TilesHelper.render(request, response, "login");
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (!(StringUtils.isBlank(username) || StringUtils.isBlank(password))) {

			try {
				request.login(username, password);
				HttpSession session = request.getSession();
				currentUser.setUser(this.getUserService().authenticate(
						username, password));
				session.setAttribute("currentUser", currentUser.getUser());
				currentUser.getUser().setLastloggedin(new Date());
				this.userService.update(currentUser.getUser());
				response.sendRedirect("homepage.do");
				return;
			} catch (Exception e) {
				log.error("Login failed for " + username + " with password = "
						+ password);
			}
		}
		request.setAttribute("loginError", "Invalid Credentials");
		this.doGet(request, response);
	}

	protected UserService getUserService() {
		return userService;
	}

}
