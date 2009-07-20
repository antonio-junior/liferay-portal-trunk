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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.util.GetterUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

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
		else if (_name.equals("classHoverName")) {
			row.setClassHoverName((String)_value);
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