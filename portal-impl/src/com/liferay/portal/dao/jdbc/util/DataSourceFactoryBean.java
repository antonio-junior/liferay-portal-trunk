/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.dao.jdbc.util;

import com.liferay.portal.kernel.util.SortedProperties;
import com.liferay.portal.util.PropsUtil;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * <a href="DataSourceFactoryBean.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class DataSourceFactoryBean extends AbstractFactoryBean {

	public Class getObjectType() {
		return DataSource.class;
	}

	public void setPropertyPrefix(String propertyPrefix) {
		_propertyPrefix = propertyPrefix;
	}

	protected Object createInstance() throws Exception {
		Properties properties = PropsUtil.getProperties(_propertyPrefix, true);

		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(properties.getProperty("driver"));
		dataSource.setUrl(properties.getProperty("url"));
		dataSource.setUsername(properties.getProperty("username"));
		dataSource.setPassword(properties.getProperty("password"));

		if (_log.isDebugEnabled()) {
			SortedProperties sortedProperties = new SortedProperties(
				properties);

			_log.debug("Properties for prefix " + _propertyPrefix);

			sortedProperties.list(System.out);
		}

		return dataSource;
	}

	private static Log _log = LogFactory.getLog(DataSourceFactoryBean.class);

	private String _propertyPrefix;

}