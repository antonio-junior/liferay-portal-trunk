/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.scheduler;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.scheduler.CronTrigger;
import com.liferay.portal.kernel.scheduler.IntervalTrigger;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerType;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.util.PrefsPropsUtil;

import java.util.concurrent.TimeUnit;

/**
 * <a href="SchedulerEntryImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 */
public class SchedulerEntryImpl implements SchedulerEntry {

	public String getDescription() {
		return _description;
	}

	public MessageListener getEventListener() {
		return _eventListener;
	}

	public String getEventListenerClass() {
		return _eventListenerClass;
	}

	public Trigger getTrigger() throws SystemException {
		if (_trigger != null) {
			return _trigger;
		}

		String triggerValue = _triggerValue;

		if (_readProperty) {
			triggerValue = PrefsPropsUtil.getString(triggerValue);
		}

		if (_triggerType == TriggerType.CRON) {
			_trigger = new CronTrigger(
				_eventListenerClass, _eventListenerClass, triggerValue);
		}
		else if (_triggerType == TriggerType.SIMPLE) {
			long intervalTime = GetterUtil.getLong(triggerValue);

			_trigger = new IntervalTrigger(
				_eventListenerClass, _eventListenerClass,
				TimeUnit.SECONDS.toMillis(intervalTime));
		}
		else {
			throw new SystemException("Unsupport trigger type " + _triggerType);
		}

		return _trigger;
	}

	public TriggerType getTriggerType() {
		return _triggerType;
	}

	public String getTriggerValue() {
		return _triggerValue;
	}

	public boolean isReadProperty() {
		return _readProperty;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setEventListener(MessageListener eventListener) {
		_eventListener = eventListener;
	}

	public void setEventListenerClass(String eventListenerClass) {
		_eventListenerClass = eventListenerClass;
	}

	public void setReadProperty(boolean readProperty) {
		_readProperty = readProperty;
	}

	public void setTriggerType(TriggerType triggerType) {
		_triggerType = triggerType;
	}

	public void setTriggerValue(String triggerValue) {
		_triggerValue = triggerValue;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{description=");
		sb.append(_description);
		sb.append(", eventListener=");
		sb.append(_eventListener);
		sb.append(", eventListenerClass=");
		sb.append(_eventListenerClass);
		sb.append(", readProperty=");
		sb.append(_readProperty);
		sb.append(", trigger=");
		sb.append(_trigger);
		sb.append(", triggerType=");
		sb.append(_triggerType);
		sb.append(", triggerValue=");
		sb.append(_triggerValue);
		sb.append("}");

		return sb.toString();
	}

	private String _description;
	private MessageListener _eventListener;
	private String _eventListenerClass;
	private boolean _readProperty;
	private Trigger _trigger;
	private TriggerType _triggerType;
	private String _triggerValue;

}