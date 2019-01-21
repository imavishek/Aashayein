$(function() {

	"use strict";

	$(".auto-button").button();
	$(".tablesorter").tablesorter({
		theme : 'jui',
		headerTemplate : '{content} {icon}',
		delayInit : true,
		widgets : [ 'uitheme' ],
//		headers : {
//			5 : {
//				sorter : false
//			}
//		},
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
					$.ajax({
						type : "GET",
						url : $(this).data('url'),
						dataType : "html",
						contentType : 'application/json',
						start_time: new Date().getTime(),
						//timeout: 3000, // sets timeout to 3 seconds
						beforeSend : function() {
							// Show loader
							$('.autoreport-spinner').show(); 
							$(".notification-holder").hide();
						},
						success : function(data) {
							window.location = '' + contextRoot + '/EmployeeRole/showRoles.abhi?message='+data+'';
						},
						error : function(xhr) {

							var message = "";

							switch (xhr.status) {
								case 404:
									message = "No Handler Found Exception";
									break;
								case 500:
									message = "Error in Deleting Employee Role";
									break;
								case 0:
									message = "Request timeout";
									break;
								default:
									message = "Error in Deleting Employee Role";
							}
							$(".notification-holder").show();
							$(".notification-holder").notification({
								type : 'error',
								message : message
							});
						},
						complete : function() {
							console.log('This request took '+(new Date().getTime() - this.start_time)+' ms');
							// Hide loader
							$(".autoreport-spinner").hide();
						},
					})
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