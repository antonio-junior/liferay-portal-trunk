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

import com.liferay.portal.NoSuchReleaseException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Release;
import com.liferay.portal.model.impl.ReleaseImpl;
import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.util.dao.hibernate.QueryUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

/**
 * <a href="ReleasePersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReleasePersistence extends BasePersistence {
	public Release create(String releaseId) {
		Release release = new ReleaseImpl();
		release.setNew(true);
		release.setPrimaryKey(releaseId);

		return release;
	}

	public Release remove(String releaseId)
		throws NoSuchReleaseException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Release release = (Release)session.get(ReleaseImpl.class, releaseId);

			if (release == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Release exists with the primary key " +
						releaseId);
				}

				throw new NoSuchReleaseException(
					"No Release exists with the primary key " + releaseId);
			}

			return remove(release);
		}
		catch (HibernateException he) {
			throw new SystemException(he);
		}
		finally {
			closeSession(session);
		}
	}

	public Release remove(Release release) throws SystemException {
		Session session = null;

		try {
			session = openSession();
			session.delete(release);
			session.flush();

			return release;
		}
		catch (HibernateException he) {
			throw new SystemException(he);
		}
		finally {
			closeSession(session);
		}
	}

	public com.liferay.portal.model.Release update(
		com.liferay.portal.model.Release release) throws SystemException {
		return update(release, false);
	}

	public com.liferay.portal.model.Release update(
		com.liferay.portal.model.Release release, boolean saveOrUpdate)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (saveOrUpdate) {
				session.saveOrUpdate(release);
			}
			else {
				if (release.isNew()) {
					session.save(release);
				}
			}

			session.flush();
			release.setNew(false);

			return release;
		}
		catch (HibernateException he) {
			throw new SystemException(he);
		}
		finally {
			closeSession(session);
		}
	}

	public Release findByPrimaryKey(String releaseId)
		throws NoSuchReleaseException, SystemException {
		Release release = fetchByPrimaryKey(releaseId);

		if (release == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Release exists with the primary key " +
					releaseId);
			}

			throw new NoSuchReleaseException(
				"No Release exists with the primary key " + releaseId);
		}

		return release;
	}

	public Release fetchByPrimaryKey(String releaseId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (Release)session.get(ReleaseImpl.class, releaseId);
		}
		catch (HibernateException he) {
			throw new SystemException(he);
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

			StringBuffer query = new StringBuffer();
			query.append("FROM com.liferay.portal.model.Release ");

			if (obc != null) {
				query.append("ORDER BY " + obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());
			q.setCacheable(true);

			return QueryUtil.list(q, getDialect(), begin, end);
		}
		catch (HibernateException he) {
			throw new SystemException(he);
		}
		finally {
			closeSession(session);
		}
	}

	public void removeAll() throws SystemException {
		Iterator itr = findAll().iterator();

		while (itr.hasNext()) {
			remove((Release)itr.next());
		}
	}

	public int countAll() throws SystemException {
		Session session = null;

		try {
			session = openSession();

			StringBuffer query = new StringBuffer();
			query.append("SELECT COUNT(*) ");
			query.append("FROM com.liferay.portal.model.Release");

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
		catch (HibernateException he) {
			throw new SystemException(he);
		}
		finally {
			closeSession(session);
		}
	}

	protected void initDao() {
	}

	private static Log _log = LogFactory.getLog(ReleasePersistence.class);
}