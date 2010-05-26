/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.search.lucene;

import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="BooleanQueryImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class BooleanQueryImpl implements BooleanQuery {

	public BooleanQueryImpl() {
		_booleanQuery = new org.apache.lucene.search.BooleanQuery();
	}

	public void add(Query query, BooleanClauseOccur occur)
		throws ParseException {

		try {
			_booleanQuery.add(
				QueryTranslator.translate(query),
				BooleanClauseOccurTranslator.translate(occur));
		}
		catch (org.apache.lucene.queryParser.ParseException pe) {
			throw new ParseException(pe.getMessage());
		}
	}

	public void addExactTerm(String field, boolean value) {
		LuceneHelperUtil.addExactTerm(_booleanQuery, field, value);
	}

	public void addExactTerm(String field, Boolean value) {
		LuceneHelperUtil.addExactTerm(_booleanQuery, field, value);
	}

	public void addExactTerm(String field, double value) {
		LuceneHelperUtil.addExactTerm(_booleanQuery, field, value);
	}

	public void addExactTerm(String field, Double value) {
		LuceneHelperUtil.addExactTerm(_booleanQuery, field, value);
	}

	public void addExactTerm(String field, int value) {
		LuceneHelperUtil.addExactTerm(_booleanQuery, field, value);
	}

	public void addExactTerm(String field, Integer value) {
		LuceneHelperUtil.addExactTerm(_booleanQuery, field, value);
	}

	public void addExactTerm(String field, long value) {
		LuceneHelperUtil.addExactTerm(_booleanQuery, field, value);
	}

	public void addExactTerm(String field, Long value) {
		LuceneHelperUtil.addExactTerm(_booleanQuery, field, value);
	}

	public void addExactTerm(String field, short value) {
		LuceneHelperUtil.addExactTerm(_booleanQuery, field, value);
	}

	public void addExactTerm(String field, Short value) {
		LuceneHelperUtil.addExactTerm(_booleanQuery, field, value);
	}

	public void addExactTerm(String field, String value) {
		LuceneHelperUtil.addExactTerm(_booleanQuery, field, value);
	}

	public void addKeywords(String[] fields, String keywords)
		throws ParseException {
		
		if (Validator.isNotNull(keywords)) {
			String value = "";

			String[] replacePatterns = {"$1$2$3", "$1"};

			List<String> values = new ArrayList<String>();

			for (String field : fields) {
				String[] patterns = {
					"(?i)^.*" + field + ":([\"\'])(.+?)(\\1).*$",
					"(?i)^.*" + field + ":([^\\s\"']*).*$"
				};

				String duplicate = "";

				for (int i = 0; i < patterns.length; i++) {
					while (keywords.matches(patterns[i])) {
						value = keywords.replaceAll(
							patterns[i], replacePatterns[i]);

						values.add(value);

						duplicate = "(?i)\\s*" + field + ":" + value +
							"\\s*";

						keywords = keywords.replaceAll(duplicate, " ");

						keywords = keywords.trim();
					}
				}

				while (!values.isEmpty()) {
					addTerm(field, values.remove(0));
				}
			}

			if (keywords.trim().length() > 0) {
				for (String field : fields) {
					addTerm(field, keywords);
				}
			}
		}
	}

	public void addRequiredTerm(String field, boolean value) {
		LuceneHelperUtil.addRequiredTerm(_booleanQuery, field, value);
	}

	public void addRequiredTerm(String field, Boolean value) {
		LuceneHelperUtil.addRequiredTerm(_booleanQuery, field, value);
	}

	public void addRequiredTerm(String field, double value) {
		LuceneHelperUtil.addRequiredTerm(_booleanQuery, field, value);
	}

	public void addRequiredTerm(String field, Double value) {
		LuceneHelperUtil.addRequiredTerm(_booleanQuery, field, value);
	}

	public void addRequiredTerm(String field, int value) {
		LuceneHelperUtil.addRequiredTerm(_booleanQuery, field, value);
	}

	public void addRequiredTerm(String field, Integer value) {
		LuceneHelperUtil.addRequiredTerm(_booleanQuery, field, value);
	}

	public void addRequiredTerm(String field, long value) {
		LuceneHelperUtil.addRequiredTerm(_booleanQuery, field, value);
	}

	public void addRequiredTerm(String field, Long value) {
		LuceneHelperUtil.addRequiredTerm(_booleanQuery, field, value);
	}

	public void addRequiredTerm(String field, short value) {
		LuceneHelperUtil.addRequiredTerm(_booleanQuery, field, value);
	}

	public void addRequiredTerm(String field, Short value) {
		LuceneHelperUtil.addRequiredTerm(_booleanQuery, field, value);
	}

	public void addRequiredTerm(String field, String value) {
		LuceneHelperUtil.addRequiredTerm(_booleanQuery, field, value);
	}

	public void addRequiredTerm(String field, String value, boolean like) {
		LuceneHelperUtil.addRequiredTerm(_booleanQuery, field, value, like);
	}

	public void addTerm(String field, long value) throws ParseException {
		try {
			LuceneHelperUtil.addTerm(_booleanQuery, field, value);
		}
		catch (org.apache.lucene.queryParser.ParseException pe) {
			throw new ParseException(pe.getMessage());
		}
	}

	public void addTerm(String field, String value) throws ParseException {
		try {
			LuceneHelperUtil.addTerm(_booleanQuery, field, value);
		}
		catch (org.apache.lucene.queryParser.ParseException pe) {
			throw new ParseException(pe.getMessage());
		}
	}

	public void addTerm(String field, String value, boolean like)
		throws ParseException {

		try {
			LuceneHelperUtil.addTerm(_booleanQuery, field, value, like);
		}
		catch (org.apache.lucene.queryParser.ParseException pe) {
			throw new ParseException(pe.getMessage());
		}
	}

	public List<BooleanClause> clauses() {
		List<org.apache.lucene.search.BooleanClause> luceneClauses =
			_booleanQuery.clauses();

		List<BooleanClause> clauses = new ArrayList<BooleanClause>(
			luceneClauses.size());

		for (int i = 0; i < luceneClauses.size(); i++) {
			clauses.add(new BooleanClauseImpl(luceneClauses.get(i)));
		}

		return clauses;
	}

	public org.apache.lucene.search.BooleanQuery getBooleanQuery() {
		return _booleanQuery;
	}

	public String toString() {
		return _booleanQuery.toString();
	}

	private org.apache.lucene.search.BooleanQuery _booleanQuery;

}