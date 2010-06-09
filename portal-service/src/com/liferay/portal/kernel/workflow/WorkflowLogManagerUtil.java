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

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * <a href="WorkflowLogManagerUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowLogManagerUtil {

	public static int getWorkflowLogCount(long companyId, long workflowTaskId)
		throws WorkflowException {

		return _workflowLogManager.getWorkflowLogCount(
			companyId, workflowTaskId);
	}

	public static int getWorkflowLogCountByWorkflowInstance(
			long companyId, long workflowInstanceId)
		throws WorkflowException {

		return _workflowLogManager.getWorkflowLogCountByWorkflowInstance(
			companyId, workflowInstanceId);
	}

	public static List<WorkflowLog> getWorkflowLogs(
			long companyId, long workflowTaskId, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return _workflowLogManager.getWorkflowLogs(
			companyId, workflowTaskId, start, end, orderByComparator);
	}

	public static List<WorkflowLog> getWorkflowLogsByWorkflowInstance(
			long companyId, long workflowInstanceId, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return _workflowLogManager.getWorkflowLogsByWorkflowInstance(
			companyId, workflowInstanceId, start, end, orderByComparator);
	}

	public static WorkflowLogManager getWorkflowLogManager() {
		return _workflowLogManager;
	}

	public void setWorkflowLogManager(WorkflowLogManager workflowLogManager) {
		_workflowLogManager = workflowLogManager;
	}

	private static WorkflowLogManager _workflowLogManager;

}