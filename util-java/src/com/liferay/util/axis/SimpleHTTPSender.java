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

package com.liferay.util.axis;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.SystemProperties;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import java.util.regex.Pattern;

import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.axis.transport.http.HTTPSender;

/**
 * <a href="SimpleHTTPSender.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SimpleHTTPSender extends HTTPSender {

	public static String getCurrentCookie() {
		return _currentCookie.get();
	}

	public void invoke(MessageContext ctx) throws AxisFault {
		String url = ctx.getStrProp(MessageContext.TRANS_URL);

		if (_pattern.matcher(url).matches()) {
			if (_log.isDebugEnabled()) {
				_log.debug("A match was found for " + url);
			}

			_invoke(ctx, url);
		}
		else {
			if (_log.isDebugEnabled()) {
				_log.debug("No match was found for " + url);
			}

			super.invoke(ctx);

			_registerCurrentCookie(ctx);
		}
	}

	private void _invoke(MessageContext ctx, String url) throws AxisFault {
		try {
			String userName = ctx.getUsername();
			String password = ctx.getPassword();

			if ((userName != null) && (password != null)) {
				Authenticator.setDefault(
					new SimpleAuthenticator(userName, password));
			}

			URL urlObj = new URL(url);

			URLConnection urlc = urlObj.openConnection();

			_writeToConnection(urlc, ctx);
			_readFromConnection(urlc, ctx);
		}
		catch (Exception e) {
			throw AxisFault.makeFault(e);
		}
		finally {
			Authenticator.setDefault(null);
		}
	}

	private void _readFromConnection(URLConnection urlc, MessageContext ctx)
		throws Exception {

		String contentType = urlc.getContentType();
		String contentLocation = urlc.getHeaderField("Content-Location");

		InputStream is = ((HttpURLConnection) urlc).getErrorStream();

		if (is == null) {
			is = urlc.getInputStream();
		}

		is = new BufferedInputStream(is, 8192);

		Message response = new Message(is, false, contentType, contentLocation);

		response.setMessageType(Message.RESPONSE);

		ctx.setResponseMessage(response);
	}

	private void _registerCurrentCookie(MessageContext ctx) {
		String cookie = StringPool.BLANK;

		try {
			cookie = GetterUtil.getString(
				ctx.getStrProp(HTTPConstants.HEADER_COOKIE));
		}
		catch (Throwable t) {
			_log.warn(t);
		}

		_currentCookie.set(cookie);
	}

	private void _writeToConnection(URLConnection urlc, MessageContext ctx)
		throws Exception {

		urlc.setDoOutput(true);

		Message request = ctx.getRequestMessage();

		String contentType = request.getContentType(ctx.getSOAPConstants());

		urlc.setRequestProperty("Content-Type", contentType);

		if (ctx.useSOAPAction()) {
			urlc.setRequestProperty("SOAPAction", ctx.getSOAPActionURI());
		}

		OutputStream os = new BufferedOutputStream(
			urlc.getOutputStream(), 8192);

		request.writeTo(os);

		os.flush();
	}

	private static ThreadLocal<String> _currentCookie =
		new ThreadLocal<String>() {

		protected String initialValue() {
			return StringPool.BLANK;
		}
	};

	private static Log _log = LogFactoryUtil.getLog(SimpleHTTPSender.class);

	private static Pattern _pattern = Pattern.compile(
		SystemProperties.get(
			SimpleHTTPSender.class.getName() + ".regexp.pattern"));

}