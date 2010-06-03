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

package com.liferay.portal.cache.memcached;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * <a href="PooledMemcachePortalCacheManager.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Michael C. Han
 */
public class PooledMemcachePortalCacheManager implements PortalCacheManager {

	public void afterPropertiesSet() {
	}

	public void destroy() throws Exception {
		for (PooledMemcachePortalCache portalCache :
				_portalCaches.values()) {

			portalCache.destroy();
		}
	}

	public void clearAll() {
		_portalCaches.clear();
	}

	public PortalCache getCache(String name) {
		return getCache(name, false);
	}

	public PortalCache getCache(String name, boolean blocking) {
		PooledMemcachePortalCache portalCache = _portalCaches.get(name);

		if (portalCache == null) {
			portalCache = new PooledMemcachePortalCache(
				name, _memcachedClientFactory, _timeout, _timeoutTimeUnit);

			portalCache.setDebug(_debug);

			_portalCaches.put(name, portalCache);
		}

		return portalCache;
	}

	public void setDebug(boolean debug) {
		_debug = debug;
	}

	public void setMemcachedClientPool(
		MemcachedClientFactory memcachedClientFactory) {

		_memcachedClientFactory = memcachedClientFactory;
	}

	public void setTimeout(int timeout) {
		_timeout = timeout;
	}

	public void setTimeoutTimeUnit(String timeoutTimeUnit) {
		_timeoutTimeUnit = TimeUnit.valueOf(timeoutTimeUnit);
	}

	private boolean _debug;
	private MemcachedClientFactory _memcachedClientFactory;
	private Map<String, PooledMemcachePortalCache> _portalCaches =
		new ConcurrentHashMap<String, PooledMemcachePortalCache>();
	private int _timeout;
	private TimeUnit _timeoutTimeUnit;

}