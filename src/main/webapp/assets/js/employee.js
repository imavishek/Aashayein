$(function() {

	"use strict";

	$(".auto-button").button();
	
    $("#tableGrid").jqGrid({
    	url: contextRoot + '/EmployeeRegistration/getEmployees.abhi',
    	datatype: "json",
        colModel: [
            { name: "employeeCode", label: "EmployeeCode", align: "center", width : 120},
            { name: "fullName", label: "Name", width : 140},
            { name: "gender", label: "Gender", align: "center", width : 80 , formatter: function (cellvalue, options, rowobject) {
                return "<img height='16' width='16' src='"+contextRoot+"/assets/img/"+cellvalue+"_icon.png' title='"+cellvalue+"'>";
            }},
            { name: "mobileNumber", label: "Mobile No.", width : 85},
            { name: "email", label: "EmailId", width : 130},
            { name: "jobTitleName", label: "JobTitle", width : 80},
            { name: "roleName", label: "Role", width : 100},
            { name: "active", label: "Active", align: "center", template: 'booleanCheckbox', width : 80},
            { name: "archive", label: "Archive", align: "center", template: 'booleanCheckbox', width : 80},
            { name: "joiningDate", label: "JoiningDate", sorttype: "date", formatter:'date', formatoptions:{ newformat: "d M Y"}, width : 90},
            { name: "recordCreated", label: "CreatedDate", sorttype: "date", formatter: "date", formatoptions: { srcformat: "Y-m-d H:i:s", newformat: "d M Y h:i:s A" }, width : 125},
        ],
		viewrecords: true,
		width:$(window).width()-103,
		//height:360,
        iconSet: "fontAwesome",
        idPrefix: "g1_",
        scroll: true,
        rownumbers: true,
        sortname: "recordCreated",
        sortorder: "desc",
        loadBeforeSend: function(xhr) {
			$(".notification-holder").hide();
		},
		loadError: function(xhr, st, er) {
			window.trackJs && trackJs.track(xhr);

			$(".notification-holder").show();
			$(".notification-holder").notification({
				type: 'error',
				message: er
			});
		},
		loadComplete: function() {
		    $("tr.jqgrow:odd").addClass('jqGridAltRow');
		}
    });
    
})