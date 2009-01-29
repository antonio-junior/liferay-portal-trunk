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

<%@ include file="/html/portlet/announcements/init.jsp" %>

<%
themeDisplay.setIncludeServiceJs(true);

String tabs1 = ParamUtil.getString(request, "tabs1", "entries");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.MAXIMIZED);

portletURL.setParameter("struts_action", "/announcements/view");
portletURL.setParameter("tabs1", tabs1);
%>

<c:if test="<%= !portletName.equals(PortletKeys.ALERTS) || (portletName.equals(PortletKeys.ALERTS) && PortletPermissionUtil.contains(permissionChecker, plid, PortletKeys.ANNOUNCEMENTS, ActionKeys.ADD_ENTRY)) %>">

	<%
	String tabs1Names = "entries";

	if (PortletPermissionUtil.contains(permissionChecker, plid, PortletKeys.ANNOUNCEMENTS, ActionKeys.ADD_ENTRY)) {
		tabs1Names += ",manage-entries";
	}
	%>

	<liferay-ui:tabs
		names="<%= tabs1Names %>"
		url="<%= portletURL.toString() %>"
	/>
</c:if>

<c:choose>
	<c:when test='<%= tabs1.equals("entries") %>'>
		<script type="text/javascript">
			function <portlet:namespace />handleEntry(entryId) {
				var entry = jQuery('#<portlet:namespace/>' + entryId);
				var container = entry.parent();

				if (container.hasClass('unread-entries')) {
					<portlet:namespace />_markEntry(entry, entryId);
				}
				else {
					<portlet:namespace />_toggleContent(entry);
				}
			}

			function <portlet:namespace />_markEntry(entry, entryId) {
				Liferay.Service.Announcements.AnnouncementsFlag.addFlag({entryId : entryId, flag: <%= AnnouncementsFlagImpl.HIDDEN %>});

				var readContainer = jQuery('.portlet-announcements .read-entries');
				var control = entry.find('.control-entry a');

				entry.hide(
					'normal',
					function() {
						entry.appendTo(readContainer);

						control.html('<liferay-ui:message key="show" />');
					}
				);

				entry.show('normal');
			}

			function <portlet:namespace />_toggleContent(entry) {
				var content = entry.find('.entry-content');
				var control = entry.find('.control-entry a');

				if (entry.hasClass('visable')) {
					entry.removeClass('visable');

					content.hide();

					control.html('<liferay-ui:message key="show" />');
				}
				else {
					entry.addClass('visable');

					content.show();

					control.html('<liferay-ui:message key="hide" />');
				}
			}
		</script>

		<%
		LinkedHashMap<Long, long[]> scopes = AnnouncementsUtil.getAnnouncementScopes(user.getUserId());

		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, delta, portletURL, null, "no-entries-were-found");

		List<AnnouncementsEntry> results = null;
		int total = 0;

		int flagValue = AnnouncementsFlagImpl.NOT_HIDDEN;
		%>

		<div class="unread-entries">
			<%@ include file="/html/portlet/announcements/entry_iterator.jspf" %>
		</div>

		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" type="article" />

		<%
		flagValue = AnnouncementsFlagImpl.HIDDEN;
		%>

		<div class="read-entries">
			<%@ include file="/html/portlet/announcements/entry_iterator.jspf" %>
		</div>

		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" type="article" />
	</c:when>
	<c:when test='<%= tabs1.equals("manage-entries") %>'>

		<%
		String distributionScope = ParamUtil.getString(request, "distributionScope");

		long classNameId = -1;
		long classPK = -1;

		String[] distributionScopeArray = StringUtil.split(distributionScope);

		if (distributionScopeArray.length == 2) {
			classNameId = GetterUtil.getLong(distributionScopeArray[0]);
			classPK = GetterUtil.getLong(distributionScopeArray[1]);
		}

		if ((classNameId == 0) && (classPK == 0) && !permissionChecker.isOmniadmin()) {
			throw new PrincipalException();
		}
		%>

		<script type="text/javascript">
			function <portlet:namespace />selectDistributionScope(distributionScope) {
				var url = "<%= portletURL.toString() %>&<portlet:namespace />distributionScope=" + distributionScope;
				submitForm(document.<portlet:namespace />fm, url);
			}
		</script>

		<form action="<%= portletURL.toString() %>" method="post" name="<portlet:namespace />fm" onSubmit="submitForm(this); return false;" />

		<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="distribution-scope" />
			</td>
			<td>
				<select name="<portlet:namespace />distributionScope" onChange="<portlet:namespace />selectDistributionScope(this.value);">
					<option value=""></option>

					<c:if test="<%= permissionChecker.isOmniadmin() %>">
						<option <%= ((classNameId == 0) && (classPK == 0)) ? "selected" : "" %> value="0,0"><liferay-ui:message key="general" /></option>
					</c:if>

					<%
					List<Group> groups = GroupLocalServiceUtil.getUserGroups(user.getUserId());
					%>

					<c:if test="<%= groups.size() > 0 %>">
						<optgroup label="<liferay-ui:message key="communities" />">

							<%
							for (Group group : groups) {
								if (group.isCommunity() && GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.MANAGE_ANNOUNCEMENTS)) {
							%>

									<option <%= (classPK == group.getGroupId()) ? "selected" : "" %> value="<%= PortalUtil.getClassNameId(Group.class) %><%= StringPool.COMMA %><%= group.getGroupId() %>"><%= group.getName() %></option>

							<%
								}
							}
							%>

						</optgroup>
					</c:if>

					<%
					List<Organization> organizations = OrganizationLocalServiceUtil.getUserOrganizations(user.getUserId());
					%>

					<c:if test="<%= organizations.size() > 0 %>">
						<optgroup label="<liferay-ui:message key="organizations" />">

							<%
							for (Organization organization : organizations) {
								if (OrganizationPermissionUtil.contains(permissionChecker, organization.getOrganizationId(), ActionKeys.MANAGE_ANNOUNCEMENTS)) {
							%>

									<option <%= (classPK == organization.getOrganizationId()) ? "selected" : "" %> value="<%= PortalUtil.getClassNameId(Organization.class) %><%= StringPool.COMMA %><%= organization.getOrganizationId() %>"><%= organization.getName() %></option>

							<%
								}
							}
							%>

						</optgroup>
					</c:if>

					<%
					List<Role> roles = RoleLocalServiceUtil.getRoles(themeDisplay.getCompanyId());
					%>

					<c:if test="<%= roles.size() > 0 %>">
						<optgroup label="<liferay-ui:message key="roles" />">

							<%
							for (Role role : roles) {
								if (RolePermissionUtil.contains(permissionChecker, role.getRoleId(), ActionKeys.MANAGE_ANNOUNCEMENTS)) {
							%>

									<option <%= (classPK == role.getRoleId()) ? "selected" : "" %> value="<%= PortalUtil.getClassNameId(Role.class) %><%= StringPool.COMMA %><%= role.getRoleId() %>"><%= role.getTitle(locale) %></option>

							<%
								}
							}
							%>

						</optgroup>
					</c:if>

					<%
					List<UserGroup> userGroups = UserGroupLocalServiceUtil.getUserGroups(themeDisplay.getCompanyId());
					%>

					<c:if test="<%= userGroups.size() > 0 %>">
						<optgroup label="<liferay-ui:message key="user-groups" />">

							<%
							for (UserGroup userGroup : userGroups) {
								if (UserGroupPermissionUtil.contains(permissionChecker, userGroup.getUserGroupId(), ActionKeys.MANAGE_ANNOUNCEMENTS)) {
							%>

									<option <%= (classPK == userGroup.getUserGroupId()) ? "selected" : "" %> value="<%= PortalUtil.getClassNameId(UserGroup.class) %><%= StringPool.COMMA %><%= userGroup.getUserGroupId() %>"><%= userGroup.getName() %></option>

							<%
								}
							}
							%>

						</optgroup>
					</c:if>
				</select>
			</td>
		</tr>
		</table>

		<br />

		<input type="button" value='<liferay-ui:message key="add-entry" />' onClick="location.href = '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/announcements/edit_entry" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="distributionScope" value="<%= distributionScope %>" /></portlet:renderURL>'" />

		<c:if test="<%= Validator.isNotNull(distributionScope) %>">
			<br /><br />

			<div class="separator"><!-- --></div>

			<%
			List<String> headerNames = new ArrayList<String>();

			headerNames.add("title");
			headerNames.add("type");
			headerNames.add("modified-date");
			headerNames.add("display-date");
			headerNames.add("expiration-date");
			headerNames.add(StringPool.BLANK);

			SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, "no-entries-were-found");

			int total = AnnouncementsEntryLocalServiceUtil.getEntriesCount(classNameId, classPK, portletName.equals(PortletKeys.ALERTS));

			searchContainer.setTotal(total);

			List<AnnouncementsEntry> results = AnnouncementsEntryLocalServiceUtil.getEntries(classNameId, classPK, portletName.equals(PortletKeys.ALERTS), searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);

			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < results.size(); i++) {
				AnnouncementsEntry entry = results.get(i);

				entry = entry.toEscapedModel();

				ResultRow row = new ResultRow(entry, entry.getEntryId(), i);

				PortletURL rowURL = renderResponse.createRenderURL();

				rowURL.setWindowState(WindowState.MAXIMIZED);

				rowURL.setParameter("struts_action", "/announcements/edit_entry");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("entryId", String.valueOf(entry.getEntryId()));

				// Title

				row.addText(entry.getTitle(), rowURL);

				// Type

				row.addText(LanguageUtil.get(pageContext, entry.getType()), rowURL);

				// Modified date

				row.addText(dateFormatDate.format(entry.getModifiedDate()), rowURL);

				// Display date

				row.addText(dateFormatDate.format(entry.getDisplayDate()), rowURL);

				// Expiration date

				row.addText(dateFormatDate.format(entry.getExpirationDate()), rowURL);

				// Action

				row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/announcements/entry_action.jsp");

				// Add result row

				resultRows.add(row);
			}
			%>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
		</c:if>

		</form>
	</c:when>
</c:choose>