/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.InstancePool;
import com.liferay.portal.kernel.util.MethodInvoker;
import com.liferay.portal.kernel.util.MethodWrapper;
import com.liferay.portal.security.auth.EmailAddressGenerator;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.mail.MailEngine;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * <a href="MailMessageListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 */
public class MailMessageListener implements MessageListener {

	public void receive(Message message) {
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	public void doMailMessage(MailMessage mailMessage) throws Exception {
		InternetAddress[] auditTrail = InternetAddress.parse(
			PropsValues.MAIL_AUDIT_TRAIL);

		if (auditTrail.length > 0) {
			InternetAddress[] bcc = mailMessage.getBCC();

			if (bcc != null) {
				InternetAddress[] allBCC = new InternetAddress[
					bcc.length + auditTrail.length];

				ArrayUtil.combine(bcc, auditTrail, allBCC);

				mailMessage.setBCC(allBCC);
			}
			else {
				mailMessage.setBCC(auditTrail);
			}
		}

		 if (getInternetAddress(mailMessage.getFrom()) == null) {
			 return;
		 }

		 boolean sendMail = false;

		 InternetAddress[] to = getInternetAddresses(mailMessage.getTo());

		 if (to != null) {
			 if (mailMessage.getTo().length > to.length) {
				 mailMessage.setTo(to);
			 }

			 sendMail = true;
		 }
		 else {
			 mailMessage.setTo(to);
		 }

		 InternetAddress[] cc = getInternetAddresses(mailMessage.getCC());

		 if (cc != null) {
			 if (mailMessage.getCC().length > cc.length) {
				 mailMessage.setCC(cc);
			 }

			 sendMail = true;
		 }
		 else {
			 mailMessage.setCC(cc);
		 }

		 InternetAddress[] bcc = getInternetAddresses(mailMessage.getBCC());

		 if (bcc != null) {
			 if (mailMessage.getBCC().length > bcc.length) {
				 mailMessage.setBCC(bcc);
			 }

			 sendMail = true;
		 }
		 else {
			 mailMessage.setBCC(bcc);
		 }

		 InternetAddress[] bulkAddresses =
			 getInternetAddresses(mailMessage.getBulkAddresses());

		 if (bulkAddresses != null) {
			 if (mailMessage.getBulkAddresses().length > bulkAddresses.length) {
				 mailMessage.setBulkAddresses(bulkAddresses);
			 }

			 sendMail = true;
		 }
		 else {
			 mailMessage.setBulkAddresses(bulkAddresses);
		 }

		if (sendMail) {
			MailEngine.send(mailMessage);
		}
	}

	public void doMethodWrapper(MethodWrapper methodWrapper) throws Exception {
		MethodInvoker.invoke(methodWrapper);
	}

	public void doReceive(Message message) throws Exception {
		Object payload = message.getPayload();

		if (payload instanceof MailMessage) {
			doMailMessage((MailMessage)payload);
		}
		else if (payload instanceof MethodWrapper) {
			doMethodWrapper((MethodWrapper)payload);
		}
	}

	protected InternetAddress getInternetAddress(
		InternetAddress internetAddress) {

		EmailAddressGenerator emailAddressGenerator =
			(EmailAddressGenerator)InstancePool.get(
				PropsValues.USERS_EMAIL_ADDRESS_GENERATOR);

		if (emailAddressGenerator.isFake(internetAddress.getAddress())) {
			return null;
		}

		return internetAddress;
	}

	protected InternetAddress[] getInternetAddresses(
		InternetAddress[] internetAddresses) {

		List<InternetAddress> list = new ArrayList<InternetAddress>();

		if (internetAddresses != null) {
			for (InternetAddress internetAddress : internetAddresses) {
				if (getInternetAddress(internetAddress) != null) {
					list.add(internetAddress);
				}
			}
		}

		if (list.size() == 0) {
			return null;
		}
		else {
			return list.toArray(new InternetAddress[list.size()]);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MailMessageListener.class);

}