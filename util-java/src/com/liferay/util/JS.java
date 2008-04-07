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

package com.liferay.util;

import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <a href="JS.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JS {

	public static String getSafeName(String name) {
		String safeName =
			StringUtil.replace(
				name,
				new String[] {
					StringPool.SPACE, StringPool.DASH, StringPool.PERIOD
				},
				new String[] {
					StringPool.BLANK, StringPool.BLANK, StringPool.BLANK
				});

		return safeName;
	}

	/**
	 * @deprecated Use <code>encodeURIComponent</code>.
	 */
	public static String escape(String s) {
		return encodeURIComponent(s);
	}

	/**
	 * @deprecated Use <code>decodeURIComponent</code>.
	 */
	public static String unescape(String s) {
		return decodeURIComponent(s);
	}

	public static String encodeURIComponent(String s) {

		// Encode URL

		try {
			s = URLEncoder.encode(s, StringPool.UTF8);
		}
		catch (Exception e) {
		}

		// Adjust for JavaScript specific annoyances

		s = StringUtil.replace(s, "+", "%20");
		s = StringUtil.replace(s, "%2B", "+");

		return s;
	}

	public static String decodeURIComponent(String s) {

		// Get rid of all unicode

		s = s.replaceAll("%u[0-9a-fA-F]{4}", StringPool.BLANK);

		// Adjust for JavaScript specific annoyances

		s = StringUtil.replace(s, "+", "%2B");
		s = StringUtil.replace(s, "%20", "+");

		// Decode URL

		try {
			s = URLDecoder.decode(s, StringPool.UTF8);
		}
		catch (Exception e) {
		}

		return s;
	}

	public static String toScript(String[] array) {
		StringMaker sm = new StringMaker();

		sm.append("new Array(");

		for (int i = 0; i < array.length; i++) {
			sm.append("'" + array[i] + "'");

			if (i + 1 < array.length) {
				sm.append(",");
			}
		}

		sm.append(")");

		return sm.toString();
	}

}