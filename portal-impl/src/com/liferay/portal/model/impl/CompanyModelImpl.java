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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanySoap;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="CompanyModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>Company</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portal.model.Company
 * @see com.liferay.portal.model.CompanyModel
 * @see com.liferay.portal.model.impl.CompanyImpl
 *
 */
public class CompanyModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "Company";
	public static final Object[][] TABLE_COLUMNS = {
			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "accountId", new Integer(Types.BIGINT) },
			

			{ "webId", new Integer(Types.VARCHAR) },
			

			{ "key_", new Integer(Types.CLOB) },
			

			{ "virtualHost", new Integer(Types.VARCHAR) },
			

			{ "mx", new Integer(Types.VARCHAR) },
			

			{ "homeURL", new Integer(Types.VARCHAR) },
			

			{ "logoId", new Integer(Types.BIGINT) }
		};
	public static final String TABLE_SQL_CREATE = "create table Company (companyId LONG not null primary key,accountId LONG,webId VARCHAR(75) null,key_ TEXT null,virtualHost VARCHAR(75) null,mx VARCHAR(75) null,homeURL VARCHAR(1024) null,logoId LONG)";
	public static final String TABLE_SQL_DROP = "drop table Company";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.Company"),
			true);

	public static Company toModel(CompanySoap soapModel) {
		Company model = new CompanyImpl();

		model.setCompanyId(soapModel.getCompanyId());
		model.setAccountId(soapModel.getAccountId());
		model.setWebId(soapModel.getWebId());
		model.setKey(soapModel.getKey());
		model.setVirtualHost(soapModel.getVirtualHost());
		model.setMx(soapModel.getMx());
		model.setHomeURL(soapModel.getHomeURL());
		model.setLogoId(soapModel.getLogoId());

		return model;
	}

	public static List<Company> toModels(CompanySoap[] soapModels) {
		List<Company> models = new ArrayList<Company>(soapModels.length);

		for (CompanySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.Company"));

	public CompanyModelImpl() {
	}

	public long getPrimaryKey() {
		return _companyId;
	}

	public void setPrimaryKey(long pk) {
		setCompanyId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_companyId);
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		if (companyId != _companyId) {
			_companyId = companyId;
		}
	}

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		if (accountId != _accountId) {
			_accountId = accountId;
		}
	}

	public String getWebId() {
		return GetterUtil.getString(_webId);
	}

	public void setWebId(String webId) {
		if (((webId == null) && (_webId != null)) ||
				((webId != null) && (_webId == null)) ||
				((webId != null) && (_webId != null) && !webId.equals(_webId))) {
			_webId = webId;
		}
	}

	public String getKey() {
		return GetterUtil.getString(_key);
	}

	public void setKey(String key) {
		if (((key == null) && (_key != null)) ||
				((key != null) && (_key == null)) ||
				((key != null) && (_key != null) && !key.equals(_key))) {
			_key = key;
		}
	}

	public String getVirtualHost() {
		return GetterUtil.getString(_virtualHost);
	}

	public void setVirtualHost(String virtualHost) {
		if (((virtualHost == null) && (_virtualHost != null)) ||
				((virtualHost != null) && (_virtualHost == null)) ||
				((virtualHost != null) && (_virtualHost != null) &&
				!virtualHost.equals(_virtualHost))) {
			_virtualHost = virtualHost;
		}
	}

	public String getMx() {
		return GetterUtil.getString(_mx);
	}

	public void setMx(String mx) {
		if (((mx == null) && (_mx != null)) || ((mx != null) && (_mx == null)) ||
				((mx != null) && (_mx != null) && !mx.equals(_mx))) {
			_mx = mx;
		}
	}

	public String getHomeURL() {
		return GetterUtil.getString(_homeURL);
	}

	public void setHomeURL(String homeURL) {
		if (((homeURL == null) && (_homeURL != null)) ||
				((homeURL != null) && (_homeURL == null)) ||
				((homeURL != null) && (_homeURL != null) &&
				!homeURL.equals(_homeURL))) {
			_homeURL = homeURL;
		}
	}

	public long getLogoId() {
		return _logoId;
	}

	public void setLogoId(long logoId) {
		if (logoId != _logoId) {
			_logoId = logoId;
		}
	}

	public Company toEscapedModel() {
		if (isEscapedModel()) {
			return (Company)this;
		}
		else {
			Company model = new CompanyImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setCompanyId(getCompanyId());
			model.setAccountId(getAccountId());
			model.setWebId(HtmlUtil.escape(getWebId()));
			model.setKey(HtmlUtil.escape(getKey()));
			model.setVirtualHost(HtmlUtil.escape(getVirtualHost()));
			model.setMx(HtmlUtil.escape(getMx()));
			model.setHomeURL(HtmlUtil.escape(getHomeURL()));
			model.setLogoId(getLogoId());

			model = (Company)Proxy.newProxyInstance(Company.class.getClassLoader(),
					new Class[] { Company.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(Company.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		CompanyImpl clone = new CompanyImpl();

		clone.setCompanyId(getCompanyId());
		clone.setAccountId(getAccountId());
		clone.setWebId(getWebId());
		clone.setKey(getKey());
		clone.setVirtualHost(getVirtualHost());
		clone.setMx(getMx());
		clone.setHomeURL(getHomeURL());
		clone.setLogoId(getLogoId());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		CompanyImpl company = (CompanyImpl)obj;

		long pk = company.getPrimaryKey();

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

		CompanyImpl company = null;

		try {
			company = (CompanyImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = company.getPrimaryKey();

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

	private long _companyId;
	private long _accountId;
	private String _webId;
	private String _key;
	private String _virtualHost;
	private String _mx;
	private String _homeURL;
	private long _logoId;
	private transient ExpandoBridge _expandoBridge;
}