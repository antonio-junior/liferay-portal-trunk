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

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Map;

/**
 * <a href="WorkflowInstanceManagerUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceManagerUtil {

	public static void deleteWorkflowInstance(long workflowInstanceId)
		throws WorkflowException {

		_workflowInstanceManager.deleteWorkflowInstance(workflowInstanceId);
	}

	public static List<String> getNextTransitionNames(
			long userId, long workflowInstanceId)
		throws WorkflowException {

		return _workflowInstanceManager.getNextTransitionNames(
			userId, workflowInstanceId);
	}

	public static WorkflowInstance getWorkflowInstance(long workflowInstanceId)
		throws WorkflowException {

		return _workflowInstanceManager.getWorkflowInstance(workflowInstanceId);
	}

	public static int getWorkflowInstanceCount(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			Boolean completed)
		throws WorkflowException {

		return _workflowInstanceManager.getWorkflowInstanceCount(
			workflowDefinitionName, workflowDefinitionVersion, completed);
	}

	public static WorkflowInstanceManager getWorkflowInstanceManager() {
		return _workflowInstanceManager;
	}

	public static List<WorkflowInstance> getWorkflowInstances(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return _workflowInstanceManager.getWorkflowInstances(
			workflowDefinitionName, workflowDefinitionVersion, completed, start,
			end, orderByComparator);
	}

	public static WorkflowInstance signalWorkflowInstance(
			long userId, long workflowInstanceId, String transitionName,
			Map<String, Object> context)
		throws WorkflowException {

		return _workflowInstanceManager.signalWorkflowInstance(
			userId, workflowInstanceId, transitionName, context);
	}

	public static WorkflowInstance startWorkflowInstance(
			long userId, String workflowDefinitionName,
			Integer workflowDefinitionVersion, String transitionName,
			Map<String, Object> context)
		throws WorkflowException {

		return _workflowInstanceManager.startWorkflowInstance(
			userId, workflowDefinitionName, workflowDefinitionVersion,
			transitionName, context);
	}

	public static WorkflowInstance updateContext(
			long workflowInstanceId, Map<String, Object> context)
		throws WorkflowException {

		return _workflowInstanceManager.updateContext(
			workflowInstanceId, context);
	}

	public void setWorkflowInstanceManager(
		WorkflowInstanceManager workflowInstanceManager) {

		_workflowInstanceManager = workflowInstanceManager;
	}

	private static WorkflowInstanceManager _workflowInstanceManager;

}