$(function() {

	"use strict";

	$(".auto-button").button();
	$("input[type=radio]").checkboxradio();
	$("#title").pqSelect({
		singlePlaceholder : "Select Title",
		radio : true,
		deselect : false,
		width : 265
	});
	$("#role").pqSelect({
		singlePlaceholder : "Select Role",
		radio : true,
		deselect : false,
		width : 265
	});
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

	// Date Format Validation
	$.validator.addMethod("dateFormat", function(value, element) {
		var check = false;
		var format = /^\d{1,2}\-\d{1,2}\-\d{4}$/;

		if (format.test(value)) {
			var splitDate = value.split('-');
			var dd = parseInt(splitDate[0], 10);
			var mm = parseInt(splitDate[1], 10);
			var yyyy = parseInt(splitDate[2], 10);
			var formatDate = new Date(yyyy, mm - 1, dd);

			if ((formatDate.getFullYear() === yyyy)
					&& (formatDate.getMonth() === mm - 1)
					&& (formatDate.getDate() === dd)) {
				if (new Date().setHours(0, 0, 0, 0) <= formatDate) {
					check = true;
				}
			} else {
				check = false;
			}
		} else {
			check = false;
		}
		return this.optional(element) || check;
	}, "Please enter valid Date   Ex. dd-mm-yyyy");

	// Employee Registration Form Validation
	$('#title, #role').pqSelect({}).on("change", function (e) {
        $(this).valid();
    })
	var validator = $("#employeeRegistration").validate({			
		rules : {
			firstName : {
				normalizer : function(value) {
					$(this).val($.trim($(this).val()));
					return $.trim(value);
				},
				required : true,
				lettersonly : true,
				nowhitespace : true,
				minlength : 3,
				maxlength : 25
			},
			middleName : {
				normalizer : function(value) {
					$(this).val($.trim($(this).val()));
					return $.trim(value);
				},
				lettersonly : true,
				nowhitespace : true,
				minlength : 2,
				maxlength : 20
			},
			lastName : {
				normalizer : function(value) {
					$(this).val($.trim($(this).val()));
					return $.trim(value);
				},
				required : true,
				lettersonly : true,
				nowhitespace : true,
				minlength : 2,
				maxlength : 15
			},
			gender : {
				required : true
			},
			title : {
				required : true
			},
			role : {
				required : true
			},
			mobileNumber : {
				required : true,
				digits : true,
				pattern : /^[6789]\d{9}$/
			},
			alternateMobileNumber : {
				digits : true,
				pattern : /^[789]\d{9}$/
			},
			email : {
				required : true,
				pattern : /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/
			},
			alternateEmail : {
				pattern : /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/
			},
			joiningDate : {
				required : true,
				dateFormat : true
			},
			profilePhoto : {
				accept: "image/jpg,image/jpeg",
				extension : "jpg|jpeg",
				maxsize : 1048576
			}
		},
		messages : {
			firstName : {
				required : "Please enter FirstName",
				lettersonly : "Please enter OnlyLetters",
				minlength : "FirstName must consist of at least 3 characters",
				maxlength : "FirstName must consist of at most 25 characters"
			},
			middleName : {
				lettersonly : "Please enter OnlyLetters",
				minlength : "MiddleName must consist of at least 2 characters",
				maxlength : "MiddleName must consist of at most 20 characters"
			},
			lastName : {
				lettersonly : "Please enter OnlyLetters",
				required : "Please enter LastName",
				minlength : "LastName must consist of at least 2 characters",
				maxlength : "LastName must consist of at most 15 characters"
			},
			gender : {
				required : "Please select Gender",
			},
			title : {
				required : "Please select Title",
			},
			role : {
				required : "Please select Role",
			},
			mobileNumber : {
				required : "Please enter MobileNumber",
				digits : "Please enter only digits",
				pattern : "Please enter valid MobileNumber",
			},
			alternateMobileNumber : {
				digits : "Please enter only digits",
				pattern : "Please enter valid AlternateMobileNumber",
			},
			email : {
				required : "Please enter Email",
				pattern : "Please enter valid Email"
			},
			alternateEmail : {
				pattern : "Please enter valid AlternateEmail"
			},
			joiningDate : {
				required : "Please enter Joining Date",
				dateFormat : "Please enter valid Joining Date"
			},
			profilePhoto : {
				accept : "Please upload jpg or jpeg image file",
				extension : "Please upload jpg or jpeg image file",
				maxsize : "Image size must be within 1Mb"
			}
		},
		ignore : ':hidden:not("#title, #role, #joiningDate")',
		errorPlacement : function(error, element) {
			error.addClass("ui red pointing label transition error_row");
			element.parent().next().html(error);
		},
		highlight : function(element, errorClass, validClass) {
			if($(element).attr("name") == 'title'
				|| $(element).attr("name") == 'role'
				|| $(element).attr("name") == 'joiningDate'
				|| $(element).attr("name") == 'profilePhoto'){
				$(element).next().addClass('error_box');
			} else{
				$(element).addClass('error_box');
			}
			$(element).parent().parent().addClass('error_row');
		},
		unhighlight : function(element, errorClass, validClass) {
			if($(element).attr("name") == 'title'
				|| $(element).attr("name") == 'role'
				|| $(element).attr("name") == 'joiningDate'
				|| $(element).attr("name") == 'profilePhoto'){
				$(element).next().removeClass('error_box');
			} else{
				$(element).removeClass('error_box');
			}
			$(element).parent().parent().removeClass('error_row');
		}
	});

	// Reset RegistrationForm
	$("#reset").click(function() {
		$('input[type="text"]').each(function() {
			$(this).attr('value', '');
		})
		
		$('input[name="gender"]').attr('checked', false);
		
		$("#title option, #role option").attr("selected", false);
		$("#title, #role").val([]);
		$("#title, #role").pqSelect('refreshData');
		
		$("#joiningDate").attr('value', '');
		$("#joiningDate_display").html('&nbsp;');
		$(".error_message").html('');
		
		$('#fileName').html('Choose a picture…');
		$('#profilePhoto').val('');
		
		validator.resetForm();
	});
})