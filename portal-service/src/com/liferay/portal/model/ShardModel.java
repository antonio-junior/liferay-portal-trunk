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
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * <p>
 * This interface is a model that represents the Shard table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Shard
 * @see       com.liferay.portal.model.impl.ShardImpl
 * @see       com.liferay.portal.model.impl.ShardModelImpl
 * @generated
 */
public interface ShardModel extends BaseModel<Shard> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getShardId();

	public void setShardId(long shardId);

	public String getClassName();

	public long getClassNameId();

	public void setClassNameId(long classNameId);

	public long getClassPK();

	public void setClassPK(long classPK);

	@AutoEscape
	public String getName();

	public void setName(String name);

	public Shard toEscapedModel();

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

	public int compareTo(Shard shard);

	public int hashCode();

	public String toString();

	public String toXmlString();
}