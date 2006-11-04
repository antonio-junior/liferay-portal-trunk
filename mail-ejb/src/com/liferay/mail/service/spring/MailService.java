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

package com.liferay.mail.service.spring;

import com.liferay.portal.SystemException;
import com.liferay.util.mail.MailMessage;

import java.rmi.RemoteException;

import java.util.List;

/**
 * <a href="MailService.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public interface MailService {

	public void addForward(
			String userId, List emailAddresses, boolean leaveCopy)
		throws RemoteException, SystemException;

	public void addUser(
			String userId, String password, String firstName, String middleName,
			String lastName, String emailAddress)
		throws RemoteException, SystemException;

	public void addVacationMessage(
			String userId, String emailAddress, String vacationMessage)
		throws RemoteException, SystemException;

	public void deleteEmailAddress(String userId)
		throws RemoteException, SystemException;

	public void deleteUser(String userId)
		throws RemoteException, SystemException;

	public void sendEmail(MailMessage mailMessage)
		throws RemoteException, SystemException;

	public void updateBlocked(String userId, List blocked)
		throws RemoteException, SystemException;

	public void updateEmailAddress(String userId, String emailAddress)
		throws RemoteException, SystemException;

	public void updatePassword(String userId, String password)
		throws RemoteException, SystemException;

}