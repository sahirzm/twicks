<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<form id="userForm">
	<fieldset>
			<legend> User Details </legend>
		<div class="row-fluid">
			<c:if test="${user.id gt 0}">
				<div class="span6">
					<label for="id">
						Id <span class="required">*</span>
					</label>
					<span class="span12">${user.id }</span>
					<input type="hidden" id="id" name="id" value="${user.id }">
				</div>
			</c:if>
			<div class="span4">
				<label for="firstname">
					First Name <span class="required">*</span>	
				</label>
				<input type="text" id="firstname" name="firstname" class="span12"
					value="${user.firstname }">
			</div>
			<div class="span4">
				<label for="middlename">
					Middle Name 
				</label>
				<input type="text" id="middlename" name="middlename" class="span12"
					value="${user.middlename }">
			</div>
			<div class="span4">
				<label for="lastname">
					Last Name <span class="required">*</span>
				</label>
				<input type="text" id="lastname" name="lastname" class="span12"
					value="${user.lastname }">
			</div>
		</div>
			<div class="row-fluid">
			<div class="span4">
				<label for="email">
				Email Id <span class="required">*</span>	
				</label>
				<input type="text" id="email" name="email" class="span12"
					value="${user.email }">
			</div>
			<div class="span4">
				<label for="mobileno">
					Mobile No <span class="required">*</span>
				</label>
				<input type="text" id="mobileno" name="mobileno" class="span12"
					value="${user.mobileno }">
			</div>
			<div class="span4">
				<label for="createdon">
					Created On <span class="required">*</span>
				</label>
				<input type="text" id="createdon" name="createdon" class="span12"
					value="${user.createdon }">
			</div>
			</div>
			<div class="row-fluid">
			<div class="span6">
				<label for="address">
					Address 
				</label>
				<textarea id="address" name="address" class="span12">${user.address }</textarea>
			</div>
			</div>
	</fieldset>
	<fieldset>
			<legend> Login Details </legend>		
			<div class="row-fluid">
			<div class="span6">
				<label for="username">
					Username <span class="required">*</span>
				</label>
				<input type="text" id="username" name="username" class="span12"
					value="${user.username }">
			</div>
			<div class="span6">
				<label for="password">
					Password <span class="required">*</span>
				</label>
				<input type="password" id="password" name="password" class="span12"
					value="${user.password }">
			</div>
			</div>
			<div class="row-fluid">
			<div class="span6">
				<label for="role">
					Role <span class="required">*</span>
				</label>
				<input type="text" id="role" name="role" class="span12"
					value="${user.role }">
			</div>
			<div class="span6">
				<label for="lastloggedin">
					Last Logged In 
				</label>
				<input type="text" id="lastloggedin" name="lastloggedin" class="span12"
					value="${user.lastloggedin }">
			</div>
			</div>
	</fieldset>
	<div class="row-fluid">
		<button type="button" id="submit" class="btn btn-primary">${company.id gt 0 ? 'Update' : 'Save' }
			</button>
		<button type="button" id="cancel" class="btn"
			onclick="loadForm('/company/list.do','#content')">Cancel</button>
	</div>
	<script type="text/javascript">
	$(function() {
		<c:choose>
		<c:when test="${product.id gt 0 }">
		changeHeading('Update User #${user.id}');
		changeTitle('Update User #${user.id} | Admin Panel');
		</c:when>
		<c:otherwise>
		changeHeading('Create New User');
		changeTitle('Create New User | Admin Panel');
		</c:otherwise>
		</c:choose>
		$('#submit').click(function() {
			<c:choose>
			<c:when test="${user.id gt 0 }">
			submitForm("#userForm", "/user/edit.do", "#content");
			</c:when>
			<c:otherwise>
			submitForm("#userForm", "/user/create.do", "#content");
			</c:otherwise>
			</c:choose>
		});
	});
</script>
</form>