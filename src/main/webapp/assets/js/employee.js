$(function() {

	"use strict";

	$(".auto-button").button();
	
    $("#tableGrid").jqGrid({
    	url: contextRoot + '/EmployeeRegistration/getEmployees.abhi',
    	datatype: "json",
        colModel: [
            { name: "employeeCode", label: "EmployeeCode", width: 115, align: "center"},
            { name: "fullName", label: "Name", width: 200},
            { name: "gender", label: "Gender", width: 70},
            { name: "mobileNumber", label: "Mobile No.", width: 100},
            { name: "email", label: "EmailId", width: 130},
            { name: "jobTitleName", label: "JobTitle", width: 90},
            { name: "roleName", label: "Role", width: 90},
            { name: "active", label: "Active", width: 70},
            { name: "archive", label: "Archive", width: 70},
            { name: "joiningDate", label: "JoiningDate", width: 100},
            { name: "recordCreated", label: "CreatedDate", width: 105},
            { name: "recordUpdated", label: "UpdatedDate", width: 105}
        ],
		viewrecords: true,
		autowidth:true,
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