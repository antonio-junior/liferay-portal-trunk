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

package com.liferay.portal.search.lucene;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.HitsImpl;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopFieldDocs;

/**
 * @author Bruno Farache
 */
public class LuceneIndexSearcherImpl implements IndexSearcher {

	public Hits search(
			long companyId, Query query, Sort[] sorts, int start, int end)
		throws SearchException {

		if (_log.isDebugEnabled()) {
			_log.debug("Query " + query);
		}

		Hits hits = null;

		org.apache.lucene.search.IndexSearcher indexSearcher = null;
		org.apache.lucene.search.Sort luceneSort = null;

		try {
			indexSearcher = LuceneHelperUtil.getSearcher(companyId, true);

			indexSearcher.setDefaultFieldSortScoring(true, true);
			indexSearcher.setSimilarity(new FieldWeightSimilarity());

			if (sorts != null) {
				SortField[] sortFields = new SortField[sorts.length];

				for (int i = 0; i < sorts.length; i++) {
					Sort sort = sorts[i];

					sortFields[i] = new SortField(
						sort.getFieldName(), sort.getType(), sort.isReverse());
				}

				luceneSort = new org.apache.lucene.search.Sort(sortFields);
			}
			else {
				luceneSort = new org.apache.lucene.search.Sort();
			}

			long startTime = System.currentTimeMillis();

			TopFieldDocs topFieldDocs = indexSearcher.search(
				QueryTranslator.translate(query), null,
				PropsValues.INDEX_SEARCH_LIMIT, luceneSort);

			long endTime = System.currentTimeMillis();

			float searchTime = (float)(endTime - startTime) / Time.SECOND;

			hits = toHits(
				indexSearcher, topFieldDocs, query, startTime, searchTime,
				start, end);
		}
		catch (BooleanQuery.TooManyClauses tmc) {
			int maxClauseCount = BooleanQuery.getMaxClauseCount();

			BooleanQuery.setMaxClauseCount(Integer.MAX_VALUE);

			try {
				long startTime = System.currentTimeMillis();

				TopFieldDocs topFieldDocs = indexSearcher.search(
					QueryTranslator.translate(query), null,
					PropsValues.INDEX_SEARCH_LIMIT, luceneSort);

				long endTime = System.currentTimeMillis();

				float searchTime = (float)(endTime - startTime) / Time.SECOND;

				hits = toHits(
					indexSearcher, topFieldDocs, query, startTime, searchTime,
					start, end);
			}
			catch (Exception e) {
				throw new SearchException(e);
			}
			finally {
				BooleanQuery.setMaxClauseCount(maxClauseCount);
			}
		}
		catch (ParseException pe) {
			_log.error("Query: " + query, pe);

			return new HitsImpl();
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
		finally {
			try {
				if (indexSearcher != null) {
					indexSearcher.close();
				}
			}
			catch (IOException ioe) {
				throw new SearchException(ioe);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Search found " + hits.getLength() + " results in " +
					hits.getSearchTime() + "ms");
		}

		return hits;
	}

	protected DocumentImpl getDocument(
		org.apache.lucene.document.Document oldDocument) {

		DocumentImpl newDocument = new DocumentImpl();

		List<org.apache.lucene.document.Fieldable> oldFieldables =
			oldDocument.getFields();

		for (org.apache.lucene.document.Fieldable oldFieldable :
				oldFieldables) {

			Field newField = null;

			String[] values = oldDocument.getValues(oldFieldable.name());

			if ((values != null) && (values.length > 1)) {
				newField = new Field(oldFieldable.name(), values);
			}
			else {
				newField = new Field(
					oldFieldable.name(), oldFieldable.stringValue());
			}

			newField.setNumeric(oldFieldable instanceof NumericField);
			newField.setTokenized(oldFieldable.isTokenized());

			newDocument.add(newField);
		}

		return newDocument;
	}

	protected String[] getQueryTerms(Query query) {
		String[] queryTerms = new String[0];

		try {
			queryTerms = LuceneHelperUtil.getQueryTerms(
				QueryTranslator.translate(query));
		}
		catch (ParseException pe) {
			_log.error("Query: " + query, pe);
		}

		return queryTerms;
	}

	protected String getSnippet(
			org.apache.lucene.document.Document doc, Query query, String field)
		throws IOException {

		String[] values = doc.getValues(field);

		String snippet = null;

		if (Validator.isNull(values)) {
			return snippet;
		}

		String s = StringUtil.merge(values);

		try {
			snippet = LuceneHelperUtil.getSnippet(
				QueryTranslator.translate(query), field, s);
		}
		catch (ParseException pe) {
			_log.error("Query: " + query, pe);
		}

		return snippet;
	}

	protected Hits toHits(
			org.apache.lucene.search.IndexSearcher indexSearcher,
			TopFieldDocs topFieldDocs, Query query, long startTime,
			float searchTime, int start, int end)
		throws IOException, ParseException {

		int length = topFieldDocs.totalHits;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
			start = 0;
			end = length;
		}

		String[] queryTerms = getQueryTerms(query);

		IndexReader indexReader = indexSearcher.getIndexReader();

		List<String> indexedFieldNames = new ArrayList<String> (
			indexReader.getFieldNames(IndexReader.FieldOption.INDEXED));

		org.apache.lucene.search.Query luceneQuery = QueryTranslator.translate(
			query);

		int scoredFieldNamesCount = LuceneHelperUtil.countScoredFieldNames(
			luceneQuery, ArrayUtil.toStringArray(indexedFieldNames.toArray()));

		Hits hits = new HitsImpl();

		if ((start > -1) && (start <= end)) {
			if (end > length) {
				end = length;
			}

			if (start > end) {
				start = end;
			}

			int subsetTotal = end - start;

			if (subsetTotal > PropsValues.INDEX_SEARCH_LIMIT) {
				subsetTotal = PropsValues.INDEX_SEARCH_LIMIT;
			}

			List<Document> subsetDocs = new ArrayList<Document>(subsetTotal);
			List<String> subsetSnippets = new ArrayList<String>(subsetTotal);
			List<Float> subsetScores = new ArrayList<Float>(subsetTotal);

			QueryConfig queryConfig = query.getQueryConfig();

			boolean highlightEnabled = queryConfig.isHighlightEnabled();

			for (int i = start; i < end; i++) {
				if (i >= PropsValues.INDEX_SEARCH_LIMIT) {
					break;
				}

				org.apache.lucene.document.Document document =
					indexSearcher.doc(topFieldDocs.scoreDocs[i].doc);

				Document subsetDocument = getDocument(document);

				subsetDocs.add(subsetDocument);

				if (highlightEnabled) {
					String subsetSnippet = getSnippet(
						document, query, Field.CONTENT);

					subsetSnippets.add(subsetSnippet);
				}
				else {
					subsetSnippets.add(StringPool.BLANK);
				}

				Float subsetScore =
					topFieldDocs.scoreDocs[i].score / scoredFieldNamesCount;

				subsetScores.add(subsetScore);

				if (_log.isDebugEnabled()) {
					try {
						Explanation explanation = indexSearcher.explain(
							luceneQuery, topFieldDocs.scoreDocs[i].doc);

						_log.debug(explanation.toString());
					}
					catch (Exception e) {
					}
				}
			}

			hits.setStart(startTime);
			hits.setSearchTime(searchTime);
			hits.setQueryTerms(queryTerms);
			hits.setDocs(subsetDocs.toArray(new Document[subsetDocs.size()]));
			hits.setLength(length);
			hits.setSnippets(
				subsetSnippets.toArray(new String[subsetSnippets.size()]));
			hits.setScores(
				subsetScores.toArray(new Float[subsetScores.size()]));
		}

		return hits;
	}

	private static Log _log = LogFactoryUtil.getLog(
		LuceneIndexSearcherImpl.class);

}