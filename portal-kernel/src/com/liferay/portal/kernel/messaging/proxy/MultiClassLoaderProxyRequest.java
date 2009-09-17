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

package com.liferay.portal.kernel.messaging.proxy;

import com.liferay.portal.kernel.util.AggregateClassLoader;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <a href="MultiClassLoaderProxyRequest.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Michael C. Han
 */
public class MultiClassLoaderProxyRequest extends ProxyRequest {

	public MultiClassLoaderProxyRequest(
			Method method, Object[] arguments)
		throws Exception {

		super(method, arguments);

		ClassLoader[] classLoaders = inspectForClassLoaders(method);

		if ((classLoaders != null) && (classLoaders.length > 0)) {
			_clientClassLoaders = classLoaders[0];

			if (classLoaders.length > 1) {
				for (int i = 1; i < classLoaders.length; i++) {
					_clientClassLoaders = new AggregateClassLoader(
						classLoaders[i], _clientClassLoaders);
				}
			}
		}
	}

	public Object execute(Object object) throws Exception {
		ClassLoader contextClassLoader = null;

		if (_clientClassLoaders != null) {
			Thread currentThread = Thread.currentThread();

			contextClassLoader = currentThread.getContextClassLoader();

			AggregateClassLoader invocationClassLoader =
				new AggregateClassLoader(
					_clientClassLoaders, contextClassLoader);

			currentThread.setContextClassLoader(invocationClassLoader);
		}

		try {
			return super.execute(object);
		}
		catch (InvocationTargetException ite) {
			throw new Exception(ite.getTargetException());
		}
		finally {
			if (contextClassLoader != null) {
				Thread currentThread = Thread.currentThread();

				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected ClassLoader[] inspectForClassLoaders(Method method)
		throws Exception {

		Annotation[][] annotationsArray = method.getParameterAnnotations();

		if ((annotationsArray == null) || (annotationsArray.length == 0)) {
			return null;
		}

		for (int i = 0; i < annotationsArray.length; i++) {
			Annotation[] annotations = annotationsArray[i];

			if ((annotations == null) || (annotations.length == 0)) {
				continue;
			}

			for (Annotation annotation : annotations) {
				if (ExecutingClassLoaders.class.isAssignableFrom(
						annotation.annotationType())) {

					return (ClassLoader[])getArguments()[i];
				}
			}
		}

		return null;
	}

	private ClassLoader _clientClassLoaders;

}