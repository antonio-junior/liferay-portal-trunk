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

package com.liferay.portal.util;

import com.liferay.portal.CookieNotSupportedException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="CookieKeys.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Minhchau Dang
 *
 */
public class CookieKeys {

	public static final String COOKIE_SUPPORT = "COOKIE_SUPPORT";

	public static final String COMPANY_ID = "COMPANY_ID";

	public static final String GUEST_LANGUAGE_ID = "GUEST_LANGUAGE_ID";

	public static final String ID = "ID";

	public static final String JSESSIONID = "jsessionid";

	public static final String LOGIN = "LOGIN";

	public static final String PASSWORD = "PASSWORD";

	public static final String REMEMBER_ME = "REMEMBER_ME";

	public static final String SCREEN_NAME = "SCREEN_NAME";

	public static final int MAX_AGE = 31536000;

	public static final int VERSION = 0;

	public static void addCookie(
		HttpServletRequest request, HttpServletResponse response,
		Cookie cookie) {

		if (!PropsValues.SESSION_ENABLE_PERSISTENT_COOKIES ||
			PropsValues.TCK_URL) {

			return;
		}

		// LEP-5175

		String name = cookie.getName();

		String originalValue = cookie.getValue();
		String encodedValue = originalValue;

		if (isEncodedCookie(name)) {
			encodedValue = new String(Hex.encodeHex(originalValue.getBytes()));

			if (_log.isDebugEnabled()) {
				_log.debug("Add encoded cookie " + name);
				_log.debug("Original value " + originalValue);
				_log.debug("Hex encoded value " + encodedValue);
			}
		}

		cookie.setSecure(request.isSecure());
		cookie.setValue(encodedValue);
		cookie.setVersion(VERSION);

		// Setting a cookie will cause the TCK to lose its ability to track
		// sessions

		response.addCookie(cookie);
	}

	public static void addSupportCookie(
		HttpServletRequest request, HttpServletResponse response) {

		Cookie cookieSupportCookie = new Cookie(COOKIE_SUPPORT, "true");

		cookieSupportCookie.setPath(StringPool.SLASH);
		cookieSupportCookie.setMaxAge(MAX_AGE);

		addCookie(request, response, cookieSupportCookie);
	}

	public static String getCookie(HttpServletRequest request, String name) {
		String value = CookieUtil.get(request, name);

		if ((value != null) && isEncodedCookie(name)) {
			try {
				String encodedValue = value;
				String originalValue = new String(
					Hex.decodeHex(encodedValue.toCharArray()));

				if (_log.isDebugEnabled()) {
					_log.debug("Get encoded cookie " + name);
					_log.debug("Hex encoded value " + encodedValue);
					_log.debug("Original value " + originalValue);
				}

				return originalValue;
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e.getMessage());
				}

				return value;
			}
		}

		return value;
	}

	public static String getDomain(HttpServletRequest request) {

		// See LEP-4602 and	LEP-4618.

		if (Validator.isNotNull(PropsValues.SESSION_COOKIE_DOMAIN)) {
			return PropsValues.SESSION_COOKIE_DOMAIN;
		}

		String host = request.getServerName();

		return getDomain(host);
	}

	public static String getDomain(String host) {

		// See LEP-4602 and LEP-4645.

		if (host == null) {
			return null;
		}

		// See LEP-5595.

		if (Validator.isIPAddress(host)) {
			return host;
		}

		int x = host.lastIndexOf(StringPool.PERIOD);

		if (x <= 0) {
			return null;
		}

		int y = host.lastIndexOf(StringPool.PERIOD, x - 1);

		if (y <= 0) {
			return StringPool.PERIOD + host;
		}

		int z = host.lastIndexOf(StringPool.PERIOD, y - 1);

		String domain = null;

		if (z <= 0) {
			domain = host.substring(y);
		}
		else {
			domain = host.substring(z);
		}

		return domain;
	}

	public static boolean hasSessionId(HttpServletRequest request) {
		String jsessionid = getCookie(request, JSESSIONID);

		if (jsessionid != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isEncodedCookie(String name) {
		if (name.equals(ID) || name.equals(LOGIN) || name.equals(PASSWORD) ||
			name.equals(SCREEN_NAME)) {

			return true;
		}
		else {
			return false;
		}
	}

	public static void validateSupportCookie(HttpServletRequest request)
		throws CookieNotSupportedException {

		if (PropsValues.SESSION_ENABLE_PERSISTENT_COOKIES &&
			PropsValues.SESSION_TEST_COOKIE_SUPPORT) {

			String cookieSupport = getCookie(request, COOKIE_SUPPORT);

			if (Validator.isNull(cookieSupport)) {
				throw new CookieNotSupportedException();
			}
		}
	}

	private static Log _log = LogFactory.getLog(CookieKeys.class);

}