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

package com.liferay.portal.service.permission;

import com.liferay.portal.NoSuchResourceException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Resource;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.spring.LayoutLocalServiceUtil;
import com.liferay.portal.service.spring.ResourceLocalServiceUtil;

/**
 * <a href="LayoutPermission.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Charles May
 *
 */
public class LayoutPermission {

	public static void check(
			PermissionChecker permissionChecker, String layoutId,
			String ownerId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, layoutId, ownerId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, Layout layout, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, layout, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, String layoutId,
			String ownerId, String actionId)
		throws PortalException, SystemException {

		Layout layout =
			LayoutLocalServiceUtil.getLayout(layoutId, ownerId);

		return contains(permissionChecker, layout, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, Layout layout, String actionId)
		throws PortalException, SystemException {

		if (GroupPermission.contains(
				permissionChecker, layout.getGroupId(),
				ActionKeys.MANAGE_LAYOUTS)) {

			return true;
		}

		try {
			ResourceLocalServiceUtil.getResource(
				layout.getCompanyId(), Layout.class.getName(),
				Resource.TYPE_CLASS, Resource.SCOPE_INDIVIDUAL,
				layout.getPrimaryKey().toString());
		}
		catch (NoSuchResourceException nsre) {
			ResourceLocalServiceUtil.addResources(
				layout.getCompanyId(), layout.getGroupId(), null,
				Layout.class.getName(), layout.getPrimaryKey().toString(),
				false, true, true);
		}

		if (permissionChecker.isSignedIn() && !layout.isPrivateLayout() &&
			layout.getGroup().isUser()) {

			permissionChecker.setSignedIn(false);

			boolean hasPermission = permissionChecker.hasPermission(
				layout.getGroupId(), Layout.class.getName(),
				layout.getPrimaryKey().toString(), actionId);

			permissionChecker.setSignedIn(true);

			if (hasPermission) {
				return true;
			}
		}

		return permissionChecker.hasPermission(
			layout.getGroupId(), Layout.class.getName(),
			layout.getPrimaryKey().toString(), actionId);
	}

}