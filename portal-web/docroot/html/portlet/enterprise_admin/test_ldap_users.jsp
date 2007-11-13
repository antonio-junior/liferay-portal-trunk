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

<%@ include file="/html/portlet/enterprise_admin/init.jsp" %>

<%
LdapContext ctx = PortalLDAPUtil.getContext(themeDisplay.getCompanyId());

if (ctx == null) {
%>

	<liferay-ui:message key="liferay-has-failed-to-connect-to-the-ldap-server" />

<%
	return;
}

NamingEnumeration enu = PortalLDAPUtil.getUsers(themeDisplay.getCompanyId(), ctx);

Properties userMappings = PortalLDAPUtil.getUserMappings(themeDisplay.getCompanyId());
%>

<liferay-ui:message key="test-ldap-users" />

<br /><br />

A small list of users has been displayed for your to review.

<br /><br />

<table class="liferay-table">

<%
int counter = 0;

while (enu.hasMore()) {
	SearchResult result = (SearchResult)enu.next();

	Attributes attrs = result.getAttributes();

	String screenName = LDAPUtil.getAttributeValue(attrs, userMappings.getProperty("screenName")).toLowerCase();
	String emailAddress = LDAPUtil.getAttributeValue(attrs, userMappings.getProperty("emailAddress"));
	String firstName = LDAPUtil.getAttributeValue(attrs, userMappings.getProperty("firstName"));
	String lastName = LDAPUtil.getAttributeValue(attrs, userMappings.getProperty("lastName"));
	String jobTitle = LDAPUtil.getAttributeValue(attrs, userMappings.getProperty("jobTitle"));
	Attribute attribute = attrs.get(userMappings.getProperty("group"));

	if (counter == 0) {
%>

		<tr>
			<th>
				#
			</th>
			<th>
				Screen Name
			</th>
			<th>
				Email Address
			</th>
			<th>
				First Name
			</th>
			<th>
				Last Name
			</th>
			<th>
				Job Title
			</th>
			<th>
				Group
			</th>
		</tr>

<%
	}

	counter++;
%>

	<tr>
		<td>
			<%= counter %>
		</td>
		<td>
			<%= screenName %>
		</td>
		<td>
			<%= emailAddress %>
		</td>
		<td>
			<%= firstName %>
		</td>
		<td>
			<%= lastName %>
		</td>
		<td>
			<%= jobTitle %>
		</td>
		<td>
			<%= (attribute == null) ? "0" : attribute.size() %>
		</td>
	</tr>

<%
	if (counter == 20) {
		break;
	}
}

if (counter == 0) {
%>

	<tr>
		<td colspan="6">
			No Users Found
		</td>
	</tr>

<%
}
%>

</table>