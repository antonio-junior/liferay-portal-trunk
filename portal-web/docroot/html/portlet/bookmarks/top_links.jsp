<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/bookmarks/init.jsp" %>

<%
String topLink = ParamUtil.getString(request, "topLink", "bookmarks-home");

long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

boolean viewFolder = GetterUtil.getBoolean((String)request.getAttribute("view.jsp-viewFolder"));

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.MAXIMIZED);
%>

<div class="top-links-container">
	<div class="top-links">
		<div class="top-links-navigation">

			<%
			portletURL.setParameter("topLink", "bookmarks-home");
			%>

			<liferay-ui:icon cssClass="top-link" image="../aui/home" message="bookmarks-home" label="<%= true %>" url='<%= (topLink.equals("bookmarks-home") && folderId == 0 && viewFolder) ? StringPool.BLANK : portletURL.toString() %>' />

			<%
			portletURL.setParameter("topLink", "recent-entries");
			%>

			<liferay-ui:icon cssClass='<%= "top-link" + (themeDisplay.isSignedIn() ? StringPool.BLANK : " last") %>' image="../aui/clock" message="recent-entries" label="<%= true %>" url='<%= topLink.equals("recent-entries") ? StringPool.BLANK : portletURL.toString() %>'/>

			<c:if test="<%= themeDisplay.isSignedIn() %>">

				<%
				portletURL.setParameter("topLink", "my-entries");
				%>

				<liferay-ui:icon cssClass="top-link last" image="../aui/person" message="my-entries" label="<%= true %>" url='<%= topLink.equals("my-entries") ? StringPool.BLANK : portletURL.toString() %>'/>
			</c:if>
		</div>

		<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" varImpl="searchURL">
			<portlet:param name="struts_action" value="/bookmarks/search" />
		</liferay-portlet:renderURL>

		<div class="folder-search">
			<aui:form action="<%= searchURL %>" method="get" name="searchFm">
				<liferay-portlet:renderURLParams varImpl="searchURL" />
				<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
				<aui:input name="breadcrumbsFolderId" type="hidden" value="<%= folderId %>" />
				<aui:input name="searchFolderIds" type="hidden" value="<%= folderId %>" />

				<aui:input inlineField="<%= true %>" id="keywords1" label="" name="keywords" size="30" type="text" />

				<aui:button type="submit" value="search" />
			</aui:form>
		</div>
	</div>
</div>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />searchFm.<portlet:namespace />keywords);
	</aui:script>
</c:if>