/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.editor.fckeditor.receiver.CommandReceiver;
import com.liferay.portal.kernel.dao.hibernate.QueryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.upload.LiferayFileItemFactory;
import com.liferay.portal.upload.UploadServletRequestImpl;
import com.liferay.portal.util.PropsValues;

import java.io.File;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * <a href="BaseCommandReceiver.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ivica Cardic
 *
 */
public abstract class BaseCommandReceiver implements CommandReceiver {

	public void createFolder(
		CommandArgument argument, HttpServletRequest request,
		HttpServletResponse response) {

		Document doc = _createDocument();

		Node root = _createRoot(
			doc, argument.getCommand(), argument.getType(),
			argument.getCurrentFolder(), StringPool.BLANK);

		Element errorEl = doc.createElement("Error");

		root.appendChild(errorEl);

		String returnValue = "0";

		try {
			returnValue = createFolder(argument);
		}
		catch (FCKException fcke) {
			returnValue = "110";
		}

		errorEl.setAttribute("number", returnValue);

		_writeDocument(doc, response);
	}

	public void getFolders(
		CommandArgument argument, HttpServletRequest request,
		HttpServletResponse response) {

		Document doc = _createDocument();

		Node root = _createRoot(
			doc, argument.getCommand(), argument.getType(),
			argument.getCurrentFolder(), getPath(argument));

		getFolders(argument, doc, root);

		_writeDocument(doc, response);
	}

	public void getFoldersAndFiles(
		CommandArgument argument, HttpServletRequest request,
		HttpServletResponse response) {

		Document doc = _createDocument();

		Node root = _createRoot(
			doc, argument.getCommand(), argument.getType(),
			argument.getCurrentFolder(), getPath(argument));

		getFoldersAndFiles(argument, doc, root);

		_writeDocument(doc, response);
	}

	public void fileUpload(
		CommandArgument argument, HttpServletRequest request,
		HttpServletResponse response) {

		ServletFileUpload upload = new ServletFileUpload(
			new LiferayFileItemFactory(
				UploadServletRequestImpl.DEFAULT_TEMP_DIR));

		List<FileItem> items = null;

		try {
			items = upload.parseRequest(request);
		}
		catch (FileUploadException fue) {
			throw new FCKException(fue);
		}

		Map<String, Object> fields = new HashMap<String, Object>();

		for (FileItem item : items) {
			if (item.isFormField()) {
				fields.put(item.getFieldName(), item.getString());
			}
			else {
				fields.put(item.getFieldName(), item);
			}
		}

		DiskFileItem fileItem = (DiskFileItem)fields.get("NewFile");

		String fileName = StringUtil.replace(fileItem.getName(), "\\", "/");
		String[] fileNameArray = StringUtil.split(fileName, "/");
		fileName = fileNameArray[fileNameArray.length - 1];

		String extension = _getExtension(fileName);

		String returnValue = null;

		try {
			returnValue = fileUpload(
				argument, fileName, fileItem.getStoreLocation(), extension);
		}
		catch (FCKException fcke) {
			Throwable cause = fcke.getCause();

			returnValue = "203";

			if (cause != null) {
				String causeString = GetterUtil.getString(cause.toString());

				if ((causeString.indexOf("NoSuchFolderException") != -1) ||
					(causeString.indexOf("NoSuchGroupException") != -1)) {

					returnValue = "204";
				}
				else if (causeString.indexOf("ImageNameException") != -1) {
					returnValue = "205";
				}
				else if (causeString.indexOf("FileNameException") != -1) {
					returnValue = "206";
				}
				else if (causeString.indexOf("PrincipalException") != -1) {
					returnValue = "207";
				}
				else {
					throw fcke;
				}
			}

			_writeUploadResponse(returnValue, response);
		}

		_writeUploadResponse(returnValue, response);
	}

	protected abstract String createFolder(CommandArgument argument);

	protected abstract String fileUpload(
		CommandArgument argument, String fileName, File file, String extension);

	protected abstract void getFolders(
		CommandArgument argument, Document doc, Node root);

	protected abstract void getFoldersAndFiles(
		CommandArgument argument, Document doc, Node root);

	protected void getRootFolders(
			CommandArgument argument, Document doc, Element foldersEl)
		throws Exception {

		LinkedHashMap<String, Object> groupParams =
			new LinkedHashMap<String, Object>();

		groupParams.put("usersGroups", new Long(argument.getUserId()));

		List<Group> groups = GroupLocalServiceUtil.search(
			argument.getCompanyId(), null, null, groupParams, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);

		User user = UserLocalServiceUtil.getUserById(argument.getUserId());

		List<Organization> userOrgs = user.getOrganizations();

		for (Organization organization : userOrgs) {
			groups.add(0, organization.getGroup());
		}

		if (PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_ENABLED ||
			PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_ENABLED) {

			Group userGroup = user.getGroup();

			groups.add(0, userGroup);
		}

		for (Group group : groups) {
			Element folderEl = doc.createElement("Folder");

			foldersEl.appendChild(folderEl);

			folderEl.setAttribute(
				"name",
				group.getGroupId() + " - " + group.getDescriptiveName());
		}
	}

	protected String getPath(CommandArgument argument) {
		return StringPool.BLANK;
	}

	protected String getSize() {
		return getSize(0);
	}

	protected String getSize(int size) {
		return String.valueOf(Math.ceil(size / 1000));
	}

	private Document _createDocument() {
		try {
			Document doc = null;

			DocumentBuilderFactory factory =
				DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = null;

			builder = factory.newDocumentBuilder();

			doc = builder.newDocument();

			return doc;
		}
		catch (ParserConfigurationException pce) {
			throw new FCKException(pce);
		}
	}

	private Node _createRoot(
		Document doc, String commandStr, String typeStr, String currentPath,
		String currentUrl) {

		Element root = doc.createElement("Connector");

		doc.appendChild(root);

		root.setAttribute("command", commandStr);
		root.setAttribute("resourceType", typeStr);

		Element el = doc.createElement("CurrentFolder");

		root.appendChild(el);

		el.setAttribute("path", currentPath);
		el.setAttribute("url", currentUrl);

		return root;
	}

	private String _getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	private void _writeDocument(Document doc, HttpServletResponse response) {
		try {
			doc.getDocumentElement().normalize();

			TransformerFactory transformerFactory =
				TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(doc);

			if (_log.isDebugEnabled()) {
				StreamResult result = new StreamResult(System.out);

				transformer.transform(source, result);
			}

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");

			PrintWriter out = response.getWriter();

			StreamResult result = new StreamResult(out);

			transformer.transform(source, result);

			out.flush();
			out.close();
		}
		catch (Exception e) {
			throw new FCKException(e);
		}
	}

	private void _writeUploadResponse(
		String returnValue, HttpServletResponse response) {

		try {
			StringBuilder sb = new StringBuilder();

			String newName = StringPool.BLANK;

			sb.append("<script type=\"text/javascript\">");
			sb.append("window.parent.frames['frmUpload'].OnUploadCompleted(");
			sb.append(returnValue);
			sb.append(",'");
			sb.append(newName);
			sb.append("');");
			sb.append("</script>");

			response.setContentType("text/html; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");

			PrintWriter out = null;

			out = response.getWriter();

			out.print(sb.toString());

			out.flush();
			out.close();
		}
		catch (Exception e) {
			throw new FCKException(e);
		}
	}

	private static Log _log = LogFactory.getLog(BaseCommandReceiver.class);

}