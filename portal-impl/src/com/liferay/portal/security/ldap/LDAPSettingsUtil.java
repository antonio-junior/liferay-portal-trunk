/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.security.ldap;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsKeys;
import com.liferay.portal.util.PropsValues;

import java.util.Properties;
import java.io.IOException;

/**
 * <a href="LDAPSettingsUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Edward Han
 */
public class LDAPSettingsUtil {
	public static boolean isAuthEnabled(long companyId) throws SystemException {
		return PrefsPropsUtil.getBoolean(
			companyId, PropsKeys.LDAP_AUTH_ENABLED,
			PropsValues.LDAP_AUTH_ENABLED);
	}

	public static boolean isExportEnabled(long companyId)
		throws SystemException {

		return PrefsPropsUtil.getBoolean(
			companyId, PropsKeys.LDAP_EXPORT_ENABLED,
			PropsValues.LDAP_EXPORT_ENABLED);
	}

	public static boolean isImportEnabled(long companyId)
		throws SystemException {

		return PrefsPropsUtil.getBoolean(
			companyId, PropsKeys.LDAP_IMPORT_ENABLED,
			PropsValues.LDAP_IMPORT_ENABLED);
	}

	public static boolean isImportOnStartup(long companyId)
		throws SystemException {

		return PrefsPropsUtil.
			getBoolean(companyId, PropsKeys.LDAP_IMPORT_ON_STARTUP);
	}

	public static boolean isNtlmEnabled(long companyId)
		throws SystemException {

		return isAuthEnabled(companyId) &&
			PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.NTLM_AUTH_ENABLED,
				PropsValues.NTLM_AUTH_ENABLED);
	}

	public static boolean isPasswordPolicyEnabled(long companyId)
		throws SystemException {

		return PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.LDAP_PASSWORD_POLICY_ENABLED,
				PropsValues.LDAP_PASSWORD_POLICY_ENABLED);
	}

	public static boolean isSiteMinderEnabled(long companyId)
		throws SystemException {

		return isAuthEnabled(companyId) &&
			PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.SITEMINDER_AUTH_ENABLED,
				PropsValues.SITEMINDER_AUTH_ENABLED);
	}

	public static String getAuthSearchFilter(
			long companyId, String emailAddress, String screenName,
			String userId)
		throws SystemException {

		String filter = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_AUTH_SEARCH_FILTER);

		if (_log.isDebugEnabled()) {
			_log.debug("Search filter before transformation " + filter);
		}

		filter = StringUtil.replace(
			filter,
			new String[] {
				"@company_id@", "@email_address@", "@screen_name@", "@user_id@"
			},
			new String[] {
				String.valueOf(companyId), emailAddress, screenName,
				userId
			});

		if (_log.isDebugEnabled()) {
			_log.debug("Search filter after transformation " + filter);
		}

		return filter;
	}

	public static Properties getContactMappings(long companyId)
		throws IOException, SystemException {

		return PropertiesUtil.load(
			PrefsPropsUtil.getString(companyId, PropsKeys.LDAP_CONTACT_MAPPINGS));
	}

	public static Properties getCustomMappings(long companyId)
		throws IOException, SystemException {

		return PropertiesUtil.load(
			PrefsPropsUtil.getString(companyId, PropsKeys.LDAP_USER_CUSTOM_MAPPINGS));
	}

	public static Properties getUserMappings(long companyId)
		throws IOException, SystemException {

		return PropertiesUtil.load(
			PrefsPropsUtil.getString(companyId, PropsKeys.LDAP_USER_MAPPINGS));
	}

	public static Properties getAllUserMappings(long companyId)
		throws IOException, SystemException {

		Properties userMappings = getUserMappings(companyId);
		Properties userCustomMappings = getCustomMappings(companyId);

		PropertiesUtil.merge(userMappings, userCustomMappings);

		return userMappings;
	}

	private static Log _log = LogFactoryUtil.getLog(LDAPSettingsUtil.class);
}
