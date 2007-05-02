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

import com.liferay.portal.service.ClassNameMapperLocalService;
import com.liferay.portal.service.ClassNameMapperLocalServiceFactory;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * <a href="ClassNameMapperLocalServiceEJBImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be overwritten
 * the next time is generated.
 * </p>
 *
 * <p>
 * This class is the EJB implementation of the service that is used when Liferay
 * is run inside a full J2EE container.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portal.service.ClassNameMapperLocalService
 * @see com.liferay.portal.service.ClassNameMapperLocalServiceUtil
 * @see com.liferay.portal.service.ejb.ClassNameMapperLocalServiceEJB
 * @see com.liferay.portal.service.ejb.ClassNameMapperLocalServiceHome
 * @see com.liferay.portal.service.impl.ClassNameMapperLocalServiceImpl
 *
 */
public class ClassNameMapperLocalServiceEJBImpl
	implements ClassNameMapperLocalService, SessionBean {
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		return ClassNameMapperLocalServiceFactory.getTxImpl().dynamicQuery(queryInitializer);
	}

	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException {
		return ClassNameMapperLocalServiceFactory.getTxImpl().dynamicQuery(queryInitializer,
			begin, end);
	}

	public void checkClassNameMappers()
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		ClassNameMapperLocalServiceFactory.getTxImpl().checkClassNameMappers();
	}

	public com.liferay.portal.model.ClassNameMapper getClassNameMapper(
		long classNameMapperId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return ClassNameMapperLocalServiceFactory.getTxImpl()
												 .getClassNameMapper(classNameMapperId);
	}

	public com.liferay.portal.model.ClassNameMapper getClassNameMapper(
		java.lang.String className)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return ClassNameMapperLocalServiceFactory.getTxImpl()
												 .getClassNameMapper(className);
	}

	public long getClassNameMapperId(java.lang.String className)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return ClassNameMapperLocalServiceFactory.getTxImpl()
												 .getClassNameMapperId(className);
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