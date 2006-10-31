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

package com.liferay.portal.service.ejb;

import com.liferay.portal.service.spring.UserLocalService;
import com.liferay.portal.spring.util.SpringUtil;

import org.springframework.context.ApplicationContext;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * <a href="UserLocalServiceEJBImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class UserLocalServiceEJBImpl implements UserLocalService, SessionBean {
	public static final String CLASS_NAME = UserLocalService.class.getName() +
		".transaction";

	public static UserLocalService getService() {
		ApplicationContext ctx = SpringUtil.getContext();

		return (UserLocalService)ctx.getBean(CLASS_NAME);
	}

	public void addGroupUsers(java.lang.String groupId,
		java.lang.String[] userIds)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		getService().addGroupUsers(groupId, userIds);
	}

	public void addRoleUsers(java.lang.String roleId, java.lang.String[] userIds)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		getService().addRoleUsers(roleId, userIds);
	}

	public void addUserGroupUsers(java.lang.String userGroupId,
		java.lang.String[] userIds)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		getService().addUserGroupUsers(userGroupId, userIds);
	}

	public com.liferay.portal.model.User addUser(
		java.lang.String creatorUserId, java.lang.String companyId,
		boolean autoUserId, java.lang.String userId, boolean autoPassword,
		java.lang.String password1, java.lang.String password2,
		boolean passwordReset, java.lang.String emailAddress,
		java.util.Locale locale, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String nickName, java.lang.String prefixId,
		java.lang.String suffixId, boolean male, int birthdayMonth,
		int birthdayDay, int birthdayYear, java.lang.String jobTitle,
		java.lang.String organizationId, java.lang.String locationId,
		boolean sendEmail, boolean checkExists)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().addUser(creatorUserId, companyId, autoUserId,
			userId, autoPassword, password1, password2, passwordReset,
			emailAddress, locale, firstName, middleName, lastName, nickName,
			prefixId, suffixId, male, birthdayMonth, birthdayDay, birthdayYear,
			jobTitle, organizationId, locationId, sendEmail, checkExists);
	}

	public com.liferay.portal.model.User addUser(
		java.lang.String creatorUserId, java.lang.String companyId,
		boolean autoUserId, java.lang.String userId, boolean autoPassword,
		java.lang.String password1, java.lang.String password2,
		boolean passwordReset, java.lang.String emailAddress,
		java.util.Locale locale, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String nickName, java.lang.String prefixId,
		java.lang.String suffixId, boolean male, int birthdayMonth,
		int birthdayDay, int birthdayYear, java.lang.String jobTitle,
		java.lang.String organizationId, java.lang.String locationId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().addUser(creatorUserId, companyId, autoUserId,
			userId, autoPassword, password1, password2, passwordReset,
			emailAddress, locale, firstName, middleName, lastName, nickName,
			prefixId, suffixId, male, birthdayMonth, birthdayDay, birthdayYear,
			jobTitle, organizationId, locationId);
	}

	public com.liferay.portal.model.User addUser(
		java.lang.String creatorUserId, java.lang.String companyId,
		boolean autoUserId, java.lang.String userId, boolean autoPassword,
		java.lang.String password1, java.lang.String password2,
		boolean passwordReset, java.lang.String emailAddress,
		java.util.Locale locale, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String nickName, java.lang.String prefixId,
		java.lang.String suffixId, boolean male, int birthdayMonth,
		int birthdayDay, int birthdayYear, java.lang.String jobTitle,
		java.lang.String organizationId, java.lang.String locationId,
		boolean sendEmail)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().addUser(creatorUserId, companyId, autoUserId,
			userId, autoPassword, password1, password2, passwordReset,
			emailAddress, locale, firstName, middleName, lastName, nickName,
			prefixId, suffixId, male, birthdayMonth, birthdayDay, birthdayYear,
			jobTitle, organizationId, locationId, sendEmail);
	}

	public int authenticateByEmailAddress(java.lang.String companyId,
		java.lang.String emailAddress, java.lang.String password,
		java.util.Map headerMap, java.util.Map parameterMap)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().authenticateByEmailAddress(companyId, emailAddress,
			password, headerMap, parameterMap);
	}

	public int authenticateByUserId(java.lang.String companyId,
		java.lang.String userId, java.lang.String password,
		java.util.Map headerMap, java.util.Map parameterMap)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().authenticateByUserId(companyId, userId, password,
			headerMap, parameterMap);
	}

	public boolean authenticateForJAAS(java.lang.String userId,
		java.lang.String encPwd)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().authenticateForJAAS(userId, encPwd);
	}

	public com.liferay.util.KeyValuePair decryptUserId(
		java.lang.String companyId, java.lang.String userId,
		java.lang.String password)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().decryptUserId(companyId, userId, password);
	}

	public void deleteRoleUser(java.lang.String roleId, java.lang.String userId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		getService().deleteRoleUser(roleId, userId);
	}

	public void deleteUser(java.lang.String userId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		getService().deleteUser(userId);
	}

	public java.lang.String encryptUserId(java.lang.String userId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().encryptUserId(userId);
	}

	public com.liferay.portal.model.User getDefaultUser(
		java.lang.String companyId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().getDefaultUser(companyId);
	}

	public java.util.List getGroupUsers(java.lang.String groupId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().getGroupUsers(groupId);
	}

	public java.util.List getPermissionUsers(java.lang.String companyId,
		java.lang.String groupId, java.lang.String name,
		java.lang.String primKey, java.lang.String actionId,
		com.liferay.portlet.enterpriseadmin.search.UserSearchTerms searchTerms,
		int begin, int end)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().getPermissionUsers(companyId, groupId, name,
			primKey, actionId, searchTerms, begin, end);
	}

	public int getPermissionUsersCount(java.lang.String companyId,
		java.lang.String groupId, java.lang.String name,
		java.lang.String primKey, java.lang.String actionId,
		com.liferay.portlet.enterpriseadmin.search.UserSearchTerms searchTerms)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().getPermissionUsersCount(companyId, groupId, name,
			primKey, actionId, searchTerms);
	}

	public java.util.List getRoleUsers(java.lang.String roleId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().getRoleUsers(roleId);
	}

	public com.liferay.portal.model.User getUserByEmailAddress(
		java.lang.String companyId, java.lang.String emailAddress)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().getUserByEmailAddress(companyId, emailAddress);
	}

	public com.liferay.portal.model.User getUserById(java.lang.String userId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().getUserById(userId);
	}

	public com.liferay.portal.model.User getUserById(
		java.lang.String companyId, java.lang.String userId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().getUserById(companyId, userId);
	}

	public java.lang.String getUserId(java.lang.String companyId,
		java.lang.String emailAddress)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().getUserId(companyId, emailAddress);
	}

	public boolean hasGroupUser(java.lang.String groupId,
		java.lang.String userId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().hasGroupUser(groupId, userId);
	}

	public boolean hasRoleUser(java.lang.String roleId, java.lang.String userId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().hasRoleUser(roleId, userId);
	}

	public boolean hasUserGroupUser(java.lang.String userGroupId,
		java.lang.String userId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().hasUserGroupUser(userGroupId, userId);
	}

	public java.util.List search(java.lang.String companyId,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, java.lang.String emailAddress,
		boolean active, java.util.Map params, boolean andSearch, int begin,
		int end, com.liferay.util.dao.hibernate.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getService().search(companyId, firstName, middleName, lastName,
			emailAddress, active, params, andSearch, begin, end, obc);
	}

	public int searchCount(java.lang.String companyId,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, java.lang.String emailAddress,
		boolean active, java.util.Map params, boolean andSearch)
		throws com.liferay.portal.SystemException {
		return getService().searchCount(companyId, firstName, middleName,
			lastName, emailAddress, active, params, andSearch);
	}

	public void sendPassword(java.lang.String companyId,
		java.lang.String emailAddress, java.lang.String remoteAddr,
		java.lang.String remoteHost, java.lang.String userAgent)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		getService().sendPassword(companyId, emailAddress, remoteAddr,
			remoteHost, userAgent);
	}

	public void setGroupUsers(java.lang.String groupId,
		java.lang.String[] userIds)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		getService().setGroupUsers(groupId, userIds);
	}

	public void setRoleUsers(java.lang.String roleId, java.lang.String[] userIds)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		getService().setRoleUsers(roleId, userIds);
	}

	public void setUserGroupUsers(java.lang.String userGroupId,
		java.lang.String[] userIds)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		getService().setUserGroupUsers(userGroupId, userIds);
	}

	public void unsetGroupUsers(java.lang.String groupId,
		java.lang.String[] userIds)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		getService().unsetGroupUsers(groupId, userIds);
	}

	public void unsetRoleUsers(java.lang.String roleId,
		java.lang.String[] userIds)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		getService().unsetRoleUsers(roleId, userIds);
	}

	public void unsetUserGroupUsers(java.lang.String userGroupId,
		java.lang.String[] userIds)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		getService().unsetUserGroupUsers(userGroupId, userIds);
	}

	public com.liferay.portal.model.User updateActive(java.lang.String userId,
		boolean active)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().updateActive(userId, active);
	}

	public com.liferay.portal.model.User updateAgreedToTermsOfUse(
		java.lang.String userId, boolean agreedToTermsOfUse)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().updateAgreedToTermsOfUse(userId, agreedToTermsOfUse);
	}

	public com.liferay.portal.model.User updateLastLogin(
		java.lang.String userId, java.lang.String loginIP)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().updateLastLogin(userId, loginIP);
	}

	public com.liferay.portal.model.User updatePassword(
		java.lang.String userId, java.lang.String password1,
		java.lang.String password2, boolean passwordReset)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().updatePassword(userId, password1, password2,
			passwordReset);
	}

	public void updatePortrait(java.lang.String userId, byte[] bytes)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		getService().updatePortrait(userId, bytes);
	}

	public com.liferay.portal.model.User updateUser(java.lang.String userId,
		java.lang.String password, java.lang.String emailAddress,
		java.lang.String languageId, java.lang.String timeZoneId,
		java.lang.String greeting, java.lang.String resolution,
		java.lang.String comments, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String nickName, java.lang.String prefixId,
		java.lang.String suffixId, boolean male, int birthdayMonth,
		int birthdayDay, int birthdayYear, java.lang.String smsSn,
		java.lang.String aimSn, java.lang.String icqSn,
		java.lang.String jabberSn, java.lang.String msnSn,
		java.lang.String skypeSn, java.lang.String ymSn,
		java.lang.String jobTitle, java.lang.String organizationId,
		java.lang.String locationId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return getService().updateUser(userId, password, emailAddress,
			languageId, timeZoneId, greeting, resolution, comments, firstName,
			middleName, lastName, nickName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, smsSn, aimSn, icqSn,
			jabberSn, msnSn, skypeSn, ymSn, jobTitle, organizationId, locationId);
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