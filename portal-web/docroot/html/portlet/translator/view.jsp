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

<%@ include file="/html/portlet/translator/init.jsp" %>

<%
Translation translation = (Translation)request.getAttribute(WebKeys.TRANSLATOR_TRANSLATION);

if (translation == null) {
	translation = new Translation(PropsUtil.get(PropsUtil.TRANSLATOR_DEFAULT_LANGUAGES), StringPool.BLANK, StringPool.BLANK);
}
%>

<form accept-charset="UTF-8" action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm" onSubmit="submitForm(this); return false;">

<c:if test="<%= Validator.isNotNull(translation.getToText()) %>">
	<%= translation.getToText() %>

	<br /><br />
</c:if>

<textarea name="<portlet:namespace />text" style="height: <%= ModelHintsDefaults.TEXTAREA_DISPLAY_HEIGHT %>px; width: <%= ModelHintsDefaults.TEXTAREA_DISPLAY_WIDTH %>px;" wrap="soft"><%= translation.getFromText() %></textarea>

<br /><br />

<select name="<portlet:namespace />id">
	<option <%= (translation.getTranslationId().equals("en_zh")) ? "selected" : "" %> value="en_zh"><bean:message key="en_zh" /></option>
	<option <%= (translation.getTranslationId().equals("en_zt")) ? "selected" : "" %> value="en_zt"><bean:message key="en_zt" /></option>
	<option <%= (translation.getTranslationId().equals("en_nl")) ? "selected" : "" %> value="en_nl"><bean:message key="en_nl" /></option>
	<option <%= (translation.getTranslationId().equals("en_fr")) ? "selected" : "" %> value="en_fr"><bean:message key="en_fr" /></option>
	<option <%= (translation.getTranslationId().equals("en_de")) ? "selected" : "" %> value="en_de"><bean:message key="en_de" /></option>
	<option <%= (translation.getTranslationId().equals("en_it")) ? "selected" : "" %> value="en_it"><bean:message key="en_it" /></option>
	<option <%= (translation.getTranslationId().equals("en_ja")) ? "selected" : "" %> value="en_ja"><bean:message key="en_ja" /></option>
	<option <%= (translation.getTranslationId().equals("en_ko")) ? "selected" : "" %> value="en_ko"><bean:message key="en_ko" /></option>
	<option <%= (translation.getTranslationId().equals("en_pt")) ? "selected" : "" %> value="en_pt"><bean:message key="en_pt" /></option>
	<option <%= (translation.getTranslationId().equals("en_es")) ? "selected" : "" %> value="en_es"><bean:message key="en_es" /></option>
	<option <%= (translation.getTranslationId().equals("zh_en")) ? "selected" : "" %> value="zh_en"><bean:message key="zh_en" /></option>
	<option <%= (translation.getTranslationId().equals("zt_en")) ? "selected" : "" %> value="zt_en"><bean:message key="zt_en" /></option>
	<option <%= (translation.getTranslationId().equals("nl_en")) ? "selected" : "" %> value="nl_en"><bean:message key="nl_en" /></option>
	<option <%= (translation.getTranslationId().equals("fr_en")) ? "selected" : "" %> value="fr_en"><bean:message key="fr_en" /></option>
	<option <%= (translation.getTranslationId().equals("fr_de")) ? "selected" : "" %> value="fr_de"><bean:message key="fr_de" /></option>
	<option <%= (translation.getTranslationId().equals("de_en")) ? "selected" : "" %> value="de_en"><bean:message key="de_en" /></option>
	<option <%= (translation.getTranslationId().equals("de_fr")) ? "selected" : "" %> value="de_fr"><bean:message key="de_fr" /></option>
	<option <%= (translation.getTranslationId().equals("it_en")) ? "selected" : "" %> value="it_en"><bean:message key="it_en" /></option>
	<option <%= (translation.getTranslationId().equals("ja_en")) ? "selected" : "" %> value="ja_en"><bean:message key="ja_en" /></option>
	<option <%= (translation.getTranslationId().equals("ko_en")) ? "selected" : "" %> value="ko_en"><bean:message key="ko_en" /></option>
	<option <%= (translation.getTranslationId().equals("pt_en")) ? "selected" : "" %> value="pt_en"><bean:message key="pt_en" /></option>
	<option <%= (translation.getTranslationId().equals("ru_en")) ? "selected" : "" %> value="ru_en"><bean:message key="ru_en" /></option>
	<option <%= (translation.getTranslationId().equals("es_en")) ? "selected" : "" %> value="es_en"><bean:message key="es_en" /></option>
</select>

<input type="submit" value="<bean:message key="translate" />" />

</form>

<c:if test="<%= renderRequest.getWindowState().equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		document.<portlet:namespace />fm.<portlet:namespace />text.focus();
	</script>
</c:if>