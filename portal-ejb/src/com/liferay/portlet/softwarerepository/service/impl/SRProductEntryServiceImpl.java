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

package com.liferay.portlet.softwarerepository.service.impl;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.impl.PrincipalBean;
import com.liferay.portlet.softwarerepository.model.SRProductEntry;
import com.liferay.portlet.softwarerepository.service.SRProductEntryLocalServiceUtil;
import com.liferay.portlet.softwarerepository.service.SRProductEntryService;
import com.liferay.portlet.softwarerepository.service.permission.SRProductEntryPermission;

/**
 * <a href="SRProductEntryServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Jorge Ferrer
 * @author  Brian Wing Shun Chan
 *
 */
public class SRProductEntryServiceImpl extends PrincipalBean
	implements SRProductEntryService {

	public SRProductEntry addProductEntry(
			String plid, String name, String type, String shortDescription,
			String longDescription, String pageURL, String repoGroupId,
			String repoArtifactId, long[] licenseIds,
			boolean addCommunityPermissions, boolean addGuestPermissions)
		throws PortalException, SystemException {

		SRProductEntryPermission.check(
			getPermissionChecker(), plid, ActionKeys.ADD_PRODUCT_ENTRY);

		return SRProductEntryLocalServiceUtil.addProductEntry(
			getUserId(), plid, name, type, shortDescription, longDescription,
			pageURL, repoGroupId, repoArtifactId, licenseIds,
			addCommunityPermissions, addGuestPermissions);
	}

	public SRProductEntry addProductEntry(
			String plid, String name, String type, String shortDescription,
			String longDescription, String pageURL, String repoGroupId,
			String repoArtifactId, long[] licenseIds,
			String[] communityPermissions, String[] guestPermissions)
		throws PortalException, SystemException {

		SRProductEntryPermission.check(
			getPermissionChecker(), plid, ActionKeys.ADD_PRODUCT_ENTRY);

		return SRProductEntryLocalServiceUtil.addProductEntry(
			getUserId(), plid, name, type, shortDescription, longDescription,
			pageURL, repoGroupId, repoArtifactId, licenseIds,
			communityPermissions, guestPermissions);
	}

	public void deleteProductEntry(long productEntryId)
		throws PortalException, SystemException {

		SRProductEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.DELETE);

		SRProductEntryLocalServiceUtil.deleteProductEntry(productEntryId);
	}

	public SRProductEntry getProductEntry(long productEntryId)
		throws PortalException, SystemException {

		SRProductEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.VIEW);

		return SRProductEntryLocalServiceUtil.getProductEntry(productEntryId);
	}

	public SRProductEntry updateProductEntry(
			long productEntryId, String name, String type,
			String shortDescription, String longDescription, String pageURL,
			String repoGroupId, String repoArtifactId, long[] licenseIds)
		throws PortalException, SystemException {

		SRProductEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.UPDATE);

		return SRProductEntryLocalServiceUtil.updateProductEntry(
			productEntryId, name, type, shortDescription, longDescription,
			pageURL, repoGroupId, repoArtifactId, licenseIds);
	}

}