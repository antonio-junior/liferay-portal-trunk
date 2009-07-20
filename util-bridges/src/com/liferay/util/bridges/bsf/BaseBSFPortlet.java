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

package com.liferay.util.bridges.bsf;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;

public abstract class BaseBSFPortlet extends GenericPortlet {

	public void init() {
		editFile = getInitParameter("edit-file");
		helpFile = getInitParameter("help-file");
		viewFile = getInitParameter("view-file");
		actionFile = getInitParameter("action-file");
		resourceFile = getInitParameter("resource-file");
		globalFiles = StringUtil.split(getInitParameter("global-files"));

		BSFManager.registerScriptingEngine(
			getScriptingEngineLanguage(), getScriptingEngineClassName(),
			new String[] {getScriptingEngineExtension()});

		bsfManager = new BSFManager();
	}

	public void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String file = renderRequest.getParameter(getFileParam());

		if (file != null) {
			include(file, renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	public void doEdit(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (renderRequest.getPreferences() == null) {
			super.doEdit(renderRequest, renderResponse);
		}
		else {
			include(editFile, renderRequest, renderResponse);
		}
	}

	public void doHelp(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException {

		include(helpFile, renderRequest, renderResponse);
	}

	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException {

		include(viewFile, renderRequest, renderResponse);
	}

	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		include(actionFile, actionRequest, actionResponse);
	}

	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		include(resourceFile, resourceRequest, resourceResponse);
	}

	protected void declareBeans(
			InputStream is, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws BSFException, IOException {

		declareBeans(
			new String(FileUtil.getBytes(is)), portletRequest, portletResponse);
	}

	protected void declareBeans(
			String code, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws BSFException, IOException {

		StringBuilder sb = new StringBuilder();

		sb.append(getGlobalScript());
		sb.append(code);

		String script = sb.toString();

		PortletConfig portletConfig = getPortletConfig();
		PortletContext portletContext = getPortletContext();
		PortletPreferences preferences = portletRequest.getPreferences();
		Map<String, String> userInfo =
			(Map<String, String>)portletRequest.getAttribute(
				PortletRequest.USER_INFO);

		bsfManager.declareBean(
			"portletConfig", portletConfig, PortletConfig.class);
		bsfManager.declareBean(
			"portletContext", portletContext, PortletContext.class);
		bsfManager.declareBean(
			"preferences", preferences, PortletPreferences.class);
		bsfManager.declareBean("userInfo", userInfo, Map.class);

		if (portletRequest instanceof ActionRequest) {
			bsfManager.declareBean(
				"actionRequest", portletRequest, ActionRequest.class);
		}
		else if (portletRequest instanceof RenderRequest) {
			bsfManager.declareBean(
				"renderRequest", portletRequest, RenderRequest.class);
		}
		else if (portletRequest instanceof ResourceRequest) {
			bsfManager.declareBean(
				"resourceRequest", portletRequest, ResourceRequest.class);
		}

		if (portletResponse instanceof ActionResponse) {
			bsfManager.declareBean(
				"actionResponse", portletResponse, ActionResponse.class);
		}
		else if (portletResponse instanceof RenderResponse) {
			bsfManager.declareBean(
				"renderResponse", portletResponse, RenderResponse.class);
		}
		else if (portletResponse instanceof ResourceResponse) {
			bsfManager.declareBean(
				"resourceResponse", portletResponse, ResourceResponse.class);
		}

		bsfManager.exec(getScriptingEngineLanguage(), "(java)", 1, 1, script);
	}

	protected String getGlobalScript() throws IOException {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < globalFiles.length; i++) {
			InputStream is = getPortletContext().getResourceAsStream(
				globalFiles[i]);

			if (is == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Global file " + globalFiles[i] + " does not exist");
				}
			}

			try {
				if (is != null) {
					sb.append(new String(FileUtil.getBytes(is)));
					sb.append(StringPool.NEW_LINE);
				}
			}
			finally {
				is.close();
			}
		}

		return sb.toString();
	}

	protected abstract String getFileParam();

	protected abstract String getScriptingEngineClassName();

	protected abstract String getScriptingEngineExtension();

	protected abstract String getScriptingEngineLanguage();

	protected void include(
			String path, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws IOException {

		InputStream is = getPortletContext().getResourceAsStream(path);

		if (is == null) {
			_log.error(
				path + " is not a valid " + getScriptingEngineLanguage() +
					" file");

			return;
		}

		try {
			declareBeans(is, portletRequest, portletResponse);
		}
		catch (BSFException bsfe) {
			logBSFException(bsfe, path);
		}
		finally {
			is.close();
		}
	}

	protected void logBSFException(BSFException bsfe, String path) {
		String message =
			"The script at " + path + " or one of the global files has errors.";

		Throwable t = bsfe.getTargetException();

		_log.error(message, t);
	}

	protected String editFile;
	protected String helpFile;
	protected String viewFile;
	protected String actionFile;
	protected String resourceFile;
	protected String[] globalFiles;
	protected BSFManager bsfManager;

	private static Log _log = LogFactoryUtil.getLog(BaseBSFPortlet.class);

}