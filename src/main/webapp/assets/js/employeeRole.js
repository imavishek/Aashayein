$(function() {
	$(".auto-button").button();
	$(".tablesorter").tablesorter({
		theme : 'jui',
		headerTemplate : '{content} {icon}',
		widgets : ['uitheme', 'zebra']
		//widgets : ['uitheme', 'filter', 'zebra'],
		//sortList: [[1,0]],
		//headers: { 0: { sorter: false} }
	});
})