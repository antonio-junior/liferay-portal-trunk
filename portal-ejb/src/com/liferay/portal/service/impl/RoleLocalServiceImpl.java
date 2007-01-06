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

package com.liferay.portal.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.DuplicateRoleException;
import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.PortalException;
import com.liferay.portal.RequiredRoleException;
import com.liferay.portal.RoleNameException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.impl.ResourceImpl;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.RoleLocalService;
import com.liferay.portal.service.persistence.RoleFinder;
import com.liferay.portal.service.persistence.RoleUtil;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.Validator;

import java.util.List;

/**
 * <a href="RoleLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class RoleLocalServiceImpl implements RoleLocalService {

	public Role addRole(String userId, String companyId, String name)
		throws PortalException, SystemException {

		return addRole(userId, companyId, name, null, null);
	}

	public Role addRole(
			String userId, String companyId, String name, String className,
			String classPK)
		throws PortalException, SystemException {

		// Role

		validate(null, companyId, name);

		String roleId = Long.toString(CounterLocalServiceUtil.increment(
			Role.class.getName()));

		Role role = RoleUtil.create(roleId);

		role.setCompanyId(companyId);
		role.setName(name);
		role.setClassName(className);
		role.setClassPK(classPK);

		RoleUtil.update(role);

		// Resources

		if (userId != null) {
			ResourceLocalServiceUtil.addResources(
				companyId, 0, userId, Role.class.getName(),
				role.getPrimaryKey().toString(), false, false, false);
		}

		return role;
	}

	public void checkSystemRoles(String companyId)
		throws PortalException, SystemException {

		String[] systemRoles = PortalUtil.getSystemRoles();

		for (int i = 0; i < systemRoles.length; i++) {
			try {
				RoleFinder.findByC_N(companyId, systemRoles[i]);
			}
			catch (NoSuchRoleException nsre) {
				addRole(null, companyId, systemRoles[i]);
			}
		}
	}

	public void deleteRole(String roleId)
		throws PortalException, SystemException {

		Role role = RoleUtil.findByPrimaryKey(roleId);

		if (PortalUtil.isSystemRole(role.getName())) {
			throw new RequiredRoleException();
		}

		// Resources

		if (Validator.isNull(role.getClassName()) &&
			Validator.isNull(role.getClassPK())) {

			ResourceLocalServiceUtil.deleteResource(
				role.getCompanyId(), Role.class.getName(),
				ResourceImpl.TYPE_CLASS, ResourceImpl.SCOPE_INDIVIDUAL,
				role.getPrimaryKey().toString());
		}

		// Role

		RoleUtil.remove(roleId);
	}

	public Role getGroupRole(String companyId, long groupId)
		throws PortalException, SystemException {

		return RoleUtil.findByC_C_C(
			companyId, Group.class.getName(), String.valueOf(groupId));
	}

	public Role getRole(String roleId) throws PortalException, SystemException {
		return RoleUtil.findByPrimaryKey(roleId);
	}

	public Role getRole(String companyId, String name)
		throws PortalException, SystemException {

		return RoleFinder.findByC_N(companyId, name);
	}

	public List getUserRelatedRoles(String userId, List groups)
		throws PortalException, SystemException {

		return RoleFinder.findByU_G(userId, groups);
	}

	public List getUserRoles(String userId)
		throws PortalException, SystemException {

		return UserUtil.getRoles(userId);
	}

	public boolean hasUserRole(String userId, String companyId, String name)
		throws PortalException, SystemException {

		Role role = RoleFinder.findByC_N(companyId, name);

		return UserUtil.containsRole(userId, role.getRoleId());
	}

	public boolean hasUserRoles(String userId, String companyId, String[] names)
		throws PortalException, SystemException {

		for (int i = 0; i < names.length; i++) {
			if (hasUserRole(userId, companyId, names[i])) {
				return true;
			}
		}

		return false;
	}

	public List search(
			String companyId, String name, String description, int begin,
			int end)
		throws SystemException {

		return RoleFinder.findByC_N_D(companyId, name, description, begin, end);
	}

	public int searchCount(String companyId, String name, String description)
		throws SystemException {

		return RoleFinder.countByC_N_D(companyId, name, description);
	}

	public void setUserRoles(String userId, String[] roleIds)
		throws PortalException, SystemException {

		UserUtil.setRoles(userId, roleIds);
	}

	public Role updateRole(String roleId, String name)
		throws PortalException, SystemException {

		Role role = RoleUtil.findByPrimaryKey(roleId);

		validate(roleId, role.getCompanyId(), name);

		if (PortalUtil.isSystemRole(role.getName())) {
			throw new RequiredRoleException();
		}

		role.setName(name);

		RoleUtil.update(role);

		return role;
	}

	protected void validate(String roleId, String companyId, String name)
		throws PortalException, SystemException {

		if ((Validator.isNull(name)) || (Validator.isNumber(name)) ||
			(name.indexOf(StringPool.COMMA) != -1) ||
			(name.indexOf(StringPool.STAR) != -1)) {

			throw new RoleNameException();
		}

		try {
			Role role = RoleFinder.findByC_N(companyId, name);

			if ((roleId == null) || !role.getRoleId().equals(roleId)) {
				throw new DuplicateRoleException();
			}
		}
		catch (NoSuchRoleException nsge) {
		}
	}

}