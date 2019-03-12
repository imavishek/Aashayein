$(function() {

	"use strict";

	var csrfToken = $("meta[name='_csrf']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");

	$("#activeResetForm").validate({
		rules : {
			username : {
				required : true,
				pattern : /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/
			}
		},
		messages : {
			username : {
				required : "Please enter UserName",
				pattern : "Please enter valid UserName"
			}
		},
		errorPlacement : function(error, element) {
			error.addClass("ui red pointing label transition error_row");
			element.parent().parent().next().html(error);
		},
		highlight : function(element, errorClass, validClass) {
			$(element).addClass('error_box');
			$(element).parent().parent().prev().addClass('error_row');
		},
		unhighlight : function(element, errorClass, validClass) {
			$(element).removeClass('error_box');
			$(element).parent().parent().prev().removeClass('error_row');
		},
		submitHandler: function(form) {
			var url = $('#activeResetForm').attr('action');
			$.ajax({
				type : "POST",
				url : url,
				data: $('#activeResetForm').serialize(),
				beforeSend : function(jqXHR) {
					jqXHR.setRequestHeader(csrfHeader, csrfToken);
					// Show loader
					$('.customSpinner').show(); 
					$(".notification-holder").hide();
				},
				success : function(data) {
					if(data != ""){
						$('#username').val('');
						$(".notification-holder").show();
						$(".notification-holder").notification({
							type : 'info',
							message : data
						});
					}
				},
				error : function(response, status, xhr) {
					console.log(response);
					console.log(status);
					console.log(xhr);
					$(".notification-holder").show();
					$(".notification-holder").notification({
						type : 'error',
						message : $(response.responseText).find(".errorDetails").html()
					});
				},
				complete : function() {
					// Hide loader
					$(".customSpinner").hide();
				},
			});
		}
	});
});