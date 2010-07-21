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

package com.liferay.portlet.messageboards.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.messageboards.model.MBDiscussion;

import java.util.List;

/**
 * The persistence utility for the message boards discussion service.
 *
 * <p>
 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regnerate this class.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MBDiscussionPersistence
 * @see MBDiscussionPersistenceImpl
 * @generated
 */
public class MBDiscussionUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(MBDiscussion mbDiscussion) {
		getPersistence().clearCache(mbDiscussion);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MBDiscussion> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MBDiscussion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MBDiscussion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static MBDiscussion remove(MBDiscussion mbDiscussion)
		throws SystemException {
		return getPersistence().remove(mbDiscussion);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static MBDiscussion update(MBDiscussion mbDiscussion, boolean merge)
		throws SystemException {
		return getPersistence().update(mbDiscussion, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static MBDiscussion update(MBDiscussion mbDiscussion, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(mbDiscussion, merge, serviceContext);
	}

	/**
	* Caches the message boards discussion in the entity cache if it is enabled.
	*
	* @param mbDiscussion the message boards discussion to cache
	*/
	public static void cacheResult(
		com.liferay.portlet.messageboards.model.MBDiscussion mbDiscussion) {
		getPersistence().cacheResult(mbDiscussion);
	}

	/**
	* Caches the message boards discussions in the entity cache if it is enabled.
	*
	* @param mbDiscussions the message boards discussions to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portlet.messageboards.model.MBDiscussion> mbDiscussions) {
		getPersistence().cacheResult(mbDiscussions);
	}

	/**
	* Creates a new message boards discussion with the primary key.
	*
	* @param discussionId the primary key for the new message boards discussion
	* @return the new message boards discussion
	*/
	public static com.liferay.portlet.messageboards.model.MBDiscussion create(
		long discussionId) {
		return getPersistence().create(discussionId);
	}

	/**
	* Removes the message boards discussion with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param discussionId the primary key of the message boards discussion to remove
	* @return the message boards discussion that was removed
	* @throws com.liferay.portlet.messageboards.NoSuchDiscussionException if a message boards discussion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBDiscussion remove(
		long discussionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.messageboards.NoSuchDiscussionException {
		return getPersistence().remove(discussionId);
	}

	public static com.liferay.portlet.messageboards.model.MBDiscussion updateImpl(
		com.liferay.portlet.messageboards.model.MBDiscussion mbDiscussion,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(mbDiscussion, merge);
	}

	/**
	* Finds the message boards discussion with the primary key or throws a {@link com.liferay.portlet.messageboards.NoSuchDiscussionException} if it could not be found.
	*
	* @param discussionId the primary key of the message boards discussion to find
	* @return the message boards discussion
	* @throws com.liferay.portlet.messageboards.NoSuchDiscussionException if a message boards discussion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBDiscussion findByPrimaryKey(
		long discussionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.messageboards.NoSuchDiscussionException {
		return getPersistence().findByPrimaryKey(discussionId);
	}

	/**
	* Finds the message boards discussion with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param discussionId the primary key of the message boards discussion to find
	* @return the message boards discussion, or <code>null</code> if a message boards discussion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBDiscussion fetchByPrimaryKey(
		long discussionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(discussionId);
	}

	/**
	* Finds all the message boards discussions where classNameId = &#63;.
	*
	* @param classNameId the class name id to search with
	* @return the matching message boards discussions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.messageboards.model.MBDiscussion> findByClassNameId(
		long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByClassNameId(classNameId);
	}

	/**
	* Finds a range of all the message boards discussions where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name id to search with
	* @param start the lower bound of the range of message boards discussions to return
	* @param end the upper bound of the range of message boards discussions to return (not inclusive)
	* @return the range of matching message boards discussions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.messageboards.model.MBDiscussion> findByClassNameId(
		long classNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByClassNameId(classNameId, start, end);
	}

	/**
	* Finds an ordered range of all the message boards discussions where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name id to search with
	* @param start the lower bound of the range of message boards discussions to return
	* @param end the upper bound of the range of message boards discussions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching message boards discussions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.messageboards.model.MBDiscussion> findByClassNameId(
		long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByClassNameId(classNameId, start, end, orderByComparator);
	}

	/**
	* Finds the first message boards discussion in the ordered set where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching message boards discussion
	* @throws com.liferay.portlet.messageboards.NoSuchDiscussionException if a matching message boards discussion could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBDiscussion findByClassNameId_First(
		long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.messageboards.NoSuchDiscussionException {
		return getPersistence()
				   .findByClassNameId_First(classNameId, orderByComparator);
	}

	/**
	* Finds the last message boards discussion in the ordered set where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching message boards discussion
	* @throws com.liferay.portlet.messageboards.NoSuchDiscussionException if a matching message boards discussion could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBDiscussion findByClassNameId_Last(
		long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.messageboards.NoSuchDiscussionException {
		return getPersistence()
				   .findByClassNameId_Last(classNameId, orderByComparator);
	}

	/**
	* Finds the message boards discussions before and after the current message boards discussion in the ordered set where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param discussionId the primary key of the current message boards discussion
	* @param classNameId the class name id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next message boards discussion
	* @throws com.liferay.portlet.messageboards.NoSuchDiscussionException if a message boards discussion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBDiscussion[] findByClassNameId_PrevAndNext(
		long discussionId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.messageboards.NoSuchDiscussionException {
		return getPersistence()
				   .findByClassNameId_PrevAndNext(discussionId, classNameId,
			orderByComparator);
	}

	/**
	* Finds the message boards discussion where threadId = &#63; or throws a {@link com.liferay.portlet.messageboards.NoSuchDiscussionException} if it could not be found.
	*
	* @param threadId the thread id to search with
	* @return the matching message boards discussion
	* @throws com.liferay.portlet.messageboards.NoSuchDiscussionException if a matching message boards discussion could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBDiscussion findByThreadId(
		long threadId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.messageboards.NoSuchDiscussionException {
		return getPersistence().findByThreadId(threadId);
	}

	/**
	* Finds the message boards discussion where threadId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param threadId the thread id to search with
	* @return the matching message boards discussion, or <code>null</code> if a matching message boards discussion could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBDiscussion fetchByThreadId(
		long threadId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByThreadId(threadId);
	}

	/**
	* Finds the message boards discussion where threadId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param threadId the thread id to search with
	* @return the matching message boards discussion, or <code>null</code> if a matching message boards discussion could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBDiscussion fetchByThreadId(
		long threadId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByThreadId(threadId, retrieveFromCache);
	}

	/**
	* Finds the message boards discussion where classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.portlet.messageboards.NoSuchDiscussionException} if it could not be found.
	*
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @return the matching message boards discussion
	* @throws com.liferay.portlet.messageboards.NoSuchDiscussionException if a matching message boards discussion could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBDiscussion findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.messageboards.NoSuchDiscussionException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	* Finds the message boards discussion where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @return the matching message boards discussion, or <code>null</code> if a matching message boards discussion could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBDiscussion fetchByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_C(classNameId, classPK);
	}

	/**
	* Finds the message boards discussion where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @return the matching message boards discussion, or <code>null</code> if a matching message boards discussion could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.messageboards.model.MBDiscussion fetchByC_C(
		long classNameId, long classPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C(classNameId, classPK, retrieveFromCache);
	}

	/**
	* Finds all the message boards discussions.
	*
	* @return the message boards discussions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.messageboards.model.MBDiscussion> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the message boards discussions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of message boards discussions to return
	* @param end the upper bound of the range of message boards discussions to return (not inclusive)
	* @return the range of message boards discussions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.messageboards.model.MBDiscussion> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the message boards discussions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of message boards discussions to return
	* @param end the upper bound of the range of message boards discussions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of message boards discussions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.messageboards.model.MBDiscussion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the message boards discussions where classNameId = &#63; from the database.
	*
	* @param classNameId the class name id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByClassNameId(long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByClassNameId(classNameId);
	}

	/**
	* Removes the message boards discussion where threadId = &#63; from the database.
	*
	* @param threadId the thread id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByThreadId(long threadId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.messageboards.NoSuchDiscussionException {
		getPersistence().removeByThreadId(threadId);
	}

	/**
	* Removes the message boards discussion where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.messageboards.NoSuchDiscussionException {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Removes all the message boards discussions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the message boards discussions where classNameId = &#63;.
	*
	* @param classNameId the class name id to search with
	* @return the number of matching message boards discussions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByClassNameId(long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByClassNameId(classNameId);
	}

	/**
	* Counts all the message boards discussions where threadId = &#63;.
	*
	* @param threadId the thread id to search with
	* @return the number of matching message boards discussions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByThreadId(long threadId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByThreadId(threadId);
	}

	/**
	* Counts all the message boards discussions where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name id to search with
	* @param classPK the class p k to search with
	* @return the number of matching message boards discussions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Counts all the message boards discussions.
	*
	* @return the number of message boards discussions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static MBDiscussionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (MBDiscussionPersistence)PortalBeanLocatorUtil.locate(MBDiscussionPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(MBDiscussionPersistence persistence) {
		_persistence = persistence;
	}

	private static MBDiscussionPersistence _persistence;
}