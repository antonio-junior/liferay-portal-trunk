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

package com.liferay.portal.cache;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheManager;
import com.liferay.portal.util.PropsUtil;

import java.net.URL;

import javax.management.MBeanServer;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.ObjectExistsException;
import net.sf.ehcache.management.ManagementService;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * <a href="EhcachePortalCacheManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Joseph Shum
 * @author Raymond Augé
 * @author Michael C. Han
 *
 */
public class EhcachePortalCacheManager
	implements DisposableBean, InitializingBean, PortalCacheManager {

	public void afterPropertiesSet() {
		URL url = getClass().getResource(PropsUtil.get(_configPropertyKey));

		_cacheManager = new CacheManager(url);

		ManagementService.registerMBeans(
			_cacheManager, _mbeanServer, _registerCacheManager, _registerCaches,
			_registerCacheConfigurations, _registerCacheStatistics);
	}

	public void clearAll() {
		_cacheManager.clearAll();
	}

	public void destroy() throws Exception {
		_cacheManager.shutdown();
	}

	public PortalCache getCache(String name) {
		Cache cache = _cacheManager.getCache(name);

		if (cache == null) {
			try {
				_cacheManager.addCache(name);
			}
			catch (ObjectExistsException oee) {

				// LEP-7122

			}

			cache = _cacheManager.getCache(name);
		}

		return new EhcachePortalCache(cache);
	}

	public void setConfigPropertyKey(String configPropertyKey) {
		_configPropertyKey = configPropertyKey;
	}

	public void setMBeanServer(MBeanServer server) {
		_mbeanServer = server;
	}

	public void setRegisterCacheConfigurations(
		boolean registerCacheConfigurations) {

		_registerCacheConfigurations = registerCacheConfigurations;
	}

	public void setRegisterCacheManager(boolean registerCacheManager) {
		_registerCacheManager = registerCacheManager;
	}

	public void setRegisterCaches(boolean registerCaches) {
		_registerCaches = registerCaches;
	}

	public void setRegisterCacheStatistics(boolean registerCacheStatistics) {
		_registerCacheStatistics = registerCacheStatistics;
	}

	private String _configPropertyKey;
	private CacheManager _cacheManager;
	private MBeanServer _mbeanServer;
	private boolean _registerCacheManager = true;
	private boolean _registerCaches = true;
	private boolean _registerCacheConfigurations = true;
	private boolean _registerCacheStatistics = true;

}