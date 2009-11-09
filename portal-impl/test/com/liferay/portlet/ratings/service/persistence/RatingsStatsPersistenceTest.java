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

package com.liferay.portlet.ratings.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.service.persistence.BasePersistenceTestCase;

import com.liferay.portlet.ratings.NoSuchStatsException;
import com.liferay.portlet.ratings.model.RatingsStats;

import java.util.List;

/**
 * <a href="RatingsStatsPersistenceTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class RatingsStatsPersistenceTest extends BasePersistenceTestCase {
	public void testCreate() throws Exception {
		long pk = nextLong();

		RatingsStats ratingsStats = _persistence.create(pk);

		assertNotNull(ratingsStats);

		assertEquals(ratingsStats.getPrimaryKey(), pk);
	}

	public void testRemove() throws Exception {
		RatingsStats newRatingsStats = addRatingsStats();

		_persistence.remove(newRatingsStats);

		RatingsStats existingRatingsStats = _persistence.fetchByPrimaryKey(newRatingsStats.getPrimaryKey());

		assertNull(existingRatingsStats);
	}

	public void testUpdateNew() throws Exception {
		addRatingsStats();
	}

	public void testUpdateExisting() throws Exception {
		long pk = nextLong();

		RatingsStats newRatingsStats = _persistence.create(pk);

		newRatingsStats.setClassNameId(nextLong());
		newRatingsStats.setClassPK(nextLong());
		newRatingsStats.setTotalEntries(nextInt());
		newRatingsStats.setTotalScore(nextDouble());
		newRatingsStats.setAverageScore(nextDouble());

		_persistence.update(newRatingsStats, false);

		RatingsStats existingRatingsStats = _persistence.findByPrimaryKey(newRatingsStats.getPrimaryKey());

		assertEquals(existingRatingsStats.getStatsId(),
			newRatingsStats.getStatsId());
		assertEquals(existingRatingsStats.getClassNameId(),
			newRatingsStats.getClassNameId());
		assertEquals(existingRatingsStats.getClassPK(),
			newRatingsStats.getClassPK());
		assertEquals(existingRatingsStats.getTotalEntries(),
			newRatingsStats.getTotalEntries());
		assertEquals(existingRatingsStats.getTotalScore(),
			newRatingsStats.getTotalScore());
		assertEquals(existingRatingsStats.getAverageScore(),
			newRatingsStats.getAverageScore());
	}

	public void testFindByPrimaryKeyExisting() throws Exception {
		RatingsStats newRatingsStats = addRatingsStats();

		RatingsStats existingRatingsStats = _persistence.findByPrimaryKey(newRatingsStats.getPrimaryKey());

		assertEquals(existingRatingsStats, newRatingsStats);
	}

	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			fail("Missing entity did not throw NoSuchStatsException");
		}
		catch (NoSuchStatsException nsee) {
		}
	}

	public void testFetchByPrimaryKeyExisting() throws Exception {
		RatingsStats newRatingsStats = addRatingsStats();

		RatingsStats existingRatingsStats = _persistence.fetchByPrimaryKey(newRatingsStats.getPrimaryKey());

		assertEquals(existingRatingsStats, newRatingsStats);
	}

	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = nextLong();

		RatingsStats missingRatingsStats = _persistence.fetchByPrimaryKey(pk);

		assertNull(missingRatingsStats);
	}

	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RatingsStats newRatingsStats = addRatingsStats();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RatingsStats.class,
				RatingsStats.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("statsId",
				newRatingsStats.getStatsId()));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		assertEquals(1, result.size());

		RatingsStats existingRatingsStats = (RatingsStats)result.get(0);

		assertEquals(existingRatingsStats, newRatingsStats);
	}

	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RatingsStats.class,
				RatingsStats.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("statsId", nextLong()));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		assertEquals(0, result.size());
	}

	protected RatingsStats addRatingsStats() throws Exception {
		long pk = nextLong();

		RatingsStats ratingsStats = _persistence.create(pk);

		ratingsStats.setClassNameId(nextLong());
		ratingsStats.setClassPK(nextLong());
		ratingsStats.setTotalEntries(nextInt());
		ratingsStats.setTotalScore(nextDouble());
		ratingsStats.setAverageScore(nextDouble());

		_persistence.update(ratingsStats, false);

		return ratingsStats;
	}

	protected void setUp() throws Exception {
		super.setUp();

		_persistence = (RatingsStatsPersistence)PortalBeanLocatorUtil.locate(RatingsStatsPersistence.class.getName() +
				".impl");
	}

	private RatingsStatsPersistence _persistence;
}