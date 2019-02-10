<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>

<jstlCore:set var="contextRoot" value="${pageContext.request.contextPath}" scope="application"/>


<!DOCTYPE html>
<html lang="en">

<head>

	<title>${title}</title>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">	

	<link rel="shortcut icon" href="${contextRoot}/assets/img/logos/Aashayein_icon.ico">
	<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:400,700" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/error.css">
</head>

<body>

	
	<div id="notfound">
		<div class="errorTitle">${errorTitle}</div>
		<div class="notfound">
			<div class="notfound-500">
				<h1>500</h1>
			</div>
			<p class="errorDetails">${errorMessage}</p>
			<a href="${contextRoot}">Go To HomePage</a>
		</div>
	</div>

</body>
</html>
