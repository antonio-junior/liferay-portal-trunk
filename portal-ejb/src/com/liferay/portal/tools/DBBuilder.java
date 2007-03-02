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

package com.liferay.portal.tools;

import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.velocity.VelocityUtil;
import com.liferay.util.FileUtil;
import com.liferay.util.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * <a href="DBBuilder.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Charles May
 * @author Alexander Chow
 *
 */
public class DBBuilder {

	public static void main(String[] args) {
		if (args.length == 1) {
			new DBBuilder(args[0]);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public DBBuilder(String databaseName) {
		try {
			_databaseName = databaseName;

			_buildSQL("portal");
			_buildSQL("portal-minimal");
			_buildSQL("indexes");
			_buildSQL("sequences");
			_buildSQL("update-1.7.5-1.8.0");
			_buildSQL("update-1.8.0-1.9.0");
			_buildSQL("update-1.9.1-1.9.5");
			_buildSQL("update-1.9.5-2.0.0");
			_buildSQL("update-2.0.3-2.1.0");
			_buildSQL("update-2.1.1-2.2.0");
			_buildSQL("update-2.2.1-2.2.5");
			_buildSQL("update-3.1.0-3.2.0");
			_buildSQL("update-3.2.0-3.5.0");
			_buildSQL("update-3.5.0-3.6.0");
			_buildSQL("update-3.6.0-4.0.0");
			_buildSQL("update-4.0.0-4.1.0");
			_buildSQL("update-4.1.0-4.2.0");
			_buildSQL("update-4.2.0-4.3.0");
			_buildCreate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void _buildCreate() throws IOException {
		_buildCreateDb2();
		_buildCreateDerby();
		_buildCreateFirebird();
		_buildCreateMySQL();
		_buildCreateOracle();
		_buildCreatePostgreSQL();
		_buildCreateSQLServer();
		_buildCreateSybase();
	}

	private void _buildCreateDb2() throws IOException {
		_buildCreateDb2(false);
		_buildCreateDb2(true);
	}

	private void _buildCreateDb2(boolean minimal) throws IOException {
		String minimalSuffix = _getMinimalSuffix(minimal);

		File file = new File(
			"../sql/create" + minimalSuffix + "/create" + minimalSuffix +
				"-db2.sql");

		StringMaker sm = new StringMaker();

		sm.append("drop database " + _databaseName + ";\n");
		sm.append("create database " + _databaseName + ";\n");
		sm.append("connect to " + _databaseName + ";\n");
		sm.append(
			FileUtil.read("../sql/portal" + minimalSuffix + "/portal" +
				minimalSuffix + "-db2.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/indexes/indexes-db2.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/sequences/sequences-db2.sql"));

		FileUtil.write(file, sm.toString());
	}

	private void _buildCreateDerby() throws IOException {
		_buildCreateDerby(false);
		_buildCreateDerby(true);
	}

	private void _buildCreateDerby(boolean minimal) throws IOException {
		String minimalSuffix = _getMinimalSuffix(minimal);

		File file = new File(
			"../sql/create" + minimalSuffix + "/create" + minimalSuffix +
				"-derby.sql");

		StringMaker sm = new StringMaker();

		sm.append("drop database " + _databaseName + ";\n");
		sm.append("create database " + _databaseName + ";\n");
		sm.append("connect to " + _databaseName + ";\n");
		sm.append(
			FileUtil.read(
				"../sql/portal" + minimalSuffix + "/portal" + minimalSuffix +
					"-derby.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/indexes/indexes-derby.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/sequences/sequences-derby.sql"));

		FileUtil.write(file, sm.toString());
	}

	private void _buildCreateFirebird() throws IOException {
		_buildCreateFirebird(false);
		_buildCreateFirebird(true);
	}

	private void _buildCreateFirebird(boolean minimal) throws IOException {
		String minimalSuffix = _getMinimalSuffix(minimal);

		File file = new File(
			"../sql/create" + minimalSuffix + "/create" + minimalSuffix +
				"-firebird.sql");

		StringMaker sm = new StringMaker();

		sm.append(
			"create database '" + _databaseName +
				".gdb' page_size 8192 user 'sysdba' password 'masterkey';\n");
		sm.append(
			"connect '" + _databaseName +
				".gdb' user 'sysdba' password 'masterkey';\n");
		sm.append(
			_readSQL(
				"../sql/portal" + minimalSuffix + "/portal" + minimalSuffix +
					"-firebird.sql",
				_FIREBIRD[0], ";\n"));

		FileUtil.write(file, sm.toString());
	}

	private void _buildCreateMySQL() throws IOException {
		_buildCreateMySQL(false);
		_buildCreateMySQL(true);
	}

	private void _buildCreateMySQL(boolean minimal) throws IOException {
		String minimalSuffix = _getMinimalSuffix(minimal);

		File file = new File(
			"../sql/create" + minimalSuffix + "/create" + minimalSuffix +
				"-mysql.sql");

		StringMaker sm = new StringMaker();

		sm.append("drop database if exists " + _databaseName + ";\n");
		sm.append(
			"create database " + _databaseName + " character set utf8;\n");
		sm.append("use ");
		sm.append(_databaseName);
		sm.append(";\n\n");
		sm.append(
			FileUtil.read(
				"../sql/portal" + minimalSuffix + "/portal" + minimalSuffix +
					"-mysql.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/indexes/indexes-mysql.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/sequences/sequences-mysql.sql"));

		FileUtil.write(file, sm.toString());
	}

	private void _buildCreateOracle() throws IOException {
		_buildCreateOracle(false);
		_buildCreateOracle(true);
	}

	private void _buildCreateOracle(boolean minimal) throws IOException {
		String minimalSuffix = _getMinimalSuffix(minimal);

		File file = new File(
			"../sql/create" + minimalSuffix + "/create" + minimalSuffix +
				"-oracle.sql");

		StringMaker sm = new StringMaker();

		sm.append("drop user &1 cascade;\n");
		sm.append("create user &1 identified by &2;\n");
		sm.append("grant connect,resource to &1;\n");
		sm.append("connect &1/&2;\n");
		sm.append("set define off;\n");
		sm.append("\n");
		sm.append(
			FileUtil.read(
				"../sql/portal" + minimalSuffix + "/portal" + minimalSuffix +
					"-oracle.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/indexes/indexes-oracle.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/sequences/sequences-oracle.sql"));
		sm.append("\n");
		sm.append("quit");

		FileUtil.write(file, sm.toString());
	}

	private void _buildCreatePostgreSQL() throws IOException {
		_buildCreatePostgreSQL(false);
		_buildCreatePostgreSQL(true);
	}

	private void _buildCreatePostgreSQL(boolean minimal) throws IOException {
		String minimalSuffix = _getMinimalSuffix(minimal);

		File file = new File(
			"../sql/create" + minimalSuffix + "/create" + minimalSuffix +
				"-postgresql.sql");

		StringMaker sm = new StringMaker();

		sm.append("drop database " + _databaseName + ";\n");
		sm.append(
			"create database " + _databaseName + " encoding = 'UNICODE';\n");
		sm.append("\\c " + _databaseName + ";\n\n");
		sm.append(
			FileUtil.read(
				"../sql/portal" + minimalSuffix + "/portal" + minimalSuffix +
					"-postgresql.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/indexes/indexes-postgresql.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/sequences/sequences-postgresql.sql"));

		FileUtil.write(file, sm.toString());
	}

	private void _buildCreateSQLServer() throws IOException {
		_buildCreateSQLServer(false);
		_buildCreateSQLServer(true);
	}

	private void _buildCreateSQLServer(boolean minimal) throws IOException {
		String minimalSuffix = _getMinimalSuffix(minimal);

		File file = new File(
			"../sql/create" + minimalSuffix + "/create" + minimalSuffix +
				"-sql-server.sql");

		StringMaker sm = new StringMaker();

		sm.append("drop database " + _databaseName + ";\n");
		sm.append("create database " + _databaseName + ";\n");
		sm.append("\n");
		sm.append("go\n");
		sm.append("\n");
		sm.append("use " + _databaseName + ";\n\n");
		sm.append(
			FileUtil.read(
				"../sql/portal" + minimalSuffix + "/portal" + minimalSuffix +
					"-sql-server.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/indexes/indexes-sql-server.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/sequences/sequences-sql-server.sql"));

		FileUtil.write(file, sm.toString());
	}

	private void _buildCreateSybase() throws IOException {
		_buildCreateSybase(false);
		_buildCreateSybase(true);
	}

	private void _buildCreateSybase(boolean minimal) throws IOException {
		String minimalSuffix = _getMinimalSuffix(minimal);

		File file = new File(
			"../sql/create" + minimalSuffix + "/create" + minimalSuffix +
				"-sybase.sql");

		StringMaker sm = new StringMaker();

		sm = new StringMaker();

		sm.append("use " + _databaseName + "\n\n");
		sm.append(
			FileUtil.read(
				"../sql/portal" + minimalSuffix + "/portal" + minimalSuffix +
					"-sybase.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/indexes/indexes-sybase.sql"));
		sm.append("\n\n");
		sm.append(FileUtil.read("../sql/sequences/sequences-sybase.sql"));

		FileUtil.write(file, sm.toString());
	}

	private static String[] _buildColumnNameTokens(String line) {
		String[] words = StringUtil.split(line, " ");

		if (words.length == 7) {
			words[5] = "not null;";
		}

		String[] template = {
			words[1], words[2], words[3], words[4], words[5]
		};

		return template;
	}

	private static String[] _buildColumnTypeTokens(String line) {
		String[] words = StringUtil.split(line, " ");

		String nullable = "";

		if (words.length == 6) {
			nullable = "not null;";
		}
		else if (words.length == 5) {
			nullable = words[4];
		}
		else if (words.length == 4) {
			nullable = "not null;";

			if (words[3].endsWith(";")) {
				words[3] = words[3].substring(0, words[3].length() - 1);
			}
		}

		String[] template = {
			words[1], words[2], "", words[3], nullable
		};

		return template;
	}

	private void _buildSQL(String fileName) throws IOException {
		File file = new File("../sql/" + fileName + ".sql");

		if (!file.exists()) {
			return;
		}

		// DB2

		String db2 = _buildTemplate(fileName, _DB2, "db2");

		db2 = _rewordDb2(db2);
		db2 = _removeLongInserts(db2);
		db2 = _removeNull(db2);
		db2 = StringUtil.replace(db2, "\\'", "''");

		FileUtil.write("../sql/" + fileName + "/" + fileName + "-db2.sql", db2);

		// Derby

		String derby = _buildTemplate(fileName, _DERBY, "derby");

		derby = _rewordDerby(derby);
		//derby = _removeLongInserts(derby);
		derby = _removeNull(derby);
		derby = StringUtil.replace(derby, "\\'", "''");

		FileUtil.write(
			"../sql/" + fileName + "/" + fileName + "-derby.sql", derby);

		// Firebird

		String firebird = _buildTemplate(fileName, _FIREBIRD, "firebird");

		firebird = _rewordFirebird(firebird);
		firebird = _removeInserts(firebird);
		firebird = _removeNull(firebird);

		FileUtil.write(
			"../sql/" + fileName + "/" + fileName + "-firebird.sql", firebird);

		// Hypersonic

		String hypersonic = _buildTemplate(fileName, _HYPERSONIC, "hypersonic");

		hypersonic = _rewordHypersonic(hypersonic);
		hypersonic = StringUtil.replace(hypersonic, "\\'", "''");

		FileUtil.write(
			"../sql/" + fileName + "/" + fileName + "-hypersonic.sql",
			hypersonic);

		// InterBase

		FileUtil.write(
			"../sql/" + fileName + "/" + fileName + "-interbase.sql", firebird);

		// JDataStore

		String jDataStore = _buildTemplate(fileName, _JDATASTORE, "jdatastore");

		jDataStore = _rewordFirebird(jDataStore);
		jDataStore = StringUtil.replace(
			jDataStore,
			new String[] {"\\'", "\\\"", "\\\\",  "\\n", "\\r"},
			new String[] {"''", "\"", "\\", "\n", "\r"});

		FileUtil.write(
			"../sql/" + fileName + "/" + fileName + "-jdatastore.sql",
			jDataStore);

		// MySQL

		String mysql = _buildTemplate(fileName, _MYSQL, "mysql");

		mysql = _rewordMysql(mysql);
		mysql = StringUtil.replace(mysql, "\\'", "''");

		FileUtil.write(
			"../sql/" + fileName + "/" + fileName + "-mysql.sql", mysql);

		// Oracle

		String oracle = _buildTemplate(fileName, _ORACLE, "oracle");

		oracle = _rewordOracle(oracle);
		oracle = StringUtil.replace(
			oracle,
			new String[] {"\\\\", "\\'", "\\\""},
			new String[] {"\\", "''", "\""});

		BufferedReader br = new BufferedReader(new StringReader(oracle));

		StringMaker imageSM = new StringMaker();
		StringMaker journalArticleSM = new StringMaker();
		StringMaker journalStructureSM = new StringMaker();
		StringMaker journalTemplateSM = new StringMaker();
		StringMaker shoppingItemSM = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.startsWith("insert into Image")) {
				_convertToOracleCSV(line, imageSM);
			}
			else if (line.startsWith("insert into JournalArticle")) {
				_convertToOracleCSV(line, journalArticleSM);
			}
			else if (line.startsWith("insert into JournalStructure")) {
				_convertToOracleCSV(line, journalStructureSM);
			}
			else if (line.startsWith("insert into JournalTemplate")) {
				_convertToOracleCSV(line, journalTemplateSM);
			}
			else if (line.startsWith("insert into ShoppingItem")) {
				_convertToOracleCSV(line, shoppingItemSM);
			}
		}

		br.close();

		if (imageSM.length() > 0) {
			FileUtil.write(
				"../sql/" + fileName + "/" + fileName + "-oracle-image.csv",
				imageSM.toString());
		}

		if (journalArticleSM.length() > 0) {
			FileUtil.write(
				"../sql/" + fileName + "/" + fileName +
					"-oracle-journalarticle.csv",
				journalArticleSM.toString());
		}

		if (journalStructureSM.length() > 0) {
			FileUtil.write(
				"../sql/" + fileName + "/" + fileName +
					"-oracle-journalstructure.csv",
				journalStructureSM.toString());
		}

		if (journalTemplateSM.length() > 0) {
			FileUtil.write(
				"../sql/" + fileName + "/" + fileName +
					"-oracle-journaltemplate.csv",
				journalTemplateSM.toString());
		}

		if (shoppingItemSM.length() > 0) {
			FileUtil.write(
				"../sql/" + fileName + "/" + fileName +
					"-oracle-shoppingitem.csv",
				shoppingItemSM.toString());
		}

		oracle = _removeLongInserts(oracle);
		oracle = StringUtil.replace(oracle, "\\n", "'||CHR(10)||'");

		FileUtil.write(
			"../sql/" + fileName + "/" + fileName + "-oracle.sql", oracle);

		// PostgreSQL

		String postgresql = _buildTemplate(fileName, _POSTGRESQL, "postgresql");

		postgresql = _rewordPostgreSQL(postgresql);

		FileUtil.write(
			"../sql/" + fileName + "/" + fileName + "-postgresql.sql",
			postgresql);

		// SAP

		String sap = _buildTemplate(fileName, _SAP, "sap");

		sap = _rewordSAP(sap);

		FileUtil.write("../sql/" + fileName + "/" + fileName + "-sap.sql", sap);

		// SQL Server

		String sqlServer = _buildTemplate(fileName, _SQL_SERVER, "sql-server");

		sqlServer = _rewordSQLServer(sqlServer);
		sqlServer = StringUtil.replace(sqlServer, "\ngo;\n", "\ngo\n");
		sqlServer = StringUtil.replace(
			sqlServer,
			new String[] {"\\\\", "\\'", "\\\"", "\\n", "\\r"},
			new String[] {"\\", "''", "\"", "\n", "\r"});

		FileUtil.write(
			"../sql/" + fileName + "/" + fileName + "-sql-server.sql",
			sqlServer);

		// Sybase

		String sybase = _buildTemplate(fileName, _SYBASE, "sybase");

		sybase = _rewordSybase(sybase);
		sybase = StringUtil.replace(sybase, ");\n", ")\ngo\n");
		sybase = StringUtil.replace(sybase, "\ngo;\n", "\ngo\n");
		sybase = StringUtil.replace(
			sybase,
			new String[] {"\\\\", "\\'", "\\\"", "\\n", "\\r"},
			new String[] {"\\", "''", "\"", "\n", "\r"});

		FileUtil.write(
			"../sql/" + fileName + "/" + fileName + "-sybase.sql", sybase);
	}

	private String _buildTemplate(
			String fileName, String[] replace, String server)
		throws IOException {

		File file = new File("../sql/" + fileName + ".sql");

		String template = FileUtil.read(file);

		if (fileName.equals("portal") || fileName.equals("portal-minimal") ||
			fileName.equals("update-3.6.0-4.0.0")) {

			BufferedReader br = new BufferedReader(new StringReader(template));

			StringMaker sm = new StringMaker();

			String line = null;

			while ((line = br.readLine()) != null) {
				if (line.startsWith("@include ")) {
					int pos = line.indexOf(" ");

					String includeFileName =
						line.substring(pos + 1, line.length());

					String include = FileUtil.read("../sql/" + includeFileName);

					if (includeFileName.endsWith(".vm")) {
						try {
							include = _evaluateVM(include);
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}

					include = _convertTimestamp(include, server);
					include = StringUtil.replace(include, _TEMPLATE, replace);

					sm.append(include);
					sm.append("\n\n");
				}
				else {
					sm.append(line);
					sm.append("\n");
				}
			}

			br.close();

			template = sm.toString();
		}

		//if (fileName.startsWith("update-")) {
			template = _convertTimestamp(template, server);
			template = StringUtil.replace(template, _TEMPLATE, replace);
		//}

		if (fileName.equals("indexes") && server.equals("sybase")) {
			template = _removeBooleanIndexes(template);
		}

		return template;
	}

	private String _convertTimestamp(String data, String server) {
		String s = null;

		if (server.equals("mysql")) {
			s = StringUtil.replace(data, "SPECIFIC_TIMESTAMP_", "");
		}
		else {
			s = data.replaceAll(
				"SPECIFIC_TIMESTAMP_" + "\\d+", "CURRENT_TIMESTAMP");
		}

		return s;
	}

	private void _convertToOracleCSV(String line, StringMaker sm) {
		int x = line.indexOf("values (");
		int y = line.lastIndexOf(");");

		line = line.substring(x + 8, y);

		line = StringUtil.replace(line, "sysdate, ", "20050101, ");

		sm.append(line);
		sm.append("\n");
	}

	private String _evaluateVM(String template) throws Exception {
		template = VelocityUtil.evaluate(template);

		// Trim insert statements because it breaks MySQL Query Browser

		BufferedReader br = new BufferedReader(new StringReader(template));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			line = line.trim();

			sm.append(line);
			sm.append("\n");
		}

		br.close();

		template = sm.toString();
		template = StringUtil.replace(template, "\n\n\n", "\n\n");

		return template;
	}

	private String _getMinimalSuffix(boolean minimal) {
		if (minimal) {
			return "-minimal";
		}
		else {
			return StringPool.BLANK;
		}
	}

	private String _readSQL(String fileName, String comments, String eol)
		throws IOException {

		BufferedReader br = new BufferedReader(
			new FileReader(new File(fileName)));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (!line.startsWith(comments)) {
				line = StringUtil.replace(
					line,
					new String[] {"\n", "\t"},
					new String[] {"", ""});

				if (line.endsWith(";")) {
					sm.append(line.substring(0, line.length() - 1));
					sm.append(eol);
				}
				else {
					sm.append(line);
				}
			}
		}

		br.close();

		return sm.toString();
	}

	private String _removeBooleanIndexes(String data) throws IOException {
		String portalData = FileUtil.read("../sql/portal-tables.sql");

		BufferedReader br = new BufferedReader(new StringReader(data));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			boolean append = true;

			int x = line.indexOf(" on ");

			if (x != -1) {
				int y = line.indexOf(" (", x);

				String table = line.substring(x + 4, y);

				x = y + 2;
				y = line.indexOf(")", x);

				String[] columns = StringUtil.split(line.substring(x, y));

				x = portalData.indexOf("create table " + table + " (");
				y = portalData.indexOf(");", x);

				String portalTableData = portalData.substring(x, y);

				for (int i = 0; i < columns.length; i++) {
					if (portalTableData.indexOf(
							columns[i].trim() + " BOOLEAN") != -1) {

						append = false;

						break;
					}
				}
			}

			if (append) {
				sm.append(line);
				sm.append("\n");
			}
		}

		br.close();

		return sm.toString();
	}

	private String _removeInserts(String data) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(data));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (!line.startsWith("insert into ") &&
				!line.startsWith("update ")) {

				sm.append(line);
				sm.append("\n");
			}
		}

		br.close();

		return sm.toString();
	}

	private String _removeLongInserts(String data) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(data));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (!line.startsWith("insert into Image") &&
				!line.startsWith("insert into JournalArticle") &&
				!line.startsWith("insert into JournalStructure") &&
				!line.startsWith("insert into JournalTemplate") &&
				!line.startsWith("insert into ShoppingItem")) {

				sm.append(line);
				sm.append("\n");
			}
		}

		br.close();

		return sm.toString();
	}

	private String _removeNull(String content) {
		content = StringUtil.replace(content, " not null", " not_null");
		content = StringUtil.replace(content, " null", "");
		content = StringUtil.replace(content, " not_null", " not null");

		return content;
	}

	private String _rewordDb2(String data) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(data));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.startsWith(_ALTER_COLUMN_TYPE) ||
				line.startsWith(_ALTER_COLUMN_NAME)) {

				line = "-- " + line;
			}

			sm.append(line);
			sm.append("\n");
		}

		br.close();

		return sm.toString();
	}

	private String _rewordDerby(String data) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(data));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.startsWith(_ALTER_COLUMN_TYPE) ||
				line.startsWith(_ALTER_COLUMN_NAME)) {

				line = "-- " + line;
			}

			sm.append(line);
			sm.append("\n");
		}

		br.close();

		return sm.toString();
	}

	private String _rewordFirebird(String data) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(data));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.startsWith(_ALTER_COLUMN_TYPE)) {
				String[] template = _buildColumnTypeTokens(line);

				line = StringUtil.replace(
					"alter table @table@ alter column \"@old-column@\" type @type@;",
					_REWORD_TEMPLATE, template);
			}
			else if (line.startsWith(_ALTER_COLUMN_NAME)) {
				String[] template = _buildColumnNameTokens(line);

				line = StringUtil.replace(
					"alter table @table@ alter column \"@old-column@\" to \"@new-column@\";",
					_REWORD_TEMPLATE, template);
			}

			sm.append(line);
			sm.append("\n");
		}

		br.close();

		return sm.toString();
	}

	private String _rewordHypersonic(String data) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(data));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.startsWith(_ALTER_COLUMN_TYPE)) {
				String[] template = _buildColumnTypeTokens(line);

				line = StringUtil.replace(
					"alter table @table@ alter column @type@ @nullable@;",
					_REWORD_TEMPLATE, template);
			}
			else if (line.startsWith(_ALTER_COLUMN_NAME)) {
				String[] template = _buildColumnNameTokens(line);

				line = StringUtil.replace(
					"alter table @table@ alter column @old-column@ rename to @new-column@;",
					_REWORD_TEMPLATE, template);
			}

			sm.append(line);
			sm.append("\n");
		}

		br.close();

		return sm.toString();
	}

	private String _rewordMysql(String data) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(data));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.startsWith(_ALTER_COLUMN_TYPE)) {
				String[] template = _buildColumnTypeTokens(line);

				line = StringUtil.replace(
					"alter table @table@ modify @old-column@ @type@;",
					_REWORD_TEMPLATE, template);
			}
			else if (line.startsWith(_ALTER_COLUMN_NAME)) {
				String[] template = _buildColumnNameTokens(line);

				line = StringUtil.replace(
					"alter table @table@ change column @old-column@ @new-column@ @type@;",
					_REWORD_TEMPLATE, template);
			}

			sm.append(line);
			sm.append("\n");
		}

		br.close();

		return sm.toString();
	}

	private String _rewordOracle(String data) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(data));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.startsWith(_ALTER_COLUMN_TYPE)) {
				String[] template = _buildColumnTypeTokens(line);

				line = StringUtil.replace(
					"alter table @table@ modify @old-column@ @type@;",
					_REWORD_TEMPLATE, template);
			}
			else if (line.startsWith(_ALTER_COLUMN_NAME)) {
				String[] template = _buildColumnNameTokens(line);

				line = StringUtil.replace(
					"alter table @table@ rename column @old-column@ to @new-column@;",
					_REWORD_TEMPLATE, template);
			}

			sm.append(line);
			sm.append("\n");
		}

		br.close();

		return sm.toString();
	}

	private String _rewordPostgreSQL(String data) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(data));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.startsWith(_ALTER_COLUMN_TYPE)) {
				String[] template = _buildColumnTypeTokens(line);

				line = StringUtil.replace(
					"alter table @table@ alter @old-column@ type @type@;",
					_REWORD_TEMPLATE, template);
			}
			else if (line.startsWith(_ALTER_COLUMN_NAME)) {
				String[] template = _buildColumnNameTokens(line);

				line = StringUtil.replace(
					"alter table @table@ rename @old-column@ to @new-column@;",
					_REWORD_TEMPLATE, template);
			}

			sm.append(line);
			sm.append("\n");
		}

		br.close();

		return sm.toString();
	}

	private String _rewordSAP(String data) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(data));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.startsWith(_ALTER_COLUMN_TYPE)) {
				String[] template = _buildColumnTypeTokens(line);

				line = StringUtil.replace(
					"alter table @table@ modify @old-column@ @type@;",
					_REWORD_TEMPLATE, template);
			}
			else if (line.startsWith(_ALTER_COLUMN_NAME)) {
				String[] template = _buildColumnNameTokens(line);

				line = StringUtil.replace(
					"rename column @table@.@old-column@ to @new-column@;",
					_REWORD_TEMPLATE, template);
			}

			sm.append(line);
			sm.append("\n");
		}

		br.close();

		return sm.toString();
	}

	private String _rewordSQLServer(String data) throws IOException {
		BufferedReader br = new BufferedReader(new StringReader(data));

		StringMaker sm = new StringMaker();

		String line = null;

		while ((line = br.readLine()) != null) {
			if (line.startsWith(_ALTER_COLUMN_TYPE)) {
				String[] template = _buildColumnTypeTokens(line);

				line = StringUtil.replace(
					"alter table @table@ alter column @old-column@ @type@;",
					_REWORD_TEMPLATE, template);
			}
			else if (line.startsWith(_ALTER_COLUMN_NAME)) {
				String[] template = _buildColumnNameTokens(line);

				line = StringUtil.replace(
					"exec sp_rename '@table@.@old-column@', '@new-column@', 'column';",
					_REWORD_TEMPLATE, template);
			}

			sm.append(line);
			sm.append("\n");
		}

		br.close();

		return sm.toString();
	}

	private String _rewordSybase(String data) throws IOException {
		return _rewordSQLServer(data);
	}

	private static String _ALTER_COLUMN_TYPE = "alter_column_type ";

	private static String _ALTER_COLUMN_NAME = "alter_column_name ";

	private static String[] _REWORD_TEMPLATE = {
		"@table@", "@old-column@", "@new-column@", "@type@", "@nullable@"
	};

	private static String[] _TEMPLATE = {
		"##", "TRUE", "FALSE",
		"'01/01/1970'", "CURRENT_TIMESTAMP",
		" BOOLEAN", " DATE", " DOUBLE",
		" INTEGER", " LONG",
		" STRING", " TEXT", " VARCHAR",
		" IDENTITY", "COMMIT_TRANSACTION"
	};

	private static String[] _DB2 = {
		"--", "1", "0",
		"'1970-01-01-00.00.00.000000'", "current timestamp",
		" smallint", " timestamp", " double",
		" integer", " bigint",
		" varchar(500)", " clob", " varchar",
		" generated always as identity", "commit"
	};

	private static String[] _DERBY = {
		"--", "1", "0",
		"'1970-01-01-00.00.00.000000'", "current timestamp",
		" smallint", " timestamp", " double",
		" integer", " bigint",
		" long varchar", " clob", " varchar",
		" generated always as identity", "commit"
	};

	private static String[] _FIREBIRD = {
		"--", "1", "0",
		"'01/01/1970'", "current_timestamp",
		" smallint", " timestamp", " double precision",
		" integer", " int64",
		" varchar(4000)", " blob", " varchar",
		"", "commit"
	};

	private static String[] _HYPERSONIC = {
		"//", "true", "false",
		"'1970-01-01'", "now()",
		" bit", " timestamp", " double",
		" int", " bigint",
		" longvarchar", " longvarchar", " varchar",
		"", "commit"
	};

	private static String[] _JDATASTORE = {
		"--", "TRUE", "FALSE",
		"'1970-01-01'", "current_timestamp",
		" boolean", " date", " double",
		" integer", " bigint",
		" long varchar", " long varchar", " varchar",
		"", "commit"
	};

	private static String[] _MYSQL = {
		"##", "1", "0",
		"'1970-01-01'", "now()",
		" tinyint", " datetime", " double",
		" integer", " bigint",
		" longtext", " longtext", " varchar",
		"  auto_increment", "commit"
	};

	private static String[] _ORACLE = {
		"--", "1", "0",
		"to_date('1970-01-01 00:00:00','YYYY-MM-DD HH24:MI:SS')", "sysdate",
		" number(1, 0)", " timestamp", " number(30,20)",
		" number(30,0)", " number(30,0)",
		" varchar2(4000)", " clob", " varchar2",
		"", "commit"
	};

	private static String[] _POSTGRESQL = {
		"--", "true", "false",
		"'01/01/1970'", "current_timestamp",
		" bool", " timestamp", " double precision",
		" integer", " bigint",
		" text", " text", " varchar",
		"", "commit"
	};

	private static String[] _SAP = {
		"##", "TRUE", "FALSE",
		"'1970-01-01 00:00:00.000000'", "timestamp",
		" boolean", " timestamp", " float",
		" int", " bigint",
		" varchar", " varchar", " varchar",
		"", "commit"
	};

	private static String[] _SQL_SERVER = {
		"--", "1", "0",
		"'19700101'", "GetDate()",
		" bit", " datetime", " float",
		" int", " bigint",
		" varchar(1000)", " text", " varchar",
		"  identity(1,1)", "go"
	};

	private static String[] _SYBASE = {
		"--", "1", "0",
		"'19700101'", "getdate()",
		" int", " datetime", " float",
		" int", " decimal(20,0)",
		" varchar(1000)", " text", " varchar",
		"  identity(1,1)", "go"
	};

	private String _databaseName;

}