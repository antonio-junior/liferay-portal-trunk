/**
 * Copyright (c) 2000-2006 Liferay, LLC. All rights reserved.
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

package com.liferay.portal.shared.servlet;

import com.liferay.portal.shared.util.PortalClassLoaderUtil;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <a href="PortalServletWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class PortalServletWrapper extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		String servletClass = config.getInitParameter("servlet-class");

		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		try {
			_servlet = (HttpServlet)classLoader.loadClass(
				servletClass).newInstance();
		}
		catch (ClassNotFoundException cnfe) {
			throw new ServletException(cnfe.getMessage());
		}
		catch (IllegalAccessException iae) {
			throw new ServletException(iae.getMessage());
		}
		catch (InstantiationException ie) {
			throw new ServletException(ie.getMessage());
		}

		_servlet.init(config);
	}

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {

		ClassLoader contextClassLoader =
			Thread.currentThread().getContextClassLoader();

		try {
			Thread.currentThread().setContextClassLoader(
				PortalClassLoaderUtil.getClassLoader());

			_servlet.service(req, res);
		}
		finally {
			Thread.currentThread().setContextClassLoader(contextClassLoader);
		}
	}

	public void destroy() {
		ClassLoader contextClassLoader =
			Thread.currentThread().getContextClassLoader();

		try {
			Thread.currentThread().setContextClassLoader(
				PortalClassLoaderUtil.getClassLoader());

			_servlet.destroy();
		}
		finally {
			Thread.currentThread().setContextClassLoader(contextClassLoader);
		}
	}

	private HttpServlet _servlet;

}