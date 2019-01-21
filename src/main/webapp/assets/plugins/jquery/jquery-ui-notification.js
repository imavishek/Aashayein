/*
 * Show an in-page notification (info or error)
 *
 */
(function($) {
	"use strict";


	var iconClasses = "",
		stateClasses = "",
		types = {
			'info'    : {icon:'ui-icon-info',   stateClass:'ui-state-highlight', note:'Note:'},
			'warning' : {icon:'ui-icon-notice', stateClass:'ui-state-warning',   note:'Warning:'},
			'error'   : {icon:'ui-icon-alert',  stateClass:'ui-state-error',     note:'Error:'}
			}
	for(var i in types) {
		iconClasses  += " " + types[i].icon;
		stateClasses += " " + types[i].stateClass;
	}


	$.widget( "ui.notification", {
		version: "0.3",
		options: {
			classes: {
				"ui-notification": "ui-corner-all ui-widget"
			},
			type: '',
			message: '',
			/*  Destroy the notification after the specified number of miliseconds.
			 * If the value is zero or negative the notification is not destroyed
			 */
			deleteAfter: 0
		},

		_create: function() {
			var self = this;

			self.noteMessage         = $('<strong>' + types[self.options.type].note + '</strong>');
			self.notificationMessage = $('<span>' + (typeof self.options.message === "string" ? self.options.message : self.options.message.toString()) + '</span>');

			self._addClass("ui-notification", types[self.options.type].stateClass);
			self.element
				.append('<span class="ui-icon ' + types[self.options.type].icon + '"/>')
				.append( self.noteMessage )
				.append( self.notificationMessage )
				.slideDown();

			self._setDelayedDelete();
		},

		_destroy: function() {
			this.element.removeClass(stateClasses);
			this.element.empty();
		},

		_setDelayedDelete: function(){
			var self = this;

			if( self.options.deleteAfter > 0 ) {
				setTimeout(function(){
					self.element.notification("destroy");
				}, self.options.deleteAfter);
			}
		},

		_setOption: function(key, value){
			var self = this;

			self._super(key, value);

			switch (key) {
				case "message":
					self.notificationMessage.html( (typeof value === "string" ? value : value.toString()) );
					break;

				case "deleteAfter":
					self._setDelayedDelete();
					break;

				case "type":
					self.element.removeClass(stateClasses).addClass( types[value].stateClass );
					self.element.find("> span.ui-icon").removeClass(iconClasses).addClass( types[value].icon );
					self.element.find("> strong").html( types[value].note );
					break;
			}
		}
	});

}(jQuery));