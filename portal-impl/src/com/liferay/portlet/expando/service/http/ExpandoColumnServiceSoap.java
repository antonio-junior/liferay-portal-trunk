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

package com.liferay.portlet.expando.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.portlet.expando.service.ExpandoColumnServiceUtil;

import java.rmi.RemoteException;

/**
 * <a href="ExpandoColumnServiceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides a SOAP utility for the
 * <code>com.liferay.portlet.expando.service.ExpandoColumnServiceUtil</code> service
 * utility. The static methods of this class calls the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.portlet.expando.model.ExpandoColumnSoap</code>. If the method in the
 * service utility returns a <code>com.liferay.portlet.expando.model.ExpandoColumn</code>,
 * that is translated to a <code>com.liferay.portlet.expando.model.ExpandoColumnSoap</code>.
 * Methods that SOAP cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/tunnel-web/secure/axis. Set the property
 * <code>tunnel.servlet.hosts.allowed</code> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portlet.expando.service.ExpandoColumnServiceUtil
 * @see com.liferay.portlet.expando.service.http.ExpandoColumnServiceHttp
 * @see com.liferay.portlet.expando.service.model.ExpandoColumnSoap
 *
 */
public class ExpandoColumnServiceSoap {
	public static com.liferay.portlet.expando.model.ExpandoColumnSoap addColumn(
		long tableId, java.lang.String name, int type)
		throws RemoteException {
		try {
			com.liferay.portlet.expando.model.ExpandoColumn returnValue = ExpandoColumnServiceUtil.addColumn(tableId,
					name, type);

			return com.liferay.portlet.expando.model.ExpandoColumnSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteColumn(long columnId) throws RemoteException {
		try {
			ExpandoColumnServiceUtil.deleteColumn(columnId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteColumn(long tableId, java.lang.String name)
		throws RemoteException {
		try {
			ExpandoColumnServiceUtil.deleteColumn(tableId, name);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteColumn(java.lang.String className,
		java.lang.String tableName, java.lang.String name)
		throws RemoteException {
		try {
			ExpandoColumnServiceUtil.deleteColumn(className, tableName, name);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteColumn(long classNameId,
		java.lang.String tableName, java.lang.String name)
		throws RemoteException {
		try {
			ExpandoColumnServiceUtil.deleteColumn(classNameId, tableName, name);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteColumns(long tableId) throws RemoteException {
		try {
			ExpandoColumnServiceUtil.deleteColumns(tableId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteColumns(java.lang.String className,
		java.lang.String tableName) throws RemoteException {
		try {
			ExpandoColumnServiceUtil.deleteColumns(className, tableName);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteColumns(long classNameId,
		java.lang.String tableName) throws RemoteException {
		try {
			ExpandoColumnServiceUtil.deleteColumns(classNameId, tableName);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.expando.model.ExpandoColumnSoap getColumn(
		long columnId) throws RemoteException {
		try {
			com.liferay.portlet.expando.model.ExpandoColumn returnValue = ExpandoColumnServiceUtil.getColumn(columnId);

			return com.liferay.portlet.expando.model.ExpandoColumnSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.expando.model.ExpandoColumnSoap getColumn(
		long tableId, java.lang.String name) throws RemoteException {
		try {
			com.liferay.portlet.expando.model.ExpandoColumn returnValue = ExpandoColumnServiceUtil.getColumn(tableId,
					name);

			return com.liferay.portlet.expando.model.ExpandoColumnSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.expando.model.ExpandoColumnSoap getColumn(
		java.lang.String className, java.lang.String tableName,
		java.lang.String name) throws RemoteException {
		try {
			com.liferay.portlet.expando.model.ExpandoColumn returnValue = ExpandoColumnServiceUtil.getColumn(className,
					tableName, name);

			return com.liferay.portlet.expando.model.ExpandoColumnSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.expando.model.ExpandoColumnSoap getColumn(
		long classNameId, java.lang.String tableName, java.lang.String name)
		throws RemoteException {
		try {
			com.liferay.portlet.expando.model.ExpandoColumn returnValue = ExpandoColumnServiceUtil.getColumn(classNameId,
					tableName, name);

			return com.liferay.portlet.expando.model.ExpandoColumnSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.expando.model.ExpandoColumnSoap[] getColumns(
		long tableId) throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.expando.model.ExpandoColumn> returnValue =
				ExpandoColumnServiceUtil.getColumns(tableId);

			return com.liferay.portlet.expando.model.ExpandoColumnSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.expando.model.ExpandoColumnSoap[] getColumns(
		java.lang.String className, java.lang.String tableName)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.expando.model.ExpandoColumn> returnValue =
				ExpandoColumnServiceUtil.getColumns(className, tableName);

			return com.liferay.portlet.expando.model.ExpandoColumnSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.expando.model.ExpandoColumnSoap[] getColumns(
		long classNameId, java.lang.String tableName) throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.expando.model.ExpandoColumn> returnValue =
				ExpandoColumnServiceUtil.getColumns(classNameId, tableName);

			return com.liferay.portlet.expando.model.ExpandoColumnSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getColumnsCount(long tableId) throws RemoteException {
		try {
			int returnValue = ExpandoColumnServiceUtil.getColumnsCount(tableId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getColumnsCount(java.lang.String className,
		java.lang.String tableName) throws RemoteException {
		try {
			int returnValue = ExpandoColumnServiceUtil.getColumnsCount(className,
					tableName);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getColumnsCount(long classNameId,
		java.lang.String tableName) throws RemoteException {
		try {
			int returnValue = ExpandoColumnServiceUtil.getColumnsCount(classNameId,
					tableName);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.expando.model.ExpandoColumnSoap getDefaultTableColumn(
		java.lang.String className, java.lang.String name)
		throws RemoteException {
		try {
			com.liferay.portlet.expando.model.ExpandoColumn returnValue = ExpandoColumnServiceUtil.getDefaultTableColumn(className,
					name);

			return com.liferay.portlet.expando.model.ExpandoColumnSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.expando.model.ExpandoColumnSoap getDefaultTableColumn(
		long classNameId, java.lang.String name) throws RemoteException {
		try {
			com.liferay.portlet.expando.model.ExpandoColumn returnValue = ExpandoColumnServiceUtil.getDefaultTableColumn(classNameId,
					name);

			return com.liferay.portlet.expando.model.ExpandoColumnSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.expando.model.ExpandoColumnSoap[] getDefaultTableColumns(
		java.lang.String className) throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.expando.model.ExpandoColumn> returnValue =
				ExpandoColumnServiceUtil.getDefaultTableColumns(className);

			return com.liferay.portlet.expando.model.ExpandoColumnSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.expando.model.ExpandoColumnSoap[] getDefaultTableColumns(
		long classNameId) throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.expando.model.ExpandoColumn> returnValue =
				ExpandoColumnServiceUtil.getDefaultTableColumns(classNameId);

			return com.liferay.portlet.expando.model.ExpandoColumnSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getDefaultTableColumnsCount(java.lang.String className)
		throws RemoteException {
		try {
			int returnValue = ExpandoColumnServiceUtil.getDefaultTableColumnsCount(className);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getDefaultTableColumnsCount(long classNameId)
		throws RemoteException {
		try {
			int returnValue = ExpandoColumnServiceUtil.getDefaultTableColumnsCount(classNameId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.expando.model.ExpandoColumnSoap updateColumn(
		long columnId, java.lang.String name, int type)
		throws RemoteException {
		try {
			com.liferay.portlet.expando.model.ExpandoColumn returnValue = ExpandoColumnServiceUtil.updateColumn(columnId,
					name, type);

			return com.liferay.portlet.expando.model.ExpandoColumnSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ExpandoColumnServiceSoap.class);
}