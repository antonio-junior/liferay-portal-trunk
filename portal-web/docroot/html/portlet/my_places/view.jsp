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

<%@ include file="/html/portlet/my_places/init.jsp" %>

<c:if test="<%= themeDisplay.isSignedIn() %>">

	<%
	PortletURL portletURL = renderResponse.createActionURL();
	portletURL.setParameter("struts_action", "/my_places/view");

	String displayTitle = null;
	String selectedStyle = "style=\"background: " + colorScheme.getPortletMenuBg() + "; color: " + colorScheme.getPortletMenuText() + ";\" ";
	String selectedTitle = "";
	boolean selectedPlace = false;

	Group userGroup = user.getGroup();

	List links = new ArrayList();
	List titles = new ArrayList();

	if ((userGroup != null) && user.hasPublicLayouts()) {
		selectedPlace = !layout.isPrivateLayout() && (layout.getGroupId() == userGroup.getGroupId());
		displayTitle =  contact.getFullName() + " (" + LanguageUtil.get(pageContext, "public") + ")";

		if (selectedPlace) {
			selectedTitle = displayTitle;
		}
		else {
			portletURL.setParameter("ownerId", LayoutImpl.PUBLIC + userGroup.getGroupId());

			links.add(portletURL.toString());
			titles.add(displayTitle);
		}
	}

	LinkedHashMap groupParams = new LinkedHashMap();

	groupParams.put("usersGroups", user.getUserId());
	groupParams.put("layoutSet", Boolean.FALSE);

	List communities = GroupLocalServiceUtil.search(company.getCompanyId(), null, null, groupParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

	for (int i = 0; i < communities.size(); i++) {
		Group community = (Group)communities.get(i);

		selectedPlace = !layout.isPrivateLayout() && (layout.getGroupId() == community.getGroupId());
		displayTitle =  community.getName() + " (" + LanguageUtil.get(pageContext, "public") + ")";

		if (selectedPlace) {
			selectedTitle = displayTitle;
		}
		else {
			portletURL.setParameter("ownerId", LayoutImpl.PUBLIC + community.getGroupId());

			links.add(portletURL.toString());
			titles.add(displayTitle);
		}
	}

	if ((userGroup != null) && user.hasPrivateLayouts()) {
		selectedPlace = layout.isPrivateLayout() && (layout.getGroupId() == userGroup.getGroupId());
		displayTitle =  contact.getFullName() + " (" + LanguageUtil.get(pageContext, "private") + ")";

		if (selectedPlace) {
			selectedTitle = displayTitle;
		}
		else {
			portletURL.setParameter("ownerId", LayoutImpl.PRIVATE + userGroup.getGroupId());

			links.add(portletURL.toString());
			titles.add(displayTitle);
		}
	}

	groupParams.put("layoutSet", Boolean.TRUE);

	communities = GroupLocalServiceUtil.search(company.getCompanyId(), null, null, groupParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

	for (int i = 0; i < communities.size(); i++) {
		Group community = (Group)communities.get(i);

		selectedPlace = layout.isPrivateLayout() && (layout.getGroupId() == community.getGroupId());
		displayTitle = community.getName() + " (" + LanguageUtil.get(pageContext, "private") + ")";

		if (selectedPlace) {
			selectedTitle = displayTitle;
		}
		else {
			portletURL.setParameter("ownerId", LayoutImpl.PRIVATE + community.getGroupId());

			links.add(portletURL.toString());
			titles.add(displayTitle);
		}
	}
	%>

	<table border="0" cellspacing="0" cellpadding="0" onclick="MyPlaces.show();">
	<tr>
		<td class="layout-my-places">
			<%= selectedTitle %>
		</td>
		<td class="layout-my-places-arrow">
			<img style="vertical-align: middle" src="<%= themeDisplay.getPathThemeImage() %>/arrows/01_down.gif" />
		</td>
	</tr>
	</table>

	<ul id="layout-my-places-menu" style="display: none">

		<%
		for (int i = 0; i < links.size(); i++) {
			%>

			<li onmouseover="this.style.backgroundColor='#cccccc'" onmouseout="this.style.backgroundColor='transparent'" onclick="window.location='<%= (String)(links.get(i)) %>'"><%= (String)(titles.get(i)) %></li>

			<%
		}
		%>

	</ul>

	<script type="text/javascript">
		var MyPlaces = {
			showing: false,

			show: function() {
				if (!MyPlaces.showing) {
					document.getElementById("layout-my-places-menu").style.display = "";
					setTimeout("document.onclick = function() { MyPlaces.hide(); }", 0);
					MyPlaces.showing = true;
				}
			},

			hide: function() {
				document.getElementById("layout-my-places-menu").style.display = "none";
				MyPlaces.showing = false;
				document.onclick = function() {};
			}
		}
	</script>
</c:if>