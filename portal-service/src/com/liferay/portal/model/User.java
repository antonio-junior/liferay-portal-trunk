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

package com.liferay.portal.model;

/**
 * <a href="User.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface User extends UserModel {
	public boolean isDefaultUser();

	public void setCompanyId(java.lang.String companyId);

	public java.lang.String getActualCompanyId();

	public java.lang.String getCompanyMx();

	public boolean hasCompanyMx();

	public boolean hasCompanyMx(java.lang.String emailAddress);

	public java.lang.String getLogin()
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException;

	public java.lang.String getDisplayUserId();

	public java.lang.String getPasswordUnencrypted();

	public void setPasswordUnencrypted(java.lang.String passwordUnencrypted);

	public boolean isPasswordExpired();

	public java.util.Locale getLocale();

	public void setLanguageId(java.lang.String languageId);

	public java.util.TimeZone getTimeZone();

	public void setTimeZoneId(java.lang.String timeZoneId);

	public void setResolution(java.lang.String resolution);

	public com.liferay.portal.model.Contact getContact();

	public java.lang.String getFirstName();

	public java.lang.String getMiddleName();

	public java.lang.String getLastName();

	public java.lang.String getNickName();

	public java.lang.String getFullName();

	public boolean getMale();

	public boolean isMale();

	public boolean getFemale();

	public boolean isFemale();

	public java.util.Date getBirthday();

	public com.liferay.portal.model.Group getGroup();

	public com.liferay.portal.model.Organization getOrganization();

	public com.liferay.portal.model.Organization getLocation();

	public int getPrivateLayoutsPageCount();

	public boolean hasPrivateLayouts();

	public int getPublicLayoutsPageCount();

	public boolean hasPublicLayouts();

	public boolean isLayoutsRequired();
}