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

package com.liferay.portal.spring.hibernate;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsUtil;

import java.sql.SQLException;

/**
 * <a href="PortalCustomSQLUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PortalCustomSQLUtil
	extends com.liferay.util.dao.hibernate.CustomSQLUtil {

	public PortalCustomSQLUtil() throws SQLException {
		super(HibernateUtil.getConnection(),
			  PropsUtil.get(PropsUtil.CUSTOM_SQL_FUNCTION_ISNULL),
			  PropsUtil.get(PropsUtil.CUSTOM_SQL_FUNCTION_ISNOTNULL));
	}

	protected String[] getConfigs() {
		return PropsUtil.getArray(PropsUtil.CUSTOM_SQL_CONFIGS);
	}

	protected String transform(String sql) {
		sql = super.transform(sql);

		long organizationClassNameId = PortalUtil.getClassNameId(
			Organization.class);
		long userClassNameId = PortalUtil.getClassNameId(User.class);
		long userGroupClassNameId = PortalUtil.getClassNameId(UserGroup.class);

		sql = StringUtil.replace(
			sql,
			new String[] {
				"[$CLASS_NAME_ID_COM.LIFERAY.PORTAL.MODEL.ORGANIZATION$]",
				"[$CLASS_NAME_ID_COM.LIFERAY.PORTAL.MODEL.USER$]",
				"[$CLASS_NAME_ID_COM.LIFERAY.PORTAL.MODEL.USERGROUP$]"
			},
			new String[] {
				String.valueOf(organizationClassNameId),
				String.valueOf(userClassNameId),
				String.valueOf(userGroupClassNameId)
			});

		return sql;
	}

}