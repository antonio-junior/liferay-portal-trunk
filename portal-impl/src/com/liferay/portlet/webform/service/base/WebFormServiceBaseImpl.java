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

package com.liferay.portlet.webform.service.base;

import com.liferay.portal.kernel.bean.InitializingBean;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.service.base.PrincipalBean;

import com.liferay.portlet.webform.service.WebFormLocalService;
import com.liferay.portlet.webform.service.WebFormService;

/**
 * <a href="WebFormServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class WebFormServiceBaseImpl extends PrincipalBean
	implements WebFormService, InitializingBean {
	public WebFormLocalService getWebFormLocalService() {
		return webFormLocalService;
	}

	public void setWebFormLocalService(WebFormLocalService webFormLocalService) {
		this.webFormLocalService = webFormLocalService;
	}

	public void afterPropertiesSet() {
		if (webFormLocalService == null) {
			webFormLocalService = (WebFormLocalService)PortalBeanLocatorUtil.locate(WebFormLocalService.class.getName() +
					".impl");
		}
	}

	protected WebFormLocalService webFormLocalService;
}