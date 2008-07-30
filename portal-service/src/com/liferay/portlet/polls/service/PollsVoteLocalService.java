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

package com.liferay.portlet.polls.service;


/**
 * <a href="PollsVoteLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.liferay.portlet.polls.service.impl.PollsVoteLocalServiceImpl</code>.
 * Modify methods in that class and rerun ServiceBuilder to populate this class
 * and all other generated classes.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portlet.polls.service.PollsVoteLocalServiceUtil
 *
 */
public interface PollsVoteLocalService {
	public com.liferay.portlet.polls.model.PollsVote addPollsVote(
		com.liferay.portlet.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.SystemException;

	public void deletePollsVote(long voteId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deletePollsVote(
		com.liferay.portlet.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public com.liferay.portlet.polls.model.PollsVote getPollsVote(long voteId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public java.util.List<com.liferay.portlet.polls.model.PollsVote> getPollsVotes(
		int start, int end) throws com.liferay.portal.SystemException;

	public int getPollsVotesCount() throws com.liferay.portal.SystemException;

	public com.liferay.portlet.polls.model.PollsVote updatePollsVote(
		com.liferay.portlet.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.polls.model.PollsVote addVote(long userId,
		long questionId, long choiceId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portlet.polls.model.PollsVote> getChoiceVotes(
		long choiceId, int start, int end)
		throws com.liferay.portal.SystemException;

	public int getChoiceVotesCount(long choiceId)
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.polls.model.PollsVote getVote(long questionId,
		long userId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portlet.polls.model.PollsVote> getQuestionVotes(
		long questionId, int start, int end)
		throws com.liferay.portal.SystemException;

	public int getQuestionVotesCount(long questionId)
		throws com.liferay.portal.SystemException;
}