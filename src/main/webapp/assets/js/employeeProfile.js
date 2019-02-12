$(function() {

	"use strict";

	$(".auto-button").button();
	$("input[type=radio]").checkboxradio();
	$("#country").pqSelect({
		singlePlaceholder : "Select Country",
		radio : true,
		deselect : false,
		width : 265
	}).on("change", function(){getCorrespondingStates(this.value);});
	$("#state").pqSelect({
		singlePlaceholder : "Select State",
		radio : true,
		deselect : false,
		width : 265
	}).on("change", function(){getCorrespondingCities(this.value);});
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
		
		event.preventDefault();
	});
	$(".cancelEdit").on("click", function(event){
		
		$(".displayInfo, .editInfo").toggle();
		$(".notification-holder").hide();
		
		event.preventDefault();
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