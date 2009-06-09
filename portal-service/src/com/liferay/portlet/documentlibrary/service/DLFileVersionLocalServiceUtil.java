/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.documentlibrary.service;


/**
 * <a href="DLFileVersionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.liferay.portlet.documentlibrary.service.DLFileVersionLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portlet.documentlibrary.service.DLFileVersionLocalService
 *
 */
public class DLFileVersionLocalServiceUtil {
	public static com.liferay.portlet.documentlibrary.model.DLFileVersion addDLFileVersion(
		com.liferay.portlet.documentlibrary.model.DLFileVersion dlFileVersion)
		throws com.liferay.portal.SystemException {
		return getService().addDLFileVersion(dlFileVersion);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileVersion createDLFileVersion(
		long fileVersionId) {
		return getService().createDLFileVersion(fileVersionId);
	}

	public static void deleteDLFileVersion(long fileVersionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteDLFileVersion(fileVersionId);
	}

	public static void deleteDLFileVersion(
		com.liferay.portlet.documentlibrary.model.DLFileVersion dlFileVersion)
		throws com.liferay.portal.SystemException {
		getService().deleteDLFileVersion(dlFileVersion);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileVersion getDLFileVersion(
		long fileVersionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getDLFileVersion(fileVersionId);
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileVersion> getDLFileVersions(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getDLFileVersions(start, end);
	}

	public static int getDLFileVersionsCount()
		throws com.liferay.portal.SystemException {
		return getService().getDLFileVersionsCount();
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileVersion updateDLFileVersion(
		com.liferay.portlet.documentlibrary.model.DLFileVersion dlFileVersion)
		throws com.liferay.portal.SystemException {
		return getService().updateDLFileVersion(dlFileVersion);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileVersion updateDLFileVersion(
		com.liferay.portlet.documentlibrary.model.DLFileVersion dlFileVersion,
		boolean merge) throws com.liferay.portal.SystemException {
		return getService().updateDLFileVersion(dlFileVersion, merge);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileVersion getFileVersion(
		long folderId, java.lang.String name, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getFileVersion(folderId, name, version);
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileVersion> getFileVersions(
		long folderId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		return getService().getFileVersions(folderId, name);
	}

	public static DLFileVersionLocalService getService() {
		if (_service == null) {
			throw new RuntimeException("DLFileVersionLocalService is not set");
		}

		return _service;
	}

	public void setService(DLFileVersionLocalService service) {
		_service = service;
	}

	private static DLFileVersionLocalService _service;
}