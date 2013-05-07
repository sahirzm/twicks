package in.ac.vit.twicks.controllers.report;

import in.ac.vit.twicks.datastorage.service.api.ReportService;
import in.ac.vit.twicks.datastorage.service.api.UserService;
import in.ac.vit.twicks.entities.UserRoles;
import in.ac.vit.twicks.security.TwicksPrincipal;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/customerdashboard.do")
@ServletSecurity(@HttpConstraint(rolesAllowed = { UserRoles.CUSTOMER }))
public class CustomerDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private ReportService reportService;
	@EJB
	private UserService userService;
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TwicksPrincipal tp = (TwicksPrincipal) request.getUserPrincipal();
		this.log.info("Got principal");
		int companyId = this.userService.getById(tp.getUser().getId())
				.getCompany().getId();
		List<Map<String, Object>> map = this.reportService
				.getAllProductsScore(companyId);
		this.log.info("writing data to stream");
		request.setAttribute("map", map);
		request.getRequestDispatcher("/WEB-INF/views/reports/allproducts.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
