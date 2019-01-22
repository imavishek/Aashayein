<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstlFn" uri="http://java.sun.com/jsp/jstl/functions"%> 


<jsp:include page="/header.jsp" />


<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/jquery/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/tableSorter/tablesorter-theme-ui.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pNotify/pnotify-custom.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pNotify/animate.css">


<div style="margin-bottom:1em;">
	<button id="addTitle" class="auto-button">Add Employee Title</button>
</div>


<table class="tablesorter">
<thead>
	<tr>
		<th class="alignCenter">Sl No.</th>
		<th class="alignCenter">Title Name</th>
		<th class="alignCenter">Edit / Delete</th>
	</tr>
</thead>
<tbody>
	<jstlCore:choose>
		<jstlCore:when test="${jstlFn:length(title) > 0}">
			<jstlCore:forEach items="${title}" var="title" varStatus="loopStatus">
				<tr>
					<td class="alignCenter"><jstlCore:out value="${title.key}"/></td>
					<td class="alignCenter"><jstlCore:out value="${title.value}"/></td>
					<td class="alignCenter">
						<button type="button" class="auto-button" data-icon="ui-icon-custom-edit">Edit</button>
						<button type="button" class="auto-button" data-icon="ui-icon-custom-delete">Delete</button>
					</td>
				</tr>
		  	</jstlCore:forEach>
		</jstlCore:when>
		<jstlCore:otherwise>
			<tr><td colspan="3" class="alignCenter error_message">No Employee Title Found</td></tr>
		</jstlCore:otherwise>
	</jstlCore:choose>
</tbody>
</table>

<div id="dialog-form" title="Add Employee Title">
	<form action="addEmployeeTitle.abhi" id="addEmployeeTitle" method="GET">
		<table>
			<tr>
				<td><label for="titleName" class="field_label">Title Name</label><span class="required">*</span></td>
				<td><input type="text" name="titleName" id="titleName" maxlength="25" class="inputfield" placeholder="Title Name"/></td>
			</tr>
			<tr><td></td><td class="errorContainer" style="padding-left:15px;"></td></tr>
		</table>
		<!-- Allow form submission with keyboard without duplicating the dialog button -->
		<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
	</form>
</div>



<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/tableSorter/tablesorter.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/tableSorter/widget-uitheme.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/pNotify/pnotify-custom.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-ui-notification.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/js/employeeTitle.js"></script>


<jsp:include page="/footer.jsp" />