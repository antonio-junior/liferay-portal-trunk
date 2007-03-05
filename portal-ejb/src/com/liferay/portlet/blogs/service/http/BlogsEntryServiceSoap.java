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

package com.liferay.portlet.blogs.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.portlet.blogs.service.BlogsEntryServiceUtil;

import java.rmi.RemoteException;

/**
 * <a href="BlogsEntryServiceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class BlogsEntryServiceSoap {
	public static com.liferay.portlet.blogs.model.BlogsEntrySoap addEntry(
		java.lang.String plid, long categoryId, java.lang.String title,
		java.lang.String content, int displayDateMonth, int displayDateDay,
		int displayDateYear, int displayDateHour, int displayDateMinute,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws RemoteException {
		try {
			com.liferay.portlet.blogs.model.BlogsEntry returnValue = BlogsEntryServiceUtil.addEntry(plid,
					categoryId, title, content, displayDateMonth,
					displayDateDay, displayDateYear, displayDateHour,
					displayDateMinute, addCommunityPermissions,
					addGuestPermissions);

			return com.liferay.portlet.blogs.model.BlogsEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);
			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.blogs.model.BlogsEntrySoap addEntry(
		java.lang.String plid, long categoryId, java.lang.String title,
		java.lang.String content, int displayDateMonth, int displayDateDay,
		int displayDateYear, int displayDateHour, int displayDateMinute,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions) throws RemoteException {
		try {
			com.liferay.portlet.blogs.model.BlogsEntry returnValue = BlogsEntryServiceUtil.addEntry(plid,
					categoryId, title, content, displayDateMonth,
					displayDateDay, displayDateYear, displayDateHour,
					displayDateMinute, communityPermissions, guestPermissions);

			return com.liferay.portlet.blogs.model.BlogsEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);
			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteEntry(long entryId) throws RemoteException {
		try {
			BlogsEntryServiceUtil.deleteEntry(entryId);
		}
		catch (Exception e) {
			_log.error(e, e);
			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.blogs.model.BlogsEntrySoap getEntry(
		long entryId) throws RemoteException {
		try {
			com.liferay.portlet.blogs.model.BlogsEntry returnValue = BlogsEntryServiceUtil.getEntry(entryId);

			return com.liferay.portlet.blogs.model.BlogsEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);
			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.blogs.model.BlogsEntrySoap updateEntry(
		long entryId, long categoryId, java.lang.String title,
		java.lang.String content, int displayDateMonth, int displayDateDay,
		int displayDateYear, int displayDateHour, int displayDateMinute)
		throws RemoteException {
		try {
			com.liferay.portlet.blogs.model.BlogsEntry returnValue = BlogsEntryServiceUtil.updateEntry(entryId,
					categoryId, title, content, displayDateMonth,
					displayDateDay, displayDateYear, displayDateHour,
					displayDateMinute);

			return com.liferay.portlet.blogs.model.BlogsEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);
			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(BlogsEntryServiceSoap.class);
}