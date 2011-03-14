<%--
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
--%>

<%@ page import="com.liferay.alloy.util.MarkupUtil" %>
<%@ page import="com.liferay.alloy.util.MessageUtil" %>
<%@ page import="com.liferay.portal.json.JSONFactoryImpl" %>
<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.servlet.taglib.aui.ScriptData" %>
<%@ page import="com.liferay.portal.kernel.servlet.taglib.CustomAttributes" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringBundler" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.io.Serializable" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>

<%!
public static ArrayList<Object> _getArrayList(Object obj) {
	return (ArrayList<Object>)_safeDeserialize(obj);
}

public static HashMap<String, Object> _getHashMap(Object obj) {
	return (HashMap)_safeDeserialize(obj);
}

public static Object _safeDeserialize(Object obj) {
	if (Validator.isNotNull(obj)) {
		return JSONFactoryUtil.looseDeserialize(StringUtil.unquote(JSONFactoryUtil.looseSerialize(obj)));
	}

	return null;
}

public static void _updateOptions(Map<String, Object> options, String key, Object value) {
	if ((options != null) && options.containsKey(key)) {
		options.put(key, value);
	}
}
%>