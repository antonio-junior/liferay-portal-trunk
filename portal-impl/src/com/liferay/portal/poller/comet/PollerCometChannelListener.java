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

package com.liferay.portal.poller.comet;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.ChannelException;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.ChannelListener;
import com.liferay.portal.kernel.poller.comet.CometRequest;
import com.liferay.portal.kernel.poller.comet.CometSession;

/**
 * @author Edward Han
 */
public class PollerCometChannelListener implements ChannelListener {

	public PollerCometChannelListener(
		CometSession cometSession, JSONObject pollerResponseHeaderJSONObject) {

		_cometSession = cometSession;
		_pollerResponseHeaderJSONObject = pollerResponseHeaderJSONObject;
	}

	public void channelListenerRemoved(long channelId) {
	}

	public void notificationEventsAvailable(long channelId) {
		sendProcessMessage();
	}

	protected void sendProcessMessage() {
		CometRequest cometRequest = _cometSession.getCometRequest();

		try {
			ChannelHubManagerUtil.unregisterChannelListener(
				cometRequest.getCompanyId(), cometRequest.getUserId(), this);
		}
		catch (ChannelException ce) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to unregister channel listener", ce);
			}
		}

		PollerCometDelayedTask pollerCometDelayedTask =
			new PollerCometDelayedTask(
				_cometSession, _pollerResponseHeaderJSONObject);

		PollerCometDelayedJobUtil.addPollerCometDelayedTask(
			pollerCometDelayedTask);
	}

	private static Log _log = LogFactoryUtil.getLog(
		PollerCometChannelListener.class);

	private CometSession _cometSession;
	private JSONObject _pollerResponseHeaderJSONObject;

}