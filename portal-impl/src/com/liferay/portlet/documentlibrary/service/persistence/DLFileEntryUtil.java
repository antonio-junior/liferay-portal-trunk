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

package com.liferay.portlet.documentlibrary.service.persistence;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.util.PropsUtil;

import com.liferay.portal.kernel.util.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="DLFileEntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class DLFileEntryUtil {
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry create(
		long fileEntryId) {
		return getPersistence().create(fileEntryId);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry remove(
		long fileEntryId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(findByPrimaryKey(fileEntryId));
		}

		com.liferay.portlet.documentlibrary.model.DLFileEntry dlFileEntry = getPersistence()
																				.remove(fileEntryId);

		if (listener != null) {
			listener.onAfterRemove(dlFileEntry);
		}

		return dlFileEntry;
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry remove(
		com.liferay.portlet.documentlibrary.model.DLFileEntry dlFileEntry)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(dlFileEntry);
		}

		dlFileEntry = getPersistence().remove(dlFileEntry);

		if (listener != null) {
			listener.onAfterRemove(dlFileEntry);
		}

		return dlFileEntry;
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry update(
		com.liferay.portlet.documentlibrary.model.DLFileEntry dlFileEntry)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();
		boolean isNew = dlFileEntry.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(dlFileEntry);
			}
			else {
				listener.onBeforeUpdate(dlFileEntry);
			}
		}

		dlFileEntry = getPersistence().update(dlFileEntry);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(dlFileEntry);
			}
			else {
				listener.onAfterUpdate(dlFileEntry);
			}
		}

		return dlFileEntry;
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry update(
		com.liferay.portlet.documentlibrary.model.DLFileEntry dlFileEntry,
		boolean saveOrUpdate) throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();
		boolean isNew = dlFileEntry.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(dlFileEntry);
			}
			else {
				listener.onBeforeUpdate(dlFileEntry);
			}
		}

		dlFileEntry = getPersistence().update(dlFileEntry, saveOrUpdate);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(dlFileEntry);
			}
			else {
				listener.onAfterUpdate(dlFileEntry);
			}
		}

		return dlFileEntry;
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByPrimaryKey(
		long fileEntryId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().findByPrimaryKey(fileEntryId);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry fetchByPrimaryKey(
		long fileEntryId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(fileEntryId);
	}

	public static java.util.List findByFolderId(long folderId)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByFolderId(folderId);
	}

	public static java.util.List findByFolderId(long folderId, int begin,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findByFolderId(folderId, begin, end);
	}

	public static java.util.List findByFolderId(long folderId, int begin,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByFolderId(folderId, begin, end, obc);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByFolderId_First(
		long folderId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().findByFolderId_First(folderId, obc);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByFolderId_Last(
		long folderId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().findByFolderId_Last(folderId, obc);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry[] findByFolderId_PrevAndNext(
		long fileEntryId, long folderId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().findByFolderId_PrevAndNext(fileEntryId,
			folderId, obc);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByF_N(
		long folderId, java.lang.String name)
		throws com.liferay.portal.SystemException, 
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().findByF_N(folderId, name);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry fetchByF_N(
		long folderId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByF_N(folderId, name);
	}

	public static java.util.List findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(queryInitializer);
	}

	public static java.util.List findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(queryInitializer, begin,
			end);
	}

	public static java.util.List findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List findAll(int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end);
	}

	public static java.util.List findAll(int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end, obc);
	}

	public static void removeByFolderId(long folderId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByFolderId(folderId);
	}

	public static void removeByF_N(long folderId, java.lang.String name)
		throws com.liferay.portal.SystemException, 
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		getPersistence().removeByF_N(folderId, name);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByFolderId(long folderId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByFolderId(folderId);
	}

	public static int countByF_N(long folderId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByF_N(folderId, name);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static DLFileEntryPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(DLFileEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static DLFileEntryUtil _getUtil() {
		if (_util == null) {
			_util = (DLFileEntryUtil)com.liferay.portal.kernel.bean.BeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static ModelListener _getListener() {
		if (Validator.isNotNull(_LISTENER)) {
			try {
				return (ModelListener)Class.forName(_LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		return null;
	}

	private static final String _UTIL = DLFileEntryUtil.class.getName();
	private static final String _LISTENER = GetterUtil.getString(PropsUtil.get(
				"value.object.listener.com.liferay.portlet.documentlibrary.model.DLFileEntry"));
	private static Log _log = LogFactory.getLog(DLFileEntryUtil.class);
	private static DLFileEntryUtil _util;
	private DLFileEntryPersistence _persistence;
}