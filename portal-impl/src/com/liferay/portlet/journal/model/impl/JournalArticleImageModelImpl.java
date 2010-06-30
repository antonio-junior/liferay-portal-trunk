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

package com.liferay.portlet.journal.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;
import com.liferay.portlet.journal.model.JournalArticleImage;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

/**
 * <a href="JournalArticleImageModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the JournalArticleImage table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JournalArticleImageImpl
 * @see       com.liferay.portlet.journal.model.JournalArticleImage
 * @see       com.liferay.portlet.journal.model.JournalArticleImageModel
 * @generated
 */
public class JournalArticleImageModelImpl extends BaseModelImpl<JournalArticleImage> {
	public static final String TABLE_NAME = "JournalArticleImage";
	public static final Object[][] TABLE_COLUMNS = {
			{ "articleImageId", new Integer(Types.BIGINT) },
			{ "groupId", new Integer(Types.BIGINT) },
			{ "articleId", new Integer(Types.VARCHAR) },
			{ "version", new Integer(Types.DOUBLE) },
			{ "elInstanceId", new Integer(Types.VARCHAR) },
			{ "elName", new Integer(Types.VARCHAR) },
			{ "languageId", new Integer(Types.VARCHAR) },
			{ "tempImage", new Integer(Types.BOOLEAN) }
		};
	public static final String TABLE_SQL_CREATE = "create table JournalArticleImage (articleImageId LONG not null primary key,groupId LONG,articleId VARCHAR(75) null,version DOUBLE,elInstanceId VARCHAR(75) null,elName VARCHAR(75) null,languageId VARCHAR(75) null,tempImage BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table JournalArticleImage";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portlet.journal.model.JournalArticleImage"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portlet.journal.model.JournalArticleImage"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portlet.journal.model.JournalArticleImage"));

	public JournalArticleImageModelImpl() {
	}

	public long getPrimaryKey() {
		return _articleImageId;
	}

	public void setPrimaryKey(long pk) {
		setArticleImageId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_articleImageId);
	}

	public long getArticleImageId() {
		return _articleImageId;
	}

	public void setArticleImageId(long articleImageId) {
		_articleImageId = articleImageId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = groupId;
		}
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	public String getArticleId() {
		if (_articleId == null) {
			return StringPool.BLANK;
		}
		else {
			return _articleId;
		}
	}

	public void setArticleId(String articleId) {
		_articleId = articleId;

		if (_originalArticleId == null) {
			_originalArticleId = articleId;
		}
	}

	public String getOriginalArticleId() {
		return GetterUtil.getString(_originalArticleId);
	}

	public double getVersion() {
		return _version;
	}

	public void setVersion(double version) {
		_version = version;

		if (!_setOriginalVersion) {
			_setOriginalVersion = true;

			_originalVersion = version;
		}
	}

	public double getOriginalVersion() {
		return _originalVersion;
	}

	public String getElInstanceId() {
		if (_elInstanceId == null) {
			return StringPool.BLANK;
		}
		else {
			return _elInstanceId;
		}
	}

	public void setElInstanceId(String elInstanceId) {
		_elInstanceId = elInstanceId;

		if (_originalElInstanceId == null) {
			_originalElInstanceId = elInstanceId;
		}
	}

	public String getOriginalElInstanceId() {
		return GetterUtil.getString(_originalElInstanceId);
	}

	public String getElName() {
		if (_elName == null) {
			return StringPool.BLANK;
		}
		else {
			return _elName;
		}
	}

	public void setElName(String elName) {
		_elName = elName;

		if (_originalElName == null) {
			_originalElName = elName;
		}
	}

	public String getOriginalElName() {
		return GetterUtil.getString(_originalElName);
	}

	public String getLanguageId() {
		if (_languageId == null) {
			return StringPool.BLANK;
		}
		else {
			return _languageId;
		}
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;

		if (_originalLanguageId == null) {
			_originalLanguageId = languageId;
		}
	}

	public String getOriginalLanguageId() {
		return GetterUtil.getString(_originalLanguageId);
	}

	public boolean getTempImage() {
		return _tempImage;
	}

	public boolean isTempImage() {
		return _tempImage;
	}

	public void setTempImage(boolean tempImage) {
		_tempImage = tempImage;
	}

	public JournalArticleImage toEscapedModel() {
		if (isEscapedModel()) {
			return (JournalArticleImage)this;
		}
		else {
			return (JournalArticleImage)Proxy.newProxyInstance(JournalArticleImage.class.getClassLoader(),
				new Class[] { JournalArticleImage.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(0,
					JournalArticleImage.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		JournalArticleImageImpl clone = new JournalArticleImageImpl();

		clone.setArticleImageId(getArticleImageId());
		clone.setGroupId(getGroupId());
		clone.setArticleId(getArticleId());
		clone.setVersion(getVersion());
		clone.setElInstanceId(getElInstanceId());
		clone.setElName(getElName());
		clone.setLanguageId(getLanguageId());
		clone.setTempImage(getTempImage());

		return clone;
	}

	public int compareTo(JournalArticleImage journalArticleImage) {
		long pk = journalArticleImage.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		JournalArticleImage journalArticleImage = null;

		try {
			journalArticleImage = (JournalArticleImage)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = journalArticleImage.getPrimaryKey();

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
		StringBundler sb = new StringBundler(17);

		sb.append("{articleImageId=");
		sb.append(getArticleImageId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", articleId=");
		sb.append(getArticleId());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append(", elInstanceId=");
		sb.append(getElInstanceId());
		sb.append(", elName=");
		sb.append(getElName());
		sb.append(", languageId=");
		sb.append(getLanguageId());
		sb.append(", tempImage=");
		sb.append(getTempImage());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portlet.journal.model.JournalArticleImage");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>articleImageId</column-name><column-value><![CDATA[");
		sb.append(getArticleImageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>articleId</column-name><column-value><![CDATA[");
		sb.append(getArticleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>elInstanceId</column-name><column-value><![CDATA[");
		sb.append(getElInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>elName</column-name><column-value><![CDATA[");
		sb.append(getElName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>languageId</column-name><column-value><![CDATA[");
		sb.append(getLanguageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tempImage</column-name><column-value><![CDATA[");
		sb.append(getTempImage());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _articleImageId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private String _articleId;
	private String _originalArticleId;
	private double _version;
	private double _originalVersion;
	private boolean _setOriginalVersion;
	private String _elInstanceId;
	private String _originalElInstanceId;
	private String _elName;
	private String _originalElName;
	private String _languageId;
	private String _originalLanguageId;
	private boolean _tempImage;
	private transient ExpandoBridge _expandoBridge;
}