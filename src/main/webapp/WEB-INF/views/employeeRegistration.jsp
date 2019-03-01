<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstlFormat" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="jstlFn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.Date" %>
<jstlCore:set var="tomorrow" value="<%=new Date(new Date().getTime() + 60*60*24*1000)%>"/>


<jsp:include page="/WEB-INF/views/header.jsp" />


<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/jquery/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pNotify/pnotify-custom.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pNotify/animate.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pqSelect/pqselect.css">

<!-- Div for spinner loader -->
<div class="customSpinner-spinner customSpinner-spin customSpinner"></div>

<!-- Show InnerContent After Window Load -->
<div class="innerContent">

	<springForm:form action="${contextRoot}/EmployeeRegistration/saveEmployee.abhi" id="employeeRegistration" method="POST" modelAttribute="employee" enctype="multipart/form-data">
		<table>
			<tr>
				<td><springForm:label path="firstName" cssClass="field_label">First Name</springForm:label><span class="required">*</span></td>
				<td><springForm:input type="text" path="firstName" maxlength="25" cssClass="inputfield" placeholder="First Name"/></td>
				<td><springForm:errors path="firstName" cssClass="error_message" element="span"/></td>
			</tr>
			<tr>
				<td><springForm:label path="middleName" cssClass="field_label">Middle Name</springForm:label></td>
				<td><springForm:input type="text" path="middleName" maxlength="20" cssClass="inputfield" placeholder="Middle Name"/></td>
				<td><springForm:errors path="middleName" cssClass="error_message" element="span"/></td>
			</tr>
			<tr>
				<td><springForm:label path="lastName" cssClass="field_label">Last Name</springForm:label><span class="required">*</span></td>
				<td><springForm:input type="text" path="lastName" maxlength="15" cssClass="inputfield" placeholder="Last Name"/></td>
				<td><springForm:errors path="lastName" cssClass="error_message" element="span"/></td>
			</tr>
			<tr>
				<td><springForm:label path="gender" for="male" cssClass="field_label">Gender</springForm:label><span class="required">*</span></td>
				<td style="padding-left:15px;">
					<springForm:radiobutton path="gender" cssClass="inputfield" value="Male" id="male"/>
					<springForm:label path="" for="male">Male</springForm:label>
					<springForm:radiobutton path="gender" value="Female" id="female"/>
					<springForm:label path="" for="female">Female</springForm:label>
					<springForm:radiobutton path="gender" value="Other" id="other"/>
					<springForm:label path="" for="other">Other</springForm:label>
				</td>
				<td><springForm:errors path="gender" cssClass="error_message" element="span"/></td>
			</tr>
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
				<td><springForm:label path="mobileNumber" cssClass="field_label">Mobile Number</springForm:label><span class="required">*</span></td>
				<td><springForm:input type="text" path="mobileNumber" maxlength="10" cssClass="inputfield" placeholder="Mobile Number"/></td>
				<td><springForm:errors path="mobileNumber" cssClass="error_message" element="span"/></td>
			</tr>
			<tr>
				<td><springForm:label path="alternateMobileNumber" cssClass="field_label">Alternate Mobile Number</springForm:label></td>
				<td><springForm:input type="text" path="alternateMobileNumber" maxlength="10" cssClass="inputfield" placeholder="Alternate Mobile Number"/></td>
				<td><springForm:errors path="alternateMobileNumber" cssClass="error_message" element="span"/></td>
			</tr>
			<tr>
				<td><springForm:label path="email" cssClass="field_label">Email</springForm:label><span class="required">*</span></td>
				<td><springForm:input type="text" path="email" cssClass="inputfield" placeholder="Email"/></td>
				<td><springForm:errors path="email" cssClass="error_message" element="span"/></td>
			</tr>
			<tr>
				<td><springForm:label path="alternateEmail" cssClass="field_label">Alternate Email</springForm:label></td>
				<td><springForm:input type="text" path="alternateEmail" cssClass="inputfield" placeholder="Alternate Email"/></td>
				<td><springForm:errors path="alternateEmail" cssClass="error_message" element="span"/></td>
			</tr>
			<tr>
				<td><springForm:label path="joiningDate" cssClass="field_label">Joining Date</springForm:label><span class="required">*</span></td>
				<td>
					<jstlFormat:formatDate value="${tomorrow}" pattern="dd-MM-yyyy" var="tomorrow" />
					<jsp:include page="/WEB-INF/tags/date_picker.jsp">
    					<jsp:param name="fieldName" value="joiningDate"/>
    					<jsp:param name="defaultDate" value='${tomorrow}'/>
    					<jsp:param name="minDate" value="1D"/>
    					<jsp:param name="yearRange" value="+0:+10"/>
					</jsp:include>
					<span class="ui-icon ui-icon-custom-info-faded" title="Default date set to tomorrow, Select Date Greater Than Today"></span>
				</td>
				<td><springForm:errors path="joiningDate" cssClass="error_message" element="span"/></td>
			</tr>
			<tr>
				<td><springForm:label path="profilePhoto" cssClass="field_label">Profile Picture</springForm:label></td>
				<td>
					<jsp:include page="/WEB-INF/tags/file_upload.jsp">
    					<jsp:param name="fieldName" value="profilePhoto"/>
    					<jsp:param name="accept" value="image/jpg,image/jpeg"/>
    					<jsp:param name="icon" value="images"/>
    					<jsp:param name="placeHolder" value="Choose a picture"/>
					</jsp:include>
					<span class="ui-icon ui-icon-custom-info-faded" title="Image file must be in .jpg or .jpeg format and must be within 1MB"></span>
				</td>
				<td><springForm:errors path="profilePhoto" cssClass="error_message" element="span"/></td>
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

<jsp:include page="/WEB-INF/views/footer.jsp" />