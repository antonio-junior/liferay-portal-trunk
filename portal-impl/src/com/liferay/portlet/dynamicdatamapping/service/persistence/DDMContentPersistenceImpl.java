/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.dynamicdatamapping.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.dynamicdatamapping.NoSuchContentException;
import com.liferay.portlet.dynamicdatamapping.model.DDMContent;
import com.liferay.portlet.dynamicdatamapping.model.impl.DDMContentImpl;
import com.liferay.portlet.dynamicdatamapping.model.impl.DDMContentModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the d d m content service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMContentPersistence
 * @see DDMContentUtil
 * @generated
 */
public class DDMContentPersistenceImpl extends BasePersistenceImpl<DDMContent>
	implements DDMContentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DDMContentUtil} to access the d d m content persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DDMContentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUuid", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_GROUPID = new FinderPath(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByGroupId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the d d m content in the entity cache if it is enabled.
	 *
	 * @param ddmContent the d d m content to cache
	 */
	public void cacheResult(DDMContent ddmContent) {
		EntityCacheUtil.putResult(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentImpl.class, ddmContent.getPrimaryKey(), ddmContent);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				ddmContent.getUuid(), Long.valueOf(ddmContent.getGroupId())
			}, ddmContent);

		ddmContent.resetOriginalValues();
	}

	/**
	 * Caches the d d m contents in the entity cache if it is enabled.
	 *
	 * @param ddmContents the d d m contents to cache
	 */
	public void cacheResult(List<DDMContent> ddmContents) {
		for (DDMContent ddmContent : ddmContents) {
			if (EntityCacheUtil.getResult(
						DDMContentModelImpl.ENTITY_CACHE_ENABLED,
						DDMContentImpl.class, ddmContent.getPrimaryKey(), this) == null) {
				cacheResult(ddmContent);
			}
		}
	}

	/**
	 * Clears the cache for all d d m contents.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DDMContentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DDMContentImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the d d m content.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(DDMContent ddmContent) {
		EntityCacheUtil.removeResult(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentImpl.class, ddmContent.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				ddmContent.getUuid(), Long.valueOf(ddmContent.getGroupId())
			});
	}

	/**
	 * Creates a new d d m content with the primary key. Does not add the d d m content to the database.
	 *
	 * @param contentId the primary key for the new d d m content
	 * @return the new d d m content
	 */
	public DDMContent create(long contentId) {
		DDMContent ddmContent = new DDMContentImpl();

		ddmContent.setNew(true);
		ddmContent.setPrimaryKey(contentId);

		String uuid = PortalUUIDUtil.generate();

		ddmContent.setUuid(uuid);

		return ddmContent;
	}

	/**
	 * Removes the d d m content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the d d m content to remove
	 * @return the d d m content that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a d d m content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the d d m content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contentId the primary key of the d d m content to remove
	 * @return the d d m content that was removed
	 * @throws com.liferay.portlet.dynamicdatamapping.NoSuchContentException if a d d m content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent remove(long contentId)
		throws NoSuchContentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DDMContent ddmContent = (DDMContent)session.get(DDMContentImpl.class,
					Long.valueOf(contentId));

			if (ddmContent == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + contentId);
				}

				throw new NoSuchContentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					contentId);
			}

			return ddmContentPersistence.remove(ddmContent);
		}
		catch (NoSuchContentException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Removes the d d m content from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmContent the d d m content to remove
	 * @return the d d m content that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent remove(DDMContent ddmContent) throws SystemException {
		return super.remove(ddmContent);
	}

	protected DDMContent removeImpl(DDMContent ddmContent)
		throws SystemException {
		ddmContent = toUnwrappedModel(ddmContent);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, ddmContent);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		DDMContentModelImpl ddmContentModelImpl = (DDMContentModelImpl)ddmContent;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				ddmContentModelImpl.getUuid(),
				Long.valueOf(ddmContentModelImpl.getGroupId())
			});

		EntityCacheUtil.removeResult(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentImpl.class, ddmContent.getPrimaryKey());

		return ddmContent;
	}

	public DDMContent updateImpl(
		com.liferay.portlet.dynamicdatamapping.model.DDMContent ddmContent,
		boolean merge) throws SystemException {
		ddmContent = toUnwrappedModel(ddmContent);

		boolean isNew = ddmContent.isNew();

		DDMContentModelImpl ddmContentModelImpl = (DDMContentModelImpl)ddmContent;

		if (Validator.isNull(ddmContent.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			ddmContent.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, ddmContent, merge);

			ddmContent.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
			DDMContentImpl.class, ddmContent.getPrimaryKey(), ddmContent);

		if (!isNew &&
				(!Validator.equals(ddmContent.getUuid(),
					ddmContentModelImpl.getOriginalUuid()) ||
				(ddmContent.getGroupId() != ddmContentModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					ddmContentModelImpl.getOriginalUuid(),
					Long.valueOf(ddmContentModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!Validator.equals(ddmContent.getUuid(),
					ddmContentModelImpl.getOriginalUuid()) ||
				(ddmContent.getGroupId() != ddmContentModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					ddmContent.getUuid(), Long.valueOf(ddmContent.getGroupId())
				}, ddmContent);
		}

		return ddmContent;
	}

	protected DDMContent toUnwrappedModel(DDMContent ddmContent) {
		if (ddmContent instanceof DDMContentImpl) {
			return ddmContent;
		}

		DDMContentImpl ddmContentImpl = new DDMContentImpl();

		ddmContentImpl.setNew(ddmContent.isNew());
		ddmContentImpl.setPrimaryKey(ddmContent.getPrimaryKey());

		ddmContentImpl.setUuid(ddmContent.getUuid());
		ddmContentImpl.setContentId(ddmContent.getContentId());
		ddmContentImpl.setGroupId(ddmContent.getGroupId());
		ddmContentImpl.setCompanyId(ddmContent.getCompanyId());
		ddmContentImpl.setUserId(ddmContent.getUserId());
		ddmContentImpl.setUserName(ddmContent.getUserName());
		ddmContentImpl.setCreateDate(ddmContent.getCreateDate());
		ddmContentImpl.setModifiedDate(ddmContent.getModifiedDate());
		ddmContentImpl.setName(ddmContent.getName());
		ddmContentImpl.setDescription(ddmContent.getDescription());
		ddmContentImpl.setXml(ddmContent.getXml());

		return ddmContentImpl;
	}

	/**
	 * Finds the d d m content with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the d d m content to find
	 * @return the d d m content
	 * @throws com.liferay.portal.NoSuchModelException if a d d m content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the d d m content with the primary key or throws a {@link com.liferay.portlet.dynamicdatamapping.NoSuchContentException} if it could not be found.
	 *
	 * @param contentId the primary key of the d d m content to find
	 * @return the d d m content
	 * @throws com.liferay.portlet.dynamicdatamapping.NoSuchContentException if a d d m content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent findByPrimaryKey(long contentId)
		throws NoSuchContentException, SystemException {
		DDMContent ddmContent = fetchByPrimaryKey(contentId);

		if (ddmContent == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + contentId);
			}

			throw new NoSuchContentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				contentId);
		}

		return ddmContent;
	}

	/**
	 * Finds the d d m content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the d d m content to find
	 * @return the d d m content, or <code>null</code> if a d d m content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the d d m content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contentId the primary key of the d d m content to find
	 * @return the d d m content, or <code>null</code> if a d d m content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent fetchByPrimaryKey(long contentId)
		throws SystemException {
		DDMContent ddmContent = (DDMContent)EntityCacheUtil.getResult(DDMContentModelImpl.ENTITY_CACHE_ENABLED,
				DDMContentImpl.class, contentId, this);

		if (ddmContent == null) {
			Session session = null;

			try {
				session = openSession();

				ddmContent = (DDMContent)session.get(DDMContentImpl.class,
						Long.valueOf(contentId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (ddmContent != null) {
					cacheResult(ddmContent);
				}

				closeSession(session);
			}
		}

		return ddmContent;
	}

	/**
	 * Finds all the d d m contents where uuid = &#63;.
	 *
	 * @param uuid the uuid to search with
	 * @return the matching d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<DDMContent> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the d d m contents where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param start the lower bound of the range of d d m contents to return
	 * @param end the upper bound of the range of d d m contents to return (not inclusive)
	 * @return the range of matching d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<DDMContent> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Finds an ordered range of all the d d m contents where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param start the lower bound of the range of d d m contents to return
	 * @param end the upper bound of the range of d d m contents to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<DDMContent> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<DDMContent> list = (List<DDMContent>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_DDMCONTENT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<DDMContent>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first d d m content in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d m content
	 * @throws com.liferay.portlet.dynamicdatamapping.NoSuchContentException if a matching d d m content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		List<DDMContent> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchContentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last d d m content in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d m content
	 * @throws com.liferay.portlet.dynamicdatamapping.NoSuchContentException if a matching d d m content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		int count = countByUuid(uuid);

		List<DDMContent> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchContentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the d d m contents before and after the current d d m content in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param contentId the primary key of the current d d m content
	 * @param uuid the uuid to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next d d m content
	 * @throws com.liferay.portlet.dynamicdatamapping.NoSuchContentException if a d d m content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent[] findByUuid_PrevAndNext(long contentId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		DDMContent ddmContent = findByPrimaryKey(contentId);

		Session session = null;

		try {
			session = openSession();

			DDMContent[] array = new DDMContentImpl[3];

			array[0] = getByUuid_PrevAndNext(session, ddmContent, uuid,
					orderByComparator, true);

			array[1] = ddmContent;

			array[2] = getByUuid_PrevAndNext(session, ddmContent, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDMContent getByUuid_PrevAndNext(Session session,
		DDMContent ddmContent, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DDMCONTENT_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(ddmContent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DDMContent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds the d d m content where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.portlet.dynamicdatamapping.NoSuchContentException} if it could not be found.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group ID to search with
	 * @return the matching d d m content
	 * @throws com.liferay.portlet.dynamicdatamapping.NoSuchContentException if a matching d d m content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent findByUUID_G(String uuid, long groupId)
		throws NoSuchContentException, SystemException {
		DDMContent ddmContent = fetchByUUID_G(uuid, groupId);

		if (ddmContent == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchContentException(msg.toString());
		}

		return ddmContent;
	}

	/**
	 * Finds the d d m content where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group ID to search with
	 * @return the matching d d m content, or <code>null</code> if a matching d d m content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Finds the d d m content where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group ID to search with
	 * @return the matching d d m content, or <code>null</code> if a matching d d m content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DDMCONTENT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<DDMContent> list = q.list();

				result = list;

				DDMContent ddmContent = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ddmContent = list.get(0);

					cacheResult(ddmContent);

					if ((ddmContent.getUuid() == null) ||
							!ddmContent.getUuid().equals(uuid) ||
							(ddmContent.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, ddmContent);
					}
				}

				return ddmContent;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (DDMContent)result;
			}
		}
	}

	/**
	 * Finds all the d d m contents where groupId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @return the matching d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<DDMContent> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the d d m contents where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param start the lower bound of the range of d d m contents to return
	 * @param end the upper bound of the range of d d m contents to return (not inclusive)
	 * @return the range of matching d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<DDMContent> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the d d m contents where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param start the lower bound of the range of d d m contents to return
	 * @param end the upper bound of the range of d d m contents to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<DDMContent> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<DDMContent> list = (List<DDMContent>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GROUPID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_DDMCONTENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<DDMContent>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_GROUPID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_GROUPID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first d d m content in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d m content
	 * @throws com.liferay.portlet.dynamicdatamapping.NoSuchContentException if a matching d d m content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		List<DDMContent> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchContentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last d d m content in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d m content
	 * @throws com.liferay.portlet.dynamicdatamapping.NoSuchContentException if a matching d d m content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		int count = countByGroupId(groupId);

		List<DDMContent> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchContentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the d d m contents before and after the current d d m content in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param contentId the primary key of the current d d m content
	 * @param groupId the group ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next d d m content
	 * @throws com.liferay.portlet.dynamicdatamapping.NoSuchContentException if a d d m content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent[] findByGroupId_PrevAndNext(long contentId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		DDMContent ddmContent = findByPrimaryKey(contentId);

		Session session = null;

		try {
			session = openSession();

			DDMContent[] array = new DDMContentImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, ddmContent, groupId,
					orderByComparator, true);

			array[1] = ddmContent;

			array[2] = getByGroupId_PrevAndNext(session, ddmContent, groupId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDMContent getByGroupId_PrevAndNext(Session session,
		DDMContent ddmContent, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DDMCONTENT_WHERE);

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
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(ddmContent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DDMContent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the d d m contents where companyId = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @return the matching d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<DDMContent> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the d d m contents where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param start the lower bound of the range of d d m contents to return
	 * @param end the upper bound of the range of d d m contents to return (not inclusive)
	 * @return the range of matching d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<DDMContent> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the d d m contents where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param start the lower bound of the range of d d m contents to return
	 * @param end the upper bound of the range of d d m contents to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<DDMContent> findByCompanyId(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<DDMContent> list = (List<DDMContent>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_DDMCONTENT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<DDMContent>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first d d m content in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching d d m content
	 * @throws com.liferay.portlet.dynamicdatamapping.NoSuchContentException if a matching d d m content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		List<DDMContent> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchContentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last d d m content in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching d d m content
	 * @throws com.liferay.portlet.dynamicdatamapping.NoSuchContentException if a matching d d m content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		int count = countByCompanyId(companyId);

		List<DDMContent> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchContentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the d d m contents before and after the current d d m content in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param contentId the primary key of the current d d m content
	 * @param companyId the company ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next d d m content
	 * @throws com.liferay.portlet.dynamicdatamapping.NoSuchContentException if a d d m content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DDMContent[] findByCompanyId_PrevAndNext(long contentId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		DDMContent ddmContent = findByPrimaryKey(contentId);

		Session session = null;

		try {
			session = openSession();

			DDMContent[] array = new DDMContentImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, ddmContent,
					companyId, orderByComparator, true);

			array[1] = ddmContent;

			array[2] = getByCompanyId_PrevAndNext(session, ddmContent,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DDMContent getByCompanyId_PrevAndNext(Session session,
		DDMContent ddmContent, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DDMCONTENT_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(ddmContent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DDMContent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the d d m contents.
	 *
	 * @return the d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<DDMContent> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the d d m contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d m contents to return
	 * @param end the upper bound of the range of d d m contents to return (not inclusive)
	 * @return the range of d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<DDMContent> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the d d m contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d m contents to return
	 * @param end the upper bound of the range of d d m contents to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<DDMContent> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<DDMContent> list = (List<DDMContent>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DDMCONTENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DDMCONTENT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<DDMContent>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<DDMContent>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the d d m contents where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (DDMContent ddmContent : findByUuid(uuid)) {
			ddmContentPersistence.remove(ddmContent);
		}
	}

	/**
	 * Removes the d d m content where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchContentException, SystemException {
		DDMContent ddmContent = findByUUID_G(uuid, groupId);

		ddmContentPersistence.remove(ddmContent);
	}

	/**
	 * Removes all the d d m contents where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGroupId(long groupId) throws SystemException {
		for (DDMContent ddmContent : findByGroupId(groupId)) {
			ddmContentPersistence.remove(ddmContent);
		}
	}

	/**
	 * Removes all the d d m contents where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (DDMContent ddmContent : findByCompanyId(companyId)) {
			ddmContentPersistence.remove(ddmContent);
		}
	}

	/**
	 * Removes all the d d m contents from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DDMContent ddmContent : findAll()) {
			ddmContentPersistence.remove(ddmContent);
		}
	}

	/**
	 * Counts all the d d m contents where uuid = &#63;.
	 *
	 * @param uuid the uuid to search with
	 * @return the number of matching d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DDMCONTENT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the d d m contents where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid to search with
	 * @param groupId the group ID to search with
	 * @return the number of matching d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DDMCONTENT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the d d m contents where groupId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @return the number of matching d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DDMCONTENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

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

	/**
	 * Counts all the d d m contents where companyId = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @return the number of matching d d m contents
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DDMCONTENT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the d d m contents.
	 *
	 * @return the number of d d m contents
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

				Query q = session.createQuery(_SQL_COUNT_DDMCONTENT);

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
	 * Initializes the d d m content persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.dynamicdatamapping.model.DDMContent")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DDMContent>> listenersList = new ArrayList<ModelListener<DDMContent>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<DDMContent>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(DDMContentImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = DDMContentPersistence.class)
	protected DDMContentPersistence ddmContentPersistence;
	@BeanReference(type = DDMListPersistence.class)
	protected DDMListPersistence ddmListPersistence;
	@BeanReference(type = DDMStorageLinkPersistence.class)
	protected DDMStorageLinkPersistence ddmStorageLinkPersistence;
	@BeanReference(type = DDMStructurePersistence.class)
	protected DDMStructurePersistence ddmStructurePersistence;
	@BeanReference(type = DDMStructureLinkPersistence.class)
	protected DDMStructureLinkPersistence ddmStructureLinkPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_DDMCONTENT = "SELECT ddmContent FROM DDMContent ddmContent";
	private static final String _SQL_SELECT_DDMCONTENT_WHERE = "SELECT ddmContent FROM DDMContent ddmContent WHERE ";
	private static final String _SQL_COUNT_DDMCONTENT = "SELECT COUNT(ddmContent) FROM DDMContent ddmContent";
	private static final String _SQL_COUNT_DDMCONTENT_WHERE = "SELECT COUNT(ddmContent) FROM DDMContent ddmContent WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "ddmContent.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "ddmContent.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(ddmContent.uuid IS NULL OR ddmContent.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "ddmContent.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "ddmContent.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(ddmContent.uuid IS NULL OR ddmContent.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "ddmContent.groupId = ?";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "ddmContent.groupId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "ddmContent.companyId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ddmContent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DDMContent exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DDMContent exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(DDMContentPersistenceImpl.class);
}