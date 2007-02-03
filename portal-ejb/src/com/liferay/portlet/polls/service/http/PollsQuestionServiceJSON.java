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

package com.liferay.portlet.polls.service.http;

import com.liferay.portlet.polls.service.PollsQuestionServiceUtil;

import org.json.JSONObject;

/**
 * <a href="PollsQuestionServiceJSON.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PollsQuestionServiceJSON {
	public static JSONObject addQuestion(java.lang.String plid,
		java.lang.String title, java.lang.String description,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		java.util.List choices, boolean addCommunityPermissions,
		boolean addGuestPermissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException, java.rmi.RemoteException {
		com.liferay.portlet.polls.model.PollsQuestion returnValue = PollsQuestionServiceUtil.addQuestion(plid,
				title, description, expirationDateMonth, expirationDateDay,
				expirationDateYear, expirationDateHour, expirationDateMinute,
				neverExpire, choices, addCommunityPermissions,
				addGuestPermissions);

		return PollsQuestionJSONSerializer.toJSONObject(returnValue);
	}

	public static JSONObject addQuestion(java.lang.String plid,
		java.lang.String title, java.lang.String description,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		java.util.List choices, java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException, java.rmi.RemoteException {
		com.liferay.portlet.polls.model.PollsQuestion returnValue = PollsQuestionServiceUtil.addQuestion(plid,
				title, description, expirationDateMonth, expirationDateDay,
				expirationDateYear, expirationDateHour, expirationDateMinute,
				neverExpire, choices, communityPermissions, guestPermissions);

		return PollsQuestionJSONSerializer.toJSONObject(returnValue);
	}

	public static void deleteQuestion(java.lang.String questionId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException, java.rmi.RemoteException {
		PollsQuestionServiceUtil.deleteQuestion(questionId);
	}

	public static JSONObject getQuestion(java.lang.String questionId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException, java.rmi.RemoteException {
		com.liferay.portlet.polls.model.PollsQuestion returnValue = PollsQuestionServiceUtil.getQuestion(questionId);

		return PollsQuestionJSONSerializer.toJSONObject(returnValue);
	}

	public static JSONObject updateQuestion(java.lang.String questionId,
		java.lang.String title, java.lang.String description,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		java.util.List choices)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException, java.rmi.RemoteException {
		com.liferay.portlet.polls.model.PollsQuestion returnValue = PollsQuestionServiceUtil.updateQuestion(questionId,
				title, description, expirationDateMonth, expirationDateDay,
				expirationDateYear, expirationDateHour, expirationDateMinute,
				neverExpire, choices);

		return PollsQuestionJSONSerializer.toJSONObject(returnValue);
	}
}