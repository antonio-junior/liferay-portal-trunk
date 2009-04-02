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

package com.liferay.portal.tools.samplesqlbuilder;

import com.liferay.portal.freemarker.FreeMarkerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil_IW;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.tools.sql.DBUtil;
import com.liferay.portal.util.InitUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.model.BlogsStatsUser;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBDiscussion;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBStatsUser;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.tags.model.TagsAsset;
import com.liferay.util.SimpleCounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="SampleSQLBuilder.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SampleSQLBuilder {

	public static void main(String[] args) {
		InitUtil.initWithSpring();

		String outputDir = System.getProperty("sample.sql.output.dir");
		boolean generateSecurity = GetterUtil.getBoolean(
			System.getProperty("sample.sql.generate.security"));
		int maxBlogsEntryCount = GetterUtil.getInteger(
			System.getProperty("sample.sql.max.blogs.entry.count"));
		int maxGroupsCount = GetterUtil.getInteger(
			System.getProperty("sample.sql.max.groups.count"));
		int maxMBCategoryCount = GetterUtil.getInteger(
			System.getProperty("sample.sql.max.mb.category.count"));
		int maxMBMessageCount = GetterUtil.getInteger(
			System.getProperty("sample.sql.max.mb.message.count"));
		int maxMBThreadCount = GetterUtil.getInteger(
			System.getProperty("sample.sql.max.mb.thread.count"));
		int maxUserCount = GetterUtil.getInteger(
			System.getProperty("sample.sql.max.user.count"));

		new SampleSQLBuilder(
			outputDir, generateSecurity, maxBlogsEntryCount, maxGroupsCount,
			maxMBCategoryCount, maxMBMessageCount, maxMBThreadCount,
			maxUserCount);
	}

	public SampleSQLBuilder(
		String outputDir, boolean generateSecurity, int maxBlogsEntryCount,
		int maxGroupsCount, int maxMBCategoryCount, int maxMBMessageCount,
		int maxMBThreadCount, int maxUserCount) {

		try {
			_outputDir = outputDir;
			_generateSecurity = generateSecurity;
			_maxBlogsEntryCount = maxBlogsEntryCount;
			_maxGroupsCount = maxGroupsCount;
			_maxMBCategoryCount = maxMBCategoryCount;
			_maxMBMessageCount = maxMBMessageCount;
			_maxMBThreadCount = maxMBThreadCount;
			_maxUserCount = maxUserCount;

			_counter = new SimpleCounter();
			_permissionCounter = new SimpleCounter();
			_resourceCounter = new SimpleCounter();
			_resourceCodeCounter = new SimpleCounter();

			_userScreenNameIncrementer = new SimpleCounter();

			_dataFactory = new DataFactory(
				_counter, _permissionCounter, _resourceCounter,
				_resourceCodeCounter);

			// Generic

			_writerGeneric = new FileWriter(_outputDir +  "/sample.sql");

			createSample();

			_writerGeneric.flush();

			// MySQL

			_writerMySQL = new FileWriter(_outputDir +  "/sample-mysql.sql");

			DBUtil mysqlDBUtil = DBUtil.getInstance(DBUtil.TYPE_MYSQL);

			boolean previousBlankLine = false;

			BufferedReader br = new BufferedReader(
				new FileReader(_outputDir +  "/sample.sql"));

			String s = null;

			while ((s = br.readLine()) != null) {
				s = mysqlDBUtil.buildSQL(s).trim();

				_writerMySQL.write(s);

				if (previousBlankLine && Validator.isNull(s)) {
				}
				else {
					_writerMySQL.write(StringPool.NEW_LINE);
				}

				if (Validator.isNull(s)) {
					previousBlankLine = true;
				}
			}

			br.close();

			_writerMySQL.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertBlogsEntry(BlogsEntry blogsEntry) throws Exception {
		Map<String, Object> context = getContext();

		put(context, "blogsEntry", blogsEntry);

		processTemplate(_tplBlogsEntry, context);
	}

	public void insertBlogsStatsUser(BlogsStatsUser blogsStatsUser)
		throws Exception {

		Map<String, Object> context = getContext();

		put(context, "blogsStatsUser", blogsStatsUser);

		processTemplate(_tplBlogsStatsUser, context);
	}

	public void insertGroup(
			Group group, List<Layout> privateLayouts,
			List<Layout> publicLayouts)
		throws Exception {

		Map<String, Object> context = getContext();

		put(context, "group", group);
		put(context, "privateLayouts", privateLayouts);
		put(context, "publicLayouts", publicLayouts);

		processTemplate(_tplGroup, context);
	}

	public void insertMBCategory(MBCategory mbCategory) throws Exception {
		Map<String, Object> context = getContext();

		put(context, "mbCategory", mbCategory);

		processTemplate(_tplMBCategory, context);
	}

	public void insertMBDiscussion(MBDiscussion mbDiscussion) throws Exception {
		Map<String, Object> context = getContext();

		put(context, "mbDiscussion", mbDiscussion);

		processTemplate(_tplMBDiscussion, context);
	}

	public void insertMBMessage(MBMessage mbMessage) throws Exception {
		Map<String, Object> context = getContext();

		put(context, "mbMessage", mbMessage);

		processTemplate(_tplMBMessage, context);
	}

	public void insertMBStatsUser(MBStatsUser mbStatsUser) throws Exception {
		Map<String, Object> context = getContext();

		put(context, "mbStatsUser", mbStatsUser);

		processTemplate(_tplMBStatsUser, context);
	}

	public void insertMBThread(MBThread mbThread) throws Exception {
		Map<String, Object> context = getContext();

		put(context, "mbThread", mbThread);

		processTemplate(_tplMBThread, context);
	}

	public void insertSecurity(String name, long primKey) throws Exception {
		insertSecurity(name, String.valueOf(primKey));
	}

	public void insertSecurity(String name, String primKey) throws Exception {
		if (!_generateSecurity) {
			return;
		}

		Map<String, Object> context = getContext();

		Resource resource = _dataFactory.addResource(name, primKey);

		put(context, "resource", resource);

		processTemplate(_tplSecurity, context);
	}

	public void insertTagsAsset(TagsAsset tagsAsset) throws Exception {
		Map<String, Object> context = getContext();

		put(context, "tagsAsset", tagsAsset);

		processTemplate(_tplTagsAsset, context);
	}

	public void insertUser(
			Contact contact, Group group, List<Group> groups,
			List<Organization> organizations, List<Layout> privateLayouts,
			List<Layout> publicLayouts, List<Role> roles, User user)
		throws Exception {

		Map<String, Object> context = getContext();

		put(context, "contact", contact);
		put(context, "group", group);
		put(context, "groups", groups);
		put(context, "organizations", organizations);
		put(context, "privateLayouts", privateLayouts);
		put(context, "publicLayouts", publicLayouts);
		put(context, "roles", roles);
		put(context, "user", user);

		processTemplate(_tplUser, context);
	}

	protected void createSample() throws Exception {
		Map<String, Object> context = getContext();

		Writer blogsEntriesCsvWriter = new FileWriter(
			new File(_outputDir + "/blogs_entries.csv"));
		Writer mbMessagesCsvWriter = new FileWriter(
			new File(_outputDir + "/mb_messages.csv"));
		Writer usersCsvWriter = new FileWriter(
			new File(_outputDir +  "/users.csv"));

		put(context, "blogsEntriesCsvWriter", blogsEntriesCsvWriter);
		put(context, "mbMessagesCsvWriter", mbMessagesCsvWriter);
		put(context, "usersCsvWriter", usersCsvWriter);

		processTemplate(_tplSample, context);

		blogsEntriesCsvWriter.flush();
		mbMessagesCsvWriter.flush();
		usersCsvWriter.flush();
	}

	protected Map<String, Object> getContext() {
		Map<String, Object> context = new HashMap<String, Object>();

		Company company = _dataFactory.getCompany();
		User defaultUser = _dataFactory.getDefaultUser();

		put(context, "companyId", company.getCompanyId());
		put(context, "counter", _counter);
		put(context, "dataFactory", _dataFactory);
		put(context, "defaultUserId", defaultUser.getCompanyId());
		put(context, "maxBlogsEntryCount", _maxBlogsEntryCount);
		put(context, "maxGroupsCount", _maxGroupsCount);
		put(context, "maxMBCategoryCount", _maxMBCategoryCount);
		put(context, "maxMBMessageCount", _maxMBMessageCount);
		put(context, "maxMBThreadCount", _maxMBThreadCount);
		put(context, "maxUserCount", _maxUserCount);
		put(context, "portalUUIDUtil", PortalUUIDUtil.getPortalUUID());
		put(context, "sampleSQLBuilder", this);
		put(context, "stringUtil", StringUtil_IW.getInstance());
		put(context, "userScreenNameIncrementer", _userScreenNameIncrementer);

		return context;
	}

	protected void processTemplate(String name, Map<String, Object> context)
		throws Exception {

		FreeMarkerUtil.process(name, context, _writerGeneric);
	}

	protected void put(Map<String, Object> context, String key, Object value) {
		context.put(key, value);
	}

	private static final String _TPL_ROOT =
		"com/liferay/portal/tools/samplesqlbuilder/dependencies/";

	private SimpleCounter _counter;
	private DataFactory _dataFactory;
	private boolean _generateSecurity;
	private int _maxBlogsEntryCount;
	private int _maxGroupsCount;
	private int _maxMBCategoryCount;
	private int _maxMBMessageCount;
	private int _maxMBThreadCount;
	private int _maxUserCount;
	private String _outputDir;
	private SimpleCounter _permissionCounter;
	private SimpleCounter _resourceCodeCounter;
	private SimpleCounter _resourceCounter;
	private String _tplGroup = _TPL_ROOT + "group.ftl";
	private String _tplBlogsEntry = _TPL_ROOT + "blogs_entry.ftl";
	private String _tplBlogsStatsUser = _TPL_ROOT + "blogs_stats_user.ftl";
	private String _tplMBCategory = _TPL_ROOT + "mb_category.ftl";
	private String _tplMBDiscussion = _TPL_ROOT + "mb_discussion.ftl";
	private String _tplMBMessage = _TPL_ROOT + "mb_message.ftl";
	private String _tplMBStatsUser = _TPL_ROOT + "mb_stats_user.ftl";
	private String _tplMBThread = _TPL_ROOT + "mb_thread.ftl";
	private String _tplSample = _TPL_ROOT + "sample.ftl";
	private String _tplSecurity = _TPL_ROOT + "security.ftl";
	private String _tplTagsAsset = _TPL_ROOT + "tags_asset.ftl";
	private String _tplUser = _TPL_ROOT + "user.ftl";
	private SimpleCounter _userScreenNameIncrementer;
	private Writer _writerGeneric;
	private Writer _writerMySQL;

}