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

package com.liferay.portlet.polls.model;

import com.liferay.portlet.polls.service.persistence.PollsChoicePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="PollsChoiceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be overwritten
 * the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by <code>com.liferay.portlet.polls.service.http.PollsChoiceServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portlet.polls.service.http.PollsChoiceServiceSoap
 *
 */
public class PollsChoiceSoap implements Serializable {
	public static PollsChoiceSoap toSoapModel(PollsChoice model) {
		PollsChoiceSoap soapModel = new PollsChoiceSoap();
		soapModel.setQuestionId(model.getQuestionId());
		soapModel.setChoiceId(model.getChoiceId());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static PollsChoiceSoap[] toSoapModels(List models) {
		List soapModels = new ArrayList(models.size());

		for (int i = 0; i < models.size(); i++) {
			PollsChoice model = (PollsChoice)models.get(i);
			soapModels.add(toSoapModel(model));
		}

		return (PollsChoiceSoap[])soapModels.toArray(new PollsChoiceSoap[0]);
	}

	public PollsChoiceSoap() {
	}

	public PollsChoicePK getPrimaryKey() {
		return new PollsChoicePK(_questionId, _choiceId);
	}

	public void setPrimaryKey(PollsChoicePK pk) {
		setQuestionId(pk.questionId);
		setChoiceId(pk.choiceId);
	}

	public long getQuestionId() {
		return _questionId;
	}

	public void setQuestionId(long questionId) {
		_questionId = questionId;
	}

	public String getChoiceId() {
		return _choiceId;
	}

	public void setChoiceId(String choiceId) {
		_choiceId = choiceId;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private long _questionId;
	private String _choiceId;
	private String _description;
}