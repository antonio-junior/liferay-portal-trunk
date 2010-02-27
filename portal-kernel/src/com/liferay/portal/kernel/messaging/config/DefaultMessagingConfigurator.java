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

package com.liferay.portal.kernel.messaging.config;

import com.liferay.portal.kernel.messaging.MessageBus;

/**
 * <a href="DefaultMessagingConfigurator.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Michael C. Han
 */
public class DefaultMessagingConfigurator
	extends AbstractMessagingConfigurator {

	public void setMessageBus(MessageBus messageBus) {
		_messageBus = messageBus;
	}

	protected MessageBus getMessageBus() {
		return _messageBus;
	}

	protected ClassLoader getOperatingClassloader() {
		return Thread.currentThread().getContextClassLoader();
	}

	private MessageBus _messageBus;

}