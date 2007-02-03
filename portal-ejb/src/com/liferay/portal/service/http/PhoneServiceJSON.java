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

package com.liferay.portal.service.http;

import com.liferay.portal.service.PhoneServiceUtil;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * <a href="PhoneServiceJSON.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PhoneServiceJSON {
	public static JSONObject addPhone(java.lang.String className,
		java.lang.String classPK, java.lang.String number,
		java.lang.String extension, int typeId, boolean primary)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException, java.rmi.RemoteException {
		com.liferay.portal.model.Phone returnValue = PhoneServiceUtil.addPhone(className,
				classPK, number, extension, typeId, primary);

		return PhoneJSONSerializer.toJSONObject(returnValue);
	}

	public static void deletePhone(long phoneId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException, java.rmi.RemoteException {
		PhoneServiceUtil.deletePhone(phoneId);
	}

	public static JSONObject getPhone(long phoneId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException, java.rmi.RemoteException {
		com.liferay.portal.model.Phone returnValue = PhoneServiceUtil.getPhone(phoneId);

		return PhoneJSONSerializer.toJSONObject(returnValue);
	}

	public static JSONArray getPhones(java.lang.String className,
		java.lang.String classPK)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException, java.rmi.RemoteException {
		java.util.List returnValue = PhoneServiceUtil.getPhones(className,
				classPK);

		return PhoneJSONSerializer.toJSONArray(returnValue);
	}

	public static JSONObject updatePhone(long phoneId, java.lang.String number,
		java.lang.String extension, int typeId, boolean primary)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException, java.rmi.RemoteException {
		com.liferay.portal.model.Phone returnValue = PhoneServiceUtil.updatePhone(phoneId,
				number, extension, typeId, primary);

		return PhoneJSONSerializer.toJSONObject(returnValue);
	}
}