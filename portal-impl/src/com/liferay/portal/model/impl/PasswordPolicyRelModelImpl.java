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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.PasswordPolicyRel;
import com.liferay.portal.model.PasswordPolicyRelModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

/**
 * The base model implementation for the PasswordPolicyRel service. Represents a row in the &quot;PasswordPolicyRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portal.model.PasswordPolicyRelModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PasswordPolicyRelImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PasswordPolicyRelImpl
 * @see com.liferay.portal.model.PasswordPolicyRel
 * @see com.liferay.portal.model.PasswordPolicyRelModel
 * @generated
 */
public class PasswordPolicyRelModelImpl extends BaseModelImpl<PasswordPolicyRel>
	implements PasswordPolicyRelModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a password policy rel model instance should use the {@link com.liferay.portal.model.PasswordPolicyRel} interface instead.
	 */
	public static final String TABLE_NAME = "PasswordPolicyRel";
	public static final Object[][] TABLE_COLUMNS = {
			{ "passwordPolicyRelId", Types.BIGINT },
			{ "passwordPolicyId", Types.BIGINT },
			{ "classNameId", Types.BIGINT },
			{ "classPK", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table PasswordPolicyRel (passwordPolicyRelId LONG not null primary key,passwordPolicyId LONG,classNameId LONG,classPK LONG)";
	public static final String TABLE_SQL_DROP = "drop table PasswordPolicyRel";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.PasswordPolicyRel"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.PasswordPolicyRel"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.PasswordPolicyRel"));

	public PasswordPolicyRelModelImpl() {
	}

	public long getPrimaryKey() {
		return _passwordPolicyRelId;
	}

	public void setPrimaryKey(long pk) {
		setPasswordPolicyRelId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_passwordPolicyRelId);
	}

	public long getPasswordPolicyRelId() {
		return _passwordPolicyRelId;
	}

	public void setPasswordPolicyRelId(long passwordPolicyRelId) {
		_passwordPolicyRelId = passwordPolicyRelId;
	}

	public long getPasswordPolicyId() {
		return _passwordPolicyId;
	}

	public void setPasswordPolicyId(long passwordPolicyId) {
		if (!_setOriginalPasswordPolicyId) {
			_setOriginalPasswordPolicyId = true;

			_originalPasswordPolicyId = _passwordPolicyId;
		}

		_passwordPolicyId = passwordPolicyId;
	}

	public long getOriginalPasswordPolicyId() {
		return _originalPasswordPolicyId;
	}

	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	public PasswordPolicyRel toEscapedModel() {
		if (isEscapedModel()) {
			return (PasswordPolicyRel)this;
		}
		else {
			return (PasswordPolicyRel)Proxy.newProxyInstance(PasswordPolicyRel.class.getClassLoader(),
				new Class[] { PasswordPolicyRel.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(0,
					PasswordPolicyRel.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		PasswordPolicyRelImpl passwordPolicyRelImpl = new PasswordPolicyRelImpl();

		PasswordPolicyRelModelImpl passwordPolicyRelModelImpl = passwordPolicyRelImpl;

		passwordPolicyRelImpl.setPasswordPolicyRelId(getPasswordPolicyRelId());

		passwordPolicyRelImpl.setPasswordPolicyId(getPasswordPolicyId());

		passwordPolicyRelModelImpl._originalPasswordPolicyId = passwordPolicyRelModelImpl._passwordPolicyId;

		passwordPolicyRelModelImpl._setOriginalPasswordPolicyId = false;
		passwordPolicyRelImpl.setClassNameId(getClassNameId());

		passwordPolicyRelModelImpl._originalClassNameId = passwordPolicyRelModelImpl._classNameId;

		passwordPolicyRelModelImpl._setOriginalClassNameId = false;
		passwordPolicyRelImpl.setClassPK(getClassPK());

		passwordPolicyRelModelImpl._originalClassPK = passwordPolicyRelModelImpl._classPK;

		passwordPolicyRelModelImpl._setOriginalClassPK = false;

		return passwordPolicyRelImpl;
	}

	public int compareTo(PasswordPolicyRel passwordPolicyRel) {
		long pk = passwordPolicyRel.getPrimaryKey();

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

		PasswordPolicyRel passwordPolicyRel = null;

		try {
			passwordPolicyRel = (PasswordPolicyRel)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = passwordPolicyRel.getPrimaryKey();

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
		StringBundler sb = new StringBundler(9);

		sb.append("{passwordPolicyRelId=");
		sb.append(getPasswordPolicyRelId());
		sb.append(", passwordPolicyId=");
		sb.append(getPasswordPolicyId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.PasswordPolicyRel");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>passwordPolicyRelId</column-name><column-value><![CDATA[");
		sb.append(getPasswordPolicyRelId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>passwordPolicyId</column-name><column-value><![CDATA[");
		sb.append(getPasswordPolicyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _passwordPolicyRelId;
	private long _passwordPolicyId;
	private long _originalPasswordPolicyId;
	private boolean _setOriginalPasswordPolicyId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private transient ExpandoBridge _expandoBridge;
}