$(function() {

	"use strict";

	// Password Validation
	$.validator.addMethod("passwordFormat", function(value, element) {
		var format = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%&\*])(?=.{8,25})/g;
		return this.optional(element) || format.test(value);
	}, "Please enter valid password");

	$("#loginForm").validate({
		rules : {
			username : {
				required : true,
				pattern : /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/
			},
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
			}
		},
		messages : {
			username : {
				required : "Please enter UserName",
				pattern : "Please enter valid UserName"
			},
			password : {
				required : "Please enter Password",
				nowhitespace : "Whitespaces are not allowed",
				minlength : "Please Enter Valid Password",
				maxlength : "Please Enter Valid Password",
				passwordFormat : "Please Enter Valid Password"
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