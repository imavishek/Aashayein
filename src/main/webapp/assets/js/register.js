$(document).ready(function(){
	
	//Applying datepicker plugin
	$("#birthday" ).datepicker({
		dateFormat: "dd/mm/yy",
		maxDate: "0D",
		changeMonth: true,
		changeYear: true,
		//yearRange: '1900:' + new Date().getFullYear().toString()
		yearRange: "-100:+0" //last hundred years
	});
	
	//Reset the form
	$("#reset").click(function(){
		["firstName", "lastName", "mobile", "email", "password", "confirmPassword"].forEach(function(item){
			$("#"+item ).val("");
		});
		$( "#birthday" ).datepicker( "setDate", "" );
		$('input[name="gender"]').attr('checked', false);
	});
});
