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

package com.liferay.portlet.social.service.persistence;

/**
 * <a href="SocialRelationFinderUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SocialRelationFinderUtil {
	public static int countByU_T(long userId, int type)
		throws com.liferay.portal.SystemException {
		return getFinder().countByU_T(userId, type);
	}

	public static java.util.List<com.liferay.portlet.social.model.SocialRelation> findByU_T(
		long userId, int type, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getFinder().findByU_T(userId, type, begin, end);
	}

	public static com.liferay.portlet.social.model.SocialRelation findByU_U_T(
		long userId1, long userId2, int type)
		throws com.liferay.portal.SystemException,
			com.liferay.portlet.social.NoSuchRelationException {
		return getFinder().findByU_U_T(userId1, userId2, type);
	}

	public static SocialRelationFinder getFinder() {
		return _getUtil()._finder;
	}

	public void setFinder(SocialRelationFinder finder) {
		_finder = finder;
	}

	private static SocialRelationFinderUtil _getUtil() {
		if (_util == null) {
			_util = (SocialRelationFinderUtil)com.liferay.portal.kernel.bean.BeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = SocialRelationFinderUtil.class.getName();
	private static SocialRelationFinderUtil _util;
	private SocialRelationFinder _finder;
}