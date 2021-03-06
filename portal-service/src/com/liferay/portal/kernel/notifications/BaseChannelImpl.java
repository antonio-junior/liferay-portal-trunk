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

package com.liferay.portal.kernel.notifications;

import com.liferay.portal.kernel.concurrent.ConcurrentHashSet;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Edward Han
 */
public abstract class BaseChannelImpl implements Channel {

	public void cleanUp() throws ChannelException {
		long currentTime = System.currentTimeMillis();

		long nextCleanUpTime = _nextCleanUpTime.get();

		if ((currentTime > nextCleanUpTime) &&
			_nextCleanUpTime.compareAndSet(
				nextCleanUpTime, currentTime + _cleanUpInterval)) {

			try {
				doCleanUp();
			}
			catch (ChannelException ce) {
				throw ce;
			}
			catch (Exception e) {
				throw new ChannelException(e);
			}
		}
	}

	public void close() throws ChannelException {
		flush();
	}

	public long getCompanyId() {
		return _companyId;
	}

	public List<NotificationEvent> getNotificationEvents()
		throws ChannelException {

		return getNotificationEvents(true);
	}

	public long getUserId() {
		return _userId;
	}

	public boolean hasNotificationEvents() {
		try {
			List<NotificationEvent> notificationEvents = getNotificationEvents(
				false);

			if (!notificationEvents.isEmpty()) {
				return true;
			}
		}
		catch (ChannelException ce) {
			if (_log.isErrorEnabled()) {
				_log.error("Unable to fetch notifications", ce);
			}
		}

		return false;
	}

	public void registerChannelListener(ChannelListener channelListener) {
		_channelListeners.add(channelListener);

		if (hasNotificationEvents()) {
			notifyChannelListeners();
		}
	}

	public void setCleanUpInterval(long cleanUpInterval) {
		_cleanUpInterval = cleanUpInterval;
	}

	public void unregisterChannelListener(ChannelListener channelListener) {
		_channelListeners.remove(channelListener);

		channelListener.channelListenerRemoved(_userId);
	}

	protected BaseChannelImpl(long companyId, long usedId) {
		_companyId = companyId;
		_userId = usedId;
	}

	protected abstract void doCleanUp() throws Exception;

	protected void notifyChannelListeners() {
		for (ChannelListener channelListener : _channelListeners) {
			channelListener.notificationEventsAvailable(_userId);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(BaseChannelImpl.class);

	private Set<ChannelListener> _channelListeners =
		new ConcurrentHashSet<ChannelListener>();
	private long _cleanUpInterval;
	private long _companyId;
	private AtomicLong _nextCleanUpTime = new AtomicLong();
	private long _userId;

}