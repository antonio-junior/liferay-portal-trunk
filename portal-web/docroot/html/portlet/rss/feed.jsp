<%
/**
 * Copyright (c) 2000-2006 Liferay, Inc. All rights reserved.
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

<%
try {
	ObjectValuePair ovp = RSSUtil.getFeed(url);

	feed = (SyndFeed)ovp.getValue();

	if (Validator.isNull(title)) {
		title = feed.getTitle();
	}
}
catch (Exception e) {
}
%>


<c:choose>
	<c:when test="<%= (url != null) && (feed != null) %>">
		<div class="portlet-rss-header" style="font-weight: bold; background-color: <%= colorScheme.getPortletSectionHeaderBg() %>; padding: 2px 5px 2px 5px; cursor: default"
			<c:if test="<%= !renderRequest.getWindowState().equals(WindowState.MAXIMIZED) %>">
				 onclick="<portlet:namespace />rssAccordion.show(this)"
			</c:if>
		>
			<%= title %>&nbsp;&nbsp;
			<a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/rss/view" /><portlet:param name="url" value="<%= url %>" /></portlet:renderURL>" style="font-weight: normal">
				more &raquo;
			</a>
		</div>
		<div class="portlet-rss-content" style="overflow: hidden; <%= hide ? "height: 1px" : "" %>">
			<div style="padding: 0 10px 0 10px">
				<div style="font-size: 0; height: 10px;"></div>

				<%
				List entries = feed.getEntries();

				for (int j = 0; j < entries.size(); j++) {
					SyndEntry entry = (SyndEntry)entries.get(j);

					SyndContent content = entry.getDescription();
					%>

					<c:choose>
						<c:when test="<%= renderRequest.getWindowState().equals(WindowState.MAXIMIZED) %>">
								<a style="font-weight: bold" href="<%= entry.getLink() %>" target="_blank"><%= entry.getTitle() %></a><br />
								<c:if test="<%= entry.getPublishedDate() != null %>">
									<%= dateFormatDateTime.format(entry.getPublishedDate()) %><br />
								</c:if>

								<div class="font-small">
									<%= content.getValue() %>
								</div>

								<c:if test="<%= ((i + 1) < urls.length) || ((j + 1) < entriesPerFeed) %>">
									<div style="font-size: 0; height: 10px;"></div>
								</c:if>
						</c:when>
						<c:otherwise>
							<a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>">
									<portlet:param name="struts_action" value="/rss/article" />
									<portlet:param name="index" value='<%= j + "" %>' />
									<portlet:param name="url" value="<%= url %>" />
								</portlet:renderURL>"><%= entry.getTitle() %></a><br />
						</c:otherwise>
					</c:choose>

					<%
					if ((j + 1) >= entriesPerFeed) {
						break;
					}
				}
				%>
				<div style="font-size: 0; height: 10px;"></div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-error">
			<%= LanguageUtil.format(pageContext, "cannot-be-found", urls[i], false) %>
		</div>
	</c:otherwise>
</c:choose>