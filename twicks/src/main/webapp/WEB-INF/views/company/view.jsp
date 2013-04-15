<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<table class="table table-bordered">
	<tr class="success">
		<td colspan="4">Company Details</td>
	</tr>
	<tr>
		<td>Company Id</td>
		<td>${company.id }</td>
	</tr>
	<tr>
		<td>Name</td>
		<td>${company.name }</td>
	</tr>
	<tr>
		<td>Address</td>
		<td>${company.address }</td>
	</tr>
	<tr>
		<td>Created On</td>
		<td>${company.createdon }</td>
	</tr>
	<tr>
		<td>Subscription Date</td>
		<td>${company.subscriptionDate }</td>
	</tr>
</table>

<div class="row-fluid">
	<button type="button" id="edit" class="btn"
		onclick="loadForm('/company/edit.do','#content','companyId=${company.id}')">
		Edit
	</button>
	<button type="button" id="delete" class="btn warn"
		onclick="loadForm('/company/delete.do','#content','companyId=${company.id }')">
		Delete
	</button>
</div>
<script type="text/javascript">
	$(function() {
		changeHeading('Company Details #${company.id}');
		changeTitle('Company Details #${company.id}');
	});
</script>