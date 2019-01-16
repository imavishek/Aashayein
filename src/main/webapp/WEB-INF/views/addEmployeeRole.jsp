<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>


<jsp:include page="/header.jsp" />


<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pqselect.css">


<springForm:form action="${contextRoot}/EmployeeRole/addEmployeeRole.abhi" id="addEmployeeRole" method="POST" modelAttribute="addEmployeeRole">
	<springForm:input type="hidden" path="moduleIds" value="0" />
	<table>
		<tr>
			<td><springForm:label path="roleName" cssClass="field_label">Role Name</springForm:label><span class="required">*</span></td>
			<td><springForm:input type="text" path="roleName" maxlength="25" cssClass="inputfield" placeholder="Role Name"/></td>
			<td>
				<springForm:errors path="roleName" cssClass="error_message" element="span"/>
				<div class="roleNameError"></div>
			</td>
		</tr>
		<tr>
			<td style="vertical-align:top;"><springForm:label path="" cssClass="field_label">Permissions</springForm:label><span class="required">*</span></td>
			<td>
				<table>
					<springForm:checkboxes items="${module}" path="moduleIds" delimiter="<br/>" cssStyle="margin-left:15px;" />
						<tr>
							
						</tr>
					
				</table>
			</td>
			<td style="vertical-align:top;">
				<springForm:errors path="moduleIds" cssClass="error_message" element="span"/>
				<div class="moduleIdsError"></div>
			</td>
		</tr>
		<tr>
			<td style="padding-top:10px;"></td>
			<td style="text-align:left;">
				<button type="submit" class="auto-button actionButton" data-icon="ui-icon-custom-save">Save</button>
			</td>
		</tr>
	</table>
</springForm:form>


<script type="text/javascript" src="${contextRoot}/assets/jquery/jquery.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/jquery/jquery-validate.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/jquery/jquery-validate-additional-methods.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/js/addEmployeeRole.js"></script>


<jsp:include page="/footer.jsp" />