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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.staging.LayoutStagingUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutRevision;
import com.liferay.portal.model.LayoutStagingHandler;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class LayoutLocalServiceStagingAdvice
	extends LayoutLocalServiceImpl implements MethodInterceptor {

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Method method = methodInvocation.getMethod();

		String methodName = method.getName();

		Object[] arguments = methodInvocation.getArguments();

		if (!_layoutLocalServiceStagingAdviceMethodNames.contains(
				methodName)) {

			return wrapReturnValue(methodInvocation.proceed());
		}

		Object returnValue = null;

		if (methodName.equals("updateLayout") && (arguments.length == 15)) {
			returnValue = updateLayout(
				(Long)arguments[0], (Boolean)arguments[1], (Long)arguments[2],
				(Long)arguments[3], (Map<Locale, String>)arguments[4],
				(Map<Locale, String>)arguments[5],
				(Map<Locale, String>)arguments[6],
				(Map<Locale, String>)arguments[7],
				(Map<Locale, String>)arguments[8], (String)arguments[9],
				(Boolean)arguments[10], (String)arguments[11],
				(Boolean)arguments[12], (byte[])arguments[13],
				(ServiceContext)arguments[14]);
		}
		else {
			try {
				Class<?> classObject = getClass();

				Method localMethod = classObject.getMethod(
					methodName, method.getParameterTypes());

				returnValue = localMethod.invoke(this, arguments);
			}
			catch (NoSuchMethodException nsme) {
				throw new SystemException(nsme);
			}
		}

		returnValue = wrapReturnValue(returnValue);

		return returnValue;
	}

	public Layout updateLayout(
			long groupId, boolean privateLayout, long layoutId,
			long parentLayoutId, Map<Locale, String> nameMap,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> keywordsMap, Map<Locale, String> robotsMap,
			String type, boolean hidden, String friendlyURL, Boolean iconImage,
			byte[] iconBytes, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Layout

		parentLayoutId = getParentLayoutId(
			groupId, privateLayout, parentLayoutId);
		String name = nameMap.get(LocaleUtil.getDefault());
		friendlyURL = getFriendlyURL(
			groupId, privateLayout, layoutId, StringPool.BLANK, friendlyURL);

		validate(
			groupId, privateLayout, layoutId, parentLayoutId, name, type,
			hidden, friendlyURL);

		validateParentLayoutId(
			groupId, privateLayout, layoutId, parentLayoutId);

		Layout layout = layoutPersistence.findByG_P_L(
			groupId, privateLayout, layoutId);

		layout = wrapLayout(layout);

		LayoutRevision layoutRevision = LayoutStagingUtil.getLayoutRevision(
			layout);

		if (layoutRevision == null) {
			return super.updateLayout(
				groupId, privateLayout, layoutId, parentLayoutId, nameMap,
				titleMap, descriptionMap, keywordsMap, robotsMap, type, hidden,
				friendlyURL, iconImage, iconBytes, serviceContext);
		}

		if (parentLayoutId != layout.getParentLayoutId()) {
			layout.setPriority(
				getNextPriority(groupId, privateLayout, parentLayoutId));
		}

		layout.setParentLayoutId(parentLayoutId);
		layoutRevision.setNameMap(nameMap);
		layoutRevision.setTitleMap(titleMap);
		layoutRevision.setDescriptionMap(descriptionMap);
		layoutRevision.setKeywordsMap(keywordsMap);
		layoutRevision.setRobotsMap(robotsMap);
		layout.setType(type);
		layout.setHidden(hidden);
		layout.setFriendlyURL(friendlyURL);

		if (iconImage != null) {
			layout.setIconImage(iconImage.booleanValue());

			if (iconImage.booleanValue()) {
				long iconImageId = layout.getIconImageId();

				if (iconImageId <= 0) {
					iconImageId = counterLocalService.increment();

					layout.setIconImageId(iconImageId);
				}
			}
		}

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		layoutRevisionLocalService.updateLayoutRevision(
			serviceContext.getUserId(), layoutRevision.getLayoutRevisionId(),
			layoutRevision.getName(), layoutRevision.getTitle(),
			layoutRevision.getDescription(), layoutRevision.getKeywords(),
			layoutRevision.getRobots(), layoutRevision.getTypeSettings(),
			layoutRevision.getIconImage(), layoutRevision.getIconImageId(),
			layoutRevision.getThemeId(), layoutRevision.getColorSchemeId(),
			layoutRevision.getWapThemeId(),
			layoutRevision.getWapColorSchemeId(), layoutRevision.getCss(),
			serviceContext);

		// Icon

		if (iconImage != null) {
			if ((iconBytes != null) && (iconBytes.length > 0)) {
				imageLocalService.updateImage(
					layout.getIconImageId(), iconBytes);
			}
		}

		// Expando

		ExpandoBridge expandoBridge = layout.getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);

		return layout;
	}

	public Layout updateLayout(
			long groupId, boolean privateLayout, long layoutId,
			String typeSettings)
		throws PortalException, SystemException {

		Layout layout = layoutPersistence.findByG_P_L(
			groupId, privateLayout, layoutId);

		layout = wrapLayout(layout);

		LayoutRevision layoutRevision = LayoutStagingUtil.getLayoutRevision(
			layout);

		if (layoutRevision == null) {
			return super.updateLayout(
				groupId, privateLayout, layoutId, typeSettings);
		}

		layout.setTypeSettings(typeSettings);

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		layoutRevisionLocalService.updateLayoutRevision(
			serviceContext.getUserId(), layoutRevision.getLayoutRevisionId(),
			layoutRevision.getName(), layoutRevision.getTitle(),
			layoutRevision.getDescription(), layoutRevision.getKeywords(),
			layoutRevision.getRobots(), layoutRevision.getTypeSettings(),
			layoutRevision.getIconImage(), layoutRevision.getIconImageId(),
			layoutRevision.getThemeId(), layoutRevision.getColorSchemeId(),
			layoutRevision.getWapThemeId(),
			layoutRevision.getWapColorSchemeId(), layoutRevision.getCss(),
			serviceContext);

		return layout;
	}

	public Layout updateLookAndFeel(
			long groupId, boolean privateLayout, long layoutId, String themeId,
			String colorSchemeId, String css, boolean wapTheme)
		throws PortalException, SystemException {

		Layout layout = layoutPersistence.findByG_P_L(
			groupId, privateLayout, layoutId);

		layout = wrapLayout(layout);

		LayoutRevision layoutRevision = LayoutStagingUtil.getLayoutRevision(
			layout);

		if (layoutRevision == null) {
			return super.updateLookAndFeel(
				groupId, privateLayout, layoutId, themeId, colorSchemeId, css,
				wapTheme);
		}

		if (wapTheme) {
			layout.setWapThemeId(themeId);
			layout.setWapColorSchemeId(colorSchemeId);
		}
		else {
			layout.setThemeId(themeId);
			layout.setColorSchemeId(colorSchemeId);
			layout.setCss(css);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		layoutRevisionLocalService.updateLayoutRevision(
			serviceContext.getUserId(), layoutRevision.getLayoutRevisionId(),
			layoutRevision.getName(), layoutRevision.getTitle(),
			layoutRevision.getDescription(), layoutRevision.getKeywords(),
			layoutRevision.getRobots(), layoutRevision.getTypeSettings(),
			layoutRevision.getIconImage(), layoutRevision.getIconImageId(),
			layoutRevision.getThemeId(), layoutRevision.getColorSchemeId(),
			layoutRevision.getWapThemeId(),
			layoutRevision.getWapColorSchemeId(), layoutRevision.getCss(),
			serviceContext);

		return layout;
	}

	public Layout updateName(Layout layout, String name, String languageId)
		throws PortalException, SystemException {

		layout = wrapLayout(layout);

		LayoutRevision layoutRevision = LayoutStagingUtil.getLayoutRevision(
			layout);

		if (layoutRevision == null) {
			return super.updateName(layout, name, languageId);
		}

		validateName(name, languageId);

		layout.setName(name, LocaleUtil.fromLanguageId(languageId));

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		layoutRevisionLocalService.updateLayoutRevision(
			serviceContext.getUserId(), layoutRevision.getLayoutRevisionId(),
			layoutRevision.getName(), layoutRevision.getTitle(),
			layoutRevision.getDescription(), layoutRevision.getKeywords(),
			layoutRevision.getRobots(), layoutRevision.getTypeSettings(),
			layoutRevision.getIconImage(), layoutRevision.getIconImageId(),
			layoutRevision.getThemeId(), layoutRevision.getColorSchemeId(),
			layoutRevision.getWapThemeId(),
			layoutRevision.getWapColorSchemeId(), layoutRevision.getCss(),
			serviceContext);

		return layout;
	}

	protected Layout unwrapLayout(Layout layout) {
		LayoutStagingHandler layoutStagingHandler =
			LayoutStagingUtil.getLayoutStagingHandler(layout);

		if (layoutStagingHandler == null) {
			return layout;
		}

		return layoutStagingHandler.getLayout();
	}

	protected Layout wrapLayout(Layout layout) {
		LayoutStagingHandler layoutStagingHandler =
			LayoutStagingUtil.getLayoutStagingHandler(layout);

		if (layoutStagingHandler != null) {
			return layout;
		}

		if (!LayoutStagingUtil.isBranchingLayout(layout)) {
			return layout;
		}

		return (Layout)Proxy.newProxyInstance(
			PortalClassLoaderUtil.getClassLoader(), new Class[] {Layout.class},
			new LayoutStagingHandler(layout));
	}

	protected List<Layout> wrapLayouts(List<Layout> layouts) {
		if (layouts.isEmpty()) {
			return layouts;
		}

		Layout firstLayout = layouts.get(0);

		Layout wrappedFirstLayout = wrapLayout(firstLayout);

		if (wrappedFirstLayout == firstLayout) {
			return layouts;
		}

		List<Layout> wrappedLayouts = new ArrayList<Layout>(layouts.size());

		wrappedLayouts.add(wrappedFirstLayout);

		for (int i = 1; i < layouts.size(); i++) {
			Layout layout = layouts.get(i);

			wrappedLayouts.add(wrapLayout(layout));
		}

		return wrappedLayouts;
	}

	protected Object wrapReturnValue(Object returnValue) {
		if (returnValue instanceof Layout) {
			returnValue = wrapLayout((Layout)returnValue);
		}
		else if (returnValue instanceof List<?>) {
			returnValue = wrapLayouts((List<Layout>)returnValue);
		}

		return returnValue;
	}

	private static Set<String> _layoutLocalServiceStagingAdviceMethodNames =
		new HashSet<String>();

	static {
		_layoutLocalServiceStagingAdviceMethodNames.add("updateLayout");
		_layoutLocalServiceStagingAdviceMethodNames.add("updateLookAndFeel");
		_layoutLocalServiceStagingAdviceMethodNames.add("updateName");
	}

}