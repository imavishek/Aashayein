<%@taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>

<springForm:input type="file" path='${param.fieldName}' accept='${param.accept}' cssClass="inputfile"/>
<label for='${param.fieldName}' style="overflow : hidden;">
	<span class="ui-icon ui-icon-custom-${param.icon}"></span>
	<span id="fileName"style="padding-left:5px;">${param.placeHolder}...</span>
</label>

<script type="text/javascript">
'use strict';

;( function ( document, window, index )
{
	var inputs = document.querySelectorAll( '.inputfile' );
	Array.prototype.forEach.call( inputs, function( input )
	{
		var fileNameField	 = $('#fileName'),
			fileNameFieldVal = $('#fileName').html();

		input.addEventListener( 'change', function( e )
		{
			var fileName = '';
			if( this.files && this.files.length > 1 )
				fileName = ( this.getAttribute( 'data-multiple-caption' ) || '' ).replace( '{count}', this.files.length );
			else
				fileName = e.target.value.split( '\\' ).pop();

			if( fileName )
				$('#fileName').html(fileName);
			else
				$('#fileName').html(fileNameFieldVal);
		});

		// Firefox bug fix
		input.addEventListener( 'focus', function(){ input.classList.add( 'has-focus' ); });
		input.addEventListener( 'blur', function(){ input.classList.remove( 'has-focus' ); });
	});
}( document, window, 0 ));
</script>