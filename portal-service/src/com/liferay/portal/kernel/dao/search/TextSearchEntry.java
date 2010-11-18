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

package com.liferay.portal.kernel.dao.search;

import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 */
public class TextSearchEntry extends SearchEntry {

	public TextSearchEntry(String align, String valign, String name) {
		this(align, valign, DEFAULT_COLSPAN, name, null);
	}

	public TextSearchEntry(
		String align, String valign, int colspan, String name) {

		this(align, valign, colspan, name, null);
	}

	public TextSearchEntry(
		String align, String valign, String name, String href) {

		this(align, valign, DEFAULT_COLSPAN, name, href, null, null);
	}

	public TextSearchEntry(
		String align, String valign, int colspan, String name, String href) {

		this(align, valign, colspan, name, href, null, null);
	}

	public TextSearchEntry(
		String align, String valign, String name, String href, String target,
		String title) {

		this(align, valign, DEFAULT_COLSPAN, name, href, target, title);
	}

	public TextSearchEntry(
		String align, String valign, int colspan, String name, String href,
		String target, String title) {

		super(align, valign, colspan);

		_name = name;
		_href = href;
		_target = target;
		_title = title;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getHref() {
		return _href;
	}

	public void setHref(String href) {
		_href = href;
	}

	public String getTarget() {
		return _target;
	}

	public void setTarget(String target) {
		_target = target;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void print(PageContext pageContext) throws Exception {
		if (_href == null) {
			pageContext.getOut().print(_name);
		}
		else {
			StringBundler sb = new StringBundler();

			sb.append("<a href=\"");
			sb.append(HtmlUtil.escapeHREF(_href));
			sb.append("\"");

			if (Validator.isNotNull(_target)) {
				sb.append(" target=\"");
				sb.append(_target);
				sb.append("\"");
			}

			if (Validator.isNotNull(_title)) {
				sb.append(" title=\"");
				sb.append(_title);
				sb.append("\"");
			}

			sb.append(">");
			sb.append(_name);
			sb.append("</a>");

			JspWriter jspWriter = pageContext.getOut();

			jspWriter.print(sb.toString());
		}
	}

	public Object clone() {
		return new TextSearchEntry(
			getAlign(), getValign(), getColspan(), getName(), getHref(),
			getTarget(), getTitle());
	}

	private String _name;
	private String _href;
	private String _target;
	private String _title;

}