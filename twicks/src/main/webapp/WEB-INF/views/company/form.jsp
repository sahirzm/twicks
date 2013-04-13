<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<form id="companyForm">
	<fieldset>
		<legend> Company Details </legend>
		<div class="row-fluid">
			<c:if test="${company.id gt 0}">
				<div class="span6">
					<label for="id"> Id </label>
					<input type="text" id="id" name="id" class="span12"
						value="${company.id }" disabled="disabled">
				</div>
			</c:if>
			<div class="span6">
				<label for="name">
					Name <span class="required">*</span>
				</label>
				<input type="text" id="name" name="name" class="span12"
					value="${company.name }">
			</div>
		</div>
	</fieldset>
	<div class="row-fluid">
		<button type="button" id="submit" class="btn btn-primary"
			href="javascript:;">${company.id gt 0 ? 'Update' : 'Save' }
			</button>
		<button type="button" id="cancel" class="btn" href="javascript:;"
			onclick="loadForm('/company/list.do','#content')">Cancel</button>
	</div>
</form>