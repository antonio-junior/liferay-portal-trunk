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

package com.liferay.portlet.tasks.service;


/**
 * <a href="TasksProposalLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.liferay.portlet.tasks.service.impl.TasksProposalLocalServiceImpl</code>.
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
 * @see com.liferay.portlet.tasks.service.TasksProposalLocalServiceFactory
 * @see com.liferay.portlet.tasks.service.TasksProposalLocalServiceUtil
 *
 */
public interface TasksProposalLocalService {
	public com.liferay.portlet.tasks.model.TasksProposal addTasksProposal(
		com.liferay.portlet.tasks.model.TasksProposal tasksProposal)
		throws com.liferay.portal.SystemException;

	public void deleteTasksProposal(long proposalId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteTasksProposal(
		com.liferay.portlet.tasks.model.TasksProposal tasksProposal)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public java.util.List<com.liferay.portlet.tasks.model.TasksProposal> dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portlet.tasks.model.TasksProposal> dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException;

	public com.liferay.portlet.tasks.model.TasksProposal updateTasksProposal(
		com.liferay.portlet.tasks.model.TasksProposal tasksProposal)
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.tasks.service.persistence.TasksReviewPersistence getTasksReviewPersistence();

	public void setTasksReviewPersistence(
		com.liferay.portlet.tasks.service.persistence.TasksReviewPersistence tasksReviewPersistence);

	public com.liferay.portlet.tasks.service.persistence.TasksProposalPersistence getTasksProposalPersistence();

	public void setTasksProposalPersistence(
		com.liferay.portlet.tasks.service.persistence.TasksProposalPersistence tasksProposalPersistence);

	public com.liferay.portlet.tasks.service.persistence.TasksProposalFinder getTasksProposalFinder();

	public void setTasksProposalFinder(
		com.liferay.portlet.tasks.service.persistence.TasksProposalFinder tasksProposalFinder);

	public com.liferay.portal.service.persistence.ActivityTrackerPersistence getActivityTrackerPersistence();

	public void setActivityTrackerPersistence(
		com.liferay.portal.service.persistence.ActivityTrackerPersistence activityTrackerPersistence);

	public com.liferay.portal.service.persistence.ActivityTrackerFinder getActivityTrackerFinder();

	public void setActivityTrackerFinder(
		com.liferay.portal.service.persistence.ActivityTrackerFinder activityTrackerFinder);

	public com.liferay.portal.service.persistence.ResourcePersistence getResourcePersistence();

	public void setResourcePersistence(
		com.liferay.portal.service.persistence.ResourcePersistence resourcePersistence);

	public com.liferay.portal.service.persistence.ResourceFinder getResourceFinder();

	public void setResourceFinder(
		com.liferay.portal.service.persistence.ResourceFinder resourceFinder);

	public com.liferay.portal.service.persistence.UserPersistence getUserPersistence();

	public void setUserPersistence(
		com.liferay.portal.service.persistence.UserPersistence userPersistence);

	public com.liferay.portal.service.persistence.UserFinder getUserFinder();

	public void setUserFinder(
		com.liferay.portal.service.persistence.UserFinder userFinder);

	public com.liferay.portlet.messageboards.service.persistence.MBMessagePersistence getMBMessagePersistence();

	public void setMBMessagePersistence(
		com.liferay.portlet.messageboards.service.persistence.MBMessagePersistence mbMessagePersistence);

	public com.liferay.portlet.messageboards.service.persistence.MBMessageFinder getMBMessageFinder();

	public void setMBMessageFinder(
		com.liferay.portlet.messageboards.service.persistence.MBMessageFinder mbMessageFinder);

	public void afterPropertiesSet();

	public com.liferay.portlet.tasks.model.TasksProposal addProposal(
		long userId, long groupId, java.lang.String className,
		java.lang.String classPK, java.lang.String name,
		java.lang.String description, long reviewUserId,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public com.liferay.portlet.tasks.model.TasksProposal addProposal(
		long userId, long groupId, java.lang.String className,
		java.lang.String classPK, java.lang.String name,
		java.lang.String description, long reviewUserId,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public com.liferay.portlet.tasks.model.TasksProposal addProposal(
		long userId, long groupId, java.lang.String className,
		java.lang.String classPK, java.lang.String name,
		java.lang.String description, long reviewUserId,
		java.lang.Boolean addCommunityPermissions,
		java.lang.Boolean addGuestPermissions,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void addProposalResources(long proposalId,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void addProposalResources(
		com.liferay.portlet.tasks.model.TasksProposal proposal,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void addProposalResources(long proposalId,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void addProposalResources(
		com.liferay.portlet.tasks.model.TasksProposal proposal,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteProposal(java.lang.String className,
		java.lang.String classPK)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteProposal(long classNameId, java.lang.String classPK)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteProposal(long proposalId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteProposal(
		com.liferay.portlet.tasks.model.TasksProposal proposal)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteProposals(long groupId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public com.liferay.portlet.tasks.model.TasksProposal getProposal(
		long proposalId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public com.liferay.portlet.tasks.model.TasksProposal getProposal(
		java.lang.String className, java.lang.String classPK)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public com.liferay.portlet.tasks.model.TasksProposal getProposal(
		long classNameId, java.lang.String classPK)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public java.util.List<com.liferay.portlet.tasks.model.TasksProposal> getProposals(
		long groupId, int begin, int end)
		throws com.liferay.portal.SystemException;

	public int getProposalsCount(long groupId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portlet.tasks.model.TasksProposal> getReviewProposals(
		long groupId, long userId, int begin, int end)
		throws com.liferay.portal.SystemException;

	public int getReviewProposalsCount(long groupId, long userId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portlet.tasks.model.TasksProposal> getUserProposals(
		long groupId, long userId, int begin, int end)
		throws com.liferay.portal.SystemException;

	public int getUserProposalsCount(long groupId, long userId)
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.tasks.model.TasksProposal updateProposal(
		long userId, long proposalId, java.lang.String description,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;
}