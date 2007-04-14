<%
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
%>

<%
for (int j = 0; j < folderColumns.length; j++) {
	String folderColumn = folderColumns[j];

	if (folderColumn.equals("action")) {
		String align = SearchEntry.DEFAULT_ALIGN;

		if ((j + 1) == folderColumns.length) {
			align = "right";
		}

		String valign = SearchEntry.DEFAULT_VALIGN;

		row.addJSP(align, valign, "/html/portlet/document_library/folder_action.jsp");
	}
	else if (folderColumn.equals("folder")) {
		StringMaker sm = new StringMaker();

		sm.append("<a href=\"");
		sm.append(rowURL);
		sm.append("\">");
		sm.append("<img align=\"left\" border=\"0\" src=\"");
		sm.append(themeDisplay.getPathThemeImages());
		sm.append("/common/folder.png\">");
		sm.append("<b>");
		sm.append(curFolder.getName());
		sm.append("</b>");

		if (Validator.isNotNull(curFolder.getDescription())) {
			sm.append("<br>");
			sm.append("<span style=\"font-size: xx-small;\">");
			sm.append(curFolder.getDescription());
			sm.append("</span>");
		}

		sm.append("</a>");

		List subfolders = DLFolderLocalServiceUtil.getFolders(portletGroupId.longValue(), curFolder.getFolderId(), 0, 5);

		if (subfolders.size() > 0) {
			sm.append("<br>");
			sm.append("<span style=\"font-size: xx-small; font-weight: bold;\"><u>");
			sm.append(LanguageUtil.get(pageContext, "subfolders"));
			sm.append("</u>: ");

			for (int k = 0; k < subfolders.size(); k++) {
				DLFolder subfolder = (DLFolder)subfolders.get(k);

				rowURL.setParameter("folderId", subfolder.getFolderId());

				sm.append("<a href=\"");
				sm.append(rowURL);
				sm.append("\">");
				sm.append(subfolder.getName());
				sm.append("</a>");

				if ((k + 1) < subfolders.size()) {
					sm.append(", ");
				}
			}

			rowURL.setParameter("folderId", curFolder.getFolderId());

			sm.append("</span>");
		}

		row.addText(sm.toString());
	}
	else if (folderColumn.equals("num-of-documents")) {
		row.addText(Integer.toString(foldersCount), rowURL);
	}
	else if (folderColumn.equals("num-of-folders")) {
		row.addText(Integer.toString(fileEntriesCount), rowURL);
	}
}
%>