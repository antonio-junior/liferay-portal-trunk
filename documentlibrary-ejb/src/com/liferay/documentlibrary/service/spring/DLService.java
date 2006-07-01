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

package com.liferay.documentlibrary.service.spring;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

import java.rmi.RemoteException;

/**
 * <a href="DLService.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public interface DLService {

	public void addDirectory(
			String companyId, String repositoryId, String dirName)
		throws PortalException, RemoteException, SystemException;

	public void addFile(
			String companyId, String portletId, String groupId,
			String repositoryId, String fileName, byte[] byteArray)
		throws PortalException, RemoteException, SystemException;

	public void deleteDirectory(
			String companyId, String portletId, String repositoryId,
			String dirName)
		throws PortalException, RemoteException, SystemException;

	public void deleteFile(
			String companyId, String portletId, String repositoryId,
			String fileName)
		throws PortalException, RemoteException, SystemException;

	public void deleteFile(
			String companyId, String portletId, String repositoryId,
			String fileName, double versionNumber)
		throws PortalException, RemoteException, SystemException;

	public byte[] getFile(
			String companyId, String repositoryId, String fileName)
		throws PortalException, RemoteException, SystemException;

	public byte[] getFile(
			String companyId, String repositoryId, String fileName,
			double versionNumber)
		throws PortalException, RemoteException, SystemException;

	public String[] getFileNames(
			String companyId, String repositoryId, String dirName)
		throws PortalException, RemoteException, SystemException;

	public long getFileSize(
			String companyId, String repositoryId, String fileName)
		throws PortalException, RemoteException, SystemException;

	public void reIndex(String[] ids) throws RemoteException, SystemException;

	public void updateFile(
			String companyId, String portletId, String groupId,
			String repositoryId, String fileName, double versionNumber,
			String sourceFileName, byte[] byteArray)
		throws PortalException, RemoteException, SystemException;

}