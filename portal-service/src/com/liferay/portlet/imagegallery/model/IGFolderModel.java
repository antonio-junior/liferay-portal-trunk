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

package com.liferay.portlet.imagegallery.model;

import com.liferay.portal.kernel.annotation.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the IGFolder service. Represents a row in the &quot;IGFolder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.imagegallery.model.impl.IGFolderModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.imagegallery.model.impl.IGFolderImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see IGFolder
 * @see com.liferay.portlet.imagegallery.model.impl.IGFolderImpl
 * @see com.liferay.portlet.imagegallery.model.impl.IGFolderModelImpl
 * @generated
 */
public interface IGFolderModel extends BaseModel<IGFolder> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a i g folder model instance should use the {@link IGFolder} interface instead.
	 */

	/**
	 * Gets the primary key of this i g folder.
	 *
	 * @return the primary key of this i g folder
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this i g folder
	 *
	 * @param pk the primary key of this i g folder
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the uuid of this i g folder.
	 *
	 * @return the uuid of this i g folder
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this i g folder.
	 *
	 * @param uuid the uuid of this i g folder
	 */
	public void setUuid(String uuid);

	/**
	 * Gets the folder id of this i g folder.
	 *
	 * @return the folder id of this i g folder
	 */
	public long getFolderId();

	/**
	 * Sets the folder id of this i g folder.
	 *
	 * @param folderId the folder id of this i g folder
	 */
	public void setFolderId(long folderId);

	/**
	 * Gets the group id of this i g folder.
	 *
	 * @return the group id of this i g folder
	 */
	public long getGroupId();

	/**
	 * Sets the group id of this i g folder.
	 *
	 * @param groupId the group id of this i g folder
	 */
	public void setGroupId(long groupId);

	/**
	 * Gets the company id of this i g folder.
	 *
	 * @return the company id of this i g folder
	 */
	public long getCompanyId();

	/**
	 * Sets the company id of this i g folder.
	 *
	 * @param companyId the company id of this i g folder
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the user id of this i g folder.
	 *
	 * @return the user id of this i g folder
	 */
	public long getUserId();

	/**
	 * Sets the user id of this i g folder.
	 *
	 * @param userId the user id of this i g folder
	 */
	public void setUserId(long userId);

	/**
	 * Gets the user uuid of this i g folder.
	 *
	 * @return the user uuid of this i g folder
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this i g folder.
	 *
	 * @param userUuid the user uuid of this i g folder
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Gets the create date of this i g folder.
	 *
	 * @return the create date of this i g folder
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this i g folder.
	 *
	 * @param createDate the create date of this i g folder
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Gets the modified date of this i g folder.
	 *
	 * @return the modified date of this i g folder
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this i g folder.
	 *
	 * @param modifiedDate the modified date of this i g folder
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Gets the parent folder id of this i g folder.
	 *
	 * @return the parent folder id of this i g folder
	 */
	public long getParentFolderId();

	/**
	 * Sets the parent folder id of this i g folder.
	 *
	 * @param parentFolderId the parent folder id of this i g folder
	 */
	public void setParentFolderId(long parentFolderId);

	/**
	 * Gets the name of this i g folder.
	 *
	 * @return the name of this i g folder
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this i g folder.
	 *
	 * @param name the name of this i g folder
	 */
	public void setName(String name);

	/**
	 * Gets the description of this i g folder.
	 *
	 * @return the description of this i g folder
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this i g folder.
	 *
	 * @param description the description of this i g folder
	 */
	public void setDescription(String description);

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

	public int compareTo(IGFolder igFolder);

	public int hashCode();

	public IGFolder toEscapedModel();

	public String toString();

	public String toXmlString();
}