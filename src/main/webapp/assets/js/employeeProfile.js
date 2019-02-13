$(function() {

	"use strict";

	$(".auto-button").button();
	$("input[type=radio]").checkboxradio();
	$("#country").pqSelect({
		singlePlaceholder : "Select Country",
		radio : true,
		deselect : false,
		width : 265
	}).on("change", function(){getCorrespondingStates(this.value); $(this).valid();});
	$("#state").pqSelect({
		singlePlaceholder : "Select State",
		radio : true,
		deselect : false,
		width : 265
	}).on("change", function(){getCorrespondingCities(this.value); $(this).valid();});
	$("#city").pqSelect({
		singlePlaceholder : "Select City",
		radio : true,
		deselect : false,
		width : 265
	});
	
	getCorrespondingStates($("#country").val());
	
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
	
	// If binding error occurs in controller then show the editInfo class or toggle class
	if(hasBindErrors){
		$(".displayInfo, .editInfo").toggle();
		$(".notification-holder").hide();
	}
	
	$("#editProfile").on("click", function(event){
		
		$(".displayInfo, .editInfo").toggle();
		$(".notification-holder").hide();
		$(".errorRow").addClass("error_row");
		
		event.preventDefault();
	});
	$(".cancelEdit").on("click", function(event){
		
		$(".displayInfo, .editInfo").toggle();
		$(".notification-holder").hide();
		$(".errorRow").removeClass("error_row");
		
		event.preventDefault();
	});
	
	$("#editEmployeeProfile").validate({			
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
			mobileNumber : {
				required : true,
				digits : true,
				pattern : /^[6789]\d{9}$/
			},
			alternateMobileNumber : {
				digits : true,
				pattern : /^[789]\d{9}$/
			},
			alternateEmail : {
				pattern : /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/
			},
			country : {
				required : true
			},
			state : {
				required : true
			},
			city : {
				required : true
			},
			pinCode : {
				required : true,
				digits : true,
				pattern : /^[123456789]\d{5}$/
			},
			addressLine1 : {
				normalizer : function(value) {
					$(this).val($(this).val().replace(/\s\s+/g, ' '));
					return value;
				},
				required : true,
				minlength : 20,
				maxlength : 150
			},
			addressLine2 : {
				normalizer : function(value) {
					$(this).val($(this).val().replace(/\s\s+/g, ' '));
					return value;
				},
				maxlength : 150
			},
			photo : {
				accept: "image/jpg,image/jpeg",
				extension : "jpg|jpeg",
				maxsize : 1048576
			}
		},
		messages : {
			firstName : {
				required : "Please enter FirstName",
				lettersonly : "Please enter OnlyLetters (No WhiteSpace)",
				minlength : "FirstName must consist of at least 3 characters",
				maxlength : "FirstName must consist of at most 25 characters"
			},
			middleName : {
				lettersonly : "Please enter OnlyLetters (No WhiteSpace)",
				minlength : "MiddleName must consist of at least 2 characters",
				maxlength : "MiddleName must consist of at most 20 characters"
			},
			lastName : {
				lettersonly : "Please enter OnlyLetters (No WhiteSpace)",
				required : "Please enter LastName",
				minlength : "LastName must consist of at least 2 characters",
				maxlength : "LastName must consist of at most 15 characters"
			},
			gender : {
				required : "Please select Gender",
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
			alternateEmail : {
				pattern : "Please enter valid AlternateEmail"
			},
			country : {
				required : "Please select Country",
			},
			state : {
				required : "Please select State",
			},
			city : {
				required : "Please select City",
			},
			pinCode : {
				required : "Please enter Pincode",
				digits : "Please enter only digits",
				pattern : "Please enter valid Pincode (6 Digits)",
			},
			addressLine1 : {
				required : "Please enter AddressLine1",
				minlength : "Address must consist of at least 20 characters",
				maxlength : "Address must consist of at most 150 characters"
			},
			addressLine2 : {
				maxlength : "Address must consist of at most 150 characters"
			},
			photo : {
				accept : "Please upload jpg or jpeg image file",
				extension : "Please upload jpg or jpeg image file",
				maxsize : "Image size must be within 1Mb"
			}
		},
		ignore : ':hidden:not("#country, #state, #city")',
		errorPlacement : function(error, element) {
			error.addClass("ui red pointing label transition error_row");
			element.parent().next().html(error);
		},
		highlight : function(element, errorClass, validClass) {
			if($(element).attr("name") == 'country'
				|| $(element).attr("name") == 'state'
				|| $(element).attr("name") == 'city'
				|| $(element).attr("name") == 'photo'){
				$(element).next().addClass('error_box');
			} else{
				$(element).addClass('error_box');
			}
			if($(element).attr("name") == 'photo'){
				$(element).parent().addClass('error_row errorRow');
			} else{
				$(element).parent().parent().addClass('error_row errorRow');
			}
			
		},
		unhighlight : function(element, errorClass, validClass) {
			if($(element).attr("name") == 'country'
				|| $(element).attr("name") == 'state'
				|| $(element).attr("name") == 'city'
				|| $(element).attr("name") == 'photo'){
				$(element).next().removeClass('error_box');
			} else{
				$(element).removeClass('error_box');
			}
			
			if($(element).attr("name") == 'photo'){
				$(element).parent().removeClass('error_row errorRow');
			} else{
				$(element).parent().parent().removeClass('error_row errorRow');
			}
		}
	});
	
	function getCorrespondingStates(countryId){
		$.ajax({
			type : "GET",
			url : '' + contextRoot + '/Address/getStates.abhi',
			data: { countryId: countryId },
			dataType : "json",
			contentType: "application/json; charset=utf-8",
			beforeSend : function() {
				// Show loader
				$('.customSpinner').show(); 
				$(".notification-holder").hide();
			},
			success : function(data) {
				var states = "";
				var statePqSelect = $("#state").pqSelect("getInstance").select;
				if(data){
					$.each(data, function(key, value) {
						if(value.stateId == state){
							states += "<option value='"+value.stateId+"' selected>"+value.stateName+"</option>";
						} else{
							states += "<option value='"+value.stateId+"' >"+value.stateName+"</option>";
						}
					});
				}
				if(states == ""){
					statePqSelect.disable();
				} else{
					statePqSelect.enable();
					$("#state").html(states);
					statePqSelect.refreshData();
				}
				
				getCorrespondingCities($("#state").val())
			},
			error : function(response, status, xhr) {
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
	
	function getCorrespondingCities(stateId){
		$.ajax({
			type : "GET",
			url : '' + contextRoot + '/Address/getCities.abhi',
			data: { stateId: stateId },
			dataType : "json",
			contentType: "application/json; charset=utf-8",
			beforeSend : function() {
				// Show loader
				$('.customSpinner').show(); 
				$(".notification-holder").hide();
			},
			success : function(data) {
				var cities = "";
				var cityPqSelect = $("#city").pqSelect("getInstance").select;
				
				if(data){
					$.each(data, function(key, value) {
						if(value.cityId == city){
							cities += "<option value='"+value.cityId+"' selected>"+value.cityName+"</option>";
						} else{
							cities += "<option value='"+value.cityId+"' >"+value.cityName+"</option>";
						}
					});
				}
				if(cities == ""){
					cityPqSelect.disable();
				} else{
					cityPqSelect.enable();
					$("#city").html(cities);
					cityPqSelect.refreshData();
				}
			},
			error : function(response, status, xhr) {
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
})