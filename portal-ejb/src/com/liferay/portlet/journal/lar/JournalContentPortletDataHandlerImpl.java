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

package com.liferay.portlet.journal.lar;

import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataException;
import com.liferay.portal.kernel.lar.PortletDataHandler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.SAXReaderFactory;
import com.liferay.portlet.journal.NoSuchArticleException;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.model.impl.JournalArticleImpl;
import com.liferay.portlet.journal.model.impl.JournalStructureImpl;
import com.liferay.portlet.journal.model.impl.JournalTemplateImpl;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalStructureLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;
import com.liferay.portlet.journal.service.persistence.JournalArticleUtil;
import com.liferay.portlet.journal.service.persistence.JournalStructurePK;
import com.liferay.portlet.journal.service.persistence.JournalStructureUtil;
import com.liferay.portlet.journal.service.persistence.JournalTemplatePK;
import com.liferay.portlet.journal.service.persistence.JournalTemplateUtil;
import com.liferay.util.GetterUtil;
import com.liferay.util.Validator;
import com.liferay.util.xml.XMLFormatter;

import com.thoughtworks.xstream.XStream;

import java.io.StringReader;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Provides the Journal Content portlet export and import functionality, which
 * is to clone the article, structure, and template referenced in the
 * Journal Content portlet if the article is associated with the layout's group.
 * Upon import, new instances of the corresponding articles, structures, and
 * templates are created or updated. The author of the newly created
 * objects are determined by the JournalCreationStrategy class defined in
 * <i>portal.properties</i>.
 *
 * <p>This <code>PortletDataHandler</code> differs from from
 * <code>JournalPortletDataHandlerImpl</code> in that it only exports articles
 * referenced in Journal Content portlets. Articles not displayed in Journal
 * Content portlets will not be exported unless
 * <code>JournalPortletDataHandlerImpl</code> is activated.
 *
 * <p><a href="JournalContentPortletDataHandlerImpl.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author  Joel Kozikowski
 *
 */
public class JournalContentPortletDataHandlerImpl
	implements PortletDataHandler {

	public String exportData(
			PortletDataContext context, String portletId,
			PortletPreferences prefs)
		throws PortletDataException {

		try {
			String articleId = prefs.getValue("article-id", null);

			if (articleId == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No article id found in preferences of portlet " +
							portletId);
				}

				return StringPool.BLANK;
			}

			long articleGroupId = GetterUtil.getLong(prefs.getValue(
				"group-id", StringPool.BLANK));

			if (articleGroupId <= 0) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No group id found in preferences of portlet " +
							portletId);
				}

				return StringPool.BLANK;
			}

			JournalArticle article = null;

			try {
				article = JournalArticleLocalServiceUtil.getLatestArticle(
					context.getCompanyId(), articleGroupId, articleId);
			}
			catch (NoSuchArticleException nsae) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No article found with company id " +
							context.getCompanyId() + ", group id " +
								articleGroupId + " and article id " +
									articleId);
				}
			}

			if (article == null) {
				return StringPool.BLANK;
			}

			if ((article.getGroupId() == context.getGroupId()) &&
				!context.checkPrimaryKey(
					JournalArticle.class, article.getPrimaryKey())) {

				SAXReader reader = SAXReaderFactory.getInstance();

				XStream xStream = new XStream();

				Document doc = DocumentHelper.createDocument();

				Element root = doc.addElement("journal-content");

				String xml = xStream.toXML(article);

				Document tempDoc = reader.read(new StringReader(xml));

				List content = root.content();

				content.add(tempDoc.getRootElement().createCopy());

				String structureId = article.getStructureId();

				if (Validator.isNotNull(structureId)) {
					JournalStructurePK structurePK = new JournalStructurePK(
						context.getCompanyId(), article.getGroupId(),
						structureId);

					if (!context.checkPrimaryKey(
							JournalStructure.class, structurePK)) {

						JournalStructure structure =
							JournalStructureUtil.findByPrimaryKey(structurePK);

						xml = xStream.toXML(structure);

						tempDoc = reader.read(new StringReader(xml));

						content.add(tempDoc.getRootElement().createCopy());
					}
				}

				String templateId = article.getTemplateId();

				if (Validator.isNotNull(templateId)) {
					JournalTemplatePK templatePK = new JournalTemplatePK(
						context.getCompanyId(), article.getGroupId(),
						templateId);

					if (!context.checkPrimaryKey(
							JournalTemplate.class, templatePK)) {

						JournalTemplate template =
							JournalTemplateUtil.findByPrimaryKey(templatePK);

						xml = xStream.toXML(template);

						tempDoc = reader.read(new StringReader(xml));

						content.add(tempDoc.getRootElement().createCopy());
					}
				}

				return XMLFormatter.toString(doc);
			}
			else {
				return StringPool.BLANK;
			}
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	public PortletPreferences importData(
			PortletDataContext context, String portletId,
			PortletPreferences prefs, String data)
		throws PortletDataException {

		try {
			importJournalData(context, portletId, data);

			// Update the group-id in the display content to reference this
			// group, since the article is now in this group.

			prefs.setValue("group-id", String.valueOf(context.getGroupId()));

			return prefs;
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	protected void importJournalData(
			PortletDataContext context, String portletId, String data)
		throws Exception {

		if (Validator.isNull(data)) {
			return;
		}

		JournalCreationStrategy creationStrategy =
			JournalCreationStrategyFactory.getInstance();

		SAXReader reader = SAXReaderFactory.getInstance();

		XStream xStream = new XStream();

		Document doc = reader.read(new StringReader(data));

		Element root = doc.getRootElement();

		Element el = root.element(JournalArticleImpl.class.getName());

		Document tempDoc = DocumentHelper.createDocument();

		tempDoc.content().add(el.createCopy());

		JournalArticle article =
			(JournalArticle)xStream.fromXML(XMLFormatter.toString(tempDoc));

		article.setGroupId(context.getGroupId());

		if (JournalArticleUtil.fetchByPrimaryKey(
				article.getPrimaryKey()) == null) {

			article.setNew(true);

			String authorId = creationStrategy.getAuthorUserId(
				context.getCompanyId(), context.getGroupId(), article);

			if (authorId != null) {
				article.setUserId(authorId);
				article.setUserName(
					creationStrategy.getAuthorUserName(
						context.getCompanyId(), context.getGroupId(), article));
			}

			String approvedById = creationStrategy.getApprovalUserId(
				context.getCompanyId(), context.getGroupId(), article);

			if (approvedById != null) {
				article.setApprovedByUserId(approvedById);
				article.setApprovedByUserName(
					creationStrategy.getApprovalUserName(
						context.getCompanyId(), context.getGroupId(), article));
				article.setApproved(true);
			}
			else {
				article.setApprovedByUserId(null);
				article.setApprovedByUserName(null);
				article.setApproved(false);
			}

			article = JournalArticleUtil.update(article);

			boolean addCommunityPermissions =
				creationStrategy.addCommunityPermissions(
					context.getCompanyId(), context.getGroupId(), article);
			boolean addGuestPermissions =
				creationStrategy.addGuestPermissions(
					context.getCompanyId(), context.getGroupId(), article);

			JournalArticleLocalServiceUtil.addArticleResources(
				article, addCommunityPermissions, addGuestPermissions);
		}
		else {
			JournalArticleUtil.update(article, true);
		}

		el = root.element(JournalStructureImpl.class.getName());

		if (el != null) {
			tempDoc = DocumentHelper.createDocument();

			tempDoc.content().add(el.createCopy());

			JournalStructure structure = (JournalStructure)xStream.fromXML(
				XMLFormatter.toString(tempDoc));

			structure.setGroupId(context.getGroupId());

			if (JournalStructureUtil.fetchByPrimaryKey(
					structure.getPrimaryKey()) == null) {

				structure.setNew(true);

				String authorId = creationStrategy.getAuthorUserId(
					context.getCompanyId(), context.getGroupId(), structure);

				if (authorId != null) {
					structure.setUserId(authorId);
					structure.setUserName(
						creationStrategy.getAuthorUserName(
							context.getCompanyId(), context.getGroupId(),
							structure));
				}

				structure = JournalStructureUtil.update(structure);

				boolean addCommunityPermissions =
					creationStrategy.addCommunityPermissions(
						context.getCompanyId(), context.getGroupId(),
						structure);
				boolean addGuestPermissions =
					creationStrategy.addGuestPermissions(
						context.getCompanyId(), context.getGroupId(),
						structure);

				JournalStructureLocalServiceUtil.addStructureResources(
					structure, addCommunityPermissions, addGuestPermissions);
			}
			else {
				JournalStructureUtil.update(structure, true);
			}
		}

		el = root.element(JournalTemplateImpl.class.getName());

		if (el != null) {
			tempDoc = DocumentHelper.createDocument();

			tempDoc.content().add(el.createCopy());

			JournalTemplate template = (JournalTemplate)xStream.fromXML(
				XMLFormatter.toString(tempDoc));

			template.setGroupId(context.getGroupId());

			if (JournalTemplateUtil.fetchByPrimaryKey(
					template.getPrimaryKey()) == null) {

				template.setNew(true);

				String authorId = creationStrategy.getAuthorUserId(
					context.getCompanyId(), context.getGroupId(), template);

				if (authorId != null) {
					template.setUserId(authorId);
					template.setUserName(
						creationStrategy.getAuthorUserName(
							context.getCompanyId(), context.getGroupId(),
							template));
				}

				template = JournalTemplateUtil.update(template);

				boolean addCommunityPermissions =
					creationStrategy.addCommunityPermissions(
						context.getCompanyId(), context.getGroupId(), template);
				boolean addGuestPermissions =
					creationStrategy.addGuestPermissions(
						context.getCompanyId(), context.getGroupId(), template);

				JournalTemplateLocalServiceUtil.addTemplateResources(
					template, addCommunityPermissions, addGuestPermissions);
			}
			else {
				JournalTemplateUtil.update(template, true);
			}
		}
	}

	private static Log _log =
		LogFactory.getLog(JournalContentPortletDataHandlerImpl.class);

}