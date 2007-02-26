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

package com.liferay.portal.service;

import com.liferay.portal.kernel.plugin.PluginPackage;

/**
 * <a href="PortletLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PortletLocalServiceUtil {
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		PortletLocalService portletLocalService = PortletLocalServiceFactory.getService();

		return portletLocalService.dynamicQuery(queryInitializer);
	}

	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException {
		PortletLocalService portletLocalService = PortletLocalServiceFactory.getService();

		return portletLocalService.dynamicQuery(queryInitializer, begin, end);
	}

	public static com.liferay.portal.model.PortletCategory getEARDisplay(
		java.lang.String xml) throws com.liferay.portal.SystemException {
		PortletLocalService portletLocalService = PortletLocalServiceFactory.getService();

		return portletLocalService.getEARDisplay(xml);
	}

	public static com.liferay.portal.model.PortletCategory getWARDisplay(
		java.lang.String servletContextName, java.lang.String xml)
		throws com.liferay.portal.SystemException {
		PortletLocalService portletLocalService = PortletLocalServiceFactory.getService();

		return portletLocalService.getWARDisplay(servletContextName, xml);
	}

	public static java.util.Map getFriendlyURLPlugins() {
		PortletLocalService portletLocalService = PortletLocalServiceFactory.getService();

		return portletLocalService.getFriendlyURLPlugins();
	}

	public static com.liferay.portal.model.Portlet getPortletById(
		java.lang.String companyId, java.lang.String portletId)
		throws com.liferay.portal.SystemException {
		PortletLocalService portletLocalService = PortletLocalServiceFactory.getService();

		return portletLocalService.getPortletById(companyId, portletId);
	}

	public static com.liferay.portal.model.Portlet getPortletByStrutsPath(
		java.lang.String companyId, java.lang.String strutsPath)
		throws com.liferay.portal.SystemException {
		PortletLocalService portletLocalService = PortletLocalServiceFactory.getService();

		return portletLocalService.getPortletByStrutsPath(companyId, strutsPath);
	}

	public static java.util.List getPortlets(java.lang.String companyId)
		throws com.liferay.portal.SystemException {
		PortletLocalService portletLocalService = PortletLocalServiceFactory.getService();

		return portletLocalService.getPortlets(companyId);
	}

	public static java.util.List getPortlets(java.lang.String companyId,
		boolean showSystem, boolean showPortal)
		throws com.liferay.portal.SystemException {
		PortletLocalService portletLocalService = PortletLocalServiceFactory.getService();

		return portletLocalService.getPortlets(companyId, showSystem, showPortal);
	}

	public static boolean hasPortlet(java.lang.String companyId,
		java.lang.String portletId) throws com.liferay.portal.SystemException {
		PortletLocalService portletLocalService = PortletLocalServiceFactory.getService();

		return portletLocalService.hasPortlet(companyId, portletId);
	}

	public static void initEAR(java.lang.String[] xmls,
		PluginPackage pluginPackage) {
		PortletLocalService portletLocalService = PortletLocalServiceFactory.getService();
		portletLocalService.initEAR(xmls, pluginPackage);
	}

	public static java.util.List initWAR(java.lang.String servletContextName,
		java.lang.String[] xmls,
		PluginPackage pluginPackage) {
		PortletLocalService portletLocalService = PortletLocalServiceFactory.getService();

		return portletLocalService.initWAR(servletContextName, xmls,
			pluginPackage);
	}

	public static com.liferay.portal.model.Portlet updatePortlet(
		java.lang.String companyId, java.lang.String portletId,
		java.lang.String roles, boolean active)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		PortletLocalService portletLocalService = PortletLocalServiceFactory.getService();

		return portletLocalService.updatePortlet(companyId, portletId, roles,
			active);
	}
}