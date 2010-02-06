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

package com.liferay.portal.util;

/**
 * <a href="PrefsPropsUtil_IW.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class PrefsPropsUtil_IW {
	public static PrefsPropsUtil_IW getInstance() {
		return _instance;
	}

	public boolean getBoolean(long companyId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getBoolean(companyId, name);
	}

	public boolean getBoolean(long companyId, java.lang.String name,
		boolean defaultValue) throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getBoolean(companyId, name, defaultValue);
	}

	public boolean getBoolean(javax.portlet.PortletPreferences preferences,
		long companyId, java.lang.String name) {
		return PrefsPropsUtil.getBoolean(preferences, companyId, name);
	}

	public boolean getBoolean(javax.portlet.PortletPreferences preferences,
		long companyId, java.lang.String name, boolean defaultValue) {
		return PrefsPropsUtil.getBoolean(preferences, companyId, name,
			defaultValue);
	}

	public boolean getBoolean(java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getBoolean(name);
	}

	public boolean getBoolean(java.lang.String name, boolean defaultValue)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getBoolean(name, defaultValue);
	}

	public java.lang.String getContent(long companyId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getContent(companyId, name);
	}

	public java.lang.String getContent(
		javax.portlet.PortletPreferences preferences, long companyId,
		java.lang.String name) {
		return PrefsPropsUtil.getContent(preferences, companyId, name);
	}

	public java.lang.String getContent(java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getContent(name);
	}

	public double getDouble(long companyId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getDouble(companyId, name);
	}

	public double getDouble(long companyId, java.lang.String name,
		double defaultValue) throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getDouble(companyId, name, defaultValue);
	}

	public double getDouble(javax.portlet.PortletPreferences preferences,
		long companyId, java.lang.String name) {
		return PrefsPropsUtil.getDouble(preferences, companyId, name);
	}

	public double getDouble(javax.portlet.PortletPreferences preferences,
		long companyId, java.lang.String name, double defaultValue) {
		return PrefsPropsUtil.getDouble(preferences, companyId, name,
			defaultValue);
	}

	public double getDouble(java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getDouble(name);
	}

	public double getDouble(java.lang.String name, double defaultValue)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getDouble(name, defaultValue);
	}

	public int getInteger(long companyId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getInteger(companyId, name);
	}

	public int getInteger(long companyId, java.lang.String name,
		int defaultValue) throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getInteger(companyId, name, defaultValue);
	}

	public int getInteger(javax.portlet.PortletPreferences preferences,
		long companyId, java.lang.String name) {
		return PrefsPropsUtil.getInteger(preferences, companyId, name);
	}

	public int getInteger(javax.portlet.PortletPreferences preferences,
		long companyId, java.lang.String name, int defaultValue) {
		return PrefsPropsUtil.getInteger(preferences, companyId, name,
			defaultValue);
	}

	public int getInteger(java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getInteger(name);
	}

	public int getInteger(java.lang.String name, int defaultValue)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getInteger(name, defaultValue);
	}

	public long getLong(long companyId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getLong(companyId, name);
	}

	public long getLong(long companyId, java.lang.String name, long defaultValue)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getLong(companyId, name, defaultValue);
	}

	public long getLong(javax.portlet.PortletPreferences preferences,
		long companyId, java.lang.String name) {
		return PrefsPropsUtil.getLong(preferences, companyId, name);
	}

	public long getLong(javax.portlet.PortletPreferences preferences,
		long companyId, java.lang.String name, long defaultValue) {
		return PrefsPropsUtil.getLong(preferences, companyId, name, defaultValue);
	}

	public long getLong(java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getLong(name);
	}

	public long getLong(java.lang.String name, long defaultValue)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getLong(name, defaultValue);
	}

	public javax.portlet.PortletPreferences getPreferences()
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getPreferences();
	}

	public javax.portlet.PortletPreferences getPreferences(long companyId)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getPreferences(companyId);
	}

	public short getShort(long companyId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getShort(companyId, name);
	}

	public short getShort(long companyId, java.lang.String name,
		short defaultValue) throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getShort(companyId, name, defaultValue);
	}

	public short getShort(javax.portlet.PortletPreferences preferences,
		long companyId, java.lang.String name) {
		return PrefsPropsUtil.getShort(preferences, companyId, name);
	}

	public short getShort(javax.portlet.PortletPreferences preferences,
		long companyId, java.lang.String name, short defaultValue) {
		return PrefsPropsUtil.getShort(preferences, companyId, name,
			defaultValue);
	}

	public short getShort(java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getShort(name);
	}

	public short getShort(java.lang.String name, short defaultValue)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getShort(name, defaultValue);
	}

	public java.lang.String getString(long companyId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getString(companyId, name);
	}

	public java.lang.String getString(long companyId, java.lang.String name,
		java.lang.String defaultValue)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getString(companyId, name, defaultValue);
	}

	public java.lang.String getString(
		javax.portlet.PortletPreferences preferences, long companyId,
		java.lang.String name) {
		return PrefsPropsUtil.getString(preferences, companyId, name);
	}

	public java.lang.String getString(
		javax.portlet.PortletPreferences preferences, long companyId,
		java.lang.String name, boolean defaultValue) {
		return PrefsPropsUtil.getString(preferences, companyId, name,
			defaultValue);
	}

	public java.lang.String getString(
		javax.portlet.PortletPreferences preferences, long companyId,
		java.lang.String name, double defaultValue) {
		return PrefsPropsUtil.getString(preferences, companyId, name,
			defaultValue);
	}

	public java.lang.String getString(
		javax.portlet.PortletPreferences preferences, long companyId,
		java.lang.String name, int defaultValue) {
		return PrefsPropsUtil.getString(preferences, companyId, name,
			defaultValue);
	}

	public java.lang.String getString(
		javax.portlet.PortletPreferences preferences, long companyId,
		java.lang.String name, long defaultValue) {
		return PrefsPropsUtil.getString(preferences, companyId, name,
			defaultValue);
	}

	public java.lang.String getString(
		javax.portlet.PortletPreferences preferences, long companyId,
		java.lang.String name, short defaultValue) {
		return PrefsPropsUtil.getString(preferences, companyId, name,
			defaultValue);
	}

	public java.lang.String getString(
		javax.portlet.PortletPreferences preferences, long companyId,
		java.lang.String name, java.lang.String defaultValue) {
		return PrefsPropsUtil.getString(preferences, companyId, name,
			defaultValue);
	}

	public java.lang.String getString(java.lang.String name)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getString(name);
	}

	public java.lang.String getString(java.lang.String name,
		java.lang.String defaultValue)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getString(name, defaultValue);
	}

	public java.lang.String[] getStringArray(long companyId,
		java.lang.String name, java.lang.String delimiter)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getStringArray(companyId, name, delimiter);
	}

	public java.lang.String[] getStringArray(long companyId,
		java.lang.String name, java.lang.String delimiter,
		java.lang.String[] defaultValue)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getStringArray(companyId, name, delimiter,
			defaultValue);
	}

	public java.lang.String[] getStringArray(
		javax.portlet.PortletPreferences preferences, long companyId,
		java.lang.String name, java.lang.String delimiter) {
		return PrefsPropsUtil.getStringArray(preferences, companyId, name,
			delimiter);
	}

	public java.lang.String[] getStringArray(
		javax.portlet.PortletPreferences preferences, long companyId,
		java.lang.String name, java.lang.String delimiter,
		java.lang.String[] defaultValue) {
		return PrefsPropsUtil.getStringArray(preferences, companyId, name,
			delimiter, defaultValue);
	}

	public java.lang.String[] getStringArray(java.lang.String name,
		java.lang.String delimiter) throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getStringArray(name, delimiter);
	}

	public java.lang.String[] getStringArray(java.lang.String name,
		java.lang.String delimiter, java.lang.String[] defaultValue)
		throws com.liferay.portal.SystemException {
		return PrefsPropsUtil.getStringArray(name, delimiter, defaultValue);
	}

	private PrefsPropsUtil_IW() {
	}

	private static PrefsPropsUtil_IW _instance = new PrefsPropsUtil_IW();
}