<%@page language="java" contentType="text/html; charset=utf-8" 
	pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jstlCore:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!-- set resources(css, js) path -->
<spring:url value="/assets/css" var="css" />


<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	

	<title>Page Not Available</title>

	
	<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:400,700" rel="stylesheet"> 
	<link rel="stylesheet" type="text/css" href="${css}/error.css">
</head>

<body>
	<div id="notfound">
		<div class="notfound">
			<div class="notfound-404">
				<h1>4<span>0</span>4</h1>
			</div>
			<p>The page you are looking for might have been removed had its name changed or is temporarily unavailable.</p>
			<a href="${contextRoot}">Go To HomePage</a>
		</div>
	</div>

</body>
</html>
