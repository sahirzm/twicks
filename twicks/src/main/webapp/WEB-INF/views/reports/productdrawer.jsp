<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="chart_div" style="height: 500px; width: 90%" class="span12"></div>
<div id="cchart_div" style="margin-top:20px;height: 500px; width: 90%" class="span12"></div>

<script type="text/javascript">
	$(function() {
		var data = google.visualization.arrayToDataTable([
				[ 'Hour', 'Twitter', 'Facebook' ], 
				<c:forEach items="${data}" var="dd">
				['${dd['hour']}', ${dd['score']}],
				</c:forEach>
				 ]);

		var cdata = google.visualization.arrayToDataTable([
		        [ 'Hour', 'Twitter', 'Facebook' ], 
		          <c:forEach items="${data}" var="dd">
		              ['${dd['hour']}', ${dd['count']}],
		          </c:forEach>
		      	]);	
		var options = {
			title : 'Product Rating',
			animation: {
				duration:5000,
				easing: 'out'
			},
			pointSize: 3
		};

		var coptions = {
				title : 'Feed Count',
				animation: {
					duration:5000,
					easing: 'out'
				},
				pointSize: 3
			};

		var chart = new google.visualization.LineChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);
		var cchart = new google.visualization.ColumnChart(document
				.getElementById('cchart_div'));
		cchart.draw(cdata, coptions);

	});
</script>