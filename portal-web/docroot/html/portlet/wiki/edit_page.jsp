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

<liferay-util:include page="/html/portlet/wiki/node_tabs.jsp" />

<%
String redirect = ParamUtil.getString(request, "redirect");

WikiNode node = (WikiNode)request.getAttribute(WebKeys.WIKI_NODE);
WikiPage wikiPage = (WikiPage)request.getAttribute(WebKeys.WIKI_PAGE);

long nodeId = BeanParamUtil.getLong(wikiPage, request, "nodeId");
String title = BeanParamUtil.getString(wikiPage, request, "title");

String content = BeanParamUtil.getString(wikiPage, request, "content");
String format = BeanParamUtil.getString(wikiPage, request, "format", WikiPageImpl.DEFAULT_FORMAT);

boolean preview = ParamUtil.getBoolean(request, "preview");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("struts_config", "/wiki/view");
	portletURL.setParameter("nodeId", Long.toString(nodeId));
	portletURL.setParameter("title", title);

	redirect = portletURL.toString();
}

boolean edit = false;

if (wikiPage != null) {
	edit = true;
}
else if (Validator.isNotNull(title)) {
	try {
		WikiPageLocalServiceUtil.validateTitle(title);

		edit = true;
	}
	catch (PortalException pe) {
	}
}

PortletURL editPageURL = renderResponse.createRenderURL();

editPageURL.setWindowState(WindowState.MAXIMIZED);

editPageURL.setParameter("struts_action", "/wiki/edit_page");
editPageURL.setParameter("redirect", currentURL);
editPageURL.setParameter("nodeId", String.valueOf(node.getNodeId()));
editPageURL.setParameter("title", title);
%>

<%@ include file="/html/portlet/wiki/page_name.jspf" %>

<c:if test="<%= preview %>">
	<liferay-ui:message key="preview" />:

	<div class="preview">
		<%@ include file="/html/portlet/wiki/view_page_content.jspf" %>
	</div>

	<br />
</c:if>

<script type="text/javascript">
	function <portlet:namespace />changeFormat(formatSel) {
		if (window.<portlet:namespace />editor) {
			document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
		}

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />previewPage() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "";
		document.<portlet:namespace />fm.<portlet:namespace />preview.value = 'true';

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />savePage() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.UPDATE %>";

		if (window.<portlet:namespace />editor) {
			document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
		}

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />savePageAndContinue() {
		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = "<%= editPageURL %>";

		<portlet:namespace />savePage();
	}
</script>

<form action="<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/wiki/edit_page" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />savePage(); return false;">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
<input name="<portlet:namespace />redirect" type="hidden" value="<%= redirect %>" />
<input name="<portlet:namespace />nodeId" type="hidden" value="<%= nodeId %>" />
<input name="<portlet:namespace />title" type="hidden" value="<%= title %>" />
<input name="<portlet:namespace />preview" type="hidden" value="" />

<liferay-ui:tags-error />

<c:if test="<%= (wikiPage == null) && edit %>">
	<div class="portlet-msg-info">
		<liferay-ui:message key="this-page-does-not-exist-yet-use-the-form-below-to-create-it" />
	</div>
</c:if>

<c:if test="<%= (wikiPage == null) && !edit %>">
	<div class="portlet-msg-error">
		<liferay-ui:message key="this-page-does-not-exist-yet-and-the-title-is-not-valid" />
	</div>

	<input type="button" onclick="history.go(-1)" value="<%= LanguageUtil.get(pageContext, "back") %>" />
</c:if>

<c:if test="<%= edit %>">
	<c:if test="<%= (WikiPageImpl.FORMATS.length > 1) %>">
		<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="format" />
			</td>
			<td>
				<select name="<portlet:namespace />format" onChange="<portlet:namespace />changeFormat(this);">

					<%
					for (int i = 0; i < WikiPageImpl.FORMATS.length; i++) {
					%>

						<option <%= format.equals(WikiPageImpl.FORMATS[i]) ? "selected" : "" %> value="<%= WikiPageImpl.FORMATS[i] %>"><%= LanguageUtil.get(pageContext, "wiki.formats." + WikiPageImpl.FORMATS[i]) %></option>

					<%
					}
					%>

				</select>
			</td>
		</tr>
		</table>
	</c:if>

	<br />

	<div>

		<%
		request.setAttribute("edit_page.jsp-wikiPage", wikiPage);
		%>

		<liferay-util:include page="<%= WikiUtil.getEditPage(format) %>" />
	</div>

	<br />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="tags" />
		</td>
		<td>

			<%
			long classPK = 0;

			if (wikiPage != null) {
				classPK = wikiPage.getResourcePrimKey();
			}
			%>

			<liferay-ui:tags-selector
				className="<%= WikiPage.class.getName() %>"
				classPK="<%= classPK %>"
				hiddenInput="tagsEntries"
			/>
		</td>
	</tr>
	</table>

	<br />

	<input type="submit" value="<liferay-ui:message key="save" />" />

	<input type="button" value="<liferay-ui:message key="save-and-continue" />" onClick="<portlet:namespace />savePageAndContinue();"  />

	<input type="button" value="<liferay-ui:message key="preview" />" onClick="<portlet:namespace />previewPage();" />

	<input type="button" value="<liferay-ui:message key="cancel" />" onClick="document.location = '<%= redirect %>'" />

	</form>

	<c:if test="<%= !preview %>">
		<script type="text/javascript">
			if (!window.<portlet:namespace />editor) {
				Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />content);
			}
		</script>
	</c:if>
</c:if>