<%
/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/html/portlet/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.search.Document" %>
<%@ page import="com.liferay.portlet.softwarerepository.NoSuchFrameworkVersionException" %>
<%@ page import="com.liferay.portlet.softwarerepository.NoSuchLicenseException" %>
<%@ page import="com.liferay.portlet.softwarerepository.NoSuchProductEntryException" %>
<%@ page import="com.liferay.portlet.softwarerepository.NoSuchProductVersionException" %>
<%@ page import="com.liferay.portlet.softwarerepository.model.SRFrameworkVersion" %>
<%@ page import="com.liferay.portlet.softwarerepository.model.SRLicense" %>
<%@ page import="com.liferay.portlet.softwarerepository.model.SRProductEntry" %>
<%@ page import="com.liferay.portlet.softwarerepository.model.SRProductVersion" %>
<%@ page import="com.liferay.portlet.softwarerepository.service.SRFrameworkVersionLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.softwarerepository.service.SRLicenseLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.softwarerepository.service.SRProductEntryLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.softwarerepository.service.SRProductVersionLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.softwarerepository.service.permission.SRFrameworkVersionPermission" %>
<%@ page import="com.liferay.portlet.softwarerepository.service.permission.SRProductEntryPermission" %>
<%@ page import="com.liferay.portlet.softwarerepository.service.permission.SRProductVersionPermission" %>

<%
DateFormat dateFormatDateTime = DateFormats.getDateTime(locale, timeZone);
%>