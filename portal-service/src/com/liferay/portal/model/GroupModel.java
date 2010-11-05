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

package com.liferay.portal.model;

import com.liferay.portal.kernel.annotation.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the Group service. Represents a row in the &quot;Group_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.GroupModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.GroupImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Group
 * @see com.liferay.portal.model.impl.GroupImpl
 * @see com.liferay.portal.model.impl.GroupModelImpl
 * @generated
 */
public interface GroupModel extends BaseModel<Group> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a group model instance should use the {@link Group} interface instead.
	 */

	/**
	 * Gets the primary key of this group.
	 *
	 * @return the primary key of this group
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this group
	 *
	 * @param pk the primary key of this group
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the group id of this group.
	 *
	 * @return the group id of this group
	 */
	public long getGroupId();

	/**
	 * Sets the group id of this group.
	 *
	 * @param groupId the group id of this group
	 */
	public void setGroupId(long groupId);

	/**
	 * Gets the company id of this group.
	 *
	 * @return the company id of this group
	 */
	public long getCompanyId();

	/**
	 * Sets the company id of this group.
	 *
	 * @param companyId the company id of this group
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the creator user id of this group.
	 *
	 * @return the creator user id of this group
	 */
	public long getCreatorUserId();

	/**
	 * Sets the creator user id of this group.
	 *
	 * @param creatorUserId the creator user id of this group
	 */
	public void setCreatorUserId(long creatorUserId);

	/**
	 * Gets the creator user uuid of this group.
	 *
	 * @return the creator user uuid of this group
	 * @throws SystemException if a system exception occurred
	 */
	public String getCreatorUserUuid() throws SystemException;

	/**
	 * Sets the creator user uuid of this group.
	 *
	 * @param creatorUserUuid the creator user uuid of this group
	 */
	public void setCreatorUserUuid(String creatorUserUuid);

	/**
	 * Gets the class name of the model instance this group is polymorphically associated with.
	 *
	 * @return the class name of the model instance this group is polymorphically associated with
	 */
	public String getClassName();

	/**
	 * Gets the class name id of this group.
	 *
	 * @return the class name id of this group
	 */
	public long getClassNameId();

	/**
	 * Sets the class name id of this group.
	 *
	 * @param classNameId the class name id of this group
	 */
	public void setClassNameId(long classNameId);

	/**
	 * Gets the class p k of this group.
	 *
	 * @return the class p k of this group
	 */
	public long getClassPK();

	/**
	 * Sets the class p k of this group.
	 *
	 * @param classPK the class p k of this group
	 */
	public void setClassPK(long classPK);

	/**
	 * Gets the parent group id of this group.
	 *
	 * @return the parent group id of this group
	 */
	public long getParentGroupId();

	/**
	 * Sets the parent group id of this group.
	 *
	 * @param parentGroupId the parent group id of this group
	 */
	public void setParentGroupId(long parentGroupId);

	/**
	 * Gets the live group id of this group.
	 *
	 * @return the live group id of this group
	 */
	public long getLiveGroupId();

	/**
	 * Sets the live group id of this group.
	 *
	 * @param liveGroupId the live group id of this group
	 */
	public void setLiveGroupId(long liveGroupId);

	/**
	 * Gets the name of this group.
	 *
	 * @return the name of this group
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this group.
	 *
	 * @param name the name of this group
	 */
	public void setName(String name);

	/**
	 * Gets the description of this group.
	 *
	 * @return the description of this group
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this group.
	 *
	 * @param description the description of this group
	 */
	public void setDescription(String description);

	/**
	 * Gets the type of this group.
	 *
	 * @return the type of this group
	 */
	public int getType();

	/**
	 * Sets the type of this group.
	 *
	 * @param type the type of this group
	 */
	public void setType(int type);

	/**
	 * Gets the type settings of this group.
	 *
	 * @return the type settings of this group
	 */
	@AutoEscape
	public String getTypeSettings();

	/**
	 * Sets the type settings of this group.
	 *
	 * @param typeSettings the type settings of this group
	 */
	public void setTypeSettings(String typeSettings);

	/**
	 * Gets the friendly u r l of this group.
	 *
	 * @return the friendly u r l of this group
	 */
	@AutoEscape
	public String getFriendlyURL();

	/**
	 * Sets the friendly u r l of this group.
	 *
	 * @param friendlyURL the friendly u r l of this group
	 */
	public void setFriendlyURL(String friendlyURL);

	/**
	 * Gets the active of this group.
	 *
	 * @return the active of this group
	 */
	public boolean getActive();

	/**
	 * Determines if this group is active.
	 *
	 * @return <code>true</code> if this group is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this group is active.
	 *
	 * @param active the active of this group
	 */
	public void setActive(boolean active);

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

	public int compareTo(Group group);

	public int hashCode();

	public Group toEscapedModel();

	public String toString();

	public String toXmlString();
}