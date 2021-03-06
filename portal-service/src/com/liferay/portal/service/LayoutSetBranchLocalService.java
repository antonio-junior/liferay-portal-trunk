/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the layout set branch local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetBranchLocalServiceUtil
 * @see com.liferay.portal.service.base.LayoutSetBranchLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.LayoutSetBranchLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LayoutSetBranchLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LayoutSetBranchLocalServiceUtil} to access the layout set branch local service. Add custom service methods to {@link com.liferay.portal.service.impl.LayoutSetBranchLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the layout set branch to the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranch the layout set branch to add
	* @return the layout set branch that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.LayoutSetBranch addLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new layout set branch with the primary key. Does not add the layout set branch to the database.
	*
	* @param layoutSetBranchId the primary key for the new layout set branch
	* @return the new layout set branch
	*/
	public com.liferay.portal.model.LayoutSetBranch createLayoutSetBranch(
		long layoutSetBranchId);

	/**
	* Deletes the layout set branch with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranchId the primary key of the layout set branch to delete
	* @throws PortalException if a layout set branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteLayoutSetBranch(long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the layout set branch from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranch the layout set branch to delete
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public void deleteLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the layout set branch with the primary key.
	*
	* @param layoutSetBranchId the primary key of the layout set branch to get
	* @return the layout set branch
	* @throws PortalException if a layout set branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.LayoutSetBranch getLayoutSetBranch(
		long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets a range of all the layout set branchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of layout set branchs to return
	* @param end the upper bound of the range of layout set branchs to return (not inclusive)
	* @return the range of layout set branchs
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.LayoutSetBranch> getLayoutSetBranchs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the number of layout set branchs.
	*
	* @return the number of layout set branchs
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLayoutSetBranchsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the layout set branch in the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranch the layout set branch to update
	* @return the layout set branch that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.LayoutSetBranch updateLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the layout set branch in the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranch the layout set branch to update
	* @param merge whether to merge the layout set branch with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the layout set branch that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.LayoutSetBranch updateLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public com.liferay.portal.model.LayoutSetBranch addLayoutSetBranch(
		long userId, long groupId, boolean privateLayout,
		java.lang.String name, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch,
		boolean includeMaster)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteLayoutSetBranches(long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteLayoutSetBranches(long groupId, boolean privateLayout,
		boolean includeMaster)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.LayoutSetBranch getLayoutSetBranch(
		long groupId, boolean privateLayout, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.LayoutSetBranch> getLayoutSetBranches(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.LayoutSetBranch getMasterLayoutSetBranch(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.LayoutSetBranch getUserLayoutSetBranch(
		long userId, long groupId, boolean privateLayout, long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.LayoutSetBranch mergeLayoutSetBranch(
		long layoutSetBranchId, long mergeLayoutSetBranchId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.LayoutSetBranch updateLayoutSetBranch(
		long layoutSetBranchId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}