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

package com.liferay.portal.service.http;

import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.service.spring.PhoneServiceUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.rmi.RemoteException;

/**
 * <a href="PhoneServiceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class PhoneServiceSoap {
	public static com.liferay.portal.model.PhoneModel addPhone(
		java.lang.String className, java.lang.String classPK,
		java.lang.String number, java.lang.String extension,
		java.lang.String typeId, boolean primary) throws RemoteException {
		try {
			com.liferay.portal.model.Phone returnValue = PhoneServiceUtil.addPhone(className,
					classPK, number, extension, typeId, primary);

			return returnValue;
		}
		catch (Exception e) {
			String stackTrace = StackTraceUtil.getStackTrace(e);
			_log.error(stackTrace);
			throw new RemoteException(stackTrace);
		}
	}

	public static void deletePhone(java.lang.String phoneId)
		throws RemoteException {
		try {
			PhoneServiceUtil.deletePhone(phoneId);
		}
		catch (Exception e) {
			String stackTrace = StackTraceUtil.getStackTrace(e);
			_log.error(stackTrace);
			throw new RemoteException(stackTrace);
		}
	}

	public static com.liferay.portal.model.PhoneModel getPhone(
		java.lang.String phoneId) throws RemoteException {
		try {
			com.liferay.portal.model.Phone returnValue = PhoneServiceUtil.getPhone(phoneId);

			return returnValue;
		}
		catch (Exception e) {
			String stackTrace = StackTraceUtil.getStackTrace(e);
			_log.error(stackTrace);
			throw new RemoteException(stackTrace);
		}
	}

	public static com.liferay.portal.model.PhoneModel[] getPhones(
		java.lang.String className, java.lang.String classPK)
		throws RemoteException {
		try {
			java.util.List returnValue = PhoneServiceUtil.getPhones(className,
					classPK);

			return (com.liferay.portal.model.Phone[])returnValue.toArray(new com.liferay.portal.model.Phone[0]);
		}
		catch (Exception e) {
			String stackTrace = StackTraceUtil.getStackTrace(e);
			_log.error(stackTrace);
			throw new RemoteException(stackTrace);
		}
	}

	public static com.liferay.portal.model.PhoneModel updatePhone(
		java.lang.String phoneId, java.lang.String number,
		java.lang.String extension, java.lang.String typeId, boolean primary)
		throws RemoteException {
		try {
			com.liferay.portal.model.Phone returnValue = PhoneServiceUtil.updatePhone(phoneId,
					number, extension, typeId, primary);

			return returnValue;
		}
		catch (Exception e) {
			String stackTrace = StackTraceUtil.getStackTrace(e);
			_log.error(stackTrace);
			throw new RemoteException(stackTrace);
		}
	}

	private static Log _log = LogFactory.getLog(PhoneServiceSoap.class);
}