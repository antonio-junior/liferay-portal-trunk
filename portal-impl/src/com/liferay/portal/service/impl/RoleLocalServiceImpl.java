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

package com.liferay.portal.service.impl;

import com.liferay.portal.DuplicateRoleException;
import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.RequiredRoleException;
import com.liferay.portal.RoleNameException;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionCacheUtil;
import com.liferay.portal.service.base.RoleLocalServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portlet.enterpriseadmin.util.EnterpriseAdminUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * <a href="RoleLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class RoleLocalServiceImpl extends RoleLocalServiceBaseImpl {

	public Role addRole(
			long userId, long companyId, String name,
			Map<Locale, String> titleMap, String description, int type)
		throws PortalException, SystemException {

		return addRole(
			userId, companyId, name, titleMap, description, type, null, 0);
	}

	public Role addRole(
			long userId, long companyId, String name,
			Map<Locale, String> titleMap, String description,
			int type, String className, long classPK)
		throws PortalException, SystemException {

		// Role

		className = GetterUtil.getString(className);
		long classNameId = PortalUtil.getClassNameId(className);

		validate(0, companyId, name);

		long roleId = counterLocalService.increment();

		if ((classNameId <= 0) || className.equals(Role.class.getName())) {
			classNameId = PortalUtil.getClassNameId(Role.class);
			classPK = roleId;
		}

		Role role = rolePersistence.create(roleId);

		role.setCompanyId(companyId);
		role.setClassNameId(classNameId);
		role.setClassPK(classPK);
		role.setName(name);
		role.setTitleMap(titleMap);
		role.setDescription(description);
		role.setType(type);

		rolePersistence.update(role, false);

		// Resources

		if (userId > 0) {
			resourceLocalService.addResources(
				companyId, 0, userId, Role.class.getName(), role.getRoleId(),
				false, false, false);

			Indexer indexer = IndexerRegistryUtil.getIndexer(User.class);

			indexer.reindex(userId);
		}

		return role;
	}

	public void addUserRoles(long userId, long[] roleIds)
		throws PortalException, SystemException {

		userPersistence.addRoles(userId, roleIds);

		Indexer indexer = IndexerRegistryUtil.getIndexer(User.class);

		indexer.reindex(userId);

		PermissionCacheUtil.clearCache();
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void checkSystemRoles(long companyId)
		throws PortalException, SystemException {

		for (Role role : roleFinder.findBySystem(companyId)) {
			_systemRolesMap.put(companyId + role.getName(), role);
		}

		// Regular roles

		String[] systemRoles = PortalUtil.getSystemRoles();

		for (String name : systemRoles) {
			String description = PropsUtil.get(
				"system.role." + StringUtil.replace(name, " ", ".") +
					".description");
			int type = RoleConstants.TYPE_REGULAR;

			checkSystemRole(companyId, name, description, type);
		}

		// Community roles

		String[] systemCommunityRoles = PortalUtil.getSystemCommunityRoles();

		for (String name : systemCommunityRoles) {
			String description = PropsUtil.get(
				"system.community.role." +
					StringUtil.replace(name, " ", ".") + ".description");
			int type = RoleConstants.TYPE_COMMUNITY;

			checkSystemRole(companyId, name, description, type);
		}

		// Organization roles

		String[] systemOrganizationRoles =
			PortalUtil.getSystemOrganizationRoles();

		for (String name : systemOrganizationRoles) {
			String description = PropsUtil.get(
				"system.organization.role." +
					StringUtil.replace(name, " ", ".") + ".description");
			int type = RoleConstants.TYPE_ORGANIZATION;

			checkSystemRole(companyId, name, description, type);
		}
	}

	public void deleteRole(long roleId)
		throws PortalException, SystemException {

		Role role = rolePersistence.findByPrimaryKey(roleId);

		if (PortalUtil.isSystemRole(role.getName())) {
			throw new RequiredRoleException();
		}

		// Resources

		String className = role.getClassName();
		long classNameId = role.getClassNameId();

		if ((classNameId <= 0) || className.equals(Role.class.getName())) {
			resourceLocalService.deleteResource(
				role.getCompanyId(), Role.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, role.getRoleId());
		}

		if ((role.getType() == RoleConstants.TYPE_COMMUNITY) ||
			(role.getType() == RoleConstants.TYPE_ORGANIZATION)) {

			userGroupRoleLocalService.deleteUserGroupRolesByRoleId(
				role.getRoleId());

			userGroupGroupRoleLocalService.deleteUserGroupGroupRolesByRoleId(
				role.getRoleId());
		}

		// Role

		rolePersistence.remove(role);

		// Permission cache

		PermissionCacheUtil.clearCache();
	}

	public Role getDefaultGroupRole(long groupId)
		throws PortalException, SystemException {

		Group group = groupPersistence.findByPrimaryKey(groupId);

		if (group.isLayout()) {
			Layout layout = layoutLocalService.getLayout(group.getClassPK());

			group = layout.getGroup();
		}

		Role role = null;

		if (group.isCommunity() || group.isLayoutPrototype() ||
			group.isLayoutSetPrototype()) {

			role = getRole(
				group.getCompanyId(), RoleConstants.COMMUNITY_MEMBER);
		}
		else if (group.isCompany()) {
			role = getRole(group.getCompanyId(), RoleConstants.ADMINISTRATOR);
		}
		else if (group.isOrganization()) {
			role = getRole(
				group.getCompanyId(), RoleConstants.ORGANIZATION_MEMBER);
		}
		else if (group.isUser() || group.isUserGroup()) {
			role = getRole(group.getCompanyId(), RoleConstants.POWER_USER);
		}

		return role;
	}

	public Role getGroupRole(long companyId, long groupId)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(Group.class);

		return rolePersistence.findByC_C_C(companyId, classNameId, groupId);
	}

	public List<Role> getGroupRoles(long groupId) throws SystemException {
		return groupPersistence.getRoles(groupId);
	}

	public Map<String, List<String>> getResourceRoles(
			long companyId, String name, int scope, String primKey)
		throws SystemException {

		return roleFinder.findByC_N_S_P(companyId, name, scope, primKey);
	}

	public Role getRole(long roleId) throws PortalException, SystemException {
		return rolePersistence.findByPrimaryKey(roleId);
	}

	public Role getRole(long companyId, String name)
		throws PortalException, SystemException {

		Role role = _systemRolesMap.get(companyId + name);

		if (role != null) {
			return role;
		}

		return rolePersistence.findByC_N(companyId, name);
	}

	public List<Role> getRoles(long companyId) throws SystemException {
		return rolePersistence.findByCompanyId(companyId);
	}

	public List<Role> getRoles(long[] roleIds)
		throws PortalException, SystemException {

		List<Role> roles = new ArrayList<Role>(roleIds.length);

		for (long roleId : roleIds) {
			Role role = getRole(roleId);

			roles.add(role);
		}

		return roles;
	}

	public List<Role> getRoles(int type, String subtype)
		throws SystemException {

		return rolePersistence.findByT_S(type, subtype);
	}

	public List<Role> getSubtypeRoles(String subtype) throws SystemException {
		return rolePersistence.findBySubtype(subtype);
	}

	public int getSubtypeRolesCount(String subtype) throws SystemException {
		return rolePersistence.countBySubtype(subtype);
	}

	public List<Role> getUserGroupGroupRoles(long userId, long groupId)
		throws SystemException {

		return roleFinder.findByUserGroupGroupRole(userId, groupId);
	}

	public List<Role> getUserGroupRoles(long userId, long groupId)
		throws SystemException {

		return roleFinder.findByUserGroupRole(userId, groupId);
	}

	public List<Role> getUserRelatedRoles(long userId, long groupId)
		throws SystemException {

		return roleFinder.findByU_G(userId, groupId);
	}

	public List<Role> getUserRelatedRoles(long userId, long[] groupIds)
		throws SystemException {

		return roleFinder.findByU_G(userId, groupIds);
	}

	public List<Role> getUserRelatedRoles(long userId, List<Group> groups)
		throws SystemException {

		return roleFinder.findByU_G(userId, groups);
	}

	public List<Role> getUserRoles(long userId) throws SystemException {
		return userPersistence.getRoles(userId);
	}

	public boolean hasUserRole(long userId, long roleId)
		throws SystemException {

		return userPersistence.containsRole(userId, roleId);
	}

	/**
	 * Returns true if the user has the regular role.
	 *
	 * @return true if the user has the regular role
	 */
	public boolean hasUserRole(
			long userId, long companyId, String name, boolean inherited)
		throws PortalException, SystemException {

		Role role = rolePersistence.findByC_N(companyId, name);

		if (role.getType() != RoleConstants.TYPE_REGULAR) {
			throw new IllegalArgumentException(name + " is not a regular role");
		}

		if (inherited) {
			if (roleFinder.countByR_U(role.getRoleId(), userId) > 0) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return userPersistence.containsRole(userId, role.getRoleId());
		}
	}

	/**
	 * Returns true if the user has any one of the specified regular roles.
	 *
	 * @return true if the user has the regular role
	 */
	public boolean hasUserRoles(
			long userId, long companyId, String[] names, boolean inherited)
		throws PortalException, SystemException {

		for (int i = 0; i < names.length; i++) {
			if (hasUserRole(userId, companyId, names[i], inherited)) {
				return true;
			}
		}

		return false;
	}

	public List<Role> search(
			long companyId, String name, String description, Integer type,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		return search(
			companyId, name, description, type,
			new LinkedHashMap<String, Object>(), start, end, obc);
	}

	public List<Role> search(
			long companyId, String name, String description, Integer type,
			LinkedHashMap<String, Object> params, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return roleFinder.findByC_N_D_T(
			companyId, name, description, type, params, start, end, obc);
	}

	public int searchCount(
			long companyId, String name, String description, Integer type)
		throws SystemException {

		return searchCount(
			companyId, name, description, type,
			new LinkedHashMap<String, Object>());
	}

	public int searchCount(
			long companyId, String name, String description, Integer type,
			LinkedHashMap<String, Object> params)
		throws SystemException {

		return roleFinder.countByC_N_D_T(
			companyId, name, description, type, params);
	}

	public void setUserRoles(long userId, long[] roleIds)
		throws PortalException, SystemException {

		roleIds = EnterpriseAdminUtil.addRequiredRoles(userId, roleIds);

		userPersistence.setRoles(userId, roleIds);

		Indexer indexer = IndexerRegistryUtil.getIndexer(User.class);

		indexer.reindex(userId);

		PermissionCacheUtil.clearCache();
	}

	public void unsetUserRoles(long userId, long[] roleIds)
		throws PortalException, SystemException {

		roleIds = EnterpriseAdminUtil.removeRequiredRoles(userId, roleIds);

		userPersistence.removeRoles(userId, roleIds);

		Indexer indexer = IndexerRegistryUtil.getIndexer(User.class);

		indexer.reindex(userId);

		PermissionCacheUtil.clearCache();
	}

	public Role updateRole(
			long roleId, String name, Map<Locale, String> titleMap,
			String description, String subtype)
		throws PortalException, SystemException {

		Role role = rolePersistence.findByPrimaryKey(roleId);

		validate(roleId, role.getCompanyId(), name);

		if (PortalUtil.isSystemRole(role.getName())) {
			name = role.getName();
			subtype = null;
		}

		role.setName(name);
		role.setTitleMap(titleMap);
		role.setDescription(description);
		role.setSubtype(subtype);

		rolePersistence.update(role, false);

		return role;
	}

	protected void checkSystemRole(
			long companyId, String name, String description, int type)
		throws PortalException, SystemException {

		Role role = _systemRolesMap.get(companyId + name);

		try {
			if (role == null) {
				role = rolePersistence.findByC_N(companyId, name);
			}

			if (!role.getDescription().equals(description)) {
				role.setDescription(description);

				roleLocalService.updateRole(role, false);
			}
		}
		catch (NoSuchRoleException nsre) {
			role = roleLocalService.addRole(
				0, companyId, name, null, description, type);
		}

		_systemRolesMap.put(companyId + name, role);
	}

	protected void validate(long roleId, long companyId, String name)
		throws PortalException, SystemException {

		if ((Validator.isNull(name)) || (Validator.isNumber(name)) ||
			(name.indexOf(StringPool.COMMA) != -1) ||
			(name.indexOf(StringPool.STAR) != -1)) {

			throw new RoleNameException();
		}

		try {
			Role role = roleFinder.findByC_N(companyId, name);

			if (role.getRoleId() != roleId) {
				throw new DuplicateRoleException();
			}
		}
		catch (NoSuchRoleException nsge) {
		}
	}

	private Map<String, Role> _systemRolesMap = new HashMap<String, Role>();

}