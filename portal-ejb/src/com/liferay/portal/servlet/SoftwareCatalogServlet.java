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

package com.liferay.portal.servlet;

import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.softwarecatalog.service.SCProductEntryLocalServiceUtil;
import com.liferay.util.GetterUtil;
import com.liferay.util.ParamUtil;
import com.liferay.util.Validator;

import java.io.IOException;
import java.io.OutputStreamWriter;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="SoftwareCatalogServlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class SoftwareCatalogServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {

		res.setContentType("text/xml; charset=UTF-8");

		OutputStreamWriter out = new OutputStreamWriter(res.getOutputStream());

		try {
			long groupId = getGroupId(req);
			String baseImageURL = getBaseImageURL(req);
			Date oldestDate = getOldestDate(req);
			int maxNumOfVersions = ParamUtil.getInteger(
				req, "maxNumOfVersions");
			Properties repoSettings = getRepoSettings(req);

			String repositoryXML =
				SCProductEntryLocalServiceUtil.getRepositoryXML(
					groupId, baseImageURL, oldestDate, maxNumOfVersions,
					repoSettings);

			if (!res.isCommitted()) {
				out.write(repositoryXML);
			}
		}
		catch (NoSuchGroupException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}

			res.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
		finally {
			out.flush();
			out.close();
		}
	}

	protected String getBaseImageURL(HttpServletRequest req) {
		String host = PortalUtil.getHost(req);

		String portalURL = PortalUtil.getPortalURL(
			host, req.getServerPort(), req.isSecure());

		return portalURL + PortalUtil.getPathImage() + "/software_catalog";
	}

	protected long getGroupId(HttpServletRequest req)
		throws SystemException, PortalException {

		String path = req.getPathInfo();

		long groupId = GetterUtil.getLong(path.substring(1));

		if (groupId <= 0) {
			groupId = ParamUtil.getLong(req, "groupId");
		}

		if (groupId <= 0) {
			long companyId = PortalInstances.getCompanyId(req);

			Group group = GroupLocalServiceUtil.getFriendlyURLGroup(
				companyId, path);

			groupId = group.getGroupId();
		}

		return groupId;
	}

	protected Date getOldestDate(HttpServletRequest req) {
		Date oldestDate = null;

		oldestDate = ParamUtil.getDate(
			req, "oldestDate", new SimpleDateFormat("yyyy.MM.dd"), null);

		if (oldestDate == null) {
			int daysOld = ParamUtil.getInteger(req, "maxAge", -1);

			if (daysOld != -1) {
				Calendar cal = Calendar.getInstance();

				cal.add(Calendar.DATE, (0 - daysOld));

				oldestDate = cal.getTime();
			}
		}

		return oldestDate;
	}

	protected Properties getRepoSettings(HttpServletRequest req) {
		Properties repoSettings = new Properties();

		String prefix = "setting_";

		Enumeration enu = req.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = (String)enu.nextElement();

			if (name.startsWith(prefix)) {
				String settingName = name.substring(
					prefix.length(), name.length());

				String value = ParamUtil.getString(req, name);

				if (Validator.isNotNull(value)) {
					repoSettings.setProperty(settingName , value);
				}
			}
		}

		return repoSettings;
	}

	private static Log _log = LogFactory.getLog(SoftwareCatalogServlet.class);

}