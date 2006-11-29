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

package com.liferay.portlet.shopping.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;

import com.liferay.portlet.shopping.service.ShoppingOrderServiceUtil;

import java.rmi.RemoteException;

/**
 * <a href="ShoppingOrderServiceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class ShoppingOrderServiceSoap {
	public static void completeOrder(java.lang.String plid,
		java.lang.String orderId, java.lang.String ppTxnId,
		java.lang.String ppPaymentStatus, double ppPaymentGross,
		java.lang.String ppReceiverEmail, java.lang.String ppPayerEmail)
		throws RemoteException {
		try {
			ShoppingOrderServiceUtil.completeOrder(plid, orderId, ppTxnId,
				ppPaymentStatus, ppPaymentGross, ppReceiverEmail, ppPayerEmail);
		}
		catch (Exception e) {
			String stackTrace = StackTraceUtil.getStackTrace(e);
			_log.error(stackTrace);
			throw new RemoteException(stackTrace);
		}
	}

	public static void deleteOrder(java.lang.String plid,
		java.lang.String orderId) throws RemoteException {
		try {
			ShoppingOrderServiceUtil.deleteOrder(plid, orderId);
		}
		catch (Exception e) {
			String stackTrace = StackTraceUtil.getStackTrace(e);
			_log.error(stackTrace);
			throw new RemoteException(stackTrace);
		}
	}

	public static com.liferay.portlet.shopping.model.ShoppingOrderSoap getOrder(
		java.lang.String plid, java.lang.String orderId)
		throws RemoteException {
		try {
			com.liferay.portlet.shopping.model.ShoppingOrder returnValue = ShoppingOrderServiceUtil.getOrder(plid,
					orderId);

			return com.liferay.portlet.shopping.model.ShoppingOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			String stackTrace = StackTraceUtil.getStackTrace(e);
			_log.error(stackTrace);
			throw new RemoteException(stackTrace);
		}
	}

	public static void sendEmail(java.lang.String plid,
		java.lang.String orderId, java.lang.String emailType)
		throws RemoteException {
		try {
			ShoppingOrderServiceUtil.sendEmail(plid, orderId, emailType);
		}
		catch (Exception e) {
			String stackTrace = StackTraceUtil.getStackTrace(e);
			_log.error(stackTrace);
			throw new RemoteException(stackTrace);
		}
	}

	public static com.liferay.portlet.shopping.model.ShoppingOrderSoap updateOrder(
		java.lang.String plid, java.lang.String orderId,
		java.lang.String billingFirstName, java.lang.String billingLastName,
		java.lang.String billingEmailAddress, java.lang.String billingCompany,
		java.lang.String billingStreet, java.lang.String billingCity,
		java.lang.String billingState, java.lang.String billingZip,
		java.lang.String billingCountry, java.lang.String billingPhone,
		boolean shipToBilling, java.lang.String shippingFirstName,
		java.lang.String shippingLastName,
		java.lang.String shippingEmailAddress,
		java.lang.String shippingCompany, java.lang.String shippingStreet,
		java.lang.String shippingCity, java.lang.String shippingState,
		java.lang.String shippingZip, java.lang.String shippingCountry,
		java.lang.String shippingPhone, java.lang.String ccName,
		java.lang.String ccType, java.lang.String ccNumber, int ccExpMonth,
		int ccExpYear, java.lang.String ccVerNumber, java.lang.String comments)
		throws RemoteException {
		try {
			com.liferay.portlet.shopping.model.ShoppingOrder returnValue = ShoppingOrderServiceUtil.updateOrder(plid,
					orderId, billingFirstName, billingLastName,
					billingEmailAddress, billingCompany, billingStreet,
					billingCity, billingState, billingZip, billingCountry,
					billingPhone, shipToBilling, shippingFirstName,
					shippingLastName, shippingEmailAddress, shippingCompany,
					shippingStreet, shippingCity, shippingState, shippingZip,
					shippingCountry, shippingPhone, ccName, ccType, ccNumber,
					ccExpMonth, ccExpYear, ccVerNumber, comments);

			return com.liferay.portlet.shopping.model.ShoppingOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			String stackTrace = StackTraceUtil.getStackTrace(e);
			_log.error(stackTrace);
			throw new RemoteException(stackTrace);
		}
	}

	public static com.liferay.portlet.shopping.model.ShoppingOrderSoap updateOrder(
		java.lang.String plid, java.lang.String orderId,
		java.lang.String ppTxnId, java.lang.String ppPaymentStatus,
		double ppPaymentGross, java.lang.String ppReceiverEmail,
		java.lang.String ppPayerEmail) throws RemoteException {
		try {
			com.liferay.portlet.shopping.model.ShoppingOrder returnValue = ShoppingOrderServiceUtil.updateOrder(plid,
					orderId, ppTxnId, ppPaymentStatus, ppPaymentGross,
					ppReceiverEmail, ppPayerEmail);

			return com.liferay.portlet.shopping.model.ShoppingOrderSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			String stackTrace = StackTraceUtil.getStackTrace(e);
			_log.error(stackTrace);
			throw new RemoteException(stackTrace);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ShoppingOrderServiceSoap.class);
}