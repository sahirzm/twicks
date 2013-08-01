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
 * Servlet implementation class CreateProduct
 */
@WebServlet("/product/create.do")
public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private CompanyService companyService;
	@Inject
	private ProductService productService;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Product product = new Product();
		List<Company> companies = this.companyService.getActiveCompanies();
		request.setAttribute("product", product);
		request.setAttribute("companies", companies);
		request.getRequestDispatcher("/WEB-INF/views/product/form.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Product product = new Product();
		ParamParser p = new ParamParser(request);
		product.setName(p.getString("name"));
		product.setKeywords(p.getString("keywords"));
		product.setCreatedon(new Date());
		int companyId = p.getInt("company");
		product.setCompany(this.companyService.getById(companyId));
		try {
			product = this.productService.save(product);
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
