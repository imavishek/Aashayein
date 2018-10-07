<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>

<!-- set resources(css, js) path -->
<spring:url value="/assets/css/jquery/jquery-ui.css" var="jqueryUICss" />

<spring:url value="/assets/js/register.js" var="registerJS" />
<spring:url value="/assets/jquery/jquery-3.3.1.js" var="jqueryJs" />
<spring:url value="/assets/jquery/jquery-ui.js" var="jqueryUIJs" />



<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${jqueryUICss}">
    
    <script type="text/javascript" src="${jqueryJs}"></script>
	<script type="text/javascript" src="${jqueryUIJs}"></script>
	<script type="text/javascript" src="${registerJS}"></script>
	
  	
</head>
<body>
	<div align="center">
	<h1><spring:message code="registration.label.heading"/></h1>
	
	<springForm:form action="Customer/registration.abhi" id="" method="POST" modelAttribute="customer">
		<spring:message code='registration.label.firstName' var="firstName"/>
		<springForm:label path="firstName">${firstName} <span>*</span></springForm:label>
		<springForm:input type="text" path="firstName" name="firstName" id="firstName" maxlength="20" placeholder="${firstName}"/><br>
		<springForm:errors path="firstName" cssClass="" element="span"/><br>
		
		<spring:message code='registration.label.lastName' var="lastName"/>
		<springForm:label path="lastName">${lastName} <span>*</span></springForm:label>
		<springForm:input type="text" path="lastName" name="lastName" id="lastName" maxlength="10" placeholder="${lastName}"/><br>
		<springForm:errors path="lastName" cssClass="" element="span"/><br>
		
		<spring:message code='registration.label.mobile' var="mobile"/>
		<springForm:label path="mobile">${mobile} <span>*</span></springForm:label>
		<springForm:input type="text" path="mobile" name="mobile" id="mobile" maxlength="10" placeholder="${mobile}"/><br>
		<springForm:errors path="mobile" cssClass="" element="span"/><br>
		
		<spring:message code='registration.label.email' var="email"/>
		<springForm:label path="email">${email} <span>*</span></springForm:label>
		<springForm:input type="text" path="email" name="email" id="email" maxlength="40" placeholder="${email}"/><br>
		<springForm:errors path="email" cssClass="" element="span"/><br>
		
		<spring:message code='registration.label.password' var="password"/>
		<springForm:label path="password">${password} <span>*</span></springForm:label>
		<springForm:password path="password" name="password" id="password" maxlength="30" placeholder="${password}"/><br>
		<springForm:errors path="password" cssClass="" element="span"/><br>
		
		<spring:message code='registration.label.confirmPassword' var="confirmPassword"/>
		<springForm:label path="confirmPassword">${confirmPassword} <span>*</span></springForm:label>
		<springForm:password path="confirmPassword" name="confirmPassword" id="confirmPassword" maxlength="30" placeholder="${confirmPassword}"/><br>
		<springForm:errors path="confirmPassword" cssClass="" element="span"/><br>
		
		<spring:message code='registration.label.birthday' var="birthday"/>
		<springForm:label path="birthday">${birthday} <span>*</span></springForm:label>
		<springForm:input path="birthday"  name="birthday" id="birthday" placeholder="${birthday}"/><br>
		<springForm:errors path="birthday" cssClass="" element="span"/><br>
		
		<spring:message code='registration.label.gender' var="gender"/>
		<spring:message code='registration.label.gender.male' var="male"/>
		<spring:message code='registration.label.gender.female' var="female"/>
		<springForm:label path="gender" for="male">${gender} <span>*</span></springForm:label>
		<springForm:radiobutton path="gender" value="Male" id="male" name="gender"/>
		<springForm:label path="" for="male">${male}</springForm:label>
        <springForm:radiobutton path="gender" value="Female" id="female" name="gender"/>
        <springForm:label path="" for="female">${female}</springForm:label>
		<springForm:errors path="gender" cssClass="" element="span"/><br>
		
		<br>
		<spring:message code='registration.label.signup' var="signup"/>
		<spring:message code='registration.label.reset' var="reset"/>
		<input type="submit" value="${signup}" name="signup"/>
		<input type="reset" value="${reset}" name="reset"/>
		
	</springForm:form>
	</div>

	<h3 align="center">||
		<a href="?siteLanguage=en">English</a> ||
		<a href="?siteLanguage=hi">हिन्दी</a> ||
		<a href="?siteLanguage=od">ଓଡ଼ିଆ</a> ||
	</h3>
</body>
</html>
