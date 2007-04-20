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

package com.liferay.portal.service;

/**
 * <a href="PasswordPolicyServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be overwritten
 * the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the <code>com.liferay.portal.service.PasswordPolicyService</code>
 * bean. The static methods of this class calls the same methods of the bean instance.
 * It's convenient to be able to just write one line to call a method on a bean
 * instead of writing a lookup call and a method call.
 * </p>
 *
 * <p>
 * <code>com.liferay.portal.service.PasswordPolicyServiceFactory</code> is responsible
 * for the lookup of the bean.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portal.service.PasswordPolicyService
 * @see com.liferay.portal.service.PasswordPolicyServiceFactory
 *
 */
public class PasswordPolicyServiceUtil {
	public static com.liferay.portal.model.PasswordPolicy addPolicy(
		java.lang.String name, java.lang.String description,
		boolean changeable, boolean changeRequired, int minAge,
		java.lang.String storageScheme, boolean checkSyntax,
		boolean allowDictionaryWords, int minLength, boolean history,
		int historyCount, boolean expireable, int maxAge, int warningTime,
		int graceLimit, boolean lockout, int maxFailure, boolean requireUnlock,
		int lockoutDuration, int resetFailureCount)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		PasswordPolicyService passwordPolicyService = PasswordPolicyServiceFactory.getService();

		return passwordPolicyService.addPolicy(name, description, changeable,
			changeRequired, minAge, storageScheme, checkSyntax,
			allowDictionaryWords, minLength, history, historyCount, expireable,
			maxAge, warningTime, graceLimit, lockout, maxFailure,
			requireUnlock, lockoutDuration, resetFailureCount);
	}

	public static void deletePolicy(long passwordPolicyId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		PasswordPolicyService passwordPolicyService = PasswordPolicyServiceFactory.getService();
		passwordPolicyService.deletePolicy(passwordPolicyId);
	}

	public static com.liferay.portal.model.PasswordPolicy getPolicy(
		long passwordPolicyId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		PasswordPolicyService passwordPolicyService = PasswordPolicyServiceFactory.getService();

		return passwordPolicyService.getPolicy(passwordPolicyId);
	}

	public static java.util.List getPolicies()
		throws com.liferay.portal.SystemException, java.rmi.RemoteException {
		PasswordPolicyService passwordPolicyService = PasswordPolicyServiceFactory.getService();

		return passwordPolicyService.getPolicies();
	}

	public static java.util.List getPolicies(int begin, int end)
		throws com.liferay.portal.SystemException, java.rmi.RemoteException {
		PasswordPolicyService passwordPolicyService = PasswordPolicyServiceFactory.getService();

		return passwordPolicyService.getPolicies(begin, end);
	}

	public static int getPoliciesCount()
		throws com.liferay.portal.SystemException, java.rmi.RemoteException {
		PasswordPolicyService passwordPolicyService = PasswordPolicyServiceFactory.getService();

		return passwordPolicyService.getPoliciesCount();
	}

	public static com.liferay.portal.model.PasswordPolicy updatePolicy(
		long passwordPolicyId, java.lang.String name,
		java.lang.String description, boolean changeable,
		boolean changeRequired, int minAge, java.lang.String storageScheme,
		boolean checkSyntax, boolean allowDictionaryWords, int minLength,
		boolean history, int historyCount, boolean expireable, int maxAge,
		int warningTime, int graceLimit, boolean lockout, int maxFailure,
		boolean requireUnlock, int lockoutDuration, int resetFailureCount)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		PasswordPolicyService passwordPolicyService = PasswordPolicyServiceFactory.getService();

		return passwordPolicyService.updatePolicy(passwordPolicyId, name,
			description, changeable, changeRequired, minAge, storageScheme,
			checkSyntax, allowDictionaryWords, minLength, history,
			historyCount, expireable, maxAge, warningTime, graceLimit, lockout,
			maxFailure, requireUnlock, lockoutDuration, resetFailureCount);
	}
}