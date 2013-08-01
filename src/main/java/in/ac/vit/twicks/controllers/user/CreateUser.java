package in.ac.vit.twicks.controllers.user;

import in.ac.vit.twicks.datastorage.service.api.CompanyService;
import in.ac.vit.twicks.datastorage.service.api.UserService;
import in.ac.vit.twicks.entities.Company;
import in.ac.vit.twicks.entities.User;
import in.ac.vit.twicks.entities.UserRoles;
import in.ac.vit.twicks.exceptions.ValidationException;
import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/user/create.do")
public class CreateUser extends HttpServlet {
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
		User user = new User();
		request.setAttribute("user", user);
		request.setAttribute("companies",
				this.companyService.getActiveCompanies());
		request.getRequestDispatcher("/WEB-INF/views/user/form.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		ParamParser p = new ParamParser(request);
		user.setAddress(p.getString("address"));
		user.setRole(p.getString("role"));
		if (user.getRole() != null && user.getRole().equals(UserRoles.CUSTOMER)) {
			int companyId = p.getInt("company");
			Company company = this.companyService.getById(companyId);
			user.setCompany(company);
		}
		user.setCreatedon(new Date());
		user.setEmail(p.getString("email"));
		user.setFirstname(p.getString("firstname"));
		user.setLastloggedin(null);
		user.setLastname(p.getString("lastname"));
		user.setMiddlename(p.getString("middlename"));
		user.setMobileno(p.getString("mobileno"));
		user.setPassword(p.getString("password"));
		user.setUsername(p.getString("username"));
		try {
			user = this.userService.save(user);
			response.sendRedirect(getServletContext().getContextPath()
					+ "/user/view.do?userId=" + user.getId());
		} catch (EJBException e) {
			if (e.getCause() instanceof ValidationException) {
				ValidationException ve = (ValidationException) e.getCause();
				response.getWriter().print(ve.getAsJSON());
				return;
			} else {
				e.printStackTrace();
			}
		}
	}

}
