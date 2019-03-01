window.onload = function() {
	setInterval(function() {
		$(".customSpinner").hide();
		$('.innerContent').show();
	}, 500);
};

$(function() {

	"use strict";

	$("#logout").click(function(e){

		e.preventDefault();

		$("#logoutForm").submit();
	});
});