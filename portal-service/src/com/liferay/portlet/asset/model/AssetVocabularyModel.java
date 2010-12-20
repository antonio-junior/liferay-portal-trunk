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

package com.liferay.portlet.asset.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the AssetVocabulary service. Represents a row in the &quot;AssetVocabulary&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.asset.model.impl.AssetVocabularyModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.asset.model.impl.AssetVocabularyImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetVocabulary
 * @see com.liferay.portlet.asset.model.impl.AssetVocabularyImpl
 * @see com.liferay.portlet.asset.model.impl.AssetVocabularyModelImpl
 * @generated
 */
public interface AssetVocabularyModel extends BaseModel<AssetVocabulary> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a asset vocabulary model instance should use the {@link AssetVocabulary} interface instead.
	 */

	/**
	 * Gets the primary key of this asset vocabulary.
	 *
	 * @return the primary key of this asset vocabulary
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this asset vocabulary
	 *
	 * @param pk the primary key of this asset vocabulary
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the uuid of this asset vocabulary.
	 *
	 * @return the uuid of this asset vocabulary
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this asset vocabulary.
	 *
	 * @param uuid the uuid of this asset vocabulary
	 */
	public void setUuid(String uuid);

	/**
	 * Gets the vocabulary ID of this asset vocabulary.
	 *
	 * @return the vocabulary ID of this asset vocabulary
	 */
	public long getVocabularyId();

	/**
	 * Sets the vocabulary ID of this asset vocabulary.
	 *
	 * @param vocabularyId the vocabulary ID of this asset vocabulary
	 */
	public void setVocabularyId(long vocabularyId);

	/**
	 * Gets the group ID of this asset vocabulary.
	 *
	 * @return the group ID of this asset vocabulary
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this asset vocabulary.
	 *
	 * @param groupId the group ID of this asset vocabulary
	 */
	public void setGroupId(long groupId);

	/**
	 * Gets the company ID of this asset vocabulary.
	 *
	 * @return the company ID of this asset vocabulary
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this asset vocabulary.
	 *
	 * @param companyId the company ID of this asset vocabulary
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the user ID of this asset vocabulary.
	 *
	 * @return the user ID of this asset vocabulary
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this asset vocabulary.
	 *
	 * @param userId the user ID of this asset vocabulary
	 */
	public void setUserId(long userId);

	/**
	 * Gets the user uuid of this asset vocabulary.
	 *
	 * @return the user uuid of this asset vocabulary
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this asset vocabulary.
	 *
	 * @param userUuid the user uuid of this asset vocabulary
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Gets the user name of this asset vocabulary.
	 *
	 * @return the user name of this asset vocabulary
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this asset vocabulary.
	 *
	 * @param userName the user name of this asset vocabulary
	 */
	public void setUserName(String userName);

	/**
	 * Gets the create date of this asset vocabulary.
	 *
	 * @return the create date of this asset vocabulary
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this asset vocabulary.
	 *
	 * @param createDate the create date of this asset vocabulary
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Gets the modified date of this asset vocabulary.
	 *
	 * @return the modified date of this asset vocabulary
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this asset vocabulary.
	 *
	 * @param modifiedDate the modified date of this asset vocabulary
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Gets the name of this asset vocabulary.
	 *
	 * @return the name of this asset vocabulary
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this asset vocabulary.
	 *
	 * @param name the name of this asset vocabulary
	 */
	public void setName(String name);

	/**
	 * Gets the title of this asset vocabulary.
	 *
	 * @return the title of this asset vocabulary
	 */
	public String getTitle();

	/**
	 * Gets the localized title of this asset vocabulary. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale to get the localized title for
	 * @return the localized title of this asset vocabulary
	 */
	public String getTitle(Locale locale);

	/**
	 * Gets the localized title of this asset vocabulary, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local to get the localized title for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this asset vocabulary. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Gets the localized title of this asset vocabulary. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized title for
	 * @return the localized title of this asset vocabulary
	 */
	public String getTitle(String languageId);

	/**
	 * Gets the localized title of this asset vocabulary, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized title for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this asset vocabulary
	 */
	public String getTitle(String languageId, boolean useDefault);

	/**
	 * Gets a map of the locales and localized title of this asset vocabulary.
	 *
	 * @return the locales and localized title
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this asset vocabulary.
	 *
	 * @param title the title of this asset vocabulary
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this asset vocabulary.
	 *
	 * @param locale the locale to set the localized title for
	 * @param title the localized title of this asset vocabulary
	 */
	public void setTitle(Locale locale, String title);

	/**
	 * Sets the localized titles of this asset vocabulary from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this asset vocabulary
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Gets the description of this asset vocabulary.
	 *
	 * @return the description of this asset vocabulary
	 */
	public String getDescription();

	/**
	 * Gets the localized description of this asset vocabulary. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale to get the localized description for
	 * @return the localized description of this asset vocabulary
	 */
	public String getDescription(Locale locale);

	/**
	 * Gets the localized description of this asset vocabulary, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local to get the localized description for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this asset vocabulary. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Gets the localized description of this asset vocabulary. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized description for
	 * @return the localized description of this asset vocabulary
	 */
	public String getDescription(String languageId);

	/**
	 * Gets the localized description of this asset vocabulary, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized description for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this asset vocabulary
	 */
	public String getDescription(String languageId, boolean useDefault);

	/**
	 * Gets a map of the locales and localized description of this asset vocabulary.
	 *
	 * @return the locales and localized description
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this asset vocabulary.
	 *
	 * @param description the description of this asset vocabulary
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this asset vocabulary.
	 *
	 * @param locale the locale to set the localized description for
	 * @param description the localized description of this asset vocabulary
	 */
	public void setDescription(Locale locale, String description);

	/**
	 * Sets the localized descriptions of this asset vocabulary from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this asset vocabulary
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Gets the settings of this asset vocabulary.
	 *
	 * @return the settings of this asset vocabulary
	 */
	@AutoEscape
	public String getSettings();

	/**
	 * Sets the settings of this asset vocabulary.
	 *
	 * @param settings the settings of this asset vocabulary
	 */
	public void setSettings(String settings);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(AssetVocabulary assetVocabulary);

	public int hashCode();

	public AssetVocabulary toEscapedModel();

	public String toString();

	public String toXmlString();
}