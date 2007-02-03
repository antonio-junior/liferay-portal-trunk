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

package com.liferay.portlet.shopping.search;

import com.liferay.util.ParamUtil;
import com.liferay.util.dao.search.DisplayTerms;

import javax.portlet.RenderRequest;

/**
 * <a href="OrderDisplayTerms.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class OrderDisplayTerms extends DisplayTerms {

	public static final String ORDER_ID = "searchOrderId";

	public static final String STATUS = "status";

	public static final String FIRST_NAME = "firstName";

	public static final String LAST_NAME = "lastName";

	public static final String EMAIL_ADDRESS = "emailAddress";

	public OrderDisplayTerms(RenderRequest req) {
		super(req);

		orderId = ParamUtil.getString(req, ORDER_ID);
		status = ParamUtil.getString(req, STATUS);
		firstName = ParamUtil.getString(req, FIRST_NAME);
		lastName = ParamUtil.getString(req, LAST_NAME);
		emailAddress = ParamUtil.getString(req, EMAIL_ADDRESS);
	}

	public String getOrderId() {
		return orderId;
	}

	public String getStatus() {
		return status;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	protected String orderId;
	protected String status;
	protected String firstName;
	protected String lastName;
	protected String emailAddress;

}