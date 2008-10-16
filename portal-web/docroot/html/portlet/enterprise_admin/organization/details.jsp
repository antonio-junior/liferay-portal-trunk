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

<%@ include file="/html/portlet/enterprise_admin/init.jsp" %>

<%
Organization organization = (Organization)request.getAttribute("organization.selOrganization");

long parentOrganizationId = BeanParamUtil.getLong(organization, request, "parentOrganizationId");

String parentOrganizationName = ParamUtil.getString(request, "parentOrganizationName");

if (parentOrganizationId <= 0) {
	parentOrganizationId = OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID;

	if (organization != null) {
		parentOrganizationId = organization.getParentOrganizationId();
	}
}

String type = BeanParamUtil.getString(organization, request, "type", PropsValues.ORGANIZATIONS_TYPES[0]);
long regionId = BeanParamUtil.getLong(organization, request, "regionId");
long countryId = BeanParamUtil.getLong(organization, request, "countryId");
int statusId = BeanParamUtil.getInteger(organization, request, "statusId");
%>

<h3><liferay-ui:message key="details" /></h3>

<%
boolean ListError = false;
if (SessionErrors.contains(renderRequest, DuplicateOrganizationException.class.getName()) ||
	SessionErrors.contains(renderRequest, OrganizationNameException.class.getName()) ||
	SessionErrors.contains(renderRequest, NoSuchCountryException.class.getName()) ||
	SessionErrors.contains(renderRequest, OrganizationParentException.class.getName())){

	request.setAttribute("organization.errorSection", "details");
}
else if(SessionErrors.contains(renderRequest, NoSuchListTypeException.class.getName())){
	NoSuchListTypeException e = (NoSuchListTypeException)SessionErrors.get(renderRequest, NoSuchListTypeException.class.getName() );
	if (Validator.equals(e.getType(), Organization.class.getName()+ListTypeImpl.ORGANIZATION_STATUS)){

		request.setAttribute("organization.errorSection", "details");
		ListError = true;
	}
}
%>

<fieldset class="block-labels col">

	<liferay-ui:error exception="<%= DuplicateOrganizationException.class %>" message="the-organization-name-is-already-taken" />
	<liferay-ui:error exception="<%= OrganizationNameException.class %>" message="please-enter-a-valid-name" />

	<div class="ctrl-holder">
		<label for="<portlet:namespace />name"><liferay-ui:message key="name" /></label>

		<liferay-ui:input-field model="<%= Organization.class %>" bean="<%= organization %>" field="name" />
	</div>

	<div class="ctrl-holder">
		<label for="<portlet:namespace />type"><liferay-ui:message key="type" /></label>

		<c:choose>
			<c:when test="<%= organization == null %>">
				<select id="<portlet:namespace />type" name="<portlet:namespace />type">

					<%
					for (String curType : PropsValues.ORGANIZATIONS_TYPES) {
					%>

						<option <%= type.equals(curType) ? "selected" : "" %> value="<%= curType %>"><liferay-ui:message key="<%= curType %>" /></option>

					<%
					}
					%>

				</select>
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="<%= organization.getType() %>" />

				<input name="<portlet:namespace />type" type="hidden" value="<%= organization.getType() %>" />
			</c:otherwise>
		</c:choose>
	</div>

	<c:if test="<%= organization != null %>">
		<div class="ctrl-holder">
			<label for="<portlet:namespace />groupId"><liferay-ui:message key="group-id" /></label>

			<%= organization.getGroup().getGroupId() %>
		</div>
	</c:if>
</fieldset>

<fieldset class="block-labels col">
	<c:choose>
		<c:when test="<%= PropsValues.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_ORGANIZATION_STATUS %>">

			<c:if test="<%= ListError %>">
				<liferay-ui:error exception="<%= NoSuchListTypeException.class %>" message="please-select-a-valid-value-from-the-list" />
			</c:if>

			<div class="ctrl-holder">
				<label for="<portlet:namespace />statusId"><liferay-ui:message key="status" /></label>

				<select name="<portlet:namespace />statusId">
					<option value=""></option>

					<%
					List<ListType> statuses = ListTypeServiceUtil.getListTypes(ListTypeImpl.ORGANIZATION_STATUS);

					for (ListType status : statuses) {
					%>

						<option <%= (status.getListTypeId() == statusId) ? "selected" : "" %> value="<%= status.getListTypeId() %>"><liferay-ui:message key="<%= status.getName() %>" /></option>

					<%
					}
					%>

				</select>
			</div>
		</c:when>
		<c:otherwise>
			<input name="<portlet:namespace />statusId" type="hidden" value="<%= (organization != null) ? organization.getStatusId() : ListTypeImpl.ORGANIZATION_STATUS_DEFAULT %>" />
		</c:otherwise>
	</c:choose>

	<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-country" />
	<div id="<portlet:namespace />countryDiv" <%= GetterUtil.getBoolean(PropsUtil.get(PropsKeys.ORGANIZATIONS_COUNTRY_ENABLED, new Filter(String.valueOf(type))))? StringPool.BLANK : "style=\"display: none;\"" %>>
		<div class="ctrl-holder">
			<label for="<portlet:namespace />countryId"><liferay-ui:message key="country" /> </label>

			<select id="<portlet:namespace />countryId" name="<portlet:namespace />countryId"></select>
		</div>

		<div class="ctrl-holder">
			<label for="<portlet:namespace />regionId"><liferay-ui:message key="region" /></label>

			<select id="<portlet:namespace />regionId" name="<portlet:namespace />regionId"></select>
		</div>
	</div>
</fieldset>

<h3><liferay-ui:message key="parent-organization" /></h3>

<%
Organization parentOrganization = null;

if (parentOrganizationId != OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) {
	try {
		parentOrganization = OrganizationLocalServiceUtil.getOrganization(parentOrganizationId);

		parentOrganizationName = parentOrganization.getName();
	}
	catch (NoSuchOrganizationException nsoe) {
	}
}

List<Organization> parentOrganizations = new ArrayList<Organization>();

if (parentOrganization != null) {
	parentOrganizations.add(parentOrganization);
}
%>

<liferay-ui:error exception="<%= OrganizationParentException.class %>" message="please-enter-a-valid-parent" />

<input name="<portlet:namespace />parentOrganizationId" type="hidden" value="<%= parentOrganizationId %>" />

<liferay-ui:search-container
	id="parentOrganizationHTML"
>
	<liferay-ui:search-container-results
		results="<%= parentOrganizations %>"
		total="<%= parentOrganizations.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.Organization"
		keyProperty="organizationId"
		modelVar="curOrganization"
	>
		<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="rowURL">
			<portlet:param name="struts_action" value="/enterprise_admin/edit_organization" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="organizationId" value="<%= String.valueOf(curOrganization.getOrganizationId()) %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="name"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="type"
			value="<%= LanguageUtil.get(pageContext, curOrganization.getType()) %>"
		/>

		<liferay-ui:search-container-column-text>
			<a href="javascript: ;" onclick="Liferay.SearchContainer.deleteRow(this);">X</a>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<c:if test="<%= parentOrganizations.size() > 0 %>">
	<br />
</c:if>

<input type="button" value="<liferay-ui:message key="select" />" onClick="<portlet:namespace />openOrganizationSelector();" />

<script type="text/javascript">
	function <portlet:namespace />openOrganizationSelector() {
		var url = '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value="/enterprise_admin/select_organization" /><portlet:param name="tabs1" value="organizations" /><portlet:param name="parentType" value="" /></portlet:renderURL>';

		<c:choose>
			<c:when test="<%= organization == null %>">
				var type = document.<portlet:namespace />fm.<portlet:namespace />type.value;
			</c:when>
			<c:otherwise>
				var type = '<%= type %>';
			</c:otherwise>
		</c:choose>

		url = Liferay.Util.addParams('childType=' + type, url);

		var organizationWindow = window.open(url, 'organization', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680');

		organizationWindow.focus();
	}

	function <portlet:namespace />removeOrganization() {
		document.<portlet:namespace />fm.<portlet:namespace />parentOrganizationId.value = "";

		var nameEl = document.getElementById("<portlet:namespace />parentOrganizationHTML");

		nameEl.href = "#";

		var parentOrganizationHTML = '';

		parentOrganizationHTML += '<tr class="portlet-section-body results-row">';
		parentOrganizationHTML += '<td align="center" class="col-1" colspan="3" valign="middle"><liferay-ui:message key="this-organization-does-not-have-a-parent" /></td></tr>';

		nameEl.innerHTML = parentOrganizationHTML;

		document.getElementById("<portlet:namespace />removeOrganizationButton").disabled = true;
	}

	function <portlet:namespace />saveOrganization(cmd) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= organization == null ? Constants.ADD : Constants.UPDATE %>";
		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />selectOrganization(organizationId, name, type) {
		var createUrl = function(href, value, onClick) {
			var anchorText = '<a href="' + href + '"' + (onClick ? ' onclick="' + onClick + '" ' : '') + '>' + value + '</a>';

			return anchorText;
		};

		var href = "<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/enterprise_admin/edit_organization" /><portlet:param name="backURL" value="<%= currentURL %>" /></portlet:renderURL>&<portlet:namespace />organizationId=" + organizationId;

		var rowColumns = [];

		rowColumns.push(createUrl(href, name));
		rowColumns.push(createUrl(href, Liferay.Language.get(type)));
		rowColumns.push(createUrl('javascript: ;', 'X', 'Liferay.SearchContainer.deleteRow(this)'));

		var searchContainer = Liferay.SearchContainer.get('parentOrganizationHTML');

		searchContainer.addRow(rowColumns);
		searchContainer.deleteRow(1);
		searchContainer.updateDataStore(organizationId);
	}

	<c:if test="<%= organization == null %>">
		jQuery('#<portlet:namespace />type').change(
			function(event) {

				<%
				for (String curType : PropsValues.ORGANIZATIONS_TYPES) {
				%>

					if (this.value == '<%= curType %>') {
						jQuery('#<portlet:namespace />countryDiv').<%= GetterUtil.getBoolean(PropsUtil.get(PropsKeys.ORGANIZATIONS_COUNTRY_ENABLED, new Filter(String.valueOf(curType)))) ? "show" : "hide" %>();
					}

				<%
				}
				%>

			}
		);
	</c:if>

	new Liferay.DynamicSelect(
		[
			{
				select: "<portlet:namespace />countryId",
				selectId: "countryId",
				selectDesc: "name",
				selectVal: "<%= countryId %>",
				selectData: function(callback) {
					Liferay.Service.Portal.Country.getCountries(
						{
							active: true
						},
						callback
					);
				}
			},
			{
				select: "<portlet:namespace />regionId",
				selectId: "regionId",
				selectDesc: "name",
				selectVal: "<%= regionId %>",
				selectData: function(callback, selectKey) {
					Liferay.Service.Portal.Region.getRegions(
						{
							countryId: selectKey,
							active: true
						},
						callback
					);
				}
			}
		]
	);
</script>