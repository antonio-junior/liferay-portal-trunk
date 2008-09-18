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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * <a href="SearchContainerResultRowTei.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 *
 */
public class SearchContainerRowTei extends TagExtraInfo {

	public VariableInfo[] getVariableInfo(TagData data) {
		Object className = data.getAttribute("className");
		Object indexVar = data.getAttribute("indexVar");

		if (Validator.isNull(indexVar)) {
			indexVar = SearchContainerRowTag.INDEX_VAR;
		}

		Object modelVar = data.getAttribute("modelVar");

		if (Validator.isNull(modelVar)) {
			modelVar = SearchContainerRowTag.MODEL_VAR;
		}

		Object rowVar = data.getAttribute("rowVar");

		if (Validator.isNull(rowVar)) {
			rowVar = SearchContainerRowTag.ROW_VAR;
		}

		return new VariableInfo[] {
			new VariableInfo(
				String.valueOf(indexVar), Integer.class.getName(), true, VariableInfo.NESTED),
			new VariableInfo(
				String.valueOf(modelVar), String.valueOf(className), true, VariableInfo.NESTED),
			new VariableInfo(
				String.valueOf(rowVar), ResultRow.class.getName(), true, VariableInfo.NESTED)
		};
	}

}
