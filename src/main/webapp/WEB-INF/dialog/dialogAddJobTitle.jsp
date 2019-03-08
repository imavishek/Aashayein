<!-- Dialog for add job title -->
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript" src="${contextRoot}/assets/js/dialogAddJobTitle.js"></script>

<springForm:form
		action="${contextRoot}/Admin/EmployeeTitle/saveEmployeeTitle.abhi"
		id="saveEmployeeTitle" method="POST" modelAttribute="employeeTitle">
		<springForm:hidden path="titleId"/>
	
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
				<div class="errorContainer" style="padding-left:15px;"></div>
			</td>
		</tr>
	</table>
</springForm:form>