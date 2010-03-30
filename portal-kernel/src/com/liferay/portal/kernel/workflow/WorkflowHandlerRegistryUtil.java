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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * <a href="WorkflowHandlerRegistryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 * @author Marcellus Tavares
 */
public class WorkflowHandlerRegistryUtil {

	public static WorkflowHandler getWorkflowHandler(String className) {
		return getWorkflowHandlerRegistry().getWorkflowHandler(className);
	}

	public static WorkflowHandlerRegistry getWorkflowHandlerRegistry() {
		return _workflowHandlerRegistry;
	}

	public static List<WorkflowHandler> getWorkflowHandlers() {
		return getWorkflowHandlerRegistry().getWorkflowHandlers();
	}

	public static void register(List<WorkflowHandler> workflowHandlers) {
		for (WorkflowHandler workflowHandler : workflowHandlers) {
			register(workflowHandler);
		}
	}

	public static void register(WorkflowHandler workflowHandler) {
		getWorkflowHandlerRegistry().register(workflowHandler);
	}

	public static void startWorkflowInstance(
			long companyId, long groupId, long userId, String className,
			long classPK, Object model)
		throws PortalException, SystemException {

		WorkflowHandler workflowHandler = getWorkflowHandler(className);

		if (workflowHandler != null) {
			workflowHandler.startWorkflowInstance(
				companyId, groupId, userId, classPK, model);
		}
	}

	public static void unregister(List<WorkflowHandler> workflowHandlers) {
		for (WorkflowHandler workflowHandler : workflowHandlers) {
			unregister(workflowHandler);
		}
	}

	public static void unregister(WorkflowHandler workflowHandler) {
		getWorkflowHandlerRegistry().unregister(workflowHandler);
	}

	public static Object updateStatus(
			long companyId, long groupId, long userId, String className,
			long classPK, int status)
		throws PortalException, SystemException {

		WorkflowHandler workflowHandler = getWorkflowHandler(className);

		if (workflowHandler != null) {
			return workflowHandler.updateStatus(
				companyId, groupId, userId, classPK, status);
		}

		return null;
	}

	public void setWorkflowHandlerRegistry(
		WorkflowHandlerRegistry workflowHandlerRegistry) {

		_workflowHandlerRegistry = workflowHandlerRegistry;
	}

	private static WorkflowHandlerRegistry _workflowHandlerRegistry;

}