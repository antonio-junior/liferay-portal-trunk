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

package com.liferay.portal.webdav;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.impl.LayoutImpl;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.util.dao.hibernate.QueryUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="BaseWebDAVStorageImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class BaseWebDAVStorageImpl implements WebDAVStorage {

	public String getRootPath() {
		return _rootPath;
	}

	public void setRootPath(String rootPath) {
		_rootPath = rootPath;
	}

	public List getCommunities(WebDAVRequest webDavReq)
		throws WebDAVException {

		try {
			LinkedHashMap groupParams = new LinkedHashMap();

			groupParams.put("usersGroups", webDavReq.getUserId());

			List communities = new ArrayList();

			Iterator itr = GroupLocalServiceUtil.search(
				webDavReq.getCompanyId(), null, null, groupParams,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS).iterator();

			while (itr.hasNext()) {
				Group group = (Group)itr.next();

				Resource resource = getResource(group);

				communities.add(resource);
			}

			return communities;
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	public boolean isAvailable(WebDAVRequest webDavReq)
		throws WebDAVException {

		if (getResource(webDavReq) == null) {
			return false;
		}
		else {
			return true;
		}
	}

	protected Resource getResource(Group group) throws WebDAVException {
		String href =
			getRootPath() + StringPool.SLASH + group.getCompanyId() +
				StringPool.SLASH + group.getName();

		return new BaseResourceImpl(href, true);
	}

	protected String getPlid(long groupId) {
		return LayoutImpl.PUBLIC + groupId + ".1";
	}

	private static Log _log = LogFactory.getLog(BaseWebDAVStorageImpl.class);

	private String _rootPath;

}