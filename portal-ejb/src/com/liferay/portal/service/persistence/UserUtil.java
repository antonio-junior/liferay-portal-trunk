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

package com.liferay.portal.service.persistence;

import com.liferay.portal.model.ModelListener;
import com.liferay.portal.util.PropsUtil;

import com.liferay.util.GetterUtil;
import com.liferay.util.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="UserUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class UserUtil {
	public static com.liferay.portal.model.User create(java.lang.String userId) {
		return getPersistence().create(userId);
	}

	public static com.liferay.portal.model.User remove(java.lang.String userId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(findByPrimaryKey(userId));
		}

		com.liferay.portal.model.User user = getPersistence().remove(userId);

		if (listener != null) {
			listener.onAfterRemove(user);
		}

		return user;
	}

	public static com.liferay.portal.model.User remove(
		com.liferay.portal.model.User user)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(user);
		}

		user = getPersistence().remove(user);

		if (listener != null) {
			listener.onAfterRemove(user);
		}

		return user;
	}

	public static com.liferay.portal.model.User update(
		com.liferay.portal.model.User user)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();
		boolean isNew = user.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(user);
			}
			else {
				listener.onBeforeUpdate(user);
			}
		}

		user = getPersistence().update(user);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(user);
			}
			else {
				listener.onAfterUpdate(user);
			}
		}

		return user;
	}

	public static com.liferay.portal.model.User update(
		com.liferay.portal.model.User user, boolean saveOrUpdate)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();
		boolean isNew = user.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(user);
			}
			else {
				listener.onBeforeUpdate(user);
			}
		}

		user = getPersistence().update(user, saveOrUpdate);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(user);
			}
			else {
				listener.onAfterUpdate(user);
			}
		}

		return user;
	}

	public static com.liferay.portal.model.User findByPrimaryKey(
		java.lang.String userId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().findByPrimaryKey(userId);
	}

	public static com.liferay.portal.model.User fetchByPrimaryKey(
		java.lang.String userId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(userId);
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

	public static com.liferay.portal.model.User findByCompanyId_First(
		java.lang.String companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static com.liferay.portal.model.User findByCompanyId_Last(
		java.lang.String companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static com.liferay.portal.model.User[] findByCompanyId_PrevAndNext(
		java.lang.String userId, java.lang.String companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().findByCompanyId_PrevAndNext(userId, companyId,
			obc);
	}

	public static com.liferay.portal.model.User findByC_U(
		java.lang.String companyId, java.lang.String userId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().findByC_U(companyId, userId);
	}

	public static com.liferay.portal.model.User fetchByC_U(
		java.lang.String companyId, java.lang.String userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByC_U(companyId, userId);
	}

	public static java.util.List findByC_P(java.lang.String companyId,
		java.lang.String password) throws com.liferay.portal.SystemException {
		return getPersistence().findByC_P(companyId, password);
	}

	public static java.util.List findByC_P(java.lang.String companyId,
		java.lang.String password, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_P(companyId, password, begin, end);
	}

	public static java.util.List findByC_P(java.lang.String companyId,
		java.lang.String password, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_P(companyId, password, begin, end, obc);
	}

	public static com.liferay.portal.model.User findByC_P_First(
		java.lang.String companyId, java.lang.String password,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().findByC_P_First(companyId, password, obc);
	}

	public static com.liferay.portal.model.User findByC_P_Last(
		java.lang.String companyId, java.lang.String password,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().findByC_P_Last(companyId, password, obc);
	}

	public static com.liferay.portal.model.User[] findByC_P_PrevAndNext(
		java.lang.String userId, java.lang.String companyId,
		java.lang.String password,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().findByC_P_PrevAndNext(userId, companyId,
			password, obc);
	}

	public static com.liferay.portal.model.User findByC_EA(
		java.lang.String companyId, java.lang.String emailAddress)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().findByC_EA(companyId, emailAddress);
	}

	public static com.liferay.portal.model.User fetchByC_EA(
		java.lang.String companyId, java.lang.String emailAddress)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByC_EA(companyId, emailAddress);
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

	public static void removeByC_U(java.lang.String companyId,
		java.lang.String userId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeByC_U(companyId, userId);
	}

	public static void removeByC_P(java.lang.String companyId,
		java.lang.String password) throws com.liferay.portal.SystemException {
		getPersistence().removeByC_P(companyId, password);
	}

	public static void removeByC_EA(java.lang.String companyId,
		java.lang.String emailAddress)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeByC_EA(companyId, emailAddress);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCompanyId(java.lang.String companyId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countByC_U(java.lang.String companyId,
		java.lang.String userId) throws com.liferay.portal.SystemException {
		return getPersistence().countByC_U(companyId, userId);
	}

	public static int countByC_P(java.lang.String companyId,
		java.lang.String password) throws com.liferay.portal.SystemException {
		return getPersistence().countByC_P(companyId, password);
	}

	public static int countByC_EA(java.lang.String companyId,
		java.lang.String emailAddress)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByC_EA(companyId, emailAddress);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static java.util.List getGroups(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getGroups(pk);
	}

	public static java.util.List getGroups(java.lang.String pk, int begin,
		int end)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getGroups(pk, begin, end);
	}

	public static java.util.List getGroups(java.lang.String pk, int begin,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getGroups(pk, begin, end, obc);
	}

	public static int getGroupsSize(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getGroupsSize(pk);
	}

	public static boolean containsGroup(java.lang.String pk,
		java.lang.String groupPK) throws com.liferay.portal.SystemException {
		return getPersistence().containsGroup(pk, groupPK);
	}

	public static boolean containsGroups(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsGroups(pk);
	}

	public static void addGroup(java.lang.String pk, java.lang.String groupPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addGroup(pk, groupPK);
	}

	public static void addGroup(java.lang.String pk,
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addGroup(pk, group);
	}

	public static void addGroups(java.lang.String pk,
		java.lang.String[] groupPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addGroups(pk, groupPKs);
	}

	public static void addGroups(java.lang.String pk, java.util.List groups)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addGroups(pk, groups);
	}

	public static void clearGroups(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().clearGroups(pk);
	}

	public static void removeGroup(java.lang.String pk, java.lang.String groupPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeGroup(pk, groupPK);
	}

	public static void removeGroup(java.lang.String pk,
		com.liferay.portal.model.Group group)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeGroup(pk, group);
	}

	public static void removeGroups(java.lang.String pk,
		java.lang.String[] groupPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeGroups(pk, groupPKs);
	}

	public static void removeGroups(java.lang.String pk, java.util.List groups)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeGroups(pk, groups);
	}

	public static void setGroups(java.lang.String pk,
		java.lang.String[] groupPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().setGroups(pk, groupPKs);
	}

	public static void setGroups(java.lang.String pk, java.util.List groups)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchGroupException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().setGroups(pk, groups);
	}

	public static java.util.List getOrganizations(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getOrganizations(pk);
	}

	public static java.util.List getOrganizations(java.lang.String pk,
		int begin, int end)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getOrganizations(pk, begin, end);
	}

	public static java.util.List getOrganizations(java.lang.String pk,
		int begin, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getOrganizations(pk, begin, end, obc);
	}

	public static int getOrganizationsSize(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getOrganizationsSize(pk);
	}

	public static boolean containsOrganization(java.lang.String pk,
		java.lang.String organizationPK)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsOrganization(pk, organizationPK);
	}

	public static boolean containsOrganizations(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsOrganizations(pk);
	}

	public static void addOrganization(java.lang.String pk,
		java.lang.String organizationPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchOrganizationException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addOrganization(pk, organizationPK);
	}

	public static void addOrganization(java.lang.String pk,
		com.liferay.portal.model.Organization organization)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchOrganizationException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addOrganization(pk, organization);
	}

	public static void addOrganizations(java.lang.String pk,
		java.lang.String[] organizationPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchOrganizationException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addOrganizations(pk, organizationPKs);
	}

	public static void addOrganizations(java.lang.String pk,
		java.util.List organizations)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchOrganizationException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addOrganizations(pk, organizations);
	}

	public static void clearOrganizations(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().clearOrganizations(pk);
	}

	public static void removeOrganization(java.lang.String pk,
		java.lang.String organizationPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchOrganizationException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeOrganization(pk, organizationPK);
	}

	public static void removeOrganization(java.lang.String pk,
		com.liferay.portal.model.Organization organization)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchOrganizationException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeOrganization(pk, organization);
	}

	public static void removeOrganizations(java.lang.String pk,
		java.lang.String[] organizationPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchOrganizationException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeOrganizations(pk, organizationPKs);
	}

	public static void removeOrganizations(java.lang.String pk,
		java.util.List organizations)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchOrganizationException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeOrganizations(pk, organizations);
	}

	public static void setOrganizations(java.lang.String pk,
		java.lang.String[] organizationPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchOrganizationException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().setOrganizations(pk, organizationPKs);
	}

	public static void setOrganizations(java.lang.String pk,
		java.util.List organizations)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchOrganizationException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().setOrganizations(pk, organizations);
	}

	public static java.util.List getPermissions(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getPermissions(pk);
	}

	public static java.util.List getPermissions(java.lang.String pk, int begin,
		int end)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getPermissions(pk, begin, end);
	}

	public static java.util.List getPermissions(java.lang.String pk, int begin,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getPermissions(pk, begin, end, obc);
	}

	public static int getPermissionsSize(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getPermissionsSize(pk);
	}

	public static boolean containsPermission(java.lang.String pk,
		java.lang.String permissionPK)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsPermission(pk, permissionPK);
	}

	public static boolean containsPermissions(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsPermissions(pk);
	}

	public static void addPermission(java.lang.String pk,
		java.lang.String permissionPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addPermission(pk, permissionPK);
	}

	public static void addPermission(java.lang.String pk,
		com.liferay.portal.model.Permission permission)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addPermission(pk, permission);
	}

	public static void addPermissions(java.lang.String pk,
		java.lang.String[] permissionPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addPermissions(pk, permissionPKs);
	}

	public static void addPermissions(java.lang.String pk,
		java.util.List permissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addPermissions(pk, permissions);
	}

	public static void clearPermissions(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().clearPermissions(pk);
	}

	public static void removePermission(java.lang.String pk,
		java.lang.String permissionPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removePermission(pk, permissionPK);
	}

	public static void removePermission(java.lang.String pk,
		com.liferay.portal.model.Permission permission)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removePermission(pk, permission);
	}

	public static void removePermissions(java.lang.String pk,
		java.lang.String[] permissionPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removePermissions(pk, permissionPKs);
	}

	public static void removePermissions(java.lang.String pk,
		java.util.List permissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removePermissions(pk, permissions);
	}

	public static void setPermissions(java.lang.String pk,
		java.lang.String[] permissionPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().setPermissions(pk, permissionPKs);
	}

	public static void setPermissions(java.lang.String pk,
		java.util.List permissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchPermissionException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().setPermissions(pk, permissions);
	}

	public static java.util.List getRoles(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getRoles(pk);
	}

	public static java.util.List getRoles(java.lang.String pk, int begin,
		int end)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getRoles(pk, begin, end);
	}

	public static java.util.List getRoles(java.lang.String pk, int begin,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getRoles(pk, begin, end, obc);
	}

	public static int getRolesSize(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getRolesSize(pk);
	}

	public static boolean containsRole(java.lang.String pk,
		java.lang.String rolePK) throws com.liferay.portal.SystemException {
		return getPersistence().containsRole(pk, rolePK);
	}

	public static boolean containsRoles(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsRoles(pk);
	}

	public static void addRole(java.lang.String pk, java.lang.String rolePK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addRole(pk, rolePK);
	}

	public static void addRole(java.lang.String pk,
		com.liferay.portal.model.Role role)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addRole(pk, role);
	}

	public static void addRoles(java.lang.String pk, java.lang.String[] rolePKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addRoles(pk, rolePKs);
	}

	public static void addRoles(java.lang.String pk, java.util.List roles)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().addRoles(pk, roles);
	}

	public static void clearRoles(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().clearRoles(pk);
	}

	public static void removeRole(java.lang.String pk, java.lang.String rolePK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeRole(pk, rolePK);
	}

	public static void removeRole(java.lang.String pk,
		com.liferay.portal.model.Role role)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeRole(pk, role);
	}

	public static void removeRoles(java.lang.String pk,
		java.lang.String[] rolePKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeRoles(pk, rolePKs);
	}

	public static void removeRoles(java.lang.String pk, java.util.List roles)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().removeRoles(pk, roles);
	}

	public static void setRoles(java.lang.String pk, java.lang.String[] rolePKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().setRoles(pk, rolePKs);
	}

	public static void setRoles(java.lang.String pk, java.util.List roles)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchRoleException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().setRoles(pk, roles);
	}

	public static java.util.List getUserGroups(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getUserGroups(pk);
	}

	public static java.util.List getUserGroups(java.lang.String pk, int begin,
		int end)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getUserGroups(pk, begin, end);
	}

	public static java.util.List getUserGroups(java.lang.String pk, int begin,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		return getPersistence().getUserGroups(pk, begin, end, obc);
	}

	public static int getUserGroupsSize(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getUserGroupsSize(pk);
	}

	public static boolean containsUserGroup(java.lang.String pk,
		java.lang.String userGroupPK) throws com.liferay.portal.SystemException {
		return getPersistence().containsUserGroup(pk, userGroupPK);
	}

	public static boolean containsUserGroups(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsUserGroups(pk);
	}

	public static void addUserGroup(java.lang.String pk,
		java.lang.String userGroupPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException, 
			com.liferay.portal.NoSuchUserGroupException {
		getPersistence().addUserGroup(pk, userGroupPK);
	}

	public static void addUserGroup(java.lang.String pk,
		com.liferay.portal.model.UserGroup userGroup)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException, 
			com.liferay.portal.NoSuchUserGroupException {
		getPersistence().addUserGroup(pk, userGroup);
	}

	public static void addUserGroups(java.lang.String pk,
		java.lang.String[] userGroupPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException, 
			com.liferay.portal.NoSuchUserGroupException {
		getPersistence().addUserGroups(pk, userGroupPKs);
	}

	public static void addUserGroups(java.lang.String pk,
		java.util.List userGroups)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException, 
			com.liferay.portal.NoSuchUserGroupException {
		getPersistence().addUserGroups(pk, userGroups);
	}

	public static void clearUserGroups(java.lang.String pk)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException {
		getPersistence().clearUserGroups(pk);
	}

	public static void removeUserGroup(java.lang.String pk,
		java.lang.String userGroupPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException, 
			com.liferay.portal.NoSuchUserGroupException {
		getPersistence().removeUserGroup(pk, userGroupPK);
	}

	public static void removeUserGroup(java.lang.String pk,
		com.liferay.portal.model.UserGroup userGroup)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException, 
			com.liferay.portal.NoSuchUserGroupException {
		getPersistence().removeUserGroup(pk, userGroup);
	}

	public static void removeUserGroups(java.lang.String pk,
		java.lang.String[] userGroupPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException, 
			com.liferay.portal.NoSuchUserGroupException {
		getPersistence().removeUserGroups(pk, userGroupPKs);
	}

	public static void removeUserGroups(java.lang.String pk,
		java.util.List userGroups)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException, 
			com.liferay.portal.NoSuchUserGroupException {
		getPersistence().removeUserGroups(pk, userGroups);
	}

	public static void setUserGroups(java.lang.String pk,
		java.lang.String[] userGroupPKs)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException, 
			com.liferay.portal.NoSuchUserGroupException {
		getPersistence().setUserGroups(pk, userGroupPKs);
	}

	public static void setUserGroups(java.lang.String pk,
		java.util.List userGroups)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.NoSuchUserException, 
			com.liferay.portal.NoSuchUserGroupException {
		getPersistence().setUserGroups(pk, userGroups);
	}

	public static void initDao() {
		getPersistence().initDao();
	}

	public static UserPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(UserPersistence persistence) {
		_persistence = persistence;
	}

	private static UserUtil _getUtil() {
		if (_util == null) {
			_util = (UserUtil)com.liferay.portal.kernel.bean.BeanLocatorUtil.locate(_UTIL);
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

	private static final String _UTIL = UserUtil.class.getName();
	private static final String _LISTENER = GetterUtil.getString(PropsUtil.get(
				"value.object.listener.com.liferay.portal.model.User"));
	private static Log _log = LogFactory.getLog(UserUtil.class);
	private static UserUtil _util;
	private UserPersistence _persistence;
}