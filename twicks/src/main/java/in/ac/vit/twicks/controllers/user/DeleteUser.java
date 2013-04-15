package in.ac.vit.twicks.controllers.user;

import in.ac.vit.twicks.datastorage.service.api.UserService;
import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/user/delete.do")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ParamParser p = new ParamParser(request);
		int userId = p.getInt("userId");
		this.userService.delete(userId);
		response.sendRedirect(getServletContext().getContextPath()
				+ "/user/list.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
