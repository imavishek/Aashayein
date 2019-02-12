<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstlFormat" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="jstlFn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.Date" %>
<jstlCore:set var="tomorrow" value="<%=new Date(new Date().getTime() + 60*60*24*1000)%>"/>


<jsp:include page="/header.jsp" />


<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/jquery/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pNotify/animate.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pqSelect/pqselect.css">

<!-- Div for spinner loader -->
<div class="customSpinner-spinner customSpinner-spin customSpinner"></div>

<!-- Show InnerContent After Window Load -->
<div class="innerContent">
	<!-- Div for showing error -->
	<div class="notification-holder"></div>
	
	<div class="displayInfo"><button type="button" id="editProfile" class="auto-button" data-icon="ui-icon-custom-edit" style="float:right;">Edit</button></div>
	<div class="editInfo"><button type="button" class="auto-button cancelEdit" data-icon="ui-icon-custom-cancel" style="float:right;">Cancel</button></div>
	<div style="clear:both;"></div>
	
	<spring:hasBindErrors name="editEmployeeProfile">
		<jstlCore:set var="hasBindErrors" value="true"/>
	</spring:hasBindErrors>
	
	<springForm:form action="${contextRoot}/EmployeeProfile/saveEmployeeProfile.abhi" name="editEmployeeProfile" id="editEmployeeProfile" method="POST" modelAttribute="editEmployeeProfile" enctype="multipart/form-data">
	<div style="display: flex;">
		<div style="width:65%;padding:5px;">
			<table>
				<tr>
					<td class="editInfo"><springForm:label path="firstName" cssClass="field_label">First Name</springForm:label><span class="required">*</span></td>
					<td class="editInfo"><springForm:input type="text" path="firstName" maxlength="25" cssClass="inputfield" placeholder="First Name"/></td>
					<td class="editInfo"><springForm:errors path="firstName" cssClass="error_message" element="span"/></td>
					
					<td class="displayInfo display_label">First Name:</td>
					<td class="displayInfo displayfield">${employee.firstName}</td>
				</tr>
				<tr>
					<td class="editInfo"><springForm:label path="middleName" cssClass="field_label">Middle Name</springForm:label></td>
					<td class="editInfo"><springForm:input type="text" path="middleName" maxlength="20" cssClass="inputfield" placeholder="Middle Name"/></td>
					<td class="editInfo"><springForm:errors path="middleName" cssClass="error_message" element="span"/></td>
					
					<td class="displayInfo display_label">Middle Name:</td>
					<td class="displayInfo displayfield">${not empty employee.middleName ? employee.middleName : "N/A"}</td>
				</tr>
				<tr>
					<td class="editInfo"><springForm:label path="lastName" cssClass="field_label">Last Name</springForm:label><span class="required">*</span></td>
					<td class="editInfo"><springForm:input type="text" path="lastName" maxlength="15" cssClass="inputfield" placeholder="Last Name"/></td>
					<td class="editInfo"><springForm:errors path="lastName" cssClass="error_message" element="span"/></td>
					
					<td class="displayInfo display_label">Last Name:</td>
					<td class="displayInfo displayfield">${employee.lastName}</td>
				</tr>
				<tr>
					<td class="editInfo"><springForm:label path="gender" for="male" cssClass="field_label">Gender</springForm:label><span class="required">*</span></td>
					<td class="editInfo" style="padding-left:15px;">
						<springForm:radiobutton path="gender" cssClass="inputfield" value="Male" id="male"/>
						<springForm:label path="" for="male">Male</springForm:label>
						<springForm:radiobutton path="gender" value="Female" id="female"/>
						<springForm:label path="" for="female">Female</springForm:label>
						<springForm:radiobutton path="gender" value="Other" id="other"/>
						<springForm:label path="" for="other">Other</springForm:label>
					</td>
					<td class="editInfo"><springForm:errors path="gender" cssClass="error_message" element="span"/></td>
					
					<td class="displayInfo display_label">Gender:</td>
					<td class="displayInfo displayfield">${employee.gender}</td>
				</tr>
				<tr>
					<td class="editInfo"><springForm:label path="mobileNumber" cssClass="field_label">Mobile Number</springForm:label><span class="required">*</span></td>
					<td class="editInfo"><springForm:input type="text" path="mobileNumber" maxlength="10" cssClass="inputfield" placeholder="Mobile Number"/></td>
					<td class="editInfo"><springForm:errors path="mobileNumber" cssClass="error_message" element="span"/></td>
					
					<td class="displayInfo display_label">Mobile Number:</td>
					<td class="displayInfo displayfield">${employee.mobileNumber}</td>
				</tr>
				<tr>
					<td class="editInfo"><springForm:label path="alternateMobileNumber" cssClass="field_label">Alternate Mobile Number</springForm:label></td>
					<td class="editInfo"><springForm:input type="text" path="alternateMobileNumber" maxlength="10" cssClass="inputfield" placeholder="Alternate Mobile Number"/></td>
					<td class="editInfo"><springForm:errors path="alternateMobileNumber" cssClass="error_message" element="span"/></td>
					
					<td class="displayInfo display_label">Alternate Mobile Number:</td>
					<td class="displayInfo displayfield">${not empty employee.alternateMobileNumber ? employee.alternateMobileNumber : "N/A"}</td>
				</tr>
				<tr>
					<td class="editInfo"><springForm:label path="alternateMobileNumber" cssClass="field_label">Alternate Email</springForm:label></td>
					<td class="editInfo"><springForm:input type="text" path="alternateEmail" cssClass="inputfield" placeholder="Alternate Email"/></td>
					<td class="editInfo"><springForm:errors path="alternateEmail" cssClass="error_message" element="span"/></td>
					
					<td class="displayInfo display_label">Alternate Email:</td>
					<td class="displayInfo displayfield">${not empty employee.alternateMobileNumber ? employee.alternateMobileNumber : "N/A"}</td>
				</tr>
				<tr>
					<td class="editInfo"><springForm:label path="country" cssClass="field_label">Country</springForm:label><span class="required">*</span></td>
					<td class="editInfo">
						<springForm:select path="country" cssClass="inputfield">
							<springForm:option value=""/>
							<springForm:options items="${countries}" itemLabel="countryName" itemValue="countryId" />
						</springForm:select>
					</td>
					<td class="editInfo"><springForm:errors path="country" cssClass="error_message" element="span"/></td>
					
					<td class="displayInfo display_label">Country:</td>
					<td class="displayInfo displayfield">${employee.countryName}</td>
				</tr>
				<tr>
					<td class="editInfo"><springForm:label path="state" cssClass="field_label">State</springForm:label><span class="required">*</span></td>
					<td class="editInfo">
						<springForm:select path="state" cssClass="inputfield">
							<springForm:option value=""/>
						</springForm:select>
					</td>
					<td class="editInfo"><springForm:errors path="state" cssClass="error_message" element="span"/></td>
					
					<td class="displayInfo display_label">State:</td>
					<td class="displayInfo displayfield">${employee.stateName}</td>
				</tr>
				<tr>
					<td class="editInfo"><springForm:label path="city" cssClass="field_label">City</springForm:label><span class="required">*</span></td>
					<td class="editInfo">
						<springForm:select path="city" cssClass="inputfield">
							<springForm:option value=""/>
						</springForm:select>
					</td>
					<td class="editInfo"><springForm:errors path="city" cssClass="error_message" element="span"/></td>
					
					<td class="displayInfo display_label">City:</td>
					<td class="displayInfo displayfield">${employee.cityName}</td>
				</tr>
				<tr>
					<td class="editInfo"><springForm:label path="pinCode" cssClass="field_label">PinCode</springForm:label><span class="required">*</span></td>
					<td class="editInfo"><springForm:input type="text" path="pinCode" maxlength="6" cssClass="inputfield" placeholder="PinCode"/></td>
					<td class="editInfo"><springForm:errors path="pinCode" cssClass="error_message" element="span"/></td>
					
					<td class="displayInfo display_label">PinCode:</td>
					<td class="displayInfo displayfield">${employee.postalCode}</td>
				</tr>
				<tr>
					<td class="editInfo"><springForm:label path="addressLine1" cssClass="field_label">AddressLine1</springForm:label><span class="required">*</span></td>
					<td class="editInfo"><springForm:textarea path="addressLine1" rows="3" column="30" cssClass="inputfield" style="height:70px;"/></td>
					<td class="editInfo"><springForm:errors path="addressLine1" cssClass="error_message" element="span"/></td>
					
					<td class="displayInfo display_label">AddressLine1:</td>
					<td class="displayInfo displayfield">${employee.addressLine1}</td>
				</tr>
				<tr>
					<td class="editInfo"><springForm:label path="addressLine2" cssClass="field_label">AddressLine2</springForm:label></td>
					<td class="editInfo"><springForm:textarea path="addressLine2" rows="3" column="30" cssClass="inputfield" style="height:70px;"/></td>
					<td class="editInfo"><springForm:errors path="addressLine2" cssClass="error_message" element="span"/></td>
					
					<td class="displayInfo display_label">AddressLine2:</td>
					<td class="displayInfo displayfield">${not empty employee.addressLine2 ? employee.addressLine2 : "N/A"}</td>
				</tr>
				<tr class="editInfo">
					<td style="padding-top:10px;"></td>
					<td style="text-align:right;padding-top:10px;">
						<button type="submit" class="auto-button actionButton" data-icon="ui-icon-custom-save">Save</button>
						<button type="button" class="auto-button actionButton cancelEdit" data-icon="ui-icon-custom-cancel">Cancel</button>
					</td>
				</tr>
			</table>
		</div>
		<div style="padding:5px;margin-top:20px;text-align: center;">
			<div class="profilePhoto">
				<jstlCore:choose>
					<jstlCore:when test="${employee.profilePhoto eq null}">
						<img title="${employee.fullName}" alt="${employee.fullName}" src="${contextRoot}/assets/upload/profilePictures/PP_${employee.gender}.png">
					</jstlCore:when>
					<jstlCore:otherwise>
						<img title="${employee.fullName}" alt="${employee.fullName}" src="${contextRoot}/assets/upload/profilePictures/${employee.profilePhoto}">
					</jstlCore:otherwise>
				</jstlCore:choose>
			</div>
			<div class="editInfo">
				<jsp:include page="/WEB-INF/tags/file_upload.jsp">
    				<jsp:param name="fieldName" value="photo"/>
    				<jsp:param name="accept" value="image/jpg,image/jpeg"/>
    				<jsp:param name="icon" value="images"/>
   					<jsp:param name="placeHolder" value="Choose a picture"/>
				</jsp:include>
				<span class="ui-icon ui-icon-custom-info-faded" title="Image file must be in .jpg or .jpeg format and  must be within 300KB"></span>
			</div>
			<div class="editInfo"><springForm:errors path="photo" cssClass="error_message" element="span"/></div>
			<div>
				<table>
					<tr>
						<td class="display_label">EmployeeCode:</td>
						<td class="displayfield">${employee.employeeCode}</td>
					</tr>
					<tr>
						<td class="display_label">EmployeeName:</td>
						<td class="displayfield">${employee.fullName}</td>
					</tr>
					<tr>
						<td class="display_label">Email:</td>
						<td class="displayfield">${employee.email}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	</springForm:form>
</div>

<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-ui-notification.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-validate.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-validate-additional-methods.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/pqSelect/pqselect.js"></script>
<script type="text/javascript">

var contextRoot = '${contextRoot}';

// Sending the state and city id of the employee to js file
var state = '${editEmployeeProfile.state}';
var city = '${editEmployeeProfile.city}';

// If binding error occurs in controller then show the editInfo class or toggle class
var hasBindErrors = '${hasBindErrors}';

$(function() {

	// Display Success or Error message
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
<script type="text/javascript" src="${contextRoot}/assets/js/employeeProfile.js"></script>

<jsp:include page="/footer.jsp" />