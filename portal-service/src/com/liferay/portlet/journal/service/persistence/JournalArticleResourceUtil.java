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

package com.liferay.portlet.journal.service.persistence;

/**
 * <a href="JournalArticleResourceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JournalArticleResourceUtil {
	public static com.liferay.portlet.journal.model.JournalArticleResource create(
		long resourcePrimKey) {
		return getPersistence().create(resourcePrimKey);
	}

	public static com.liferay.portlet.journal.model.JournalArticleResource remove(
		long resourcePrimKey)
		throws com.liferay.portal.SystemException,
			com.liferay.portlet.journal.NoSuchArticleResourceException {
		return getPersistence().remove(resourcePrimKey);
	}

	public static com.liferay.portlet.journal.model.JournalArticleResource remove(
		com.liferay.portlet.journal.model.JournalArticleResource journalArticleResource)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(journalArticleResource);
	}

	/**
	 * @deprecated Use <code>update(JournalArticleResource journalArticleResource, boolean merge)</code>.
	 */
	public static com.liferay.portlet.journal.model.JournalArticleResource update(
		com.liferay.portlet.journal.model.JournalArticleResource journalArticleResource)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(journalArticleResource);
	}

	/**
	 * Add, update, or merge, the entity. This method also calls the model
	 * listeners to trigger the proper events associated with adding, deleting,
	 * or updating an entity.
	 *
	 * @param        journalArticleResource the entity to add, update, or merge
	 * @param        merge boolean value for whether to merge the entity. The
	 *                default value is false. Setting merge to true is more
	 *                expensive and should only be true when journalArticleResource is
	 *                transient. See LEP-5473 for a detailed discussion of this
	 *                method.
	 * @return        true if the portlet can be displayed via Ajax
	 */
	public static com.liferay.portlet.journal.model.JournalArticleResource update(
		com.liferay.portlet.journal.model.JournalArticleResource journalArticleResource,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().update(journalArticleResource, merge);
	}

	public static com.liferay.portlet.journal.model.JournalArticleResource updateImpl(
		com.liferay.portlet.journal.model.JournalArticleResource journalArticleResource,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(journalArticleResource, merge);
	}

	public static com.liferay.portlet.journal.model.JournalArticleResource findByPrimaryKey(
		long resourcePrimKey)
		throws com.liferay.portal.SystemException,
			com.liferay.portlet.journal.NoSuchArticleResourceException {
		return getPersistence().findByPrimaryKey(resourcePrimKey);
	}

	public static com.liferay.portlet.journal.model.JournalArticleResource fetchByPrimaryKey(
		long resourcePrimKey) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(resourcePrimKey);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalArticleResource> findByGroupId(
		long groupId) throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalArticleResource> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalArticleResource> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId, start, end, obc);
	}

	public static com.liferay.portlet.journal.model.JournalArticleResource findByGroupId_First(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portlet.journal.NoSuchArticleResourceException {
		return getPersistence().findByGroupId_First(groupId, obc);
	}

	public static com.liferay.portlet.journal.model.JournalArticleResource findByGroupId_Last(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portlet.journal.NoSuchArticleResourceException {
		return getPersistence().findByGroupId_Last(groupId, obc);
	}

	public static com.liferay.portlet.journal.model.JournalArticleResource[] findByGroupId_PrevAndNext(
		long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portlet.journal.NoSuchArticleResourceException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(resourcePrimKey, groupId, obc);
	}

	public static com.liferay.portlet.journal.model.JournalArticleResource findByG_A(
		long groupId, java.lang.String articleId)
		throws com.liferay.portal.SystemException,
			com.liferay.portlet.journal.NoSuchArticleResourceException {
		return getPersistence().findByG_A(groupId, articleId);
	}

	public static com.liferay.portlet.journal.model.JournalArticleResource fetchByG_A(
		long groupId, java.lang.String articleId)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByG_A(groupId, articleId);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalArticleResource> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(queryInitializer);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalArticleResource> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findWithDynamicQuery(queryInitializer, start, end);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalArticleResource> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalArticleResource> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalArticleResource> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	public static void removeByG_A(long groupId, java.lang.String articleId)
		throws com.liferay.portal.SystemException,
			com.liferay.portlet.journal.NoSuchArticleResourceException {
		getPersistence().removeByG_A(groupId, articleId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByGroupId(long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	public static int countByG_A(long groupId, java.lang.String articleId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByG_A(groupId, articleId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static JournalArticleResourcePersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(JournalArticleResourcePersistence persistence) {
		_persistence = persistence;
	}

	private static JournalArticleResourceUtil _getUtil() {
		if (_util == null) {
			_util = (JournalArticleResourceUtil)com.liferay.portal.kernel.bean.BeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = JournalArticleResourceUtil.class.getName();
	private static JournalArticleResourceUtil _util;
	private JournalArticleResourcePersistence _persistence;
}