<!-- Dialog for add job title -->
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript" src="${contextRoot}/assets/js/dialogAddJobTitle.js"></script>

<springForm:form
		action="${contextRoot}/EmployeeTitle/saveEmployeeTitle.abhi"
		id="saveEmployeeTitle" method="POST" modelAttribute="employeeTitle">
		<springForm:hidden path="titleId"/>
		<springForm:errors path="titleId" cssClass="error_message" element="span" />
	
	<table>
		<tr>
			<td>
				<springForm:label path="titleName" cssClass="field_label">Title Name</springForm:label>
				<span class="required">*</span>
			</td>
			<td>
				<springForm:input type="text" path="titleName" maxlength="25" cssClass="inputfield" placeholder="Title Name" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<springForm:errors path="titleName" cssClass="error_message" element="span" />
				<div class="errorContainer" style="padding-left:15px;"></div>
			</td>
		</tr>
	</table>
</springForm:form>