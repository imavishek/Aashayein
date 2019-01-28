<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>

<springForm:hidden path='${param["fieldName"]}' value=""/>
<span class="date-picker ui-widget ui-state-default ui-corner-all inputfield">
	<span class="date-picker-display" id='${param["fieldName"]}_display'></span>
	<span class="date-picker-actions">
		<a href="" id='${param["fieldName"]}_trigger'>
			<img src="${contextRoot}/assets/img/calendar.png" alt="" width="16" height="16">
		</a>
		<a href="" id='${param["fieldName"]}_delete'>
			<img src="${contextRoot}/assets/img/delete.png" alt="" width="16" height="16">
		</a>
	</span>
</span>

<script type="text/javascript">
$(function() {
	
	var $dp = jQuery('#${param["fieldName"]}'),
		$dp_display = jQuery('#${param["fieldName"]}_display'),
		$dp_trigger = jQuery('#${param["fieldName"]}_trigger');

	$dp.datepicker({
		minDate : "0D",
		altField: $dp_display,
		altFormat: "dd-mm-yy",
		dateFormat : "dd-mm-yy",
		setDate : new Date(),
		changeMonth : true,
		changeYear : true,
		yearRange : "+0:+10",
		onSelect : function(dateText, inst) {
			var date = $(this).datepicker('getDate'),
				formattedDate = $.datepicker.formatDate(inst.settings.altFormat, date);
			$(inst.settings.altField).html(formattedDate);
			
			$(this).valid();
		}
	});
	$dp_trigger.add($dp_display).click(function(event) {

		var w = $dp.datepicker("widget");

		event.preventDefault();

		if (w.is(":visible")) {
			$dp.datepicker("hide");
		} else {
			$dp.datepicker("show");
			w.position({
				my : "left top",
				at : "left bottom",
				of : $(this)
			});
		}
	});
	jQuery('#${param["fieldName"]}_delete').click(function(event) {

		event.preventDefault();

		$dp.val('');
		$dp_display.html('&nbsp;');

		$dp.valid();
	});
});
</script>