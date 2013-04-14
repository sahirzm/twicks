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
		<div class="row-fluid">
			<div class="span6">
				<label for="address">
					Address <span class="required">*</span>
				</label>
				<textarea type="text" id="address" name="address" class="span12">${company.address }</textarea>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span6">
				<label for="subscriptionDate">
					Subscription Date <span class="required">*</span>
				</label>
				<input type="text" id="subscriptionDate" name="subscriptionDate"
					class="span12" value="${company.subscriptionDate }">
			</div>
			<c:if test="${company.id gt 0}">
				<div class="span6">
					<label for="createdon">
						Created On <span class="required">*</span>
					</label>
					<input type="text" id="createdon" name="createdon" class="span12"
						value="${company.createdon }">
				</div>
			</c:if>
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
<script type="text/javascript">
	$(function() {
		<c:choose>
		<c:when test="${company.id gt 0 }">
		changeHeading('Update Company #${company.id}');
		changeTitle('Update Company #${company.id} | Admin Panel');
		</c:when>
		<c:otherwise>
		changeHeading('Create New Company');
		changeTitle('Create New Company | Admin Panel');
		</c:otherwise>
		</c:choose>
		var subscriptionDate = $('#subscriptionDate').datepicker({
			format : 'yyyy-mm-dd'
		}).on('show', function(ev) {
			$('#subscriptionDate').attr("disabled", "disabled");
		}).on('changeDate', function(ev) {
			$('#subscriptionDate').datepicker("hide");
			$('#subscriptionDate').removeAttr("disabled");
		}).on('hide', function(ev) {
			$('#subscriptionDate').removeAttr("disabled");
			});
		$('#submit').click(function() {
			<c:choose>
			<c:when test="${company.id gt 0 }">
			console.log("Calling");
			submitForm("#companyForm", "/company/edit.do", "#content");
			</c:when>
			<c:otherwise>
			submitForm("#companyForm", "/company/create.do", "#content");
			</c:otherwise>
			</c:choose>
		});
	});
</script>