<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>


<jsp:include page="/header.jsp" />


<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pqselect.css">


<springForm:form action="${contextRoot}/EmployeeRegistration/register.abhi" id="employeeRegistration" method="POST" modelAttribute="employee" enctype="multipart/form-data">
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
					<springForm:options items="${title}"/>
				</springForm:select>
			</td>
			<td><springForm:errors path="title" cssClass="error_message" element="span"/></td>
		</tr>
		<tr>
			<td><springForm:label path="role" cssClass="field_label">Role</springForm:label><span class="required">*</span></td>
			<td>
				<springForm:select path="role" cssClass="inputfield">
					<springForm:option value=""/>
					<springForm:options items="${title}"/>
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
			<td><springForm:input path="joiningDate" readonly="true" cssClass="inputfield" placeholder="Joining Date"/><span class="ui-icon ui-icon-info" title="Default date set to today and enter date greater or same today"></span></td>
			<td><springForm:errors path="joiningDate" cssClass="error_message" element="span"/></td>
		</tr>
		<tr>
			<td><springForm:label path="profilePhoto" cssClass="field_label">Profile Picture</springForm:label></td>
			<td><springForm:input type="file" path="profilePhoto" accept="image/jpg,image/jpeg" cssClass="inputfile" placeholder="Upload Photo"/><span class="ui-icon ui-icon-info" title="Image file must be in .jpg or .jpeg format and  must be within 300KB"></span></td>
			<td><springForm:errors path="profilePhoto" cssClass="error_message" element="span"/></td>
		</tr>
		
		<tr>
			<td style="padding-top:10px;"></td>
			<td style="text-align:right;padding-top:10px;">
				<button type="reset" class="auto-button actionButton" id="reset" data-icon="ui-icon-custom-cancel">Reset</button>
				<button type="submit" class="auto-button actionButton" data-icon="ui-icon-custom-save">Save</button>
			</td>
		</tr>
		
	</table>
</springForm:form>


<script type="text/javascript" src="${contextRoot}/assets/jquery/jquery.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/jquery/jquery-validate.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/jquery/jquery-validate-additional-methods.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/jquery/pqselect.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/js/employeeRegistration.js"></script>


<jsp:include page="/footer.jsp" />