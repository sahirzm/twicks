package in.ac.vit.twicks.controllers.product;

import in.ac.vit.twicks.datastorage.service.api.ProductService;
import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewCompany
 */
@RolesAllowed({ "admin" })
@WebServlet("/product/view.do")
public class ViewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private ProductService productService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ParamParser p = new ParamParser(request);
		int productId = p.getInt("productId");
		if (productId == 0) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		} else {
			Product product = this.productService.getById(productId);
			if (product == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			} else {
				request.setAttribute("product", product);
				request.getRequestDispatcher("/WEB-INF/views/product/view.jsp")
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
