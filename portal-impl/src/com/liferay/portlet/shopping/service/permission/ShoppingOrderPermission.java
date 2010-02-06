/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.shopping.service.permission;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.shopping.model.ShoppingOrder;
import com.liferay.portlet.shopping.service.ShoppingOrderLocalServiceUtil;

/**
 * <a href="ShoppingOrderPermission.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class ShoppingOrderPermission {

	public static void check(
			PermissionChecker permissionChecker, long groupId, long orderId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, groupId, orderId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId,
			ShoppingOrder order, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, order, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long orderId,
			String actionId)
		throws PortalException, SystemException {

		ShoppingOrder order =
			ShoppingOrderLocalServiceUtil.getOrder(orderId);

		return contains(permissionChecker, groupId, order, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, ShoppingOrder order,
		String actionId) {

		if (ShoppingPermission.contains(
				permissionChecker, groupId, ActionKeys.MANAGE_ORDERS)) {

			return true;
		}
		else {
			if (permissionChecker.hasOwnerPermission(
					order.getCompanyId(), ShoppingOrder.class.getName(),
					order.getOrderId(), order.getUserId(), actionId)) {

				return true;
			}

			return permissionChecker.hasPermission(
				order.getGroupId(), ShoppingOrder.class.getName(),
				order.getOrderId(), actionId);
		}
	}

}