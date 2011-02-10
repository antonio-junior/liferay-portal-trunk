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

package com.liferay.portal.service.persistence;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.RepositoryEntry;

/**
 * The persistence interface for the repository entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RepositoryEntryPersistenceImpl
 * @see RepositoryEntryUtil
 * @generated
 */
public interface RepositoryEntryPersistence extends BasePersistence<RepositoryEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RepositoryEntryUtil} to access the repository entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the repository entry in the entity cache if it is enabled.
	*
	* @param repositoryEntry the repository entry to cache
	*/
	public void cacheResult(
		com.liferay.portal.model.RepositoryEntry repositoryEntry);

	/**
	* Caches the repository entries in the entity cache if it is enabled.
	*
	* @param repositoryEntries the repository entries to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.model.RepositoryEntry> repositoryEntries);

	/**
	* Creates a new repository entry with the primary key. Does not add the repository entry to the database.
	*
	* @param repositoryEntryId the primary key for the new repository entry
	* @return the new repository entry
	*/
	public com.liferay.portal.model.RepositoryEntry create(
		long repositoryEntryId);

	/**
	* Removes the repository entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param repositoryEntryId the primary key of the repository entry to remove
	* @return the repository entry that was removed
	* @throws com.liferay.portal.NoSuchRepositoryEntryException if a repository entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.RepositoryEntry remove(
		long repositoryEntryId)
		throws com.liferay.portal.NoSuchRepositoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.RepositoryEntry updateImpl(
		com.liferay.portal.model.RepositoryEntry repositoryEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the repository entry with the primary key or throws a {@link com.liferay.portal.NoSuchRepositoryEntryException} if it could not be found.
	*
	* @param repositoryEntryId the primary key of the repository entry to find
	* @return the repository entry
	* @throws com.liferay.portal.NoSuchRepositoryEntryException if a repository entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.RepositoryEntry findByPrimaryKey(
		long repositoryEntryId)
		throws com.liferay.portal.NoSuchRepositoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the repository entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param repositoryEntryId the primary key of the repository entry to find
	* @return the repository entry, or <code>null</code> if a repository entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.RepositoryEntry fetchByPrimaryKey(
		long repositoryEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the repository entries where repositoryId = &#63;.
	*
	* @param repositoryId the repository ID to search with
	* @return the matching repository entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.RepositoryEntry> findByRepositoryId(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the repository entries where repositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param repositoryId the repository ID to search with
	* @param start the lower bound of the range of repository entries to return
	* @param end the upper bound of the range of repository entries to return (not inclusive)
	* @return the range of matching repository entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.RepositoryEntry> findByRepositoryId(
		long repositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the repository entries where repositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param repositoryId the repository ID to search with
	* @param start the lower bound of the range of repository entries to return
	* @param end the upper bound of the range of repository entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching repository entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.RepositoryEntry> findByRepositoryId(
		long repositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first repository entry in the ordered set where repositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param repositoryId the repository ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching repository entry
	* @throws com.liferay.portal.NoSuchRepositoryEntryException if a matching repository entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.RepositoryEntry findByRepositoryId_First(
		long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRepositoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last repository entry in the ordered set where repositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param repositoryId the repository ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching repository entry
	* @throws com.liferay.portal.NoSuchRepositoryEntryException if a matching repository entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.RepositoryEntry findByRepositoryId_Last(
		long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRepositoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the repository entries before and after the current repository entry in the ordered set where repositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param repositoryEntryId the primary key of the current repository entry
	* @param repositoryId the repository ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next repository entry
	* @throws com.liferay.portal.NoSuchRepositoryEntryException if a repository entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.RepositoryEntry[] findByRepositoryId_PrevAndNext(
		long repositoryEntryId, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchRepositoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the repository entry where repositoryId = &#63; and mappedId = &#63; or throws a {@link com.liferay.portal.NoSuchRepositoryEntryException} if it could not be found.
	*
	* @param repositoryId the repository ID to search with
	* @param mappedId the mapped ID to search with
	* @return the matching repository entry
	* @throws com.liferay.portal.NoSuchRepositoryEntryException if a matching repository entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.RepositoryEntry findByR_M(
		long repositoryId, java.lang.String mappedId)
		throws com.liferay.portal.NoSuchRepositoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the repository entry where repositoryId = &#63; and mappedId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param repositoryId the repository ID to search with
	* @param mappedId the mapped ID to search with
	* @return the matching repository entry, or <code>null</code> if a matching repository entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.RepositoryEntry fetchByR_M(
		long repositoryId, java.lang.String mappedId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the repository entry where repositoryId = &#63; and mappedId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param repositoryId the repository ID to search with
	* @param mappedId the mapped ID to search with
	* @return the matching repository entry, or <code>null</code> if a matching repository entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.RepositoryEntry fetchByR_M(
		long repositoryId, java.lang.String mappedId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the repository entries.
	*
	* @return the repository entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.RepositoryEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the repository entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of repository entries to return
	* @param end the upper bound of the range of repository entries to return (not inclusive)
	* @return the range of repository entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.RepositoryEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the repository entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of repository entries to return
	* @param end the upper bound of the range of repository entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of repository entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.RepositoryEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the repository entries where repositoryId = &#63; from the database.
	*
	* @param repositoryId the repository ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByRepositoryId(long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the repository entry where repositoryId = &#63; and mappedId = &#63; from the database.
	*
	* @param repositoryId the repository ID to search with
	* @param mappedId the mapped ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByR_M(long repositoryId, java.lang.String mappedId)
		throws com.liferay.portal.NoSuchRepositoryEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the repository entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the repository entries where repositoryId = &#63;.
	*
	* @param repositoryId the repository ID to search with
	* @return the number of matching repository entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByRepositoryId(long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the repository entries where repositoryId = &#63; and mappedId = &#63;.
	*
	* @param repositoryId the repository ID to search with
	* @param mappedId the mapped ID to search with
	* @return the number of matching repository entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByR_M(long repositoryId, java.lang.String mappedId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the repository entries.
	*
	* @return the number of repository entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public RepositoryEntry remove(RepositoryEntry repositoryEntry)
		throws SystemException;
}