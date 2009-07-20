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

package com.liferay.portal.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhoneSoap implements Serializable {
	public static PhoneSoap toSoapModel(Phone model) {
		PhoneSoap soapModel = new PhoneSoap();

		soapModel.setPhoneId(model.getPhoneId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setNumber(model.getNumber());
		soapModel.setExtension(model.getExtension());
		soapModel.setTypeId(model.getTypeId());
		soapModel.setPrimary(model.getPrimary());

		return soapModel;
	}

	public static PhoneSoap[] toSoapModels(Phone[] models) {
		PhoneSoap[] soapModels = new PhoneSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PhoneSoap[][] toSoapModels(Phone[][] models) {
		PhoneSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PhoneSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PhoneSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PhoneSoap[] toSoapModels(List<Phone> models) {
		List<PhoneSoap> soapModels = new ArrayList<PhoneSoap>(models.size());

		for (Phone model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PhoneSoap[soapModels.size()]);
	}

	public PhoneSoap() {
	}

	public long getPrimaryKey() {
		return _phoneId;
	}

	public void setPrimaryKey(long pk) {
		setPhoneId(pk);
	}

	public long getPhoneId() {
		return _phoneId;
	}

	public void setPhoneId(long phoneId) {
		_phoneId = phoneId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public String getNumber() {
		return _number;
	}

	public void setNumber(String number) {
		_number = number;
	}

	public String getExtension() {
		return _extension;
	}

	public void setExtension(String extension) {
		_extension = extension;
	}

	public int getTypeId() {
		return _typeId;
	}

	public void setTypeId(int typeId) {
		_typeId = typeId;
	}

	public boolean getPrimary() {
		return _primary;
	}

	public boolean isPrimary() {
		return _primary;
	}

	public void setPrimary(boolean primary) {
		_primary = primary;
	}

	private long _phoneId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private String _number;
	private String _extension;
	private int _typeId;
	private boolean _primary;
}