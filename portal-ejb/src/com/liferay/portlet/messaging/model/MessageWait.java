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

package com.liferay.portlet.messaging.model;

import com.liferay.portal.util.PropsUtil;
import com.liferay.util.GetterUtil;


/**
 * <a href="MessageWait.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Ming-Gih Lam
 *
 */
public class MessageWait {
	
	public synchronized void waitForMessages()
		throws InterruptedException {
		
		int heartbeatCycle = GetterUtil.getInteger(PropsUtil
			.get(PropsUtil.JABBER_XMPP_HEARTBEAT_MILLISEC), 12000);
		
		wait(heartbeatCycle);
	}
	
	public synchronized void notifyWait() {
		notify();
	}

	public String getCmd() {
		return _cmd;
	}

	public void setCmd(String cmd) {
		this._cmd = cmd;
	}
	
	private String _cmd;

}