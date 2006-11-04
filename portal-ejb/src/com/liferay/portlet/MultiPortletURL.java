/**
 * Copyright (c) 2000-2006 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.util.PortalUtil;
import com.liferay.util.StringPool;
import com.liferay.util.Validator;
import com.liferay.util.portlet.PortletURLWrapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;

import org.springframework.web.util.HtmlUtils;

/**
 * <a href="MultiPortletURL.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Sergey Ponomarev
 *
 */
public class MultiPortletURL extends PortletURLWrapper {

	public MultiPortletURL(PortletURL portletURL) {
		super(portletURL);
	}

	public void addPortletParameters(String portletId, Map parameters) {
		if (Validator.isNotNull(portletId)) {
			_portletsParams.put(portletId, parameters);
		}
	}

	public String toString() {
		String url = super.toString();

		if (Validator.isNull(url)) {
			return url;
		}
		else {
			StringBuffer sb = new StringBuffer(url);

			if (!url.endsWith(StringPool.AMPERSAND)) {
				sb.append(StringPool.AMPERSAND);
			}

			String suffix = StringPool.BLANK;

			int pos = url.lastIndexOf(StringPool.POUND);

			if (pos >= 0) {
				suffix = url.substring(pos);

				sb.delete(pos, sb.length());
			}

			Iterator itr1 = _portletsParams.keySet().iterator();

			while (itr1.hasNext()) {
				String portletId = (String)itr1.next();

				String namespace = PortalUtil.getPortletNamespace(portletId);

				Map parameters = (Map)_portletsParams.get(portletId);

				Iterator itr2 = parameters.keySet().iterator();

				while (itr2.hasNext()) {
					String name = (String)itr2.next();

					List values = (List)parameters.get(name);

					for (int i = 0; i < values.size(); i++) {
						String value = (String)values.get(i);

						sb.append(namespace);
						sb.append(name);
						sb.append(StringPool.EQUAL);
						sb.append(HtmlUtils.htmlEscape(value));
						sb.append(StringPool.AMPERSAND);
					}
				}
			}

			return sb.toString() + suffix;
		}
	}

	private Map _portletsParams = new HashMap();

}