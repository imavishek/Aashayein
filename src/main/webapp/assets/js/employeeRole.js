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
		}
	// widgets : ['uitheme', 'filter', 'zebra'],
	// sortList: [[1,0]],
	});
})