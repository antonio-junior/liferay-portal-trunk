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

package com.liferay.counter.model.impl;

import com.liferay.counter.model.Counter;
import com.liferay.counter.model.CounterModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

/**
 * <p>
 * This interface is a model that represents the Counter table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CounterImpl
 * @see       com.liferay.counter.model.Counter
 * @see       com.liferay.counter.model.CounterModel
 * @generated
 */
public class CounterModelImpl extends BaseModelImpl<Counter>
	implements CounterModel {
	public static final String TABLE_NAME = "Counter";
	public static final Object[][] TABLE_COLUMNS = {
			{ "name", new Integer(Types.VARCHAR) },
			{ "currentId", new Integer(Types.BIGINT) }
		};
	public static final String TABLE_SQL_CREATE = "create table Counter (name VARCHAR(75) not null primary key,currentId LONG)";
	public static final String TABLE_SQL_DROP = "drop table Counter";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.counter.model.Counter"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.counter.model.Counter"),
			false);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.counter.model.Counter"));

	public CounterModelImpl() {
	}

	public String getPrimaryKey() {
		return _name;
	}

	public void setPrimaryKey(String pk) {
		setName(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return _name;
	}

	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	public void setName(String name) {
		_name = name;
	}

	public long getCurrentId() {
		return _currentId;
	}

	public void setCurrentId(long currentId) {
		_currentId = currentId;
	}

	public Counter toEscapedModel() {
		if (isEscapedModel()) {
			return (Counter)this;
		}
		else {
			return (Counter)Proxy.newProxyInstance(Counter.class.getClassLoader(),
				new Class[] { Counter.class }, new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		CounterImpl clone = new CounterImpl();

		clone.setName(getName());
		clone.setCurrentId(getCurrentId());

		return clone;
	}

	public int compareTo(Counter counter) {
		String pk = counter.getPrimaryKey();

		return getPrimaryKey().compareTo(pk);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Counter counter = null;

		try {
			counter = (Counter)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		String pk = counter.getPrimaryKey();

		if (getPrimaryKey().equals(pk)) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{name=");
		sb.append(getName());
		sb.append(", currentId=");
		sb.append(getCurrentId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(10);

		sb.append("<model><model-name>");
		sb.append("com.liferay.counter.model.Counter");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentId</column-name><column-value><![CDATA[");
		sb.append(getCurrentId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _name;
	private long _currentId;
}