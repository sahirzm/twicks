package in.ac.vit.twicks.controllers;

import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/homepage.do")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private CurrentUser user;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ParamParser p = new ParamParser(request);
		boolean partial = p.getBoolean("partial");
		if (partial) {
			request.getRequestDispatcher("/WEB-INF/views/"
					+ user.getUser().getRole() + "home.jsp").forward(request, response);
			return;
		} else {
			Log.debug(user.getUser().getRole());
			TilesHelper.render(request, response, user.getUser().getRole()
					+ "homepage");
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
