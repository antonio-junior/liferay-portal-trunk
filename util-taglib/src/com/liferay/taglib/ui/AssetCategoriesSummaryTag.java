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

import com.liferay.taglib.util.IncludeTag;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="AssetCategoriesSummaryTag.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class AssetCategoriesSummaryTag extends IncludeTag {

	public int doStartTag() {
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		request.setAttribute(
			"liferay-ui:asset-categories-summary:className", _className);
		request.setAttribute(
			"liferay-ui:asset-categories-summary:classPK",
			String.valueOf(_classPK));
		request.setAttribute(
			"liferay-ui:asset-categories-summary:message", _message);
		request.setAttribute(
			"liferay-ui:asset-categories-summary:portletURL", _portletURL);

		return EVAL_BODY_BUFFERED;
	}

	public PortletURL getPortletURL() {
		return _portletURL;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	protected String getDefaultPage() {
		return _PAGE;
	}

	private static final String _PAGE =
		"/html/taglib/ui/asset_categories_summary/page.jsp";

	private String _className;
	private long _classPK;
	private String _message;
	private PortletURL _portletURL;

}