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

package com.liferay.portlet.announcements.service;


/**
 * <a href="AnnouncementServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.liferay.portlet.announcements.service.AnnouncementService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * <p>
 * <code>com.liferay.portlet.announcements.service.AnnouncementServiceFactory</code>
 * is responsible for the lookup of the bean.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portlet.announcements.service.AnnouncementService
 * @see com.liferay.portlet.announcements.service.AnnouncementServiceFactory
 *
 */
public class AnnouncementServiceUtil {
	public static com.liferay.portlet.announcements.model.Announcement addAnnouncement(
		long userId, long plid, long classNameId, long classPK,
		java.lang.String title, java.lang.String content, java.lang.String url,
		java.lang.String type, int displayMonth, int displayDay,
		int displayYear, int expirationMonth, int expirationDay,
		int expirationYear, int priority, boolean alert)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		AnnouncementService announcementService = AnnouncementServiceFactory.getService();

		return announcementService.addAnnouncement(userId, plid, classNameId,
			classPK, title, content, url, type, displayMonth, displayDay,
			displayYear, expirationMonth, expirationDay, expirationYear,
			priority, alert);
	}

	public static void deleteAnnouncement(long announcementId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		AnnouncementService announcementService = AnnouncementServiceFactory.getService();

		announcementService.deleteAnnouncement(announcementId);
	}

	public static com.liferay.portlet.announcements.model.Announcement updateAnnouncement(
		long announcementId, java.lang.String title, java.lang.String content,
		java.lang.String url, java.lang.String type, int displayMonth,
		int displayDay, int displayYear, int expirationMonth,
		int expirationDay, int expirationYear, int priority)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		AnnouncementService announcementService = AnnouncementServiceFactory.getService();

		return announcementService.updateAnnouncement(announcementId, title,
			content, url, type, displayMonth, displayDay, displayYear,
			expirationMonth, expirationDay, expirationYear, priority);
	}
}