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

package com.liferay.portlet.softwarecatalog.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQuery;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.spring.hibernate.FinderCache;
import com.liferay.portal.spring.hibernate.HibernateUtil;
import com.liferay.portal.util.PropsUtil;

import com.liferay.portlet.softwarecatalog.NoSuchProductVersionException;
import com.liferay.portlet.softwarecatalog.model.SCProductVersion;
import com.liferay.portlet.softwarecatalog.model.impl.SCProductVersionImpl;
import com.liferay.portlet.softwarecatalog.model.impl.SCProductVersionModelImpl;

import com.liferay.util.dao.hibernate.QueryPos;
import com.liferay.util.dao.hibernate.QueryUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="SCProductVersionPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SCProductVersionPersistenceImpl extends BasePersistence
	implements SCProductVersionPersistence {
	public SCProductVersion create(long productVersionId) {
		SCProductVersion scProductVersion = new SCProductVersionImpl();

		scProductVersion.setNew(true);
		scProductVersion.setPrimaryKey(productVersionId);

		return scProductVersion;
	}

	public SCProductVersion remove(long productVersionId)
		throws NoSuchProductVersionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SCProductVersion scProductVersion = (SCProductVersion)session.get(SCProductVersionImpl.class,
					new Long(productVersionId));

			if (scProductVersion == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No SCProductVersion exists with the primary key " +
						productVersionId);
				}

				throw new NoSuchProductVersionException(
					"No SCProductVersion exists with the primary key " +
					productVersionId);
			}

			return remove(scProductVersion);
		}
		catch (NoSuchProductVersionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public SCProductVersion remove(SCProductVersion scProductVersion)
		throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(scProductVersion);
			}
		}

		scProductVersion = removeImpl(scProductVersion);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(scProductVersion);
			}
		}

		return scProductVersion;
	}

	protected SCProductVersion removeImpl(SCProductVersion scProductVersion)
		throws SystemException {
		try {
			clearSCFrameworkVersions.clear(scProductVersion.getPrimaryKey());
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			FinderCache.clearCache("SCFrameworkVersi_SCProductVers");
		}

		Session session = null;

		try {
			session = openSession();

			session.delete(scProductVersion);

			session.flush();

			return scProductVersion;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(SCProductVersion.class.getName());
		}
	}

	/**
	 * @deprecated Use <code>update(SCProductVersion scProductVersion, boolean merge)</code>.
	 */
	public SCProductVersion update(SCProductVersion scProductVersion)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(SCProductVersion scProductVersion) method. Use update(SCProductVersion scProductVersion, boolean merge) instead.");
		}

		return update(scProductVersion, false);
	}

	/**
	 * Add, update, or merge, the entity. This method also calls the model
	 * listeners to trigger the proper events associated with adding, deleting,
	 * or updating an entity.
	 *
	 * @param        scProductVersion the entity to add, update, or merge
	 * @param        merge boolean value for whether to merge the entity. The
	 *                default value is false. Setting merge to true is more
	 *                expensive and should only be true when scProductVersion is
	 *                transient. See LEP-5473 for a detailed discussion of this
	 *                method.
	 * @return        true if the portlet can be displayed via Ajax
	 */
	public SCProductVersion update(SCProductVersion scProductVersion,
		boolean merge) throws SystemException {
		boolean isNew = scProductVersion.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(scProductVersion);
				}
				else {
					listener.onBeforeUpdate(scProductVersion);
				}
			}
		}

		scProductVersion = updateImpl(scProductVersion, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(scProductVersion);
				}
				else {
					listener.onAfterUpdate(scProductVersion);
				}
			}
		}

		return scProductVersion;
	}

	public SCProductVersion updateImpl(
		com.liferay.portlet.softwarecatalog.model.SCProductVersion scProductVersion,
		boolean merge) throws SystemException {
		FinderCache.clearCache("SCFrameworkVersi_SCProductVers");

		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(scProductVersion);
			}
			else {
				if (scProductVersion.isNew()) {
					session.save(scProductVersion);
				}
			}

			session.flush();

			scProductVersion.setNew(false);

			return scProductVersion;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(SCProductVersion.class.getName());
		}
	}

	public SCProductVersion findByPrimaryKey(long productVersionId)
		throws NoSuchProductVersionException, SystemException {
		SCProductVersion scProductVersion = fetchByPrimaryKey(productVersionId);

		if (scProductVersion == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No SCProductVersion exists with the primary key " +
					productVersionId);
			}

			throw new NoSuchProductVersionException(
				"No SCProductVersion exists with the primary key " +
				productVersionId);
		}

		return scProductVersion;
	}

	public SCProductVersion fetchByPrimaryKey(long productVersionId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (SCProductVersion)session.get(SCProductVersionImpl.class,
				new Long(productVersionId));
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SCProductVersion> findByProductEntryId(long productEntryId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SCProductVersionModelImpl.CACHE_ENABLED;
		String finderClassName = SCProductVersion.class.getName();
		String finderMethodName = "findByProductEntryId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(productEntryId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.portlet.softwarecatalog.model.SCProductVersion WHERE ");

				query.append("productEntryId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				List<SCProductVersion> list = q.list();

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

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
			return (List<SCProductVersion>)result;
		}
	}

	public List<SCProductVersion> findByProductEntryId(long productEntryId,
		int start, int end) throws SystemException {
		return findByProductEntryId(productEntryId, start, end, null);
	}

	public List<SCProductVersion> findByProductEntryId(long productEntryId,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = SCProductVersionModelImpl.CACHE_ENABLED;
		String finderClassName = SCProductVersion.class.getName();
		String finderMethodName = "findByProductEntryId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(productEntryId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.portlet.softwarecatalog.model.SCProductVersion WHERE ");

				query.append("productEntryId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				List<SCProductVersion> list = (List<SCProductVersion>)QueryUtil.list(q,
						getDialect(), start, end);

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

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
			return (List<SCProductVersion>)result;
		}
	}

	public SCProductVersion findByProductEntryId_First(long productEntryId,
		OrderByComparator obc)
		throws NoSuchProductVersionException, SystemException {
		List<SCProductVersion> list = findByProductEntryId(productEntryId, 0,
				1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No SCProductVersion exists with the key {");

			msg.append("productEntryId=" + productEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchProductVersionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public SCProductVersion findByProductEntryId_Last(long productEntryId,
		OrderByComparator obc)
		throws NoSuchProductVersionException, SystemException {
		int count = countByProductEntryId(productEntryId);

		List<SCProductVersion> list = findByProductEntryId(productEntryId,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No SCProductVersion exists with the key {");

			msg.append("productEntryId=" + productEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchProductVersionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public SCProductVersion[] findByProductEntryId_PrevAndNext(
		long productVersionId, long productEntryId, OrderByComparator obc)
		throws NoSuchProductVersionException, SystemException {
		SCProductVersion scProductVersion = findByPrimaryKey(productVersionId);

		int count = countByProductEntryId(productEntryId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.portlet.softwarecatalog.model.SCProductVersion WHERE ");

			query.append("productEntryId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(productEntryId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					scProductVersion);

			SCProductVersion[] array = new SCProductVersionImpl[3];

			array[0] = (SCProductVersion)objArray[0];
			array[1] = (SCProductVersion)objArray[1];
			array[2] = (SCProductVersion)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public SCProductVersion findByDirectDownloadURL(String directDownloadURL)
		throws NoSuchProductVersionException, SystemException {
		SCProductVersion scProductVersion = fetchByDirectDownloadURL(directDownloadURL);

		if (scProductVersion == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No SCProductVersion exists with the key {");

			msg.append("directDownloadURL=" + directDownloadURL);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchProductVersionException(msg.toString());
		}

		return scProductVersion;
	}

	public SCProductVersion fetchByDirectDownloadURL(String directDownloadURL)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SCProductVersionModelImpl.CACHE_ENABLED;
		String finderClassName = SCProductVersion.class.getName();
		String finderMethodName = "fetchByDirectDownloadURL";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { directDownloadURL };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.portlet.softwarecatalog.model.SCProductVersion WHERE ");

				if (directDownloadURL == null) {
					query.append("directDownloadURL IS NULL");
				}
				else {
					query.append("lower(directDownloadURL) = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (directDownloadURL != null) {
					qPos.add(directDownloadURL);
				}

				List<SCProductVersion> list = q.list();

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				if (list.size() == 0) {
					return null;
				}
				else {
					return list.get(0);
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
			List<SCProductVersion> list = (List<SCProductVersion>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
		}
	}

	public List<SCProductVersion> findWithDynamicQuery(
		DynamicQueryInitializer queryInitializer) throws SystemException {
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

	public List<SCProductVersion> findWithDynamicQuery(
		DynamicQueryInitializer queryInitializer, int start, int end)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			DynamicQuery query = queryInitializer.initialize(session);

			query.setLimit(start, end);

			return query.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SCProductVersion> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<SCProductVersion> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<SCProductVersion> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = SCProductVersionModelImpl.CACHE_ENABLED;
		String finderClassName = SCProductVersion.class.getName();
		String finderMethodName = "findAll";
		String[] finderParams = new String[] {
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.portlet.softwarecatalog.model.SCProductVersion ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				List<SCProductVersion> list = (List<SCProductVersion>)QueryUtil.list(q,
						getDialect(), start, end);

				if (obc == null) {
					Collections.sort(list);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

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
			return (List<SCProductVersion>)result;
		}
	}

	public void removeByProductEntryId(long productEntryId)
		throws SystemException {
		for (SCProductVersion scProductVersion : findByProductEntryId(
				productEntryId)) {
			remove(scProductVersion);
		}
	}

	public void removeByDirectDownloadURL(String directDownloadURL)
		throws NoSuchProductVersionException, SystemException {
		SCProductVersion scProductVersion = findByDirectDownloadURL(directDownloadURL);

		remove(scProductVersion);
	}

	public void removeAll() throws SystemException {
		for (SCProductVersion scProductVersion : findAll()) {
			remove(scProductVersion);
		}
	}

	public int countByProductEntryId(long productEntryId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SCProductVersionModelImpl.CACHE_ENABLED;
		String finderClassName = SCProductVersion.class.getName();
		String finderMethodName = "countByProductEntryId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(productEntryId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.portlet.softwarecatalog.model.SCProductVersion WHERE ");

				query.append("productEntryId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countByDirectDownloadURL(String directDownloadURL)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SCProductVersionModelImpl.CACHE_ENABLED;
		String finderClassName = SCProductVersion.class.getName();
		String finderMethodName = "countByDirectDownloadURL";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { directDownloadURL };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.portlet.softwarecatalog.model.SCProductVersion WHERE ");

				if (directDownloadURL == null) {
					query.append("directDownloadURL IS NULL");
				}
				else {
					query.append("lower(directDownloadURL) = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (directDownloadURL != null) {
					qPos.add(directDownloadURL);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = SCProductVersionModelImpl.CACHE_ENABLED;
		String finderClassName = SCProductVersion.class.getName();
		String finderMethodName = "countAll";
		String[] finderParams = new String[] {  };
		Object[] finderArgs = new Object[] {  };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(*) FROM com.liferay.portlet.softwarecatalog.model.SCProductVersion");

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> getSCFrameworkVersions(
		long pk) throws SystemException {
		return getSCFrameworkVersions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> getSCFrameworkVersions(
		long pk, int start, int end) throws SystemException {
		return getSCFrameworkVersions(pk, start, end, null);
	}

	public List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> getSCFrameworkVersions(
		long pk, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SCProductVersionModelImpl.CACHE_ENABLED_SCFRAMEWORKVERSI_SCPRODUCTVERS;

		String finderClassName = "SCFrameworkVersi_SCProductVers";

		String finderMethodName = "getSCFrameworkVersions";
		String[] finderParams = new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(pk), String.valueOf(start), String.valueOf(end),
				String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = HibernateUtil.openSession();

				StringBuilder sb = new StringBuilder();

				sb.append(_SQL_GETSCFRAMEWORKVERSIONS);

				if (obc != null) {
					sb.append("ORDER BY ");
					sb.append(obc.getOrderBy());
				}

				else {
					sb.append("ORDER BY ");

					sb.append("SCFrameworkVersion.priority ASC, ");
					sb.append("SCFrameworkVersion.name ASC");
				}

				String sql = sb.toString();

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("SCFrameworkVersion",
					com.liferay.portlet.softwarecatalog.model.impl.SCFrameworkVersionImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> list =
					(List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion>)QueryUtil.list(q,
						getDialect(), start, end);

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw new SystemException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion>)result;
		}
	}

	public int getSCFrameworkVersionsSize(long pk) throws SystemException {
		boolean finderClassNameCacheEnabled = SCProductVersionModelImpl.CACHE_ENABLED_SCFRAMEWORKVERSI_SCPRODUCTVERS;

		String finderClassName = "SCFrameworkVersi_SCProductVers";

		String finderMethodName = "getSCFrameworkVersionsSize";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(pk) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETSCFRAMEWORKVERSIONSSIZE);

				q.addScalar(HibernateUtil.getCountColumnName(), Hibernate.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public boolean containsSCFrameworkVersion(long pk, long scFrameworkVersionPK)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SCProductVersionModelImpl.CACHE_ENABLED_SCFRAMEWORKVERSI_SCPRODUCTVERS;

		String finderClassName = "SCFrameworkVersi_SCProductVers";

		String finderMethodName = "containsSCFrameworkVersions";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				Long.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(pk),
				
				new Long(scFrameworkVersionPK)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			try {
				Boolean value = Boolean.valueOf(containsSCFrameworkVersion.contains(
							pk, scFrameworkVersionPK));

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, value);

				return value.booleanValue();
			}
			catch (DataAccessException dae) {
				throw new SystemException(dae);
			}
		}
		else {
			return ((Boolean)result).booleanValue();
		}
	}

	public boolean containsSCFrameworkVersions(long pk)
		throws SystemException {
		if (getSCFrameworkVersionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void addSCFrameworkVersion(long pk, long scFrameworkVersionPK)
		throws SystemException {
		try {
			addSCFrameworkVersion.add(pk, scFrameworkVersionPK);
		}
		catch (DataAccessException dae) {
			throw new SystemException(dae);
		}
		finally {
			FinderCache.clearCache("SCFrameworkVersi_SCProductVers");
		}
	}

	public void addSCFrameworkVersion(long pk,
		com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion scFrameworkVersion)
		throws SystemException {
		try {
			addSCFrameworkVersion.add(pk, scFrameworkVersion.getPrimaryKey());
		}
		catch (DataAccessException dae) {
			throw new SystemException(dae);
		}
		finally {
			FinderCache.clearCache("SCFrameworkVersi_SCProductVers");
		}
	}

	public void addSCFrameworkVersions(long pk, long[] scFrameworkVersionPKs)
		throws SystemException {
		try {
			for (long scFrameworkVersionPK : scFrameworkVersionPKs) {
				addSCFrameworkVersion.add(pk, scFrameworkVersionPK);
			}
		}
		catch (DataAccessException dae) {
			throw new SystemException(dae);
		}
		finally {
			FinderCache.clearCache("SCFrameworkVersi_SCProductVers");
		}
	}

	public void addSCFrameworkVersions(long pk,
		List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> scFrameworkVersions)
		throws SystemException {
		try {
			for (com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion scFrameworkVersion : scFrameworkVersions) {
				addSCFrameworkVersion.add(pk, scFrameworkVersion.getPrimaryKey());
			}
		}
		catch (DataAccessException dae) {
			throw new SystemException(dae);
		}
		finally {
			FinderCache.clearCache("SCFrameworkVersi_SCProductVers");
		}
	}

	public void clearSCFrameworkVersions(long pk) throws SystemException {
		try {
			clearSCFrameworkVersions.clear(pk);
		}
		catch (DataAccessException dae) {
			throw new SystemException(dae);
		}
		finally {
			FinderCache.clearCache("SCFrameworkVersi_SCProductVers");
		}
	}

	public void removeSCFrameworkVersion(long pk, long scFrameworkVersionPK)
		throws SystemException {
		try {
			removeSCFrameworkVersion.remove(pk, scFrameworkVersionPK);
		}
		catch (DataAccessException dae) {
			throw new SystemException(dae);
		}
		finally {
			FinderCache.clearCache("SCFrameworkVersi_SCProductVers");
		}
	}

	public void removeSCFrameworkVersion(long pk,
		com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion scFrameworkVersion)
		throws SystemException {
		try {
			removeSCFrameworkVersion.remove(pk,
				scFrameworkVersion.getPrimaryKey());
		}
		catch (DataAccessException dae) {
			throw new SystemException(dae);
		}
		finally {
			FinderCache.clearCache("SCFrameworkVersi_SCProductVers");
		}
	}

	public void removeSCFrameworkVersions(long pk, long[] scFrameworkVersionPKs)
		throws SystemException {
		try {
			for (long scFrameworkVersionPK : scFrameworkVersionPKs) {
				removeSCFrameworkVersion.remove(pk, scFrameworkVersionPK);
			}
		}
		catch (DataAccessException dae) {
			throw new SystemException(dae);
		}
		finally {
			FinderCache.clearCache("SCFrameworkVersi_SCProductVers");
		}
	}

	public void removeSCFrameworkVersions(long pk,
		List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> scFrameworkVersions)
		throws SystemException {
		try {
			for (com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion scFrameworkVersion : scFrameworkVersions) {
				removeSCFrameworkVersion.remove(pk,
					scFrameworkVersion.getPrimaryKey());
			}
		}
		catch (DataAccessException dae) {
			throw new SystemException(dae);
		}
		finally {
			FinderCache.clearCache("SCFrameworkVersi_SCProductVers");
		}
	}

	public void setSCFrameworkVersions(long pk, long[] scFrameworkVersionPKs)
		throws SystemException {
		try {
			clearSCFrameworkVersions.clear(pk);

			for (long scFrameworkVersionPK : scFrameworkVersionPKs) {
				addSCFrameworkVersion.add(pk, scFrameworkVersionPK);
			}
		}
		catch (DataAccessException dae) {
			throw new SystemException(dae);
		}
		finally {
			FinderCache.clearCache("SCFrameworkVersi_SCProductVers");
		}
	}

	public void setSCFrameworkVersions(long pk,
		List<com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion> scFrameworkVersions)
		throws SystemException {
		try {
			clearSCFrameworkVersions.clear(pk);

			for (com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion scFrameworkVersion : scFrameworkVersions) {
				addSCFrameworkVersion.add(pk, scFrameworkVersion.getPrimaryKey());
			}
		}
		catch (DataAccessException dae) {
			throw new SystemException(dae);
		}
		finally {
			FinderCache.clearCache("SCFrameworkVersi_SCProductVers");
		}
	}

	public void registerListener(ModelListener listener) {
		List<ModelListener> listeners = ListUtil.fromArray(_listeners);

		listeners.add(listener);

		_listeners = listeners.toArray(new ModelListener[listeners.size()]);
	}

	public void unregisterListener(ModelListener listener) {
		List<ModelListener> listeners = ListUtil.fromArray(_listeners);

		listeners.remove(listener);

		_listeners = listeners.toArray(new ModelListener[listeners.size()]);
	}

	protected void initDao() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					PropsUtil.get(
						"value.object.listener.com.liferay.portlet.softwarecatalog.model.SCProductVersion")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener> listeners = new ArrayList<ModelListener>();

				for (String listenerClassName : listenerClassNames) {
					listeners.add((ModelListener)Class.forName(
							listenerClassName).newInstance());
				}

				_listeners = listeners.toArray(new ModelListener[listeners.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsSCFrameworkVersion = new ContainsSCFrameworkVersion(this);

		addSCFrameworkVersion = new AddSCFrameworkVersion(this);
		clearSCFrameworkVersions = new ClearSCFrameworkVersions(this);
		removeSCFrameworkVersion = new RemoveSCFrameworkVersion(this);
	}

	protected ContainsSCFrameworkVersion containsSCFrameworkVersion;
	protected AddSCFrameworkVersion addSCFrameworkVersion;
	protected ClearSCFrameworkVersions clearSCFrameworkVersions;
	protected RemoveSCFrameworkVersion removeSCFrameworkVersion;

	protected class ContainsSCFrameworkVersion extends MappingSqlQuery {
		protected ContainsSCFrameworkVersion(
			SCProductVersionPersistenceImpl persistenceImpl) {
			super(persistenceImpl.getDataSource(),
				_SQL_CONTAINSSCFRAMEWORKVERSION);

			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.BIGINT));

			compile();
		}

		protected Object mapRow(ResultSet rs, int rowNumber)
			throws SQLException {
			return new Integer(rs.getInt("COUNT_VALUE"));
		}

		protected boolean contains(long productVersionId,
			long frameworkVersionId) {
			List<Integer> results = execute(new Object[] {
						new Long(productVersionId), new Long(frameworkVersionId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}
	}

	protected class AddSCFrameworkVersion extends SqlUpdate {
		protected AddSCFrameworkVersion(
			SCProductVersionPersistenceImpl persistenceImpl) {
			super(persistenceImpl.getDataSource(),
				"INSERT INTO SCFrameworkVersi_SCProductVers (productVersionId, frameworkVersionId) VALUES (?, ?)");

			_persistenceImpl = persistenceImpl;

			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.BIGINT));

			compile();
		}

		protected void add(long productVersionId, long frameworkVersionId) {
			if (!_persistenceImpl.containsSCFrameworkVersion.contains(
						productVersionId, frameworkVersionId)) {
				update(new Object[] {
						new Long(productVersionId), new Long(frameworkVersionId)
					});
			}
		}

		private SCProductVersionPersistenceImpl _persistenceImpl;
	}

	protected class ClearSCFrameworkVersions extends SqlUpdate {
		protected ClearSCFrameworkVersions(
			SCProductVersionPersistenceImpl persistenceImpl) {
			super(persistenceImpl.getDataSource(),
				"DELETE FROM SCFrameworkVersi_SCProductVers WHERE productVersionId = ?");

			declareParameter(new SqlParameter(Types.BIGINT));

			compile();
		}

		protected void clear(long productVersionId) {
			update(new Object[] { new Long(productVersionId) });
		}
	}

	protected class RemoveSCFrameworkVersion extends SqlUpdate {
		protected RemoveSCFrameworkVersion(
			SCProductVersionPersistenceImpl persistenceImpl) {
			super(persistenceImpl.getDataSource(),
				"DELETE FROM SCFrameworkVersi_SCProductVers WHERE productVersionId = ? AND frameworkVersionId = ?");

			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.BIGINT));

			compile();
		}

		protected void remove(long productVersionId, long frameworkVersionId) {
			update(new Object[] {
					new Long(productVersionId), new Long(frameworkVersionId)
				});
		}
	}

	private static final String _SQL_GETSCFRAMEWORKVERSIONS = "SELECT {SCFrameworkVersion.*} FROM SCFrameworkVersion INNER JOIN SCFrameworkVersi_SCProductVers ON (SCFrameworkVersi_SCProductVers.frameworkVersionId = SCFrameworkVersion.frameworkVersionId) WHERE (SCFrameworkVersi_SCProductVers.productVersionId = ?)";
	private static final String _SQL_GETSCFRAMEWORKVERSIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM SCFrameworkVersi_SCProductVers WHERE productVersionId = ?";
	private static final String _SQL_CONTAINSSCFRAMEWORKVERSION = "SELECT COUNT(*) AS COUNT_VALUE FROM SCFrameworkVersi_SCProductVers WHERE productVersionId = ? AND frameworkVersionId = ?";
	private static Log _log = LogFactory.getLog(SCProductVersionPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}