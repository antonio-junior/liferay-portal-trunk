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

package com.liferay.portlet.softwarecatalog.model.impl;

import com.liferay.portal.SystemException;
import com.liferay.portlet.softwarecatalog.model.SCLicense;
import com.liferay.portlet.softwarecatalog.model.SCProductEntry;
import com.liferay.portlet.softwarecatalog.model.SCProductScreenshot;
import com.liferay.portlet.softwarecatalog.model.SCProductVersion;
import com.liferay.portlet.softwarecatalog.service.SCLicenseLocalServiceUtil;
import com.liferay.portlet.softwarecatalog.service.SCProductScreenshotLocalServiceUtil;
import com.liferay.portlet.softwarecatalog.service.SCProductVersionLocalServiceUtil;

import java.util.List;

/**
 * <a href="SCProductEntryImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SCProductEntryImpl
	extends SCProductEntryModelImpl implements SCProductEntry {

	public SCProductEntryImpl() {
	}

	public SCProductVersion getLatestVersion() throws SystemException {
		List<SCProductVersion> results =
			SCProductVersionLocalServiceUtil.getProductVersions(
				getProductEntryId(), 0, 1);

		SCProductVersion lastVersion = null;

		if (results.size() > 0) {
			lastVersion = results.get(0);
		}

		return lastVersion;
	}

	public List<SCLicense> getLicenses() throws SystemException {
		return SCLicenseLocalServiceUtil.getProductEntryLicenses(
			getProductEntryId());
	}

	public List<SCProductScreenshot> getScreenshots() throws SystemException {
		return SCProductScreenshotLocalServiceUtil.getProductScreenshots(
			getProductEntryId());
	}

}