/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.model;

import com.liferay.portal.SystemException;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * <a href="MembershipRequestModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the MembershipRequest table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MembershipRequest
 * @see       com.liferay.portal.model.impl.MembershipRequestImpl
 * @see       com.liferay.portal.model.impl.MembershipRequestModelImpl
 * @generated
 */
public interface MembershipRequestModel extends BaseModel<MembershipRequest> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getMembershipRequestId();

	public void setMembershipRequestId(long membershipRequestId);

	public long getCompanyId();

	public void setCompanyId(long companyId);

	public long getUserId();

	public void setUserId(long userId);

	public String getUserUuid() throws SystemException;

	public void setUserUuid(String userUuid);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public long getGroupId();

	public void setGroupId(long groupId);

	public String getComments();

	public void setComments(String comments);

	public String getReplyComments();

	public void setReplyComments(String replyComments);

	public Date getReplyDate();

	public void setReplyDate(Date replyDate);

	public long getReplierUserId();

	public void setReplierUserId(long replierUserId);

	public String getReplierUserUuid() throws SystemException;

	public void setReplierUserUuid(String replierUserUuid);

	public int getStatusId();

	public void setStatusId(int statusId);

	public MembershipRequest toEscapedModel();

	public boolean isNew();

	public boolean setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(MembershipRequest membershipRequest);

	public int hashCode();

	public String toString();

	public String toXmlString();
}