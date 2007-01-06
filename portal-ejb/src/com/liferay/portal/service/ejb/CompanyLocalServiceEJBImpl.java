/**
 * Copyright (c) 2000-2006 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.CompanyLocalServiceFactory;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * <a href="CompanyLocalServiceEJBImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class CompanyLocalServiceEJBImpl implements CompanyLocalService,
	SessionBean {
	public void checkCompany(java.lang.String companyId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		CompanyLocalServiceFactory.getTxImpl().checkCompany(companyId);
	}

	public void checkCompanyKey(java.lang.String companyId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		CompanyLocalServiceFactory.getTxImpl().checkCompanyKey(companyId);
	}

	public java.util.List getCompanies()
		throws com.liferay.portal.SystemException {
		return CompanyLocalServiceFactory.getTxImpl().getCompanies();
	}

	public com.liferay.portal.model.Company getCompany(
		java.lang.String companyId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return CompanyLocalServiceFactory.getTxImpl().getCompany(companyId);
	}

	public com.liferay.portal.kernel.search.Hits search(
		java.lang.String companyId, java.lang.String keywords)
		throws com.liferay.portal.SystemException {
		return CompanyLocalServiceFactory.getTxImpl().search(companyId, keywords);
	}

	public com.liferay.portal.kernel.search.Hits search(
		java.lang.String companyId, java.lang.String portletId, long groupId,
		java.lang.String type, java.lang.String keywords)
		throws com.liferay.portal.SystemException {
		return CompanyLocalServiceFactory.getTxImpl().search(companyId,
			portletId, groupId, type, keywords);
	}

	public com.liferay.portal.model.Company updateCompany(
		java.lang.String companyId, java.lang.String portalURL,
		java.lang.String homeURL, java.lang.String mx, java.lang.String name,
		java.lang.String legalName, java.lang.String legalId,
		java.lang.String legalType, java.lang.String sicCode,
		java.lang.String tickerSymbol, java.lang.String industry,
		java.lang.String type, java.lang.String size)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return CompanyLocalServiceFactory.getTxImpl().updateCompany(companyId,
			portalURL, homeURL, mx, name, legalName, legalId, legalType,
			sicCode, tickerSymbol, industry, type, size);
	}

	public void updateDisplay(java.lang.String companyId,
		java.lang.String languageId, java.lang.String timeZoneId,
		java.lang.String resolution)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		CompanyLocalServiceFactory.getTxImpl().updateDisplay(companyId,
			languageId, timeZoneId, resolution);
	}

	public void updateLogo(java.lang.String companyId, java.io.File file)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		CompanyLocalServiceFactory.getTxImpl().updateLogo(companyId, file);
	}

	public void updateSecurity(java.lang.String companyId,
		java.lang.String authType, boolean autoLogin, boolean sendPassword,
		boolean strangers)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		CompanyLocalServiceFactory.getTxImpl().updateSecurity(companyId,
			authType, autoLogin, sendPassword, strangers);
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