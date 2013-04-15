package in.ac.vit.twicks.controllers.product;

import in.ac.vit.twicks.datastorage.service.api.ProductService;
import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/product/delete.do")
public class DeleteProduct extends HttpServlet {
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
		if (productId > 0) {
			this.productService.delete(productId);
			response.sendRedirect(getServletContext().getContextPath()
					+ "/product/list.do");
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