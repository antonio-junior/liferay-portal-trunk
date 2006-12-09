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

package com.liferay.portal.events;

import com.liferay.lock.service.LockServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.bean.BeanLocatorImpl;
import com.liferay.portal.kernel.bean.BeanLocatorUtil;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.ReleaseLocalServiceUtil;
import com.liferay.portal.spring.hibernate.CacheRegistry;
import com.liferay.portal.struts.ActionException;
import com.liferay.portal.struts.SimpleAction;
import com.liferay.portal.upgrade.UpgradeProcess;
import com.liferay.portal.util.ClusterPool;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.ReleaseInfo;
import com.liferay.util.SimpleCachePool;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="StartupAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class StartupAction extends SimpleAction {

	public void run(String[] ids) throws ActionException {

		try {

			// Print release information

			System.out.println("Starting " + ReleaseInfo.getReleaseInfo());

			// Keep track of uptime

			SimpleCachePool.put(
				StartupAction.class.getName() + ".uptime", new Date());

			// Bean locator

			BeanLocatorUtil.setBeanLocator(new BeanLocatorImpl());

			// Clear locks

			try {
				LockServiceUtil.clear();
			}
			catch (Exception e) {
				e.printStackTrace();
			}

			// Add shutdown hook

			Runtime.getRuntime().addShutdownHook(
				new Thread(new ShutdownHook()));

			// Disable database caching before upgrade

			CacheRegistry.setActive(false);

			// Upgrade

			int buildNumber =
				ReleaseLocalServiceUtil.getRelease().getBuildNumber();

			String[] upgradeProcesses =
				PropsUtil.getArray(PropsUtil.UPGRADE_PROCESSES);

			for (int i = 0; i < upgradeProcesses.length; i++) {
				if (_log.isDebugEnabled()) {
					_log.debug("Initializing upgrade " + upgradeProcesses[i]);
				}

				try {
					UpgradeProcess upgradeProcess =
						(UpgradeProcess)Class.forName(
							upgradeProcesses[i]).newInstance();

					if ((upgradeProcess.getThreshold() == 0) ||
						(upgradeProcess.getThreshold() >= buildNumber)) {

						if (_log.isInfoEnabled()) {
							_log.info(
								"Running upgrade " + upgradeProcesses[i]);
						}

						upgradeProcess.upgrade();

						if (_log.isInfoEnabled()) {
							_log.info(
								"Finished upgrade " + upgradeProcesses[i]);
						}
					}
					else {
						if (_log.isDebugEnabled()) {
							_log.debug(
								"Upgrade threshold " +
									upgradeProcess.getThreshold() +
										" will not trigger upgrade");

							_log.debug(
								"Skipping upgrade " + upgradeProcesses[i]);
						}
					}
				}
				catch (ClassNotFoundException cnfe) {
					_log.error(upgradeProcesses[i] + " cannot be found");
				}
				catch (InstantiationException ie) {
					_log.error(upgradeProcesses[i] + " cannot be initiated");
				}
			}

			ReleaseLocalServiceUtil.updateRelease();

			// Enable database caching after upgrade

			CacheRegistry.setActive(true);

			ClusterPool.clear();

			// Delete temporary images

			_deleteTempImages();
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	private void _deleteTempImages() throws PortalException, SystemException {

		// Journal

		ImageLocalServiceUtil.deleteImages("%.journal.article.PREVIEW_%");
	}

	private static Log _log = LogFactory.getLog(StartupAction.class);

}