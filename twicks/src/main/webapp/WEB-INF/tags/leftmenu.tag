<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ attribute name="menus" required="true"
	type="in.ac.vit.andromedics.controllers.MenuBar"%>
<%@ attribute name="id" required="true"%>
<c:if test="${menus.isAllowed(pageContext.request)}">
	<div class="accordion-body collapse" id="collapse${id}">
		<ul class="${menus.styleClass}">
			<c:forEach items="${menus.menus}" var="menu">
				<c:if test="${menu.isAllowed(pageContext.request)}">
					<li class="${menu.styleClass}"><c:choose>
							<c:when test="${menu.type eq 1}">
								<a class="${menu.hrefClass}" href="${baseUrl}${menu.link}"><fmt:message
										key="${menu.name}" /> </a>
							</c:when>
							<c:otherwise>
								<a class="${menu.hrefClass}" href="javascript:;"
									onclick="${menu.onClick}"><fmt:message key="${menu.name}" />
								</a>
							</c:otherwise>
						</c:choose></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</c:if>