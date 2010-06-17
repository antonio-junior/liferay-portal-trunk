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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.util.GetterUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <a href="SearchContainerRowParameterTag.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class SearchContainerRowParameterTag extends TagSupport {

	public int doStartTag() throws JspException {
		SearchContainerRowTag parentRowTag =
			(SearchContainerRowTag)findAncestorWithClass(
			this, SearchContainerRowTag.class);

		if (parentRowTag == null) {
			throw new JspTagException(
				"Requires liferay-ui:search-container-row");
		}

		ResultRow row = parentRowTag.getRow();

		if (_name.equals("className")) {
			row.setClassName(_name);
		}
		else if (_name.equals("restricted")) {
			row.setRestricted(GetterUtil.getBoolean((String)_value, false));
		}
		else {
			row.setParameter(_name, _value);
		}

		return EVAL_BODY_INCLUDE;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setValue(Object value) {
		_value = value;
	}

	private String _name;
	private Object _value;

}