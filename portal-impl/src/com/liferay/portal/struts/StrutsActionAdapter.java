/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.struts;

import com.liferay.portal.kernel.struts.BaseStrutsAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Mika Koivisto
 */
public class StrutsActionAdapter extends BaseStrutsAction {

	public StrutsActionAdapter(
		Action action, ActionMapping actionMapping, ActionForm actionForm) {

		_action = action;
		_actionMapping = actionMapping;
		_actionForm = actionForm;
	}

	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ActionForward actionForward = _action.execute(
			_actionMapping, _actionForm, request, response);

		return actionForward.getPath();
	}

	private Action _action;
	private ActionForm _actionForm;
	private ActionMapping _actionMapping;

}