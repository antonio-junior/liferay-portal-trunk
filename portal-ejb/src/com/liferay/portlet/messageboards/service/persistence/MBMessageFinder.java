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

package com.liferay.portlet.messageboards.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.spring.hibernate.CustomSQLUtil;
import com.liferay.portal.spring.hibernate.HibernateUtil;
import com.liferay.portlet.messageboards.model.impl.MBMessageImpl;
import com.liferay.util.StringUtil;
import com.liferay.util.dao.hibernate.QueryPos;
import com.liferay.util.dao.hibernate.QueryUtil;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 * <a href="MBMessageFinder.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MBMessageFinder {

	public static String COUNT_BY_CATEGORY_IDS =
		MBMessageFinder.class.getName() + ".countByCategoryIds";

	public static String COUNT_BY_GROUP_ID =
		MBMessageFinder.class.getName() + ".countByGroupId";

	public static String FIND_BY_GROUP_ID =
		MBMessageFinder.class.getName() + ".findByGroupId";

	public static int countByCategoryIds(List categoryIds)
		throws SystemException {

		Session session = null;

		try {
			session = HibernateUtil.openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_CATEGORY_IDS);

			sql = StringUtil.replace(
				sql, "[$CATEGORY_ID$]", _getCategoryIds(categoryIds));

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(false);

			q.addScalar(HibernateUtil.getCountColumnName(), Hibernate.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			for (int i = 0; i < categoryIds.size(); i++) {
				Long categoryId = (Long)categoryIds.get(i);

				qPos.add(categoryId);
			}

			Iterator itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = (Long)itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
	}

	public static int countByGroupId(long groupId) throws SystemException {
		Session session = null;

		try {
			session = HibernateUtil.openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_GROUP_ID);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(false);

			q.addScalar(HibernateUtil.getCountColumnName(), Hibernate.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Iterator itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = (Long)itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
	}

	public static List findByGroupId(long groupId, int begin, int end)
		throws SystemException {

		Session session = null;

		try {
			session = HibernateUtil.openSession();

			String sql = CustomSQLUtil.get(FIND_BY_GROUP_ID);

			SQLQuery q = session.createSQLQuery(sql);

			q.setCacheable(false);

			q.addEntity("MBMessage", MBMessageImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return QueryUtil.list(q, HibernateUtil.getDialect(), begin, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
	}

	private static String _getCategoryIds(List categoryIds) {
		StringMaker sm = new StringMaker();

		for (int i = 0; i < categoryIds.size(); i++) {
			sm.append("categoryId = ? ");

			if ((i + 1) != categoryIds.size()) {
				sm.append("OR ");
			}
		}

		return sm.toString();
	}

}