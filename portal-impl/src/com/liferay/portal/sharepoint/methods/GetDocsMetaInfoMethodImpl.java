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

package com.liferay.portal.sharepoint.methods;

import com.liferay.portal.sharepoint.Property;
import com.liferay.portal.sharepoint.ResponseElement;
import com.liferay.portal.sharepoint.SharepointRequest;
import com.liferay.portal.sharepoint.SharepointStorage;
import com.liferay.portal.sharepoint.Tree;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="GetDocsMetaInfoMethodImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 */
public class GetDocsMetaInfoMethodImpl extends BaseMethodImpl {

	public String getMethodName() {
		return _METHOD_NAME;
	}

	public String getRootPath(SharepointRequest sharepointRequest) {
		String urlList = sharepointRequest.getParameterValue("url_list");

		urlList = urlList.substring(1, urlList.length() - 1);

		int pos = urlList.lastIndexOf("sharepoint/");

		if (pos != -1) {
			urlList = urlList.substring(pos + 11);
		}

		return urlList;
	}

	protected List<ResponseElement> getElements(
			SharepointRequest sharepointRequest)
		throws Exception {

		List<ResponseElement> elements = new ArrayList<ResponseElement>();

		SharepointStorage storage = sharepointRequest.getSharepointStorage();

		Tree documentListTree = new Tree();

		try {
			documentListTree.addChild(
				storage.getDocumentTree(sharepointRequest));
		}
		catch (Exception e1) {
			if (e1 instanceof NoSuchFileEntryException) {
				try {
					documentListTree.addChild(
						storage.getFolderTree(sharepointRequest));
				}
				catch (Exception e2) {
				}
			}
		}

		Property documentProperty = new Property(
			"document_list", documentListTree);

		elements.add(documentProperty);

		elements.add(new Property("urldirs", new Tree()));

		return elements;
	}

	private static final String _METHOD_NAME = "getDocsMetaInfo";

}