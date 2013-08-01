package in.ac.vit.twicks.controllers.user;

import in.ac.vit.twicks.datastorage.service.api.UserService;
import in.ac.vit.twicks.entities.User;
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

/**
 * Servlet implementation class ListUser
 */
@WebServlet("/user/list.do")
public class ListUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ParamParser p = new ParamParser(request);
		int sEcho = p.getInt("sEcho", -1);
		if (sEcho == -1) {
			request.getRequestDispatcher("/WEB-INF/views/user/list.jsp")
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
				sortField = "firstname";
				break;
			case 2:
				sortField = "lastname";
				break;
			case 3:
				sortField = "email";
				break;
			case 4:
				sortField = "company";
				break;
			default:
				sortField = "id";
			}
			String sortOrder = p.getString("sSortDir_0");
			Map<String, String> filters = new HashMap<>();

			ArrayList<String> list = new ArrayList<>();
			list.add("id");
			list.add("firstname");
			list.add("lastname");
			list.add("email");
			list.add("company");

			for (int i = 0; i < list.size(); i++) {
				String value = p.getString("sSearch_" + i, null);
				if (value != null) {
					filters.put(list.get(i), value);
				}
			}
			List<User> users = this.userService.get(first, pageSize, sortField,
					sortOrder, filters);
			int totalCount = this.userService.getCount();
			int totalFilteredCount = this.userService.getCount(filters);
			Map<String, Object> map = new HashMap<>();
			map.put("sEcho", sEcho);
			map.put("iTotalRecords", totalCount);
			map.put("iTotalDisplayRecords", totalFilteredCount);
			ArrayList<ArrayList<String>> aaData = new ArrayList<>();
			for (User user : users) {
				ArrayList<String> sData = new ArrayList<>();
				sData.add(user.getId().toString());
				sData.add(user.getFirstname());
				sData.add(user.getLastname());
				sData.add(user.getEmail());
				sData.add(user.getCompany() != null ? user.getCompany().getName() : "N/A");
				String link = "<a href='javascript:;' onclick='loadForm(\"/user/view.do\",\"#content\",\"userId="
						+ user.getId() + "\")' class='viewLink'>view</a>";
				link += " <a href='javascript:;' onclick='loadForm(\"/user/edit.do\",\"#content\",\"userId="
						+ user.getId() + "\")' class='editLink'>edit</a>";
				link += " <a href='javascript:;' onclick='loadForm(\"/user/delete.do\",\"#content\",\"userId="
						+ user.getId() + "\")' class='deleteLink'>delete</a>";
				sData.add(link);
				aaData.add(sData);
			}
			map.put("aaData", aaData);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(), map);
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
