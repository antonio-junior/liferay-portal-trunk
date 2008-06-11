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

package com.liferay.portal.scheduler.quartz;

import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <a href="MessageSenderJob.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 * @author Bruno Farache
 *
 */
public class MessageSenderJob implements Job {

	public void execute(JobExecutionContext jobExecutionContext)
		throws JobExecutionException {

		try {
			JobDetail jobDetail = jobExecutionContext.getJobDetail();

			JobDataMap jobDataMap = jobDetail.getJobDataMap();

			String destination = jobDataMap.getString(
				SchedulerEngine.DESTINATION);
			String messageBody = jobDataMap.getString(
				SchedulerEngine.MESSAGE_BODY);

			MessageBusUtil.sendMessage(destination, messageBody);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static final Log _log = LogFactory.getLog(MessageSenderJob.class);

}