$(function() {

	"use strict";

	$(".auto-button").button();
	$(".tablesorter").tablesorter({
		theme : 'jui',
		headerTemplate : '{content} {icon}',
		delayInit : true,
		widgets : [ 'uitheme', 'filter', 'stickyHeaders' ],
		sortList : [ [ 2, 1 ] ],
		headers : {
			5 : {
				sorter : false,
				filter : false
			}
		},
		widgetOptions : {
			stickyHeaders_attachTo : '.wrapper',
			filter_reset: '.resetFilter',
			filter_functions : {
				4 : {
					"YES" : function(e, n, f, i, $r, c, data) {
						return 'YES' == e;
					},
					"NO" : function(e, n, f, i, $r, c, data) {
						return 'NO' == e;
					}
				}
			},
			filter_formatter : {
				2 : function($cell, indx) {
						return $.tablesorter.filterFormatter.uiDateCompare($cell, indx, {
							selected : 3,
							changeMonth : true,
							changeYear : true,
						});
				},
				3 : function($cell, indx) {
					return $.tablesorter.filterFormatter.uiDateCompare($cell, indx, {
						selected : 3,
						changeMonth : true,
						changeYear : true,
					});
				}
			}
		}
	});
	$("#dialogAddJobTitle").dialog({
		autoOpen : false,
		title : 'Add Job Title',
		draggable : true,
		resizable : false,
		height : "auto",
		width : 570,
		modal : true,
		buttons : {
			"Save" : {
				text : 'Save',
				'data-icon' : 'ui-icon-custom-save',

				click : function() {
					if ($('#saveEmployeeTitle').valid()) {
						$('#saveEmployeeTitle').submit();
						$(this).dialog("close");
					}
				},
			},
			"Cancel" : {
				text : 'Cancel',
				'data-icon' : 'ui-icon-custom-cancel',

				click : function() {
					$(this).dialog("close");
				},
			}
		}
	});
	$("#dialogDelete").dialog({
		autoOpen : false,
		title : 'Delete Employee Title',
		draggable : true,
		resizable : false,
		height : "auto",
		width : 500,
		modal : true,
		buttons : {
			"Delete" : {
				text : 'Ok',
				'data-icon' : 'ui-icon-custom-tick',

				click : function() {
					window.location = $(this).data('url')
					$(this).dialog("close");
				},
			},
			"Cancel" : {
				text : 'Cancel',
				'data-icon' : 'ui-icon-custom-cancel',

				click : function() {
					$(this).dialog("close");
				},
			}
		}
	});
	$("#addTitle").button().on("click", function(e) {

		e.preventDefault();

		$("#dialogAddJobTitle").load('' + contextRoot + '/EmployeeTitle/showJobTitleDialog', function( response, status, xhr ) {
			if ( status == "error" ) {
				var errorText = "The page you are looking for might have been removed had its name changed or is temporarily unavailable";
				
				$(".notification-holder").notification({
					type : 'error',
					message : errorText
				});
			} else if (status == "success") {
				$("#dialogAddJobTitle").dialog("open");
			}
		});
	});
	
	$(".editTitle").button().on("click", function(e) {

		e.preventDefault();

		$("#dialogAddJobTitle").load($(this).attr("href"), function( response, status, xhr ) {
			if ( status == "error" ) {

				/*
				* If error occurred then response is an error page (HTML content). Get the errorTitle from
				* the response (HTML content) and use this as notification message
				*/
				$(".notification-holder").notification({
					type : 'error',
					message : $(response).find(".errorTitle").html()
				});
			} else if (status == "success") {
				$("#dialogAddJobTitle").dialog("open");
			}
		});
	});
	
	$(".deleteTitle").button().on("click", function(e) {

		e.preventDefault();

		var url = $(this).attr("href");

		$("#dialogDelete").load('' + contextRoot + '/Dialog/showDeleteDialog?message=title', function( response, status, xhr ) {
			if ( status == "error" ) {
				var errorText = "The page you are looking for might have been removed had its name changed or is temporarily unavailable";
				
				$(".notification-holder").notification({
					type : 'error',
					message : errorText
				});
			} else if (status == "success") {
				$("#dialogDelete").data('url', url).dialog("open");
			}
		});
	});
})