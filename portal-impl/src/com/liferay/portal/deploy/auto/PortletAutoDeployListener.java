/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.deploy.auto;

import com.liferay.portal.kernel.deploy.auto.AutoDeployException;
import com.liferay.portal.kernel.deploy.auto.BaseAutoDeployListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.Portal;

import java.io.File;

/**
 * <a href="PortletAutoDeployListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class PortletAutoDeployListener extends BaseAutoDeployListener {

	public PortletAutoDeployListener() {
		_deployer = new PortletAutoDeployer();
	}

	public void deploy(File file) throws AutoDeployException {
		if (_log.isDebugEnabled()) {
			_log.debug("Invoking deploy for " + file.getPath());
		}

		AutoDeployer deployer = null;

		if (isMatchingFile(
				file, "WEB-INF/" + Portal.PORTLET_XML_FILE_NAME_STANDARD)) {

			deployer = _deployer;
		}
		else if (isMatchingFile(file, "index_mvc.jsp")) {
			deployer = getMvcDeployer();
		}
		else if (isMatchingFile(file, "index.php")) {
			deployer = getPhpDeployer();
		}
		else if (!isExtPlugin(file) && !isHookPlugin(file) &&
				 !isMatchingFile(
					file, "WEB-INF/liferay-layout-templates.xml") &&
				 !isThemePlugin(file) && !isWebPlugin(file) &&
				 file.getName().endsWith(".war")) {

			if (_log.isInfoEnabled()) {
				_log.info("Deploying package as a web application");
			}

			deployer = getWaiDeployer();
		}
		else {
			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Copying portlets for " + file.getPath());
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Using deployer " + deployer.getClass().getName());
		}

		deployer.autoDeploy(file.getName());

		if (_log.isInfoEnabled()) {
			_log.info(
				"Portlets for " + file.getPath() + " copied successfully. " +
					"Deployment will start in a few seconds.");
		}
	}

	protected AutoDeployer getMvcDeployer() {
		if (_mvcDeployer == null) {
			_mvcDeployer = new MVCPortletAutoDeployer();
		}

		return _mvcDeployer;
	}

	protected AutoDeployer getPhpDeployer() throws AutoDeployException {
		if (_phpDeployer == null) {
			_phpDeployer = new PHPPortletAutoDeployer();
		}

		return _phpDeployer;
	}

	protected AutoDeployer getWaiDeployer() throws AutoDeployException {
		if (_waiDeployer == null) {
			_waiDeployer = new WAIAutoDeployer();
		}

		return _waiDeployer;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortletAutoDeployListener.class);

	private AutoDeployer _deployer;
	private MVCPortletAutoDeployer _mvcDeployer;
	private PHPPortletAutoDeployer _phpDeployer;
	private WAIAutoDeployer _waiDeployer;

}