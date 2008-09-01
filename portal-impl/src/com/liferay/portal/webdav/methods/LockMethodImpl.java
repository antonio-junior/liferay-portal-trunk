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

package com.liferay.portal.webdav.methods;

import com.liferay.lock.model.Lock;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.webdav.WebDAVException;
import com.liferay.portal.webdav.WebDAVRequest;
import com.liferay.portal.webdav.WebDAVStorage;
import com.liferay.portal.webdav.WebDAVUtil;
import com.liferay.util.servlet.ServletResponseUtil;
import com.liferay.util.xml.XMLFormatter;

import java.io.StringReader;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * <a href="LockMethodImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Alexander Chow
 *
 */
public class LockMethodImpl implements Method {

	public int process(WebDAVRequest webDavRequest) throws WebDAVException {
		try {
			return doProcess(webDavRequest);
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	protected int doProcess(WebDAVRequest webDavRequest) throws Exception {
		WebDAVStorage storage = webDavRequest.getWebDAVStorage();

		if (!storage.isSupportsClassTwo()) {
			return HttpServletResponse.SC_METHOD_NOT_ALLOWED;
		}

		int statusCode = HttpServletResponse.SC_PRECONDITION_FAILED;

		HttpServletRequest request = webDavRequest.getHttpServletRequest();
		HttpServletResponse response = webDavRequest.getHttpServletResponse();

		Lock lock = null;

		String lockUuid = webDavRequest.getLockUuid();
		long timeout = WebDAVUtil.getTimeout(request);

		if (Validator.isNull(lockUuid)) {
			String owner = null;

			String xml = new String(
				FileUtil.getBytes(request.getInputStream()));

			if (Validator.isNull(xml)) {
				_log.error("Empty request XML");

				return statusCode;
			}
			else {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Request XML\n" + XMLFormatter.toString(xml));
				}

				SAXReader reader = new SAXReader();

				Document doc = reader.read(new StringReader(xml));

				Element root = doc.getRootElement();

				boolean exclusive = false;

				List<Element> lockscopeEls = root.element(
					"lockscope").elements();

				for (Element scopeEl : lockscopeEls) {
					String name = GetterUtil.getString(scopeEl.getName());

					if (name.equals("exclusive")) {
						exclusive = true;
					}
				}

				if (!exclusive) {
					return HttpServletResponse.SC_BAD_REQUEST;
				}

				Element ownerEl = root.element("owner");

				owner = ownerEl.getTextTrim();

				if (Validator.isNull(owner)) {
					List<Element> childEls = ownerEl.elements("href");

					for (Element childEl : childEls) {
						owner =
							"<D:href>" + childEl.getTextTrim() + "</D:href>";
					}
				}
			}

			lock = storage.lockResource(webDavRequest, owner, timeout);
		}
		else {
			lock = storage.refreshResourceLock(
				webDavRequest, lockUuid, timeout);
		}

		if (lock == null) {
			return WebDAVUtil.SC_LOCKED;
		}

		long depth = WebDAVUtil.getDepth(request);

		String xml = getResponseXML(lock, depth);

		if (_log.isDebugEnabled()) {
			_log.debug("Response XML\n" + xml);
		}

		response.setContentType(ContentTypes.TEXT_XML_UTF8);
		response.setStatus(HttpServletResponse.SC_OK);

		response.setHeader(
			"Lock-Token", "<" + WebDAVUtil.TOKEN_PREFIX + lock.getUuid() + ">");

		try {
			ServletResponseUtil.write(response, xml);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e);
			}
		}

		return -1;
	}

	protected String getResponseXML(Lock lock, long depth) throws Exception {
		StringBuilder sb = new StringBuilder();

		long timeoutSecs = lock.getExpirationTime() / Time.SECOND;

		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		sb.append("<D:prop xmlns:D=\"DAV:\">");
		sb.append("<D:lockdiscovery>");
		sb.append("<D:activelock>");
		sb.append("<D:locktype><D:write/></D:locktype>");
		sb.append("<D:lockscope><D:exclusive/></D:lockscope>");

		if (depth < 0) {
			sb.append("<D:depth>Infinity</D:depth>");
		}

		sb.append("<D:owner>" + lock.getOwner() + "</D:owner>");
		sb.append("<D:timeout>Second-" + timeoutSecs + "</D:timeout>");
		sb.append(
			"<D:locktoken><D:href>" + WebDAVUtil.TOKEN_PREFIX + lock.getUuid() +
			"</D:href></D:locktoken>");
		sb.append("</D:activelock>");
		sb.append("</D:lockdiscovery>");
		sb.append("</D:prop>");

		return XMLFormatter.toString(sb.toString());
	}

	private static Log _log = LogFactory.getLog(LockMethodImpl.class);

}