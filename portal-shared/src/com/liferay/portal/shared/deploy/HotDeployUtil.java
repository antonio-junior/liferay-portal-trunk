/**
 * Copyright (c) 2000-2006 Liferay, LLC. All rights reserved.
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

package com.liferay.portal.shared.deploy;

import com.liferay.portal.shared.log.Log;
import com.liferay.portal.shared.log.LogFactoryUtil;
import com.liferay.portal.shared.util.StackTraceUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="HotDeployUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Ivica Cardic
 * @author  Brian Wing Shun Chan
 *
 */
public class HotDeployUtil {

	public static void fireDeployEvent(HotDeployEvent event) {
		_instance._fireDeployEvent(event);
	}

	public static void fireUndeployEvent(HotDeployEvent event) {
		_instance._fireUndeployEvent(event);
	}

	public static void registerListener(HotDeployListener listener) {
		_instance._registerListener(listener);
	}

	private HotDeployUtil() {
		_listeners = new ArrayList();
	}

	private void _fireDeployEvent(HotDeployEvent event) {
		Iterator itr = _listeners.iterator();

		while (itr.hasNext()) {
			HotDeployListener listener = (HotDeployListener)itr.next();

			try {
				listener.invokeDeploy(event);
			}
			catch (HotDeployException hde) {
				_log.error(StackTraceUtil.getStackTrace(hde));
			}
		}
	}

	private void _fireUndeployEvent(HotDeployEvent event) {
		Iterator itr = _listeners.iterator();

		while (itr.hasNext()) {
			HotDeployListener listener = (HotDeployListener)itr.next();

			try {
				listener.invokeUndeploy(event);
			}
			catch (HotDeployException hde) {
				_log.error(StackTraceUtil.getStackTrace(hde));
			}
		}
	}

	private void _registerListener(HotDeployListener listener) {
		_listeners.add(listener);
	}

	private static Log _log = LogFactoryUtil.getLog(HotDeployUtil.class);

	private static HotDeployUtil _instance = new HotDeployUtil();

	private List _listeners;

}