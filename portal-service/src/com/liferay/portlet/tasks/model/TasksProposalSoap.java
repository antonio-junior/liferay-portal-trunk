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

package com.liferay.portlet.tasks.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TasksProposalSoap implements Serializable {
	public static TasksProposalSoap toSoapModel(TasksProposal model) {
		TasksProposalSoap soapModel = new TasksProposalSoap();

		soapModel.setProposalId(model.getProposalId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setPublishDate(model.getPublishDate());
		soapModel.setDueDate(model.getDueDate());

		return soapModel;
	}

	public static TasksProposalSoap[] toSoapModels(TasksProposal[] models) {
		TasksProposalSoap[] soapModels = new TasksProposalSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TasksProposalSoap[][] toSoapModels(TasksProposal[][] models) {
		TasksProposalSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TasksProposalSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TasksProposalSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TasksProposalSoap[] toSoapModels(List<TasksProposal> models) {
		List<TasksProposalSoap> soapModels = new ArrayList<TasksProposalSoap>(models.size());

		for (TasksProposal model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TasksProposalSoap[soapModels.size()]);
	}

	public TasksProposalSoap() {
	}

	public long getPrimaryKey() {
		return _proposalId;
	}

	public void setPrimaryKey(long pk) {
		setProposalId(pk);
	}

	public long getProposalId() {
		return _proposalId;
	}

	public void setProposalId(long proposalId) {
		_proposalId = proposalId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public String getClassPK() {
		return _classPK;
	}

	public void setClassPK(String classPK) {
		_classPK = classPK;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getPublishDate() {
		return _publishDate;
	}

	public void setPublishDate(Date publishDate) {
		_publishDate = publishDate;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	private long _proposalId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private String _classPK;
	private String _name;
	private String _description;
	private Date _publishDate;
	private Date _dueDate;
}