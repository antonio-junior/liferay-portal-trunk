/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.journal.service;


/**
 * <a href="JournalArticleImageLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link JournalArticleImageLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JournalArticleImageLocalService
 * @generated
 */
public class JournalArticleImageLocalServiceWrapper
	implements JournalArticleImageLocalService {
	public JournalArticleImageLocalServiceWrapper(
		JournalArticleImageLocalService journalArticleImageLocalService) {
		_journalArticleImageLocalService = journalArticleImageLocalService;
	}

	public com.liferay.portlet.journal.model.JournalArticleImage addJournalArticleImage(
		com.liferay.portlet.journal.model.JournalArticleImage journalArticleImage)
		throws com.liferay.portal.SystemException {
		return _journalArticleImageLocalService.addJournalArticleImage(journalArticleImage);
	}

	public com.liferay.portlet.journal.model.JournalArticleImage createJournalArticleImage(
		long articleImageId) {
		return _journalArticleImageLocalService.createJournalArticleImage(articleImageId);
	}

	public void deleteJournalArticleImage(long articleImageId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_journalArticleImageLocalService.deleteJournalArticleImage(articleImageId);
	}

	public void deleteJournalArticleImage(
		com.liferay.portlet.journal.model.JournalArticleImage journalArticleImage)
		throws com.liferay.portal.SystemException {
		_journalArticleImageLocalService.deleteJournalArticleImage(journalArticleImage);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _journalArticleImageLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _journalArticleImageLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	public com.liferay.portlet.journal.model.JournalArticleImage getJournalArticleImage(
		long articleImageId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _journalArticleImageLocalService.getJournalArticleImage(articleImageId);
	}

	public java.util.List<com.liferay.portlet.journal.model.JournalArticleImage> getJournalArticleImages(
		int start, int end) throws com.liferay.portal.SystemException {
		return _journalArticleImageLocalService.getJournalArticleImages(start,
			end);
	}

	public int getJournalArticleImagesCount()
		throws com.liferay.portal.SystemException {
		return _journalArticleImageLocalService.getJournalArticleImagesCount();
	}

	public com.liferay.portlet.journal.model.JournalArticleImage updateJournalArticleImage(
		com.liferay.portlet.journal.model.JournalArticleImage journalArticleImage)
		throws com.liferay.portal.SystemException {
		return _journalArticleImageLocalService.updateJournalArticleImage(journalArticleImage);
	}

	public com.liferay.portlet.journal.model.JournalArticleImage updateJournalArticleImage(
		com.liferay.portlet.journal.model.JournalArticleImage journalArticleImage,
		boolean merge) throws com.liferay.portal.SystemException {
		return _journalArticleImageLocalService.updateJournalArticleImage(journalArticleImage,
			merge);
	}

	public void addArticleImageId(long articleImageId, long groupId,
		java.lang.String articleId, double version,
		java.lang.String elInstanceId, java.lang.String elName,
		java.lang.String languageId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_journalArticleImageLocalService.addArticleImageId(articleImageId,
			groupId, articleId, version, elInstanceId, elName, languageId);
	}

	public void deleteArticleImage(long articleImageId)
		throws com.liferay.portal.SystemException {
		_journalArticleImageLocalService.deleteArticleImage(articleImageId);
	}

	public void deleteArticleImage(
		com.liferay.portlet.journal.model.JournalArticleImage articleImage)
		throws com.liferay.portal.SystemException {
		_journalArticleImageLocalService.deleteArticleImage(articleImage);
	}

	public void deleteArticleImage(long groupId, java.lang.String articleId,
		double version, java.lang.String elInstanceId, java.lang.String elName,
		java.lang.String languageId) throws com.liferay.portal.SystemException {
		_journalArticleImageLocalService.deleteArticleImage(groupId, articleId,
			version, elInstanceId, elName, languageId);
	}

	public void deleteImages(long groupId, java.lang.String articleId,
		double version) throws com.liferay.portal.SystemException {
		_journalArticleImageLocalService.deleteImages(groupId, articleId,
			version);
	}

	public com.liferay.portlet.journal.model.JournalArticleImage getArticleImage(
		long articleImageId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _journalArticleImageLocalService.getArticleImage(articleImageId);
	}

	public long getArticleImageId(long groupId, java.lang.String articleId,
		double version, java.lang.String elInstanceId, java.lang.String elName,
		java.lang.String languageId) throws com.liferay.portal.SystemException {
		return _journalArticleImageLocalService.getArticleImageId(groupId,
			articleId, version, elInstanceId, elName, languageId);
	}

	public long getArticleImageId(long groupId, java.lang.String articleId,
		double version, java.lang.String elInstanceId, java.lang.String elName,
		java.lang.String languageId, boolean tempImage)
		throws com.liferay.portal.SystemException {
		return _journalArticleImageLocalService.getArticleImageId(groupId,
			articleId, version, elInstanceId, elName, languageId, tempImage);
	}

	public java.util.List<com.liferay.portlet.journal.model.JournalArticleImage> getArticleImages(
		long groupId) throws com.liferay.portal.SystemException {
		return _journalArticleImageLocalService.getArticleImages(groupId);
	}

	public JournalArticleImageLocalService getWrappedJournalArticleImageLocalService() {
		return _journalArticleImageLocalService;
	}

	private JournalArticleImageLocalService _journalArticleImageLocalService;
}