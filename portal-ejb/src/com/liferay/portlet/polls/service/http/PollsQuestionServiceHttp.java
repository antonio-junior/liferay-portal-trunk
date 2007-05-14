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

package com.liferay.portlet.polls.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.BooleanWrapper;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.MethodWrapper;
import com.liferay.portal.kernel.util.NullWrapper;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;

import com.liferay.portlet.polls.service.PollsQuestionServiceUtil;

/**
 * <a href="PollsQuestionServiceHttp.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be overwritten
 * the next time is generated.
 * </p>
 *
 * <p>
 * This class provides a HTTP utility for the <code>com.liferay.portlet.polls.service.PollsQuestionServiceUtil</code>
 * service utility. The static methods of this class calls the same methods of the
 * service utility. However, the signatures are different because it requires an
 * additional <code>com.liferay.portal.security.auth.HttpPrincipal</code> parameter.
 * </p>
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for tunneling
 * without the cost of serializing to text. The drawback is that it only works with
 * Java.
 * </p>
 *
 * <p>
 * Set the property <code>tunnel.servlet.hosts.allowed</code> in portal.properties
 * to configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portal.security.auth.HttpPrincipal
 * @see com.liferay.portlet.polls.service.PollsQuestionServiceUtil
 * @see com.liferay.portlet.polls.service.http.PollsQuestionServiceSoap
 *
 */
public class PollsQuestionServiceHttp {
	public static com.liferay.portlet.polls.model.PollsQuestion addQuestion(
		HttpPrincipal httpPrincipal, long plid, java.lang.String title,
		java.lang.String description, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire, java.util.List choices,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException {
		try {
			Object paramObj0 = new LongWrapper(plid);
			Object paramObj1 = title;

			if (title == null) {
				paramObj1 = new NullWrapper("java.lang.String");
			}

			Object paramObj2 = description;

			if (description == null) {
				paramObj2 = new NullWrapper("java.lang.String");
			}

			Object paramObj3 = new IntegerWrapper(expirationDateMonth);
			Object paramObj4 = new IntegerWrapper(expirationDateDay);
			Object paramObj5 = new IntegerWrapper(expirationDateYear);
			Object paramObj6 = new IntegerWrapper(expirationDateHour);
			Object paramObj7 = new IntegerWrapper(expirationDateMinute);
			Object paramObj8 = new BooleanWrapper(neverExpire);
			Object paramObj9 = choices;

			if (choices == null) {
				paramObj9 = new NullWrapper("java.util.List");
			}

			Object paramObj10 = new BooleanWrapper(addCommunityPermissions);
			Object paramObj11 = new BooleanWrapper(addGuestPermissions);
			MethodWrapper methodWrapper = new MethodWrapper(PollsQuestionServiceUtil.class.getName(),
					"addQuestion",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11
					});
			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodWrapper);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.SystemException) {
					throw (com.liferay.portal.SystemException)e;
				}

				if (e instanceof com.liferay.portal.PortalException) {
					throw (com.liferay.portal.PortalException)e;
				}

				throw new com.liferay.portal.SystemException(e);
			}

			return (com.liferay.portlet.polls.model.PollsQuestion)returnObj;
		}
		catch (com.liferay.portal.SystemException se) {
			_log.error(se, se);
			throw se;
		}
	}

	public static com.liferay.portlet.polls.model.PollsQuestion addQuestion(
		HttpPrincipal httpPrincipal, long plid, java.lang.String title,
		java.lang.String description, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire, java.util.List choices,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException {
		try {
			Object paramObj0 = new LongWrapper(plid);
			Object paramObj1 = title;

			if (title == null) {
				paramObj1 = new NullWrapper("java.lang.String");
			}

			Object paramObj2 = description;

			if (description == null) {
				paramObj2 = new NullWrapper("java.lang.String");
			}

			Object paramObj3 = new IntegerWrapper(expirationDateMonth);
			Object paramObj4 = new IntegerWrapper(expirationDateDay);
			Object paramObj5 = new IntegerWrapper(expirationDateYear);
			Object paramObj6 = new IntegerWrapper(expirationDateHour);
			Object paramObj7 = new IntegerWrapper(expirationDateMinute);
			Object paramObj8 = new BooleanWrapper(neverExpire);
			Object paramObj9 = choices;

			if (choices == null) {
				paramObj9 = new NullWrapper("java.util.List");
			}

			Object paramObj10 = communityPermissions;

			if (communityPermissions == null) {
				paramObj10 = new NullWrapper("[Ljava.lang.String;");
			}

			Object paramObj11 = guestPermissions;

			if (guestPermissions == null) {
				paramObj11 = new NullWrapper("[Ljava.lang.String;");
			}

			MethodWrapper methodWrapper = new MethodWrapper(PollsQuestionServiceUtil.class.getName(),
					"addQuestion",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11
					});
			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodWrapper);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.SystemException) {
					throw (com.liferay.portal.SystemException)e;
				}

				if (e instanceof com.liferay.portal.PortalException) {
					throw (com.liferay.portal.PortalException)e;
				}

				throw new com.liferay.portal.SystemException(e);
			}

			return (com.liferay.portlet.polls.model.PollsQuestion)returnObj;
		}
		catch (com.liferay.portal.SystemException se) {
			_log.error(se, se);
			throw se;
		}
	}

	public static void deleteQuestion(HttpPrincipal httpPrincipal,
		long questionId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException {
		try {
			Object paramObj0 = new LongWrapper(questionId);
			MethodWrapper methodWrapper = new MethodWrapper(PollsQuestionServiceUtil.class.getName(),
					"deleteQuestion", new Object[] { paramObj0 });

			try {
				TunnelUtil.invoke(httpPrincipal, methodWrapper);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.SystemException) {
					throw (com.liferay.portal.SystemException)e;
				}

				if (e instanceof com.liferay.portal.PortalException) {
					throw (com.liferay.portal.PortalException)e;
				}

				throw new com.liferay.portal.SystemException(e);
			}
		}
		catch (com.liferay.portal.SystemException se) {
			_log.error(se, se);
			throw se;
		}
	}

	public static com.liferay.portlet.polls.model.PollsQuestion getQuestion(
		HttpPrincipal httpPrincipal, long questionId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException {
		try {
			Object paramObj0 = new LongWrapper(questionId);
			MethodWrapper methodWrapper = new MethodWrapper(PollsQuestionServiceUtil.class.getName(),
					"getQuestion", new Object[] { paramObj0 });
			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodWrapper);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.SystemException) {
					throw (com.liferay.portal.SystemException)e;
				}

				if (e instanceof com.liferay.portal.PortalException) {
					throw (com.liferay.portal.PortalException)e;
				}

				throw new com.liferay.portal.SystemException(e);
			}

			return (com.liferay.portlet.polls.model.PollsQuestion)returnObj;
		}
		catch (com.liferay.portal.SystemException se) {
			_log.error(se, se);
			throw se;
		}
	}

	public static com.liferay.portlet.polls.model.PollsQuestion updateQuestion(
		HttpPrincipal httpPrincipal, long questionId, java.lang.String title,
		java.lang.String description, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire, java.util.List choices)
		throws com.liferay.portal.SystemException, 
			com.liferay.portal.PortalException {
		try {
			Object paramObj0 = new LongWrapper(questionId);
			Object paramObj1 = title;

			if (title == null) {
				paramObj1 = new NullWrapper("java.lang.String");
			}

			Object paramObj2 = description;

			if (description == null) {
				paramObj2 = new NullWrapper("java.lang.String");
			}

			Object paramObj3 = new IntegerWrapper(expirationDateMonth);
			Object paramObj4 = new IntegerWrapper(expirationDateDay);
			Object paramObj5 = new IntegerWrapper(expirationDateYear);
			Object paramObj6 = new IntegerWrapper(expirationDateHour);
			Object paramObj7 = new IntegerWrapper(expirationDateMinute);
			Object paramObj8 = new BooleanWrapper(neverExpire);
			Object paramObj9 = choices;

			if (choices == null) {
				paramObj9 = new NullWrapper("java.util.List");
			}

			MethodWrapper methodWrapper = new MethodWrapper(PollsQuestionServiceUtil.class.getName(),
					"updateQuestion",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9
					});
			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodWrapper);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.SystemException) {
					throw (com.liferay.portal.SystemException)e;
				}

				if (e instanceof com.liferay.portal.PortalException) {
					throw (com.liferay.portal.PortalException)e;
				}

				throw new com.liferay.portal.SystemException(e);
			}

			return (com.liferay.portlet.polls.model.PollsQuestion)returnObj;
		}
		catch (com.liferay.portal.SystemException se) {
			_log.error(se, se);
			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PollsQuestionServiceHttp.class);
}