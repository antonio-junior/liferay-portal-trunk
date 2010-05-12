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

package com.liferay.portlet.shopping.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.shopping.NoSuchCouponException;
import com.liferay.portlet.shopping.model.ShoppingCoupon;
import com.liferay.portlet.shopping.model.impl.ShoppingCouponImpl;
import com.liferay.portlet.shopping.model.impl.ShoppingCouponModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="ShoppingCouponPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ShoppingCouponPersistence
 * @see       ShoppingCouponUtil
 * @generated
 */
public class ShoppingCouponPersistenceImpl extends BasePersistenceImpl<ShoppingCoupon>
	implements ShoppingCouponPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = ShoppingCouponImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_GROUPID = new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_CODE = new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByCode",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_CODE = new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCode",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(ShoppingCoupon shoppingCoupon) {
		EntityCacheUtil.putResult(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponImpl.class, shoppingCoupon.getPrimaryKey(),
			shoppingCoupon);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
			new Object[] { shoppingCoupon.getCode() }, shoppingCoupon);
	}

	public void cacheResult(List<ShoppingCoupon> shoppingCoupons) {
		for (ShoppingCoupon shoppingCoupon : shoppingCoupons) {
			if (EntityCacheUtil.getResult(
						ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
						ShoppingCouponImpl.class,
						shoppingCoupon.getPrimaryKey(), this) == null) {
				cacheResult(shoppingCoupon);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(ShoppingCouponImpl.class.getName());
		EntityCacheUtil.clearCache(ShoppingCouponImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(ShoppingCoupon shoppingCoupon) {
		EntityCacheUtil.removeResult(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponImpl.class, shoppingCoupon.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE,
			new Object[] { shoppingCoupon.getCode() });
	}

	public ShoppingCoupon create(long couponId) {
		ShoppingCoupon shoppingCoupon = new ShoppingCouponImpl();

		shoppingCoupon.setNew(true);
		shoppingCoupon.setPrimaryKey(couponId);

		return shoppingCoupon;
	}

	public ShoppingCoupon remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public ShoppingCoupon remove(long couponId)
		throws NoSuchCouponException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ShoppingCoupon shoppingCoupon = (ShoppingCoupon)session.get(ShoppingCouponImpl.class,
					new Long(couponId));

			if (shoppingCoupon == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + couponId);
				}

				throw new NoSuchCouponException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					couponId);
			}

			return remove(shoppingCoupon);
		}
		catch (NoSuchCouponException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public ShoppingCoupon remove(ShoppingCoupon shoppingCoupon)
		throws SystemException {
		for (ModelListener<ShoppingCoupon> listener : listeners) {
			listener.onBeforeRemove(shoppingCoupon);
		}

		shoppingCoupon = removeImpl(shoppingCoupon);

		for (ModelListener<ShoppingCoupon> listener : listeners) {
			listener.onAfterRemove(shoppingCoupon);
		}

		return shoppingCoupon;
	}

	protected ShoppingCoupon removeImpl(ShoppingCoupon shoppingCoupon)
		throws SystemException {
		shoppingCoupon = toUnwrappedModel(shoppingCoupon);

		Session session = null;

		try {
			session = openSession();

			if (shoppingCoupon.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(ShoppingCouponImpl.class,
						shoppingCoupon.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(shoppingCoupon);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		ShoppingCouponModelImpl shoppingCouponModelImpl = (ShoppingCouponModelImpl)shoppingCoupon;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE,
			new Object[] { shoppingCouponModelImpl.getOriginalCode() });

		EntityCacheUtil.removeResult(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponImpl.class, shoppingCoupon.getPrimaryKey());

		return shoppingCoupon;
	}

	public ShoppingCoupon updateImpl(
		com.liferay.portlet.shopping.model.ShoppingCoupon shoppingCoupon,
		boolean merge) throws SystemException {
		shoppingCoupon = toUnwrappedModel(shoppingCoupon);

		boolean isNew = shoppingCoupon.isNew();

		ShoppingCouponModelImpl shoppingCouponModelImpl = (ShoppingCouponModelImpl)shoppingCoupon;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, shoppingCoupon, merge);

			shoppingCoupon.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingCouponImpl.class, shoppingCoupon.getPrimaryKey(),
			shoppingCoupon);

		if (!isNew &&
				(!Validator.equals(shoppingCoupon.getCode(),
					shoppingCouponModelImpl.getOriginalCode()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE,
				new Object[] { shoppingCouponModelImpl.getOriginalCode() });
		}

		if (isNew ||
				(!Validator.equals(shoppingCoupon.getCode(),
					shoppingCouponModelImpl.getOriginalCode()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
				new Object[] { shoppingCoupon.getCode() }, shoppingCoupon);
		}

		return shoppingCoupon;
	}

	protected ShoppingCoupon toUnwrappedModel(ShoppingCoupon shoppingCoupon) {
		if (shoppingCoupon instanceof ShoppingCouponImpl) {
			return shoppingCoupon;
		}

		ShoppingCouponImpl shoppingCouponImpl = new ShoppingCouponImpl();

		shoppingCouponImpl.setNew(shoppingCoupon.isNew());
		shoppingCouponImpl.setPrimaryKey(shoppingCoupon.getPrimaryKey());

		shoppingCouponImpl.setCouponId(shoppingCoupon.getCouponId());
		shoppingCouponImpl.setGroupId(shoppingCoupon.getGroupId());
		shoppingCouponImpl.setCompanyId(shoppingCoupon.getCompanyId());
		shoppingCouponImpl.setUserId(shoppingCoupon.getUserId());
		shoppingCouponImpl.setUserName(shoppingCoupon.getUserName());
		shoppingCouponImpl.setCreateDate(shoppingCoupon.getCreateDate());
		shoppingCouponImpl.setModifiedDate(shoppingCoupon.getModifiedDate());
		shoppingCouponImpl.setCode(shoppingCoupon.getCode());
		shoppingCouponImpl.setName(shoppingCoupon.getName());
		shoppingCouponImpl.setDescription(shoppingCoupon.getDescription());
		shoppingCouponImpl.setStartDate(shoppingCoupon.getStartDate());
		shoppingCouponImpl.setEndDate(shoppingCoupon.getEndDate());
		shoppingCouponImpl.setActive(shoppingCoupon.isActive());
		shoppingCouponImpl.setLimitCategories(shoppingCoupon.getLimitCategories());
		shoppingCouponImpl.setLimitSkus(shoppingCoupon.getLimitSkus());
		shoppingCouponImpl.setMinOrder(shoppingCoupon.getMinOrder());
		shoppingCouponImpl.setDiscount(shoppingCoupon.getDiscount());
		shoppingCouponImpl.setDiscountType(shoppingCoupon.getDiscountType());

		return shoppingCouponImpl;
	}

	public ShoppingCoupon findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public ShoppingCoupon findByPrimaryKey(long couponId)
		throws NoSuchCouponException, SystemException {
		ShoppingCoupon shoppingCoupon = fetchByPrimaryKey(couponId);

		if (shoppingCoupon == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + couponId);
			}

			throw new NoSuchCouponException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				couponId);
		}

		return shoppingCoupon;
	}

	public ShoppingCoupon fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public ShoppingCoupon fetchByPrimaryKey(long couponId)
		throws SystemException {
		ShoppingCoupon shoppingCoupon = (ShoppingCoupon)EntityCacheUtil.getResult(ShoppingCouponModelImpl.ENTITY_CACHE_ENABLED,
				ShoppingCouponImpl.class, couponId, this);

		if (shoppingCoupon == null) {
			Session session = null;

			try {
				session = openSession();

				shoppingCoupon = (ShoppingCoupon)session.get(ShoppingCouponImpl.class,
						new Long(couponId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (shoppingCoupon != null) {
					cacheResult(shoppingCoupon);
				}

				closeSession(session);
			}
		}

		return shoppingCoupon;
	}

	public List<ShoppingCoupon> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<ShoppingCoupon> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	public List<ShoppingCoupon> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<ShoppingCoupon> list = (List<ShoppingCoupon>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GROUPID,
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

				query.append(_SQL_SELECT_SHOPPINGCOUPON_WHERE);

				query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(ShoppingCouponModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<ShoppingCoupon>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ShoppingCoupon>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_GROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ShoppingCoupon findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchCouponException, SystemException {
		List<ShoppingCoupon> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCouponException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ShoppingCoupon findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchCouponException, SystemException {
		int count = countByGroupId(groupId);

		List<ShoppingCoupon> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCouponException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ShoppingCoupon[] findByGroupId_PrevAndNext(long couponId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchCouponException, SystemException {
		ShoppingCoupon shoppingCoupon = findByPrimaryKey(couponId);

		Session session = null;

		try {
			session = openSession();

			ShoppingCoupon[] array = new ShoppingCouponImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, shoppingCoupon,
					groupId, orderByComparator, true);

			array[1] = shoppingCoupon;

			array[2] = getByGroupId_PrevAndNext(session, shoppingCoupon,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ShoppingCoupon getByGroupId_PrevAndNext(Session session,
		ShoppingCoupon shoppingCoupon, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SHOPPINGCOUPON_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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

			query.append(WHERE_LIMIT_2);
		}

		else {
			query.append(ShoppingCouponModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(shoppingCoupon);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ShoppingCoupon> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public ShoppingCoupon findByCode(String code)
		throws NoSuchCouponException, SystemException {
		ShoppingCoupon shoppingCoupon = fetchByCode(code);

		if (shoppingCoupon == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("code=");
			msg.append(code);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCouponException(msg.toString());
		}

		return shoppingCoupon;
	}

	public ShoppingCoupon fetchByCode(String code) throws SystemException {
		return fetchByCode(code, true);
	}

	public ShoppingCoupon fetchByCode(String code, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { code };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CODE,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_SHOPPINGCOUPON_WHERE);

				if (code == null) {
					query.append(_FINDER_COLUMN_CODE_CODE_1);
				}
				else {
					if (code.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_CODE_CODE_3);
					}
					else {
						query.append(_FINDER_COLUMN_CODE_CODE_2);
					}
				}

				query.append(ShoppingCouponModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (code != null) {
					qPos.add(code);
				}

				List<ShoppingCoupon> list = q.list();

				result = list;

				ShoppingCoupon shoppingCoupon = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
						finderArgs, list);
				}
				else {
					shoppingCoupon = list.get(0);

					cacheResult(shoppingCoupon);

					if ((shoppingCoupon.getCode() == null) ||
							!shoppingCoupon.getCode().equals(code)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
							finderArgs, shoppingCoupon);
					}
				}

				return shoppingCoupon;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
						finderArgs, new ArrayList<ShoppingCoupon>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (ShoppingCoupon)result;
			}
		}
	}

	public List<ShoppingCoupon> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<ShoppingCoupon> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<ShoppingCoupon> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<ShoppingCoupon> list = (List<ShoppingCoupon>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_SHOPPINGCOUPON);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_SHOPPINGCOUPON.concat(ShoppingCouponModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<ShoppingCoupon>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ShoppingCoupon>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ShoppingCoupon>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByGroupId(long groupId) throws SystemException {
		for (ShoppingCoupon shoppingCoupon : findByGroupId(groupId)) {
			remove(shoppingCoupon);
		}
	}

	public void removeByCode(String code)
		throws NoSuchCouponException, SystemException {
		ShoppingCoupon shoppingCoupon = findByCode(code);

		remove(shoppingCoupon);
	}

	public void removeAll() throws SystemException {
		for (ShoppingCoupon shoppingCoupon : findAll()) {
			remove(shoppingCoupon);
		}
	}

	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(groupId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_SHOPPINGCOUPON_WHERE);

				query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByCode(String code) throws SystemException {
		Object[] finderArgs = new Object[] { code };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CODE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_SHOPPINGCOUPON_WHERE);

				if (code == null) {
					query.append(_FINDER_COLUMN_CODE_CODE_1);
				}
				else {
					if (code.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_CODE_CODE_3);
					}
					else {
						query.append(_FINDER_COLUMN_CODE_CODE_2);
					}
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (code != null) {
					qPos.add(code);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CODE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SHOPPINGCOUPON);

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

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.shopping.model.ShoppingCoupon")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ShoppingCoupon>> listenersList = new ArrayList<ModelListener<ShoppingCoupon>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ShoppingCoupon>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = ShoppingCartPersistence.class)
	protected ShoppingCartPersistence shoppingCartPersistence;
	@BeanReference(type = ShoppingCategoryPersistence.class)
	protected ShoppingCategoryPersistence shoppingCategoryPersistence;
	@BeanReference(type = ShoppingCouponPersistence.class)
	protected ShoppingCouponPersistence shoppingCouponPersistence;
	@BeanReference(type = ShoppingItemPersistence.class)
	protected ShoppingItemPersistence shoppingItemPersistence;
	@BeanReference(type = ShoppingItemFieldPersistence.class)
	protected ShoppingItemFieldPersistence shoppingItemFieldPersistence;
	@BeanReference(type = ShoppingItemPricePersistence.class)
	protected ShoppingItemPricePersistence shoppingItemPricePersistence;
	@BeanReference(type = ShoppingOrderPersistence.class)
	protected ShoppingOrderPersistence shoppingOrderPersistence;
	@BeanReference(type = ShoppingOrderItemPersistence.class)
	protected ShoppingOrderItemPersistence shoppingOrderItemPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_SHOPPINGCOUPON = "SELECT shoppingCoupon FROM ShoppingCoupon shoppingCoupon";
	private static final String _SQL_SELECT_SHOPPINGCOUPON_WHERE = "SELECT shoppingCoupon FROM ShoppingCoupon shoppingCoupon WHERE ";
	private static final String _SQL_COUNT_SHOPPINGCOUPON = "SELECT COUNT(shoppingCoupon) FROM ShoppingCoupon shoppingCoupon";
	private static final String _SQL_COUNT_SHOPPINGCOUPON_WHERE = "SELECT COUNT(shoppingCoupon) FROM ShoppingCoupon shoppingCoupon WHERE ";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "shoppingCoupon.groupId = ?";
	private static final String _FINDER_COLUMN_CODE_CODE_1 = "shoppingCoupon.code IS NULL";
	private static final String _FINDER_COLUMN_CODE_CODE_2 = "shoppingCoupon.code = ?";
	private static final String _FINDER_COLUMN_CODE_CODE_3 = "(shoppingCoupon.code IS NULL OR shoppingCoupon.code = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "shoppingCoupon.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ShoppingCoupon exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ShoppingCoupon exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(ShoppingCouponPersistenceImpl.class);
}