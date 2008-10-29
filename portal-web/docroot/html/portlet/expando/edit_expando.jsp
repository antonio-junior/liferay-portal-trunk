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

<%@ include file="/html/portlet/expando/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String modelResource = ParamUtil.getString(request, "modelResource");
String modelResourceName = ResourceActionsUtil.getModelResource(pageContext, modelResource);

ExpandoColumn column = (ExpandoColumn)request.getAttribute(WebKeys.EXPANDO_COLUMN);

long columnId = BeanParamUtil.getLong(column, request, "columnId");

ExpandoBridge expandoBridge = new ExpandoBridgeImpl(modelResource);

UnicodeProperties properties = new UnicodeProperties(true);
Object defaultValue = null;

if (column != null) {
	properties = expandoBridge.getAttributeProperties(column.getName());
	defaultValue = expandoBridge.getAttributeDefault(column.getName());
}
%>

<script type="text/javascript">
	function <portlet:namespace />saveExpando(options) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= column == null ? Constants.ADD : Constants.UPDATE %>";
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<form action="<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/expando/edit_expando" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveExpando(); return false;">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
<input name="<portlet:namespace />columnId" type="hidden" value="<%= columnId %>" />
<input name="<portlet:namespace />modelResource" type="hidden" value="<%= HtmlUtil.escape(modelResource) %>" />

<div>
	<liferay-ui:message key="edit-custom-attributes-for" />: <a href="<%= HtmlUtil.escape(redirect) %>"><%= modelResourceName %></a>
</div>

<br />

<liferay-ui:tabs
	names="custom-attribute"
	backURL="<%= redirect %>"
/>

<liferay-ui:error exception="<%= ColumnNameException.class %>" message="please-enter-a-valid-name" />
<liferay-ui:error exception="<%= ColumnTypeException.class %>" message="please-select-a-valid-type" />
<liferay-ui:error exception="<%= DuplicateColumnNameException.class %>" message="please-enter-a-unique-name" />
<liferay-ui:error exception="<%= ValueDataException.class %>" message="please-enter-a-valid-value" />

<table class="lfr-table">

<c:if test="<%= column != null %>">
	<tr>
		<td>
			<liferay-ui:message key="name" />
		</td>
		<td>

			<%
			String name = column.getName();
			String localizedName = LanguageUtil.get(pageContext, name);

			if (name.equals(localizedName)) {
				localizedName = TextFormatter.format(name, TextFormatter.J);
			}
			%>

			<%= localizedName %>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<br />
		</td>
	</tr>
</c:if>

<tr>
	<td>
		<liferay-ui:message key="key" />
	</td>
	<td>
		<c:choose>
			<c:when test="<%= column != null %>">
				<input name="<portlet:namespace />name" type="hidden" value="<%= column.getName() %>" />

				<%= column.getName() %>
			</c:when>
			<c:otherwise>
				<liferay-ui:input-field model="<%= ExpandoColumn.class %>" bean="<%= column %>" field="name" />
			</c:otherwise>
		</c:choose>

		<liferay-ui:icon-help message="custom-attribute-key-help" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="type" />
	</td>
	<td>
		<c:choose>
			<c:when test="<%= column != null %>">
				<input name="<portlet:namespace />type" type="hidden" value="<%= column.getType() %>" />

				<liferay-ui:message key="<%= ExpandoColumnConstants.getTypeLabel(column.getType()) %>" />
			</c:when>
			<c:otherwise>
				<select name="<portlet:namespace />type">

					<%
					for (int type : ExpandoColumnConstants.TYPES) {
						if ((type == ExpandoColumnConstants.BOOLEAN_ARRAY) || (type == ExpandoColumnConstants.DATE_ARRAY)) {
							continue;
						}
					%>

						<option value="<%= type %>"><%= ExpandoColumnConstants.getTypeLabel(type) %></option>

					<%
					}
					%>

				</select>
			</c:otherwise>
		</c:choose>
	</td>
</tr>

<c:if test="<%= column != null %>">
	<tr>
		<td colspan="2">
			<br />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="default-value" />
		</td>
		<td>
			<c:choose>
				<c:when test="<%= column.getType() == ExpandoColumnConstants.BOOLEAN %>">

					<%
					boolean curValue = ((Boolean)defaultValue).booleanValue();
					%>

					<select name="<portlet:namespace />defaultValue">
						<option <%= curValue ? "selected=" : "" %> value="1"><liferay-ui:message key="true" /></option>
						<option <%= !curValue ? "selected=" : "" %> value="0"><liferay-ui:message key="false" /></option>
					</select>
				</c:when>
				<c:when test="<%= column.getType() == ExpandoColumnConstants.BOOLEAN_ARRAY %>">
				</c:when>
				<c:when test="<%= column.getType() == ExpandoColumnConstants.DATE %>">

					<%
					Calendar defaultValueDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

					if (defaultValue != null) {
						defaultValueDate.setTime((Date)defaultValue);
					}
					%>

					<liferay-ui:input-date
						monthParam="defaultValueMonth"
						monthValue="<%= defaultValueDate.get(Calendar.MONTH) %>"
						dayParam="defaultValueDay"
						dayValue="<%= defaultValueDate.get(Calendar.DATE) %>"
						yearParam="defaultValueYear"
						yearValue="<%= defaultValueDate.get(Calendar.YEAR) %>"
						yearRangeStart="<%= defaultValueDate.get(Calendar.YEAR) - 100 %>"
						yearRangeEnd="<%= defaultValueDate.get(Calendar.YEAR) + 100 %>"
						firstDayOfWeek="<%= defaultValueDate.getFirstDayOfWeek() - 1 %>"
						disabled="<%= false %>"
					/>

					&nbsp;

					<liferay-ui:input-time
						hourParam="defaultValueHour"
						hourValue="<%= defaultValueDate.get(Calendar.HOUR) %>"
						minuteParam="defaultValueMinute"
						minuteValue="<%= defaultValueDate.get(Calendar.MINUTE) %>"
						minuteInterval="<%= 1 %>"
						amPmParam="defaultValueAmPm"
						amPmValue="<%= defaultValueDate.get(Calendar.AM_PM) %>"
						disabled="<%= false %>"
					/>
				</c:when>
				<c:when test="<%= column.getType() == ExpandoColumnConstants.DATE_ARRAY %>">
				</c:when>
				<c:when test="<%= column.getType() == ExpandoColumnConstants.DOUBLE_ARRAY %>">
					<textarea class="lfr-textarea" name="<portlet:namespace />defaultValue"><%= StringUtil.merge((double[])defaultValue, StringPool.NEW_LINE) %></textarea>
				</c:when>
				<c:when test="<%= column.getType() == ExpandoColumnConstants.FLOAT_ARRAY %>">
					<textarea class="lfr-textarea" name="<portlet:namespace />defaultValue"><%= StringUtil.merge((float[])defaultValue, StringPool.NEW_LINE) %></textarea>
				</c:when>
				<c:when test="<%= column.getType() == ExpandoColumnConstants.INTEGER_ARRAY %>">
					<textarea class="lfr-textarea" name="<portlet:namespace />defaultValue"><%= StringUtil.merge((int[])defaultValue, StringPool.NEW_LINE) %></textarea>
				</c:when>
				<c:when test="<%= column.getType() == ExpandoColumnConstants.LONG_ARRAY %>">
					<textarea class="lfr-textarea" name="<portlet:namespace />defaultValue"><%= StringUtil.merge((long[])defaultValue, StringPool.NEW_LINE) %></textarea>
				</c:when>
				<c:when test="<%= column.getType() == ExpandoColumnConstants.SHORT_ARRAY %>">
					<textarea class="lfr-textarea" name="<portlet:namespace />defaultValue"><%= StringUtil.merge((short[])defaultValue, StringPool.NEW_LINE) %></textarea>
				</c:when>
				<c:when test="<%= column.getType() == ExpandoColumnConstants.STRING_ARRAY %>">
					<textarea class="lfr-textarea" name="<portlet:namespace />defaultValue"><%= StringUtil.merge((String[])defaultValue, StringPool.NEW_LINE) %></textarea>
				</c:when>
				<c:otherwise>
					<input class="lfr-input-text" name="<portlet:namespace />defaultValue" type="text" value="<%= String.valueOf(defaultValue) %>" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:if>

</table>

<br />

<liferay-ui:tabs names="properties" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="hidden" />
	</td>
	<td>
		<input type="hidden" name="<portlet:namespace />PropertyName(hidden)" value="hidden" />

		<%
		boolean propertyHidden = GetterUtil.getBoolean(properties.get("hidden"));
		%>

		<select name="<portlet:namespace />Property(hidden)">
			<option <%= propertyHidden ? "selected=" : "" %> value="1"><liferay-ui:message key="true" /></option>
			<option <%= !propertyHidden ? "selected=" : "" %> value="0"><liferay-ui:message key="false" /></option>
		</select>

		<liferay-ui:icon-help message="custom-attribute-hidden-help" />
	</td>
</tr>

<c:if test="<%= (column != null) && (column.getType() == ExpandoColumnConstants.STRING) %>">
	<tr>
		<td>
			<liferay-ui:message key="searchable" />
		</td>
		<td>
			<input type="hidden" name="<portlet:namespace />PropertyName(indexable)" value="indexable" />

			<%
			boolean propertyIndexable = GetterUtil.getBoolean(properties.get(ExpandoBridgeIndexer.INDEXABLE));
			%>

			<select name="<portlet:namespace />Property(indexable)">
				<option <%= propertyIndexable ? "selected=" : "" %> value="1"><liferay-ui:message key="true" /></option>
				<option <%= !propertyIndexable ? "selected=" : "" %> value="0"><liferay-ui:message key="false" /></option>
			</select>

			<liferay-ui:icon-help message="custom-attribute-indexable-help" />
		</td>
	</tr>
</c:if>

</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

</form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) && (column == null) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>