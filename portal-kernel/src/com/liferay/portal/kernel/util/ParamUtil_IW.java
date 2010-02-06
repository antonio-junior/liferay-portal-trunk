/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.kernel.util;

/**
 * <a href="ParamUtil_IW.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class ParamUtil_IW {
	public static ParamUtil_IW getInstance() {
		return _instance;
	}

	public boolean get(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, boolean defaultValue) {
		return ParamUtil.get(request, param, defaultValue);
	}

	public java.util.Date get(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, java.text.DateFormat dateFormat,
		java.util.Date defaultValue) {
		return ParamUtil.get(request, param, dateFormat, defaultValue);
	}

	public double get(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, double defaultValue) {
		return ParamUtil.get(request, param, defaultValue);
	}

	public float get(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, float defaultValue) {
		return ParamUtil.get(request, param, defaultValue);
	}

	public int get(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, int defaultValue) {
		return ParamUtil.get(request, param, defaultValue);
	}

	public long get(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, long defaultValue) {
		return ParamUtil.get(request, param, defaultValue);
	}

	public short get(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, short defaultValue) {
		return ParamUtil.get(request, param, defaultValue);
	}

	public java.lang.String get(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, java.lang.String defaultValue) {
		return ParamUtil.get(request, param, defaultValue);
	}

	public boolean get(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, boolean defaultValue) {
		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public java.util.Date get(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, java.text.DateFormat dateFormat,
		java.util.Date defaultValue) {
		return ParamUtil.get(portletRequest, param, dateFormat, defaultValue);
	}

	public double get(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, double defaultValue) {
		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public float get(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, float defaultValue) {
		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public int get(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, int defaultValue) {
		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public long get(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, long defaultValue) {
		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public short get(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, short defaultValue) {
		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public java.lang.String get(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, java.lang.String defaultValue) {
		return ParamUtil.get(portletRequest, param, defaultValue);
	}

	public boolean getBoolean(javax.servlet.http.HttpServletRequest request,
		java.lang.String param) {
		return ParamUtil.getBoolean(request, param);
	}

	public boolean getBoolean(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, boolean defaultValue) {
		return ParamUtil.getBoolean(request, param, defaultValue);
	}

	public boolean getBoolean(javax.portlet.PortletRequest portletRequest,
		java.lang.String param) {
		return ParamUtil.getBoolean(portletRequest, param);
	}

	public boolean getBoolean(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, boolean defaultValue) {
		return ParamUtil.getBoolean(portletRequest, param, defaultValue);
	}

	public boolean[] getBooleanValues(
		javax.servlet.http.HttpServletRequest request, java.lang.String param) {
		return ParamUtil.getBooleanValues(request, param);
	}

	public boolean[] getBooleanValues(
		javax.servlet.http.HttpServletRequest request, java.lang.String param,
		boolean[] defaultValue) {
		return ParamUtil.getBooleanValues(request, param, defaultValue);
	}

	public boolean[] getBooleanValues(
		javax.portlet.PortletRequest portletRequest, java.lang.String param) {
		return ParamUtil.getBooleanValues(portletRequest, param);
	}

	public boolean[] getBooleanValues(
		javax.portlet.PortletRequest portletRequest, java.lang.String param,
		boolean[] defaultValue) {
		return ParamUtil.getBooleanValues(portletRequest, param, defaultValue);
	}

	public java.util.Date getDate(
		javax.servlet.http.HttpServletRequest request, java.lang.String param,
		java.text.DateFormat dateFormat) {
		return ParamUtil.getDate(request, param, dateFormat);
	}

	public java.util.Date getDate(
		javax.servlet.http.HttpServletRequest request, java.lang.String param,
		java.text.DateFormat dateFormat, java.util.Date defaultValue) {
		return ParamUtil.getDate(request, param, dateFormat, defaultValue);
	}

	public java.util.Date getDate(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, java.text.DateFormat dateFormat) {
		return ParamUtil.getDate(portletRequest, param, dateFormat);
	}

	public java.util.Date getDate(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, java.text.DateFormat dateFormat,
		java.util.Date defaultValue) {
		return ParamUtil.getDate(portletRequest, param, dateFormat, defaultValue);
	}

	public java.util.Date[] getDateValues(
		javax.servlet.http.HttpServletRequest request, java.lang.String param,
		java.text.DateFormat dateFormat) {
		return ParamUtil.getDateValues(request, param, dateFormat);
	}

	public java.util.Date[] getDateValues(
		javax.servlet.http.HttpServletRequest request, java.lang.String param,
		java.text.DateFormat dateFormat, java.util.Date[] defaultValue) {
		return ParamUtil.getDateValues(request, param, dateFormat, defaultValue);
	}

	public java.util.Date[] getDateValues(
		javax.portlet.PortletRequest portletRequest, java.lang.String param,
		java.text.DateFormat dateFormat) {
		return ParamUtil.getDateValues(portletRequest, param, dateFormat);
	}

	public java.util.Date[] getDateValues(
		javax.portlet.PortletRequest portletRequest, java.lang.String param,
		java.text.DateFormat dateFormat, java.util.Date[] defaultValue) {
		return ParamUtil.getDateValues(portletRequest, param, dateFormat,
			defaultValue);
	}

	public double getDouble(javax.servlet.http.HttpServletRequest request,
		java.lang.String param) {
		return ParamUtil.getDouble(request, param);
	}

	public double getDouble(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, double defaultValue) {
		return ParamUtil.getDouble(request, param, defaultValue);
	}

	public double getDouble(javax.portlet.PortletRequest portletRequest,
		java.lang.String param) {
		return ParamUtil.getDouble(portletRequest, param);
	}

	public double getDouble(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, double defaultValue) {
		return ParamUtil.getDouble(portletRequest, param, defaultValue);
	}

	public double[] getDoubleValues(
		javax.servlet.http.HttpServletRequest request, java.lang.String param) {
		return ParamUtil.getDoubleValues(request, param);
	}

	public double[] getDoubleValues(
		javax.servlet.http.HttpServletRequest request, java.lang.String param,
		double[] defaultValue) {
		return ParamUtil.getDoubleValues(request, param, defaultValue);
	}

	public double[] getDoubleValues(
		javax.portlet.PortletRequest portletRequest, java.lang.String param) {
		return ParamUtil.getDoubleValues(portletRequest, param);
	}

	public double[] getDoubleValues(
		javax.portlet.PortletRequest portletRequest, java.lang.String param,
		double[] defaultValue) {
		return ParamUtil.getDoubleValues(portletRequest, param, defaultValue);
	}

	public float getFloat(javax.servlet.http.HttpServletRequest request,
		java.lang.String param) {
		return ParamUtil.getFloat(request, param);
	}

	public float getFloat(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, float defaultValue) {
		return ParamUtil.getFloat(request, param, defaultValue);
	}

	public float getFloat(javax.portlet.PortletRequest portletRequest,
		java.lang.String param) {
		return ParamUtil.getFloat(portletRequest, param);
	}

	public float getFloat(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, float defaultValue) {
		return ParamUtil.getFloat(portletRequest, param, defaultValue);
	}

	public float[] getFloatValues(
		javax.servlet.http.HttpServletRequest request, java.lang.String param) {
		return ParamUtil.getFloatValues(request, param);
	}

	public float[] getFloatValues(
		javax.servlet.http.HttpServletRequest request, java.lang.String param,
		float[] defaultValue) {
		return ParamUtil.getFloatValues(request, param, defaultValue);
	}

	public float[] getFloatValues(javax.portlet.PortletRequest portletRequest,
		java.lang.String param) {
		return ParamUtil.getFloatValues(portletRequest, param);
	}

	public float[] getFloatValues(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, float[] defaultValue) {
		return ParamUtil.getFloatValues(portletRequest, param, defaultValue);
	}

	public int getInteger(javax.servlet.http.HttpServletRequest request,
		java.lang.String param) {
		return ParamUtil.getInteger(request, param);
	}

	public int getInteger(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, int defaultValue) {
		return ParamUtil.getInteger(request, param, defaultValue);
	}

	public int getInteger(javax.portlet.PortletRequest portletRequest,
		java.lang.String param) {
		return ParamUtil.getInteger(portletRequest, param);
	}

	public int getInteger(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, int defaultValue) {
		return ParamUtil.getInteger(portletRequest, param, defaultValue);
	}

	public int[] getIntegerValues(
		javax.servlet.http.HttpServletRequest request, java.lang.String param) {
		return ParamUtil.getIntegerValues(request, param);
	}

	public int[] getIntegerValues(
		javax.servlet.http.HttpServletRequest request, java.lang.String param,
		int[] defaultValue) {
		return ParamUtil.getIntegerValues(request, param, defaultValue);
	}

	public int[] getIntegerValues(javax.portlet.PortletRequest portletRequest,
		java.lang.String param) {
		return ParamUtil.getIntegerValues(portletRequest, param);
	}

	public int[] getIntegerValues(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, int[] defaultValue) {
		return ParamUtil.getIntegerValues(portletRequest, param, defaultValue);
	}

	public long getLong(javax.servlet.http.HttpServletRequest request,
		java.lang.String param) {
		return ParamUtil.getLong(request, param);
	}

	public long getLong(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, long defaultValue) {
		return ParamUtil.getLong(request, param, defaultValue);
	}

	public long getLong(javax.portlet.PortletRequest portletRequest,
		java.lang.String param) {
		return ParamUtil.getLong(portletRequest, param);
	}

	public long getLong(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, long defaultValue) {
		return ParamUtil.getLong(portletRequest, param, defaultValue);
	}

	public long[] getLongValues(javax.servlet.http.HttpServletRequest request,
		java.lang.String param) {
		return ParamUtil.getLongValues(request, param);
	}

	public long[] getLongValues(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, long[] defaultValue) {
		return ParamUtil.getLongValues(request, param, defaultValue);
	}

	public long[] getLongValues(javax.portlet.PortletRequest portletRequest,
		java.lang.String param) {
		return ParamUtil.getLongValues(portletRequest, param);
	}

	public long[] getLongValues(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, long[] defaultValue) {
		return ParamUtil.getLongValues(portletRequest, param, defaultValue);
	}

	public short getShort(javax.servlet.http.HttpServletRequest request,
		java.lang.String param) {
		return ParamUtil.getShort(request, param);
	}

	public short getShort(javax.servlet.http.HttpServletRequest request,
		java.lang.String param, short defaultValue) {
		return ParamUtil.getShort(request, param, defaultValue);
	}

	public short getShort(javax.portlet.PortletRequest portletRequest,
		java.lang.String param) {
		return ParamUtil.getShort(portletRequest, param);
	}

	public short getShort(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, short defaultValue) {
		return ParamUtil.getShort(portletRequest, param, defaultValue);
	}

	public short[] getShortValues(
		javax.servlet.http.HttpServletRequest request, java.lang.String param) {
		return ParamUtil.getShortValues(request, param);
	}

	public short[] getShortValues(
		javax.servlet.http.HttpServletRequest request, java.lang.String param,
		short[] defaultValue) {
		return ParamUtil.getShortValues(request, param, defaultValue);
	}

	public short[] getShortValues(javax.portlet.PortletRequest portletRequest,
		java.lang.String param) {
		return ParamUtil.getShortValues(portletRequest, param);
	}

	public short[] getShortValues(javax.portlet.PortletRequest portletRequest,
		java.lang.String param, short[] defaultValue) {
		return ParamUtil.getShortValues(portletRequest, param, defaultValue);
	}

	public java.lang.String getString(
		javax.servlet.http.HttpServletRequest request, java.lang.String param) {
		return ParamUtil.getString(request, param);
	}

	public java.lang.String getString(
		javax.servlet.http.HttpServletRequest request, java.lang.String param,
		java.lang.String defaultValue) {
		return ParamUtil.getString(request, param, defaultValue);
	}

	public java.lang.String getString(
		javax.portlet.PortletRequest portletRequest, java.lang.String param) {
		return ParamUtil.getString(portletRequest, param);
	}

	public java.lang.String getString(
		javax.portlet.PortletRequest portletRequest, java.lang.String param,
		java.lang.String defaultValue) {
		return ParamUtil.getString(portletRequest, param, defaultValue);
	}

	public void print(javax.servlet.http.HttpServletRequest request) {
		ParamUtil.print(request);
	}

	public void print(javax.portlet.PortletRequest portletRequest) {
		ParamUtil.print(portletRequest);
	}

	private ParamUtil_IW() {
	}

	private static ParamUtil_IW _instance = new ParamUtil_IW();
}