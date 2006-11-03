<%
/**
 * Copyright (c) 2000-2006 Liferay, LLC. All rights reserved.
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

<%@ include file="/html/portlet/quick_note/init.jsp" %>

<div id="<portlet:namespace />note" style="background: #FFFFE0; padding: 5px;"><%= data %></div>

<script type="text/javascript">
	QuickEdit.create(
		"<portlet:namespace />note",
		{
			inputType: "textarea",
			onEdit:
				function(input, textWidth, textHeight) {
					textHeight += 5;

					if (textHeight < 50) {
						textHeight = 50;
					}

					input.style.height = (textHeight) + "px";
					input.style.width = "100%";
					input.style.padding = "5px";
				},
			onComplete:
				function(newTextObj, oldText) {
					if (newTextObj.offsetHeight < 10) {
						newTextObj.style.height = "15px";
					}

					var newText = newTextObj.innerHTML;

					if (oldText != newText) {
						var url =
							"<%= themeDisplay.getPathMain() %>/quick_note/save?" +
							"&plid=<%= plid %>" +
							"&portletId=<%= portletDisplay.getId() %>" +
							"&data=" + encodeURIComponent(newText);

						Ajax.request(url);
					}
			}
		});
</script>