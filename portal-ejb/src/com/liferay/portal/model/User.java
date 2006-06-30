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

package com.liferay.portal.model;

import com.liferay.portal.service.spring.CompanyLocalServiceUtil;
import com.liferay.portal.service.spring.ContactLocalServiceUtil;
import com.liferay.portal.service.spring.GroupLocalServiceUtil;
import com.liferay.portal.service.spring.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.spring.OrganizationLocalServiceUtil;
import com.liferay.portal.service.spring.RoleLocalServiceUtil;
import com.liferay.portal.service.spring.UserLocalServiceUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.util.LocaleUtil;
import com.liferay.util.StringPool;
import com.liferay.util.Validator;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="User.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class User extends UserModel {

	public static final String DEFAULT = "default";

	public static String getActualCompanyId(String defaultUserId) {
		if (isDefaultUser(defaultUserId)) {
			return defaultUserId.substring(
				0, defaultUserId.indexOf(DEFAULT) - 1);
		}
		else {
			return null;
		}
	}

	public static String getDefaultUserId(String companyId) {
		return companyId + StringPool.PERIOD + DEFAULT;
	}

	public static boolean isDefaultUser(String userId) {
		if ((userId != null) && userId.endsWith(StringPool.PERIOD + DEFAULT)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static String getFullName(
		String firstName, String middleName, String lastName) {

		return Contact.getFullName(firstName, middleName, lastName);
	}

	public User() {
	}

	public boolean isDefaultUser() {
		return _defaultUser;
	}

	public void setCompanyId(String companyId) {
		if (companyId.equalsIgnoreCase(DEFAULT)) {
			_defaultUser = true;
		}
		else {
			_defaultUser = false;
		}

		super.setCompanyId(companyId);
	}

	public String getActualCompanyId() {
		if (isDefaultUser()) {
			return getActualCompanyId(getUserId());
		}
		else {
			return getCompanyId();
		}
	}

	public String getCompanyMx() {
		String companyMx = null;

		try {
			Company company =
				CompanyLocalServiceUtil.getCompany(getCompanyId());

			companyMx = company.getMx();
		}
		catch (Exception e) {
			_log.error(e);
		}

		return companyMx;
	}

	public boolean hasCompanyMx() {
		return hasCompanyMx(getEmailAddress());
	}

	public boolean hasCompanyMx(String emailAddress) {
		String mx = emailAddress.substring(
			emailAddress.indexOf(StringPool.AT) + 1, emailAddress.length());

		if (mx.equals(getCompanyMx())) {
			return true;
		}

		try {
			String[] mailHostNames = PrefsPropsUtil.getStringArray(
				getCompanyId(), PropsUtil.ADMIN_MAIL_HOST_NAMES);

			for (int i = 0; i < mailHostNames.length; i++) {
				if (mx.equalsIgnoreCase(mailHostNames[i])) {
					return true;
				}
			}
		}
		catch (Exception e) {
			_log.error(e);
		}

		return false;
	}

	public String getPasswordUnencrypted() {
		return _passwordUnencrypted;
	}

	public void setPasswordUnencrypted(String passwordUnencrypted) {
		_passwordUnencrypted = passwordUnencrypted;
	}

	public boolean isPasswordExpired() {
		if (getPasswordExpirationDate() != null &&
			getPasswordExpirationDate().before(new Date())) {

			return true;
		}
		else {
			return false;
		}
	}

	public Locale getLocale() {
		return _locale;
	}

	public void setLanguageId(String languageId) {
		_locale = LocaleUtil.fromLanguageId(languageId);

		super.setLanguageId(LocaleUtil.toLanguageId(_locale));
	}

	public TimeZone getTimeZone() {
		return _timeZone;
	}

	public void setTimeZoneId(String timeZoneId) {
		if (Validator.isNull(timeZoneId)) {
			timeZoneId = TimeZone.getDefault().getID();
		}

		_timeZone = TimeZone.getTimeZone(timeZoneId);

		super.setTimeZoneId(timeZoneId);
	}

	public void setResolution(String resolution) {
		if (Validator.isNull(resolution)) {
			resolution = PropsUtil.get(
				PropsUtil.DEFAULT_USER_LAYOUT_RESOLUTION);
		}

		super.setResolution(resolution);
	}

	public Contact getContact() {
		Contact contact = null;

		try {
			contact = ContactLocalServiceUtil.getContact(getUserId());
		}
		catch (Exception e) {
			contact = new Contact();

			_log.error(e);
		}

		return contact;
	}

	public String getFirstName() {
		return getContact().getFirstName();
	}

	public String getMiddleName() {
		return getContact().getMiddleName();
	}

	public String getLastName() {
		return getContact().getLastName();
	}

	public String getNickName() {
		return getContact().getNickName();
	}

	public String getFullName() {
		return getContact().getFullName();
	}

	public boolean getMale() {
		return getContact().getMale();
	}

	public boolean isMale() {
		return getMale();
	}

	public boolean getFemale() {
		return !getMale();
	}

	public boolean isFemale() {
		return getFemale();
	}

	public Date getBirthday() {
		return getContact().getBirthday();
	}

	public Group getGroup() {
		Group group = null;

		try {
			group = GroupLocalServiceUtil.getUserGroup(
				getCompanyId(), getUserId());
		}
		catch (Exception e) {
		}

		return group;
	}

	public Organization getOrganization() {
		try {
			List organizations =
				OrganizationLocalServiceUtil.getUserOrganizations(getUserId());

			for (int i = 0; i < organizations.size(); i++) {
				Organization organization = (Organization)organizations.get(i);

				if (organization.getParentOrganizationId().equals(
						Organization.DEFAULT_PARENT_ORGANIZATION_ID)) {

					return organization;
				}
			}
		}
		catch (Exception e) {
			_log.warn("User does not have belong to an organization");
		}

		return new Organization();
	}

	public Organization getLocation() {
		try {
			List organizations =
				OrganizationLocalServiceUtil.getUserOrganizations(getUserId());

			for (int i = 0; i < organizations.size(); i++) {
				Organization organization = (Organization)organizations.get(i);

				if (!organization.getParentOrganizationId().equals(
						Organization.DEFAULT_PARENT_ORGANIZATION_ID)) {

					return organization;
				}
			}
		}
		catch (Exception e) {
			_log.warn("User does not have belong to a location");
		}

		return new Organization();
	}

	public boolean hasPrivateLayouts() {
		try {
			Group group = getGroup();

			if (group == null) {
				return false;
			}

			LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
				Layout.PRIVATE + group.getGroupId());

			if (layoutSet.getPageCount() > 0) {
				return true;
			}
		}
		catch (Exception e) {
			_log.error(e);
		}

		return false;
	}

	public boolean hasPublicLayouts() {
		try {
			Group group = getGroup();

			if (group == null) {
				return false;
			}

			LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
				Layout.PUBLIC + group.getGroupId());

			if (layoutSet.getPageCount() > 0) {
				return true;
			}
		}
		catch (Exception e) {
			_log.error(e);
		}

		return false;
	}

	public boolean isLayoutsRequired() {
		try {
			Role role = RoleLocalServiceUtil.getRole(
				getCompanyId(), Role.POWER_USER);

			return UserLocalServiceUtil.hasRoleUser(
				role.getRoleId(), getUserId());
		}
		catch (Exception e) {
			return false;
		}
	}

	private static Log _log = LogFactory.getLog(User.class);

	private boolean _defaultUser;
	private String _passwordUnencrypted;
	private Locale _locale;
	private TimeZone _timeZone;
	private User _user;

}