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

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.asset.NoSuchVocabularyException;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.model.impl.AssetVocabularyImpl;
import com.liferay.portlet.asset.model.impl.AssetVocabularyModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="AssetVocabularyPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetVocabularyPersistence
 * @see       AssetVocabularyUtil
 * @generated
 */
public class AssetVocabularyPersistenceImpl extends BasePersistenceImpl<AssetVocabulary>
	implements AssetVocabularyPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = AssetVocabularyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_UUID = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_GROUPID = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_GROUPID = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYID = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_G_N = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_G_N = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByG_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(AssetVocabulary assetVocabulary) {
		EntityCacheUtil.putResult(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyImpl.class, assetVocabulary.getPrimaryKey(),
			assetVocabulary);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				assetVocabulary.getUuid(),
				new Long(assetVocabulary.getGroupId())
			}, assetVocabulary);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_N,
			new Object[] {
				new Long(assetVocabulary.getGroupId()),
				
			assetVocabulary.getName()
			}, assetVocabulary);
	}

	public void cacheResult(List<AssetVocabulary> assetVocabularies) {
		for (AssetVocabulary assetVocabulary : assetVocabularies) {
			if (EntityCacheUtil.getResult(
						AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
						AssetVocabularyImpl.class,
						assetVocabulary.getPrimaryKey(), this) == null) {
				cacheResult(assetVocabulary);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(AssetVocabularyImpl.class.getName());
		EntityCacheUtil.clearCache(AssetVocabularyImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public AssetVocabulary create(long vocabularyId) {
		AssetVocabulary assetVocabulary = new AssetVocabularyImpl();

		assetVocabulary.setNew(true);
		assetVocabulary.setPrimaryKey(vocabularyId);

		String uuid = PortalUUIDUtil.generate();

		assetVocabulary.setUuid(uuid);

		return assetVocabulary;
	}

	public AssetVocabulary remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public AssetVocabulary remove(long vocabularyId)
		throws NoSuchVocabularyException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetVocabulary assetVocabulary = (AssetVocabulary)session.get(AssetVocabularyImpl.class,
					new Long(vocabularyId));

			if (assetVocabulary == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + vocabularyId);
				}

				throw new NoSuchVocabularyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					vocabularyId);
			}

			return remove(assetVocabulary);
		}
		catch (NoSuchVocabularyException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public AssetVocabulary remove(AssetVocabulary assetVocabulary)
		throws SystemException {
		for (ModelListener<AssetVocabulary> listener : listeners) {
			listener.onBeforeRemove(assetVocabulary);
		}

		assetVocabulary = removeImpl(assetVocabulary);

		for (ModelListener<AssetVocabulary> listener : listeners) {
			listener.onAfterRemove(assetVocabulary);
		}

		return assetVocabulary;
	}

	protected AssetVocabulary removeImpl(AssetVocabulary assetVocabulary)
		throws SystemException {
		assetVocabulary = toUnwrappedModel(assetVocabulary);

		Session session = null;

		try {
			session = openSession();

			if (assetVocabulary.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(AssetVocabularyImpl.class,
						assetVocabulary.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(assetVocabulary);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		AssetVocabularyModelImpl assetVocabularyModelImpl = (AssetVocabularyModelImpl)assetVocabulary;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				assetVocabularyModelImpl.getOriginalUuid(),
				new Long(assetVocabularyModelImpl.getOriginalGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_N,
			new Object[] {
				new Long(assetVocabularyModelImpl.getOriginalGroupId()),
				
			assetVocabularyModelImpl.getOriginalName()
			});

		EntityCacheUtil.removeResult(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyImpl.class, assetVocabulary.getPrimaryKey());

		return assetVocabulary;
	}

	public AssetVocabulary updateImpl(
		com.liferay.portlet.asset.model.AssetVocabulary assetVocabulary,
		boolean merge) throws SystemException {
		assetVocabulary = toUnwrappedModel(assetVocabulary);

		boolean isNew = assetVocabulary.isNew();

		AssetVocabularyModelImpl assetVocabularyModelImpl = (AssetVocabularyModelImpl)assetVocabulary;

		if (Validator.isNull(assetVocabulary.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			assetVocabulary.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetVocabulary, merge);

			assetVocabulary.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
			AssetVocabularyImpl.class, assetVocabulary.getPrimaryKey(),
			assetVocabulary);

		if (!isNew &&
				(!Validator.equals(assetVocabulary.getUuid(),
					assetVocabularyModelImpl.getOriginalUuid()) ||
				(assetVocabulary.getGroupId() != assetVocabularyModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					assetVocabularyModelImpl.getOriginalUuid(),
					new Long(assetVocabularyModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!Validator.equals(assetVocabulary.getUuid(),
					assetVocabularyModelImpl.getOriginalUuid()) ||
				(assetVocabulary.getGroupId() != assetVocabularyModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					assetVocabulary.getUuid(),
					new Long(assetVocabulary.getGroupId())
				}, assetVocabulary);
		}

		if (!isNew &&
				((assetVocabulary.getGroupId() != assetVocabularyModelImpl.getOriginalGroupId()) ||
				!Validator.equals(assetVocabulary.getName(),
					assetVocabularyModelImpl.getOriginalName()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_N,
				new Object[] {
					new Long(assetVocabularyModelImpl.getOriginalGroupId()),
					
				assetVocabularyModelImpl.getOriginalName()
				});
		}

		if (isNew ||
				((assetVocabulary.getGroupId() != assetVocabularyModelImpl.getOriginalGroupId()) ||
				!Validator.equals(assetVocabulary.getName(),
					assetVocabularyModelImpl.getOriginalName()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_N,
				new Object[] {
					new Long(assetVocabulary.getGroupId()),
					
				assetVocabulary.getName()
				}, assetVocabulary);
		}

		return assetVocabulary;
	}

	protected AssetVocabulary toUnwrappedModel(AssetVocabulary assetVocabulary) {
		if (assetVocabulary instanceof AssetVocabularyImpl) {
			return assetVocabulary;
		}

		AssetVocabularyImpl assetVocabularyImpl = new AssetVocabularyImpl();

		assetVocabularyImpl.setNew(assetVocabulary.isNew());
		assetVocabularyImpl.setPrimaryKey(assetVocabulary.getPrimaryKey());

		assetVocabularyImpl.setUuid(assetVocabulary.getUuid());
		assetVocabularyImpl.setVocabularyId(assetVocabulary.getVocabularyId());
		assetVocabularyImpl.setGroupId(assetVocabulary.getGroupId());
		assetVocabularyImpl.setCompanyId(assetVocabulary.getCompanyId());
		assetVocabularyImpl.setUserId(assetVocabulary.getUserId());
		assetVocabularyImpl.setUserName(assetVocabulary.getUserName());
		assetVocabularyImpl.setCreateDate(assetVocabulary.getCreateDate());
		assetVocabularyImpl.setModifiedDate(assetVocabulary.getModifiedDate());
		assetVocabularyImpl.setName(assetVocabulary.getName());
		assetVocabularyImpl.setDescription(assetVocabulary.getDescription());

		return assetVocabularyImpl;
	}

	public AssetVocabulary findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public AssetVocabulary findByPrimaryKey(long vocabularyId)
		throws NoSuchVocabularyException, SystemException {
		AssetVocabulary assetVocabulary = fetchByPrimaryKey(vocabularyId);

		if (assetVocabulary == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + vocabularyId);
			}

			throw new NoSuchVocabularyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				vocabularyId);
		}

		return assetVocabulary;
	}

	public AssetVocabulary fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public AssetVocabulary fetchByPrimaryKey(long vocabularyId)
		throws SystemException {
		AssetVocabulary assetVocabulary = (AssetVocabulary)EntityCacheUtil.getResult(AssetVocabularyModelImpl.ENTITY_CACHE_ENABLED,
				AssetVocabularyImpl.class, vocabularyId, this);

		if (assetVocabulary == null) {
			Session session = null;

			try {
				session = openSession();

				assetVocabulary = (AssetVocabulary)session.get(AssetVocabularyImpl.class,
						new Long(vocabularyId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (assetVocabulary != null) {
					cacheResult(assetVocabulary);
				}

				closeSession(session);
			}
		}

		return assetVocabulary;
	}

	public List<AssetVocabulary> findByUuid(String uuid)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		List<AssetVocabulary> list = (List<AssetVocabulary>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_ASSETVOCABULARY_WHERE);

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

				query.append(AssetVocabularyModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AssetVocabulary>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<AssetVocabulary> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	public List<AssetVocabulary> findByUuid(String uuid, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<AssetVocabulary> list = (List<AssetVocabulary>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_UUID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (obc != null) {
					query = new StringBundler(3 +
							(obc.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_ASSETVOCABULARY_WHERE);

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

				if (obc != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
				}

				else {
					query.append(AssetVocabularyModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<AssetVocabulary>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AssetVocabulary>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_UUID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public AssetVocabulary findByUuid_First(String uuid, OrderByComparator obc)
		throws NoSuchVocabularyException, SystemException {
		List<AssetVocabulary> list = findByUuid(uuid, 0, 1, obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchVocabularyException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public AssetVocabulary findByUuid_Last(String uuid, OrderByComparator obc)
		throws NoSuchVocabularyException, SystemException {
		int count = countByUuid(uuid);

		List<AssetVocabulary> list = findByUuid(uuid, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchVocabularyException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public AssetVocabulary[] findByUuid_PrevAndNext(long vocabularyId,
		String uuid, OrderByComparator obc)
		throws NoSuchVocabularyException, SystemException {
		AssetVocabulary assetVocabulary = findByPrimaryKey(vocabularyId);

		int count = countByUuid(uuid);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (obc != null) {
				query = new StringBundler(3 +
						(obc.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASSETVOCABULARY_WHERE);

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

			if (obc != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
			}

			else {
				query.append(AssetVocabularyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			if (uuid != null) {
				qPos.add(uuid);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					assetVocabulary);

			AssetVocabulary[] array = new AssetVocabularyImpl[3];

			array[0] = (AssetVocabulary)objArray[0];
			array[1] = (AssetVocabulary)objArray[1];
			array[2] = (AssetVocabulary)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public AssetVocabulary findByUUID_G(String uuid, long groupId)
		throws NoSuchVocabularyException, SystemException {
		AssetVocabulary assetVocabulary = fetchByUUID_G(uuid, groupId);

		if (assetVocabulary == null) {
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

			throw new NoSuchVocabularyException(msg.toString());
		}

		return assetVocabulary;
	}

	public AssetVocabulary fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	public AssetVocabulary fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, new Long(groupId) };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(4);

				query.append(_SQL_SELECT_ASSETVOCABULARY_WHERE);

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

				query.append(AssetVocabularyModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<AssetVocabulary> list = q.list();

				result = list;

				AssetVocabulary assetVocabulary = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					assetVocabulary = list.get(0);

					cacheResult(assetVocabulary);

					if ((assetVocabulary.getUuid() == null) ||
							!assetVocabulary.getUuid().equals(uuid) ||
							(assetVocabulary.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, assetVocabulary);
					}
				}

				return assetVocabulary;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, new ArrayList<AssetVocabulary>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (AssetVocabulary)result;
			}
		}
	}

	public List<AssetVocabulary> findByGroupId(long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(groupId) };

		List<AssetVocabulary> list = (List<AssetVocabulary>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_ASSETVOCABULARY_WHERE);

				query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

				query.append(AssetVocabularyModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AssetVocabulary>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_GROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<AssetVocabulary> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	public List<AssetVocabulary> findByGroupId(long groupId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<AssetVocabulary> list = (List<AssetVocabulary>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_GROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (obc != null) {
					query = new StringBundler(3 +
							(obc.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_ASSETVOCABULARY_WHERE);

				query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

				if (obc != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
				}

				else {
					query.append(AssetVocabularyModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<AssetVocabulary>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AssetVocabulary>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_GROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public AssetVocabulary findByGroupId_First(long groupId,
		OrderByComparator obc)
		throws NoSuchVocabularyException, SystemException {
		List<AssetVocabulary> list = findByGroupId(groupId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchVocabularyException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public AssetVocabulary findByGroupId_Last(long groupId,
		OrderByComparator obc)
		throws NoSuchVocabularyException, SystemException {
		int count = countByGroupId(groupId);

		List<AssetVocabulary> list = findByGroupId(groupId, count - 1, count,
				obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchVocabularyException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public AssetVocabulary[] findByGroupId_PrevAndNext(long vocabularyId,
		long groupId, OrderByComparator obc)
		throws NoSuchVocabularyException, SystemException {
		AssetVocabulary assetVocabulary = findByPrimaryKey(vocabularyId);

		int count = countByGroupId(groupId);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (obc != null) {
				query = new StringBundler(3 +
						(obc.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASSETVOCABULARY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (obc != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
			}

			else {
				query.append(AssetVocabularyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					assetVocabulary);

			AssetVocabulary[] array = new AssetVocabularyImpl[3];

			array[0] = (AssetVocabulary)objArray[0];
			array[1] = (AssetVocabulary)objArray[1];
			array[2] = (AssetVocabulary)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<AssetVocabulary> findByCompanyId(long companyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		List<AssetVocabulary> list = (List<AssetVocabulary>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_ASSETVOCABULARY_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				query.append(AssetVocabularyModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AssetVocabulary>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<AssetVocabulary> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<AssetVocabulary> findByCompanyId(long companyId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<AssetVocabulary> list = (List<AssetVocabulary>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (obc != null) {
					query = new StringBundler(3 +
							(obc.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_ASSETVOCABULARY_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				if (obc != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
				}

				else {
					query.append(AssetVocabularyModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<AssetVocabulary>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AssetVocabulary>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public AssetVocabulary findByCompanyId_First(long companyId,
		OrderByComparator obc)
		throws NoSuchVocabularyException, SystemException {
		List<AssetVocabulary> list = findByCompanyId(companyId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchVocabularyException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public AssetVocabulary findByCompanyId_Last(long companyId,
		OrderByComparator obc)
		throws NoSuchVocabularyException, SystemException {
		int count = countByCompanyId(companyId);

		List<AssetVocabulary> list = findByCompanyId(companyId, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchVocabularyException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public AssetVocabulary[] findByCompanyId_PrevAndNext(long vocabularyId,
		long companyId, OrderByComparator obc)
		throws NoSuchVocabularyException, SystemException {
		AssetVocabulary assetVocabulary = findByPrimaryKey(vocabularyId);

		int count = countByCompanyId(companyId);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (obc != null) {
				query = new StringBundler(3 +
						(obc.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASSETVOCABULARY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (obc != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
			}

			else {
				query.append(AssetVocabularyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					assetVocabulary);

			AssetVocabulary[] array = new AssetVocabularyImpl[3];

			array[0] = (AssetVocabulary)objArray[0];
			array[1] = (AssetVocabulary)objArray[1];
			array[2] = (AssetVocabulary)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public AssetVocabulary findByG_N(long groupId, String name)
		throws NoSuchVocabularyException, SystemException {
		AssetVocabulary assetVocabulary = fetchByG_N(groupId, name);

		if (assetVocabulary == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchVocabularyException(msg.toString());
		}

		return assetVocabulary;
	}

	public AssetVocabulary fetchByG_N(long groupId, String name)
		throws SystemException {
		return fetchByG_N(groupId, name, true);
	}

	public AssetVocabulary fetchByG_N(long groupId, String name,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(groupId), name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_N,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(4);

				query.append(_SQL_SELECT_ASSETVOCABULARY_WHERE);

				query.append(_FINDER_COLUMN_G_N_GROUPID_2);

				if (name == null) {
					query.append(_FINDER_COLUMN_G_N_NAME_1);
				}
				else {
					if (name.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_N_NAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_G_N_NAME_2);
					}
				}

				query.append(AssetVocabularyModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (name != null) {
					qPos.add(name);
				}

				List<AssetVocabulary> list = q.list();

				result = list;

				AssetVocabulary assetVocabulary = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_N,
						finderArgs, list);
				}
				else {
					assetVocabulary = list.get(0);

					cacheResult(assetVocabulary);

					if ((assetVocabulary.getGroupId() != groupId) ||
							(assetVocabulary.getName() == null) ||
							!assetVocabulary.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_N,
							finderArgs, assetVocabulary);
					}
				}

				return assetVocabulary;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_N,
						finderArgs, new ArrayList<AssetVocabulary>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (AssetVocabulary)result;
			}
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.setLimit(start, end);

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<AssetVocabulary> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<AssetVocabulary> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<AssetVocabulary> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<AssetVocabulary> list = (List<AssetVocabulary>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (obc != null) {
					query = new StringBundler(2 +
							(obc.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_ASSETVOCABULARY);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_ASSETVOCABULARY.concat(AssetVocabularyModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (obc == null) {
					list = (List<AssetVocabulary>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetVocabulary>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AssetVocabulary>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByUuid(String uuid) throws SystemException {
		for (AssetVocabulary assetVocabulary : findByUuid(uuid)) {
			remove(assetVocabulary);
		}
	}

	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchVocabularyException, SystemException {
		AssetVocabulary assetVocabulary = findByUUID_G(uuid, groupId);

		remove(assetVocabulary);
	}

	public void removeByGroupId(long groupId) throws SystemException {
		for (AssetVocabulary assetVocabulary : findByGroupId(groupId)) {
			remove(assetVocabulary);
		}
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (AssetVocabulary assetVocabulary : findByCompanyId(companyId)) {
			remove(assetVocabulary);
		}
	}

	public void removeByG_N(long groupId, String name)
		throws NoSuchVocabularyException, SystemException {
		AssetVocabulary assetVocabulary = findByG_N(groupId, name);

		remove(assetVocabulary);
	}

	public void removeAll() throws SystemException {
		for (AssetVocabulary assetVocabulary : findAll()) {
			remove(assetVocabulary);
		}
	}

	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_ASSETVOCABULARY_WHERE);

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

	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, new Long(groupId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_ASSETVOCABULARY_WHERE);

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

	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(groupId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_ASSETVOCABULARY_WHERE);

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

	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_ASSETVOCABULARY_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				String sql = query.toString();

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

	public int countByG_N(long groupId, String name) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(groupId), name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_N,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_ASSETVOCABULARY_WHERE);

				query.append(_FINDER_COLUMN_G_N_GROUPID_2);

				if (name == null) {
					query.append(_FINDER_COLUMN_G_N_NAME_1);
				}
				else {
					if (name.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_N_NAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_G_N_NAME_2);
					}
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (name != null) {
					qPos.add(name);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_N, finderArgs,
					count);

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

				Query q = session.createQuery(_SQL_COUNT_ASSETVOCABULARY);

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
						"value.object.listener.com.liferay.portlet.asset.model.AssetVocabulary")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetVocabulary>> listenersList = new ArrayList<ModelListener<AssetVocabulary>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AssetVocabulary>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.portlet.asset.service.persistence.AssetCategoryPersistence")
	protected com.liferay.portlet.asset.service.persistence.AssetCategoryPersistence assetCategoryPersistence;
	@BeanReference(name = "com.liferay.portlet.asset.service.persistence.AssetCategoryPropertyPersistence")
	protected com.liferay.portlet.asset.service.persistence.AssetCategoryPropertyPersistence assetCategoryPropertyPersistence;
	@BeanReference(name = "com.liferay.portlet.asset.service.persistence.AssetEntryPersistence")
	protected com.liferay.portlet.asset.service.persistence.AssetEntryPersistence assetEntryPersistence;
	@BeanReference(name = "com.liferay.portlet.asset.service.persistence.AssetTagPersistence")
	protected com.liferay.portlet.asset.service.persistence.AssetTagPersistence assetTagPersistence;
	@BeanReference(name = "com.liferay.portlet.asset.service.persistence.AssetTagPropertyPersistence")
	protected com.liferay.portlet.asset.service.persistence.AssetTagPropertyPersistence assetTagPropertyPersistence;
	@BeanReference(name = "com.liferay.portlet.asset.service.persistence.AssetTagStatsPersistence")
	protected com.liferay.portlet.asset.service.persistence.AssetTagStatsPersistence assetTagStatsPersistence;
	@BeanReference(name = "com.liferay.portlet.asset.service.persistence.AssetVocabularyPersistence")
	protected com.liferay.portlet.asset.service.persistence.AssetVocabularyPersistence assetVocabularyPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.GroupPersistence")
	protected com.liferay.portal.service.persistence.GroupPersistence groupPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ResourcePersistence")
	protected com.liferay.portal.service.persistence.ResourcePersistence resourcePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence")
	protected com.liferay.portal.service.persistence.UserPersistence userPersistence;
	private static final String _SQL_SELECT_ASSETVOCABULARY = "SELECT assetVocabulary FROM AssetVocabulary assetVocabulary";
	private static final String _SQL_SELECT_ASSETVOCABULARY_WHERE = "SELECT assetVocabulary FROM AssetVocabulary assetVocabulary WHERE ";
	private static final String _SQL_COUNT_ASSETVOCABULARY = "SELECT COUNT(assetVocabulary) FROM AssetVocabulary assetVocabulary";
	private static final String _SQL_COUNT_ASSETVOCABULARY_WHERE = "SELECT COUNT(assetVocabulary) FROM AssetVocabulary assetVocabulary WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "assetVocabulary.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "assetVocabulary.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(assetVocabulary.uuid IS NULL OR assetVocabulary.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "assetVocabulary.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "assetVocabulary.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(assetVocabulary.uuid IS NULL OR assetVocabulary.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "assetVocabulary.groupId = ?";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "assetVocabulary.groupId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "assetVocabulary.companyId = ?";
	private static final String _FINDER_COLUMN_G_N_GROUPID_2 = "assetVocabulary.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_N_NAME_1 = "assetVocabulary.name IS NULL";
	private static final String _FINDER_COLUMN_G_N_NAME_2 = "assetVocabulary.name = ?";
	private static final String _FINDER_COLUMN_G_N_NAME_3 = "(assetVocabulary.name IS NULL OR assetVocabulary.name = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetVocabulary.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetVocabulary exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetVocabulary exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(AssetVocabularyPersistenceImpl.class);
}