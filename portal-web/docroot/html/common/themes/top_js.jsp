<%
/**
 * Copyright (c) 2000-2006 Liferay, LLC. All rights reserved.
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
		getPathThemeRoot : function() {
			return("<%= themeDisplay.getPathThemeRoot() %>");
		},
		getPathThemeImage : function() {
			return("<%= themeDisplay.getPathThemeImage() %>");
		},
		getPathMain : function() {
			return("<%= themeDisplay.getPathMain() %>");
		}
	};

	var mainPath = themeDisplay.getPathMain();
</script>

<c:choose>
	<c:when test="<%= GetterUtil.getBoolean(PropsUtil.get(PropsUtil.JAVASCRIPT_FAST_LOAD)) %>">

		<%--
		everything.js includes all of the JavaScript files. It is autogenerated
		with the ant build-javascript task.
		--%>

		<script src="<%= themeDisplay.getPathJavaScript() %>/everything.js" type="text/javascript"></script>

		<c:if test="<%= GetterUtil.getBoolean(PropsUtil.get(PropsUtil.JAVASCRIPT_LOG_ENABLED)) %>">
			<script src="<%= themeDisplay.getPathJavaScript() %>/log.js" type="text/javascript"></script>
		</c:if>
	</c:when>
	<c:otherwise>
		<script src="<%= themeDisplay.getPathJavaScript() %>/sniffer.js" type="text/javascript"></script>
		<script src="<%= themeDisplay.getPathJavaScript() %>/util.js" type="text/javascript"></script>
		<script src="<%= themeDisplay.getPathJavaScript() %>/portal.js" type="text/javascript"></script>
		<script src="<%= themeDisplay.getPathJavaScript() %>/ajax.js" type="text/javascript"></script>
		<script src="<%= themeDisplay.getPathJavaScript() %>/alerts.js" type="text/javascript"></script>
		<script src="<%= themeDisplay.getPathJavaScript() %>/swfobject.js" type="text/javascript"></script>

		<script src="<%= themeDisplay.getPathJavaScript() %>/calendar/calendar_stripped.js" type="text/javascript"></script>
		<script src="<%= themeDisplay.getPathJavaScript() %>/calendar/calendar-setup_stripped.js" type="text/javascript"></script>

		<script src="<%= themeDisplay.getPathJavaScript() %>/colorpicker/colorpicker.js" type="text/javascript"></script>

		<script src="<%= themeDisplay.getPathJavaScript() %>/dragdrop/coordinates.js" type="text/javascript"></script>
		<script src="<%= themeDisplay.getPathJavaScript() %>/dragdrop/drag.js" type="text/javascript"></script>
		<script src="<%= themeDisplay.getPathJavaScript() %>/dragdrop/dragdrop.js" type="text/javascript"></script>
		<script src="<%= themeDisplay.getPathJavaScript() %>/dragdrop/resize.js" type="text/javascript"></script>

		<script src="<%= themeDisplay.getPathJavaScript() %>/portlet/layout_configuration.js" type="text/javascript"></script>
		<script src="<%= themeDisplay.getPathJavaScript() %>/portlet/messaging.js" type="text/javascript"></script>

		<c:if test="<%= GetterUtil.getBoolean(PropsUtil.get(PropsUtil.JAVASCRIPT_LOG_ENABLED)) %>">
			<script src="<%= themeDisplay.getPathJavaScript() %>/log/log4javascript.js" type="text/javascript"></script>
		</c:if>
	</c:otherwise>
</c:choose>

<script type="text/javascript" src="<%= themeDisplay.getPathMain() %>/portal/javascript_cached?themeId=<%= themeDisplay.getTheme().getThemeId() %>&languageId=<%= themeDisplay.getLocale().toString() %>&colorSchemeId=<%= themeDisplay.getColorScheme().getColorSchemeId() %>"></script>

<script type="text/javascript">
	<c:if test="<%= themeDisplay.isSignedIn() %>">
		function showPageSettings() {
			Alerts.popupIframe("<%= themeDisplay.getURLPageSettings().toString() %>",
				{
					height: 400,
					width: 700,
					modal: true,
					title: '<%= UnicodeLanguageUtil.get(pageContext, "page-settings") %>',
					onClose: function() {
							window.location.reload(false);
						}
				});
		}
	</c:if>

	<%@ include file="/html/js/log/log.jsp" %>

	<liferay-theme:include page="javascript.jsp" />
</script>