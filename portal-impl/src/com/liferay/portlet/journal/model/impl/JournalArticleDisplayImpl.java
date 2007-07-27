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

package com.liferay.portlet.journal.model.impl;

import com.liferay.portlet.journal.model.JournalArticleDisplay;

/**
 * <a href="JournalArticleDisplayImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JournalArticleDisplayImpl implements JournalArticleDisplay {

	public JournalArticleDisplayImpl(long id, long resourcePrimKey,
									 long groupId, String articleId,
									 double version, String title,
									 String description,
									 String[] availableLocales,
									 String content) {

		_id = id;
		_resourcePrimKey = resourcePrimKey;
		_groupId = groupId;
		_articleId = articleId;
		_version = version;
		_title = title;
		_description = description;
		_availableLocales = availableLocales;
		_content = content;
	}

	public long getId() {
		return _id;
	}

	public long getResourcePrimKey() {
		return _resourcePrimKey;
	}

	public long getGroupId() {
		return _groupId;
	}

	public String getArticleId() {
		return _articleId;
	}

	public double getVersion() {
		return _version;
	}

	public String getTitle() {
		return _title;
	}

	public String getDescription() {
		return _description;
	}

	public String[] getAvailableLocales() {
		return _availableLocales;
	}

	public String getContent() {
		return _content;
	}

	private long _id;
	private long _resourcePrimKey;
	private long _groupId;
	private String _articleId;
	private double _version;
	private String _title;
	private String _description;
	private String[] _availableLocales;
	private String _content;

}