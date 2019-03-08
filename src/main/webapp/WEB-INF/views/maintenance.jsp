<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>

<jstlCore:set var="contextRoot" value="${pageContext.request.contextPath}" scope="application"/>


<!DOCTYPE html>
<html lang="en">

<head>

	<title>Under Maintenance</title>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">	

	<link rel="shortcut icon" href="${contextRoot}/assets/img/logos/Aashayein_icon.ico">
	<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:400,700" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/error.css">
</head>

<body>


	<div id="notfound">
		<div class="errorTitle">Under Maintenance</div>
		<div class="notfound">
			<img src="${contextRoot}/assets/img/maintenance.png" />
			<p class="errorDetails">The site is undergoing a brief bit of
				maintenance.We apologize for the inconvenience, we're doing our best
				to get things back to working order for you.</p>
		</div>
	</div>

</body>
</html>
