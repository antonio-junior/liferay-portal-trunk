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

package com.liferay.portlet.announcements.model;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the AnnouncementsFlag service. Represents a row in the &quot;AnnouncementsFlag&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.announcements.model.impl.AnnouncementsFlagModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. All helper methods and application logic should be put in {@link com.liferay.portlet.announcements.model.impl.AnnouncementsFlagImpl}.
 * </p>
 *
 * <p>
 * Never reference this interface directly. All methods that expect a  announcements flag model instance should use the {@link AnnouncementsFlag} interface instead.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AnnouncementsFlag
 * @see       com.liferay.portlet.announcements.model.impl.AnnouncementsFlagImpl
 * @see       com.liferay.portlet.announcements.model.impl.AnnouncementsFlagModelImpl
 * @generated
 */
public interface AnnouncementsFlagModel extends BaseModel<AnnouncementsFlag> {
	/**
	 * Gets the primary key of this  announcements flag.
	 *
	 * @return the primary key of this  announcements flag
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this  announcements flag
	 *
	 * @param pk the primary key of this  announcements flag
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the flag id of this  announcements flag.
	 *
	 * @return the flag id of this  announcements flag
	 */
	public long getFlagId();

	/**
	 * Sets the flag id of this  announcements flag.
	 *
	 * @param flagId the flag id of this  announcements flag
	 */
	public void setFlagId(long flagId);

	/**
	 * Gets the user id of this  announcements flag.
	 *
	 * @return the user id of this  announcements flag
	 */
	public long getUserId();

	/**
	 * Sets the user id of this  announcements flag.
	 *
	 * @param userId the user id of this  announcements flag
	 */
	public void setUserId(long userId);

	/**
	 * Gets the user uuid of this  announcements flag.
	 *
	 * @return the user uuid of this  announcements flag
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this  announcements flag.
	 *
	 * @param userUuid the user uuid of this  announcements flag
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Gets the create date of this  announcements flag.
	 *
	 * @return the create date of this  announcements flag
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this  announcements flag.
	 *
	 * @param createDate the create date of this  announcements flag
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Gets the entry id of this  announcements flag.
	 *
	 * @return the entry id of this  announcements flag
	 */
	public long getEntryId();

	/**
	 * Sets the entry id of this  announcements flag.
	 *
	 * @param entryId the entry id of this  announcements flag
	 */
	public void setEntryId(long entryId);

	/**
	 * Gets the value of this  announcements flag.
	 *
	 * @return the value of this  announcements flag
	 */
	public int getValue();

	/**
	 * Sets the value of this  announcements flag.
	 *
	 * @param value the value of this  announcements flag
	 */
	public void setValue(int value);

	/**
	 * Gets a copy of this  announcements flag as an escaped model instance by wrapping it with an {@link com.liferay.portal.kernel.bean.AutoEscapeBeanHandler}.
	 *
	 * @return the escaped model instance
	 * @see com.liferay.portal.kernel.bean.AutoEscapeBeanHandler
	 */
	public AnnouncementsFlag toEscapedModel();

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

	public int compareTo(AnnouncementsFlag announcementsFlag);

	public int hashCode();

	public String toString();

	public String toXmlString();
}