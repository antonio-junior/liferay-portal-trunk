/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.kernel.messaging;

/**
 * <a href="SerialDispatchedDestination.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * Destination that delivers a message to a list of message listeners one at a
 * time.
 * </p>
 *
 * @author Michael C. Han
 *
 */
public class SerialDispatchedDestination extends ParallelDispatchedDestination {

	public SerialDispatchedDestination(String name) {
		super(name, _NUM_WORKERS, _MAX_WORKERS);
	}

	protected void doDispatch(
		final MessageListener[] listeners, final String messageId,
		final String message) {

		Runnable runnable = new Runnable() {

			public void run() {
				for (MessageListener listener : listeners) {
					listener.receive(messageId, message);
				}
			}

		};

		executor.execute(runnable);
	}

	private static final int _NUM_WORKERS = 1;

	private static final int _MAX_WORKERS = 1;

}