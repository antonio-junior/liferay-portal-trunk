<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>

<%@ include file="/html/taglib/ui/search/init.jsp" %>

<portlet:defineObjects />

<%
long groupId = ParamUtil.getLong(request, namespace + "groupId");

Group group = themeDisplay.getScopeGroup();

String keywords = ParamUtil.getString(request, namespace + "keywords");

LiferayPortletURL portletURL = (LiferayPortletURL)renderResponse.createRenderURL();

portletURL.setPortletId(PortletKeys.SEARCH);

portletURL.setWindowState(WindowState.MAXIMIZED);
portletURL.setPortletMode(PortletMode.VIEW);

portletURL.setParameter("struts_action", "/search/search");
%>

<form action="<%= portletURL.toString() %>" method="post" name="<%= randomNamespace %><%= namespace %>fm" onSubmit="<%= randomNamespace %><%= namespace %>search(); return false;">
<input name="<%= namespace %>keywords" size="30" type="text" value="<%= HtmlUtil.escapeAttribute(keywords) %>" />

<select name="<%= namespace %>groupId">
	<option value="0" <%= (groupId == 0) ? "selected" : "" %>><liferay-ui:message key="everything" /></option>
	<option value="<%= group.getGroupId() %>" <%= (groupId != 0) ? "selected" : "" %>><liferay-ui:message key='<%= "this-" + (group.isOrganization() ? "organization" : "community") %>' /></option>
</select>

<input align="absmiddle" border="0" src="<%= themeDisplay.getPathThemeImages() %>/common/search.png" title="<liferay-ui:message key="search" />" type="image" />