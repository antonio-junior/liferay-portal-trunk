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

package com.liferay.portal.service;

/**
 * <p>
 * This class is a wrapper for {@link LayoutRevisionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LayoutRevisionLocalService
 * @generated
 */
public class LayoutRevisionLocalServiceWrapper
	implements LayoutRevisionLocalService {
	public LayoutRevisionLocalServiceWrapper(
		LayoutRevisionLocalService layoutRevisionLocalService) {
		_layoutRevisionLocalService = layoutRevisionLocalService;
	}

	/**
	* Adds the layout revision to the database. Also notifies the appropriate model listeners.
	*
	* @param layoutRevision the layout revision to add
	* @return the layout revision that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.LayoutRevision addLayoutRevision(
		com.liferay.portal.model.LayoutRevision layoutRevision)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.addLayoutRevision(layoutRevision);
	}

	/**
	* Creates a new layout revision with the primary key. Does not add the layout revision to the database.
	*
	* @param revisionId the primary key for the new layout revision
	* @return the new layout revision
	*/
	public com.liferay.portal.model.LayoutRevision createLayoutRevision(
		long revisionId) {
		return _layoutRevisionLocalService.createLayoutRevision(revisionId);
	}

	/**
	* Deletes the layout revision with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param revisionId the primary key of the layout revision to delete
	* @throws PortalException if a layout revision with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteLayoutRevision(long revisionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutRevisionLocalService.deleteLayoutRevision(revisionId);
	}

	/**
	* Deletes the layout revision from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutRevision the layout revision to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteLayoutRevision(
		com.liferay.portal.model.LayoutRevision layoutRevision)
		throws com.liferay.portal.kernel.exception.SystemException {
		_layoutRevisionLocalService.deleteLayoutRevision(layoutRevision);
	}

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
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.dynamicQuery(dynamicQuery);
	}

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
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the layout revision with the primary key.
	*
	* @param revisionId the primary key of the layout revision to get
	* @return the layout revision
	* @throws PortalException if a layout revision with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.LayoutRevision getLayoutRevision(
		long revisionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.getLayoutRevision(revisionId);
	}

	/**
	* Gets a range of all the layout revisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of layout revisions to return
	* @param end the upper bound of the range of layout revisions to return (not inclusive)
	* @return the range of layout revisions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.LayoutRevision> getLayoutRevisions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.getLayoutRevisions(start, end);
	}

	/**
	* Gets the number of layout revisions.
	*
	* @return the number of layout revisions
	* @throws SystemException if a system exception occurred
	*/
	public int getLayoutRevisionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.getLayoutRevisionsCount();
	}

	/**
	* Updates the layout revision in the database. Also notifies the appropriate model listeners.
	*
	* @param layoutRevision the layout revision to update
	* @return the layout revision that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.LayoutRevision updateLayoutRevision(
		com.liferay.portal.model.LayoutRevision layoutRevision)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.updateLayoutRevision(layoutRevision);
	}

	/**
	* Updates the layout revision in the database. Also notifies the appropriate model listeners.
	*
	* @param layoutRevision the layout revision to update
	* @param merge whether to merge the layout revision with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the layout revision that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.LayoutRevision updateLayoutRevision(
		com.liferay.portal.model.LayoutRevision layoutRevision, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.updateLayoutRevision(layoutRevision,
			merge);
	}

	public com.liferay.portal.model.LayoutRevision addRevision(long branchId,
		long plid, long groupId, java.lang.String name, java.lang.String title,
		java.lang.String description, java.lang.String typeSettings,
		boolean iconImage, long iconImageId, java.lang.String themeId,
		java.lang.String colorSchemeId, java.lang.String wapThemeId,
		java.lang.String wapColorSchemeId, java.lang.String css, boolean head,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.addRevision(branchId, plid, groupId,
			name, title, description, typeSettings, iconImage, iconImageId,
			themeId, colorSchemeId, wapThemeId, wapColorSchemeId, css, head,
			serviceContext);
	}

	public void deleteRevision(long revisionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutRevisionLocalService.deleteRevision(revisionId);
	}

	public void deleteRevision(com.liferay.portal.model.LayoutRevision revision)
		throws com.liferay.portal.kernel.exception.SystemException {
		_layoutRevisionLocalService.deleteRevision(revision);
	}

	public void deleteRevisionsByBranch(long branchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutRevisionLocalService.deleteRevisionsByBranch(branchId);
	}

	public void deleteRevisionsByLayout(long plid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutRevisionLocalService.deleteRevisionsByLayout(plid);
	}

	public void deleteRevisions(long branchId, long plid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutRevisionLocalService.deleteRevisions(branchId, plid);
	}

	public com.liferay.portal.model.LayoutRevision checkLatestRevision(
		long branchId, long plid,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.checkLatestRevision(branchId, plid,
			serviceContext);
	}

	public com.liferay.portal.model.LayoutRevision getHeadRevision(
		long branchId, long plid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.getHeadRevision(branchId, plid);
	}

	public com.liferay.portal.model.LayoutRevision getRevision(long revisionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.getRevision(revisionId);
	}

	public java.util.List<com.liferay.portal.model.LayoutRevision> getRevisions(
		long plid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.getRevisions(plid);
	}

	public java.util.List<com.liferay.portal.model.LayoutRevision> getRevisions(
		long branchId, long plid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.getRevisions(branchId, plid);
	}

	public java.util.List<com.liferay.portal.model.LayoutRevision> getRevisions(
		long branchId, long plid, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.getRevisions(branchId, plid, status);
	}

	public com.liferay.portal.model.LayoutRevision revertToRevision(
		long revisionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.revertToRevision(revisionId);
	}

	public com.liferay.portal.model.LayoutRevision updateRevision(
		long revisionId, java.lang.String name, java.lang.String title,
		java.lang.String description, java.lang.String typeSettings,
		boolean iconImage, long iconImageId, java.lang.String themeId,
		java.lang.String colorSchemeId, java.lang.String wapThemeId,
		java.lang.String wapColorSchemeId, java.lang.String css,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.updateRevision(revisionId, name,
			title, description, typeSettings, iconImage, iconImageId, themeId,
			colorSchemeId, wapThemeId, wapColorSchemeId, css, serviceContext);
	}

	public com.liferay.portal.model.LayoutRevision updateStatus(long userId,
		long revisionId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionLocalService.updateStatus(userId, revisionId,
			status, serviceContext);
	}

	public LayoutRevisionLocalService getWrappedLayoutRevisionLocalService() {
		return _layoutRevisionLocalService;
	}

	private LayoutRevisionLocalService _layoutRevisionLocalService;
}