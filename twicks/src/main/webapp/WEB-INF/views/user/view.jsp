<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<table class="table table-bordered">
	<tr class="success">
		<td colspan="4">User Details</td>
	</tr>
	<tr>
		<td>User Id</td>
		<td>${user.id }</td>
	</tr>
	<tr>
		<td>First Name</td>
		<td>${user.firstname }</td>
		<td>Middle Name</td>
		<td>${user.middlename }</td>
		<td>Last Name</td>
		<td>${user.lastname }</td>
	</tr>
	<tr>
		<td>Address</td>
		<td>${user.address }</td>
		<td>Email Id</td>
		<td>${user.email }</td>
		<td>Mobile No</td>
		<td>${user.mobileno }</td>
	</tr>
	<tr>
		<td>Last Logged In</td>
		<td>${user.lastloggedin }</td>
	</tr>
	<tr>
		<td>Created On</td>
		<td>${user.createdon }</td>
	</tr>
	<tr>
		<td>Username</td>
		<td>${user.username }</td>
		<td>Password</td>
		<td>${user.password }</td>
	</tr>
	<tr>
		<td>Role</td>
		<td>${user.role }</td>
	</tr>
</table>
<div class="row-fluid">
	<button type="button" id="edit" class="btn"
		onclick="loadForm('/user/edit.do','#content','userId=${user.id}')">
		Edit
	</button>
	<button type="button" id="delete" class="btn warn"
		onclick="loadForm('/user/delete.do','#content','userId=${user.id }')">
		Delete
	</button>
</div>
<script type="text/javascript">
	$(function() {
		changeHeading('View Company Details #${user.id}');
		changeTitle('View Company Details #${user.id}');
	});
</script>	