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

package com.liferay.portalweb.portal.controlpanel.organizations.organizationphonenumber.addorganizationphonenumbermultiple;

import com.liferay.portalweb.portal.BaseTests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Brian Wing Shun Chan
 */
public class AddOrganizationPhoneNumberMultipleTests extends BaseTests {

	public static Test suite() {
		TestSuite testSuite = new TestSuite();

		testSuite.addTestSuite(AddOrganizationTest.class);
		testSuite.addTestSuite(AddOrganizationPhoneNumber1Test.class);
		testSuite.addTestSuite(AddOrganizationPhoneNumber2Test.class);
		testSuite.addTestSuite(AddOrganizationPhoneNumber3Test.class);
		testSuite.addTestSuite(TearDownTest.class);

		return testSuite;
	}

}