package in.ac.vit.twicks.controllers.report;

import in.ac.vit.twicks.datastorage.service.api.ReportService;
import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductDrawer
 */
@WebServlet("/productdrawer.do")
public class ProductDrawer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ReportService reportService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ParamParser p = new ParamParser(request);
		int productId = p.getInt("productId");
		List<Map<String, Object>> result = this.reportService
				.getLast24HrsScore(productId);
		request.setAttribute("data", result);

		request.getRequestDispatcher("/WEB-INF/views/reports/productdrawer.jsp")
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
