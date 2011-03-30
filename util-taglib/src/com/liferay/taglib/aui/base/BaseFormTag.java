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

package com.liferay.taglib.aui.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 * @author Nathan Cavanaugh
 * @author Julio Camarero
 * @generated
 */
public class BaseFormTag extends com.liferay.taglib.util.IncludeTag {

	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doStartTag();
	}

	public java.lang.String getAction() {
		return _action;
	}

	public java.lang.String getCssClass() {
		return _cssClass;
	}

	public boolean getEscapeXml() {
		return _escapeXml;
	}

	public boolean getInlineLabels() {
		return _inlineLabels;
	}

	public java.lang.String getName() {
		return _name;
	}

	public java.lang.String getOnSubmit() {
		return _onSubmit;
	}

	public boolean getUseNamespace() {
		return _useNamespace;
	}

	public void setAction(java.lang.String action) {
		_action = action;

		setScopedAttribute("action", action);
	}

	public void setCssClass(java.lang.String cssClass) {
		_cssClass = cssClass;

		setScopedAttribute("cssClass", cssClass);
	}

	public void setEscapeXml(boolean escapeXml) {
		_escapeXml = escapeXml;

		setScopedAttribute("escapeXml", escapeXml);
	}

	public void setInlineLabels(boolean inlineLabels) {
		_inlineLabels = inlineLabels;

		setScopedAttribute("inlineLabels", inlineLabels);
	}

	public void setName(java.lang.String name) {
		_name = name;

		setScopedAttribute("name", name);
	}

	public void setOnSubmit(java.lang.String onSubmit) {
		_onSubmit = onSubmit;

		setScopedAttribute("onSubmit", onSubmit);
	}

	public void setUseNamespace(boolean useNamespace) {
		_useNamespace = useNamespace;

		setScopedAttribute("useNamespace", useNamespace);
	}

	protected void cleanUp() {
		_action = null;
		_cssClass = null;
		_escapeXml = false;
		_inlineLabels = false;
		_name = "fm";
		_onSubmit = null;
		_useNamespace = true;
	}

	protected String getEndPage() {
		return _END_PAGE;
	}

	protected String getStartPage() {
		return _START_PAGE;
	}

	protected void setAttributes(HttpServletRequest request) {
		setNamespacedAttribute(request, "action", _action);
		setNamespacedAttribute(request, "cssClass", _cssClass);
		setNamespacedAttribute(request, "escapeXml", _escapeXml);
		setNamespacedAttribute(request, "inlineLabels", _inlineLabels);
		setNamespacedAttribute(request, "name", _name);
		setNamespacedAttribute(request, "onSubmit", _onSubmit);
		setNamespacedAttribute(request, "useNamespace", _useNamespace);
	}

	protected static final String _ATTRIBUTE_NAMESPACE = "aui:form:";

	private static final String _END_PAGE =
		"/html/taglib/aui/form/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/aui/form/start.jsp";

	protected java.lang.String _action;
	protected java.lang.String _cssClass;
	protected boolean _escapeXml;
	protected boolean _inlineLabels;
	protected java.lang.String _name;
	protected java.lang.String _onSubmit;
	protected boolean _useNamespace;

}