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

package com.liferay.portal.model.impl;

import com.liferay.portal.model.LayoutBranch;
import com.liferay.portal.model.LayoutBranchConstants;

/**
 * The model implementation for the LayoutBranch service. Represents a row in the &quot;LayoutBranch&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portal.model.LayoutBranch} interface.
 * </p>
 *
 * <p>
 * Never reference this class directly. All methods that expect a layout branch model instance should use the {@link LayoutBranch} interface instead.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class LayoutBranchImpl extends LayoutBranchModelImpl
	implements LayoutBranch {
	public LayoutBranchImpl() {
	}

	public boolean isMaster() {
		if (getName().equals(LayoutBranchConstants.MASTER_BRANCH_NAME)) {
			return true;
		}

		return false;
	}

}