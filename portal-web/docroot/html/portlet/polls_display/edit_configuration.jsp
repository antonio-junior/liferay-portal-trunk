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

<%@ include file="/html/portlet/polls_display/init.jsp" %>

<%
questionId = ParamUtil.getLong(request, "questionId", questionId);

List questions = PollsQuestionLocalServiceUtil.getQuestions(portletGroupId.longValue());
%>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<liferay-ui:error exception="<%= NoSuchQuestionException.class %>" message="the-question-could-not-be-found" />

<table class="liferay-table">
<tr>
	<td>
		<bean:message key="question" />
	</td>
	<td>
		<select name="<portlet:namespace />questionId">
			<option value=""></option>

			<%
			for (int i = 0; i < questions.size(); i++) {
				PollsQuestion question = (PollsQuestion)questions.get(i);
			%>

				<option <%= (questionId == question.getQuestionId()) ? "selected" : "" %> value="<%= question.getQuestionId() %>"><%= question.getTitle() %></option>

			<%
			}
			%>

		</select>
	</td>
</tr>
</table>

<br />

<input type="button" value="<bean:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>