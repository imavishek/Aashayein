$(function() {
	"use strict";

	$(".auto-button").button();
	$(".tablesorter").tablesorter({
		theme : 'jui',
		headerTemplate : '{content} {icon}',
		delayInit : true,
		widgets : [ 'uitheme' ],
		headers : {
			5 : {
				sorter : false
			}
		},
		sortList : [ [ 2, 1 ] ],
	// widgets : ['uitheme', 'filter', 'zebra'],
	// sortList: [[1,0]],
	});

	$(".deleteRole").button().on("click", function(e) {

		e.preventDefault();

		var url = $(this).attr("href");

		$("#dialog-confirm").dialog({
			resizable : false,
			height : "auto",
			width : 500,
			modal : true,
			buttons : {
				"Delete" : {
					click : function() {
						$(this).dialog("close");
					},
					text : 'Ok',
					'data-icon' : 'ui-icon-custom-tick'
				},
				"Cancel" : {
					click : function() {
						$(this).dialog("close");
					},
					text : 'Cancel',
					'data-icon' : 'ui-icon-custom-delete'
				}
			}
		});
	});
})