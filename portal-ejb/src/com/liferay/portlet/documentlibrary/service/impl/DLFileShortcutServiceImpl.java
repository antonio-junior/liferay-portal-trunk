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

package com.liferay.portlet.documentlibrary.service.impl;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.impl.PrincipalBean;
import com.liferay.portlet.documentlibrary.FileShortcutPermissionException;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.service.permission.DLFileEntryPermission;
import com.liferay.portlet.documentlibrary.service.permission.DLFileShortcutPermission;
import com.liferay.portlet.documentlibrary.service.permission.DLFolderPermission;
import com.liferay.portlet.documentlibrary.service.spring.DLFileShortcutLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.spring.DLFileShortcutService;

/**
 * <a href="DLFileShortcutServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class DLFileShortcutServiceImpl
	extends PrincipalBean implements DLFileShortcutService {

	public DLFileShortcut addFileShortcut(
			String folderId, String toFolderId, String toName,
			boolean addCommunityPermissions, boolean addGuestPermissions)
		throws PortalException, SystemException {

		DLFolderPermission.check(
			getPermissionChecker(), folderId, ActionKeys.ADD_SHORTCUT);

		try {
			DLFileEntryPermission.check(
				getPermissionChecker(), toFolderId, toName, ActionKeys.VIEW);
		}
		catch (PrincipalException pe) {
			throw new FileShortcutPermissionException();
		}

		return DLFileShortcutLocalServiceUtil.addFileShortcut(
			getUserId(), folderId, toFolderId, toName, addCommunityPermissions,
			addGuestPermissions);
	}

	public void deleteFileShortcut(long fileShortcutId)
		throws PortalException, SystemException {

		DLFileShortcutPermission.check(
			getPermissionChecker(), fileShortcutId, ActionKeys.DELETE);

		DLFileShortcutLocalServiceUtil.deleteFileShortcut(fileShortcutId);
	}

	public DLFileShortcut getFileShortcut(long fileShortcutId)
		throws PortalException, SystemException {

		DLFileShortcutPermission.check(
			getPermissionChecker(), fileShortcutId, ActionKeys.VIEW);

		return DLFileShortcutLocalServiceUtil.getFileShortcut(fileShortcutId);
	}

	public DLFileShortcut updateFileShortcut(
			long fileShortcutId, String folderId, String toFolderId,
			String toName)
		throws PortalException, SystemException {

		DLFileShortcutPermission.check(
			getPermissionChecker(), fileShortcutId, ActionKeys.UPDATE);

		try {
			DLFileEntryPermission.check(
				getPermissionChecker(), toFolderId, toName, ActionKeys.VIEW);
		}
		catch (PrincipalException pe) {
			throw new FileShortcutPermissionException();
		}

		return DLFileShortcutLocalServiceUtil.updateFileShortcut(
			getUserId(), fileShortcutId, folderId, toFolderId, toName);
	}

}