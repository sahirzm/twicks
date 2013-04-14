package in.ac.vit.twicks.controllers.company;

import in.ac.vit.twicks.datastorage.service.api.CompanyService;
import in.ac.vit.twicks.entities.Company;
import in.ac.vit.twicks.exceptions.ValidationException;
import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;
import java.sql.Timestamp;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateCompany
 */
@WebServlet("/company/create.do")
public class CreateCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private CompanyService companyService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Company company = new Company();
		request.setAttribute("company", company);
		request.getRequestDispatcher("/WEB-INF/views/company/form.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Company company = new Company();
		ParamParser p = new ParamParser(request);
		company.setName(p.getString("name"));
		company.setAddress(p.getString("address"));
		company.setCreatedon(new Timestamp(System.currentTimeMillis()));
		company.setSubscriptionDate(p.getDate("subscriptionDate"));
		try {
			company = this.companyService.save(company);
			response.sendRedirect(getServletContext().getContextPath()
					+ "/company/view.do?companyId=" + company.getId());
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
