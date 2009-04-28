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

package com.liferay.portlet.categories.service.base;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.counter.service.CounterService;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.categories.model.CategoriesProperty;
import com.liferay.portlet.categories.service.CategoriesEntryLocalService;
import com.liferay.portlet.categories.service.CategoriesEntryService;
import com.liferay.portlet.categories.service.CategoriesPropertyLocalService;
import com.liferay.portlet.categories.service.CategoriesPropertyService;
import com.liferay.portlet.categories.service.CategoriesVocabularyLocalService;
import com.liferay.portlet.categories.service.CategoriesVocabularyService;
import com.liferay.portlet.categories.service.persistence.CategoriesEntryFinder;
import com.liferay.portlet.categories.service.persistence.CategoriesEntryPersistence;
import com.liferay.portlet.categories.service.persistence.CategoriesPropertyFinder;
import com.liferay.portlet.categories.service.persistence.CategoriesPropertyPersistence;
import com.liferay.portlet.categories.service.persistence.CategoriesVocabularyPersistence;

import java.util.List;

/**
 * <a href="CategoriesPropertyLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class CategoriesPropertyLocalServiceBaseImpl
	implements CategoriesPropertyLocalService {
	public CategoriesProperty addCategoriesProperty(
		CategoriesProperty categoriesProperty) throws SystemException {
		categoriesProperty.setNew(true);

		return categoriesPropertyPersistence.update(categoriesProperty, false);
	}

	public CategoriesProperty createCategoriesProperty(long propertyId) {
		return categoriesPropertyPersistence.create(propertyId);
	}

	public void deleteCategoriesProperty(long propertyId)
		throws PortalException, SystemException {
		categoriesPropertyPersistence.remove(propertyId);
	}

	public void deleteCategoriesProperty(CategoriesProperty categoriesProperty)
		throws SystemException {
		categoriesPropertyPersistence.remove(categoriesProperty);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return categoriesPropertyPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return categoriesPropertyPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public CategoriesProperty getCategoriesProperty(long propertyId)
		throws PortalException, SystemException {
		return categoriesPropertyPersistence.findByPrimaryKey(propertyId);
	}

	public List<CategoriesProperty> getCategoriesProperties(int start, int end)
		throws SystemException {
		return categoriesPropertyPersistence.findAll(start, end);
	}

	public int getCategoriesPropertiesCount() throws SystemException {
		return categoriesPropertyPersistence.countAll();
	}

	public CategoriesProperty updateCategoriesProperty(
		CategoriesProperty categoriesProperty) throws SystemException {
		categoriesProperty.setNew(false);

		return categoriesPropertyPersistence.update(categoriesProperty, true);
	}

	public CategoriesProperty updateCategoriesProperty(
		CategoriesProperty categoriesProperty, boolean merge)
		throws SystemException {
		categoriesProperty.setNew(false);

		return categoriesPropertyPersistence.update(categoriesProperty, merge);
	}

	public CategoriesEntryLocalService getCategoriesEntryLocalService() {
		return categoriesEntryLocalService;
	}

	public void setCategoriesEntryLocalService(
		CategoriesEntryLocalService categoriesEntryLocalService) {
		this.categoriesEntryLocalService = categoriesEntryLocalService;
	}

	public CategoriesEntryService getCategoriesEntryService() {
		return categoriesEntryService;
	}

	public void setCategoriesEntryService(
		CategoriesEntryService categoriesEntryService) {
		this.categoriesEntryService = categoriesEntryService;
	}

	public CategoriesEntryPersistence getCategoriesEntryPersistence() {
		return categoriesEntryPersistence;
	}

	public void setCategoriesEntryPersistence(
		CategoriesEntryPersistence categoriesEntryPersistence) {
		this.categoriesEntryPersistence = categoriesEntryPersistence;
	}

	public CategoriesEntryFinder getCategoriesEntryFinder() {
		return categoriesEntryFinder;
	}

	public void setCategoriesEntryFinder(
		CategoriesEntryFinder categoriesEntryFinder) {
		this.categoriesEntryFinder = categoriesEntryFinder;
	}

	public CategoriesPropertyLocalService getCategoriesPropertyLocalService() {
		return categoriesPropertyLocalService;
	}

	public void setCategoriesPropertyLocalService(
		CategoriesPropertyLocalService categoriesPropertyLocalService) {
		this.categoriesPropertyLocalService = categoriesPropertyLocalService;
	}

	public CategoriesPropertyService getCategoriesPropertyService() {
		return categoriesPropertyService;
	}

	public void setCategoriesPropertyService(
		CategoriesPropertyService categoriesPropertyService) {
		this.categoriesPropertyService = categoriesPropertyService;
	}

	public CategoriesPropertyPersistence getCategoriesPropertyPersistence() {
		return categoriesPropertyPersistence;
	}

	public void setCategoriesPropertyPersistence(
		CategoriesPropertyPersistence categoriesPropertyPersistence) {
		this.categoriesPropertyPersistence = categoriesPropertyPersistence;
	}

	public CategoriesPropertyFinder getCategoriesPropertyFinder() {
		return categoriesPropertyFinder;
	}

	public void setCategoriesPropertyFinder(
		CategoriesPropertyFinder categoriesPropertyFinder) {
		this.categoriesPropertyFinder = categoriesPropertyFinder;
	}

	public CategoriesVocabularyLocalService getCategoriesVocabularyLocalService() {
		return categoriesVocabularyLocalService;
	}

	public void setCategoriesVocabularyLocalService(
		CategoriesVocabularyLocalService categoriesVocabularyLocalService) {
		this.categoriesVocabularyLocalService = categoriesVocabularyLocalService;
	}

	public CategoriesVocabularyService getCategoriesVocabularyService() {
		return categoriesVocabularyService;
	}

	public void setCategoriesVocabularyService(
		CategoriesVocabularyService categoriesVocabularyService) {
		this.categoriesVocabularyService = categoriesVocabularyService;
	}

	public CategoriesVocabularyPersistence getCategoriesVocabularyPersistence() {
		return categoriesVocabularyPersistence;
	}

	public void setCategoriesVocabularyPersistence(
		CategoriesVocabularyPersistence categoriesVocabularyPersistence) {
		this.categoriesVocabularyPersistence = categoriesVocabularyPersistence;
	}

	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	public CounterService getCounterService() {
		return counterService;
	}

	public void setCounterService(CounterService counterService) {
		this.counterService = counterService;
	}

	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public UserFinder getUserFinder() {
		return userFinder;
	}

	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	@BeanReference(name = "com.liferay.portlet.categories.service.CategoriesEntryLocalService.impl")
	protected CategoriesEntryLocalService categoriesEntryLocalService;
	@BeanReference(name = "com.liferay.portlet.categories.service.CategoriesEntryService.impl")
	protected CategoriesEntryService categoriesEntryService;
	@BeanReference(name = "com.liferay.portlet.categories.service.persistence.CategoriesEntryPersistence.impl")
	protected CategoriesEntryPersistence categoriesEntryPersistence;
	@BeanReference(name = "com.liferay.portlet.categories.service.persistence.CategoriesEntryFinder.impl")
	protected CategoriesEntryFinder categoriesEntryFinder;
	@BeanReference(name = "com.liferay.portlet.categories.service.CategoriesPropertyLocalService.impl")
	protected CategoriesPropertyLocalService categoriesPropertyLocalService;
	@BeanReference(name = "com.liferay.portlet.categories.service.CategoriesPropertyService.impl")
	protected CategoriesPropertyService categoriesPropertyService;
	@BeanReference(name = "com.liferay.portlet.categories.service.persistence.CategoriesPropertyPersistence.impl")
	protected CategoriesPropertyPersistence categoriesPropertyPersistence;
	@BeanReference(name = "com.liferay.portlet.categories.service.persistence.CategoriesPropertyFinder.impl")
	protected CategoriesPropertyFinder categoriesPropertyFinder;
	@BeanReference(name = "com.liferay.portlet.categories.service.CategoriesVocabularyLocalService.impl")
	protected CategoriesVocabularyLocalService categoriesVocabularyLocalService;
	@BeanReference(name = "com.liferay.portlet.categories.service.CategoriesVocabularyService.impl")
	protected CategoriesVocabularyService categoriesVocabularyService;
	@BeanReference(name = "com.liferay.portlet.categories.service.persistence.CategoriesVocabularyPersistence.impl")
	protected CategoriesVocabularyPersistence categoriesVocabularyPersistence;
	@BeanReference(name = "com.liferay.counter.service.CounterLocalService.impl")
	protected CounterLocalService counterLocalService;
	@BeanReference(name = "com.liferay.counter.service.CounterService.impl")
	protected CounterService counterService;
	@BeanReference(name = "com.liferay.portal.service.UserLocalService.impl")
	protected UserLocalService userLocalService;
	@BeanReference(name = "com.liferay.portal.service.UserService.impl")
	protected UserService userService;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence.impl")
	protected UserPersistence userPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserFinder.impl")
	protected UserFinder userFinder;
}