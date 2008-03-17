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

package com.liferay.portlet.announcements.service.impl;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portlet.announcements.model.AnnouncementFlag;
import com.liferay.portlet.announcements.service.base.AnnouncementFlagLocalServiceBaseImpl;

import java.util.Date;

/**
 * <a href="AnnouncementFlagLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Thiago Moreira
 * @author Raymond Augé
 *
 */
public class AnnouncementFlagLocalServiceImpl extends
		AnnouncementFlagLocalServiceBaseImpl {

	public AnnouncementFlag addAnnouncementFlag(
			long userId, long announcementId, int flag)
		throws PortalException, SystemException {

		long announcementFlagId = counterLocalService.increment();

		AnnouncementFlag announcementFlag =
			announcementFlagPersistence.create(announcementFlagId);

		announcementFlag.setUserId(userId);
		announcementFlag.setAnnouncementId(announcementId);
		announcementFlag.setFlag(flag);
		announcementFlag.setFlagDate(new Date());

		announcementFlagPersistence.update(announcementFlag);

		return announcementFlag;
	}

	public AnnouncementFlag getAnnouncementFlag(
			long userId, long announcementId, int flag)
		throws PortalException, SystemException {

		return announcementFlagPersistence.fetchByU_A_F(
			userId, announcementId, flag);
	}

	public void deleteAnnouncementFlag(long announcementFlagId)
		throws PortalException, SystemException {

		announcementFlagPersistence.remove(announcementFlagId);
	}

	public void deleteAnnouncementFlags(long announcementId)
		throws PortalException, SystemException {

		announcementFlagPersistence.removeByAnnouncementId(announcementId);
	}

}