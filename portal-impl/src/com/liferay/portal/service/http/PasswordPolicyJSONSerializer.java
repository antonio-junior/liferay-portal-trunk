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

package com.liferay.portal.service.http;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.PasswordPolicy;

import java.util.Date;
import java.util.List;

/**
 * <a href="PasswordPolicyJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by {@link PasswordPolicyServiceJSON} to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see    com.liferay.portal.service.http.PasswordPolicyServiceJSON
 * @generated
 */
public class PasswordPolicyJSONSerializer {
	public static JSONObject toJSONObject(PasswordPolicy model) {
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		jsonObj.put("passwordPolicyId", model.getPasswordPolicyId());
		jsonObj.put("companyId", model.getCompanyId());
		jsonObj.put("userId", model.getUserId());
		jsonObj.put("userName", model.getUserName());

		Date createDate = model.getCreateDate();

		String createDateJSON = StringPool.BLANK;

		if (createDate != null) {
			createDateJSON = String.valueOf(createDate.getTime());
		}

		jsonObj.put("createDate", createDateJSON);

		Date modifiedDate = model.getModifiedDate();

		String modifiedDateJSON = StringPool.BLANK;

		if (modifiedDate != null) {
			modifiedDateJSON = String.valueOf(modifiedDate.getTime());
		}

		jsonObj.put("modifiedDate", modifiedDateJSON);
		jsonObj.put("defaultPolicy", model.getDefaultPolicy());
		jsonObj.put("name", model.getName());
		jsonObj.put("description", model.getDescription());
		jsonObj.put("changeable", model.getChangeable());
		jsonObj.put("changeRequired", model.getChangeRequired());
		jsonObj.put("minAge", model.getMinAge());
		jsonObj.put("checkSyntax", model.getCheckSyntax());
		jsonObj.put("allowDictionaryWords", model.getAllowDictionaryWords());
		jsonObj.put("minLength", model.getMinLength());
		jsonObj.put("history", model.getHistory());
		jsonObj.put("historyCount", model.getHistoryCount());
		jsonObj.put("expireable", model.getExpireable());
		jsonObj.put("maxAge", model.getMaxAge());
		jsonObj.put("warningTime", model.getWarningTime());
		jsonObj.put("graceLimit", model.getGraceLimit());
		jsonObj.put("lockout", model.getLockout());
		jsonObj.put("maxFailure", model.getMaxFailure());
		jsonObj.put("lockoutDuration", model.getLockoutDuration());
		jsonObj.put("requireUnlock", model.getRequireUnlock());
		jsonObj.put("resetFailureCount", model.getResetFailureCount());

		return jsonObj;
	}

	public static JSONArray toJSONArray(
		com.liferay.portal.model.PasswordPolicy[] models) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (PasswordPolicy model : models) {
			jsonArray.put(toJSONObject(model));
		}

		return jsonArray;
	}

	public static JSONArray toJSONArray(
		com.liferay.portal.model.PasswordPolicy[][] models) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (PasswordPolicy[] model : models) {
			jsonArray.put(toJSONArray(model));
		}

		return jsonArray;
	}

	public static JSONArray toJSONArray(
		List<com.liferay.portal.model.PasswordPolicy> models) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (PasswordPolicy model : models) {
			jsonArray.put(toJSONObject(model));
		}

		return jsonArray;
	}
}