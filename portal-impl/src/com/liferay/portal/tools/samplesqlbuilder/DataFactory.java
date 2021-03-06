/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.tools.samplesqlbuilder;

import com.liferay.counter.model.Counter;
import com.liferay.counter.model.impl.CounterModelImpl;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortletConstants;
import com.liferay.portal.model.ModelHintsUtil;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceCode;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.ClassNameImpl;
import com.liferay.portal.model.impl.CompanyImpl;
import com.liferay.portal.model.impl.ContactImpl;
import com.liferay.portal.model.impl.GroupImpl;
import com.liferay.portal.model.impl.LayoutImpl;
import com.liferay.portal.model.impl.PermissionImpl;
import com.liferay.portal.model.impl.ResourceCodeImpl;
import com.liferay.portal.model.impl.ResourceImpl;
import com.liferay.portal.model.impl.RoleImpl;
import com.liferay.portal.model.impl.UserImpl;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.impl.AssetEntryImpl;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.model.BlogsStatsUser;
import com.liferay.portlet.blogs.model.impl.BlogsEntryImpl;
import com.liferay.portlet.blogs.model.impl.BlogsStatsUserImpl;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.model.MBDiscussion;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBStatsUser;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.model.impl.MBCategoryImpl;
import com.liferay.portlet.messageboards.model.impl.MBDiscussionImpl;
import com.liferay.portlet.messageboards.model.impl.MBMessageImpl;
import com.liferay.portlet.messageboards.model.impl.MBStatsUserImpl;
import com.liferay.portlet.messageboards.model.impl.MBThreadImpl;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.impl.WikiNodeImpl;
import com.liferay.portlet.wiki.model.impl.WikiPageImpl;
import com.liferay.util.SimpleCounter;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class DataFactory {

	public DataFactory(
		String baseDir, int maxGroupsCount, int maxUserToGroupCount,
		SimpleCounter counter, SimpleCounter permissionCounter,
		SimpleCounter resourceCounter, SimpleCounter resourceCodeCounter) {

		try {
			_baseDir = baseDir;
			_maxGroupsCount = maxGroupsCount;
			_maxUserToGroupCount = maxUserToGroupCount;

			_counter = counter;
			_permissionCounter = permissionCounter;
			_resourceCounter = resourceCounter;
			_resourceCodeCounter = resourceCodeCounter;

			initClassNames();
			initCompany();
			initDefaultUser();
			initGroups();
			initResourceCodes();
			initRoles();
			initUserNames();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AssetEntry addAssetEntry(
			long groupId, long userId, long classNameId, long classPK,
			String mimeType, String title)
		throws Exception {

		AssetEntry assetEntry = new AssetEntryImpl();

		assetEntry.setGroupId(groupId);
		assetEntry.setUserId(userId);
		assetEntry.setClassNameId(classNameId);
		assetEntry.setClassPK(classPK);
		assetEntry.setMimeType(mimeType);
		assetEntry.setTitle(title);

		return assetEntry;
	}

	public BlogsEntry addBlogsEntry(
			long groupId, long userId, String title, String urlTitle,
			String content)
		throws Exception {

		BlogsEntry blogsEntry = new BlogsEntryImpl();

		blogsEntry.setEntryId(_counter.get());
		blogsEntry.setGroupId(groupId);
		blogsEntry.setUserId(userId);
		blogsEntry.setTitle(title);
		blogsEntry.setUrlTitle(urlTitle);
		blogsEntry.setContent(content);

		return blogsEntry;
	}

	public BlogsStatsUser addBlogsStatsUser(long groupId, long userId)
		throws Exception {

		BlogsStatsUser blogsStatsUser = new BlogsStatsUserImpl();

		blogsStatsUser.setGroupId(groupId);
		blogsStatsUser.setUserId(userId);

		return blogsStatsUser;
	}

	public Contact addContact(String firstName, String lastName)
		throws Exception {

		Contact contact = new ContactImpl();

		contact.setContactId(_counter.get());
		contact.setAccountId(_company.getAccountId());
		contact.setFirstName(firstName);
		contact.setLastName(lastName);

		return contact;
	}

	public Group addGroup(
			long groupId, long classNameId, long classPK, String name,
			String friendlyURL)
		throws Exception {

		Group group = new GroupImpl();

		group.setGroupId(groupId);
		group.setClassNameId(classNameId);
		group.setClassPK(classPK);
		group.setName(name);
		group.setFriendlyURL(friendlyURL);

		return group;
	}

	public Layout addLayout(
			int layoutId, String name, String friendlyURL, String column1,
			String column2)
		throws Exception {

		Layout layout = new LayoutImpl();

		layout.setPlid(_counter.get());
		layout.setPrivateLayout(false);
		layout.setLayoutId(layoutId);
		layout.setName(name);
		layout.setFriendlyURL(friendlyURL);

		UnicodeProperties typeSettingsProperties = new UnicodeProperties(true);

		typeSettingsProperties.setProperty(
			LayoutTypePortletConstants.LAYOUT_TEMPLATE_ID, "2_columns_ii");
		typeSettingsProperties.setProperty("column-1", column1);
		typeSettingsProperties.setProperty("column-2", column2);

		String typeSettings = StringUtil.replace(
			typeSettingsProperties.toString(), "\n", "\\n");

		layout.setTypeSettings(typeSettings);

		return layout;
	}

	public MBCategory addMBCategory(
			long categoryId, long groupId, long companyId, long userId,
			String name, String description, int threadCount, int messageCount)
		throws Exception {

		MBCategory mbCategory = new MBCategoryImpl();

		mbCategory.setCategoryId(categoryId);
		mbCategory.setGroupId(groupId);
		mbCategory.setCompanyId(companyId);
		mbCategory.setUserId(userId);
		mbCategory.setName(name);
		mbCategory.setDescription(description);
		mbCategory.setDisplayStyle(MBCategoryConstants.DEFAULT_DISPLAY_STYLE);
		mbCategory.setThreadCount(threadCount);
		mbCategory.setMessageCount(messageCount);

		return mbCategory;
	}

	public MBDiscussion addMBDiscussion(
			long classNameId, long classPK, long threadId)
		throws Exception {

		MBDiscussion mbDiscussion = new MBDiscussionImpl();

		mbDiscussion.setDiscussionId(_counter.get());
		mbDiscussion.setClassNameId(classNameId);
		mbDiscussion.setClassPK(classPK);
		mbDiscussion.setThreadId(threadId);

		return mbDiscussion;
	}

	public MBMessage addMBMessage(
			long messageId, long groupId, long userId, long classNameId,
			long classPK, long categoryId, long threadId, long rootMessageId,
			long parentMessageId, String subject, String body)
		throws Exception {

		MBMessage mbMessage = new MBMessageImpl();

		mbMessage.setMessageId(messageId);
		mbMessage.setGroupId(groupId);
		mbMessage.setUserId(userId);
		mbMessage.setClassNameId(classNameId);
		mbMessage.setClassPK(classPK);
		mbMessage.setCategoryId(categoryId);
		mbMessage.setThreadId(threadId);
		mbMessage.setRootMessageId(rootMessageId);
		mbMessage.setParentMessageId(parentMessageId);
		mbMessage.setSubject(subject);
		mbMessage.setBody(body);

		return mbMessage;
	}

	public MBStatsUser addMBStatsUser(long groupId, long userId)
		throws Exception {

		MBStatsUser mbStatsUser = new MBStatsUserImpl();

		mbStatsUser.setGroupId(groupId);
		mbStatsUser.setUserId(userId);

		return mbStatsUser;
	}

	public MBThread addMBThread(
			long threadId, long groupId, long companyId, long categoryId,
			long rootMessageId, int messageCount, long lastPostByUserId)
		throws Exception {

		MBThread mbThread = new MBThreadImpl();

		mbThread.setThreadId(threadId);
		mbThread.setGroupId(groupId);
		mbThread.setCompanyId(companyId);
		mbThread.setCategoryId(categoryId);
		mbThread.setRootMessageId(rootMessageId);
		mbThread.setRootMessageUserId(lastPostByUserId);
		mbThread.setMessageCount(messageCount);
		mbThread.setLastPostByUserId(lastPostByUserId);

		return mbThread;
	}

	public List<Permission> addPermissions(Resource resource) throws Exception {
		List<Permission> permissions = new ArrayList<Permission>();

		String name = _individualResourceNames.get(resource.getCodeId());

		List<String> actions = ResourceActionsUtil.getModelResourceActions(
			name);

		for (String action : actions) {
			Permission permission = new PermissionImpl();

			permission.setPermissionId(_permissionCounter.get());
			permission.setCompanyId(_company.getCompanyId());
			permission.setActionId(action);
			permission.setResourceId(resource.getResourceId());

			permissions.add(permission);
		}

		return permissions;
	}

	public Resource addResource(String name, String primKey) throws Exception {
		Long codeId = _individualResourceCodeIds.get(name);

		Resource resource = new ResourceImpl();

		resource.setResourceId(_resourceCounter.get());
		resource.setCodeId(codeId);
		resource.setPrimKey(primKey);

		return resource;
	}

	public List<KeyValuePair> addRolesPermissions(
			Resource resource, List<Permission> permissions, Role memberRole)
		throws Exception {

		List<KeyValuePair> rolesPermissions = new ArrayList<KeyValuePair>();

		for (Permission permission : permissions) {
			KeyValuePair kvp = new KeyValuePair();

			kvp.setKey(String.valueOf(_ownerRole.getRoleId()));
			kvp.setValue(String.valueOf(permission.getPermissionId()));

			rolesPermissions.add(kvp);
		}

		String name = _individualResourceNames.get(resource.getCodeId());

		if (memberRole != null) {
			List<String> communityDefaultactions =
				ResourceActionsUtil.getModelResourceCommunityDefaultActions(
					name);

			for (Permission permission : permissions) {
				if (!communityDefaultactions.contains(
						permission.getActionId())) {

					continue;
				}

				KeyValuePair kvp = new KeyValuePair();

				kvp.setKey(String.valueOf(memberRole.getRoleId()));
				kvp.setValue(String.valueOf(permission.getPermissionId()));

				rolesPermissions.add(kvp);
			}
		}

		List<String> guestDefaultactions =
			ResourceActionsUtil.getModelResourceGuestDefaultActions(name);

		for (Permission permission : permissions) {
			if (!guestDefaultactions.contains(permission.getActionId())) {
				continue;
			}

			KeyValuePair kvp = new KeyValuePair();

			kvp.setKey(String.valueOf(_guestRole.getRoleId()));
			kvp.setValue(String.valueOf(permission.getPermissionId()));

			rolesPermissions.add(kvp);
		}

		return rolesPermissions;
	}

	public User addUser(boolean defaultUser, String screenName)
		throws Exception {

		User user = new UserImpl();

		user.setUserId(_counter.get());
		user.setDefaultUser(defaultUser);

		if (Validator.isNull(screenName)) {
			screenName = String.valueOf(user.getUserId());
		}

		user.setScreenName(screenName);

		String emailAddress = screenName + "@liferay.com";

		user.setEmailAddress(emailAddress);

		return user;
	}

	public List<Long> addUserToGroupIds(long groupId) {
		List<Long> groupIds = new ArrayList<Long>(_maxUserToGroupCount + 1);

		groupIds.add(_guestGroup.getGroupId());

		if ((groupId + _maxUserToGroupCount) > _maxGroupsCount) {
			groupId = groupId - _maxUserToGroupCount + 1;
		}

		for (int i = 0; i < _maxUserToGroupCount; i++) {
			groupIds.add(groupId + i);
		}

		return groupIds;
	}

	public WikiNode addWikiNode(
			long groupId, long userId, String name, String description)
		throws Exception {

		WikiNode wikiNode = new WikiNodeImpl();

		wikiNode.setNodeId(_counter.get());
		wikiNode.setGroupId(groupId);
		wikiNode.setUserId(userId);
		wikiNode.setName(name);
		wikiNode.setDescription(description);

		return wikiNode;
	}

	public WikiPage addWikiPage(
			long groupId, long userId, long nodeId, String title,
			double version, String content, boolean head)
		throws Exception {

		WikiPage wikiPage = new WikiPageImpl();

		wikiPage.setPageId(_counter.get());
		wikiPage.setResourcePrimKey(_counter.get());
		wikiPage.setGroupId(groupId);
		wikiPage.setUserId(userId);
		wikiPage.setNodeId(nodeId);
		wikiPage.setTitle(title);
		wikiPage.setVersion(version);
		wikiPage.setContent(content);
		wikiPage.setHead(head);

		return wikiPage;
	}

	public Role getAdministratorRole() {
		return _administratorRole;
	}

	public ClassName getBlogsEntryClassName() {
		return _blogsEntryClassName;
	}

	public List<ClassName> getClassNames() {
		return _classNames;
	}

	public Role getCommunityAdministratorRole() {
		return _communityAdministratorRole;
	}

	public Role getCommunityMemberRole() {
		return _communityMemberRole;
	}

	public Role getCommunityOwnerRole() {
		return _communityOwnerRole;
	}

	public Company getCompany() {
		return _company;
	}

	public List<CounterModelImpl> getCounters() {
		return _counters;
	}

	public User getDefaultUser() {
		return _defaultUser;
	}

	public ClassName getGroupClassName() {
		return _groupClassName;
	}

	public List<Group> getGroups() {
		return _groups;
	}

	public Group getGuestGroup() {
		return _guestGroup;
	}

	public Role getGuestRole() {
		return _guestRole;
	}

	public Role getOrganizationAdministratorRole() {
		return _organizationAdministratorRole;
	}

	public Role getOrganizationMemberRole() {
		return _organizationMemberRole;
	}

	public Role getOrganizationOwnerRole() {
		return _organizationOwnerRole;
	}

	public Role getPowerUserRole() {
		return _powerUserRole;
	}

	public List<ResourceCode> getResourceCodes() {
		return _resourceCodes;
	}

	public ClassName getRoleClassName() {
		return _roleClassName;
	}

	public List<Role> getRoles() {
		return _roles;
	}

	public ClassName getUserClassName() {
		return _userClassName;
	}

	public Object[] getUserNames() {
		return _userNames;
	}

	public Role getUserRole() {
		return _userRole;
	}

	public ClassName getWikiPageClassName() {
		return _wikiPageClassName;
	}

	public void initClassNames() throws Exception {
		if (_classNames != null) {
			return;
		}

		_classNames = new ArrayList<ClassName>();

		List<String> models = ModelHintsUtil.getModels();

		for (String model : models) {
			ClassName className = new ClassNameImpl();

			className.setClassNameId(_counter.get());
			className.setValue(model);

			_classNames.add(className);

			if (model.equals(BlogsEntry.class.getName())) {
				_blogsEntryClassName = className;
			}
			if (model.equals(Group.class.getName())) {
				_groupClassName = className;
			}
			else if (model.equals(Role.class.getName())) {
				_roleClassName = className;
			}
			else if (model.equals(User.class.getName())) {
				_userClassName = className;
			}
			else if (model.equals(WikiPage.class.getName())) {
				_wikiPageClassName = className;
			}
		}
	}

	public void initCompany() throws Exception {
		_company = new CompanyImpl();

		_company.setCompanyId(_counter.get());
		_company.setAccountId(_counter.get());
	}

	public void initCounters() throws Exception {
		if (_counters != null) {
			return;
		}

		_counters = new ArrayList<CounterModelImpl>();

		// Counter

		CounterModelImpl counter = new CounterModelImpl();

		counter.setName(Counter.class.getName());
		counter.setCurrentId(_counter.get());

		_counters.add(counter);

		// Permission

		counter = new CounterModelImpl();

		counter.setName(Permission.class.getName());
		counter.setCurrentId(_permissionCounter.get());

		_counters.add(counter);

		// Resource

		counter = new CounterModelImpl();

		counter.setName(Resource.class.getName());
		counter.setCurrentId(_resourceCounter.get());

		_counters.add(counter);

		// ResourceCode

		counter = new CounterModelImpl();

		counter.setName(ResourceCode.class.getName());
		counter.setCurrentId(_resourceCodeCounter.get());

		_counters.add(counter);
	}

	public void initDefaultUser() throws Exception {
		_defaultUser = new UserImpl();

		_defaultUser.setUserId(_counter.get());
	}

	public void initGroups() throws Exception {
		if (_groups != null) {
			return;
		}

		_groups = new ArrayList<Group>();

		// Guest

		Group group = new GroupImpl();

		group.setGroupId(_counter.get());
		group.setClassNameId(_groupClassName.getClassNameId());
		group.setClassPK(group.getGroupId());
		group.setName(GroupConstants.GUEST);
		group.setFriendlyURL("/guest");

		_groups.add(group);

		_guestGroup = group;
	}

	public void initResourceCodes() throws Exception {
		if (_resourceCodes != null) {
			return;
		}

		_resourceCodes = new ArrayList<ResourceCode>();

		_individualResourceCodeIds = new HashMap<String, Long>();
		_individualResourceNames = new HashMap<Long, String>();

		List<String> models = ModelHintsUtil.getModels();

		for (String model : models) {
			initResourceCodes(model);
		}

		Document document = SAXReaderUtil.read(
			new File(
				_baseDir, "../portal-web/docroot/WEB-INF/portlet-custom.xml"),
			false);

		Element rootElement = document.getRootElement();

		List<Element> portletElements = rootElement.elements("portlet");

		for (Element portletElement : portletElements) {
			String portletName = portletElement.elementText("portlet-name");

			initResourceCodes(portletName);
		}
	}

	public void initResourceCodes(String name) throws Exception {

		// Company

		ResourceCode resourceCode = newResourceCode();

		resourceCode.setName(name);
		resourceCode.setScope(ResourceConstants.SCOPE_COMPANY);

		_resourceCodes.add(resourceCode);

		// Group

		resourceCode = newResourceCode();

		resourceCode.setName(name);
		resourceCode.setScope(ResourceConstants.SCOPE_GROUP);

		_resourceCodes.add(resourceCode);

		// Group template

		resourceCode = newResourceCode();

		resourceCode.setName(name);
		resourceCode.setScope(ResourceConstants.SCOPE_GROUP_TEMPLATE);

		_resourceCodes.add(resourceCode);

		// Individual

		resourceCode = newResourceCode();

		resourceCode.setName(name);
		resourceCode.setScope(ResourceConstants.SCOPE_INDIVIDUAL);

		_resourceCodes.add(resourceCode);

		_individualResourceCodeIds.put(name, resourceCode.getCodeId());
		_individualResourceNames.put(resourceCode.getCodeId(), name);
	}

	public void initRoles() throws Exception {
		if (_roles != null) {
			return;
		}

		_roles = new ArrayList<Role>();

		// Administrator

		Role role = newRole();

		role.setName(RoleConstants.ADMINISTRATOR);
		role.setType(RoleConstants.TYPE_REGULAR);

		_roles.add(role);

		_administratorRole = role;

		// Community Administrator

		role = newRole();

		role.setName(RoleConstants.COMMUNITY_ADMINISTRATOR);
		role.setType(RoleConstants.TYPE_COMMUNITY);

		_roles.add(role);

		_communityAdministratorRole = role;

		// Community Member

		role = newRole();

		role.setName(RoleConstants.COMMUNITY_MEMBER);
		role.setType(RoleConstants.TYPE_COMMUNITY);

		_roles.add(role);

		_communityMemberRole = role;

		// Community Owner

		role = newRole();

		role.setName(RoleConstants.COMMUNITY_OWNER);
		role.setType(RoleConstants.TYPE_COMMUNITY);

		_roles.add(role);

		_communityOwnerRole = role;

		// Guest

		role = newRole();

		role.setName(RoleConstants.GUEST);
		role.setType(RoleConstants.TYPE_REGULAR);

		_roles.add(role);

		_guestRole = role;

		// Organization Administrator

		role = newRole();

		role.setName(RoleConstants.ORGANIZATION_ADMINISTRATOR);
		role.setType(RoleConstants.TYPE_ORGANIZATION);

		_roles.add(role);

		_communityAdministratorRole = role;

		// Organization Member

		role = newRole();

		role.setName(RoleConstants.ORGANIZATION_MEMBER);
		role.setType(RoleConstants.TYPE_ORGANIZATION);

		_roles.add(role);

		_communityMemberRole = role;

		// Organization Owner

		role = newRole();

		role.setName(RoleConstants.ORGANIZATION_OWNER);
		role.setType(RoleConstants.TYPE_ORGANIZATION);

		_roles.add(role);

		_communityOwnerRole = role;

		// Owner

		role = newRole();

		role.setName(RoleConstants.OWNER);
		role.setType(RoleConstants.TYPE_REGULAR);

		_roles.add(role);

		_ownerRole = role;

		// Power User

		role = newRole();

		role.setName(RoleConstants.POWER_USER);
		role.setType(RoleConstants.TYPE_REGULAR);

		_roles.add(role);

		_powerUserRole = role;

		// User

		role = newRole();

		role.setName(RoleConstants.USER);
		role.setType(RoleConstants.TYPE_REGULAR);

		_roles.add(role);

		_userRole = role;
	}

	public void initUserNames() throws Exception {
		if (_userNames != null) {
			return;
		}

		_userNames = new Object[2];

		String dependenciesDir =
			"../portal-impl/src/com/liferay/portal/tools/samplesqlbuilder/" +
				"dependencies/";

		List<String> firstNames = ListUtil.fromFile(
			new File(_baseDir, dependenciesDir + "first_names.txt"));
		List<String> lastNames = ListUtil.fromFile(
			new File(_baseDir, dependenciesDir + "last_names.txt"));

		_userNames[0] = firstNames;
		_userNames[1] = lastNames;
	}

	public IntegerWrapper newInteger() {
		return new IntegerWrapper();
	}

	protected ResourceCode newResourceCode() {
		ResourceCode resourceCode = new ResourceCodeImpl();

		resourceCode.setCodeId(_resourceCodeCounter.get());

		return resourceCode;
	}

	protected Role newRole() {
		Role role = new RoleImpl();

		role.setRoleId(_counter.get());
		role.setClassNameId(_roleClassName.getClassNameId());
		role.setClassPK(role.getRoleId());

		return role;
	}

	private Role _administratorRole;
	private String _baseDir;
	private ClassName _blogsEntryClassName;
	private List<ClassName> _classNames;
	private Role _communityAdministratorRole;
	private Role _communityMemberRole;
	private Role _communityOwnerRole;
	private Company _company;
	private SimpleCounter _counter;
	private List<CounterModelImpl> _counters;
	private User _defaultUser;
	private ClassName _groupClassName;
	private List<Group> _groups;
	private Group _guestGroup;
	private Role _guestRole;
	private Map<String, Long> _individualResourceCodeIds;
	private Map<Long, String> _individualResourceNames;
	private int _maxGroupsCount;
	private int _maxUserToGroupCount;
	private Role _organizationAdministratorRole;
	private Role _organizationMemberRole;
	private Role _organizationOwnerRole;
	private Role _ownerRole;
	private SimpleCounter _permissionCounter;
	private Role _powerUserRole;
	private SimpleCounter _resourceCodeCounter;
	private List<ResourceCode> _resourceCodes;
	private SimpleCounter _resourceCounter;
	private ClassName _roleClassName;
	private List<Role> _roles;
	private ClassName _userClassName;
	private Object[] _userNames;
	private Role _userRole;
	private ClassName _wikiPageClassName;

}