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

package com.liferay.portal.service.persistence;

/**
 * <a href="ExpandoTablePersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface ExpandoTablePersistence {
	public com.liferay.portal.model.ExpandoTable create(long tableId);

	public com.liferay.portal.model.ExpandoTable remove(long tableId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoTableException;

	public com.liferay.portal.model.ExpandoTable remove(
		com.liferay.portal.model.ExpandoTable expandoTable)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.model.ExpandoTable update(
		com.liferay.portal.model.ExpandoTable expandoTable)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.model.ExpandoTable update(
		com.liferay.portal.model.ExpandoTable expandoTable, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.model.ExpandoTable updateImpl(
		com.liferay.portal.model.ExpandoTable expandoTable, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.model.ExpandoTable findByPrimaryKey(long tableId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoTableException;

	public com.liferay.portal.model.ExpandoTable fetchByPrimaryKey(long tableId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.model.ExpandoTable> findByClassNameId(
		long classNameId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.model.ExpandoTable> findByClassNameId(
		long classNameId, int begin, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.model.ExpandoTable> findByClassNameId(
		long classNameId, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.model.ExpandoTable findByClassNameId_First(
		long classNameId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoTableException;

	public com.liferay.portal.model.ExpandoTable findByClassNameId_Last(
		long classNameId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoTableException;

	public com.liferay.portal.model.ExpandoTable[] findByClassNameId_PrevAndNext(
		long tableId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoTableException;

	public com.liferay.portal.model.ExpandoTable findByC_N(long classNameId,
		java.lang.String name)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoTableException;

	public com.liferay.portal.model.ExpandoTable fetchByC_N(long classNameId,
		java.lang.String name) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.model.ExpandoTable> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.model.ExpandoTable> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.model.ExpandoTable> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.model.ExpandoTable> findAll(
		int begin, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.model.ExpandoTable> findAll(
		int begin, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByClassNameId(long classNameId)
		throws com.liferay.portal.SystemException;

	public void removeByC_N(long classNameId, java.lang.String name)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoTableException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByClassNameId(long classNameId)
		throws com.liferay.portal.SystemException;

	public int countByC_N(long classNameId, java.lang.String name)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.model.ExpandoColumn> getExpandoColumns(
		long pk)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoTableException;

	public java.util.List<com.liferay.portal.model.ExpandoColumn> getExpandoColumns(
		long pk, int begin, int end)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoTableException;

	public java.util.List<com.liferay.portal.model.ExpandoColumn> getExpandoColumns(
		long pk, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoTableException;

	public int getExpandoColumnsSize(long pk)
		throws com.liferay.portal.SystemException;

	public boolean containsExpandoColumn(long pk, long expandoColumnPK)
		throws com.liferay.portal.SystemException;

	public boolean containsExpandoColumns(long pk)
		throws com.liferay.portal.SystemException;

	public void addExpandoColumn(long pk, long expandoColumnPK)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoColumnException,
			com.liferay.portal.NoSuchExpandoTableException;

	public void addExpandoColumn(long pk,
		com.liferay.portal.model.ExpandoColumn expandoColumn)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoColumnException,
			com.liferay.portal.NoSuchExpandoTableException;

	public void addExpandoColumns(long pk, long[] expandoColumnPKs)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoColumnException,
			com.liferay.portal.NoSuchExpandoTableException;

	public void addExpandoColumns(long pk,
		java.util.List<com.liferay.portal.model.ExpandoColumn> expandoColumns)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoColumnException,
			com.liferay.portal.NoSuchExpandoTableException;

	public void clearExpandoColumns(long pk)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoTableException;

	public void removeExpandoColumn(long pk, long expandoColumnPK)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoColumnException,
			com.liferay.portal.NoSuchExpandoTableException;

	public void removeExpandoColumn(long pk,
		com.liferay.portal.model.ExpandoColumn expandoColumn)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoColumnException,
			com.liferay.portal.NoSuchExpandoTableException;

	public void removeExpandoColumns(long pk, long[] expandoColumnPKs)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoColumnException,
			com.liferay.portal.NoSuchExpandoTableException;

	public void removeExpandoColumns(long pk,
		java.util.List<com.liferay.portal.model.ExpandoColumn> expandoColumns)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoColumnException,
			com.liferay.portal.NoSuchExpandoTableException;

	public void setExpandoColumns(long pk, long[] expandoColumnPKs)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoColumnException,
			com.liferay.portal.NoSuchExpandoTableException;

	public void setExpandoColumns(long pk,
		java.util.List<com.liferay.portal.model.ExpandoColumn> expandoColumns)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.NoSuchExpandoColumnException,
			com.liferay.portal.NoSuchExpandoTableException;
}