/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the User service. Represents a row in the &quot;User_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.UserModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.UserImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see User
 * @see com.liferay.portal.model.impl.UserImpl
 * @see com.liferay.portal.model.impl.UserModelImpl
 * @generated
 */
public interface UserModel extends BaseModel<User> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a user model instance should use the {@link User} interface instead.
	 */

	/**
	 * Gets the primary key of this user.
	 *
	 * @return the primary key of this user
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this user
	 *
	 * @param pk the primary key of this user
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the uuid of this user.
	 *
	 * @return the uuid of this user
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this user.
	 *
	 * @param uuid the uuid of this user
	 */
	public void setUuid(String uuid);

	/**
	 * Gets the user ID of this user.
	 *
	 * @return the user ID of this user
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this user.
	 *
	 * @param userId the user ID of this user
	 */
	public void setUserId(long userId);

	/**
	 * Gets the user uuid of this user.
	 *
	 * @return the user uuid of this user
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this user.
	 *
	 * @param userUuid the user uuid of this user
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Gets the company ID of this user.
	 *
	 * @return the company ID of this user
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this user.
	 *
	 * @param companyId the company ID of this user
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the create date of this user.
	 *
	 * @return the create date of this user
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this user.
	 *
	 * @param createDate the create date of this user
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Gets the modified date of this user.
	 *
	 * @return the modified date of this user
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this user.
	 *
	 * @param modifiedDate the modified date of this user
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Gets the default user of this user.
	 *
	 * @return the default user of this user
	 */
	public boolean getDefaultUser();

	/**
	 * Determines if this user is default user.
	 *
	 * @return <code>true</code> if this user is default user; <code>false</code> otherwise
	 */
	public boolean isDefaultUser();

	/**
	 * Sets whether this user is default user.
	 *
	 * @param defaultUser the default user of this user
	 */
	public void setDefaultUser(boolean defaultUser);

	/**
	 * Gets the contact ID of this user.
	 *
	 * @return the contact ID of this user
	 */
	public long getContactId();

	/**
	 * Sets the contact ID of this user.
	 *
	 * @param contactId the contact ID of this user
	 */
	public void setContactId(long contactId);

	/**
	 * Gets the password of this user.
	 *
	 * @return the password of this user
	 */
	@AutoEscape
	public String getPassword();

	/**
	 * Sets the password of this user.
	 *
	 * @param password the password of this user
	 */
	public void setPassword(String password);

	/**
	 * Gets the password encrypted of this user.
	 *
	 * @return the password encrypted of this user
	 */
	public boolean getPasswordEncrypted();

	/**
	 * Determines if this user is password encrypted.
	 *
	 * @return <code>true</code> if this user is password encrypted; <code>false</code> otherwise
	 */
	public boolean isPasswordEncrypted();

	/**
	 * Sets whether this user is password encrypted.
	 *
	 * @param passwordEncrypted the password encrypted of this user
	 */
	public void setPasswordEncrypted(boolean passwordEncrypted);

	/**
	 * Gets the password reset of this user.
	 *
	 * @return the password reset of this user
	 */
	public boolean getPasswordReset();

	/**
	 * Determines if this user is password reset.
	 *
	 * @return <code>true</code> if this user is password reset; <code>false</code> otherwise
	 */
	public boolean isPasswordReset();

	/**
	 * Sets whether this user is password reset.
	 *
	 * @param passwordReset the password reset of this user
	 */
	public void setPasswordReset(boolean passwordReset);

	/**
	 * Gets the password modified date of this user.
	 *
	 * @return the password modified date of this user
	 */
	public Date getPasswordModifiedDate();

	/**
	 * Sets the password modified date of this user.
	 *
	 * @param passwordModifiedDate the password modified date of this user
	 */
	public void setPasswordModifiedDate(Date passwordModifiedDate);

	/**
	 * Gets the digest of this user.
	 *
	 * @return the digest of this user
	 */
	@AutoEscape
	public String getDigest();

	/**
	 * Sets the digest of this user.
	 *
	 * @param digest the digest of this user
	 */
	public void setDigest(String digest);

	/**
	 * Gets the reminder query question of this user.
	 *
	 * @return the reminder query question of this user
	 */
	@AutoEscape
	public String getReminderQueryQuestion();

	/**
	 * Sets the reminder query question of this user.
	 *
	 * @param reminderQueryQuestion the reminder query question of this user
	 */
	public void setReminderQueryQuestion(String reminderQueryQuestion);

	/**
	 * Gets the reminder query answer of this user.
	 *
	 * @return the reminder query answer of this user
	 */
	@AutoEscape
	public String getReminderQueryAnswer();

	/**
	 * Sets the reminder query answer of this user.
	 *
	 * @param reminderQueryAnswer the reminder query answer of this user
	 */
	public void setReminderQueryAnswer(String reminderQueryAnswer);

	/**
	 * Gets the grace login count of this user.
	 *
	 * @return the grace login count of this user
	 */
	public int getGraceLoginCount();

	/**
	 * Sets the grace login count of this user.
	 *
	 * @param graceLoginCount the grace login count of this user
	 */
	public void setGraceLoginCount(int graceLoginCount);

	/**
	 * Gets the screen name of this user.
	 *
	 * @return the screen name of this user
	 */
	@AutoEscape
	public String getScreenName();

	/**
	 * Sets the screen name of this user.
	 *
	 * @param screenName the screen name of this user
	 */
	public void setScreenName(String screenName);

	/**
	 * Gets the email address of this user.
	 *
	 * @return the email address of this user
	 */
	@AutoEscape
	public String getEmailAddress();

	/**
	 * Sets the email address of this user.
	 *
	 * @param emailAddress the email address of this user
	 */
	public void setEmailAddress(String emailAddress);

	/**
	 * Gets the facebook ID of this user.
	 *
	 * @return the facebook ID of this user
	 */
	public long getFacebookId();

	/**
	 * Sets the facebook ID of this user.
	 *
	 * @param facebookId the facebook ID of this user
	 */
	public void setFacebookId(long facebookId);

	/**
	 * Gets the open ID of this user.
	 *
	 * @return the open ID of this user
	 */
	@AutoEscape
	public String getOpenId();

	/**
	 * Sets the open ID of this user.
	 *
	 * @param openId the open ID of this user
	 */
	public void setOpenId(String openId);

	/**
	 * Gets the portrait ID of this user.
	 *
	 * @return the portrait ID of this user
	 */
	public long getPortraitId();

	/**
	 * Sets the portrait ID of this user.
	 *
	 * @param portraitId the portrait ID of this user
	 */
	public void setPortraitId(long portraitId);

	/**
	 * Gets the language ID of this user.
	 *
	 * @return the language ID of this user
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this user.
	 *
	 * @param languageId the language ID of this user
	 */
	public void setLanguageId(String languageId);

	/**
	 * Gets the time zone ID of this user.
	 *
	 * @return the time zone ID of this user
	 */
	@AutoEscape
	public String getTimeZoneId();

	/**
	 * Sets the time zone ID of this user.
	 *
	 * @param timeZoneId the time zone ID of this user
	 */
	public void setTimeZoneId(String timeZoneId);

	/**
	 * Gets the greeting of this user.
	 *
	 * @return the greeting of this user
	 */
	@AutoEscape
	public String getGreeting();

	/**
	 * Sets the greeting of this user.
	 *
	 * @param greeting the greeting of this user
	 */
	public void setGreeting(String greeting);

	/**
	 * Gets the comments of this user.
	 *
	 * @return the comments of this user
	 */
	@AutoEscape
	public String getComments();

	/**
	 * Sets the comments of this user.
	 *
	 * @param comments the comments of this user
	 */
	public void setComments(String comments);

	/**
	 * Gets the first name of this user.
	 *
	 * @return the first name of this user
	 */
	@AutoEscape
	public String getFirstName();

	/**
	 * Sets the first name of this user.
	 *
	 * @param firstName the first name of this user
	 */
	public void setFirstName(String firstName);

	/**
	 * Gets the middle name of this user.
	 *
	 * @return the middle name of this user
	 */
	@AutoEscape
	public String getMiddleName();

	/**
	 * Sets the middle name of this user.
	 *
	 * @param middleName the middle name of this user
	 */
	public void setMiddleName(String middleName);

	/**
	 * Gets the last name of this user.
	 *
	 * @return the last name of this user
	 */
	@AutoEscape
	public String getLastName();

	/**
	 * Sets the last name of this user.
	 *
	 * @param lastName the last name of this user
	 */
	public void setLastName(String lastName);

	/**
	 * Gets the job title of this user.
	 *
	 * @return the job title of this user
	 */
	@AutoEscape
	public String getJobTitle();

	/**
	 * Sets the job title of this user.
	 *
	 * @param jobTitle the job title of this user
	 */
	public void setJobTitle(String jobTitle);

	/**
	 * Gets the login date of this user.
	 *
	 * @return the login date of this user
	 */
	public Date getLoginDate();

	/**
	 * Sets the login date of this user.
	 *
	 * @param loginDate the login date of this user
	 */
	public void setLoginDate(Date loginDate);

	/**
	 * Gets the login i p of this user.
	 *
	 * @return the login i p of this user
	 */
	@AutoEscape
	public String getLoginIP();

	/**
	 * Sets the login i p of this user.
	 *
	 * @param loginIP the login i p of this user
	 */
	public void setLoginIP(String loginIP);

	/**
	 * Gets the last login date of this user.
	 *
	 * @return the last login date of this user
	 */
	public Date getLastLoginDate();

	/**
	 * Sets the last login date of this user.
	 *
	 * @param lastLoginDate the last login date of this user
	 */
	public void setLastLoginDate(Date lastLoginDate);

	/**
	 * Gets the last login i p of this user.
	 *
	 * @return the last login i p of this user
	 */
	@AutoEscape
	public String getLastLoginIP();

	/**
	 * Sets the last login i p of this user.
	 *
	 * @param lastLoginIP the last login i p of this user
	 */
	public void setLastLoginIP(String lastLoginIP);

	/**
	 * Gets the last failed login date of this user.
	 *
	 * @return the last failed login date of this user
	 */
	public Date getLastFailedLoginDate();

	/**
	 * Sets the last failed login date of this user.
	 *
	 * @param lastFailedLoginDate the last failed login date of this user
	 */
	public void setLastFailedLoginDate(Date lastFailedLoginDate);

	/**
	 * Gets the failed login attempts of this user.
	 *
	 * @return the failed login attempts of this user
	 */
	public int getFailedLoginAttempts();

	/**
	 * Sets the failed login attempts of this user.
	 *
	 * @param failedLoginAttempts the failed login attempts of this user
	 */
	public void setFailedLoginAttempts(int failedLoginAttempts);

	/**
	 * Gets the lockout of this user.
	 *
	 * @return the lockout of this user
	 */
	public boolean getLockout();

	/**
	 * Determines if this user is lockout.
	 *
	 * @return <code>true</code> if this user is lockout; <code>false</code> otherwise
	 */
	public boolean isLockout();

	/**
	 * Sets whether this user is lockout.
	 *
	 * @param lockout the lockout of this user
	 */
	public void setLockout(boolean lockout);

	/**
	 * Gets the lockout date of this user.
	 *
	 * @return the lockout date of this user
	 */
	public Date getLockoutDate();

	/**
	 * Sets the lockout date of this user.
	 *
	 * @param lockoutDate the lockout date of this user
	 */
	public void setLockoutDate(Date lockoutDate);

	/**
	 * Gets the agreed to terms of use of this user.
	 *
	 * @return the agreed to terms of use of this user
	 */
	public boolean getAgreedToTermsOfUse();

	/**
	 * Determines if this user is agreed to terms of use.
	 *
	 * @return <code>true</code> if this user is agreed to terms of use; <code>false</code> otherwise
	 */
	public boolean isAgreedToTermsOfUse();

	/**
	 * Sets whether this user is agreed to terms of use.
	 *
	 * @param agreedToTermsOfUse the agreed to terms of use of this user
	 */
	public void setAgreedToTermsOfUse(boolean agreedToTermsOfUse);

	/**
	 * Gets the status of this user.
	 *
	 * @return the status of this user
	 */
	public int getStatus();

	/**
	 * Sets the status of this user.
	 *
	 * @param status the status of this user
	 */
	public void setStatus(int status);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(User user);

	public int hashCode();

	public User toEscapedModel();

	public String toString();

	public String toXmlString();
}