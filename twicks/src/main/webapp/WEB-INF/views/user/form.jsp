<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<form id="userForm">
	<fieldset>
			<legend> Company Details </legend>
		<div class="row-fluid">
			<c:if test="${user.id gt 0}">
				<div class="span6">
					<label for="id">
						<fmt:message key="USER_ID_LBL" />
						<span class="required">*</span>
					</label>
					<span class="span12">${user.id }</span>
					<input type="hidden" id="id" name="id" value="${user.id }">
				</div>
			</c:if>
		<div class="row-fluid">
			<div class="span4">
				<label for="firstname">
					<fmt:message key="USER_FIRSTNAME_LBL" />
					<span class="required">*</span>
				</label>
				<input type="text" id="firstname" name="firstname" class="span12"
					value="${user.firstname }">
			</div>
			<div class="span4">
				<label for="middlename">
					<fmt:message key="USER_MIDDLENAME_LBL" />
				</label>
				<input type="text" id="middlename" name="middlename" class="span12"
					value="${user.middlename }">
			</div>
			<div class="span4">
				<label for="lastname">
					<fmt:message key="USER_LASTNAME_LBL" />
					<span class="required">*</span>
				</label>
				<input type="text" id="lastname" name="lastname" class="span12"
					value="${user.lastname }">
			</div>
			</div>
			<div class="row-fluid">
			<div class="span4">
				<label for="createdon">
					<fmt:message key="USER_CREATED_ON_LBL" />
				</label>
				<input type="text" id="createdon" name="createdon" class="span12"
					value="${user.createdon }">
			</div>
			<div class="span4">
				<label for="email">
					<fmt:message key="USER_EMAIL_LBL" />
				</label>
				<input type="text" id="email" name="email" class="span12"
					value="${user.email }">
			</div>
			<div class="span4">
				<label for="mobileno">
					<fmt:message key="USER_MOBILE_NO_LBL" />
				</label>
				<input type="text" id="mobileno" name="mobileno" class="span12"
					value="${user.mobileno }">
			</div>
			</div>
			<div class="row-fluid">
			<div class="span6">
				<label for="address">
					<fmt:message key="USER_ADDRESS_LBL" />
				</label>
				<textarea type="text" id="address" name="address" class="span12">${user.address }</textarea>
			</div>
			</div>
	</fieldset>
	<fieldset>
	<legend>
			<fmt:message key="USER_LOGIN_DETAILS" />
		</legend>		
			<div class="row-fluid">
			<div class="span6">
				<label for="name">
					<fmt:message key="USER_USERNAME_LBL" />
					<span class="required">*</span>
				</label>
				<input type="text" id="username" name="username" class="span12"
					value="${user.username }">
			</div>
			<div class="span6">
				<label for="password">
					<fmt:message key="USER_PASSWORD_LBL" />
					<span class="required">*</span>
				</label>
				<input type="text" id="password" name="password" class="span12"
					value="${user.password }">
			</div>
			</div>
			<div class="row-fluid">
			<div class="span6">
				<label for="role">
					<fmt:message key="USER_ROLE_LBL" />
					<span class="required">*</span>
				</label>
				<input type="text" id="role" name="role" class="span12"
					value="${user.role }">
			</div>
			<div class="span6">
				<label for="lastloggedin">
					<fmt:message key="USER_LAST_LOGGED_IN_LBL" />
					<span class="required">*</span>
				</label>
				<input type="text" id="lastloggedin" name="lastloggedin" class="span12"
					value="${user.lastloggedin }">
			</div>
			</div>
	</fieldset>
	<div class="row-fluid">
		<button type="button" id="submit" class="btn btn-primary"
			href="javascript:;">${company.id gt 0 ? 'Update' : 'Save' }
			</button>
		<button type="button" id="cancel" class="btn" href="javascript:;"
			onclick="loadForm('/company/list.do','#content')">Cancel</button>
	</div>
</form>