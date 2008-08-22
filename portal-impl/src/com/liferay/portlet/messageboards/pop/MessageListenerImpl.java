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

package com.liferay.portlet.messageboards.pop;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.pop.MessageListener;
import com.liferay.portal.kernel.pop.MessageListenerException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionCheckerUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.messageboards.NoSuchMessageException;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;
import com.liferay.portlet.messageboards.util.MBMailMessage;
import com.liferay.portlet.messageboards.util.MBUtil;

import javax.mail.Message;

import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="MessageListenerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 *
 */
public class MessageListenerImpl implements MessageListener {

	public boolean accept(String from, String recipient) {
		try {
			if (!recipient.startsWith(MBUtil.POP_PORTLET_PREFIX)) {
				return false;
			}

			Company company = getCompany(recipient);
			long categoryId = getCategoryId(recipient);

			MBCategory category = MBCategoryLocalServiceUtil.getCategory(
				categoryId);

			if (category.getCompanyId() != company.getCompanyId()) {
				return false;
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Check to see if user " + from + " exists");
			}

			UserLocalServiceUtil.getUserByEmailAddress(
				company.getCompanyId(), from);

			return true;
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Mail rejected", e);
			}

			return false;
		}
	}

	public void deliver(String from, String recipient, Message message)
		throws MessageListenerException {

		try {
			StopWatch stopWatch = null;

			if (_log.isDebugEnabled()) {
				stopWatch = new StopWatch();

				stopWatch.start();

				_log.debug(
					"Deliver message from " + from + " to " + recipient);
			}

			Company company = getCompany(recipient);
			long categoryId = getCategoryId(recipient);

			if (_log.isDebugEnabled()) {
				_log.debug("Category id " + categoryId);
			}

			User user = UserLocalServiceUtil.getUserByEmailAddress(
				company.getCompanyId(), from);

			long parentMessageId = getParentMessageId(recipient, message);

			if (_log.isDebugEnabled()) {
				_log.debug("Parent message id " + parentMessageId);
			}

			MBMessage parentMessage = null;

			try {
				if (parentMessageId > 0) {
					parentMessage = MBMessageLocalServiceUtil.getMessage(
						parentMessageId);
				}
			}
			catch (NoSuchMessageException nsme) {

				// If the parent message does not exist we ignore it and post
				// the message as a new thread.

			}

			if (_log.isDebugEnabled()) {
				_log.debug("Parent message " + parentMessage);
			}

			MBMailMessage collector = new MBMailMessage();

			MBUtil.collectPartContent(message, collector);

			PermissionCheckerUtil.setThreadValues(user);

			if (parentMessage == null) {
				MBMessageServiceUtil.addMessage(
					categoryId, message.getSubject(), collector.getBody(),
					collector.getFiles(), false, 0.0, null, true, true);
			}
			else {
				MBMessageServiceUtil.addMessage(
					categoryId, parentMessage.getThreadId(),
					parentMessage.getMessageId(), message.getSubject(),
					collector.getBody(), collector.getFiles(), false, 0.0, null,
					true, true);
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Delivering message takes " + stopWatch.getTime() + " ms");
			}
		}
		catch (PrincipalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug("Prevented unauthorized post from " + from);
			}

			throw new MessageListenerException(pe);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new MessageListenerException(e);
		}
	}

	public String getId() {
		return MessageListenerImpl.class.getName();
	}

	protected long getCategoryId(String recipient) {
		int pos = recipient.indexOf(StringPool.AT);

		String target = recipient.substring(
			MBUtil.POP_PORTLET_PREFIX.length(), pos);

		String[] parts = StringUtil.split(target, ".");

		long categoryId = GetterUtil.getLong(parts[0]);

		return categoryId;
	}

	protected Company getCompany(String recipient)
		throws PortalException, SystemException {

		int pos = recipient.indexOf(StringPool.AT);

		String mx = recipient.substring(
			pos + PropsValues.POP_SERVER_SUBDOMAIN.length() + 2);

		return CompanyLocalServiceUtil.getCompanyByMx(mx);
	}

	protected long getParentMessageId(String recipient, Message message)
		throws Exception {

		// Get the parent message ID from the recipient address

		int pos = recipient.indexOf(StringPool.AT);

		String target = recipient.substring(
			MBUtil.POP_PORTLET_PREFIX.length(), pos);

		String[] parts = StringUtil.split(target, StringPool.PERIOD);

		long parentMessageId = 0;

		if (parts.length == 2) {
			parentMessageId = GetterUtil.getLong(parts[1]);
		}

		if (parentMessageId > 0) {
			return parentMessageId;
		}
		else {
			return MBUtil.getParentMessageId(message);
		}
	}

	private static Log _log = LogFactory.getLog(MessageListenerImpl.class);

}