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

package com.liferay.portal.googleapps;

import java.util.List;

/**
 * <a href="GGroupManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public interface GGroupManager {

	public void deleteGGroup(String emailAddress) throws GoogleAppsException;

	public GGroup getGGroup(String emailAddress) throws GoogleAppsException;

	public List<GGroupMember> getGGroupMembers(String emailAddress)
		throws GoogleAppsException;

	public List<GGroup> getGGroups() throws GoogleAppsException;

	public List<GGroup> getGGroups(long userId, boolean directOnly)
		throws GoogleAppsException;

}