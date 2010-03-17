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
import com.liferay.portal.NoSuchReleaseException;
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
import com.liferay.portal.model.Release;
import com.liferay.portal.model.impl.ReleaseImpl;
import com.liferay.portal.model.impl.ReleaseModelImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="ReleasePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ReleasePersistence
 * @see       ReleaseUtil
 * @generated
 */
public class ReleasePersistenceImpl extends BasePersistenceImpl<Release>
	implements ReleasePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = ReleaseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME = new FinderPath(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByServletContextName", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_SERVLETCONTEXTNAME = new FinderPath(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByServletContextName", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Release release) {
		EntityCacheUtil.putResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseImpl.class, release.getPrimaryKey(), release);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
			new Object[] { release.getServletContextName() }, release);
	}

	public void cacheResult(List<Release> releases) {
		for (Release release : releases) {
			if (EntityCacheUtil.getResult(
						ReleaseModelImpl.ENTITY_CACHE_ENABLED,
						ReleaseImpl.class, release.getPrimaryKey(), this) == null) {
				cacheResult(release);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(ReleaseImpl.class.getName());
		EntityCacheUtil.clearCache(ReleaseImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public Release create(long releaseId) {
		Release release = new ReleaseImpl();

		release.setNew(true);
		release.setPrimaryKey(releaseId);

		return release;
	}

	public Release remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public Release remove(long releaseId)
		throws NoSuchReleaseException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Release release = (Release)session.get(ReleaseImpl.class,
					new Long(releaseId));

			if (release == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + releaseId);
				}

				throw new NoSuchReleaseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					releaseId);
			}

			return remove(release);
		}
		catch (NoSuchReleaseException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Release remove(Release release) throws SystemException {
		for (ModelListener<Release> listener : listeners) {
			listener.onBeforeRemove(release);
		}

		release = removeImpl(release);

		for (ModelListener<Release> listener : listeners) {
			listener.onAfterRemove(release);
		}

		return release;
	}

	protected Release removeImpl(Release release) throws SystemException {
		release = toUnwrappedModel(release);

		Session session = null;

		try {
			session = openSession();

			if (release.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(ReleaseImpl.class,
						release.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(release);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		ReleaseModelImpl releaseModelImpl = (ReleaseModelImpl)release;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
			new Object[] { releaseModelImpl.getOriginalServletContextName() });

		EntityCacheUtil.removeResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseImpl.class, release.getPrimaryKey());

		return release;
	}

	public Release updateImpl(com.liferay.portal.model.Release release,
		boolean merge) throws SystemException {
		release = toUnwrappedModel(release);

		boolean isNew = release.isNew();

		ReleaseModelImpl releaseModelImpl = (ReleaseModelImpl)release;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, release, merge);

			release.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseImpl.class, release.getPrimaryKey(), release);

		if (!isNew &&
				(!Validator.equals(release.getServletContextName(),
					releaseModelImpl.getOriginalServletContextName()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
				new Object[] { releaseModelImpl.getOriginalServletContextName() });
		}

		if (isNew ||
				(!Validator.equals(release.getServletContextName(),
					releaseModelImpl.getOriginalServletContextName()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
				new Object[] { release.getServletContextName() }, release);
		}

		return release;
	}

	protected Release toUnwrappedModel(Release release) {
		if (release instanceof ReleaseImpl) {
			return release;
		}

		ReleaseImpl releaseImpl = new ReleaseImpl();

		releaseImpl.setNew(release.isNew());
		releaseImpl.setPrimaryKey(release.getPrimaryKey());

		releaseImpl.setReleaseId(release.getReleaseId());
		releaseImpl.setCreateDate(release.getCreateDate());
		releaseImpl.setModifiedDate(release.getModifiedDate());
		releaseImpl.setServletContextName(release.getServletContextName());
		releaseImpl.setBuildNumber(release.getBuildNumber());
		releaseImpl.setBuildDate(release.getBuildDate());
		releaseImpl.setVerified(release.isVerified());
		releaseImpl.setTestString(release.getTestString());

		return releaseImpl;
	}

	public Release findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Release findByPrimaryKey(long releaseId)
		throws NoSuchReleaseException, SystemException {
		Release release = fetchByPrimaryKey(releaseId);

		if (release == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + releaseId);
			}

			throw new NoSuchReleaseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				releaseId);
		}

		return release;
	}

	public Release fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Release fetchByPrimaryKey(long releaseId) throws SystemException {
		Release release = (Release)EntityCacheUtil.getResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
				ReleaseImpl.class, releaseId, this);

		if (release == null) {
			Session session = null;

			try {
				session = openSession();

				release = (Release)session.get(ReleaseImpl.class,
						new Long(releaseId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (release != null) {
					cacheResult(release);
				}

				closeSession(session);
			}
		}

		return release;
	}

	public Release findByServletContextName(String servletContextName)
		throws NoSuchReleaseException, SystemException {
		Release release = fetchByServletContextName(servletContextName);

		if (release == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("servletContextName=");
			msg.append(servletContextName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchReleaseException(msg.toString());
		}

		return release;
	}

	public Release fetchByServletContextName(String servletContextName)
		throws SystemException {
		return fetchByServletContextName(servletContextName, true);
	}

	public Release fetchByServletContextName(String servletContextName,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { servletContextName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_SELECT_RELEASE_WHERE);

				if (servletContextName == null) {
					query.append(_FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_1);
				}
				else {
					if (servletContextName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_2);
					}
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (servletContextName != null) {
					qPos.add(servletContextName);
				}

				List<Release> list = q.list();

				result = list;

				Release release = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
						finderArgs, list);
				}
				else {
					release = list.get(0);

					cacheResult(release);

					if ((release.getServletContextName() == null) ||
							!release.getServletContextName()
										.equals(servletContextName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
							finderArgs, release);
					}
				}

				return release;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
						finderArgs, new ArrayList<Release>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (Release)result;
			}
		}
	}

	public List<Release> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Release> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Release> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Release> list = (List<Release>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_RELEASE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				sql = _SQL_SELECT_RELEASE;

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Release>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Release>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Release>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByServletContextName(String servletContextName)
		throws NoSuchReleaseException, SystemException {
		Release release = findByServletContextName(servletContextName);

		remove(release);
	}

	public void removeAll() throws SystemException {
		for (Release release : findAll()) {
			remove(release);
		}
	}

	public int countByServletContextName(String servletContextName)
		throws SystemException {
		Object[] finderArgs = new Object[] { servletContextName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SERVLETCONTEXTNAME,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_RELEASE_WHERE);

				if (servletContextName == null) {
					query.append(_FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_1);
				}
				else {
					if (servletContextName.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_3);
					}
					else {
						query.append(_FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_2);
					}
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (servletContextName != null) {
					qPos.add(servletContextName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SERVLETCONTEXTNAME,
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

				Query q = session.createQuery(_SQL_COUNT_RELEASE);

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
						"value.object.listener.com.liferay.portal.model.Release")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Release>> listenersList = new ArrayList<ModelListener<Release>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Release>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.portal.service.persistence.AccountPersistence")
	protected com.liferay.portal.service.persistence.AccountPersistence accountPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.AddressPersistence")
	protected com.liferay.portal.service.persistence.AddressPersistence addressPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.BrowserTrackerPersistence")
	protected com.liferay.portal.service.persistence.BrowserTrackerPersistence browserTrackerPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ClassNamePersistence")
	protected com.liferay.portal.service.persistence.ClassNamePersistence classNamePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.CompanyPersistence")
	protected com.liferay.portal.service.persistence.CompanyPersistence companyPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ContactPersistence")
	protected com.liferay.portal.service.persistence.ContactPersistence contactPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.CountryPersistence")
	protected com.liferay.portal.service.persistence.CountryPersistence countryPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.EmailAddressPersistence")
	protected com.liferay.portal.service.persistence.EmailAddressPersistence emailAddressPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.GroupPersistence")
	protected com.liferay.portal.service.persistence.GroupPersistence groupPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ImagePersistence")
	protected com.liferay.portal.service.persistence.ImagePersistence imagePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.LayoutPersistence")
	protected com.liferay.portal.service.persistence.LayoutPersistence layoutPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.LayoutPrototypePersistence")
	protected com.liferay.portal.service.persistence.LayoutPrototypePersistence layoutPrototypePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.LayoutSetPersistence")
	protected com.liferay.portal.service.persistence.LayoutSetPersistence layoutSetPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.LayoutSetPrototypePersistence")
	protected com.liferay.portal.service.persistence.LayoutSetPrototypePersistence layoutSetPrototypePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ListTypePersistence")
	protected com.liferay.portal.service.persistence.ListTypePersistence listTypePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.LockPersistence")
	protected com.liferay.portal.service.persistence.LockPersistence lockPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.MembershipRequestPersistence")
	protected com.liferay.portal.service.persistence.MembershipRequestPersistence membershipRequestPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.OrganizationPersistence")
	protected com.liferay.portal.service.persistence.OrganizationPersistence organizationPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.OrgGroupPermissionPersistence")
	protected com.liferay.portal.service.persistence.OrgGroupPermissionPersistence orgGroupPermissionPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.OrgGroupRolePersistence")
	protected com.liferay.portal.service.persistence.OrgGroupRolePersistence orgGroupRolePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.OrgLaborPersistence")
	protected com.liferay.portal.service.persistence.OrgLaborPersistence orgLaborPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.PasswordPolicyPersistence")
	protected com.liferay.portal.service.persistence.PasswordPolicyPersistence passwordPolicyPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.PasswordPolicyRelPersistence")
	protected com.liferay.portal.service.persistence.PasswordPolicyRelPersistence passwordPolicyRelPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.PasswordTrackerPersistence")
	protected com.liferay.portal.service.persistence.PasswordTrackerPersistence passwordTrackerPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.PermissionPersistence")
	protected com.liferay.portal.service.persistence.PermissionPersistence permissionPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.PhonePersistence")
	protected com.liferay.portal.service.persistence.PhonePersistence phonePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.PluginSettingPersistence")
	protected com.liferay.portal.service.persistence.PluginSettingPersistence pluginSettingPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.PortletPersistence")
	protected com.liferay.portal.service.persistence.PortletPersistence portletPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.PortletItemPersistence")
	protected com.liferay.portal.service.persistence.PortletItemPersistence portletItemPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.PortletPreferencesPersistence")
	protected com.liferay.portal.service.persistence.PortletPreferencesPersistence portletPreferencesPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.RegionPersistence")
	protected com.liferay.portal.service.persistence.RegionPersistence regionPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ReleasePersistence")
	protected com.liferay.portal.service.persistence.ReleasePersistence releasePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ResourcePersistence")
	protected com.liferay.portal.service.persistence.ResourcePersistence resourcePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ResourceActionPersistence")
	protected com.liferay.portal.service.persistence.ResourceActionPersistence resourceActionPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ResourceCodePersistence")
	protected com.liferay.portal.service.persistence.ResourceCodePersistence resourceCodePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ResourcePermissionPersistence")
	protected com.liferay.portal.service.persistence.ResourcePermissionPersistence resourcePermissionPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.RolePersistence")
	protected com.liferay.portal.service.persistence.RolePersistence rolePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ServiceComponentPersistence")
	protected com.liferay.portal.service.persistence.ServiceComponentPersistence serviceComponentPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ShardPersistence")
	protected com.liferay.portal.service.persistence.ShardPersistence shardPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.SubscriptionPersistence")
	protected com.liferay.portal.service.persistence.SubscriptionPersistence subscriptionPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.TeamPersistence")
	protected com.liferay.portal.service.persistence.TeamPersistence teamPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence")
	protected com.liferay.portal.service.persistence.UserPersistence userPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserGroupPersistence")
	protected com.liferay.portal.service.persistence.UserGroupPersistence userGroupPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserGroupGroupRolePersistence")
	protected com.liferay.portal.service.persistence.UserGroupGroupRolePersistence userGroupGroupRolePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserGroupRolePersistence")
	protected com.liferay.portal.service.persistence.UserGroupRolePersistence userGroupRolePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserIdMapperPersistence")
	protected com.liferay.portal.service.persistence.UserIdMapperPersistence userIdMapperPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserTrackerPersistence")
	protected com.liferay.portal.service.persistence.UserTrackerPersistence userTrackerPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserTrackerPathPersistence")
	protected com.liferay.portal.service.persistence.UserTrackerPathPersistence userTrackerPathPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.WebDAVPropsPersistence")
	protected com.liferay.portal.service.persistence.WebDAVPropsPersistence webDAVPropsPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.WebsitePersistence")
	protected com.liferay.portal.service.persistence.WebsitePersistence websitePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.WorkflowDefinitionLinkPersistence")
	protected com.liferay.portal.service.persistence.WorkflowDefinitionLinkPersistence workflowDefinitionLinkPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.WorkflowInstanceLinkPersistence")
	protected com.liferay.portal.service.persistence.WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;
	private static final String _SQL_SELECT_RELEASE = "SELECT release FROM Release release";
	private static final String _SQL_SELECT_RELEASE_WHERE = "SELECT release FROM Release release WHERE ";
	private static final String _SQL_COUNT_RELEASE = "SELECT COUNT(release) FROM Release release";
	private static final String _SQL_COUNT_RELEASE_WHERE = "SELECT COUNT(release) FROM Release release WHERE ";
	private static final String _FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_1 =
		"release.servletContextName IS NULL";
	private static final String _FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_2 =
		"release.servletContextName = ?";
	private static final String _FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_3 =
		"(release.servletContextName IS NULL OR release.servletContextName = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "release.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Release exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Release exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(ReleasePersistenceImpl.class);
}