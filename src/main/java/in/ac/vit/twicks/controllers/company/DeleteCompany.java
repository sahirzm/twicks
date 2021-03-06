package in.ac.vit.twicks.controllers.company;

import in.ac.vit.twicks.datastorage.service.api.CompanyService;
import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteCompany
 */
@WebServlet("/company/delete.do")
public class DeleteCompany extends HttpServlet {
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
		if (companyId > 0) {
			this.companyService.delete(companyId);
			response.sendRedirect(getServletContext().getContextPath()
					+ "/company/list.do");
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
