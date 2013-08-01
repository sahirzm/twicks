<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div id="myCarousel" class="carousel slide">
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
	</ol>
	<!-- Carousel items -->
	<div class="carousel-inner">
		<div class="item">
			<img alt="" style="border-radius:4px" src="${baseUrl }/resources/img/slide1.jpg"/>
		</div>
		<div class="active item">
			<img alt="" style="border-radius:4px" src="${baseUrl }/resources/img/slide2.jpeg"/>
		</div>
		<div class="item">
			<img alt="" style="border-radius:4px" src="${baseUrl }/resources/img/slide3.jpg"/>
		</div>
	</div>
	<!-- Carousel nav -->
	<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
	<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
</div>
<script type="text/javascript">
	$(function() {
		$('#myCarousel').carousel({
			interval : 5000
		});
	});
</script>