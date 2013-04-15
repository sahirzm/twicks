<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<form id="productForm">
	<fieldset>
		<legend>Product Details</legend>
		<div class="row-fluid">
			<c:if test="${product.id gt 0}">
				<div class="span6">
					<label for="id"> Id </label>
					${product.id }
					<input type="hidden" id="id" name="id" value="${product.id }">
				</div>
			</c:if>

			<div class="span6">
				<label for="name">
					Name <span class="required">*</span>
				</label>
				<input type="text" id="name" name="name" class="span12"
					value="${product.name }">
			</div>
		</div>
		<div class="row-fluid">
			<div class="span6">
				<label for="company">
					Company<span class="required">*</span>
				</label>
				<select id="company" name="company" class="span12">
					<option value="">Select Company</option>
					<c:forEach items="${companies}" var="company">
						<option value="${company.id}"
							<c:if test="${product.company.id eq company.id}">selected="selected"</c:if>>
							<c:out value="${company.name }" />
						</option>
					</c:forEach>
				</select>
			</div>
			<c:if test="${product.id gt 0 }">
				<div class="span6">
					<label for="createdon">
						Created On <span class="required">*</span>
					</label>
					<fmt:formatDate value="${product.createdon }" />
				</div>
			</c:if>
		</div>
		<div class="row-fluid">
			<div class="span6">
				<label for="keyword">
					Keyword <span class="required">*</span>
				</label>
				<textarea type="text" id="keywords" name="keywords" class="span12">${product.keywords }</textarea>
			</div>
		</div>
	</fieldset>
	<div class="row-fluid">
		<button type="button" id="submit" class="btn btn-primary"
			href="javascript:;">${product.id gt 0 ? 'Update' : 'Save' }
		</button>
		<button type="button" id="cancel" class="btn" href="javascript:;"
			onclick="loadForm('/product/list.do','#content')">Cancel</button>
	</div>
</form>
<script type="text/javascript">
	$(function() {
		<c:choose>
		<c:when test="${product.id gt 0 }">
		changeHeading('Update Product #${product.id}');
		changeTitle('Update product #${product.id} | Admin Panel');
		</c:when>
		<c:otherwise>
		changeHeading('Create New product');
		changeTitle('Create New product | Admin Panel');
		</c:otherwise>
		</c:choose>
		$('#submit').click(function() {
			<c:choose>
			<c:when test="${product.id gt 0 }">
			submitForm("#productForm", "/product/edit.do", "#content");
			</c:when>
			<c:otherwise>
			submitForm("#productForm", "/product/create.do", "#content");
			</c:otherwise>
			</c:choose>
		});
	});
</script>