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

package com.liferay.portalweb.portlet.managepages.page.savepagetypelinktopage;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * <a href="SavePageTypeLinkToPageTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class SavePageTypeLinkToPageTest extends BaseTestCase {
	public void testSavePageTypeLinkToPage() throws Exception {
		selenium.open("/web/guest/home/");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isElementPresent(
							"//li[@class='first manage-page']/a")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("//li[@class='first manage-page']/a",
			RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible(
							"//div[@id='_88_layoutsTreeOutput']/ul/li")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("//div[@id='_88_treeExpandAll']/a",
			RuntimeVariables.replace(""));

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible(
							"//div[@id='_88_layoutsTreeOutput']/ul/li/ul/li/div/div[3]/a")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("//div[@id='_88_layoutsTreeOutput']/ul/li/ul/li[2]/div/div[3]/a",
			RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		selenium.clickAt("//li[@id='_88_tabs3pageTabsId']/span/a",
			RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		Thread.sleep(5000);
		selenium.select("_88_type",
			RuntimeVariables.replace("label=Link to Page"));

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isElementPresent(
							"TypeSettingsProperties(linkToLayoutId)")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent(
				"TypeSettingsProperties(linkToLayoutId)"));
		assertEquals(RuntimeVariables.replace(
				"- Welcome - Manage Pages Test Page"),
			selenium.getText("TypeSettingsProperties(linkToLayoutId)"));
		selenium.select("TypeSettingsProperties(linkToLayoutId)",
			RuntimeVariables.replace("label=regexp:-\\sWelcome"));
		selenium.click(RuntimeVariables.replace("//input[@value='Save']"));
		selenium.waitForPageToLoad("30000");
		selenium.open("/web/guest/home/");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isElementPresent("link=Manage Pages Test Page")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("link=Manage Pages Test Page",
			RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		assertEquals(RuntimeVariables.replace("Sign In"),
			selenium.getText("//section[@id='portlet_58']/header/h1"));
		assertEquals(RuntimeVariables.replace("Hello World"),
			selenium.getText("//section[@id='portlet_47']/header/h1"));
		assertTrue(selenium.isElementPresent("link=Manage Pages Test Page"));
		assertFalse(selenium.isElementPresent(
				"//td[@class='panel-page-content panel-page-frontpage']/h2"));
		assertFalse(selenium.isElementPresent(
				"//td[@class='panel-page-content panel-page-frontpage']/div"));
		assertFalse(selenium.isTextPresent(
				"Please select a tool from the left menu."));
		assertFalse(selenium.isTextPresent("This is a test Web Content"));
		assertFalse(selenium.isElementPresent("//a[@id='logo']"));
	}
}