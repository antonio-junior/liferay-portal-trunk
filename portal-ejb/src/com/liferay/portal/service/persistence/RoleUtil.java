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

package com.liferay.portal.service.persistence;

import com.liferay.portal.model.ModelListener;
import com.liferay.portal.util.PropsUtil;

import com.liferay.util.GetterUtil;
import com.liferay.util.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="RoleUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class RoleUtil {
	public static com.liferay.portal.model.Role create(java.lang.String roleId) {
		return getPersistence().create(roleId);
	}

	public static com.liferay.portal.model.Role remove(java.lang.String roleId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(findByPrimaryKey(roleId));
		}

		com.liferay.portal.model.Role role = getPersistence().remove(roleId);

		if (listener != null) {
			listener.onAfterRemove(role);
		}

		return role;
	}

	public static com.liferay.portal.model.Role remove(
		com.liferay.portal.model.Role role)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(role);
		}

		role = getPersistence().remove(role);

		if (listener != null) {
			listener.onAfterRemove(role);
		}

		return role;
	}

	public static com.liferay.portal.model.Role update(
		com.liferay.portal.model.Role role)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();
		boolean isNew = role.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(role);
			}
			else {
				listener.onBeforeUpdate(role);
			}
		}

		role = getPersistence().update(role);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(role);
			}
			else {
				listener.onAfterUpdate(role);
			}
		}

		return role;
	}

	public static com.liferay.portal.model.Role update(
		com.liferay.portal.model.Role role, boolean saveOrUpdate)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();
		boolean isNew = role.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(role);
			}
			else {
				listener.onBeforeUpdate(role);
			}
		}

		role = getPersistence().update(role, saveOrUpdate);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(role);
			}
			else {
				listener.onAfterUpdate(role);
			}
		}

		return role;
	}

	public static com.liferay.portal.model.Role findByPrimaryKey(
		java.lang.String roleId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().findByPrimaryKey(roleId);
	}

	public static com.liferay.portal.model.Role fetchByPrimaryKey(
		java.lang.String roleId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(roleId);
	}

	public static java.util.List findByCompanyId(java.lang.String companyId)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List findByCompanyId(java.lang.String companyId,
		int begin, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, begin, end);
	}

	public static java.util.List findByCompanyId(java.lang.String companyId,
		int begin, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, begin, end, obc);
	}

	public static com.liferay.portal.model.Role findByCompanyId_First(
		java.lang.String companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static com.liferay.portal.model.Role findByCompanyId_Last(
		java.lang.String companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static com.liferay.portal.model.Role[] findByCompanyId_PrevAndNext(
		java.lang.String roleId, java.lang.String companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().findByCompanyId_PrevAndNext(roleId, companyId,
			obc);
	}

	public static com.liferay.portal.model.Role findByC_N(
		java.lang.String companyId, java.lang.String name)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().findByC_N(companyId, name);
	}

	public static com.liferay.portal.model.Role fetchByC_N(
		java.lang.String companyId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByC_N(companyId, name);
	}

	public static com.liferay.portal.model.Role findByC_C_C(
		java.lang.String companyId, java.lang.String className,
		java.lang.String classPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().findByC_C_C(companyId, className, classPK);
	}

	public static com.liferay.portal.model.Role fetchByC_C_C(
		java.lang.String companyId, java.lang.String className,
		java.lang.String classPK) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByC_C_C(companyId, className, classPK);
	}

	public static java.util.List findWithDynamicQuery(
		com.liferay.util.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(queryInitializer);
	}

	public static java.util.List findWithDynamicQuery(
		com.liferay.util.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(queryInitializer, begin,
			end);
	}

	public static java.util.List findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List findAll(int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end);
	}

	public static java.util.List findAll(int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end, obc);
	}

	public static void removeByCompanyId(java.lang.String companyId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeByC_N(java.lang.String companyId,
		java.lang.String name)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().removeByC_N(companyId, name);
	}

	public static void removeByC_C_C(java.lang.String companyId,
		java.lang.String className, java.lang.String classPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().removeByC_C_C(companyId, className, classPK);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCompanyId(java.lang.String companyId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countByC_N(java.lang.String companyId,
		java.lang.String name) throws com.liferay.portal.SystemException {
		return getPersistence().countByC_N(companyId, name);
	}

	public static int countByC_C_C(java.lang.String companyId,
		java.lang.String className, java.lang.String classPK)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByC_C_C(companyId, className, classPK);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static java.util.List getGroups(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().getGroups(pk);
	}

	public static java.util.List getGroups(java.lang.String pk, int begin,
		int end)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().getGroups(pk, begin, end);
	}

	public static java.util.List getGroups(java.lang.String pk, int begin,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().getGroups(pk, begin, end, obc);
	}

	public static int getGroupsSize(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getGroupsSize(pk);
	}

	public static boolean containsGroup(java.lang.String pk, long groupPK)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsGroup(pk, groupPK);
	}

	public static boolean containsGroups(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsGroups(pk);
	}

	public static void addGroup(java.lang.String pk, long groupPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().addGroup(pk, groupPK);
	}

	public static void addGroup(java.lang.String pk,
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().addGroup(pk, group);
	}

	public static void addGroups(java.lang.String pk, long[] groupPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().addGroups(pk, groupPKs);
	}

	public static void addGroups(java.lang.String pk, java.util.List groups)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().addGroups(pk, groups);
	}

	public static void clearGroups(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().clearGroups(pk);
	}

	public static void removeGroup(java.lang.String pk, long groupPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().removeGroup(pk, groupPK);
	}

	public static void removeGroup(java.lang.String pk,
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().removeGroup(pk, group);
	}

	public static void removeGroups(java.lang.String pk, long[] groupPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().removeGroups(pk, groupPKs);
	}

	public static void removeGroups(java.lang.String pk, java.util.List groups)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().removeGroups(pk, groups);
	}

	public static void setGroups(java.lang.String pk, long[] groupPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().setGroups(pk, groupPKs);
	}

	public static void setGroups(java.lang.String pk, java.util.List groups)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().setGroups(pk, groups);
	}

	public static java.util.List getPermissions(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().getPermissions(pk);
	}

	public static java.util.List getPermissions(java.lang.String pk, int begin,
		int end)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().getPermissions(pk, begin, end);
	}

	public static java.util.List getPermissions(java.lang.String pk, int begin,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().getPermissions(pk, begin, end, obc);
	}

	public static int getPermissionsSize(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getPermissionsSize(pk);
	}

	public static boolean containsPermission(java.lang.String pk,
		long permissionPK) throws com.liferay.portal.SystemException {
		return getPersistence().containsPermission(pk, permissionPK);
	}

	public static boolean containsPermissions(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsPermissions(pk);
	}

	public static void addPermission(java.lang.String pk, long permissionPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().addPermission(pk, permissionPK);
	}

	public static void addPermission(java.lang.String pk,
		com.liferay.portal.model.Permission permission)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().addPermission(pk, permission);
	}

	public static void addPermissions(java.lang.String pk, long[] permissionPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().addPermissions(pk, permissionPKs);
	}

	public static void addPermissions(java.lang.String pk,
		java.util.List permissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().addPermissions(pk, permissions);
	}

	public static void clearPermissions(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().clearPermissions(pk);
	}

	public static void removePermission(java.lang.String pk, long permissionPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().removePermission(pk, permissionPK);
	}

	public static void removePermission(java.lang.String pk,
		com.liferay.portal.model.Permission permission)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().removePermission(pk, permission);
	}

	public static void removePermissions(java.lang.String pk,
		long[] permissionPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().removePermissions(pk, permissionPKs);
	}

	public static void removePermissions(java.lang.String pk,
		java.util.List permissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().removePermissions(pk, permissions);
	}

	public static void setPermissions(java.lang.String pk, long[] permissionPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().setPermissions(pk, permissionPKs);
	}

	public static void setPermissions(java.lang.String pk,
		java.util.List permissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().setPermissions(pk, permissions);
	}

	public static java.util.List getUsers(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().getUsers(pk);
	}

	public static java.util.List getUsers(java.lang.String pk, int begin,
		int end)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().getUsers(pk, begin, end);
	}

	public static java.util.List getUsers(java.lang.String pk, int begin,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		return getPersistence().getUsers(pk, begin, end, obc);
	}

	public static int getUsersSize(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getUsersSize(pk);
	}

	public static boolean containsUser(java.lang.String pk,
		java.lang.String userPK) throws com.liferay.portal.SystemException {
		return getPersistence().containsUser(pk, userPK);
	}

	public static boolean containsUsers(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsUsers(pk);
	}

	public static void addUser(java.lang.String pk, java.lang.String userPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addUser(pk, userPK);
	}

	public static void addUser(java.lang.String pk,
		com.liferay.portal.model.User user)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addUser(pk, user);
	}

	public static void addUsers(java.lang.String pk, java.lang.String[] userPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addUsers(pk, userPKs);
	}

	public static void addUsers(java.lang.String pk, java.util.List users)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addUsers(pk, users);
	}

	public static void clearUsers(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException {
		getPersistence().clearUsers(pk);
	}

	public static void removeUser(java.lang.String pk, java.lang.String userPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeUser(pk, userPK);
	}

	public static void removeUser(java.lang.String pk,
		com.liferay.portal.model.User user)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeUser(pk, user);
	}

	public static void removeUsers(java.lang.String pk,
		java.lang.String[] userPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeUsers(pk, userPKs);
	}

	public static void removeUsers(java.lang.String pk, java.util.List users)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeUsers(pk, users);
	}

	public static void setUsers(java.lang.String pk, java.lang.String[] userPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().setUsers(pk, userPKs);
	}

	public static void setUsers(java.lang.String pk, java.util.List users)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().setUsers(pk, users);
	}

	public static void initDao() {
		getPersistence().initDao();
	}

	public static RolePersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(RolePersistence persistence) {
		_persistence = persistence;
	}

	private static RoleUtil _getUtil() {
		if (_util == null) {
			_util = (RoleUtil)com.liferay.portal.kernel.bean.BeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static ModelListener _getListener() {
		if (Validator.isNotNull(_LISTENER)) {
			try {
				return (ModelListener)Class.forName(_LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		return null;
	}

	private static final String _UTIL = RoleUtil.class.getName();
	private static final String _LISTENER = GetterUtil.getString(PropsUtil.get(
				"value.object.listener.com.liferay.portal.model.Role"));
	private static Log _log = LogFactory.getLog(RoleUtil.class);
	private static RoleUtil _util;
	private RolePersistence _persistence;
}