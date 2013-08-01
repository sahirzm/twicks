<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="totalProducts" value="${fn:length(map)}" />
<c:forEach items="${map}" var="rec" varStatus="i">
	<c:if test="${i.count%4 ==0 }">
		<div class="row">
	</c:if>
	<div class="span3 productScore" id="${rec['productId']}">
		<div class="name">
			<c:out value="${rec['productName']}" />
		</div>
		<div class="score">
			<div>
				<c:out value="${rec['score']}" />
			</div>
		</div>
	</div>
	<c:if test="${i.count%4 ==0 }">
	</div>
	</c:if>
	<c:if test="${i.count%4 ==0 && i.count!=0}">
		<div class="span12 insightContainer"></div>
	</c:if>
</c:forEach>
<c:if test="${totalProducts % 4 != 0}">
	</div>
	<div class="span12 insightContainer"></div>
</c:if>
<script type="text/javascript">
	$(function() {
		$('.productScore').click(function() {
			$('.insightContainer').slideUp(500);
			$.each($('.insightContainer'), function(con) {
				$(con).html('');
			});
			var container = $(this).nextAll('.insightContainer:first');
			$.ajax({
				url : BASE_URL + '/productdrawer.do',
				type : "get",
				data : 'productId=' + $(this).attr('id'),
				success : function(data) {
					$(container).slideDown(1000, function() {
						$(container).html(data);
					});
				}
			});

		});
	});
</script>