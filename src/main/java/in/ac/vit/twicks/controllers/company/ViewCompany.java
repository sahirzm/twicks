package in.ac.vit.twicks.controllers.company;

import in.ac.vit.twicks.datastorage.service.api.CompanyService;
import in.ac.vit.twicks.entities.Company;
import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewCompany
 */

@WebServlet("/company/view.do")
public class ViewCompany extends HttpServlet {
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
		if (companyId == 0) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		} else {
			Company company = this.companyService.getById(companyId);
			if (company == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			} else {
				request.setAttribute("company", company);
				request.getRequestDispatcher("/WEB-INF/views/company/view.jsp")
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
		this.doGet(request, response);
	}

}
