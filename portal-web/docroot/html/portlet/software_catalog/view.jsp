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

<%@ include file="/html/portlet/software_catalog/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "products");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.MAXIMIZED);

portletURL.setParameter("struts_action", "/software_catalog/view");
portletURL.setParameter("tabs1", tabs1);
%>

<liferay-util:include page="/html/portlet/software_catalog/tabs1.jsp" />

<form action="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/software_catalog/search" /></portlet:renderURL>" method="post" name="<portlet:namespace />fm" onSubmit="submitForm(this); return false;">

<c:choose>
	<c:when test='<%= tabs1.equals("products") || tabs1.equals("my-products") %>'>

		<%
		List headerNames = new ArrayList();

		headerNames.add("name");
		headerNames.add("type");
		headerNames.add("licenses");
		headerNames.add("modified-date");
		headerNames.add(StringPool.BLANK);

		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);

		int total = 0;

		if (tabs1.equals("products")) {
			total = SCProductEntryLocalServiceUtil.getProductEntriesCount(portletGroupId.longValue());
		}
		else {
			total = SCProductEntryLocalServiceUtil.getProductEntriesCount(portletGroupId.longValue(), user.getUserId());
		}

		searchContainer.setTotal(total);

		List results = null;

		if (tabs1.equals("products")) {
			results = SCProductEntryLocalServiceUtil.getProductEntries(portletGroupId.longValue(), searchContainer.getStart(), searchContainer.getEnd());
		}
		else {
			results = SCProductEntryLocalServiceUtil.getProductEntries(portletGroupId.longValue(), user.getUserId(), searchContainer.getStart(), searchContainer.getEnd());
		}

		searchContainer.setResults(results);

		List resultRows = searchContainer.getResultRows();

		for (int i = 0; i < results.size(); i++) {
			SCProductEntry productEntry = (SCProductEntry) results.get(i);

			String productEntryId = String.valueOf(productEntry.getProductEntryId());

			ResultRow row = new ResultRow(productEntry, productEntryId, i);

			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setWindowState(WindowState.MAXIMIZED);

			rowURL.setParameter("struts_action", "/software_catalog/view_product_entry");
			rowURL.setParameter("redirect", currentURL);
			rowURL.setParameter("productEntryId", productEntryId);

			// Name and short description

			StringMaker sm = new StringMaker();

			String imageId = productEntry.getImageId("0");
			if (imageId != null) {
				String imageURL = themeDisplay.getPathImage() + "/software_catalog?img_id=" + imageId + "&small=1";
				sm.append("<img src='");
				sm.append(imageURL);
				sm.append("' align='left' style='margin-right: 10px'/>");
			}

			sm.append("<b>");
			sm.append(productEntry.getName());
			sm.append("</b>");

			if (Validator.isNotNull(productEntry.getShortDescription())) {
				sm.append("<br />");
				sm.append("<span style=\"font-size: xx-small;\">");
				sm.append(productEntry.getShortDescription());
				sm.append("</span>");
			}

			row.addText(sm.toString(), rowURL);

			// Type

			row.addText(LanguageUtil.get(pageContext, productEntry.getType()), rowURL);

			// Licenses

			sm = new StringMaker();

			Iterator itr = productEntry.getLicenses().iterator();

			while (itr.hasNext()) {
				SCLicense license = (SCLicense) itr.next();

				sm.append(license.getName());

				if (itr.hasNext()) {
					sm.append(", ");
				}
			}

			row.addText(sm.toString(), rowURL);

			// Modified date

			row.addText(dateFormatDateTime.format(productEntry.getModifiedDate()), rowURL);

			// Action

			row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/software_catalog/product_entry_action.jsp");

			// Add result row

			resultRows.add(row);
		}

		boolean showAddProductEntryButton = SCProductEntryPermission.contains(permissionChecker, plid, ActionKeys.ADD_PRODUCT_ENTRY);
		%>

		<c:if test="<%= showAddProductEntryButton || (results.size() > 0) %>">
			<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<c:if test="<%= showAddProductEntryButton %>">
					<td>
						<input type="button" value="<bean:message key="add-product" />" onClick="self.location = '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/software_catalog/edit_product_entry" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>';">
					</td>
					<td style="padding-left: 30px;"></td>
				</c:if>

				<c:if test='<%= (results.size() > 0) && tabs1.equals("products") %>'>
					<td>
						<input name="<portlet:namespace />keywords" size="30" type="text">

						<select name="<portlet:namespace/>type">
							<option value=""></option>
							<option value="portlet"><bean:message key="portlet" /></option>
							<option value="theme"><bean:message key="theme" /></option>
							<option value="layout-template"><bean:message key="layout-template" /></option>
							<option value="extension"><bean:message key="extension" /></option>
						</select>

						<input type="submit" value="<bean:message key="search-products" />">
					</td>
				</c:if>
			</tr>
			</table>

			<c:if test="<%= results.size() > 0 %>">
				<br />
			</c:if>
		</c:if>

		<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("framework-versions") %>'>

		<%
		List headerNames = new ArrayList();

		headerNames.add("name");
		headerNames.add("url");
		headerNames.add("active");
		headerNames.add(StringPool.BLANK);

		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);

		int total = SCFrameworkVersionLocalServiceUtil.getFrameworkVersionsCount(portletGroupId.longValue());

		searchContainer.setTotal(total);

		List results = SCFrameworkVersionLocalServiceUtil.getFrameworkVersions(portletGroupId.longValue(), searchContainer.getStart(),searchContainer.getEnd());

		searchContainer.setResults(results);

		List resultRows = searchContainer.getResultRows();

		for (int i = 0; i < results.size(); i++) {
			SCFrameworkVersion frameworkVersion = (SCFrameworkVersion) results.get(i);

			ResultRow row = new ResultRow(frameworkVersion, frameworkVersion.getFrameworkVersionId(), i);

			String rowHREF = frameworkVersion.getUrl();

			TextSearchEntry rowTextEntry = new TextSearchEntry(SearchEntry.DEFAULT_ALIGN, SearchEntry.DEFAULT_VALIGN, frameworkVersion.getName(), rowHREF, "_blank", frameworkVersion.getName());

			// Name

			row.addText(rowTextEntry);

			// URL

			rowTextEntry = (TextSearchEntry) rowTextEntry.clone();

			rowTextEntry.setName(frameworkVersion.getUrl());

			row.addText(rowTextEntry);

			// Active

			rowTextEntry = (TextSearchEntry) rowTextEntry.clone();

			rowTextEntry.setName(LanguageUtil.get(pageContext,frameworkVersion.isActive() ? "yes" : "no"));

			row.addText(rowTextEntry);

			// Action

			row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/software_catalog/framework_version_action.jsp");

			// Add result row

			resultRows.add(row);
		}
		%>

		<c:if test="<%= SCFrameworkVersionPermission.contains(permissionChecker, plid, ActionKeys.ADD_FRAMEWORK_VERSION) %>">
			<input type="button" value="<bean:message key="add-framework-version" />" onClick="self.location = '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/software_catalog/edit_framework_version" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>';"><br />

			<c:if test="<%= results.size() > 0 %>">
				<br />
			</c:if>
		</c:if>

		<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("licenses") %>'>

		<%
		List headerNames = new ArrayList();

		headerNames.add("name");
		headerNames.add("url");
		headerNames.add("open-source");
		headerNames.add("active");
		headerNames.add("recommended");
		headerNames.add(StringPool.BLANK);

		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);

		int total = SCLicenseLocalServiceUtil.getLicensesCount();

		searchContainer.setTotal(total);

		List results = SCLicenseLocalServiceUtil.getLicenses(searchContainer.getStart(), searchContainer.getEnd());

		searchContainer.setResults(results);

		List resultRows = searchContainer.getResultRows();

		for (int i = 0; i < results.size(); i++) {
			SCLicense license = (SCLicense) results.get(i);

			ResultRow row = new ResultRow(license, license.getLicenseId(), i);

			String rowHREF = license.getUrl();

			TextSearchEntry rowTextEntry = new TextSearchEntry(SearchEntry.DEFAULT_ALIGN, SearchEntry.DEFAULT_VALIGN, license.getName(), rowHREF, "_blank", license.getName());

			// Name

			row.addText(rowTextEntry);

			// URL

			rowTextEntry = (TextSearchEntry) rowTextEntry.clone();

			rowTextEntry.setName(license.getUrl());

			row.addText(rowTextEntry);

			// Open source

			rowTextEntry = (TextSearchEntry) rowTextEntry.clone();

			rowTextEntry.setName(LanguageUtil.get(pageContext, license.isOpenSource() ? "yes" : "no"));

			row.addText(rowTextEntry);

			// Active

			rowTextEntry = (TextSearchEntry) rowTextEntry.clone();

			rowTextEntry.setName(LanguageUtil.get(pageContext, license.isActive() ? "yes" : "no"));

			row.addText(rowTextEntry);

			// Recommended

			rowTextEntry = (TextSearchEntry) rowTextEntry.clone();

			rowTextEntry.setName(LanguageUtil.get(pageContext, license.isRecommended() ? "yes" : "no"));

			row.addText(rowTextEntry);

			// Action

			row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/software_catalog/license_action.jsp");

			// Add result row

			resultRows.add(row);
		}
		%>

		<c:if test="<%= PortalPermission.contains(permissionChecker, ActionKeys.ADD_LICENSE) %>">
			<input type="button" value="<bean:message key="add-license" />" onClick="self.location = '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/software_catalog/edit_license" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>';"><br />

			<c:if test="<%= results.size() > 0 %>">
				<br />
			</c:if>
		</c:if>

		<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
	</c:when>
</c:choose>

</form>