/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.service.impl;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.ColorScheme;
import com.liferay.portal.model.PluginSetting;
import com.liferay.portal.model.Theme;
import com.liferay.portal.model.impl.ColorSchemeImpl;
import com.liferay.portal.model.impl.PortletImpl;
import com.liferay.portal.model.impl.ThemeImpl;
import com.liferay.portal.plugin.PluginUtil;
import com.liferay.portal.service.PluginSettingLocalServiceUtil;
import com.liferay.portal.theme.ThemeCompanyId;
import com.liferay.portal.theme.ThemeCompanyLimit;
import com.liferay.portal.theme.ThemeGroupId;
import com.liferay.portal.theme.ThemeGroupLimit;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.ReleaseInfo;
import com.liferay.portal.util.SAXReaderFactory;
import com.liferay.util.CollectionFactory;
import com.liferay.util.ContextReplace;
import com.liferay.util.GetterUtil;
import com.liferay.util.ListUtil;
import com.liferay.util.Validator;
import com.liferay.util.xml.XMLSafeReader;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * <a href="ThemeLocalUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 *
 */
public class ThemeLocalUtil {

	public static ColorScheme getColorScheme(
			long companyId, String themeId, String colorSchemeId,
			boolean wapTheme)
		throws PortalException, SystemException {

		colorSchemeId = GetterUtil.getString(colorSchemeId);

		Theme theme = getTheme(companyId, themeId, wapTheme);

		Map colorSchemesMap = theme.getColorSchemesMap();

		ColorScheme colorScheme = (ColorScheme)colorSchemesMap.get(
			colorSchemeId);

		if (colorScheme == null) {
			List colorSchemes = theme.getColorSchemes();

			if (colorSchemes.size() > 0) {
				for (int i = (colorSchemes.size() - 1); i >= 0; i--) {
					colorScheme = (ColorScheme)colorSchemes.get(i);

					if (colorScheme.isDefaultCs()) {
						break;
					}
				}
			}
		}

		if (colorScheme == null) {
			if (wapTheme) {
				colorSchemeId = ColorSchemeImpl.getDefaultWapColorSchemeId();
			}
			else {
				colorSchemeId =
					ColorSchemeImpl.getDefaultRegularColorSchemeId();
			}
		}

		if (colorScheme == null) {
			colorScheme = ColorSchemeImpl.getNullColorScheme();
		}

		return colorScheme;
	}

	public static Theme getTheme(
			long companyId, String themeId, boolean wapTheme)
		throws PortalException, SystemException {

		themeId = GetterUtil.getString(themeId);

		Theme theme = (Theme)_getThemes(companyId).get(themeId);

		if (theme == null) {
			if (wapTheme) {
				themeId = ThemeImpl.getDefaultWapThemeId();
			}
			else {
				themeId = ThemeImpl.getDefaultRegularThemeId();
			}

			theme = (Theme)_themes.get(themeId);
		}

		return theme;
	}

	public static List getThemes(long companyId) {
		List themes = ListUtil.fromCollection(_getThemes(companyId).values());

		Collections.sort(themes);

		return themes;
	}

	public static List getThemes(
			long companyId, long groupId, long userId, boolean wapTheme)
		throws PortalException, SystemException {

		List themes = getThemes(companyId);

		themes = PluginUtil.restrictPlugins(themes, companyId, userId);

		Iterator itr = themes.iterator();

		while (itr.hasNext()) {
			Theme theme = (Theme)itr.next();

			if ((!theme.isGroupAvailable(groupId)) ||
				(theme.isWapTheme() != wapTheme)) {

				itr.remove();
			}
		}

		return themes;
	}

	public static List init(
		ServletContext ctx, boolean loadFromServletContext, String[] xmls,
		PluginPackage pluginPackage) {

		return init(null, ctx, loadFromServletContext, xmls, pluginPackage);
	}

	public static List init(
		String servletContextName, ServletContext ctx,
		boolean loadFromServletContext, String[] xmls,
		PluginPackage pluginPackage) {

		List themeIds = new ArrayList();

		try {
			for (int i = 0; i < xmls.length; i++) {
				Set themes = _readThemes(
					servletContextName, ctx, loadFromServletContext, xmls[i],
					pluginPackage);

				Iterator itr = themes.iterator();

				while (itr.hasNext()) {
					String themeId = (String)itr.next();

					if (!themeIds.contains(themeId)) {
						themeIds.add(themeId);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		_themesPool.clear();

		return themeIds;
	}

	public static void uninstallThemes(List themeIds) {
		for (int i = 0; i < themeIds.size(); i++) {
			String themeId = (String)themeIds.get(i);

			_themes.remove(themeId);

			LayoutTemplateLocalUtil.uninstallLayoutTemplates(themeId);
		}

		_themesPool.clear();
	}

	private static List _getCompanyLimitExcludes(Element el) {
		List includes = new ArrayList();

		if (el != null) {
			List companyIds = el.elements("company-id");

			for (int i = 0; i < companyIds.size(); i++) {
				Element companyIdEl = (Element)companyIds.get(i);

				String name = companyIdEl.attributeValue("name");
				String pattern = companyIdEl.attributeValue("pattern");

				ThemeCompanyId themeCompanyId = null;

				if (Validator.isNotNull(name)) {
					themeCompanyId = new ThemeCompanyId(name, false);
				}
				else if (Validator.isNotNull(pattern)) {
					themeCompanyId = new ThemeCompanyId(pattern, true);
				}

				if (themeCompanyId != null) {
					includes.add(themeCompanyId);
				}
			}
		}

		return includes;
	}

	private static List _getCompanyLimitIncludes(Element el) {
		return _getCompanyLimitExcludes(el);
	}

	private static List _getGroupLimitExcludes(Element el) {
		List includes = new ArrayList();

		if (el != null) {
			List groupIds = el.elements("group-id");

			for (int i = 0; i < groupIds.size(); i++) {
				Element groupIdEl = (Element)groupIds.get(i);

				String name = groupIdEl.attributeValue("name");
				String pattern = groupIdEl.attributeValue("pattern");

				ThemeGroupId themeGroupId = null;

				if (Validator.isNotNull(name)) {
					themeGroupId = new ThemeGroupId(name, false);
				}
				else if (Validator.isNotNull(pattern)) {
					themeGroupId = new ThemeGroupId(pattern, true);
				}

				if (themeGroupId != null) {
					includes.add(themeGroupId);
				}
			}
		}

		return includes;
	}

	private static List _getGroupLimitIncludes(Element el) {
		return _getGroupLimitExcludes(el);
	}

	private static Map _getThemes(long companyId) {
		Long companyIdObj = new Long(companyId);

		Map themes = (Map)_themesPool.get(companyIdObj);

		if (themes == null) {
			themes = CollectionFactory.getSyncHashMap();

			Iterator itr = _themes.entrySet().iterator();

			while (itr.hasNext()) {
				Map.Entry entry = (Map.Entry)itr.next();

				String themeId = (String)entry.getKey();
				Theme theme = (Theme)entry.getValue();

				if (theme.isCompanyAvailable(companyId)) {
					themes.put(themeId, theme);
				}
			}

			_themesPool.put(companyIdObj, themes);
		}

		return themes;
	}

	private static String _getVersion(String version) {
		int x = version.indexOf(StringPool.PERIOD);
		int y = version.indexOf(StringPool.PERIOD, x + 1);

		if ((x == -1) || (y == -1)) {
			return version;
		}
		else {
			return version.substring(0, y);
		}
	}

	private static void _readColorSchemes(
			Element theme, Map colorSchemes, ContextReplace themeContextReplace)
		throws IOException {

		Iterator itr = theme.elements("color-scheme").iterator();

		while (itr.hasNext()) {
			Element colorScheme = (Element)itr.next();

			ContextReplace colorSchemeContextReplace =
				(ContextReplace)themeContextReplace.clone();

			String id = colorScheme.attributeValue("id");

			colorSchemeContextReplace.addValue("color-scheme-id", id);

			ColorScheme colorSchemeModel =
				(ColorScheme)colorSchemes.get(id);

			if (colorSchemeModel == null) {
				colorSchemeModel = new ColorSchemeImpl(id);
			}

			String name = GetterUtil.getString(
				colorScheme.attributeValue("name"), colorSchemeModel.getName());

			name = colorSchemeContextReplace.replace(name);

			boolean defaultCs = GetterUtil.getBoolean(
				colorScheme.elementText("default-cs"),
				colorSchemeModel.isDefaultCs());

			String cssClass = GetterUtil.getString(
				colorScheme.elementText("css-class"),
				colorSchemeModel.getCssClass());

			cssClass = colorSchemeContextReplace.replace(cssClass);

			colorSchemeContextReplace.addValue("css-class", cssClass);

			String colorSchemeImagesPath = GetterUtil.getString(
				colorScheme.elementText("color-scheme-images-path"),
				colorSchemeModel.getColorSchemeImagesPath());

			colorSchemeImagesPath = colorSchemeContextReplace.replace(
				colorSchemeImagesPath);

			colorSchemeContextReplace.addValue(
				"color-scheme-images-path", colorSchemeImagesPath);

			colorSchemeModel.setName(name);
			colorSchemeModel.setDefaultCs(defaultCs);
			colorSchemeModel.setCssClass(cssClass);
			colorSchemeModel.setColorSchemeImagesPath(colorSchemeImagesPath);

			colorSchemes.put(id, colorSchemeModel);
		}
	}

	private static Set _readThemes(
			String servletContextName, ServletContext ctx,
			boolean loadFromServletContext, String xml,
			PluginPackage pluginPackage)
		throws DocumentException, IOException {

		Set themeIds = new HashSet();

		if (xml == null) {
			return themeIds;
		}

		SAXReader reader = SAXReaderFactory.getInstance();

		Document doc = reader.read(new XMLSafeReader(xml));

		Element root = doc.getRootElement();

		Set compatibleVersions = new HashSet();

		Element compatibilityEl = root.element("compatibility");

		if (compatibilityEl != null) {
			Iterator itr = compatibilityEl.elements("version").iterator();

			while (itr.hasNext()) {
				Element versionEl = (Element)itr.next();

				String version = versionEl.getTextTrim();

				version = _getVersion(version);

				compatibleVersions.add(version);
			}
		}

		if (!compatibleVersions.contains(
				_getVersion(ReleaseInfo.getVersion()))) {

			_log.error(
				"Themes in this WAR are not compatible with " +
					ReleaseInfo.getServerInfo());

			return themeIds;
		}

		ThemeCompanyLimit companyLimit = null;

		Element companyLimitEl = root.element("company-limit");

		if (companyLimitEl != null) {
			companyLimit = new ThemeCompanyLimit();

			Element companyIncludesEl =
				companyLimitEl.element("company-includes");

			if (companyIncludesEl != null) {
				companyLimit.setIncludes(
					_getCompanyLimitIncludes(companyIncludesEl));
			}

			Element companyExcludesEl =
				companyLimitEl.element("company-excludes");

			if (companyExcludesEl != null) {
				companyLimit.setExcludes(
					_getCompanyLimitExcludes(companyExcludesEl));
			}
		}

		ThemeGroupLimit groupLimit = null;

		Element groupLimitEl = root.element("group-limit");

		if (groupLimitEl != null) {
			groupLimit = new ThemeGroupLimit();

			Element groupIncludesEl = groupLimitEl.element("group-includes");

			if (groupIncludesEl != null) {
				groupLimit.setIncludes(_getGroupLimitIncludes(groupIncludesEl));
			}

			Element groupExcludesEl =
				groupLimitEl.element("group-excludes");

			if (groupExcludesEl != null) {
				groupLimit.setExcludes(_getGroupLimitExcludes(groupExcludesEl));
			}
		}

		Iterator itr1 = root.elements("theme").iterator();

		while (itr1.hasNext()) {
			Element theme = (Element)itr1.next();

			ContextReplace themeContextReplace = new ContextReplace();

			String themeId = theme.attributeValue("id");

			if (servletContextName != null) {
				themeId =
					themeId + PortletImpl.WAR_SEPARATOR + servletContextName;
			}

			themeId = PortalUtil.getJsSafePortletName(themeId);

			themeContextReplace.addValue("theme-id", themeId);

			themeIds.add(themeId);

			Theme themeModel = (Theme)_themes.get(themeId);

			if (themeModel == null) {
				themeModel = new ThemeImpl(themeId);

				_themes.put(themeId, themeModel);
			}

			PluginSetting pluginSetting =
				PluginSettingLocalServiceUtil.getDefaultPluginSetting();

			themeModel.setPluginPackage(pluginPackage);
			themeModel.setDefaultPluginSetting(pluginSetting);

			themeModel.setThemeCompanyLimit(companyLimit);
			themeModel.setThemeGroupLimit(groupLimit);

			if (servletContextName != null) {
				themeModel.setServletContextName(servletContextName);
			}

			themeModel.setLoadFromServletContext(loadFromServletContext);

			String name = GetterUtil.getString(
				theme.attributeValue("name"), themeModel.getName());

			String rootPath = GetterUtil.getString(
				theme.elementText("root-path"), themeModel.getRootPath());

			rootPath = themeContextReplace.replace(rootPath);

			themeContextReplace.addValue("root-path", rootPath);

			String templatesPath = GetterUtil.getString(
				theme.elementText("templates-path"),
				themeModel.getTemplatesPath());

			templatesPath = themeContextReplace.replace(templatesPath);

			themeContextReplace.addValue("templates-path", templatesPath);

			String cssPath = GetterUtil.getString(
				theme.elementText("css-path"), themeModel.getCssPath());

			cssPath = themeContextReplace.replace(cssPath);

			themeContextReplace.addValue("css-path", cssPath);

			String imagesPath = GetterUtil.getString(
				theme.elementText("images-path"),
				themeModel.getImagesPath());

			imagesPath = themeContextReplace.replace(imagesPath);

			themeContextReplace.addValue("images-path", imagesPath);

			String javaScriptPath = GetterUtil.getString(
				theme.elementText("javascript-path"),
				themeModel.getJavaScriptPath());

			javaScriptPath = themeContextReplace.replace(javaScriptPath);

			themeContextReplace.addValue("javascript-path", javaScriptPath);

			String virtualPath = GetterUtil.getString(
				theme.elementText("virtual-path"), themeModel.getVirtualPath());

			String templateExtension = GetterUtil.getString(
				theme.elementText("template-extension"),
				themeModel.getTemplateExtension());

			themeModel.setName(name);
			themeModel.setRootPath(rootPath);
			themeModel.setTemplatesPath(templatesPath);
			themeModel.setCssPath(cssPath);
			themeModel.setImagesPath(imagesPath);
			themeModel.setJavaScriptPath(javaScriptPath);
			themeModel.setVirtualPath(virtualPath);
			themeModel.setTemplateExtension(templateExtension);

			Element settingsEl = theme.element("settings");

			if (settingsEl != null) {
				Iterator itr2 = settingsEl.elements("setting").iterator();

				while (itr2.hasNext()) {
					Element settingEl = (Element)itr2.next();

					String key = settingEl.attributeValue("key");
					String value = settingEl.attributeValue("value");

					themeModel.setSetting(key, value);
				}
			}

			themeModel.setWapTheme(GetterUtil.getBoolean(
				theme.elementText("wap-theme"), themeModel.isWapTheme()));

			Element rolesEl = theme.element("roles");

			if (rolesEl != null) {
				Iterator itr2 = rolesEl.elements("role-name").iterator();

				while (itr2.hasNext()) {
					Element roleNameEl = (Element)itr2.next();

					pluginSetting.addRole(roleNameEl.getText());
				}
			}

			_readColorSchemes(
				theme, themeModel.getColorSchemesMap(), themeContextReplace);
			_readColorSchemes(
				theme, themeModel.getColorSchemesMap(), themeContextReplace);

			Element layoutTemplatesEl = theme.element("layout-templates");

			if (layoutTemplatesEl != null) {
				Element standardEl = layoutTemplatesEl.element("standard");

				if (standardEl != null) {
					LayoutTemplateLocalUtil.readLayoutTemplate(
						null, ctx, null, standardEl, true, themeId,
						pluginPackage);
				}

				Element customEl = layoutTemplatesEl.element("custom");

				if (customEl != null) {
					LayoutTemplateLocalUtil.readLayoutTemplate(
						null, ctx, null, customEl, false, themeId,
						pluginPackage);
				}
			}
		}

		return themeIds;
	}

	private static Log _log = LogFactory.getLog(ThemeLocalUtil.class);

	private static Map _themes = CollectionFactory.getSyncHashMap();
	private static Map _themesPool = CollectionFactory.getSyncHashMap();

}