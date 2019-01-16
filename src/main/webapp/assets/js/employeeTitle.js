$(function() {
	$(".auto-button").button();
	$(".tablesorter").tablesorter({
		theme : 'jui',
		headerTemplate : '{content} {icon}',
		widgets : ['uitheme', 'zebra']
		//widgets : ['uitheme', 'filter', 'zebra'],
		//sortList: [[1,0]],
		//headers: { 0: { sorter: false} }
	});
	var validator = $("#addEmployeeTitle").validate({
		rules : {
			titleName : {
				normalizer : function(value) {
					$(this).val($.trim($(this).val()));
					return $.trim(value);
				},
				required : true,
				lettersonly : true,
				minlength : 3,
				maxlength : 25
			},
		},
		messages : {
			titleName : {
				required : "Please enter TitleName",
				lettersonly : "Please enter OnlyLetters",
				minlength : "RoleName must consist of at least 3 characters",
				maxlength : "RoleName must consist of at most 25 characters"
			}
		},
		errorPlacement : function(error, element) {
			error.addClass("ui red pointing label transition error_message");
			$('.errorContainer').html(error);
		},
		highlight : function(element, errorClass, validClass) {
			$(element).addClass('error_box');
			$(element).parent().parent().addClass('error_message');
		},
		unhighlight : function(element, errorClass, validClass) {
			$(element).removeClass('error_box');
			$(element).parent().parent().removeClass('error_message');
		}
	});
	$("#addTitle" ).button().on( "click", function(e) {
		e.preventDefault();
		dialog.dialog( "open" );
	});
	dialog = $("#dialog-form" ).dialog({
		autoOpen: false,
		height: 200,
		width: 570,
		modal: true,
		buttons: {
			"Add": addTitle,
			Cancel: function() {
				dialog.dialog( "close" );
			}
		},
		close: function() {
			form[ 0 ].reset();
			validator.resetForm();
			$(".error_box").removeClass("error_box");
			$("#addEmployeeTitle tr").removeClass("error_message");
		}
	});
	form = dialog.find( "form" ).on( "submit", function( event ) {
		event.preventDefault();
		addTitle();
	});
	function addTitle() {
		if ($('#addEmployeeTitle').valid()) {
//			console.log(typeof form.serializeArray());
//			console.log(form.serializeArray());
//			console.log('------------------------');
//			console.log(form.serialize());
//			console.log('------------------------');
//			console.log(JSON.stringify(form.serializeArray()));
//			console.log('------------------------');
//			console.log(JSON.stringify(form.serialize()));
//			console.log('------------------------');
//			window.location = 'addEmployeeTitle.abhi?'+form.serialize();
			var titleName = $('#titleName').val();
			$.ajax({
				type: form.attr('method'),
				url : form.attr('action'),
				data : form.serialize(),  //Data to be sent to the server. It is converted to a query string, if not already a string. 
				dataType: "json",  //The type of data that you're expecting back from the server.
				contentType: 'application/json',  //type of data sending to the server,
				mimeType: 'application/json',
				async : true, //Default true

				success : function(res) {

					if(res.validated){
						console.log(res);
						//Set response
						$('#resultContainer pre code').text(JSON.stringify(res.employee));
						$('#resultContainer').show();
					}else{
						console.log(res);
						//Set error messages
						$.each(res.errorMessages,function(key,value){
							$('input[name='+key+']').after('<span class="error">'+value+'</span>');
						});
					}
				}
			})
			dialog.dialog( "close" );
		}
	}
})