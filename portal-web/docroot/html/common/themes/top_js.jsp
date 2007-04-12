<%
/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<script type="text/javascript">
	var themeDisplay = {
		getCompanyId : function() {
			return "<%= themeDisplay.getCompanyId() %>";
		},
		getDoAsUserIdEncoded : function() {

			// Use Java to encode the encrypted user id because IE doesn't
			// properly encode with encodeURIComponent. It leaves a '+' as a '+'
			// whereas Firefox correctly encodes a '+' to '%2B'.

			return "<%= Http.encodeURL(themeDisplay.getDoAsUserId()) %>";
		},
		getPlid : function() {
			return "<%= themeDisplay.getPlid() %>";
		},
		getGroupId : function() {
			return "<%= themeDisplay.getPortletGroupId() %>";
		},

		<c:if test="<%= layout != null %>">
			getLayoutId : function() {
				return "<%= layout.getLayoutId() %>";
			},
			getOwnerId : function() {
				return "<%= layout.getOwnerId() %>";
			},
			isPrivateLayout : function() {
				return "<%= layout.isPrivateLayout() %>";
			},
			getParentLayoutId : function() {
				return "<%= layout.getParentLayoutId() %>";
			},
		</c:if>

		getLanguageId : function() {
			return "<%= LanguageUtil.getLanguageId(request) %>";
		},
		getPathMain : function() {
			return "<%= themeDisplay.getPathMain() %>";
		},
		getPathThemeImages : function() {
			return "<%= themeDisplay.getPathThemeImages() %>";
		},
		getPathThemeRoot : function() {
			return "<%= themeDisplay.getPathThemeRoot() %>";
		},
		getPlid: function() {
			return "<%= themeDisplay.getPlid() %>";
		},
		getURLHome : function() {
			return "<%= themeDisplay.getURLHome() %>";
		},
		isFreeformLayout : function() {
			return <%= themeDisplay.isFreeformLayout() %>;
		}
	};

	var mainPath = themeDisplay.getPathMain();
</script>

<c:choose>
	<c:when test='<%= ParamUtil.getBoolean(request, "js_fast_load", GetterUtil.getBoolean(PropsUtil.get(PropsUtil.JAVASCRIPT_FAST_LOAD))) %>'>

		<%--
		everything.js includes all of the JavaScript files. It is autogenerated
		with the Ant build-javascript task.
		--%>

		<script src="<%= themeDisplay.getPathJavaScript() %>/everything.js" type="text/javascript"></script>
	</c:when>
	<c:otherwise>

		<%
		String[] javaScriptFiles = PropsUtil.getArray(PropsUtil.JAVASCRIPT_FILES);

		for (int i = 0; i < javaScriptFiles.length; i++) {
		%>

			<script src="<%= themeDisplay.getPathJavaScript() %>/<%= javaScriptFiles[i] %>" type="text/javascript"></script>

		<%
		}
		%>

	</c:otherwise>
</c:choose>

<c:if test="<%= GetterUtil.getBoolean(PropsUtil.get(PropsUtil.JAVASCRIPT_LOG_ENABLED)) %>">
	<script src="<%= themeDisplay.getPathJavaScript() %>/firebug/firebug.js" type="text/javascript"></script>
</c:if>

<script type="text/javascript" src="<%= themeDisplay.getPathMain() %>/portal/javascript_cached?languageId=<%= themeDisplay.getLocale().toString() %>&themeId=<%= themeDisplay.getThemeId() %>&colorSchemeId=<%= themeDisplay.getColorSchemeId() %>"></script>

<script type="text/javascript">
	function showLayoutTemplates() {
		var message = Alerts.fireMessageBox(
			{
				width: 700,
				modal: true,
				title: "<%= UnicodeLanguageUtil.get(pageContext, "layout") %>"
			});

		url = "<%= themeDisplay.getPathMain() %>/layout_configuration/templates?p_l_id=<%= plid %>&doAsUserId=<%= themeDisplay.getDoAsUserId() %>";

		AjaxUtil.update(url, message);
	}

	_$J(document).ready(
		function() {
			Liferay.Util.addInputType();
		}
	);

	Liferay.Portlet.ready(Liferay.Util.addInputType);

	<c:if test="<%= themeDisplay.isShowPageSettingsIcon() %>">
		function showPageSettings() {
			var url = "<%= themeDisplay.getURLPageSettings().toString() %>";

			url = url.replace(/\b=<%= WindowState.MAXIMIZED %>\b/,"=<%= LiferayWindowState.POP_UP %>");

			var message = Alerts.popupIframe(
				url,
				{
					width: 700,
					modal: true,
					title: '<%= UnicodeLanguageUtil.get(pageContext, "page-settings") %>',
					onClose:
						function() {
							window.location.reload(false);
						}
				});
		}
	</c:if>

	<c:if test="<%= !themeDisplay.isStatePopUp() %>">
		<c:if test="<%= MessagingUtil.isJabberEnabled() && themeDisplay.isSignedIn() %>">
			_$J(document).ready(
				function() {
					Messaging.init("<%= request.getRemoteUser() %>");
				}
			);
		</c:if>

		<c:if test="<%= GetterUtil.getBoolean(PropsUtil.get(PropsUtil.REVERSE_AJAX_ENABLED)) && themeDisplay.isSignedIn()%>">
			if (!is_safari) {
				_$J(document).ready(
					function() {
						setTimeout("ReverseAjax.initialize()", 2000);
					}
				);
			}
		</c:if>

		<%
		String scroll = ParamUtil.getString(request, "scroll");
		%>

		<c:if test="<%= Validator.isNotNull(scroll) %>">
			_$J(document).last(
				function() {
					document.getElementById("<%= scroll %>").scrollIntoView();
				}
			);
		</c:if>
	</c:if>

	<%
	List portletIds = layoutTypePortlet.getPortletIds();
	%>

	<c:choose>
		<c:when test="<%= portletIds.size() > 0 && !layoutTypePortlet.hasStateMax() %>">
			Liferay.Portlet.list = {<%
				for (int i = 0; i < portletIds.size(); i++) {
					out.print("\"" + portletIds.get(i) + "\":1");

					if (i < portletIds.size() - 1) {
						out.print(",");
					}
				}
			%>};
		</c:when>
		<c:otherwise>
			_$J(document).ready(
				function() {
						Liferay.Portlet.processLast();
				}
			);
		</c:otherwise>
	</c:choose>

	//TODO: Check to see if user has permissions

	<c:if test="<%= (layout != null) && true %>">
		jQuery(document).ready(
			function() {
				Liferay.Draggables.init();

				new Liferay.Navigation(
					{
						layoutIds: [<%= ListUtil.toString(layouts, "layoutId") %>],
						navBlock: '#navigation'
					}
				);
			}
		);
	</c:if>

	jQuery(document).ready(
		function() {
			Liferay.Dock.init();
		}
	);
</script>