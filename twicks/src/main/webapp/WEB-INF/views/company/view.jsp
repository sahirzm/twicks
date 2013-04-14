<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<table class="table table-bordered">
	<tr class="success">
		<td colspan="4"><fmt:message key="STORE_DETAILS" /></td>
	</tr>
	<tr>
		<td><fmt:message key="STORE_ID_LBL" /></td>
		<td>${store.id }</td>
		<td><fmt:message key="STORE_NAME_LBL" /></td>
		<td>${store.name }</td>
	</tr>
	<tr class="success">
		<td colspan="4"><fmt:message key="STORE_ADDRESS_DETAILS" /></td>
	</tr>
	<tr>
		<td><fmt:message key="STORE_STREET1_LBL" /></td>
		<td>${store.street1 }</td>
		<td><fmt:message key="STORE_STREET2_LBL" /></td>
		<td>${store.street2 }</td>
	</tr>
	<tr>
		<td><fmt:message key="STORE_CITY_LBL" /></td>
		<td>${store.city }</td>
		<td><fmt:message key="STORE_STATE_LBL" /></td>
		<td>${store.state }</td>
	</tr>
	<tr>
		<td><fmt:message key="STORE_COUNTRY_LBL" /></td>
		<td>${store.country }</td>
		<td><fmt:message key="STORE_PINCODE_LBL" /></td>
		<td>${store.pincode }</td>
	</tr>
	<tr>
		<td><fmt:message key="STORE_PHONE_NO_LBL" /></td>
		<td>${store.phoneNo }</td>
		<td><fmt:message key="STORE_FAX_LBL" /></td>
		<td>${store.fax }</td>
	</tr>
	<tr>
		<td><fmt:message key="STORE_EMAIL_LBL" /></td>
		<td>${store.email }</td>
		<td><fmt:message key="STORE_WEBSITE_LBL" /></td>
		<td>${store.website }
	</tr>
	<tr class="success">
		<td colspan="4"><fmt:message key="STORE_SUBSCRIPTION_DETAILS" /></td>
	</tr>
	<tr>
		<td><fmt:message key="STORE_SUBSCRIPTION_DATE_LBL" /></td>
		<td><fmt:formatDate value="${store.subscriptionDate }"
				type="date" /></td>
		<td><fmt:message key="STORE_ACTIVE_LBL" /></td>
		<td><c:choose>
				<c:when test="${store.active eq 'ACTIVE' }">
					<fmt:message key="ACTIVE" />
				</c:when>
				<c:otherwise>
					<fmt:message key="INACTIVE" />
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td><fmt:message key="CREATED_ON_LBL" /></td>
		<td><fmt:formatDate value="${store.createdOn }" type="date" /></td>
		<td></td>
		<td></td>
	</tr>
</table>

<div class="row-fluid">
	<button type="button" id="edit" class="btn"
		onclick="loadForm('/company/edit.do','#content','companyId=${company.id}')">
		<fmt:message key="BTN_EDIT_LBL" />
	</button>
	<button type="button" id="delete" class="btn warn"
		onclick="loadForm('/company/delete.do','#content','companyId=${company.id }')">
		<fmt:message key="BTN_DELETE_LBL" />
	</button>
</div>
<script type="text/javascript">
	$(function() {
		changeHeading('View Company Details #${company.id}');
		changeTitle('View Company Details #${company.id}');
	});
</script>