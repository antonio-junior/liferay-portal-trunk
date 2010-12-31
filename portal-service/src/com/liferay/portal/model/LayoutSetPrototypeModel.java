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
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the LayoutSetPrototype service. Represents a row in the &quot;LayoutSetPrototype&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.LayoutSetPrototypeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetPrototype
 * @see com.liferay.portal.model.impl.LayoutSetPrototypeImpl
 * @see com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl
 * @generated
 */
public interface LayoutSetPrototypeModel extends BaseModel<LayoutSetPrototype> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a layout set prototype model instance should use the {@link LayoutSetPrototype} interface instead.
	 */

	/**
	 * Gets the primary key of this layout set prototype.
	 *
	 * @return the primary key of this layout set prototype
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this layout set prototype
	 *
	 * @param pk the primary key of this layout set prototype
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the layout set prototype ID of this layout set prototype.
	 *
	 * @return the layout set prototype ID of this layout set prototype
	 */
	public long getLayoutSetPrototypeId();

	/**
	 * Sets the layout set prototype ID of this layout set prototype.
	 *
	 * @param layoutSetPrototypeId the layout set prototype ID of this layout set prototype
	 */
	public void setLayoutSetPrototypeId(long layoutSetPrototypeId);

	/**
	 * Gets the company ID of this layout set prototype.
	 *
	 * @return the company ID of this layout set prototype
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this layout set prototype.
	 *
	 * @param companyId the company ID of this layout set prototype
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the name of this layout set prototype.
	 *
	 * @return the name of this layout set prototype
	 */
	public String getName();

	/**
	 * Gets the localized name of this layout set prototype. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale to get the localized name for
	 * @return the localized name of this layout set prototype
	 */
	public String getName(Locale locale);

	/**
	 * Gets the localized name of this layout set prototype, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local to get the localized name for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this layout set prototype. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Gets the localized name of this layout set prototype. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized name for
	 * @return the localized name of this layout set prototype
	 */
	public String getName(String languageId);

	/**
	 * Gets the localized name of this layout set prototype, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the id of the language to get the localized name for
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this layout set prototype
	 */
	public String getName(String languageId, boolean useDefault);

	/**
	 * Gets a map of the locales and localized name of this layout set prototype.
	 *
	 * @return the locales and localized name
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this layout set prototype.
	 *
	 * @param name the name of this layout set prototype
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this layout set prototype.
	 *
	 * @param locale the locale to set the localized name for
	 * @param name the localized name of this layout set prototype
	 */
	public void setName(Locale locale, String name);

	/**
	 * Sets the localized names of this layout set prototype from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this layout set prototype
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Gets the description of this layout set prototype.
	 *
	 * @return the description of this layout set prototype
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this layout set prototype.
	 *
	 * @param description the description of this layout set prototype
	 */
	public void setDescription(String description);

	/**
	 * Gets the settings of this layout set prototype.
	 *
	 * @return the settings of this layout set prototype
	 */
	@AutoEscape
	public String getSettings();

	/**
	 * Sets the settings of this layout set prototype.
	 *
	 * @param settings the settings of this layout set prototype
	 */
	public void setSettings(String settings);

	/**
	 * Gets the active of this layout set prototype.
	 *
	 * @return the active of this layout set prototype
	 */
	public boolean getActive();

	/**
	 * Determines if this layout set prototype is active.
	 *
	 * @return <code>true</code> if this layout set prototype is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this layout set prototype is active.
	 *
	 * @param active the active of this layout set prototype
	 */
	public void setActive(boolean active);

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

	public int compareTo(LayoutSetPrototype layoutSetPrototype);

	public int hashCode();

	public LayoutSetPrototype toEscapedModel();

	public String toString();

	public String toXmlString();
}