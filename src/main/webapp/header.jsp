<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="date" class="java.util.Date" />
<format:formatDate var="now" value="${date}" pattern="y" />

<jstlCore:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Aashayein</title>
	<link rel="shortcut icon" href="assets/img/logos/Aashayein_icon.ico">
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<div class="main-menu noprint">
		<ul class="sf-menu">
			<li>
				<a href="">Reports</a>
				<ul>
					<li><a href="">Shipping Volume Report</a></li>
					<li><a href="">Shipping Volume Report 2.0</a></li>
					<li><a href="">Wood Production Volume</a></li>
					<li><a href="">Report: Wood Production Volume 2.0</a></li>
				</ul>
			</li>
			
			<li>
				<a href="">Shipping</a>
				<ul>
					<li><a href="">Shipping Report</a></li>
					<li><a href="">Ready To Ship Report</a></li>
					<li><a href="">Stock Report</a></li>
					<li><a href="">Dropship Report</a></li>
				</ul>
			</li>

			<li>
				<a href="">Wood Production</a>
				<ul>
					<li><a href="">Production Status</a></li>
					<li><a href="">Special Order Wood</a></li>
					<li><a href="">Report: Exceptions Wood</a></li>
					<li><a href="">Report: Late &amp; Current Wood Order</a></li>
					<li><hr style="border:solid grey 2px;"></li>
					<li><a href="">Report: Wood Production Activity</a></li>
				</ul>
			</li>
			
			<li>
				<a href="">Cable Production</a>
				<ul>
					<li><a href="">Production Status</a></li>
					<li><a href="">Dashboard</a></li>
					<li><a href="">Special Order Cable</a></li>
					<li><a href="">Report: Exceptions Metal</a></li>
				</ul>
			</li>

			<li>
				<a href="">Sales</a>
				<ul>
					<li><a href="">Customers</a></li>
					<li><a href="">Orders</a></li>
					<li><a href="">Quotes</a></li>
					<li>
						<a href="">Reports</a>
						<ul>
							<li><a href="">Status</a></li>
							<li><a href="">Product Mix</a></li>
							<li><a href="">Financials</a></li>
							<li><a href="">Monthly Pace</a></li>
						</ul>
					</li>
				</ul>
			</li>
			
			<li>
				<a href="">Accounting</a>
				<ul>
					<li><a href="">Invoicing</a></li>
					<li><a href="">Pending QuickBooks Transactions</a></li>
					<li><a href="">Charged Without Sale</a></li>
					<li><a href="">Unbalanced Orders</a></li>
					<li><a href="">Missing Transactions</a></li>
				</ul>
			</li>
			
			<li>
				<div>Products</div>
				<ul>
					<li><a href="">Products</a></li>
					<li><a href="">Posts</a></li>
					<li><a href="">Product Lookup</a></li>
					<li><a href="">Product History</a></li>
					<li><a href="">Total sold products</a></li>
					<li><a href="">Inventory</a></li>
				</ul>
			</li>

			<li>
				<div>Admin</div>
				<ul>
					<li><a href="">Employees</a></li>
					<li><a href="">Roles</a></li>
					<li><a href="">Allowed IP</a></li>
					<li><a href="">Exceptions</a></li>
					<li><a href="">Accounting Types</a></li>
					<li><a href="">Accounting Teams</a></li>
					<li><a href="">Settings</a></li>
				</ul>
			</li>

			<li><a href="">Logout</a></li>
		</ul>
	</div>
	<div id="pageContent">
		<img src="${contextRoot}/assets/img/logos/Aashayein.png" id="pageLogo" height="45" width="210" class="noprint" alt="Aashayein"/>
		<h1 class="page-title">&nbsp;</h1>
		<div id="content">
