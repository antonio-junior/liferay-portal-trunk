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

package com.liferay.portlet.blogs.job;

import com.liferay.portal.kernel.job.IntervalJob;
import com.liferay.portal.kernel.job.JobSchedulerUtil;
import com.liferay.portal.kernel.job.Scheduler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PropsValues;

/**
 * <a href="BlogsScheduler.java.html"><b><i>View Source</i></b></a>
 *
 * @author Alexander Chow
 */
public class BlogsScheduler implements Scheduler {

	public void schedule() {
		if (PropsValues.BLOGS_TRACKBACK_VERIFIER_JOB_INTERVAL <= 0) {
			if (_log.isDebugEnabled()) {
				_log.debug("Verification of blogs trackbacks is disabled");
			}

			_trackbackVerifier = null;
		}

		JobSchedulerUtil.schedule(_trackbackVerifier);
	}

	public void unschedule() {
		JobSchedulerUtil.unschedule(_trackbackVerifier);
	}

	private IntervalJob _trackbackVerifier = new TrackbackVerifierJob();

	private static Log _log = LogFactoryUtil.getLog(Scheduler.class);

}