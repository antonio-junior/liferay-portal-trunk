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

import com.liferay.portal.NoSuchClassNameException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQuery;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.impl.ClassNameImpl;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.spring.hibernate.FinderCache;
import com.liferay.portal.spring.hibernate.HibernateUtil;

import com.liferay.util.dao.hibernate.QueryUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="ClassNamePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ClassNamePersistenceImpl extends BasePersistence
	implements ClassNamePersistence {
	public ClassName create(long classNameId) {
		ClassName className = new ClassNameImpl();
		className.setNew(true);
		className.setPrimaryKey(classNameId);

		return className;
	}

	public ClassName remove(long classNameId)
		throws NoSuchClassNameException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ClassName className = (ClassName)session.get(ClassNameImpl.class,
					new Long(classNameId));

			if (className == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No ClassName exists with the primary key " +
						classNameId);
				}

				throw new NoSuchClassNameException(
					"No ClassName exists with the primary key " + classNameId);
			}

			return remove(className);
		}
		catch (NoSuchClassNameException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public ClassName remove(ClassName className) throws SystemException {
		FinderCache.clearCache(ClassName.class.getName());

		Session session = null;

		try {
			session = openSession();
			session.delete(className);
			session.flush();

			return className;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public ClassName update(com.liferay.portal.model.ClassName className)
		throws SystemException {
		return update(className, false);
	}

	public ClassName update(com.liferay.portal.model.ClassName className,
		boolean saveOrUpdate) throws SystemException {
		FinderCache.clearCache(ClassName.class.getName());

		Session session = null;

		try {
			session = openSession();

			if (saveOrUpdate) {
				session.saveOrUpdate(className);
			}
			else {
				if (className.isNew()) {
					session.save(className);
				}
			}

			session.flush();
			className.setNew(false);

			return className;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public ClassName findByPrimaryKey(long classNameId)
		throws NoSuchClassNameException, SystemException {
		ClassName className = fetchByPrimaryKey(classNameId);

		if (className == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No ClassName exists with the primary key " +
					classNameId);
			}

			throw new NoSuchClassNameException(
				"No ClassName exists with the primary key " + classNameId);
		}

		return className;
	}

	public ClassName fetchByPrimaryKey(long classNameId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (ClassName)session.get(ClassNameImpl.class,
				new Long(classNameId));
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public ClassName findByValue(String value)
		throws NoSuchClassNameException, SystemException {
		ClassName className = fetchByValue(value);

		if (className == null) {
			StringMaker msg = new StringMaker();
			msg.append("No ClassName exists with the key ");
			msg.append(StringPool.OPEN_CURLY_BRACE);
			msg.append("value=");
			msg.append(value);
			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchClassNameException(msg.toString());
		}

		return className;
	}

	public ClassName fetchByValue(String value) throws SystemException {
		String finderClassName = ClassName.class.getName();
		String finderMethodName = "fetchByValue";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { value };
		Object result = FinderCache.getResult(finderClassName,
				finderMethodName, finderParams, finderArgs);

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();
				query.append("FROM com.liferay.portal.model.ClassName WHERE ");

				if (value == null) {
					query.append("value IS NULL");
				}
				else {
					query.append("value = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());
				int queryPos = 0;

				if (value != null) {
					q.setString(queryPos++, value);
				}

				List list = q.list();
				FinderCache.putResult(finderClassName, finderMethodName,
					finderParams, finderArgs, list);

				if (list.size() == 0) {
					return null;
				}
				else {
					return (ClassName)list.get(0);
				}
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			List list = (List)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return (ClassName)list.get(0);
			}
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
		String finderClassName = ClassName.class.getName();
		String finderMethodName = "findAll";
		String[] finderParams = new String[] {
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				String.valueOf(begin), String.valueOf(end), String.valueOf(obc)
			};
		Object result = FinderCache.getResult(finderClassName,
				finderMethodName, finderParams, finderArgs);

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();
				query.append("FROM com.liferay.portal.model.ClassName ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());
				List list = QueryUtil.list(q, getDialect(), begin, end);

				if (obc == null) {
					Collections.sort(list);
				}

				FinderCache.putResult(finderClassName, finderMethodName,
					finderParams, finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List)result;
		}
	}

	public void removeByValue(String value)
		throws NoSuchClassNameException, SystemException {
		ClassName className = findByValue(value);
		remove(className);
	}

	public void removeAll() throws SystemException {
		Iterator itr = findAll().iterator();

		while (itr.hasNext()) {
			remove((ClassName)itr.next());
		}
	}

	public int countByValue(String value) throws SystemException {
		String finderClassName = ClassName.class.getName();
		String finderMethodName = "countByValue";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { value };
		Object result = FinderCache.getResult(finderClassName,
				finderMethodName, finderParams, finderArgs);

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();
				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.portal.model.ClassName WHERE ");

				if (value == null) {
					query.append("value IS NULL");
				}
				else {
					query.append("value = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());
				int queryPos = 0;

				if (value != null) {
					q.setString(queryPos++, value);
				}

				Iterator itr = q.list().iterator();

				if (itr.hasNext()) {
					Long count = (Long)itr.next();

					if (count != null) {
						FinderCache.putResult(finderClassName,
							finderMethodName, finderParams, finderArgs, count);

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
		else {
			return ((Integer)result).intValue();
		}
	}

	public int countAll() throws SystemException {
		String finderClassName = ClassName.class.getName();
		String finderMethodName = "countAll";
		String[] finderParams = new String[] {  };
		Object[] finderArgs = new Object[] {  };
		Object result = FinderCache.getResult(finderClassName,
				finderMethodName, finderParams, finderArgs);

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();
				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.portal.model.ClassName");

				Query q = session.createQuery(query.toString());
				Iterator itr = q.list().iterator();

				if (itr.hasNext()) {
					Long count = (Long)itr.next();

					if (count != null) {
						FinderCache.putResult(finderClassName,
							finderMethodName, finderParams, finderArgs, count);

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
		else {
			return ((Integer)result).intValue();
		}
	}

	protected void initDao() {
	}

	private static Log _log = LogFactory.getLog(ClassNamePersistenceImpl.class);
}