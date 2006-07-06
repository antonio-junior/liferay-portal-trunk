/**
 * Copyright (c) 2000-2006 Liferay, LLC. All rights reserved.
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

package com.liferay.portlet.messageboards.service.impl;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.persistence.MBMessageFlagFinder;
import com.liferay.portlet.messageboards.service.persistence.MBMessageUtil;
import com.liferay.portlet.messageboards.service.persistence.MBThreadFinder;
import com.liferay.portlet.messageboards.service.persistence.MBThreadUtil;
import com.liferay.portlet.messageboards.service.spring.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.spring.MBThreadLocalService;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="MBThreadLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class MBThreadLocalServiceImpl implements MBThreadLocalService {

	public void deleteThread(String threadId)
		throws PortalException, SystemException {

		MBThread thread = MBThreadUtil.findByPrimaryKey(threadId);

		deleteThread(thread);
	}

	public void deleteThread(MBThread thread)
		throws PortalException, SystemException {

		Iterator itr = MBMessageUtil.findByThreadId(
			thread.getThreadId()).iterator();

		while (itr.hasNext()) {
			MBMessage message = (MBMessage)itr.next();

			MBMessageLocalServiceUtil.deleteMessage(message);
		}
	}

	public void deleteThreads(String categoryId)
		throws PortalException, SystemException {

		Iterator itr = MBThreadUtil.findByCategoryId(categoryId).iterator();

		while (itr.hasNext()) {
			MBThread thread = (MBThread)itr.next();

			deleteThread(thread);
		}
	}

	public List getGroupThreads(String groupId, int begin, int end)
		throws SystemException {

		return MBThreadFinder.findByGroupId(groupId, begin, end);
	}

	public int getGroupThreadsCount(String groupId) throws SystemException {
		return MBThreadFinder.countByGroupId(groupId);
	}

	public List getThreads(String categoryId, int begin, int end)
		throws SystemException {

		return MBThreadUtil.findByCategoryId(categoryId, begin, end);
	}

	public int getThreadsCount(String categoryId) throws SystemException {
		return MBThreadUtil.countByCategoryId(categoryId);
	}

	public boolean hasReadThread(String userId, String threadId)
		throws SystemException {

		if (userId == null) {

			// Unauthenticated users do not have a record of read messages

			return true;
		}

		int total = MBMessageUtil.countByThreadId(threadId);
		int read = MBMessageFlagFinder.countByT_U(threadId, userId);

		if (total != read) {
			return false;
		}
		else {
			return true;
		}
	}

	private static Log _log = LogFactory.getLog(MBThreadLocalServiceImpl.class);

}