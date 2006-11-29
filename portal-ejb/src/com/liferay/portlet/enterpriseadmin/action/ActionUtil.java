/**
 * Copyright (c) 2000-2006 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.enterpriseadmin.action;

import com.liferay.portal.model.Address;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.OrgLabor;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.model.Website;
import com.liferay.portal.service.AddressServiceUtil;
import com.liferay.portal.service.EmailAddressServiceUtil;
import com.liferay.portal.service.OrgLaborServiceUtil;
import com.liferay.portal.service.OrganizationServiceUtil;
import com.liferay.portal.service.PhoneServiceUtil;
import com.liferay.portal.service.RoleServiceUtil;
import com.liferay.portal.service.UserGroupServiceUtil;
import com.liferay.portal.service.WebsiteServiceUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.ActionRequestImpl;
import com.liferay.portlet.RenderRequestImpl;
import com.liferay.util.ParamUtil;
import com.liferay.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="ActionUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class ActionUtil {

	public static void getAddress(ActionRequest req) throws Exception {
		HttpServletRequest httpReq =
			((ActionRequestImpl)req).getHttpServletRequest();

		getAddress(httpReq);
	}

	public static void getAddress(RenderRequest req) throws Exception {
		HttpServletRequest httpReq =
			((RenderRequestImpl)req).getHttpServletRequest();

		getAddress(httpReq);
	}

	public static void getAddress(HttpServletRequest req) throws Exception {
		String addressId = ParamUtil.getString(req, "addressId");

		Address address = null;

		if (Validator.isNotNull(addressId)) {
			address = AddressServiceUtil.getAddress(addressId);
		}

		req.setAttribute(WebKeys.ADDRESS, address);
	}

	public static void getEmailAddress(ActionRequest req) throws Exception {
		HttpServletRequest httpReq =
			((ActionRequestImpl)req).getHttpServletRequest();

		getEmailAddress(httpReq);
	}

	public static void getEmailAddress(RenderRequest req) throws Exception {
		HttpServletRequest httpReq =
			((RenderRequestImpl)req).getHttpServletRequest();

		getEmailAddress(httpReq);
	}

	public static void getEmailAddress(
		HttpServletRequest req) throws Exception {

		String emailAddressId = ParamUtil.getString(req, "emailAddressId");

		EmailAddress emailAddress = null;

		if (Validator.isNotNull(emailAddressId)) {
			emailAddress =
				EmailAddressServiceUtil.getEmailAddress(emailAddressId);
		}

		req.setAttribute(WebKeys.EMAIL_ADDRESS, emailAddress);
	}

	public static void getPhone(ActionRequest req) throws Exception {
		HttpServletRequest httpReq =
			((ActionRequestImpl)req).getHttpServletRequest();

		getPhone(httpReq);
	}

	public static void getPhone(RenderRequest req) throws Exception {
		HttpServletRequest httpReq =
			((RenderRequestImpl)req).getHttpServletRequest();

		getPhone(httpReq);
	}

	public static void getPhone(HttpServletRequest req) throws Exception {
		String phoneId = ParamUtil.getString(req, "phoneId");

		Phone phone = null;

		if (Validator.isNotNull(phoneId)) {
			phone = PhoneServiceUtil.getPhone(phoneId);
		}

		req.setAttribute(WebKeys.PHONE, phone);
	}

	public static void getOrganization(ActionRequest req) throws Exception {
		HttpServletRequest httpReq =
			((ActionRequestImpl)req).getHttpServletRequest();

		getOrganization(httpReq);
	}

	public static void getOrganization(RenderRequest req) throws Exception {
		HttpServletRequest httpReq =
			((RenderRequestImpl)req).getHttpServletRequest();

		getOrganization(httpReq);
	}

	public static void getOrganization(HttpServletRequest req)
		throws Exception {

		String organizationId = ParamUtil.getString(req, "organizationId");

		Organization organization = null;

		if (Validator.isNotNull(organizationId)) {
			organization =
				OrganizationServiceUtil.getOrganization(organizationId);
		}

		req.setAttribute(WebKeys.ORGANIZATION, organization);
	}

	public static void getOrgLabor(ActionRequest req) throws Exception {
		HttpServletRequest httpReq =
			((ActionRequestImpl)req).getHttpServletRequest();

		getOrgLabor(httpReq);
	}

	public static void getOrgLabor(RenderRequest req) throws Exception {
		HttpServletRequest httpReq =
			((RenderRequestImpl)req).getHttpServletRequest();

		getOrgLabor(httpReq);
	}

	public static void getOrgLabor(HttpServletRequest req) throws Exception {
		String orgLaborId = ParamUtil.getString(req, "orgLaborId");

		OrgLabor orgLabor = null;

		if (Validator.isNotNull(orgLaborId)) {
			orgLabor = OrgLaborServiceUtil.getOrgLabor(orgLaborId);
		}

		req.setAttribute(WebKeys.ORG_LABOR, orgLabor);
	}

	public static void getRole(ActionRequest req) throws Exception {
		HttpServletRequest httpReq =
			((ActionRequestImpl)req).getHttpServletRequest();

		getRole(httpReq);
	}

	public static void getRole(RenderRequest req) throws Exception {
		HttpServletRequest httpReq =
			((RenderRequestImpl)req).getHttpServletRequest();

		getRole(httpReq);
	}

	public static void getRole(HttpServletRequest req)
		throws Exception {

		String roleId = ParamUtil.getString(req, "roleId");

		Role role = null;

		if (Validator.isNotNull(roleId)) {
			role = RoleServiceUtil.getRole(roleId);
		}

		req.setAttribute(WebKeys.ROLE, role);
	}

	public static void getUserGroup(ActionRequest req) throws Exception {
		HttpServletRequest httpReq =
			((ActionRequestImpl)req).getHttpServletRequest();

		getUserGroup(httpReq);
	}

	public static void getUserGroup(RenderRequest req) throws Exception {
		HttpServletRequest httpReq =
			((RenderRequestImpl)req).getHttpServletRequest();

		getUserGroup(httpReq);
	}

	public static void getUserGroup(HttpServletRequest req)
		throws Exception {

		String userGroupId = ParamUtil.getString(req, "userGroupId");

		UserGroup userGroup = null;

		if (Validator.isNotNull(userGroupId)) {
			userGroup = UserGroupServiceUtil.getUserGroup(userGroupId);
		}

		req.setAttribute(WebKeys.USER_GROUP, userGroup);
	}

	public static void getWebsite(ActionRequest req) throws Exception {
		HttpServletRequest httpReq =
			((ActionRequestImpl)req).getHttpServletRequest();

		getWebsite(httpReq);
	}

	public static void getWebsite(RenderRequest req) throws Exception {
		HttpServletRequest httpReq =
			((RenderRequestImpl)req).getHttpServletRequest();

		getWebsite(httpReq);
	}

	public static void getWebsite(HttpServletRequest req) throws Exception {
		String websiteId = ParamUtil.getString(req, "websiteId");

		Website website = null;

		if (Validator.isNotNull(websiteId)) {
			website = WebsiteServiceUtil.getWebsite(websiteId);
		}

		req.setAttribute(WebKeys.WEBSITE, website);
	}

}