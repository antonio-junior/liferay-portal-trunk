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

import java.io.InputStream;
import java.io.Serializable;

import java.util.Map;

/**
 * <a href="DefaultWorkflowDefinition.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class DefaultWorkflowDefinition
	implements Serializable, WorkflowDefinition {

	public InputStream getInputStream() {
		return _inputStream;
	}

	public String getName() {
		return _name;
	}

	public Map<String, Object> getOptionalAttributes() {
		return _optionalAttributes;
	}

	public int getVersion() {
		return _version;
	}

	public void setInputStream(InputStream inputStream) {
		_inputStream = inputStream;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOptionalAttributes(Map<String, Object> optionalAttributes) {
		_optionalAttributes = optionalAttributes;
	}

	public void setVersion(int version) {
		_version = version;
	}

	private InputStream _inputStream;
	private String _name;
	private Map<String, Object> _optionalAttributes;
	private int _version;

}