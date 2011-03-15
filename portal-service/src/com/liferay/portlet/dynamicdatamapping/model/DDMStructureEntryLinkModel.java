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

package com.liferay.portlet.dynamicdatamapping.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the DDMStructureEntryLink service. Represents a row in the &quot;DDMStructureEntryLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureEntryLinkModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureEntryLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructureEntryLink
 * @see com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureEntryLinkImpl
 * @see com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureEntryLinkModelImpl
 * @generated
 */
public interface DDMStructureEntryLinkModel extends BaseModel<DDMStructureEntryLink> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a d d m structure entry link model instance should use the {@link DDMStructureEntryLink} interface instead.
	 */

	/**
	 * Gets the primary key of this d d m structure entry link.
	 *
	 * @return the primary key of this d d m structure entry link
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this d d m structure entry link
	 *
	 * @param pk the primary key of this d d m structure entry link
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the structure entry link ID of this d d m structure entry link.
	 *
	 * @return the structure entry link ID of this d d m structure entry link
	 */
	public long getStructureEntryLinkId();

	/**
	 * Sets the structure entry link ID of this d d m structure entry link.
	 *
	 * @param structureEntryLinkId the structure entry link ID of this d d m structure entry link
	 */
	public void setStructureEntryLinkId(long structureEntryLinkId);

	/**
	 * Gets the structure ID of this d d m structure entry link.
	 *
	 * @return the structure ID of this d d m structure entry link
	 */
	public String getStructureId();

	/**
	 * Sets the structure ID of this d d m structure entry link.
	 *
	 * @param structureId the structure ID of this d d m structure entry link
	 */
	public void setStructureId(String structureId);

	/**
	 * Gets the class name of this d d m structure entry link.
	 *
	 * @return the class name of this d d m structure entry link
	 */
	@AutoEscape
	public String getClassName();

	/**
	 * Sets the class name of this d d m structure entry link.
	 *
	 * @param className the class name of this d d m structure entry link
	 */
	public void setClassName(String className);

	/**
	 * Gets the class p k of this d d m structure entry link.
	 *
	 * @return the class p k of this d d m structure entry link
	 */
	public long getClassPK();

	/**
	 * Sets the class p k of this d d m structure entry link.
	 *
	 * @param classPK the class p k of this d d m structure entry link
	 */
	public void setClassPK(long classPK);

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

	public int compareTo(DDMStructureEntryLink ddmStructureEntryLink);

	public int hashCode();

	public DDMStructureEntryLink toEscapedModel();

	public String toString();

	public String toXmlString();
}