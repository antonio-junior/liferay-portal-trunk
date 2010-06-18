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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.io.OutputStreamWriter;
import com.liferay.portal.kernel.io.WriterOutputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncPrintWriter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.jsp.PageContext;

/**
 * <a href="PipingServletResponse.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 */
public class PipingServletResponse extends HttpServletResponseWrapper {

	public PipingServletResponse(
		HttpServletResponse response, OutputStream outputStream) {

		super(response);

		if (outputStream == null) {
			throw new NullPointerException("OutputStream is null");
		}

		_servletOutputStream = new PipingServletOutputStream(outputStream);
	}

	public PipingServletResponse(
		HttpServletResponse response, PrintWriter printWriter) {

		super(response);

		if (printWriter == null) {
			throw new NullPointerException("PrintWriter is null");
		}

		_printWriter = printWriter;
	}

	public PipingServletResponse(
		HttpServletResponse response, ServletOutputStream servletOutputStream) {

		super(response);

		if (servletOutputStream == null) {
			throw new NullPointerException("ServletOutputStream is null");
		}

		_servletOutputStream = servletOutputStream;
	}

	public PipingServletResponse(HttpServletResponse response, Writer writer) {
		super(response);

		if (writer == null) {
			throw new NullPointerException("Writer is null");
		}

		_printWriter = new UnsyncPrintWriter(writer, true);
	}

	public PipingServletResponse(PageContext pageContext) {
		this(
			(HttpServletResponse)pageContext.getResponse(),
			pageContext.getOut());
	}

	public ServletOutputStream getOutputStream()
		throws UnsupportedEncodingException {

		if (_servletOutputStream == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Get OutputStream on a PipingServletResponse " +
					"with Writer. Will do auto convert, but this is very bad " +
					"for performance.");
			}
			_servletOutputStream = new PipingServletOutputStream(
				new WriterOutputStream(_printWriter, getCharacterEncoding(),
					true));
		}

		return  _servletOutputStream;
	}

	public PrintWriter getWriter() throws UnsupportedEncodingException {

		if (_printWriter == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Get Writer on a PipingServletResponse with " +
					"OuputStream. Will do auto convert, but this is very " +
					"bad for performance.");
			}
			_printWriter = new UnsyncPrintWriter(new OutputStreamWriter(
				_servletOutputStream));
		}

		return _printWriter;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PipingServletResponse.class);

	private PrintWriter _printWriter;
	private ServletOutputStream _servletOutputStream;

}