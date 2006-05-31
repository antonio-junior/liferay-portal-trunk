/**
 * Copyright (c) 2000-2006 Liferay, LLC. All rights reserved.
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

import com.liferay.portal.service.spring.PortletLocalService;
import com.liferay.portal.spring.util.SpringUtil;

import org.springframework.context.ApplicationContext;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * <a href="PortletLocalServiceEJBImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class PortletLocalServiceEJBImpl implements PortletLocalService,
	SessionBean {
	public static final String CLASS_NAME = PortletLocalService.class.getName() +
		".transaction";

	public static PortletLocalService getService() {
		ApplicationContext ctx = SpringUtil.getContext();

		return (PortletLocalService)ctx.getBean(CLASS_NAME);
	}

	public com.liferay.portal.model.PortletCategory getEARDisplay(
		java.lang.String xml)
		throws org.dom4j.DocumentException, java.io.IOException {
		return getService().getEARDisplay(xml);
	}

	public com.liferay.portal.model.PortletCategory getWARDisplay(
		java.lang.String servletContextName, java.lang.String xml)
		throws org.dom4j.DocumentException, java.io.IOException {
		return getService().getWARDisplay(servletContextName, xml);
	}

	public com.liferay.portal.model.Portlet getPortletById(
		java.lang.String companyId, java.lang.String portletId)
		throws com.liferay.portal.SystemException {
		return getService().getPortletById(companyId, portletId);
	}

	public com.liferay.portal.model.Portlet getPortletByStrutsPath(
		java.lang.String companyId, java.lang.String strutsPath)
		throws com.liferay.portal.SystemException {
		return getService().getPortletByStrutsPath(companyId, strutsPath);
	}

	public java.util.List getPortlets(java.lang.String companyId)
		throws com.liferay.portal.SystemException {
		return getService().getPortlets(companyId);
	}

	public java.util.List getPortlets(java.lang.String companyId,
		boolean showSystem, boolean showPortal)
		throws com.liferay.portal.SystemException {
		return getService().getPortlets(companyId, showSystem, showPortal);
	}

	public void initEAR(java.lang.String[] xmls) {
		getService().initEAR(xmls);
	}

	public java.util.List initWAR(java.lang.String servletContextName,
		java.lang.String[] xmls) {
		return getService().initWAR(servletContextName, xmls);
	}

	public com.liferay.portal.model.Portlet updatePortlet(
		java.lang.String companyId, java.lang.String portletId,
		java.lang.String roles, boolean active)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().updatePortlet(companyId, portletId, roles, active);
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