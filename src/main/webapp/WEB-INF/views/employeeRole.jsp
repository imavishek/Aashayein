<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstlFormat" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@taglib prefix="jstlFn" uri="http://java.sun.com/jsp/jstl/functions"%> 


<jsp:include page="/header.jsp" />


<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/tablesorter-theme-ui.css">

<span class="notification-holder">${message}</span>

<div style="margin-bottom:1em;">
	<a href='<jstlCore:url value="/EmployeeRole/showAddRole.abhi"/>' class="auto-button">Add Employee Role</a>
</div>


<table class="tablesorter">
<thead>
	<tr>
		<th style="width:25%;" class="alignCenter">Sl No.</th>
		<th class="alignCenter">Role Name</th>
		<th class="alignCenter">CreatedDate</th>
		<th class="alignCenter">UpdatedDate</th>
		<th class="alignCenter">Archive</th>
		<th class="alignCenter">Action</th>
	</tr>
</thead>
<tbody>
	<jstlCore:choose>
		<jstlCore:when test="${jstlFn:length(employeeRoles) > 0}">
			<jstlCore:forEach items="${employeeRoles}" var="role" varStatus="loopStatus">
				<tr>
					<td class="alignCenter"><jstlCore:out value="${loopStatus.index+1}"/></td>
					<td class="alignCenter"><jstlCore:out value="${role.roleName}"/></td>
					<td class="alignCenter"><jstlFormat:formatDate value="${role.recordCreated}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
					<jstlFormat:formatDate value="${role.recordUpdated}" pattern="dd-MM-yyyy HH:mm:ss" var="updatedDate" />
					<td class="alignCenter"><jstlCore:out value="${not empty updatedDate ? updatedDate : 'N/A'}"/></td>
					<td class="alignCenter"><jstlCore:out value="${role.archive eq 0 ? 'NO' : 'YES'}"/></td>
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