/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.LayoutBranch;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.LayoutBranchServiceBaseImpl;
import com.liferay.portal.service.permission.GroupPermissionUtil;

import java.util.List;

/**
 * The implementation of the layout branch remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portal.service.LayoutBranchService} interface.
 * </p>
 *
 * <p>
 * Never reference this interface directly. Always use {@link com.liferay.portal.service.LayoutBranchServiceUtil} to access the layout branch remote service.
 * </p>
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.base.LayoutBranchServiceBaseImpl
 * @see com.liferay.portal.service.LayoutBranchServiceUtil
 */
public class LayoutBranchServiceImpl extends LayoutBranchServiceBaseImpl {

	public LayoutBranch addBranch(
			long groupId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_BRANCH);

		return layoutBranchLocalService.addBranch(name, description, serviceContext);
	}

	public void deleteBranch(long groupId, long branchId)
		throws PortalException, SystemException {

		if (!getPermissionChecker().hasPermission(
				groupId, LayoutBranch.class.getName(), branchId,
				ActionKeys.DELETE)) {

			throw new PrincipalException();
		}

		layoutBranchLocalService.deleteBranch(branchId);
	}

	public List<LayoutBranch> getBranches(long groupId)
		throws PortalException, SystemException {

		return layoutBranchLocalService.getBranches(groupId);
	}

	public LayoutBranch updateBranch(
			long groupId, long branchId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (!getPermissionChecker().hasPermission(
				groupId, LayoutBranch.class.getName(), branchId,
				ActionKeys.UPDATE)) {

			throw new PrincipalException();
		}

		return layoutBranchLocalService.updateBranch(
			branchId, name, description, serviceContext);
	}

}