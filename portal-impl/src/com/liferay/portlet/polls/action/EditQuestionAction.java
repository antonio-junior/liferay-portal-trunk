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

package com.liferay.portlet.polls.action;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.polls.DuplicateVoteException;
import com.liferay.portlet.polls.NoSuchChoiceException;
import com.liferay.portlet.polls.NoSuchQuestionException;
import com.liferay.portlet.polls.QuestionChoiceException;
import com.liferay.portlet.polls.QuestionDescriptionException;
import com.liferay.portlet.polls.QuestionExpirationDateException;
import com.liferay.portlet.polls.QuestionExpiredException;
import com.liferay.portlet.polls.QuestionTitleException;
import com.liferay.portlet.polls.model.PollsChoice;
import com.liferay.portlet.polls.service.PollsQuestionServiceUtil;
import com.liferay.portlet.polls.service.persistence.PollsChoiceUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 */
public class EditQuestionAction extends PortletAction {

	public static final String CHOICE_DESCRIPTION_PREFIX = "choiceDescription";

	public static final String CHOICE_NAME_PREFIX = "choiceName";

	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateQuestion(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteQuestion(actionRequest);
			}

			if (Validator.isNotNull(cmd)) {
				sendRedirect(actionRequest, actionResponse);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchQuestionException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				setForward(actionRequest, "portlet.polls.error");
			}
			else if (e instanceof DuplicateVoteException ||
					 e instanceof NoSuchChoiceException ||
					 e instanceof QuestionChoiceException ||
					 e instanceof QuestionDescriptionException ||
					 e instanceof QuestionExpirationDateException ||

					 e instanceof QuestionTitleException) {

				SessionErrors.add(actionRequest, e.getClass().getName());
			}
			else if (e instanceof QuestionExpiredException) {
			}
			else {
				throw e;
			}
		}
	}

	public ActionForward render(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		try {
			ActionUtil.getQuestion(renderRequest);
		}
		catch (Exception e) {
			if (e instanceof NoSuchQuestionException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass().getName());

				return mapping.findForward("portlet.polls.error");
			}
			else {
				throw e;
			}
		}

		return mapping.findForward(
			getForward(renderRequest, "portlet.polls.edit_question"));
	}

	protected void deleteQuestion(ActionRequest actionRequest)
		throws Exception {

		long questionId = ParamUtil.getLong(actionRequest, "questionId");

		PollsQuestionServiceUtil.deleteQuestion(questionId);

	}

	protected void updateQuestion(ActionRequest actionRequest)
		throws Exception {

		long questionId = ParamUtil.getLong(actionRequest, "questionId");

		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "title");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(
				actionRequest, "description");

		int expirationDateMonth = ParamUtil.getInteger(
			actionRequest, "expirationDateMonth");
		int expirationDateDay = ParamUtil.getInteger(
			actionRequest, "expirationDateDay");
		int expirationDateYear = ParamUtil.getInteger(
			actionRequest, "expirationDateYear");
		int expirationDateHour = ParamUtil.getInteger(
			actionRequest, "expirationDateHour");
		int expirationDateMinute = ParamUtil.getInteger(
			actionRequest, "expirationDateMinute");
		int expirationDateAmPm = ParamUtil.getInteger(
			actionRequest, "expirationDateAmPm");
		boolean neverExpire = ParamUtil.getBoolean(
			actionRequest, "neverExpire");

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		List<PollsChoice> choices = new ArrayList<PollsChoice>();

		Set<String> readParameters = new HashSet<String>();

		Enumeration<String> enu = actionRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String param = enu.nextElement();

			if (param.startsWith(CHOICE_DESCRIPTION_PREFIX)) {
				try {
					String id = param.substring(
						CHOICE_DESCRIPTION_PREFIX.length(),
						param.indexOf(CharPool.UNDERLINE));

					if (readParameters.contains(id)) {
						continue;
					}

					String choiceName = ParamUtil.getString(
						actionRequest, CHOICE_NAME_PREFIX + id);

					Map<Locale, String> localeChoiceDescriptionMap =
						LocalizationUtil.getLocalizationMap(
							actionRequest, CHOICE_DESCRIPTION_PREFIX + id);

					PollsChoice choice = PollsChoiceUtil.create(0);

					choice.setName(choiceName);
					choice.setDescriptionMap(localeChoiceDescriptionMap);

					choices.add(choice);

					readParameters.add(id);
				}
				catch (Exception e) {
				}
			}
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			BookmarksEntry.class.getName(), actionRequest);

		if (questionId <= 0) {

			// Add question

			PollsQuestionServiceUtil.addQuestion(
				titleMap, descriptionMap, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, neverExpire, choices, serviceContext);
		}
		else {

			// Update question

			PollsQuestionServiceUtil.updateQuestion(
				questionId, titleMap, descriptionMap, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, neverExpire, choices, serviceContext);
		}
	}

}