<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstlFormat" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="jstlFn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jstlCore:set var="contextRoot" value="${pageContext.request.contextPath}" scope="application"/>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Aashayein</title>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<link rel="shortcut icon" href="${contextRoot}/assets/img/logos/Aashayein_icon.ico">
	<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/jquery/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/bootstrap/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.css">
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/css/animsition.css">
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/hamburgers/1.1.3/hamburgers.css">
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.css">
	<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/style.css">
	<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/login.css">

	<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery.js"></script>
	<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-ui.js"></script>
	<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-validate.js"></script>
	<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-validate-additional-methods.js"></script>
	<script type="text/javascript" src="${contextRoot}/assets/js/common.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/js/animsition.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
	<script type="text/javascript" src="${contextRoot}/assets/js/setPassword.js"></script>
</head>
<body>
	<div class="limiter">
	<div class="container-login100">
		<!-- Div for spinner loader -->
		<div class="customSpinner-spinner customSpinner-spin customSpinner"></div>
		<!-- Show InnerContent After Window Load -->
		<div class="innerContent">
			<div class="wrap-login100" style="background-color: #a7a4a4bf;">
				<jstlCore:url value="/${action}" var="url"/>
				<springForm:form action="${url}" cssClass="login100-form" id="passwordForm" method="POST" modelAttribute="password">
					<span class="login100-form-title p-b-48">
						<img src="${contextRoot}/assets/img/logos/Aashayein.png" style="margin-bottom:25px;" height="60" width="240" alt="Aashayein"/>
					</span>
					
					<springForm:hidden path="tokenUUID"/>
					<springForm:errors path="tokenUUID" cssClass="error_message" element="span" />
					<table>
					<tr>
						<td><springForm:label path="password" cssClass="field_label">Password</springForm:label><span class="required">*</span></td>
					</tr>
					<tr>
						<td><springForm:password path="password" maxlength="25" cssClass="field-input" placeholder="Password"/></td>
						<td><span class="ui-icon ui-icon-custom-info-faded" title="Password must contain at least one small & one capital alphabet & a numeric digit & one special character (@,!,#,$,&,*,%) between 8 to 25 character."></span></td>
					</tr>
					<tr>
						<td><springForm:errors path="password" cssClass="error_message" element="span"/></td>
					</tr>
					<tr>
						<td><springForm:label path="confirmPassword" cssClass="field_label">Confirm Password</springForm:label><span class="required">*</span></td>
					</tr>
					<tr>
						<td><springForm:password path="confirmPassword" maxlength="25" cssClass="field-input" placeholder="Confirm Password"/></td>
					</tr>
					<tr>
						<td><springForm:errors path="confirmPassword" cssClass="error_message" element="span"/></td>
					</tr>
					</table>
					&nbsp;
					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button type="submit" class="login100-form-btn">Save</button>
						</div>
					</div>
				</springForm:form>
			</div>
		</div>
	</div>
	</div>

</body>
</html>