<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstlFormat" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="jstlFn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="now" class="java.util.Date" />


<jsp:include page="/header.jsp" />


<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/jquery/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pNotify/pnotify-custom.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pNotify/animate.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pqSelect/pqselect.css">

<!-- Div for spinner loader -->
<div class="customSpinner-spinner customSpinner-spin customSpinner"></div>

<!-- Show InnerContent After Window Load -->
<div class="innerContent">

	<!-- Div for showing error -->
	<div class="notification-holder"></div>
	<div style="display: flex;">
		<div style="width: 50%;padding: 15px;">
			<springForm:form action="${contextRoot}/Employee/saveEmployee.abhi" id="employeeRegistration" method="POST" modelAttribute="editEmployee">
				<table>
				<tr>
					<td><springForm:label path="title" cssClass="field_label">Title</springForm:label><span class="required">*</span></td>
					<td>
						<springForm:select path="title" cssClass="inputfield">
							<springForm:option value=""/>
							<springForm:options items="${jobTitles}" itemLabel="titleName" itemValue="titleId" />
						</springForm:select>
					</td>
					<td><springForm:errors path="title" cssClass="error_message" element="span"/></td>
				</tr>
				<tr>
					<td><springForm:label path="role" cssClass="field_label">Role</springForm:label><span class="required">*</span></td>
					<td>
						<springForm:select path="role" cssClass="inputfield">
							<springForm:option value=""/>
							<springForm:options items="${employeeRoles}" itemLabel="roleName" itemValue="roleId" />
						</springForm:select>
					</td>
					<td><springForm:errors path="role" cssClass="error_message" element="span"/></td>
				</tr>
				<tr>
					<td style="padding-top:10px;"></td>
					<td style="text-align:right;padding-top:10px;">
						<button type="submit" class="auto-button actionButton" data-icon="ui-icon-custom-save">Save</button>
						<button type="reset" class="auto-button actionButton" id="reset" data-icon="ui-icon-custom-reset">Reset</button>
					</td>
				</tr>
				</table>
			</springForm:form>
		</div>
		
		<div style="width: 50%;padding: 15px;">
			<div  class="profilePhoto" align="center">
				<jstlCore:choose>
					<jstlCore:when test="${employee.profilePhoto eq null}">
						<img alt="${employee.fullName}" src="${contextRoot}/assets/upload/profilePictures/a.jpg">
					</jstlCore:when>
					<jstlCore:otherwise>
						<img width="600" height="400" alt="${employee.fullName}" src="${contextRoot}/assets/upload/profilePictures/${employee.profilePhoto}">
					</jstlCore:otherwise>
				</jstlCore:choose>
			</div>
			<div>
				<table>
					<tr>
						<td><span>EmployeeCode: </span></td>
						<td>${employee.employeeCode}</td>
					</tr>
					<tr>
						<td><span>EmployeeName: </span></td>
						<td>${employee.fullName}</td>
					</tr>
					<tr>
						<td><span>Gender: </span></td>
						<td>${employee.gender}</td>
					</tr>
					<tr>
						<td><span>Mobile Number: </span></td>
						<td>${employee.mobileNumber}</td>
					</tr>
					<tr>
						<td><span>Alternate Mobile Number: </span></td>
						<td>${employee.alternateMobileNumber}</td>
					</tr>
					<tr>
						<td><span>Email: </span></td>
						<td>${employee.email}</td>
					</tr>
					<tr>
						<td><span>Alternate Email: </span></td>
						<td>${employee.alternateEmail}</td>
					</tr>
					<tr>
						<td><span>Profile Activated: </span></td>
						<td>${employee.active}</td>
					</tr>
				</table>
			</div>
	
		</div>
	</div>
	
</div>



<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-validate.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-validate-additional-methods.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/pNotify/pnotify-custom.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/pqSelect/pqselect.js"></script>
<script type="text/javascript">

var contextRoot = '${contextRoot}';

$(function() {

 	//Display Success or Error message
	var message = '${message}';

	if (message != "") {
		new PNotify({
			type : '${jstlFn:toLowerCase(messageType)}', // type : 'Success' not correct so type : 'success'
			styling : "jqueryui",
			title : '${messageType}',
			text : message
		});
	}
})
</script>
<script type="text/javascript" src="${contextRoot}/assets/js/employeeRegistration.js"></script>

<jsp:include page="/footer.jsp" />