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

package com.liferay.portal.model.impl;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletLayoutListener;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.LayoutTemplate;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.LayoutTypePortletConstants;
import com.liferay.portal.model.PersonalizedPages;
import com.liferay.portal.model.Plugin;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.LayoutTemplateLocalServiceUtil;
import com.liferay.portal.service.PluginSettingLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.PortalPreferences;
import com.liferay.util.JS;
import com.liferay.util.PwdGenerator;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Berentey Zsolt
 * @author Jorge Ferrer
 */
public class LayoutTypePortletImpl
	extends LayoutTypeImpl implements LayoutTypePortlet {

	public static String getFullInstanceSeparator() {
		String instanceId = PwdGenerator.getPassword(
			PwdGenerator.KEY1 + PwdGenerator.KEY2 + PwdGenerator.KEY3, 4);

		return PortletConstants.INSTANCE_SEPARATOR + instanceId;
	}

	public static Layout getTemplateLayout(Layout layout) {
		try {
			LayoutSet layoutSet = layout.getLayoutSet();

			UnicodeProperties settingsProperties =
				layoutSet.getSettingsProperties();

			String layoutSetPrototypeUuid = settingsProperties.getProperty(
				"layoutSetPrototypeUuid");

			if (Validator.isNull(layoutSetPrototypeUuid)) {
				return null;
			}

			LayoutSetPrototype layoutSetPrototype =
				LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototypeByUuid(
					layoutSetPrototypeUuid);

			Group group = layoutSetPrototype.getGroup();

			return LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
				layout.getUuid(), group.getGroupId());
		}
		catch (NoSuchLayoutException nsle) {
			_log.error(nsle, nsle);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	public LayoutTypePortletImpl(Layout layout) {
		super(layout);

		_templateLayout = getTemplateLayout(layout);
	}

	public void addModeAboutPortletId(String portletId) {
		removeModesPortletId(portletId);
		setModeAbout(StringUtil.add(getModeAbout(), portletId));
	}

	public void addModeConfigPortletId(String portletId) {
		removeModesPortletId(portletId);
		setModeConfig(StringUtil.add(getModeConfig(), portletId));
	}

	public void addModeEditDefaultsPortletId(String portletId) {
		removeModesPortletId(portletId);
		setModeEditDefaults(StringUtil.add(getModeEditDefaults(), portletId));
	}

	public void addModeEditGuestPortletId(String portletId) {
		removeModesPortletId(portletId);
		setModeEditGuest(StringUtil.add(getModeEditGuest(), portletId));
	}

	public void addModeEditPortletId(String portletId) {
		removeModesPortletId(portletId);
		setModeEdit(StringUtil.add(getModeEdit(), portletId));
	}

	public void addModeHelpPortletId(String portletId) {
		removeModesPortletId(portletId);
		setModeHelp(StringUtil.add(getModeHelp(), portletId));
	}

	public void addModePreviewPortletId(String portletId) {
		removeModesPortletId(portletId);
		setModePreview(StringUtil.add(getModePreview(), portletId));
	}

	public void addModePrintPortletId(String portletId) {
		removeModesPortletId(portletId);
		setModePrint(StringUtil.add(getModePrint(), portletId));
	}

	public String addPortletId(long userId, String portletId)
		throws PortalException, SystemException {

		return addPortletId(userId, portletId, true);
	}

	public String addPortletId(
			long userId, String portletId, boolean checkPermission)
		throws PortalException, SystemException {

		return addPortletId(userId, portletId, null, -1, checkPermission);
	}

	public String addPortletId(
			long userId, String portletId, String columnId, int columnPos)
		throws PortalException, SystemException {

		return addPortletId(userId, portletId, columnId, columnPos, true);
	}

	public String addPortletId(
			long userId, String portletId, String columnId, int columnPos,
			boolean checkPermission)
		throws PortalException, SystemException {

		portletId = JS.getSafeName(portletId);

		Layout layout = getLayout();

		Portlet portlet = null;

		try {
			portlet = PortletLocalServiceUtil.getPortletById(
				layout.getCompanyId(), portletId);

			if (portlet == null) {
				_log.error(
					"Portlet " + portletId +
						" cannot be added because it is not registered");

				return null;
			}

			if (checkPermission && !portlet.hasAddPortletPermission(userId)) {
				return null;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (portlet.isSystem()) {
			return null;
		}

		if ((portlet.isInstanceable()) &&
			(PortletConstants.getInstanceId(portlet.getPortletId()) == null)) {

			portletId = portletId + getFullInstanceSeparator();
		}

		if (hasPortletId(portletId)) {
			return null;
		}

		if (columnId == null) {
			LayoutTemplate layoutTemplate = getLayoutTemplate();

			List<String> columns = layoutTemplate.getColumns();

			if (columns.size() > 0) {
				columnId = columns.get(0);
			}
		}

		if (columnId != null) {
			if (isPersonalizable() && isColumnDisabled(columnId)) {
				return null;
			}

			String columnValue = StringPool.BLANK;

			if (hasUserPreferenes()) {
				columnValue = getUserPreference(columnId);
			}
			else {
				columnValue = getTypeSettingsProperties().getProperty(
					columnId);
			}

			if ((columnValue == null) &&
				(columnId.startsWith(_NESTED_PORTLETS_NAMESPACE))) {

				addNestedColumn(columnId);
			}

			if (columnPos >= 0) {
				List<String> portletIds = ListUtil.fromArray(
					StringUtil.split(columnValue));

				if (columnPos <= portletIds.size()) {
					portletIds.add(columnPos, portletId);
				}
				else {
					portletIds.add(portletId);
				}

				columnValue = StringUtil.merge(portletIds);
			}
			else {
				columnValue = StringUtil.add(columnValue, portletId);
			}

			if (hasUserPreferenes()) {
				setUserPreference(columnId, columnValue);
			}
			else {
				getTypeSettingsProperties().setProperty(columnId, columnValue);
			}
		}

		try {
			PortletLayoutListener portletLayoutListener =
				portlet.getPortletLayoutListenerInstance();

			if (_enablePortletLayoutListener &&
				(portletLayoutListener != null)) {

				portletLayoutListener.onAddToLayout(
					portletId, layout.getPlid());
			}
		}
		catch (Exception e) {
			_log.error("Unable to fire portlet layout listener event", e);
		}

		return portletId;
	}

	public void addPortletIds(
			long userId, String[] portletIds, boolean checkPermission)
		throws PortalException, SystemException {

		for (String portletId : portletIds) {
			addPortletId(userId, portletId, checkPermission);
		}
	}

	public void addPortletIds(
			long userId, String[] portletIds, String columnId,
			boolean checkPermission)
		throws PortalException, SystemException {

		for (String portletId : portletIds) {
			addPortletId(userId, portletId, columnId, -1, checkPermission);
		}
	}

	public void addStateMaxPortletId(String portletId) {
		removeStatesPortletId(portletId);
		//setStateMax(StringUtil.add(getStateMax(), portletId));
		setStateMax(StringUtil.add(StringPool.BLANK, portletId));
	}

	public void addStateMinPortletId(String portletId) {
		removeStateMaxPortletId(portletId);
		setStateMin(StringUtil.add(getStateMin(), portletId));
	}

	public List<Portlet> addStaticPortlets(
		List<Portlet> portlets, List<Portlet> startPortlets,
		List<Portlet> endPortlets) {

		// Return the original array of portlets if no static portlets are
		// specified

		if (startPortlets == null) {
			startPortlets = new ArrayList<Portlet>();
		}

		if (endPortlets == null) {
			endPortlets = new ArrayList<Portlet>();
		}

		if ((startPortlets.isEmpty()) && (endPortlets.isEmpty())) {
			return portlets;
		}

		// New array of portlets that contain the static portlets

		List<Portlet> list = new ArrayList<Portlet>(
			portlets.size() + startPortlets.size() + endPortlets.size());

		if (!startPortlets.isEmpty()) {
			list.addAll(startPortlets);
		}

		for (int i = 0; i < portlets.size(); i++) {
			Portlet portlet = portlets.get(i);

			// Add the portlet if and only if it is not also a static portlet

			if (!startPortlets.contains(portlet) &&
				!endPortlets.contains(portlet)) {

				list.add(portlet);
			}
		}

		if (!endPortlets.isEmpty()) {
			list.addAll(endPortlets);
		}

		return list;
	}

	public List<Portlet> getAllPortlets()
		throws PortalException, SystemException {

		List<Portlet> portlets = new ArrayList<Portlet>();

		List<String> columns = getColumns();

		for (int i = 0; i < columns.size(); i++) {
			String columnId = columns.get(i);

			portlets.addAll(getAllPortlets(columnId));
		}

		List<Portlet> staticPortlets = getStaticPortlets(
			PropsKeys.LAYOUT_STATIC_PORTLETS_ALL);

		return addStaticPortlets(portlets, staticPortlets, null);
	}

	public List<Portlet> getAllPortlets(String columnId)
		throws PortalException, SystemException {

		String columnValue = StringPool.BLANK;

		if (isPersonalizable() && isColumnDisabled(columnId) && hasTemplate()) {
			columnValue = getTemplateProperty(columnId);
		}
		else if (isPersonalizable() && !isColumnDisabled(columnId) &&
				 hasUserPreferenes()) {

			columnValue = getUserPreference(columnId);
		}
		else {
			columnValue = getTypeSettingsProperties().getProperty(columnId);
		}

		String[] portletIds = StringUtil.split(columnValue);

		List<Portlet> portlets = new ArrayList<Portlet>(portletIds.length);

		for (String portletId : portletIds) {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				getCompanyId(), portletId);

			if (portlet != null) {
				portlets.add(portlet);
			}
		}

		List<Portlet> startPortlets = getStaticPortlets(
			PropsKeys.LAYOUT_STATIC_PORTLETS_START + columnId);

		List<Portlet> endPortlets = getStaticPortlets(
			PropsKeys.LAYOUT_STATIC_PORTLETS_END + columnId);

		return addStaticPortlets(portlets, startPortlets, endPortlets);
	}

	public LayoutTemplate getLayoutTemplate() {
		LayoutTemplate layoutTemplate =
			LayoutTemplateLocalServiceUtil.getLayoutTemplate(
				getLayoutTemplateId(), false, null);

		if (layoutTemplate == null) {
			layoutTemplate = new LayoutTemplateImpl(
				StringPool.BLANK, StringPool.BLANK);

			List<String> columns = new ArrayList<String>();

			for (int i = 1; i <= 10; i++) {
				columns.add("column-" + i);
			}

			layoutTemplate.setColumns(columns);
		}

		return layoutTemplate;
	}

	public String getLayoutTemplateId() {
		String layoutTemplateId = StringPool.BLANK;

		if (hasTemplate()) {
			layoutTemplateId = getTemplateProperty(
				LayoutTypePortletConstants.LAYOUT_TEMPLATE_ID);
		}
		else {
			layoutTemplateId =
				getTypeSettingsProperties().getProperty(
					LayoutTypePortletConstants.LAYOUT_TEMPLATE_ID);
		}

		if (Validator.isNull(layoutTemplateId)) {
			layoutTemplateId = StringPool.BLANK;
		}

		return layoutTemplateId;
	}

	public String getModeAbout() {
		return getTypeSettingsProperties().getProperty(
			LayoutTypePortletConstants.MODE_ABOUT);
	}

	public String getModeConfig() {
		return getTypeSettingsProperties().getProperty(
			LayoutTypePortletConstants.MODE_CONFIG);
	}

	public String getModeEdit() {
		return getTypeSettingsProperties().getProperty(
			LayoutTypePortletConstants.MODE_EDIT);
	}

	public String getModeEditDefaults() {
		return getTypeSettingsProperties().getProperty(
			LayoutTypePortletConstants.MODE_EDIT_DEFAULTS);
	}

	public String getModeEditGuest() {
		return getTypeSettingsProperties().getProperty(
			LayoutTypePortletConstants.MODE_EDIT_GUEST);
	}

	public String getModeHelp() {
		return getTypeSettingsProperties().getProperty(
			LayoutTypePortletConstants.MODE_HELP);
	}

	public String getModePreview() {
		return getTypeSettingsProperties().getProperty(
			LayoutTypePortletConstants.MODE_PREVIEW);
	}

	public String getModePrint() {
		return getTypeSettingsProperties().getProperty(
			LayoutTypePortletConstants.MODE_PRINT);
	}

	public int getNumOfColumns() {
		return getLayoutTemplate().getColumns().size();
	}

	public PortalPreferences getPortalPreferences() {
		return _portalPreferences;
	}

	public List<String> getPortletIds() {
		List<String> portletIds = new ArrayList<String>();

		List<String> columns = getColumns();

		for (int i = 0; i < columns.size(); i++) {
			String columnId = columns.get(i);

			String columnValue = StringPool.BLANK;

			if (isPersonalizable() && isColumnDisabled(columnId) &&
				hasTemplate()) {

				columnValue = getTemplateProperty(columnId);
			}
			else if (isPersonalizable() && !isColumnDisabled(columnId) &&
					 hasUserPreferenes()) {

				columnValue = getUserPreference(columnId);
			}
			else {
				columnValue = getTypeSettingsProperties().getProperty(columnId);
			}

			portletIds.addAll(
				ListUtil.fromArray(StringUtil.split(columnValue)));
		}

		return portletIds;
	}

	public List<Portlet> getPortlets() throws SystemException {
		List<String> portletIds = getPortletIds();

		List<Portlet> portlets = new ArrayList<Portlet>(portletIds.size());

		for (int i = 0; i < portletIds.size(); i++) {
			String portletId = portletIds.get(i);

			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				getCompanyId(), portletId);

			if (portlet != null) {
				portlets.add(portlet);
			}
		}

		return portlets;
	}

	public String getStateMax() {
		return getTypeSettingsProperties().getProperty(
			LayoutTypePortletConstants.STATE_MAX);
	}

	public String getStateMaxPortletId() {
		String[] stateMax = StringUtil.split(getStateMax());

		if (stateMax.length > 0) {
			return stateMax[0];
		}
		else {
			return StringPool.BLANK;
		}
	}

	public String getStateMin() {
		return getTypeSettingsProperties().getProperty(
			LayoutTypePortletConstants.STATE_MIN);
	}

	public boolean hasDefaultScopePortletId(long groupId, String portletId)
		throws PortalException, SystemException {

		if (hasPortletId(portletId)) {
			long scopeGroupId = PortalUtil.getScopeGroupId(
				getLayout(), portletId);

			if (groupId == scopeGroupId) {
				return true;
			}
		}

		return false;
	}

	public boolean hasModeAboutPortletId(String portletId) {
		return StringUtil.contains(getModeAbout(), portletId);
	}

	public boolean hasModeConfigPortletId(String portletId) {
		return StringUtil.contains(getModeConfig(), portletId);
	}

	public boolean hasModeEditDefaultsPortletId(String portletId) {
		return StringUtil.contains(getModeEditDefaults(), portletId);
	}

	public boolean hasModeEditGuestPortletId(String portletId) {
		return StringUtil.contains(getModeEditGuest(), portletId);
	}

	public boolean hasModeEditPortletId(String portletId) {
		return StringUtil.contains(getModeEdit(), portletId);
	}

	public boolean hasModeHelpPortletId(String portletId) {
		return StringUtil.contains(getModeHelp(), portletId);
	}

	public boolean hasModePreviewPortletId(String portletId) {
		return StringUtil.contains(getModePreview(), portletId);
	}

	public boolean hasModePrintPortletId(String portletId) {
		return StringUtil.contains(getModePrint(), portletId);
	}

	public boolean hasModeViewPortletId(String portletId) {
		if (hasModeAboutPortletId(portletId) ||
			hasModeConfigPortletId(portletId) ||
			hasModeEditPortletId(portletId) ||
			hasModeEditDefaultsPortletId(portletId) ||
			hasModeEditGuestPortletId(portletId) ||
			hasModeHelpPortletId(portletId) ||
			hasModePreviewPortletId(portletId) ||
			hasModePrintPortletId(portletId)) {

			return false;
		}
		else {
			return true;
		}
	}

	public boolean hasPortletId(String portletId)
		throws PortalException, SystemException {

		List<String> columns = getColumns();

		for (int i = 0; i < columns.size(); i++) {
			String columnId = columns.get(i);

			if (hasNonstaticPortletId(columnId, portletId)) {
				return true;
			}

			if (hasStaticPortletId(columnId, portletId)) {
				return true;
			}
		}

		Layout layout = getLayout();

		if (layout.isTypeControlPanel()) {
			return true;
		}

		return false;
	}

	public boolean hasStateMax() {
		String[] stateMax = StringUtil.split(getStateMax());

		if (stateMax.length > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasStateMaxPortletId(String portletId) {
		if (StringUtil.contains(getStateMax(), portletId)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasStateMin() {
		String[] stateMin = StringUtil.split(getStateMin());

		if (stateMin.length > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasStateMinPortletId(String portletId) {
		if (StringUtil.contains(getStateMin(), portletId)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasStateNormalPortletId(String portletId) {
		if (hasStateMaxPortletId(portletId) ||
			hasStateMinPortletId(portletId)) {

			return false;
		}
		else {
			return true;
		}
	}

	public boolean hasTemplate() {
		if (_templateLayout != null) {
			return true;
		}

		return false;
	}

	public boolean hasUpdatePermission() {
		return _updatePermission;
	}

	public boolean isColumnDisabled(String columnId) {
		if (!isTemplatePersonalizable(columnId) ||
			(isPersonalizedView() && !isPersonalizable(columnId)) ||
			(!isPersonalizedView() && !hasUpdatePermission())) {

			return true;
		}

		return false;
	}

	public boolean isDefaultUpdated() {
		if (!isPersonalizedView() || !hasUserPreferenes()) {
			return false;
		}

		String preferencesModifiedDateString = _portalPreferences.getValue(
			PersonalizedPages.namespacePlid(getPlid()), _MODIFIED_DATE,
			_NULL_DATE);

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			PropsValues.INDEX_DATE_FORMAT_PATTERN);

		try {
			Date preferencesModifiedDate = dateFormat.parse(
				preferencesModifiedDateString);

			if (hasTemplate()) {
				String propertiesModifiedDateString =
					_templateLayout.getTypeSettingsProperties().getProperty(
						_MODIFIED_DATE, _NULL_DATE);

				Date propertiesModifiedDate = dateFormat.parse(
					propertiesModifiedDateString);

				return propertiesModifiedDate.after(preferencesModifiedDate);
			}
			else {
				Layout layout = getLayout();

				String propertiesModifiedDateString =
					layout.getTypeSettingsProperties().getProperty(
						_MODIFIED_DATE, _NULL_DATE);

				Date propertiesModifiedDate = dateFormat.parse(
					propertiesModifiedDateString);

				return propertiesModifiedDate.after(preferencesModifiedDate);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	public boolean isPersonalizable() {
		for (String columnId : getColumns()) {
			if (isPersonalizable(columnId)) {
				return true;
			}
		}

		return false;
	}

	public boolean isPersonalizable(String columnId) {
		if (!isLayoutSetPrototype() && isTemplatePersonalizable(columnId)) {
			String personalizableString =
				getTypeSettingsProperties().getProperty(
					PersonalizedPages.namespaceColumnId(columnId));

			boolean personalizable = GetterUtil.getBoolean(
				personalizableString);

			if (!personalizable && hasUserPreferenes()) {
				String columnValue = _portalPreferences.getValue(
					PersonalizedPages.namespacePlid(getPlid()), columnId,
					StringPool.NULL);

				if (!Validator.equals(columnValue, StringPool.NULL)) {
					setUserPreference(columnId, null);
				}
			}

			return personalizable;
		}

		return false;
	}

	public boolean isPersonalizedView() {
		return _personalizedView;
	}

	public boolean isTemplatePersonalizable(String columnId) {
		if (!hasTemplate()) {
			return true;
		}

		String personalizable = getTemplateProperty(
			PersonalizedPages.namespaceColumnId(columnId));

		return GetterUtil.getBoolean(personalizable, false);
	}

	public void movePortletId(
			long userId, String portletId, String columnId, int columnPos)
		throws PortalException, SystemException {

		_enablePortletLayoutListener = false;

		try {
			removePortletId(userId, portletId, false);
			addPortletId(userId, portletId, columnId, columnPos);
		}
		finally {
			_enablePortletLayoutListener = true;
		}

		Layout layout = getLayout();

		try {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				layout.getCompanyId(), portletId);

			if (portlet != null) {
				PortletLayoutListener portletLayoutListener =
					portlet.getPortletLayoutListenerInstance();

				if (portletLayoutListener != null) {
					portletLayoutListener.onMoveInLayout(
						portletId, layout.getPlid());
				}
			}
		}
		catch (Exception e) {
			_log.error("Unable to fire portlet layout listener event", e);
		}
	}

	public void removeModeAboutPortletId(String portletId) {
		setModeAbout(StringUtil.remove(getModeAbout(), portletId));
	}

	public void removeModeConfigPortletId(String portletId) {
		setModeConfig(StringUtil.remove(getModeConfig(), portletId));
	}

	public void removeModeEditDefaultsPortletId(String portletId) {
		setModeEditDefaults(
			StringUtil.remove(getModeEditDefaults(), portletId));
	}

	public void removeModeEditGuestPortletId(String portletId) {
		setModeEditGuest(StringUtil.remove(getModeEditGuest(), portletId));
	}

	public void removeModeEditPortletId(String portletId) {
		setModeEdit(StringUtil.remove(getModeEdit(), portletId));
	}

	public void removeModeHelpPortletId(String portletId) {
		setModeHelp(StringUtil.remove(getModeHelp(), portletId));
	}

	public void removeModePreviewPortletId(String portletId) {
		setModePreview(StringUtil.remove(getModePreview(), portletId));
	}

	public void removeModePrintPortletId(String portletId) {
		setModePrint(StringUtil.remove(getModePrint(), portletId));
	}

	public void removeModesPortletId(String portletId) {
		removeModeAboutPortletId(portletId);
		removeModeConfigPortletId(portletId);
		removeModeEditPortletId(portletId);
		removeModeEditDefaultsPortletId(portletId);
		removeModeEditGuestPortletId(portletId);
		removeModeHelpPortletId(portletId);
		removeModePreviewPortletId(portletId);
		removeModePrintPortletId(portletId);
	}

	public void removeNestedColumns(String portletId) {
		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		UnicodeProperties newTypeSettingsProperties = new UnicodeProperties();

		for (Map.Entry<String, String> entry :
				typeSettingsProperties.entrySet()) {

			String key = entry.getKey();

			if (!key.startsWith(portletId)) {
				newTypeSettingsProperties.setProperty(key, entry.getValue());
			}
		}

		Layout layout = getLayout();

		layout.setTypeSettingsProperties(newTypeSettingsProperties);

		String nestedColumnIds = GetterUtil.getString(
			getTypeSettingsProperties().getProperty(
				LayoutTypePortletConstants.NESTED_COLUMN_IDS));

		String[] nestedColumnIdsArray = ArrayUtil.removeByPrefix(
			StringUtil.split(nestedColumnIds), portletId);

		getTypeSettingsProperties().setProperty(
			LayoutTypePortletConstants.NESTED_COLUMN_IDS,
			StringUtil.merge(nestedColumnIdsArray));
	}

	public void removePortletId(long userId, String portletId) {
		removePortletId(userId, portletId, true);
	}

	public void removePortletId (
		long userId, String portletId, boolean cleanUp) {

		try {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				getCompanyId(), portletId);

			if (portlet == null) {
				_log.error(
					"Portlet " + portletId +
						" cannot be removed because it is not registered");

				return;
			}

			if (!portlet.hasAddPortletPermission(userId) &&
				!isPersonalizable()) {

				return;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		List<String> columns = getColumns();

		for (int i = 0; i < columns.size(); i++) {
			String columnId = columns.get(i);

			if (isPersonalizable() && isColumnDisabled(columnId)) {
				continue;
			}

			String columnValue = StringPool.BLANK;

			if (hasUserPreferenes()) {
				columnValue = getUserPreference(columnId);
			}
			else {
				columnValue = getTypeSettingsProperties().getProperty(
					columnId);
			}

			columnValue = StringUtil.remove(columnValue, portletId);

			if (hasUserPreferenes()) {
				setUserPreference(columnId, columnValue);

				try {
					String rootPortletId = PortletConstants.getRootPortletId(
						portletId);

					ResourceLocalServiceUtil.deleteResource(
						getCompanyId(), rootPortletId,
						ResourceConstants.SCOPE_INDIVIDUAL,
						PortletPermissionUtil.getPrimaryKey(
							getPlid(), portletId));
				}
				catch (Exception e) {
				}
			}
			else {
				getTypeSettingsProperties().setProperty(columnId, columnValue);
			}
		}

		if (cleanUp) {
			removeStatesPortletId(portletId);
			removeModesPortletId(portletId);

			try {
				onRemoveFromLayout(portletId);
			}
			catch (Exception e) {
				_log.error("Unable to fire portlet layout listener event", e);
			}
		}
	}

	public void removeStateMaxPortletId(String portletId) {
		setStateMax(StringUtil.remove(getStateMax(), portletId));
	}

	public void removeStateMinPortletId(String portletId) {
		setStateMin(StringUtil.remove(getStateMin(), portletId));
	}

	public void removeStatesPortletId(String portletId) {
		removeStateMaxPortletId(portletId);
		removeStateMinPortletId(portletId);
	}

	public void reorganizePortlets(
		List<String> newColumns, List<String> oldColumns) {

		String lastNewColumnId = newColumns.get(newColumns.size() - 1);
		String lastNewColumnValue =
			getTypeSettingsProperties().getProperty(lastNewColumnId);

		Iterator<String> itr = oldColumns.iterator();

		while (itr.hasNext()) {
			String oldColumnId = itr.next();

			if (!newColumns.contains(oldColumnId)) {
				String oldColumnValue = getTypeSettingsProperties().remove(
					oldColumnId);

				String[] portletIds = StringUtil.split(oldColumnValue);

				for (String portletId : portletIds) {
					lastNewColumnValue = StringUtil.add(
						lastNewColumnValue, portletId);
				}
			}
		}

		getTypeSettingsProperties().setProperty(
			lastNewColumnId, lastNewColumnValue);
	}

	public void resetModes() {
		setModeAbout(StringPool.BLANK);
		setModeConfig(StringPool.BLANK);
		setModeEdit(StringPool.BLANK);
		setModeEditDefaults(StringPool.BLANK);
		setModeEditGuest(StringPool.BLANK);
		setModeHelp(StringPool.BLANK);
		setModePreview(StringPool.BLANK);
		setModePrint(StringPool.BLANK);
	}

	public void resetStates() {
		setStateMax(StringPool.BLANK);
		setStateMin(StringPool.BLANK);
	}

	public void resetUserPreferences() {
		if (hasUserPreferenes()) {
			Layout layout = getLayout();

			long plid = layout.getPlid();

			_portalPreferences.resetValues(
				PersonalizedPages.namespacePlid(plid));

			_portalPreferences.setValue(
				PersonalizedPages.namespacePlid(plid), _MODIFIED_DATE,
				_dateFormat.format(new Date()));
		}
	}

	public void setLayoutTemplateId(long userId, String newLayoutTemplateId) {
		setLayoutTemplateId(userId, newLayoutTemplateId, true);
	}

	public void setLayoutTemplateId(
		long userId, String newLayoutTemplateId, boolean checkPermission) {

		if (checkPermission &&
			!PluginSettingLocalServiceUtil.hasPermission(
				userId, newLayoutTemplateId, Plugin.TYPE_LAYOUT_TEMPLATE)) {

			return;
		}

		String oldLayoutTemplateId = getLayoutTemplateId();

		if (Validator.isNull(oldLayoutTemplateId)) {
			oldLayoutTemplateId = PropsValues.DEFAULT_LAYOUT_TEMPLATE_ID;
		}

		getTypeSettingsProperties().setProperty(
			LayoutTypePortletConstants.LAYOUT_TEMPLATE_ID, newLayoutTemplateId);

		String themeId = null;

		try {
			Layout layout = getLayout();

			Theme theme = layout.getTheme();

			if (theme != null) {
				themeId = theme.getThemeId();
			}
			else {
				themeId = layout.getThemeId();
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		LayoutTemplate oldLayoutTemplate =
			LayoutTemplateLocalServiceUtil.getLayoutTemplate(
				oldLayoutTemplateId, false, themeId);

		if (oldLayoutTemplate == null) {
			return;
		}

		LayoutTemplate newLayoutTemplate =
			LayoutTemplateLocalServiceUtil.getLayoutTemplate(
				newLayoutTemplateId, false, themeId);

		List<String> oldColumns = oldLayoutTemplate.getColumns();
		List<String> newColumns = newLayoutTemplate.getColumns();

		reorganizePortlets(newColumns, oldColumns);
	}

	public void setModeAbout(String modeAbout) {
		getTypeSettingsProperties().setProperty(
			LayoutTypePortletConstants.MODE_ABOUT, modeAbout);
	}

	public void setModeConfig(String modeConfig) {
		getTypeSettingsProperties().setProperty(
			LayoutTypePortletConstants.MODE_CONFIG, modeConfig);
	}

	public void setModeEdit(String modeEdit) {
		getTypeSettingsProperties().setProperty(
			LayoutTypePortletConstants.MODE_EDIT, modeEdit);
	}

	public void setModeEditDefaults(String modeEditDefaults) {
		getTypeSettingsProperties().setProperty(
			LayoutTypePortletConstants.MODE_EDIT_DEFAULTS, modeEditDefaults);
	}

	public void setModeEditGuest(String modeEditGuest) {
		getTypeSettingsProperties().setProperty(
			LayoutTypePortletConstants.MODE_EDIT_GUEST, modeEditGuest);
	}

	public void setModeHelp(String modeHelp) {
		getTypeSettingsProperties().setProperty(
			LayoutTypePortletConstants.MODE_HELP, modeHelp);
	}

	public void setModePreview(String modePreview) {
		getTypeSettingsProperties().setProperty(
			LayoutTypePortletConstants.MODE_PREVIEW, modePreview);
	}

	public void setModePrint(String modePrint) {
		getTypeSettingsProperties().setProperty(
			LayoutTypePortletConstants.MODE_PRINT, modePrint);
	}

	/*protected boolean hasStaticPortletId(String portletId) {
		LayoutTemplate layoutTemplate = getLayoutTemplate();

		List columns = layoutTemplate.getColumns();

		for (int i = 0; i < columns.size(); i++) {
			String columnId = (String)columns.get(i);

			if (hasStaticPortletId(columnId, portletId)) {
				return true;
			}
		}

		return false;
	}*/

	public void setPersonalizedView(boolean personalizedView) {
		_personalizedView = personalizedView;
	}

	public void setPortalPreferences(PortalPreferences portalPreferences) {
		_portalPreferences = portalPreferences;
	}

	public void setPortletIds(String columnId, String portletIds) {
		getTypeSettingsProperties().setProperty(columnId, portletIds);
	}

	public void setStateMax(String stateMax) {
		getTypeSettingsProperties().setProperty(
			LayoutTypePortletConstants.STATE_MAX, stateMax);
	}

	public void setStateMin(String stateMin) {
		getTypeSettingsProperties().setProperty(
			LayoutTypePortletConstants.STATE_MIN, stateMin);
	}

	public void setUpdatePermission(boolean updatePermission) {
		_updatePermission = updatePermission;
	}

	protected void addNestedColumn(String columnId) {
		String nestedColumnIds = getTypeSettingsProperties().getProperty(
			LayoutTypePortletConstants.NESTED_COLUMN_IDS, StringPool.BLANK);

		if (nestedColumnIds.indexOf(columnId) == -1) {
			nestedColumnIds = StringUtil.add(nestedColumnIds, columnId);

			getTypeSettingsProperties().setProperty(
				LayoutTypePortletConstants.NESTED_COLUMN_IDS, nestedColumnIds);
		}
	}

	protected void deletePortletSetup(String portletId) {
		try {
			List<PortletPreferences> portletPreferencesList =
				PortletPreferencesLocalServiceUtil.getPortletPreferences(
					getPlid(), portletId);

			for (PortletPreferences portletPreferences :
					portletPreferencesList) {

				PortletPreferencesLocalServiceUtil.deletePortletPreferences(
					portletPreferences.getPortletPreferencesId());
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected List<String> getColumns() {
		LayoutTemplate layoutTemplate = getLayoutTemplate();

		List<String> columns = new ArrayList<String>();

		columns.addAll(layoutTemplate.getColumns());
		columns.addAll(getNestedColumns());

		return columns;
	}

	protected long getCompanyId() {
		Layout layout = getLayout();

		return layout.getCompanyId();
	}

	protected List<String> getNestedColumns() {
		String nestedColumnIds = getTypeSettingsProperties().getProperty(
			LayoutTypePortletConstants.NESTED_COLUMN_IDS);

		return ListUtil.fromArray(StringUtil.split(nestedColumnIds));
	}

	protected long getPlid() {
		Layout layout = getLayout();

		return layout.getPlid();
	}

	protected String[] getStaticPortletIds(String position)
		throws PortalException, SystemException {

		Layout layout = getLayout();

		if (hasTemplate()) {
			layout = _templateLayout;
		}

		String selector1 = StringPool.BLANK;

		Group group = layout.getGroup();

		if (group.isUser()) {
			selector1 = LayoutTypePortletConstants.STATIC_PORTLET_USER_SELECTOR;
		}
		else if (group.isCommunity()) {
			selector1 =
				LayoutTypePortletConstants.STATIC_PORTLET_COMMUNITY_SELECTOR;
		}
		else if (group.isOrganization()) {
			selector1 =
				LayoutTypePortletConstants.STATIC_PORTLET_ORGANIZATION_SELECTOR;
		}

		String selector2 = layout.getFriendlyURL();

		String[] portletIds = PropsUtil.getArray(
			position, new Filter(selector1, selector2));

		for (int i = 0; i < portletIds.length; i++) {
			portletIds[i] = JS.getSafeName(portletIds[i]);
		}

		return portletIds;
	}

	protected List<Portlet> getStaticPortlets(String position)
		throws PortalException, SystemException {

		String[] portletIds = getStaticPortletIds(position);

		List<Portlet> portlets = new ArrayList<Portlet>();

		for (String portletId : portletIds) {
			if (Validator.isNull(portletId) ||
				hasNonstaticPortletId(portletId)) {

				continue;
			}

			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				getCompanyId(), portletId);

			if (portlet != null) {
				Portlet staticPortlet = portlet;

				if (portlet.isInstanceable()) {

					// Instanceable portlets do not need to be cloned because
					// they are already cloned. See the method getPortletById in
					// the class PortletLocalServiceImpl and how it references
					// the method getClonedInstance in the class PortletImpl.

				}
				else {
					staticPortlet = (Portlet)staticPortlet.clone();
				}

				staticPortlet.setStatic(true);

				if (position.startsWith("layout.static.portlets.start")) {
					staticPortlet.setStaticStart(true);
				}

				portlets.add(staticPortlet);
			}
		}

		return portlets;
	}

	protected String getTemplateProperty(String key) {
		if (_templateLayout == null) {
			return StringPool.BLANK;
		}

		return _templateLayout.getTypeSettingsProperties().getProperty(key);
	}

	protected String getUserPreference(String key) {
		String value = StringPool.BLANK;

		if (hasUserPreferenes()) {
			value = _portalPreferences.getValue(
				PersonalizedPages.namespacePlid(getPlid()), key,
				StringPool.NULL);

			if (value.equals(StringPool.NULL)) {
				if (hasTemplate()) {
					value = getTemplateProperty(key);
				}
				else {
					value = getTypeSettingsProperties().getProperty(key);
				}
			}
		}

		return value;
	}

	protected boolean hasNonstaticPortletId(String portletId) {
		LayoutTemplate layoutTemplate = getLayoutTemplate();

		List<String> columns = layoutTemplate.getColumns();

		for (int i = 0; i < columns.size(); i++) {
			String columnId = columns.get(i);

			if (hasNonstaticPortletId(columnId, portletId)) {
				return true;
			}
		}

		return false;
	}

	protected boolean hasNonstaticPortletId(String columnId, String portletId) {
		String columnValue = StringPool.BLANK;

		if (isPersonalizable() && isColumnDisabled(columnId) && hasTemplate()) {
			columnValue = getTemplateProperty(columnId);
		}
		else if (isPersonalizable() && !isColumnDisabled(columnId) &&
				 hasUserPreferenes()) {

			columnValue = getUserPreference(columnId);
		}
		else {
			columnValue = getTypeSettingsProperties().getProperty(columnId);
		}

		if (StringUtil.contains(columnValue, portletId)) {
			return true;
		}
		else {
			return false;
		}
	}

	protected boolean hasStaticPortletId(String columnId, String portletId)
		throws PortalException, SystemException {

		String[] staticPortletIdsStart = getStaticPortletIds(
			PropsKeys.LAYOUT_STATIC_PORTLETS_START + columnId);

		String[] staticPortletIdsEnd = getStaticPortletIds(
			PropsKeys.LAYOUT_STATIC_PORTLETS_END + columnId);

		String[] staticPortletIds = ArrayUtil.append(
			staticPortletIdsStart, staticPortletIdsEnd);

		for (String staticPortletId : staticPortletIds) {
			if (staticPortletId.equals(portletId)) {
				return true;
			}
		}

		return false;
	}

	protected boolean hasUserPreferenes() {
		if (_portalPreferences != null) {
			return true;
		}

		return false;
	}

	protected boolean isLayoutSetPrototype() {
		try {
			Layout layout = getLayout();

			LayoutSet layoutSet = layout.getLayoutSet();

			Group group = layoutSet.getGroup();

			return group.isLayoutSetPrototype();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	protected void onRemoveFromLayout(String portletId) throws SystemException {
		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			getCompanyId(), portletId);

		if (portlet == null) {
			return;
		}

		if (portlet.getRootPortletId().equals(PortletKeys.NESTED_PORTLETS)) {
			UnicodeProperties typeSettingsProperties =
				getTypeSettingsProperties();

			for (Map.Entry<String, String> entry :
					typeSettingsProperties.entrySet()) {

				String key = entry.getKey();

				if (key.startsWith(portlet.getPortletId())) {
					String portletIds = entry.getValue();

					String[] portletIdsArray = StringUtil.split(portletIds);

					for (String curPortletId : portletIdsArray) {
						onRemoveFromLayout(curPortletId);
					}
				}
			}

			removeNestedColumns(portletId);
		}

		if (_enablePortletLayoutListener) {
			PortletLayoutListener portletLayoutListener =
				portlet.getPortletLayoutListenerInstance();

			if ((portletLayoutListener != null)) {
				portletLayoutListener.onRemoveFromLayout(portletId, getPlid());
			}
		}

		deletePortletSetup(portletId);
	}

	protected void setUserPreference(String key, String value) {
		if (!hasUserPreferenes()) {
			return;
		}

		_portalPreferences.setValue(
			PersonalizedPages.namespacePlid(getPlid()), key, value);

		_portalPreferences.setValue(
			PersonalizedPages.namespacePlid(getPlid()), _MODIFIED_DATE,
			_dateFormat.format(new Date()));
	}

	private static final String _MODIFIED_DATE = "modifiedDate";

	private static final String _NESTED_PORTLETS_NAMESPACE =
		PortalUtil.getPortletNamespace(PortletKeys.NESTED_PORTLETS);

	private static final String _NULL_DATE = "00000000000000";

	private static Log _log = LogFactoryUtil.getLog(
		LayoutTypePortletImpl.class);

	private Format _dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
		PropsValues.INDEX_DATE_FORMAT_PATTERN);
	private boolean _enablePortletLayoutListener = true;
	private boolean _personalizedView;
	private PortalPreferences _portalPreferences;
	private Layout _templateLayout;
	private boolean _updatePermission;

}