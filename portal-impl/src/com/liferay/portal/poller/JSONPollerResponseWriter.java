/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.poller;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.poller.PollerException;
import com.liferay.portal.kernel.poller.PollerResponse;
import com.liferay.portal.kernel.poller.PollerWriterClosedException;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public class JSONPollerResponseWriter implements PollerResponseWriter {

	public JSONPollerResponseWriter() {
		_jsonArray = JSONFactoryUtil.createJSONArray();
	}

	public synchronized void close() throws PollerException {
		if (!_closed) {
			try {
				doClose();
			}
			catch (PollerException pe) {
				throw pe;
			}
			catch (Exception e) {
				throw new PollerException(e);
			}
			finally {
				_closed = true;
			}
		}
	}

	public JSONArray getJSONArray() {
		return _jsonArray;
	}

	public void write(JSONArray jsonArray) throws PollerException {
		if (_closed) {
			throw new PollerWriterClosedException();
		}

		_jsonArray.put(jsonArray);
	}

	public void write(JSONObject jsonObject) throws PollerException {
		if (_closed) {
			throw new PollerWriterClosedException();
		}

		_jsonArray.put(jsonObject);
	}

	public void write(PollerResponse pollerResponse) throws PollerException {
		if (_closed) {
			throw new PollerWriterClosedException();
		}

		_jsonArray.put(pollerResponse.toJSONObject());
	}

	public void write(String string) throws PollerException {
		if (_closed) {
			throw new PollerWriterClosedException();
		}

		_jsonArray.put(string);
	}

	protected void doClose() throws Exception {
	}

	private boolean _closed;
	private JSONArray _jsonArray;

}