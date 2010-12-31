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

package com.liferay.portal.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the PortletPreferences service. Represents a row in the &quot;PortletPreferences&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.PortletPreferencesModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.PortletPreferencesImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortletPreferences
 * @see com.liferay.portal.model.impl.PortletPreferencesImpl
 * @see com.liferay.portal.model.impl.PortletPreferencesModelImpl
 * @generated
 */
public interface PortletPreferencesModel extends BaseModel<PortletPreferences> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a portlet preferences model instance should use the {@link PortletPreferences} interface instead.
	 */

	/**
	 * Gets the primary key of this portlet preferences.
	 *
	 * @return the primary key of this portlet preferences
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this portlet preferences
	 *
	 * @param pk the primary key of this portlet preferences
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the portlet preferences ID of this portlet preferences.
	 *
	 * @return the portlet preferences ID of this portlet preferences
	 */
	public long getPortletPreferencesId();

	/**
	 * Sets the portlet preferences ID of this portlet preferences.
	 *
	 * @param portletPreferencesId the portlet preferences ID of this portlet preferences
	 */
	public void setPortletPreferencesId(long portletPreferencesId);

	/**
	 * Gets the owner ID of this portlet preferences.
	 *
	 * @return the owner ID of this portlet preferences
	 */
	public long getOwnerId();

	/**
	 * Sets the owner ID of this portlet preferences.
	 *
	 * @param ownerId the owner ID of this portlet preferences
	 */
	public void setOwnerId(long ownerId);

	/**
	 * Gets the owner type of this portlet preferences.
	 *
	 * @return the owner type of this portlet preferences
	 */
	public int getOwnerType();

	/**
	 * Sets the owner type of this portlet preferences.
	 *
	 * @param ownerType the owner type of this portlet preferences
	 */
	public void setOwnerType(int ownerType);

	/**
	 * Gets the plid of this portlet preferences.
	 *
	 * @return the plid of this portlet preferences
	 */
	public long getPlid();

	/**
	 * Sets the plid of this portlet preferences.
	 *
	 * @param plid the plid of this portlet preferences
	 */
	public void setPlid(long plid);

	/**
	 * Gets the portlet ID of this portlet preferences.
	 *
	 * @return the portlet ID of this portlet preferences
	 */
	@AutoEscape
	public String getPortletId();

	/**
	 * Sets the portlet ID of this portlet preferences.
	 *
	 * @param portletId the portlet ID of this portlet preferences
	 */
	public void setPortletId(String portletId);

	/**
	 * Gets the preferences of this portlet preferences.
	 *
	 * @return the preferences of this portlet preferences
	 */
	@AutoEscape
	public String getPreferences();

	/**
	 * Sets the preferences of this portlet preferences.
	 *
	 * @param preferences the preferences of this portlet preferences
	 */
	public void setPreferences(String preferences);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(PortletPreferences portletPreferences);

	public int hashCode();

	public PortletPreferences toEscapedModel();

	public String toString();

	public String toXmlString();
}