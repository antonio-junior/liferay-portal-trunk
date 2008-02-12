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

<%@ include file="/html/portlet/wiki/init.jsp" %>

<%
WikiNode node = (WikiNode)request.getAttribute(WebKeys.WIKI_NODE);
WikiPage wikiPage = (WikiPage)request.getAttribute(WebKeys.WIKI_PAGE);

WikiPageResource wikiPageResource = null;

String[] fileNames = null;

if (wikiPage != null) {
	wikiPageResource = wikiPage.getWikiPageResource();
	fileNames = wikiPageResource.getAttachmentFileNames();
}

if (fileNames == null) {
	fileNames = new String[0];
}

WikiPage originalPage = null;

if (wikiPage.getRedirectToPage() != null) {
	originalPage = wikiPage;
	wikiPage = wikiPage.getRedirectToPage();
}

String title = wikiPage.getTitle();

boolean print = ParamUtil.getBoolean(request, Constants.PRINT);

PortletURL viewPageURL = renderResponse.createRenderURL();

viewPageURL.setParameter("struts_action", "/wiki/view");
viewPageURL.setParameter("nodeId", String.valueOf(node.getNodeId()));
viewPageURL.setParameter("title", title);

PortletURL editPageURL = PortletURLUtil.clone(viewPageURL, renderResponse);

editPageURL.setParameter("struts_action", "/wiki/edit_page");
editPageURL.setParameter("redirect", currentURL);

PortletURL printPageURL = PortletURLUtil.clone(viewPageURL, renderResponse);

printPageURL.setWindowState(LiferayWindowState.POP_UP);

printPageURL.setParameter("print", "true");
%>

<c:choose>
	<c:when test="<%= print %>">
		<script type="text/javascript">
			print();
		</script>

		<div class="wiki-popup-print">
			<liferay-ui:icon image="print" message="print" url="javascript: print();"/>
		</div>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			function <portlet:namespace />printPage() {
				window.open('<%= printPageURL %>', '', "directories=0,height=480,left=80,location=1,menubar=1,resizable=1,scrollbars=yes,status=0,toolbar=0,top=180,width=640");
			}
		</script>
	</c:otherwise>
</c:choose>

<liferay-util:include page="/html/portlet/wiki/top_links.jsp" />

<h1 class="wiki-page-title">
	<c:if test="<%= !print %>">
		<div class="wiki-page-actions">
			<c:if test="<%= WikiPagePermission.contains(permissionChecker, wikiPage, ActionKeys.UPDATE) %>">
				<liferay-ui:icon image="edit" url="<%= editPageURL.toString() %>" />
			</c:if>

			<liferay-ui:icon image="print" message="print" url='<%= "javascript: " + renderResponse.getNamespace() + "printPage();" %>' />

			<c:if test="<%= portletName.equals(PortletKeys.WIKI) %>">

				<%
				PortletURL viewPageHistoryURL = PortletURLUtil.clone(viewPageURL, renderResponse);

				viewPageHistoryURL.setParameter("struts_action", "/wiki/view_page_info");
				%>

				<liferay-ui:icon image="history" message="info-history-links-and-attachments" url="<%= viewPageHistoryURL.toString() %>" />
			</c:if>
		</div>
	</c:if>

	<%= title %>
</h1>

<br />

<c:if test="<%= originalPage != null %>">
	<div class="wiki-page-redirect">
		(<%= LanguageUtil.format(pageContext, "redirected-from-x", originalPage.getTitle()) %>)
	</div>
</c:if>

<c:if test="<%= !wikiPage.isHead() %>">
	<div class="wiki-page-old-version">
		(<liferay-ui:message key="you-are-viewing-an-archived-version-of-this-page" /> (<%= wikiPage.getVersion() %>), <a href="<%= viewPageURL %>"><liferay-ui:message key="go-to-the-latest-version" /></a>)
	</div>
</c:if>

<liferay-ui:tags-summary
	className="<%= WikiPage.class.getName() %>"
	classPK="<%= wikiPage.getResourcePrimKey() %>"
/>

<div>
	<%@ include file="/html/portlet/wiki/view_page_content.jspf" %>
</div>


<c:if test="<%= fileNames.length > 0 %>">
	<%
	PortletURL viewAttachmentsURL = PortletURLUtil.clone(viewPageURL, renderResponse);
	viewAttachmentsURL.setParameter("struts_action", "/wiki/view_page_attachments");
	%>
	<img align="absmiddle" src="<%= themeDisplay.getPathThemeImages() %>/common/clip.png"/><a href="<%= viewAttachmentsURL.toString() %>"><%= LanguageUtil.format(pageContext, "x-attachments", fileNames.length) %></a>

	<br/>
</c:if>

<c:if test="<%= WikiPagePermission.contains(permissionChecker, wikiPage, ActionKeys.ADD_DISCUSSION) %>">
	<c:if test="<%= Validator.isNotNull(pageContent) %>">
		<br />
	</c:if>

	<liferay-ui:tabs names="comments" />

	<portlet:actionURL var="discussionURL">
		<portlet:param name="struts_action" value="/wiki/edit_page_discussion" />
	</portlet:actionURL>

	<liferay-ui:discussion
		formName="fm2"
		formAction="<%= discussionURL %>"
		className="<%= WikiPage.class.getName() %>"
		classPK="<%= wikiPage.getResourcePrimKey() %>"
		userId="<%= wikiPage.getUserId() %>"
		subject="<%= wikiPage.getTitle() %>"
		redirect="<%= currentURL %>"
	/>
</c:if>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fmSearch.<portlet:namespace />keywords);
	</script>
</c:if>