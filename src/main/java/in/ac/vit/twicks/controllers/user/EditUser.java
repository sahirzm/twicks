package in.ac.vit.twicks.controllers.user;

import in.ac.vit.twicks.datastorage.service.api.CompanyService;
import in.ac.vit.twicks.datastorage.service.api.UserService;
import in.ac.vit.twicks.entities.User;
import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/user/edit.do")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private UserService userService;
	@Inject
	private CompanyService companyService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ParamParser p = new ParamParser(request);
		int userId = p.getInt("userId");
		User user = this.userService.getById(userId);
		if (user == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		} else {
			request.setAttribute("user", user);
			request.setAttribute("companies",
					this.companyService.getActiveCompanies());
			request.getRequestDispatcher("/WEB-INF/views/user/form.jsp")
					.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
