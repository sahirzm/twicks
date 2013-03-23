package in.ac.vit.twicks.servlets;

import in.ac.vit.twicks.datastorage.service.api.StatusService;
import in.ac.vit.twicks.search.fetchers.FetchingService;
import in.ac.vit.twicks.search.statuses.TwitterStatus;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.servlet.ServletApplicationContext;
import org.apache.tiles.request.servlet.ServletRequest;

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
		TwitterStatus status = new TwitterStatus("1234",
				"This is my first Twitter status", System.currentTimeMillis()
				+ "", 1, "en-US");
		this.statusService.storeStatus(status);
		ApplicationContext context = new ServletApplicationContext(
				request.getServletContext());
		TilesContainer container = TilesAccess.getContainer(context);
		Request tilesRequest = new ServletRequest(
				container.getApplicationContext(), request, response);
		container.render("twicks.homepage", tilesRequest);
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
