$(function() {

	"use strict";

	$(document).tooltip({
		hide: {
			effect: "highlight",
			delay: 250
		},
		position : {
			my : "center bottom-20",
			at : "center top",
			using : function(position, feedback) {
				$(this).css(position);
				$("<div>").addClass("arrow").addClass(feedback.vertical).addClass(feedback.horizontal).appendTo(this);
			}
		}
	});

	// Password Validation
	$.validator.addMethod("passwordFormat", function(value, element) {
		var format = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%&\*])(?=.{8,25})/g;
		return this.optional(element) || format.test(value);
	}, "Please enter valid password");
	
	$("#passwordForm").validate({
		rules : {
			password : {
				normalizer : function(value) {
					$(this).val($.trim($(this).val()));
					return $.trim(value);
				},
				required : true,
				nowhitespace : true,
				minlength : 8,
				maxlength : 25,
				passwordFormat : true
			},
			confirmPassword : {
				normalizer : function(value) {
					$(this).val($.trim($(this).val()));
					return $.trim(value);
				},
				required : true,
				nowhitespace : true,
				equalTo: "#password"
			}
		},
		messages : {
			password : {
				required : "Please enter Password",
				nowhitespace : "Whitespaces are not allowed",
				minlength : "Minimum 8 character",
				maxlength : "Maximum 25 character",
				passwordFormat : "Please Enter Valid Password"
			},
			confirmPassword : {
				required : "Please enter Confirm Password",
				nowhitespace : "Whitespaces are not allowed",
				equalTo: "Password mismatch"
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
		}
	});
});