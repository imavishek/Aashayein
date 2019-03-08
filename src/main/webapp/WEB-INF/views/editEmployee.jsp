<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<!-- Div for showing error -->
	<div class="notification-holder"></div>
	<div style="display: flex;">
		<div style="width:50%;padding:5px;">
			<div  class="profilePhoto" style="margin-left:115px;">
				<jstlCore:choose>
					<jstlCore:when test="${employee.profilePhoto eq null}">
						<img title="${employee.fullName}" alt="${employee.fullName}" src="${contextRoot}/assets/upload/profilePictures/PP_${employee.gender}.png">
					</jstlCore:when>
					<jstlCore:otherwise>
						<img title="${employee.fullName}" alt="${employee.fullName}" src="${contextRoot}/assets/upload/profilePictures/${employee.profilePhoto}">
					</jstlCore:otherwise>
				</jstlCore:choose>
			</div>
			<div>
				<table style="margin-left:25px;">
					<tr>
						<td class="display_label">EmployeeCode:</td>
						<td class="displayfield">${employee.employeeCode}</td>
					</tr>
					<tr>
						<td class="display_label">Profile Activated:</td>
						<td class="displayfield">
							<jstlCore:choose>
								<jstlCore:when test="${employee.active eq 1}">
									<img alt="Yes" title="Yes" src="${contextRoot}/assets/img/tick.png">
								</jstlCore:when>
								<jstlCore:otherwise>
									<img alt="No" title="No" src="${contextRoot}/assets/img/cancel.png">
								</jstlCore:otherwise>
							</jstlCore:choose>
						</td>
					</tr>
					<tr>
						<td class="display_label">EmployeeName:</td>
						<td class="displayfield">${employee.fullName}</td>
					</tr>
					<tr>
						<td class="display_label">Gender:</td>
						<td class="displayfield">${employee.gender}</td>
					</tr>
					<tr>
						<td class="display_label">Mobile Number:</td>
						<td class="displayfield">${employee.mobileNumber}</td>
					</tr>
					<tr>
						<td class="display_label">Alternate Mobile Number:</td>
						<td class="displayfield">${not empty employee.alternateMobileNumber ? employee.alternateMobileNumber : 'N/A'}</td>
					</tr>
					<tr>
						<td class="display_label">Email:</td>
						<td class="displayfield">${employee.email}</td>
					</tr>
					<tr>
						<td class="display_label">Alternate Email:</td>
						<td class="displayfield">${not empty employee.alternateEmail ? employee.alternateEmail : 'N/A'}</td>
					</tr>
					<jstlCore:if test="${employee.active eq 1}">
						<tr>
							<td class="display_label">Joined On:</td>
							<td class="displayfield"><jstlFormat:formatDate value="${employee.joiningDate}" pattern="dd-MM-yyyy"/></td>
						</tr>
					</jstlCore:if>
					<tr>
						<td class="display_label">Country:</td>
						<td class="displayfield">${not empty employee.countryName ? employee.countryName : 'N/A'}</td>
					</tr>
					<tr>
						<td class="display_label">State:</td>
						<td class="displayfield">${not empty employee.stateName ? employee.stateName : 'N/A'}</td>
					</tr>
					<tr>
						<td class="display_label">City:</td>
						<td class="displayfield">${not empty employee.cityName ? employee.cityName : 'N/A'}</td>
					</tr>
					<tr>
						<td class="display_label">PostalCode:</td>
						<td class="displayfield">${not empty employee.postalCode ? employee.postalCode : 'N/A'}</td>
					</tr>
					<tr>
						<td class="display_label">AddressLine1:</td>
						<td class="displayfield">${not empty employee.addressLine1 ? employee.addressLine1 : 'N/A'}</td>
					</tr>
					<tr>
						<td class="display_label">AddressLine2:</td>
						<td class="displayfield">${not empty employee.addressLine2 ? employee.addressLine2 : 'N/A'}</td>
					</tr>
				</table>
			</div>
		</div>
		<div style="width:50%;padding:5px 5px 5px 15px;margin-top:20px;">
			<springForm:form action="${contextRoot}/Admin/Employee/saveEmployee.abhi" id="editEmployee" method="POST" modelAttribute="editEmployee">
			
				<springForm:hidden path="employeeId"/>
				<springForm:errors path="employeeId" cssClass="error_message" element="span" />
				
				<springForm:hidden path="employeeCode"/>
				<springForm:errors path="employeeCode" cssClass="error_message" element="span" />
		
				<table>
				<caption style="font-size:20px;font-weight:bold;padding-bottom:30px;color:#1c94c4;text-decoration:underline;">Edit Title, Role And JoiningDate</caption>

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
				<%-- <jstlCore:if test="${employee.active eq 0}"> --%>
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
							<span class="ui-icon ui-icon-custom-info-faded" title="Select Date Greater Than Today"></span>
						</td>
						<td><springForm:errors path="joiningDate" cssClass="error_message" element="span"/></td>
					</tr>
				<%-- </jstlCore:if> --%>
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
	</div>
</div>

<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-validate.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-validate-additional-methods.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/pqSelect/pqselect.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/pNotify/pnotify-custom.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/js/editEmployee.js"></script>

<jsp:include page="/WEB-INF/views/footer.jsp" />