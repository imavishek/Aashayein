$(document).ready(function(){
	$( "#birthday" ).datepicker({
		dateFormat: "dd/mm/yy",
    	maxDate: "0D",
    	changeMonth: true,
        changeYear: true,
        //yearRange: '1900:' + new Date().getFullYear().toString()
        yearRange: "-100:+0" //last hundred years
    });
});
