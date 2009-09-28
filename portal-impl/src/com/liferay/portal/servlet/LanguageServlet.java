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

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.servlet.ServletResponseUtil;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <a href="LanguageServlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class LanguageServlet extends HttpServlet {

	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		String path = request.getPathInfo();

		if (_log.isDebugEnabled()) {
			_log.debug("Path " + path);
		}

		if (Validator.isNotNull(path) && path.startsWith(StringPool.SLASH)) {
			path = path.substring(1, path.length());
		}

		String[] pathArray = StringUtil.split(path, StringPool.SLASH);

		if (pathArray.length == 0) {
			_log.error("Language id is not specified");

			return;
		}

		if (pathArray.length == 1) {
			_log.error("Language key is not specified");

			return;
		}

		Locale locale = LocaleUtil.fromLanguageId(pathArray[0]);
		String key = pathArray[1];

		Object[] arguments = null;

		if (pathArray.length > 2) {
			arguments = new Object[pathArray.length - 2];

			System.arraycopy(pathArray, 2, arguments, 0, arguments.length);
		}

		String value = key;

		try {
			if ((arguments == null) || (arguments.length == 0)) {
				value = LanguageUtil.get(locale, key);
			}
			else {
				value = LanguageUtil.format(locale, key, arguments);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		response.setContentType(ContentTypes.TEXT_PLAIN_UTF8);

		ServletResponseUtil.write(response, value.getBytes(StringPool.UTF8));
	}

	private static Log _log = LogFactoryUtil.getLog(LanguageServlet.class);

}