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

package com.liferay.portal.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.CompanyServiceUtil;

import java.rmi.RemoteException;

/**
 * <a href="CompanyServiceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides a SOAP utility for the
 * <code>com.liferay.portal.service.CompanyServiceUtil</code> service
 * utility. The static methods of this class calls the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.portal.model.CompanySoap</code>. If the method in the
 * service utility returns a <code>com.liferay.portal.model.Company</code>,
 * that is translated to a <code>com.liferay.portal.model.CompanySoap</code>.
 * Methods that SOAP cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/tunnel-web/secure/axis. Set the property
 * <code>tunnel.servlet.hosts.allowed</code> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portal.model.CompanySoap
 * @see com.liferay.portal.service.CompanyServiceUtil
 * @see com.liferay.portal.service.http.CompanyServiceHttp
 *
 */
public class CompanyServiceSoap {
	public static com.liferay.portal.model.CompanySoap addCompany(
		java.lang.String webId, java.lang.String virtualHost,
		java.lang.String mx) throws RemoteException {
		try {
			com.liferay.portal.model.Company returnValue = CompanyServiceUtil.addCompany(webId,
					virtualHost, mx);

			return com.liferay.portal.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.CompanySoap getCompanyById(
		long companyId) throws RemoteException {
		try {
			com.liferay.portal.model.Company returnValue = CompanyServiceUtil.getCompanyById(companyId);

			return com.liferay.portal.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.CompanySoap getCompanyByLogoId(
		long logoId) throws RemoteException {
		try {
			com.liferay.portal.model.Company returnValue = CompanyServiceUtil.getCompanyByLogoId(logoId);

			return com.liferay.portal.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.CompanySoap getCompanyByMx(
		java.lang.String mx) throws RemoteException {
		try {
			com.liferay.portal.model.Company returnValue = CompanyServiceUtil.getCompanyByMx(mx);

			return com.liferay.portal.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.CompanySoap getCompanyByVirtualHost(
		java.lang.String virtualHost) throws RemoteException {
		try {
			com.liferay.portal.model.Company returnValue = CompanyServiceUtil.getCompanyByVirtualHost(virtualHost);

			return com.liferay.portal.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.CompanySoap getCompanyByWebId(
		java.lang.String webId) throws RemoteException {
		try {
			com.liferay.portal.model.Company returnValue = CompanyServiceUtil.getCompanyByWebId(webId);

			return com.liferay.portal.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.CompanySoap updateCompany(
		long companyId, java.lang.String virtualHost, java.lang.String mx)
		throws RemoteException {
		try {
			com.liferay.portal.model.Company returnValue = CompanyServiceUtil.updateCompany(companyId,
					virtualHost, mx);

			return com.liferay.portal.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.CompanySoap updateCompany(
		long companyId, java.lang.String virtualHost, java.lang.String mx,
		java.lang.String homeURL, java.lang.String name,
		java.lang.String legalName, java.lang.String legalId,
		java.lang.String legalType, java.lang.String sicCode,
		java.lang.String tickerSymbol, java.lang.String industry,
		java.lang.String type, java.lang.String size) throws RemoteException {
		try {
			com.liferay.portal.model.Company returnValue = CompanyServiceUtil.updateCompany(companyId,
					virtualHost, mx, homeURL, name, legalName, legalId,
					legalType, sicCode, tickerSymbol, industry, type, size);

			return com.liferay.portal.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateDisplay(long companyId,
		java.lang.String languageId, java.lang.String timeZoneId)
		throws RemoteException {
		try {
			CompanyServiceUtil.updateDisplay(companyId, languageId, timeZoneId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateSecurity(long companyId,
		java.lang.String authType, boolean autoLogin, boolean sendPassword,
		boolean strangers, boolean strangersWithMx, boolean strangersVerify,
		boolean communityLogo) throws RemoteException {
		try {
			CompanyServiceUtil.updateSecurity(companyId, authType, autoLogin,
				sendPassword, strangers, strangersWithMx, strangersVerify,
				communityLogo);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CompanyServiceSoap.class);
}