package in.ac.vit.twicks.controllers.company;

import in.ac.vit.twicks.datastorage.service.api.CompanyService;
import in.ac.vit.twicks.entities.Company;
import in.ac.vit.twicks.exceptions.ValidationException;
import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditCompany
 */
@WebServlet("/company/edit.do")
public class EditCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private CompanyService companyService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ParamParser p = new ParamParser(request);
		int companyId = p.getInt("companyId");
		if (companyId <= 0) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			Company company = this.companyService.getById(companyId);
			if (company == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else {
				request.setAttribute("company", company);
				request.getRequestDispatcher("/WEB-INF/views/company/form.jsp")
						.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ParamParser p = new ParamParser(request);
		int companyId = p.getInt("id");
		Company company = this.companyService.getById(companyId);
		company.setName(p.getString("name"));
		company.setAddress(p.getString("address"));
		company.setSubscriptionDate(p.getDate("subscriptionDate"));
		try {
			company = this.companyService.update(company);
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
