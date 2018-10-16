<%@page language="java" contentType="text/html; charset=utf-8" 
	pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>

<jstlCore:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!-- set resources(css, js) path -->
<spring:url value="/assets/css" var="css" />
<spring:url value="/assets/js" var="js" />
<spring:url value="/assets/jquery" var="jquery" />

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	
	<link rel="stylesheet" type="text/css" href="${css}/jquery/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="${css}/register.css">
    
    
    <script type="text/javascript" src="${jquery}/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="${jquery}/jquery-ui.js"></script>
	<script type="text/javascript" src="${js}/register.js"></script>
	
  	
</head>
<body>
	<div align="center">
	<h1><spring:message code="registration.label.heading"/></h1>
	
	<springForm:form action="${contextRoot}/CustomerRegistration/register.abhi" id="" method="POST" modelAttribute="customer">
		<spring:message code='registration.label.firstName' var="firstName"/>
		<springForm:label path="firstName">${firstName} </springForm:label>
		<springForm:input type="text" path="firstName" name="firstName" id="firstName" maxlength="25" placeholder="${firstName}"/><br>
		<springForm:errors path="firstName" cssClass="error" element="span"/><br>
		
		<spring:message code='registration.label.lastName' var="lastName"/>
		<springForm:label path="lastName">${lastName} </springForm:label>
		<springForm:input type="text" path="lastName" name="lastName" id="lastName" maxlength="20" placeholder="${lastName}"/><br>
		<springForm:errors path="lastName" cssClass="error" element="span"/><br>
		
		<spring:message code='registration.label.mobile' var="mobile"/>
		<springForm:label path="mobile">${mobile} </springForm:label>
		<springForm:input type="text" path="mobile" name="mobile" id="mobile" maxlength="10" placeholder="${mobile}"/><br>
		<springForm:errors path="mobile" cssClass="error" element="span"/><br>
		
		<spring:message code='registration.label.email' var="email"/>
		<springForm:label path="email">${email} </springForm:label>
		<springForm:input type="text" path="email" name="email" id="email" maxlength="40" placeholder="${email}"/><br>
		<springForm:errors path="email" cssClass="error" element="span"/><br>
		
		<spring:message code='registration.label.password' var="password"/>
		<springForm:label path="password">${password} </springForm:label>
		<springForm:password path="password" name="password" id="password" maxlength="30" placeholder="${password}"/><br>
		<springForm:errors path="password" cssClass="error" element="span"/><br>
		
		<spring:message code='registration.label.confirmPassword' var="confirmPassword"/>
		<springForm:label path="confirmPassword">${confirmPassword} </springForm:label>
		<springForm:password path="confirmPassword" name="confirmPassword" id="confirmPassword" maxlength="30" placeholder="${confirmPassword}"/><br>
		<springForm:errors path="confirmPassword" cssClass="error" element="span"/><br>
		
		<spring:message code='registration.label.birthday' var="birthday"/>
		<springForm:label path="birthday">${birthday} </springForm:label>
		<springForm:input path="birthday"  name="birthday" id="birthday" readonly="true" placeholder="${birthday}"/><br>
		<springForm:errors path="birthday" cssClass="error" element="span"/><br>
		
		<spring:message code='registration.label.gender' var="gender"/>
		<spring:message code='registration.label.gender.male' var="male"/>
		<spring:message code='registration.label.gender.female' var="female"/>
		<springForm:label path="gender" for="male">${gender} </springForm:label>
		<springForm:radiobutton path="gender" value="Male" id="male" name="gender"/>
		<springForm:label path="" for="male">${male}</springForm:label>
        <springForm:radiobutton path="gender" value="Female" id="female" name="gender"/>
        <springForm:label path="" for="female">${female}</springForm:label><br>
		<springForm:errors path="gender" cssClass="error" element="span"/><br>
		
		<br>
		<spring:message code='registration.label.signup' var="signup"/>
		<spring:message code='registration.label.reset' var="reset"/>
		<input type="submit" value="${signup}" name="signup"/>
		<button type="button" name="reset" id="reset">${reset}</button>
		
	</springForm:form>
	</div>

	<h3 align="center">||
		<a href="${contextRoot}/CustomerRegistration/showPage.abhi?siteLanguage=en">English</a> ||
		<a href="${contextRoot}/CustomerRegistration/showPage.abhi?siteLanguage=hi">हिन्दी</a> ||
		<a href="${contextRoot}/CustomerRegistration/showPage.abhi?siteLanguage=od">ଓଡ଼ିଆ</a> ||
	</h3>
</body>
</html>
