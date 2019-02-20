<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="jstlCore" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstlFormat" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@taglib prefix="jstlFn" uri="http://java.sun.com/jsp/jstl/functions"%> 


<jsp:include page="/header.jsp" />


<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/jquery/jquery-ui.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/jqGrid/jqGrid-ui.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pNotify/pnotify-custom.css">
<link rel="stylesheet" type="text/css" href="${contextRoot}/assets/css/pNotify/animate.css">


<!-- Div for spinner loader -->
<div class="customSpinner-spinner customSpinner-spin customSpinner"></div>

<!-- Show InnerContent After Window Load -->
<div class="innerContent">

	<!-- Div for showing error -->
	<div class="notification-holder"></div>
	
	<div style="margin-bottom:.8em;">
		<a href='<jstlCore:url value="/EmployeeRegistration/showRegistration.abhi"/>' class="auto-button" data-icon="ui-icon-plusthick">Add Employee</a>
	</div>
	
	<div class="wrapper">
		<table id="tableGrid"></table>
	</div>
	
	<!-- Dialog for delete role -->
	<div id="dialogDelete"></div>
</div>


<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/jquery/jquery-ui-notification.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/jqGrid/jqGrid.js"></script>
<script type="text/javascript" src="${contextRoot}/assets/plugins/pNotify/pnotify-custom.js"></script>
<script type="text/javascript">

var contextRoot = '${contextRoot}';

$(function() {

	// Display Success or Error message
	var message = '${message}';

	if (message != "") {
		new PNotify({
			type : '${jstlFn:toLowerCase(messageType)}', // type : 'Success' not correct so type : 'success'
			styling : "jqueryui",
			title : '${messageType}',
			text : message
		});
	}
})
</script>
<script type="text/javascript" src="${contextRoot}/assets/js/employee.js"></script>


<jsp:include page="/footer.jsp" />