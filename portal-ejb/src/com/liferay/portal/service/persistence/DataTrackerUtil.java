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

package com.liferay.portal.service.persistence;

import com.liferay.portal.model.ModelListener;
import com.liferay.portal.util.PropsUtil;

import com.liferay.util.GetterUtil;
import com.liferay.util.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.ApplicationContext;

/**
 * <a href="DataTrackerUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class DataTrackerUtil {
	public static com.liferay.portal.model.DataTracker create(
		java.lang.String dataTrackerId) {
		return getPersistence().create(dataTrackerId);
	}

	public static com.liferay.portal.model.DataTracker remove(
		java.lang.String dataTrackerId)
		throws com.liferay.portal.NoSuchDataTrackerException, 
			com.liferay.portal.SystemException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(findByPrimaryKey(dataTrackerId));
		}

		com.liferay.portal.model.DataTracker dataTracker = getPersistence()
															   .remove(dataTrackerId);

		if (listener != null) {
			listener.onAfterRemove(dataTracker);
		}

		return dataTracker;
	}

	public static com.liferay.portal.model.DataTracker remove(
		com.liferay.portal.model.DataTracker dataTracker)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(dataTracker);
		}

		dataTracker = getPersistence().remove(dataTracker);

		if (listener != null) {
			listener.onAfterRemove(dataTracker);
		}

		return dataTracker;
	}

	public static com.liferay.portal.model.DataTracker update(
		com.liferay.portal.model.DataTracker dataTracker)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();
		boolean isNew = dataTracker.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(dataTracker);
			}
			else {
				listener.onBeforeUpdate(dataTracker);
			}
		}

		dataTracker = getPersistence().update(dataTracker);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(dataTracker);
			}
			else {
				listener.onAfterUpdate(dataTracker);
			}
		}

		return dataTracker;
	}

	public static com.liferay.portal.model.DataTracker update(
		com.liferay.portal.model.DataTracker dataTracker, boolean saveOrUpdate)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();
		boolean isNew = dataTracker.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(dataTracker);
			}
			else {
				listener.onBeforeUpdate(dataTracker);
			}
		}

		dataTracker = getPersistence().update(dataTracker, saveOrUpdate);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(dataTracker);
			}
			else {
				listener.onAfterUpdate(dataTracker);
			}
		}

		return dataTracker;
	}

	public static com.liferay.portal.model.DataTracker findByPrimaryKey(
		java.lang.String dataTrackerId)
		throws com.liferay.portal.NoSuchDataTrackerException, 
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(dataTrackerId);
	}

	public static com.liferay.portal.model.DataTracker fetchByPrimaryKey(
		java.lang.String dataTrackerId)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(dataTrackerId);
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
		com.liferay.util.dao.hibernate.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end, obc);
	}

	public static void initDao() {
		getPersistence().initDao();
	}

	public static DataTrackerPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(DataTrackerPersistence persistence) {
		_persistence = persistence;
	}

	private static DataTrackerUtil _getUtil() {
		if (_util == null) {
			_util = (DataTrackerUtil)com.liferay.portal.kernel.bean.BeanLocatorUtil.locate(_UTIL);
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

	private static final String _UTIL = DataTrackerUtil.class.getName();
	private static final String _LISTENER = GetterUtil.getString(PropsUtil.get(
				"value.object.listener.com.liferay.portal.model.DataTracker"));
	private static Log _log = LogFactory.getLog(DataTrackerUtil.class);
	private static DataTrackerUtil _util;
	private DataTrackerPersistence _persistence;
}