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

import com.liferay.portal.model.ClusterGroup;

/**
 * The persistence interface for the cluster group service.
 *
 * <p>
 * Never modify this interface directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regnerate this interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ClusterGroupPersistenceImpl
 * @see ClusterGroupUtil
 * @generated
 */
public interface ClusterGroupPersistence extends BasePersistence<ClusterGroup> {
	/**
	* Caches the cluster group in the entity cache if it is enabled.
	*
	* @param clusterGroup the cluster group to cache
	*/
	public void cacheResult(com.liferay.portal.model.ClusterGroup clusterGroup);

	/**
	* Caches the cluster groups in the entity cache if it is enabled.
	*
	* @param clusterGroups the cluster groups to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.model.ClusterGroup> clusterGroups);

	/**
	* Creates a new cluster group with the primary key.
	*
	* @param clusterGroupId the primary key for the new cluster group
	* @return the new cluster group
	*/
	public com.liferay.portal.model.ClusterGroup create(long clusterGroupId);

	/**
	* Removes the cluster group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param clusterGroupId the primary key of the cluster group to remove
	* @return the cluster group that was removed
	* @throws com.liferay.portal.NoSuchClusterGroupException if a cluster group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ClusterGroup remove(long clusterGroupId)
		throws com.liferay.portal.NoSuchClusterGroupException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.ClusterGroup updateImpl(
		com.liferay.portal.model.ClusterGroup clusterGroup, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the cluster group with the primary key or throws a {@link com.liferay.portal.NoSuchClusterGroupException} if it could not be found.
	*
	* @param clusterGroupId the primary key of the cluster group to find
	* @return the cluster group
	* @throws com.liferay.portal.NoSuchClusterGroupException if a cluster group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ClusterGroup findByPrimaryKey(
		long clusterGroupId)
		throws com.liferay.portal.NoSuchClusterGroupException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the cluster group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param clusterGroupId the primary key of the cluster group to find
	* @return the cluster group, or <code>null</code> if a cluster group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ClusterGroup fetchByPrimaryKey(
		long clusterGroupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the cluster groups.
	*
	* @return the cluster groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ClusterGroup> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the cluster groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of cluster groups to return
	* @param end the upper bound of the range of cluster groups to return (not inclusive)
	* @return the range of cluster groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ClusterGroup> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the cluster groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of cluster groups to return
	* @param end the upper bound of the range of cluster groups to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of cluster groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ClusterGroup> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the cluster groups from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the cluster groups.
	*
	* @return the number of cluster groups
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}