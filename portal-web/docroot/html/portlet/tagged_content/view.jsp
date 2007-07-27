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

<%@ include file="/html/portlet/tagged_content/init.jsp" %>

<%

// Merge URL tags

String[] compilerEntries = (String[])request.getAttribute(WebKeys.TAGS_COMPILER_ENTRIES);

if ((compilerEntries != null) && (compilerEntries.length > 0)) {
	String[] newEntries = ArrayUtil.append(entries, compilerEntries);

	entries = newEntries;
}

if (themeDisplay.isSignedIn()) {

	// Merge my global tags

	PortalPreferences myGlobalPrefs = PortletPreferencesFactory.getPortalPreferences(request);

	String[] myGlobalEntries = myGlobalPrefs.getValues(PortletKeys.MY_GLOBAL_TAGS, "entries", new String[0]);

	if ((myGlobalEntries != null) && (myGlobalEntries.length > 0)) {
		String[] newEntries = ArrayUtil.append(entries, myGlobalEntries);

		entries = newEntries;
	}

	// Merge my community tags

	PortletPreferences myCommunityPrefs = PortletPreferencesFactory.getPortletPreferences(request, PortletKeys.MY_COMMUNITY_TAGS);

	String[] myCommunityEntries = myCommunityPrefs.getValues("entries", new String[0]);

	if ((myCommunityEntries != null) && (myCommunityEntries.length > 0)) {
		String[] newEntries = ArrayUtil.append(entries, myCommunityEntries);

		entries = newEntries;
	}
}

%>

<liferay-ui:message key="tags" />: 

<% 
if (entries.length > 0) {
	%>( <%
}

for (int k = 0; k < entries.length; k++) {
	if (k+1 == entries.length) {
		%><%= entries[k] %> )<%
	}
	else {
		%>
		<c:if test="<%= andOperator%>">
			<%= entries[k] %> AND
		</c:if>
		<c:if test="<%= !andOperator%>">
			<%= entries[k] %> OR
		</c:if>
		<%
	}
}

if (entries.length > 0 && notEntries.length > 0) {
	%> AND NOT( <%
}

for (int k = 0; k < notEntries.length; k++) {
	if (k+1 == notEntries.length) {
		%><%= notEntries[k] %> )<%
	}
	else {
		%>
			<%= notEntries[k] %> OR
		<%
	}
}
%>

<%

// Display content

PortletURL portletURL = renderResponse.createRenderURL();

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, null, null);

long[] entryIds = TagsEntryLocalServiceUtil.getEntryIds(company.getCompanyId(), entries);
long[] notEntryIds = TagsEntryLocalServiceUtil.getEntryIds(company.getCompanyId(), notEntries);

int total = TagsAssetLocalServiceUtil.getAssetsCount(entryIds, notEntryIds, andOperator);

searchContainer.setTotal(total);

List results = TagsAssetLocalServiceUtil.getAssets(entryIds, notEntryIds, andOperator, searchContainer.getStart(), searchContainer.getEnd());

searchContainer.setResults(results);


for (int i = 0; i < results.size(); i++) {
	TagsAsset asset = (TagsAsset)results.get(i);
	
	String className = PortalUtil.getClassName(asset.getClassNameId());
	long classPK = asset.getClassPK();
%>

	<div class="separator"></div>

	<div>
		<c:choose>
			<c:when test="<%= display.equals("full-content") %>">
				<%@ include file="/html/portlet/tagged_content/display_full_content.jspf" %>
			</c:when>
			<c:when test="<%= display.equals("abstracts") %>">
				<%@ include file="/html/portlet/tagged_content/display_abstract.jspf" %>
			</c:when>
			<c:otherwise>
				<%= display %> is not a display type.
			</c:otherwise>
		</c:choose>
	</div>

<%
}
%>

<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />