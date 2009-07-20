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

package com.liferay.portlet.expando.model;

import com.liferay.portal.model.BaseModel;

public interface ExpandoColumnModel extends BaseModel<ExpandoColumn> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getColumnId();

	public void setColumnId(long columnId);

	public long getCompanyId();

	public void setCompanyId(long companyId);

	public long getTableId();

	public void setTableId(long tableId);

	public String getName();

	public void setName(String name);

	public int getType();

	public void setType(int type);

	public String getDefaultData();

	public void setDefaultData(String defaultData);

	public String getTypeSettings();

	public void setTypeSettings(String typeSettings);

	public ExpandoColumn toEscapedModel();
}