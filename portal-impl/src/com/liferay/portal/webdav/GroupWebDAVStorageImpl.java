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

package com.liferay.portal.webdav;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.webdav.BaseResourceImpl;
import com.liferay.portal.kernel.webdav.BaseWebDAVStorageImpl;
import com.liferay.portal.kernel.webdav.Resource;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVUtil;
import com.liferay.portal.model.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="GroupWebDAVStorageImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Alexander Chow
 */
public class GroupWebDAVStorageImpl extends BaseWebDAVStorageImpl {

	public Resource getResource(WebDAVRequest webDavRequest) {
		verifyGroup(webDavRequest);

		String path = getRootPath() + webDavRequest.getPath();

		return new BaseResourceImpl(path, StringPool.BLANK, StringPool.BLANK);
	}

	public List<Resource> getResources(WebDAVRequest webDavRequest) {
		verifyGroup(webDavRequest);

		List<Resource> resources = new ArrayList<Resource>();

		String path = getRootPath() + webDavRequest.getPath();

		for (String token : WebDAVUtil.getStorageTokens()) {
			resources.add(new BaseResourceImpl(path, token, token));
		}

		return resources;
	}

	protected void verifyGroup(WebDAVRequest webDavRequest) {
		String path = webDavRequest.getPath();

		try {
			long companyId = webDavRequest.getCompanyId();
			long userId = webDavRequest.getUserId();

			List<Group> groups =
				CompanyWebDAVStorageImpl.getGroups(companyId, userId);

			for (Group group : groups) {
				if (path.equals(group.getFriendlyURL())) {
					return;
				}
			}
		}
		catch (Exception e) {
		}

		throw new RuntimeException(
			"Invalid group for given credentials " +
				webDavRequest.getRootPath() + path);
	}

}