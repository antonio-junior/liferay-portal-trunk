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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.MembershipRequestModel;
import com.liferay.portal.model.MembershipRequestSoap;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The base model implementation for the MembershipRequest service. Represents a row in the &quot;MembershipRequest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portal.model.MembershipRequestModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MembershipRequestImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MembershipRequestImpl
 * @see com.liferay.portal.model.MembershipRequest
 * @see com.liferay.portal.model.MembershipRequestModel
 * @generated
 */
public class MembershipRequestModelImpl extends BaseModelImpl<MembershipRequest>
	implements MembershipRequestModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a membership request model instance should use the {@link com.liferay.portal.model.MembershipRequest} interface instead.
	 */
	public static final String TABLE_NAME = "MembershipRequest";
	public static final Object[][] TABLE_COLUMNS = {
			{ "membershipRequestId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "comments", Types.VARCHAR },
			{ "replyComments", Types.VARCHAR },
			{ "replyDate", Types.TIMESTAMP },
			{ "replierUserId", Types.BIGINT },
			{ "statusId", Types.INTEGER }
		};
	public static final String TABLE_SQL_CREATE = "create table MembershipRequest (membershipRequestId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,createDate DATE null,comments STRING null,replyComments STRING null,replyDate DATE null,replierUserId LONG,statusId INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table MembershipRequest";
	public static final String ORDER_BY_JPQL = " ORDER BY membershipRequest.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY MembershipRequest.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.MembershipRequest"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.MembershipRequest"),
			true);

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static MembershipRequest toModel(MembershipRequestSoap soapModel) {
		MembershipRequest model = new MembershipRequestImpl();

		model.setMembershipRequestId(soapModel.getMembershipRequestId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setComments(soapModel.getComments());
		model.setReplyComments(soapModel.getReplyComments());
		model.setReplyDate(soapModel.getReplyDate());
		model.setReplierUserId(soapModel.getReplierUserId());
		model.setStatusId(soapModel.getStatusId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<MembershipRequest> toModels(
		MembershipRequestSoap[] soapModels) {
		List<MembershipRequest> models = new ArrayList<MembershipRequest>(soapModels.length);

		for (MembershipRequestSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.MembershipRequest"));

	public MembershipRequestModelImpl() {
	}

	public long getPrimaryKey() {
		return _membershipRequestId;
	}

	public void setPrimaryKey(long pk) {
		setMembershipRequestId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_membershipRequestId);
	}

	public long getMembershipRequestId() {
		return _membershipRequestId;
	}

	public void setMembershipRequestId(long membershipRequestId) {
		_membershipRequestId = membershipRequestId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getComments() {
		if (_comments == null) {
			return StringPool.BLANK;
		}
		else {
			return _comments;
		}
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public String getReplyComments() {
		if (_replyComments == null) {
			return StringPool.BLANK;
		}
		else {
			return _replyComments;
		}
	}

	public void setReplyComments(String replyComments) {
		_replyComments = replyComments;
	}

	public Date getReplyDate() {
		return _replyDate;
	}

	public void setReplyDate(Date replyDate) {
		_replyDate = replyDate;
	}

	public long getReplierUserId() {
		return _replierUserId;
	}

	public void setReplierUserId(long replierUserId) {
		_replierUserId = replierUserId;
	}

	public String getReplierUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getReplierUserId(), "uuid",
			_replierUserUuid);
	}

	public void setReplierUserUuid(String replierUserUuid) {
		_replierUserUuid = replierUserUuid;
	}

	public int getStatusId() {
		return _statusId;
	}

	public void setStatusId(int statusId) {
		_statusId = statusId;
	}

	public MembershipRequest toEscapedModel() {
		if (isEscapedModel()) {
			return (MembershipRequest)this;
		}
		else {
			return (MembershipRequest)Proxy.newProxyInstance(MembershipRequest.class.getClassLoader(),
				new Class[] { MembershipRequest.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					MembershipRequest.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		MembershipRequestImpl membershipRequestImpl = new MembershipRequestImpl();

		membershipRequestImpl.setMembershipRequestId(getMembershipRequestId());

		membershipRequestImpl.setGroupId(getGroupId());

		membershipRequestImpl.setCompanyId(getCompanyId());

		membershipRequestImpl.setUserId(getUserId());

		membershipRequestImpl.setCreateDate(getCreateDate());

		membershipRequestImpl.setComments(getComments());

		membershipRequestImpl.setReplyComments(getReplyComments());

		membershipRequestImpl.setReplyDate(getReplyDate());

		membershipRequestImpl.setReplierUserId(getReplierUserId());

		membershipRequestImpl.setStatusId(getStatusId());

		return membershipRequestImpl;
	}

	public int compareTo(MembershipRequest membershipRequest) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				membershipRequest.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		MembershipRequest membershipRequest = null;

		try {
			membershipRequest = (MembershipRequest)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = membershipRequest.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{membershipRequestId=");
		sb.append(getMembershipRequestId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", comments=");
		sb.append(getComments());
		sb.append(", replyComments=");
		sb.append(getReplyComments());
		sb.append(", replyDate=");
		sb.append(getReplyDate());
		sb.append(", replierUserId=");
		sb.append(getReplierUserId());
		sb.append(", statusId=");
		sb.append(getStatusId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.MembershipRequest");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>membershipRequestId</column-name><column-value><![CDATA[");
		sb.append(getMembershipRequestId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comments</column-name><column-value><![CDATA[");
		sb.append(getComments());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>replyComments</column-name><column-value><![CDATA[");
		sb.append(getReplyComments());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>replyDate</column-name><column-value><![CDATA[");
		sb.append(getReplyDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>replierUserId</column-name><column-value><![CDATA[");
		sb.append(getReplierUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusId</column-name><column-value><![CDATA[");
		sb.append(getStatusId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _membershipRequestId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private String _comments;
	private String _replyComments;
	private Date _replyDate;
	private long _replierUserId;
	private String _replierUserUuid;
	private int _statusId;
	private transient ExpandoBridge _expandoBridge;
}