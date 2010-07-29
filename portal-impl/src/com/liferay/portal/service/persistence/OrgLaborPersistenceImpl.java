/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.NoSuchOrgLaborException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.OrgLabor;
import com.liferay.portal.model.impl.OrgLaborImpl;
import com.liferay.portal.model.impl.OrgLaborModelImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence for the org labor service.
 *
 * <p>
 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regnerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrgLaborPersistence
 * @see OrgLaborUtil
 * @generated
 */
public class OrgLaborPersistenceImpl extends BasePersistenceImpl<OrgLabor>
	implements OrgLaborPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = OrgLaborImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_ORGANIZATIONID = new FinderPath(OrgLaborModelImpl.ENTITY_CACHE_ENABLED,
			OrgLaborModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByOrganizationId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ORGANIZATIONID = new FinderPath(OrgLaborModelImpl.ENTITY_CACHE_ENABLED,
			OrgLaborModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByOrganizationId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(OrgLaborModelImpl.ENTITY_CACHE_ENABLED,
			OrgLaborModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OrgLaborModelImpl.ENTITY_CACHE_ENABLED,
			OrgLaborModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the org labor in the entity cache if it is enabled.
	 *
	 * @param orgLabor the org labor to cache
	 */
	public void cacheResult(OrgLabor orgLabor) {
		EntityCacheUtil.putResult(OrgLaborModelImpl.ENTITY_CACHE_ENABLED,
			OrgLaborImpl.class, orgLabor.getPrimaryKey(), orgLabor);
	}

	/**
	 * Caches the org labors in the entity cache if it is enabled.
	 *
	 * @param orgLabors the org labors to cache
	 */
	public void cacheResult(List<OrgLabor> orgLabors) {
		for (OrgLabor orgLabor : orgLabors) {
			if (EntityCacheUtil.getResult(
						OrgLaborModelImpl.ENTITY_CACHE_ENABLED,
						OrgLaborImpl.class, orgLabor.getPrimaryKey(), this) == null) {
				cacheResult(orgLabor);
			}
		}
	}

	/**
	 * Clears the cache for all org labors.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(OrgLaborImpl.class.getName());
		EntityCacheUtil.clearCache(OrgLaborImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the org labor.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(OrgLabor orgLabor) {
		EntityCacheUtil.removeResult(OrgLaborModelImpl.ENTITY_CACHE_ENABLED,
			OrgLaborImpl.class, orgLabor.getPrimaryKey());
	}

	/**
	 * Creates a new org labor with the primary key.
	 *
	 * @param orgLaborId the primary key for the new org labor
	 * @return the new org labor
	 */
	public OrgLabor create(long orgLaborId) {
		OrgLabor orgLabor = new OrgLaborImpl();

		orgLabor.setNew(true);
		orgLabor.setPrimaryKey(orgLaborId);

		return orgLabor;
	}

	/**
	 * Removes the org labor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the org labor to remove
	 * @return the org labor that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a org labor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrgLabor remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the org labor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orgLaborId the primary key of the org labor to remove
	 * @return the org labor that was removed
	 * @throws com.liferay.portal.NoSuchOrgLaborException if a org labor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrgLabor remove(long orgLaborId)
		throws NoSuchOrgLaborException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OrgLabor orgLabor = (OrgLabor)session.get(OrgLaborImpl.class,
					new Long(orgLaborId));

			if (orgLabor == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + orgLaborId);
				}

				throw new NoSuchOrgLaborException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					orgLaborId);
			}

			return remove(orgLabor);
		}
		catch (NoSuchOrgLaborException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OrgLabor removeImpl(OrgLabor orgLabor) throws SystemException {
		orgLabor = toUnwrappedModel(orgLabor);

		Session session = null;

		try {
			session = openSession();

			if (orgLabor.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(OrgLaborImpl.class,
						orgLabor.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(orgLabor);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(OrgLaborModelImpl.ENTITY_CACHE_ENABLED,
			OrgLaborImpl.class, orgLabor.getPrimaryKey());

		return orgLabor;
	}

	public OrgLabor updateImpl(com.liferay.portal.model.OrgLabor orgLabor,
		boolean merge) throws SystemException {
		orgLabor = toUnwrappedModel(orgLabor);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, orgLabor, merge);

			orgLabor.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(OrgLaborModelImpl.ENTITY_CACHE_ENABLED,
			OrgLaborImpl.class, orgLabor.getPrimaryKey(), orgLabor);

		return orgLabor;
	}

	protected OrgLabor toUnwrappedModel(OrgLabor orgLabor) {
		if (orgLabor instanceof OrgLaborImpl) {
			return orgLabor;
		}

		OrgLaborImpl orgLaborImpl = new OrgLaborImpl();

		orgLaborImpl.setNew(orgLabor.isNew());
		orgLaborImpl.setPrimaryKey(orgLabor.getPrimaryKey());

		orgLaborImpl.setOrgLaborId(orgLabor.getOrgLaborId());
		orgLaborImpl.setOrganizationId(orgLabor.getOrganizationId());
		orgLaborImpl.setTypeId(orgLabor.getTypeId());
		orgLaborImpl.setSunOpen(orgLabor.getSunOpen());
		orgLaborImpl.setSunClose(orgLabor.getSunClose());
		orgLaborImpl.setMonOpen(orgLabor.getMonOpen());
		orgLaborImpl.setMonClose(orgLabor.getMonClose());
		orgLaborImpl.setTueOpen(orgLabor.getTueOpen());
		orgLaborImpl.setTueClose(orgLabor.getTueClose());
		orgLaborImpl.setWedOpen(orgLabor.getWedOpen());
		orgLaborImpl.setWedClose(orgLabor.getWedClose());
		orgLaborImpl.setThuOpen(orgLabor.getThuOpen());
		orgLaborImpl.setThuClose(orgLabor.getThuClose());
		orgLaborImpl.setFriOpen(orgLabor.getFriOpen());
		orgLaborImpl.setFriClose(orgLabor.getFriClose());
		orgLaborImpl.setSatOpen(orgLabor.getSatOpen());
		orgLaborImpl.setSatClose(orgLabor.getSatClose());

		return orgLaborImpl;
	}

	/**
	 * Finds the org labor with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the org labor to find
	 * @return the org labor
	 * @throws com.liferay.portal.NoSuchModelException if a org labor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrgLabor findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the org labor with the primary key or throws a {@link com.liferay.portal.NoSuchOrgLaborException} if it could not be found.
	 *
	 * @param orgLaborId the primary key of the org labor to find
	 * @return the org labor
	 * @throws com.liferay.portal.NoSuchOrgLaborException if a org labor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrgLabor findByPrimaryKey(long orgLaborId)
		throws NoSuchOrgLaborException, SystemException {
		OrgLabor orgLabor = fetchByPrimaryKey(orgLaborId);

		if (orgLabor == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + orgLaborId);
			}

			throw new NoSuchOrgLaborException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				orgLaborId);
		}

		return orgLabor;
	}

	/**
	 * Finds the org labor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the org labor to find
	 * @return the org labor, or <code>null</code> if a org labor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrgLabor fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the org labor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param orgLaborId the primary key of the org labor to find
	 * @return the org labor, or <code>null</code> if a org labor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrgLabor fetchByPrimaryKey(long orgLaborId)
		throws SystemException {
		OrgLabor orgLabor = (OrgLabor)EntityCacheUtil.getResult(OrgLaborModelImpl.ENTITY_CACHE_ENABLED,
				OrgLaborImpl.class, orgLaborId, this);

		if (orgLabor == null) {
			Session session = null;

			try {
				session = openSession();

				orgLabor = (OrgLabor)session.get(OrgLaborImpl.class,
						new Long(orgLaborId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (orgLabor != null) {
					cacheResult(orgLabor);
				}

				closeSession(session);
			}
		}

		return orgLabor;
	}

	/**
	 * Finds all the org labors where organizationId = &#63;.
	 *
	 * @param organizationId the organization id to search with
	 * @return the matching org labors
	 * @throws SystemException if a system exception occurred
	 */
	public List<OrgLabor> findByOrganizationId(long organizationId)
		throws SystemException {
		return findByOrganizationId(organizationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the org labors where organizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param organizationId the organization id to search with
	 * @param start the lower bound of the range of org labors to return
	 * @param end the upper bound of the range of org labors to return (not inclusive)
	 * @return the range of matching org labors
	 * @throws SystemException if a system exception occurred
	 */
	public List<OrgLabor> findByOrganizationId(long organizationId, int start,
		int end) throws SystemException {
		return findByOrganizationId(organizationId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the org labors where organizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param organizationId the organization id to search with
	 * @param start the lower bound of the range of org labors to return
	 * @param end the upper bound of the range of org labors to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching org labors
	 * @throws SystemException if a system exception occurred
	 */
	public List<OrgLabor> findByOrganizationId(long organizationId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				organizationId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<OrgLabor> list = (List<OrgLabor>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ORGANIZATIONID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_ORGLABOR_WHERE);

				query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(OrgLaborModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

				list = (List<OrgLabor>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<OrgLabor>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ORGANIZATIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first org labor in the ordered set where organizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param organizationId the organization id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching org labor
	 * @throws com.liferay.portal.NoSuchOrgLaborException if a matching org labor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrgLabor findByOrganizationId_First(long organizationId,
		OrderByComparator orderByComparator)
		throws NoSuchOrgLaborException, SystemException {
		List<OrgLabor> list = findByOrganizationId(organizationId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("organizationId=");
			msg.append(organizationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchOrgLaborException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last org labor in the ordered set where organizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param organizationId the organization id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching org labor
	 * @throws com.liferay.portal.NoSuchOrgLaborException if a matching org labor could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrgLabor findByOrganizationId_Last(long organizationId,
		OrderByComparator orderByComparator)
		throws NoSuchOrgLaborException, SystemException {
		int count = countByOrganizationId(organizationId);

		List<OrgLabor> list = findByOrganizationId(organizationId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("organizationId=");
			msg.append(organizationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchOrgLaborException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the org labors before and after the current org labor in the ordered set where organizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param orgLaborId the primary key of the current org labor
	 * @param organizationId the organization id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next org labor
	 * @throws com.liferay.portal.NoSuchOrgLaborException if a org labor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrgLabor[] findByOrganizationId_PrevAndNext(long orgLaborId,
		long organizationId, OrderByComparator orderByComparator)
		throws NoSuchOrgLaborException, SystemException {
		OrgLabor orgLabor = findByPrimaryKey(orgLaborId);

		Session session = null;

		try {
			session = openSession();

			OrgLabor[] array = new OrgLaborImpl[3];

			array[0] = getByOrganizationId_PrevAndNext(session, orgLabor,
					organizationId, orderByComparator, true);

			array[1] = orgLabor;

			array[2] = getByOrganizationId_PrevAndNext(session, orgLabor,
					organizationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OrgLabor getByOrganizationId_PrevAndNext(Session session,
		OrgLabor orgLabor, long organizationId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ORGLABOR_WHERE);

		query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(OrgLaborModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(organizationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(orgLabor);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OrgLabor> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the org labors.
	 *
	 * @return the org labors
	 * @throws SystemException if a system exception occurred
	 */
	public List<OrgLabor> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the org labors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of org labors to return
	 * @param end the upper bound of the range of org labors to return (not inclusive)
	 * @return the range of org labors
	 * @throws SystemException if a system exception occurred
	 */
	public List<OrgLabor> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the org labors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of org labors to return
	 * @param end the upper bound of the range of org labors to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of org labors
	 * @throws SystemException if a system exception occurred
	 */
	public List<OrgLabor> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<OrgLabor> list = (List<OrgLabor>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (orderByComparator != null) {
					query = new StringBundler(2 +
							(orderByComparator.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_ORGLABOR);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_ORGLABOR.concat(OrgLaborModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<OrgLabor>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<OrgLabor>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<OrgLabor>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the org labors where organizationId = &#63; from the database.
	 *
	 * @param organizationId the organization id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOrganizationId(long organizationId)
		throws SystemException {
		for (OrgLabor orgLabor : findByOrganizationId(organizationId)) {
			remove(orgLabor);
		}
	}

	/**
	 * Removes all the org labors from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (OrgLabor orgLabor : findAll()) {
			remove(orgLabor);
		}
	}

	/**
	 * Counts all the org labors where organizationId = &#63;.
	 *
	 * @param organizationId the organization id to search with
	 * @return the number of matching org labors
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOrganizationId(long organizationId)
		throws SystemException {
		Object[] finderArgs = new Object[] { organizationId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_ORGLABOR_WHERE);

				query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the org labors.
	 *
	 * @return the number of org labors
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ORGLABOR);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the org labor persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portal.model.OrgLabor")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OrgLabor>> listenersList = new ArrayList<ModelListener<OrgLabor>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<OrgLabor>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = AccountPersistence.class)
	protected AccountPersistence accountPersistence;
	@BeanReference(type = AddressPersistence.class)
	protected AddressPersistence addressPersistence;
	@BeanReference(type = BrowserTrackerPersistence.class)
	protected BrowserTrackerPersistence browserTrackerPersistence;
	@BeanReference(type = ClusterGroupPersistence.class)
	protected ClusterGroupPersistence clusterGroupPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@BeanReference(type = ContactPersistence.class)
	protected ContactPersistence contactPersistence;
	@BeanReference(type = CountryPersistence.class)
	protected CountryPersistence countryPersistence;
	@BeanReference(type = EmailAddressPersistence.class)
	protected EmailAddressPersistence emailAddressPersistence;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = LayoutPrototypePersistence.class)
	protected LayoutPrototypePersistence layoutPrototypePersistence;
	@BeanReference(type = LayoutSetPersistence.class)
	protected LayoutSetPersistence layoutSetPersistence;
	@BeanReference(type = LayoutSetPrototypePersistence.class)
	protected LayoutSetPrototypePersistence layoutSetPrototypePersistence;
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = LockPersistence.class)
	protected LockPersistence lockPersistence;
	@BeanReference(type = MembershipRequestPersistence.class)
	protected MembershipRequestPersistence membershipRequestPersistence;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = OrgGroupPermissionPersistence.class)
	protected OrgGroupPermissionPersistence orgGroupPermissionPersistence;
	@BeanReference(type = OrgGroupRolePersistence.class)
	protected OrgGroupRolePersistence orgGroupRolePersistence;
	@BeanReference(type = OrgLaborPersistence.class)
	protected OrgLaborPersistence orgLaborPersistence;
	@BeanReference(type = PasswordPolicyPersistence.class)
	protected PasswordPolicyPersistence passwordPolicyPersistence;
	@BeanReference(type = PasswordPolicyRelPersistence.class)
	protected PasswordPolicyRelPersistence passwordPolicyRelPersistence;
	@BeanReference(type = PasswordTrackerPersistence.class)
	protected PasswordTrackerPersistence passwordTrackerPersistence;
	@BeanReference(type = PermissionPersistence.class)
	protected PermissionPersistence permissionPersistence;
	@BeanReference(type = PhonePersistence.class)
	protected PhonePersistence phonePersistence;
	@BeanReference(type = PluginSettingPersistence.class)
	protected PluginSettingPersistence pluginSettingPersistence;
	@BeanReference(type = PortletPersistence.class)
	protected PortletPersistence portletPersistence;
	@BeanReference(type = PortletItemPersistence.class)
	protected PortletItemPersistence portletItemPersistence;
	@BeanReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;
	@BeanReference(type = RegionPersistence.class)
	protected RegionPersistence regionPersistence;
	@BeanReference(type = ReleasePersistence.class)
	protected ReleasePersistence releasePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = ResourceActionPersistence.class)
	protected ResourceActionPersistence resourceActionPersistence;
	@BeanReference(type = ResourceCodePersistence.class)
	protected ResourceCodePersistence resourceCodePersistence;
	@BeanReference(type = ResourcePermissionPersistence.class)
	protected ResourcePermissionPersistence resourcePermissionPersistence;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = ServiceComponentPersistence.class)
	protected ServiceComponentPersistence serviceComponentPersistence;
	@BeanReference(type = ShardPersistence.class)
	protected ShardPersistence shardPersistence;
	@BeanReference(type = SubscriptionPersistence.class)
	protected SubscriptionPersistence subscriptionPersistence;
	@BeanReference(type = TicketPersistence.class)
	protected TicketPersistence ticketPersistence;
	@BeanReference(type = TeamPersistence.class)
	protected TeamPersistence teamPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserGroupPersistence.class)
	protected UserGroupPersistence userGroupPersistence;
	@BeanReference(type = UserGroupGroupRolePersistence.class)
	protected UserGroupGroupRolePersistence userGroupGroupRolePersistence;
	@BeanReference(type = UserGroupRolePersistence.class)
	protected UserGroupRolePersistence userGroupRolePersistence;
	@BeanReference(type = UserIdMapperPersistence.class)
	protected UserIdMapperPersistence userIdMapperPersistence;
	@BeanReference(type = UserTrackerPersistence.class)
	protected UserTrackerPersistence userTrackerPersistence;
	@BeanReference(type = UserTrackerPathPersistence.class)
	protected UserTrackerPathPersistence userTrackerPathPersistence;
	@BeanReference(type = WebDAVPropsPersistence.class)
	protected WebDAVPropsPersistence webDAVPropsPersistence;
	@BeanReference(type = WebsitePersistence.class)
	protected WebsitePersistence websitePersistence;
	@BeanReference(type = WorkflowDefinitionLinkPersistence.class)
	protected WorkflowDefinitionLinkPersistence workflowDefinitionLinkPersistence;
	@BeanReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;
	private static final String _SQL_SELECT_ORGLABOR = "SELECT orgLabor FROM OrgLabor orgLabor";
	private static final String _SQL_SELECT_ORGLABOR_WHERE = "SELECT orgLabor FROM OrgLabor orgLabor WHERE ";
	private static final String _SQL_COUNT_ORGLABOR = "SELECT COUNT(orgLabor) FROM OrgLabor orgLabor";
	private static final String _SQL_COUNT_ORGLABOR_WHERE = "SELECT COUNT(orgLabor) FROM OrgLabor orgLabor WHERE ";
	private static final String _FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2 = "orgLabor.organizationId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "orgLabor.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OrgLabor exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OrgLabor exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(OrgLaborPersistenceImpl.class);
}