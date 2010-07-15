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

package com.liferay.portlet.expando.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoValue;

import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       ExpandoValuePersistence
 * @see       ExpandoValuePersistenceImpl
 * @generated
 */
public class ExpandoValueUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(ExpandoValue expandoValue) {
		getPersistence().clearCache(expandoValue);
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
	public static List<ExpandoValue> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ExpandoValue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ExpandoValue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static ExpandoValue remove(ExpandoValue expandoValue)
		throws SystemException {
		return getPersistence().remove(expandoValue);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static ExpandoValue update(ExpandoValue expandoValue, boolean merge)
		throws SystemException {
		return getPersistence().update(expandoValue, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static ExpandoValue update(ExpandoValue expandoValue, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(expandoValue, merge, serviceContext);
	}

	public static void cacheResult(
		com.liferay.portlet.expando.model.ExpandoValue expandoValue) {
		getPersistence().cacheResult(expandoValue);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portlet.expando.model.ExpandoValue> expandoValues) {
		getPersistence().cacheResult(expandoValues);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue create(
		long valueId) {
		return getPersistence().create(valueId);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue remove(
		long valueId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence().remove(valueId);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue updateImpl(
		com.liferay.portlet.expando.model.ExpandoValue expandoValue,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(expandoValue, merge);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByPrimaryKey(
		long valueId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence().findByPrimaryKey(valueId);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue fetchByPrimaryKey(
		long valueId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(valueId);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByTableId(
		long tableId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTableId(tableId);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByTableId(
		long tableId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTableId(tableId, start, end);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByTableId(
		long tableId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTableId(tableId, start, end, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByTableId_First(
		long tableId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence().findByTableId_First(tableId, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByTableId_Last(
		long tableId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence().findByTableId_Last(tableId, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue[] findByTableId_PrevAndNext(
		long valueId, long tableId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByTableId_PrevAndNext(valueId, tableId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByColumnId(
		long columnId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByColumnId(columnId);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByColumnId(
		long columnId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByColumnId(columnId, start, end);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByColumnId(
		long columnId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByColumnId(columnId, start, end, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByColumnId_First(
		long columnId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence().findByColumnId_First(columnId, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByColumnId_Last(
		long columnId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence().findByColumnId_Last(columnId, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue[] findByColumnId_PrevAndNext(
		long valueId, long columnId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByColumnId_PrevAndNext(valueId, columnId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByRowId(
		long rowId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByRowId(rowId);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByRowId(
		long rowId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByRowId(rowId, start, end);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByRowId(
		long rowId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByRowId(rowId, start, end, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByRowId_First(
		long rowId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence().findByRowId_First(rowId, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByRowId_Last(
		long rowId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence().findByRowId_Last(rowId, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue[] findByRowId_PrevAndNext(
		long valueId, long rowId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByRowId_PrevAndNext(valueId, rowId, orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByT_C(
		long tableId, long columnId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_C(tableId, columnId);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByT_C(
		long tableId, long columnId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_C(tableId, columnId, start, end);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByT_C(
		long tableId, long columnId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_C(tableId, columnId, start, end, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByT_C_First(
		long tableId, long columnId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByT_C_First(tableId, columnId, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByT_C_Last(
		long tableId, long columnId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByT_C_Last(tableId, columnId, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue[] findByT_C_PrevAndNext(
		long valueId, long tableId, long columnId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByT_C_PrevAndNext(valueId, tableId, columnId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByT_CPK(
		long tableId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_CPK(tableId, classPK);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByT_CPK(
		long tableId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_CPK(tableId, classPK, start, end);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByT_CPK(
		long tableId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_CPK(tableId, classPK, start, end, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByT_CPK_First(
		long tableId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByT_CPK_First(tableId, classPK, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByT_CPK_Last(
		long tableId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByT_CPK_Last(tableId, classPK, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue[] findByT_CPK_PrevAndNext(
		long valueId, long tableId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByT_CPK_PrevAndNext(valueId, tableId, classPK,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByT_R(
		long tableId, long rowId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_R(tableId, rowId);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByT_R(
		long tableId, long rowId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_R(tableId, rowId, start, end);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByT_R(
		long tableId, long rowId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_R(tableId, rowId, start, end, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByT_R_First(
		long tableId, long rowId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByT_R_First(tableId, rowId, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByT_R_Last(
		long tableId, long rowId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence().findByT_R_Last(tableId, rowId, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue[] findByT_R_PrevAndNext(
		long valueId, long tableId, long rowId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByT_R_PrevAndNext(valueId, tableId, rowId,
			orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByC_R(
		long columnId, long rowId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence().findByC_R(columnId, rowId);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue fetchByC_R(
		long columnId, long rowId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_R(columnId, rowId);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue fetchByC_R(
		long columnId, long rowId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_R(columnId, rowId, retrieveFromCache);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue[] findByC_C_PrevAndNext(
		long valueId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByC_C_PrevAndNext(valueId, classNameId, classPK,
			orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByT_C_C(
		long tableId, long columnId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence().findByT_C_C(tableId, columnId, classPK);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue fetchByT_C_C(
		long tableId, long columnId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByT_C_C(tableId, columnId, classPK);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue fetchByT_C_C(
		long tableId, long columnId, long classPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByT_C_C(tableId, columnId, classPK, retrieveFromCache);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByT_C_D(
		long tableId, long columnId, java.lang.String data)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_C_D(tableId, columnId, data);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByT_C_D(
		long tableId, long columnId, java.lang.String data, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_C_D(tableId, columnId, data, start, end);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findByT_C_D(
		long tableId, long columnId, java.lang.String data, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_C_D(tableId, columnId, data, start, end,
			orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByT_C_D_First(
		long tableId, long columnId, java.lang.String data,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByT_C_D_First(tableId, columnId, data, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue findByT_C_D_Last(
		long tableId, long columnId, java.lang.String data,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByT_C_D_Last(tableId, columnId, data, orderByComparator);
	}

	public static com.liferay.portlet.expando.model.ExpandoValue[] findByT_C_D_PrevAndNext(
		long valueId, long tableId, long columnId, java.lang.String data,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		return getPersistence()
				   .findByT_C_D_PrevAndNext(valueId, tableId, columnId, data,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portlet.expando.model.ExpandoValue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByTableId(long tableId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTableId(tableId);
	}

	public static void removeByColumnId(long columnId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByColumnId(columnId);
	}

	public static void removeByRowId(long rowId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByRowId(rowId);
	}

	public static void removeByT_C(long tableId, long columnId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByT_C(tableId, columnId);
	}

	public static void removeByT_CPK(long tableId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByT_CPK(tableId, classPK);
	}

	public static void removeByT_R(long tableId, long rowId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByT_R(tableId, rowId);
	}

	public static void removeByC_R(long columnId, long rowId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		getPersistence().removeByC_R(columnId, rowId);
	}

	public static void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	public static void removeByT_C_C(long tableId, long columnId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.expando.NoSuchValueException {
		getPersistence().removeByT_C_C(tableId, columnId, classPK);
	}

	public static void removeByT_C_D(long tableId, long columnId,
		java.lang.String data)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByT_C_D(tableId, columnId, data);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByTableId(long tableId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTableId(tableId);
	}

	public static int countByColumnId(long columnId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByColumnId(columnId);
	}

	public static int countByRowId(long rowId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByRowId(rowId);
	}

	public static int countByT_C(long tableId, long columnId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByT_C(tableId, columnId);
	}

	public static int countByT_CPK(long tableId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByT_CPK(tableId, classPK);
	}

	public static int countByT_R(long tableId, long rowId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByT_R(tableId, rowId);
	}

	public static int countByC_R(long columnId, long rowId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_R(columnId, rowId);
	}

	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	public static int countByT_C_C(long tableId, long columnId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByT_C_C(tableId, columnId, classPK);
	}

	public static int countByT_C_D(long tableId, long columnId,
		java.lang.String data)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByT_C_D(tableId, columnId, data);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ExpandoValuePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ExpandoValuePersistence)PortalBeanLocatorUtil.locate(ExpandoValuePersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(ExpandoValuePersistence persistence) {
		_persistence = persistence;
	}

	private static ExpandoValuePersistence _persistence;
}