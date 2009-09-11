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

package com.liferay.portal.dao.shard;

import com.liferay.portal.kernel.util.ThreadLocalManager;
import com.liferay.portal.util.PropsValues;

import java.util.Map;

import org.hibernate.SessionFactory;

import org.springframework.aop.TargetSource;

/**
 * <a href="ShardSessionFactoryTargetSource.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Michael Young
 */
public class ShardSessionFactoryTargetSource implements TargetSource {

	public SessionFactory getSessionFactory() {
		return _sessionFactoryThreadLocal.get();
	}

	public Object getTarget() throws Exception {
		return getSessionFactory();
	}

	public Class<?> getTargetClass() {
		return _sessionFactories.get(PropsValues.SHARD_DEFAULT_NAME).getClass();
	}

	public boolean isStatic() {
		return false;
	}

	public void releaseTarget(Object target) throws Exception {
	}

	public void setSessionFactory(String shardName) {
		_sessionFactoryThreadLocal.set(_sessionFactories.get(shardName));
	}

	public void setSessionFactories(
		Map<String, SessionFactory> sessionFactories) {

		_sessionFactories = sessionFactories;
	}

	private static Map<String, SessionFactory> _sessionFactories;

	private static ThreadLocal<SessionFactory> _sessionFactoryThreadLocal =
		new ThreadLocal<SessionFactory>() {

		protected SessionFactory initialValue() {
			return _sessionFactories.get(PropsValues.SHARD_DEFAULT_NAME);
		}

	};

	static {
		ThreadLocalManager.registerThreadLocal(_sessionFactoryThreadLocal);
	}

}