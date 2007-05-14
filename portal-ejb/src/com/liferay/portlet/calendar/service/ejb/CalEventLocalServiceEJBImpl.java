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

package com.liferay.portlet.calendar.service.ejb;

import com.liferay.portlet.calendar.service.CalEventLocalService;
import com.liferay.portlet.calendar.service.CalEventLocalServiceFactory;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * <a href="CalEventLocalServiceEJBImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be overwritten
 * the next time is generated.
 * </p>
 *
 * <p>
 * This class is the EJB implementation of the service that is used when Liferay
 * is run inside a full J2EE container.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portlet.calendar.service.CalEventLocalService
 * @see com.liferay.portlet.calendar.service.CalEventLocalServiceUtil
 * @see com.liferay.portlet.calendar.service.ejb.CalEventLocalServiceEJB
 * @see com.liferay.portlet.calendar.service.ejb.CalEventLocalServiceHome
 * @see com.liferay.portlet.calendar.service.impl.CalEventLocalServiceImpl
 *
 */
public class CalEventLocalServiceEJBImpl implements CalEventLocalService,
	SessionBean {
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().dynamicQuery(queryInitializer);
	}

	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().dynamicQuery(queryInitializer,
			begin, end);
	}

	public com.liferay.portlet.calendar.model.CalEvent addEvent(long userId,
		long plid, java.lang.String title, java.lang.String description,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, int endDateMonth,
		int endDateDay, int endDateYear, int durationHour, int durationMinute,
		boolean allDay, boolean timeZoneSensitive, java.lang.String type,
		boolean repeating, com.liferay.portal.kernel.cal.Recurrence recurrence,
		java.lang.String remindBy, int firstReminder, int secondReminder,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().addEvent(userId, plid,
			title, description, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, durationHour, durationMinute, allDay,
			timeZoneSensitive, type, repeating, recurrence, remindBy,
			firstReminder, secondReminder, addCommunityPermissions,
			addGuestPermissions);
	}

	public com.liferay.portlet.calendar.model.CalEvent addEvent(long userId,
		long plid, java.lang.String title, java.lang.String description,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, int endDateMonth,
		int endDateDay, int endDateYear, int durationHour, int durationMinute,
		boolean allDay, boolean timeZoneSensitive, java.lang.String type,
		boolean repeating, com.liferay.portal.kernel.cal.Recurrence recurrence,
		java.lang.String remindBy, int firstReminder, int secondReminder,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().addEvent(userId, plid,
			title, description, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, durationHour, durationMinute, allDay,
			timeZoneSensitive, type, repeating, recurrence, remindBy,
			firstReminder, secondReminder, communityPermissions,
			guestPermissions);
	}

	public com.liferay.portlet.calendar.model.CalEvent addEvent(long userId,
		long plid, java.lang.String title, java.lang.String description,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, int endDateMonth,
		int endDateDay, int endDateYear, int durationHour, int durationMinute,
		boolean allDay, boolean timeZoneSensitive, java.lang.String type,
		boolean repeating, com.liferay.portal.kernel.cal.Recurrence recurrence,
		java.lang.String remindBy, int firstReminder, int secondReminder,
		java.lang.Boolean addCommunityPermissions,
		java.lang.Boolean addGuestPermissions,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().addEvent(userId, plid,
			title, description, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, durationHour, durationMinute, allDay,
			timeZoneSensitive, type, repeating, recurrence, remindBy,
			firstReminder, secondReminder, addCommunityPermissions,
			addGuestPermissions, communityPermissions, guestPermissions);
	}

	public void addEventResources(long eventId,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		CalEventLocalServiceFactory.getTxImpl().addEventResources(eventId,
			addCommunityPermissions, addGuestPermissions);
	}

	public void addEventResources(
		com.liferay.portlet.calendar.model.CalEvent event,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		CalEventLocalServiceFactory.getTxImpl().addEventResources(event,
			addCommunityPermissions, addGuestPermissions);
	}

	public void addEventResources(long eventId,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		CalEventLocalServiceFactory.getTxImpl().addEventResources(eventId,
			communityPermissions, guestPermissions);
	}

	public void addEventResources(
		com.liferay.portlet.calendar.model.CalEvent event,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		CalEventLocalServiceFactory.getTxImpl().addEventResources(event,
			communityPermissions, guestPermissions);
	}

	public void checkEvents()
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		CalEventLocalServiceFactory.getTxImpl().checkEvents();
	}

	public void deleteEvent(long eventId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		CalEventLocalServiceFactory.getTxImpl().deleteEvent(eventId);
	}

	public void deleteEvent(com.liferay.portlet.calendar.model.CalEvent event)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		CalEventLocalServiceFactory.getTxImpl().deleteEvent(event);
	}

	public void deleteEvents(long groupId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		CalEventLocalServiceFactory.getTxImpl().deleteEvents(groupId);
	}

	public java.io.File export(long eventId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().export(eventId);
	}

	public com.liferay.portlet.calendar.model.CalEvent getEvent(long eventId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().getEvent(eventId);
	}

	public java.util.List getEvents(long groupId, java.lang.String type,
		int begin, int end) throws com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().getEvents(groupId, type,
			begin, end);
	}

	public java.util.List getEvents(long groupId, java.util.Calendar cal)
		throws com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().getEvents(groupId, cal);
	}

	public java.util.List getEvents(long groupId, java.util.Calendar cal,
		java.lang.String type) throws com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().getEvents(groupId, cal,
			type);
	}

	public int getEventsCount(long groupId, java.lang.String type)
		throws com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().getEventsCount(groupId,
			type);
	}

	public java.util.List getRepeatingEvents(long groupId)
		throws com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().getRepeatingEvents(groupId);
	}

	public boolean hasEvents(long groupId, java.util.Calendar cal)
		throws com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().hasEvents(groupId, cal);
	}

	public boolean hasEvents(long groupId, java.util.Calendar cal,
		java.lang.String type) throws com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().hasEvents(groupId, cal,
			type);
	}

	public com.liferay.portlet.calendar.model.CalEvent updateEvent(
		long userId, long eventId, java.lang.String title,
		java.lang.String description, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int durationHour,
		int durationMinute, boolean allDay, boolean timeZoneSensitive,
		java.lang.String type, boolean repeating,
		com.liferay.portal.kernel.cal.Recurrence recurrence,
		java.lang.String remindBy, int firstReminder, int secondReminder)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		return CalEventLocalServiceFactory.getTxImpl().updateEvent(userId,
			eventId, title, description, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, durationHour, durationMinute, allDay,
			timeZoneSensitive, type, repeating, recurrence, remindBy,
			firstReminder, secondReminder);
	}

	public void ejbCreate() throws CreateException {
	}

	public void ejbRemove() {
	}

	public void ejbActivate() {
	}

	public void ejbPassivate() {
	}

	public SessionContext getSessionContext() {
		return _sc;
	}

	public void setSessionContext(SessionContext sc) {
		_sc = sc;
	}

	private SessionContext _sc;
}