<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="jstlFn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<jstlCore:set var="contextRoot" value="${pageContext.request.contextPath}" scope="application"/>


<!DOCTYPE html>
<html lang="en">
<head>
	<title>Aashayein</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<sec:csrfMetaTags />

	<!-- Common css file for every page -->
	<link rel="shortcut icon" href="${contextRoot}/assets/img/logos/Aashayein_icon.ico">
	<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/style.css">
	
	<!-- Common js file for every page -->
	<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery.js"></script>
	<script type="text/javascript" src="${contextRoot}/assets/js/common.js"></script>
</head>
<body>
	<div class="main-menu noprint">
		<ul class="sf-menu">
			<li style="margin-right: 30px;">
				<img src="${contextRoot}/assets/img/logos/Aashayein.png" height="30" width="210" class="noprint" alt="Aashayein"/>
			</li>
			<sec:authorize access="isAuthenticated()">

				<sec:authentication property="principal.user.gender" var="gender" />
				<sec:authentication property="principal.user.profilePhoto" var="profilePhoto" />
				<sec:authentication property="principal.user.fullName" var="name"/>

				<sec:authorize access="hasRole('ROLE_Production')">
					<li>
						<a href="">Production</a>
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_Sales')">
					<li>
						<a href="">Sales</a>
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_CustomerService')">
					<li>
						<a href="">Customer Service</a>
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_Accounting')">
					<li>
						<a href="">Accounting</a>
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_Products')">
					<li>
						<a href="">Products</a>
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_Admin')">
					<li>
						<a href="">Admin</a>
						<ul>
							<li><a href='<jstlCore:url value="/Admin/Employee/showEmployees.abhi"/>'>Employees</a></li>
							<li><a href='<jstlCore:url value="/Admin/EmployeeRole/showRoles.abhi"/>'>Roles</a></li>
							<li><a href='<jstlCore:url value="/Admin/EmployeeTitle/showTitles.abhi"/>'>Titles</a></li>
						</ul>
					</li>
				</sec:authorize>
				<li>
					<a id="logout" href="#">Logout</a>
					<form action='<jstlCore:url value="/Login/logout.abhi"/>' id="logoutForm" method="post">
						<sec:csrfInput/>
					</form>
				</li>
				<li style="float:right;">

					<jstlCore:set var="defaultPhoto" value="PP_${gender}.png"/>
					<jstlCore:set var="photo" value="${jstlFn:length(profilePhoto) > 0 ? profilePhoto : defaultPhoto}"/>

					<a href='<jstlCore:url value="/EmployeeProfile/showEmployeeProfile.abhi"/>' style="float: left; border:0; padding:0;">
						<img height="30px" width="30px" style="border-radius: 50%; padding-top: 2px;" alt="${name}" src="${contextRoot}/assets/upload/profilePictures/${photo}"/>
					</a>
					<a href='<jstlCore:url value="/EmployeeProfile/showEmployeeProfile.abhi"/>' style="float: left; border:0; padding-top:1em;">
						<span>${name}</span>
					</a>
					
				</li>
			</sec:authorize>
		</ul>
	</div>
	<div id="breadCrumb" class="noprint">
		${jstlFn:length(breadcrumb) > 0 ? breadcrumb : "&nbsp;"}
	</div>
	<div id="pageContent">
		<h1 class="page-title">${jstlFn:length(pageTitle) > 0 ? pageTitle : "&nbsp;"}</h1>
		<div id="content">
