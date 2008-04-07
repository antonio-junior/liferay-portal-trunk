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

package com.liferay.portlet.expando.service.impl;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.ValueDataException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.base.ExpandoValueLocalServiceBaseImpl;

import java.util.List;

/**
 * <a href="ExpandoValueLocalServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 *
 */
public class ExpandoValueLocalServiceImpl
	extends ExpandoValueLocalServiceBaseImpl {

	public ExpandoValue addValue(long columnId, long rowId, String data)
		throws PortalException, SystemException {

		validate(data);

		ExpandoColumn column = expandoColumnPersistence.findByPrimaryKey(
			columnId);

		ExpandoValue value = expandoValuePersistence.fetchByT_C_R(
			column.getTableId(), columnId, rowId);

		if (value == null) {
			long valueId = counterLocalService.increment();

			value = expandoValuePersistence.create(valueId);

			value.setTableId(column.getTableId());
			value.setColumnId(columnId);
			value.setRowId(rowId);
		}

		value.setData(data);

		expandoValuePersistence.update(value, false);

		return value;
	}

	public void deleteColumnValues(long columnId) throws SystemException {
		expandoValuePersistence.removeByColumnId(columnId);
	}

	public void deleteRowValues(long rowId) throws SystemException {
		expandoValuePersistence.removeByRowId(rowId);
	}

	public void deleteTableValues(long tableId) throws SystemException {
		expandoValuePersistence.removeByTableId(tableId);
	}

	public void deleteValue(long valueId)
		throws PortalException, SystemException {

		expandoValuePersistence.remove(valueId);
	}

	public void deleteValues(String className, long classPK)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		deleteValues(classNameId, classPK);
	}

	public void deleteValues(long classNameId, long classPK)
		throws PortalException, SystemException {

		expandoValuePersistence.removeByC_C(classNameId, classPK);
	}

	public List<ExpandoValue> getColumnValues(long columnId, int begin, int end)
		throws SystemException {

		return expandoValuePersistence.findByColumnId(columnId, begin, end);
	}

	public int getColumnValuesCount(long columnId) throws SystemException {
		return expandoValuePersistence.countByColumnId(columnId);
	}

	public List<ExpandoValue> getRowValues(long rowId)
		throws SystemException {

		return expandoValuePersistence.findByRowId(rowId);
	}

	public List<ExpandoValue> getRowValues(long rowId, int begin, int end)
		throws SystemException {

		return expandoValuePersistence.findByRowId(rowId, begin, end);
	}

	public int getRowValuesCount(long rowId) throws SystemException {
		return expandoValuePersistence.countByRowId(rowId);
	}

	public List<ExpandoValue> getTableValues(long tableId, int begin, int end)
		throws SystemException {

		return expandoValuePersistence.findByTableId(tableId, begin, end);
	}

	public int getTableValuesCount(long tableId) throws SystemException {
		return expandoValuePersistence.countByTableId(tableId);
	}

	public ExpandoValue getValue(long valueId)
		throws PortalException, SystemException {

		return expandoValuePersistence.findByPrimaryKey(valueId);
	}

	public ExpandoValue getValue(long columnId, long rowId)
		throws PortalException, SystemException {

		return expandoValuePersistence.findByC_R(columnId, rowId);
	}

	protected void validate(String data) throws PortalException {
		if (Validator.isNull(data)) {
			throw new ValueDataException();
		}
	}

}