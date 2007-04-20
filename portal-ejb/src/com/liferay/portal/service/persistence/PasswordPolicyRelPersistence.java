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

package com.liferay.portal.service.persistence;

import com.liferay.portal.NoSuchPasswordPolicyRelException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQuery;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.PasswordPolicyRel;
import com.liferay.portal.model.impl.PasswordPolicyRelImpl;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.spring.hibernate.HibernateUtil;

import com.liferay.util.dao.hibernate.QueryUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

/**
 * <a href="PasswordPolicyRelPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PasswordPolicyRelPersistence extends BasePersistence {
	public PasswordPolicyRel create(long passwordPolicyRelId) {
		PasswordPolicyRel passwordPolicyRel = new PasswordPolicyRelImpl();
		passwordPolicyRel.setNew(true);
		passwordPolicyRel.setPrimaryKey(passwordPolicyRelId);

		return passwordPolicyRel;
	}

	public PasswordPolicyRel remove(long passwordPolicyRelId)
		throws NoSuchPasswordPolicyRelException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PasswordPolicyRel passwordPolicyRel = (PasswordPolicyRel)session.get(PasswordPolicyRelImpl.class,
					new Long(passwordPolicyRelId));

			if (passwordPolicyRel == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No PasswordPolicyRel exists with the primary key " +
						passwordPolicyRelId);
				}

				throw new NoSuchPasswordPolicyRelException(
					"No PasswordPolicyRel exists with the primary key " +
					passwordPolicyRelId);
			}

			return remove(passwordPolicyRel);
		}
		catch (NoSuchPasswordPolicyRelException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public PasswordPolicyRel remove(PasswordPolicyRel passwordPolicyRel)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();
			session.delete(passwordPolicyRel);
			session.flush();

			return passwordPolicyRel;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public com.liferay.portal.model.PasswordPolicyRel update(
		com.liferay.portal.model.PasswordPolicyRel passwordPolicyRel)
		throws SystemException {
		return update(passwordPolicyRel, false);
	}

	public com.liferay.portal.model.PasswordPolicyRel update(
		com.liferay.portal.model.PasswordPolicyRel passwordPolicyRel,
		boolean saveOrUpdate) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (saveOrUpdate) {
				session.saveOrUpdate(passwordPolicyRel);
			}
			else {
				if (passwordPolicyRel.isNew()) {
					session.save(passwordPolicyRel);
				}
			}

			session.flush();
			passwordPolicyRel.setNew(false);

			return passwordPolicyRel;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public PasswordPolicyRel findByPrimaryKey(long passwordPolicyRelId)
		throws NoSuchPasswordPolicyRelException, SystemException {
		PasswordPolicyRel passwordPolicyRel = fetchByPrimaryKey(passwordPolicyRelId);

		if (passwordPolicyRel == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No PasswordPolicyRel exists with the primary key " +
					passwordPolicyRelId);
			}

			throw new NoSuchPasswordPolicyRelException(
				"No PasswordPolicyRel exists with the primary key " +
				passwordPolicyRelId);
		}

		return passwordPolicyRel;
	}

	public PasswordPolicyRel fetchByPrimaryKey(long passwordPolicyRelId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (PasswordPolicyRel)session.get(PasswordPolicyRelImpl.class,
				new Long(passwordPolicyRelId));
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findByP(long passwordPolicyId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append(
				"FROM com.liferay.portal.model.PasswordPolicyRel WHERE ");
			query.append("passwordPolicyId = ?");
			query.append(" ");

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, passwordPolicyId);

			return q.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findByP(long passwordPolicyId, int begin, int end)
		throws SystemException {
		return findByP(passwordPolicyId, begin, end, null);
	}

	public List findByP(long passwordPolicyId, int begin, int end,
		OrderByComparator obc) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append(
				"FROM com.liferay.portal.model.PasswordPolicyRel WHERE ");
			query.append("passwordPolicyId = ?");
			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, passwordPolicyId);

			return QueryUtil.list(q, getDialect(), begin, end);
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public PasswordPolicyRel findByP_First(long passwordPolicyId,
		OrderByComparator obc)
		throws NoSuchPasswordPolicyRelException, SystemException {
		List list = findByP(passwordPolicyId, 0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();
			msg.append("No PasswordPolicyRel exists with the key ");
			msg.append(StringPool.OPEN_CURLY_BRACE);
			msg.append("passwordPolicyId=");
			msg.append(passwordPolicyId);
			msg.append(StringPool.CLOSE_CURLY_BRACE);
			throw new NoSuchPasswordPolicyRelException(msg.toString());
		}
		else {
			return (PasswordPolicyRel)list.get(0);
		}
	}

	public PasswordPolicyRel findByP_Last(long passwordPolicyId,
		OrderByComparator obc)
		throws NoSuchPasswordPolicyRelException, SystemException {
		int count = countByP(passwordPolicyId);
		List list = findByP(passwordPolicyId, count - 1, count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();
			msg.append("No PasswordPolicyRel exists with the key ");
			msg.append(StringPool.OPEN_CURLY_BRACE);
			msg.append("passwordPolicyId=");
			msg.append(passwordPolicyId);
			msg.append(StringPool.CLOSE_CURLY_BRACE);
			throw new NoSuchPasswordPolicyRelException(msg.toString());
		}
		else {
			return (PasswordPolicyRel)list.get(0);
		}
	}

	public PasswordPolicyRel[] findByP_PrevAndNext(long passwordPolicyRelId,
		long passwordPolicyId, OrderByComparator obc)
		throws NoSuchPasswordPolicyRelException, SystemException {
		PasswordPolicyRel passwordPolicyRel = findByPrimaryKey(passwordPolicyRelId);
		int count = countByP(passwordPolicyId);
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append(
				"FROM com.liferay.portal.model.PasswordPolicyRel WHERE ");
			query.append("passwordPolicyId = ?");
			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, passwordPolicyId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					passwordPolicyRel);
			PasswordPolicyRel[] array = new PasswordPolicyRelImpl[3];
			array[0] = (PasswordPolicyRel)objArray[0];
			array[1] = (PasswordPolicyRel)objArray[1];
			array[2] = (PasswordPolicyRel)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public PasswordPolicyRel findByC_C(String className, String classPK)
		throws NoSuchPasswordPolicyRelException, SystemException {
		PasswordPolicyRel passwordPolicyRel = fetchByC_C(className, classPK);

		if (passwordPolicyRel == null) {
			StringMaker msg = new StringMaker();
			msg.append("No PasswordPolicyRel exists with the key ");
			msg.append(StringPool.OPEN_CURLY_BRACE);
			msg.append("className=");
			msg.append(className);
			msg.append(", ");
			msg.append("classPK=");
			msg.append(classPK);
			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPasswordPolicyRelException(msg.toString());
		}

		return passwordPolicyRel;
	}

	public PasswordPolicyRel fetchByC_C(String className, String classPK)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append(
				"FROM com.liferay.portal.model.PasswordPolicyRel WHERE ");

			if (className == null) {
				query.append("className IS NULL");
			}
			else {
				query.append("className = ?");
			}

			query.append(" AND ");

			if (classPK == null) {
				query.append("classPK IS NULL");
			}
			else {
				query.append("classPK = ?");
			}

			query.append(" ");

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;

			if (className != null) {
				q.setString(queryPos++, className);
			}

			if (classPK != null) {
				q.setString(queryPos++, classPK);
			}

			List list = q.list();

			if (list.size() == 0) {
				return null;
			}

			PasswordPolicyRel passwordPolicyRel = (PasswordPolicyRel)list.get(0);

			return passwordPolicyRel;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public PasswordPolicyRel findByP_C_C(long passwordPolicyId,
		String className, String classPK)
		throws NoSuchPasswordPolicyRelException, SystemException {
		PasswordPolicyRel passwordPolicyRel = fetchByP_C_C(passwordPolicyId,
				className, classPK);

		if (passwordPolicyRel == null) {
			StringMaker msg = new StringMaker();
			msg.append("No PasswordPolicyRel exists with the key ");
			msg.append(StringPool.OPEN_CURLY_BRACE);
			msg.append("passwordPolicyId=");
			msg.append(passwordPolicyId);
			msg.append(", ");
			msg.append("className=");
			msg.append(className);
			msg.append(", ");
			msg.append("classPK=");
			msg.append(classPK);
			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPasswordPolicyRelException(msg.toString());
		}

		return passwordPolicyRel;
	}

	public PasswordPolicyRel fetchByP_C_C(long passwordPolicyId,
		String className, String classPK) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append(
				"FROM com.liferay.portal.model.PasswordPolicyRel WHERE ");
			query.append("passwordPolicyId = ?");
			query.append(" AND ");

			if (className == null) {
				query.append("className IS NULL");
			}
			else {
				query.append("className = ?");
			}

			query.append(" AND ");

			if (classPK == null) {
				query.append("classPK IS NULL");
			}
			else {
				query.append("classPK = ?");
			}

			query.append(" ");

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, passwordPolicyId);

			if (className != null) {
				q.setString(queryPos++, className);
			}

			if (classPK != null) {
				q.setString(queryPos++, classPK);
			}

			List list = q.list();

			if (list.size() == 0) {
				return null;
			}

			PasswordPolicyRel passwordPolicyRel = (PasswordPolicyRel)list.get(0);

			return passwordPolicyRel;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findWithDynamicQuery(DynamicQueryInitializer queryInitializer)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			DynamicQuery query = queryInitializer.initialize(session);

			return query.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findWithDynamicQuery(DynamicQueryInitializer queryInitializer,
		int begin, int end) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			DynamicQuery query = queryInitializer.initialize(session);
			query.setLimit(begin, end);

			return query.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List findAll(int begin, int end) throws SystemException {
		return findAll(begin, end, null);
	}

	public List findAll(int begin, int end, OrderByComparator obc)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append("FROM com.liferay.portal.model.PasswordPolicyRel ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			return QueryUtil.list(q, getDialect(), begin, end);
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public void removeByP(long passwordPolicyId) throws SystemException {
		Iterator itr = findByP(passwordPolicyId).iterator();

		while (itr.hasNext()) {
			PasswordPolicyRel passwordPolicyRel = (PasswordPolicyRel)itr.next();
			remove(passwordPolicyRel);
		}
	}

	public void removeByC_C(String className, String classPK)
		throws NoSuchPasswordPolicyRelException, SystemException {
		PasswordPolicyRel passwordPolicyRel = findByC_C(className, classPK);
		remove(passwordPolicyRel);
	}

	public void removeByP_C_C(long passwordPolicyId, String className,
		String classPK)
		throws NoSuchPasswordPolicyRelException, SystemException {
		PasswordPolicyRel passwordPolicyRel = findByP_C_C(passwordPolicyId,
				className, classPK);
		remove(passwordPolicyRel);
	}

	public void removeAll() throws SystemException {
		Iterator itr = findAll().iterator();

		while (itr.hasNext()) {
			remove((PasswordPolicyRel)itr.next());
		}
	}

	public int countByP(long passwordPolicyId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append("SELECT COUNT(*) ");
			query.append(
				"FROM com.liferay.portal.model.PasswordPolicyRel WHERE ");
			query.append("passwordPolicyId = ?");
			query.append(" ");

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, passwordPolicyId);

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
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countByC_C(String className, String classPK)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append("SELECT COUNT(*) ");
			query.append(
				"FROM com.liferay.portal.model.PasswordPolicyRel WHERE ");

			if (className == null) {
				query.append("className IS NULL");
			}
			else {
				query.append("className = ?");
			}

			query.append(" AND ");

			if (classPK == null) {
				query.append("classPK IS NULL");
			}
			else {
				query.append("classPK = ?");
			}

			query.append(" ");

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;

			if (className != null) {
				q.setString(queryPos++, className);
			}

			if (classPK != null) {
				q.setString(queryPos++, classPK);
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
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countByP_C_C(long passwordPolicyId, String className,
		String classPK) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append("SELECT COUNT(*) ");
			query.append(
				"FROM com.liferay.portal.model.PasswordPolicyRel WHERE ");
			query.append("passwordPolicyId = ?");
			query.append(" AND ");

			if (className == null) {
				query.append("className IS NULL");
			}
			else {
				query.append("className = ?");
			}

			query.append(" AND ");

			if (classPK == null) {
				query.append("classPK IS NULL");
			}
			else {
				query.append("classPK = ?");
			}

			query.append(" ");

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			int queryPos = 0;
			q.setLong(queryPos++, passwordPolicyId);

			if (className != null) {
				q.setString(queryPos++, className);
			}

			if (classPK != null) {
				q.setString(queryPos++, classPK);
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
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countAll() throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append("SELECT COUNT(*) ");
			query.append("FROM com.liferay.portal.model.PasswordPolicyRel");

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

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
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected void initDao() {
	}

	private static Log _log = LogFactory.getLog(PasswordPolicyRelPersistence.class);
}