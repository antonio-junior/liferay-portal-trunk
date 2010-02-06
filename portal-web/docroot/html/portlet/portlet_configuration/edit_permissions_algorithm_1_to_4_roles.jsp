<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/portlet_configuration/init.jsp" %>

<%
String tabs2 = (String)request.getAttribute("edit_permissions_algorithm_1_to_4.jsp-tabs2");
String tabs3 = (String)request.getAttribute("edit_permissions_algorithm_1_to_4.jsp-tabs3");

String portletResource = (String)request.getAttribute("edit_permissions_algorithm_1_to_4.jsp-portletResource");
String modelResource = (String)request.getAttribute("edit_permissions_algorithm_1_to_4.jsp-modelResource");
Resource resource = (Resource)request.getAttribute("edit_permissions_algorithm_1_to_4.jsp-resource");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_permissions_algorithm_1_to_4.jsp-portletURL");

String roleIds = ParamUtil.getString(request, "roleIds");

long[] roleIdsArray = StringUtil.split(roleIds, 0L);

int roleIdsPos = ParamUtil.getInteger(request, "roleIdsPos");

int type = RoleConstants.TYPE_REGULAR;

if (tabs2.equals("community-roles")) {
	type = RoleConstants.TYPE_COMMUNITY;
}
else if (tabs2.equals("organization-roles")) {
	type = RoleConstants.TYPE_ORGANIZATION;
}
%>

<aui:input name="roleIds" type="hidden" value="<%= roleIds %>" />
<aui:input name="roleIdsPos" type="hidden" value="<%= roleIdsPos %>" />
<aui:input name="roleIdsPosValue" type="hidden" />
<aui:input name="roleIdActionIds" type="hidden" />

<c:choose>
	<c:when test="<%= roleIdsArray.length == 0 %>">
		<liferay-ui:tabs
			names="current,available"
			param="tabs3"
			url="<%= portletURL.toString() %>"
		/>

		<%
		RoleSearch searchContainer = new RoleSearch(renderRequest, portletURL);

		searchContainer.setRowChecker(new RowChecker(renderResponse));
		%>

		<liferay-ui:search-form
			page="/html/portlet/enterprise_admin/role_search.jsp"
			searchContainer="<%= searchContainer %>"
		/>

		<%
		RoleSearchTerms searchTerms = (RoleSearchTerms)searchContainer.getSearchTerms();

		LinkedHashMap roleParams = new LinkedHashMap();

		if (tabs3.equals("current")) {
			roleParams.put("permissionsResourceId", new Long(resource.getResourceId()));
		}

		int total = RoleLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getName(), searchTerms.getDescription(), new Integer(type), roleParams);

		searchContainer.setTotal(total);

		List results = RoleLocalServiceUtil.search(company.getCompanyId(), searchTerms.getName(), searchTerms.getDescription(), new Integer(type), roleParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

		searchContainer.setResults(results);
		%>

		<div class="separator"><!-- --></div>

		<aui:button onClick='<%= renderResponse.getNamespace() + "updateRolePermissions();" %>' value="update-permissions" />

		<br /><br />

		<%
		List<String> headerNames = new ArrayList<String>();

		headerNames.add("name");
		headerNames.add("type");
		headerNames.add("permissions");

		searchContainer.setHeaderNames(headerNames);

		List resultRows = searchContainer.getResultRows();

		for (int i = 0; i < results.size(); i++) {
			Role role = (Role)results.get(i);

			role = role.toEscapedModel();

			ResultRow row = new ResultRow(role, role.getRoleId(), i);

			// Name

			row.addText(role.getTitle(locale));

			// Type

			row.addText(LanguageUtil.get(pageContext, role.getTypeLabel()));

			// Permissions

			List permissions = PermissionLocalServiceUtil.getRolePermissions(role.getRoleId(), resource.getResourceId());

			List actions = ResourceActionsUtil.getActions(permissions);
			List actionsNames = ResourceActionsUtil.getActionsNames(pageContext, actions);

			row.addText(StringUtil.merge(actionsNames, ", "));

			// Add result row

			resultRows.add(row);
		}
		%>

		<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
	</c:when>
	<c:otherwise>

		<%
		Role role = RoleLocalServiceUtil.getRole(roleIdsArray[roleIdsPos]);

		boolean isGuestRole = role.getName().equals(RoleConstants.GUEST);
		%>

		<liferay-ui:tabs names="<%= role.getTitle(locale) %>" />

		<%
		List permissions = PermissionLocalServiceUtil.getRolePermissions(role.getRoleId(), resource.getResourceId());

		List actions1 = ResourceActionsUtil.getResourceActions(portletResource, modelResource);
		List actions2 = ResourceActionsUtil.getActions(permissions);

		List guestUnsupportedActions = ResourceActionsUtil.getResourceGuestUnsupportedActions(portletResource, modelResource);

		// Left list

		List leftList = new ArrayList();

		for (int i = 0; i < actions2.size(); i++) {
			String actionId = (String)actions2.get(i);

			if (isGuestRole) {
				if (!guestUnsupportedActions.contains(actionId)) {
					leftList.add(new KeyValuePair(actionId, ResourceActionsUtil.getAction(pageContext, actionId)));
				}
			}
			else {
				leftList.add(new KeyValuePair(actionId, ResourceActionsUtil.getAction(pageContext, actionId)));
			}
		}

		leftList = ListUtil.sort(leftList, new KeyValuePairComparator(false, true));

		// Right list

		List rightList = new ArrayList();

		for (int i = 0; i < actions1.size(); i++) {
			String actionId = (String)actions1.get(i);

			if (!actions2.contains(actionId)) {
				if (isGuestRole) {
					if (!guestUnsupportedActions.contains(actionId)) {
						rightList.add(new KeyValuePair(actionId, ResourceActionsUtil.getAction(pageContext, actionId)));
					}
				}
				else {
					rightList.add(new KeyValuePair(actionId, ResourceActionsUtil.getAction(pageContext, actionId)));
				}
			}
		}

		rightList = ListUtil.sort(rightList, new KeyValuePairComparator(false, true));
		%>

		<div class="assign-permissions">
			<liferay-ui:input-move-boxes
				formName="fm"
				leftTitle="what-they-can-do"
				rightTitle="what-they-cant-do"
				leftBoxName="current_actions"
				rightBoxName="available_actions"
				leftList="<%= leftList %>"
				rightList="<%= rightList %>"
			/>

			<aui:button-row>

				<%
				String taglibPreviousOnClick = renderResponse.getNamespace() + "saveRolePermissions(" + (roleIdsPos - 1) + ", '" + roleIdsArray[roleIdsPos] + "');";
				String taglibNextOnClick = renderResponse.getNamespace() + "saveRolePermissions(" + (roleIdsPos + 1) + ", '" + roleIdsArray[roleIdsPos] + "');";
				String taglibFinishedOnClick = renderResponse.getNamespace() + "saveRolePermissions(-1, '"+ roleIdsArray[roleIdsPos] + "');";
				%>

				<aui:button cssClass="previous" disabled="<%= roleIdsPos <= 0 %>" onClick='<%= taglibPreviousOnClick %>' value="previous" />

				<aui:button cssClass="next" disabled="<%= roleIdsPos + 1 >= roleIdsArray.length %>" onClick='<%= taglibNextOnClick %>' value="next" />

				<aui:button cssClass="finished" onClick="<%= taglibFinishedOnClick %>" value="finished"  />
			</aui:button-row>
		</div>
	</c:otherwise>
</c:choose>