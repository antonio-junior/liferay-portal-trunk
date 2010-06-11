/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portalweb.portlet.blogs.entry.viewentryscopepage;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * <a href="ConfigurePortletScopeCurrentPageTest.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class ConfigurePortletScopeCurrentPageTest extends BaseTestCase {
	public void testConfigurePortletScopeCurrentPage()
		throws Exception {
		selenium.open("/web/guest/home/");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("link=Blogs Page Scope Page")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("link=Blogs Page Scope Page",
			RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		selenium.clickAt("//strong/a", RuntimeVariables.replace("Options Icon"));

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible(
							"//div[@class='lfr-component lfr-menu-list']/ul/li[2]/a")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.click("//div[@class='lfr-component lfr-menu-list']/ul/li[2]/a");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("link=Scope")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("link=Scope", RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("_86_scopeLayoutId")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.select("_86_scopeLayoutId",
			RuntimeVariables.replace(
				"label=Current Page (Blogs Page Scope Page)"));
		selenium.clickAt("//input[@value='Save']", RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (RuntimeVariables.replace(
							"Your request processed successfully.")
										.equals(selenium.getText(
								"//div[3]/div/div/div/div/div"))) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		assertEquals(RuntimeVariables.replace(
				"Your request processed successfully."),
			selenium.getText("//div[3]/div/div/div/div/div"));
		assertEquals("Current Page (Blogs Page Scope Page)",
			selenium.getSelectedLabel("_86_scopeLayoutId"));
		selenium.open("/web/guest/home/");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("link=Blogs Page Scope Page")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("link=Blogs Page Scope Page",
			RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		assertEquals(RuntimeVariables.replace("Blogs (Blogs Page Scope Page)"),
			selenium.getText("//h1/span[2]"));
		selenium.clickAt("link=Control Panel", RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		assertEquals(RuntimeVariables.replace("Default"),
			selenium.getText("//div[2]/span/a"));
		selenium.click("//div[2]/span/a");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("//div[5]/div/div/div[1]")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		assertEquals(RuntimeVariables.replace("Default"),
			selenium.getText("//div[5]/div/div/div[2]/ul/li[1]/a"));
		assertEquals(RuntimeVariables.replace("Blogs Page Scope Page"),
			selenium.getText("//div[5]/div/div/div[2]/ul/li[2]/a"));
	}
}