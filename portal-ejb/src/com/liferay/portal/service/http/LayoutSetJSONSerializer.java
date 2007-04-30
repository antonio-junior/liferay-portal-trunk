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

package com.liferay.portal.service.http;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.LayoutSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * <a href="LayoutSetJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be overwritten
 * the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by <code>com.liferay.portal.service.http.LayoutSetServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portal.service.http.LayoutSetServiceJSON
 *
 */
public class LayoutSetJSONSerializer {
	public static JSONObject toJSONObject(LayoutSet model) {
		JSONObject jsonObj = new JSONObject();
		String ownerId = model.getOwnerId();

		if (ownerId == null) {
			jsonObj.put("ownerId", StringPool.BLANK);
		}
		else {
			jsonObj.put("ownerId", ownerId.toString());
		}

		jsonObj.put("companyId", model.getCompanyId());
		jsonObj.put("groupId", model.getGroupId());
		jsonObj.put("userId", model.getUserId());
		jsonObj.put("privateLayout", model.getPrivateLayout());
		jsonObj.put("logo", model.getLogo());

		String themeId = model.getThemeId();

		if (themeId == null) {
			jsonObj.put("themeId", StringPool.BLANK);
		}
		else {
			jsonObj.put("themeId", themeId.toString());
		}

		String colorSchemeId = model.getColorSchemeId();

		if (colorSchemeId == null) {
			jsonObj.put("colorSchemeId", StringPool.BLANK);
		}
		else {
			jsonObj.put("colorSchemeId", colorSchemeId.toString());
		}

		String wapThemeId = model.getWapThemeId();

		if (wapThemeId == null) {
			jsonObj.put("wapThemeId", StringPool.BLANK);
		}
		else {
			jsonObj.put("wapThemeId", wapThemeId.toString());
		}

		String wapColorSchemeId = model.getWapColorSchemeId();

		if (wapColorSchemeId == null) {
			jsonObj.put("wapColorSchemeId", StringPool.BLANK);
		}
		else {
			jsonObj.put("wapColorSchemeId", wapColorSchemeId.toString());
		}

		String css = model.getCss();

		if (css == null) {
			jsonObj.put("css", StringPool.BLANK);
		}
		else {
			jsonObj.put("css", css.toString());
		}

		jsonObj.put("pageCount", model.getPageCount());

		String virtualHost = model.getVirtualHost();

		if (virtualHost == null) {
			jsonObj.put("virtualHost", StringPool.BLANK);
		}
		else {
			jsonObj.put("virtualHost", virtualHost.toString());
		}

		return jsonObj;
	}

	public static JSONArray toJSONArray(List models) {
		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < models.size(); i++) {
			LayoutSet model = (LayoutSet)models.get(i);
			jsonArray.put(toJSONObject(model));
		}

		return jsonArray;
	}
}