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

package com.liferay.portal.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Role;

/**
 * <a href="RoleRoleIdComparator.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 */
public class RoleRoleIdComparator extends OrderByComparator {

	public static String ORDER_BY_ASC = "Role_.roleId ASC";

	public static String ORDER_BY_DESC = "Role_.roleId DESC";

	public static String[] ORDER_BY_FIELDS = {"roleId"};

	public RoleRoleIdComparator() {
		this(false);
	}

	public RoleRoleIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	public int compare(Object obj1, Object obj2) {
		Role role1 = (Role)obj1;
		Role role2 = (Role)obj2;

		int value = 0;

		if (role1.getRoleId() < role2.getRoleId()) {
			value = -1;
		}
		else if (role1.getRoleId() == role2.getRoleId()) {
			value = 0;
		}
		else {
			value = 1;
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}