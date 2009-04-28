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

package com.liferay.portlet.tags.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.tags.NoSuchAssetException;
import com.liferay.portlet.tags.model.TagsAsset;
import com.liferay.portlet.tags.model.impl.TagsAssetImpl;
import com.liferay.portlet.tags.model.impl.TagsAssetModelImpl;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="TagsAssetPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TagsAssetPersistenceImpl extends BasePersistenceImpl
	implements TagsAssetPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = TagsAssetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(TagsAssetModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYID = new FinderPath(TagsAssetModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(TagsAssetModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C = new FinderPath(TagsAssetModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(TagsAssetModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(TagsAssetModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TagsAssetModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(TagsAsset tagsAsset) {
		EntityCacheUtil.putResult(TagsAssetModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetImpl.class, tagsAsset.getPrimaryKey(), tagsAsset);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] {
				new Long(tagsAsset.getClassNameId()),
				new Long(tagsAsset.getClassPK())
			}, tagsAsset);
	}

	public void cacheResult(List<TagsAsset> tagsAssets) {
		for (TagsAsset tagsAsset : tagsAssets) {
			if (EntityCacheUtil.getResult(
						TagsAssetModelImpl.ENTITY_CACHE_ENABLED,
						TagsAssetImpl.class, tagsAsset.getPrimaryKey(), this) == null) {
				cacheResult(tagsAsset);
			}
		}
	}

	public TagsAsset create(long assetId) {
		TagsAsset tagsAsset = new TagsAssetImpl();

		tagsAsset.setNew(true);
		tagsAsset.setPrimaryKey(assetId);

		return tagsAsset;
	}

	public TagsAsset remove(long assetId)
		throws NoSuchAssetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TagsAsset tagsAsset = (TagsAsset)session.get(TagsAssetImpl.class,
					new Long(assetId));

			if (tagsAsset == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No TagsAsset exists with the primary key " +
						assetId);
				}

				throw new NoSuchAssetException(
					"No TagsAsset exists with the primary key " + assetId);
			}

			return remove(tagsAsset);
		}
		catch (NoSuchAssetException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public TagsAsset remove(TagsAsset tagsAsset) throws SystemException {
		for (ModelListener<TagsAsset> listener : listeners) {
			listener.onBeforeRemove(tagsAsset);
		}

		tagsAsset = removeImpl(tagsAsset);

		for (ModelListener<TagsAsset> listener : listeners) {
			listener.onAfterRemove(tagsAsset);
		}

		return tagsAsset;
	}

	protected TagsAsset removeImpl(TagsAsset tagsAsset)
		throws SystemException {
		try {
			clearTagsEntries.clear(tagsAsset.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_TagsEntries");
		}

		try {
			clearCategoriesEntries.clear(tagsAsset.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_CategoriesEntries");
		}

		Session session = null;

		try {
			session = openSession();

			if (tagsAsset.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(TagsAssetImpl.class,
						tagsAsset.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(tagsAsset);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		TagsAssetModelImpl tagsAssetModelImpl = (TagsAssetModelImpl)tagsAsset;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] {
				new Long(tagsAssetModelImpl.getOriginalClassNameId()),
				new Long(tagsAssetModelImpl.getOriginalClassPK())
			});

		EntityCacheUtil.removeResult(TagsAssetModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetImpl.class, tagsAsset.getPrimaryKey());

		return tagsAsset;
	}

	/**
	 * @deprecated Use <code>update(TagsAsset tagsAsset, boolean merge)</code>.
	 */
	public TagsAsset update(TagsAsset tagsAsset) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(TagsAsset tagsAsset) method. Use update(TagsAsset tagsAsset, boolean merge) instead.");
		}

		return update(tagsAsset, false);
	}

	/**
	 * Add, update, or merge, the entity. This method also calls the model
	 * listeners to trigger the proper events associated with adding, deleting,
	 * or updating an entity.
	 *
	 * @param        tagsAsset the entity to add, update, or merge
	 * @param        merge boolean value for whether to merge the entity. The
	 *                default value is false. Setting merge to true is more
	 *                expensive and should only be true when tagsAsset is
	 *                transient. See LEP-5473 for a detailed discussion of this
	 *                method.
	 * @return        true if the portlet can be displayed via Ajax
	 */
	public TagsAsset update(TagsAsset tagsAsset, boolean merge)
		throws SystemException {
		boolean isNew = tagsAsset.isNew();

		for (ModelListener<TagsAsset> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(tagsAsset);
			}
			else {
				listener.onBeforeUpdate(tagsAsset);
			}
		}

		tagsAsset = updateImpl(tagsAsset, merge);

		for (ModelListener<TagsAsset> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(tagsAsset);
			}
			else {
				listener.onAfterUpdate(tagsAsset);
			}
		}

		return tagsAsset;
	}

	public TagsAsset updateImpl(
		com.liferay.portlet.tags.model.TagsAsset tagsAsset, boolean merge)
		throws SystemException {
		boolean isNew = tagsAsset.isNew();

		TagsAssetModelImpl tagsAssetModelImpl = (TagsAssetModelImpl)tagsAsset;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, tagsAsset, merge);

			tagsAsset.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(TagsAssetModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetImpl.class, tagsAsset.getPrimaryKey(), tagsAsset);

		if (!isNew &&
				((tagsAsset.getClassNameId() != tagsAssetModelImpl.getOriginalClassNameId()) ||
				(tagsAsset.getClassPK() != tagsAssetModelImpl.getOriginalClassPK()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
				new Object[] {
					new Long(tagsAssetModelImpl.getOriginalClassNameId()),
					new Long(tagsAssetModelImpl.getOriginalClassPK())
				});
		}

		if (isNew ||
				((tagsAsset.getClassNameId() != tagsAssetModelImpl.getOriginalClassNameId()) ||
				(tagsAsset.getClassPK() != tagsAssetModelImpl.getOriginalClassPK()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
				new Object[] {
					new Long(tagsAsset.getClassNameId()),
					new Long(tagsAsset.getClassPK())
				}, tagsAsset);
		}

		return tagsAsset;
	}

	public TagsAsset findByPrimaryKey(long assetId)
		throws NoSuchAssetException, SystemException {
		TagsAsset tagsAsset = fetchByPrimaryKey(assetId);

		if (tagsAsset == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No TagsAsset exists with the primary key " +
					assetId);
			}

			throw new NoSuchAssetException(
				"No TagsAsset exists with the primary key " + assetId);
		}

		return tagsAsset;
	}

	public TagsAsset fetchByPrimaryKey(long assetId) throws SystemException {
		TagsAsset tagsAsset = (TagsAsset)EntityCacheUtil.getResult(TagsAssetModelImpl.ENTITY_CACHE_ENABLED,
				TagsAssetImpl.class, assetId, this);

		if (tagsAsset == null) {
			Session session = null;

			try {
				session = openSession();

				tagsAsset = (TagsAsset)session.get(TagsAssetImpl.class,
						new Long(assetId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (tagsAsset != null) {
					cacheResult(tagsAsset);
				}

				closeSession(session);
			}
		}

		return tagsAsset;
	}

	public List<TagsAsset> findByCompanyId(long companyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		List<TagsAsset> list = (List<TagsAsset>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.portlet.tags.model.TagsAsset WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<TagsAsset>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<TagsAsset> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<TagsAsset> findByCompanyId(long companyId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<TagsAsset> list = (List<TagsAsset>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.portlet.tags.model.TagsAsset WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<TagsAsset>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<TagsAsset>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public TagsAsset findByCompanyId_First(long companyId, OrderByComparator obc)
		throws NoSuchAssetException, SystemException {
		List<TagsAsset> list = findByCompanyId(companyId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TagsAsset exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAssetException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TagsAsset findByCompanyId_Last(long companyId, OrderByComparator obc)
		throws NoSuchAssetException, SystemException {
		int count = countByCompanyId(companyId);

		List<TagsAsset> list = findByCompanyId(companyId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TagsAsset exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAssetException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TagsAsset[] findByCompanyId_PrevAndNext(long assetId,
		long companyId, OrderByComparator obc)
		throws NoSuchAssetException, SystemException {
		TagsAsset tagsAsset = findByPrimaryKey(assetId);

		int count = countByCompanyId(companyId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.portlet.tags.model.TagsAsset WHERE ");

			query.append("companyId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					tagsAsset);

			TagsAsset[] array = new TagsAssetImpl[3];

			array[0] = (TagsAsset)objArray[0];
			array[1] = (TagsAsset)objArray[1];
			array[2] = (TagsAsset)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public TagsAsset findByC_C(long classNameId, long classPK)
		throws NoSuchAssetException, SystemException {
		TagsAsset tagsAsset = fetchByC_C(classNameId, classPK);

		if (tagsAsset == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TagsAsset exists with the key {");

			msg.append("classNameId=" + classNameId);

			msg.append(", ");
			msg.append("classPK=" + classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAssetException(msg.toString());
		}

		return tagsAsset;
	}

	public TagsAsset fetchByC_C(long classNameId, long classPK)
		throws SystemException {
		return fetchByC_C(classNameId, classPK, true);
	}

	public TagsAsset fetchByC_C(long classNameId, long classPK,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(classNameId), new Long(classPK)
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_C,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.portlet.tags.model.TagsAsset WHERE ");

				query.append("classNameId = ?");

				query.append(" AND ");

				query.append("classPK = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				List<TagsAsset> list = q.list();

				result = list;

				TagsAsset tagsAsset = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
						finderArgs, list);
				}
				else {
					tagsAsset = list.get(0);

					cacheResult(tagsAsset);

					if ((tagsAsset.getClassNameId() != classNameId) ||
							(tagsAsset.getClassPK() != classPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
							finderArgs, list);
					}
				}

				return tagsAsset;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
						finderArgs, new ArrayList<TagsAsset>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List) {
				return null;
			}
			else {
				return (TagsAsset)result;
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

	public List<TagsAsset> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<TagsAsset> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<TagsAsset> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<TagsAsset> list = (List<TagsAsset>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.portlet.tags.model.TagsAsset ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<TagsAsset>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TagsAsset>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<TagsAsset>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (TagsAsset tagsAsset : findByCompanyId(companyId)) {
			remove(tagsAsset);
		}
	}

	public void removeByC_C(long classNameId, long classPK)
		throws NoSuchAssetException, SystemException {
		TagsAsset tagsAsset = findByC_C(classNameId, classPK);

		remove(tagsAsset);
	}

	public void removeAll() throws SystemException {
		for (TagsAsset tagsAsset : findAll()) {
			remove(tagsAsset);
		}
	}

	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.portlet.tags.model.TagsAsset WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

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

	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(classNameId), new Long(classPK)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.portlet.tags.model.TagsAsset WHERE ");

				query.append("classNameId = ?");

				query.append(" AND ");

				query.append("classPK = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, finderArgs,
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

				Query q = session.createQuery(
						"SELECT COUNT(*) FROM com.liferay.portlet.tags.model.TagsAsset");

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

	public List<com.liferay.portlet.tags.model.TagsEntry> getTagsEntries(
		long pk) throws SystemException {
		return getTagsEntries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<com.liferay.portlet.tags.model.TagsEntry> getTagsEntries(
		long pk, int start, int end) throws SystemException {
		return getTagsEntries(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_TAGSENTRIES = new FinderPath(com.liferay.portlet.tags.model.impl.TagsEntryModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetModelImpl.FINDER_CACHE_ENABLED_TAGSASSETS_TAGSENTRIES,
			"TagsAssets_TagsEntries", "getTagsEntries",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	public List<com.liferay.portlet.tags.model.TagsEntry> getTagsEntries(
		long pk, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(pk), String.valueOf(start), String.valueOf(end),
				String.valueOf(obc)
			};

		List<com.liferay.portlet.tags.model.TagsEntry> list = (List<com.liferay.portlet.tags.model.TagsEntry>)FinderCacheUtil.getResult(FINDER_PATH_GET_TAGSENTRIES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder sb = new StringBuilder();

				sb.append(_SQL_GETTAGSENTRIES);

				if (obc != null) {
					sb.append("ORDER BY ");
					sb.append(obc.getOrderBy());
				}

				else {
					sb.append("ORDER BY ");

					sb.append("TagsEntry.name ASC");
				}

				String sql = sb.toString();

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("TagsEntry",
					com.liferay.portlet.tags.model.impl.TagsEntryImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.portlet.tags.model.TagsEntry>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<com.liferay.portlet.tags.model.TagsEntry>();
				}

				tagsEntryPersistence.cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_GET_TAGSENTRIES,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_TAGSENTRIES_SIZE = new FinderPath(com.liferay.portlet.tags.model.impl.TagsEntryModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetModelImpl.FINDER_CACHE_ENABLED_TAGSASSETS_TAGSENTRIES,
			"TagsAssets_TagsEntries", "getTagsEntriesSize",
			new String[] { Long.class.getName() });

	public int getTagsEntriesSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(pk) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_TAGSENTRIES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETTAGSENTRIESSIZE);

				q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_TAGSENTRIES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_TAGSENTRY = new FinderPath(com.liferay.portlet.tags.model.impl.TagsEntryModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetModelImpl.FINDER_CACHE_ENABLED_TAGSASSETS_TAGSENTRIES,
			"TagsAssets_TagsEntries", "containsTagsEntry",
			new String[] { Long.class.getName(), Long.class.getName() });

	public boolean containsTagsEntry(long pk, long tagsEntryPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(pk), new Long(tagsEntryPK) };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_TAGSENTRY,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsTagsEntry.contains(pk,
							tagsEntryPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_TAGSENTRY,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	public boolean containsTagsEntries(long pk) throws SystemException {
		if (getTagsEntriesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void addTagsEntry(long pk, long tagsEntryPK)
		throws SystemException {
		try {
			addTagsEntry.add(pk, tagsEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_TagsEntries");
		}
	}

	public void addTagsEntry(long pk,
		com.liferay.portlet.tags.model.TagsEntry tagsEntry)
		throws SystemException {
		try {
			addTagsEntry.add(pk, tagsEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_TagsEntries");
		}
	}

	public void addTagsEntries(long pk, long[] tagsEntryPKs)
		throws SystemException {
		try {
			for (long tagsEntryPK : tagsEntryPKs) {
				addTagsEntry.add(pk, tagsEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_TagsEntries");
		}
	}

	public void addTagsEntries(long pk,
		List<com.liferay.portlet.tags.model.TagsEntry> tagsEntries)
		throws SystemException {
		try {
			for (com.liferay.portlet.tags.model.TagsEntry tagsEntry : tagsEntries) {
				addTagsEntry.add(pk, tagsEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_TagsEntries");
		}
	}

	public void clearTagsEntries(long pk) throws SystemException {
		try {
			clearTagsEntries.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_TagsEntries");
		}
	}

	public void removeTagsEntry(long pk, long tagsEntryPK)
		throws SystemException {
		try {
			removeTagsEntry.remove(pk, tagsEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_TagsEntries");
		}
	}

	public void removeTagsEntry(long pk,
		com.liferay.portlet.tags.model.TagsEntry tagsEntry)
		throws SystemException {
		try {
			removeTagsEntry.remove(pk, tagsEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_TagsEntries");
		}
	}

	public void removeTagsEntries(long pk, long[] tagsEntryPKs)
		throws SystemException {
		try {
			for (long tagsEntryPK : tagsEntryPKs) {
				removeTagsEntry.remove(pk, tagsEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_TagsEntries");
		}
	}

	public void removeTagsEntries(long pk,
		List<com.liferay.portlet.tags.model.TagsEntry> tagsEntries)
		throws SystemException {
		try {
			for (com.liferay.portlet.tags.model.TagsEntry tagsEntry : tagsEntries) {
				removeTagsEntry.remove(pk, tagsEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_TagsEntries");
		}
	}

	public void setTagsEntries(long pk, long[] tagsEntryPKs)
		throws SystemException {
		try {
			clearTagsEntries.clear(pk);

			for (long tagsEntryPK : tagsEntryPKs) {
				addTagsEntry.add(pk, tagsEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_TagsEntries");
		}
	}

	public void setTagsEntries(long pk,
		List<com.liferay.portlet.tags.model.TagsEntry> tagsEntries)
		throws SystemException {
		try {
			clearTagsEntries.clear(pk);

			for (com.liferay.portlet.tags.model.TagsEntry tagsEntry : tagsEntries) {
				addTagsEntry.add(pk, tagsEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_TagsEntries");
		}
	}

	public List<com.liferay.portlet.categories.model.CategoriesEntry> getCategoriesEntries(
		long pk) throws SystemException {
		return getCategoriesEntries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<com.liferay.portlet.categories.model.CategoriesEntry> getCategoriesEntries(
		long pk, int start, int end) throws SystemException {
		return getCategoriesEntries(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_CATEGORIESENTRIES = new FinderPath(com.liferay.portlet.categories.model.impl.CategoriesEntryModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetModelImpl.FINDER_CACHE_ENABLED_TAGSASSETS_CATEGORIESENTRIES,
			"TagsAssets_CategoriesEntries", "getCategoriesEntries",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	public List<com.liferay.portlet.categories.model.CategoriesEntry> getCategoriesEntries(
		long pk, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(pk), String.valueOf(start), String.valueOf(end),
				String.valueOf(obc)
			};

		List<com.liferay.portlet.categories.model.CategoriesEntry> list = (List<com.liferay.portlet.categories.model.CategoriesEntry>)FinderCacheUtil.getResult(FINDER_PATH_GET_CATEGORIESENTRIES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder sb = new StringBuilder();

				sb.append(_SQL_GETCATEGORIESENTRIES);

				if (obc != null) {
					sb.append("ORDER BY ");
					sb.append(obc.getOrderBy());
				}

				else {
					sb.append("ORDER BY ");

					sb.append("CategoriesEntry.name ASC");
				}

				String sql = sb.toString();

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("CategoriesEntry",
					com.liferay.portlet.categories.model.impl.CategoriesEntryImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.portlet.categories.model.CategoriesEntry>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<com.liferay.portlet.categories.model.CategoriesEntry>();
				}

				categoriesEntryPersistence.cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_GET_CATEGORIESENTRIES,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_CATEGORIESENTRIES_SIZE = new FinderPath(com.liferay.portlet.categories.model.impl.CategoriesEntryModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetModelImpl.FINDER_CACHE_ENABLED_TAGSASSETS_CATEGORIESENTRIES,
			"TagsAssets_CategoriesEntries", "getCategoriesEntriesSize",
			new String[] { Long.class.getName() });

	public int getCategoriesEntriesSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(pk) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_CATEGORIESENTRIES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETCATEGORIESENTRIESSIZE);

				q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_CATEGORIESENTRIES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_CATEGORIESENTRY = new FinderPath(com.liferay.portlet.categories.model.impl.CategoriesEntryModelImpl.ENTITY_CACHE_ENABLED,
			TagsAssetModelImpl.FINDER_CACHE_ENABLED_TAGSASSETS_CATEGORIESENTRIES,
			"TagsAssets_CategoriesEntries", "containsCategoriesEntry",
			new String[] { Long.class.getName(), Long.class.getName() });

	public boolean containsCategoriesEntry(long pk, long categoriesEntryPK)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(pk),
				
				new Long(categoriesEntryPK)
			};

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_CATEGORIESENTRY,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsCategoriesEntry.contains(pk,
							categoriesEntryPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_CATEGORIESENTRY,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	public boolean containsCategoriesEntries(long pk) throws SystemException {
		if (getCategoriesEntriesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void addCategoriesEntry(long pk, long categoriesEntryPK)
		throws SystemException {
		try {
			addCategoriesEntry.add(pk, categoriesEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_CategoriesEntries");
		}
	}

	public void addCategoriesEntry(long pk,
		com.liferay.portlet.categories.model.CategoriesEntry categoriesEntry)
		throws SystemException {
		try {
			addCategoriesEntry.add(pk, categoriesEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_CategoriesEntries");
		}
	}

	public void addCategoriesEntries(long pk, long[] categoriesEntryPKs)
		throws SystemException {
		try {
			for (long categoriesEntryPK : categoriesEntryPKs) {
				addCategoriesEntry.add(pk, categoriesEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_CategoriesEntries");
		}
	}

	public void addCategoriesEntries(long pk,
		List<com.liferay.portlet.categories.model.CategoriesEntry> categoriesEntries)
		throws SystemException {
		try {
			for (com.liferay.portlet.categories.model.CategoriesEntry categoriesEntry : categoriesEntries) {
				addCategoriesEntry.add(pk, categoriesEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_CategoriesEntries");
		}
	}

	public void clearCategoriesEntries(long pk) throws SystemException {
		try {
			clearCategoriesEntries.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_CategoriesEntries");
		}
	}

	public void removeCategoriesEntry(long pk, long categoriesEntryPK)
		throws SystemException {
		try {
			removeCategoriesEntry.remove(pk, categoriesEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_CategoriesEntries");
		}
	}

	public void removeCategoriesEntry(long pk,
		com.liferay.portlet.categories.model.CategoriesEntry categoriesEntry)
		throws SystemException {
		try {
			removeCategoriesEntry.remove(pk, categoriesEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_CategoriesEntries");
		}
	}

	public void removeCategoriesEntries(long pk, long[] categoriesEntryPKs)
		throws SystemException {
		try {
			for (long categoriesEntryPK : categoriesEntryPKs) {
				removeCategoriesEntry.remove(pk, categoriesEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_CategoriesEntries");
		}
	}

	public void removeCategoriesEntries(long pk,
		List<com.liferay.portlet.categories.model.CategoriesEntry> categoriesEntries)
		throws SystemException {
		try {
			for (com.liferay.portlet.categories.model.CategoriesEntry categoriesEntry : categoriesEntries) {
				removeCategoriesEntry.remove(pk, categoriesEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_CategoriesEntries");
		}
	}

	public void setCategoriesEntries(long pk, long[] categoriesEntryPKs)
		throws SystemException {
		try {
			clearCategoriesEntries.clear(pk);

			for (long categoriesEntryPK : categoriesEntryPKs) {
				addCategoriesEntry.add(pk, categoriesEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_CategoriesEntries");
		}
	}

	public void setCategoriesEntries(long pk,
		List<com.liferay.portlet.categories.model.CategoriesEntry> categoriesEntries)
		throws SystemException {
		try {
			clearCategoriesEntries.clear(pk);

			for (com.liferay.portlet.categories.model.CategoriesEntry categoriesEntry : categoriesEntries) {
				addCategoriesEntry.add(pk, categoriesEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("TagsAssets_CategoriesEntries");
		}
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.tags.model.TagsAsset")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TagsAsset>> listenersList = new ArrayList<ModelListener<TagsAsset>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<TagsAsset>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsTagsEntry = new ContainsTagsEntry(this);

		addTagsEntry = new AddTagsEntry(this);
		clearTagsEntries = new ClearTagsEntries(this);
		removeTagsEntry = new RemoveTagsEntry(this);

		containsCategoriesEntry = new ContainsCategoriesEntry(this);

		addCategoriesEntry = new AddCategoriesEntry(this);
		clearCategoriesEntries = new ClearCategoriesEntries(this);
		removeCategoriesEntry = new RemoveCategoriesEntry(this);
	}

	@BeanReference(name = "com.liferay.portlet.tags.service.persistence.TagsAssetPersistence.impl")
	protected com.liferay.portlet.tags.service.persistence.TagsAssetPersistence tagsAssetPersistence;
	@BeanReference(name = "com.liferay.portlet.tags.service.persistence.TagsEntryPersistence.impl")
	protected com.liferay.portlet.tags.service.persistence.TagsEntryPersistence tagsEntryPersistence;
	@BeanReference(name = "com.liferay.portlet.tags.service.persistence.TagsPropertyPersistence.impl")
	protected com.liferay.portlet.tags.service.persistence.TagsPropertyPersistence tagsPropertyPersistence;
	@BeanReference(name = "com.liferay.portlet.tags.service.persistence.TagsSourcePersistence.impl")
	protected com.liferay.portlet.tags.service.persistence.TagsSourcePersistence tagsSourcePersistence;
	@BeanReference(name = "com.liferay.portlet.tags.service.persistence.TagsVocabularyPersistence.impl")
	protected com.liferay.portlet.tags.service.persistence.TagsVocabularyPersistence tagsVocabularyPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.CompanyPersistence.impl")
	protected com.liferay.portal.service.persistence.CompanyPersistence companyPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.GroupPersistence.impl")
	protected com.liferay.portal.service.persistence.GroupPersistence groupPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence.impl")
	protected com.liferay.portal.service.persistence.UserPersistence userPersistence;
	@BeanReference(name = "com.liferay.portlet.blogs.service.persistence.BlogsEntryPersistence.impl")
	protected com.liferay.portlet.blogs.service.persistence.BlogsEntryPersistence blogsEntryPersistence;
	@BeanReference(name = "com.liferay.portlet.bookmarks.service.persistence.BookmarksEntryPersistence.impl")
	protected com.liferay.portlet.bookmarks.service.persistence.BookmarksEntryPersistence bookmarksEntryPersistence;
	@BeanReference(name = "com.liferay.portlet.categories.service.persistence.CategoriesEntryPersistence.impl")
	protected com.liferay.portlet.categories.service.persistence.CategoriesEntryPersistence categoriesEntryPersistence;
	@BeanReference(name = "com.liferay.portlet.documentlibrary.service.persistence.DLFileEntryPersistence.impl")
	protected com.liferay.portlet.documentlibrary.service.persistence.DLFileEntryPersistence dlFileEntryPersistence;
	@BeanReference(name = "com.liferay.portlet.journal.service.persistence.JournalArticlePersistence.impl")
	protected com.liferay.portlet.journal.service.persistence.JournalArticlePersistence journalArticlePersistence;
	@BeanReference(name = "com.liferay.portlet.journal.service.persistence.JournalArticleResourcePersistence.impl")
	protected com.liferay.portlet.journal.service.persistence.JournalArticleResourcePersistence journalArticleResourcePersistence;
	@BeanReference(name = "com.liferay.portlet.messageboards.service.persistence.MBMessagePersistence.impl")
	protected com.liferay.portlet.messageboards.service.persistence.MBMessagePersistence mbMessagePersistence;
	@BeanReference(name = "com.liferay.portlet.wiki.service.persistence.WikiPagePersistence.impl")
	protected com.liferay.portlet.wiki.service.persistence.WikiPagePersistence wikiPagePersistence;
	@BeanReference(name = "com.liferay.portlet.wiki.service.persistence.WikiPageResourcePersistence.impl")
	protected com.liferay.portlet.wiki.service.persistence.WikiPageResourcePersistence wikiPageResourcePersistence;
	protected ContainsTagsEntry containsTagsEntry;
	protected AddTagsEntry addTagsEntry;
	protected ClearTagsEntries clearTagsEntries;
	protected RemoveTagsEntry removeTagsEntry;
	protected ContainsCategoriesEntry containsCategoriesEntry;
	protected AddCategoriesEntry addCategoriesEntry;
	protected ClearCategoriesEntries clearCategoriesEntries;
	protected RemoveCategoriesEntry removeCategoriesEntry;

	protected class ContainsTagsEntry {
		protected ContainsTagsEntry(TagsAssetPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSTAGSENTRY,
					new int[] { Types.BIGINT, Types.BIGINT }, RowMapper.COUNT);
		}

		protected boolean contains(long assetId, long entryId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(assetId), new Long(entryId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery _mappingSqlQuery;
	}

	protected class AddTagsEntry {
		protected AddTagsEntry(TagsAssetPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO TagsAssets_TagsEntries (assetId, entryId) VALUES (?, ?)",
					new int[] { Types.BIGINT, Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void add(long assetId, long entryId)
			throws SystemException {
			if (!_persistenceImpl.containsTagsEntry.contains(assetId, entryId)) {
				ModelListener<com.liferay.portlet.tags.model.TagsEntry>[] tagsEntryListeners =
					tagsEntryPersistence.getListeners();

				for (ModelListener<TagsAsset> listener : listeners) {
					listener.onBeforeAddAssociation(assetId,
						com.liferay.portlet.tags.model.TagsEntry.class.getName(),
						entryId);
				}

				for (ModelListener<com.liferay.portlet.tags.model.TagsEntry> listener : tagsEntryListeners) {
					listener.onBeforeAddAssociation(entryId,
						TagsAsset.class.getName(), assetId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(assetId), new Long(entryId)
					});

				for (ModelListener<TagsAsset> listener : listeners) {
					listener.onAfterAddAssociation(assetId,
						com.liferay.portlet.tags.model.TagsEntry.class.getName(),
						entryId);
				}

				for (ModelListener<com.liferay.portlet.tags.model.TagsEntry> listener : tagsEntryListeners) {
					listener.onAfterAddAssociation(entryId,
						TagsAsset.class.getName(), assetId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
		private TagsAssetPersistenceImpl _persistenceImpl;
	}

	protected class ClearTagsEntries {
		protected ClearTagsEntries(TagsAssetPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM TagsAssets_TagsEntries WHERE assetId = ?",
					new int[] { Types.BIGINT });
		}

		protected void clear(long assetId) throws SystemException {
			ModelListener<com.liferay.portlet.tags.model.TagsEntry>[] tagsEntryListeners =
				tagsEntryPersistence.getListeners();

			List<com.liferay.portlet.tags.model.TagsEntry> tagsEntries = null;

			if ((listeners.length > 0) || (tagsEntryListeners.length > 0)) {
				tagsEntries = getTagsEntries(assetId);

				for (com.liferay.portlet.tags.model.TagsEntry tagsEntry : tagsEntries) {
					for (ModelListener<TagsAsset> listener : listeners) {
						listener.onBeforeRemoveAssociation(assetId,
							com.liferay.portlet.tags.model.TagsEntry.class.getName(),
							tagsEntry.getPrimaryKey());
					}

					for (ModelListener<com.liferay.portlet.tags.model.TagsEntry> listener : tagsEntryListeners) {
						listener.onBeforeRemoveAssociation(tagsEntry.getPrimaryKey(),
							TagsAsset.class.getName(), assetId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(assetId) });

			if ((listeners.length > 0) || (tagsEntryListeners.length > 0)) {
				for (com.liferay.portlet.tags.model.TagsEntry tagsEntry : tagsEntries) {
					for (ModelListener<TagsAsset> listener : listeners) {
						listener.onAfterRemoveAssociation(assetId,
							com.liferay.portlet.tags.model.TagsEntry.class.getName(),
							tagsEntry.getPrimaryKey());
					}

					for (ModelListener<com.liferay.portlet.tags.model.TagsEntry> listener : tagsEntryListeners) {
						listener.onBeforeRemoveAssociation(tagsEntry.getPrimaryKey(),
							TagsAsset.class.getName(), assetId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveTagsEntry {
		protected RemoveTagsEntry(TagsAssetPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM TagsAssets_TagsEntries WHERE assetId = ? AND entryId = ?",
					new int[] { Types.BIGINT, Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void remove(long assetId, long entryId)
			throws SystemException {
			if (_persistenceImpl.containsTagsEntry.contains(assetId, entryId)) {
				ModelListener<com.liferay.portlet.tags.model.TagsEntry>[] tagsEntryListeners =
					tagsEntryPersistence.getListeners();

				for (ModelListener<TagsAsset> listener : listeners) {
					listener.onBeforeRemoveAssociation(assetId,
						com.liferay.portlet.tags.model.TagsEntry.class.getName(),
						entryId);
				}

				for (ModelListener<com.liferay.portlet.tags.model.TagsEntry> listener : tagsEntryListeners) {
					listener.onBeforeRemoveAssociation(entryId,
						TagsAsset.class.getName(), assetId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(assetId), new Long(entryId)
					});

				for (ModelListener<TagsAsset> listener : listeners) {
					listener.onAfterRemoveAssociation(assetId,
						com.liferay.portlet.tags.model.TagsEntry.class.getName(),
						entryId);
				}

				for (ModelListener<com.liferay.portlet.tags.model.TagsEntry> listener : tagsEntryListeners) {
					listener.onAfterRemoveAssociation(entryId,
						TagsAsset.class.getName(), assetId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
		private TagsAssetPersistenceImpl _persistenceImpl;
	}

	protected class ContainsCategoriesEntry {
		protected ContainsCategoriesEntry(
			TagsAssetPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSCATEGORIESENTRY,
					new int[] { Types.BIGINT, Types.BIGINT }, RowMapper.COUNT);
		}

		protected boolean contains(long assetId, long entryId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(assetId), new Long(entryId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery _mappingSqlQuery;
	}

	protected class AddCategoriesEntry {
		protected AddCategoriesEntry(TagsAssetPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO TagsAssets_CategoriesEntries (assetId, entryId) VALUES (?, ?)",
					new int[] { Types.BIGINT, Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void add(long assetId, long entryId)
			throws SystemException {
			if (!_persistenceImpl.containsCategoriesEntry.contains(assetId,
						entryId)) {
				ModelListener<com.liferay.portlet.categories.model.CategoriesEntry>[] categoriesEntryListeners =
					categoriesEntryPersistence.getListeners();

				for (ModelListener<TagsAsset> listener : listeners) {
					listener.onBeforeAddAssociation(assetId,
						com.liferay.portlet.categories.model.CategoriesEntry.class.getName(),
						entryId);
				}

				for (ModelListener<com.liferay.portlet.categories.model.CategoriesEntry> listener : categoriesEntryListeners) {
					listener.onBeforeAddAssociation(entryId,
						TagsAsset.class.getName(), assetId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(assetId), new Long(entryId)
					});

				for (ModelListener<TagsAsset> listener : listeners) {
					listener.onAfterAddAssociation(assetId,
						com.liferay.portlet.categories.model.CategoriesEntry.class.getName(),
						entryId);
				}

				for (ModelListener<com.liferay.portlet.categories.model.CategoriesEntry> listener : categoriesEntryListeners) {
					listener.onAfterAddAssociation(entryId,
						TagsAsset.class.getName(), assetId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
		private TagsAssetPersistenceImpl _persistenceImpl;
	}

	protected class ClearCategoriesEntries {
		protected ClearCategoriesEntries(
			TagsAssetPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM TagsAssets_CategoriesEntries WHERE assetId = ?",
					new int[] { Types.BIGINT });
		}

		protected void clear(long assetId) throws SystemException {
			ModelListener<com.liferay.portlet.categories.model.CategoriesEntry>[] categoriesEntryListeners =
				categoriesEntryPersistence.getListeners();

			List<com.liferay.portlet.categories.model.CategoriesEntry> categoriesEntries =
				null;

			if ((listeners.length > 0) ||
					(categoriesEntryListeners.length > 0)) {
				categoriesEntries = getCategoriesEntries(assetId);

				for (com.liferay.portlet.categories.model.CategoriesEntry categoriesEntry : categoriesEntries) {
					for (ModelListener<TagsAsset> listener : listeners) {
						listener.onBeforeRemoveAssociation(assetId,
							com.liferay.portlet.categories.model.CategoriesEntry.class.getName(),
							categoriesEntry.getPrimaryKey());
					}

					for (ModelListener<com.liferay.portlet.categories.model.CategoriesEntry> listener : categoriesEntryListeners) {
						listener.onBeforeRemoveAssociation(categoriesEntry.getPrimaryKey(),
							TagsAsset.class.getName(), assetId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(assetId) });

			if ((listeners.length > 0) ||
					(categoriesEntryListeners.length > 0)) {
				for (com.liferay.portlet.categories.model.CategoriesEntry categoriesEntry : categoriesEntries) {
					for (ModelListener<TagsAsset> listener : listeners) {
						listener.onAfterRemoveAssociation(assetId,
							com.liferay.portlet.categories.model.CategoriesEntry.class.getName(),
							categoriesEntry.getPrimaryKey());
					}

					for (ModelListener<com.liferay.portlet.categories.model.CategoriesEntry> listener : categoriesEntryListeners) {
						listener.onBeforeRemoveAssociation(categoriesEntry.getPrimaryKey(),
							TagsAsset.class.getName(), assetId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveCategoriesEntry {
		protected RemoveCategoriesEntry(
			TagsAssetPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM TagsAssets_CategoriesEntries WHERE assetId = ? AND entryId = ?",
					new int[] { Types.BIGINT, Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void remove(long assetId, long entryId)
			throws SystemException {
			if (_persistenceImpl.containsCategoriesEntry.contains(assetId,
						entryId)) {
				ModelListener<com.liferay.portlet.categories.model.CategoriesEntry>[] categoriesEntryListeners =
					categoriesEntryPersistence.getListeners();

				for (ModelListener<TagsAsset> listener : listeners) {
					listener.onBeforeRemoveAssociation(assetId,
						com.liferay.portlet.categories.model.CategoriesEntry.class.getName(),
						entryId);
				}

				for (ModelListener<com.liferay.portlet.categories.model.CategoriesEntry> listener : categoriesEntryListeners) {
					listener.onBeforeRemoveAssociation(entryId,
						TagsAsset.class.getName(), assetId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(assetId), new Long(entryId)
					});

				for (ModelListener<TagsAsset> listener : listeners) {
					listener.onAfterRemoveAssociation(assetId,
						com.liferay.portlet.categories.model.CategoriesEntry.class.getName(),
						entryId);
				}

				for (ModelListener<com.liferay.portlet.categories.model.CategoriesEntry> listener : categoriesEntryListeners) {
					listener.onAfterRemoveAssociation(entryId,
						TagsAsset.class.getName(), assetId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
		private TagsAssetPersistenceImpl _persistenceImpl;
	}

	private static final String _SQL_GETTAGSENTRIES = "SELECT {TagsEntry.*} FROM TagsEntry INNER JOIN TagsAssets_TagsEntries ON (TagsAssets_TagsEntries.entryId = TagsEntry.entryId) WHERE (TagsAssets_TagsEntries.assetId = ?)";
	private static final String _SQL_GETTAGSENTRIESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM TagsAssets_TagsEntries WHERE assetId = ?";
	private static final String _SQL_CONTAINSTAGSENTRY = "SELECT COUNT(*) AS COUNT_VALUE FROM TagsAssets_TagsEntries WHERE assetId = ? AND entryId = ?";
	private static final String _SQL_GETCATEGORIESENTRIES = "SELECT {CategoriesEntry.*} FROM CategoriesEntry INNER JOIN TagsAssets_CategoriesEntries ON (TagsAssets_CategoriesEntries.entryId = CategoriesEntry.entryId) WHERE (TagsAssets_CategoriesEntries.assetId = ?)";
	private static final String _SQL_GETCATEGORIESENTRIESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM TagsAssets_CategoriesEntries WHERE assetId = ?";
	private static final String _SQL_CONTAINSCATEGORIESENTRY = "SELECT COUNT(*) AS COUNT_VALUE FROM TagsAssets_CategoriesEntries WHERE assetId = ? AND entryId = ?";
	private static Log _log = LogFactoryUtil.getLog(TagsAssetPersistenceImpl.class);
}