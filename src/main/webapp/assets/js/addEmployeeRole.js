$(function() {
	$(".auto-button").button();
	$("#addEmployeeRole").validate({
		rules : {
			roleName : {
				normalizer : function(value) {
					$(this).val($.trim($(this).val()));
					return $.trim(value);
				},
				required : true,
				lettersonly : true,
				minlength : 3,
				maxlength : 25
			},
			moduleIds : {
				required : true,
				digits : true,
				// Assume module id below 25
				max : 25
			},
		},
		messages : {
			roleName : {
				required : "Please enter RoleName",
				lettersonly : "Please enter OnlyLetters",
				minlength : "RoleName must consist of at least 3 characters",
				maxlength : "RoleName must consist of at most 25 characters"
			},
			moduleIds : {
				required : "Please select Permission",
				digits : "Please select valid Permission",
				max : "Please select valid Permission"
			}
		},
		errorPlacement : function(error, element) {
			error.addClass("ui red pointing label transition error_message");
			$('.' + element.attr("name") + 'Error').html(error);
		},
		highlight : function(element, errorClass, validClass) {
			$(element).addClass('error_box');
		},
		unhighlight : function(element, errorClass, validClass) {
			$(element).removeClass('error_box');
		}
	});
})