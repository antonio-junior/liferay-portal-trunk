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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanySoap;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="CompanyModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Company table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CompanyImpl
 * @see       com.liferay.portal.model.Company
 * @see       com.liferay.portal.model.CompanyModel
 * @generated
 */
public class CompanyModelImpl extends BaseModelImpl<Company> {
	public static final String TABLE_NAME = "Company";
	public static final Object[][] TABLE_COLUMNS = {
			{ "companyId", new Integer(Types.BIGINT) },
			{ "accountId", new Integer(Types.BIGINT) },
			{ "webId", new Integer(Types.VARCHAR) },
			{ "key_", new Integer(Types.CLOB) },
			{ "virtualHost", new Integer(Types.VARCHAR) },
			{ "mx", new Integer(Types.VARCHAR) },
			{ "homeURL", new Integer(Types.VARCHAR) },
			{ "logoId", new Integer(Types.BIGINT) },
			{ "system", new Integer(Types.BOOLEAN) },
			{ "maxUsers", new Integer(Types.INTEGER) }
		};
	public static final String TABLE_SQL_CREATE = "create table Company (companyId LONG not null primary key,accountId LONG,webId VARCHAR(75) null,key_ TEXT null,virtualHost VARCHAR(75) null,mx VARCHAR(75) null,homeURL STRING null,logoId LONG,system BOOLEAN,maxUsers INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table Company";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.Company"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.Company"),
			true);

	public static Company toModel(CompanySoap soapModel) {
		Company model = new CompanyImpl();

		model.setCompanyId(soapModel.getCompanyId());
		model.setAccountId(soapModel.getAccountId());
		model.setWebId(soapModel.getWebId());
		model.setKey(soapModel.getKey());
		model.setVirtualHost(soapModel.getVirtualHost());
		model.setMx(soapModel.getMx());
		model.setHomeURL(soapModel.getHomeURL());
		model.setLogoId(soapModel.getLogoId());
		model.setSystem(soapModel.getSystem());
		model.setMaxUsers(soapModel.getMaxUsers());

		return model;
	}

	public static List<Company> toModels(CompanySoap[] soapModels) {
		List<Company> models = new ArrayList<Company>(soapModels.length);

		for (CompanySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.Company"));

	public CompanyModelImpl() {
	}

	public long getPrimaryKey() {
		return _companyId;
	}

	public void setPrimaryKey(long pk) {
		setCompanyId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_companyId);
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public String getWebId() {
		if (_webId == null) {
			return StringPool.BLANK;
		}
		else {
			return _webId;
		}
	}

	public void setWebId(String webId) {
		_webId = webId;

		if (_originalWebId == null) {
			_originalWebId = webId;
		}
	}

	public String getOriginalWebId() {
		return GetterUtil.getString(_originalWebId);
	}

	public String getKey() {
		if (_key == null) {
			return StringPool.BLANK;
		}
		else {
			return _key;
		}
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getVirtualHost() {
		if (_virtualHost == null) {
			return StringPool.BLANK;
		}
		else {
			return _virtualHost;
		}
	}

	public void setVirtualHost(String virtualHost) {
		_virtualHost = virtualHost;

		if (_originalVirtualHost == null) {
			_originalVirtualHost = virtualHost;
		}
	}

	public String getOriginalVirtualHost() {
		return GetterUtil.getString(_originalVirtualHost);
	}

	public String getMx() {
		if (_mx == null) {
			return StringPool.BLANK;
		}
		else {
			return _mx;
		}
	}

	public void setMx(String mx) {
		_mx = mx;

		if (_originalMx == null) {
			_originalMx = mx;
		}
	}

	public String getOriginalMx() {
		return GetterUtil.getString(_originalMx);
	}

	public String getHomeURL() {
		if (_homeURL == null) {
			return StringPool.BLANK;
		}
		else {
			return _homeURL;
		}
	}

	public void setHomeURL(String homeURL) {
		_homeURL = homeURL;
	}

	public long getLogoId() {
		return _logoId;
	}

	public void setLogoId(long logoId) {
		_logoId = logoId;

		if (!_setOriginalLogoId) {
			_setOriginalLogoId = true;

			_originalLogoId = logoId;
		}
	}

	public long getOriginalLogoId() {
		return _originalLogoId;
	}

	public boolean getSystem() {
		return _system;
	}

	public boolean isSystem() {
		return _system;
	}

	public void setSystem(boolean system) {
		_system = system;
	}

	public int getMaxUsers() {
		return _maxUsers;
	}

	public void setMaxUsers(int maxUsers) {
		_maxUsers = maxUsers;
	}

	public Company toEscapedModel() {
		if (isEscapedModel()) {
			return (Company)this;
		}
		else {
			return (Company)Proxy.newProxyInstance(Company.class.getClassLoader(),
				new Class[] { Company.class }, new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					Company.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		CompanyImpl clone = new CompanyImpl();

		clone.setCompanyId(getCompanyId());
		clone.setAccountId(getAccountId());
		clone.setWebId(getWebId());
		clone.setKey(getKey());
		clone.setVirtualHost(getVirtualHost());
		clone.setMx(getMx());
		clone.setHomeURL(getHomeURL());
		clone.setLogoId(getLogoId());
		clone.setSystem(getSystem());
		clone.setMaxUsers(getMaxUsers());

		return clone;
	}

	public int compareTo(Company company) {
		long pk = company.getPrimaryKey();

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

		Company company = null;

		try {
			company = (Company)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = company.getPrimaryKey();

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

		sb.append("{companyId=");
		sb.append(getCompanyId());
		sb.append(", accountId=");
		sb.append(getAccountId());
		sb.append(", webId=");
		sb.append(getWebId());
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", virtualHost=");
		sb.append(getVirtualHost());
		sb.append(", mx=");
		sb.append(getMx());
		sb.append(", homeURL=");
		sb.append(getHomeURL());
		sb.append(", logoId=");
		sb.append(getLogoId());
		sb.append(", system=");
		sb.append(getSystem());
		sb.append(", maxUsers=");
		sb.append(getMaxUsers());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.Company");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountId</column-name><column-value><![CDATA[");
		sb.append(getAccountId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>webId</column-name><column-value><![CDATA[");
		sb.append(getWebId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>virtualHost</column-name><column-value><![CDATA[");
		sb.append(getVirtualHost());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mx</column-name><column-value><![CDATA[");
		sb.append(getMx());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>homeURL</column-name><column-value><![CDATA[");
		sb.append(getHomeURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>logoId</column-name><column-value><![CDATA[");
		sb.append(getLogoId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>system</column-name><column-value><![CDATA[");
		sb.append(getSystem());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>maxUsers</column-name><column-value><![CDATA[");
		sb.append(getMaxUsers());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _companyId;
	private long _accountId;
	private String _webId;
	private String _originalWebId;
	private String _key;
	private String _virtualHost;
	private String _originalVirtualHost;
	private String _mx;
	private String _originalMx;
	private String _homeURL;
	private long _logoId;
	private long _originalLogoId;
	private boolean _setOriginalLogoId;
	private boolean _system;
	private int _maxUsers;
	private transient ExpandoBridge _expandoBridge;
}