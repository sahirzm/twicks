package in.ac.vit.twicks.controllers.product;

import in.ac.vit.twicks.datastorage.service.api.CompanyService;
import in.ac.vit.twicks.datastorage.service.api.ProductService;
import in.ac.vit.twicks.entities.Company;
import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.exceptions.ValidationException;
import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
@WebServlet("/product/edit.do")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private ProductService productService;
	@Inject
	private CompanyService companyService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ParamParser p = new ParamParser(request);
		int productId = p.getInt("productId");
		if (productId <= 0) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			Product product = this.productService.getById(productId);
			if (product == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else {
				request.setAttribute("product", product);
				List<Company> companies = this.companyService.getActiveCompanies();
				request.setAttribute("companies", companies);
				request.getRequestDispatcher("/WEB-INF/views/product/form.jsp")
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
		int productId = p.getInt("id");
		Product product= this.productService.getById(productId);
		product.setName(p.getString("name"));
		product.setKeywords(p.getString("keywords"));
		product.setCreatedon(new Date());
		int companyId = p.getInt("company");
		product.setCompany(this.companyService.getById(companyId));
		try {
			product = this.productService.update(product);
			response.sendRedirect(getServletContext().getContextPath()
					+ "/product/view.do?productId=" + product.getId());
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
