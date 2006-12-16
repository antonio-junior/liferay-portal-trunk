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

package com.liferay.portlet.softwarerepository.service.ejb;

import com.liferay.portal.service.impl.PrincipalSessionBean;

import com.liferay.portlet.softwarerepository.service.SRProductVersionService;
import com.liferay.portlet.softwarerepository.service.SRProductVersionServiceFactory;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * <a href="SRProductVersionServiceEJBImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class SRProductVersionServiceEJBImpl implements SRProductVersionService,
	SessionBean {
	public com.liferay.portlet.softwarerepository.model.SRProductVersion addProductVersion(
		long productEntryId, java.lang.String version,
		java.lang.String changeLog, long[] frameworkVersionIds,
		java.lang.String downloadPageURL, java.lang.String directDownloadURL,
		boolean repoStoreArtifact)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		PrincipalSessionBean.setThreadValues(_sc);

		return SRProductVersionServiceFactory.getTxImpl().addProductVersion(productEntryId,
			version, changeLog, frameworkVersionIds, downloadPageURL,
			directDownloadURL, repoStoreArtifact);
	}

	public void deleteProductVersion(long productVersionId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		PrincipalSessionBean.setThreadValues(_sc);
		SRProductVersionServiceFactory.getTxImpl().deleteProductVersion(productVersionId);
	}

	public com.liferay.portlet.softwarerepository.model.SRProductVersion getProductVersion(
		long productVersionId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		PrincipalSessionBean.setThreadValues(_sc);

		return SRProductVersionServiceFactory.getTxImpl().getProductVersion(productVersionId);
	}

	public java.util.List getProductVersions(long productEntryId, int begin,
		int end)
		throws com.liferay.portal.SystemException, java.rmi.RemoteException {
		PrincipalSessionBean.setThreadValues(_sc);

		return SRProductVersionServiceFactory.getTxImpl().getProductVersions(productEntryId,
			begin, end);
	}

	public int getProductVersionsCount(long productEntryId)
		throws com.liferay.portal.SystemException, java.rmi.RemoteException {
		PrincipalSessionBean.setThreadValues(_sc);

		return SRProductVersionServiceFactory.getTxImpl()
											 .getProductVersionsCount(productEntryId);
	}

	public com.liferay.portlet.softwarerepository.model.SRProductVersion updateProductVersion(
		long productVersionId, java.lang.String version,
		java.lang.String changeLog, long[] frameworkVersionIds,
		java.lang.String downloadPageURL, java.lang.String directDownloadURL,
		boolean repoStoreArtifact)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		PrincipalSessionBean.setThreadValues(_sc);

		return SRProductVersionServiceFactory.getTxImpl().updateProductVersion(productVersionId,
			version, changeLog, frameworkVersionIds, downloadPageURL,
			directDownloadURL, repoStoreArtifact);
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