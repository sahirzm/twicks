<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<table class="table table-bordered">
	<tr class="success">
		<td colspan="4">Product Details</td>
	</tr>
	<tr>
		<td>Product Id</td>
		<td>${product.id }</td>
	</tr>
	<tr>
		<td>Name</td>
		<td>${product.name }</td>
	</tr>
	<tr>
		<td>Keyword</td>
		<td>${product.keywords }</td>
	</tr>
	<tr>
	<td>Created On</td>
		<td>${product.createdon }</td>
	</tr>
</table>

<div class="row-fluid">
	<button type="button" id="edit" class="btn"
		onclick="loadForm('/product/edit.do','#content','productId=${product.id}')">
		Edit
	</button>
	<button type="button" id="delete" class="btn warn"
		onclick="loadForm('/product/delete.do','#content','productId=${product.id }')">
		Delete
	</button>
</div>
<script type="text/javascript">
	$(function() {
		changeHeading('Product Details #${product.id}');
		changeTitle('Product Details #${product.id}');
	});
</script>