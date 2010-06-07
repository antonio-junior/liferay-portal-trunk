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

package com.liferay.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.Route;
import com.liferay.portal.kernel.portlet.RouteNotFoundException;
import com.liferay.portal.kernel.portlet.Router;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <a href="RouterImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Connor McKay
 * @author Brian Wing Shun Chan
 */
public class RouterImpl implements Router {

	public Route addRoute(String pattern) {
		Route route = new RouteImpl(pattern);

		_routes.add(route);

		return route;
	}

	public String parametersToUrl(Map<String, ?> parameters)
		throws RouteNotFoundException {

		for (Route route : _routes) {
			String url = route.parametersToUrl(parameters);

			if (url != null) {
				if (!parameters.isEmpty()) {
					url = url.concat(StringPool.QUESTION).concat(
						parametersToQueryString(parameters));
				}

				return url;
			}
		}

		throw new RouteNotFoundException(
			"No route could be found to use for parameters " +
				MapUtil.toString(parameters));
	}

	public Map<String, String> urlToParameters(String url)
		throws RouteNotFoundException {

		Map<String, String> queryParameters = new HashMap<String, String>();

		String[] urlParts = url.split("\\?", 2);

		if (urlParts.length > 1) {
			String query = urlParts[1];

			url = urlParts[0];

			try {
				queryStringToParameters(query, queryParameters);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}
			}
		}

		for (Route route : _routes) {
			Map<String, String> parameters = route.urlToParameters(url);

			if (parameters != null) {
				queryParameters.putAll(parameters);

				return queryParameters;
			}
		}

		throw new RouteNotFoundException(
			"No route could be found to match url " + url);
	}

	protected String parametersToQueryString(Map<String, ?> parameters) {
		StringBundler sb = new StringBundler(parameters.size() * 4 - 1);

		Iterator<String> itr = parameters.keySet().iterator();

		while (itr.hasNext()) {
			String name = itr.next();

			String value = MapUtil.getString(parameters, name);

			sb.append(HttpUtil.encodeURL(name));
			sb.append(StringPool.EQUAL);
			sb.append(HttpUtil.encodeURL(value));

			if (itr.hasNext()) {
				sb.append(StringPool.AMPERSAND);
			}
		}

		return sb.toString();
	}

	protected void queryStringToParameters(
		String queryString, Map<String, String> parameters) {

		String[] parametersArray = queryString.split(StringPool.AMPERSAND);

		for (String parameterString : parametersArray) {
			String[] parameterArray = parameterString.split(StringPool.EQUAL);

			if (parameterArray.length != 2) {
				throw new IllegalArgumentException(
					"Invalid query string " + queryString);
			}

			String name = parameterArray[0];
			String value = parameterArray[1];

			parameters.put(HttpUtil.decodeURL(name), HttpUtil.decodeURL(value));
		}
	}

	private static Log _log = LogFactoryUtil.getLog(Router.class);

	private List<Route> _routes = new ArrayList<Route>();

}