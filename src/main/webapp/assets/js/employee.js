$(function() {

	"use strict";

	$(".auto-button").button();
	$("#tableGrid").jqGrid({
		url : contextRoot + '/Employee/getEmployees.abhi',
		datatype: "json",
		colModel: [
			{ name: "employeeId",		key: true, hidden: true},
			{ name: "employeeCode",		label: "EmployeeCode", align: "center", width: 110, searchoptions: {attr: { placeholder: "EmployeeCode" }}},
			{ name: "fullName",			label: "Name", width : 130, searchoptions: {attr: { placeholder: "EmployeeName" }}},
			{ name: "gender",			label: "Gender", align: "center", width : 80 , formatter: genderIconFormatter, stype:"select", searchoptions: {sopt:['eq'], value: ":All;Male:Male;Female:Female;Other:Other"}, sortable: false},
			{ name: "mobileNumber",		label: "Mobile No.", sorttype: "integer", width : 85, searchoptions: {attr: { placeholder: "Mobile" }}},
			{ name: "email",			label: "EmailId", width : 130, searchoptions: {attr: { placeholder: "Email" }}},
			{ name: "jobTitleName",		label: "JobTitle", width : 80, searchoptions: {attr: { placeholder: "JobTitle" }}},
			{ name: "roleName",			label: "Role", width : 100, searchoptions: {attr: { placeholder: "Role" }}},
			{ name: "active",			label: "Active", align: "center", template: 'booleanCheckbox', firstsortorder: "desc", width : 80, sortable: false},
			{ name: "archive",			label: "Archive", align: "center", template: 'booleanCheckbox', firstsortorder: "desc", width : 80, sortable: false},
			{ name: "joiningDate",		label: "JoiningDate", sorttype: "date", formatter:'date', formatoptions:{srcformat: "u1000", newformat: "d-M-Y"}, searchoptions: {attr: { placeholder: "dd-M-yy" }, sopt: ["eq"], dataInit: dataPickerSearch}, width : 90},
			{ name: "recordCreated",	label: "CreatedDate", sorttype: "date", formatter: "date", formatoptions: { srcformat: "Y-m-d H:i:s", newformat: "d-M-Y h:i:s A" }, search:false, width : 135},
			{ name: "",					label: "Action", align: "center", width: 60, formatter: actionFormatter, search:false, sortable: false},
		],
		loadonce: true,
		rownumbers: true,
		sortable: true,
		autowidth: true,
		shrinkToFit: true,
		pager: true,
		viewrecords: true,
		headertitles: true,
		rowNum: 15,
		rowList: [5, 10, 20, "10000:All"],
		searching: { defaultSearch: "cn" },
		iconSet: "fontAwesome",
		idPrefix: "g1_",
		sortname: "recordCreated",
		sortorder: "desc",
		loadBeforeSend: function(response) {
			$(".notification-holder").hide();
		},
		loadError: function(response, status, xhr) {
			$(".notification-holder").show();
			$(".notification-holder").notification({
				type : 'error',
				message : $(response).find(".errorTitle").html()
			});
		},
		loadComplete: function() {
			$("tr.jqgrow:odd").addClass('jqGridAltRow');
		}
	}).jqGrid("navGrid", {edit: false, add: false, del: false})
		.jqGrid("filterToolbar", {searchOnEnter: false});

	function genderIconFormatter(cellvalue, options, rowObject) {
		var iconName = cellvalue;

		return "<img height='16' width='16' src='"+contextRoot+"/assets/img/"+iconName+"_icon.png' title='"+iconName+"'>";
	}

	function actionFormatter(cellvalue, options, rowObject) {
		var employeeId = rowObject.employeeId;
		var employeeCode = rowObject.employeeCode;
		console.log(employeeId);
		console.log(rowObject.archive);
		if(rowObject.archive == 1){
			return '<a title="UnArchive" style="cursor: pointer;"><img src="'
					+ contextRoot + '/assets/img/tick.png" alt="UnArchive" ></a>';
		}
		



		return '<a href="'
			+ contextRoot
			+ '/Employee/showEditEmployee.abhi?employeeId='
			+ employeeId
			+ '&employeeCode='
			+ employeeCode
			+ '" title="Edit" style="cursor: pointer;"><img src="'
			+ contextRoot
			+ '/assets/img/pencil.png" alt="Edit" ></a>&nbsp;&nbsp;&nbsp;<a title="Delete" style="cursor: pointer;"><img src="'
			+ contextRoot + '/assets/img/delete.png" alt="Delete" ></a>';
	}

	function dataPickerSearch (elem, options) {
		var self = this, $elem = $(elem),
		filterOnSelect = function () {
			setTimeout(function () {
				self.triggerToolbar();
			}, 50);
		},
		triggerInputChangeOnSelect = function () {
			$elem.change();
		};
		setTimeout(function () {
			$elem.datepicker({
				dateFormat: "dd-M-yy",
				autoSize: true,
				changeYear: true,
				changeMonth: true,
				onSelect: (options.mode === "filter" ? filterOnSelect : triggerInputChangeOnSelect)
			});
		}, 50);
	}
})