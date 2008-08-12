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

package com.liferay.portlet.tags.service.persistence;

/**
 * <a href="TagsEntryFinder.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface TagsEntryFinder {
	public int countByG_N_P(long groupId, java.lang.String name,
		java.lang.String[] properties)
		throws com.liferay.portal.SystemException;

	public int countByG_C_N(long groupId, long classNameId,
		java.lang.String name) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portlet.tags.model.TagsEntry> findByA_F(
		long assetId, boolean folksonomy)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portlet.tags.model.TagsEntry> findByG_N_P(
		long groupId, java.lang.String name, java.lang.String[] properties)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portlet.tags.model.TagsEntry> findByG_N_P(
		long groupId, java.lang.String name, java.lang.String[] properties,
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portlet.tags.model.TagsEntry> findByG_C_N(
		long groupId, long classNameId, java.lang.String name)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portlet.tags.model.TagsEntry> findByG_C_N(
		long groupId, long classNameId, java.lang.String name, int start,
		int end) throws com.liferay.portal.SystemException;
}