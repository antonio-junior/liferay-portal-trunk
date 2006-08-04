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

package com.liferay.portal.service.impl;

import com.liferay.counter.service.spring.CounterServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.OrgLabor;
import com.liferay.portal.service.persistence.OrgLaborUtil;
import com.liferay.portal.service.spring.ListTypeServiceUtil;
import com.liferay.portal.service.spring.OrgLaborLocalService;

import java.rmi.RemoteException;

import java.util.List;

/**
 * <a href="OrgLaborLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class OrgLaborLocalServiceImpl implements OrgLaborLocalService {

	public OrgLabor addOrgLabor(
			String organizationId, String typeId, int sunOpen, int sunClose,
			int monOpen, int monClose, int tueOpen, int tueClose, int wedOpen,
			int wedClose, int thuOpen, int thuClose, int friOpen, int friClose,
			int satOpen, int satClose)
		throws PortalException, SystemException {

		validate(typeId);

		String orgLaborId = Long.toString(CounterServiceUtil.increment(
			OrgLabor.class.getName()));

		OrgLabor orgLabor = OrgLaborUtil.create(orgLaborId);

		orgLabor.setOrganizationId(organizationId);
		orgLabor.setTypeId(typeId);
		orgLabor.setSunOpen(sunOpen);
		orgLabor.setSunClose(sunClose);
		orgLabor.setMonOpen(monOpen);
		orgLabor.setMonClose(monClose);
		orgLabor.setTueOpen(tueOpen);
		orgLabor.setTueClose(tueClose);
		orgLabor.setWedOpen(wedOpen);
		orgLabor.setWedClose(wedClose);
		orgLabor.setThuOpen(thuOpen);
		orgLabor.setThuClose(thuClose);
		orgLabor.setFriOpen(friOpen);
		orgLabor.setFriClose(friClose);
		orgLabor.setSatOpen(satOpen);
		orgLabor.setSatClose(satClose);

		OrgLaborUtil.update(orgLabor);

		return orgLabor;
	}

	public void deleteOrgLabor(String orgLaborId)
		throws PortalException, SystemException {

		OrgLaborUtil.remove(orgLaborId);
	}

	public OrgLabor getOrgLabor(String orgLaborId)
		throws PortalException, SystemException {

		return OrgLaborUtil.findByPrimaryKey(orgLaborId);
	}

	public List getOrgLabors(String organizationId) throws SystemException {
		return OrgLaborUtil.findByOrganizationId(organizationId);
	}

	public OrgLabor updateOrgLabor(
			String orgLaborId, int sunOpen, int sunClose, int monOpen,
			int monClose, int tueOpen, int tueClose, int wedOpen, int wedClose,
			int thuOpen, int thuClose, int friOpen, int friClose, int satOpen,
			int satClose)
		throws PortalException, SystemException {

		OrgLabor orgLabor = OrgLaborUtil.findByPrimaryKey(orgLaborId);

		orgLabor.setSunOpen(sunOpen);
		orgLabor.setSunClose(sunClose);
		orgLabor.setMonOpen(monOpen);
		orgLabor.setMonClose(monClose);
		orgLabor.setTueOpen(tueOpen);
		orgLabor.setTueClose(tueClose);
		orgLabor.setWedOpen(wedOpen);
		orgLabor.setWedClose(wedClose);
		orgLabor.setThuOpen(thuOpen);
		orgLabor.setThuClose(thuClose);
		orgLabor.setFriOpen(friOpen);
		orgLabor.setFriClose(friClose);
		orgLabor.setSatOpen(satOpen);
		orgLabor.setSatClose(satClose);

		OrgLaborUtil.update(orgLabor);

		return orgLabor;
	}

	protected void validate(String typeId)
		throws PortalException, SystemException {

		try {
			ListTypeServiceUtil.validate(typeId, ListType.ORGANIZATION_SERVICE);
		}
		catch (RemoteException re) {
			throw new SystemException(re);
		}
	}

}