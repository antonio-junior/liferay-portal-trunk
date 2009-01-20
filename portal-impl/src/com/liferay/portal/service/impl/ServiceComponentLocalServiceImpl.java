/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.service.impl;

import com.liferay.portal.OldServiceComponentException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.ModelHintsUtil;
import com.liferay.portal.model.ServiceComponent;
import com.liferay.portal.service.base.ServiceComponentLocalServiceBaseImpl;
import com.liferay.portal.tools.servicebuilder.Entity;
import com.liferay.portal.tools.sql.DBUtil;
import com.liferay.portal.upgrade.util.DefaultUpgradeTableImpl;
import com.liferay.portal.upgrade.util.UpgradeTable;

import java.io.IOException;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

/**
 * <a href="ServiceComponentLocalServiceImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ServiceComponentLocalServiceImpl
	extends ServiceComponentLocalServiceBaseImpl {

	public void destroyServiceComponent(
			ServletContext servletContext, ClassLoader classLoader)
		throws SystemException {

		try {
			clearCacheRegistry(servletContext);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public ServiceComponent initServiceComponent(
			ServletContext servletContext, ClassLoader classLoader,
			String buildNamespace, long buildNumber, long buildDate)
		throws PortalException, SystemException {

		try {
			ModelHintsUtil.read(
				classLoader, "META-INF/portlet-model-hints.xml");
		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		try {
			ModelHintsUtil.read(
				classLoader, "META-INF/portlet-model-hints-ext.xml");
		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		ServiceComponent serviceComponent = null;
		ServiceComponent previousServiceComponent = null;

		List<ServiceComponent> serviceComponents =
			serviceComponentPersistence.findByBuildNamespace(
				buildNamespace, 0, 1);

		if (serviceComponents.size() == 0) {
			long serviceComponentId = counterLocalService.increment();

			serviceComponent = serviceComponentPersistence.create(
				serviceComponentId);

			serviceComponent.setBuildNamespace(buildNamespace);
			serviceComponent.setBuildNumber(buildNumber);
			serviceComponent.setBuildDate(buildDate);
		}
		else {
			serviceComponent = serviceComponents.get(0);

			if (serviceComponent.getBuildNumber() < buildNumber) {
				previousServiceComponent = serviceComponent;

				long serviceComponentId = counterLocalService.increment();

				serviceComponent = serviceComponentPersistence.create(
					serviceComponentId);

				serviceComponent.setBuildNamespace(buildNamespace);
				serviceComponent.setBuildNumber(buildNumber);
				serviceComponent.setBuildDate(buildDate);
			}
			else if (serviceComponent.getBuildNumber() > buildNumber) {
				throw new OldServiceComponentException(
					"Build namespace " + buildNamespace + " has build number " +
						serviceComponent.getBuildNumber() +
							" which is newer than " + buildNumber);
			}
			else {
				return serviceComponent;
			}
		}

		try {
			Document doc = SAXReaderUtil.createDocument(StringPool.UTF8);

			Element data = doc.addElement("data");

			String tablesSQL = HttpUtil.URLtoString(servletContext.getResource(
				"/WEB-INF/sql/tables.sql"));

			data.addElement("tables-sql").addCDATA(tablesSQL);

			String sequencesSQL = HttpUtil.URLtoString(
				servletContext.getResource("/WEB-INF/sql/sequences.sql"));

			data.addElement("sequences-sql").addCDATA(sequencesSQL);

			String indexesSQL = HttpUtil.URLtoString(servletContext.getResource(
				"/WEB-INF/sql/indexes.sql"));

			data.addElement("indexes-sql").addCDATA(indexesSQL);

			String dataXML = doc.formattedString();

			serviceComponent.setData(dataXML);

			serviceComponentPersistence.update(serviceComponent, false);

			upgradeDB(
				classLoader, buildNamespace, buildNumber,
				previousServiceComponent, tablesSQL, sequencesSQL, indexesSQL);

			removeOldServiceComponents(buildNamespace);

			return serviceComponent;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	protected void clearCacheRegistry(ServletContext servletContext)
		throws DocumentException, IOException {

		String xml = HttpUtil.URLtoString(
			servletContext.getResource(
				"/WEB-INF/classes/META-INF/portlet-hbm.xml"));

		if (xml == null) {
			return;
		}

		Document doc = SAXReaderUtil.read(xml);

		Element root = doc.getRootElement();

		List<Element> classEls = root.elements("class");

		for (Element classEl : classEls) {
			String name = classEl.attributeValue("name");

			CacheRegistry.unregister(name);
		}

		CacheRegistry.clear();
	}

	protected List<String> getModels(ClassLoader classLoader)
		throws DocumentException, IOException {

		List<String> models = new ArrayList<String>();

		String xml = StringUtil.read(
			classLoader, "META-INF/portlet-model-hints.xml");

		models.addAll(getModels(xml));

		xml = StringUtil.read(
			classLoader, "META-INF/portlet-model-hints-ext.xml");

		models.addAll(getModels(xml));

		return models;
	}

	protected List<String> getModels(String xml) throws DocumentException {
		List<String> models = new ArrayList<String>();

		Document doc = SAXReaderUtil.read(xml);

		Element root = doc.getRootElement();

		Iterator<Element> itr = root.elements("model").iterator();

		while (itr.hasNext()) {
			Element modelEl = itr.next();

			String name = modelEl.attributeValue("name");

			models.add(name);
		}

		return models;
	}

	protected void upgradeDB(
			ClassLoader classLoader, String buildNamespace, long buildNumber,
			ServiceComponent previousServiceComponent, String tablesSQL,
			String sequencesSQL, String indexesSQL)
		throws Exception {

		DBUtil dbUtil = DBUtil.getInstance();

		if (previousServiceComponent == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Running " + buildNamespace +
						" SQL scripts for the first time");
			}

			dbUtil.runSQLTemplateString(tablesSQL, true, false);
			dbUtil.runSQLTemplateString(sequencesSQL, true, false);
			dbUtil.runSQLTemplateString(indexesSQL, true, false);
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Upgrading " + buildNamespace +
						" database to build number " + buildNumber);
			}

			if (!tablesSQL.equals(
					previousServiceComponent.getTablesSQL())) {

				if (_log.isInfoEnabled()) {
					_log.info("Upgrading database with tables.sql");
				}

				dbUtil.runSQLTemplateString(tablesSQL, true, false);

				upgradeModels(classLoader);
			}

			if (!sequencesSQL.equals(
					previousServiceComponent.getSequencesSQL())) {

				if (_log.isInfoEnabled()) {
					_log.info("Upgrading database with sequences.sql");
				}

				dbUtil.runSQLTemplateString(sequencesSQL, true, false);
			}

			if (!indexesSQL.equals(
					previousServiceComponent.getIndexesSQL())) {

				if (_log.isInfoEnabled()) {
					_log.info("Upgrading database with indexes.sql");
				}

				dbUtil.runSQLTemplateString(indexesSQL, true, false);
			}
		}
	}

	protected void upgradeModels(ClassLoader classLoader) throws Exception {
		List<String> models = getModels(classLoader);

		for (String name : models) {
			int pos = name.lastIndexOf(".model.");

			name =
				name.substring(0, pos) + ".model.impl." +
					name.substring(pos + 7) + "ModelImpl";

			Class<?> modelClass = Class.forName(name, true, classLoader);

			Field tableNameField = modelClass.getField("TABLE_NAME");
			Field tableColumnsField = modelClass.getField("TABLE_COLUMNS");
			Field tableSQLCreateField = modelClass.getField("TABLE_SQL_CREATE");
			Field dataSourceField = modelClass.getField("DATA_SOURCE");

			String tableName = (String)tableNameField.get(null);
			Object[][] tableColumns = (Object[][])tableColumnsField.get(null);
			String tableSQLCreate = (String)tableSQLCreateField.get(null);
			String dataSource = (String)dataSourceField.get(null);

			if (!dataSource.equals(Entity.DEFAULT_DATA_SOURCE)) {
				continue;
			}

			UpgradeTable upgradeTable = new DefaultUpgradeTableImpl(
				tableName, tableColumns);

			upgradeTable.setCreateSQL(tableSQLCreate);

			upgradeTable.updateTable();
		}
	}

	protected void removeOldServiceComponents(String buildNamespace)
		throws SystemException {

		int serviceComponentsCount =
			serviceComponentPersistence.countByBuildNamespace(buildNamespace);

		if (serviceComponentsCount < _MAX_SERVICE_COMPONENTS) {
			return;
		}

		List<ServiceComponent> serviceComponents =
			serviceComponentPersistence.findByBuildNamespace(
				buildNamespace, _MAX_SERVICE_COMPONENTS,
				serviceComponentsCount);

		for (int i = 0; i < serviceComponents.size(); i++) {
			ServiceComponent serviceComponent = serviceComponents.get(i);

			serviceComponentPersistence.remove(serviceComponent);
		}
	}

	private static final int _MAX_SERVICE_COMPONENTS = 10;

	private static Log _log =
		LogFactoryUtil.getLog(ServiceComponentLocalServiceImpl.class);

}