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

package com.liferay.portal.editor.fckeditor.receiver.impl;

import com.liferay.portal.editor.fckeditor.command.CommandArgument;
import com.liferay.portal.editor.fckeditor.exception.FCKException;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.LiferayWindowState;
import com.liferay.portlet.PortletURLImpl;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.spring.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.spring.DLFileEntryServiceUtil;
import com.liferay.portlet.documentlibrary.service.spring.DLFolderLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.spring.DLFolderServiceUtil;
import com.liferay.util.FileUtil;
import com.liferay.util.StringPool;

import java.io.File;

import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * <a href="DocumentCommandReceiver.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Ivica Cardic
 *
 */
public class DocumentCommandReceiver extends BaseCommandReceiver {

	protected String createFolder(CommandArgument arg) {
		try {
			DLFolder folder = _getFolder(
				arg.getGroupId(), "/" + arg.getCurrentFolder());

			DLFolderServiceUtil.addFolder(
				arg.getPlid(), folder.getFolderId(), arg.getNewFolder(),
				StringPool.BLANK, true, true);
		}
		catch (Exception e) {
			throw new FCKException(e);
		}

		return "0";
	}

	protected String fileUpload(
		CommandArgument arg, String fileName, File file, String extension) {

		try {
			DLFolder folder = _getFolder(
				arg.getGroupId(), arg.getCurrentFolder());

			DLFileEntryServiceUtil.addFileEntry(
				folder.getFolderId(), fileName, fileName, StringPool.BLANK,
				FileUtil.getBytes(file), true, true);
		}
		catch (Exception e) {
			throw new FCKException(e);
		}

		return "0";
	}

	protected void getFolders(CommandArgument arg, Node root, Document doc) {
		try {
			_getFolders(arg, root, doc);
		}
		catch (Exception e) {
			throw new FCKException(e);
		}
	}

	protected void getFoldersAndFiles(
		CommandArgument arg, Node root, Document doc) {

		try {
			_getFolders(arg, root, doc);
			_getFiles(arg, root, doc);
		}
		catch (Exception e) {
			throw new FCKException(e);
		}
	}

	private void _getFiles(CommandArgument arg, Node root, Document doc)
		throws Exception {

		Element filesEl = doc.createElement("Files");

		root.appendChild(filesEl);

		DLFolder folder = _getFolder(
			arg.getGroupId(), arg.getCurrentFolder());

		List files = DLFileEntryLocalServiceUtil.getFileEntries(
			folder.getFolderId());

		for (int i = 0; i < files.size(); i++) {
			DLFileEntry fileEntry = (DLFileEntry)files.get(i);

			Element fileEl = doc.createElement("File");

			filesEl.appendChild(fileEl);

			fileEl.setAttribute("name", fileEntry.getName());
			fileEl.setAttribute("desc", fileEntry.getName());
			fileEl.setAttribute("size", getSize(fileEntry.getSize()));

			PortletURLImpl portletURL = new PortletURLImpl(
				arg.getHttpServletRequest(), PortletKeys.DOCUMENT_LIBRARY,
				arg.getPlid(), true);

			portletURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			portletURL.setParameter(
				"struts_action", "/document_library/get_file");
			portletURL.setParameter("folderId", fileEntry.getFolderId());
			portletURL.setParameter("name", fileEntry.getName());

			fileEl.setAttribute("url", portletURL.toString());
		}
	}

	private DLFolder _getFolder(String groupId, String folderName)
		throws Exception {

		DLFolder folder = new DLFolder();

		folder.setFolderId(DLFolder.DEFAULT_PARENT_FOLDER_ID);

		if (!folderName.equals("/")) {
			StringTokenizer st = new StringTokenizer(folderName, "/");

			while (st.hasMoreTokens()) {
				String curFolderName = (String)st.nextToken();

				List folders = DLFolderLocalServiceUtil.getFolders(
					groupId, folder.getFolderId());

				for (int i = 0; i < folders.size(); i++) {
					DLFolder curFolder = (DLFolder)folders.get(i);

					if (curFolder.getName().equals(curFolderName)) {
						folder = curFolder;

						break;
					}
				}
			}
		}

		return folder;
	}

	private void _getFolders(CommandArgument arg, Node root, Document doc)
		throws Exception {

		Element foldersEl = doc.createElement("Folders");

		root.appendChild(foldersEl);

		DLFolder folder = _getFolder(
			arg.getGroupId(), arg.getCurrentFolder());

		List folders = DLFolderLocalServiceUtil.getFolders(
			arg.getGroupId(), folder.getFolderId());

		for (int i = 0; i < folders.size(); i++) {
			folder = (DLFolder)folders.get(i);

			Element folderEl = doc.createElement("Folder");

			foldersEl.appendChild(folderEl);

			folderEl.setAttribute("name", folder.getName());
		}
	}

	private static Log _log = LogFactory.getLog(DocumentCommandReceiver.class);

}