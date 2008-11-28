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

package com.liferay.portlet.tags.model.impl;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.tags.NoSuchEntryException;
import com.liferay.portlet.tags.model.TagsEntry;
import com.liferay.portlet.tags.model.TagsEntryConstants;
import com.liferay.portlet.tags.model.TagsVocabulary;
import com.liferay.portlet.tags.service.TagsEntryLocalServiceUtil;
import com.liferay.portlet.tags.service.TagsVocabularyLocalServiceUtil;

/**
 * <a href="TagsEntryImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Bruno Farache
 *
 */
public class TagsEntryImpl extends TagsEntryModelImpl implements TagsEntry {

	public TagsEntryImpl() {
	}

	public String getParentName() throws PortalException, SystemException {
		String name = StringPool.BLANK;

		if (getParentEntryId() == TagsEntryConstants.DEFAULT_PARENT_ENTRY_ID) {
			return name;
		}

		try {
			name = TagsEntryLocalServiceUtil.getEntry(
				getParentEntryId()).getName();
		}
		catch (NoSuchEntryException nsee) {
		}

		return name;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", StringPool.BLANK);
	}

	public TagsVocabulary getVocabulary()
		throws PortalException, SystemException {

		return TagsVocabularyLocalServiceUtil.getVocabulary(getVocabularyId());
	}

	public boolean isCategory() throws PortalException, SystemException {
		TagsVocabulary vocabulary = getVocabulary();

		if (vocabulary.isFolksonomy()) {
			return false;
		}
		else {
			return true;
		}
	}

}