package in.ac.vit.twicks.controllers;

import in.ac.vit.twicks.entities.User;
import in.ac.vit.twicks.entities.UserRoles;
import in.ac.vit.twicks.security.TwicksPrincipal;
import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/homepage.do")
@ServletSecurity(@HttpConstraint(rolesAllowed = { UserRoles.ADMIN,
		UserRoles.CUSTOMER }))
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private transient Logger log = Logger.getLogger(this.getClass());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ParamParser p = new ParamParser(request);
		boolean partial = p.getBoolean("partial");
		Principal pl = request.getUserPrincipal();
		TwicksPrincipal tp = (TwicksPrincipal) pl;
		User user = tp.getUser();
		if (user == null) {
			this.log.error("User is null");
		}
		if (partial) {
			request.getRequestDispatcher(
					"/WEB-INF/views/" + user.getRole() + "home.jsp").forward(
					request, response);
			return;
		} else {
			this.log.debug(user.getRole());
			TilesHelper.render(request, response, user.getRole() + "homepage");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}