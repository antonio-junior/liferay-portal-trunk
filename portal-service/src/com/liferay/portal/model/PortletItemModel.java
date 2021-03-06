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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the PortletItem service. Represents a row in the &quot;PortletItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.PortletItemModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.PortletItemImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortletItem
 * @see com.liferay.portal.model.impl.PortletItemImpl
 * @see com.liferay.portal.model.impl.PortletItemModelImpl
 * @generated
 */
public interface PortletItemModel extends BaseModel<PortletItem>, GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a portlet item model instance should use the {@link PortletItem} interface instead.
	 */

	/**
	 * Gets the primary key of this portlet item.
	 *
	 * @return the primary key of this portlet item
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this portlet item
	 *
	 * @param pk the primary key of this portlet item
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the portlet item ID of this portlet item.
	 *
	 * @return the portlet item ID of this portlet item
	 */
	public long getPortletItemId();

	/**
	 * Sets the portlet item ID of this portlet item.
	 *
	 * @param portletItemId the portlet item ID of this portlet item
	 */
	public void setPortletItemId(long portletItemId);

	/**
	 * Gets the group ID of this portlet item.
	 *
	 * @return the group ID of this portlet item
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this portlet item.
	 *
	 * @param groupId the group ID of this portlet item
	 */
	public void setGroupId(long groupId);

	/**
	 * Gets the company ID of this portlet item.
	 *
	 * @return the company ID of this portlet item
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this portlet item.
	 *
	 * @param companyId the company ID of this portlet item
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the user ID of this portlet item.
	 *
	 * @return the user ID of this portlet item
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this portlet item.
	 *
	 * @param userId the user ID of this portlet item
	 */
	public void setUserId(long userId);

	/**
	 * Gets the user uuid of this portlet item.
	 *
	 * @return the user uuid of this portlet item
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this portlet item.
	 *
	 * @param userUuid the user uuid of this portlet item
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Gets the user name of this portlet item.
	 *
	 * @return the user name of this portlet item
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this portlet item.
	 *
	 * @param userName the user name of this portlet item
	 */
	public void setUserName(String userName);

	/**
	 * Gets the create date of this portlet item.
	 *
	 * @return the create date of this portlet item
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this portlet item.
	 *
	 * @param createDate the create date of this portlet item
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Gets the modified date of this portlet item.
	 *
	 * @return the modified date of this portlet item
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this portlet item.
	 *
	 * @param modifiedDate the modified date of this portlet item
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Gets the name of this portlet item.
	 *
	 * @return the name of this portlet item
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this portlet item.
	 *
	 * @param name the name of this portlet item
	 */
	public void setName(String name);

	/**
	 * Gets the portlet ID of this portlet item.
	 *
	 * @return the portlet ID of this portlet item
	 */
	@AutoEscape
	public String getPortletId();

	/**
	 * Sets the portlet ID of this portlet item.
	 *
	 * @param portletId the portlet ID of this portlet item
	 */
	public void setPortletId(String portletId);

	/**
	 * Gets the class name of the model instance this portlet item is polymorphically associated with.
	 *
	 * @return the class name of the model instance this portlet item is polymorphically associated with
	 */
	public String getClassName();

	/**
	 * Gets the class name ID of this portlet item.
	 *
	 * @return the class name ID of this portlet item
	 */
	public long getClassNameId();

	/**
	 * Sets the class name ID of this portlet item.
	 *
	 * @param classNameId the class name ID of this portlet item
	 */
	public void setClassNameId(long classNameId);

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

	public int compareTo(PortletItem portletItem);

	public int hashCode();

	public PortletItem toEscapedModel();

	public String toString();

	public String toXmlString();
}