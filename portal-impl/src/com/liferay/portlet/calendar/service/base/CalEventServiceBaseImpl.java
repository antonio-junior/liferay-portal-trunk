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

package com.liferay.portlet.calendar.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.mail.service.MailService;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.CompanyService;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.PortletPreferencesLocalService;
import com.liferay.portal.service.PortletPreferencesService;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.GroupFinder;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.PortletPreferencesFinder;
import com.liferay.portal.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.service.persistence.ResourceFinder;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.asset.service.AssetEntryLocalService;
import com.liferay.portlet.asset.service.AssetEntryService;
import com.liferay.portlet.asset.service.AssetTagLocalService;
import com.liferay.portlet.asset.service.AssetTagService;
import com.liferay.portlet.asset.service.persistence.AssetEntryFinder;
import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.asset.service.persistence.AssetTagFinder;
import com.liferay.portlet.asset.service.persistence.AssetTagPersistence;
import com.liferay.portlet.calendar.service.CalEventLocalService;
import com.liferay.portlet.calendar.service.CalEventService;
import com.liferay.portlet.calendar.service.persistence.CalEventFinder;
import com.liferay.portlet.calendar.service.persistence.CalEventPersistence;
import com.liferay.portlet.expando.service.ExpandoValueLocalService;
import com.liferay.portlet.expando.service.ExpandoValueService;
import com.liferay.portlet.expando.service.persistence.ExpandoValuePersistence;
import com.liferay.portlet.social.service.SocialActivityLocalService;
import com.liferay.portlet.social.service.persistence.SocialActivityFinder;
import com.liferay.portlet.social.service.persistence.SocialActivityPersistence;

import javax.sql.DataSource;

/**
 * The base implementation of the cal event remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.calendar.service.impl.CalEventServiceImpl}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.calendar.service.impl.CalEventServiceImpl
 * @see com.liferay.portlet.calendar.service.CalEventServiceUtil
 * @generated
 */
public abstract class CalEventServiceBaseImpl extends PrincipalBean
	implements CalEventService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portlet.calendar.service.CalEventServiceUtil} to access the cal event remote service.
	 */

	/**
	 * Gets the cal event local service.
	 *
	 * @return the cal event local service
	 */
	public CalEventLocalService getCalEventLocalService() {
		return calEventLocalService;
	}

	/**
	 * Sets the cal event local service.
	 *
	 * @param calEventLocalService the cal event local service
	 */
	public void setCalEventLocalService(
		CalEventLocalService calEventLocalService) {
		this.calEventLocalService = calEventLocalService;
	}

	/**
	 * Gets the cal event remote service.
	 *
	 * @return the cal event remote service
	 */
	public CalEventService getCalEventService() {
		return calEventService;
	}

	/**
	 * Sets the cal event remote service.
	 *
	 * @param calEventService the cal event remote service
	 */
	public void setCalEventService(CalEventService calEventService) {
		this.calEventService = calEventService;
	}

	/**
	 * Gets the cal event persistence.
	 *
	 * @return the cal event persistence
	 */
	public CalEventPersistence getCalEventPersistence() {
		return calEventPersistence;
	}

	/**
	 * Sets the cal event persistence.
	 *
	 * @param calEventPersistence the cal event persistence
	 */
	public void setCalEventPersistence(CalEventPersistence calEventPersistence) {
		this.calEventPersistence = calEventPersistence;
	}

	/**
	 * Gets the cal event finder.
	 *
	 * @return the cal event finder
	 */
	public CalEventFinder getCalEventFinder() {
		return calEventFinder;
	}

	/**
	 * Sets the cal event finder.
	 *
	 * @param calEventFinder the cal event finder
	 */
	public void setCalEventFinder(CalEventFinder calEventFinder) {
		this.calEventFinder = calEventFinder;
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
	 * Gets the mail remote service.
	 *
	 * @return the mail remote service
	 */
	public MailService getMailService() {
		return mailService;
	}

	/**
	 * Sets the mail remote service.
	 *
	 * @param mailService the mail remote service
	 */
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	/**
	 * Gets the company local service.
	 *
	 * @return the company local service
	 */
	public CompanyLocalService getCompanyLocalService() {
		return companyLocalService;
	}

	/**
	 * Sets the company local service.
	 *
	 * @param companyLocalService the company local service
	 */
	public void setCompanyLocalService(CompanyLocalService companyLocalService) {
		this.companyLocalService = companyLocalService;
	}

	/**
	 * Gets the company remote service.
	 *
	 * @return the company remote service
	 */
	public CompanyService getCompanyService() {
		return companyService;
	}

	/**
	 * Sets the company remote service.
	 *
	 * @param companyService the company remote service
	 */
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	/**
	 * Gets the company persistence.
	 *
	 * @return the company persistence
	 */
	public CompanyPersistence getCompanyPersistence() {
		return companyPersistence;
	}

	/**
	 * Sets the company persistence.
	 *
	 * @param companyPersistence the company persistence
	 */
	public void setCompanyPersistence(CompanyPersistence companyPersistence) {
		this.companyPersistence = companyPersistence;
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
	 * Gets the portlet preferences local service.
	 *
	 * @return the portlet preferences local service
	 */
	public PortletPreferencesLocalService getPortletPreferencesLocalService() {
		return portletPreferencesLocalService;
	}

	/**
	 * Sets the portlet preferences local service.
	 *
	 * @param portletPreferencesLocalService the portlet preferences local service
	 */
	public void setPortletPreferencesLocalService(
		PortletPreferencesLocalService portletPreferencesLocalService) {
		this.portletPreferencesLocalService = portletPreferencesLocalService;
	}

	/**
	 * Gets the portlet preferences remote service.
	 *
	 * @return the portlet preferences remote service
	 */
	public PortletPreferencesService getPortletPreferencesService() {
		return portletPreferencesService;
	}

	/**
	 * Sets the portlet preferences remote service.
	 *
	 * @param portletPreferencesService the portlet preferences remote service
	 */
	public void setPortletPreferencesService(
		PortletPreferencesService portletPreferencesService) {
		this.portletPreferencesService = portletPreferencesService;
	}

	/**
	 * Gets the portlet preferences persistence.
	 *
	 * @return the portlet preferences persistence
	 */
	public PortletPreferencesPersistence getPortletPreferencesPersistence() {
		return portletPreferencesPersistence;
	}

	/**
	 * Sets the portlet preferences persistence.
	 *
	 * @param portletPreferencesPersistence the portlet preferences persistence
	 */
	public void setPortletPreferencesPersistence(
		PortletPreferencesPersistence portletPreferencesPersistence) {
		this.portletPreferencesPersistence = portletPreferencesPersistence;
	}

	/**
	 * Gets the portlet preferences finder.
	 *
	 * @return the portlet preferences finder
	 */
	public PortletPreferencesFinder getPortletPreferencesFinder() {
		return portletPreferencesFinder;
	}

	/**
	 * Sets the portlet preferences finder.
	 *
	 * @param portletPreferencesFinder the portlet preferences finder
	 */
	public void setPortletPreferencesFinder(
		PortletPreferencesFinder portletPreferencesFinder) {
		this.portletPreferencesFinder = portletPreferencesFinder;
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
	 * Gets the asset entry local service.
	 *
	 * @return the asset entry local service
	 */
	public AssetEntryLocalService getAssetEntryLocalService() {
		return assetEntryLocalService;
	}

	/**
	 * Sets the asset entry local service.
	 *
	 * @param assetEntryLocalService the asset entry local service
	 */
	public void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {
		this.assetEntryLocalService = assetEntryLocalService;
	}

	/**
	 * Gets the asset entry remote service.
	 *
	 * @return the asset entry remote service
	 */
	public AssetEntryService getAssetEntryService() {
		return assetEntryService;
	}

	/**
	 * Sets the asset entry remote service.
	 *
	 * @param assetEntryService the asset entry remote service
	 */
	public void setAssetEntryService(AssetEntryService assetEntryService) {
		this.assetEntryService = assetEntryService;
	}

	/**
	 * Gets the asset entry persistence.
	 *
	 * @return the asset entry persistence
	 */
	public AssetEntryPersistence getAssetEntryPersistence() {
		return assetEntryPersistence;
	}

	/**
	 * Sets the asset entry persistence.
	 *
	 * @param assetEntryPersistence the asset entry persistence
	 */
	public void setAssetEntryPersistence(
		AssetEntryPersistence assetEntryPersistence) {
		this.assetEntryPersistence = assetEntryPersistence;
	}

	/**
	 * Gets the asset entry finder.
	 *
	 * @return the asset entry finder
	 */
	public AssetEntryFinder getAssetEntryFinder() {
		return assetEntryFinder;
	}

	/**
	 * Sets the asset entry finder.
	 *
	 * @param assetEntryFinder the asset entry finder
	 */
	public void setAssetEntryFinder(AssetEntryFinder assetEntryFinder) {
		this.assetEntryFinder = assetEntryFinder;
	}

	/**
	 * Gets the asset tag local service.
	 *
	 * @return the asset tag local service
	 */
	public AssetTagLocalService getAssetTagLocalService() {
		return assetTagLocalService;
	}

	/**
	 * Sets the asset tag local service.
	 *
	 * @param assetTagLocalService the asset tag local service
	 */
	public void setAssetTagLocalService(
		AssetTagLocalService assetTagLocalService) {
		this.assetTagLocalService = assetTagLocalService;
	}

	/**
	 * Gets the asset tag remote service.
	 *
	 * @return the asset tag remote service
	 */
	public AssetTagService getAssetTagService() {
		return assetTagService;
	}

	/**
	 * Sets the asset tag remote service.
	 *
	 * @param assetTagService the asset tag remote service
	 */
	public void setAssetTagService(AssetTagService assetTagService) {
		this.assetTagService = assetTagService;
	}

	/**
	 * Gets the asset tag persistence.
	 *
	 * @return the asset tag persistence
	 */
	public AssetTagPersistence getAssetTagPersistence() {
		return assetTagPersistence;
	}

	/**
	 * Sets the asset tag persistence.
	 *
	 * @param assetTagPersistence the asset tag persistence
	 */
	public void setAssetTagPersistence(AssetTagPersistence assetTagPersistence) {
		this.assetTagPersistence = assetTagPersistence;
	}

	/**
	 * Gets the asset tag finder.
	 *
	 * @return the asset tag finder
	 */
	public AssetTagFinder getAssetTagFinder() {
		return assetTagFinder;
	}

	/**
	 * Sets the asset tag finder.
	 *
	 * @param assetTagFinder the asset tag finder
	 */
	public void setAssetTagFinder(AssetTagFinder assetTagFinder) {
		this.assetTagFinder = assetTagFinder;
	}

	/**
	 * Gets the expando value local service.
	 *
	 * @return the expando value local service
	 */
	public ExpandoValueLocalService getExpandoValueLocalService() {
		return expandoValueLocalService;
	}

	/**
	 * Sets the expando value local service.
	 *
	 * @param expandoValueLocalService the expando value local service
	 */
	public void setExpandoValueLocalService(
		ExpandoValueLocalService expandoValueLocalService) {
		this.expandoValueLocalService = expandoValueLocalService;
	}

	/**
	 * Gets the expando value remote service.
	 *
	 * @return the expando value remote service
	 */
	public ExpandoValueService getExpandoValueService() {
		return expandoValueService;
	}

	/**
	 * Sets the expando value remote service.
	 *
	 * @param expandoValueService the expando value remote service
	 */
	public void setExpandoValueService(ExpandoValueService expandoValueService) {
		this.expandoValueService = expandoValueService;
	}

	/**
	 * Gets the expando value persistence.
	 *
	 * @return the expando value persistence
	 */
	public ExpandoValuePersistence getExpandoValuePersistence() {
		return expandoValuePersistence;
	}

	/**
	 * Sets the expando value persistence.
	 *
	 * @param expandoValuePersistence the expando value persistence
	 */
	public void setExpandoValuePersistence(
		ExpandoValuePersistence expandoValuePersistence) {
		this.expandoValuePersistence = expandoValuePersistence;
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
	 * Performs an SQL query.
	 *
	 * @param sql the sql query to perform
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = calEventPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = CalEventLocalService.class)
	protected CalEventLocalService calEventLocalService;
	@BeanReference(type = CalEventService.class)
	protected CalEventService calEventService;
	@BeanReference(type = CalEventPersistence.class)
	protected CalEventPersistence calEventPersistence;
	@BeanReference(type = CalEventFinder.class)
	protected CalEventFinder calEventFinder;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = MailService.class)
	protected MailService mailService;
	@BeanReference(type = CompanyLocalService.class)
	protected CompanyLocalService companyLocalService;
	@BeanReference(type = CompanyService.class)
	protected CompanyService companyService;
	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@BeanReference(type = GroupLocalService.class)
	protected GroupLocalService groupLocalService;
	@BeanReference(type = GroupService.class)
	protected GroupService groupService;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = GroupFinder.class)
	protected GroupFinder groupFinder;
	@BeanReference(type = PortletPreferencesLocalService.class)
	protected PortletPreferencesLocalService portletPreferencesLocalService;
	@BeanReference(type = PortletPreferencesService.class)
	protected PortletPreferencesService portletPreferencesService;
	@BeanReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;
	@BeanReference(type = PortletPreferencesFinder.class)
	protected PortletPreferencesFinder portletPreferencesFinder;
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
	@BeanReference(type = AssetEntryLocalService.class)
	protected AssetEntryLocalService assetEntryLocalService;
	@BeanReference(type = AssetEntryService.class)
	protected AssetEntryService assetEntryService;
	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	@BeanReference(type = AssetEntryFinder.class)
	protected AssetEntryFinder assetEntryFinder;
	@BeanReference(type = AssetTagLocalService.class)
	protected AssetTagLocalService assetTagLocalService;
	@BeanReference(type = AssetTagService.class)
	protected AssetTagService assetTagService;
	@BeanReference(type = AssetTagPersistence.class)
	protected AssetTagPersistence assetTagPersistence;
	@BeanReference(type = AssetTagFinder.class)
	protected AssetTagFinder assetTagFinder;
	@BeanReference(type = ExpandoValueLocalService.class)
	protected ExpandoValueLocalService expandoValueLocalService;
	@BeanReference(type = ExpandoValueService.class)
	protected ExpandoValueService expandoValueService;
	@BeanReference(type = ExpandoValuePersistence.class)
	protected ExpandoValuePersistence expandoValuePersistence;
	@BeanReference(type = SocialActivityLocalService.class)
	protected SocialActivityLocalService socialActivityLocalService;
	@BeanReference(type = SocialActivityPersistence.class)
	protected SocialActivityPersistence socialActivityPersistence;
	@BeanReference(type = SocialActivityFinder.class)
	protected SocialActivityFinder socialActivityFinder;
}