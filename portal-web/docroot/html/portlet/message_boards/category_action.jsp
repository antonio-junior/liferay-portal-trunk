<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MBCategory category = (MBCategory)row.getObject();
MBMailing mailing= null;
try {
	mailing= MBMailingLocalServiceUtil.getMailingByCategory(category.getCategoryId());
} catch (NoSuchMailingException nsme) {
}
%>



<liferay-ui:icon-menu>
	<c:if test="<%= MBCategoryPermission.contains(permissionChecker, category, ActionKeys.UPDATE) %>">
		<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editURL">
			<portlet:param name="struts_action" value="/message_boards/edit_category" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="categoryId" value="<%= String.valueOf(category.getCategoryId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" url="<%= editURL %>" />
	</c:if>

	<c:if test="<%= mailing != null && MBMailingPermission.contains(permissionChecker, mailing, ActionKeys.ACTIVATE)%>">
		<c:choose>
			<c:when test="<%=   mailing.isActive() %>">
				<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="deactiveURL">
					<portlet:param name="struts_action" value="/message_boards/edit_category" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DEACTIVATE %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="mailingId" value="<%= String.valueOf(mailing.getMailingId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon image="deactivate" message="deactivate-mailing" url="<%= deactiveURL %>" />
			</c:when>
			<c:otherwise>
				<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="activeURL">
					<portlet:param name="struts_action" value="/message_boards/edit_category" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ACTIVATE %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="mailingId" value="<%= String.valueOf(mailing.getMailingId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon image="activate" message="activate-mailing" url="<%= activeURL %>" />
			</c:otherwise>
		</c:choose>
	</c:if>

	<c:if test="<%= MBCategoryPermission.contains(permissionChecker, category, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= MBCategory.class.getName() %>"
			modelResourceDescription="<%= category.getName() %>"
			resourcePrimKey="<%= String.valueOf(category.getCategoryId()) %>"
			var="permissionsURL"
		/>

		<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
	</c:if>

	<liferay-ui:icon image="rss" url='<%= themeDisplay.getPortalURL() + themeDisplay.getPathMain() + "/message_boards/rss?p_l_id=" + plid + "&categoryId=" + category.getCategoryId() + rssURLParams %>' method="get" target="_blank" />

	<c:if test="<%= MBCategoryPermission.contains(permissionChecker, category, ActionKeys.SUBSCRIBE) %>">
		<c:choose>
			<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), MBCategory.class.getName(), category.getCategoryId()) %>">
				<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="unsubscribeURL">
					<portlet:param name="struts_action" value="/message_boards/edit_category" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNSUBSCRIBE %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="categoryId" value="<%= String.valueOf(category.getCategoryId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon image="unsubscribe" url="<%= unsubscribeURL %>" />
			</c:when>
			<c:otherwise>
				<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="subscribeURL">
					<portlet:param name="struts_action" value="/message_boards/edit_category" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.SUBSCRIBE %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="categoryId" value="<%= String.valueOf(category.getCategoryId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon image="subscribe" url="<%= subscribeURL %>" />
			</c:otherwise>
		</c:choose>
	</c:if>

	<c:if test="<%= MBCategoryPermission.contains(permissionChecker, category, ActionKeys.DELETE) %>">
		<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="deleteURL">
			<portlet:param name="struts_action" value="/message_boards/edit_category" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="categoryId" value="<%= String.valueOf(category.getCategoryId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteURL %>" />
	</c:if>
</liferay-ui:icon-menu>