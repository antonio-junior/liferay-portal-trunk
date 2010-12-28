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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.upgrade.util.UpgradeAssetPublisherManualEntries;

/**
 * @author Douglas Wong
 */
public class VerifyAssetPublisher extends VerifyProcess {

	protected void doVerify() throws Exception {
		UpgradeAssetPublisherManualEntries upgrade =
			new UpgradeAssetPublisherManualEntries();

		upgrade.upgrade();
	}

	private static Log _log = LogFactoryUtil.getLog(VerifyAssetPublisher.class);

}