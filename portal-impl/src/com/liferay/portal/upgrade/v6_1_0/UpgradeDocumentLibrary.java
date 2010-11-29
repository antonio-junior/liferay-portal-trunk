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

package com.liferay.portal.upgrade.v6_1_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.upgrade.v6_1_0.util.DLFileVersionTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Brian Wing Shun Chan
 * @author Douglas Wong
 * @author Alexander Chow
 */
public class UpgradeDocumentLibrary extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		updateFileRanks();
		updateFileShortcuts();
		updateFileVersions();
	}

	protected long getFileEntryId(long folderId, String name) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		long fileEntryId = 0;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select fileEntryId from DLFileEntry where folderId = ? " +
					"and name = ?");

			ps.setLong(1, folderId);
			ps.setString(2, name);

			rs = ps.executeQuery();

			if (rs.next()) {
				fileEntryId = rs.getLong("fileEntryId");
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return fileEntryId;
	}

	protected void updateFileRanks() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select fileRankId, folderId, name from DLFileRank");

			rs = ps.executeQuery();

			while (rs.next()) {
				long fileRankId = rs.getLong("fileRankId");
				long folderId = rs.getLong("folderId");
				String name = rs.getString("name");

				long fileEntryId = getFileEntryId(folderId, name);

				runSQL(
					"update DLFileRank set fileEntryId = '" + fileEntryId +
						"' where fileRankId = " + fileRankId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		runSQL("alter table DLFileRank drop column folderId");
		runSQL("alter table DLFileRank drop column name");
	}

	protected void updateFileShortcuts() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select fileShortcutId, toFolderId, toName from " +
					"DLFileShortcut");

			rs = ps.executeQuery();

			while (rs.next()) {
				long fileShortcutId = rs.getLong("fileShortcutId");
				long toFolderId = rs.getLong("toFolderId");
				String toName = rs.getString("toName");

				long toFileEntryId = getFileEntryId(toFolderId, toName);

				runSQL(
					"update DLFileShortcut set toFileEntryId = " +
						toFileEntryId + " where fileShortcutId = " +
							fileShortcutId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		runSQL("alter table DLFileShortcut drop column toFolderId");
		runSQL("alter table DLFileShortcut drop column toName");
	}

	protected void updateFileVersions() throws Exception {
		try {
			runSQL("alter_column_type DLFileVersion title VARCHAR(255)");
		}
		catch (Exception e) {
			UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
				DLFileVersionTable.TABLE_NAME,
				DLFileVersionTable.TABLE_COLUMNS);

			upgradeTable.setCreateSQL(DLFileVersionTable.TABLE_SQL_CREATE);

			upgradeTable.updateTable();
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select fileVersionId, folderId, name from DLFileVersion");

			rs = ps.executeQuery();

			while (rs.next()) {
				long fileVersionId = rs.getLong("fileVersionId");
				long folderId = rs.getLong("folderId");
				String name = rs.getString("name");

				long fileEntryId = getFileEntryId(folderId, name);

				runSQL(
					"update DLFileVersion set fileEntryId = '" + fileEntryId +
						"' where fileVersionId = " + fileVersionId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		runSQL("alter table DLFileVersion drop column folderId");
		runSQL("alter table DLFileVersion drop column name");
	}

}