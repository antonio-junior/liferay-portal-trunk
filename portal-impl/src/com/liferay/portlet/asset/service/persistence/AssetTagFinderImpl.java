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

package com.liferay.portlet.asset.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.asset.NoSuchTagException;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.model.impl.AssetTagImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * <a href="AssetTagFinderImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 */
public class AssetTagFinderImpl
	extends BasePersistenceImpl<AssetTag> implements AssetTagFinder {

	public static String COUNT_BY_G_C_N =
		AssetTagFinder.class.getName() + ".countByG_C_N";

	public static String COUNT_BY_G_N_P =
		AssetTagFinder.class.getName() + ".countByG_N_P";

	public static String FIND_BY_ENTRY_ID =
		AssetTagFinder.class.getName() + ".findByEntryId";

	public static String FIND_BY_G_N =
		AssetTagFinder.class.getName() + ".findByG_N";

	public static String FIND_BY_C_C =
		AssetTagFinder.class.getName() + ".findByC_C";

	public static String FIND_BY_G_C_N =
		AssetTagFinder.class.getName() + ".findByG_C_N";

	public static String FIND_BY_G_N_P =
		AssetTagFinder.class.getName() + ".findByG_N_P";

	public int countByG_C_N(long groupId, long classNameId, String name)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_G_C_N);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(classNameId);
			qPos.add(name);
			qPos.add(name);

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

	public int countByG_N_P(long groupId, String name, String[] tagProperties)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_G_N_P);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, tagProperties);
			qPos.add(groupId);
			qPos.add(name);
			qPos.add(name);

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

	public List<AssetTag> findByEntryId(long entryId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_ENTRY_ID);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetTag", AssetTagImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(entryId);

			return (List<AssetTag>) QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public AssetTag findByG_N(long groupId, String name)
		throws NoSuchTagException, SystemException {

		name = name.trim().toLowerCase();

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_G_N);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetTag", AssetTagImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(name);

			List<AssetTag> list = q.list();

			if (list.size() == 0) {
				StringBundler sb = new StringBundler(6);

				sb.append("No AssetTag exists with the key ");
				sb.append("{groupId=");
				sb.append(groupId);
				sb.append(", name=");
				sb.append(name);
				sb.append("}");

				throw new NoSuchTagException(sb.toString());
			}
			else {
				return list.get(0);
			}
		}
		catch (NoSuchTagException nste) {
			throw nste;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<AssetTag> findByC_C(long classNameId, long classPK)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_C);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetTag", AssetTagImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(classNameId);
			qPos.add(classPK);

			return (List<AssetTag>) QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<AssetTag> findByG_C_N(
			long groupId, long classNameId, String name)
		throws SystemException {

		return findByG_C_N(
			groupId, classNameId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<AssetTag> findByG_C_N(
			long groupId, long classNameId, String name, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_G_C_N);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetTag", AssetTagImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(classNameId);
			qPos.add(name);
			qPos.add(name);

			return (List<AssetTag>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<AssetTag> findByG_N_P(
			long groupId, String name, String[] tagProperties)
		throws SystemException {

		return findByG_N_P(
			groupId, name, tagProperties, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<AssetTag> findByG_N_P(
			long groupId, String name, String[] tagProperties, int start,
			int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_G_N_P);

			sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(tagProperties));

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetTag", AssetTagImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, tagProperties);
			qPos.add(groupId);
			qPos.add(name);
			qPos.add(name);

			return (List<AssetTag>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getJoin(String[] tagProperties) {
		if (tagProperties.length == 0) {
			return StringPool.BLANK;
		}
		else {
			StringBundler sb = new StringBundler(tagProperties.length * 3 + 1);

			sb.append(" INNER JOIN AssetTagProperty ON ");
			sb.append(" (AssetTagProperty.tagId = AssetTag.tagId) AND ");

			for (int i = 0; i < tagProperties.length; i++) {
				sb.append("(AssetTagProperty.key_ = ? AND ");
				sb.append("AssetTagProperty.value = ?) ");

				if ((i + 1) < tagProperties.length) {
					sb.append(" AND ");
				}
			}

			return sb.toString();
		}
	}

	protected void setJoin(QueryPos qPos, String[] tagProperties) {
		for (int i = 0; i < tagProperties.length; i++) {
			String[] tagProperty = StringUtil.split(
				tagProperties[i], StringPool.COLON);

			String key = StringPool.BLANK;

			if (tagProperty.length > 0) {
				key = GetterUtil.getString(tagProperty[0]);
			}

			String value = StringPool.BLANK;

			if (tagProperty.length > 1) {
				value = GetterUtil.getString(tagProperty[1]);
			}

			qPos.add(key);
			qPos.add(value);
		}
	}

}