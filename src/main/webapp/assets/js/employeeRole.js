$(function() {
	$(".auto-button").button();
	$(".tablesorter").tablesorter({
		theme : 'jui',
		dateFormat : "dd-MM-yyyy HH:mm:ss",
		headerTemplate : '{content} {icon}',
		widgets : [ 'uitheme', 'zebra', 'resizable' ],
		headers : {
			2 : {
				sorter : "shortDate",
				dateFormat : "dd-MM-yyyy HH:mm:ss"
			},
			3 : {
				sorter : "shortDate",
				dateFormat : "dd-MM-yyyy HH:mm:ss"
			},
			5 : {
				sorter : false
			}
		},
		widgetOptions : {
			resizable : true,
		}
	// widgets : ['uitheme', 'filter', 'zebra'],
	// sortList: [[1,0]],
	});
})