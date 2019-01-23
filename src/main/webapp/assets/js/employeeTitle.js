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
	$("#dialog").dialog({
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
	$("#addTitle").button().on("click", function(e) {

		e.preventDefault();

		$("#dialog").load('' + contextRoot + '/Dialog/showJobTitleDialog', function() {
			$("#dialog").dialog("open");
		});
	});
})