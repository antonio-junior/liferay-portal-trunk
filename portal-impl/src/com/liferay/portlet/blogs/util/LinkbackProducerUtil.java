/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.blogs.util;

import com.liferay.ibm.icu.util.Calendar;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xmlrpc.Response;
import com.liferay.portal.kernel.xmlrpc.XmlRpcException;
import com.liferay.portal.kernel.xmlrpc.XmlRpcUtil;
import com.liferay.portal.xml.StAXReaderUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;

/**
 * <a href="LinkbackProducerUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Alexander Chow
 */
public class LinkbackProducerUtil {

	public static void sendPingback(String sourceUri, String targetUri)
		throws Exception {

		String serverUri = _discoverPingbackServer(targetUri);

		if (Validator.isNull(serverUri)) {
			return;
		}

		_pingbackQueue.add(
			new Tuple(new Date(), serverUri, sourceUri, targetUri));
	}

	public static synchronized void sendQueuedPingbacks()
		throws XmlRpcException {

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.MINUTE, -1);

		Date expiration = cal.getTime();

		while (!_pingbackQueue.isEmpty()) {
			Tuple tuple = _pingbackQueue.get(0);

			Date time = (Date)tuple.getObject(0);

			if (time.before(expiration)) {
				_pingbackQueue.remove(0);

				String serverUri = (String)tuple.getObject(1);
				String sourceUri = (String)tuple.getObject(2);
				String targetUri = (String)tuple.getObject(3);

				if (_log.isInfoEnabled()) {
					_log.info(
						"XML-RPC pingback " + serverUri + ", source " +
							sourceUri + ", target " + targetUri);
				}

				Response response = XmlRpcUtil.executeMethod(
					serverUri, "pingback.ping",
					new Object[] {sourceUri, targetUri});

				if (_log.isInfoEnabled()) {
					_log.info(response.toString());
				}
			}
			else {
				break;
			}
		}
	}

	public static boolean sendTrackback(
			String trackback, Map<String, String> parts)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Pinging trackback " + trackback);
		}

		Http.Options options = new Http.Options();

		options.setLocation(trackback);
		options.setParts(parts);
		options.setPost(true);

		String xml = HttpUtil.URLtoString(options);

		if (_log.isInfoEnabled()) {
			_log.info(xml);
		}

		String error = xml;

		XMLStreamReader xmlStreamReader = null;

		try {
			XMLInputFactory xmlInputFactory =
				StAXReaderUtil.getXMLInputFactory();

			xmlStreamReader = xmlInputFactory.createXMLStreamReader(
				new UnsyncStringReader(xml));

			xmlStreamReader.nextTag();
			xmlStreamReader.nextTag();

			String name = xmlStreamReader.getLocalName();

			if (name.equals("error")) {
				int status = GetterUtil.getInteger(
					xmlStreamReader.getElementText(), 1);

				if (status == 0) {
					return true;
				}

				xmlStreamReader.nextTag();

				name = xmlStreamReader.getLocalName();

				if (name.equals("message")) {
					error = xmlStreamReader.getElementText();
				}
			}
		}
		finally {
			if (xmlStreamReader != null) {
				try {
					xmlStreamReader.close();
				}
				catch (Exception e) {
				}
			}
		}

		_log.error(
			"Error while pinging trackback at " + trackback + ": " + error);

		return false;
	}

	private static String _discoverPingbackServer(String targetUri) {
		String serverUri = null;

		try {
			Http.Options options = new Http.Options();

			options.setLocation(targetUri);
			options.setHead(true);

			HttpUtil.URLtoByteArray(options);

			Http.Response response = options.getResponse();

			serverUri = response.getHeader("X-Pingback");
		}
		catch (Exception e) {
			_log.error("Unable to call HEAD of " + targetUri, e);
		}

		if (Validator.isNull(serverUri)) {
			try {
				Source clientSource = new Source(
					HttpUtil.URLtoString(targetUri));

				List<StartTag> startTags = clientSource.getAllStartTags("link");

				for (StartTag startTag : startTags) {
					String rel = startTag.getAttributeValue("rel");

					if (rel.equalsIgnoreCase("pingback")) {
						String href = startTag.getAttributeValue("href");

						serverUri = HtmlUtil.escape(href);

						break;
					}
				}
			}
			catch (Exception e) {
				_log.error("Unable to call GET of " + targetUri, e);
			}
		}

		return serverUri;
	}

	private static Log _log = LogFactoryUtil.getLog(LinkbackProducerUtil.class);

	private static List<Tuple> _pingbackQueue = Collections.synchronizedList(
		new ArrayList<Tuple>());

}