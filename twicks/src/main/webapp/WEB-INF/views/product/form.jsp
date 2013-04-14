<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<form id="productForm">
	<fieldset>
		<legend> Product Details </legend>
		<div class="row-fluid">
			<c:if test="${product.id gt 0}">
				<div class="span6">
					<label for="id"> Id </label>
					<input type="text" id="id" name="id" class="span12"
						value="${product.id }" disabled="disabled">
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
		<div class="span4">
				<label for="createdon">
					<fmt:message key="PRODUCT_CREATED_ON_LBL" />
				</label>
				<input type="text" id="createdon" name="createdon" class="span12"
					value="${product.createdon }">
			</div>
			<div class="span6">
				<label for="keyword">
					<fmt:message key="PRODUCT_KEYWORD_LBL" />
				</label>
				<textarea type="text" id="keyword" name="keyword" class="span12">${product.keyword }</textarea>
			</div>
	</fieldset>