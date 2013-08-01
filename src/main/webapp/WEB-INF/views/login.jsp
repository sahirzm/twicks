<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<form method="post" action="${baseUrl}/login.do">
	<h2>
		<fmt:message key="LOGIN_MESSAGE" />
	</h2>
	<c:if test="${not empty loginError  }">
		<div class="alert alert-error">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong><fmt:message key="ERROR" /></strong>
			<fmt:message key="${loginError }" />
		</div>
	</c:if>
	<div class="control-group">
		<label class="control-label" for="username">
			<fmt:message key="USERNAME_LBL" />
			<span class="required">*</span>
		</label>
		<div class="controls">
			<input type="text" id="username" name="username" class="input-large">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="password">
			<fmt:message key="PASSWORD_LBL" />
			<span class="required">*</span>
		</label>
		<div class="controls">
			<input type="password" id="Password" name="password"
				class="input-large">
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button type="submit" class="btn btn-primary"><fmt:message key="BTN_SIGNIN_LBL" /></button>
		</div>
	</div>
</form>