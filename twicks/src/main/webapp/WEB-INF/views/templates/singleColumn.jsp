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
<title><tiles:insertAttribute name="pageTitle" /></title>
<script type="text/javascript">
	var BASE_URL = "${baseUrl}";
</script>
</head>
<body>
	<div class="container">
		<tiles:insertAttribute name="menu" />

		<tiles:insertAttribute name="banner" ignore="true" />
		<div id="pageHeading" class="span12">
			<h1 class="pageHeading">
				<tiles:insertAttribute name="pageHeading" ignore="true" />
			</h1>
		</div>
		<div id="content" class="span12">
			<tiles:insertAttribute name="content" />
		</div>
		<hr />

		<footer>
			<p>&copy; M. Tech Software Technology 2013. VIT University.</p>
		</footer>

	</div>
	<!--/container-->
	<div class="modal-backdrop" style="display: none" id="ajaxIndicator">
		<img src="${baseUrl }/resources/img/ajax-loader.gif"
			style="top: 50%; margin-left: 40%; position: fixed" />
	</div>

	<script type="text/javascript"
		src="${baseUrl}/resources/js/andromedics.js"></script>
	<tiles:insertAttribute name="script" ignore="true" />

</body>
</html>