<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="row">
	<div class="span3">
		<h3 class="muted">Please Log in</h3>
		<form method="post" action="${baseUrl}/login.do">
			<c:if test="${not empty loginError  }">
				<div class="alert alert-error">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<strong>ERROR!</strong> ${loginError }
				</div>
			</c:if>
			<div class="control-group">
				<label class="control-label" for="username">
					Username<span class="required">*</span>
				</label>
				<div class="controls">
					<input type="text" id="username" name="username"
						class="input-large">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password">
					Password<span class="required">*</span>
				</label>
				<div class="controls">
					<input type="password" id="Password" name="password"
						class="input-large">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn btn-primary">Sign In</button>
				</div>
			</div>
		</form>
	</div>
	<div id="declaration" class="span9">
		<p>A product by joint efforts of</p>
		<p>M. Tech Software Technology, VIT University</p>
		<p>under guidance of</p>
		<p>Prof. Sakthi Ganesh.</p>
	</div>
</div>