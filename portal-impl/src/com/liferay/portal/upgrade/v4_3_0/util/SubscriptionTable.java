/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.upgrade.v4_3_0.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class SubscriptionTable {

	public static String TABLE_NAME = "Subscription";

	public static Object[][] TABLE_COLUMNS = {
		{"subscriptionId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT},
		{"frequency", Types.VARCHAR}
	};

	public static String TABLE_SQL_CREATE = "create table Subscription (subscriptionId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,frequency VARCHAR(75) null)";

	public static String TABLE_SQL_DROP = "drop table Subscription";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_786D171A on Subscription (companyId, classNameId, classPK)",
		"create index IX_2E1A92D4 on Subscription (companyId, userId, classNameId, classPK)",
		"create index IX_54243AFD on Subscription (userId)"
	};

}