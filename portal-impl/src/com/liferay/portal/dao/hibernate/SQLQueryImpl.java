/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.dao.hibernate;

import com.liferay.portal.kernel.dao.hibernate.HibernateException;
import com.liferay.portal.kernel.dao.hibernate.Query;
import com.liferay.portal.kernel.dao.hibernate.SQLQuery;
import com.liferay.portal.kernel.dao.hibernate.ScrollableResults;
import com.liferay.portal.kernel.dao.hibernate.Type;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Iterator;
import java.util.List;

/**
 * <a href="SQLQueryImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SQLQueryImpl implements SQLQuery {

	public SQLQueryImpl(org.hibernate.SQLQuery sqlQuery) {
		_sqlQuery = sqlQuery;
	}

	public SQLQuery addEntity(String alias, Class entityClass) {
		_sqlQuery.addEntity(alias, entityClass);

		return this;
	}

	public SQLQuery addScalar(String columnAlias, Type type) {
		_sqlQuery.addScalar(columnAlias, TypeTranslator.translate(type));

		return this;
	}

	public Iterator iterate() throws HibernateException {
		try {
			return _sqlQuery.iterate();
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	public List list() throws HibernateException {
		try {
			return _sqlQuery.list();
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	public ScrollableResults scroll() throws HibernateException {
		try {
			return new ScrollableResultsImpl(_sqlQuery.scroll());
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	public Query setBoolean(int pos, boolean value) {
		_sqlQuery.setBoolean(pos, value);

		return this;
	}

	public Query setDouble(int pos, double value) {
		_sqlQuery.setDouble(pos, value);

		return this;
	}

	public Query setFirstResult(int firstResult) {
		_sqlQuery.setFirstResult(firstResult);

		return this;
	}

	public Query setFloat(int pos, float value) {
		_sqlQuery.setFloat(pos, value);

		return this;
	}

	public Query setInteger(int pos, int value) {
		_sqlQuery.setInteger(pos, value);

		return this;
	}

	public Query setLong(int pos, long value) {
		_sqlQuery.setLong(pos, value);

		return this;
	}

	public Query setMaxResults(int maxResults) {
		_sqlQuery.setMaxResults(maxResults);

		return this;
	}

	public Query setSerializable(int pos, Serializable value) {
		_sqlQuery.setSerializable(pos, value);

		return this;
	}

	public Query setShort(int pos, short value) {
		_sqlQuery.setShort(pos, value);

		return this;
	}

	public Query setString(int pos, String value) {
		_sqlQuery.setString(pos, value);

		return this;
	}

	public Query setTimestamp(int pos, Timestamp value) {
		_sqlQuery.setTimestamp(pos, value);

		return this;
	}

	public Object uniqueResult() throws HibernateException {
		try {
			return _sqlQuery.uniqueResult();
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	private org.hibernate.SQLQuery _sqlQuery;

}