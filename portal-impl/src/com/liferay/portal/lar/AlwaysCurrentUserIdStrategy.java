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

package com.liferay.portal.lar;

import com.liferay.portal.kernel.lar.UserIdStrategy;
import com.liferay.portal.model.User;

/**
 * <a href="AlwaysCurrentUserIdStrategy.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 */
public class AlwaysCurrentUserIdStrategy implements UserIdStrategy {

	public AlwaysCurrentUserIdStrategy(User user) {
		_user = user;
	}

	public long getUserId(String userUuid) {
		return _user.getUserId();
	}

	private User _user;

}