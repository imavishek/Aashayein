<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstlFn" uri="http://java.sun.com/jsp/jstl/functions"%> 


<jsp:include page="/header.jsp" />


<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/tablesorter-theme-ui.css">


<div style="margin-bottom:1em;">
	<a href='<jstlCore:url value="/EmployeeRole/showAddRole.abhi"/>' class="auto-button">Add Employee Role</a>
</div>


<table class="tablesorter">
<thead>
	<tr>
		<th style="width:25%;" class="alignCenter">Role Id</th>
		<th class="alignCenter">Role Name</th>
		<th class="alignCenter">Edit / Delete</th>
	</tr>
</thead>
<tbody>
	<jstlCore:choose>
		<jstlCore:when test="${jstlFn:length(role) > 0}">
			<jstlCore:forEach items="${role}" var="role" varStatus="loopStatus">
				<tr>
					<td class="alignCenter"><jstlCore:out value="${role.key}"/></td>
					<td class="alignCenter"><jstlCore:out value="${role.value}"/></td>
					<td class="alignCenter">
						<button type="button" class="auto-button" data-icon="ui-icon-custom-edit">Edit</button>
						<button type="button" class="auto-button" data-icon="ui-icon-custom-delete">Delete</button>
					</td>
				</tr>
		  	</jstlCore:forEach>
		</jstlCore:when>
		<jstlCore:otherwise>
			<tr><td colspan="3" class="alignCenter error_message">No Employee Role Found</td></tr>
		</jstlCore:otherwise>
	</jstlCore:choose>
</tbody>
</table>


<script type="text/javascript" src="${contextRoot}/assets/jquery/jquery.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/jquery/tablesorter.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/jquery/tablesorter-widgets.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/js/employeeRole.js"></script>


<jsp:include page="/footer.jsp" />