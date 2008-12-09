/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.social.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.impl.SocialActivityImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="SocialActivityFinderImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SocialActivityFinderImpl
	extends BasePersistenceImpl implements SocialActivityFinder {

	public static String COUNT_BY_GROUP_ID =
		SocialActivityFinder.class.getName() + ".countByGroupId";

	public static String COUNT_BY_GROUP_USERS =
		SocialActivityFinder.class.getName() + ".countByGroupUsers";

	public static String COUNT_BY_ORGANIZATION_ID =
		SocialActivityFinder.class.getName() + ".countByOrganizationId";

	public static String COUNT_BY_ORGANIZATION_USERS =
		SocialActivityFinder.class.getName() + ".countByOrganizationUsers";

	public static String COUNT_BY_RELATION =
		SocialActivityFinder.class.getName() + ".countByRelation";

	public static String COUNT_BY_RELATION_TYPE =
		SocialActivityFinder.class.getName() + ".countByRelationType";

	public static String COUNT_BY_USER_GROUPS =
		SocialActivityFinder.class.getName() + ".countByUserGroups";

	public static String COUNT_BY_USER_GROUPS_AND_ORGANIZATIONS =
		SocialActivityFinder.class.getName() +
			".countByUserGroupsAndOrganizations";

	public static String COUNT_BY_USER_ORGANIZATIONS =
		SocialActivityFinder.class.getName() + ".countByUserOrganizations";

	public static String FIND_BY_GROUP_ID =
		SocialActivityFinder.class.getName() + ".findByGroupId";

	public static String FIND_BY_GROUP_USERS =
		SocialActivityFinder.class.getName() + ".findByGroupUsers";

	public static String FIND_BY_ORGANIZATION_ID =
		SocialActivityFinder.class.getName() + ".findByOrganizationId";

	public static String FIND_BY_ORGANIZATION_USERS =
		SocialActivityFinder.class.getName() + ".findByOrganizationUsers";

	public static String FIND_BY_RELATION =
		SocialActivityFinder.class.getName() + ".findByRelation";

	public static String FIND_BY_RELATION_TYPE =
		SocialActivityFinder.class.getName() + ".findByRelationType";

	public static String FIND_BY_USER_GROUPS =
		SocialActivityFinder.class.getName() + ".findByUserGroups";

	public static String FIND_BY_USER_GROUPS_AND_ORGANIZATIONS =
		SocialActivityFinder.class.getName() +
			".findByUserGroupsAndOrganizations";

	public static String FIND_BY_USER_ORGANIZATIONS =
		SocialActivityFinder.class.getName() + ".findByUserOrganizations";

	public int countByGroupId(long groupId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_GROUP_ID);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

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
			closeSession(session);
		}
	}

	public int countByGroupUsers(long groupId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_GROUP_USERS);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

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
			closeSession(session);
		}
	}

	public int countByOrganizationId(long organizationId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_ORGANIZATION_ID);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(organizationId);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

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
			closeSession(session);
		}
	}

	public int countByOrganizationUsers(long organizationId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_ORGANIZATION_USERS);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(organizationId);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

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
			closeSession(session);
		}
	}

	public int countByRelation(long userId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_RELATION);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

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
			closeSession(session);
		}
	}

	public int countByRelationType(long userId, int type)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_RELATION_TYPE);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(type);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

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
			closeSession(session);
		}
	}

	public int countByUserGroups(long userId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_USER_GROUPS);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

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
			closeSession(session);
		}
	}

	public int countByUserGroupsAndOrganizations(long userId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				COUNT_BY_USER_GROUPS_AND_ORGANIZATIONS);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(userId);

			int count = 0;

			Iterator<Long> itr = q.list().iterator();

			while (itr.hasNext()) {
				Long l = itr.next();

				if (l != null) {
					count += l.intValue();
				}
			}

			return count;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countByUserOrganizations(long userId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_USER_ORGANIZATIONS);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

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
			closeSession(session);
		}
	}

	public List<SocialActivity> findByGroupId(long groupId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_GROUP_ID);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return Collections.unmodifiableList((List<SocialActivity>)
				QueryUtil.list(q, getDialect(), start, end));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SocialActivity> findByGroupUsers(
			long groupId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_GROUP_USERS);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return Collections.unmodifiableList((List<SocialActivity>)
				QueryUtil.list(q, getDialect(), start, end));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SocialActivity> findByOrganizationId(
			long organizationId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_ORGANIZATION_ID);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(organizationId);

			return Collections.unmodifiableList((List<SocialActivity>)
				QueryUtil.list(q, getDialect(), start, end));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SocialActivity> findByOrganizationUsers(
			long organizationId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_ORGANIZATION_USERS);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(organizationId);

			return Collections.unmodifiableList((List<SocialActivity>)
				QueryUtil.list(q, getDialect(), start, end));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SocialActivity> findByRelation(long userId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_RELATION);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			return Collections.unmodifiableList((List<SocialActivity>)
				QueryUtil.list(q, getDialect(), start, end));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SocialActivity> findByRelationType(
			long userId, int type, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_RELATION_TYPE);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(type);

			return Collections.unmodifiableList((List<SocialActivity>)
				QueryUtil.list(q, getDialect(), start, end));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SocialActivity> findByUserGroups(
			long userId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_USER_GROUPS);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			return Collections.unmodifiableList((List<SocialActivity>)
				QueryUtil.list(q, getDialect(), start, end));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SocialActivity> findByUserGroupsAndOrganizations(
			long userId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				FIND_BY_USER_GROUPS_AND_ORGANIZATIONS);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("activityId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(userId);

			List<SocialActivity> socialActivities =
				new ArrayList<SocialActivity>();

			Iterator<Long> itr = (Iterator<Long>)QueryUtil.iterate(
				q, getDialect(), start, end);

			while (itr.hasNext()) {
				Long activityId = itr.next();

				SocialActivity socialActivity =
					SocialActivityUtil.findByPrimaryKey(activityId);

				socialActivities.add(socialActivity);
			}

			return Collections.unmodifiableList(socialActivities);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SocialActivity> findByUserOrganizations(
			long userId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_USER_ORGANIZATIONS);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("SocialActivity", SocialActivityImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			return Collections.unmodifiableList((List<SocialActivity>)
				QueryUtil.list(q, getDialect(), start, end));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}