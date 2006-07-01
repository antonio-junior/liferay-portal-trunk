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

/**
 * <a href="DLServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class DLServiceUtil {

	public static void addDirectory(
			String companyId, String repositoryId, String dirName)
		throws PortalException, SystemException {

		try {
			DLService dlService = DLServiceFactory.getService();

			dlService.addDirectory(companyId, repositoryId, dirName);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static void addFile(
			String companyId, String portletId, String groupId,
			String repositoryId, String fileName, byte[] byteArray)
		throws PortalException, SystemException {

		try {
			DLService dlService = DLServiceFactory.getService();

			dlService.addFile(
				companyId, portletId, groupId, repositoryId, fileName,
				byteArray);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static void deleteDirectory(
			String companyId, String portletId, String repositoryId,
			String dirName)
		throws PortalException, SystemException {

		try {
			DLService dlService = DLServiceFactory.getService();

			dlService.deleteDirectory(
				companyId, portletId, repositoryId, dirName);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static void deleteFile(
			String companyId, String portletId, String repositoryId,
			String fileName)
		throws PortalException, SystemException {

		try {
			DLService dlService = DLServiceFactory.getService();

			dlService.deleteFile(companyId, portletId, repositoryId, fileName);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static void deleteFile(
			String companyId, String portletId, String repositoryId,
			String fileName, double versionNumber)
		throws PortalException, SystemException {

		try {
			DLService dlService = DLServiceFactory.getService();

			dlService.deleteFile(
				companyId, portletId, repositoryId, fileName, versionNumber);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static byte[] getFile(
			String companyId, String repositoryId, String fileName)
		throws PortalException, SystemException {

		try {
			DLService dlService = DLServiceFactory.getService();

			return dlService.getFile(companyId, repositoryId, fileName);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static byte[] getFile(
			String companyId, String repositoryId, String fileName,
			double versionNumber)
		throws PortalException, SystemException {

		try {
			DLService dlService = DLServiceFactory.getService();

			return dlService.getFile(
				companyId, repositoryId, fileName, versionNumber);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static String[] getFileNames(
			String companyId, String repositoryId, String dirName)
		throws PortalException, SystemException {

		try {
			DLService dlService = DLServiceFactory.getService();

			return dlService.getFileNames(companyId, repositoryId, dirName);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static long getFileSize(
			String companyId, String repositoryId, String fileName)
		throws PortalException, SystemException {

		try {
			DLService dlService = DLServiceFactory.getService();

			return dlService.getFileSize(companyId, repositoryId, fileName);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static void reIndex(String[] ids)
		throws PortalException, SystemException {

		try {
			DLService dlService = DLServiceFactory.getService();

			dlService.reIndex(ids);
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static void updateFile(
			String companyId, String portletId, String groupId,
			String repositoryId, String fileName, double versionNumber,
			String sourceFileName, byte[] byteArray)
		throws PortalException, SystemException {

		try {
			DLService dlService = DLServiceFactory.getService();

			dlService.updateFile(
				companyId, portletId, groupId, repositoryId, fileName,
				versionNumber, sourceFileName, byteArray);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

}