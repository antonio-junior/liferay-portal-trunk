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

<%@ include file="/html/portlet/enterprise_admin/init.jsp" %>

<%
User selUser = (User)request.getAttribute("user.selUser");
%>

<h3><liferay-ui:message key="alerts-and-announcements" /></h3>

<liferay-ui:message key="select-the-delivery-options-for-alerts-and-announcements" />

<br /><br />

<%
SearchContainer searchContainer = new SearchContainer();

List<String> headerNames = new ArrayList<String>();

headerNames.add("type");
headerNames.add("email");
headerNames.add("sms");
headerNames.add("website");

searchContainer.setHeaderNames(headerNames);

List<AnnouncementsDelivery> results = AnnouncementsDeliveryLocalServiceUtil.getUserDeliveries(selUser.getUserId());
List<ResultRow> resultRows = searchContainer.getResultRows();

for (int i = 0; i < results.size(); i++) {
	AnnouncementsDelivery delivery = results.get(i);

	ResultRow row = new ResultRow(delivery, delivery.getDeliveryId(), i);

	// Type

	row.addText(LanguageUtil.get(pageContext, delivery.getType()));

	// Email

	row.addJSP("/html/portlet/enterprise_admin/user_announcements_checkbox.jsp");

	// SMS

	row.addJSP("/html/portlet/enterprise_admin/user_announcements_checkbox.jsp");

	// Website

	row.addJSP("/html/portlet/enterprise_admin/user_announcements_checkbox.jsp");

	// Add result row

	resultRows.add(row);
}
%>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />