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

package com.liferay.portal.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.OrgGroupPermission;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the org group permission service.
 *
 * <p>
 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regnerate this class.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrgGroupPermissionPersistence
 * @see OrgGroupPermissionPersistenceImpl
 * @generated
 */
public class OrgGroupPermissionUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(OrgGroupPermission orgGroupPermission) {
		getPersistence().clearCache(orgGroupPermission);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<OrgGroupPermission> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OrgGroupPermission> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OrgGroupPermission> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static OrgGroupPermission remove(
		OrgGroupPermission orgGroupPermission) throws SystemException {
		return getPersistence().remove(orgGroupPermission);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static OrgGroupPermission update(
		OrgGroupPermission orgGroupPermission, boolean merge)
		throws SystemException {
		return getPersistence().update(orgGroupPermission, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static OrgGroupPermission update(
		OrgGroupPermission orgGroupPermission, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(orgGroupPermission, merge, serviceContext);
	}

	/**
	* Caches the org group permission in the entity cache if it is enabled.
	*
	* @param orgGroupPermission the org group permission to cache
	*/
	public static void cacheResult(
		com.liferay.portal.model.OrgGroupPermission orgGroupPermission) {
		getPersistence().cacheResult(orgGroupPermission);
	}

	/**
	* Caches the org group permissions in the entity cache if it is enabled.
	*
	* @param orgGroupPermissions the org group permissions to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.OrgGroupPermission> orgGroupPermissions) {
		getPersistence().cacheResult(orgGroupPermissions);
	}

	/**
	* Creates a new org group permission with the primary key.
	*
	* @param orgGroupPermissionPK the primary key for the new org group permission
	* @return the new org group permission
	*/
	public static com.liferay.portal.model.OrgGroupPermission create(
		com.liferay.portal.service.persistence.OrgGroupPermissionPK orgGroupPermissionPK) {
		return getPersistence().create(orgGroupPermissionPK);
	}

	/**
	* Removes the org group permission with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param orgGroupPermissionPK the primary key of the org group permission to remove
	* @return the org group permission that was removed
	* @throws com.liferay.portal.NoSuchOrgGroupPermissionException if a org group permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgGroupPermission remove(
		com.liferay.portal.service.persistence.OrgGroupPermissionPK orgGroupPermissionPK)
		throws com.liferay.portal.NoSuchOrgGroupPermissionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(orgGroupPermissionPK);
	}

	public static com.liferay.portal.model.OrgGroupPermission updateImpl(
		com.liferay.portal.model.OrgGroupPermission orgGroupPermission,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(orgGroupPermission, merge);
	}

	/**
	* Finds the org group permission with the primary key or throws a {@link com.liferay.portal.NoSuchOrgGroupPermissionException} if it could not be found.
	*
	* @param orgGroupPermissionPK the primary key of the org group permission to find
	* @return the org group permission
	* @throws com.liferay.portal.NoSuchOrgGroupPermissionException if a org group permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgGroupPermission findByPrimaryKey(
		com.liferay.portal.service.persistence.OrgGroupPermissionPK orgGroupPermissionPK)
		throws com.liferay.portal.NoSuchOrgGroupPermissionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(orgGroupPermissionPK);
	}

	/**
	* Finds the org group permission with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param orgGroupPermissionPK the primary key of the org group permission to find
	* @return the org group permission, or <code>null</code> if a org group permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgGroupPermission fetchByPrimaryKey(
		com.liferay.portal.service.persistence.OrgGroupPermissionPK orgGroupPermissionPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(orgGroupPermissionPK);
	}

	/**
	* Finds all the org group permissions where groupId = &#63;.
	*
	* @param groupId the group id to search with
	* @return the matching org group permissions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgGroupPermission> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Finds a range of all the org group permissions where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param start the lower bound of the range of org group permissions to return
	* @param end the upper bound of the range of org group permissions to return (not inclusive)
	* @return the range of matching org group permissions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgGroupPermission> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Finds an ordered range of all the org group permissions where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param start the lower bound of the range of org group permissions to return
	* @param end the upper bound of the range of org group permissions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching org group permissions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgGroupPermission> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Finds the first org group permission in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching org group permission
	* @throws com.liferay.portal.NoSuchOrgGroupPermissionException if a matching org group permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgGroupPermission findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchOrgGroupPermissionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Finds the last org group permission in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching org group permission
	* @throws com.liferay.portal.NoSuchOrgGroupPermissionException if a matching org group permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgGroupPermission findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchOrgGroupPermissionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Finds the org group permissions before and after the current org group permission in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param orgGroupPermissionPK the primary key of the current org group permission
	* @param groupId the group id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next org group permission
	* @throws com.liferay.portal.NoSuchOrgGroupPermissionException if a org group permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgGroupPermission[] findByGroupId_PrevAndNext(
		com.liferay.portal.service.persistence.OrgGroupPermissionPK orgGroupPermissionPK,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchOrgGroupPermissionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(orgGroupPermissionPK, groupId,
			orderByComparator);
	}

	/**
	* Finds all the org group permissions where permissionId = &#63;.
	*
	* @param permissionId the permission id to search with
	* @return the matching org group permissions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgGroupPermission> findByPermissionId(
		long permissionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPermissionId(permissionId);
	}

	/**
	* Finds a range of all the org group permissions where permissionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param permissionId the permission id to search with
	* @param start the lower bound of the range of org group permissions to return
	* @param end the upper bound of the range of org group permissions to return (not inclusive)
	* @return the range of matching org group permissions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgGroupPermission> findByPermissionId(
		long permissionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPermissionId(permissionId, start, end);
	}

	/**
	* Finds an ordered range of all the org group permissions where permissionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param permissionId the permission id to search with
	* @param start the lower bound of the range of org group permissions to return
	* @param end the upper bound of the range of org group permissions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching org group permissions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgGroupPermission> findByPermissionId(
		long permissionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPermissionId(permissionId, start, end,
			orderByComparator);
	}

	/**
	* Finds the first org group permission in the ordered set where permissionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param permissionId the permission id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching org group permission
	* @throws com.liferay.portal.NoSuchOrgGroupPermissionException if a matching org group permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgGroupPermission findByPermissionId_First(
		long permissionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchOrgGroupPermissionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPermissionId_First(permissionId, orderByComparator);
	}

	/**
	* Finds the last org group permission in the ordered set where permissionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param permissionId the permission id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching org group permission
	* @throws com.liferay.portal.NoSuchOrgGroupPermissionException if a matching org group permission could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgGroupPermission findByPermissionId_Last(
		long permissionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchOrgGroupPermissionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPermissionId_Last(permissionId, orderByComparator);
	}

	/**
	* Finds the org group permissions before and after the current org group permission in the ordered set where permissionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param orgGroupPermissionPK the primary key of the current org group permission
	* @param permissionId the permission id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next org group permission
	* @throws com.liferay.portal.NoSuchOrgGroupPermissionException if a org group permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.OrgGroupPermission[] findByPermissionId_PrevAndNext(
		com.liferay.portal.service.persistence.OrgGroupPermissionPK orgGroupPermissionPK,
		long permissionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchOrgGroupPermissionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPermissionId_PrevAndNext(orgGroupPermissionPK,
			permissionId, orderByComparator);
	}

	/**
	* Finds all the org group permissions.
	*
	* @return the org group permissions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgGroupPermission> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the org group permissions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of org group permissions to return
	* @param end the upper bound of the range of org group permissions to return (not inclusive)
	* @return the range of org group permissions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgGroupPermission> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the org group permissions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of org group permissions to return
	* @param end the upper bound of the range of org group permissions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of org group permissions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.OrgGroupPermission> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the org group permissions where groupId = &#63; from the database.
	*
	* @param groupId the group id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Removes all the org group permissions where permissionId = &#63; from the database.
	*
	* @param permissionId the permission id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPermissionId(long permissionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByPermissionId(permissionId);
	}

	/**
	* Removes all the org group permissions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the org group permissions where groupId = &#63;.
	*
	* @param groupId the group id to search with
	* @return the number of matching org group permissions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Counts all the org group permissions where permissionId = &#63;.
	*
	* @param permissionId the permission id to search with
	* @return the number of matching org group permissions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPermissionId(long permissionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPermissionId(permissionId);
	}

	/**
	* Counts all the org group permissions.
	*
	* @return the number of org group permissions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static OrgGroupPermissionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (OrgGroupPermissionPersistence)PortalBeanLocatorUtil.locate(OrgGroupPermissionPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(OrgGroupPermissionPersistence persistence) {
		_persistence = persistence;
	}

	private static OrgGroupPermissionPersistence _persistence;
}