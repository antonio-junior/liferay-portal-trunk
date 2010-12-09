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
 * The base model interface for the VirtualHost service. Represents a row in the &quot;VirtualHost&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.VirtualHostModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.VirtualHostImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VirtualHost
 * @see com.liferay.portal.model.impl.VirtualHostImpl
 * @see com.liferay.portal.model.impl.VirtualHostModelImpl
 * @generated
 */
public interface VirtualHostModel extends BaseModel<VirtualHost> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a virtual host model instance should use the {@link VirtualHost} interface instead.
	 */

	/**
	 * Gets the primary key of this virtual host.
	 *
	 * @return the primary key of this virtual host
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this virtual host
	 *
	 * @param pk the primary key of this virtual host
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the virtual host id of this virtual host.
	 *
	 * @return the virtual host id of this virtual host
	 */
	public long getVirtualHostId();

	/**
	 * Sets the virtual host id of this virtual host.
	 *
	 * @param virtualHostId the virtual host id of this virtual host
	 */
	public void setVirtualHostId(long virtualHostId);

	/**
	 * Gets the company id of this virtual host.
	 *
	 * @return the company id of this virtual host
	 */
	public long getCompanyId();

	/**
	 * Sets the company id of this virtual host.
	 *
	 * @param companyId the company id of this virtual host
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the layout set id of this virtual host.
	 *
	 * @return the layout set id of this virtual host
	 */
	public long getLayoutSetId();

	/**
	 * Sets the layout set id of this virtual host.
	 *
	 * @param layoutSetId the layout set id of this virtual host
	 */
	public void setLayoutSetId(long layoutSetId);

	/**
	 * Gets the hostname of this virtual host.
	 *
	 * @return the hostname of this virtual host
	 */
	@AutoEscape
	public String getHostname();

	/**
	 * Sets the hostname of this virtual host.
	 *
	 * @param hostname the hostname of this virtual host
	 */
	public void setHostname(String hostname);

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

	public int compareTo(VirtualHost virtualHost);

	public int hashCode();

	public VirtualHost toEscapedModel();

	public String toString();

	public String toXmlString();
}