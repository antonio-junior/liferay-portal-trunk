;(function(A, Liferay) {
	var AArray = A.Array;
	var Browser = Liferay.Browser;
	var Lang = A.Lang;

	var isArray = Lang.isArray;
	var arrayIndexOf = AArray.indexOf;

	var Window = {
		ALIGN_CENTER: {
			points: ['tc', 'tc']
		},
		XY: [50, 100],
		XY_INCREMENTOR: 50,
		_map: {}
	};

	var Util = {
		submitCountdown: 0,

		actsAsAspect: function(object) {
			object.yield = null;
			object.rv = {};

			object.before = function(method, f) {
				var original = eval('this.' + method);

				this[method] = function() {
					f.apply(this, arguments);

					return original.apply(this, arguments);
				};
			};

			object.after = function(method, f) {
				var original = eval('this.' + method);

				this[method] = function() {
					this.rv[method] = original.apply(this, arguments);

					return f.apply(this, arguments);
				};
			};

			object.around = function(method, f) {
				var original = eval('this.' + method);

				this[method] = function() {
					this.yield = original;

					return f.apply(this, arguments);
				};
			};
		},

		addInputFocus: function() {
			A.use(
				'aui-base',
				function(A) {
					var handleFocus = function(event) {
						var target = event.target;

						var tagName = target.get('tagName');

						if (tagName) {
							tagName = tagName.toLowerCase();
						}

						var nodeType = target.get('type');

						if (((tagName == 'input') && (/text|password/).test(nodeType)) ||
							(tagName == 'textarea')) {

							var action = 'addClass';

							if (/blur|focusout/.test(event.type)) {
								action = 'removeClass';
							}

							target[action]('focus');
						}
					};

					A.on('focus', handleFocus, document);
					A.on('blur', handleFocus, document);
				}
			);

			Util.addInputFocus = function(){};
		},

		addInputType: function(el) {
			Util.addInputType = Lang.emptyFn;

			if (Browser.isIe() && Browser.getMajorVersion() < 7) {
				Util.addInputType = function(el) {
					var item;

					if (el) {
						el = A.one(el);
					}
					else {
						el = A.one(document.body);
					}

					var defaultType = 'text';

					el.all('input').each(
						function(item, index, collection) {
							var type = item.get('type') || defaultType;

							item.addClass(type);
						}
					);
				};
			}

			return Util.addInputType(el);
		},

		addParams: function(params, url) {
			A.use('querystring-stringify-simple');

			if (Lang.isObject(params)) {
				params = A.QueryString.stringify(params);
			}
			else {
				params = Lang.trim(params);
			}

			if (params) {
				var loc = url || location.href;
				var anchorHash, finalUrl;

				if (loc.indexOf('#') > -1) {
					var locationPieces = loc.split('#');
					loc = locationPieces[0];
					anchorHash = locationPieces[1];
				}

				if (loc.indexOf('?') == -1) {
					params = '?' + params;
				}
				else {
					params = '&' + params;
				}

				if (loc.indexOf(params) == -1) {
					finalUrl = loc + params;

					if (anchorHash) {
						finalUrl += '#' + anchorHash;
					}
					if (!url) {
						location.href = finalUrl;
					}

					return finalUrl;
				}
			}
		},

		checkTab: function(box) {
			if ((document.all) && (event.keyCode == 9)) {
				box.selection = document.selection.createRange();

				setTimeout(
					function() {
						Util.processTab(box.id);
					},
					0
				);
			}
		},

		disableEsc: function() {
			if ((document.all) && (event.keyCode == 27)) {
				event.returnValue = false;
			}
		},

		disableFormButtons: function(inputs, form) {
			inputs.set('disabled', true);
			inputs.setStyle('opacity', 0.5);
		},

		enableFormButtons: function(inputs, form) {
			Util._submitLocked = null;

			document.body.style.cursor = 'auto';

			inputs.set('disabled', false);
			inputs.setStyle('opacity', 1);
		},

		endsWith: function(str, x) {
			return (str.lastIndexOf(x) === (str.length - x.length));
		},

		escapeHTML: function(str) {
			return str.replace(
				/<|>|&/gi,
				function(match) {
					var str = '';

					if (match == '<') {
						str = '&lt;';
					}
					else if (match == '>') {
						str = '&gt;';
					}
					else if (match == '&') {
						str = '&amp;';
					}
					else if (match == '\"') {
						str = '&#034;';
					}
					else if (match == '\'') {
						str = '&#039;';
					}

					return str;
				}
			);
		},

		getColumnId: function(str) {
			var columnId = str.replace(/layout-column_/, '');

			return columnId;
		},

		getHistoryParam: function(portletNamespace) {
			var historyKey = '&' + portletNamespace + 'historyKey=';
			var historyParam = '';

			if (location.hash) {
				historyParam = location.hash.replace('#_LFR_FN_', historyKey);
			}
			else if (location.href.indexOf(historyKey) > -1) {
				var historyParamRE = new RegExp(historyKey + '([^#&]+)');

				historyParam = location.href.match(historyParamRE);

				historyParam = historyParam && historyParam[0];
			}

			return historyParam;
		},

		getOpener: function() {
			var openingWindow = Window._opener;

			if (!openingWindow) {
				var topUtil = Liferay.Util.getTop().Liferay.Util;

				var windowName = Liferay.Util.getWindowName();

				var dialog = topUtil.Window._map[windowName];

				if (dialog) {
					openingWindow = topUtil.Window._map[windowName]._opener;

					Window._opener = openingWindow;
				}
			}

			return openingWindow || window.opener || window.parent;
		},

		getPortletId: function(portletId) {
			portletId = portletId.replace(/^p_p_id_/i, '');
			portletId = portletId.replace(/_$/, '');

			return portletId;
		},

		getTop: function() {
			var win = window;
			var parent = win.parent;
			var top = win;
			var parentThemeDisplay;

			while (parent != win) {
				parentThemeDisplay = parent.themeDisplay;

				if (parentThemeDisplay && !parentThemeDisplay.isStatePopUp()) {
					top = parent;

					break;
				}
			}

			return top;
		},

		getWindow: function(id) {
			if (!id) {
				id = Util.getWindowName();
			}

			return Util.getTop().Liferay.Util.Window._map[id];
		},

		getWindowName: function() {
			return window.name || Window._name || '';
		},

		getURLWithSessionId: function(url) {
			if (document.cookie && (document.cookie.length > 0)) {
				return url;
			}

			// LEP-4787

			var x = url.indexOf(';');

			if (x > -1) {
				return url;
			}

			var sessionId = ';jsessionid=' + themeDisplay.getSessionId();

			x = url.indexOf('?');

			if (x > -1) {
				return url.substring(0, x) + sessionId + url.substring(x);
			}

			// In IE6, http://www.abc.com;jsessionid=XYZ does not work, but
			// http://www.abc.com/;jsessionid=XYZ does work.

			x = url.indexOf('//');

			if (x > -1) {
				var y = url.lastIndexOf('/');

				if (x + 1 == y) {
					return url + '/' + sessionId;
				}
			}

			return url + sessionId;
		},

		isArray: function(object) {
			return !!(window.Array && object.constructor == window.Array);
		},

		openWindow: function(config) {
			config.openingWindow = window;

			var top = Util.getTop();

			var topUtil = top.Liferay.Util;
			var topAUI = top.AUI;

			topUtil._openWindowProvider(config);
		},

		processTab: function(id) {
			document.all[id].selection.text = String.fromCharCode(9);
			document.all[id].focus();
		},

		randomInt: function() {
			return (Math.ceil(Math.random() * (new Date).getTime()));
		},

		randomMinMax: function(min, max) {
			return (Math.round(Math.random() * (max - min))) + min;
		},

		selectAndCopy: function(el) {
			el.focus();
			el.select();

			if (document.all) {
				var textRange = el.createTextRange();

				textRange.execCommand('copy');
			}
		},

		setBox: function(oldBox, newBox) {
			for (var i = oldBox.length - 1; i > -1; i--) {
				oldBox.options[i] = null;
			}

			for (var i = 0; i < newBox.length; i++) {
				oldBox.options[i] = new Option(newBox[i].value, i);
			}

			oldBox.options[0].selected = true;
		},

		showCapsLock: function(event, span) {
			var keyCode = event.keyCode ? event.keyCode : event.which;
			var shiftKey = event.shiftKey ? event.shiftKey : ((keyCode == 16) ? true : false);

			if (((keyCode >= 65 && keyCode <= 90) && !shiftKey) ||
				((keyCode >= 97 && keyCode <= 122) && shiftKey)) {

				document.getElementById(span).style.display = '';
			}
			else {
				document.getElementById(span).style.display = 'none';
			}
		},

		sortByAscending: function(a, b) {
			a = a[1].toLowerCase();
			b = b[1].toLowerCase();

			if (a > b) {
				return 1;
			}

			if (a < b) {
				return -1;
			}

			return 0;
		},

		startsWith: function(str, x) {
			return (str.indexOf(x) === 0);
		},

		textareaTabs: function(event) {
			var el = event.currentTarget.getDOM();
			var pressedKey = event.keyCode;

			if (event.isKey('TAB')) {
				event.halt();

				var oldscroll = el.scrollTop;

				if (el.setSelectionRange) {
					var caretPos = el.selectionStart + 1;
					var elValue = el.value;

					el.value = elValue.substring(0, el.selectionStart) + '\t' + elValue.substring(el.selectionEnd, elValue.length);

					setTimeout(
						function() {
							el.focus();
							el.setSelectionRange(caretPos, caretPos);
						}, 0);

				}
				else {
					document.selection.createRange().text='\t';
				}

		        el.scrollTop = oldscroll;

				return false;
		    }
		},

		toCharCode: A.cached(
			function(name) {
				var buffer = [];

				name = unescape(escape(name).replace(/%u/g, '\\u'));

				for (var i = 0; i < name.length; i++) {
					buffer[i] = name.charCodeAt(i);
				}

				return buffer.join('');
			}
		),

		uncamelize: function(value, separator) {
			separator = separator || ' ';

			value = value.replace(/([a-zA-Z][a-zA-Z])([A-Z])([a-z])/g, '$1' + separator + '$2$3');
			value = value.replace(/([a-z])([A-Z])/g, '$1' + separator + '$2');

			return value;
		},

		unescapeHTML: function(str) {
			return str.replace(
				/&lt;|&gt;|&amp;|&#034;|&#039;/gi,
				function(match) {
					var str = '';

					if (match == '&lt;') {
						str = '<';
					}
					else if (match == '&gt;') {
						str = '>';
					}
					else if (match == '&amp;') {
						str = '&';
					}
					else if (match == '&#034;') {
						str = '\"';
					}
					else if (match == '&#039;') {
						str = '\'';
					}

					return str;
				}
			);
		},

		_defaultSubmitFormFn: function(event) {
			var form = event.form;
			var action = event.action;
			var singleSubmit = event.singleSubmit;

			var inputs = form.all('input[type=button], input[type=reset], input[type=submit]');

			Util.disableFormButtons(inputs, form);

			if (singleSubmit === false) {
				Util._submitLocked = A.later(
					10000,
					Util,
					Util.enableFormButtons,
					[inputs, form]
				);
			}
			else {
				Util._submitLocked = true;
			}

			if (action != null) {
				form.attr('action', action);
			}

			form.submit();
		},

		_getEditableInstance: function(title) {
			var editable = Util._EDITABLE;

			if (!editable) {
				editable = new A.Editable(
					{
						after: {
							contentTextChange: function(event) {
								var instance = this;

								if (!event.initial) {
									var title = instance.get('node');

									var portletTitleEditOptions = title.getData('portletTitleEditOptions');

									Util.savePortletTitle(
										{
											doAsUserId: portletTitleEditOptions.doAsUserId,
											plid: portletTitleEditOptions.plid,
											portletId: portletTitleEditOptions.portletId,
											title: event.newVal
										}
									);
								}
							},
							startEditing: function(event) {
								var instance = this;

								var Layout = Liferay.Layout;

								if (Layout) {
									instance._dragListener = Layout.getLayoutHandler().on(
										'drag:start',
										function(event) {
											instance.fire('save');
										}
									);
								}
							},
							stopEditing: function(event) {
								var instance = this;

								if (instance._dragListener) {
									instance._dragListener.detach();
								}
							}
						},
						cssClass: 'lfr-portlet-title-editable',
						node: title
					}
				);

				Util._EDITABLE = editable;
			}

			return editable;
		}
	};

	Liferay.provide(
		Util,
		'afterIframeLoaded',
		function(event) {
			var iframePlugin = event.currentTarget;

			var iframeBody = iframePlugin.node.get('contentWindow.document.body');

			iframeBody.addClass('aui-dialog-iframe-popup');

			var closeButton = iframeBody.one('.aui-button-input-cancel');

			if (closeButton) {
				var dialog = iframePlugin.get('host');

				closeButton.on('click', dialog.close, dialog);
			}

			var rolesSearchContainer = iframeBody.one('#rolesSearchContainerSearchContainer');

			if (rolesSearchContainer) {
				rolesSearchContainer.delegate(
					'click',
					function(event){
						event.preventDefault();

						submitForm(document.hrefFm, event.currentTarget.attr('href'));
					},
					'a'
				);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'check',
		function(form, name, checked) {
			var checkbox = A.one(form[name]);

			if (checkbox) {
				checkbox.set('checked', checked);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'checkAll',
		function(form, name, allBox) {
			var selector;

			if (isArray(name)) {
				selector = 'input[name='+ name.join('], input[name=') + ']';
			}
			else {
				selector = 'input[name=' + name + ']';
			}

			form = A.one(form);

			form.all(selector).set('checked', A.one(allBox).get('checked'));
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'checkAllBox',
		function(form, name, allBox) {
			var totalBoxes = 0;
			var totalOn = 0;
			var inputs = A.one(form).all('input[type=checkbox]');

			allBox = A.one(allBox);

			if (!isArray(name)) {
				name = [name];
			}

			inputs.each(
				function(item, index, collection) {
					if (!item.compareTo(allBox)) {
						if (arrayIndexOf(name, item.getAttribute('name')) > -1) {
							totalBoxes++;
						}

						if (item.get('checked')) {
							totalOn++;
						}
					}
				}
			);

			allBox.set('checked', (totalBoxes == totalOn));
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'createFlyouts',
		function(options) {
			options = options || {};

			var flyout = A.one(options.container);
			var containers = [];

			if (flyout) {
				var lis = flyout.all('li');

				lis.each(
					function(item, index, collection) {
						var childUL = item.one('ul');

						if (childUL) {
							childUL.hide();

							item.addClass('lfr-flyout');
							item.addClass('has-children lfr-flyout-has-children');
						}
					}
				);

				var hideTask = A.debounce(
					function(event) {
						showTask.cancel();

						var li = event.currentTarget;

						if (li.hasClass('has-children')) {
							var childUL = event.currentTarget.one('> ul');

							if (childUL) {
								childUL.hide();

								if (options.mouseOut) {
									options.mouseOut.apply(event.currentTarget, [event]);
								}
							}
						}
					},
					300
				);

				var showTask = A.debounce(
					function(event) {
						hideTask.cancel();

						var li = event.currentTarget;

						if (li.hasClass('has-children')) {
							var childUL = event.currentTarget.one('> ul');

							if (childUL) {
								childUL.show();

								if (options.mouseOver) {
									options.mouseOver.apply(event.currentTarget, [event]);
								}
							}
						}
					},
					0
				);

				lis.on('mouseenter', showTask, 'li');
				lis.on('mouseleave', hideTask, 'li');
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'disableElements',
		function(obj) {
			var el = A.one(obj);

			if (el) {
				el = el.getDOM();

				var children = el.getElementsByTagName('*');

				var emptyFnFalse = Lang.emptyFnFalse;
				var Event = A.Event;

				for (var i = children.length - 1; i >= 0; i--) {
					var item = children[i];

					item.style.cursor = 'default';

					el.onclick = emptyFnFalse;
					el.onmouseover = emptyFnFalse;
					el.onmouseout = emptyFnFalse;
					el.onmouseenter = emptyFnFalse;
					el.onmouseleave = emptyFnFalse;

					Event.purgeElement(el, false);

					item.href = 'javascript:;';
					item.disabled = true;
					item.action = '';
					item.onsubmit = emptyFnFalse;
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'disableTextareaTabs',
		function(textarea) {
			textarea = A.one(textarea);

			if (textarea && textarea.attr('textareatabs') != 'enabled') {
				textarea.attr('textareatabs', 'disabled');

				textarea.detach('keydown', Util.textareaTabs);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'disableToggleBoxes',
		function(checkBoxId, toggleBoxId, checkDisabled) {
			var checkBox = A.one('#' + checkBoxId);
			var toggleBox = A.one('#' + toggleBoxId);

			if (checkBox && toggleBox) {
				if (checkBox.get('checked') && checkDisabled) {
					toggleBox.set('disabled', true);
				}
				else {
					toggleBox.set('disabled', false);
				}

				checkBox.on(
					'click',
					function() {
						toggleBox.set('disabled', !toggleBox.get('disabled'));
					}
				);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'enableTextareaTabs',
		function(textarea) {
			textarea = A.one(textarea);

			if (textarea && textarea.attr('textareatabs') != 'enabled') {
				textarea.attr('textareatabs', 'disabled');

				textarea.on('keydown', Util.textareaTabs);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'focusFormField',
		function(el, caretPosition) {
			Util.addInputFocus();

			var interacting = false;

			var clickHandle = A.getDoc().on(
				'click',
				function(event) {
					interacting = true;

					clickHandle.detach();
				}
			);

			if (!interacting) {
				el = A.one(el);

				try {
					el.focus();
				}
				catch (e) {
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'forcePost',
		function(link) {
			link = A.one(link);

			if (link) {
				var url = link.attr('href');

				submitForm(document.hrefFm, url);

				Util._submitLocked = null;
			}
		},
		['aui-base']
	);

	/**
	 * OPTIONS
	 *
	 * Required
	 * button {string|object}: The button that opens the popup when clicked.
	 * height {number}: The height to set the popup to.
	 * textarea {string}: the name of the textarea to auto-resize.
	 * url {string}: The url to open that sets the editor.
	 * width {number}: The width to set the popup to.
	 */

	Liferay.provide(
		Util,
		'inlineEditor',
		function(options) {
			if (options.uri && options.button) {
				var button = options.button;
				var height = options.height || 640;
				var textarea = options.textarea;
				var uri = options.uri;
				var width = options.width || 680;

				options.dialog = {
					stack: false
				};

				var editorButton = A.one(button);

				if (editorButton) {
					delete options.button;

					editorButton.on(
						'click',
						function(event) {
							Util.openWindow(options);
						}
					);
				}
			}
		},
		['aui-dialog', 'aui-io']
	);

	Liferay.provide(
		Util,
		'moveItem',
		function(fromBox, toBox, sort) {
			fromBox = A.one(fromBox);
			toBox = A.one(toBox);

			var selectedIndex = fromBox.get('selectedIndex');

			var selectedOption;

			if (selectedIndex >= 0) {
				var options = fromBox.all('option');

				selectedOption = options.item(selectedIndex);

				options.each(
					function(item, index, collection) {
						if (item.get('selected')) {
							toBox.append(item);
						}
					}
				);
			}

			if (selectedOption && selectedOption.text() != '' && sort == true) {
				Util.sortBox(toBox);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'openFormBuilderWindow',
		function(config) {
			var instance = this;

			var formBuilderURL = Liferay.PortletURL.createRenderURL();

			formBuilderURL.setEscapeXML(false);
			formBuilderURL.setParameter('struts_action', '/dynamic_data_mapping/edit_structure');
			formBuilderURL.setParameter('portletResourceNamespace', config.namespace);
			formBuilderURL.setParameter('availableFields', config.availableFields);
			formBuilderURL.setParameter('callback', config.callback);
			formBuilderURL.setPortletId(166);
			formBuilderURL.setWindowState('pop_up');

			config.uri = formBuilderURL.toString();

			var dialogConfig = config.dialog;

			if (!dialogConfig) {
				dialogConfig = {};

				config.dialog = dialogConfig;
			}

			if (!('align' in dialogConfig)) {
				dialogConfig.align = Util.Window.ALIGN_CENTER;
			}

			Util.openWindow(config);
		},
		['liferay-portlet-url']
	);

	Liferay.provide(
		Util,
		'portletTitleEdit',
		function(options) {
			var obj = options.obj;
			var title = obj.one('.portlet-title-text');

			if (title && !title.hasClass('not-editable')) {
				title.setData('portletTitleEditOptions', options);

				title.on(
					'click',
					function(event) {
						var editable = Util._getEditableInstance(title);

						var rendered = editable.get('rendered');

						if (rendered) {
							editable.fire('stopEditing');
						}

						editable.set('node', event.currentTarget);

						if (rendered) {
							editable.syncUI();
						}

						editable._startEditing(event);
					}
				);
			}
		},
		['aui-editable']
	);

	Liferay.provide(
		Util,
		'removeItem',
		function(box, value) {
			box = A.one(box);

			var selectedIndex =  box.get('selectedIndex');

			if (!value) {
				box.all('option').item(selectedIndex).remove(true);
			}
			else {
				box.all('option[value=' + value + ']').item(selectedIndex).remove(true);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'reorder',
		function(box, down) {
			box = A.one(box);

			var selectedIndex = box.get('selectedIndex');

			if (selectedIndex == -1) {
				box.set('selectedIndex', 0);
			}
			else {
				var options = box.get('options');

				var selectedOption = options.item(selectedIndex);
				var lastIndex = box.get('length') - 1;

				var currentOption;
				var newOption;
				var newIndex;

				var text = selectedOption.get('text');
				var value = selectedOption.val();

				if (value && (selectedIndex > 0) && (down == 0)) {
					var previousOption = options.item(selectedIndex - 1);

					selectedOption.set('text', previousOption.get('text'));
					selectedOption.val(previousOption.val());

					newOption = previousOption;
					newIndex = selectedIndex - 1;
				}
				else if ((selectedIndex < lastIndex) && (options.item(selectedIndex + 1).val()) && (down == 1)) {
					var nextOption = options.item(selectedIndex + 1);

					selectedOption.set('text', nextOption.get('text'));
					selectedOption.val(nextOption.val());

					newOption = nextOption;
					newIndex = selectedIndex + 1;
				}
				else if (selectedIndex == 0) {
					var nextIndex;
					var nextOption;

					for (var i = 0; i < lastIndex; i++) {
						nextIndex = i + 1;
						currentOption = options.item(i);
						nextOption = options.item(nextIndex);

						currentOption.set('text', nextOption.get('text'));
						currentOption.val(nextOption.val());
					}

					newOption = options.item(lastIndex);
					newIndex = lastIndex;
				}
				else if (selectedIndex == lastIndex) {
					var previousIndex;
					var previousOption;

					for (var i = lastIndex; i > 0; i--) {
						previousIndex = i - 1;
						currentOption = options.item(i);
						previousOption = options.item(previousIndex);

						currentOption.set('text', previousOption.get('text'));
						currentOption.val(previousOption.val());
					}

					newOption = options.item(0);
					newIndex = 0;
				}

				if (newOption) {
					newOption.set('text', text);
					newOption.val(value);

					box.set('selectedIndex', newIndex);
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'resizeTextarea',
		function(elString, usingRichEditor, resizeToInlinePopup) {
			var el = A.one('#' + elString);

			if (!el) {
				el = A.one('textarea[name=' + elString + ']');
			}

			if (el) {
				var pageBody = A.getBody();

				var resize = function() {
					var pageBodyHeight = pageBody.get('offsetHeight');

					if (usingRichEditor) {
						try {
							if (el.get('nodeName').toLowerCase() != 'iframe') {
								el = window[elString];
							}
						}
						catch (e) {
						}
					}

					var diff = 170;

					if (!resizeToInlinePopup) {
						diff = 100;
					}

					el = A.one(el);

					var styles = {
						height: (pageBodyHeight - diff) + 'px',
						width: '98%'
					};

					if (usingRichEditor) {
						if (!el || !A.DOM.inDoc(el)) {
							A.on(
								'available',
								function(event) {
									el = A.one(window[elString]);

									if (el) {
										el.setStyles(styles);
									}
								},
								'#' + elString + '_cp'
							);

							return;
						}
					}

					if (el) {
						el.setStyles(styles);
					}
				};

				resize();

				A.getWin().on('resize', resize);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'savePortletTitle',
		function(params) {
			A.mix(
				params,
				{
					doAsUserId: 0,
					plid: 0,
					portletId: 0,
					title: '',
					url: themeDisplay.getPathMain() + '/portlet_configuration/update_title'
				}
			);

			A.io.request(
				params.url,
				{
					data: {
						doAsUserId: params.doAsUserId,
						p_l_id: params.plid,
						portletId: params.portletId,
						title: params.title
					}
				}
			);
		},
		['aui-io']
	);

	Liferay.provide(
		Util,
		'setSelectedValue',
		function(col, value) {
			var option = A.one(col).one('option[value=' + value + ']');

			if (option) {
				option.set('selected', true);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'sortBox',
		function(box) {
			var newBox = [];

			var options = box.all('option');

			for (var i = 0; i < options.size(); i++) {
				newBox[i] = [options.item(i).val(), options.item(i).text()];
			}

			newBox.sort(Util.sortByAscending);

			var boxObj = A.one(box);

			boxObj.all('option').remove(true);

			A.each(
				newBox,
				function(item, index, collection) {
					boxObj.append('<option value="' + item[0] + '">' + item[1] + '</option>');
				}
			);

			if (Browser.isIe()) {
				var currentWidth = boxObj.getStyle('width');

				if (currentWidth == 'auto') {
					boxObj.setStyle('width', 'auto');
				}
			}
		},
		['aui-base']
	);

	/**
	 * OPTIONS
	 *
	 * Required
	 * uri {string}: The url to open that sets the editor.
	 */

	Liferay.provide(
		Util,
		'switchEditor',
		function(options) {
			var uri = options.uri;

			var windowName = Liferay.Util.getWindowName();

			var dialog = Liferay.Util.getWindow(windowName);

			if (dialog) {
				dialog.iframe.set('uri', uri);
			}
		},
		['aui-io']
	);

	Liferay.provide(
		Util,
		'toggleBoxes',
		function(checkBoxId, toggleBoxId, displayWhenUnchecked) {
			var checkBox = A.one('#' + checkBoxId);
			var toggleBox = A.one('#' + toggleBoxId);

			if (checkBox && toggleBox) {
				var checked = checkBox.get('checked');

				if (checked) {
					toggleBox.show();
				}
				else {
					toggleBox.hide();
				}

				if (displayWhenUnchecked) {
					toggleBox.toggle();
				}

				checkBox.on(
					'click',
					function() {
						toggleBox.toggle();
					}
				);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'toggleControls',
		function() {
			var trigger = A.one('.toggle-controls');

			if (trigger) {
				var docBody = A.getBody();
				var hiddenClass = 'controls-hidden';
				var visibleClass = 'controls-visible';
				var currentClass = visibleClass;

				if (Liferay._editControlsState != 'visible') {
					currentClass = hiddenClass;
				}

				docBody.addClass(currentClass);

				trigger.on(
					'click',
					function(event) {
						docBody.toggleClass(visibleClass).toggleClass(hiddenClass);

						Liferay._editControlsState = (docBody.hasClass(visibleClass) ? 'visible' : 'hidden');

						A.io.request(
							themeDisplay.getPathMain() + '/portal/session_click',
							{
								data: {
									'liferay_toggle_controls': Liferay._editControlsState
								}
							}
						);
					}
				);
			}
		},
		['aui-io']
	);

	Liferay.provide(
		Util,
		'toggleRadio',
		function(radioId, showBoxId, hideBoxIds) {
			var radioButton = A.one('#' + radioId);
			var showBox = A.one('#' + showBoxId);

			if (radioButton && showBox) {
				var checked = radioButton.get('checked');

				showBox.toggle(checked);

				radioButton.on(
					'change',
					function() {
						showBox.show();

						var hideBox;

						if (isArray(hideBoxIds)) {
							hideBox = A.all('#' + hideBoxIds.join(',#'));
						}
						else {
							hideBox = A.one('#' + hideBoxIds);
						}

						hideBox.hide();
					}
				);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'toggleSelectBox',
		function(selectBoxId, value, toggleBoxId) {
			var selectBox = A.one('#' + selectBoxId);
			var toggleBox = A.one('#' + toggleBoxId);

			if (selectBox && toggleBox) {
				var toggle = function() {
					var action = 'show';

					if (selectBox.val() != value) {
						action = 'hide';
					}

					toggleBox[action]();
				};

				toggle();

				selectBox.on('change', toggle);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'updateCheckboxValue',
		function(checkbox) {
			A.one(checkbox).previous().val(checkbox.checked);
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'submitForm',
		function(form, action, singleSubmit) {
			if (!Util._submitLocked) {
				Liferay.fire(
					'submitForm',
					{
						form: A.one(form),
						action: action,
						singleSubmit: singleSubmit
					}
				);
			}
		},
		['aui-base']
	);

	Liferay.publish(
		'submitForm',
		{
			defaultFn: Util._defaultSubmitFormFn
		}
	);

	Liferay.provide(
		Util,
		'_openWindowProvider',
		function(config) {
			Util._openWindow(config);
		},
		['liferay-util-window']
	);

	Liferay.after(
		'closeWindow',
		function(event) {
			var id = event.id;

			var dialog = Liferay.Util.getTop().Liferay.Util.Window._map[id];

			if (dialog && dialog.iframe) {
				var dialogWindow = dialog.iframe.node.get('contentWindow').getDOM();

				var openingWindow = dialogWindow.Liferay.Util.getOpener();
				var refresh = event.refresh;

				if (refresh && openingWindow) {
					if (!event.portletAjaxable) {
						openingWindow.location.reload();
					}
					else {
						openingWindow.Liferay.Portlet.refresh('#p_p_id_' + refresh + '_');
					}
				}

				dialog.close();
			}
		}
	);

	Util.Window = Window;

	Liferay.Util = Util;

	// 0-200: Theme Developer
	// 200-400: Portlet Developer
	// 400+: Liferay

	Liferay.zIndex = {
		DOCK: 10,
		DOCK_PARENT: 20,
		ALERT: 430,
		DROP_AREA: 440,
		DROP_POSITION: 450,
		DRAG_ITEM: 460,
		TOOLTIP: 470,
		WINDOW: 1000
	};
})(AUI(), Liferay);