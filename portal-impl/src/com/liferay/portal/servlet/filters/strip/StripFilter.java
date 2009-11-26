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

package com.liferay.portal.servlet.filters.strip;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.KMPSearch;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.portal.servlet.filters.etag.ETagUtil;
import com.liferay.portal.util.MinifierUtil;
import com.liferay.util.servlet.ServletResponseUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <a href="StripFilter.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Shuyang Zhou
 */
public class StripFilter extends BasePortalFilter {

	public static final String SKIP_FILTER =
		StripFilter.class.getName() + "SKIP_FILTER";

	protected int countContinuousWhiteSpace(byte[] oldByteArray, int offset) {
		int count = 0;
		for(int i = offset ; i < oldByteArray.length ; i++) {
			char c = (char) oldByteArray[i];
			if ((c == CharPool.NEW_LINE) ||
				(c == CharPool.RETURN) ||
				(c == CharPool.TAB)) {
				count++;
			}
			else{
				return count;
			}
		}
		return count;
	}

	protected boolean hasMarker(byte[] oldByteArray, int pos, byte[] marker) {
		if ((pos + marker.length) >= oldByteArray.length) {
			return false;
		}

		for (int i = 0; i < marker.length; i++) {
			byte c = marker[i];

			byte oldC = oldByteArray[pos + i + 1];

			if ((c != oldC) &&
				(Character.toUpperCase(c) != oldC)) {

				return false;
			}
		}

		return true;
	}

	protected boolean hasMarker(byte[] oldByteArray, int pos, char[] marker) {
		if ((pos + marker.length) >= oldByteArray.length) {
			return false;
		}

		for (int i = 0; i < marker.length; i++) {
			char c = marker[i];

			char oldC = (char)oldByteArray[pos + i + 1];

			if ((c != oldC) &&
				(Character.toUpperCase(c) != oldC)) {

				return false;
			}
		}

		return true;
	}

	protected boolean isAlreadyFiltered(HttpServletRequest request) {
		if (request.getAttribute(SKIP_FILTER) != null) {
			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isInclude(HttpServletRequest request) {
		String uri = (String)request.getAttribute(
			JavaConstants.JAVAX_SERVLET_INCLUDE_REQUEST_URI);

		if (uri == null) {
			return false;
		}
		else {
			return true;
		}
	}

	protected boolean isStrip(HttpServletRequest request) {
		if (!ParamUtil.getBoolean(request, _STRIP, true)) {
			return false;
		}
		else {

			// Modifying binary content through a servlet filter under certain
			// conditions is bad on performance the user will not start
			// downloading the content until the entire content is modified.

			String lifecycle = ParamUtil.getString(request, "p_p_lifecycle");

			if ((lifecycle.equals("1") &&
				 LiferayWindowState.isExclusive(request)) ||
				lifecycle.equals("2")) {

				return false;
			}
			else {
				return true;
			}
		}
	}

	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		if (isStrip(request) && !isInclude(request) &&
			!isAlreadyFiltered(request)) {

			if (_log.isDebugEnabled()) {
				String completeURL = HttpUtil.getCompleteURL(request);

				_log.debug("Stripping " + completeURL);
			}

			request.setAttribute(SKIP_FILTER, Boolean.TRUE);

			StripResponse stripResponse = new StripResponse(response);

			processFilter(
				StripFilter.class, request, stripResponse, filterChain);

			String contentType = GetterUtil.getString(
				stripResponse.getContentType()).toLowerCase();

			byte[] oldByteArray = stripResponse.getData();

			if ((oldByteArray != null) && (oldByteArray.length > 0)) {
				byte[] newByteArray = null;

				if (_log.isDebugEnabled()) {
					_log.debug("Stripping content of type " + contentType);
				}

				if (contentType.indexOf("text/") != -1) {
					newByteArray = strip(oldByteArray);
				}
				else {
					newByteArray = oldByteArray;
				}

				if (!ETagUtil.processETag(request, response, newByteArray)) {
					ServletResponseUtil.write(response, newByteArray);
				}
			}
		}
		else {
			if (_log.isDebugEnabled()) {
				String completeURL = HttpUtil.getCompleteURL(request);

				_log.debug("Not stripping " + completeURL);
			}

			processFilter(StripFilter.class, request, response, filterChain);
		}
	}

	protected int processCSS(
			byte[] oldByteArray, ByteArrayOutputStream newBytes,
			int currentIndex)
		throws IOException {

		int beginIndex = currentIndex + _MARKER_STYLE_OPEN.length + 1;
		int closeTagIndex =
			KMPSearch.search(
				oldByteArray, beginIndex, _MARKER_STYLE_CLOSE,
				_MARKER_STYLE_CLOSE_NEXTS);

		if (closeTagIndex == -1) {
			_log.error("Missing </style>");
			return currentIndex + 1;
		}

		int newBeginIndex = closeTagIndex + _MARKER_STYLE_CLOSE.length;
		newBeginIndex += countContinuousWhiteSpace(oldByteArray, newBeginIndex);

		String styleContent =
			new String(oldByteArray, beginIndex, closeTagIndex - beginIndex);

		if (Validator.isNull(styleContent)) {
			return newBeginIndex;
		}

		styleContent = MinifierUtil.minifyCss(styleContent);

		if (Validator.isNull(styleContent)) {
			return newBeginIndex;
		}

		newBytes.write(_STYLE_TYPE_CSS);
		newBytes.write(styleContent.getBytes());
		newBytes.write(_MARKER_STYLE_CLOSE);
		return newBeginIndex;
	}

	protected int processJavaScript(
			byte[] oldByteArray, ByteArrayOutputStream newBytes,
			int currentIndex, byte[] openTag)
		throws IOException {

		int beginIndex = currentIndex + openTag.length + 1;
		int closeTagIndex =
			KMPSearch.search(
				oldByteArray, beginIndex, _MARKER_SCRIPT_CLOSE,
				_MARKER_SCRIPT_CLOSE_NEXTS);

		if (closeTagIndex == -1) {
			_log.error("Missing </script>");
			return currentIndex + 1;
		}

		int newBeginIndex = closeTagIndex + _MARKER_SCRIPT_CLOSE.length;
		newBeginIndex += countContinuousWhiteSpace(oldByteArray, newBeginIndex);

		String scriptContent =
			new String(oldByteArray, beginIndex, closeTagIndex - beginIndex);

		if (Validator.isNull(scriptContent)) {
			return newBeginIndex;
		}

		scriptContent = MinifierUtil.minifyJavaScript(scriptContent);

		if (Validator.isNull(scriptContent)) {
			return newBeginIndex;
		}

		newBytes.write(_SCRIPT_TYPE_JAVASCRIPT);
		newBytes.write(_CDATA_OPEN);
		newBytes.write(scriptContent.getBytes());
		newBytes.write(_CDATA_CLOSE);
		newBytes.write(_MARKER_SCRIPT_CLOSE);

		return newBeginIndex;
	}

	protected byte[] strip(byte[] oldByteArray) throws IOException {
		ByteArrayOutputStream newBytes = new ByteArrayOutputStream(
			oldByteArray.length);

		int state = _STATE_NORMAL;

		boolean removeStartingWhitespace = true;

		for (int i = 0; i < oldByteArray.length; i++) {
			byte b = oldByteArray[i];

			char c = (char)b;

			if (c == CharPool.LESS_THAN) {
				if (state == _STATE_NORMAL) {
					if (hasMarker(oldByteArray, i, _MARKER_PRE_OPEN) ||
						hasMarker(oldByteArray, i, _MARKER_TEXTAREA_OPEN)) {

						state = _STATE_IGNORE;
					}
					else if (hasMarker(oldByteArray, i, _MARKER_DIV_CLOSE) ||
							 hasMarker(oldByteArray, i, _MARKER_FORM_CLOSE) ||
							 hasMarker(oldByteArray, i, _MARKER_LI_CLOSE) ||
							 hasMarker(oldByteArray, i, _MARKER_SCRIPT_CLOSE) ||
							 hasMarker(oldByteArray, i, _MARKER_STYLE_CLOSE) ||
							 hasMarker(oldByteArray, i, _MARKER_TABLE_CLOSE) ||
							 hasMarker(oldByteArray, i, _MARKER_TD_CLOSE) ||
							 hasMarker(oldByteArray, i, _MARKER_TD_OPEN) ||
							 hasMarker(oldByteArray, i, _MARKER_TR_CLOSE) ||
							 hasMarker(oldByteArray, i, _MARKER_TR_OPEN) ||
							 hasMarker(oldByteArray, i, _MARKER_UL_CLOSE)) {

						state = _STATE_FOUND_ELEMENT;
					}
					else if (hasMarker(
								oldByteArray, i, _MARKER_JAVASCRIPT_OPEN)) {
						i = processJavaScript(oldByteArray, newBytes, i,
							_MARKER_JAVASCRIPT_OPEN) - 1;
						state = _STATE_NORMAL;
						continue;
					}
					else if (hasMarker(oldByteArray, i, _MARKER_SCRIPT_OPEN)) {

						i = processJavaScript(oldByteArray, newBytes, i,
							_MARKER_SCRIPT_OPEN) - 1;
						state = _STATE_NORMAL;
						continue;
					}
					else if (hasMarker(oldByteArray, i, _MARKER_STYLE_OPEN)) {

						i = processCSS(oldByteArray, newBytes, i) - 1;
						state = _STATE_NORMAL;
						continue;
					}
				}
				else if (state == _STATE_IGNORE) {
					if (hasMarker(oldByteArray, i, _MARKER_PRE_CLOSE) ||
						hasMarker(oldByteArray, i, _MARKER_TEXTAREA_CLOSE)) {

						state = _STATE_NORMAL;
					}
				}
			}
			else if (c == CharPool.GREATER_THAN) {
				if (state == _STATE_FOUND_ELEMENT) {
					state = _STATE_NORMAL;

					newBytes.write(b);

					while ((i + 1) < oldByteArray.length) {
						char nextChar = (char)oldByteArray[i + 1];

						if (Validator.isWhitespace(nextChar)) {
							i++;
						}
						else {
							break;
						}
					}

					continue;
				}
			}

			if (state == _STATE_NORMAL) {
				if ((i + 1) < oldByteArray.length) {
					if (removeStartingWhitespace) {
						if (Validator.isWhitespace(c)) {
							continue;
						}
						else {
							removeStartingWhitespace = false;
						}
					}

					if ((c == CharPool.NEW_LINE) ||
						(c == CharPool.RETURN) ||
						(c == CharPool.TAB)) {

						char nextChar = (char)oldByteArray[i + 1];

						if ((nextChar == CharPool.NEW_LINE) ||
							(nextChar == CharPool.RETURN) ||
							(nextChar == CharPool.TAB)) {

							continue;
						}
					}
				}
			}

			newBytes.write(b);
		}

		return newBytes.toByteArray();
	}

	private static final byte[] _CDATA_CLOSE = "/*]]>*/".getBytes();

	private static final byte[] _CDATA_OPEN = "/*<![CDATA[*/".getBytes();

	private static final char[] _MARKER_DIV_CLOSE = "/div>".toCharArray();

	private static final char[] _MARKER_FORM_CLOSE = "/form>".toCharArray();

	private static final byte[] _MARKER_JAVASCRIPT_OPEN =
		"script type=\"text/javascript\">".getBytes();

	private static final char[] _MARKER_LI_CLOSE = "/li>".toCharArray();

	private static final char[] _MARKER_PRE_CLOSE = "/pre>".toCharArray();

	private static final char[] _MARKER_PRE_OPEN = "pre>".toCharArray();

	private static final byte[] _MARKER_SCRIPT_OPEN = "script>".getBytes();

	private static final byte[] _MARKER_SCRIPT_CLOSE = "</script>".getBytes();

	private static final int[] _MARKER_SCRIPT_CLOSE_NEXTS =
		KMPSearch.generateNexts(_MARKER_SCRIPT_CLOSE);

	private static final byte[] _MARKER_STYLE_OPEN =
		"style type=\"text/css\">".getBytes();

	private static final byte[] _MARKER_STYLE_CLOSE = "</style>".getBytes();

	private static final int[] _MARKER_STYLE_CLOSE_NEXTS =
		KMPSearch.generateNexts(_MARKER_STYLE_CLOSE);

	private static final char[] _MARKER_TABLE_CLOSE = "/table>".toCharArray();

	private static final char[] _MARKER_TD_CLOSE = "/td>".toCharArray();

	private static final char[] _MARKER_TD_OPEN = "td>".toCharArray();

	private static final char[] _MARKER_TR_CLOSE = "/tr>".toCharArray();

	private static final char[] _MARKER_TR_OPEN = "tr>".toCharArray();

	private static final char[] _MARKER_TEXTAREA_CLOSE =
		"/textarea>".toCharArray();

	private static final char[] _MARKER_TEXTAREA_OPEN =
		"textarea ".toCharArray();

	private static final char[] _MARKER_UL_CLOSE = "/ul>".toCharArray();

	private static final byte[] _SCRIPT_TYPE_JAVASCRIPT =
		"<script type=\"text/javascript\">".getBytes();

	private static final int _STATE_FOUND_ELEMENT = 3;

	private static final int _STATE_IGNORE = 1;

	private static final int _STATE_NORMAL = 0;

	private static final byte[] _STYLE_TYPE_CSS =
		"<style type=\"text/css\">".getBytes();

	private static final String _STRIP = "strip";

	private static Log _log = LogFactoryUtil.getLog(StripFilter.class);

}