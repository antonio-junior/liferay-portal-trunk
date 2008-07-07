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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialRequestLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="VerifySocial.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class VerifySocial extends VerifyProcess {

	public void verify() throws VerifyException {
		_log.info("Verifying");

		try {
			verifySocial();
		}
		catch (Exception e) {
			throw new VerifyException(e);
		}
	}

	protected void deleteDuplicateActivities() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("select distinct sa1.* from SocialActivity sa1 ");
		sb.append("inner join SocialActivity sa2 on ");
		sb.append("sa1.activityId != sa2.activityId and ");
		sb.append("sa1.groupId = sa2.groupId and ");
		sb.append("sa1.userId = sa2.userId and ");
		sb.append("sa1.classNameId = sa2.classNameId and ");
		sb.append("sa1.classPK = sa2.classPK and ");
		sb.append("sa1.type_ = sa2.type_ and ");
		sb.append("sa1.extraData = sa2.extraData and ");
		sb.append("sa1.receiverUserId = sa2.receiverUserId ");
		sb.append("where sa1.mirrorActivityId = 0 ");
		sb.append("order by sa1.groupId, sa1.userId, sa1.classNameId, ");
		sb.append("sa1.classPK, sa1.type_, sa1.extraData, ");
		sb.append("sa1.receiverUserId, sa1.createDate desc");

		String sql = sb.toString();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			long groupId = 0;
			long userId = 0;
			long classNameId = 0;
			long classPK = 0;
			long type = 0;
			String extraData = StringPool.BLANK;
			long receiverUserId = 0;

			while (rs.next()) {
				long curActivityId = rs.getLong("activityId");
				long curGroupId = rs.getLong("groupId");
				long curUserId = rs.getLong("userId");
				long curClassNameId = rs.getLong("classNameId");
				long curClassPK = rs.getLong("classPK");
				long curType = rs.getLong("type_");
				String curExtraData = rs.getString("extraData");
				long curReceiverUserId = rs.getLong("receiverUserId");

				if ((curGroupId == groupId) && (curUserId == userId) &&
					(curClassNameId == classNameId) &&
					(curClassPK == classPK) && (curType == type) &&
					(curExtraData.equals(extraData)) &&
					(curReceiverUserId == receiverUserId)) {

					SocialActivityLocalServiceUtil.deleteActivity(
						curActivityId);
				}
				else {
					groupId = curGroupId;
					userId = curUserId;
					classNameId = curClassNameId;
					classPK = curClassPK;
					type = curType;
					extraData = curExtraData;
					receiverUserId = curReceiverUserId;
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void deleteDuplicateRequests() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("select distinct sr1.* from SocialRequest sr1 ");
		sb.append("inner join SocialRequest sr2 on ");
		sb.append("sr1.requestId != sr2.requestId and ");
		sb.append("sr1.groupId = sr2.groupId and ");
		sb.append("sr1.userId = sr2.userId and ");
		sb.append("sr1.classNameId = sr2.classNameId and ");
		sb.append("sr1.classPK = sr2.classPK and ");
		sb.append("sr1.type_ = sr2.type_ and ");
		sb.append("sr1.extraData = sr2.extraData and ");
		sb.append("sr1.receiverUserId = sr2.receiverUserId ");
		sb.append("order by sr1.groupId, sr1.userId, sr1.classNameId, ");
		sb.append("sr1.classPK, sr1.type_, sr1.extraData, ");
		sb.append("sr1.receiverUserId, sr1.createDate desc");

		String sql = sb.toString();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			long groupId = 0;
			long userId = 0;
			long classNameId = 0;
			long classPK = 0;
			long type = 0;
			String extraData = StringPool.BLANK;
			long receiverUserId = 0;

			while (rs.next()) {
				long curRequestId = rs.getLong("requestId");
				long curGroupId = rs.getLong("groupId");
				long curUserId = rs.getLong("userId");
				long curClassNameId = rs.getLong("classNameId");
				long curClassPK = rs.getLong("classPK");
				long curType = rs.getLong("type_");
				String curExtraData = rs.getString("extraData");
				long curReceiverUserId = rs.getLong("receiverUserId");

				if ((curGroupId == groupId) && (curUserId == userId) &&
					(curClassNameId == classNameId) &&
					(curClassPK == classPK) && (curType == type) &&
					(curExtraData.equals(extraData)) &&
					(curReceiverUserId == receiverUserId)) {

					SocialRequestLocalServiceUtil.deleteRequest(curRequestId);
				}
				else {
					groupId = curGroupId;
					userId = curUserId;
					classNameId = curClassNameId;
					classPK = curClassPK;
					type = curType;
					extraData = curExtraData;
					receiverUserId = curReceiverUserId;
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void verifySocial() throws Exception {
		deleteDuplicateActivities();
		deleteDuplicateRequests();
	}

	private static Log _log = LogFactory.getLog(VerifyJournal.class);

}