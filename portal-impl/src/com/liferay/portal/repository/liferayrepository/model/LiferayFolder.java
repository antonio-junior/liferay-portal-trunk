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

package com.liferay.portal.repository.liferayrepository.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Alexander Chow
 */
public class LiferayFolder extends LiferayModel implements Folder {

	public LiferayFolder(DLFolder dlFolder) {
		_dlFolder = dlFolder;
	}

	public LiferayFolder(DLFolder dlFolder, boolean escapedModel) {
		_dlFolder = dlFolder;
		_escapedModel = escapedModel;
	}

	public List<Folder> getAncestors() throws PortalException, SystemException {
		return toFolders(_dlFolder.getAncestors());
	}

	public Map<String, Serializable> getAttributes() {
		ExpandoBridge expandoBridge = _dlFolder.getExpandoBridge();

		return expandoBridge.getAttributes();
	}

	public long getCompanyId() {
		return _dlFolder.getCompanyId();
	}

	public Date getCreateDate() {
		return _dlFolder.getCreateDate();
	}

	public String getDescription() {
		return _dlFolder.getDescription();
	}

	public long getFolderId() {
		return _dlFolder.getFolderId();
	}

	public long getGroupId() {
		return _dlFolder.getGroupId();
	}

	public Date getLastPostDate() {
		return _dlFolder.getLastPostDate();
	}

	public Object getModel() {
		return _dlFolder;
	}

	public Date getModifiedDate() {
		return _dlFolder.getCreateDate();
	}

	public String getName() {
		return _dlFolder.getName();
	}

	public Folder getParentFolder() throws PortalException, SystemException {
		return new LiferayFolder(_dlFolder.getParentFolder());
	}

	public long getParentFolderId() {
		return _dlFolder.getParentFolderId();
	}

	public String getPath() throws PortalException, SystemException {
		return _dlFolder.getPath();
	}

	public String[] getPathArray() throws PortalException, SystemException {
		return _dlFolder.getPathArray();
	}

	public long getPrimaryKey() {
		return _dlFolder.getPrimaryKey();
	}

	public long getRepositoryId() {
		return _dlFolder.getRepositoryId();
	}

	public long getUserId() {
		return _dlFolder.getUserId();
	}

	public String getUserName() {
		return _dlFolder.getUserName();
	}

	public String getUserUuid() throws SystemException {
		return _dlFolder.getUserUuid();
	}

	public String getUuid() {
		return _dlFolder.getUuid();
	}

	public boolean hasInheritableLock() {
		return _dlFolder.hasInheritableLock();
	}

	public boolean hasLock() {
		return _dlFolder.hasLock();
	}

	public boolean isDefaultRepository() {
		if (_dlFolder.getGroupId() == _dlFolder.getRepositoryId()) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isEscapedModel() {
		return _escapedModel;
	}

	public boolean isLocked() {
		return _dlFolder.isLocked();
	}

	public boolean isMountPoint() {
		return _dlFolder.isMountPoint();
	}

	public boolean isRoot() {
		return _dlFolder.isRoot();
	}

	public void prepare() throws SystemException {
		_dlFolder.setUserUuid(getUserUuid());
	}

	public Folder toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return new LiferayFolder(_dlFolder.toEscapedModel(), true);
		}
	}

	private DLFolder _dlFolder;
	private boolean _escapedModel;

}