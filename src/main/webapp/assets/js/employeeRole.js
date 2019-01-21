$(function() {

	"use strict";

	$(".auto-button").button();
	$(".tablesorter").tablesorter({
		theme : 'jui',
		headerTemplate : '{content} {icon}',
		delayInit : true,
		widgets : [ 'uitheme' ],
		sortList : [ [ 2, 1 ] ],
	});
	$("#dialog").dialog({
		autoOpen : false,
		title : 'Delete Employee Role',
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
				'data-icon' : 'ui-icon-custom-delete',

				click : function() {
					$(this).dialog("close");
				},
			}
		}
	});
	$(".deleteRole").button().on("click", function(e) {

		e.preventDefault();

		var url = $(this).attr("href");

		$("#dialog").load('' + contextRoot + '/Common/showDeleteDialog?message=role', function() {
			$("#dialog").data('url', url).dialog("open");
		});
	});
})