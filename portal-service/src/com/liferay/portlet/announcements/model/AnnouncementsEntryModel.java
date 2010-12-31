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

package com.liferay.portlet.announcements.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the AnnouncementsEntry service. Represents a row in the &quot;AnnouncementsEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.announcements.model.impl.AnnouncementsEntryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.announcements.model.impl.AnnouncementsEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsEntry
 * @see com.liferay.portlet.announcements.model.impl.AnnouncementsEntryImpl
 * @see com.liferay.portlet.announcements.model.impl.AnnouncementsEntryModelImpl
 * @generated
 */
public interface AnnouncementsEntryModel extends BaseModel<AnnouncementsEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a announcements entry model instance should use the {@link AnnouncementsEntry} interface instead.
	 */

	/**
	 * Gets the primary key of this announcements entry.
	 *
	 * @return the primary key of this announcements entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this announcements entry
	 *
	 * @param pk the primary key of this announcements entry
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the uuid of this announcements entry.
	 *
	 * @return the uuid of this announcements entry
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this announcements entry.
	 *
	 * @param uuid the uuid of this announcements entry
	 */
	public void setUuid(String uuid);

	/**
	 * Gets the entry ID of this announcements entry.
	 *
	 * @return the entry ID of this announcements entry
	 */
	public long getEntryId();

	/**
	 * Sets the entry ID of this announcements entry.
	 *
	 * @param entryId the entry ID of this announcements entry
	 */
	public void setEntryId(long entryId);

	/**
	 * Gets the company ID of this announcements entry.
	 *
	 * @return the company ID of this announcements entry
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this announcements entry.
	 *
	 * @param companyId the company ID of this announcements entry
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the user ID of this announcements entry.
	 *
	 * @return the user ID of this announcements entry
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this announcements entry.
	 *
	 * @param userId the user ID of this announcements entry
	 */
	public void setUserId(long userId);

	/**
	 * Gets the user uuid of this announcements entry.
	 *
	 * @return the user uuid of this announcements entry
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this announcements entry.
	 *
	 * @param userUuid the user uuid of this announcements entry
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Gets the user name of this announcements entry.
	 *
	 * @return the user name of this announcements entry
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this announcements entry.
	 *
	 * @param userName the user name of this announcements entry
	 */
	public void setUserName(String userName);

	/**
	 * Gets the create date of this announcements entry.
	 *
	 * @return the create date of this announcements entry
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this announcements entry.
	 *
	 * @param createDate the create date of this announcements entry
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Gets the modified date of this announcements entry.
	 *
	 * @return the modified date of this announcements entry
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this announcements entry.
	 *
	 * @param modifiedDate the modified date of this announcements entry
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Gets the class name of the model instance this announcements entry is polymorphically associated with.
	 *
	 * @return the class name of the model instance this announcements entry is polymorphically associated with
	 */
	public String getClassName();

	/**
	 * Gets the class name ID of this announcements entry.
	 *
	 * @return the class name ID of this announcements entry
	 */
	public long getClassNameId();

	/**
	 * Sets the class name ID of this announcements entry.
	 *
	 * @param classNameId the class name ID of this announcements entry
	 */
	public void setClassNameId(long classNameId);

	/**
	 * Gets the class p k of this announcements entry.
	 *
	 * @return the class p k of this announcements entry
	 */
	public long getClassPK();

	/**
	 * Sets the class p k of this announcements entry.
	 *
	 * @param classPK the class p k of this announcements entry
	 */
	public void setClassPK(long classPK);

	/**
	 * Gets the title of this announcements entry.
	 *
	 * @return the title of this announcements entry
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this announcements entry.
	 *
	 * @param title the title of this announcements entry
	 */
	public void setTitle(String title);

	/**
	 * Gets the content of this announcements entry.
	 *
	 * @return the content of this announcements entry
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this announcements entry.
	 *
	 * @param content the content of this announcements entry
	 */
	public void setContent(String content);

	/**
	 * Gets the url of this announcements entry.
	 *
	 * @return the url of this announcements entry
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this announcements entry.
	 *
	 * @param url the url of this announcements entry
	 */
	public void setUrl(String url);

	/**
	 * Gets the type of this announcements entry.
	 *
	 * @return the type of this announcements entry
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this announcements entry.
	 *
	 * @param type the type of this announcements entry
	 */
	public void setType(String type);

	/**
	 * Gets the display date of this announcements entry.
	 *
	 * @return the display date of this announcements entry
	 */
	public Date getDisplayDate();

	/**
	 * Sets the display date of this announcements entry.
	 *
	 * @param displayDate the display date of this announcements entry
	 */
	public void setDisplayDate(Date displayDate);

	/**
	 * Gets the expiration date of this announcements entry.
	 *
	 * @return the expiration date of this announcements entry
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this announcements entry.
	 *
	 * @param expirationDate the expiration date of this announcements entry
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Gets the priority of this announcements entry.
	 *
	 * @return the priority of this announcements entry
	 */
	public int getPriority();

	/**
	 * Sets the priority of this announcements entry.
	 *
	 * @param priority the priority of this announcements entry
	 */
	public void setPriority(int priority);

	/**
	 * Gets the alert of this announcements entry.
	 *
	 * @return the alert of this announcements entry
	 */
	public boolean getAlert();

	/**
	 * Determines if this announcements entry is alert.
	 *
	 * @return <code>true</code> if this announcements entry is alert; <code>false</code> otherwise
	 */
	public boolean isAlert();

	/**
	 * Sets whether this announcements entry is alert.
	 *
	 * @param alert the alert of this announcements entry
	 */
	public void setAlert(boolean alert);

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

	public int compareTo(AnnouncementsEntry announcementsEntry);

	public int hashCode();

	public AnnouncementsEntry toEscapedModel();

	public String toString();

	public String toXmlString();
}