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

package com.liferay.portal.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the LayoutRevision service. Represents a row in the &quot;LayoutRevision&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.LayoutRevisionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.LayoutRevisionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutRevision
 * @see com.liferay.portal.model.impl.LayoutRevisionImpl
 * @see com.liferay.portal.model.impl.LayoutRevisionModelImpl
 * @generated
 */
public interface LayoutRevisionModel extends BaseModel<LayoutRevision> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a layout revision model instance should use the {@link LayoutRevision} interface instead.
	 */

	/**
	 * Gets the primary key of this layout revision.
	 *
	 * @return the primary key of this layout revision
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this layout revision
	 *
	 * @param pk the primary key of this layout revision
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the layout revision ID of this layout revision.
	 *
	 * @return the layout revision ID of this layout revision
	 */
	public long getLayoutRevisionId();

	/**
	 * Sets the layout revision ID of this layout revision.
	 *
	 * @param layoutRevisionId the layout revision ID of this layout revision
	 */
	public void setLayoutRevisionId(long layoutRevisionId);

	/**
	 * Gets the group ID of this layout revision.
	 *
	 * @return the group ID of this layout revision
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this layout revision.
	 *
	 * @param groupId the group ID of this layout revision
	 */
	public void setGroupId(long groupId);

	/**
	 * Gets the company ID of this layout revision.
	 *
	 * @return the company ID of this layout revision
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this layout revision.
	 *
	 * @param companyId the company ID of this layout revision
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the user ID of this layout revision.
	 *
	 * @return the user ID of this layout revision
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this layout revision.
	 *
	 * @param userId the user ID of this layout revision
	 */
	public void setUserId(long userId);

	/**
	 * Gets the user uuid of this layout revision.
	 *
	 * @return the user uuid of this layout revision
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this layout revision.
	 *
	 * @param userUuid the user uuid of this layout revision
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Gets the user name of this layout revision.
	 *
	 * @return the user name of this layout revision
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this layout revision.
	 *
	 * @param userName the user name of this layout revision
	 */
	public void setUserName(String userName);

	/**
	 * Gets the create date of this layout revision.
	 *
	 * @return the create date of this layout revision
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this layout revision.
	 *
	 * @param createDate the create date of this layout revision
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Gets the modified date of this layout revision.
	 *
	 * @return the modified date of this layout revision
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this layout revision.
	 *
	 * @param modifiedDate the modified date of this layout revision
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Gets the layout set branch ID of this layout revision.
	 *
	 * @return the layout set branch ID of this layout revision
	 */
	public long getLayoutSetBranchId();

	/**
	 * Sets the layout set branch ID of this layout revision.
	 *
	 * @param layoutSetBranchId the layout set branch ID of this layout revision
	 */
	public void setLayoutSetBranchId(long layoutSetBranchId);

	/**
	 * Gets the parent layout revision ID of this layout revision.
	 *
	 * @return the parent layout revision ID of this layout revision
	 */
	public long getParentLayoutRevisionId();

	/**
	 * Sets the parent layout revision ID of this layout revision.
	 *
	 * @param parentLayoutRevisionId the parent layout revision ID of this layout revision
	 */
	public void setParentLayoutRevisionId(long parentLayoutRevisionId);

	/**
	 * Gets the head of this layout revision.
	 *
	 * @return the head of this layout revision
	 */
	public boolean getHead();

	/**
	 * Determines if this layout revision is head.
	 *
	 * @return <code>true</code> if this layout revision is head; <code>false</code> otherwise
	 */
	public boolean isHead();

	/**
	 * Sets whether this layout revision is head.
	 *
	 * @param head the head of this layout revision
	 */
	public void setHead(boolean head);

	/**
	 * Gets the plid of this layout revision.
	 *
	 * @return the plid of this layout revision
	 */
	public long getPlid();

	/**
	 * Sets the plid of this layout revision.
	 *
	 * @param plid the plid of this layout revision
	 */
	public void setPlid(long plid);

	/**
	 * Gets the name of this layout revision.
	 *
	 * @return the name of this layout revision
	 */
	public String getName();

	/**
	 * Gets the localized name of this layout revision. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale to get the localized name for
	 * @return the localized name of this layout revision
	 */
	public String getName(Locale locale);

	/**
	 * Gets the localized name of this layout revision, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local to get the localized name for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this layout revision. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Gets the localized name of this layout revision. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized name for
	 * @return the localized name of this layout revision
	 */
	public String getName(String languageId);

	/**
	 * Gets the localized name of this layout revision, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized name for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this layout revision
	 */
	public String getName(String languageId, boolean useDefault);

	/**
	 * Gets a map of the locales and localized name of this layout revision.
	 *
	 * @return the locales and localized name
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this layout revision.
	 *
	 * @param name the name of this layout revision
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this layout revision.
	 *
	 * @param name the localized name of this layout revision
	 * @param locale the locale to set the localized name for
	 */
	public void setName(String name, Locale locale);

	public void setName(String name, Locale locale, Locale defaultLocale);

	/**
	 * Sets the localized names of this layout revision from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this layout revision
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Gets the title of this layout revision.
	 *
	 * @return the title of this layout revision
	 */
	public String getTitle();

	/**
	 * Gets the localized title of this layout revision. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale to get the localized title for
	 * @return the localized title of this layout revision
	 */
	public String getTitle(Locale locale);

	/**
	 * Gets the localized title of this layout revision, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local to get the localized title for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this layout revision. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Gets the localized title of this layout revision. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized title for
	 * @return the localized title of this layout revision
	 */
	public String getTitle(String languageId);

	/**
	 * Gets the localized title of this layout revision, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized title for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this layout revision
	 */
	public String getTitle(String languageId, boolean useDefault);

	/**
	 * Gets a map of the locales and localized title of this layout revision.
	 *
	 * @return the locales and localized title
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this layout revision.
	 *
	 * @param title the title of this layout revision
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this layout revision.
	 *
	 * @param title the localized title of this layout revision
	 * @param locale the locale to set the localized title for
	 */
	public void setTitle(String title, Locale locale);

	public void setTitle(String title, Locale locale, Locale defaultLocale);

	/**
	 * Sets the localized titles of this layout revision from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this layout revision
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Gets the description of this layout revision.
	 *
	 * @return the description of this layout revision
	 */
	public String getDescription();

	/**
	 * Gets the localized description of this layout revision. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale to get the localized description for
	 * @return the localized description of this layout revision
	 */
	public String getDescription(Locale locale);

	/**
	 * Gets the localized description of this layout revision, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local to get the localized description for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this layout revision. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Gets the localized description of this layout revision. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized description for
	 * @return the localized description of this layout revision
	 */
	public String getDescription(String languageId);

	/**
	 * Gets the localized description of this layout revision, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized description for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this layout revision
	 */
	public String getDescription(String languageId, boolean useDefault);

	/**
	 * Gets a map of the locales and localized description of this layout revision.
	 *
	 * @return the locales and localized description
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this layout revision.
	 *
	 * @param description the description of this layout revision
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this layout revision.
	 *
	 * @param description the localized description of this layout revision
	 * @param locale the locale to set the localized description for
	 */
	public void setDescription(String description, Locale locale);

	public void setDescription(String description, Locale locale,
		Locale defaultLocale);

	/**
	 * Sets the localized descriptions of this layout revision from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this layout revision
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale);

	/**
	 * Gets the keywords of this layout revision.
	 *
	 * @return the keywords of this layout revision
	 */
	public String getKeywords();

	/**
	 * Gets the localized keywords of this layout revision. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale to get the localized keywords for
	 * @return the localized keywords of this layout revision
	 */
	public String getKeywords(Locale locale);

	/**
	 * Gets the localized keywords of this layout revision, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local to get the localized keywords for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized keywords of this layout revision. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	public String getKeywords(Locale locale, boolean useDefault);

	/**
	 * Gets the localized keywords of this layout revision. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized keywords for
	 * @return the localized keywords of this layout revision
	 */
	public String getKeywords(String languageId);

	/**
	 * Gets the localized keywords of this layout revision, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized keywords for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized keywords of this layout revision
	 */
	public String getKeywords(String languageId, boolean useDefault);

	/**
	 * Gets a map of the locales and localized keywords of this layout revision.
	 *
	 * @return the locales and localized keywords
	 */
	public Map<Locale, String> getKeywordsMap();

	/**
	 * Sets the keywords of this layout revision.
	 *
	 * @param keywords the keywords of this layout revision
	 */
	public void setKeywords(String keywords);

	/**
	 * Sets the localized keywords of this layout revision.
	 *
	 * @param keywords the localized keywords of this layout revision
	 * @param locale the locale to set the localized keywords for
	 */
	public void setKeywords(String keywords, Locale locale);

	public void setKeywords(String keywords, Locale locale, Locale defaultLocale);

	/**
	 * Sets the localized keywordses of this layout revision from the map of locales and localized keywordses.
	 *
	 * @param keywordsMap the locales and localized keywordses of this layout revision
	 */
	public void setKeywordsMap(Map<Locale, String> keywordsMap);

	public void setKeywordsMap(Map<Locale, String> keywordsMap,
		Locale defaultLocale);

	/**
	 * Gets the robots of this layout revision.
	 *
	 * @return the robots of this layout revision
	 */
	public String getRobots();

	/**
	 * Gets the localized robots of this layout revision. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale to get the localized robots for
	 * @return the localized robots of this layout revision
	 */
	public String getRobots(Locale locale);

	/**
	 * Gets the localized robots of this layout revision, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local to get the localized robots for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized robots of this layout revision. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	public String getRobots(Locale locale, boolean useDefault);

	/**
	 * Gets the localized robots of this layout revision. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized robots for
	 * @return the localized robots of this layout revision
	 */
	public String getRobots(String languageId);

	/**
	 * Gets the localized robots of this layout revision, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized robots for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized robots of this layout revision
	 */
	public String getRobots(String languageId, boolean useDefault);

	/**
	 * Gets a map of the locales and localized robots of this layout revision.
	 *
	 * @return the locales and localized robots
	 */
	public Map<Locale, String> getRobotsMap();

	/**
	 * Sets the robots of this layout revision.
	 *
	 * @param robots the robots of this layout revision
	 */
	public void setRobots(String robots);

	/**
	 * Sets the localized robots of this layout revision.
	 *
	 * @param robots the localized robots of this layout revision
	 * @param locale the locale to set the localized robots for
	 */
	public void setRobots(String robots, Locale locale);

	public void setRobots(String robots, Locale locale, Locale defaultLocale);

	/**
	 * Sets the localized robotses of this layout revision from the map of locales and localized robotses.
	 *
	 * @param robotsMap the locales and localized robotses of this layout revision
	 */
	public void setRobotsMap(Map<Locale, String> robotsMap);

	public void setRobotsMap(Map<Locale, String> robotsMap, Locale defaultLocale);

	/**
	 * Gets the type settings of this layout revision.
	 *
	 * @return the type settings of this layout revision
	 */
	@AutoEscape
	public String getTypeSettings();

	/**
	 * Sets the type settings of this layout revision.
	 *
	 * @param typeSettings the type settings of this layout revision
	 */
	public void setTypeSettings(String typeSettings);

	/**
	 * Gets the icon image of this layout revision.
	 *
	 * @return the icon image of this layout revision
	 */
	public boolean getIconImage();

	/**
	 * Determines if this layout revision is icon image.
	 *
	 * @return <code>true</code> if this layout revision is icon image; <code>false</code> otherwise
	 */
	public boolean isIconImage();

	/**
	 * Sets whether this layout revision is icon image.
	 *
	 * @param iconImage the icon image of this layout revision
	 */
	public void setIconImage(boolean iconImage);

	/**
	 * Gets the icon image ID of this layout revision.
	 *
	 * @return the icon image ID of this layout revision
	 */
	public long getIconImageId();

	/**
	 * Sets the icon image ID of this layout revision.
	 *
	 * @param iconImageId the icon image ID of this layout revision
	 */
	public void setIconImageId(long iconImageId);

	/**
	 * Gets the theme ID of this layout revision.
	 *
	 * @return the theme ID of this layout revision
	 */
	@AutoEscape
	public String getThemeId();

	/**
	 * Sets the theme ID of this layout revision.
	 *
	 * @param themeId the theme ID of this layout revision
	 */
	public void setThemeId(String themeId);

	/**
	 * Gets the color scheme ID of this layout revision.
	 *
	 * @return the color scheme ID of this layout revision
	 */
	@AutoEscape
	public String getColorSchemeId();

	/**
	 * Sets the color scheme ID of this layout revision.
	 *
	 * @param colorSchemeId the color scheme ID of this layout revision
	 */
	public void setColorSchemeId(String colorSchemeId);

	/**
	 * Gets the wap theme ID of this layout revision.
	 *
	 * @return the wap theme ID of this layout revision
	 */
	@AutoEscape
	public String getWapThemeId();

	/**
	 * Sets the wap theme ID of this layout revision.
	 *
	 * @param wapThemeId the wap theme ID of this layout revision
	 */
	public void setWapThemeId(String wapThemeId);

	/**
	 * Gets the wap color scheme ID of this layout revision.
	 *
	 * @return the wap color scheme ID of this layout revision
	 */
	@AutoEscape
	public String getWapColorSchemeId();

	/**
	 * Sets the wap color scheme ID of this layout revision.
	 *
	 * @param wapColorSchemeId the wap color scheme ID of this layout revision
	 */
	public void setWapColorSchemeId(String wapColorSchemeId);

	/**
	 * Gets the css of this layout revision.
	 *
	 * @return the css of this layout revision
	 */
	@AutoEscape
	public String getCss();

	/**
	 * Sets the css of this layout revision.
	 *
	 * @param css the css of this layout revision
	 */
	public void setCss(String css);

	/**
	 * Gets the status of this layout revision.
	 *
	 * @return the status of this layout revision
	 */
	public int getStatus();

	/**
	 * Sets the status of this layout revision.
	 *
	 * @param status the status of this layout revision
	 */
	public void setStatus(int status);

	/**
	 * Gets the status by user ID of this layout revision.
	 *
	 * @return the status by user ID of this layout revision
	 */
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this layout revision.
	 *
	 * @param statusByUserId the status by user ID of this layout revision
	 */
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Gets the status by user uuid of this layout revision.
	 *
	 * @return the status by user uuid of this layout revision
	 * @throws SystemException if a system exception occurred
	 */
	public String getStatusByUserUuid() throws SystemException;

	/**
	 * Sets the status by user uuid of this layout revision.
	 *
	 * @param statusByUserUuid the status by user uuid of this layout revision
	 */
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Gets the status by user name of this layout revision.
	 *
	 * @return the status by user name of this layout revision
	 */
	@AutoEscape
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this layout revision.
	 *
	 * @param statusByUserName the status by user name of this layout revision
	 */
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Gets the status date of this layout revision.
	 *
	 * @return the status date of this layout revision
	 */
	public Date getStatusDate();

	/**
	 * Sets the status date of this layout revision.
	 *
	 * @param statusDate the status date of this layout revision
	 */
	public void setStatusDate(Date statusDate);

	/**
	 * @deprecated {@link #isApproved}
	 */
	public boolean getApproved();

	/**
	 * Determines if this layout revision is approved.
	 *
	 * @return <code>true</code> if this layout revision is approved; <code>false</code> otherwise
	 */
	public boolean isApproved();

	/**
	 * Determines if this layout revision is a draft.
	 *
	 * @return <code>true</code> if this layout revision is a draft; <code>false</code> otherwise
	 */
	public boolean isDraft();

	/**
	 * Determines if this layout revision is expired.
	 *
	 * @return <code>true</code> if this layout revision is expired; <code>false</code> otherwise
	 */
	public boolean isExpired();

	/**
	 * Determines if this layout revision is pending.
	 *
	 * @return <code>true</code> if this layout revision is pending; <code>false</code> otherwise
	 */
	public boolean isPending();

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

	public int compareTo(LayoutRevision layoutRevision);

	public int hashCode();

	public LayoutRevision toEscapedModel();

	public String toString();

	public String toXmlString();
}