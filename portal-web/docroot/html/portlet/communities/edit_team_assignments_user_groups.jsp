<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/communities/init.jsp" %>

<%

String tabs2 = (String)request.getAttribute("edit_team_assignments.jsp-tabs2");

int cur = (Integer)request.getAttribute("edit_team_assignments.jsp-cur");

Team team = (Team)request.getAttribute("edit_team_assignments.jsp-team");

Group group = (Group)request.getAttribute("edit_team_assignments.jsp-group");

Organization organization = (Organization)request.getAttribute("edit_team_assignments.jsp-organization");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_team_assignments.jsp-portletURL");
%>


<liferay-ui:tabs
	names="current,available"
	param="tabs2"
	url="<%= portletURL.toString() %>"
/>

<aui:input name="addUserGroupIds" type="hidden" />
<aui:input name="removeUserGroupIds" type="hidden" />

<liferay-ui:search-container
	rowChecker="<%= new UserGroupTeamChecker(renderResponse, team) %>"
	searchContainer="<%= new UserGroupSearch(renderRequest, portletURL) %>"
>
	<liferay-ui:search-form
		page="/html/portlet/enterprise_admin/user_group_search.jsp"
	/>

	<%
	UserGroupSearchTerms searchTerms = (UserGroupSearchTerms)searchContainer.getSearchTerms();

	LinkedHashMap userGroupParams = new LinkedHashMap();

    userGroupParams.put("userGroupsGroups", new Long(group.getGroupId()));

	if (tabs2.equals("current")) {
		userGroupParams.put("userGroupsTeams", new Long(team.getTeamId()));
	}
	%>

	<liferay-ui:search-container-results
		results="<%= UserGroupLocalServiceUtil.search(company.getCompanyId(), searchTerms.getName(), searchTerms.getDescription(), userGroupParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
		total="<%= UserGroupLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getName(), searchTerms.getDescription(), userGroupParams) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.UserGroup"
		escapedModel="<%= true %>"
		keyProperty="userGroupId"
		modelVar="userGroup"
	>
		<liferay-ui:search-container-column-text
			name="name"
			orderable="<%= true %>"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			name="description"
			orderable="<%= true %>"
			property="description"
		/>
	</liferay-ui:search-container-row>

    <div class="separator"><!-- --></div>

    <%
    String taglibOnClick = renderResponse.getNamespace() + "updateTeamUserGroups('" + portletURL.toString() + StringPool.AMPERSAND + renderResponse.getNamespace() + "cur=" + cur + "');";
    %>

    <aui:button onClick="<%= taglibOnClick %>" value="update-associations" />

    <br /><br />

    <liferay-ui:search-iterator />
</liferay-ui:search-container>