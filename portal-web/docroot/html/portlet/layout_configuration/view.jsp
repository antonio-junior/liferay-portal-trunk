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

<%@ include file="/html/portlet/layout_configuration/init.jsp" %>

<c:if test="<%= themeDisplay.isSignedIn() && (layout != null) && layout.getType().equals(Layout.TYPE_PORTLET) %>">

	<%
	PortletURL refererURL = renderResponse.createActionURL();

	refererURL.setParameter("updateLayout", "true");
	%>

	<div id="portal_add_content">
		<div class="portal-add-content">
			<form action="<%= themeDisplay.getPathMain() %>/portal/update_layout?p_l_id=<%= plid %>&cmd=template&refresh=true" method="post" name="<portlet:namespace />fm">
			<input name="<%= WebKeys.REFERER %>" type="hidden" value="<%= refererURL.toString() %>">

			<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<div>
						<span id="portal_add_content_title" style="margin-right: 5px; cursor: move;"><%= LanguageUtil.get(pageContext, "content") %></span>

						<input class="form-text" id="layout_configuration_content" type="text" onKeyPress="if (event.keyCode == 13) { return false; }" onKeyUp="LayoutConfiguration.startShowTimer(this.value);" />
					</div>

					<br><br>

					<%
					PortletCategory portletCategory = (PortletCategory)WebAppPool.get(company.getCompanyId(), WebKeys.PORTLET_CATEGORY);

					List categories = ListUtil.fromCollection(portletCategory.getCategories());

					Collections.sort(categories, new PortletCategoryComparator(company.getCompanyId(), locale));

					Iterator itr = categories.iterator();

					while (itr.hasNext()) {
						request.setAttribute(WebKeys.PORTLET_CATEGORY, itr.next());
					%>

						<liferay-util:include page="/html/portlet/layout_configuration/view_category.jsp" />

					<%
					}
					%>

					<br>

					<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<b><%= LanguageUtil.get(pageContext, "template") %></b>
						</td>
						<td style="padding-left: 10px;"></td>
						<td>

							<%
							List layoutTemplates = LayoutTemplateLocalUtil.getLayoutTemplates();
							%>

							<select name="layoutTemplateId" onChange="submitForm(document.<portlet:namespace />fm);">

								<%
								Group group = layout.getGroup();

								String selector1 = StringPool.BLANK;

								if (group.isUser()) {
									selector1 = "desktop";
								}
								else if (group.isCommunity()) {
									selector1 = "community";
								}
								else if (group.isOrganization()) {
									selector1 = "organization";
								}

								String selector2 = StringPool.BLANK;

								if ((layout.getPriority() == 0) && (layout.getParentLayoutId().equals(Layout.DEFAULT_PARENT_LAYOUT_ID))) {
									selector2 = "firstLayout";
								}

								String[] restrictedTemplates = PropsUtil.getComponentProperties().getStringArray(PropsUtil.LAYOUT_TEMPLATE_RESTRICTIONS, Filter.by(selector1, selector2));

								for (int i = 0; i < layoutTemplates.size(); i++) {
									LayoutTemplate layoutTemplate = (LayoutTemplate)layoutTemplates.get(i);

									String layoutTemplateId = layoutTemplate.getLayoutTemplateId();

									boolean restrictedTemplate = false;

									for (int j = 0; j < restrictedTemplates.length; j++) {
										if (layoutTemplateId.equals(restrictedTemplates[j])) {
											restrictedTemplate = true;

											break;
										}
									}

									if (!restrictedTemplate) {
									%>

										<option <%= layoutTypePortlet.getLayoutTemplateId().equals(layoutTemplate.getLayoutTemplateId()) ? "selected" : "" %> value="<%= layoutTemplate.getLayoutTemplateId() %>"><%= layoutTemplate.getName() %></option>

									<%
									}
								}
								%>

							</select>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			</table>

			</form>
		</div>
	</div>
</c:if>