$(function() {

	"use strict";

	$("#saveEmployeeTitle").validate({
		rules : {
			titleName : {
				normalizer : function(value) {
					$(this).val($.trim($(this).val()));
					return $.trim(value);
				},
				required : true,
				lettersonly : true,
				minlength : 3,
				maxlength : 25
			},
		},
		messages : {
			titleName : {
				required : "Please enter TitleName",
				lettersonly : "Please enter OnlyLetters",
				minlength : "RoleName must consist of at least 3 characters",
				maxlength : "RoleName must consist of at most 25 characters"
			}
		},
		errorPlacement : function(error, element) {
			error.addClass("ui red pointing label transition error_row");
			$('.errorContainer').html(error);
		},
		highlight : function(element, errorClass, validClass) {
			$(element).addClass('error_box');
			$(element).parent().parent().addClass('error_row');
		},
		unhighlight : function(element, errorClass, validClass) {
			$(element).removeClass('error_box');
			$(element).parent().parent().removeClass('error_row');
		}
	});
})