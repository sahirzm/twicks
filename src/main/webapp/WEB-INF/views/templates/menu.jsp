<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<div class="masthead">
	<h3 class="muted">Twicks</h3>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<tags:mainmenu menus="${mainMenu }" />
				<c:if test="${currentUser.loggedIn}">
					<div class="navbar-text pull-right dropdown">

						<a class="dropdown-toggle" id="dLabel" role="button"
							data-toggle="dropdown" data-target="#" href="#"> 
Welcome, ${currentUser.name}
						</a> <b class="caret"></b>

						<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
							<li><a href="#">My Profile</a></li>
							<li><a href="${baseUrl}/logout.do">Logout</a></li>
						</ul>

					</div>
				</c:if>
			</div>
		</div>
	</div>
	<!-- /.navbar -->
</div>
<script type="text/javascript">
	$(function() {
		$('a.dropdown-toggle').attr("data-toggle", "dropdown");
		$('.dropdown-toggle').dropdown();
	});
</script>