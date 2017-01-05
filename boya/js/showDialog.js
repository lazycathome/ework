// jQuery showDialogs Plugin
//
// Version 1.0
//
// 23 March 2010
// Usage:
//		jAlert( message, [title, callback] )
//		jConfirm( message, [title, callback] )
//		jPrompt( message, [value, title, callback] )
// 
// License:
// 
// This plugin is dual-licensed under the GNU General Public License and the MIT License and
// is copyright 2008 A Beautiful Site, LLC. 
//

(function($) {
	
	$.showDialog = {

		verticalOffset: -75,                // 从屏幕中心向上的偏移量
		horizontalOffset: 0,                // 从屏幕中心向左的偏移量
		repositionOnResize: true,           // 是否自动适应窗口大小
		overlayOpacity: .01,                // 透明度
		overlayColor: '#FFF',               // 基本色
		draggable: true,                    // 是否允许拖放
		okButton: '&nbsp;OK&nbsp;',         // ok按钮文本
		cancelButton: '&nbsp;Cancel&nbsp;', // cancel按钮文本
		dialogClass: null,                  // 对话框的classname
		
		alert: function(message, title, callback) {
			if( title == null ) title = 'Alert';
			$.showDialog._show(title, message, null, 'alert', function(result) {
				if( callback ) callback(result);
			});
		},
		
		confirm: function(message, title, callback) {
			if( title == null ) title = 'Confirm';
			$.showDialog._show(title, message, null, 'confirm', function(result) {
				if( callback ) callback(result);
			});
		},
			
		prompt: function(message, value, title, callback) {
			if( title == null ) title = 'Prompt';
			$.showDialog._show(title, message, value, 'prompt', function(result) {
				if( callback ) callback(result);
			});
		},
		
		// Private methods
		_show: function(title, msg, value, type, callback) {
			
			$.showDialog._hide();
			$.showDialog._overlay('show');
			
			$("BODY").append(
			  '<div id="popup_container">' +
			    '<h1 id="popup_title"></h1>' +
			    '<div id="popup_content">' +
			      '<div id="popup_message"></div>' +
				'</div>' +
			  '</div>');
			
			if( $.showDialog.dialogClass ) $("#popup_container").addClass($.showDialog.dialogClass);
			
			var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'fixed'; 
			
			$("#popup_container").css({
				position: pos,
				zIndex: 99999,
				padding: 0,
				margin: 0
			});
			
			$("#popup_title").text(title);
			$("#popup_content").addClass(type);
			$("#popup_message").text(msg);
			$("#popup_message").html( $("#popup_message").text().replace(/\n/g, '<br />') );
			
			$("#popup_container").css({
				minWidth: $("#popup_container").outerWidth(),
				maxWidth: $("#popup_container").outerWidth()
			});
			
			$.showDialog._reposition();
			$.showDialog._maintainPosition(true);
			
			switch( type ) {
				case 'alert':
					$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.showDialog.okButton + '" id="popup_ok" /></div>');
					$("#popup_ok").click( function() {
						$.showDialog._hide();
						callback(true);
					});
					$("#popup_ok").focus().keypress( function(e) {
						if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
					});
				break;
				case 'confirm':
					$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.showDialog.okButton + '" id="popup_ok" /> <input type="button" value="' + $.showDialog.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_ok").click( function() {
						$.showDialog._hide();
						if( callback ) callback(true);
					});
					$("#popup_cancel").click( function() {
						$.showDialog._hide();
						if( callback ) callback(false);
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
				break;
				case 'prompt':
					$("#popup_message").append('<br /><input type="text" size="30" id="popup_prompt" />').after('<div id="popup_panel"><input type="button" value="' + $.showDialog.okButton + '" id="popup_ok" /> <input type="button" value="' + $.showDialog.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_prompt").width( $("#popup_message").width() );
					$("#popup_ok").click( function() {
						var val = $("#popup_prompt").val();
						$.showDialog._hide();
						if( callback ) callback( val );
					});
					$("#popup_cancel").click( function() {
						$.showDialog._hide();
						if( callback ) callback( null );
					});
					$("#popup_prompt, #popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
					if( value ) $("#popup_prompt").val(value);
					$("#popup_prompt").focus().select();
				break;
			}
			
			if( $.showDialog.draggable ) {
				try {
					$("#popup_container").jqDrag("#popup_title");
					$("#popup_title").css({ cursor: 'move' });
				} catch(e) {}
			}
		},
		
		_hide: function() {
			$("#popup_container").remove();
			$.showDialog._overlay('hide');
			$.showDialog._maintainPosition(false);
		},
		
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.showDialog._overlay('hide');
					$("BODY").append('<div id="popup_overlay"></div>');
					$("#popup_overlay").css({
						position: 'absolute',
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						background: $.showDialog.overlayColor,
						opacity: $.showDialog.overlayOpacity
					});
				break;
				case 'hide':
					$("#popup_overlay").remove();
				break;
			}
		},
		
		_reposition: function() {
			var top = (($(window).height() / 2) - ($("#popup_container").outerHeight() / 2)) + $.showDialog.verticalOffset;
			var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.showDialog.horizontalOffset;
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			
			if( $.browser.msie && parseInt($.browser.version) <= 6 ) top = top + $(window).scrollTop();
			
			$("#popup_container").css({
				top: top + 'px',
				left: left + 'px'
			});
			$("#popup_overlay").height( $(document).height() );
		},
		
		_maintainPosition: function(status) {
			if( $.showDialog.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', $.showDialog._reposition);
					break;
					case false:
						$(window).unbind('resize', $.showDialog._reposition);
					break;
				}
			}
		}
		
	};
	
	jAlert = function(message, title, callback) {
		$.showDialog.alert(message, title, callback);
	};
	
	jConfirm = function(message, title, callback) {
		$.showDialog.confirm(message, title, callback);
	};
		
	jPrompt = function(message, value, title, callback) {
		$.showDialog.prompt(message, value, title, callback);
	};
	
})(jQuery);