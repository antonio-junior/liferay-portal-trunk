Liferay.Service.register("Liferay.Service.Portal", "com.liferay.portal.service.http");

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Address",
	{
		addAddress: true,
		deleteAddress: true,
		getAddress: true,
		getAddresses: true,
		updateAddress: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "ClassName",
	{
		getClassName: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Company",
	{
		addCompany: true,
		getCompanyById: true,
		getCompanyByLogoId: true,
		getCompanyByMx: true,
		getCompanyByVirtualHost: true,
		getCompanyByWebId: true,
		updateCompany: true,
		updateDisplay: true,
		updateSecurity: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Country",
	{
		addCountry: true,
		getCountries: true,
		getCountry: true,
		getCountryByA2: true,
		getCountryByA3: true,
		getCountryByName: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "EmailAddress",
	{
		addEmailAddress: true,
		deleteEmailAddress: true,
		getEmailAddress: true,
		getEmailAddresses: true,
		updateEmailAddress: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Group",
	{
		addGroup: true,
		addRoleGroups: true,
		deleteGroup: true,
		getGroup: true,
		getManageableGroups: true,
		getOrganizationsGroups: true,
		getUserGroup: true,
		getUserGroupsGroups: true,
		getUserOrganizationsGroups: true,
		hasUserGroup: true,
		search: true,
		searchCount: true,
		setRoleGroups: true,
		unsetRoleGroups: true,
		updateFriendlyURL: true,
		updateGroup: true,
		updateWorkflow: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Layout",
	{
		addLayout: true,
		deleteLayout: true,
		getLayoutName: true,
		getLayoutReferences: true,
		setLayouts: true,
		unschedulePublishToLive: true,
		unschedulePublishToRemote: true,
		updateLayout: true,
		updateLookAndFeel: true,
		updateName: true,
		updateParentLayoutId: true,
		updatePriority: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "LayoutSet",
	{
		updateLookAndFeel: true,
		updateVirtualHost: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "ListType",
	{
		getListType: true,
		getListTypes: true,
		validate: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "MembershipRequest",
	{
		addMembershipRequest: true,
		deleteMembershipRequests: true,
		getMembershipRequest: true,
		updateStatus: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Organization",
	{
		addGroupOrganizations: true,
		addPasswordPolicyOrganizations: true,
		addOrganization: true,
		deleteLogo: true,
		deleteOrganization: true,
		getManageableOrganizations: true,
		getOrganization: true,
		getOrganizationId: true,
		getUserOrganizations: true,
		setGroupOrganizations: true,
		unsetGroupOrganizations: true,
		unsetPasswordPolicyOrganizations: true,
		updateOrganization: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "OrgLabor",
	{
		addOrgLabor: true,
		deleteOrgLabor: true,
		getOrgLabor: true,
		getOrgLabors: true,
		updateOrgLabor: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "PasswordPolicy",
	{
		addPasswordPolicy: true,
		deletePasswordPolicy: true,
		updatePasswordPolicy: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Permission",
	{
		checkPermission: true,
		hasGroupPermission: true,
		hasUserPermission: true,
		hasUserPermissions: true,
		setGroupPermissions: true,
		setOrgGroupPermissions: true,
		setRolePermission: true,
		setRolePermissions: true,
		setUserPermissions: true,
		unsetRolePermission: true,
		unsetRolePermissions: true,
		unsetUserPermissions: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Phone",
	{
		addPhone: true,
		deletePhone: true,
		getPhone: true,
		getPhones: true,
		updatePhone: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Portal",
	{
		getAutoDeployDirectory: true,
		getBuildNumber: true,
		test: true,
		testCounterRollback: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "PluginSetting",
	{
		updatePluginSetting: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Portlet",
	{
		getWARPortlets: true,
		updatePortlet: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "PortletPreferences",
	{
		deleteArchivedPreferences: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Region",
	{
		addRegion: true,
		getRegions: true,
		getRegion: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Resource",
	{
		getResource: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Role",
	{
		addRole: true,
		addUserRoles: true,
		deleteRole: true,
		getGroupRole: true,
		getGroupRoles: true,
		getRole: true,
		getUserGroupRoles: true,
		getUserRelatedRoles: true,
		getUserRoles: true,
		hasUserRole: true,
		hasUserRoles: true,
		unsetUserRoles: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Theme",
	{
		getThemes: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "User",
	{
		addGroupUsers: true,
		addOrganizationUsers: true,
		addPasswordPolicyUsers: true,
		addRoleUsers: true,
		addUserGroupUsers: true,
		addUser: true,
		deletePortrait: true,
		deleteRoleUser: true,
		deleteUser: true,
		getDefaultUserId: true,
		getGroupUserIds: true,
		getOrganizationUserIds: true,
		getRoleUserIds: true,
		getUserByEmailAddress: true,
		getUserById: true,
		getUserByScreenName: true,
		getUserIdByEmailAddress: true,
		getUserIdByScreenName: true,
		hasGroupUser: true,
		hasRoleUser: true,
		setRoleUsers: true,
		setUserGroupUsers: true,
		unsetGroupUsers: true,
		unsetOrganizationUsers: true,
		unsetPasswordPolicyUsers: true,
		unsetRoleUsers: true,
		unsetUserGroupUsers: true,
		updateActive: true,
		updateAgreedToTermsOfUse: true,
		updateEmailAddress: true,
		updateLockout: true,
		updateOpenId: true,
		updateOrganizations: true,
		updatePassword: true,
		updatePortrait: true,
		updateReminderQuery: true,
		updateScreenName: true,
		updateUser: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "UserGroup",
	{
		addGroupUserGroups: true,
		addUserGroup: true,
		deleteUserGroup: true,
		getUserGroup: true,
		getUserUserGroups: true,
		unsetGroupUserGroups: true,
		updateUserGroup: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "UserGroupRole",
	{
		addUserGroupRoles: true,
		deleteUserGroupRoles: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Portal, "Website",
	{
		addWebsite: true,
		deleteWebsite: true,
		getWebsite: true,
		getWebsites: true,
		updateWebsite: true
	}
);

Liferay.Service.register("Liferay.Service.Announcements", "com.liferay.portlet.announcements.service.http");

Liferay.Service.registerClass(
	Liferay.Service.Announcements, "AnnouncementsDelivery",
	{
		updateDelivery: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Announcements, "AnnouncementsEntry",
	{
		addEntry: true,
		deleteEntry: true,
		updateEntry: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Announcements, "AnnouncementsFlag",
	{
		addFlag: true,
		getFlag: true,
		deleteFlag: true
	}
);

Liferay.Service.register("Liferay.Service.Blogs", "com.liferay.portlet.blogs.service.http");

Liferay.Service.registerClass(
	Liferay.Service.Blogs, "BlogsEntry",
	{
		addEntry: true,
		deleteEntry: true,
		getCompanyEntries: true,
		getEntry: true,
		getGroupEntries: true,
		getOrganizationEntries: true,
		updateEntry: true
	}
);

Liferay.Service.register("Liferay.Service.Bookmarks", "com.liferay.portlet.bookmarks.service.http");

Liferay.Service.registerClass(
	Liferay.Service.Bookmarks, "BookmarksEntry",
	{
		addEntry: true,
		deleteEntry: true,
		getEntry: true,
		openEntry: true,
		updateEntry: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Bookmarks, "BookmarksFolder",
	{
		addFolder: true,
		deleteFolder: true,
		getFolder: true,
		updateFolder: true
	}
);

Liferay.Service.register("Liferay.Service.Cal", "com.liferay.portlet.calendar.service.http");

Liferay.Service.registerClass(
	Liferay.Service.Cal, "CalEvent",
	{
		addEvent: true,
		deleteEvent: true,
		getEvent: true,
		updateEvent: true
	}
);

Liferay.Service.register("Liferay.Service.DL", "com.liferay.portlet.documentlibrary.service.http");

Liferay.Service.registerClass(
	Liferay.Service.DL, "DLFileEntry",
	{
		addFileEntry: true,
		deleteFileEntry: true,
		deleteFileEntryByTitle: true,
		getFileEntries: true,
		getFileEntry: true,
		getFileEntryByTitle: true,
		hasFileEntryLock: true,
		lockFileEntry: true,
		refreshFileEntryLock: true,
		unlockFileEntry: true,
		updateFileEntry: true,
		verifyFileEntryLock: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.DL, "DLFileShortcut",
	{
		addFileShortcut: true,
		deleteFileShortcut: true,
		getFileShortcut: true,
		updateFileShortcut: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.DL, "DLFolder",
	{
		addFolder: true,
		copyFolder: true,
		deleteFolder: true,
		getFolder: true,
		getFolderId: true,
		getFolders: true,
		hasInheritableLock: true,
		lockFolder: true,
		refreshFolderLock: true,
		reIndexSearch: true,
		unlockFolder: true,
		updateFolder: true,
		verifyInheritableLock: true
	}
);

Liferay.Service.register("Liferay.Service.Expando", "com.liferay.portlet.expando.service.http");

Liferay.Service.registerClass(
	Liferay.Service.Expando, "ExpandoColumn",
	{
		addColumn: true,
		deleteColumn: true,
		updateColumn: true,
		updateTypeSettings: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Expando, "ExpandoValue",
	{
		addValue: true
	}
);

Liferay.Service.register("Liferay.Service.IG", "com.liferay.portlet.imagegallery.service.http");

Liferay.Service.registerClass(
	Liferay.Service.IG, "IGFolder",
	{
		addFolder: true,
		copyFolder: true,
		deleteFolder: true,
		getFolder: true,
		getFolders: true,
		updateFolder: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.IG, "IGImage",
	{
		deleteImage: true,
		deleteImageByFolderIdAndNameWithExtension: true,
		getImage: true,
		getImageByFolderIdAndNameWithExtension: true,
		getImageByLargeImageId: true,
		getImageBySmallImageId: true,
		getImages: true
	}
);

Liferay.Service.register("Liferay.Service.Journal", "com.liferay.portlet.journal.service.http");

Liferay.Service.registerClass(
	Liferay.Service.Journal, "JournalArticle",
	{
		addArticle: true,
		copyArticle: true,
		getArticle: true,
		removeArticleLocale: true,
		updateContent: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Journal, "JournalFeed",
	{
		addFeed: true,
		deleteFeed: true,
		getFeed: true,
		updateFeed: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Journal, "JournalStructure",
	{
		addStructure: true,
		copyStructure: true,
		deleteStructure: true,
		getStructure: true,
		updateStructure: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Journal, "JournalTemplate",
	{
		copyTemplate: true,
		deleteTemplate: true,
		getStructureTemplates: true,
		getTemplate: true
	}
);

Liferay.Service.register("Liferay.Service.MB", "com.liferay.portlet.messageboards.service.http");

Liferay.Service.registerClass(
	Liferay.Service.MB, "MBBan",
	{
		addBan: true,
		deleteBan: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.MB, "MBCategory",
	{
		addCategory: true,
		deleteCategory: true,
		getCategory: true,
		getCategories: true,
		getCategoriesCount: true,
		subscribeCategory: true,
		unsubscribeCategory: true,
		updateCategory: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.MB, "MBMessage",
	{
		addDiscussionMessage: true,
		addMessage: true,
		deleteDiscussionMessage: true,
		deleteMessage: true,
		getCategoryMessages: true,
		getCategoryMessagesCount: true,
		getMessage: true,
		getMessageDisplay: true,
		subscribeMessage: true,
		unsubscribeMessage: true,
		updateDiscussionMessage: true,
		updateMessage: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.MB, "MBMessageFlag",
	{
		addAnswerFlag: true,
		deleteAnswerFlag: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.MB, "MBThread",
	{
		moveThread: true,
		splitThread: true
	}
);

Liferay.Service.register("Liferay.Service.Polls", "com.liferay.portlet.polls.service.http");

Liferay.Service.registerClass(
	Liferay.Service.Polls, "PollsQuestion",
	{
		addQuestion: true,
		deleteQuestion: true,
		getQuestion: true,
		updateQuestion: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Polls, "PollsVote",
	{
		addVote: true
	}
);

Liferay.Service.register("Liferay.Service.Ratings", "com.liferay.portlet.ratings.service.http");

Liferay.Service.registerClass(
	Liferay.Service.Ratings, "RatingsEntry",
	{
		deleteEntry: true,
		updateEntry: true
	}
);

Liferay.Service.register("Liferay.Service.Shopping", "com.liferay.portlet.shopping.service.http");

Liferay.Service.registerClass(
	Liferay.Service.Shopping, "ShoppingCategory",
	{
		addCategory: true,
		deleteCategory: true,
		getCategory: true,
		updateCategory: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Shopping, "ShoppingCoupon",
	{
		addCoupon: true,
		deleteCoupon: true,
		getCoupon: true,
		search: true,
		updateCoupon: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Shopping, "ShoppingItem",
	{
		addBookItems: true,
		deleteItem: true,
		getItem: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Shopping, "ShoppingOrder",
	{
		completeOrder: true,
		deleteOrder: true,
		getOrder: true,
		sendEmail: true,
		updateOrder: true
	}
);

Liferay.Service.register("Liferay.Service.Social", "com.liferay.portlet.social.service.http");

Liferay.Service.register("Liferay.Service.SC", "com.liferay.portlet.softwarecatalog.service.http");

Liferay.Service.registerClass(
	Liferay.Service.SC, "SCLicense",
	{
		addLicense: true,
		deleteLicense: true,
		getLicense: true,
		updateLicense: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.SC, "SCFrameworkVersion",
	{
		addFrameworkVersion: true,
		deleteFrameworkVersion: true,
		getFrameworkVersion: true,
		getFrameworkVersions: true,
		updateFrameworkVersion: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.SC, "SCProductEntry",
	{
		addProductEntry: true,
		deleteProductEntry: true,
		getProductEntry: true,
		updateProductEntry: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.SC, "SCProductVersion",
	{
		addProductVersion: true,
		deleteProductVersion: true,
		getProductVersion: true,
		getProductVersions: true,
		getProductVersionsCount: true,
		updateProductVersion: true
	}
);

Liferay.Service.register("Liferay.Service.Tags", "com.liferay.portlet.tags.service.http");

Liferay.Service.registerClass(
	Liferay.Service.Tags, "TagsAsset",
	{
		deleteAsset: true,
		getAsset: true,
		getAssets: true,
		getAssetsCount: true,
		getAssetsRSS: true,
		getAssetTypes: true,
		getCompanyAssetDisplays: true,
		getCompanyAssets: true,
		getCompanyAssetsCount: true,
		getCompanyAssetsRSS: true,
		incrementViewCounter: true,
		searchAssetDisplays: true,
		searchAssetDisplaysCount: true,
		updateAsset: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Tags, "TagsEntry",
	{
		addEntry: true,
		deleteEntry: true,
		getEntries: true,
		getEntry: true,
		getGroupVocabularyEntries: true,
		getGroupVocabularyRootEntries: true,
		mergeEntries: true,
		search: true,
		updateEntry: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Tags, "TagsProperty",
	{
		addProperty: true,
		deleteProperty: true,
		getProperties: true,
		getPropertyValues: true,
		updateProperty: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Tags, "TagsVocabulary",
	{
		addVocabulary: true,
		deleteVocabulary: true,
		getCompanyVocabularies: true,
		getGroupVocabularies: true,
		getVocabulary: true,
		updateVocabulary: true
	}
);

Liferay.Service.register("Liferay.Service.Tasks", "com.liferay.portlet.tasks.service.http");

Liferay.Service.registerClass(
	Liferay.Service.Tasks, "TasksReview",
	{
		approveReview: true,
		rejectReview: true,
		updateReviews: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Tasks, "TasksProposal",
	{
		addProposal: true,
		deleteProposal: true,
		updateProposal: true
	}
);

Liferay.Service.register("Liferay.Service.Wiki", "com.liferay.portlet.wiki.service.http");

Liferay.Service.registerClass(
	Liferay.Service.Wiki, "WikiNode",
	{
		addNode: true,
		deleteNode: true,
		getNode: true,
		subscribeNode: true,
		unsubscribeNode: true,
		updateNode: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Wiki, "WikiPage",
	{
		addPage: true,
		addPageAttachments: true,
		changeParent: true,
		deletePage: true,
		deletePageAttachment: true,
		getNodePages: true,
		getNodePagesRSS: true,
		getPage: true,
		getPagesRSS: true,
		movePage: true,
		revertPage: true,
		subscribePage: true,
		unsubscribePage: true,
		updatePage: true
	}
);