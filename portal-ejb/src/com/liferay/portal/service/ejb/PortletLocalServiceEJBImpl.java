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

package com.liferay.portal.service.ejb;

import com.liferay.portal.service.PortletLocalService;
import com.liferay.portal.service.PortletLocalServiceFactory;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * <a href="PortletLocalServiceEJBImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PortletLocalServiceEJBImpl implements PortletLocalService,
	SessionBean {
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		return PortletLocalServiceFactory.getTxImpl().dynamicQuery(queryInitializer);
	}

	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException {
		return PortletLocalServiceFactory.getTxImpl().dynamicQuery(queryInitializer,
			begin, end);
	}

	public com.liferay.portal.model.PortletCategory getEARDisplay(
		java.lang.String xml) throws com.liferay.portal.SystemException {
		return PortletLocalServiceFactory.getTxImpl().getEARDisplay(xml);
	}

	public com.liferay.portal.model.PortletCategory getWARDisplay(
		java.lang.String servletContextName, java.lang.String xml)
		throws com.liferay.portal.SystemException {
		return PortletLocalServiceFactory.getTxImpl().getWARDisplay(servletContextName,
			xml);
	}

	public java.util.Map getFriendlyURLPlugins() {
		return PortletLocalServiceFactory.getTxImpl().getFriendlyURLPlugins();
	}

	public com.liferay.portal.model.Portlet getPortletById(
		java.lang.String companyId, java.lang.String portletId)
		throws com.liferay.portal.SystemException {
		return PortletLocalServiceFactory.getTxImpl().getPortletById(companyId,
			portletId);
	}

	public com.liferay.portal.model.Portlet getPortletByStrutsPath(
		java.lang.String companyId, java.lang.String strutsPath)
		throws com.liferay.portal.SystemException {
		return PortletLocalServiceFactory.getTxImpl().getPortletByStrutsPath(companyId,
			strutsPath);
	}

	public java.util.List getPortlets(java.lang.String companyId)
		throws com.liferay.portal.SystemException {
		return PortletLocalServiceFactory.getTxImpl().getPortlets(companyId);
	}

	public java.util.List getPortlets(java.lang.String companyId,
		boolean showSystem, boolean showPortal)
		throws com.liferay.portal.SystemException {
		return PortletLocalServiceFactory.getTxImpl().getPortlets(companyId,
			showSystem, showPortal);
	}

	public boolean hasPortlet(java.lang.String companyId,
		java.lang.String portletId) throws com.liferay.portal.SystemException {
		return PortletLocalServiceFactory.getTxImpl().hasPortlet(companyId,
			portletId);
	}

	public void initEAR(java.lang.String[] xmls,
		com.liferay.portal.kernel.plugin.PluginPackage pluginPackage) {
		PortletLocalServiceFactory.getTxImpl().initEAR(xmls, pluginPackage);
	}

	public java.util.List initWAR(java.lang.String servletContextName,
		java.lang.String[] xmls,
		com.liferay.portal.kernel.plugin.PluginPackage pluginPackage) {
		return PortletLocalServiceFactory.getTxImpl().initWAR(servletContextName,
			xmls, pluginPackage);
	}

	public com.liferay.portal.model.Portlet updatePortlet(
		java.lang.String companyId, java.lang.String portletId,
		java.lang.String roles, boolean active)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return PortletLocalServiceFactory.getTxImpl().updatePortlet(companyId,
			portletId, roles, active);
	}

	public void ejbCreate() throws CreateException {
	}

	public void ejbRemove() {
	}

	public void ejbActivate() {
	}

	public void ejbPassivate() {
	}

	public SessionContext getSessionContext() {
		return _sc;
	}

	public void setSessionContext(SessionContext sc) {
		_sc = sc;
	}

	private SessionContext _sc;
}