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

package com.liferay.portlet.imagegallery.service;

/**
 * <a href="IGImageLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface IGImageLocalService {
	public com.liferay.portlet.imagegallery.model.IGImage addImage(
		java.lang.String userId, java.lang.String folderId,
		java.lang.String description, java.io.File file,
		java.lang.String contentType, boolean addCommunityPermissions,
		boolean addGuestPermissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException;

	public com.liferay.portlet.imagegallery.model.IGImage addImage(
		java.lang.String userId, java.lang.String folderId,
		java.lang.String description, java.io.File file,
		java.lang.String contentType, java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException;

	public com.liferay.portlet.imagegallery.model.IGImage addImage(
		java.lang.String userId, java.lang.String folderId,
		java.lang.String description, java.io.File file,
		java.lang.String contentType,
		java.lang.Boolean addCommunityPermissions,
		java.lang.Boolean addGuestPermissions,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException;

	public void addImageResources(java.lang.String folderId,
		java.lang.String imageId, boolean addCommunityPermissions,
		boolean addGuestPermissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException;

	public void addImageResources(
		com.liferay.portlet.imagegallery.model.IGFolder folder,
		com.liferay.portlet.imagegallery.model.IGImage image,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException;

	public void addImageResources(java.lang.String folderId,
		java.lang.String imageId, java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException;

	public void addImageResources(
		com.liferay.portlet.imagegallery.model.IGFolder folder,
		com.liferay.portlet.imagegallery.model.IGImage image,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException;

	public void deleteImage(java.lang.String companyId, java.lang.String imageId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException;

	public void deleteImage(
		com.liferay.portlet.imagegallery.model.IGImage image)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException;

	public void deleteImages(java.lang.String folderId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException;

	public int getFoldersImagesCount(java.util.List folderIds)
		throws com.liferay.portal.SystemException;

	public java.util.List getGroupImages(long groupId, int begin, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List getGroupImages(long groupId, java.lang.String userId,
		int begin, int end) throws com.liferay.portal.SystemException;

	public int getGroupImagesCount(long groupId)
		throws com.liferay.portal.SystemException;

	public int getGroupImagesCount(long groupId, java.lang.String userId)
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.imagegallery.model.IGImage getImage(
		java.lang.String companyId, java.lang.String imageId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException;

	public java.util.List getImages(java.lang.String folderId)
		throws com.liferay.portal.SystemException;

	public java.util.List getImages(java.lang.String folderId, int begin,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List getImages(java.lang.String folderId, int begin,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public int getImagesCount(java.lang.String folderId)
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.imagegallery.model.IGImage updateImage(
		java.lang.String companyId, java.lang.String imageId,
		java.lang.String folderId, java.lang.String description,
		java.io.File file, java.lang.String contentType)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException;
}