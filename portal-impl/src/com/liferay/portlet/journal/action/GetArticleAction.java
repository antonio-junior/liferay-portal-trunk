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

package com.liferay.portlet.journal.action;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.StatusConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.ProcessingInstruction;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.model.JournalTemplateConstants;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;
import com.liferay.portlet.journal.util.JournalUtil;
import com.liferay.util.servlet.ServletResponseUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <a href="GetArticleAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Aug�
 */
public class GetArticleAction extends Action {

	public ActionForward execute(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		try {
			long groupId = ParamUtil.getLong(request, "groupId");
			String articleId =  ParamUtil.getString(request, "articleId");

			String languageId = LanguageUtil.getLanguageId(request);

			JournalArticle article =
				JournalArticleLocalServiceUtil.getLatestArticle(
					groupId, articleId, StatusConstants.APPROVED);

			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			Map<String, String> tokens = JournalUtil.getTokens(
				groupId, themeDisplay);

			String xml = article.getContentByLocale(languageId);

			Document doc = SAXReaderUtil.read(xml);

			Element root = doc.getRootElement();

			addProcessingInstructions(doc, request, themeDisplay, article);

			JournalUtil.addAllReservedEls(root, tokens, article);

			xml = JournalUtil.formatXML(doc);

			String contentType = ContentTypes.TEXT_XML_UTF8;

			String fileName = null;
			byte[] bytes = xml.getBytes();

			ServletResponseUtil.sendFile(
				response, fileName, bytes, contentType);

			return null;
		}
		catch (Exception e) {
			PortalUtil.sendError(e, request, response);

			return null;
		}
	}

	protected void addProcessingInstructions(
		Document doc, HttpServletRequest request, ThemeDisplay themeDisplay,
		JournalArticle article) {

		// Add style sheets in the reverse order that they appear in the
		// document

		// Portal CSS

		StringBuilder sb = new StringBuilder();

		sb.append(
			PortalUtil.getStaticResourceURL(
				request,
				themeDisplay.getCDNHost() + themeDisplay.getPathContext() +
					"/html/portal/css.jsp"));

		String url = sb.toString();

		Map<String, String> arguments = new LinkedHashMap<String, String>();

		arguments.put("type", "text/css");
		arguments.put("href", url);
		arguments.put("title", "portal css");
		arguments.put("alternate", "yes");

		addStyleSheet(doc, url, arguments);

		// Theme CSS

		sb = new StringBuilder();

		sb.append(
			PortalUtil.getStaticResourceURL(
				request, themeDisplay.getPathThemeCss() + "/main.css"));

		url = sb.toString();

		arguments.clear();

		arguments.put("type", "text/css");
		arguments.put("href", url);
		arguments.put("title", "theme css");

		addStyleSheet(doc, url, arguments);

		// XSL template

		String templateId = article.getTemplateId();

		if (Validator.isNotNull(templateId)) {
			JournalTemplate template = null;

			try {
				template = JournalTemplateLocalServiceUtil.getTemplate(
					article.getGroupId(), templateId);

				if (Validator.equals(
						template.getLangType(),
						JournalTemplateConstants.LANG_TYPE_XSL)) {

					url =
						themeDisplay.getPathMain() +
							"/journal/get_template?groupId=" +
								article.getGroupId() + "&templateId=" +
									templateId;

					arguments.clear();

					arguments.put("type", "text/xsl");
					arguments.put("href", url);
					arguments.put("title", "xsl");

					addStyleSheet(doc, url, arguments);
				}
			}
			catch (Exception e) {
			}
		}
	}

	protected void addStyleSheet(
		Document doc, String url, Map<String, String> arguments) {

		List<Node> content = doc.content();

		ProcessingInstruction processingInstruction =
			SAXReaderUtil.createProcessingInstruction(
				"xml-stylesheet", arguments);

		content.add(0, processingInstruction);
	}

}