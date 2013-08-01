<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="menus" required="true"
	type="in.ac.vit.twicks.controllers.MenuBar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:if test="${menus.isAllowed(pageContext.request)}">
	<ul class="${menus.styleClass}">
		<c:forEach items="${menus.menus}" var="menu">
			<c:if test="${menu.isAllowed(pageContext.request)}">
				<li
					class="${menu.link eq pageContext.request.servletPath ? 'active' : ''} ${menu.styleClass}"><c:choose>
						<c:when test="${menu.type eq 1}">
							<a class="${menu.hrefClass}" href="${baseUrl}${menu.link}">${menu.name}
								<c:if test="${menu.hasMenuBar()}">
									<b class="caret"></b>
								</c:if>
							</a>
						</c:when>
						<c:otherwise>
							<a class="${menu.hrefClass}" href="javascript:;"
								onclick="${menu.onClick}">${menu.name} <c:if
									test="${menu.hasMenuBar()}">
									<b class="caret"></b>
								</c:if>
							</a>
						</c:otherwise>
					</c:choose> <c:if test="${menu.hasMenuBar()}">
						<tags:mainmenu menus="${menu.menuBar}" />
					</c:if></li>
			</c:if>
		</c:forEach>
	</ul>
</c:if>