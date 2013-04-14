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
		<div class="span6">
				<label for="createdon">
					Created On <span class="required">*</span>
				</label>
				<input type="text" id="createdon" name="createdon" class="span12"
					value="${product.createdon }">
			</div>
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
		<button type="button" id="submit" class="btn btn-primary">${company.id gt 0 ? 'Update' : 'Save' }
			</button>
		<button type="button" id="cancel" class="btn"
			onclick="loadForm('/company/list.do','#content')">Cancel</button>
	</div>
</form>