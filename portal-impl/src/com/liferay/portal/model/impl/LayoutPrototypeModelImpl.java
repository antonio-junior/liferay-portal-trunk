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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.model.LayoutPrototypeSoap;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import com.liferay.util.LocalizationUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * <a href="LayoutPrototypeModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the LayoutPrototype table in the
 * database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see    LayoutPrototypeImpl
 * @see    com.liferay.portal.model.LayoutPrototype
 * @see    com.liferay.portal.model.LayoutPrototypeModel
 */
public class LayoutPrototypeModelImpl extends BaseModelImpl<LayoutPrototype> {
	public static final String TABLE_NAME = "LayoutPrototype";
	public static final Object[][] TABLE_COLUMNS = {
			{ "layoutPrototypeId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "name", new Integer(Types.VARCHAR) },
			

			{ "description", new Integer(Types.VARCHAR) },
			

			{ "settings_", new Integer(Types.VARCHAR) },
			

			{ "active_", new Integer(Types.BOOLEAN) }
		};
	public static final String TABLE_SQL_CREATE = "create table LayoutPrototype (layoutPrototypeId LONG not null primary key,companyId LONG,name STRING null,description STRING null,settings_ STRING null,active_ BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table LayoutPrototype";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.LayoutPrototype"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.LayoutPrototype"),
			true);

	public static LayoutPrototype toModel(LayoutPrototypeSoap soapModel) {
		LayoutPrototype model = new LayoutPrototypeImpl();

		model.setLayoutPrototypeId(soapModel.getLayoutPrototypeId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setSettings(soapModel.getSettings());
		model.setActive(soapModel.getActive());

		return model;
	}

	public static List<LayoutPrototype> toModels(
		LayoutPrototypeSoap[] soapModels) {
		List<LayoutPrototype> models = new ArrayList<LayoutPrototype>(soapModels.length);

		for (LayoutPrototypeSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.LayoutPrototype"));

	public LayoutPrototypeModelImpl() {
	}

	public long getPrimaryKey() {
		return _layoutPrototypeId;
	}

	public void setPrimaryKey(long pk) {
		setLayoutPrototypeId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_layoutPrototypeId);
	}

	public long getLayoutPrototypeId() {
		return _layoutPrototypeId;
	}

	public void setLayoutPrototypeId(long layoutPrototypeId) {
		_layoutPrototypeId = layoutPrototypeId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public String getName() {
		return GetterUtil.getString(_name);
	}

	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	public String getName(String languageId) {
		String value = LocalizationUtil.getLocalization(getName(), languageId);

		if (isEscapedModel()) {
			return HtmlUtil.escape(value);
		}
		else {
			return value;
		}
	}

	public String getName(String languageId, boolean useDefault) {
		String value = LocalizationUtil.getLocalization(getName(), languageId,
				useDefault);

		if (isEscapedModel()) {
			return HtmlUtil.escape(value);
		}
		else {
			return value;
		}
	}

	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	public void setName(String name) {
		_name = name;
	}

	public void setName(Locale locale, String name) {
		String languageId = LocaleUtil.toLanguageId(locale);

		if (Validator.isNotNull(name)) {
			setName(LocalizationUtil.updateLocalization(getName(), "Name",
					name, languageId));
		}
		else {
			setName(LocalizationUtil.removeLocalization(getName(), "Name",
					languageId));
		}
	}

	public void setNameMap(Map<Locale, String> nameMap) {
		if (nameMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			Locale[] locales = LanguageUtil.getAvailableLocales();

			for (Locale locale : locales) {
				String name = nameMap.get(locale);

				setName(locale, name);
			}
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	public String getDescription() {
		return GetterUtil.getString(_description);
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getSettings() {
		return GetterUtil.getString(_settings);
	}

	public void setSettings(String settings) {
		_settings = settings;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public LayoutPrototype toEscapedModel() {
		if (isEscapedModel()) {
			return (LayoutPrototype)this;
		}
		else {
			LayoutPrototype model = new LayoutPrototypeImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setLayoutPrototypeId(getLayoutPrototypeId());
			model.setCompanyId(getCompanyId());
			model.setName(getName());
			model.setDescription(HtmlUtil.escape(getDescription()));
			model.setSettings(HtmlUtil.escape(getSettings()));
			model.setActive(getActive());

			model = (LayoutPrototype)Proxy.newProxyInstance(LayoutPrototype.class.getClassLoader(),
					new Class[] { LayoutPrototype.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(LayoutPrototype.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		LayoutPrototypeImpl clone = new LayoutPrototypeImpl();

		clone.setLayoutPrototypeId(getLayoutPrototypeId());
		clone.setCompanyId(getCompanyId());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setSettings(getSettings());
		clone.setActive(getActive());

		return clone;
	}

	public int compareTo(LayoutPrototype layoutPrototype) {
		long pk = layoutPrototype.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		LayoutPrototype layoutPrototype = null;

		try {
			layoutPrototype = (LayoutPrototype)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = layoutPrototype.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{layoutPrototypeId=");
		sb.append(getLayoutPrototypeId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", settings=");
		sb.append(getSettings());
		sb.append(", active=");
		sb.append(getActive());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.LayoutPrototype");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>layoutPrototypeId</column-name><column-value><![CDATA[");
		sb.append(getLayoutPrototypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>settings</column-name><column-value><![CDATA[");
		sb.append(getSettings());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _layoutPrototypeId;
	private long _companyId;
	private String _name;
	private String _description;
	private String _settings;
	private boolean _active;
	private transient ExpandoBridge _expandoBridge;
}