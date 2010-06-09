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

package com.liferay.portal.kernel.jmx.model;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.management.MBeanInfo;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * <a href="MBean.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 */
public class MBean implements Serializable {

	public MBean(String domainName, String mBeanName) {
		_domainName = domainName;
		_mBeanName = mBeanName;
		_loaded = false;
	}

	public MBean(ObjectName objectName) {
		this(objectName.getDomain(), objectName.getKeyPropertyListString());
		_objectName = objectName;
	}

	public MBean(ObjectName objectName, MBeanInfo mBeanInfo) {
		_domainName = objectName.getDomain();
		_mBeanName = objectName.getKeyPropertyListString();
		_mBeanInfo = mBeanInfo;
		_loaded = true;
	}

	public String getDomainName() {
		return _domainName;
	}

	public MBeanInfo getMBeanInfo() {
		return _mBeanInfo;
	}

	public String getMBeanName() {
		return _mBeanName;
	}

	public ObjectName getObjectName() throws MalformedObjectNameException {
		if (_objectName == null) {
			_objectName = new ObjectName(
				_domainName.concat(":").concat(_mBeanName));
		}
		return _objectName;
	}

	public List<String> getPath() {

		if (_path == null) {
			String[] steps = StringUtil.split(_mBeanName);
			_path = new ArrayList<String>(steps.length);
			for (String step : steps) {
				String[] keyValuePair = StringUtil.split(step,
					StringPool.EQUAL);
				if (keyValuePair.length != 2) {
					_log.error("Wrong MBean name syntax:" + _mBeanName);
				} else {
					_path.add(keyValuePair[1]);
				}
			}
		}
		return _path;
	}

	public boolean isLoaded() {
		return _loaded;
	}

	private static Log _log = LogFactoryUtil.getLog(MBean.class);

	private String _domainName;
	private boolean _loaded;
	private MBeanInfo _mBeanInfo;
	private String _mBeanName;
	private ObjectName _objectName;
	private List<String> _path;

}