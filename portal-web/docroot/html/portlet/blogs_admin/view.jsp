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

<%@ include file="/html/portlet/blogs_admin/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/blogs_admin/view");
%>

<aui:form action="<%= portletURL.toString() %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
	<aui:input name="deleteEntryIds" type="hidden" />

	<liferay-util:include page="/html/portlet/blogs_admin/toolbar.jsp">
		<liferay-util:param name="toolbarItem" value="view-all" />
	</liferay-util:include>

	<liferay-ui:search-container
		rowChecker="<%= new RowChecker(renderResponse) %>"
		headerNames="title,author,createDate,status"
		emptyResultsMessage="no-entries-were-found"
	>
		<liferay-ui:search-container-results>
			<%@ include file="/html/portlet/blogs_admin/entry_search_results_database.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.portlet.blogs.model.BlogsEntry"
			escapedModel="<%= true %>"
			keyProperty="entryId"
			modelVar="entry"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="struts_action" value="/blogs_admin/view_entry" />
				<portlet:param name="redirect" value="<%= searchContainer.getIteratorURL().toString() %>" />
				<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
			</liferay-portlet:renderURL>

			<%@ include file="/html/portlet/blogs_admin/search_columns.jspf" %>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/html/portlet/blogs_admin/entry_action.jsp"
			/>

		</liferay-ui:search-container-row>

		<% String taglibOnClick = renderResponse.getNamespace() + "deleteEntries('" + Constants.DELETE + "');"; %>

		<aui:button onClick="<%= taglibOnClick %>" value="delete" />

		<div class="separator"><!-- --></div>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />deleteEntries',
		function(cmd) {
			var deleteEntries = true;

			var deleteEntryIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");

			if (!deleteEntryIds) {
				deleteEntries = false;
			}

			if (deleteEntries) {
				document.<portlet:namespace />fm.method = "post";
				document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = cmd;
				document.<portlet:namespace />fm.<portlet:namespace />deleteEntryIds.value = deleteEntryIds;
				submitForm(document.<portlet:namespace />fm, "<portlet:actionURL><portlet:param name="struts_action" value="/blogs_admin/edit_entry" /></portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>