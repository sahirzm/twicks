package in.ac.vit.twicks.controllers.company;

import in.ac.vit.twicks.datastorage.service.api.CompanyService;
import in.ac.vit.twicks.entities.Company;
import in.ac.vit.twicks.utils.ParamParser;
import in.ac.vit.twicks.utils.Utils;

import java.io.IOException;
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

@WebServlet("/company/list.do")
public class ListCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private CompanyService companyService;

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
			request.getRequestDispatcher("/WEB-INF/views/company/list.jsp")
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
				sortField = "subscriptionDate";
				break;
			default:
				sortField = "id";
			}
			String sortOrder = p.getString("sSortDir_0");
			Map<String, String> filters = new HashMap<>();

			String[] list = { "id", "name", "subscriptionDate" };
			for (int i = 0; i < list.length; i++) {
				String value = p.getString("sSearch_" + i, null);
				if (value != null) {
					filters.put(list[i], value);
				}
			}
			List<Company> companies = this.getCompanyService().get(first,
					pageSize, sortField, sortOrder, filters);
			int totalCount = this.getCompanyService().getCount();
			int totalFilteredCount = this.getCompanyService().getCount(filters);
			Map<String, Object> map = new HashMap<>();
			map.put("sEcho", sEcho);
			map.put("iTotalRecords", totalCount);
			map.put("iTotalDisplayRecords", totalFilteredCount);
			String[][] aaData = new String[companies.size()][];
			int i = 0;
			for (Company company : companies) {
				String[] sData = new String[4];
				sData[0] = company.getId().toString();
				sData[1] = company.getName();
				sData[2] = Utils.dateToStr(company.getSubscriptionDate());
				sData[3] = "<a href='javascript:;' onclick='loadForm(\"/company/view.do\",\"#content\",\"companyId="
						+ company.getId() + "\")' class='viewLink'>view</a>";
				sData[3] += " <a href='javascript:;' onclick='loadForm(\"/company/edit.do\",\"#content\",\"companyId="
						+ company.getId() + "\")' class='editLink'>edit</a>";
				sData[3] += " <a href='javascript:;' onclick='loadForm(\"/company/delete.do\",\"#content\",\"companyId="
						+ company.getId()
						+ "\")' class='deleteLink'>delete</a>";
				aaData[i++] = sData;
			}
			map.put("aaData", aaData);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(), map);
		}
	}

	protected CompanyService getCompanyService() {
		return this.companyService;
	}
}
