package in.ac.vit.twicks.controllers.product;

import in.ac.vit.twicks.controllers.CurrentUser;
import in.ac.vit.twicks.datastorage.service.api.ProductService;
import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.entities.UserRoles;
import in.ac.vit.twicks.utils.ParamParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

@WebServlet("/product/list.do")
public class ListProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private ProductService productService;
	@Inject
	private CurrentUser currentUser;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ParamParser p = new ParamParser(request);
		int sEcho = p.getInt("sEcho", -1);
		if (sEcho == -1) {
			request.getRequestDispatcher("/WEB-INF/views/product/list.jsp")
					.forward(request, response);
		} else {
			// echo json stores list
			int first = p.getInt("iDisplayStart");
			int pageSize = p.getInt("iDisplayLength");
			int sortCol = p.getInt("iSortCol_0");
			String sortField = "";
			switch (sortCol) {
			case 0:
				sortField = "id";
				break;
			case 1:
				sortField = "name";
				break;
			case 2:
				sortField = "keywords";
				break;
			default:
				sortField = "company";
			}
			String sortOrder = p.getString("sSortDir_0");
			Map<String, String> filters = new HashMap<>();

			ArrayList<String> list = new ArrayList<>();
			list.add("id");
			list.add("name");
			list.add("keywords");
			if(currentUser.isUserRole(UserRoles.ADMIN)) {
				list.add("company");
			}
			for (int i = 0; i < list.size(); i++) {
				String value = p.getString("sSearch_" + i, null);
				if (value != null) {
					filters.put(list.get(i), value);
				}
			}
			List<Product> products = this.productService.get(first,
					pageSize, sortField, sortOrder, filters);
			int totalCount = this.productService.getCount();
			int totalFilteredCount = this.productService.getCount(filters);
			Map<String, Object> map = new HashMap<>();
			map.put("sEcho", sEcho);
			map.put("iTotalRecords", totalCount);
			map.put("iTotalDisplayRecords", totalFilteredCount);
			ArrayList<ArrayList<String>> aaData = new ArrayList<>();
			for (Product product : products) {
				ArrayList<String> sData = new ArrayList<>();
				sData.add(product.getId().toString());
				sData.add(product.getName());
				sData.add(product.getKeywords());
				if(this.currentUser.isUserRole(UserRoles.ADMIN)) {
					sData.add(product.getCompany().getName());
				}
				String link = "<a href='javascript:;' onclick='loadForm(\"/product/view.do\",\"#content\",\"productId="
						+ product.getId() + "\")' class='viewLink'>view</a>";
				link += " <a href='javascript:;' onclick='loadForm(\"/product/edit.do\",\"#content\",\"productId="
						+ product.getId() + "\")' class='editLink'>edit</a>";
				link += " <a href='javascript:;' onclick='loadForm(\"/product/delete.do\",\"#content\",\"productId="
						+ product.getId()
						+ "\")' class='deleteLink'>delete</a>";
				sData.add(link);
				aaData.add(sData);
			}
			map.put("aaData", aaData);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(), map);
		}
	}

}
