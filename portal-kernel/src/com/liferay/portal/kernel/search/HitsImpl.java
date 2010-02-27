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

package com.liferay.portal.kernel.search;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="HitsImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 */
public class HitsImpl implements Hits {

	public HitsImpl() {
	}

	public Document doc(int n) {
		return _docs[n];
	}

	public Document[] getDocs() {
		return _docs;
	}

	public int getLength() {
		return _length;
	}

	public String[] getQueryTerms() {
		return _queryTerms;
	}

	public float[] getScores() {
		return _scores;
	}

	public float getSearchTime() {
		return _searchTime;
	}

	public String[] getSnippets() {
		return _snippets;
	}

	public long getStart() {
		return _start;
	}

	public float score(int n) {
		return _scores[n];
	}

	public void setDocs(Document[] docs) {
		_docs = docs;
	}

	public void setLength(int length) {
		_length = length;
	}

	public void setQueryTerms(String[] queryTerms) {
		_queryTerms = queryTerms;
	}

	public void setScores(float[] scores) {
		_scores = scores;
	}

	public void setScores(Float[] scores) {
		float[] primScores = new float[scores.length];

		for (int i = 0; i < scores.length; i++) {
			primScores[i] = scores[i].floatValue();
		}

		setScores(primScores);
	}

	public void setSearchTime(float time) {
		_searchTime = time;
	}

	public void setSnippets(String[] snippets) {
		_snippets = snippets;
	}

	public void setStart(long start) {
		_start = start;
	}

	public String snippet(int n) {
		return _snippets[n];
	}

	public List<Document> toList() {
		List<Document> subset = new ArrayList<Document>(_docs.length);

		for (int i = 0; i < _docs.length; i++) {
			subset.add(_docs[i]);
		}

		return subset;
	}

	private Document[] _docs;
	private int _length;
	private String[] _queryTerms;
	private float[] _scores = new float[0];
	private float _searchTime;
	private String[] _snippets = {};
	private long _start;

}