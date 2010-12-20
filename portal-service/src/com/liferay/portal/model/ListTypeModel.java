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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the ListType service. Represents a row in the &quot;ListType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.ListTypeModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.ListTypeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ListType
 * @see com.liferay.portal.model.impl.ListTypeImpl
 * @see com.liferay.portal.model.impl.ListTypeModelImpl
 * @generated
 */
public interface ListTypeModel extends BaseModel<ListType> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a list type model instance should use the {@link ListType} interface instead.
	 */

	/**
	 * Gets the primary key of this list type.
	 *
	 * @return the primary key of this list type
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this list type
	 *
	 * @param pk the primary key of this list type
	 */
	public void setPrimaryKey(int pk);

	/**
	 * Gets the list type ID of this list type.
	 *
	 * @return the list type ID of this list type
	 */
	public int getListTypeId();

	/**
	 * Sets the list type ID of this list type.
	 *
	 * @param listTypeId the list type ID of this list type
	 */
	public void setListTypeId(int listTypeId);

	/**
	 * Gets the name of this list type.
	 *
	 * @return the name of this list type
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this list type.
	 *
	 * @param name the name of this list type
	 */
	public void setName(String name);

	/**
	 * Gets the type of this list type.
	 *
	 * @return the type of this list type
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this list type.
	 *
	 * @param type the type of this list type
	 */
	public void setType(String type);

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

	public int compareTo(ListType listType);

	public int hashCode();

	public ListType toEscapedModel();

	public String toString();

	public String toXmlString();
}