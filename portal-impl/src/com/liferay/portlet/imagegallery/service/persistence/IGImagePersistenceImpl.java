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

package com.liferay.portlet.imagegallery.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQuery;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.spring.hibernate.FinderCache;
import com.liferay.portal.spring.hibernate.HibernateUtil;
import com.liferay.portal.util.PropsUtil;

import com.liferay.portlet.imagegallery.NoSuchImageException;
import com.liferay.portlet.imagegallery.model.IGImage;
import com.liferay.portlet.imagegallery.model.impl.IGImageImpl;

import com.liferay.util.dao.hibernate.QueryUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="IGImagePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class IGImagePersistenceImpl extends BasePersistence
	implements IGImagePersistence {
	public IGImage create(long imageId) {
		IGImage igImage = new IGImageImpl();
		igImage.setNew(true);
		igImage.setPrimaryKey(imageId);

		return igImage;
	}

	public IGImage remove(long imageId)
		throws NoSuchImageException, SystemException {
		Session session = null;

		try {
			session = openSession();

			IGImage igImage = (IGImage)session.get(IGImageImpl.class,
					new Long(imageId));

			if (igImage == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No IGImage exists with the primary key " +
						imageId);
				}

				throw new NoSuchImageException(
					"No IGImage exists with the primary key " + imageId);
			}

			return remove(igImage);
		}
		catch (NoSuchImageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public IGImage remove(IGImage igImage) throws SystemException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(igImage);
		}

		igImage = removeImpl(igImage);

		if (listener != null) {
			listener.onAfterRemove(igImage);
		}

		return igImage;
	}

	protected IGImage removeImpl(IGImage igImage) throws SystemException {
		Session session = null;

		try {
			session = openSession();
			session.delete(igImage);
			session.flush();

			return igImage;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
			FinderCache.clearCache(IGImage.class.getName());
		}
	}

	public IGImage update(
		com.liferay.portlet.imagegallery.model.IGImage igImage)
		throws SystemException {
		return update(igImage, false);
	}

	public IGImage update(
		com.liferay.portlet.imagegallery.model.IGImage igImage, boolean merge)
		throws SystemException {
		ModelListener listener = _getListener();
		boolean isNew = igImage.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(igImage);
			}
			else {
				listener.onBeforeUpdate(igImage);
			}
		}

		igImage = updateImpl(igImage, merge);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(igImage);
			}
			else {
				listener.onAfterUpdate(igImage);
			}
		}

		return igImage;
	}

	public IGImage updateImpl(
		com.liferay.portlet.imagegallery.model.IGImage igImage, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(igImage);
			}
			else {
				if (igImage.isNew()) {
					session.save(igImage);
				}
			}

			session.flush();
			igImage.setNew(false);

			return igImage;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
			FinderCache.clearCache(IGImage.class.getName());
		}
	}

	public IGImage findByPrimaryKey(long imageId)
		throws NoSuchImageException, SystemException {
		IGImage igImage = fetchByPrimaryKey(imageId);

		if (igImage == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No IGImage exists with the primary key " + imageId);
			}

			throw new NoSuchImageException(
				"No IGImage exists with the primary key " + imageId);
		}

		return igImage;
	}

	public IGImage fetchByPrimaryKey(long imageId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (IGImage)session.get(IGImageImpl.class, new Long(imageId));
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findByFolderId(long folderId) throws SystemException {
		String finderClassName = IGImage.class.getName();
		String finderMethodName = "findByFolderId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(folderId) };
		Object result = FinderCache.getResult(finderClassName,
				finderMethodName, finderParams, finderArgs, getSessionFactory());

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();
				query.append(
					"FROM com.liferay.portlet.imagegallery.model.IGImage WHERE ");
				query.append("folderId = ?");
				query.append(" ");
				query.append("ORDER BY ");
				query.append("imageId ASC");

				Query q = session.createQuery(query.toString());
				int queryPos = 0;
				q.setLong(queryPos++, folderId);

				List list = q.list();
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

	public List findByFolderId(long folderId, int begin, int end)
		throws SystemException {
		return findByFolderId(folderId, begin, end, null);
	}

	public List findByFolderId(long folderId, int begin, int end,
		OrderByComparator obc) throws SystemException {
		String finderClassName = IGImage.class.getName();
		String finderMethodName = "findByFolderId";
		String[] finderParams = new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(folderId), String.valueOf(begin), String.valueOf(end),
				String.valueOf(obc)
			};
		Object result = FinderCache.getResult(finderClassName,
				finderMethodName, finderParams, finderArgs, getSessionFactory());

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();
				query.append(
					"FROM com.liferay.portlet.imagegallery.model.IGImage WHERE ");
				query.append("folderId = ?");
				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}
				else {
					query.append("ORDER BY ");
					query.append("imageId ASC");
				}

				Query q = session.createQuery(query.toString());
				int queryPos = 0;
				q.setLong(queryPos++, folderId);

				List list = QueryUtil.list(q, getDialect(), begin, end);
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

	public IGImage findByFolderId_First(long folderId, OrderByComparator obc)
		throws NoSuchImageException, SystemException {
		List list = findByFolderId(folderId, 0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();
			msg.append("No IGImage exists with the key ");
			msg.append(StringPool.OPEN_CURLY_BRACE);
			msg.append("folderId=");
			msg.append(folderId);
			msg.append(StringPool.CLOSE_CURLY_BRACE);
			throw new NoSuchImageException(msg.toString());
		}
		else {
			return (IGImage)list.get(0);
		}
	}

	public IGImage findByFolderId_Last(long folderId, OrderByComparator obc)
		throws NoSuchImageException, SystemException {
		int count = countByFolderId(folderId);
		List list = findByFolderId(folderId, count - 1, count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();
			msg.append("No IGImage exists with the key ");
			msg.append(StringPool.OPEN_CURLY_BRACE);
			msg.append("folderId=");
			msg.append(folderId);
			msg.append(StringPool.CLOSE_CURLY_BRACE);
			throw new NoSuchImageException(msg.toString());
		}
		else {
			return (IGImage)list.get(0);
		}
	}

	public IGImage[] findByFolderId_PrevAndNext(long imageId, long folderId,
		OrderByComparator obc) throws NoSuchImageException, SystemException {
		IGImage igImage = findByPrimaryKey(imageId);
		int count = countByFolderId(folderId);
		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();
			query.append(
				"FROM com.liferay.portlet.imagegallery.model.IGImage WHERE ");
			query.append("folderId = ?");
			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}
			else {
				query.append("ORDER BY ");
				query.append("imageId ASC");
			}

			Query q = session.createQuery(query.toString());
			int queryPos = 0;
			q.setLong(queryPos++, folderId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, igImage);
			IGImage[] array = new IGImageImpl[3];
			array[0] = (IGImage)objArray[0];
			array[1] = (IGImage)objArray[1];
			array[2] = (IGImage)objArray[2];

			return array;
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
		String finderClassName = IGImage.class.getName();
		String finderMethodName = "findAll";
		String[] finderParams = new String[] {
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				String.valueOf(begin), String.valueOf(end), String.valueOf(obc)
			};
		Object result = FinderCache.getResult(finderClassName,
				finderMethodName, finderParams, finderArgs, getSessionFactory());

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();
				query.append(
					"FROM com.liferay.portlet.imagegallery.model.IGImage ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}
				else {
					query.append("ORDER BY ");
					query.append("imageId ASC");
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

	public void removeByFolderId(long folderId) throws SystemException {
		Iterator itr = findByFolderId(folderId).iterator();

		while (itr.hasNext()) {
			IGImage igImage = (IGImage)itr.next();
			remove(igImage);
		}
	}

	public void removeAll() throws SystemException {
		Iterator itr = findAll().iterator();

		while (itr.hasNext()) {
			remove((IGImage)itr.next());
		}
	}

	public int countByFolderId(long folderId) throws SystemException {
		String finderClassName = IGImage.class.getName();
		String finderMethodName = "countByFolderId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(folderId) };
		Object result = FinderCache.getResult(finderClassName,
				finderMethodName, finderParams, finderArgs, getSessionFactory());

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();
				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.portlet.imagegallery.model.IGImage WHERE ");
				query.append("folderId = ?");
				query.append(" ");

				Query q = session.createQuery(query.toString());
				int queryPos = 0;
				q.setLong(queryPos++, folderId);

				Long count = null;
				Iterator itr = q.list().iterator();

				if (itr.hasNext()) {
					count = (Long)itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassName, finderMethodName,
					finderParams, finderArgs, count);

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
		String finderClassName = IGImage.class.getName();
		String finderMethodName = "countAll";
		String[] finderParams = new String[] {  };
		Object[] finderArgs = new Object[] {  };
		Object result = FinderCache.getResult(finderClassName,
				finderMethodName, finderParams, finderArgs, getSessionFactory());

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();
				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.portlet.imagegallery.model.IGImage");

				Query q = session.createQuery(query.toString());
				Long count = null;
				Iterator itr = q.list().iterator();

				if (itr.hasNext()) {
					count = (Long)itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassName, finderMethodName,
					finderParams, finderArgs, count);

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

	protected void initDao() {
	}

	private static ModelListener _getListener() {
		if (Validator.isNotNull(_LISTENER)) {
			try {
				return (ModelListener)Class.forName(_LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		return null;
	}

	private static final String _LISTENER = GetterUtil.getString(PropsUtil.get(
				"value.object.listener.com.liferay.portlet.imagegallery.model.IGImage"));
	private static Log _log = LogFactory.getLog(IGImagePersistenceImpl.class);
}