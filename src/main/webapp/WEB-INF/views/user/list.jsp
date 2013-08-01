<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<table id="userList" class="table table-bordered dataTable">
	<thead>
		<tr>
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Company</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tfoot>
		<tr class="success">
			<th><input type="text" value="Id" /></th>
			<th><input type="text" value="First Name" /></th>
			<th><input type="text" value="Last Name" /></th>
			<th><input type="text" value="Email" /></th>
			<th><input type="text" value="Company" /></th>
			<th>Actions</th>
		</tr>
	</tfoot>
	<tbody>
	</tbody>
</table>

<script type="text/javascript">
	$(function() {
		changeHeading("List Users");
		changeTitle("List User | Admin Panel");

		var oTable = $('#userList')
				.dataTable(
						{
							bProcessing : true,
							bServerSide : true,
							sAjaxSource : BASE_URL + "/user/list.do",
							sDom : "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
							sPaginationType : "bootstrap"
						});

		var asInitVals = new Array();

		$("tfoot input").keyup(function() {
			/* Filter on the column (the index) of this element */
			oTable.fnFilter(this.value, $(this).parent().index());
		});

		$("tfoot select").change(function() {
			/* Filter on the column (the index) of this element */
			oTable.fnFilter(this.value, $(this).parent().index());
		});

		$("tfoot input").each(function(i) {
			asInitVals[$(this).parent().index()] = this.value;
			this.className = "search_init";
		});

		$("tfoot input").focus(function() {
			if (this.className == "search_init") {
				this.className = "";
				this.value = "";
			}
		});

		$("tfoot input").blur(function(i) {
			if (this.value == "") {
				this.className = "search_init";
				this.value = asInitVals[$(this).parent().index()];
			}
		});
	});
</script>