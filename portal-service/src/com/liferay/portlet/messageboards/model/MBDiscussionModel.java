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

package com.liferay.portlet.messageboards.model;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the MBDiscussion service. Represents a row in the &quot;MBDiscussion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.messageboards.model.impl.MBDiscussionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. All helper methods and application logic should be put in {@link com.liferay.portlet.messageboards.model.impl.MBDiscussionImpl}.
 * </p>
 *
 * <p>
 * Never reference this interface directly. All methods that expect a  m b discussion model instance should use the {@link MBDiscussion} interface instead.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MBDiscussion
 * @see       com.liferay.portlet.messageboards.model.impl.MBDiscussionImpl
 * @see       com.liferay.portlet.messageboards.model.impl.MBDiscussionModelImpl
 * @generated
 */
public interface MBDiscussionModel extends BaseModel<MBDiscussion> {
	/**
	 * Gets the primary key of this  m b discussion.
	 *
	 * @return the primary key of this  m b discussion
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this  m b discussion
	 *
	 * @param pk the primary key of this  m b discussion
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the discussion id of this  m b discussion.
	 *
	 * @return the discussion id of this  m b discussion
	 */
	public long getDiscussionId();

	/**
	 * Sets the discussion id of this  m b discussion.
	 *
	 * @param discussionId the discussion id of this  m b discussion
	 */
	public void setDiscussionId(long discussionId);

	/**
	 * Gets the class name of the model instance this  m b discussion is associated with.
	 *
	 * @return the class name of the model instance this  m b discussion is associated with
	 */
	public String getClassName();

	/**
	 * Gets the class name id of this  m b discussion.
	 *
	 * @return the class name id of this  m b discussion
	 */
	public long getClassNameId();

	/**
	 * Sets the class name id of this  m b discussion.
	 *
	 * @param classNameId the class name id of this  m b discussion
	 */
	public void setClassNameId(long classNameId);

	/**
	 * Gets the class p k of this  m b discussion.
	 *
	 * @return the class p k of this  m b discussion
	 */
	public long getClassPK();

	/**
	 * Sets the class p k of this  m b discussion.
	 *
	 * @param classPK the class p k of this  m b discussion
	 */
	public void setClassPK(long classPK);

	/**
	 * Gets the thread id of this  m b discussion.
	 *
	 * @return the thread id of this  m b discussion
	 */
	public long getThreadId();

	/**
	 * Sets the thread id of this  m b discussion.
	 *
	 * @param threadId the thread id of this  m b discussion
	 */
	public void setThreadId(long threadId);

	/**
	 * Gets a copy of this  m b discussion as an escaped model instance by wrapping it with an {@link com.liferay.portal.kernel.bean.AutoEscapeBeanHandler}.
	 *
	 * @return the escaped model instance
	 * @see com.liferay.portal.kernel.bean.AutoEscapeBeanHandler
	 */
	public MBDiscussion toEscapedModel();

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

	public int compareTo(MBDiscussion mbDiscussion);

	public int hashCode();

	public String toString();

	public String toXmlString();
}