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

package com.liferay.portal.lastmodified;

import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.ColorScheme;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <a href="LastModifiedAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class LastModifiedAction extends Action {

	public ActionForward execute(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Theme theme = themeDisplay.getTheme();
		ColorScheme colorScheme = themeDisplay.getColorScheme();

		String themeId = ParamUtil.getString(request, "themeId");
		String colorSchemeId = ParamUtil.getString(request, "colorSchemeId");

		// The layout id is not always passed to this request. Therefore,
		// ServicePreAction could set the wrong theme and color scheme. This
		// check ensures that the theme and color scheme used is the one passed
		// to it from the request parameters.

		if ((theme == null) || (colorScheme == null) ||
			(!theme.getThemeId().equals(themeId)) ||
			(!colorScheme.getColorSchemeId().equals(colorSchemeId))) {

			boolean wapTheme = BrowserSnifferUtil.isWap(request);

			theme = ThemeLocalServiceUtil.getTheme(
				themeDisplay.getCompanyId(), themeId, wapTheme);
			colorScheme = ThemeLocalServiceUtil.getColorScheme(
				themeDisplay.getCompanyId(), themeId, colorSchemeId, wapTheme);

			themeDisplay.setLookAndFeel(theme, colorScheme);

			request.setAttribute(WebKeys.THEME, theme);
			request.setAttribute(WebKeys.COLOR_SCHEME, colorScheme);
		}

		response.addHeader(HttpHeaders.CACHE_CONTROL, "max-age=0");

		response.setDateHeader(
			HttpHeaders.LAST_MODIFIED, System.currentTimeMillis());

		return mapping.findForward("modified.jsp");
	}

	public abstract String getLastModifiedKey(HttpServletRequest request);

	public abstract String getLastModifiedValue(String key);

	public abstract void setLastModifiedValue(String key, String value);

}