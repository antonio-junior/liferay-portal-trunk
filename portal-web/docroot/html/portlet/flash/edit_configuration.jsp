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

<%@ include file="/html/portlet/flash/init.jsp" %>

<%
if (movie.equals(StringPool.BLANK)) {
	movie = themeDisplay.getPathFlash() + "/tennis.swf";
}

if (flashAttributes.equals(StringPool.BLANK)) {
	flashAttributes =
		"align=left\n" +
		"allowScriptAccess=sameDomain\n" +
		"base=.\n" +
		"bgcolor=#FFFFFF\n" +
		"devicefont=true\n" +
		"height=500\n" +
		"loop=true\n" +
		"menu=false\n" +
		"play=false\n" +
		"quality=best\n" +
		"salign=\n" +
		"scale=showall\n" +
		"swliveconnect=false\n" +
		"width=100%\n" +
		"wmode=opaque";
}

if (flashVariables.equals(StringPool.BLANK)) {
	flashVariables = "var1=hello\nvar2=world";
}

movie = ParamUtil.getString(request, "movie", movie);
flashAttributes = ParamUtil.getString(request, "flashAttributes", flashAttributes);
flashVariables = ParamUtil.getString(request, "flashVariables", flashVariables);
%>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>">

<table class="liferay-table">
<tr>
	<td>
		<bean:message key="movie" />
	</td>
	<td>
		<input name="<portlet:namespace />movie" style="width: <%= ModelHintsDefaults.TEXT_DISPLAY_WIDTH %>px;" type="text" value="<%= movie %>">
	</td>
</tr>
<tr>
	<td>
		<bean:message key="flash-attributes" />
	</td>
	<td>
		<textarea name="<portlet:namespace />flashAttributes" style="height: <%= ModelHintsDefaults.TEXTAREA_DISPLAY_HEIGHT %>px; width: <%= ModelHintsDefaults.TEXTAREA_DISPLAY_WIDTH %>px;" wrap="soft" onKeyDown="Liferay.Util.checkTab(this); disableEsc();"><%= flashAttributes %></textarea>
	</td>
</tr>
<tr>
	<td>
		<bean:message key="flash-variables" />
	</td>
	<td>
		<textarea name="<portlet:namespace />flashVariables" style="height: <%= ModelHintsDefaults.TEXTAREA_DISPLAY_HEIGHT %>px; width: <%= ModelHintsDefaults.TEXTAREA_DISPLAY_WIDTH %>px;" wrap="soft" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();"><%= flashVariables %></textarea>
	</td>
</tr>
</table>

<br />

<input type="button" value="<bean:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);">

</form>

<script type="text/javascript">
	document.<portlet:namespace />fm.<portlet:namespace />movie.focus();
</script>