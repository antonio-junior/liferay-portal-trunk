/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.tools;

import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.ModelHintsUtil;
import com.liferay.portal.util.SAXReaderFactory;
import com.liferay.util.FileUtil;
import com.liferay.util.GetterUtil;
import com.liferay.util.StringUtil;
import com.liferay.util.TextFormatter;
import com.liferay.util.Time;
import com.liferay.util.Validator;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.Type;

import de.hunsicker.io.FileFormat;
import de.hunsicker.jalopy.Jalopy;
import de.hunsicker.jalopy.storage.Convention;
import de.hunsicker.jalopy.storage.ConventionKeys;
import de.hunsicker.jalopy.storage.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * <a href="ServiceBuilder.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Charles May
 * @author Alexander Chow
 *
 */
public class ServiceBuilder {

	public static void main(String[] args) {
		if (args.length == 8) {
			new ServiceBuilder(
				args[0], args[1], args[2], args[3], args[4], args[5], args[6],
				args[7]);
		}
		else {
			System.out.println(
				"Please pass in the correct number of parameters. Sample " +
					"parameters are:\n" +
				"\tservice.xml\n" +
				"\tclasses/META-INF/portal-hbm.xml\n" +
				"\tclasses/META-INF/portal-model-hints.xml\n" +
				"\tclasses/META-INF/portal-spring-enterprise.xml\n" +
				"\tclasses/META-INF/portal-spring-professional.xml\n" +
				"\tcom.liferay.portal.kernel.bean.BeanLocatorUtil\n" +
				"\t../portal-service/src\n" +
				"\t../portal-web/docroot/html/js/liferay_services.js");
		}
	}

	public static Set getBadCmpFields() {
		Set badCmpFields = new HashSet();

		badCmpFields.add("access");
		badCmpFields.add("active");
		badCmpFields.add("alias");
		badCmpFields.add("date");
		badCmpFields.add("end");
		badCmpFields.add("idd");
		badCmpFields.add("featured");
		badCmpFields.add("fields");
		badCmpFields.add("from");
		badCmpFields.add("hidden");
		badCmpFields.add("index");
		badCmpFields.add("internal");
		badCmpFields.add("interval");
		badCmpFields.add("join");
		badCmpFields.add("key");
		badCmpFields.add("log");
		badCmpFields.add("number");
		badCmpFields.add("password");
		badCmpFields.add("primary");
		badCmpFields.add("sale");
		badCmpFields.add("settings");
		badCmpFields.add("size");
		badCmpFields.add("start");
		badCmpFields.add("text");
		badCmpFields.add("to");
		badCmpFields.add("type");
		badCmpFields.add("values");

		return badCmpFields;
	}

	public static Set getBadTableNames() {
		Set badTableNames = new HashSet();

		badTableNames = new HashSet();
		badTableNames.add("Account");
		badTableNames.add("Action");
		badTableNames.add("Cache");
		badTableNames.add("Contact");
		badTableNames.add("Group");
		badTableNames.add("Organization");
		badTableNames.add("Permission");
		badTableNames.add("Release");
		badTableNames.add("Resource");
		badTableNames.add("Role");
		badTableNames.add("User");

		return badTableNames;
	}

	public static void writeFile(File file, String content) throws IOException {
		writeFile(file, content, "Brian Wing Shun Chan");
	}

	public static void writeFile(File file, String content, String author)
		throws IOException {

		File tempFile = new File("ServiceBuilder.temp");

		FileUtil.write(tempFile, content);

		/*if (file.getName().equals("ShoppingItemPersistence.java")) {
			FileUtil.write(file, content);

			return;
		}*/

		// Strip unused imports

		String[] checkImports = new String[] {
			"com.liferay.portal.PortalException",
			"com.liferay.portal.kernel.log.Log",
			"com.liferay.portal.kernel.log.LogFactoryUtil",
			"com.liferay.portal.kernel.util.BooleanWrapper",
			"com.liferay.portal.kernel.util.DoubleWrapper",
			"com.liferay.portal.kernel.util.FloatWrapper",
			"com.liferay.portal.kernel.util.IntegerWrapper",
			"com.liferay.portal.kernel.util.LongWrapper",
			"com.liferay.portal.kernel.util.MethodWrapper",
			"com.liferay.portal.kernel.util.NullWrapper",
			"com.liferay.portal.kernel.util.OrderByComparator",
			"com.liferay.portal.kernel.util.ShortWrapper",
			"com.liferay.portal.kernel.util.StringPool",
			"com.liferay.portal.security.auth.HttpPrincipal",
			"com.liferay.portal.service.http.TunnelUtil",
			"com.liferay.portal.service.impl.PrincipalSessionBean",
			"com.liferay.portal.spring.hibernate.HibernateUtil",
			"com.liferay.portal.util.PropsUtil",
			"com.liferay.util.DateUtil",
			"com.liferay.util.GetterUtil",
			"com.liferay.util.InstancePool",
			"com.liferay.util.dao.hibernate.QueryPos",
			"com.liferay.util.dao.hibernate.QueryUtil",
			"java.rmi.RemoteException",
			"java.sql.ResultSet",
			"java.sql.SQLException",
			"java.sql.Types",
			"java.util.Collection",
			"java.util.Collections",
			"java.util.Date",
			"java.util.HashSet",
			"java.util.Iterator",
			"java.util.List",
			"java.util.Properties",
			"java.util.Set",
			"javax.sql.DataSource",
			"org.apache.commons.logging.Log",
			"org.apache.commons.logging.LogFactory",
			"org.hibernate.Hibernate",
			"org.hibernate.ObjectNotFoundException",
			"org.hibernate.Query",
			"org.hibernate.SQLQuery",
			"org.json.JSONArray",
			"org.json.JSONObject",
			"org.springframework.dao.DataAccessException",
			"org.springframework.jdbc.core.SqlParameter",
			"org.springframework.jdbc.object.MappingSqlQuery",
			"org.springframework.jdbc.object.SqlUpdate"
		};

		Set classes = ClassUtil.getClasses(tempFile);

		for (int i = 0; i < checkImports.length; i++) {
			String importClass = checkImports[i].substring(
				checkImports[i].lastIndexOf(".") + 1, checkImports[i].length());

			if (!classes.contains(importClass)) {
				content = StringUtil.replace(
					content, "import " + checkImports[i] + ";", "");
			}
		}

		FileUtil.write(tempFile, content);

		// Beautify

		StringBuffer sb = new StringBuffer();

		Jalopy jalopy = new Jalopy();
		jalopy.setFileFormat(FileFormat.UNIX);
		jalopy.setInput(tempFile);
		jalopy.setOutput(sb);

		try {
			Jalopy.setConvention("../tools/jalopy.xml");
		}
		catch (FileNotFoundException fnne) {
		}

		Environment env = Environment.getInstance();
		env.set("author", author);
		env.set("fileName", file.getName());

		Convention convention = Convention.getInstance();

		convention.put(
			ConventionKeys.COMMENT_JAVADOC_TEMPLATE_CLASS,
			env.interpolate(convention.get(
				ConventionKeys.COMMENT_JAVADOC_TEMPLATE_CLASS, "")));

		convention.put(
			ConventionKeys.COMMENT_JAVADOC_TEMPLATE_INTERFACE,
			env.interpolate(convention.get(
				ConventionKeys.COMMENT_JAVADOC_TEMPLATE_INTERFACE, "")));

		jalopy.format();

		String newContent = sb.toString();

		/*
		// Remove blank lines after try {

		newContent = StringUtil.replace(newContent, "try {\n\n", "try {\n");

		// Remove blank lines after ) {

		newContent = StringUtil.replace(newContent, ") {\n\n", ") {\n");

		// Remove blank lines empty braces { }

		newContent = StringUtil.replace(newContent, "\n\n\t}", "\n\t}");

		// Add space to last }

		newContent = newContent.substring(0, newContent.length() - 2) + "\n\n}";
		*/

		// Write file if and only if the file has changed

		String oldContent = null;

		if (file.exists()) {

			// Read file

			oldContent = FileUtil.read(file);

			// Keep old version number

			int x = oldContent.indexOf("@version $Revision:");

			if (x != -1) {
				int y = oldContent.indexOf("$", x);
				y = oldContent.indexOf("$", y + 1);

				String oldVersion = oldContent.substring(x, y + 1);

				newContent = StringUtil.replace(
					newContent, "@version $Rev: $", oldVersion);
			}
		}
		else {
			newContent = StringUtil.replace(
				newContent, "@version $Rev: $", "@version $Revision: 1.183 $");
		}

		if (oldContent == null || !oldContent.equals(newContent)) {
			FileUtil.write(file, newContent);

			System.out.println("Writing " + file);

			// Workaround for bug with XJavaDoc

			file.setLastModified(
				System.currentTimeMillis() - (Time.SECOND * 5));
		}

		tempFile.deleteOnExit();
	}

	public ServiceBuilder(String fileName, String hbmFileName,
						  String modelHintsFileName, String springEntFileName,
						  String springProFileName,
						  String beanLocatorUtilClassName, String serviceDir,
						  String jsonFileName) {

		new ServiceBuilder(
			fileName, hbmFileName, modelHintsFileName, springEntFileName,
			springProFileName, beanLocatorUtilClassName, serviceDir,
			jsonFileName, true);
	}

	public ServiceBuilder(String fileName, String hbmFileName,
						  String modelHintsFileName, String springEntFileName,
						  String springProFileName,
						  String beanLocatorUtilClassName, String serviceDir,
						  String jsonFileName, boolean build) {

		try {
			_badTableNames = ServiceBuilder.getBadTableNames();
			_badCmpFields = ServiceBuilder.getBadCmpFields();

			_hbmFileName = hbmFileName;
			_modelHintsFileName = modelHintsFileName;
			_springEntFileName = springEntFileName;
			_springProFileName = springProFileName;
			_beanLocatorUtilClassName = beanLocatorUtilClassName;
			_serviceDir = serviceDir;

			SAXReader reader = SAXReaderFactory.getInstance();

			Document doc = reader.read(new File(fileName));

			Element root = doc.getRootElement();

			_portalRoot = root.attributeValue("root-dir");

			String packagePath = root.attributeValue("package-path");

			_portletName = root.element("portlet").attributeValue("name");

			_portletShortName =
				root.element("portlet").attributeValue("short-name");

			_portletPackageName =
				TextFormatter.format(_portletName, TextFormatter.B);

			_outputPath =
				"src/" + StringUtil.replace(packagePath, ".", "/") + "/" +
					_portletPackageName;

			if (Validator.isNull(_serviceDir)) {
				_serviceOutputPath = _outputPath;
			}
			else {
				_serviceOutputPath =
					_serviceDir + "/" +
						StringUtil.replace(packagePath, ".", "/") + "/" +
							_portletPackageName;
			}

			_jsonFileName = jsonFileName;

			_packagePath = packagePath + "." + _portletPackageName;

			_ejbList = new ArrayList();

			List entities = root.elements("entity");

			Iterator itr1 = entities.iterator();

			while (itr1.hasNext()) {
				Element entityEl = (Element)itr1.next();

				String ejbName = entityEl.attributeValue("name");

				String table = entityEl.attributeValue("table");
				if (Validator.isNull(table)) {
					table = ejbName;

					if (_badTableNames.contains(ejbName)) {
						table += "_";
					}
				}

				boolean localService = GetterUtil.getBoolean(
					entityEl.attributeValue("local-service"), false);
				boolean remoteService = GetterUtil.getBoolean(
					entityEl.attributeValue("remote-service"), true);
				String persistenceClass = GetterUtil.getString(
					entityEl.attributeValue("persistence-class"),
					_packagePath + ".service.persistence."+ ejbName +
						"Persistence");
				String dataSource = entityEl.attributeValue("data-source");
				String sessionFactory = entityEl.attributeValue(
					"session-factory");

				Iterator itr2 = null;

				List pkList = new ArrayList();
				List regularColList = new ArrayList();
				List collectionList = new ArrayList();
				List columnList = new ArrayList();

				List columns = entityEl.elements("column");

				itr2 = columns.iterator();

				while (itr2.hasNext()) {
					Element column = (Element)itr2.next();

					String columnName = column.attributeValue("name");

					String columnDBName = column.attributeValue("db-name");

					if (Validator.isNull(columnDBName)) {
						columnDBName = columnName;

						if (_badCmpFields.contains(columnName)) {
							columnDBName += "_";
						}
					}

					String columnType = column.attributeValue("type");
					boolean primary = GetterUtil.getBoolean(
						column.attributeValue("primary"), false);
					String collectionEntity = column.attributeValue("entity");
					String mappingKey = column.attributeValue("mapping-key");
					String mappingTable = column.attributeValue("mapping-table");
					String idType = column.attributeValue("id-type");
					String idParam = column.attributeValue("id-param");
					boolean convertNull = GetterUtil.getBoolean(
						column.attributeValue("convert-null"), true);

					EntityColumn col = new EntityColumn(
						columnName, columnDBName, columnType, primary,
						collectionEntity, mappingKey, mappingTable, idType,
						idParam, convertNull);

					if (primary) {
						pkList.add(col);
					}

					if (columnType.equals("Collection")) {
						collectionList.add(col);
					}
					else {
						regularColList.add(col);
					}

					columnList.add(col);
				}

				EntityOrder order = null;

				Element orderEl = entityEl.element("order");

				if (orderEl != null) {
					boolean asc = true;
					if ((orderEl.attribute("by") != null) &&
						(orderEl.attributeValue("by").equals("desc"))) {

						asc = false;
					}

					List orderColsList = new ArrayList();

					order = new EntityOrder(asc, orderColsList);

					List orderCols = orderEl.elements("order-column");

					Iterator itr3 = orderCols.iterator();

					while (itr3.hasNext()) {
						Element orderColEl = (Element)itr3.next();

						String orderColName =
							orderColEl.attributeValue("name");
						boolean orderColCaseSensitive = GetterUtil.getBoolean(
							orderColEl.attributeValue("case-sensitive"),
							true);

						boolean orderColByAscending = asc;
						String orderColBy = GetterUtil.getString(
							orderColEl.attributeValue("order-by"));
						if (orderColBy.equals("asc")) {
							orderColByAscending = true;
						}
						else if (orderColBy.equals("desc")) {
							orderColByAscending = false;
						}

						EntityColumn col = Entity.getColumn(
							orderColName, columnList);

						col = (EntityColumn)col.clone();

						col.setCaseSensitive(orderColCaseSensitive);
						col.setOrderByAscending(orderColByAscending);

						orderColsList.add(col);
					}
				}

				List finderList = new ArrayList();

				List finders = entityEl.elements("finder");

				itr2 = finders.iterator();

				while (itr2.hasNext()) {
					Element finderEl = (Element)itr2.next();

					String finderName = finderEl.attributeValue("name");
					String finderReturn =
						finderEl.attributeValue("return-type");
					String finderWhere =
						finderEl.attributeValue("where");
					boolean finderDBIndex = GetterUtil.getBoolean(
						finderEl.attributeValue("db-index"), true);

					List finderColsList = new ArrayList();

					List finderCols = finderEl.elements("finder-column");

					Iterator itr3 = finderCols.iterator();

					while (itr3.hasNext()) {
						Element finderColEl = (Element)itr3.next();

						String finderColName =
							finderColEl.attributeValue("name");

						String finderColDBName =
							finderColEl.attributeValue("db-name");

						if (Validator.isNull(finderColDBName)) {
							finderColDBName = finderColName;

							if (_badCmpFields.contains(finderColName)) {
								finderColDBName += "_";
							}
						}

						String finderColComparator = GetterUtil.getString(
							finderColEl.attributeValue("comparator"), "=");

						EntityColumn col = Entity.getColumn(
							finderColName, columnList);

						col = (EntityColumn)col.clone();

						col.setDBName(finderColDBName);
						col.setComparator(finderColComparator);

						finderColsList.add(col);
					}

					finderList.add(new EntityFinder(
						finderName, finderReturn, finderColsList, finderWhere,
						finderDBIndex));
				}

				List referenceList = new ArrayList();

				if (build) {
					List references = entityEl.elements("reference");

					itr2 = references.iterator();

					while (itr2.hasNext()) {
						Element reference = (Element)itr2.next();

						String refPackage =
							reference.attributeValue("package-path");
						String refEntity = reference.attributeValue("entity");

						if ((refPackage == null) || (refEntity == null)) {
							referenceList.add(reference.getText().trim());
						}
						else {
							referenceList.add(
								getEntity(refPackage + "." + refEntity));
						}
					}
				}

				_ejbList.add(
					new Entity(
						_packagePath, _portletName, _portletShortName, ejbName,
						table, localService, remoteService, persistenceClass,
						dataSource, sessionFactory, pkList, regularColList,
						collectionList, columnList, order, finderList,
						referenceList));
			}

			List exceptionList = new ArrayList();

			if (root.element("exceptions") != null) {
				List exceptions =
					root.element("exceptions").elements("exception");

				itr1 = exceptions.iterator();

				while (itr1.hasNext()) {
					Element exception = (Element)itr1.next();

					exceptionList.add(exception.getText());
				}
			}

			if (build) {
				for (int x = 0; x < _ejbList.size(); x++) {
					Entity entity = (Entity)_ejbList.get(x);

					System.out.println("Building " + entity.getName());

					if (true ||
						entity.getName().equals("EmailAddress") ||
						entity.getName().equals("User")) {

						if (entity.hasColumns()) {
							_createHBM(entity);
							_createHBMUtil(entity);

							_createPersistence(entity);
							_createPersistenceUtil(entity);

							_createModelImpl(entity);
							_createExtendedModelImpl(entity);

							_createModel(entity);
							_createExtendedModel(entity);

							_createModelSoap(entity);

							_createPool(entity);

							if (entity.getPKList().size() > 1) {
								_createEJBPK(entity);
							}
						}

						if (entity.hasLocalService()) {
							_createServiceImpl(entity, _LOCAL);
							_createService(entity, _LOCAL);
							_createServiceEJB(entity, _LOCAL);
							_createServiceEJBImpl(entity, _LOCAL);
							_createServiceHome(entity, _LOCAL);
							_createServiceFactory(entity, _LOCAL);
							_createServiceUtil(entity, _LOCAL);
						}

						if (entity.hasRemoteService()) {
							_createServiceImpl(entity, _REMOTE);
							_createService(entity, _REMOTE);
							_createServiceEJB(entity, _REMOTE);
							_createServiceEJBImpl(entity, _REMOTE);
							_createServiceHome(entity, _REMOTE);
							_createServiceFactory(entity, _REMOTE);
							_createServiceUtil(entity, _REMOTE);

							_createServiceHttp(entity);
							_createServiceJSON(entity);

							if (entity.hasColumns()) {
								_createServiceJSONSerializer(entity);
							}

							_createServiceSoap(entity);
						}
					}
				}

				_createEJBXML();
				_createHBMXML();
				_createJSONJS();
				_createModelHintsXML();
				_createSpringXML(true);
				_createSpringXML(false);

				_createSQLIndexes();
				_createSQLTables();
				_createSQLSequences();

				_createExceptions(exceptionList);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Entity getEntity(String name) throws IOException {
		int pos = name.lastIndexOf(".");

		if (pos == -1) {
			pos = _ejbList.indexOf(new Entity(name));

			return (Entity)_ejbList.get(pos);
		}
		else {
			String refPackage = name.substring(0, pos);
			String refPackageDir = StringUtil.replace(refPackage, ".", "/");
			String refEntity = name.substring(pos + 1, name.length());
			String refFileName = "src/" + refPackageDir + "/service.xml";

			File refFile = new File(refFileName);

			boolean useTempFile = false;

			if (!refFile.exists()) {
				refFileName = Time.getTimestamp();
				refFile = new File(refFileName);

				ClassLoader classLoader = getClass().getClassLoader();

				FileUtil.write(
					refFileName,
					StringUtil.read(
						classLoader, refPackageDir + "/service.xml"));

				useTempFile = true;
			}

			ServiceBuilder serviceBuilder = new ServiceBuilder(
				refFileName, _hbmFileName, _modelHintsFileName,
				_springEntFileName, _springProFileName,
				_beanLocatorUtilClassName, _serviceDir, _jsonFileName, false);

			Entity entity = serviceBuilder.getEntity(refEntity);

			if (useTempFile) {
				refFile.deleteOnExit();
			}

			return entity;
		}
	}

	private void _appendNullLogic(EntityColumn col, StringBuffer sb) {
		sb.append("if (" + col.getName() + " == null) {");

		if (col.getComparator().equals("=")) {
			sb.append("query.append(\"" + col.getDBName() + " IS NULL\");");
		}
		else if (col.getComparator().equals("<>") || col.getComparator().equals("!=")) {
			sb.append("query.append(\"" + col.getDBName() + " IS NOT NULL\");");
		}
		else {
			sb.append("query.append(\"" + col.getDBName() + " " + col.getComparator() + " null\");");
		}

		sb.append("} else {");
	}

	private void _createEJBPK(Entity entity) throws IOException {
		List pkList = entity.getPKList();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service.persistence;");

		// Imports

		sb.append("import com.liferay.portal.kernel.util.StringPool;");
		sb.append("import com.liferay.util.DateUtil;");
		sb.append("import java.io.Serializable;");
		sb.append("import java.util.Date;");

		// Class declaration

		sb.append("public class " + entity.getPKClassName() + " implements Comparable, Serializable {");

		// Fields

		for (int i = 0; i < pkList.size(); i++) {
			EntityColumn col = (EntityColumn)pkList.get(i);

			sb.append("public " + col.getType() + " " + col.getName() + ";");
		}

		// Default constructor

		sb.append("public " + entity.getPKClassName() + "() {}");

		// Primary key constructor

		sb.append("public " + entity.getPKClassName() + "(");

		for (int i = 0; i < pkList.size(); i++) {
			EntityColumn col = (EntityColumn)pkList.get(i);

			sb.append(col.getType() + " " + col.getName());

			if ((i + 1) != pkList.size()) {
				sb.append(", ");
			}
		}

		sb.append(") {");

		for (int i = 0; i < pkList.size(); i++) {
			EntityColumn col = (EntityColumn)pkList.get(i);

			sb.append("this." + col.getName() + " = " + col.getName() + ";");
		}

		sb.append("}");

		// Getter and setter methods

		for (int i = 0; i < pkList.size(); i++) {
			EntityColumn col = (EntityColumn)pkList.get(i);

			if (!col.isCollection()) {
				sb.append("public " + col.getType() + " get" + col.getMethodName() + "() {");
				sb.append("return " + col.getName() + ";");
				sb.append("}");

				sb.append("public void set" + col.getMethodName() + "(" + col.getType() + " " + col.getName() + ") {");
				sb.append("this." + col.getName() + " = " + col.getName() + ";");
				sb.append("}");
			}
		}

		// Compare to method

		sb.append("public int compareTo(Object obj) {");
		sb.append("if (obj == null) {");
		sb.append("return -1;");
		sb.append("}");
		sb.append(entity.getPKClassName() + " pk = (" + entity.getPKClassName() + ")obj;");
		sb.append("int value = 0;");

		for (int i = 0; i < pkList.size(); i++) {
			EntityColumn col = (EntityColumn)pkList.get(i);

			String colType = col.getType();

			if (!col.isPrimitiveType()) {
				if (colType.equals("Date")) {
					sb.append("value = DateUtil.compareTo(" + col.getName() + ", pk." + col.getName() + ");");
				}
				else {
					sb.append("value = " + col.getName() + ".compareTo(pk." + col.getName() + ");");
				}
			}
			else {
				if (colType.equals("boolean")) {
					sb.append("if (!" + col.getName() + " && pk." + col.getName() + ") {");
					sb.append("value = -1;");
					sb.append("}");
					sb.append("else if (" + col.getName() + " && !pk." + col.getName() + ") {");
					sb.append("value = 1;");
					sb.append("}");
					sb.append("else {");
					sb.append("value = 0;");
					sb.append("}");
				}
				else {
					sb.append("if (" + col.getName() + " < pk." + col.getName() + ") {");
					sb.append("value = -1;");
					sb.append("}");
					sb.append("else if (" + col.getName() + " > pk." + col.getName() + ") {");
					sb.append("value = 1;");
					sb.append("}");
					sb.append("else {");
					sb.append("value = 0;");
					sb.append("}");
				}
			}

			sb.append("if (value != 0) {");
			sb.append("return value;");
			sb.append("}");
		}

		sb.append("return 0;");
		sb.append("}");

		// Equals method

		sb.append("public boolean equals(Object obj) {");
		sb.append("if (obj == null) {");
		sb.append("return false;");
		sb.append("}");
		sb.append(entity.getPKClassName() + " pk = null;");
		sb.append("try {");
		sb.append("pk = (" + entity.getPKClassName() + ")obj;");
		sb.append("}");
		sb.append("catch (ClassCastException cce) {");
		sb.append("return false;");
		sb.append("}");
		sb.append("if (");

		for (int i = 0; i < pkList.size(); i++) {
			EntityColumn col = (EntityColumn)pkList.get(i);

			if (!col.isPrimitiveType()) {
				sb.append("(" + col.getName() + ".equals(pk." + col.getName() + "))");
			}
			else {
				sb.append("(" + col.getName() + " == pk." + col.getName() + ")");
			}

			if ((i + 1) != pkList.size()) {
				sb.append(" && ");
			}
		}

		sb.append(") {");
		sb.append("return true;");
		sb.append("} else {");
		sb.append("return false;");
		sb.append("}");
		sb.append("}");

		// Hash code method

		sb.append("public int hashCode() {");
		sb.append("return (");

		for (int i = 0; i < pkList.size(); i++) {
			EntityColumn col = (EntityColumn)pkList.get(i);

			if (i != 0) {
				sb.append(" + ");
			}

			if (!col.isPrimitiveType() && !col.getType().equals("String")) {
				sb.append(col.getName() + ".toString()");
			}
			else {
				sb.append(col.getName());
			}
		}

		sb.append(").hashCode();");
		sb.append("}");

		// To string method

		sb.append("public String toString() {");
		sb.append("StringBuffer sb = new StringBuffer();");
		sb.append("sb.append(StringPool.OPEN_CURLY_BRACE);");

		for (int i = 0; i < pkList.size(); i++) {
			EntityColumn col = (EntityColumn)pkList.get(i);

			sb.append("sb.append(\"" + col.getName() + "\");");
			sb.append("sb.append(StringPool.EQUAL);");
			sb.append("sb.append(" + col.getName() + ");");

			if ((i + 1) != pkList.size()) {
				sb.append("sb.append(StringPool.COMMA);");
				sb.append("sb.append(StringPool.SPACE);");
			}
		}

		sb.append("sb.append(StringPool.CLOSE_CURLY_BRACE);");
		sb.append("return sb.toString();");
		sb.append("}");

		// Class close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_serviceOutputPath + "/service/persistence/" + entity.getPKClassName() + ".java");

		writeFile(ejbFile, sb.toString());

		if (Validator.isNotNull(_serviceDir)) {
			ejbFile = new File(_outputPath + "/service/persistence/" + entity.getPKClassName() + ".java");

			if (ejbFile.exists()) {
				System.out.println("Relocating " + ejbFile);

				ejbFile.delete();
			}
		}
	}

	private void _createEJBXML() throws IOException {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < _ejbList.size(); i++) {
			Entity entity = (Entity)_ejbList.get(i);

			List referenceList = entity.getReferenceList();

			if (entity.hasLocalService()) {
				_createEJBXMLSession(entity, referenceList, sb, _LOCAL);
			}

			if (entity.hasRemoteService()) {
				_createEJBXMLSession(entity, referenceList, sb, _REMOTE);
			}
		}

		File xmlFile = new File("classes/META-INF/ejb-jar.xml");

		if (!xmlFile.exists()) {
			String content =
				"<?xml version=\"1.0\"?>\n" +
				"<!DOCTYPE ejb-jar PUBLIC \"-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN\" \"http://java.sun.com/dtd/ejb-jar_2_0.dtd\">\n" +
				"\n" +
				"<ejb-jar>\n" +
				"\t<enterprise-beans>\n" +
				"\t</enterprise-beans>\n" +
				"</ejb-jar>";

			FileUtil.write(xmlFile, content);
		}

		String oldContent = FileUtil.read(xmlFile);
		String newContent = new String(oldContent);

		int x = oldContent.indexOf("<session>");
		int y = oldContent.lastIndexOf("</session>");

		if (x == -1) {
			x = newContent.indexOf("<enterprise-beans/>");
			if (x != -1) {
				newContent = StringUtil.replace(
					newContent, "<enterprise-beans/>", "<enterprise-beans />");
			}

			x = newContent.indexOf("<enterprise-beans />");
			if (x != -1) {
				newContent = StringUtil.replace(
					newContent, "<enterprise-beans />",
					"<enterprise-beans>\n\t</enterprise-beans>");
			}

			x = newContent.indexOf("</enterprise-beans>") - 1;
			newContent =
				newContent.substring(0, x) + sb.toString() +
				newContent.substring(x, newContent.length());
		}
		else {
			int firstSession = newContent.indexOf(
				"<ejb-name>" + StringUtil.replace(
					_packagePath + ".service.ejb.", ".", "_"),
				x);
			int lastSession = newContent.lastIndexOf(
				"<ejb-name>" + StringUtil.replace(
					_packagePath + ".service.ejb.", ".", "_"),
				y);

			if (firstSession == -1 || firstSession > y) {
				x = newContent.indexOf("</enterprise-beans>") - 1;
				newContent =
					newContent.substring(0, x) + sb.toString() +
					newContent.substring(x, newContent.length());
			}
			else {
				firstSession = newContent.lastIndexOf(
					"<session>", firstSession) - 2;
				lastSession = newContent.indexOf(
					"</session>", lastSession) + 11;

				newContent =
					newContent.substring(0, firstSession) + sb.toString() +
					newContent.substring(lastSession, newContent.length());
			}
		}

		if (!oldContent.equals(newContent)) {
			FileUtil.write(xmlFile, newContent);
		}
	}

	private void _createEJBXMLSession(Entity entity, List referenceList, StringBuffer sb, int sessionType) throws IOException {
		sb.append("\t\t<session>\n");
		sb.append("\t\t\t<display-name>" + entity.getName() + _getSessionTypeName(sessionType) + "ServiceEJB</display-name>\n");
		sb.append("\t\t\t<ejb-name>" + StringUtil.replace(_packagePath + ".service.ejb.", ".", "_") + entity.getName() + _getSessionTypeName(sessionType) + "ServiceEJB</ejb-name>\n");
		sb.append("\t\t\t<" + (sessionType == _LOCAL ? "local-" : "") + "home>" + _packagePath + ".service.ejb." + entity.getName() + _getSessionTypeName(sessionType) + "ServiceHome</" + (sessionType == _LOCAL ? "local-" : "") + "home>\n");
		sb.append("\t\t\t<" + (sessionType == _LOCAL ? "local" : "remote") + ">" + _packagePath + ".service.ejb." + entity.getName() + _getSessionTypeName(sessionType) + "ServiceEJB</" + (sessionType == _LOCAL ? "local" : "remote") + ">\n");
		sb.append("\t\t\t<ejb-class>" + _packagePath + ".service.ejb." + entity.getName() + _getSessionTypeName(sessionType) + "ServiceEJBImpl</ejb-class>\n");
		sb.append("\t\t\t<session-type>Stateless</session-type>\n");
		sb.append("\t\t\t<transaction-type>Bean</transaction-type>\n");

		File manualFile = new File("../portal-ejb/classes/META-INF/ejb-jar-ref.xml");

		if (!manualFile.exists()) {
			manualFile = new File("../ext-ejb/classes/META-INF/ejb-jar-ref.xml");
		}

		if (manualFile.exists()) {
			String content = FileUtil.read(manualFile);

			int x = content.indexOf("<ejb-ref>");
			int y = content.lastIndexOf("</ejb-ref>") + 11;

			if (x != -1) {
				content = content.substring(x - 1, y);
				content = StringUtil.replace(content, "\t<", "\t\t\t<");

				sb.append(content);
			}
		}

		for (int j = 0; j < referenceList.size(); j++) {
			Object reference = referenceList.get(j);

			if (reference instanceof Entity) {
				Entity entityRef = (Entity)referenceList.get(j);

				if (entityRef.hasLocalService()) {
					sb.append("\t\t\t<ejb-local-ref>\n");
					sb.append("\t\t\t\t<ejb-ref-name>ejb/liferay/" + entityRef.getName() + "LocalServiceHome</ejb-ref-name>\n");
					sb.append("\t\t\t\t<ejb-ref-type>Session</ejb-ref-type>\n");
					sb.append("\t\t\t\t<local-home>" + entityRef.getPackagePath() + ".service.ejb." + entityRef.getName() + "LocalServiceHome</local-home>\n");
					sb.append("\t\t\t\t<local>" + entityRef.getPackagePath() + ".service.ejb." + entityRef.getName() + "LocalServiceEJB</local>\n");
					sb.append("\t\t\t\t<ejb-link>" + StringUtil.replace(entityRef.getPackagePath() + ".service.ejb.", ".", "_") + entityRef.getName() + "LocalServiceEJB</ejb-link>\n");
					sb.append("\t\t\t</ejb-local-ref>\n");
				}
			}
			else if (reference instanceof String) {
				sb.append("\t\t\t" + reference + "\n");
			}
		}

		for (int j = 0; j < _ejbList.size(); j++) {
			Entity entityRef = (Entity)_ejbList.get(j);

			if (!((sessionType == _LOCAL) && entity.equals(entityRef)) && entityRef.hasLocalService()) {
				sb.append("\t\t\t<ejb-local-ref>\n");
				sb.append("\t\t\t\t<ejb-ref-name>ejb/liferay/" + entityRef.getName() + "LocalServiceHome</ejb-ref-name>\n");
				sb.append("\t\t\t\t<ejb-ref-type>Session</ejb-ref-type>\n");
				sb.append("\t\t\t\t<local-home>" + _packagePath + ".service.ejb." + entityRef.getName() + "LocalServiceHome</local-home>\n");
				sb.append("\t\t\t\t<local>" + _packagePath + ".service.ejb." + entityRef.getName() + "LocalServiceEJB</local>\n");
				sb.append("\t\t\t\t<ejb-link>" + StringUtil.replace(_packagePath + ".service.ejb.", ".", "_") + entityRef.getName() + "LocalServiceEJB</ejb-link>\n");
				sb.append("\t\t\t</ejb-local-ref>\n");
			}
		}

		manualFile = new File("../portal-ejb/classes/META-INF/ejb-jar-local-ref.xml");

		if (!manualFile.exists()) {
			manualFile = new File("../ext-ejb/classes/META-INF/ejb-jar-local-ref.xml");
		}

		if (manualFile.exists()) {
			String content = FileUtil.read(manualFile);

			int x = content.indexOf("<ejb-local-ref>");
			int y = content.lastIndexOf("</ejb-local-ref>") + 17;

			if (x != -1) {
				content = content.substring(x - 1, y);
				content = StringUtil.replace(content, "\t<", "\t\t\t<");

				sb.append(content);
			}
		}

		sb.append("\t\t\t<resource-ref>\n");
		sb.append("\t\t\t\t<res-ref-name>jdbc/LiferayPool</res-ref-name>\n");
		sb.append("\t\t\t\t<res-type>javax.sql.DataSource</res-type>\n");
		sb.append("\t\t\t\t<res-auth>Container</res-auth>\n");
		sb.append("\t\t\t\t<res-sharing-scope>Shareable</res-sharing-scope>\n");
		sb.append("\t\t\t</resource-ref>\n");
		sb.append("\t\t\t<resource-ref>\n");
		sb.append("\t\t\t\t<res-ref-name>mail/MailSession</res-ref-name>\n");
		sb.append("\t\t\t\t<res-type>javax.mail.Session</res-type>\n");
		sb.append("\t\t\t\t<res-auth>Container</res-auth>\n");
		sb.append("\t\t\t</resource-ref>\n");
		sb.append("\t\t</session>\n");
	}

	private void _createExceptions(List exceptions) throws IOException {
		String copyright = null;
		try {
			copyright = FileUtil.read("../copyright.txt");
		}
		catch (FileNotFoundException fnfe) {
		}

		for (int i = 0; i < _ejbList.size(); i++) {
			Entity entity = (Entity)_ejbList.get(i);

			if (entity.hasColumns()) {
				exceptions.add(_getNoSuchEntityException(entity));
			}
		}

		for (int i = 0; i < exceptions.size(); i++) {
			String exception = (String)exceptions.get(i);

			StringBuffer sb = new StringBuffer();

			if (Validator.isNotNull(copyright)) {
				sb.append(copyright + "\n");
				sb.append("\n");
			}

			sb.append("package " + _packagePath + ";\n");
			sb.append("\n");
			sb.append("import com.liferay.portal.PortalException;\n");
			sb.append("\n");

			if (Validator.isNotNull(copyright)) {
				sb.append("/**\n");
				sb.append(" * <a href=\"" + exception + "Exception.java.html\"><b><i>View Source</i></b></a>\n");
				sb.append(" *\n");
				sb.append(" * @author Brian Wing Shun Chan\n");
				sb.append(" *\n");
				sb.append(" */\n");
			}

			sb.append("public class " + exception + "Exception extends PortalException {\n");
			sb.append("\n");
			sb.append("\tpublic " + exception + "Exception() {\n");
			sb.append("\t\tsuper();\n");
			sb.append("\t}\n");
			sb.append("\n");
			sb.append("\tpublic " + exception + "Exception(String msg) {\n");
			sb.append("\t\tsuper(msg);\n");
			sb.append("\t}\n");
			sb.append("\n");
			sb.append("\tpublic " + exception + "Exception(String msg, Throwable cause) {\n");
			sb.append("\t\tsuper(msg, cause);\n");
			sb.append("\t}\n");
			sb.append("\n");
			sb.append("\tpublic " + exception + "Exception(Throwable cause) {\n");
			sb.append("\t\tsuper(cause);\n");
			sb.append("\t}\n");
			sb.append("\n");
			sb.append("}");

			File exceptionFile = new File(_serviceOutputPath + "/" + exception + "Exception.java");

			if (!exceptionFile.exists()) {
				FileUtil.write(exceptionFile, sb.toString());
			}

			if (Validator.isNotNull(_serviceDir)) {
				exceptionFile = new File(_outputPath + "/" + exception + "Exception.java");

				if (exceptionFile.exists()) {
					System.out.println("Relocating " + exceptionFile);

					exceptionFile.delete();
				}
			}
		}
	}

	private void _createExtendedModel(Entity entity) throws IOException {
		JavaClass javaClass = _getJavaClass(_outputPath + "/model/impl/" + entity.getName() + "Impl.java");

		JavaMethod[] methods = javaClass.getMethods();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".model;");

		// Interface declaration

		sb.append("public interface " + entity.getName() + " extends " + entity.getName() + "Model {");

		// Methods

		for (int i = 0; i < methods.length; i++) {
			JavaMethod javaMethod = methods[i];

			String methodName = javaMethod.getName();

			if (!javaMethod.isConstructor() && !javaMethod.isStatic() && javaMethod.isPublic()) {
				sb.append("public " + javaMethod.getReturns().getValue() + _getDimensions(javaMethod.getReturns()) + " " + methodName + "(");

				JavaParameter[] parameters = javaMethod.getParameters();

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					sb.append(javaParameter.getType().getValue() + _getDimensions(javaParameter.getType()) + " " + javaParameter.getName());

					if ((j + 1) != parameters.length) {
						sb.append(", ");
					}
				}

				sb.append(")");

				Type[] thrownExceptions = javaMethod.getExceptions();

				Set newExceptions = new LinkedHashSet();

				for (int j = 0; j < thrownExceptions.length; j++) {
					Type thrownException = thrownExceptions[j];

					newExceptions.add(thrownException.getValue());
				}

				if (newExceptions.size() > 0) {
					sb.append(" throws ");

					Iterator itr = newExceptions.iterator();

					while (itr.hasNext()) {
						sb.append(itr.next());

						if (itr.hasNext()) {
							sb.append(", ");
						}
					}
				}

				sb.append(";");
			}
		}

		// Interface close brace

		sb.append("}");

		// Write file

		File modelFile = new File(_serviceOutputPath + "/model/" + entity.getName() + ".java");

		writeFile(modelFile, sb.toString());

		if (Validator.isNotNull(_serviceDir)) {
			modelFile = new File(_outputPath + "/model/" + entity.getName() + ".java");

			if (modelFile.exists()) {
				System.out.println("Relocating " + modelFile);

				modelFile.delete();
			}
		}
	}

	private void _createExtendedModelImpl(Entity entity) throws IOException {
		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".model.impl;");

		// Imports

		sb.append("import " + _packagePath + ".model." + entity.getName() + ";");

		// Class declaration

		sb.append("public class " + entity.getName() + "Impl extends " + entity.getName() + "ModelImpl implements " + entity.getName() + " {");

		// Empty constructor

		sb.append("public " + entity.getName() + "Impl() {");
		sb.append("}");

		// Class close brace

		sb.append("}");

		// Write file

		File modelFile = new File(_outputPath + "/model/impl/" + entity.getName() + "Impl.java");

		if (!modelFile.exists()) {
			writeFile(modelFile, sb.toString());
		}
	}

	private void _createHBM(Entity entity) throws IOException {
		File ejbFile = new File(_outputPath + "/service/persistence/" + entity.getName() + "HBM.java");

		if (ejbFile.exists()) {
			System.out.println("Removing deprecated " + ejbFile);

			ejbFile.delete();
		}
	}

	private void _createHBMUtil(Entity entity) throws IOException {
		File ejbFile = new File(_outputPath + "/service/persistence/" + entity.getName() + "HBMUtil.java");

		if (ejbFile.exists()) {
			System.out.println("Removing deprecated " + ejbFile);

			ejbFile.delete();
		}
	}

	private void _createHBMXML() throws IOException {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < _ejbList.size(); i++) {
			Entity entity = (Entity)_ejbList.get(i);

			List pkList = entity.getPKList();
			List columnList = entity.getColumnList();

			if (entity.hasColumns()) {
				sb.append("\t<class name=\"" + _packagePath + ".model.impl." + entity.getName() + "Impl\" table=\"" + entity.getTable() + "\">\n");
				sb.append("\t\t<cache usage=\"read-write\" />\n");

				if (entity.hasCompoundPK()) {
					sb.append("\t\t<composite-id name=\"primaryKey\" class=\"" + _packagePath + ".service.persistence." + entity.getName() + "PK\">\n");

					for (int j = 0; j < pkList.size(); j++) {
						EntityColumn col = (EntityColumn)pkList.get(j);

						sb.append("\t\t\t<key-property name=\"" + col.getName() + "\" ");

						if (!col.getName().equals(col.getDBName())) {
							sb.append("column=\"" + col.getDBName() + "\" />\n");
						}
						else {
							sb.append("/>\n");
						}
					}

					sb.append("\t\t</composite-id>\n");
				}
				else {
					EntityColumn col = (EntityColumn)pkList.get(0);

					sb.append("\t\t<id name=\"" + col.getName() + "\" ");

					if (!col.getName().equals(col.getDBName())) {
						sb.append("column=\"" + col.getDBName() + "\" ");
					}

					sb.append("type=\"");

					if (!entity.hasPrimitivePK()) {
						sb.append("java.lang.");
					}

					sb.append(col.getType() + "\">\n");

					String colIdType = col.getIdType();

					if (Validator.isNull(colIdType)) {
						sb.append("\t\t\t<generator class=\"assigned\" />\n");
					}
					else if (colIdType.equals("class")) {
						sb.append("\t\t\t<generator class=\"" + col.getIdParam() + "\" />\n");
					}
					else if (colIdType.equals("sequence")) {
						sb.append("\t\t\t<generator class=\"sequence\">\n");
						sb.append("\t\t\t\t<param name=\"sequence\">" + col.getIdParam() + "</param>\n");
						sb.append("\t\t\t</generator>\n");
					}
					else {
						sb.append("\t\t\t<generator class=\"" + colIdType + "\" />\n");
					}

					sb.append("\t\t</id>\n");
				}

				for (int j = 0; j < columnList.size(); j++) {
					EntityColumn col = (EntityColumn)columnList.get(j);

					String colType = col.getType();

					if (!col.isPrimary() && !col.isCollection() && col.getEJBName() == null) {
						sb.append("\t\t<property name=\"" + col.getName() + "\" ");

						if (!col.getName().equals(col.getDBName())) {
							sb.append("column=\"" + col.getDBName() + "\" ");
						}

						if (col.isPrimitiveType() || colType.equals("String")) {
							sb.append("type=\"com.liferay.util.dao.hibernate.");
							sb.append(_getPrimitiveObj(colType));
							sb.append("Type\" ");
						}

						sb.append("/>\n");
					}
				}

				sb.append("\t</class>\n");
			}
		}

		File xmlFile = new File(_hbmFileName);

		if (!xmlFile.exists()) {
			String content =
				"<?xml version=\"1.0\"?>\n" +
				"<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\" \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">\n" +
				"\n" +
				"<hibernate-mapping default-lazy=\"false\" auto-import=\"false\">\n" +
				"</hibernate-mapping>";

			FileUtil.write(xmlFile, content);
		}

		String oldContent = FileUtil.read(xmlFile);
		String newContent = _fixHBMXML(oldContent);

		int firstClass = newContent.indexOf(
			"<class name=\"" + _packagePath + ".model.impl.");
		int lastClass = newContent.lastIndexOf(
			"<class name=\"" + _packagePath + ".model.impl.");

		if (firstClass == -1) {
			int x = newContent.indexOf("</hibernate-mapping>");

			newContent =
				newContent.substring(0, x) + sb.toString() +
				newContent.substring(x, newContent.length());
		}
		else {
			firstClass = newContent.lastIndexOf(
				"<class", firstClass) - 1;
			lastClass = newContent.indexOf(
				"</class>", lastClass) + 9;

			newContent =
				newContent.substring(0, firstClass) + sb.toString() +
				newContent.substring(lastClass, newContent.length());
		}

		if (!oldContent.equals(newContent)) {
			FileUtil.write(xmlFile, newContent);
		}
	}

	private void _createJSONJS() throws IOException {
		StringBuffer sb = new StringBuffer();

		if (_ejbList.size() > 0) {
			sb.append("Liferay.Service." + _portletShortName + " = {\n");
			sb.append("\tservicePackage: \"" + _packagePath + ".service.http.\"\n");
			sb.append("};\n\n");
		}

		for (int i = 0; i < _ejbList.size(); i++) {
			Entity entity = (Entity)_ejbList.get(i);

			if (entity.hasRemoteService()) {
				JavaClass javaClass = _getJavaClass(_outputPath + "/service/http/" + entity.getName() + "ServiceJSON.java");

				JavaMethod[] methods = javaClass.getMethods();

				Set jsonMethods = new LinkedHashSet();

				for (int j = 0; j < methods.length; j++) {
					JavaMethod javaMethod = methods[j];

					String methodName = javaMethod.getName();

					if (javaMethod.isPublic()) {
						jsonMethods.add(methodName);
					}
				}

				if (jsonMethods.size() > 0) {
					sb.append("Liferay.Service." + _portletShortName + "." + entity.getName() + " = {\n");
					sb.append("\tserviceClassName: Liferay.Service." + _portletShortName + ".servicePackage + \"" + entity.getName() + "\" + Liferay.Service.classNameSuffix,\n\n");

					Iterator itr = jsonMethods.iterator();

					while (itr.hasNext()) {
						String methodName = (String)itr.next();

						sb.append("\t" + methodName + ": function(params, callback) {\n");
						sb.append("\t\tparams.serviceParameters = Liferay.Service.getParameters(params);\n");
						sb.append("\t\tparams.serviceClassName = this.serviceClassName;\n");
						sb.append("\t\tparams.serviceMethodName = \"" + methodName + "\";\n\n");
						sb.append("\t\t_$J.getJSON(Liferay.Service.url, params, callback);\n");
						sb.append("\t}");

						if (itr.hasNext()) {
							sb.append(",\n");
						}

						sb.append("\n");
					}

					sb.append("};\n\n");
				}
			}
		}

		File xmlFile = new File(_jsonFileName);

		if (!xmlFile.exists()) {
			String content = "";

			FileUtil.write(xmlFile, content);
		}

		String oldContent = FileUtil.read(xmlFile);
		String newContent = new String(oldContent);

		int oldBegin = oldContent.indexOf(
			"Liferay.Service." + _portletShortName);

		int oldEnd = oldContent.lastIndexOf(
			"Liferay.Service." + _portletShortName);
		oldEnd = oldContent.indexOf("};", oldEnd);

		int newBegin = newContent.indexOf(
			"Liferay.Service." + _portletShortName);

		int newEnd = newContent.lastIndexOf(
			"Liferay.Service." + _portletShortName);
		newEnd = newContent.indexOf("};", newEnd);

		if (newBegin == -1) {
			newContent = oldContent + "\n\n" + sb.toString().trim();
		}
		else {
			newContent =
				newContent.substring(0, oldBegin) + sb.toString().trim() +
				newContent.substring(oldEnd + 2, newContent.length());
		}

		if (!oldContent.equals(newContent)) {
			FileUtil.write(xmlFile, newContent);
		}
	}

	private void _createModel(Entity entity) throws IOException {
		List regularColList = entity.getRegularColList();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".model;");

		// Imports

		if (entity.hasCompoundPK()) {
			sb.append("import " + _packagePath + ".service.persistence." + entity.getName() + "PK;");
		}

		sb.append("import com.liferay.portal.model.BaseModel;");
		sb.append("import java.util.Date;");

		// Interface declaration

		sb.append("public interface " + entity.getName() + "Model extends BaseModel {");

		// Primary key accessor

		sb.append("public " + entity.getPKClassName() + " getPrimaryKey();");

		sb.append("public void setPrimaryKey(" + entity.getPKClassName() + " pk);");

		// Getter and setter methods

		for (int i = 0; i < regularColList.size(); i++) {
			EntityColumn col = (EntityColumn)regularColList.get(i);

			String colType = col.getType();

			sb.append("public " + colType + " get" + col.getMethodName() + "();");

			if (colType.equals("boolean")) {
				sb.append("public " + colType + " is" + col.getMethodName() + "();");
			}

			sb.append("public void set" + col.getMethodName() + "(" + colType + " " + col.getName() + ");");
		}

		// Interface close brace

		sb.append("}");

		// Write file

		File modelFile = new File(_serviceOutputPath + "/model/" + entity.getName() + "Model.java");

		writeFile(modelFile, sb.toString());

		if (Validator.isNotNull(_serviceDir)) {
			modelFile = new File(_outputPath + "/model/" + entity.getName() + "Model.java");

			if (modelFile.exists()) {
				System.out.println("Relocating " + modelFile);

				modelFile.delete();
			}
		}
	}

	private void _createModelHintsXML() throws IOException {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < _ejbList.size(); i++) {
			Entity entity = (Entity)_ejbList.get(i);

			List columnList = entity.getColumnList();

			if (entity.hasColumns()) {
				sb.append("\t<model name=\"" + _packagePath + ".model." + entity.getName() + "\">\n");

				Map defaultHints = ModelHintsUtil.getDefaultHints(_packagePath + ".model." + entity.getName());

				if ((defaultHints != null) && (defaultHints.size() > 0)) {
					sb.append("\t\t<default-hints>\n");

					Iterator itr = defaultHints.entrySet().iterator();

					while (itr.hasNext()) {
						Map.Entry entry = (Map.Entry)itr.next();

						String key = (String)entry.getKey();
						String value = (String)entry.getValue();

						sb.append("\t\t\t<hint name=\"" + key + "\">" + value + "</hint>\n");
					}

					sb.append("\t\t</default-hints>\n");
				}

				for (int j = 0; j < columnList.size(); j++) {
					EntityColumn col = (EntityColumn)columnList.get(j);

					if (!col.isCollection()) {
						sb.append("\t\t<field name=\"" + col.getName() + "\" type=\"" + col.getType() + "\"");

						Element field = ModelHintsUtil.getFieldsEl(_packagePath + ".model." + entity.getName(), col.getName());

						List hints = null;

						if (field != null) {
							hints = field.elements();
						}

						if ((hints == null) || (hints.size() == 0)) {
							sb.append(" />\n");
						}
						else {
							sb.append(">\n");

							Iterator itr = hints.iterator();

							while (itr.hasNext()) {
								Element hint = (Element)itr.next();

								if (hint.getName().equals("hint")) {
									sb.append("\t\t\t<hint name=\"" + hint.attributeValue("name") + "\">" + hint.getText() + "</hint>\n");
								}
								else {
									sb.append("\t\t\t<hint-collection name=\"" + hint.attributeValue("name") + "\" />\n");
								}
							}

							sb.append("\t\t</field>\n");
						}
					}
				}

				sb.append("\t</model>\n");
			}
		}

		File xmlFile = new File(_modelHintsFileName);

		if (!xmlFile.exists()) {
			String content =
				"<?xml version=\"1.0\"?>\n" +
				"\n" +
				"<model-hints>\n" +
				"</model-hints>";

			FileUtil.write(xmlFile, content);
		}

		String oldContent = FileUtil.read(xmlFile);
		String newContent = new String(oldContent);

		int firstModel = newContent.indexOf(
			"<model name=\"" + _packagePath + ".model.");
		int lastModel = newContent.lastIndexOf(
			"<model name=\"" + _packagePath + ".model.");

		if (firstModel == -1) {
			int x = newContent.indexOf("</model-hints>");
			newContent =
				newContent.substring(0, x) + sb.toString() +
				newContent.substring(x, newContent.length());
		}
		else {
			firstModel = newContent.lastIndexOf(
				"<model", firstModel) - 1;
			lastModel = newContent.indexOf(
				"</model>", lastModel) + 9;

			newContent =
				newContent.substring(0, firstModel) + sb.toString() +
				newContent.substring(lastModel, newContent.length());
		}

		if (!oldContent.equals(newContent)) {
			FileUtil.write(xmlFile, newContent);
		}
	}

	private void _createModelImpl(Entity entity) throws IOException {
		List pkList = entity.getPKList();
		List regularColList = entity.getRegularColList();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".model.impl;");

		// Imports

		if (entity.hasCompoundPK()) {
			sb.append("import " + _packagePath + ".service.persistence." + entity.getName() + "PK;");
		}

		sb.append("import com.liferay.portal.model.impl.BaseModelImpl;");
		sb.append("import com.liferay.portal.util.PropsUtil;");
		sb.append("import com.liferay.util.DateUtil;");
		sb.append("import com.liferay.util.GetterUtil;");
		sb.append("import com.liferay.util.XSSUtil;");
		sb.append("import java.sql.Types;");
		sb.append("import java.util.Date;");

		// Class declaration

		sb.append("public class " + entity.getName() + "ModelImpl extends BaseModelImpl {");

		// Fields

		sb.append("public static String TABLE_NAME = \"" + entity.getTable() + "\";");

		sb.append("public static Object[][] TABLE_COLUMNS = {");

		for (int i = 0; i < regularColList.size(); i++) {
			EntityColumn col = (EntityColumn)regularColList.get(i);

			String sqlType = _getSqlType(_packagePath + ".model." + entity.getName(), col.getName(), col.getType());

			sb.append("{\"" + col.getDBName() + "\", new Integer(Types." + sqlType + ")}");

			if ((i + 1) < regularColList.size()) {
				sb.append(",");
			}
		}

		sb.append("};");

		sb.append("public static boolean XSS_ALLOW_BY_MODEL = GetterUtil.getBoolean(PropsUtil.get(\"xss.allow." + _packagePath + ".model." + entity.getName() + "\"), XSS_ALLOW);");

		for (int i = 0; i < regularColList.size(); i++) {
			EntityColumn col = (EntityColumn)regularColList.get(i);

			if (col.getType().equals("String")) {
				sb.append("public static boolean XSS_ALLOW_" + col.getName().toUpperCase() + " = GetterUtil.getBoolean(PropsUtil.get(\"xss.allow." + _packagePath + ".model." + entity.getName() + "." + col.getName() + "\"), XSS_ALLOW_BY_MODEL);");
			}
		}

		sb.append("public static long LOCK_EXPIRATION_TIME = GetterUtil.getLong(PropsUtil.get(\"lock.expiration.time." + _packagePath + ".model." + entity.getName() + "Model\"));");

		// Empty constructor

		sb.append("public " + entity.getName() + "ModelImpl() {");
		sb.append("}");

		// Primary key accessor

		sb.append("public " + entity.getPKClassName() + " getPrimaryKey() {");

		if (entity.hasCompoundPK()) {
			sb.append("return new " + entity.getPKClassName() + "(");

			for (int i = 0; i < pkList.size(); i++) {
				EntityColumn col = (EntityColumn)pkList.get(i);

				sb.append("_" + col.getName());

				if ((i + 1) != (pkList.size())) {
					sb.append(", ");
				}
			}

			sb.append(");");
		}
		else {
			EntityColumn col = (EntityColumn)pkList.get(0);

			sb.append("return _" + col.getName() + ";");
		}

		sb.append("}");

		sb.append("public void setPrimaryKey(" + entity.getPKClassName() + " pk) {");

		if (entity.hasCompoundPK()) {
			for (int i = 0; i < pkList.size(); i++) {
				EntityColumn col = (EntityColumn)pkList.get(i);

				sb.append("set" + col.getMethodName() + "(pk." + col.getName() + ");");
			}
		}
		else {
			EntityColumn col = (EntityColumn)pkList.get(0);

			sb.append("set" + col.getMethodName() + "(pk);");
		}

		sb.append("}");

		// Getter and setter methods

		for (int i = 0; i < regularColList.size(); i++) {
			EntityColumn col = (EntityColumn)regularColList.get(i);

			String colType = col.getType();

			sb.append("public " + colType + " get" + col.getMethodName() + "() {");

			if (colType.equals("String") && col.isConvertNull()) {
				sb.append("return GetterUtil.getString(_" + col.getName() + ");");
			}
			else {
				sb.append("return _" + col.getName() + ";");
			}

			sb.append("}");

			if (colType.equals("boolean")) {
				sb.append("public " + colType + " is" + col.getMethodName() + "() {");
				sb.append("return _" + col.getName() + ";");
				sb.append("}");
			}

			sb.append("public void set" + col.getMethodName() + "(" + colType + " " + col.getName() + ") {");
			sb.append("if (");

			if (!col.isPrimitiveType()) {
				sb.append("(" + col.getName() + " == null && _" + col.getName() + " != null) ||");
				sb.append("(" + col.getName() + " != null && _" + col.getName() + " == null) ||");
				sb.append("(" + col.getName() + " != null && _" + col.getName() + " != null && !" + col.getName() + ".equals(_" + col.getName() + "))");
			}
			else {
				sb.append(col.getName() + " != _" + col.getName());
			}

			sb.append(") {");

			if (colType.equals("String")) {
				sb.append("if (!XSS_ALLOW_" + col.getName().toUpperCase() + ") {");
				sb.append(col.getName() + " = XSSUtil.strip(" + col.getName() + ");");
				sb.append("}");
			}

			sb.append("_" + col.getName() + " = " + col.getName() + ";");
			sb.append("}");
			sb.append("}");
		}

		// Clone method

		sb.append("public Object clone() {");
		sb.append(entity.getName() + "Impl clone = new " + entity.getName() + "Impl();");

		for (int i = 0; i < regularColList.size(); i++) {
			EntityColumn col = (EntityColumn)regularColList.get(i);

			sb.append("clone.set" + col.getMethodName() + "(");

			if (col.getEJBName() == null) {
				sb.append("get" + col.getMethodName() + "()");
			}
			else {
				sb.append("(" + col.getEJBName() + ")get" + col.getMethodName() + "().clone()");
			}

			sb.append(");");
		}

		sb.append("return clone;");
		sb.append("}");

		// Compare to method

		sb.append("public int compareTo(Object obj) {");
		sb.append("if (obj == null) {");
		sb.append("return -1;");
		sb.append("}");
		sb.append(entity.getName() + "Impl " + entity.getVarName() + " = (" + entity.getName() + "Impl)obj;");

		if (entity.isOrdered()) {
			EntityOrder order = entity.getOrder();

			List orderList = order.getColumns();

			sb.append("int value = 0;");

			for (int i = 0; i < orderList.size(); i++) {
				EntityColumn col = (EntityColumn)orderList.get(i);

				String colType = col.getType();

				if (!col.isPrimitiveType()) {
					if (colType.equals("Date")) {
						sb.append("value = DateUtil.compareTo(get" + col.getMethodName() + "(), " + entity.getVarName() + ".get" + col.getMethodName() + "());");
					}
					else {
						if (col.isCaseSensitive()) {
							sb.append("value = get" + col.getMethodName() + "().compareTo(" + entity.getVarName() + ".get" + col.getMethodName() + "());");
						}
						else {
							sb.append("value = get" + col.getMethodName() + "().toLowerCase().compareTo(" + entity.getVarName() + ".get" + col.getMethodName() + "().toLowerCase());");
						}
					}
				}
				else {
					String ltComparator = "<";
					String gtComparator = ">";

					if (colType.equals("boolean")) {
						ltComparator = "==";
						gtComparator = "!=";
					}

					sb.append("if (get" + col.getMethodName() + "() " + ltComparator + " " + entity.getVarName() + ".get" + col.getMethodName() + "()) {");
					sb.append("value = -1;");
					sb.append("}");
					sb.append("else if (get" + col.getMethodName() + "() " + gtComparator + " " + entity.getVarName() + ".get" + col.getMethodName() + "()) {");
					sb.append("value = 1;");
					sb.append("}");
					sb.append("else {");
					sb.append("value = 0;");
					sb.append("}");
				}

				if (!col.isOrderByAscending()) {
					sb.append("value = value * -1;");
				}

				sb.append("if (value != 0) {");
				sb.append("return value;");
				sb.append("}");
			}

			sb.append("return 0;");
		}
		else {
			sb.append(entity.getPKClassName() + " pk = " + entity.getVarName() + ".getPrimaryKey();");

			if (entity.hasPrimitivePK()) {
				sb.append("if (getPrimaryKey() < pk) {");
				sb.append("return -1;");
				sb.append("}");
				sb.append("else if (getPrimaryKey() > pk) {");
				sb.append("return 1;");
				sb.append("}");
				sb.append("else {");
				sb.append("return 0;");
				sb.append("}");
			}
			else {
				sb.append("return getPrimaryKey().compareTo(pk);");
			}
		}

		sb.append("}");

		// Equals method

		sb.append("public boolean equals(Object obj) {");
		sb.append("if (obj == null) {");
		sb.append("return false;");
		sb.append("}");
		sb.append(entity.getName() + "Impl " + entity.getVarName() + " = null;");
		sb.append("try {");
		sb.append(entity.getVarName() + " = (" + entity.getName() + "Impl)obj;");
		sb.append("}");
		sb.append("catch (ClassCastException cce) {");
		sb.append("return false;");
		sb.append("}");
		sb.append(entity.getPKClassName() + " pk = " + entity.getVarName() + ".getPrimaryKey();");

		if (entity.hasPrimitivePK()) {
			sb.append("if (getPrimaryKey() == pk) {");
		}
		else {
			sb.append("if (getPrimaryKey().equals(pk)) {");
		}

		sb.append("return true;");
		sb.append("}");
		sb.append("else {");
		sb.append("return false;");
		sb.append("}");
		sb.append("}");

		// Hash code method

		sb.append("public int hashCode() {");

		if (entity.hasPrimitivePK()) {
			sb.append("return (int)getPrimaryKey();");
		}
		else {
			sb.append("return getPrimaryKey().hashCode();");
		}

		sb.append("}");

		// Fields

		for (int i = 0; i < regularColList.size(); i++) {
			EntityColumn col = (EntityColumn)regularColList.get(i);

			sb.append("private " + col.getType() + " _" + col.getName() + ";");
		}

		// Class close brace

		sb.append("}");

		// Write file

		File modelFile = new File(_outputPath + "/model/impl/" + entity.getName() + "ModelImpl.java");

		writeFile(modelFile, sb.toString());
	}

	private void _createModelSoap(Entity entity) throws IOException {
		List pkList = entity.getPKList();
		List regularColList = entity.getRegularColList();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".model;");

		// Imports

		if (entity.hasCompoundPK()) {
			sb.append("import " + _packagePath + ".service.persistence." + entity.getName() + "PK;");
		}

		sb.append("import java.io.Serializable;");
		sb.append("import java.util.ArrayList;");
		sb.append("import java.util.Date;");
		sb.append("import java.util.List;");

		// Class declaration

		sb.append("public class " + entity.getName() + "Soap implements Serializable {");

		// Methods

		sb.append("public static " + entity.getName() + "Soap toSoapModel(" + entity.getName() + " model) {");
		sb.append(entity.getName() + "Soap soapModel = new " + entity.getName() + "Soap();");

		for (int i = 0; i < regularColList.size(); i++) {
			EntityColumn col = (EntityColumn)regularColList.get(i);

			sb.append("soapModel.set" + col.getMethodName() + "(model.get" + col.getMethodName() + "());");
		}

		sb.append("return soapModel;");
		sb.append("}");

		sb.append("public static " + entity.getName() + "Soap[] toSoapModels(List models) {");
		sb.append("List soapModels = new ArrayList(models.size());");
		sb.append("for (int i = 0; i < models.size(); i++) {");
		sb.append(entity.getName() + " model = (" + entity.getName() + ")models.get(i);");
		sb.append("soapModels.add(toSoapModel(model));");
		sb.append("}");
		sb.append("return (" + entity.getName() + "Soap[])soapModels.toArray(new " + entity.getName() + "Soap[0]);");
		sb.append("}");

		// Empty constructor

		sb.append("public " + entity.getName() + "Soap() {");
		sb.append("}");

		// Primary key accessor

		sb.append("public " + entity.getPKClassName() + " getPrimaryKey() {");

		if (entity.hasCompoundPK()) {
			sb.append("return new " + entity.getPKClassName() + "(");

			for (int i = 0; i < pkList.size(); i++) {
				EntityColumn col = (EntityColumn)pkList.get(i);

				sb.append("_" + col.getName());

				if ((i + 1) != (pkList.size())) {
					sb.append(", ");
				}
			}

			sb.append(");");
		}
		else {
			EntityColumn col = (EntityColumn)pkList.get(0);

			sb.append("return _" + col.getName() + ";");
		}

		sb.append("}");

		sb.append("public void setPrimaryKey(" + entity.getPKClassName() + " pk) {");

		if (entity.hasCompoundPK()) {
			for (int i = 0; i < pkList.size(); i++) {
				EntityColumn col = (EntityColumn)pkList.get(i);

				sb.append("set" + col.getMethodName() + "(pk." + col.getName() + ");");
			}
		}
		else {
			EntityColumn col = (EntityColumn)pkList.get(0);

			sb.append("set" + col.getMethodName() + "(pk);");
		}

		sb.append("}");

		// Getter and setter methods

		for (int i = 0; i < regularColList.size(); i++) {
			EntityColumn col = (EntityColumn)regularColList.get(i);

			String colType = col.getType();

			sb.append("public " + colType + " get" + col.getMethodName() + "() {");
			sb.append("return _" + col.getName() + ";");
			sb.append("}");

			if (colType.equals("boolean")) {
				sb.append("public " + colType + " is" + col.getMethodName() + "() {");
				sb.append("return _" + col.getName() + ";");
				sb.append("}");
			}

			sb.append("public void set" + col.getMethodName() + "(" + colType + " " + col.getName() + ") {");
			sb.append("_" + col.getName() + " = " + col.getName() + ";");
			sb.append("}");
		}

		// Fields

		for (int i = 0; i < regularColList.size(); i++) {
			EntityColumn col = (EntityColumn)regularColList.get(i);

			sb.append("private " + col.getType() + " _" + col.getName() + ";");
		}

		// Class close brace

		sb.append("}");

		// Write file

		File modelFile = new File(_serviceOutputPath + "/model/" + entity.getName() + "Soap.java");

		writeFile(modelFile, sb.toString());
	}

	private void _createPersistence(Entity entity) throws IOException {
		List columnList = entity.getColumnList();
		List finderList = entity.getFinderList();

		String pkClassName = entity.getPKClassName();
		String pkVarName = entity.getPKVarName();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service.persistence;");

		// Imports

		sb.append("import " + _packagePath + "." + _getNoSuchEntityException(entity) + "Exception;");
		sb.append("import " + _packagePath + ".model." + entity.getName() + ";");
		sb.append("import " + _packagePath + ".model.impl." + entity.getName() + "Impl;");
		sb.append("import com.liferay.portal.PortalException;");
		sb.append("import com.liferay.portal.SystemException;");
		sb.append("import com.liferay.portal.kernel.util.OrderByComparator;");
		sb.append("import com.liferay.portal.kernel.util.StringPool;");
		sb.append("import com.liferay.portal.service.persistence.BasePersistence;");
		sb.append("import com.liferay.portal.spring.hibernate.HibernateUtil;");
		sb.append("import com.liferay.util.dao.DynamicQuery;");
		sb.append("import com.liferay.util.dao.DynamicQueryInitializer;");
		sb.append("import com.liferay.util.dao.hibernate.QueryPos;");
		sb.append("import com.liferay.util.dao.hibernate.QueryUtil;");
		sb.append("import java.sql.ResultSet;");
		sb.append("import java.sql.SQLException;");
		sb.append("import java.sql.Types;");
		sb.append("import java.util.Collection;");
		sb.append("import java.util.Collections;");
		sb.append("import java.util.Date;");
		sb.append("import java.util.HashSet;");
		sb.append("import java.util.Iterator;");
		sb.append("import java.util.List;");
		sb.append("import java.util.Set;");
		sb.append("import javax.sql.DataSource;");
		sb.append("import org.apache.commons.logging.Log;");
		sb.append("import org.apache.commons.logging.LogFactory;");
		sb.append("import org.hibernate.Hibernate;");
		sb.append("import org.hibernate.HibernateException;");
		sb.append("import org.hibernate.ObjectNotFoundException;");
		sb.append("import org.hibernate.Query;");
		sb.append("import org.hibernate.Session;");
		sb.append("import org.hibernate.SQLQuery;");
		sb.append("import org.springframework.dao.DataAccessException;");
		sb.append("import org.springframework.jdbc.core.SqlParameter;");
		sb.append("import org.springframework.jdbc.object.MappingSqlQuery;");
		sb.append("import org.springframework.jdbc.object.SqlUpdate;");

		// Class declaration

		sb.append("public class " + entity.getName() + "Persistence extends BasePersistence {");

		// Create method

		sb.append("public " + entity.getName() + " create(" + entity.getPKClassName() + " " + pkVarName + ") {");
		sb.append(entity.getName() + " " + entity.getVarName() + " = new " + entity.getName() + "Impl();");
		sb.append(entity.getVarName() + ".setNew(true);");
		sb.append(entity.getVarName() + ".setPrimaryKey(" + pkVarName + ");");
		sb.append("return " + entity.getVarName() + ";");
		sb.append("}");

		// Remove method

		sb.append("public " + entity.getName() + " remove(" + pkClassName + " " + pkVarName + ") throws " + _getNoSuchEntityException(entity) + "Exception, SystemException {");
		sb.append("Session session = null;");
		sb.append("try {");
		sb.append("session = openSession();");
		sb.append(entity.getName() + " " + entity.getVarName() + " = (" + entity.getName() + ")session.get(" + entity.getName() + "Impl.class, ");

		if (entity.hasPrimitivePK()) {
			sb.append("new ");
			sb.append(_getPrimitiveObj(entity.getPKClassName()));
			sb.append("(");
		}

		sb.append(pkVarName);

		if (entity.hasPrimitivePK()) {
			sb.append(")");
		}

		sb.append(");");
		sb.append("if (" + entity.getVarName() + " == null) {");
		sb.append("if (_log.isWarnEnabled()) {");
		sb.append("_log.warn(\"No " + entity.getName() + " exists with the primary key \" + " + pkVarName + ");");
		sb.append("}");
		sb.append("throw new " + _getNoSuchEntityException(entity) + "Exception(\"No " + entity.getName() + " exists with the primary key \" + " + pkVarName + ");");
		sb.append("}");
		sb.append("return remove(" + entity.getVarName() + ");");
		sb.append("}");
		sb.append("catch (HibernateException he) {");
		sb.append("throw new SystemException(he);");
		sb.append("}");
		sb.append("finally {");
		sb.append("closeSession(session);");
		sb.append("}");
		sb.append("}");

		sb.append("public " + entity.getName() + " remove(" + entity.getName() + " " + entity.getVarName() + ") throws SystemException {");
		sb.append("Session session = null;");
		sb.append("try {");
		sb.append("session = openSession();");
		sb.append("session.delete(" + entity.getVarName() + ");");
		sb.append("session.flush();");

		for (int i = 0; i < columnList.size(); i++) {
			EntityColumn col = (EntityColumn)columnList.get(i);

			if (col.isCollection() && col.isMappingManyToMany()) {
				Entity tempEntity = getEntity(col.getEJBName());

				// clearUsers(String pk)

				sb.append("clear" + tempEntity.getNames() + ".clear(" + entity.getVarName() + ".getPrimaryKey());");
			}
		}

		sb.append("return " + entity.getVarName() + ";");
		sb.append("}");
		sb.append("catch (HibernateException he) {");
		sb.append("throw new SystemException(he);");
		sb.append("}");
		sb.append("finally {");
		sb.append("closeSession(session);");
		sb.append("}");
		sb.append("}");

		// Update method

		sb.append("public " + _packagePath + ".model." + entity.getName() + " update(" + _packagePath + ".model." + entity.getName() + " " + entity.getVarName() + ") throws SystemException {");
		sb.append("return update(" + entity.getVarName() + ", false);");
		sb.append("}");

		sb.append("public " + _packagePath + ".model." + entity.getName() + " update(" + _packagePath + ".model." + entity.getName() + " " + entity.getVarName() + ", boolean saveOrUpdate) throws SystemException {");
		sb.append("Session session = null;");
		sb.append("try {");
		sb.append("session = openSession();");
		sb.append("if (saveOrUpdate) {");
		sb.append("session.saveOrUpdate(" + entity.getVarName() + ");");
		sb.append("}");
		sb.append("else {");
		sb.append("if (" + entity.getVarName() + ".isNew()) {");
		sb.append("session.save(" + entity.getVarName() + ");");
		sb.append("}");
		sb.append("}");
		sb.append("session.flush();");
		sb.append(entity.getVarName() + ".setNew(false);");
		sb.append("return " + entity.getVarName() + ";");
		sb.append("}");
		sb.append("catch (HibernateException he) {");
		sb.append("throw new SystemException(he);");
		sb.append("}");
		sb.append("finally {");
		sb.append("closeSession(session);");
		sb.append("}");
		sb.append("}");

		// Finder methods

		sb.append("public " + entity.getName() + " findByPrimaryKey(" + pkClassName + " " + pkVarName + ") throws " + _getNoSuchEntityException(entity) + "Exception, SystemException {");
		sb.append(entity.getName() + " " + entity.getVarName() + " = fetchByPrimaryKey(" + pkVarName + ");");
		sb.append("if (" + entity.getVarName() + " == null) {");
		sb.append("if (_log.isWarnEnabled()) {");
		sb.append("_log.warn(\"No " + entity.getName() + " exists with the primary key \" + " + pkVarName + ");");
		sb.append("}");
		sb.append("throw new " + _getNoSuchEntityException(entity) + "Exception(\"No " + entity.getName() + " exists with the primary key \" + " + pkVarName + ");");
		sb.append("}");
		sb.append("return " + entity.getVarName() + ";");
		sb.append("}");

		sb.append("public " + entity.getName() + " fetchByPrimaryKey(" + pkClassName + " " + pkVarName + ") throws SystemException {");
		sb.append("Session session = null;");
		sb.append("try {");
		sb.append("session = openSession();");
		sb.append("return (" + entity.getName() + ")session.get(" + entity.getName() + "Impl.class, ");

		if (entity.hasPrimitivePK()) {
			sb.append("new ");
			sb.append(_getPrimitiveObj(entity.getPKClassName()));
			sb.append("(");
		}

		sb.append(pkVarName);

		if (entity.hasPrimitivePK()) {
			sb.append(")");
		}

		sb.append(");");
		sb.append("}");
		sb.append("catch (HibernateException he) {");
		sb.append("throw new SystemException(he);");
		sb.append("}");
		sb.append("finally {");
		sb.append("closeSession(session);");
		sb.append("}");
		sb.append("}");

		for (int i = 0; i < finderList.size(); i++) {
			EntityFinder finder = (EntityFinder)finderList.get(i);

			List finderColsList = finder.getColumns();

			if (!finder.isCollection()) {
				sb.append("public " + entity.getName() + " findBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append(col.getType() + " " + col.getName());

					if ((j + 1) != finderColsList.size()) {
						sb.append(", ");
					}
				}

				sb.append(") throws " + _getNoSuchEntityException(entity) + "Exception, SystemException {");
				sb.append(entity.getName() + " " + entity.getVarName() + " = fetchBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append(col.getName());

					if ((j + 1) != finderColsList.size()) {
						sb.append(", ");
					}
				}

				sb.append(");");
				sb.append("if (" + entity.getVarName() + " == null) {");
				sb.append("String msg = \"No " + entity.getName() + " exists with the key \";");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					if (j == 0) {
						sb.append("msg += StringPool.OPEN_CURLY_BRACE;");
					}

					sb.append("msg += \"" + col.getName() + "=\";");
					sb.append("msg += " + col.getName() + ";");

					if ((j + 1) != finderColsList.size()) {
						sb.append("msg += \", \";");
					}

					if ((j + 1) == finderColsList.size()) {
						sb.append("msg += StringPool.CLOSE_CURLY_BRACE;");
					}
				}

				sb.append("if (_log.isWarnEnabled()) {");
				sb.append("_log.warn(msg);");
				sb.append("}");
				sb.append("throw new " + _getNoSuchEntityException(entity) + "Exception(msg);");
				sb.append("}");
				sb.append("return " + entity.getVarName() + ";");
				sb.append("}");

				sb.append("public " + entity.getName() + " fetchBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append(col.getType() + " " + col.getName());

					if ((j + 1) != finderColsList.size()) {
						sb.append(", ");
					}
				}

				sb.append(") throws SystemException {");
				sb.append("Session session = null;");
				sb.append("try {");
				sb.append("session = openSession();");
				sb.append("StringBuffer query = new StringBuffer();");
				sb.append("query.append(\"FROM " + _packagePath + ".model." + entity.getName() + " WHERE \");");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					if (_requiresNullCheck(col)) {
						_appendNullLogic(col, sb);
					}

					sb.append("query.append(\"" + col.getDBName() + " " + col.getComparator() + " ?\");");

					if (_requiresNullCheck(col)) {
						sb.append("}");
					}

					if ((j + 1) != finderColsList.size()) {
						sb.append("query.append(\" AND \");");
					}
					else if (Validator.isNull(finder.getWhere())) {
						sb.append("query.append(\" \");");
					}
					else {
						sb.append("query.append(\" AND " + finder.getWhere() + " \");");
					}
				}

				EntityOrder order = entity.getOrder();

				if (order != null) {
					List orderList = order.getColumns();

					sb.append("query.append(\"ORDER BY \");");

					for (int j = 0; j < orderList.size(); j++) {
						EntityColumn col = (EntityColumn)orderList.get(j);

						sb.append("query.append(\"" + col.getDBName() + " " + (col.isOrderByAscending() ? "ASC" : "DESC") + "\")");

						if ((j + 1) != orderList.size()) {
							sb.append(".append(\", \");");
						}
						else {
							sb.append(";");
						}
					}
				}

				sb.append("Query q = session.createQuery(query.toString());");
				sb.append("q.setCacheable(true);");
				sb.append("int queryPos = 0;");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					if (_requiresNullCheck(col)) {
						sb.append("if (" + col.getName() + " != null) {");
					}

					String colType = col.getType();
					String colObjType = colType;

					if (col.isPrimitiveType()) {
						colObjType = _getPrimitiveObj(colType);
					}

					sb.append("q.set" + colObjType + "(queryPos++, " + col.getName());

					if (colType.equals("Boolean")) {
						sb.append(".booleanValue()");
					}
					else if (colType.equals("Double")) {
						sb.append(".doubleValue()");
					}
					else if (colType.equals("Float")) {
						sb.append(".floatValue()");
					}
					else if (colType.equals("Integer")) {
						sb.append(".intValue()");
					}
					else if (colType.equals("Long")) {
						sb.append(".longValue()");
					}
					else if (colType.equals("Short")) {
						sb.append(".shortValue()");
					}

					sb.append(");");

					if (_requiresNullCheck(col)) {
						sb.append("}");
					}
				}

				sb.append("List list = q.list();");
				sb.append("if (list.size() == 0) {");
				sb.append("return null;");
				sb.append("}");
				sb.append(entity.getName() + " " + entity.getVarName() + " = (" + entity.getName() + ")list.get(0);");
				sb.append("return " + entity.getVarName() + ";");
				sb.append("}");
				sb.append("catch (HibernateException he) {");
				sb.append("throw new SystemException(he);");
				sb.append("}");
				sb.append("finally {");
				sb.append("closeSession(session);");
				sb.append("}");
				sb.append("}");
			}
			else {
				sb.append("public List findBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append(col.getType() + " " + col.getName());

					if ((j + 1) != finderColsList.size()) {
						sb.append(", ");
					}
				}

				sb.append(") throws SystemException {");
				sb.append("Session session = null;");
				sb.append("try {");
				sb.append("session = openSession();");
				sb.append("StringBuffer query = new StringBuffer();");
				sb.append("query.append(\"FROM " + _packagePath + ".model." + entity.getName() + " WHERE \");");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					if (_requiresNullCheck(col)) {
						_appendNullLogic(col, sb);
					}

					sb.append("query.append(\"" + col.getDBName() + " " + col.getComparator() + " ?\");");

					if (_requiresNullCheck(col)) {
						sb.append("}");
					}

					if ((j + 1) != finderColsList.size()) {
						sb.append("query.append(\" AND \");");
					}
					else if (Validator.isNull(finder.getWhere())) {
						sb.append("query.append(\" \");");
					}
					else {
						sb.append("query.append(\" AND " + finder.getWhere() + " \");");
					}
				}

				EntityOrder order = entity.getOrder();

				if (order != null) {
					List orderList = order.getColumns();

					sb.append("query.append(\"ORDER BY \");");

					for (int j = 0; j < orderList.size(); j++) {
						EntityColumn col = (EntityColumn)orderList.get(j);

						sb.append("query.append(\"" + col.getDBName() + " " + (col.isOrderByAscending() ? "ASC" : "DESC") + "\")");

						if ((j + 1) != orderList.size()) {
							sb.append(".append(\", \");");
						}
						else {
							sb.append(";");
						}
					}
				}

				sb.append("Query q = session.createQuery(query.toString());");
				sb.append("q.setCacheable(true);");
				sb.append("int queryPos = 0;");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					if (_requiresNullCheck(col)) {
						sb.append("if (" + col.getName() + " != null) {");
					}

					String colType = col.getType();
					String colObjType = colType;

					if (col.isPrimitiveType()) {
						colObjType = _getPrimitiveObj(colType);
					}

					sb.append("q.set" + colObjType + "(queryPos++, " + col.getName());

					if (colType.equals("Boolean")) {
						sb.append(".booleanValue()");
					}
					else if (colType.equals("Double")) {
						sb.append(".doubleValue()");
					}
					else if (colType.equals("Float")) {
						sb.append(".floatValue()");
					}
					else if (colType.equals("Integer")) {
						sb.append(".intValue()");
					}
					else if (colType.equals("Long")) {
						sb.append(".longValue()");
					}
					else if (colType.equals("Short")) {
						sb.append(".shortValue()");
					}

					sb.append(");");

					if (_requiresNullCheck(col)) {
						sb.append("}");
					}
				}

				sb.append("return q.list();");
				sb.append("}");
				sb.append("catch (HibernateException he) {");
				sb.append("throw new SystemException(he);");
				sb.append("}");
				sb.append("finally {");
				sb.append("closeSession(session);");
				sb.append("}");
				sb.append("}");

				sb.append("public List findBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append(col.getType() + " " + col.getName() + ", ");
				}

				sb.append("int begin, int end) throws SystemException {");
				sb.append("return findBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append("" + col.getName() + ", ");
				}

				sb.append("begin, end, null);");
				sb.append("}");

				sb.append("public List findBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append(col.getType() + " " + col.getName() + ", ");
				}

				sb.append("int begin, int end, OrderByComparator obc) throws SystemException {");
				sb.append("Session session = null;");
				sb.append("try {");
				sb.append("session = openSession();");
				sb.append("StringBuffer query = new StringBuffer();");
				sb.append("query.append(\"FROM " + _packagePath + ".model." + entity.getName() + " WHERE \");");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					if (_requiresNullCheck(col)) {
						_appendNullLogic(col, sb);
					}

					sb.append("query.append(\"" + col.getDBName() + " " + col.getComparator() + " ?\");");

					if (_requiresNullCheck(col)) {
						sb.append("}");
					}

					if ((j + 1) != finderColsList.size()) {
						sb.append("query.append(\" AND \");");
					}
					else if (Validator.isNull(finder.getWhere())) {
						sb.append("query.append(\" \");");
					}
					else {
						sb.append("query.append(\" AND " + finder.getWhere() + " \");");
					}
				}

				sb.append("if (obc != null) {");
				sb.append("query.append(\"ORDER BY \" + obc.getOrderBy());");
				sb.append("}");

				if (order != null) {
					List orderList = order.getColumns();

					sb.append("else {");
					sb.append("query.append(\"ORDER BY \");");

					for (int j = 0; j < orderList.size(); j++) {
						EntityColumn col = (EntityColumn)orderList.get(j);

						sb.append("query.append(\"" + col.getDBName() + " " + (col.isOrderByAscending() ? "ASC" : "DESC") + "\")");

						if ((j + 1) != orderList.size()) {
							sb.append(".append(\", \");");
						}
						else {
							sb.append(";");
						}
					}

					sb.append("}");
				}

				sb.append("Query q = session.createQuery(query.toString());");
				sb.append("q.setCacheable(true);");
				sb.append("int queryPos = 0;");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					if (_requiresNullCheck(col)) {
						sb.append("if (" + col.getName() + " != null) {");
					}

					String colType = col.getType();
					String colObjType = colType;

					if (col.isPrimitiveType()) {
						colObjType = _getPrimitiveObj(colType);
					}

					sb.append("q.set" + colObjType + "(queryPos++, " + col.getName());

					if (colType.equals("Boolean")) {
						sb.append(".booleanValue()");
					}
					else if (colType.equals("Double")) {
						sb.append(".doubleValue()");
					}
					else if (colType.equals("Float")) {
						sb.append(".floatValue()");
					}
					else if (colType.equals("Integer")) {
						sb.append(".intValue()");
					}
					else if (colType.equals("Long")) {
						sb.append(".longValue()");
					}
					else if (colType.equals("Short")) {
						sb.append(".shortValue()");
					}

					sb.append(");");

					if (_requiresNullCheck(col)) {
						sb.append("}");
					}
				}

				sb.append("return QueryUtil.list(q, getDialect(), begin, end);");
				sb.append("}");
				sb.append("catch (HibernateException he) {");
				sb.append("throw new SystemException(he);");
				sb.append("}");
				sb.append("finally {");
				sb.append("closeSession(session);");
				sb.append("}");
				sb.append("}");

				sb.append("public " + entity.getName() + " findBy" + finder.getName() + "_First(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append(col.getType() + " " + col.getName() + ", ");
				}

				sb.append("OrderByComparator obc) throws " + _getNoSuchEntityException(entity) + "Exception, SystemException {");

				sb.append("List list = findBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append("" + col.getName() + ", ");
				}

				sb.append("0, 1, obc);");

				sb.append("if (list.size() == 0) {");
				sb.append("String msg = \"No " + entity.getName() + " exists with the key \";");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					if (j == 0) {
						sb.append("msg += StringPool.OPEN_CURLY_BRACE;");
					}

					sb.append("msg += \"" + col.getName() + "=\";");
					sb.append("msg += " + col.getName() + ";");

					if ((j + 1) != finderColsList.size()) {
						sb.append("msg += \", \";");
					}

					if ((j + 1) == finderColsList.size()) {
						sb.append("msg += StringPool.CLOSE_CURLY_BRACE;");
					}
				}

				sb.append("throw new " + _getNoSuchEntityException(entity) + "Exception(msg);");				sb.append("}");
				sb.append("else {");
				sb.append("return (" + entity.getName() + ")list.get(0);");
				sb.append("}");
				sb.append("}");

				sb.append("public " + entity.getName() + " findBy" + finder.getName() + "_Last(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append(col.getType() + " " + col.getName() + ", ");
				}

				sb.append("OrderByComparator obc) throws " + _getNoSuchEntityException(entity) + "Exception, SystemException {");

				sb.append("int count = countBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append("" + col.getName());

					if ((j + 1) != finderColsList.size()) {
						sb.append(", ");
					}
				}

				sb.append(");");

				sb.append("List list = findBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append("" + col.getName() + ", ");
				}

				sb.append("count - 1, count, obc);");

				sb.append("if (list.size() == 0) {");
				sb.append("String msg = \"No " + entity.getName() + " exists with the key \";");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					if (j == 0) {
						sb.append("msg += StringPool.OPEN_CURLY_BRACE;");
					}

					sb.append("msg += \"" + col.getName() + "=\";");
					sb.append("msg += " + col.getName() + ";");

					if ((j + 1) != finderColsList.size()) {
						sb.append("msg += \", \";");
					}

					if ((j + 1) == finderColsList.size()) {
						sb.append("msg += StringPool.CLOSE_CURLY_BRACE;");
					}
				}

				sb.append("throw new " + _getNoSuchEntityException(entity) + "Exception(msg);");				sb.append("}");
				sb.append("else {");
				sb.append("return (" + entity.getName() + ")list.get(0);");
				sb.append("}");
				sb.append("}");

				sb.append("public " + entity.getName() + "[] findBy" + finder.getName() + "_PrevAndNext(" + pkClassName + " " + pkVarName + ", ");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append(col.getType() + " " + col.getName() + ", ");
				}

				sb.append("OrderByComparator obc) throws " + _getNoSuchEntityException(entity) + "Exception, SystemException {");
				sb.append(entity.getName() + " " + entity.getVarName() + " = findByPrimaryKey(" + pkVarName + ");");

				sb.append("int count = countBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append("" + col.getName());

					if ((j + 1) != finderColsList.size()) {
						sb.append(", ");
					}
				}

				sb.append(");");

				sb.append("Session session = null;");
				sb.append("try {");
				sb.append("session = openSession();");
				sb.append("StringBuffer query = new StringBuffer();");
				sb.append("query.append(\"FROM " + _packagePath + ".model." + entity.getName() + " WHERE \");");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					if (_requiresNullCheck(col)) {
						_appendNullLogic(col, sb);
					}

					sb.append("query.append(\"" + col.getDBName() + " " + col.getComparator() + " ?\");");

					if (_requiresNullCheck(col)) {
						sb.append("}");
					}

					if ((j + 1) != finderColsList.size()) {
						sb.append("query.append(\" AND \");");
					}
					else if (Validator.isNull(finder.getWhere())) {
						sb.append("query.append(\" \");");
					}
					else {
						sb.append("query.append(\" AND " + finder.getWhere() + " \");");
					}
				}

				sb.append("if (obc != null) {");
				sb.append("query.append(\"ORDER BY \" + obc.getOrderBy());");
				sb.append("}");

				if (order != null) {
					List orderList = order.getColumns();

					sb.append("else {");
					sb.append("query.append(\"ORDER BY \");");

					for (int j = 0; j < orderList.size(); j++) {
						EntityColumn col = (EntityColumn)orderList.get(j);

						sb.append("query.append(\"" + col.getDBName() + " " + (col.isOrderByAscending() ? "ASC" : "DESC") + "\")");

						if ((j + 1) != orderList.size()) {
							sb.append(".append(\", \");");
						}
						else {
							sb.append(";");
						}
					}

					sb.append("}");
				}

				sb.append("Query q = session.createQuery(query.toString());");
				sb.append("q.setCacheable(true);");
				sb.append("int queryPos = 0;");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					if (_requiresNullCheck(col)) {
						sb.append("if (" + col.getName() + " != null) {");
					}

					String colType = col.getType();
					String colObjType = colType;

					if (col.isPrimitiveType()) {
						colObjType = _getPrimitiveObj(colType);
					}

					sb.append("q.set" + colObjType + "(queryPos++, " + col.getName());

					if (colType.equals("Boolean")) {
						sb.append(".booleanValue()");
					}
					else if (colType.equals("Double")) {
						sb.append(".doubleValue()");
					}
					else if (colType.equals("Float")) {
						sb.append(".floatValue()");
					}
					else if (colType.equals("Integer")) {
						sb.append(".intValue()");
					}
					else if (colType.equals("Long")) {
						sb.append(".longValue()");
					}
					else if (colType.equals("Short")) {
						sb.append(".shortValue()");
					}

					sb.append(");");

					if (_requiresNullCheck(col)) {
						sb.append("}");
					}
				}

				sb.append("Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, " + entity.getVarName() + ");");

				sb.append(entity.getName() + "[] array = new " + entity.getName() + "Impl[3];");

				sb.append("array[0] = (" + entity.getName() + ")objArray[0];");
				sb.append("array[1] = (" + entity.getName() + ")objArray[1];");
				sb.append("array[2] = (" + entity.getName() + ")objArray[2];");

				sb.append("return array;");
				sb.append("}");
				sb.append("catch (HibernateException he) {");
				sb.append("throw new SystemException(he);");
				sb.append("}");
				sb.append("finally {");
				sb.append("closeSession(session);");
				sb.append("}");
				sb.append("}");
			}
		}

		sb.append("public List findWithDynamicQuery(DynamicQueryInitializer queryInitializer) throws SystemException {");
		sb.append("Session session = null;");
		sb.append("try {");
		sb.append("session = openSession();");
		sb.append("DynamicQuery query = queryInitializer.initialize(session);");
		sb.append("return query.list();");
		sb.append("}");
		sb.append("catch (HibernateException he) {");
		sb.append("throw new SystemException(he);");
		sb.append("}");
		sb.append("finally {");
		sb.append("closeSession(session);");
		sb.append("}");
		sb.append("}");

		sb.append("public List findWithDynamicQuery(DynamicQueryInitializer queryInitializer, int begin, int end) throws SystemException {");
		sb.append("Session session = null;");
		sb.append("try {");
		sb.append("session = openSession();");
		sb.append("DynamicQuery query = queryInitializer.initialize(session);");
		sb.append("query.setLimit(begin, end);");
		sb.append("return query.list();");
		sb.append("}");
		sb.append("catch (HibernateException he) {");
		sb.append("throw new SystemException(he);");
		sb.append("}");
		sb.append("finally {");
		sb.append("closeSession(session);");
		sb.append("}");
		sb.append("}");

		sb.append("public List findAll() throws SystemException {");
		sb.append("return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);");
		sb.append("}");

		sb.append("public List findAll(int begin, int end) throws SystemException {");
		sb.append("return findAll(begin, end, null);");
		sb.append("}");

		sb.append("public List findAll(int begin, int end, OrderByComparator obc) throws SystemException {");
		sb.append("Session session = null;");
		sb.append("try {");
		sb.append("session = openSession();");
		sb.append("StringBuffer query = new StringBuffer();");
		sb.append("query.append(\"FROM " + _packagePath + ".model." + entity.getName() + " \");");

		sb.append("if (obc != null) {");
		sb.append("query.append(\"ORDER BY \" + obc.getOrderBy());");
		sb.append("}");

		EntityOrder order = entity.getOrder();

		if (order != null) {
			List orderList = order.getColumns();

			sb.append("else {");
			sb.append("query.append(\"ORDER BY \");");

			for (int j = 0; j < orderList.size(); j++) {
				EntityColumn col = (EntityColumn)orderList.get(j);

				sb.append("query.append(\"" + col.getDBName() + " " + (col.isOrderByAscending() ? "ASC" : "DESC") + "\")");

				if ((j + 1) != orderList.size()) {
					sb.append(".append(\", \");");
				}
				else {
					sb.append(";");
				}
			}

			sb.append("}");
		}

		sb.append("Query q = session.createQuery(query.toString());");
		sb.append("q.setCacheable(true);");
		sb.append("return QueryUtil.list(q, getDialect(), begin, end);");
		sb.append("}");
		sb.append("catch (HibernateException he) {");
		sb.append("throw new SystemException(he);");
		sb.append("}");
		sb.append("finally {");
		sb.append("closeSession(session);");
		sb.append("}");
		sb.append("}");

		// Remove by methods

		for (int i = 0; i < finderList.size(); i++) {
			EntityFinder finder = (EntityFinder)finderList.get(i);

			List finderColsList = finder.getColumns();

			if (!finder.isCollection()) {
				sb.append("public void removeBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append(col.getType() + " " + col.getName());

					if ((j + 1) != finderColsList.size()) {
						sb.append(", ");
					}
				}
				sb.append(") throws " + _getNoSuchEntityException(entity) + "Exception, SystemException {");
				sb.append(entity.getName() + " " + entity.getVarName() + " = findBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append(col.getName());

					if ((j + 1) != finderColsList.size()) {
						sb.append(", ");
					}
				}

				sb.append(");");
				sb.append("remove(" + entity.getVarName() + ");");
				sb.append("}");
			}
			else {
				sb.append("public void removeBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append(col.getType() + " " + col.getName());

					if ((j + 1) != finderColsList.size()) {
						sb.append(", ");
					}
				}

				sb.append(") throws SystemException {");
				sb.append("Iterator itr = findBy" + finder.getName() + "(");

				for (int j = 0; j < finderColsList.size(); j++) {
					EntityColumn col = (EntityColumn)finderColsList.get(j);

					sb.append(col.getName());

					if ((j + 1) != finderColsList.size()) {
						sb.append(", ");
					}
				}

				sb.append(").iterator();");
				sb.append("while (itr.hasNext()) {");
				sb.append(entity.getName() + " " + entity.getVarName() + " = (" + entity.getName() + ")itr.next();");
				sb.append("remove(" + entity.getVarName() + ");");
				sb.append("}");
				sb.append("}");
			}
		}

		sb.append("public void removeAll() throws SystemException {");
		sb.append("Iterator itr = findAll().iterator();");
		sb.append("while (itr.hasNext()) {");
		sb.append("remove((" + entity.getName() + ")itr.next());");
		sb.append("}");
		sb.append("}");

		// Count by methods

		for (int i = 0; i < finderList.size(); i++) {
			EntityFinder finder = (EntityFinder)finderList.get(i);

			List finderColsList = finder.getColumns();

			sb.append("public int countBy" + finder.getName() + "(");

			for (int j = 0; j < finderColsList.size(); j++) {
				EntityColumn col = (EntityColumn)finderColsList.get(j);

				sb.append(col.getType() + " " + col.getName());

				if ((j + 1) != finderColsList.size()) {
					sb.append(", ");
				}
			}

			sb.append(") throws SystemException {");
			sb.append("Session session = null;");
			sb.append("try {");
			sb.append("session = openSession();");
			sb.append("StringBuffer query = new StringBuffer();");
			sb.append("query.append(\"SELECT COUNT(*) \");");
			sb.append("query.append(\"FROM " + _packagePath + ".model." + entity.getName() + " WHERE \");");

			for (int j = 0; j < finderColsList.size(); j++) {
				EntityColumn col = (EntityColumn)finderColsList.get(j);

				if (_requiresNullCheck(col)) {
					_appendNullLogic(col, sb);
				}

				sb.append("query.append(\"" + col.getDBName() + " " + col.getComparator() + " ?\");");

				if (_requiresNullCheck(col)) {
					sb.append("}");
				}

				if ((j + 1) != finderColsList.size()) {
					sb.append("query.append(\" AND \");");
				}
				else if (Validator.isNull(finder.getWhere())) {
					sb.append("query.append(\" \");");
				}
				else {
					sb.append("query.append(\" AND " + finder.getWhere() + " \");");
				}
			}

			sb.append("Query q = session.createQuery(query.toString());");
			sb.append("q.setCacheable(true);");
			sb.append("int queryPos = 0;");

			for (int j = 0; j < finderColsList.size(); j++) {
				EntityColumn col = (EntityColumn)finderColsList.get(j);

				if (_requiresNullCheck(col)) {
					sb.append("if (" + col.getName() + " != null) {");
				}

				String colType = col.getType();
				String colObjType = colType;

				if (col.isPrimitiveType()) {
					colObjType = _getPrimitiveObj(colType);
				}

				sb.append("q.set" + colObjType + "(queryPos++, " + col.getName());

				if (colType.equals("Boolean")) {
					sb.append(".booleanValue()");
				}
				else if (colType.equals("Double")) {
					sb.append(".doubleValue()");
				}
				else if (colType.equals("Float")) {
					sb.append(".floatValue()");
				}
				else if (colType.equals("Integer")) {
					sb.append(".intValue()");
				}
				else if (colType.equals("Long")) {
					sb.append(".longValue()");
				}
				else if (colType.equals("Short")) {
					sb.append(".shortValue()");
				}

				sb.append(");");

				if (_requiresNullCheck(col)) {
					sb.append("}");
				}
			}

			sb.append("Iterator itr = q.list().iterator();");
			sb.append("if (itr.hasNext()) {");
			sb.append("Long count = (Long)itr.next();");
			sb.append("if (count != null) {");
			sb.append("return count.intValue();");
			sb.append("}");
			sb.append("}");
			sb.append("return 0;");
			sb.append("}");
			sb.append("catch (HibernateException he) {");
			sb.append("throw new SystemException(he);");
			sb.append("}");
			sb.append("finally {");
			sb.append("closeSession(session);");
			sb.append("}");
			sb.append("}");
		}

		sb.append("public int countAll() throws SystemException {");
		sb.append("Session session = null;");
		sb.append("try {");
		sb.append("session = openSession();");
		sb.append("StringBuffer query = new StringBuffer();");
		sb.append("query.append(\"SELECT COUNT(*) \");");
		sb.append("query.append(\"FROM " + _packagePath + ".model." + entity.getName() + "\");");
		sb.append("Query q = session.createQuery(query.toString());");
		sb.append("q.setCacheable(true);");
		sb.append("Iterator itr = q.list().iterator();");
		sb.append("if (itr.hasNext()) {");
		sb.append("Long count = (Long)itr.next();");
		sb.append("if (count != null) {");
		sb.append("return count.intValue();");
		sb.append("}");
		sb.append("}");
		sb.append("return 0;");
		sb.append("}");
		sb.append("catch (HibernateException he) {");
		sb.append("throw new SystemException(he);");
		sb.append("}");
		sb.append("finally {");
		sb.append("closeSession(session);");
		sb.append("}");
		sb.append("}");

		// Relationship methods

		for (int i = 0; i < columnList.size(); i++) {
			EntityColumn col = (EntityColumn)columnList.get(i);

			if ((col.isCollection()) &&
				(col.isMappingManyToMany() || col.isMappingOneToMany())) {

				Entity tempEntity = getEntity(col.getEJBName());
				EntityOrder tempOrder = tempEntity.getOrder();

				// getUsers(String pk)

				sb.append("public List get" + tempEntity.getNames() + "(" + entity.getPKClassName() + " pk) throws " + _getNoSuchEntityException(entity) + "Exception, SystemException {");
				sb.append("return get" + tempEntity.getNames() + "(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);");
				sb.append("}");

				// getUsers(String pk, int begin, int end)

				sb.append("public List get" + tempEntity.getNames() + "(" + entity.getPKClassName() + " pk, int begin, int end) throws " + _getNoSuchEntityException(entity) + "Exception, SystemException {");
				sb.append("return get" + tempEntity.getNames() + "(pk, begin, end, null);");
				sb.append("}");

				// getUsers(String pk, int begin, int end, OrderByComparator obc)

				sb.append("public List get" + tempEntity.getNames() + "(" + entity.getPKClassName() + " pk, int begin, int end, OrderByComparator obc) throws " + _getNoSuchEntityException(entity) + "Exception, SystemException {");
				sb.append("Session session = null;");
				sb.append("try {");
				sb.append("session = HibernateUtil.openSession();");
				sb.append("String sql = _SQL_GET" + tempEntity.getName().toUpperCase() + "S;");

				sb.append("if (obc != null) {");
				sb.append("sql += \"ORDER BY \" + obc.getOrderBy();");
				sb.append("}");

				if (tempOrder != null) {
					List tempOrderList = tempOrder.getColumns();

					sb.append("else {");
					sb.append("sql += \"ORDER BY \";");

					for (int j = 0; j < tempOrderList.size(); j++) {
						EntityColumn tempOrderCol = (EntityColumn)tempOrderList.get(j);

						sb.append("sql += \"" + tempEntity.getTable() + "." + tempOrderCol.getDBName() + " " + (tempOrderCol.isOrderByAscending() ? "ASC" : "DESC") + "\";");

						if ((j + 1) != tempOrderList.size()) {
							sb.append("sql += \", \";");
						}
					}

					sb.append("}");
				}

				sb.append("SQLQuery q = session.createSQLQuery(sql);");
				sb.append("q.setCacheable(false);");
				sb.append("q.addEntity(\"" + tempEntity.getTable() + "\", " + tempEntity.getPackagePath() + ".model.impl." + tempEntity.getName() + "Impl.class);");
				sb.append("QueryPos qPos = QueryPos.getInstance(q);");
				sb.append("qPos.add(pk);");
				sb.append("return QueryUtil.list(q, HibernateUtil.getDialect(), begin, end);");
				sb.append("}");
				sb.append("catch (Exception e) {");
				sb.append("throw new SystemException(e);");
				sb.append("}");
				sb.append("finally {");
				sb.append("HibernateUtil.closeSession(session);");
				sb.append("}");
				sb.append("}");

				// getUsersSize(String pk)

				sb.append("public int get" + tempEntity.getNames() + "Size(" + entity.getPKClassName() + " pk) throws SystemException {");
				sb.append("Session session = null;");
				sb.append("try {");
				sb.append("session = openSession();");
				sb.append("SQLQuery q = session.createSQLQuery(_SQL_GET" + tempEntity.getName().toUpperCase() + "SSIZE);");
				sb.append("q.setCacheable(false);");
				sb.append("q.addScalar(HibernateUtil.getCountColumnName(), Hibernate.LONG);");
				sb.append("QueryPos qPos = QueryPos.getInstance(q);");
				sb.append("qPos.add(pk);");
				sb.append("Iterator itr = q.list().iterator();");
				sb.append("if (itr.hasNext()) {");
				sb.append("Long count = (Long)itr.next();");
				sb.append("if (count != null) {");
				sb.append("return count.intValue();");
				sb.append("}");
				sb.append("}");
				sb.append("return 0;");
				sb.append("}");
				sb.append("catch (HibernateException he) {");
				sb.append("throw new SystemException(he);");
				sb.append("}");
				sb.append("finally {");
				sb.append("closeSession(session);");
				sb.append("}");
				sb.append("}");

				// containsUser(String pk, String userPK)

				sb.append("public boolean contains" + tempEntity.getName() + "(" + entity.getPKClassName() + " pk, " + tempEntity.getPKClassName() + " " + tempEntity.getVarName() + "PK) throws SystemException {");
				sb.append("try {");
				sb.append("return contains" + tempEntity.getName() + ".contains(pk, " + tempEntity.getVarName() + "PK);");
				sb.append("}");
				sb.append("catch (DataAccessException dae) {");
				sb.append("throw new SystemException(dae);");
				sb.append("}");
				sb.append("}");

				// containsUsers(String pk)

				sb.append("public boolean contains" + tempEntity.getName() + "s(" + entity.getPKClassName() + " pk) throws SystemException {");
				sb.append("if (get" + tempEntity.getNames() + "Size(pk) > 0) {");
				sb.append("return true;");
				sb.append("}");
				sb.append("else {");
				sb.append("return false;");
				sb.append("}");
				sb.append("}");

				if (col.isMappingManyToMany()) {

					// addUser(String pk, String userPK)

					sb.append("public void add" + tempEntity.getName() + "(" + entity.getPKClassName() + " pk, " + tempEntity.getPKClassName() + " " + tempEntity.getVarName() + "PK) throws " + _getNoSuchEntityException(entity) + "Exception, " + tempEntity.getPackagePath() + "." + _getNoSuchEntityException(tempEntity) + "Exception, SystemException {");
					sb.append("try {");
					sb.append("add" + tempEntity.getName() + ".add(pk, " + tempEntity.getVarName() + "PK);");
					sb.append("}");
					sb.append("catch (DataAccessException dae) {");
					sb.append("throw new SystemException(dae);");
					sb.append("}");
					sb.append("}");

					// addUser(String pk, User user)

					sb.append("public void add" + tempEntity.getName() + "(" + entity.getPKClassName() + " pk, " + tempEntity.getPackagePath() + ".model." + tempEntity.getName() + " " + tempEntity.getVarName() + ") throws " + _getNoSuchEntityException(entity) + "Exception, " + tempEntity.getPackagePath() + "." + _getNoSuchEntityException(tempEntity) + "Exception, SystemException {");
					sb.append("try {");
					sb.append("add" + tempEntity.getName() + ".add(pk, " + tempEntity.getVarName() + ".getPrimaryKey());");
					sb.append("}");
					sb.append("catch (DataAccessException dae) {");
					sb.append("throw new SystemException(dae);");
					sb.append("}");
					sb.append("}");

					// addUsers(String pk, String[] userPKs)

					sb.append("public void add" + tempEntity.getNames() + "(" + entity.getPKClassName() + " pk, " + tempEntity.getPKClassName() + "[] " + tempEntity.getVarName() + "PKs) throws " + _getNoSuchEntityException(entity) + "Exception, " + tempEntity.getPackagePath() + "." + _getNoSuchEntityException(tempEntity) + "Exception, SystemException {");
					sb.append("try {");
					sb.append("for (int i = 0; i < " + tempEntity.getVarName() + "PKs.length; i++) {");
					sb.append("add" + tempEntity.getName() + ".add(pk, " + tempEntity.getVarName() + "PKs[i]);");
					sb.append("}");
					sb.append("}");
					sb.append("catch (DataAccessException dae) {");
					sb.append("throw new SystemException(dae);");
					sb.append("}");
					sb.append("}");

					// addUsers(String pk, List users)

					sb.append("public void add" + tempEntity.getNames() + "(" + entity.getPKClassName() + " pk, List " + tempEntity.getVarNames() + ") throws " + _getNoSuchEntityException(entity) + "Exception, " + tempEntity.getPackagePath() + "." + _getNoSuchEntityException(tempEntity) + "Exception, SystemException {");
					sb.append("try {");
					sb.append("for (int i = 0; i < " + tempEntity.getVarNames() + ".size(); i++) {");
					sb.append(tempEntity.getPackagePath() + ".model." + tempEntity.getName() + " " + tempEntity.getVarName() + " = (" + tempEntity.getPackagePath() + ".model." + tempEntity.getName() + ")" + tempEntity.getVarNames() + ".get(i);");
					sb.append("add" + tempEntity.getName() + ".add(pk, " + tempEntity.getVarName() + ".getPrimaryKey());");
					sb.append("}");
					sb.append("}");
					sb.append("catch (DataAccessException dae) {");
					sb.append("throw new SystemException(dae);");
					sb.append("}");
					sb.append("}");

					// clearUsers(String pk)

					sb.append("public void clear" + tempEntity.getNames() + "(" + entity.getPKClassName() + " pk) throws " + _getNoSuchEntityException(entity) + "Exception, SystemException {");
					sb.append("try {");
					sb.append("clear" + tempEntity.getNames() + ".clear(pk);");
					sb.append("}");
					sb.append("catch (DataAccessException dae) {");
					sb.append("throw new SystemException(dae);");
					sb.append("}");
					sb.append("}");

					// removeUser(String pk, String userPK)

					sb.append("public void remove" + tempEntity.getName() + "(" + entity.getPKClassName() + " pk, " + tempEntity.getPKClassName() + " " + tempEntity.getVarName() + "PK) throws " + _getNoSuchEntityException(entity) + "Exception, " + tempEntity.getPackagePath() + "." + _getNoSuchEntityException(tempEntity) + "Exception, SystemException {");
					sb.append("try {");
					sb.append("remove" + tempEntity.getName() + ".remove(pk, " + tempEntity.getVarName() + "PK);");
					sb.append("}");
					sb.append("catch (DataAccessException dae) {");
					sb.append("throw new SystemException(dae);");
					sb.append("}");
					sb.append("}");

					// removeUser(String pk, User user)

					sb.append("public void remove" + tempEntity.getName() + "(" + entity.getPKClassName() + " pk, " + tempEntity.getPackagePath() + ".model." + tempEntity.getName() + " " + tempEntity.getVarName() + ") throws " + _getNoSuchEntityException(entity) + "Exception, " + tempEntity.getPackagePath() + "." + _getNoSuchEntityException(tempEntity) + "Exception, SystemException {");
					sb.append("try {");
					sb.append("remove" + tempEntity.getName() + ".remove(pk, " + tempEntity.getVarName() + ".getPrimaryKey());");
					sb.append("}");
					sb.append("catch (DataAccessException dae) {");
					sb.append("throw new SystemException(dae);");
					sb.append("}");
					sb.append("}");

					// removeUsers(String pk, String[] userPKs)

					sb.append("public void remove" + tempEntity.getNames() + "(" + entity.getPKClassName() + " pk, " + tempEntity.getPKClassName() + "[] " + tempEntity.getVarName() + "PKs) throws " + _getNoSuchEntityException(entity) + "Exception, " + tempEntity.getPackagePath() + "." + _getNoSuchEntityException(tempEntity) + "Exception, SystemException {");
					sb.append("try {");
					sb.append("for (int i = 0; i < " + tempEntity.getVarName() + "PKs.length; i++) {");
					sb.append("remove" + tempEntity.getName() + ".remove(pk, " + tempEntity.getVarName() + "PKs[i]);");
					sb.append("}");
					sb.append("}");
					sb.append("catch (DataAccessException dae) {");
					sb.append("throw new SystemException(dae);");
					sb.append("}");
					sb.append("}");

					// removeUsers(String pk, List users)

					sb.append("public void remove" + tempEntity.getNames() + "(" + entity.getPKClassName() + " pk, List " + tempEntity.getVarNames() + ") throws " + _getNoSuchEntityException(entity) + "Exception, " + tempEntity.getPackagePath() + "." + _getNoSuchEntityException(tempEntity) + "Exception, SystemException {");
					sb.append("try {");
					sb.append("for (int i = 0; i < " + tempEntity.getVarNames() + ".size(); i++) {");
					sb.append(tempEntity.getPackagePath() + ".model." + tempEntity.getName() + " " + tempEntity.getVarName() + " = (" + tempEntity.getPackagePath() + ".model." + tempEntity.getName() + ")" + tempEntity.getVarNames() + ".get(i);");
					sb.append("remove" + tempEntity.getName() + ".remove(pk, " + tempEntity.getVarName() + ".getPrimaryKey());");
					sb.append("}");
					sb.append("}");
					sb.append("catch (DataAccessException dae) {");
					sb.append("throw new SystemException(dae);");
					sb.append("}");
					sb.append("}");

					// setUsers(String pk, String[] pks)

					sb.append("public void set" + tempEntity.getNames() + "(" + entity.getPKClassName() + " pk, " + tempEntity.getPKClassName() + "[] " + tempEntity.getVarName() + "PKs) throws " + _getNoSuchEntityException(entity) + "Exception, " + tempEntity.getPackagePath() + "." + _getNoSuchEntityException(tempEntity) + "Exception, SystemException {");
					sb.append("try {");
					sb.append("clear" + tempEntity.getNames() + ".clear(pk);");
					sb.append("for (int i = 0; i < " + tempEntity.getVarName() + "PKs.length; i++) {");
					sb.append("add" + tempEntity.getName() + ".add(pk, " + tempEntity.getVarName() + "PKs[i]);");
					sb.append("}");
					sb.append("}");
					sb.append("catch (DataAccessException dae) {");
					sb.append("throw new SystemException(dae);");
					sb.append("}");
					sb.append("}");

					// setUsers(String pk, List pks)

					sb.append("public void set" + tempEntity.getNames() + "(" + entity.getPKClassName() + " pk, List " + tempEntity.getVarNames() + ") throws " + _getNoSuchEntityException(entity) + "Exception, " + tempEntity.getPackagePath() + "." + _getNoSuchEntityException(tempEntity) + "Exception, SystemException {");
					sb.append("try {");
					sb.append("clear" + tempEntity.getNames() + ".clear(pk);");
					sb.append("for (int i = 0; i < " + tempEntity.getVarNames() + ".size(); i++) {");
					sb.append(tempEntity.getPackagePath() + ".model." + tempEntity.getName() + " " + tempEntity.getVarName() + " = (" + tempEntity.getPackagePath() + ".model." + tempEntity.getName() + ")" + tempEntity.getVarNames() + ".get(i);");
					sb.append("add" + tempEntity.getName() + ".add(pk, " + tempEntity.getVarName() + ".getPrimaryKey());");
					sb.append("}");
					sb.append("}");
					sb.append("catch (DataAccessException dae) {");
					sb.append("throw new SystemException(dae);");
					sb.append("}");
					sb.append("}");
				}
			}
		}

		sb.append("protected void initDao() {");

		for (int i = 0; i < columnList.size(); i++) {
			EntityColumn col = (EntityColumn)columnList.get(i);

			if ((col.isCollection()) &&
				(col.isMappingManyToMany() || col.isMappingOneToMany())) {

				Entity tempEntity = getEntity(col.getEJBName());

				// containsUser(String pk, String userPK)

				sb.append("contains" + tempEntity.getName() + " = new Contains" + tempEntity.getName() + "(this);");

				if (col.isMappingManyToMany()) {

					// addUser(String pk, String userPK)

					sb.append("add" + tempEntity.getName() + " = new Add" + tempEntity.getName() + "(this);");

					// clearUsers(String pk)

					sb.append("clear" + tempEntity.getNames() + " = new Clear" + tempEntity.getNames() + "(this);");

					// removeUser(String pk, String userPK)

					sb.append("remove" + tempEntity.getName() + " = new Remove" + tempEntity.getName() + "(this);");
				}
			}
		}

		sb.append("}");

		for (int i = 0; i < columnList.size(); i++) {
			EntityColumn col = (EntityColumn)columnList.get(i);

			if ((col.isCollection()) &&
				(col.isMappingManyToMany() || col.isMappingOneToMany())) {

				Entity tempEntity = getEntity(col.getEJBName());

				// containsUser(String pk, String userPK)

				sb.append("protected Contains" + tempEntity.getName() + " contains" + tempEntity.getName() + ";");

				if (col.isMappingManyToMany()) {

					// addUser(String pk, String userPK)

					sb.append("protected Add" + tempEntity.getName() + " add" + tempEntity.getName() + ";");

					// clearUsers(String pk)

					sb.append("protected Clear" + tempEntity.getNames() + " clear" + tempEntity.getNames() + ";");

					// removeUser(String pk, String userPK)

					sb.append("protected Remove" + tempEntity.getName() + " remove" + tempEntity.getName() + ";");
				}
			}
		}

		for (int i = 0; i < columnList.size(); i++) {
			EntityColumn col = (EntityColumn)columnList.get(i);

			if ((col.isCollection()) &&
				(col.isMappingManyToMany() || col.isMappingOneToMany())) {

				Entity tempEntity = getEntity(col.getEJBName());

				String entitySqlType = _getSqlType(_packagePath + ".model." + entity.getName(), entity.getPKVarName(), entity.getPKClassName());

				String pkVarNameWrapper = pkVarName;

				if (entity.hasPrimitivePK()) {
					pkVarNameWrapper = "new " + _getPrimitiveObj(entity.getPKClassName()) + "(" + pkVarName + ")";
				}

				String tempEntitySqlType = _getSqlType(tempEntity.getPackagePath() + ".model." + entity.getName(), tempEntity.getPKVarName(), tempEntity.getPKClassName());

				String tempEntityPkVarNameWrapper = tempEntity.getPKVarName();

				if (tempEntity.hasPrimitivePK()) {
					tempEntityPkVarNameWrapper = "new " + _getPrimitiveObj(tempEntity.getPKClassName()) + "(" + tempEntityPkVarNameWrapper + ")";
				}

				// containsUser(String pk, String userPK)

				sb.append("protected class Contains" + tempEntity.getName() + " extends MappingSqlQuery {");
				sb.append("protected Contains" + tempEntity.getName() + "(" + entity.getName() + "Persistence persistence) {");
				sb.append("super(persistence.getDataSource(), _SQL_CONTAINS" + tempEntity.getName().toUpperCase() + ");");
				sb.append("declareParameter(new SqlParameter(Types." + entitySqlType + "));");
				sb.append("declareParameter(new SqlParameter(Types." + tempEntitySqlType + "));");
				sb.append("compile();");
				sb.append("}");
				sb.append("protected Object mapRow(ResultSet rs, int rowNumber) throws SQLException {");
				sb.append("return new Integer(rs.getInt(\"COUNT_VALUE\"));");
				sb.append("}");
				sb.append("protected boolean contains(" + pkClassName + " " + pkVarName + ", " + tempEntity.getPKClassName() + " " + tempEntity.getPKVarName() + ") {");
				sb.append("List results = execute(new Object[] {" + pkVarNameWrapper + ", " + tempEntityPkVarNameWrapper + "});");
				sb.append("if (results.size() > 0) {");
				sb.append("Integer count = (Integer)results.get(0);");
				sb.append("if (count.intValue() > 0) {");
				sb.append("return true;");
				sb.append("}");
				sb.append("}");
				sb.append("return false;");
				sb.append("}");
				sb.append("}");

				if (col.isMappingManyToMany()) {

					// addUser(String pk, String userPK)

					sb.append("protected class Add" + tempEntity.getName() + " extends SqlUpdate {");
					sb.append("protected Add" + tempEntity.getName() + "(" + entity.getName() + "Persistence persistence) {");
					sb.append("super(persistence.getDataSource(), \"INSERT INTO " + col.getMappingTable() + " (" + pkVarName + ", " + tempEntity.getPKVarName() + ") VALUES (?, ?)\");");
					sb.append("_persistence = persistence;");
					sb.append("declareParameter(new SqlParameter(Types." + entitySqlType + "));");
					sb.append("declareParameter(new SqlParameter(Types." + tempEntitySqlType + "));");
					sb.append("compile();");
					sb.append("}");
					sb.append("protected void add(" + pkClassName + " " + pkVarName + ", " + tempEntity.getPKClassName() + " " + tempEntity.getPKVarName() + ") {");
					sb.append("if (!_persistence.contains" + tempEntity.getName() + ".contains(" + pkVarName + ", " + tempEntity.getPKVarName() + ")) {");
					sb.append("update(new Object[] {" + pkVarNameWrapper + ", " + tempEntityPkVarNameWrapper + "});");
					sb.append("}");
					sb.append("}");
					sb.append("private " + entity.getName() + "Persistence _persistence;");
					sb.append("}");

					// clearUsers(String pk)

					sb.append("protected class Clear" + tempEntity.getNames() + " extends SqlUpdate {");
					sb.append("protected Clear" + tempEntity.getNames() + "(" + entity.getName() + "Persistence persistence) {");
					sb.append("super(persistence.getDataSource(), \"DELETE FROM " + col.getMappingTable() + " WHERE " + pkVarName + " = ?\");");
					sb.append("declareParameter(new SqlParameter(Types." + entitySqlType + "));");
					sb.append("compile();");
					sb.append("}");
					sb.append("protected void clear(" + pkClassName + " " + pkVarName + ") {");
					sb.append("update(new Object[] {" + pkVarNameWrapper + "});");
					sb.append("}");
					sb.append("}");

					// removeUser(String pk, String userPK)

					sb.append("protected class Remove" + tempEntity.getName() + " extends SqlUpdate {");
					sb.append("protected Remove" + tempEntity.getName() + "(" + entity.getName() + "Persistence persistence) {");
					sb.append("super(persistence.getDataSource(), \"DELETE FROM " + col.getMappingTable() + " WHERE " + pkVarName + " = ? AND " + tempEntity.getPKVarName() + " = ?\");");
					sb.append("declareParameter(new SqlParameter(Types." + entitySqlType + "));");
					sb.append("declareParameter(new SqlParameter(Types." + tempEntitySqlType + "));");
					sb.append("compile();");
					sb.append("}");
					sb.append("protected void remove(" + pkClassName + " " + pkVarName + ", " + tempEntity.getPKClassName() + " " + tempEntity.getPKVarName() + ") {");
					sb.append("update(new Object[] {" + pkVarNameWrapper + ", " + tempEntityPkVarNameWrapper + "});");
					sb.append("}");
					sb.append("}");
				}
			}
		}

		for (int i = 0; i < columnList.size(); i++) {
			EntityColumn col = (EntityColumn)columnList.get(i);

			if (col.isCollection()) {
				Entity tempEntity = getEntity(col.getEJBName());

				if (col.isMappingManyToMany()) {

					// getUsers(String pk)

					sb.append("private static final String _SQL_GET" + tempEntity.getName().toUpperCase() + "S = \"SELECT {" + tempEntity.getTable() + ".*} FROM " + tempEntity.getTable() + " INNER JOIN " + col.getMappingTable() + " ON (" + col.getMappingTable() + "." + tempEntity.getPKVarName() + " = " + tempEntity.getTable() + "." + tempEntity.getPKVarName() + ") WHERE (" + col.getMappingTable() + "." + entity.getPKVarName() + " = ?)\";");

					// getUsersSize(String pk)

					sb.append("private static final String _SQL_GET" + tempEntity.getName().toUpperCase() + "SSIZE = \"SELECT COUNT(*) AS COUNT_VALUE FROM " + col.getMappingTable() + " WHERE " + entity.getPKVarName() + " = ?\";");

					// containsUser(String pk, String userPK)

					sb.append("private static final String _SQL_CONTAINS" + tempEntity.getName().toUpperCase() + " = \"SELECT COUNT(*) AS COUNT_VALUE FROM " + col.getMappingTable() + " WHERE " + entity.getPKVarName() + " = ? AND " + tempEntity.getPKVarName() + " = ?\";");
				}
				else if (col.isMappingOneToMany()) {

					// getUsers(String pk)

					sb.append("private static final String _SQL_GET" + tempEntity.getName().toUpperCase() + "S = \"SELECT {" + tempEntity.getTable() + ".*} FROM " + tempEntity.getTable() + " INNER JOIN " + entity.getTable() + " ON (" + entity.getTable() + "." + entity.getPKVarName() + " = " + tempEntity.getTable() + "." + entity.getPKVarName() + ") WHERE (" + entity.getTable() + "." + entity.getPKVarName() + " = ?)\";");

					// getUsersSize(String pk)

					sb.append("private static final String _SQL_GET" + tempEntity.getName().toUpperCase() + "SSIZE = \"SELECT COUNT(*) AS COUNT_VALUE FROM " + tempEntity.getTable() + " WHERE " + entity.getPKVarName() + " = ?\";");

					// containsUser(String pk, String userPK)

					sb.append("private static final String _SQL_CONTAINS" + tempEntity.getName().toUpperCase() + " = \"SELECT COUNT(*) AS COUNT_VALUE FROM " + tempEntity.getTable() + " WHERE " + entity.getPKVarName() + " = ? AND " + tempEntity.getPKVarName() + " = ?\";");
				}
			}
		}

		// Fields

		sb.append("private static Log _log = LogFactory.getLog(" + entity.getName() + "Persistence.class);");

		// Class close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_outputPath + "/service/persistence/" + entity.getName() + "Persistence.java");

		writeFile(ejbFile, sb.toString());
	}

	private void _createPersistenceUtil(Entity entity) throws IOException {
		JavaClass javaClass = _getJavaClass(_outputPath + "/service/persistence/" + entity.getName() + "Persistence.java");

		JavaMethod[] methods = javaClass.getMethods();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service.persistence;");

		// Imports

		sb.append("import com.liferay.portal.model.ModelListener;");
		sb.append("import com.liferay.portal.util.PropsUtil;");
		sb.append("import com.liferay.util.GetterUtil;");
		sb.append("import com.liferay.util.InstancePool;");
		sb.append("import com.liferay.util.Validator;");
		sb.append("import org.apache.commons.logging.Log;");
		sb.append("import org.apache.commons.logging.LogFactory;");

		// Class declaration

		sb.append("public class " + entity.getName() + "Util {");

		// Methods

		for (int i = 0; i < methods.length; i++) {
			JavaMethod javaMethod = methods[i];

			String methodName = javaMethod.getName();

			if (!javaMethod.isConstructor()) {
				sb.append("public static " + javaMethod.getReturns().getValue() + _getDimensions(javaMethod.getReturns()) + " " + methodName + "(");

				JavaParameter[] parameters = javaMethod.getParameters();

				String p0Name = "";

				if (parameters.length > 0) {
					p0Name = parameters[0].getName();
				}

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					sb.append(javaParameter.getType().getValue() + _getDimensions(javaParameter.getType()) + " " + javaParameter.getName());

					if ((j + 1) != parameters.length) {
						sb.append(", ");
					}
				}

				sb.append(")");

				Type[] thrownExceptions = javaMethod.getExceptions();

				if (thrownExceptions.length > 0) {
					sb.append(" throws ");

					for (int j = 0; j < thrownExceptions.length; j++) {
						Type thrownException = thrownExceptions[j];

						sb.append(thrownException.getValue());

						if ((j + 1) != thrownExceptions.length) {
							sb.append(", ");
						}
					}
				}

				sb.append(" {");

				if (methodName.equals("remove") || methodName.equals("update")) {
					sb.append("ModelListener listener = _getListener();");

					if (methodName.equals("update")) {
						sb.append("boolean isNew = " + p0Name + ".isNew();");
					}

					sb.append("if (listener != null) {");

					if (methodName.equals("remove")) {
						if (entity.getVarName().equals(p0Name)) {
							sb.append("listener.onBeforeRemove(" + p0Name + ");");
						}
						else {
							sb.append("listener.onBeforeRemove(findByPrimaryKey(" + p0Name + "));");
						}
					}
					else {
						sb.append("if (isNew) {");
						sb.append("listener.onBeforeCreate(" + p0Name + ");");
						sb.append("}");
						sb.append("else {");
						sb.append("listener.onBeforeUpdate(" + p0Name + ");");
						sb.append("}");
					}

					sb.append("}");

					if (methodName.equals("remove") && !entity.getVarName().equals(p0Name)) {
						sb.append(_packagePath + ".model." + entity.getName() + " " + entity.getVarName() + " = ");
					}
					else {
						sb.append(entity.getVarName() + " = ");
					}
				}
				else {
					if (!javaMethod.getReturns().getValue().equals("void")) {
						sb.append("return ");
					}
				}

				sb.append("getPersistence()." + methodName + "(");

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					sb.append(javaParameter.getName());

					if ((j + 1) != parameters.length) {
						sb.append(", ");
					}
				}

				sb.append(");");

				if (methodName.equals("remove") || methodName.equals("update")) {
					sb.append("if (listener != null) {");

					if (methodName.equals("remove")) {
						sb.append("listener.onAfterRemove(" + entity.getVarName() + ");");
					}
					else {
						sb.append("if (isNew) {");
						sb.append("listener.onAfterCreate(" + entity.getVarName() + ");");
						sb.append("}");
						sb.append("else {");
						sb.append("listener.onAfterUpdate(" + entity.getVarName() + ");");
						sb.append("}");
					}

					sb.append("}");

					sb.append("return " + entity.getVarName() + ";");
				}

				sb.append("}");
			}
		}

		sb.append("public static " + entity.getName() + "Persistence getPersistence() {");
		sb.append("return _getUtil()._persistence;");
		sb.append("}");

		sb.append("public void setPersistence(" + entity.getName() + "Persistence persistence) {");
		sb.append("_persistence = persistence;");
		sb.append("}");

		sb.append("private static " + entity.getName() + "Util _getUtil() {");
		sb.append("if (_util == null) {");
		sb.append("_util = (" + entity.getName() + "Util)" + _beanLocatorUtilClassName + ".locate(_UTIL);");
		sb.append("}");
		sb.append("return _util;");
		sb.append("}");

		sb.append("private static ModelListener _getListener() {");
		sb.append("if (Validator.isNotNull(_LISTENER)) {");
		sb.append("try {");
		sb.append("return (ModelListener)Class.forName(_LISTENER).newInstance();");
		sb.append("}");
		sb.append("catch (Exception e) {");
		sb.append("_log.error(e);");
		sb.append("}");
		sb.append("}");
		sb.append("return null;");
		sb.append("}");

		// Fields

		sb.append("private static final String _UTIL = " + entity.getName() + "Util.class.getName();");
		sb.append("private static final String _LISTENER = GetterUtil.getString(PropsUtil.get(\"value.object.listener." + _packagePath + ".model." + entity.getName() + "\"));");

		sb.append("private static Log _log = LogFactory.getLog(" + entity.getName() + "Util.class);");

		sb.append("private static " + entity.getName() + "Util _util;");

		sb.append("private " + entity.getName() + "Persistence _persistence;");

		// Class close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_outputPath + "/service/persistence/" + entity.getName() + "Util.java");

		writeFile(ejbFile, sb.toString());
	}

	private void _createPool(Entity entity) throws IOException {
		File ejbFile = new File(_outputPath + "/service/persistence/" + entity.getName() + "Pool.java");

		if (ejbFile.exists()) {
			System.out.println("Removing deprecated " + ejbFile);

			ejbFile.delete();
		}
	}

	private void _createService(Entity entity, int sessionType) throws IOException {
		JavaClass javaClass = _getJavaClass(_outputPath + "/service/impl/" + entity.getName() + (sessionType != _REMOTE ? "Local" : "") + "ServiceImpl.java");

		JavaMethod[] methods = javaClass.getMethods();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service;");

		// Interface declaration

		sb.append("public interface " + entity.getName() + _getSessionTypeName(sessionType) + "Service {");

		// Methods

		for (int i = 0; i < methods.length; i++) {
			JavaMethod javaMethod = methods[i];

			String methodName = javaMethod.getName();

			if (!javaMethod.isConstructor() && javaMethod.isPublic() && _isCustomMethod(javaMethod)) {
				sb.append("public " + javaMethod.getReturns().getValue() + _getDimensions(javaMethod.getReturns()) + " " + methodName + "(");

				JavaParameter[] parameters = javaMethod.getParameters();

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					sb.append(javaParameter.getType().getValue() + _getDimensions(javaParameter.getType()) + " " + javaParameter.getName());

					if ((j + 1) != parameters.length) {
						sb.append(", ");
					}
				}

				sb.append(")");

				Type[] thrownExceptions = javaMethod.getExceptions();

				Set newExceptions = new LinkedHashSet();

				for (int j = 0; j < thrownExceptions.length; j++) {
					Type thrownException = thrownExceptions[j];

					newExceptions.add(thrownException.getValue());
				}

				if (sessionType != _LOCAL) {
					newExceptions.add("java.rmi.RemoteException");
				}

				if (newExceptions.size() > 0) {
					sb.append(" throws ");

					Iterator itr = newExceptions.iterator();

					while (itr.hasNext()) {
						sb.append(itr.next());

						if (itr.hasNext()) {
							sb.append(", ");
						}
					}
				}

				sb.append(";");
			}
		}

		// Interface close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_serviceOutputPath + "/service/" + entity.getName() + _getSessionTypeName(sessionType) + "Service.java");

		writeFile(ejbFile, sb.toString());

		ejbFile = new File(_outputPath + "/service/spring/" + entity.getName() + _getSessionTypeName(sessionType) + "Service.java");

		if (ejbFile.exists()) {
			System.out.println("Relocating " + ejbFile);

			ejbFile.delete();
		}
	}

	private void _createServiceEJB(Entity entity, int sessionType) throws IOException {
		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service.ejb;");

		// Imports

		sb.append("import " + _packagePath + ".service." + entity.getName() + _getSessionTypeName(sessionType) + "Service;");

		if (sessionType == _LOCAL) {
			sb.append("import javax.ejb.EJBLocalObject;");
		}
		else {
			sb.append("import javax.ejb.EJBObject;");
		}

		// Interface declaration

		sb.append("public interface " + entity.getName() + _getSessionTypeName(sessionType) + "ServiceEJB extends EJB" + (sessionType == _LOCAL ? "Local" : "") + "Object, " + entity.getName() + _getSessionTypeName(sessionType) + "Service {");

		// Interface close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_outputPath + "/service/ejb/" + entity.getName() + _getSessionTypeName(sessionType) + "ServiceEJB.java");

		writeFile(ejbFile, sb.toString());
	}

	private void _createServiceEJBImpl(Entity entity, int sessionType) throws IOException {
		JavaClass javaClass = _getJavaClass(_serviceOutputPath + "/service/" + entity.getName() + (sessionType != _REMOTE ? "Local" : "") + "Service.java");

		JavaMethod[] methods = javaClass.getMethods();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service.ejb;");

		// Imports

		sb.append("import " + _packagePath + ".service." + entity.getName() + _getSessionTypeName(sessionType) + "Service;");

		if (methods.length > 0) {
			sb.append("import " + _packagePath + ".service." + entity.getName() + _getSessionTypeName(sessionType) + "ServiceFactory;");
		}

		sb.append("import javax.ejb.CreateException;");
		sb.append("import javax.ejb.SessionContext;");
		sb.append("import javax.ejb.SessionBean;");

		if (sessionType == _REMOTE) {
			sb.append("import com.liferay.portal.service.impl.PrincipalSessionBean;");
		}

		// Class declaration

		sb.append("public class " + entity.getName() + _getSessionTypeName(sessionType) + "ServiceEJBImpl implements " + entity.getName() + _getSessionTypeName(sessionType) + "Service, SessionBean {");

		// Methods

		for (int i = 0; i < methods.length; i++) {
			JavaMethod javaMethod = methods[i];

			String methodName = javaMethod.getName();

			if (!javaMethod.isConstructor() && javaMethod.isPublic() && _isCustomMethod(javaMethod)) {
				sb.append("public " + javaMethod.getReturns().getValue() + _getDimensions(javaMethod.getReturns()) + " " + methodName + "(");

				JavaParameter[] parameters = javaMethod.getParameters();

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					sb.append(javaParameter.getType().getValue() + _getDimensions(javaParameter.getType()) + " " + javaParameter.getName());

					if ((j + 1) != parameters.length) {
						sb.append(", ");
					}
				}

				sb.append(")");

				Type[] thrownExceptions = javaMethod.getExceptions();

				Set newExceptions = new LinkedHashSet();

				for (int j = 0; j < thrownExceptions.length; j++) {
					Type thrownException = thrownExceptions[j];

					newExceptions.add(thrownException.getValue());
				}

				if (newExceptions.size() > 0) {
					sb.append(" throws ");

					Iterator itr = newExceptions.iterator();

					while (itr.hasNext()) {
						sb.append(itr.next());

						if (itr.hasNext()) {
							sb.append(", ");
						}
					}
				}

				sb.append("{");

				if (sessionType == _REMOTE) {
					sb.append("PrincipalSessionBean.setThreadValues(_sc);");
				}

				if (!javaMethod.getReturns().getValue().equals("void")) {
					sb.append("return ");
				}

				sb.append(entity.getName() + _getSessionTypeName(sessionType) + "ServiceFactory.getTxImpl()." + methodName + "(");

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					sb.append(javaParameter.getName());

					if ((j + 1) != parameters.length) {
						sb.append(", ");
					}
				}

				sb.append(");");
				sb.append("}");
			}
		}

		sb.append("public void ejbCreate() throws CreateException {");
		sb.append("}");

		sb.append("public void ejbRemove() {");
		sb.append("}");

		sb.append("public void ejbActivate() {");
		sb.append("}");

		sb.append("public void ejbPassivate() {");
		sb.append("}");

		sb.append("public SessionContext getSessionContext() {");
		sb.append("return _sc;");
		sb.append("}");

		sb.append("public void setSessionContext(SessionContext sc) {");
		sb.append("_sc = sc;");
		sb.append("}");

		// Fields

		sb.append("private SessionContext _sc;");

		// Class close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_outputPath + "/service/ejb/" + entity.getName() + _getSessionTypeName(sessionType) + "ServiceEJBImpl.java");

		writeFile(ejbFile, sb.toString());
	}

	private void _createServiceFactory(Entity entity, int sessionType) throws IOException {
		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service;");

		// Class declaration

		sb.append("public class " + entity.getName() + _getSessionTypeName(sessionType) + "ServiceFactory {");

		// Methods

		sb.append("public static " + entity.getName() + _getSessionTypeName(sessionType) + "Service getService() {");
		sb.append("return _getFactory()._service;");
		sb.append("}");

		sb.append("public static " + entity.getName() + _getSessionTypeName(sessionType) + "Service getTxImpl() {");
		sb.append("if (_txImpl == null) {");
		sb.append("_txImpl = (" + entity.getName() + _getSessionTypeName(sessionType) + "Service)" + _beanLocatorUtilClassName + ".locate(_TX_IMPL);");
		sb.append("}");
		sb.append("return _txImpl;");
		sb.append("}");

		sb.append("public void setService(" + entity.getName() + _getSessionTypeName(sessionType) + "Service service) {");
		sb.append("_service = service;");
		sb.append("}");

		sb.append("private static " + entity.getName() + _getSessionTypeName(sessionType) + "ServiceFactory _getFactory() {");
		sb.append("if (_factory == null) {");
		sb.append("_factory = (" + entity.getName() + _getSessionTypeName(sessionType) + "ServiceFactory)" + _beanLocatorUtilClassName + ".locate(_FACTORY);");
		sb.append("}");
		sb.append("return _factory;");
		sb.append("}");

		// Fields

		sb.append("private static final String _FACTORY = " + entity.getName() + _getSessionTypeName(sessionType) + "ServiceFactory.class.getName();");
		sb.append("private static final String _TX_IMPL = " + entity.getName() + _getSessionTypeName(sessionType) + "Service.class.getName() + \".transaction\";");

		sb.append("private static " + entity.getName() + _getSessionTypeName(sessionType) + "ServiceFactory _factory;");
		sb.append("private static " + entity.getName() + _getSessionTypeName(sessionType) + "Service _txImpl;");

		sb.append("private " + entity.getName() + _getSessionTypeName(sessionType) + "Service _service;");

		// Class close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_serviceOutputPath + "/service/" + entity.getName() + _getSessionTypeName(sessionType) + "ServiceFactory.java");

		writeFile(ejbFile, sb.toString());

		ejbFile = new File(_outputPath + "/service/spring/" + entity.getName() + _getSessionTypeName(sessionType) + "ServiceFactory.java");

		if (ejbFile.exists()) {
			System.out.println("Relocating " + ejbFile);

			ejbFile.delete();
		}
	}

	private void _createServiceHome(Entity entity, int sessionType) throws IOException {
		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service.ejb;");

		// Imports

		sb.append("import javax.ejb.CreateException;");

		if (sessionType == _LOCAL) {
			sb.append("import javax.ejb.EJBLocalHome;");
		}
		else {
			sb.append("import java.rmi.RemoteException;");
			sb.append("import javax.ejb.EJBHome;");
		}

		// Interface declaration

		sb.append("public interface " + entity.getName() + _getSessionTypeName(sessionType) + "ServiceHome extends EJB" + (sessionType == _LOCAL ? "Local" : "") + "Home {");

		// Create method

		sb.append("public " + entity.getName() + _getSessionTypeName(sessionType) + "ServiceEJB create() throws CreateException");

		if (sessionType != _LOCAL) {
			sb.append(", RemoteException");
		}

		sb.append(";");

		// Interface close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_outputPath + "/service/ejb/" + entity.getName() + _getSessionTypeName(sessionType) + "ServiceHome.java");

		writeFile(ejbFile, sb.toString());
	}

	private void _createServiceHttp(Entity entity) throws IOException {
		JavaClass javaClass = _getJavaClass(_outputPath + "/service/impl/" + entity.getName() + "ServiceImpl.java");

		JavaMethod[] methods = javaClass.getMethods();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service.http;");

		// Imports

		if (_hasHttpMethods(javaClass)) {
			sb.append("import " + _packagePath + ".service." + entity.getName() + "ServiceUtil;");
		}

		sb.append("import com.liferay.portal.kernel.log.Log;");
		sb.append("import com.liferay.portal.kernel.log.LogFactoryUtil;");
		sb.append("import com.liferay.portal.kernel.util.BooleanWrapper;");
		sb.append("import com.liferay.portal.kernel.util.DoubleWrapper;");
		sb.append("import com.liferay.portal.kernel.util.FloatWrapper;");
		sb.append("import com.liferay.portal.kernel.util.IntegerWrapper;");
		sb.append("import com.liferay.portal.kernel.util.LongWrapper;");
		sb.append("import com.liferay.portal.kernel.util.MethodWrapper;");
		sb.append("import com.liferay.portal.kernel.util.NullWrapper;");
		sb.append("import com.liferay.portal.kernel.util.ShortWrapper;");
		sb.append("import com.liferay.portal.security.auth.HttpPrincipal;");
		sb.append("import com.liferay.portal.service.http.TunnelUtil;");

		// Class declaration

		sb.append("public class " + entity.getName() + "ServiceHttp {");

		// Methods

		for (int i = 0; i < methods.length; i++) {
			JavaMethod javaMethod = methods[i];

			String methodName = javaMethod.getName();

			if (!javaMethod.isConstructor() && javaMethod.isPublic() && _isCustomMethod(javaMethod)) {
				Type returnType = javaMethod.getReturns();
				String returnTypeName = returnType.getValue() + _getDimensions(returnType);

				sb.append("public static " + returnTypeName + " " + methodName + "(HttpPrincipal httpPrincipal");

				JavaParameter[] parameters = javaMethod.getParameters();

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					if (j == 0) {
						sb.append(", ");
					}

					sb.append(javaParameter.getType().getValue() + _getDimensions(javaParameter.getType()) + " " + javaParameter.getName());

					if ((j + 1) != parameters.length) {
						sb.append(", ");
					}
				}

				sb.append(")");

				Type[] thrownExceptions = javaMethod.getExceptions();

				Set newExceptions = new LinkedHashSet();

				for (int j = 0; j < thrownExceptions.length; j++) {
					Type thrownException = thrownExceptions[j];

					newExceptions.add(thrownException.getValue());
				}

				newExceptions.add("com.liferay.portal.SystemException");

				if (newExceptions.size() > 0) {
					sb.append(" throws ");

					Iterator itr = newExceptions.iterator();

					while (itr.hasNext()) {
						sb.append(itr.next());

						if (itr.hasNext()) {
							sb.append(", ");
						}
					}
				}

				sb.append("{");
				sb.append("try {");

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					String parameterTypeName =
						javaParameter.getType().getValue() +
							_getDimensions(javaParameter.getType());

					sb.append("Object paramObj" + j + " = ");

					if (parameterTypeName.equals("boolean")) {
						sb.append("new BooleanWrapper(" + javaParameter.getName() + ");");
					}
					else if (parameterTypeName.equals("double")) {
						sb.append("new DoubleWrapper(" + javaParameter.getName() + ");");
					}
					else if (parameterTypeName.equals("float")) {
						sb.append("new FloatWrapper(" + javaParameter.getName() + ");");
					}
					else if (parameterTypeName.equals("int")) {
						sb.append("new IntegerWrapper(" + javaParameter.getName() + ");");
					}
					else if (parameterTypeName.equals("long")) {
						sb.append("new LongWrapper(" + javaParameter.getName() + ");");
					}
					else if (parameterTypeName.equals("short")) {
						sb.append("new ShortWrapper(" + javaParameter.getName() + ");");
					}
					else {
						sb.append(javaParameter.getName() + ";");

						sb.append("if (" + javaParameter.getName() + " == null) {");
						sb.append("paramObj" + j + " = new NullWrapper(\"" + _getClassName(javaParameter.getType()) + "\");");
						sb.append("}");
					}
				}

				sb.append("MethodWrapper methodWrapper = new MethodWrapper(");
				sb.append(entity.getName() + "ServiceUtil.class.getName(),");
				sb.append("\"" + methodName + "\",");

				if (parameters.length == 0) {
					sb.append("new Object[0]);");
				}
				else {
					sb.append("new Object[] {");

					for (int j = 0; j < parameters.length; j++) {
						sb.append("paramObj" + j);

						if ((j + 1) != parameters.length) {
							sb.append(", ");
						}
					}

					sb.append("});");
				}

				if (!returnTypeName.equals("void")) {
					sb.append("Object returnObj = null;");
				}

				sb.append("try {");

				if (!returnTypeName.equals("void")) {
					sb.append("returnObj =");
				}

				sb.append("TunnelUtil.invoke(httpPrincipal, methodWrapper);");
				sb.append("}");
				sb.append("catch (Exception e) {");

				Iterator itr = newExceptions.iterator();

				while (itr.hasNext()) {
					String exceptionType = (String)itr.next();

					sb.append("if (e instanceof " + exceptionType + ") {");
					sb.append("throw (" + exceptionType + ")e;");
					sb.append("}");
				}

				sb.append("throw new com.liferay.portal.SystemException(e);");
				sb.append("}");

				if (!returnTypeName.equals("void")) {
					if (returnTypeName.equals("boolean")) {
						sb.append("return ((Boolean)returnObj).booleanValue();");
					}
					else if (returnTypeName.equals("double")) {
						sb.append("return ((Double)returnObj).doubleValue();");
					}
					else if (returnTypeName.equals("float")) {
						sb.append("return ((Float)returnObj).floatValue();");
					}
					else if (returnTypeName.equals("int")) {
						sb.append("return ((Integer)returnObj).intValue();");
					}
					else if (returnTypeName.equals("long")) {
						sb.append("return ((Long)returnObj).longValue();");
					}
					else if (returnTypeName.equals("short")) {
						sb.append("return ((Short)returnObj).shortValue();");
					}
					else {
						sb.append("return (" + returnTypeName + ")returnObj;");
					}
				}

				sb.append("}");
				sb.append("catch (com.liferay.portal.SystemException se) {");
				sb.append("_log.error(se, se);");
				sb.append("throw se;");
				sb.append("}");
				sb.append("}");
			}
		}

		// Fields

		if (sb.indexOf("_log.") != -1) {
			sb.append("private static Log _log = LogFactoryUtil.getLog(" + entity.getName() + "ServiceHttp.class);");
		}

		// Class close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_outputPath + "/service/http/" + entity.getName() + "ServiceHttp.java");

		writeFile(ejbFile, sb.toString());
	}

	private void _createServiceImpl(Entity entity, int sessionType) throws IOException {
		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service.impl;");

		// Imports

		sb.append("import " + _packagePath + ".service." + entity.getName() + _getSessionTypeName(sessionType) + "Service;");

		if (sessionType == _REMOTE) {
			sb.append("import com.liferay.portal.service.impl.PrincipalBean;");
		}

		// Class declaration

		sb.append("public class " + entity.getName() + _getSessionTypeName(sessionType) + "ServiceImpl " + (sessionType == _REMOTE ? "extends PrincipalBean " : "") + "implements " + entity.getName() + _getSessionTypeName(sessionType) + "Service {");

		// Class close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_outputPath + "/service/impl/" + entity.getName() + _getSessionTypeName(sessionType) + "ServiceImpl.java");

		if (!ejbFile.exists()) {
			writeFile(ejbFile, sb.toString());
		}
	}

	private void _createServiceJSON(Entity entity) throws IOException {
		JavaClass javaClass = _getJavaClass(_outputPath + "/service/impl/" + entity.getName() + "ServiceImpl.java");

		JavaMethod[] methods = javaClass.getMethods();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service.http;");

		// Imports

		if (_hasSoapMethods(javaClass)) {
			sb.append("import " + _packagePath + ".service." + entity.getName() + "ServiceUtil;");
		}

		sb.append("import org.json.JSONArray;");
		sb.append("import org.json.JSONObject;");

		// Class declaration

		sb.append("public class " + entity.getName() + "ServiceJSON {");

		// Methods

		for (int i = 0; i < methods.length; i++) {
			JavaMethod javaMethod = methods[i];

			String methodName = javaMethod.getName();

			if (!javaMethod.isConstructor() && javaMethod.isPublic() && _isCustomMethod(javaMethod) && _isSoapMethod(javaMethod)) {
				String returnValueName = javaMethod.getReturns().getValue();
				String returnValueDimension = _getDimensions(javaMethod.getReturns());

				String extendedModelName = _packagePath + ".model." + entity.getName();
				String soapModelName = "JSONObject";

				sb.append("public static ");

				if (returnValueName.equals(extendedModelName)) {
					sb.append(soapModelName + returnValueDimension);
				}
				else if (returnValueName.equals("java.util.List")) {
					if (entity.hasColumns()) {
						sb.append("JSONArray");
					}
					else {
						sb.append("java.util.List");
					}
				}
				else {
					sb.append(returnValueName + returnValueDimension);
				}

				sb.append(" " + methodName + "(");

				JavaParameter[] parameters = javaMethod.getParameters();

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					String parameterTypeName = javaParameter.getType().getValue() + _getDimensions(javaParameter.getType());

					if (parameterTypeName.equals("java.util.Locale")) {
						parameterTypeName = "String";
					}

					sb.append(parameterTypeName + " " + javaParameter.getName());

					if ((j + 1) != parameters.length) {
						sb.append(", ");
					}
				}

				sb.append(")");

				Type[] thrownExceptions = javaMethod.getExceptions();

				Set newExceptions = new LinkedHashSet();

				for (int j = 0; j < thrownExceptions.length; j++) {
					Type thrownException = thrownExceptions[j];

					newExceptions.add(thrownException.getValue());
				}

				newExceptions.add("java.rmi.RemoteException");

				if (newExceptions.size() > 0) {
					sb.append(" throws ");

					Iterator itr = newExceptions.iterator();

					while (itr.hasNext()) {
						sb.append(itr.next());

						if (itr.hasNext()) {
							sb.append(", ");
						}
					}
				}

				sb.append("{");

				if (!returnValueName.equals("void")) {
					sb.append(returnValueName + returnValueDimension + " returnValue = ");
				}

				sb.append(entity.getName() + "ServiceUtil." + methodName + "(");

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					String parameterTypeName =
						javaParameter.getType().getValue() +
							_getDimensions(javaParameter.getType());

					if (parameterTypeName.equals("java.util.Locale")) {
						sb.append("new java.util.Locale(");
					}

					sb.append(javaParameter.getName());

					if (parameterTypeName.equals("java.util.Locale")) {
						sb.append(")");
					}

					if ((j + 1) != parameters.length) {
						sb.append(", ");
					}
				}

				sb.append(");");

				if (!returnValueName.equals("void")) {
					if (returnValueName.equals(extendedModelName)) {
						sb.append("return " + entity.getName() + "JSONSerializer.toJSONObject(returnValue);");
					}
					else if (entity.hasColumns() && returnValueName.equals("java.util.List")) {
						sb.append("return " + entity.getName() + "JSONSerializer.toJSONArray(returnValue);");
					}
					else {
						sb.append("return returnValue;");
					}
				}

				sb.append("}");
			}
		}

		// Fields

		if (sb.indexOf("_log.") != -1) {
			sb.append("private static Log _log = LogFactoryUtil.getLog(" + entity.getName() + "ServiceJSON.class);");
		}

		// Class close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_outputPath + "/service/http/" + entity.getName() + "ServiceJSON.java");

		writeFile(ejbFile, sb.toString());
	}

	private void _createServiceJSONSerializer(Entity entity) throws IOException {
		List regularColList = entity.getRegularColList();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service.http;");

		// Imports

		sb.append("import " + _packagePath + ".model." + entity.getName() + ";");
		sb.append("import java.util.List;");
		sb.append("import org.json.JSONArray;");
		sb.append("import org.json.JSONObject;");

		// Class declaration

		sb.append("public class " + entity.getName() + "JSONSerializer {");

		// Methods

		sb.append("public static JSONObject toJSONObject(" + entity.getName() + " model) {");
		sb.append("JSONObject jsonObj = new JSONObject();");

		for (int i = 0; i < regularColList.size(); i++) {
			EntityColumn col = (EntityColumn)regularColList.get(i);

			if (col.isPrimitiveType()) {
				sb.append("jsonObj.put(\"" + col.getName() + "\", model.get" + col.getMethodName() + "());");
			}
			else {
				sb.append("jsonObj.put(\"" + col.getName() + "\", model.get" + col.getMethodName() + "().toString());");
			}
		}

		sb.append("return jsonObj;");
		sb.append("}");

		sb.append("public static JSONArray toJSONArray(List models) {");
		sb.append("JSONArray jsonArray = new JSONArray();");
		sb.append("for (int i = 0; i < models.size(); i++) {");
		sb.append(entity.getName() + " model = (" + entity.getName() + ")models.get(i);");
		sb.append("jsonArray.put(toJSONObject(model));");
		sb.append("}");
		sb.append("return jsonArray;");
		sb.append("}");

		// Class close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_outputPath + "/service/http/" + entity.getName() + "JSONSerializer.java");

		writeFile(ejbFile, sb.toString());
	}

	private void _createServiceSoap(Entity entity) throws IOException {
		JavaClass javaClass = _getJavaClass(_outputPath + "/service/impl/" + entity.getName() + "ServiceImpl.java");

		JavaMethod[] methods = javaClass.getMethods();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service.http;");

		// Imports

		if (_hasSoapMethods(javaClass)) {
			sb.append("import " + _packagePath + ".service." + entity.getName() + "ServiceUtil;");
		}

		sb.append("import com.liferay.portal.kernel.log.Log;");
		sb.append("import com.liferay.portal.kernel.log.LogFactoryUtil;");
		sb.append("import java.rmi.RemoteException;");

		// Class declaration

		sb.append("public class " + entity.getName() + "ServiceSoap {");

		// Methods

		for (int i = 0; i < methods.length; i++) {
			JavaMethod javaMethod = methods[i];

			String methodName = javaMethod.getName();

			if (!javaMethod.isConstructor() && javaMethod.isPublic() && _isCustomMethod(javaMethod) && _isSoapMethod(javaMethod)) {
				String returnValueName = javaMethod.getReturns().getValue();
				String returnValueDimension = _getDimensions(javaMethod.getReturns());

				String extendedModelName = _packagePath + ".model." + entity.getName();
				String soapModelName = _packagePath + ".model." + entity.getName() + "Soap";

				sb.append("public static ");

				if (returnValueName.equals(extendedModelName)) {
					sb.append(soapModelName + returnValueDimension);
				}
				else if (returnValueName.equals("java.util.List")) {
					if (entity.hasColumns()) {
						sb.append(soapModelName + "[]");
					}
					else {
						sb.append("java.util.List");
					}
				}
				else {
					sb.append(returnValueName + returnValueDimension);
				}

				sb.append(" " + methodName + "(");

				JavaParameter[] parameters = javaMethod.getParameters();

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					String parameterTypeName = javaParameter.getType().getValue() + _getDimensions(javaParameter.getType());

					if (parameterTypeName.equals("java.util.Locale")) {
						parameterTypeName = "String";
					}

					sb.append(parameterTypeName + " " + javaParameter.getName());

					if ((j + 1) != parameters.length) {
						sb.append(", ");
					}
				}

				sb.append(") throws RemoteException {");
				sb.append("try {");

				if (!returnValueName.equals("void")) {
					sb.append(returnValueName + returnValueDimension + " returnValue = ");
				}

				sb.append(entity.getName() + "ServiceUtil." + methodName + "(");

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					String parameterTypeName =
						javaParameter.getType().getValue() +
							_getDimensions(javaParameter.getType());

					if (parameterTypeName.equals("java.util.Locale")) {
						sb.append("new java.util.Locale(");
					}

					sb.append(javaParameter.getName());

					if (parameterTypeName.equals("java.util.Locale")) {
						sb.append(")");
					}

					if ((j + 1) != parameters.length) {
						sb.append(", ");
					}
				}

				sb.append(");");

				if (!returnValueName.equals("void")) {
					if (returnValueName.equals(extendedModelName)) {
						sb.append("return " + soapModelName + ".toSoapModel(returnValue);");
					}
					else if (entity.hasColumns() && returnValueName.equals("java.util.List")) {
						sb.append("return " + soapModelName + ".toSoapModels(returnValue);");
					}
					else {
						sb.append("return returnValue;");
					}
				}

				sb.append("}");

				sb.append("catch (Exception e) {");
				sb.append("_log.error(e, e);");
				sb.append("throw new RemoteException(e.getMessage());");
				sb.append("}");
				sb.append("}");
			}
		}

		// Fields

		if (sb.indexOf("_log.") != -1) {
			sb.append("private static Log _log = LogFactoryUtil.getLog(" + entity.getName() + "ServiceSoap.class);");
		}

		// Class close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_outputPath + "/service/http/" + entity.getName() + "ServiceSoap.java");

		writeFile(ejbFile, sb.toString());
	}

	private void _createServiceUtil(Entity entity, int sessionType) throws IOException {
		JavaClass javaClass = _getJavaClass(_serviceOutputPath + "/service/" + entity.getName() + (sessionType != _REMOTE ? "Local" : "") + "Service.java");

		JavaMethod[] methods = javaClass.getMethods();

		StringBuffer sb = new StringBuffer();

		// Package

		sb.append("package " + _packagePath + ".service;");

		// Class declaration

		sb.append("public class " + entity.getName() + _getSessionTypeName(sessionType) + "ServiceUtil {");

		// Methods

		for (int i = 0; i < methods.length; i++) {
			JavaMethod javaMethod = methods[i];

			String methodName = javaMethod.getName();

			if (!javaMethod.isConstructor() && javaMethod.isPublic() && _isCustomMethod(javaMethod)) {
				sb.append("public static " + javaMethod.getReturns().getValue() + _getDimensions(javaMethod.getReturns()) + " " + methodName + "(");

				JavaParameter[] parameters = javaMethod.getParameters();

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					sb.append(javaParameter.getType().getValue() + _getDimensions(javaParameter.getType()) + " " + javaParameter.getName());

					if ((j + 1) != parameters.length) {
						sb.append(", ");
					}
				}

				sb.append(")");

				Type[] thrownExceptions = javaMethod.getExceptions();

				Set newExceptions = new LinkedHashSet();

				for (int j = 0; j < thrownExceptions.length; j++) {
					Type thrownException = thrownExceptions[j];

					newExceptions.add(thrownException.getValue());
				}

				if (newExceptions.size() > 0) {
					sb.append(" throws ");

					Iterator itr = newExceptions.iterator();

					while (itr.hasNext()) {
						sb.append(itr.next());

						if (itr.hasNext()) {
							sb.append(", ");
						}
					}
				}

				sb.append("{");
				sb.append(entity.getName() + _getSessionTypeName(sessionType) + "Service " + entity.getVarName() + _getSessionTypeName(sessionType) + "Service = " + entity.getName() + _getSessionTypeName(sessionType) + "ServiceFactory.getService();");

				if (!javaMethod.getReturns().getValue().equals("void")) {
					sb.append("return ");
				}

				sb.append(entity.getVarName() + _getSessionTypeName(sessionType) + "Service." + methodName + "(");

				for (int j = 0; j < parameters.length; j++) {
					JavaParameter javaParameter = parameters[j];

					sb.append(javaParameter.getName());

					if ((j + 1) != parameters.length) {
						sb.append(", ");
					}
				}

				sb.append(");");
				sb.append("}");
			}
		}

		// Class close brace

		sb.append("}");

		// Write file

		File ejbFile = new File(_serviceOutputPath + "/service/" + entity.getName() + _getSessionTypeName(sessionType) + "ServiceUtil.java");

		writeFile(ejbFile, sb.toString());

		ejbFile = new File(_outputPath + "/service/spring/" + entity.getName() + _getSessionTypeName(sessionType) + "ServiceUtil.java");

		if (ejbFile.exists()) {
			System.out.println("Relocating " + ejbFile);

			ejbFile.delete();
		}
	}

	private void _createSpringXML(boolean enterprise) throws IOException {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < _ejbList.size(); i++) {
			Entity entity = (Entity)_ejbList.get(i);

			if (entity.hasLocalService()) {
				_createSpringXMLSession(entity, sb, _LOCAL, enterprise);
			}

			if (entity.hasRemoteService()) {
				_createSpringXMLSession(entity, sb, _REMOTE, enterprise);
			}

			_createSpringXMLSession(entity, sb);
		}

		File xmlFile = null;

		if (enterprise) {
			xmlFile = new File(_springEntFileName);
		}
		else {
			xmlFile = new File(_springProFileName);
		}

		if (!xmlFile.exists()) {
			String content =
				"<?xml version=\"1.0\"?>\n" +
				"<!DOCTYPE beans PUBLIC \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\">\n" +
				"\n" +
				"<beans>\n" +
				"</beans>";

			FileUtil.write(xmlFile, content);
		}

		String oldContent = FileUtil.read(xmlFile);
		String newContent = _fixSpringXML(oldContent);

		int x = oldContent.indexOf("<beans>");
		int y = oldContent.lastIndexOf("</beans>");

		int firstSession = newContent.indexOf(
			"<bean id=\"" +  _packagePath + ".service.", x);

		int lastSession = newContent.lastIndexOf(
			"<bean id=\"" +  _packagePath + ".service.", y);

		if (firstSession == -1 || firstSession > y) {
			x = newContent.indexOf("</beans>");
			newContent =
				newContent.substring(0, x) + sb.toString() +
				newContent.substring(x, newContent.length());
		}
		else {
			firstSession = newContent.lastIndexOf("<bean", firstSession) - 1;
			lastSession = newContent.indexOf("</bean>", lastSession) + 8;

			newContent =
				newContent.substring(0, firstSession) + sb.toString() +
				newContent.substring(lastSession, newContent.length());
		}

		if (!oldContent.equals(newContent)) {
			FileUtil.write(xmlFile, newContent);
		}
	}

	private void _createSpringXMLSession(Entity entity, StringBuffer sb, int sessionType, boolean enterprise) {
		if (enterprise) {
			sb.append("\t<bean id=\"" + _packagePath + ".service." + entity.getName() + _getSessionTypeName(sessionType) + "Service.enterprise\" class=\"" + (sessionType == _LOCAL ? "com.liferay.portal.spring.ejb.LocalSessionFactoryBean" : "com.liferay.portal.spring.ejb.RemoteSessionFactoryBean") + "\" lazy-init=\"true\">\n");

			sb.append("\t\t<property name=\"businessInterface\">\n");
			sb.append("\t\t\t<value>" + _packagePath + ".service." + entity.getName() + _getSessionTypeName(sessionType) + "Service</value>\n");
			sb.append("\t\t</property>\n");

			sb.append("\t\t<property name=\"jndiName\">\n");

			if (sessionType == _LOCAL) {
				sb.append("\t\t\t<value>ejb/liferay/" + entity.getName() + "LocalServiceHome</value>\n");
			}
			else {
				sb.append("\t\t\t<value>" + StringUtil.replace(_packagePath + ".service.ejb.", ".", "_") + entity.getName() + _getSessionTypeName(sessionType) + "ServiceEJB</value>\n");
			}

			sb.append("\t\t</property>\n");

			sb.append("\t</bean>\n");
		}

		sb.append("\t<bean id=\"" + _packagePath + ".service." + entity.getName() + _getSessionTypeName(sessionType) + "Service.professional\" class=\"" + _packagePath + ".service.impl." + entity.getName() + _getSessionTypeName(sessionType) + "ServiceImpl\" lazy-init=\"true\" />\n");

		sb.append("\t<bean id=\"" + _packagePath + ".service." + entity.getName() + _getSessionTypeName(sessionType) + "Service.transaction\" class=\"org.springframework.transaction.interceptor.TransactionProxyFactoryBean\" lazy-init=\"true\">\n");
		sb.append("\t\t<property name=\"transactionManager\">\n");
		sb.append("\t\t\t<ref bean=\"liferayTransactionManager\" />\n");
		sb.append("\t\t</property>\n");
		sb.append("\t\t<property name=\"target\">\n");
		sb.append("\t\t\t<ref bean=\"" + _packagePath + ".service." + entity.getName() + _getSessionTypeName(sessionType) + "Service.professional\" />\n");
		sb.append("\t\t</property>\n");
		sb.append("\t\t<property name=\"transactionAttributes\">\n");
		sb.append("\t\t\t<props>\n");
		//sb.append("\t\t\t\t<prop key=\"*\">PROPAGATION_REQUIRED,-PortalException,-SystemException</prop>\n");
		sb.append("\t\t\t\t<prop key=\"*\">PROPAGATION_REQUIRED</prop>\n");
		sb.append("\t\t\t</props>\n");
		sb.append("\t\t</property>\n");
		sb.append("\t</bean>\n");

		sb.append("\t<bean id=\"" + _packagePath + ".service." + entity.getName() + _getSessionTypeName(sessionType) + "ServiceFactory\" class=\"" + _packagePath + ".service." + entity.getName() + _getSessionTypeName(sessionType) + "ServiceFactory\" lazy-init=\"true\">\n");
		sb.append("\t\t<property name=\"service\">\n");
		sb.append("\t\t\t<ref bean=\"" + _packagePath + ".service." + entity.getName() + _getSessionTypeName(sessionType) + "Service." + (enterprise ? "enterprise" : "transaction") + "\" />\n");
		sb.append("\t\t</property>\n");
		sb.append("\t</bean>\n");
	}

	private void _createSpringXMLSession(Entity entity, StringBuffer sb) {
		if (entity.hasColumns()) {
			sb.append("\t<bean id=\"" + _packagePath + ".service.persistence." + entity.getName() + "Persistence\" class=\"" + entity.getPersistenceClass() + "\" lazy-init=\"true\">\n");
			sb.append("\t\t<property name=\"dataSource\">\n");
			sb.append("\t\t\t<ref bean=\"" + entity.getDataSource() + "\" />\n");
			sb.append("\t\t</property>\n");
			sb.append("\t\t<property name=\"sessionFactory\">\n");
			sb.append("\t\t\t<ref bean=\"" + entity.getSessionFactory() + "\" />\n");
			sb.append("\t\t</property>\n");
			sb.append("\t</bean>\n");

			sb.append("\t<bean id=\"" + _packagePath + ".service.persistence." + entity.getName() + "Util\" class=\"" + _packagePath + ".service.persistence." + entity.getName() + "Util\" lazy-init=\"true\">\n");
			sb.append("\t\t<property name=\"persistence\">\n");
			sb.append("\t\t\t<ref bean=\"" + _packagePath + ".service.persistence." + entity.getName() + "Persistence\" />\n");
			sb.append("\t\t</property>\n");
			sb.append("\t</bean>\n");
		}
	}

	private void _createSQLIndexes() throws IOException {
		String sqlPath = _portalRoot + "/sql";

		// indexes.sql

		File sqlFile = new File(sqlPath + "/indexes.sql");

		Map indexSQLs = new TreeMap();

		BufferedReader br = new BufferedReader(new FileReader(sqlFile));

		while (true) {
			String indexSQL = br.readLine();

			if (indexSQL == null) {
				break;
			}

			if (Validator.isNotNull(indexSQL.trim())) {
				int pos = indexSQL.indexOf(" on ");

				String indexSpec = indexSQL.substring(pos + 4);

				indexSQLs.put(indexSpec, indexSQL);
			}
		}

		br.close();

		// indexes.properties

		File propsFile = new File(sqlPath + "/indexes.properties");

		Map indexProps = new TreeMap();

		br = new BufferedReader(new FileReader(propsFile));

		while (true) {
			String indexMapping = br.readLine();

			if (indexMapping == null) {
				break;
			}

			if (Validator.isNotNull(indexMapping.trim())) {
				String[] splitIndexMapping = indexMapping.split("\\=");

				indexProps.put(splitIndexMapping[1], splitIndexMapping[0]);
			}
		}

		br.close();

		// indexes.sql

		for (int i = 0; i < _ejbList.size(); i++) {
			Entity entity = (Entity)_ejbList.get(i);

			List finderList = entity.getFinderList();

			for (int j = 0; j < finderList.size(); j++) {
				EntityFinder finder = (EntityFinder)finderList.get(j);

				if (finder.isDBIndex()) {
					StringBuffer sb = new StringBuffer();

					sb.append(entity.getTable() + " (");

					List finderColsList = finder.getColumns();

					for (int k = 0; k < finderColsList.size(); k++) {
						EntityColumn col = (EntityColumn)finderColsList.get(k);

						sb.append(col.getDBName());

						if ((k + 1) != finderColsList.size()) {
							sb.append(", ");
						}
					}

					sb.append(");");

					String indexSpec = sb.toString();

					String indexHash =
						Integer.toHexString(indexSpec.hashCode()).toUpperCase();

					String indexName = "IX_" + indexHash;

					sb = new StringBuffer();

					sb.append("create index " + indexName + " on ");
					sb.append(indexSpec);

					indexSQLs.put(indexSpec, sb.toString());

					String finderName =
						entity.getTable() + StringPool.PERIOD +
							finder.getName();

					indexProps.put(finderName, indexName);
				}
			}
		}

		StringBuffer sb = new StringBuffer();

		Iterator itr = indexSQLs.values().iterator();

		String prevEntityName = null;

		while (itr.hasNext()) {
			String indexSQL = (String)itr.next();

			String entityName = indexSQL.split(" ")[4];

			if ((prevEntityName != null) &&
				(!prevEntityName.equals(entityName))) {

				sb.append("\n");
			}

			sb.append(indexSQL);

			if (itr.hasNext()) {
				sb.append("\n");
			}

			prevEntityName = entityName;
		}

		FileUtil.write(sqlFile, sb.toString(), true);

		// indexes.properties

		sb = new StringBuffer();

		itr = indexProps.keySet().iterator();

		prevEntityName = null;

		while (itr.hasNext()) {
			String finderName = (String)itr.next();

			String indexName = (String)indexProps.get(finderName);

			String entityName = finderName.split("\\.")[0];

			if ((prevEntityName != null) &&
				(!prevEntityName.equals(entityName))) {

				sb.append("\n");
			}

			sb.append(indexName + StringPool.EQUAL + finderName);

			if (itr.hasNext()) {
				sb.append("\n");
			}

			prevEntityName = entityName;
		}

		FileUtil.write(propsFile, sb.toString(), true);
	}

	private void _createSQLSequences() throws IOException {
		String sqlPath = _portalRoot + "/sql";

		File sqlFile = new File(sqlPath + "/sequences.sql");

		Set sequenceSQLs = new TreeSet();

		BufferedReader br = new BufferedReader(new FileReader(sqlFile));

		while (true) {
			String sequenceSQL = br.readLine();

			if (sequenceSQL == null) {
				break;
			}

			if (Validator.isNotNull(sequenceSQL)) {
				sequenceSQLs.add(sequenceSQL);
			}
		}

		br.close();

		for (int i = 0; i < _ejbList.size(); i++) {
			Entity entity = (Entity)_ejbList.get(i);

			List columnList = entity.getColumnList();

			for (int j = 0; j < columnList.size(); j++) {
				EntityColumn column = (EntityColumn)columnList.get(j);

				if ("sequence".equals(column.getIdType())) {
					StringBuffer sb = new StringBuffer();

					String sequenceName = column.getIdParam();

					if (sequenceName.length() > 30) {
						sequenceName = sequenceName.substring(0, 30);
					}

					sb.append("create sequence " + sequenceName + ";");

					String sequenceSQL = sb.toString();

					if (!sequenceSQLs.contains(sequenceSQL)) {
						sequenceSQLs.add(sequenceSQL);
					}
				}
			}
		}

		StringBuffer sb = new StringBuffer();

		Iterator itr = sequenceSQLs.iterator();

		while (itr.hasNext()) {
			String sequenceSQL = (String)itr.next();

			sb.append(sequenceSQL);

			if (itr.hasNext()) {
				sb.append("\n");
			}
		}

		FileUtil.write(sqlFile, sb.toString(), true);
	}

	private void _createSQLTables() throws IOException {
		String sqlPath = _portalRoot + "/sql";

		File sqlFile = new File(sqlPath + "/portal-tables.sql");

		if (!sqlFile.exists()) {
			FileUtil.write(sqlFile, StringPool.BLANK);
		}

		for (int i = 0; i < _ejbList.size(); i++) {
			Entity entity = (Entity)_ejbList.get(i);

			List pkList = entity.getPKList();
			List regularColList = entity.getRegularColList();

			if (regularColList.size() > 0) {
				StringBuffer sb = new StringBuffer();

				sb.append(_CREATE_TABLE + entity.getTable() + " (\n");

				for (int j = 0; j < regularColList.size(); j++) {
					EntityColumn col = (EntityColumn)regularColList.get(j);

					String colName = col.getName();
					String colType = col.getType();
					String colIdType = col.getIdType();

					sb.append("\t" + col.getDBName());
					sb.append(" ");

					if (colType.equalsIgnoreCase("boolean")) {
						sb.append("BOOLEAN");
					}
					else if (colType.equalsIgnoreCase("double") ||
							 colType.equalsIgnoreCase("float")) {

						sb.append("DOUBLE");
					}
					else if (colType.equals("int") ||
							 colType.equals("Integer") ||
							 colType.equalsIgnoreCase("short")) {

						sb.append("INTEGER");
					}
					else if (colType.equalsIgnoreCase("long")) {
						sb.append("LONG");
					}
					else if (colType.equals("String")) {
						Map hints = ModelHintsUtil.getHints(_packagePath + ".model." + entity.getName(), colName);

						int maxLength = 75;

						if (hints != null) {
							maxLength = GetterUtil.getInteger(
								(String)hints.get("max-length"), maxLength);
						}

						if (maxLength < 4000) {
							sb.append("VARCHAR(" + maxLength + ")");
						}
						else if (maxLength == 4000) {
							sb.append("STRING");
						}
						else if (maxLength > 4000) {
							sb.append("TEXT");
						}
					}
					else if (colType.equals("Date")) {
						sb.append("DATE null");
					}
					else {
						sb.append("invalid");
					}

					if (col.isPrimary() || colName.equals("groupId") ||
						colName.equals("companyId") ||
						colName.equals("userId")) {

						if (!col.isPrimitiveType()) {
							sb.append(" not null");
						}

						if (col.isPrimary() && !entity.hasCompoundPK()) {
							sb.append(" primary key");
						}
					}
					else if (colType.equals("String")) {
						sb.append(" null");
					}

					if (Validator.isNotNull(colIdType) &&
						colIdType.equals("identity")) {

						sb.append(" IDENTITY");
					}

					if (((j + 1) != regularColList.size()) ||
						(entity.hasCompoundPK())) {

						sb.append(",");
					}

					sb.append("\n");
				}

				if (entity.hasCompoundPK()) {
					sb.append("\tprimary key (");

					for (int k = 0; k < pkList.size(); k++) {
						EntityColumn pk = (EntityColumn)pkList.get(k);

						sb.append(pk.getDBName());

						if ((k + 1) != pkList.size()) {
							sb.append(", ");
						}
					}

					sb.append(")\n");
				}

				sb.append(");");

				String newCreateTableString = sb.toString();

				_createSQLTables(sqlFile, newCreateTableString, entity, true);
				_createSQLTables(new File(sqlPath + "/update-4.2.0-4.3.0.sql"), newCreateTableString, entity, false);
			}
		}
	}

	private void _createSQLTables(File sqlFile, String newCreateTableString, Entity entity, boolean addMissingTables) throws IOException {
		if (!sqlFile.exists()) {
			FileUtil.write(sqlFile, StringPool.BLANK);
		}

		String content = FileUtil.read(sqlFile);

		int x = content.indexOf(_CREATE_TABLE + entity.getTable() + " (");
		int y = content.indexOf(");", x);

		if (x != -1) {
			String oldCreateTableString = content.substring(x + 1, y);

			if (!oldCreateTableString.equals(newCreateTableString)) {
				content =
					content.substring(0, x) + newCreateTableString +
						content.substring(y + 2, content.length());

				FileUtil.write(sqlFile, content);
			}
		}
		else if (addMissingTables) {
			StringBuffer sb = new StringBuffer();

			BufferedReader br = new BufferedReader(new StringReader(content));

			String line = null;
			boolean appendNewTable = true;

			while ((line = br.readLine()) != null) {
				if (appendNewTable && line.startsWith(_CREATE_TABLE)) {
					x = _CREATE_TABLE.length();
					y = line.indexOf(" ", x);

					String tableName = line.substring(x, y);

					if (tableName.compareTo(entity.getTable()) > 0) {
						sb.append(newCreateTableString + "\n\n");

						appendNewTable = false;
					}
				}

				sb.append(line).append('\n');
			}

			if (appendNewTable) {
				sb.append("\n" + newCreateTableString);
			}

			br.close();

			FileUtil.write(sqlFile, sb.toString(), true);
		}
	}

	private String _fixHBMXML(String content) throws IOException {
		StringBuffer sb = new StringBuffer();

		BufferedReader br = new BufferedReader(new StringReader(content));

		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.startsWith("\t<class name=\"")) {
				line = StringUtil.replace(
					line,
					new String[] {
						".service.persistence.", "HBM\" table=\""
					},
					new String[] {
						".model.", "\" table=\""
					});

				if (line.indexOf(".model.impl.") == -1) {
					line = StringUtil.replace(
						line,
						new String[] {
							".model.", "\" table=\""
						},
						new String[] {
							".model.impl.", "Impl\" table=\""
						});
				}
			}

			sb.append(line);
			sb.append('\n');
		}

		br.close();

		return sb.toString().trim();
	}

	private String _fixSpringXML(String content) throws IOException {
		return StringUtil.replace(content, ".service.spring.", ".service.");
	}

	private String _getClassName(Type type) {
		int dimensions = type.getDimensions();
		String name = type.getValue();

		if (dimensions > 0) {
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < dimensions; i++) {
				sb.append("[");
			}

			if (name.equals("boolean")) {
				return sb.toString() + "Z";
			}
			else if (name.equals("byte")) {
				return sb.toString() + "B";
			}
			else if (name.equals("char")) {
				return sb.toString() + "C";
			}
			else if (name.equals("double")) {
				return sb.toString() + "D";
			}
			else if (name.equals("float")) {
				return sb.toString() + "F";
			}
			else if (name.equals("int")) {
				return sb.toString() + "I";
			}
			else if (name.equals("long")) {
				return sb.toString() + "J";
			}
			else if (name.equals("short")) {
				return sb.toString() + "S";
			}
			else {
				return sb.toString() + "L" + name + ";";
			}
		}

		return name;
	}

	private String _getDimensions(Type type) {
		String dimensions = "";

		for (int i = 0; i < type.getDimensions(); i++) {
			dimensions += "[]";
		}

		return dimensions;
	}

	private JavaClass _getJavaClass(String fileName) throws IOException {
		int pos = fileName.indexOf("src/") + 3;

		String srcFile = fileName.substring(pos + 1, fileName.length());
		String className = StringUtil.replace(
			srcFile.substring(0, srcFile.length() - 5), "/", ".");

		JavaDocBuilder builder = new JavaDocBuilder();

		builder.addSource(new File(fileName));

		return builder.getClassByName(className);
	}

	private String _getNoSuchEntityException(Entity entity) {
		String noSuchEntityException = entity.getName();

		if (Validator.isNull(entity.getPortletShortName()) || noSuchEntityException.startsWith(entity.getPortletShortName())) {
			noSuchEntityException = noSuchEntityException.substring(entity.getPortletShortName().length(), noSuchEntityException.length());
		}

		noSuchEntityException = "NoSuch" + noSuchEntityException;

		return noSuchEntityException;
	}

	private String _getPrimitiveObj(String type) {
		if (type.equals("boolean")) {
			return "Boolean";
		}
		else if (type.equals("double")) {
			return "Double";
		}
		else if (type.equals("float")) {
			return "Float";
		}
		else if (type.equals("int")) {
			return "Integer";
		}
		else if (type.equals("long")) {
			return "Long";
		}
		else if (type.equals("short")) {
			return "Short";
		}
		else {
			return type;
		}
	}

	private String _getSessionTypeName(int sessionType) {
		if (sessionType == _LOCAL) {
			return "Local";
		}
		else {
			return "";
		}
	}

	private String _getSqlType(String model, String field, String type) {
		if (type.equals("boolean")) {
			return "BOOLEAN";
		}
		else if (type.equals("double")) {
			return "DOUBLE";
		}
		else if (type.equals("float")) {
			return "FLOAT";
		}
		else if (type.equals("int")) {
			return "INTEGER";
		}
		else if (type.equals("long")) {
			return "BIGINT";
		}
		else if (type.equals("short")) {
			return "INTEGER";
		}
		else if (type.equals("Date")) {
			return "TIMESTAMP";
		}
		else if (type.equals("String")) {
			Map hints = ModelHintsUtil.getHints(model, field);

			if (hints != null) {
				int maxLength = GetterUtil.getInteger(
					(String)hints.get("max-length"));

				if (maxLength == 2000000) {
					return "CLOB";
				}
			}

			return "VARCHAR";
		}
		else {
			return null;
		}
	}

	private boolean _hasHttpMethods(JavaClass javaClass) {
		JavaMethod[] methods = javaClass.getMethods();

		for (int i = 0; i < methods.length; i++) {
			JavaMethod javaMethod = methods[i];

			if (!javaMethod.isConstructor() && javaMethod.isPublic() &&
				_isCustomMethod(javaMethod)) {

				return true;
			}
		}

		return false;
	}

	private boolean _hasSoapMethods(JavaClass javaClass) {
		JavaMethod[] methods = javaClass.getMethods();

		for (int i = 0; i < methods.length; i++) {
			JavaMethod javaMethod = methods[i];

			if (!javaMethod.isConstructor() && javaMethod.isPublic() &&
				_isCustomMethod(javaMethod) && _isSoapMethod(javaMethod)) {

				return true;
			}
		}

		return false;
	}

	private boolean _isCustomMethod(JavaMethod method) {
		String methodName = method.getName();

		if (methodName.equals("hasAdministrator") ||
			methodName.equals("ejbCreate") ||
			methodName.equals("ejbRemove") ||
			methodName.equals("ejbActivate") ||
			methodName.equals("ejbPassivate") ||
			methodName.equals("getSessionContext") ||
			methodName.equals("setSessionContext") ||
			methodName.equals("hashCode") ||
			methodName.equals("getClass") ||
			methodName.equals("wait") ||
			methodName.equals("equals") ||
			methodName.equals("toString") ||
			methodName.equals("notify") ||
			methodName.equals("notifyAll")) {

			return false;
		}
		else if (methodName.equals("getUser") &&
				 method.getParameters().length == 0) {

			return false;
		}
		else if (methodName.equals("getUserId") &&
				 method.getParameters().length == 0) {

			return false;
		}
		else {
			return true;
		}
	}

	private boolean _isSoapMethod(JavaMethod method) {
		String returnValueName = method.getReturns().getValue();

		if (returnValueName.startsWith("java.io") ||
			returnValueName.equals("java.util.Map") ||
			returnValueName.equals("java.util.Properties") ||
			returnValueName.startsWith("javax")) {

			return false;
		}

		JavaParameter[] parameters = method.getParameters();

		for (int i = 0; i < parameters.length; i++) {
			JavaParameter javaParameter = parameters[i];

			String parameterTypeName =
				javaParameter.getType().getValue() +
					_getDimensions(javaParameter.getType());

			if ((parameterTypeName.indexOf(
					"com.liferay.portal.model.") != -1) ||
				(parameterTypeName.equals(
					"com.liferay.portal.theme.ThemeDisplay")) ||
				(parameterTypeName.equals(
					"com.liferay.portlet.PortletPreferencesImpl")) ||
				 parameterTypeName.startsWith("java.io") ||
				 //parameterTypeName.startsWith("java.util.List") ||
				 //parameterTypeName.startsWith("java.util.Locale") ||
				 parameterTypeName.startsWith("java.util.Map") ||
				 parameterTypeName.startsWith("java.util.Properties") ||
				 parameterTypeName.startsWith("javax")) {

				return false;
			}
		}

		return true;
	}

	private boolean _requiresNullCheck(EntityColumn col) {
		return !col.isPrimitiveType();
	}

	private static final int _REMOTE = 0;

	private static final int _LOCAL = 1;

	private static final String _CREATE_TABLE = "create table ";

	private Set _badTableNames;
	private Set _badCmpFields;
	private String _hbmFileName;
	private String _modelHintsFileName;
	private String _springEntFileName;
	private String _springProFileName;
	private String _beanLocatorUtilClassName;
	private String _serviceDir;
	private String _jsonFileName;
	private String _portalRoot;
	private String _portletName;
	private String _portletShortName;
	private String _portletPackageName;
	private String _outputPath;
	private String _serviceOutputPath;
	private String _packagePath;
	private List _ejbList;

}