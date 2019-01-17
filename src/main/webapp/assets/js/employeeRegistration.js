$(function() {
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
	$("#joiningDate").datepicker({
		dateFormat : "dd/mm/yy",
		minDate : "0D",
		setDate : new Date(),
		changeMonth : true,
		changeYear : true,
		yearRange : "+0:+10"
	});
	if ($("#joiningDate").datepicker('getDate') == null) {
		$("#joiningDate").datepicker("setDate", new Date());
	}
	$(document).tooltip(
			{
				position : {
					my : "center bottom-20",
					at : "center top",
					using : function(position, feedback) {
						$(this).css(position);
						$("<div>").addClass("arrow")
								.addClass(feedback.vertical).addClass(
										feedback.horizontal).appendTo(this);
					}
				}
			});

	/*
	 * $.validator.addMethod("emailField", function(value, element) {
	 * console.log(email_regx.test(value)); return this.optional(element) ||
	 * email_regx.test(value); }); $.validator.addMethod('filesize',
	 * function(value, element, arg) { return this.optional(element) ||
	 * (element.files[0].size <= arg) });
	 */

	// Date Format Validation
	$.validator.addMethod("dateFormat", function(value, element) {
		var check = false;
		var format = /^\d{1,2}\/\d{1,2}\/\d{4}$/;

		if (format.test(value)) {
			var splitDate = value.split('/');
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
	}, "Please enter valid Date   Ex. dd/mm/yyyy");

	// Employee Registration Form Validation
	$("#employeeRegistration")
			.validate(
					{
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
								extension : "jpg|jpeg",
								maxsize : 300000
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
								extension : "Please upload jpg or jpeg image file",
								maxsize : "Image size must be within 300kb"
							}
						},
						ignore : ':hidden:not("#title, #role")',
						errorPlacement : function(error, element) {
							error
									.addClass("ui red pointing label transition error_message");
							element.parent().next().html(error);
						},
						highlight : function(element, errorClass, validClass) {
							$(element).addClass('error_box');
							$(element).parent().parent().addClass(
									'error_message');
						},
						unhighlight : function(element, errorClass, validClass) {
							$(element).removeClass('error_box');
							$(element).parent().parent().removeClass(
									'error_message');
						}
					});

	// Reset Title and Role on click Reset button
	$("#reset").click(function() {
		$('input[type="text"]').each(function() {
			$(this).attr('value', '');
		})
		$('input[name="gender"]').attr('checked', false);
		$("#title, #role").val([]);
		$("#title, #role").pqSelect('refreshData');
	});
	// $("#employeeRegistration").submit(function(e){
	// e.preventDefault();
	// console.log($('#employeeRegistration').serialize());
	//		
	//		
	//		
	// $.ajax({
	// url : '/aashayein/EmployeeRegistration/register.abhi',
	// dataType: "json",
	// type: "POST",
	// contentType: 'application/json',
	// mimeType: 'application/json',
	// enctype: 'multipart/form-data',
	// data : $('#employeeRegistration').serialize(),
	// success : function(res) {
	// if(res.validated){
	// console.log(res);
	// //Set response
	// $('#resultContainer pre code').text(JSON.stringify(res.employee));
	// $('#resultContainer').show();
	//
	// }else{
	// console.log(res);
	// //Set error messages
	// $.each(res.errorMessages,function(key,value){
	// $('input[name='+key+']').after('<span class="error">'+value+'</span>');
	// });
	// }
	// }
	// })
	// });
})