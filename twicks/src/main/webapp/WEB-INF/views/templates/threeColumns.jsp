<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}"
	scope="request" />
<!DOCTYPE html>
<html>
<head>
<link href="${baseUrl}/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${baseUrl}/resources/bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet" type="text/css" />
<link href="${baseUrl}/resources/css/default.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${baseUrl}/resources/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${baseUrl}/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${baseUrl}/resources/bootstrap/js/bootstrap-datepicker.js"></script>
<title><tiles:importAttribute name="pageTitle" /> <fmt:message
		key="${pageTitle }" /></title>
<script type="text/javascript">
	var BASE_URL = "${baseUrl}";
</script>
</head>
<body>

	<tiles:insertAttribute name="menu" />

	<tiles:insertAttribute name="banner" ignore="true" />

	<div class="container-fluid">
		<div id="pageHeading" class="span12">
			<h1 class="pageHeading">
				<tiles:importAttribute name="pageHeading" ignore="true" />
				<fmt:message key="${pageHeading }" />
			</h1>
		</div>
		<div class="row-fluid">
			<div id="left" class="span3">
				<tiles:insertAttribute name="left" />
			</div>
			<div id="content" class="span6">
				<tiles:insertAttribute name="content" />
			</div>
			<div id="right" class="span3">
				<tiles:insertAttribute name="right" />
			</div>
		</div>
		<hr />
		<tiles:insertAttribute name="footer" ignore="true">
			<footer>
				<p>
					<fmt:message key="FOOTER_MSG" />
				</p>
			</footer>
		</tiles:insertAttribute>
	</div>
	<!--/.fluid-container-->

	<script type="text/javascript"
		src="${baseUrl}/resources/jquery-1.9.1.min.js"></script>
	<script type="text/javascript"
		src="${baseUrl}/resources/bootstrap/js/bootstrap.min.js"></script>

	<tiles:insertAttribute name="script" ignore="true" />

</body>
</html>