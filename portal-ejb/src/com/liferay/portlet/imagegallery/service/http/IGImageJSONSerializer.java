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

package com.liferay.portlet.imagegallery.service.http;

import com.liferay.portlet.imagegallery.model.IGImage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * <a href="IGImageJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class IGImageJSONSerializer {
	public static JSONObject toJSONObject(IGImage model) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("companyId", model.getCompanyId().toString());
		jsonObj.put("imageId", model.getImageId().toString());
		jsonObj.put("userId", model.getUserId().toString());
		jsonObj.put("createDate", model.getCreateDate().toString());
		jsonObj.put("modifiedDate", model.getModifiedDate().toString());
		jsonObj.put("folderId", model.getFolderId().toString());
		jsonObj.put("description", model.getDescription().toString());
		jsonObj.put("height", model.getHeight());
		jsonObj.put("width", model.getWidth());
		jsonObj.put("size", model.getSize());

		return jsonObj;
	}

	public static JSONArray toJSONArray(List models) {
		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < models.size(); i++) {
			IGImage model = (IGImage)models.get(i);
			jsonArray.put(toJSONObject(model));
		}

		return jsonArray;
	}
}