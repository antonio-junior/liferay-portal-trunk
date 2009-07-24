<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
String baseProviderURL = ParamUtil.getString(request, "baseProviderURL");
String baseDN = ParamUtil.getString(request, "baseDN");
String principal = ParamUtil.getString(request, "principal");
String credentials = ParamUtil.getString(request, "credentials");

LdapContext ldapContext = PortalLDAPUtil.getContext(themeDisplay.getCompanyId(), baseProviderURL, principal, credentials);

if (ldapContext == null) {
%>

	<liferay-ui:message key="liferay-has-failed-to-connect-to-the-ldap-server" />

<%
	return;
}

if (Validator.isNull(ParamUtil.getString(request, "groupMappingGroupName")) ||
	Validator.isNull(ParamUtil.getString(request, "groupMappingUser"))) {
%>

	<liferay-ui:message key="please-map-each-of-the-group-properties-group-name-and-user-to-an-ldap-attribute" />

<%
	return;
}

String groupFilter = ParamUtil.getString(request, "importGroupSearchFilter");

List<SearchResult> results = PortalLDAPUtil.getGroups(themeDisplay.getCompanyId(), ldapContext, 20, baseDN, groupFilter);

String groupMappingsParam =
	"groupName=" + ParamUtil.getString(request, "groupMappingGroupName") +
	"\ndescription=" + ParamUtil.getString(request, "groupMappingDescription") +
	"\nuser=" + ParamUtil.getString(request, "groupMappingUser");

Properties groupMappings = PropertiesUtil.load(groupMappingsParam);
%>

<liferay-ui:message key="test-ldap-groups" />

<br /><br />

<liferay-ui:message key="a-subset-of-groups-has-been-displayed-for-you-to-review" />

<br /><br />

<table class="lfr-table">

<%
boolean showMissingAttributeMessage = false;

int counter = 0;

for (SearchResult result : results) {
	Attributes attrs = result.getAttributes();

	String name = LDAPUtil.getAttributeValue(attrs, groupMappings.getProperty("groupName")).toLowerCase();
	String description = LDAPUtil.getAttributeValue(attrs, groupMappings.getProperty("description"));
	Attribute attribute = attrs.get(groupMappings.getProperty("user"));

	if (Validator.isNull(name)) {
		showMissingAttributeMessage = true;
	}

	if (attribute != null) {
		StringBuilder sb = new StringBuilder();

		sb.append("(&");
		sb.append(groupFilter);
		sb.append("(");
		sb.append(groupMappings.getProperty("groupName"));
		sb.append("=");
		sb.append(name);
		sb.append("))");

		String filter = sb.toString();

		attribute = PortalLDAPUtil.getMultivaluedAttribute(themeDisplay.getCompanyId(), ldapContext, baseDN, filter, attribute);
	}

	if (counter == 0) {
%>

		<tr>
			<th>
				#
			</th>
			<th>
				<liferay-ui:message key="name" />
			</th>
			<th>
				<liferay-ui:message key="description" />
			</th>
			<th>
				<liferay-ui:message key="members" />
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
			<%= name %>
		</td>
		<td>
			<%= description %>
		</td>
		<td>
			<%= (attribute == null) ? "0" : String.valueOf(attribute.size()) %>
		</td>
	</tr>

<%
}

if (counter == 0) {
%>

	<tr>
		<td colspan="4">
			<liferay-ui:message key="no-groups-were-found" />
		</td>
	</tr>

<%
}
%>

</table>

<%
if (showMissingAttributeMessage) {
%>

	<div class="portlet-msg-info">
		<liferay-ui:message key="the-above-results-include-groups-which-are-missing-the-required-attributes-(group-name-and-user).-these-groups-will-not-be-imported-until-these-attributes-are-filled-in" />
	</div>

<%
}

if (ldapContext != null) {
	ldapContext.close();
}
%>