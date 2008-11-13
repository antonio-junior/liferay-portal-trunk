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

package com.liferay.portlet.blogs.service.http;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

import com.liferay.portlet.blogs.service.BlogsEntryServiceUtil;

/**
 * <a href="BlogsEntryServiceJSON.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides a JSON utility for the
 * <code>com.liferay.portlet.blogs.service.BlogsEntryServiceUtil</code>
 * service utility. The static methods of this class calls the same methods of
 * the service utility. However, the signatures are different because it is
 * difficult for JSON to support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to a
 * <code>com.liferay.portal.kernel.json.JSONArray</code>. If the method in the
 * service utility returns a <code>com.liferay.portlet.blogs.model.BlogsEntry</code>,
 * that is translated to a
 * <code>com.liferay.portal.kernel.json.JSONObject</code>. Methods that JSON
 * cannot safely use are skipped. The logic for the translation is encapsulated
 * in <code>com.liferay.portlet.blogs.service.http.BlogsEntryJSONSerializer</code>.
 * </p>
 *
 * <p>
 * This allows you to call the the backend services directly from JavaScript.
 * See <code>portal-web/docroot/html/portlet/tags_admin/unpacked.js</code> for a
 * reference of how that portlet uses the generated JavaScript in
 * <code>portal-web/docroot/html/js/service.js</code> to call the backend
 * services directly from JavaScript.
 * </p>
 *
 * <p>
 * The JSON utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portlet.blogs.service.BlogsEntryServiceUtil
 * @see com.liferay.portlet.blogs.service.http.BlogsEntryJSONSerializer
 *
 */
public class BlogsEntryServiceJSON {
	public static JSONObject addEntry(java.lang.String title,
		java.lang.String content, int displayDateMonth, int displayDateDay,
		int displayDateYear, int displayDateHour, int displayDateMinute,
		boolean draft, boolean allowTrackbacks, java.lang.String[] trackbacks,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		com.liferay.portlet.blogs.model.BlogsEntry returnValue = BlogsEntryServiceUtil.addEntry(title,
				content, displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, draft, allowTrackbacks,
				trackbacks, serviceContext);

		return BlogsEntryJSONSerializer.toJSONObject(returnValue);
	}

	public static void deleteEntry(long entryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		BlogsEntryServiceUtil.deleteEntry(entryId);
	}

	public static JSONArray getCompanyEntries(long companyId, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		java.util.List<com.liferay.portlet.blogs.model.BlogsEntry> returnValue = BlogsEntryServiceUtil.getCompanyEntries(companyId,
				max);

		return BlogsEntryJSONSerializer.toJSONArray(returnValue);
	}

	public static JSONArray getPublishedCompanyEntries(long companyId, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		java.util.List<com.liferay.portlet.blogs.model.BlogsEntry> returnValue = BlogsEntryServiceUtil.getPublishedCompanyEntries(companyId,
				max);

		return BlogsEntryJSONSerializer.toJSONArray(returnValue);
	}

	public static JSONObject getEntry(long entryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		com.liferay.portlet.blogs.model.BlogsEntry returnValue = BlogsEntryServiceUtil.getEntry(entryId);

		return BlogsEntryJSONSerializer.toJSONObject(returnValue);
	}

	public static JSONObject getEntry(long groupId, java.lang.String urlTitle)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		com.liferay.portlet.blogs.model.BlogsEntry returnValue = BlogsEntryServiceUtil.getEntry(groupId,
				urlTitle);

		return BlogsEntryJSONSerializer.toJSONObject(returnValue);
	}

	public static JSONArray getGroupEntries(long groupId, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		java.util.List<com.liferay.portlet.blogs.model.BlogsEntry> returnValue = BlogsEntryServiceUtil.getGroupEntries(groupId,
				max);

		return BlogsEntryJSONSerializer.toJSONArray(returnValue);
	}

	public static JSONArray getPublishedGroupEntries(long groupId, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		java.util.List<com.liferay.portlet.blogs.model.BlogsEntry> returnValue = BlogsEntryServiceUtil.getPublishedGroupEntries(groupId,
				max);

		return BlogsEntryJSONSerializer.toJSONArray(returnValue);
	}

	public static JSONArray getOrganizationEntries(long organizationId, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		java.util.List<com.liferay.portlet.blogs.model.BlogsEntry> returnValue = BlogsEntryServiceUtil.getOrganizationEntries(organizationId,
				max);

		return BlogsEntryJSONSerializer.toJSONArray(returnValue);
	}

	public static JSONArray getPublishedOrganizationEntries(
		long organizationId, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		java.util.List<com.liferay.portlet.blogs.model.BlogsEntry> returnValue = BlogsEntryServiceUtil.getPublishedOrganizationEntries(organizationId,
				max);

		return BlogsEntryJSONSerializer.toJSONArray(returnValue);
	}

	public static JSONObject updateEntry(long entryId, java.lang.String title,
		java.lang.String content, int displayDateMonth, int displayDateDay,
		int displayDateYear, int displayDateHour, int displayDateMinute,
		boolean draft, boolean allowTrackbacks, java.lang.String[] trackbacks,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		com.liferay.portlet.blogs.model.BlogsEntry returnValue = BlogsEntryServiceUtil.updateEntry(entryId,
				title, content, displayDateMonth, displayDateDay,
				displayDateYear, displayDateHour, displayDateMinute, draft,
				allowTrackbacks, trackbacks, serviceContext);

		return BlogsEntryJSONSerializer.toJSONObject(returnValue);
	}
}