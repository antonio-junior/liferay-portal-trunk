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

package com.liferay.portlet.social.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.LayoutLocalService;
import com.liferay.portal.service.LayoutService;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.GroupFinder;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.LayoutFinder;
import com.liferay.portal.service.persistence.LayoutPersistence;
import com.liferay.portal.service.persistence.ResourceFinder;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalService;
import com.liferay.portlet.social.service.SocialActivityLocalService;
import com.liferay.portlet.social.service.SocialEquityGroupSettingLocalService;
import com.liferay.portlet.social.service.SocialEquityHistoryLocalService;
import com.liferay.portlet.social.service.SocialEquityLogLocalService;
import com.liferay.portlet.social.service.SocialEquitySettingLocalService;
import com.liferay.portlet.social.service.SocialEquityUserLocalService;
import com.liferay.portlet.social.service.SocialRelationLocalService;
import com.liferay.portlet.social.service.SocialRequestInterpreterLocalService;
import com.liferay.portlet.social.service.SocialRequestLocalService;
import com.liferay.portlet.social.service.persistence.SocialActivityFinder;
import com.liferay.portlet.social.service.persistence.SocialActivityPersistence;
import com.liferay.portlet.social.service.persistence.SocialEquityAssetEntryPersistence;
import com.liferay.portlet.social.service.persistence.SocialEquityGroupSettingPersistence;
import com.liferay.portlet.social.service.persistence.SocialEquityHistoryPersistence;
import com.liferay.portlet.social.service.persistence.SocialEquityLogPersistence;
import com.liferay.portlet.social.service.persistence.SocialEquitySettingPersistence;
import com.liferay.portlet.social.service.persistence.SocialEquityUserPersistence;
import com.liferay.portlet.social.service.persistence.SocialRelationPersistence;
import com.liferay.portlet.social.service.persistence.SocialRequestPersistence;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the social activity local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.social.service.impl.SocialActivityLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.social.service.impl.SocialActivityLocalServiceImpl
 * @see com.liferay.portlet.social.service.SocialActivityLocalServiceUtil
 * @generated
 */
public abstract class SocialActivityLocalServiceBaseImpl
	implements SocialActivityLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portlet.social.service.SocialActivityLocalServiceUtil} to access the social activity local service.
	 */

	/**
	 * Adds the social activity to the database. Also notifies the appropriate model listeners.
	 *
	 * @param socialActivity the social activity to add
	 * @return the social activity that was added
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivity addSocialActivity(SocialActivity socialActivity)
		throws SystemException {
		socialActivity.setNew(true);

		return socialActivityPersistence.update(socialActivity, false);
	}

	/**
	 * Creates a new social activity with the primary key. Does not add the social activity to the database.
	 *
	 * @param activityId the primary key for the new social activity
	 * @return the new social activity
	 */
	public SocialActivity createSocialActivity(long activityId) {
		return socialActivityPersistence.create(activityId);
	}

	/**
	 * Deletes the social activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param activityId the primary key of the social activity to delete
	 * @throws PortalException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteSocialActivity(long activityId)
		throws PortalException, SystemException {
		socialActivityPersistence.remove(activityId);
	}

	/**
	 * Deletes the social activity from the database. Also notifies the appropriate model listeners.
	 *
	 * @param socialActivity the social activity to delete
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteSocialActivity(SocialActivity socialActivity)
		throws SystemException {
		socialActivityPersistence.remove(socialActivity);
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query to search with
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return socialActivityPersistence.findWithDynamicQuery(dynamicQuery);
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
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return socialActivityPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
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
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return socialActivityPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Counts the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query to search with
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return socialActivityPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Gets the social activity with the primary key.
	 *
	 * @param activityId the primary key of the social activity to get
	 * @return the social activity
	 * @throws PortalException if a social activity with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivity getSocialActivity(long activityId)
		throws PortalException, SystemException {
		return socialActivityPersistence.findByPrimaryKey(activityId);
	}

	/**
	 * Gets a range of all the social activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of social activities to return
	 * @param end the upper bound of the range of social activities to return (not inclusive)
	 * @return the range of social activities
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivity> getSocialActivities(int start, int end)
		throws SystemException {
		return socialActivityPersistence.findAll(start, end);
	}

	/**
	 * Gets the number of social activities.
	 *
	 * @return the number of social activities
	 * @throws SystemException if a system exception occurred
	 */
	public int getSocialActivitiesCount() throws SystemException {
		return socialActivityPersistence.countAll();
	}

	/**
	 * Updates the social activity in the database. Also notifies the appropriate model listeners.
	 *
	 * @param socialActivity the social activity to update
	 * @return the social activity that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivity updateSocialActivity(SocialActivity socialActivity)
		throws SystemException {
		socialActivity.setNew(false);

		return socialActivityPersistence.update(socialActivity, true);
	}

	/**
	 * Updates the social activity in the database. Also notifies the appropriate model listeners.
	 *
	 * @param socialActivity the social activity to update
	 * @param merge whether to merge the social activity with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the social activity that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivity updateSocialActivity(SocialActivity socialActivity,
		boolean merge) throws SystemException {
		socialActivity.setNew(false);

		return socialActivityPersistence.update(socialActivity, merge);
	}

	/**
	 * Gets the social activity local service.
	 *
	 * @return the social activity local service
	 */
	public SocialActivityLocalService getSocialActivityLocalService() {
		return socialActivityLocalService;
	}

	/**
	 * Sets the social activity local service.
	 *
	 * @param socialActivityLocalService the social activity local service
	 */
	public void setSocialActivityLocalService(
		SocialActivityLocalService socialActivityLocalService) {
		this.socialActivityLocalService = socialActivityLocalService;
	}

	/**
	 * Gets the social activity persistence.
	 *
	 * @return the social activity persistence
	 */
	public SocialActivityPersistence getSocialActivityPersistence() {
		return socialActivityPersistence;
	}

	/**
	 * Sets the social activity persistence.
	 *
	 * @param socialActivityPersistence the social activity persistence
	 */
	public void setSocialActivityPersistence(
		SocialActivityPersistence socialActivityPersistence) {
		this.socialActivityPersistence = socialActivityPersistence;
	}

	/**
	 * Gets the social activity finder.
	 *
	 * @return the social activity finder
	 */
	public SocialActivityFinder getSocialActivityFinder() {
		return socialActivityFinder;
	}

	/**
	 * Sets the social activity finder.
	 *
	 * @param socialActivityFinder the social activity finder
	 */
	public void setSocialActivityFinder(
		SocialActivityFinder socialActivityFinder) {
		this.socialActivityFinder = socialActivityFinder;
	}

	/**
	 * Gets the social activity interpreter local service.
	 *
	 * @return the social activity interpreter local service
	 */
	public SocialActivityInterpreterLocalService getSocialActivityInterpreterLocalService() {
		return socialActivityInterpreterLocalService;
	}

	/**
	 * Sets the social activity interpreter local service.
	 *
	 * @param socialActivityInterpreterLocalService the social activity interpreter local service
	 */
	public void setSocialActivityInterpreterLocalService(
		SocialActivityInterpreterLocalService socialActivityInterpreterLocalService) {
		this.socialActivityInterpreterLocalService = socialActivityInterpreterLocalService;
	}

	/**
	 * Gets the social equity asset entry persistence.
	 *
	 * @return the social equity asset entry persistence
	 */
	public SocialEquityAssetEntryPersistence getSocialEquityAssetEntryPersistence() {
		return socialEquityAssetEntryPersistence;
	}

	/**
	 * Sets the social equity asset entry persistence.
	 *
	 * @param socialEquityAssetEntryPersistence the social equity asset entry persistence
	 */
	public void setSocialEquityAssetEntryPersistence(
		SocialEquityAssetEntryPersistence socialEquityAssetEntryPersistence) {
		this.socialEquityAssetEntryPersistence = socialEquityAssetEntryPersistence;
	}

	/**
	 * Gets the social equity group setting local service.
	 *
	 * @return the social equity group setting local service
	 */
	public SocialEquityGroupSettingLocalService getSocialEquityGroupSettingLocalService() {
		return socialEquityGroupSettingLocalService;
	}

	/**
	 * Sets the social equity group setting local service.
	 *
	 * @param socialEquityGroupSettingLocalService the social equity group setting local service
	 */
	public void setSocialEquityGroupSettingLocalService(
		SocialEquityGroupSettingLocalService socialEquityGroupSettingLocalService) {
		this.socialEquityGroupSettingLocalService = socialEquityGroupSettingLocalService;
	}

	/**
	 * Gets the social equity group setting persistence.
	 *
	 * @return the social equity group setting persistence
	 */
	public SocialEquityGroupSettingPersistence getSocialEquityGroupSettingPersistence() {
		return socialEquityGroupSettingPersistence;
	}

	/**
	 * Sets the social equity group setting persistence.
	 *
	 * @param socialEquityGroupSettingPersistence the social equity group setting persistence
	 */
	public void setSocialEquityGroupSettingPersistence(
		SocialEquityGroupSettingPersistence socialEquityGroupSettingPersistence) {
		this.socialEquityGroupSettingPersistence = socialEquityGroupSettingPersistence;
	}

	/**
	 * Gets the social equity history local service.
	 *
	 * @return the social equity history local service
	 */
	public SocialEquityHistoryLocalService getSocialEquityHistoryLocalService() {
		return socialEquityHistoryLocalService;
	}

	/**
	 * Sets the social equity history local service.
	 *
	 * @param socialEquityHistoryLocalService the social equity history local service
	 */
	public void setSocialEquityHistoryLocalService(
		SocialEquityHistoryLocalService socialEquityHistoryLocalService) {
		this.socialEquityHistoryLocalService = socialEquityHistoryLocalService;
	}

	/**
	 * Gets the social equity history persistence.
	 *
	 * @return the social equity history persistence
	 */
	public SocialEquityHistoryPersistence getSocialEquityHistoryPersistence() {
		return socialEquityHistoryPersistence;
	}

	/**
	 * Sets the social equity history persistence.
	 *
	 * @param socialEquityHistoryPersistence the social equity history persistence
	 */
	public void setSocialEquityHistoryPersistence(
		SocialEquityHistoryPersistence socialEquityHistoryPersistence) {
		this.socialEquityHistoryPersistence = socialEquityHistoryPersistence;
	}

	/**
	 * Gets the social equity log local service.
	 *
	 * @return the social equity log local service
	 */
	public SocialEquityLogLocalService getSocialEquityLogLocalService() {
		return socialEquityLogLocalService;
	}

	/**
	 * Sets the social equity log local service.
	 *
	 * @param socialEquityLogLocalService the social equity log local service
	 */
	public void setSocialEquityLogLocalService(
		SocialEquityLogLocalService socialEquityLogLocalService) {
		this.socialEquityLogLocalService = socialEquityLogLocalService;
	}

	/**
	 * Gets the social equity log persistence.
	 *
	 * @return the social equity log persistence
	 */
	public SocialEquityLogPersistence getSocialEquityLogPersistence() {
		return socialEquityLogPersistence;
	}

	/**
	 * Sets the social equity log persistence.
	 *
	 * @param socialEquityLogPersistence the social equity log persistence
	 */
	public void setSocialEquityLogPersistence(
		SocialEquityLogPersistence socialEquityLogPersistence) {
		this.socialEquityLogPersistence = socialEquityLogPersistence;
	}

	/**
	 * Gets the social equity setting local service.
	 *
	 * @return the social equity setting local service
	 */
	public SocialEquitySettingLocalService getSocialEquitySettingLocalService() {
		return socialEquitySettingLocalService;
	}

	/**
	 * Sets the social equity setting local service.
	 *
	 * @param socialEquitySettingLocalService the social equity setting local service
	 */
	public void setSocialEquitySettingLocalService(
		SocialEquitySettingLocalService socialEquitySettingLocalService) {
		this.socialEquitySettingLocalService = socialEquitySettingLocalService;
	}

	/**
	 * Gets the social equity setting persistence.
	 *
	 * @return the social equity setting persistence
	 */
	public SocialEquitySettingPersistence getSocialEquitySettingPersistence() {
		return socialEquitySettingPersistence;
	}

	/**
	 * Sets the social equity setting persistence.
	 *
	 * @param socialEquitySettingPersistence the social equity setting persistence
	 */
	public void setSocialEquitySettingPersistence(
		SocialEquitySettingPersistence socialEquitySettingPersistence) {
		this.socialEquitySettingPersistence = socialEquitySettingPersistence;
	}

	/**
	 * Gets the social equity user local service.
	 *
	 * @return the social equity user local service
	 */
	public SocialEquityUserLocalService getSocialEquityUserLocalService() {
		return socialEquityUserLocalService;
	}

	/**
	 * Sets the social equity user local service.
	 *
	 * @param socialEquityUserLocalService the social equity user local service
	 */
	public void setSocialEquityUserLocalService(
		SocialEquityUserLocalService socialEquityUserLocalService) {
		this.socialEquityUserLocalService = socialEquityUserLocalService;
	}

	/**
	 * Gets the social equity user persistence.
	 *
	 * @return the social equity user persistence
	 */
	public SocialEquityUserPersistence getSocialEquityUserPersistence() {
		return socialEquityUserPersistence;
	}

	/**
	 * Sets the social equity user persistence.
	 *
	 * @param socialEquityUserPersistence the social equity user persistence
	 */
	public void setSocialEquityUserPersistence(
		SocialEquityUserPersistence socialEquityUserPersistence) {
		this.socialEquityUserPersistence = socialEquityUserPersistence;
	}

	/**
	 * Gets the social relation local service.
	 *
	 * @return the social relation local service
	 */
	public SocialRelationLocalService getSocialRelationLocalService() {
		return socialRelationLocalService;
	}

	/**
	 * Sets the social relation local service.
	 *
	 * @param socialRelationLocalService the social relation local service
	 */
	public void setSocialRelationLocalService(
		SocialRelationLocalService socialRelationLocalService) {
		this.socialRelationLocalService = socialRelationLocalService;
	}

	/**
	 * Gets the social relation persistence.
	 *
	 * @return the social relation persistence
	 */
	public SocialRelationPersistence getSocialRelationPersistence() {
		return socialRelationPersistence;
	}

	/**
	 * Sets the social relation persistence.
	 *
	 * @param socialRelationPersistence the social relation persistence
	 */
	public void setSocialRelationPersistence(
		SocialRelationPersistence socialRelationPersistence) {
		this.socialRelationPersistence = socialRelationPersistence;
	}

	/**
	 * Gets the social request local service.
	 *
	 * @return the social request local service
	 */
	public SocialRequestLocalService getSocialRequestLocalService() {
		return socialRequestLocalService;
	}

	/**
	 * Sets the social request local service.
	 *
	 * @param socialRequestLocalService the social request local service
	 */
	public void setSocialRequestLocalService(
		SocialRequestLocalService socialRequestLocalService) {
		this.socialRequestLocalService = socialRequestLocalService;
	}

	/**
	 * Gets the social request persistence.
	 *
	 * @return the social request persistence
	 */
	public SocialRequestPersistence getSocialRequestPersistence() {
		return socialRequestPersistence;
	}

	/**
	 * Sets the social request persistence.
	 *
	 * @param socialRequestPersistence the social request persistence
	 */
	public void setSocialRequestPersistence(
		SocialRequestPersistence socialRequestPersistence) {
		this.socialRequestPersistence = socialRequestPersistence;
	}

	/**
	 * Gets the social request interpreter local service.
	 *
	 * @return the social request interpreter local service
	 */
	public SocialRequestInterpreterLocalService getSocialRequestInterpreterLocalService() {
		return socialRequestInterpreterLocalService;
	}

	/**
	 * Sets the social request interpreter local service.
	 *
	 * @param socialRequestInterpreterLocalService the social request interpreter local service
	 */
	public void setSocialRequestInterpreterLocalService(
		SocialRequestInterpreterLocalService socialRequestInterpreterLocalService) {
		this.socialRequestInterpreterLocalService = socialRequestInterpreterLocalService;
	}

	/**
	 * Gets the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Gets the group local service.
	 *
	 * @return the group local service
	 */
	public GroupLocalService getGroupLocalService() {
		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	/**
	 * Gets the group remote service.
	 *
	 * @return the group remote service
	 */
	public GroupService getGroupService() {
		return groupService;
	}

	/**
	 * Sets the group remote service.
	 *
	 * @param groupService the group remote service
	 */
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	/**
	 * Gets the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Gets the group finder.
	 *
	 * @return the group finder
	 */
	public GroupFinder getGroupFinder() {
		return groupFinder;
	}

	/**
	 * Sets the group finder.
	 *
	 * @param groupFinder the group finder
	 */
	public void setGroupFinder(GroupFinder groupFinder) {
		this.groupFinder = groupFinder;
	}

	/**
	 * Gets the layout local service.
	 *
	 * @return the layout local service
	 */
	public LayoutLocalService getLayoutLocalService() {
		return layoutLocalService;
	}

	/**
	 * Sets the layout local service.
	 *
	 * @param layoutLocalService the layout local service
	 */
	public void setLayoutLocalService(LayoutLocalService layoutLocalService) {
		this.layoutLocalService = layoutLocalService;
	}

	/**
	 * Gets the layout remote service.
	 *
	 * @return the layout remote service
	 */
	public LayoutService getLayoutService() {
		return layoutService;
	}

	/**
	 * Sets the layout remote service.
	 *
	 * @param layoutService the layout remote service
	 */
	public void setLayoutService(LayoutService layoutService) {
		this.layoutService = layoutService;
	}

	/**
	 * Gets the layout persistence.
	 *
	 * @return the layout persistence
	 */
	public LayoutPersistence getLayoutPersistence() {
		return layoutPersistence;
	}

	/**
	 * Sets the layout persistence.
	 *
	 * @param layoutPersistence the layout persistence
	 */
	public void setLayoutPersistence(LayoutPersistence layoutPersistence) {
		this.layoutPersistence = layoutPersistence;
	}

	/**
	 * Gets the layout finder.
	 *
	 * @return the layout finder
	 */
	public LayoutFinder getLayoutFinder() {
		return layoutFinder;
	}

	/**
	 * Sets the layout finder.
	 *
	 * @param layoutFinder the layout finder
	 */
	public void setLayoutFinder(LayoutFinder layoutFinder) {
		this.layoutFinder = layoutFinder;
	}

	/**
	 * Gets the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Gets the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Gets the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Gets the resource finder.
	 *
	 * @return the resource finder
	 */
	public ResourceFinder getResourceFinder() {
		return resourceFinder;
	}

	/**
	 * Sets the resource finder.
	 *
	 * @param resourceFinder the resource finder
	 */
	public void setResourceFinder(ResourceFinder resourceFinder) {
		this.resourceFinder = resourceFinder;
	}

	/**
	 * Gets the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Gets the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Gets the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Gets the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	/**
	 * Gets the Spring bean ID for this implementation.
	 *
	 * @return the Spring bean ID for this implementation
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this implementation.
	 *
	 * @param beanIdentifier the Spring bean ID for this implementation
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query to perform
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = socialActivityPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = SocialActivityLocalService.class)
	protected SocialActivityLocalService socialActivityLocalService;
	@BeanReference(type = SocialActivityPersistence.class)
	protected SocialActivityPersistence socialActivityPersistence;
	@BeanReference(type = SocialActivityFinder.class)
	protected SocialActivityFinder socialActivityFinder;
	@BeanReference(type = SocialActivityInterpreterLocalService.class)
	protected SocialActivityInterpreterLocalService socialActivityInterpreterLocalService;
	@BeanReference(type = SocialEquityAssetEntryPersistence.class)
	protected SocialEquityAssetEntryPersistence socialEquityAssetEntryPersistence;
	@BeanReference(type = SocialEquityGroupSettingLocalService.class)
	protected SocialEquityGroupSettingLocalService socialEquityGroupSettingLocalService;
	@BeanReference(type = SocialEquityGroupSettingPersistence.class)
	protected SocialEquityGroupSettingPersistence socialEquityGroupSettingPersistence;
	@BeanReference(type = SocialEquityHistoryLocalService.class)
	protected SocialEquityHistoryLocalService socialEquityHistoryLocalService;
	@BeanReference(type = SocialEquityHistoryPersistence.class)
	protected SocialEquityHistoryPersistence socialEquityHistoryPersistence;
	@BeanReference(type = SocialEquityLogLocalService.class)
	protected SocialEquityLogLocalService socialEquityLogLocalService;
	@BeanReference(type = SocialEquityLogPersistence.class)
	protected SocialEquityLogPersistence socialEquityLogPersistence;
	@BeanReference(type = SocialEquitySettingLocalService.class)
	protected SocialEquitySettingLocalService socialEquitySettingLocalService;
	@BeanReference(type = SocialEquitySettingPersistence.class)
	protected SocialEquitySettingPersistence socialEquitySettingPersistence;
	@BeanReference(type = SocialEquityUserLocalService.class)
	protected SocialEquityUserLocalService socialEquityUserLocalService;
	@BeanReference(type = SocialEquityUserPersistence.class)
	protected SocialEquityUserPersistence socialEquityUserPersistence;
	@BeanReference(type = SocialRelationLocalService.class)
	protected SocialRelationLocalService socialRelationLocalService;
	@BeanReference(type = SocialRelationPersistence.class)
	protected SocialRelationPersistence socialRelationPersistence;
	@BeanReference(type = SocialRequestLocalService.class)
	protected SocialRequestLocalService socialRequestLocalService;
	@BeanReference(type = SocialRequestPersistence.class)
	protected SocialRequestPersistence socialRequestPersistence;
	@BeanReference(type = SocialRequestInterpreterLocalService.class)
	protected SocialRequestInterpreterLocalService socialRequestInterpreterLocalService;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = GroupLocalService.class)
	protected GroupLocalService groupLocalService;
	@BeanReference(type = GroupService.class)
	protected GroupService groupService;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = GroupFinder.class)
	protected GroupFinder groupFinder;
	@BeanReference(type = LayoutLocalService.class)
	protected LayoutLocalService layoutLocalService;
	@BeanReference(type = LayoutService.class)
	protected LayoutService layoutService;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = LayoutFinder.class)
	protected LayoutFinder layoutFinder;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = ResourceFinder.class)
	protected ResourceFinder resourceFinder;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	private String _beanIdentifier;
}