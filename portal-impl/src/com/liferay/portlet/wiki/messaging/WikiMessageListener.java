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

package com.liferay.portlet.wiki.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;

/**
 * @author Brian Wing Shun Chan
 */
public class WikiMessageListener extends BaseMessageListener {

	protected void doReceive(Message message) throws Exception {
		long companyId = message.getLong("companyId");
		long userId = message.getLong("userId");
		long groupId = message.getLong("groupId");
		long nodeId = message.getLong("nodeId");
		long pageResourcePrimKey = message.getLong("pageResourcePrimKey");
		String fromName = message.getString("fromName");
		String fromAddress = message.getString("fromAddress");
		String subject = message.getString("subject");
		String body = message.getString("body");
		String replyToAddress = message.getString("replyToAddress");
		String mailId = message.getString("mailId");
		boolean htmlFormat = message.getBoolean("htmlFormat");

		if (_log.isInfoEnabled()) {
			_log.info(
				"Sending notifications for {mailId=" + mailId +
					", pageResourcePrimKey=" + pageResourcePrimKey +
						", nodeId=" + nodeId + "}");
		}

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(companyId);
		subscriptionSender.setUserId(userId);
		subscriptionSender.setGroupId(groupId);
		subscriptionSender.setFrom(fromName, fromAddress);
		subscriptionSender.setSubject(subject);
		subscriptionSender.setBody(body);
		subscriptionSender.setReplyToAddress(replyToAddress);
		subscriptionSender.setMailId(mailId);
		subscriptionSender.setHtmlFormat(htmlFormat);

		subscriptionSender.notifyPersistedSubscribers(
			WikiNode.class.getName(), nodeId);
		subscriptionSender.notifyPersistedSubscribers(
			WikiPage.class.getName(), pageResourcePrimKey);

		if (_log.isInfoEnabled()) {
			_log.info("Finished sending notifications");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(WikiMessageListener.class);

}