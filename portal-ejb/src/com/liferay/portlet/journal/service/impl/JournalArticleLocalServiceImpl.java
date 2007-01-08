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

package com.liferay.portlet.journal.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.lucene.LuceneFields;
import com.liferay.portal.lucene.LuceneUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.ResourceImpl;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.impl.ImageLocalUtil;
import com.liferay.portal.service.persistence.CompanyUtil;
import com.liferay.portal.service.persistence.PortletPreferencesPK;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.journal.ArticleContentException;
import com.liferay.portlet.journal.ArticleDisplayDateException;
import com.liferay.portlet.journal.ArticleExpirationDateException;
import com.liferay.portlet.journal.ArticleIdException;
import com.liferay.portlet.journal.ArticleReviewDateException;
import com.liferay.portlet.journal.ArticleTitleException;
import com.liferay.portlet.journal.DuplicateArticleIdException;
import com.liferay.portlet.journal.NoSuchArticleException;
import com.liferay.portlet.journal.NoSuchTemplateException;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.model.impl.JournalArticleImpl;
import com.liferay.portlet.journal.model.impl.JournalStructureImpl;
import com.liferay.portlet.journal.service.JournalArticleLocalService;
import com.liferay.portlet.journal.service.JournalContentSearchLocalServiceUtil;
import com.liferay.portlet.journal.service.persistence.JournalArticleFinder;
import com.liferay.portlet.journal.service.persistence.JournalArticlePK;
import com.liferay.portlet.journal.service.persistence.JournalArticleUtil;
import com.liferay.portlet.journal.service.persistence.JournalStructurePK;
import com.liferay.portlet.journal.service.persistence.JournalStructureUtil;
import com.liferay.portlet.journal.service.persistence.JournalTemplatePK;
import com.liferay.portlet.journal.service.persistence.JournalTemplateUtil;
import com.liferay.portlet.journal.util.Indexer;
import com.liferay.portlet.journal.util.JournalUtil;
import com.liferay.portlet.journal.util.comparator.ArticleDisplayDateComparator;
import com.liferay.util.Html;
import com.liferay.util.LocaleUtil;
import com.liferay.util.MathUtil;
import com.liferay.util.StringUtil;
import com.liferay.util.Time;
import com.liferay.util.Validator;
import com.liferay.util.lucene.HitsImpl;

import java.io.IOException;
import java.io.StringReader;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import javax.portlet.PortletPreferences;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * <a href="JournalArticleLocalServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class JournalArticleLocalServiceImpl
	implements JournalArticleLocalService {

	public JournalArticle addArticle(
			String userId, String articleId, boolean autoArticleId,
			String plid, String title, String description, String content,
			String type, String structureId, String templateId,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, int reviewDateMonth, int reviewDateDay,
			int reviewDateYear, int reviewDateHour, int reviewDateMinute,
			boolean neverReview, Map images, String articleURL,
			PortletPreferences prefs, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		return addArticle(
			userId, articleId, autoArticleId, plid, title, description, content,
			type, structureId, templateId, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			reviewDateMonth, reviewDateDay, reviewDateYear, reviewDateHour,
			reviewDateMinute, neverReview, images, articleURL, prefs,
			new Boolean(addCommunityPermissions),
			new Boolean(addGuestPermissions), null, null);
	}

	public JournalArticle addArticle(
			String userId, String articleId, boolean autoArticleId,
			String plid, String title, String description, String content,
			String type, String structureId, String templateId,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, int reviewDateMonth, int reviewDateDay,
			int reviewDateYear, int reviewDateHour, int reviewDateMinute,
			boolean neverReview, Map images, String articleURL,
			PortletPreferences prefs, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		return addArticle(
			userId, articleId, autoArticleId, plid, title, description, content,
			type, structureId, templateId, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			reviewDateMonth, reviewDateDay, reviewDateYear, reviewDateHour,
			reviewDateMinute, neverReview, images, articleURL, prefs, null,
			null, communityPermissions, guestPermissions);
	}

	public JournalArticle addArticle(
			String userId, String articleId, boolean autoArticleId,
			String plid, String title, String description, String content,
			String type, String structureId, String templateId,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, int reviewDateMonth, int reviewDateDay,
			int reviewDateYear, int reviewDateHour, int reviewDateMinute,
			boolean neverReview, Map images, String articleURL,
			PortletPreferences prefs, Boolean addCommunityPermissions,
			Boolean addGuestPermissions, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		// Article

		User user = UserUtil.findByPrimaryKey(userId);
		articleId = articleId.trim().toUpperCase();
		long groupId = PortalUtil.getPortletGroupId(plid);
		Date now = new Date();

		Date displayDate = PortalUtil.getDate(
			displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, user.getTimeZone(),
			new ArticleDisplayDateException());

		Date expirationDate = null;

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				new ArticleExpirationDateException());
		}

		Date reviewDate = null;

		if (!neverReview) {
			reviewDate = PortalUtil.getDate(
				reviewDateMonth, reviewDateDay, reviewDateYear, reviewDateHour,
				reviewDateMinute, user.getTimeZone(),
				new ArticleReviewDateException());
		}

		validate(
			user.getCompanyId(), groupId, articleId, autoArticleId, title,
			content, structureId, templateId);

		if (autoArticleId) {
			articleId = String.valueOf(CounterLocalServiceUtil.increment(
				JournalArticle.class.getName() + "." + user.getCompanyId()));
		}

		JournalArticlePK pk = new JournalArticlePK(
			user.getCompanyId(), groupId, articleId,
			JournalArticleImpl.DEFAULT_VERSION);

		JournalArticle article = JournalArticleUtil.create(pk);

		content = format(
			article.getCompanyId(), groupId, articleId, article.getVersion(),
			false, content, structureId, images);

		article.setCompanyId(user.getCompanyId());
		article.setGroupId(groupId);
		article.setUserId(user.getUserId());
		article.setUserName(user.getFullName());
		article.setCreateDate(now);
		article.setModifiedDate(now);
		article.setTitle(title);
		article.setDescription(description);
		article.setContent(content);
		article.setType(type);
		article.setStructureId(structureId);
		article.setTemplateId(templateId);
		article.setDisplayDate(displayDate);
		article.setApproved(false);
		article.setExpirationDate(expirationDate);
		article.setReviewDate(reviewDate);

		JournalArticleUtil.update(article);

		// Resources

		if ((addCommunityPermissions != null) &&
			(addGuestPermissions != null)) {

			addArticleResources(
				article, addCommunityPermissions.booleanValue(),
				addGuestPermissions.booleanValue());
		}
		else {
			addArticleResources(
				article, communityPermissions, guestPermissions);
		}

		// Email

		sendEmail(article, articleURL, prefs, "requested");

		return article;
	}

	public void addArticleResources(
			String companyId, long groupId, String articleId,
			boolean addCommunityPermissions, boolean addGuestPermissions)
		throws PortalException, SystemException {

		JournalArticle article = getLatestArticle(
			companyId, groupId, articleId);

		addArticleResources(
			article, addCommunityPermissions, addGuestPermissions);
	}

	public void addArticleResources(
			JournalArticle article, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		ResourceLocalServiceUtil.addResources(
			article.getCompanyId(), article.getGroupId(),
			article.getUserId(), JournalArticle.class.getName(),
			article.getResourcePK().toString(), false, addCommunityPermissions,
			addGuestPermissions);
	}

	public void addArticleResources(
			String companyId, long groupId, String articleId,
			String[] communityPermissions, String[] guestPermissions)
		throws PortalException, SystemException {

		JournalArticle article = getLatestArticle(
			companyId, groupId, articleId);

		addArticleResources(article, communityPermissions, guestPermissions);
	}

	public void addArticleResources(
			JournalArticle article, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		ResourceLocalServiceUtil.addModelResources(
			article.getCompanyId(), article.getGroupId(),
			article.getUserId(), JournalArticle.class.getName(),
			article.getResourcePK().toString(), communityPermissions,
			guestPermissions);
	}

	public JournalArticle approveArticle(
			String userId, long groupId, String articleId, double version,
			String articleURL, PortletPreferences prefs)
		throws PortalException, SystemException {

		// Article

		User user = UserUtil.findByPrimaryKey(userId);
		Date now = new Date();

		JournalArticlePK pk = new JournalArticlePK(
			user.getCompanyId(), groupId, articleId, version);

		JournalArticle article = JournalArticleUtil.findByPrimaryKey(pk);

		article.setModifiedDate(now);
		article.setApproved(true);
		article.setApprovedByUserId(user.getUserId());
		article.setApprovedByUserName(user.getFullName());
		article.setApprovedDate(now);

		JournalArticleUtil.update(article);

		// Email

		sendEmail(article, articleURL, prefs, "granted");

		// Lucene

		try {
			Indexer.updateArticle(
				article.getCompanyId(), article.getGroupId(),
				article.getArticleId(), article.getVersion(),
				article.getTitle(), article.getDescription(),
				article.getContent(), article.getType(),
				article.getDisplayDate());
		}
		catch (IOException ioe) {
			_log.error("Indexing " + pk, ioe);
		}

		return article;
	}

	public void checkArticles() throws PortalException, SystemException {
		Date now = new Date();

		List articles = JournalArticleUtil.findAll();

		for (int i = 0; i < articles.size(); i++) {
			JournalArticle article = (JournalArticle)articles.get(i);

			Date expirationDate = article.getExpirationDate();
			Date reviewDate = article.getReviewDate();

			if (!article.isExpired() && (expirationDate != null) &&
				expirationDate.before(now)) {

				article.setApproved(false);
				article.setExpired(true);

				JournalArticleUtil.update(article);
			}
			else if (reviewDate != null) {

				// Check in 15 minute intervals because of CheckArticleJob

				long diff = reviewDate.getTime() - now.getTime();

				if ((diff > 0) && (diff < (Time.MINUTE * 15))) {
					String articleURL = StringPool.BLANK;

					PortletPreferencesPK prefsPK = new PortletPreferencesPK(
						PortletKeys.JOURNAL, PortletKeys.PREFS_LAYOUT_ID_SHARED,
						PortletKeys.PREFS_OWNER_ID_GROUP + StringPool.PERIOD +
							article.getGroupId());

					PortletPreferences prefs =
						PortletPreferencesLocalServiceUtil.getPreferences(
							article.getCompanyId(), prefsPK);

					sendEmail(article, articleURL, prefs, "review");
				}
			}
		}
	}

	public void checkNewLine(
			String companyId, long groupId, String articleId, double version)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleUtil.findByPrimaryKey(new JournalArticlePK(
				companyId, groupId, articleId,
				JournalArticleImpl.DEFAULT_VERSION));

		String content = article.getContent();

		if ((content != null) && (content.indexOf("\\n") != -1)) {
			content = StringUtil.replace(
				content,
				new String[] {"\\n", "\\r"},
				new String[] {"\n", "\r"});

			article.setContent(content);

			JournalArticleUtil.update(article);
		}
	}

	public void deleteArticle(
			String companyId, long groupId, String articleId, double version,
			String articleURL, PortletPreferences prefs)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleUtil.findByPrimaryKey(new JournalArticlePK(
				companyId, groupId, articleId, version));

		deleteArticle(article, articleURL, prefs);
	}

	public void deleteArticle(
			JournalArticle article, String articleURL, PortletPreferences prefs)
		throws PortalException, SystemException {

		// Lucene

		try {
			if (article.isApproved()) {
				Indexer.deleteArticle(
					article.getCompanyId(), article.getArticleId());
			}
		}
		catch (IOException ioe) {
			_log.error("Deleting index " + article.getPrimaryKey(), ioe);
		}

		// Email

		if ((prefs != null) && !article.isApproved() &&
			isLatestVersion(
				article.getCompanyId(), article.getGroupId(),
				article.getArticleId(), article.getVersion())) {

			sendEmail(article, articleURL, prefs, "denied");
		}

		// Content searches

		JournalContentSearchLocalServiceUtil.deleteArticleContentSearches(
			article.getCompanyId(), article.getGroupId(),
			article.getArticleId());

		// Images

		ImageLocalServiceUtil.deleteImages(
			"%.journal.article." + article.getGroupId() + "." +
				article.getArticleId() + ".%." + article.getVersion());

		// Resources

		if (JournalArticleUtil.countByC_G_A(
				article.getCompanyId(), article.getGroupId(),
					article.getArticleId()) == 1) {

			ResourceLocalServiceUtil.deleteResource(
				article.getCompanyId(), JournalArticle.class.getName(),
				ResourceImpl.TYPE_CLASS, ResourceImpl.SCOPE_INDIVIDUAL,
				article.getResourcePK().toString());
		}

		// Article

		JournalArticleUtil.remove(article.getPrimaryKey());
	}

	public void deleteArticles(long groupId)
		throws PortalException, SystemException {

		Iterator itr = JournalArticleUtil.findByGroupId(groupId).iterator();

		while (itr.hasNext()) {
			JournalArticle article = (JournalArticle)itr.next();

			deleteArticle(article, null, null);
		}
	}

	public void expireArticle(
			String companyId, long groupId, String articleId, double version,
			String articleURL, PortletPreferences prefs)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleUtil.findByPrimaryKey(new JournalArticlePK(
				companyId, groupId, articleId, version));

		expireArticle(article, articleURL, prefs);
	}

	public void expireArticle(
			JournalArticle article, String articleURL, PortletPreferences prefs)
		throws PortalException, SystemException {

		// Email

		if ((prefs != null) && !article.isApproved() &&
			isLatestVersion(
				article.getCompanyId(), article.getGroupId(),
				article.getArticleId(), article.getVersion())) {

			sendEmail(article, articleURL, prefs, "denied");
		}

		// Article

		article.setExpirationDate(new Date());

		article.setApproved(false);
		article.setExpired(true);

		JournalArticleUtil.update(article);
	}

	public JournalArticle getArticle(
			String companyId, long groupId, String articleId)
		throws PortalException, SystemException {

		// Get the latest article that is approved, if none are approved, get
		// the latest unapproved article

		try {
			return getLatestArticle(
				companyId, groupId, articleId, Boolean.TRUE);
		}
		catch (NoSuchArticleException nsae) {
			return getLatestArticle(
				companyId, groupId, articleId, Boolean.FALSE);
		}
	}

	public JournalArticle getArticle(
			String companyId, long groupId, String articleId, double version)
		throws PortalException, SystemException {

		return JournalArticleUtil.findByPrimaryKey(
			new JournalArticlePK(companyId, groupId, articleId, version));
	}

	public String getArticleContent(
			String companyId, long groupId, String articleId,
			String languageId, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		JournalArticle article = getDisplayArticle(
			companyId, groupId, articleId);

		return getArticleContent(
			companyId, groupId, articleId, article.getVersion(), languageId,
			themeDisplay);
	}

	public String getArticleContent(
			String companyId, long groupId, String articleId, double version,
			String languageId, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleUtil.findByPrimaryKey(new JournalArticlePK(
				companyId, groupId, articleId, version));

		/*if (!article.isTemplateDriven()) {
			return article.getContent();
		}*/

		Map tokens = JournalUtil.getTokens(groupId, themeDisplay);

		String xml = article.getContent();

		try {
			Document doc = null;

			Element root = null;

			if (article.isTemplateDriven()) {
				SAXReader reader = new SAXReader();

				doc = reader.read(new StringReader(xml));

				root = doc.getRootElement();
			}

			JournalUtil.addReservedEl(
				root, tokens, JournalStructureImpl.RESERVED_ARTICLE_ID,
				article.getArticleId());

			JournalUtil.addReservedEl(
				root, tokens, JournalStructureImpl.RESERVED_ARTICLE_VERSION,
				Double.toString(article.getVersion()));

			JournalUtil.addReservedEl(
				root, tokens, JournalStructureImpl.RESERVED_ARTICLE_TITLE,
				article.getTitle());

			JournalUtil.addReservedEl(
				root, tokens, JournalStructureImpl.RESERVED_ARTICLE_DESCRIPTION,
				article.getDescription());

			JournalUtil.addReservedEl(
				root, tokens, JournalStructureImpl.RESERVED_ARTICLE_CREATE_DATE,
				article.getCreateDate().toString());

			JournalUtil.addReservedEl(
				root, tokens,
				JournalStructureImpl.RESERVED_ARTICLE_MODIFIED_DATE,
				article.getModifiedDate().toString());

			if (article.getDisplayDate() != null) {
				JournalUtil.addReservedEl(
					root, tokens,
					JournalStructureImpl.RESERVED_ARTICLE_DISPLAY_DATE,
					article.getDisplayDate().toString());
			}

			JournalUtil.addReservedEl(
				root, tokens, JournalStructureImpl.RESERVED_ARTICLE_AUTHOR_ID,
				article.getUserId());

			String userName = StringPool.BLANK;
			String userEmailAddress = StringPool.BLANK;

			User user = null;

			try {
				user = UserLocalServiceUtil.getUserById(article.getUserId());

				userName = user.getFullName();
				userEmailAddress = user.getEmailAddress();
			}
			catch (NoSuchUserException nsue) {
			}

			JournalUtil.addReservedEl(
				root, tokens, JournalStructureImpl.RESERVED_ARTICLE_AUTHOR_NAME,
				userName);

			JournalUtil.addReservedEl(
				root, tokens,
				JournalStructureImpl.RESERVED_ARTICLE_AUTHOR_EMAIL_ADDRESS,
				userEmailAddress);

			if (article.isTemplateDriven()) {
				xml = JournalUtil.formatXML(doc);
			}
		}
		catch (DocumentException de) {
			throw new SystemException(de);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}

		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Transforming " + articleId + " " + version + " " +
						languageId);
			}

			String script = null;
			String langType = null;

			if (article.isTemplateDriven()) {
				JournalTemplate template =
					JournalTemplateUtil.findByPrimaryKey(new JournalTemplatePK(
						companyId, groupId, article.getTemplateId()));

				script = template.getXsl();
				langType = template.getLangType();
			}

			return JournalUtil.transform(
				tokens, languageId, xml, script, langType);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public List getArticles() throws SystemException {
		return JournalArticleUtil.findAll();
	}

	public List getArticles(long groupId) throws SystemException {
		return JournalArticleUtil.findByGroupId(groupId);
	}

	public List getArticles(long groupId, int begin, int end)
		throws SystemException {

		return JournalArticleUtil.findByGroupId(groupId, begin, end);
	}

	public List getArticles(
			long groupId, int begin, int end, OrderByComparator obc)
		throws SystemException {

		return JournalArticleUtil.findByGroupId(groupId, begin, end, obc);
	}

	public int getArticlesCount(long groupId) throws SystemException {
		return JournalArticleUtil.countByGroupId(groupId);
	}

	public JournalArticle getDisplayArticle(
			String companyId, long groupId, String articleId)
		throws PortalException, SystemException {

		List articles = JournalArticleUtil.findByC_G_A_A(
			companyId, groupId, articleId, true);

		if (articles.size() == 0) {
			throw new NoSuchArticleException();
		}

		Collections.sort(articles, new ArticleDisplayDateComparator());

		Date now = new Date();

		for (int i = 0; i < articles.size(); i++) {
			JournalArticle article = (JournalArticle)articles.get(i);

			if (article.getDisplayDate().before(now)) {
				return article;
			}
		}

		return (JournalArticle)articles.get(0);
	}

	public JournalArticle getLatestArticle(
			String companyId, long groupId, String articleId)
		throws PortalException, SystemException {

		return getLatestArticle(companyId, groupId, articleId, null);
	}

	public JournalArticle getLatestArticle(
			String companyId, long groupId, String articleId,
			Boolean approved)
		throws PortalException, SystemException {

		List articles = null;

		if (approved == null) {
			articles = JournalArticleUtil.findByC_G_A(
				companyId, groupId, articleId, 0, 1);
		}
		else {
			articles = JournalArticleUtil.findByC_G_A_A(
				companyId, groupId, articleId, approved.booleanValue(), 0, 1);
		}

		if (articles.size() == 0) {
			throw new NoSuchArticleException();
		}

		return (JournalArticle)articles.get(0);
	}

	public double getLatestVersion(
			String companyId, long groupId, String articleId)
		throws PortalException, SystemException {

		JournalArticle article = getLatestArticle(
			companyId, groupId, articleId);

		return article.getVersion();
	}

	public List getStructureArticles(
			String companyId, long groupId, String structureId)
		throws SystemException {

		return JournalArticleUtil.findByC_G_S(companyId, groupId, structureId);
	}

	public List getStructureArticles(
			String companyId, long groupId, String structureId, int begin,
			int end, OrderByComparator obc)
		throws SystemException {

		return JournalArticleUtil.findByC_G_S(
			companyId, groupId, structureId, begin, end, obc);
	}

	public int getStructureArticlesCount(
			String companyId, long groupId, String structureId)
		throws SystemException {

		return JournalArticleUtil.countByC_G_S(companyId, groupId, structureId);
	}

	public List getTemplateArticles(
			String companyId, long groupId, String templateId)
		throws SystemException {

		return JournalArticleUtil.findByC_G_T(companyId, groupId, templateId);
	}

	public List getTemplateArticles(
			String companyId, long groupId, String templateId, int begin,
			int end, OrderByComparator obc)
		throws SystemException {

		return JournalArticleUtil.findByC_G_T(
			companyId, groupId, templateId, begin, end, obc);
	}

	public int getTemplateArticlesCount(
			String companyId, long groupId, String templateId)
		throws SystemException {

		return JournalArticleUtil.countByC_G_T(companyId, groupId, templateId);
	}

	public boolean isLatestVersion(
			String companyId, long groupId, String articleId, double version)
		throws PortalException, SystemException {

		if (getLatestVersion(companyId, groupId, articleId) == version) {
			return true;
		}
		else {
			return false;
		}
	}

	public void reIndex(String[] ids) throws SystemException {
		try {
			String companyId = ids[0];

			Iterator itr = JournalArticleUtil.findByCompanyId(
				companyId).iterator();

			while (itr.hasNext()) {
				JournalArticle article = (JournalArticle)itr.next();

				long groupId = article.getGroupId();
				String articleId = article.getArticleId();
				double version = article.getVersion();
				String title = article.getTitle();
				String description = article.getDescription();
				String content = article.getContent();
				String type = article.getType();
				Date displayDate = article.getDisplayDate();

				if (article.isApproved()) {
					try {
						Indexer.addArticle(
							companyId, groupId, articleId, version, title,
							description, content, type, displayDate);
					}
					catch (Exception e1) {
						_log.error("Reindexing " + article.getPrimaryKey(), e1);
					}
				}
			}
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e2) {
			throw new SystemException(e2);
		}
	}

	public JournalArticle removeArticleLocale(
			String companyId, long groupId, String articleId, double version,
			String languageId)
		throws PortalException, SystemException {

		JournalArticle article = JournalArticleUtil.findByPrimaryKey(
			new JournalArticlePK(companyId, groupId, articleId, version));

		if (Validator.isNotNull(article.getStructureId())) {
			String content = JournalUtil.removeArticleLocale(
				article.getContent(), languageId);

			article.setContent(content);

			JournalArticleUtil.update(article);
		}

		return article;
	}

	public Hits search(
			String companyId, long groupId, String title, String description,
			String content, String type)
		throws SystemException {

		return search(
			companyId, groupId, title, description, content, type,
			"displayDate");
	}

	public Hits search(
			String companyId, long groupId, String title, String description,
			String content, String type, String sortField)
		throws SystemException {

		try {
			HitsImpl hits = new HitsImpl();

			if ((Validator.isNull(title)) && (Validator.isNull(content)) &&
				(Validator.isNull(type))) {

				return hits;
			}

			BooleanQuery contextQuery = new BooleanQuery();

			LuceneUtil.addRequiredTerm(
				contextQuery, LuceneFields.PORTLET_ID, Indexer.PORTLET_ID);
			LuceneUtil.addRequiredTerm(
				contextQuery, LuceneFields.GROUP_ID, groupId);

			BooleanQuery searchQuery = new BooleanQuery();

			LuceneUtil.addTerm(searchQuery, LuceneFields.TITLE, title);
			LuceneUtil.addTerm(searchQuery, LuceneFields.CONTENT, content);
			LuceneUtil.addTerm(
				searchQuery, LuceneFields.DESCRIPTION, description);

			if (Validator.isNotNull(type)) {
				LuceneUtil.addRequiredTerm(searchQuery, "type", type);
			}

			BooleanQuery fullQuery = new BooleanQuery();

			fullQuery.add(contextQuery, BooleanClause.Occur.MUST);
			fullQuery.add(searchQuery, BooleanClause.Occur.MUST);

			Searcher searcher = LuceneUtil.getSearcher(companyId);

			Sort sort = new Sort(new SortField(sortField, true));

			hits.recordHits(searcher.search(fullQuery, sort));

			return hits;
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
		catch (ParseException pe) {
			_log.error("Parsing keywords", pe);

			return new HitsImpl();
		}
	}

	public List search(
			String companyId, long groupId, String articleId, Double version,
			String title, String description, String content, String type,
			String structureId, String templateId, Date displayDateGT,
			Date displayDateLT, Boolean approved, Boolean expired,
			Date reviewDate, boolean andOperator, int begin, int end,
			OrderByComparator obc)
		throws SystemException {

		return JournalArticleFinder.findByC_G_A_V_T_D_C_T_S_T_D_A_E_R(
			companyId, groupId, articleId, version, title, description, content,
			type, structureId, templateId, displayDateGT, displayDateLT,
			approved, expired, reviewDate, andOperator, begin, end, obc);
	}

	public int searchCount(
			String companyId, long groupId, String articleId, Double version,
			String title, String description, String content, String type,
			String structureId, String templateId, Date displayDateGT,
			Date displayDateLT, Boolean approved, Boolean expired,
			Date reviewDate, boolean andOperator)
		throws SystemException {

		return JournalArticleFinder.countByC_G_A_V_T_D_C_T_S_T_D_A_E_R(
			companyId, groupId, articleId, version, title, description, content,
			type, structureId, templateId, displayDateGT, displayDateLT,
			approved, expired, reviewDate, andOperator);
	}

	public JournalArticle updateArticle(
			String userId, long groupId, String articleId, double version,
			boolean incrementVersion, String title, String description,
			String content, String type, String structureId, String templateId,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, int reviewDateMonth, int reviewDateDay,
			int reviewDateYear, int reviewDateHour, int reviewDateMinute,
			boolean neverReview, Map images, String articleURL,
			PortletPreferences prefs)
		throws PortalException, SystemException {

		// Article

		User user = UserUtil.findByPrimaryKey(userId);
		articleId = articleId.trim().toUpperCase();
		Date now = new Date();

		Date displayDate = PortalUtil.getDate(
			displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, user.getTimeZone(),
			new ArticleDisplayDateException());

		Date expirationDate = null;

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				new ArticleExpirationDateException());
		}

		Date reviewDate = null;

		if (!neverReview) {
			reviewDate = PortalUtil.getDate(
				reviewDateMonth, reviewDateDay, reviewDateYear, reviewDateHour,
				reviewDateMinute, user.getTimeZone(),
				new ArticleReviewDateException());
		}

		validate(
			user.getCompanyId(), title, content, groupId, structureId,
			templateId);

		JournalArticlePK oldPK = new JournalArticlePK(
			user.getCompanyId(), groupId, articleId, version);

		JournalArticle oldArticle = JournalArticleUtil.findByPrimaryKey(oldPK);

		JournalArticle article = null;

		if (incrementVersion) {
			double latestVersion = getLatestVersion(
				user.getCompanyId(), groupId, articleId);

			JournalArticlePK pk = new JournalArticlePK(
				user.getCompanyId(), groupId, articleId,
				MathUtil.format(latestVersion + 0.1, 1, 1));

			article = JournalArticleUtil.create(pk);

			article.setCompanyId(user.getCompanyId());
			article.setGroupId(oldArticle.getGroupId());
			article.setUserId(user.getUserId());
			article.setUserName(user.getFullName());
			article.setCreateDate(now);
		}
		else {
			article = oldArticle;
		}

		content = format(
			article.getCompanyId(), groupId, articleId, article.getVersion(),
			incrementVersion, content, structureId, images);

		boolean approved = oldArticle.isApproved();

		if (incrementVersion) {
			approved = false;
		}

		boolean expired = oldArticle.isExpired();

		if (incrementVersion) {
			expired = false;
		}

		article.setModifiedDate(now);
		article.setTitle(title);
		article.setDescription(description);
		article.setContent(content);
		article.setType(type);
		article.setStructureId(structureId);
		article.setTemplateId(templateId);
		article.setDisplayDate(displayDate);
		article.setApproved(approved);
		article.setExpired(expired);
		article.setExpirationDate(expirationDate);
		article.setReviewDate(reviewDate);

		JournalArticleUtil.update(article);

		// Email

		if (incrementVersion) {
			sendEmail(article, articleURL, prefs, "requested");
		}

		// Lucene

		try {
			if (article.isApproved()) {
				Indexer.updateArticle(
					article.getCompanyId(), article.getGroupId(),
					article.getArticleId(), article.getVersion(),
					article.getTitle(), article.getContent(),
					article.getContent(), article.getType(),
					article.getDisplayDate());
			}
		}
		catch (IOException ioe) {
			_log.error("Indexing " + article.getPrimaryKey(), ioe);
		}

		return article;
	}

	public JournalArticle updateContent(
			String companyId, long groupId, String articleId, double version,
			String content)
		throws PortalException, SystemException {

		JournalArticlePK pk = new JournalArticlePK(
			companyId, groupId, articleId, version);

		JournalArticle article = JournalArticleUtil.findByPrimaryKey(pk);

		article.setContent(content);

		JournalArticleUtil.update(article);

		return article;
	}

	protected String format(
		String companyId, long groupId, String articleId, double version,
		boolean incrementVersion, String content, String structureId,
		Map images) {

		if (Validator.isNotNull(structureId)) {
			SAXReader reader = new SAXReader();

			Document doc = null;

			try {
				doc = reader.read(new StringReader(content));

				Element root = doc.getRootElement();

				format(
					companyId, groupId, articleId, version, incrementVersion,
					root, images);

				content = JournalUtil.formatXML(doc);
			}
			catch (DocumentException de) {
				_log.error(de);
			}
			catch (IOException ioe) {
				_log.error(ioe);
			}
		}

		return content;
	}

	protected void format(
		String companyId, long groupId, String articleId, double version,
		boolean incrementVersion, Element root, Map images) {

		Iterator itr = root.elements().iterator();

		while (itr.hasNext()) {
			Element el = (Element)itr.next();

			String elName = el.attributeValue("name", StringPool.BLANK);
			String elType = el.attributeValue("type", StringPool.BLANK);

			if (elType.equals("image")) {
				formatImage(
					companyId, groupId, articleId, version, incrementVersion,
					el, elName, images);
			}
			else if (elType.equals("text_area")) {
				Element dynamicContent = el.element("dynamic-content");

				String text = dynamicContent.getText();

				if (Validator.isNull(Html.stripHtml(text))) {
					dynamicContent.setText(StringPool.BLANK);
				}
			}

			format(
				companyId, groupId, articleId, version, incrementVersion, el,
				images);
		}
	}

	protected void formatImage(
		String companyId, long groupId, String articleId, double version,
		boolean incrementVersion, Element el, String elName, Map images) {

		List imageContents = el.elements("dynamic-content");

		Iterator itr = imageContents.listIterator();

		while (itr.hasNext()) {
			Element dynamicContent = (Element)itr.next();

			String elLanguage = dynamicContent.attributeValue(
				"language-id", StringPool.BLANK);

			if (!elLanguage.equals(StringPool.BLANK)) {
				elLanguage = "_" + elLanguage;
			}

			String imageId =
				companyId + ".journal.article." + groupId + "." + articleId +
					"." + elName + elLanguage + "." + version;

			double oldVersion = MathUtil.format(version - 0.1, 1, 1);

			String oldImageId =
				companyId + ".journal.article." + groupId + "." + articleId +
					"." + elName + elLanguage + "." + oldVersion;

			String elContent =
				"/image/journal/article?img_id=" + groupId + "." + articleId +
					"." + elName + elLanguage + "&version=" + version;

			if (dynamicContent.getText().equals("delete")) {
				dynamicContent.setText(StringPool.BLANK);
				ImageLocalUtil.remove(imageId);

				String defaultElLanguage = "";

				if (!Validator.isNotNull(elLanguage)) {
					defaultElLanguage =
						"_" + LocaleUtil.toLanguageId(Locale.getDefault());
				}

				String defaultImageId =
					companyId + ".journal.article." + groupId + "." +
						articleId + "." + elName + defaultElLanguage + "." +
							version;

				ImageLocalUtil.remove(defaultImageId);

				continue;
			}

			byte[] bytes = (byte[])images.get(elName + elLanguage);

			if (bytes != null && (bytes.length > 0)) {
				dynamicContent.setText(elContent);
				dynamicContent.addAttribute("id", imageId);

				ImageLocalUtil.put(imageId, bytes);

				continue;
			}

			if ((version > JournalArticleImpl.DEFAULT_VERSION) &&
				(incrementVersion)) {

				Image oldImage = ImageLocalUtil.get(oldImageId);

				if (oldImage != null) {
					dynamicContent.setText(elContent);
					dynamicContent.addAttribute("id", imageId);

					bytes = oldImage.getTextObj();

					ImageLocalUtil.put(imageId, bytes);
				}

				continue;
			}

			Image image = ImageLocalUtil.get(imageId);

			if (image != null) {
				dynamicContent.setText(elContent);
				dynamicContent.addAttribute("id", imageId);

				continue;
			}

			String defaultElLanguage = "";

			if (!Validator.isNotNull(elLanguage)) {
				defaultElLanguage =
					"_" + LocaleUtil.toLanguageId(Locale.getDefault());
			}

			String defaultImageId =
				companyId + ".journal.article." + groupId + "." + articleId +
					"." + elName + defaultElLanguage + "." + version;

			Image defaultImage = ImageLocalUtil.get(defaultImageId);

			if (defaultImage != null) {
				dynamicContent.setText(elContent);
				dynamicContent.addAttribute("id", imageId);

				bytes = defaultImage.getTextObj();

				ImageLocalUtil.put(imageId, bytes);

				continue;
			}

			dynamicContent.setText(StringPool.BLANK);
		}
	}

	protected void sendEmail(
			JournalArticle article, String articleURL, PortletPreferences prefs,
			String emailType)
		throws PortalException, SystemException {

		try {
			if (emailType.equals("denied") &&
				JournalUtil.getEmailArticleApprovalDeniedEnabled(prefs)) {
			}
			else if (emailType.equals("granted") &&
					 JournalUtil.getEmailArticleApprovalGrantedEnabled(prefs)) {
			}
			else if (emailType.equals("requested") &&
					 JournalUtil.getEmailArticleApprovalRequestedEnabled(
						prefs)) {
			}
			else if (emailType.equals("review") &&
					 JournalUtil.getEmailArticleReviewEnabled(prefs)) {
			}
			else {
				return;
			}

			Company company = CompanyUtil.findByPrimaryKey(
				article.getCompanyId());

			User user = UserUtil.findByPrimaryKey(article.getUserId());

			articleURL +=
				"&groupId=" + article.getGroupId() + "&articleId=" +
					article.getArticleId() + "&version=" + article.getVersion();

			String portletName = PortalUtil.getPortletTitle(
				PortletKeys.JOURNAL, user);

			String fromName = JournalUtil.getEmailFromName(prefs);
			String fromAddress = JournalUtil.getEmailFromAddress(prefs);

			String toName = user.getFullName();
			String toAddress = user.getEmailAddress();

			if (emailType.equals("requested") ||
				emailType.equals("review")) {

				String tempToName = fromName;
				String tempToAddress = fromAddress;

				fromName = toName;
				fromAddress = toAddress;

				toName = tempToName;
				toAddress = tempToAddress;
			}

			String subject = null;
			String body = null;

			if (emailType.equals("denied")) {
				subject =
					JournalUtil.getEmailArticleApprovalDeniedSubject(prefs);
				body = JournalUtil.getEmailArticleApprovalDeniedBody(prefs);
			}
			else if (emailType.equals("granted")) {
				subject =
					JournalUtil.getEmailArticleApprovalGrantedSubject(prefs);
				body = JournalUtil.getEmailArticleApprovalGrantedBody(prefs);
			}
			else if (emailType.equals("requested")) {
				subject =
					JournalUtil.getEmailArticleApprovalRequestedSubject(prefs);
				body = JournalUtil.getEmailArticleApprovalRequestedBody(prefs);
			}
			else if (emailType.equals("review")) {
				subject = JournalUtil.getEmailArticleReviewSubject(prefs);
				body = JournalUtil.getEmailArticleReviewBody(prefs);
			}

			subject = StringUtil.replace(
				subject,
				new String[] {
					"[$ARTICLE_ID$]",
					"[$ARTICLE_TITLE$]",
					"[$ARTICLE_URL$]",
					"[$ARTICLE_VERSION$]",
					"[$FROM_ADDRESS$]",
					"[$FROM_NAME$]",
					"[$PORTAL_URL$]",
					"[$PORTLET_NAME$]",
					"[$TO_ADDRESS$]",
					"[$TO_NAME$]"
				},
				new String[] {
					article.getArticleId(),
					article.getTitle(),
					articleURL,
					String.valueOf(article.getVersion()),
					fromAddress,
					fromName,
					company.getPortalURL(),
					portletName,
					toAddress,
					toName,
				});

			body = StringUtil.replace(
				body,
				new String[] {
					"[$ARTICLE_ID$]",
					"[$ARTICLE_TITLE$]",
					"[$ARTICLE_URL$]",
					"[$ARTICLE_VERSION$]",
					"[$FROM_ADDRESS$]",
					"[$FROM_NAME$]",
					"[$PORTAL_URL$]",
					"[$PORTLET_NAME$]",
					"[$TO_ADDRESS$]",
					"[$TO_NAME$]"
				},
				new String[] {
					article.getArticleId(),
					article.getTitle(),
					articleURL,
					String.valueOf(article.getVersion()),
					fromAddress,
					fromName,
					company.getPortalURL(),
					portletName,
					toAddress,
					toName,
				});

			InternetAddress from = new InternetAddress(fromAddress, fromName);

			InternetAddress to = new InternetAddress(toAddress, toName);

			MailMessage message = new MailMessage(
				from, to, subject, body, true);

			MailServiceUtil.sendEmail(message);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
		catch (PortalException pe) {
			throw pe;
		}
	}

	protected void validate(
			String companyId, long groupId, String articleId,
			boolean autoArticleId, String title, String content,
			String structureId, String templateId)
		throws PortalException, SystemException {

		if (!autoArticleId) {
			if ((Validator.isNull(articleId)) ||
				(Validator.isNumber(articleId)) ||
				(articleId.indexOf(StringPool.SPACE) != -1)) {

				throw new ArticleIdException();
			}

			try {
				JournalArticleUtil.findByPrimaryKey(new JournalArticlePK(
					companyId, groupId, articleId,
					JournalArticleImpl.DEFAULT_VERSION));

				throw new DuplicateArticleIdException();
			}
			catch (NoSuchArticleException nste) {
			}
		}

		validate(companyId, title, content, groupId, structureId, templateId);
	}

	protected void validate(
			String companyId, String title, String content, long groupId,
			String structureId, String templateId)
		throws PortalException, SystemException {

		if (Validator.isNull(title)) {
			throw new ArticleTitleException();
		}
		else if (Validator.isNull(content)) {
			throw new ArticleContentException();
		}

		if (Validator.isNotNull(structureId)) {
			JournalStructureUtil.findByPrimaryKey(
				new JournalStructurePK(companyId, groupId, structureId));

			JournalTemplate template = JournalTemplateUtil.findByPrimaryKey(
				new JournalTemplatePK(companyId, groupId, templateId));

			if (!template.getStructureId().equals(structureId)) {
				throw new NoSuchTemplateException();
			}
		}
	}

	private static Log _log =
		LogFactory.getLog(JournalArticleLocalServiceImpl.class);

}