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

package com.liferay.portlet;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;

import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Enumeration;
import java.util.Set;

import javax.portlet.PortletContext;
import javax.portlet.PortletRequestDispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="PortletContextImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Brett Randall
 *
 */
public class PortletContextImpl implements PortletContext {

	public PortletContextImpl(Portlet portlet, ServletContext servletContext) {
		_portlet = portlet;
		_servletContext = servletContext;
		_servletContextName = GetterUtil.getString(
			_servletContext.getServletContextName());
	}

	public Object getAttribute(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		return _servletContext.getAttribute(name);
	}

	public Enumeration<String> getAttributeNames() {
		return _servletContext.getAttributeNames();
	}

	public Enumeration<String> getContainerRuntimeOptions() {
		return null;
	}

	public String getInitParameter(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		return _servletContext.getInitParameter(name);
	}

	public Enumeration<String> getInitParameterNames() {
		return _servletContext.getInitParameterNames();
	}

	public int getMajorVersion() {
		return _MAJOR_VERSION;
	}

	public String getMimeType(String file) {
		return _servletContext.getMimeType(file);
	}

	public int getMinorVersion() {
		return _MINOR_VERSION;
	}

	public PortletRequestDispatcher getNamedDispatcher(String name) {
		RequestDispatcher requestDispatcher = null;

		try {
			requestDispatcher = _servletContext.getNamedDispatcher(name);
		}
		catch (IllegalArgumentException iae) {
			return null;
		}

		if (requestDispatcher != null) {
			return new PortletRequestDispatcherImpl(
				requestDispatcher, true, this);
		}
		else {
			return null;
		}
	}

	public Portlet getPortlet() {
		return _portlet;
	}

	public String getPortletContextName() {
		return _servletContextName;
	}

	public String getRealPath(String path) {
		return _servletContext.getRealPath(path);
	}

	public PortletRequestDispatcher getRequestDispatcher(String path) {
		RequestDispatcher requestDispatcher = null;

		try {
			requestDispatcher = _servletContext.getRequestDispatcher(path);
		}
		catch (IllegalArgumentException iae) {
			return null;
		}

		// Workaround for bug in Jetty that returns the default request
		// dispatcher instead of null for an invalid path

		if ((requestDispatcher != null) &&
			(requestDispatcher.getClass().getName().equals(
				"org.mortbay.jetty.servlet.Dispatcher"))) {

			// Dispatcher[/,default[org.mortbay.jetty.servlet.Default]]

			String rdToString = requestDispatcher.toString();

			String rdPath = rdToString.substring(11, rdToString.indexOf(","));

			if (rdPath.equals(StringPool.SLASH) &&
				!path.equals(StringPool.SLASH)) {

				requestDispatcher = null;
			}
		}

		if (requestDispatcher != null) {
			return new PortletRequestDispatcherImpl(
				requestDispatcher, false, this, path);
		}
		else {
			return null;
		}
	}

	public URL getResource(String path) throws MalformedURLException {
		if ((path == null) || (!path.startsWith(StringPool.SLASH))) {
			throw new MalformedURLException();
		}

		return _servletContext.getResource(path);
	}

	public InputStream getResourceAsStream(String path) {
		return _servletContext.getResourceAsStream(path);
	}

	public Set<String> getResourcePaths(String path) {
		return _servletContext.getResourcePaths(path);
	}

	public String getServerInfo() {
		return ReleaseInfo.getServerInfo();
	}

	public ServletContext getServletContext() {
		return _servletContext;
	}

	public boolean isWARFile() {
		PortletApp portletApp = _portlet.getPortletApp();

		return portletApp.isWARFile();
	}

	public void log(String msg) {
		if (_log.isInfoEnabled()) {
			_log.info(msg);
		}
	}

	public void log(String msg, Throwable throwable) {
		if (_log.isInfoEnabled()) {
			_log.info(msg, throwable);
		}
	}

	public void removeAttribute(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		_servletContext.removeAttribute(name);
	}

	public void setAttribute(String name, Object obj) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		_servletContext.setAttribute(name, obj);
	}

	private static int _MAJOR_VERSION = 2;

	private static int _MINOR_VERSION = 0;

	private static Log _log = LogFactory.getLog(PortletContextImpl.class);

	private Portlet _portlet;
	private ServletContext _servletContext;
	private String _servletContextName;

}