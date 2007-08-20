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

package com.liferay.portlet.communities.action;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.LayoutServiceUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portlet.ActionResponseImpl;
import com.liferay.util.servlet.ServletResponseUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * <a href="ExportPagesAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Alexander Chow
 * @author Raymond Aug�
 *
 */
public class ExportPagesAction extends PortletAction {

	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig config,
			ActionRequest req, ActionResponse res)
		throws Exception {

		try {
			long groupId = ParamUtil.getLong(req, "groupId");
			boolean privateLayout = ParamUtil.getBoolean(req, "privateLayout");
			String fileName = ParamUtil.getString(req, "exportFileName");

			byte[] byteArray = LayoutServiceUtil.exportLayouts(
				groupId, privateLayout, req.getParameterMap());

			HttpServletResponse httpRes =
				((ActionResponseImpl)res).getHttpServletResponse();

			ServletResponseUtil.sendFile(httpRes, fileName, byteArray);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactory.getLog(ExportPagesAction.class);

}