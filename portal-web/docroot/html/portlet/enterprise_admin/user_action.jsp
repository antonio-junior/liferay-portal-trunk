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

<%@ include file="/html/portlet/enterprise_admin/init.jsp" %>

<c:if test="<%= portletName.equals(PortletKeys.ENTERPRISE_ADMIN) || portletName.equals(PortletKeys.ORGANIZATION_ADMIN) %>">

	<%
	UserSearch searchContainer = (UserSearch)request.getAttribute("liferay-ui:search:searchContainer");

	String redirect = searchContainer.getIteratorURL().toString();

	UserSearchTerms searchTerms = (UserSearchTerms)searchContainer.getSearchTerms();

	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

	User user2 = (User)row.getObject();

	long userId = user2.getUserId();

	long[] organizationIdsArray = user2.getOrganizationIds();
	%>

	<c:if test="<%= UserPermissionUtil.contains(permissionChecker, userId, organizationIdsArray, ActionKeys.UPDATE) %>">
		<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editUserURL">
			<portlet:param name="struts_action" value="/enterprise_admin/edit_user" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="p_u_i_d" value="<%= String.valueOf(user2.getUserId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" url="<%= editUserURL %>" />
	</c:if>

	<c:if test="<%= UserPermissionUtil.contains(permissionChecker, userId, organizationIdsArray, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= User.class.getName() %>"
			modelResourceDescription="<%= user2.getFullName() %>"
			resourcePrimKey="<%= String.valueOf(user2.getUserId()) %>"
			var="permissionsUserURL"
		/>

		<liferay-ui:icon image="permissions" url="<%= permissionsUserURL %>" />
	</c:if>

	<c:if test="<%= UserPermissionUtil.contains(permissionChecker, userId, organizationIdsArray, ActionKeys.UPDATE) && user2.isLayoutsRequired() %>">
		<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="pagesURL">
			<portlet:param name="struts_action" value="/enterprise_admin/edit_pages" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(user2.getGroup().getGroupId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="pages" message="configure-pages" url="<%= pagesURL %>" />
	</c:if>

	<c:if test="<%= portletName.equals(PortletKeys.ENTERPRISE_ADMIN) %>">
		<c:if test="<%= !GetterUtil.getBoolean(PropsUtil.get(PropsUtil.PORTAL_JAAS_ENABLE)) && GetterUtil.getBoolean(PropsUtil.get(PropsUtil.PORTAL_IMPERSONATION_ENABLE)) && (user.getUserId() != user2.getUserId()) && !themeDisplay.isImpersonated() && UserPermissionUtil.contains(permissionChecker, userId, organizationIdsArray, ActionKeys.IMPERSONATE) %>">
			<liferay-security:doAsURL
				doAsUserId="<%= user2.getUserId() %>"
				var="impersonateUserURL"
			/>

			<liferay-ui:icon image="impersonate_user" url="<%= impersonateUserURL %>" target="_blank" />
		</c:if>

		<c:if test="<%= UserPermissionUtil.contains(permissionChecker, userId, organizationIdsArray, ActionKeys.DELETE) %>">
			<c:if test="<%= !searchTerms.isActive() %>">
				<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="restoreUserURL">
					<portlet:param name="struts_action" value="/enterprise_admin/edit_user" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESTORE %>" />
					<portlet:param name="redirect" value="<%= redirect %>" />
					<portlet:param name="deleteUserIds" value="<%= String.valueOf(userId) %>" />
				</portlet:actionURL>

				<liferay-ui:icon image="activate" url="<%= restoreUserURL %>" />
			</c:if>

			<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="deleteUserURL">
				<portlet:param name="struts_action" value="/enterprise_admin/edit_user" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= searchTerms.isActive() ? Constants.DEACTIVATE : Constants.DELETE %>" />
				<portlet:param name="redirect" value="<%= redirect %>" />
				<portlet:param name="deleteUserIds" value="<%= String.valueOf(userId) %>" />
			</portlet:actionURL>

			<c:choose>
				<c:when test="<%= searchTerms.isActive() %>">
					<liferay-ui:icon-deactivate url="<%= deleteUserURL %>" />
				</c:when>
				<c:when test="<%= !searchTerms.isActive() && GetterUtil.getBoolean(PropsUtil.get(PropsUtil.USERS_DELETE)) %>">
					<liferay-ui:icon-delete url="<%= deleteUserURL %>" />
				</c:when>
			</c:choose>
		</c:if>
	</c:if>
</c:if>