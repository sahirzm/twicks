package in.ac.vit.twicks.servlets;

import in.ac.vit.twicks.datastorage.service.api.DailyReportService;
import in.ac.vit.twicks.datastorage.service.api.HourlyReportService;
import in.ac.vit.twicks.datastorage.service.api.StatusService;
import in.ac.vit.twicks.search.fetchers.FetchingService;

import java.io.IOException;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private FetchingService fetchingService;
	@EJB
	private StatusService statusService;
	@EJB
	private HourlyReportService hourlyReportService;
	@EJB
	private DailyReportService dailyReportService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// this.fetchingService.startFetchers();
		for (int j = 5; j < 9; j++) {
			Calendar calendar = Calendar.getInstance();
			for (int i = 0; i < 24; i++) {
				calendar.set(Calendar.HOUR_OF_DAY, i);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				calendar.set(Calendar.DAY_OF_MONTH, j);
				calendar.set(Calendar.MONTH, 5);
				this.hourlyReportService.generateReport(calendar.getTime());
			}
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			this.dailyReportService.generateReport(calendar.getTime());
		}
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
