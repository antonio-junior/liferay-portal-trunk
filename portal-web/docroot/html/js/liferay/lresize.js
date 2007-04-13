(function ($) {
	$.fn.lResize = function(options) {
		this.each(function() {
			$.lResize(this, options);
		});
	};
	
	$.fn.lResizeBind = $.fn.lDragBind;

	$.lResize = function(container, options) {
		/* OPTIONS
		 * handle (object): resize handle
		 * onStart (function): execute when start
		 * onMove (function): execute when resizing
		 * onComplete (function): execute when completed
		 */
		$.lResize.create(container, options);
	};
	
	$.lResize.extendNativeFunctionObject({
	
		/* Resize direction */
		HORIZONTAL : "horizontal",
		VERTICAL : "vertical",
	
		/* Resize modes */
		ADD : "add",
		SUBTRACT : "subtract",

		resizeRule: function(container, direction, mode) {
			this.container = container;
			this.direction = direction;
			this.next = null;
			this.origWidth = null;
			this.origHeight = null;
			this.mode = mode;
		},
		
		bind: function(container, type, fn) {
			$.lDrag.bind(container, type, fn);
		},

		create: function(container, options) {
			if (options.handle && options.direction && options.mode) {
				var handle = options.handle;
				
				if (!handle.resizeSettings) {
					handle.resizeSettings = new Object();
				}
				
				var settings = handle.resizeSettings;
			
				if (!handle.dragSettings) {
					$.lDrag.create(handle, {
						onStart: $.lResize.onMouseDown,
						onMove: $.lResize.onResize,
						onComplete: $.lResize.onMouseUp,
						noDrag: !options.drag
					});
				}
				
				var jHandle = $(handle);
				jHandle.lDragBind("start", options.onStart);
				jHandle.lDragBind("move", options.onMove);
				jHandle.lDragBind("complete", options.onComplete);
				
				if (!settings.resizeRules) {
					settings.resizeRules = new Array();
				}
				
				settings.resizeRules.push(new $.lResize.resizeRule(container, options.direction, options.mode));
			}
		},

		onMouseDown : function() {
			var handle = $.lDrag.container;
			var settings = handle.resizeSettings;
			var mouse = mousePos;
			
			settings.mouseStart = new Coordinate(mousePos.x, mousePos.y);
			
			for (var i = 0; i < settings.resizeRules.length; i++) {
				var resizeRule = settings.resizeRules[i];
				var jContainer = $(resizeRule.container);

				resizeRule.origWidth = jContainer.width();
				resizeRule.origHeight = jContainer.height();
			}
		},

		onResize: function() {
			var handle = $.lDrag.container;
			var settings = handle.resizeSettings;
			var mouse = mousePos;
			var mouseDelta = mousePos.minus(settings.mouseStart);
			
			var newLength;
			var lengthCorrection = 0;
			var noChange = false;
			
			for (var i = 0; i < settings.resizeRules.length; i++) {
				var resizeRule = settings.resizeRules[i];
				var jContainer = $(resizeRule.container);

				if (resizeRule.direction == $.lResize.HORIZONTAL) {
					resizeRule.prevLength = jContainer.width();
					
					if (resizeRule.mode == $.lResize.ADD) {
						newLength = resizeRule.origWidth + mouseDelta.x;
					}
					else {
						newLength = resizeRule.origWidth - mouseDelta.x
					}
				}
				else if (resizeRule.direction == $.lResize.VERTICAL) {
					resizeRule.prevLength = jContainer.height();
					
					if (resizeRule.mode == $.lResize.ADD) {
						newLength = resizeRule.origHeight + mouseDelta.y;
					}
					else {
						newLength = resizeRule.origHeight - mouseDelta.y;
					}
				}
				
				resizeRule.newLength = newLength;
				
				if (newLength < 1) {
					lengthCorrection = Math.max(-newLength + 1, lengthCorrection);
					newLength = 1;
				}
				
				if (newLength == resizeRule.prevLength) {
					noChange = true;
				}
			}
			
			for (var i = 0; i < settings.resizeRules.length; i++) {
				var resizeRule = settings.resizeRules[i];
				var jContainer = $(resizeRule.container);

				if (!settings.disableStop && noChange) {
					newLength = resizeRule.prevLength;
				}
				else if (resizeRule.newLength < 1) {
					newLength = resizeRule.newLength + lengthCorrection;
				}
				else {
					newLength = resizeRule.newLength - lengthCorrection;
				}
				
				if (resizeRule.direction == $.lResize.HORIZONTAL) {
						jContainer.width(newLength);
						//alert("h: " + jContainer[0].className);
				}
				else if (resizeRule.direction == $.lResize.VERTICAL) {
						jContainer.height(newLength);
						//alert("v: " + jContainer[0].className);
				}
			}
		},

		onMouseUp: function() {
			var handle = $.lDrag.container;
			var settings = handle.resizeSettings;
			
			settings.mouseEnd = new Coordinate(mousePos.x, mousePos.y);
		}
	});
})(jQuery);