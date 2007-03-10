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
import com.liferay.portal.model.PasswordTracker;
import com.liferay.portal.model.User;
import com.liferay.portal.security.pwd.PwdEncryptor;
import com.liferay.portal.service.base.PasswordTrackerLocalServiceBaseImpl;
import com.liferay.portal.service.persistence.PasswordTrackerUtil;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.util.GetterUtil;
import com.liferay.util.Time;

import java.util.Date;
import java.util.Iterator;

/**
 * <a href="PasswordTrackerLocalServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PasswordTrackerLocalServiceImpl
	extends PasswordTrackerLocalServiceBaseImpl {

	public void deletePasswordTrackers(String userId) throws SystemException {
		PasswordTrackerUtil.removeByUserId(userId);
	}

	public boolean isValidPassword(String userId, String password)
		throws PortalException, SystemException {

		int passwordsRecycle = GetterUtil.getInteger(
			PropsUtil.get(PropsUtil.PASSWORDS_RECYCLE));

		if (passwordsRecycle > 0) {
			String newEncPwd = PwdEncryptor.encrypt(password);

			User user = UserUtil.findByPrimaryKey(userId);

			String oldEncPwd = user.getPassword();
			if (!user.isPasswordEncrypted()) {
				oldEncPwd = PwdEncryptor.encrypt(user.getPassword());
			}

			if (oldEncPwd.equals(newEncPwd)) {
				return false;
			}

			Date now = new Date();

			Iterator itr = PasswordTrackerUtil.findByUserId(userId).iterator();

			while (itr.hasNext()) {
				PasswordTracker passwordTracker = (PasswordTracker)itr.next();

				Date recycleDate = new Date(
					passwordTracker.getCreateDate().getTime() +
					Time.DAY * passwordsRecycle);

				if (recycleDate.after(now)) {
					if (passwordTracker.getPassword().equals(newEncPwd)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public void trackPassword(String userId, String encPwd)
		throws PortalException, SystemException {

		long passwordTrackerId = CounterLocalServiceUtil.increment(
			PasswordTracker.class.getName());

		PasswordTracker passwordTracker =
			PasswordTrackerUtil.create(passwordTrackerId);

		passwordTracker.setUserId(userId);
		passwordTracker.setCreateDate(new Date());
		passwordTracker.setPassword(encPwd);

		PasswordTrackerUtil.update(passwordTracker);
	}

}