<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:if test="${sideMenu.isAllowed(pageContext.request)}">
	<div class="accordion" id="accordion">
		<c:forEach items="${sideMenu.menus}" var="menu" varStatus="i">
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle" data-toggle="collapse"
						data-target="#collapse${i.index}" data-parent="#accordion"
						href="javascript:;"> <fmt:message key="${menu.name}" />
					</a>
				</div>
				<tags:leftmenu menus="${menu.menuBar}" id="${i.index}" />
			</div>
		</c:forEach>
	</div>
</c:if>
<script type="text/javascript">
	$(function() {
		$('#accordion .accordion-toggle').click(function() {
			var id = $(this).attr("data-target");
			$(id).addClass("in");
		});
	});
</script>