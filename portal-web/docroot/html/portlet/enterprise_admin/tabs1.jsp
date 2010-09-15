<%--
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
--%>

<%@ include file="/html/portlet/enterprise_admin/init.jsp" %>

<%
PortletURL tabs1URL = renderResponse.createRenderURL();

tabs1URL.setParameter("struts_action", "/enterprise_admin/view");

String tabs1Names = "users,organizations,user-groups";

String tabs1Values = tabs1Names;

if (!filterManageableOrganizations) {
	if (windowState.equals(WindowState.MAXIMIZED)) {
		tabs1Names += ",roles,password-policies,settings,monitoring,plugins";
	}
	else {
		tabs1Names += ",roles,&raquo;";
	}

	tabs1Values = tabs1Names;
}

String backURL = ParamUtil.getString(request, "backURL");
%>

<liferay-ui:tabs
	names="<%= tabs1Names %>"
	tabsValues="<%= tabs1Values %>"
	url="<%= tabs1URL.toString() %>"
	backURL="<%= backURL %>"
/>