$(function() {

	"use strict";

	$(".auto-button").button();
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
	var validator = $("#editEmployee").validate({			
		rules : {
			title : {
				required : true
			},
			role : {
				required : true
			},
			joiningDate : {
				required : true,
				dateFormat : true
			}
		},
		messages : {
			title : {
				required : "Please select Title",
			},
			role : {
				required : "Please select Role",
			},
			joiningDate : {
				required : "Please enter Joining Date",
				dateFormat : "Please enter valid Joining Date"
			},
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
		
		$("#title option, #role option").attr("selected", false);
		$("#title, #role").val([]);
		$("#title, #role").pqSelect('refreshData');
		
		$("#joiningDate").attr('value', '');
		$("#joiningDate_display").html('&nbsp;');
		$(".error_message").html('');
		
		validator.resetForm();
	});
})