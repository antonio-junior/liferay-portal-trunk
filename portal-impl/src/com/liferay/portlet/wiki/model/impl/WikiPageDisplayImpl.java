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

package com.liferay.portlet.wiki.model.impl;

import com.liferay.portlet.wiki.model.WikiPageDisplay;

/**
 * <a href="WikiPageDisplayImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class WikiPageDisplayImpl implements WikiPageDisplay {

	public WikiPageDisplayImpl(
		long nodeId, String title, long userId,
		double version, String content, String htmlContent, String format,
		boolean head, String redirectTo, String[] attachments) {

		_nodeId = nodeId;
		_title = title;
		_userId = userId;
		_version = version;
		_content = content;
		_htmlContent = htmlContent;
		_format = format;
		_head = head;
		_redirectTo = redirectTo;
		_attachments = attachments;
	}

	public long getUserId() {
		return _userId;
	}

	public long getNodeId() {
		return _nodeId;
	}

	public void setNodeId(long nodeId) {
		_nodeId = nodeId;
	}

	public String[] getAttachments() {
		return _attachments;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public double getVersion() {
		return _version;
	}

	public void setVersion(double version) {
		_version = version;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public String getHtmlContent() {
		return _htmlContent;
	}

	public String getFormat() {
		return _format;
	}

	public void setFormat(String format) {
		_format = format;
	}

	public boolean getHead() {
		return _head;
	}

	public boolean isHead() {
		return _head;
	}

	public void setHead(boolean head) {
		_head = head;
	}

	public String getRedirectTo() {
		return _redirectTo;
	}

	public void setRedirectTo(String redirectTo) {
		_redirectTo = redirectTo;
	}

	private String _content;
	private String[] _attachments;
	private String _format;
	private boolean _head;
	private String _htmlContent;
	private long _nodeId;
	private String _redirectTo;
	private long _userId;
	private String _title;
	private double _version;

}