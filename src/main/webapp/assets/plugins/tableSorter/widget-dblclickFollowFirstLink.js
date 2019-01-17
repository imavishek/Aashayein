/*! Widget: dblclickFollowFirstLink
 *
 * On DoubleClick open in a new window the first A of the row
 *
 * Requires tablesorter v2.8+ and jQuery 1.7+
 */
;( function( $ ) {
	'use strict';


	var ts = $.tablesorter;


	ts.dblclickFollowFirstLink = {
		init : function( c ) {
			c.$table.on("dblclick", "> tbody > tr > td", function() {
				var url = $(this).closest("tr").find("a:first").attr("href");
				window.open(url);
			});
		}
	};


	ts.addWidget({
		id: 'dblclickFollowFirstLink',
		options: {
		},
		init : function( table, thisWidget, c, wo ) {
			ts.dblclickFollowFirstLink.init( c, wo );
		},
		remove : function( table, c ) {
			c.$table.off( 'dblclick' );
		}
	});

})( jQuery );
