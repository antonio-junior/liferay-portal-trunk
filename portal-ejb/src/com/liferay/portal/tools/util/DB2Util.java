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

package com.liferay.portal.tools.util;

import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.util.FileUtil;
import com.liferay.util.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * <a href="DB2Util.java.html"><b><i>View Source</i></b></a>
 *
 * @author Alexander Chow
 *
 */
public class DB2Util extends DBUtil {

	public static DBUtil getInstance() {
		return _instance;
	}

	public String buildSQL(String template) throws IOException {
		template = convertTimestamp(template);
		template = StringUtil.replace(template, TEMPLATE, getTemplate());

		template = reword(template);
		template = removeLongInserts(template);
		template = removeNull(template);
		template = StringUtil.replace(template, "\\'", "''");

		return template;
	}

	protected void buildCreateFile(String databaseName, boolean minimal)
		throws IOException {

		String minimalSuffix = getMinimalSuffix(minimal);

		File file = new File(
			"../sql/create" + minimalSuffix + "/create" + minimalSuffix +
				"-db2.sql");

		StringMaker sm = new StringMaker();

		sm.append("drop database " + databaseName + ";\n");
		sm.append("create database " + databaseName + ";\n");
		sm.append("connect to " + databaseName + ";\n");
		sm.append(
			FileUtil.read("../sql/portal" + minimalSuffix + "/portal" +
				minimalSuffix + "-db2.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/indexes/indexes-db2.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/sequences/sequences-db2.sql"));

		FileUtil.write(file, sm.toString());
	}

	protected String getServerName() {
		return "db2";
	}

	protected String[] getTemplate() {
		return _DB2;
	}

	protected String reword(String data) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(data));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.startsWith(ALTER_COLUMN_TYPE) ||
				line.startsWith(ALTER_COLUMN_NAME)) {

				line = "-- " + line;
			}

			sm.append(line);
			sm.append("\n");
		}

		br.close();

		return sm.toString();
	}

	private DB2Util() {
	}

	private static String[] _DB2 = {
		"--", "1", "0",
		"'1970-01-01-00.00.00.000000'", "current timestamp",
		" smallint", " timestamp", " double",
		" integer", " bigint",
		" varchar(500)", " clob", " varchar",
		" generated always as identity", "commit"
	};

	private static DB2Util _instance = new DB2Util();

}