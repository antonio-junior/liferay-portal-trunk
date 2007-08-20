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

package com.liferay.portal.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.UserTracker;
import com.liferay.portal.model.UserTrackerPath;
import com.liferay.portal.service.base.UserTrackerLocalServiceBaseImpl;
import com.liferay.portal.service.persistence.UserTrackerPathUtil;
import com.liferay.portal.service.persistence.UserTrackerUtil;
import com.liferay.portal.util.PropsUtil;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="UserTrackerLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class UserTrackerLocalServiceImpl
	extends UserTrackerLocalServiceBaseImpl {

	public UserTracker addUserTracker(
			long companyId, long userId, Date modifiedDate, String sessionId,
			String remoteAddr, String remoteHost, String userAgent,
			List userTrackerPaths)
		throws SystemException {

		if (GetterUtil.getBoolean(PropsUtil.get(
				PropsUtil.SESSION_TRACKER_PERSISTENCE_ENABLED))) {

			long userTrackerId = CounterLocalServiceUtil.increment(
				UserTracker.class.getName());

			UserTracker userTracker = UserTrackerUtil.create(userTrackerId);

			userTracker.setCompanyId(companyId);
			userTracker.setUserId(userId);
			userTracker.setModifiedDate(modifiedDate);
			userTracker.setSessionId(sessionId);
			userTracker.setRemoteAddr(remoteAddr);
			userTracker.setRemoteHost(remoteHost);
			userTracker.setUserAgent(userAgent);

			UserTrackerUtil.update(userTracker);

			Iterator itr = userTrackerPaths.iterator();

			while (itr.hasNext()) {
				UserTrackerPath userTrackerPath = (UserTrackerPath)itr.next();

				long pathId = CounterLocalServiceUtil.increment(
					UserTrackerPath.class.getName());

				userTrackerPath.setUserTrackerPathId(pathId);
				userTrackerPath.setUserTrackerId(userTrackerId);

				UserTrackerPathUtil.update(userTrackerPath);
			}

			return userTracker;
		}
		else {
			return null;
		}
	}

	public void deleteUserTracker(long userTrackerId)
		throws PortalException, SystemException {

		// Paths

		UserTrackerPathUtil.removeByUserTrackerId(userTrackerId);

		// User tracker

		UserTrackerUtil.remove(userTrackerId);
	}

	public List getUserTrackers(long companyId, int begin, int end)
		throws SystemException {

		return UserTrackerUtil.findByCompanyId(companyId, begin, end);
	}

}