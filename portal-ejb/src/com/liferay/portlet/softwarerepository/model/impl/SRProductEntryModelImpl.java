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

package com.liferay.portlet.softwarerepository.model.impl;

import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PropsUtil;

import com.liferay.util.DateUtil;
import com.liferay.util.GetterUtil;
import com.liferay.util.XSSUtil;

import java.util.Date;

/**
 * <a href="SRProductEntryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class SRProductEntryModelImpl extends BaseModelImpl {
	public static boolean XSS_ALLOW_BY_MODEL = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portlet.softwarerepository.model.SRProductEntry"),
			XSS_ALLOW);
	public static boolean XSS_ALLOW_GROUPID = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portlet.softwarerepository.model.SRProductEntry.groupId"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_COMPANYID = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portlet.softwarerepository.model.SRProductEntry.companyId"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_USERID = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portlet.softwarerepository.model.SRProductEntry.userId"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_USERNAME = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portlet.softwarerepository.model.SRProductEntry.userName"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_NAME = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portlet.softwarerepository.model.SRProductEntry.name"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_TYPE = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portlet.softwarerepository.model.SRProductEntry.type"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_SHORTDESCRIPTION = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portlet.softwarerepository.model.SRProductEntry.shortDescription"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_LONGDESCRIPTION = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portlet.softwarerepository.model.SRProductEntry.longDescription"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_PAGEURL = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portlet.softwarerepository.model.SRProductEntry.pageURL"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_REPOGROUPID = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portlet.softwarerepository.model.SRProductEntry.repoGroupId"),
			XSS_ALLOW_BY_MODEL);
	public static boolean XSS_ALLOW_REPOARTIFACTID = GetterUtil.getBoolean(PropsUtil.get(
				"xss.allow.com.liferay.portlet.softwarerepository.model.SRProductEntry.repoArtifactId"),
			XSS_ALLOW_BY_MODEL);
	public static long LOCK_EXPIRATION_TIME = GetterUtil.getLong(PropsUtil.get(
				"lock.expiration.time.com.liferay.portlet.softwarerepository.model.SRProductEntryModel"));

	public SRProductEntryModelImpl() {
	}

	public long getPrimaryKey() {
		return _productEntryId;
	}

	public void setPrimaryKey(long pk) {
		setProductEntryId(pk);
	}

	public long getProductEntryId() {
		return _productEntryId;
	}

	public void setProductEntryId(long productEntryId) {
		if (productEntryId != _productEntryId) {
			_productEntryId = productEntryId;
		}
	}

	public String getGroupId() {
		return GetterUtil.getString(_groupId);
	}

	public void setGroupId(String groupId) {
		if (((groupId == null) && (_groupId != null)) ||
				((groupId != null) && (_groupId == null)) ||
				((groupId != null) && (_groupId != null) &&
				!groupId.equals(_groupId))) {
			if (!XSS_ALLOW_GROUPID) {
				groupId = XSSUtil.strip(groupId);
			}

			_groupId = groupId;
		}
	}

	public String getCompanyId() {
		return GetterUtil.getString(_companyId);
	}

	public void setCompanyId(String companyId) {
		if (((companyId == null) && (_companyId != null)) ||
				((companyId != null) && (_companyId == null)) ||
				((companyId != null) && (_companyId != null) &&
				!companyId.equals(_companyId))) {
			if (!XSS_ALLOW_COMPANYID) {
				companyId = XSSUtil.strip(companyId);
			}

			_companyId = companyId;
		}
	}

	public String getUserId() {
		return GetterUtil.getString(_userId);
	}

	public void setUserId(String userId) {
		if (((userId == null) && (_userId != null)) ||
				((userId != null) && (_userId == null)) ||
				((userId != null) && (_userId != null) &&
				!userId.equals(_userId))) {
			if (!XSS_ALLOW_USERID) {
				userId = XSSUtil.strip(userId);
			}

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

	public String getName() {
		return GetterUtil.getString(_name);
	}

	public void setName(String name) {
		if (((name == null) && (_name != null)) ||
				((name != null) && (_name == null)) ||
				((name != null) && (_name != null) && !name.equals(_name))) {
			if (!XSS_ALLOW_NAME) {
				name = XSSUtil.strip(name);
			}

			_name = name;
		}
	}

	public String getType() {
		return GetterUtil.getString(_type);
	}

	public void setType(String type) {
		if (((type == null) && (_type != null)) ||
				((type != null) && (_type == null)) ||
				((type != null) && (_type != null) && !type.equals(_type))) {
			if (!XSS_ALLOW_TYPE) {
				type = XSSUtil.strip(type);
			}

			_type = type;
		}
	}

	public String getShortDescription() {
		return GetterUtil.getString(_shortDescription);
	}

	public void setShortDescription(String shortDescription) {
		if (((shortDescription == null) && (_shortDescription != null)) ||
				((shortDescription != null) && (_shortDescription == null)) ||
				((shortDescription != null) && (_shortDescription != null) &&
				!shortDescription.equals(_shortDescription))) {
			if (!XSS_ALLOW_SHORTDESCRIPTION) {
				shortDescription = XSSUtil.strip(shortDescription);
			}

			_shortDescription = shortDescription;
		}
	}

	public String getLongDescription() {
		return GetterUtil.getString(_longDescription);
	}

	public void setLongDescription(String longDescription) {
		if (((longDescription == null) && (_longDescription != null)) ||
				((longDescription != null) && (_longDescription == null)) ||
				((longDescription != null) && (_longDescription != null) &&
				!longDescription.equals(_longDescription))) {
			if (!XSS_ALLOW_LONGDESCRIPTION) {
				longDescription = XSSUtil.strip(longDescription);
			}

			_longDescription = longDescription;
		}
	}

	public String getPageURL() {
		return GetterUtil.getString(_pageURL);
	}

	public void setPageURL(String pageURL) {
		if (((pageURL == null) && (_pageURL != null)) ||
				((pageURL != null) && (_pageURL == null)) ||
				((pageURL != null) && (_pageURL != null) &&
				!pageURL.equals(_pageURL))) {
			if (!XSS_ALLOW_PAGEURL) {
				pageURL = XSSUtil.strip(pageURL);
			}

			_pageURL = pageURL;
		}
	}

	public String getRepoGroupId() {
		return GetterUtil.getString(_repoGroupId);
	}

	public void setRepoGroupId(String repoGroupId) {
		if (((repoGroupId == null) && (_repoGroupId != null)) ||
				((repoGroupId != null) && (_repoGroupId == null)) ||
				((repoGroupId != null) && (_repoGroupId != null) &&
				!repoGroupId.equals(_repoGroupId))) {
			if (!XSS_ALLOW_REPOGROUPID) {
				repoGroupId = XSSUtil.strip(repoGroupId);
			}

			_repoGroupId = repoGroupId;
		}
	}

	public String getRepoArtifactId() {
		return GetterUtil.getString(_repoArtifactId);
	}

	public void setRepoArtifactId(String repoArtifactId) {
		if (((repoArtifactId == null) && (_repoArtifactId != null)) ||
				((repoArtifactId != null) && (_repoArtifactId == null)) ||
				((repoArtifactId != null) && (_repoArtifactId != null) &&
				!repoArtifactId.equals(_repoArtifactId))) {
			if (!XSS_ALLOW_REPOARTIFACTID) {
				repoArtifactId = XSSUtil.strip(repoArtifactId);
			}

			_repoArtifactId = repoArtifactId;
		}
	}

	public Object clone() {
		SRProductEntryImpl clone = new SRProductEntryImpl();
		clone.setProductEntryId(getProductEntryId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setType(getType());
		clone.setShortDescription(getShortDescription());
		clone.setLongDescription(getLongDescription());
		clone.setPageURL(getPageURL());
		clone.setRepoGroupId(getRepoGroupId());
		clone.setRepoArtifactId(getRepoArtifactId());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		SRProductEntryImpl srProductEntry = (SRProductEntryImpl)obj;
		int value = 0;
		value = DateUtil.compareTo(getModifiedDate(),
				srProductEntry.getModifiedDate());
		value = value * -1;

		if (value != 0) {
			return value;
		}

		value = getName().compareTo(srProductEntry.getName());
		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		SRProductEntryImpl srProductEntry = null;

		try {
			srProductEntry = (SRProductEntryImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = srProductEntry.getPrimaryKey();

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

	private long _productEntryId;
	private String _groupId;
	private String _companyId;
	private String _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _type;
	private String _shortDescription;
	private String _longDescription;
	private String _pageURL;
	private String _repoGroupId;
	private String _repoArtifactId;
}