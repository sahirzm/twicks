<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<table id="productList" class="table table-bordered dataTable">
	<thead>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Keywords</th>
			<c:if test="${currentUser.isUserRole('admin')}">
				<th>Company</th>
			</c:if>
			<th>Actions</th>
		</tr>
	</thead>
	<tfoot>
		<tr class="success">
			<th><input type="text" value="Id" /></th>
			<th><input type="text" value="name" /></th>
			<th><input type="text" value="Keywords" /></th>
			<c:if test="${currentUser.isUserRole('admin')}">
				<th><input type="text" value="Company" /></th>
			</c:if>
			<th>Actions</th>
		</tr>
	</tfoot>
	<tbody>
	</tbody>
</table>

<script type="text/javascript">
	$(function() {
		changeHeading("List Products");
		changeTitle("List Products | Admin Panel");
		
		var oTable = $('#productList')
				.dataTable(
						{
							bProcessing : true,
							bServerSide : true,
							sAjaxSource : BASE_URL + "/product/list.do",
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