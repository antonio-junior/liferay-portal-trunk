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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PropsUtil;

import com.liferay.util.XSSUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;

/**
 * <a href="ContactModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be overwritten
 * the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>Contact_</code> table in the
 * database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portal.service.model.Contact
 * @see com.liferay.portal.service.model.ContactModel
 * @see com.liferay.portal.service.model.impl.ContactImpl
 *
 */
public class ContactModelImpl extends BaseModelImpl {
	public static String TABLE_NAME = "Contact_";
	public static Object[][] TABLE_COLUMNS = {
			{ "contactId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "userId", new Integer(Types.BIGINT) },
			{ "userName", new Integer(Types.VARCHAR) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "accountId", new Integer(Types.BIGINT) },
			{ "parentContactId", new Integer(Types.BIGINT) },
			{ "firstName", new Integer(Types.VARCHAR) },
			{ "middleName", new Integer(Types.VARCHAR) },
			{ "lastName", new Integer(Types.VARCHAR) },
			{ "prefixId", new Integer(Types.INTEGER) },
			{ "suffixId", new Integer(Types.INTEGER) },
			{ "male", new Integer(Types.BOOLEAN) },
			{ "birthday", new Integer(Types.TIMESTAMP) },
			{ "smsSn", new Integer(Types.VARCHAR) },
			{ "aimSn", new Integer(Types.VARCHAR) },
			{ "icqSn", new Integer(Types.VARCHAR) },
			{ "jabberSn", new Integer(Types.VARCHAR) },
			{ "msnSn", new Integer(Types.VARCHAR) },
			{ "skypeSn", new Integer(Types.VARCHAR) },
			{ "ymSn", new Integer(Types.VARCHAR) },
			{ "employeeStatusId", new Integer(Types.VARCHAR) },
			{ "employeeNumber", new Integer(Types.VARCHAR) },
			{ "jobTitle", new Integer(Types.VARCHAR) },
			{ "jobClass", new Integer(Types.VARCHAR) },
			{ "hoursOfOperation", new Integer(Types.VARCHAR) }
		};
	public static String TABLE_SQL_CREATE = "create table Contact_ (contactId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,accountId LONG,parentContactId LONG,firstName VARCHAR(75) null,middleName VARCHAR(75) null,lastName VARCHAR(75) null,prefixId INTEGER,suffixId INTEGER,male BOOLEAN,birthday DATE null,smsSn VARCHAR(75) null,aimSn VARCHAR(75) null,icqSn VARCHAR(75) null,jabberSn VARCHAR(75) null,msnSn VARCHAR(75) null,skypeSn VARCHAR(75) null,ymSn VARCHAR(75) null,employeeStatusId VARCHAR(75) null,employeeNumber VARCHAR(75) null,jobTitle VARCHAR(100) null,jobClass VARCHAR(75) null,hoursOfOperation VARCHAR(75) null)";
	public static String TABLE_SQL_DROP = "drop table Contact_";
	public static boolean XSS_ALLOW_BY_MODEL = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact"), XSS_ALLOW);
	public static boolean XSS_ALLOW_USERNAME = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.userName"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_FIRSTNAME = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.firstName"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_MIDDLENAME = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.middleName"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_LASTNAME = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.lastName"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_SMSSN = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.smsSn"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_AIMSN = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.aimSn"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_ICQSN = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.icqSn"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_JABBERSN = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.jabberSn"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_MSNSN = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.msnSn"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_SKYPESN = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.skypeSn"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_YMSN = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.ymSn"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_EMPLOYEESTATUSID = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.employeeStatusId"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_EMPLOYEENUMBER = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.employeeNumber"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_JOBTITLE = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.jobTitle"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_JOBCLASS = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.jobClass"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_HOURSOFOPERATION = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portal.model.Contact.hoursOfOperation"),
			XSS_ALLOW_BY_MODEL);
	public static long LOCK_EXPIRATION_TIME = GetterUtil.getLong(PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.ContactModel"));

	public ContactModelImpl() {
	}

	public long getPrimaryKey() {
		return _contactId;
	}

	public void setPrimaryKey(long pk) {
		setContactId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_contactId);
	}

	public long getContactId() {
		return _contactId;
	}

	public void setContactId(long contactId) {
		if (contactId != _contactId) {
			_contactId = contactId;
		}
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		if (companyId != _companyId) {
			_companyId = companyId;
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		if (userId != _userId) {
			_userId = userId;
		}
	}

	public String getUserName() {
		return GetterUtil.getString(_userName);
	}

	public void setUserName(String userName) {
		if (((userName == null) && (_userName != null)) ||
				((userName != null) && (_userName == null)) ||
				((userName != null) && (_userName != null) &&
				!userName.equals(_userName))) {
			if (!XSS_ALLOW_USERNAME) {
				userName = XSSUtil.strip(userName);
			}

			_userName = userName;
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		if (((createDate == null) && (_createDate != null)) ||
				((createDate != null) && (_createDate == null)) ||
				((createDate != null) && (_createDate != null) &&
				!createDate.equals(_createDate))) {
			_createDate = createDate;
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (((modifiedDate == null) && (_modifiedDate != null)) ||
				((modifiedDate != null) && (_modifiedDate == null)) ||
				((modifiedDate != null) && (_modifiedDate != null) &&
				!modifiedDate.equals(_modifiedDate))) {
			_modifiedDate = modifiedDate;
		}
	}

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		if (accountId != _accountId) {
			_accountId = accountId;
		}
	}

	public long getParentContactId() {
		return _parentContactId;
	}

	public void setParentContactId(long parentContactId) {
		if (parentContactId != _parentContactId) {
			_parentContactId = parentContactId;
		}
	}

	public String getFirstName() {
		return GetterUtil.getString(_firstName);
	}

	public void setFirstName(String firstName) {
		if (((firstName == null) && (_firstName != null)) ||
				((firstName != null) && (_firstName == null)) ||
				((firstName != null) && (_firstName != null) &&
				!firstName.equals(_firstName))) {
			if (!XSS_ALLOW_FIRSTNAME) {
				firstName = XSSUtil.strip(firstName);
			}

			_firstName = firstName;
		}
	}

	public String getMiddleName() {
		return GetterUtil.getString(_middleName);
	}

	public void setMiddleName(String middleName) {
		if (((middleName == null) && (_middleName != null)) ||
				((middleName != null) && (_middleName == null)) ||
				((middleName != null) && (_middleName != null) &&
				!middleName.equals(_middleName))) {
			if (!XSS_ALLOW_MIDDLENAME) {
				middleName = XSSUtil.strip(middleName);
			}

			_middleName = middleName;
		}
	}

	public String getLastName() {
		return GetterUtil.getString(_lastName);
	}

	public void setLastName(String lastName) {
		if (((lastName == null) && (_lastName != null)) ||
				((lastName != null) && (_lastName == null)) ||
				((lastName != null) && (_lastName != null) &&
				!lastName.equals(_lastName))) {
			if (!XSS_ALLOW_LASTNAME) {
				lastName = XSSUtil.strip(lastName);
			}

			_lastName = lastName;
		}
	}

	public int getPrefixId() {
		return _prefixId;
	}

	public void setPrefixId(int prefixId) {
		if (prefixId != _prefixId) {
			_prefixId = prefixId;
		}
	}

	public int getSuffixId() {
		return _suffixId;
	}

	public void setSuffixId(int suffixId) {
		if (suffixId != _suffixId) {
			_suffixId = suffixId;
		}
	}

	public boolean getMale() {
		return _male;
	}

	public boolean isMale() {
		return _male;
	}

	public void setMale(boolean male) {
		if (male != _male) {
			_male = male;
		}
	}

	public Date getBirthday() {
		return _birthday;
	}

	public void setBirthday(Date birthday) {
		if (((birthday == null) && (_birthday != null)) ||
				((birthday != null) && (_birthday == null)) ||
				((birthday != null) && (_birthday != null) &&
				!birthday.equals(_birthday))) {
			_birthday = birthday;
		}
	}

	public String getSmsSn() {
		return GetterUtil.getString(_smsSn);
	}

	public void setSmsSn(String smsSn) {
		if (((smsSn == null) && (_smsSn != null)) ||
				((smsSn != null) && (_smsSn == null)) ||
				((smsSn != null) && (_smsSn != null) && !smsSn.equals(_smsSn))) {
			if (!XSS_ALLOW_SMSSN) {
				smsSn = XSSUtil.strip(smsSn);
			}

			_smsSn = smsSn;
		}
	}

	public String getAimSn() {
		return GetterUtil.getString(_aimSn);
	}

	public void setAimSn(String aimSn) {
		if (((aimSn == null) && (_aimSn != null)) ||
				((aimSn != null) && (_aimSn == null)) ||
				((aimSn != null) && (_aimSn != null) && !aimSn.equals(_aimSn))) {
			if (!XSS_ALLOW_AIMSN) {
				aimSn = XSSUtil.strip(aimSn);
			}

			_aimSn = aimSn;
		}
	}

	public String getIcqSn() {
		return GetterUtil.getString(_icqSn);
	}

	public void setIcqSn(String icqSn) {
		if (((icqSn == null) && (_icqSn != null)) ||
				((icqSn != null) && (_icqSn == null)) ||
				((icqSn != null) && (_icqSn != null) && !icqSn.equals(_icqSn))) {
			if (!XSS_ALLOW_ICQSN) {
				icqSn = XSSUtil.strip(icqSn);
			}

			_icqSn = icqSn;
		}
	}

	public String getJabberSn() {
		return GetterUtil.getString(_jabberSn);
	}

	public void setJabberSn(String jabberSn) {
		if (((jabberSn == null) && (_jabberSn != null)) ||
				((jabberSn != null) && (_jabberSn == null)) ||
				((jabberSn != null) && (_jabberSn != null) &&
				!jabberSn.equals(_jabberSn))) {
			if (!XSS_ALLOW_JABBERSN) {
				jabberSn = XSSUtil.strip(jabberSn);
			}

			_jabberSn = jabberSn;
		}
	}

	public String getMsnSn() {
		return GetterUtil.getString(_msnSn);
	}

	public void setMsnSn(String msnSn) {
		if (((msnSn == null) && (_msnSn != null)) ||
				((msnSn != null) && (_msnSn == null)) ||
				((msnSn != null) && (_msnSn != null) && !msnSn.equals(_msnSn))) {
			if (!XSS_ALLOW_MSNSN) {
				msnSn = XSSUtil.strip(msnSn);
			}

			_msnSn = msnSn;
		}
	}

	public String getSkypeSn() {
		return GetterUtil.getString(_skypeSn);
	}

	public void setSkypeSn(String skypeSn) {
		if (((skypeSn == null) && (_skypeSn != null)) ||
				((skypeSn != null) && (_skypeSn == null)) ||
				((skypeSn != null) && (_skypeSn != null) &&
				!skypeSn.equals(_skypeSn))) {
			if (!XSS_ALLOW_SKYPESN) {
				skypeSn = XSSUtil.strip(skypeSn);
			}

			_skypeSn = skypeSn;
		}
	}

	public String getYmSn() {
		return GetterUtil.getString(_ymSn);
	}

	public void setYmSn(String ymSn) {
		if (((ymSn == null) && (_ymSn != null)) ||
				((ymSn != null) && (_ymSn == null)) ||
				((ymSn != null) && (_ymSn != null) && !ymSn.equals(_ymSn))) {
			if (!XSS_ALLOW_YMSN) {
				ymSn = XSSUtil.strip(ymSn);
			}

			_ymSn = ymSn;
		}
	}

	public String getEmployeeStatusId() {
		return GetterUtil.getString(_employeeStatusId);
	}

	public void setEmployeeStatusId(String employeeStatusId) {
		if (((employeeStatusId == null) && (_employeeStatusId != null)) ||
				((employeeStatusId != null) && (_employeeStatusId == null)) ||
				((employeeStatusId != null) && (_employeeStatusId != null) &&
				!employeeStatusId.equals(_employeeStatusId))) {
			if (!XSS_ALLOW_EMPLOYEESTATUSID) {
				employeeStatusId = XSSUtil.strip(employeeStatusId);
			}

			_employeeStatusId = employeeStatusId;
		}
	}

	public String getEmployeeNumber() {
		return GetterUtil.getString(_employeeNumber);
	}

	public void setEmployeeNumber(String employeeNumber) {
		if (((employeeNumber == null) && (_employeeNumber != null)) ||
				((employeeNumber != null) && (_employeeNumber == null)) ||
				((employeeNumber != null) && (_employeeNumber != null) &&
				!employeeNumber.equals(_employeeNumber))) {
			if (!XSS_ALLOW_EMPLOYEENUMBER) {
				employeeNumber = XSSUtil.strip(employeeNumber);
			}

			_employeeNumber = employeeNumber;
		}
	}

	public String getJobTitle() {
		return GetterUtil.getString(_jobTitle);
	}

	public void setJobTitle(String jobTitle) {
		if (((jobTitle == null) && (_jobTitle != null)) ||
				((jobTitle != null) && (_jobTitle == null)) ||
				((jobTitle != null) && (_jobTitle != null) &&
				!jobTitle.equals(_jobTitle))) {
			if (!XSS_ALLOW_JOBTITLE) {
				jobTitle = XSSUtil.strip(jobTitle);
			}

			_jobTitle = jobTitle;
		}
	}

	public String getJobClass() {
		return GetterUtil.getString(_jobClass);
	}

	public void setJobClass(String jobClass) {
		if (((jobClass == null) && (_jobClass != null)) ||
				((jobClass != null) && (_jobClass == null)) ||
				((jobClass != null) && (_jobClass != null) &&
				!jobClass.equals(_jobClass))) {
			if (!XSS_ALLOW_JOBCLASS) {
				jobClass = XSSUtil.strip(jobClass);
			}

			_jobClass = jobClass;
		}
	}

	public String getHoursOfOperation() {
		return GetterUtil.getString(_hoursOfOperation);
	}

	public void setHoursOfOperation(String hoursOfOperation) {
		if (((hoursOfOperation == null) && (_hoursOfOperation != null)) ||
				((hoursOfOperation != null) && (_hoursOfOperation == null)) ||
				((hoursOfOperation != null) && (_hoursOfOperation != null) &&
				!hoursOfOperation.equals(_hoursOfOperation))) {
			if (!XSS_ALLOW_HOURSOFOPERATION) {
				hoursOfOperation = XSSUtil.strip(hoursOfOperation);
			}

			_hoursOfOperation = hoursOfOperation;
		}
	}

	public Object clone() {
		ContactImpl clone = new ContactImpl();
		clone.setContactId(getContactId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAccountId(getAccountId());
		clone.setParentContactId(getParentContactId());
		clone.setFirstName(getFirstName());
		clone.setMiddleName(getMiddleName());
		clone.setLastName(getLastName());
		clone.setPrefixId(getPrefixId());
		clone.setSuffixId(getSuffixId());
		clone.setMale(getMale());
		clone.setBirthday(getBirthday());
		clone.setSmsSn(getSmsSn());
		clone.setAimSn(getAimSn());
		clone.setIcqSn(getIcqSn());
		clone.setJabberSn(getJabberSn());
		clone.setMsnSn(getMsnSn());
		clone.setSkypeSn(getSkypeSn());
		clone.setYmSn(getYmSn());
		clone.setEmployeeStatusId(getEmployeeStatusId());
		clone.setEmployeeNumber(getEmployeeNumber());
		clone.setJobTitle(getJobTitle());
		clone.setJobClass(getJobClass());
		clone.setHoursOfOperation(getHoursOfOperation());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		ContactImpl contact = (ContactImpl)obj;
		long pk = contact.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		ContactImpl contact = null;

		try {
			contact = (ContactImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = contact.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	private long _contactId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _accountId;
	private long _parentContactId;
	private String _firstName;
	private String _middleName;
	private String _lastName;
	private int _prefixId;
	private int _suffixId;
	private boolean _male;
	private Date _birthday;
	private String _smsSn;
	private String _aimSn;
	private String _icqSn;
	private String _jabberSn;
	private String _msnSn;
	private String _skypeSn;
	private String _ymSn;
	private String _employeeStatusId;
	private String _employeeNumber;
	private String _jobTitle;
	private String _jobClass;
	private String _hoursOfOperation;
}