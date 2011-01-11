AUI().add(
	'liferay-category-admin',
	function(A) {
		var CATEGORY = 0;

		var CSS_VOCABULARY_EDIT_CATEGORY = 'vocabulary-content-edit-category';

		var JSON = A.JSON;

		var EXC_NO_SUCH_VOCABULARY = 'NoSuchVocabularyException';

		var EXC_PRINCIPAL = 'auth.PrincipalException';

		var EXC_VOCABULARY_NAME = 'VocabularyNameException';

		var MSG_TYPE_ERROR = 'error';

		var MSG_TYPE_SUCCESS = 'success';

		var Node = A.Node;

		var VOCABULARY = 1;

		var AssetCategoryAdmin = A.Component.create(
			{
				NAME: 'assetcategoryadmin',

				EXTENDS: A.Base,

				constructor: function() {
					AssetCategoryAdmin.superclass.constructor.apply(this, arguments);
				},

				prototype: {
					initializer: function(config) {
						var instance = this;

						var childrenContainer = A.one(instance._categoryContainerSelector);

						instance.portletId = config.portletId;
						instance._prefixedPortletId = ['_', config.portletId, '_'].join('');
						instance._container = A.one('.vocabulary-container');
						instance._vocabularyContent = A.one( '.vocabulary-content' );

						var categoryRenderURL = instance._createRenderURL(CATEGORY);
						var vocabularyRenderURL = instance._createRenderURL(VOCABULARY);

						A.all('.vocabulary-close').on(
							'click',
							function() {
								instance._closeEditSection();
							}
						);

						A.all('.vocabulary-save-category-properties').on(
							'click',
							function() {
								instance._saveCategoryProperties();
							}
						);

						instance._portletMessageContainer = Node.create('<div class="aui-helper-hidden lfr-message-response" id="vocabulary-messages" />');
						instance._categoryMessageContainer = Node.create('<div class="aui-helper-hidden lfr-message-response" id="vocabulary-category-messages" />');

						instance._container.placeBefore(instance._portletMessageContainer);
						childrenContainer.placeBefore(instance._categoryMessageContainer);

						instance._toolbarCategoryPanel = new A.OverlayContextPanel(
							{
								after: {
									hide: A.bind(instance._hideFloatingPanels, instance)
								},
								align: {
									points: ['tr', 'br']
								},
								hideOnDocumentClick: false,
								trigger: '.add-category-button',
								zIndex: 1000
							}
						).render();

						instance._toolbarCategoryPanel.plug(
							A.Plugin.IO,
							{
								uri: categoryRenderURL.toString(),
								after: {
									success: A.bind(instance._initializeCategoryPanel, instance)
								}
							}
						);

						instance._bindCloseEvent(instance._toolbarCategoryPanel);

						instance._toolbarCategoryPanel.after(
							'visibleChange',
							function(event) {
								if (event.newVal) {
									instance._feedVocabularySelect(instance._vocabularies, instance._selectedVocabularyId);
									instance._showToolBarCategorySection();
								}
								else {
									instance._categoryForm.reset();
								}
							}
						);

						instance._vocabularyCategoryPanel = new A.OverlayContextPanel(
							{
								after: {
									hide: A.bind(instance._hideFloatingPanels, instance)
								},
								align: {
									points: ['tr', 'br']
								},
								hideOnDocumentClick: false,
								trigger: '.add-vocabulary-button',
								zIndex: 1000
							}
						).render();

						instance._bindCloseEvent(instance._vocabularyCategoryPanel);

						instance._vocabularyCategoryPanel.plug(
							A.Plugin.IO,
							{
								uri: vocabularyRenderURL.toString(),
								after: {
									success: A.bind(instance._initializeVocabularyCategoryPanel, instance)
								}
							}
						);

						instance._vocabularyCategoryPanel.after(
							'visibleChange',
							function(event) {
								if (event.newVal) {
									instance._showToolBarVocabularySection();
								}
								else {
									instance._vocabularyForm.reset();									
								}
							}
						);

						A.one('.permissions-categories-button').on(
							'click',
							function() {
								var categoryName = instance._selectedCategoryName;
								var categoryId = instance._selectedCategoryId;

								if (categoryName && categoryId) {
									var portletURL = instance._createPermissionURL(
										'com.liferay.portlet.asset.model.AssetCategory',
										categoryName,
										categoryId
									);

									submitForm(document.hrefFm, portletURL.toString());
								}
								else {
									instance._showToolBarCategorySection();
								}
							}
						);

						A.one('.permissions-vocabulary-button').on(
							'click',
							function() {
								var vocabularyName = instance._selectedVocabularyName;
								var vocabularyId = instance._selectedVocabularyId;

								if (vocabularyName && vocabularyId) {
									var portletURL = instance._createPermissionURL(
										'com.liferay.portlet.asset.model.AssetVocabulary',
										vocabularyName,
										vocabularyId
									);

									submitForm(document.hrefFm, portletURL.toString());
								}
								else {
									instance._showToolBarVocabularySection();
								}
							}
						);

						A.one('#vocabulary-select-search').on(
							'change',
							function(event) {
								var searchInput = A.one('#vocabulary-search-input');

								if (searchInput) {
									searchInput.focus();
								}

								instance._reloadSearch();
							}
						);

					   	A.one('input.vocabulary-delete-categories-button').on(
							'click',
							function() {
								if (confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-this-category'))) {
									instance._deleteCategory(
										instance._selectedCategoryId,
										function(message) {
											var errorKey;
											var exception = message.exception;

											if (!exception) {
												instance._closeEditSection();
												instance._hideToolbarSections();
												instance._displayVocabularyCategories(instance._selectedVocabularyId);
											}
											else {
												if (exception.indexOf(EXC_PRINCIPAL) > -1) {
													errorKey = Liferay.Language.get('you-do-not-have-permission-to-access-the-requested-resource');
												}
												else {
													errorKey = Liferay.Language.get('your-request-failed-to-complete');
												}

												instance._sendMessage(MSG_TYPE_ERROR, errorKey);
											}
										}
									);
								}
							}
						);

						A.one('input.vocabulary-delete-list-button').on(
							'click',
							function() {
								if (confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-this-list'))) {
									instance._deleteVocabulary(
										instance._selectedVocabularyId,
										function(message) {
											var errorKey;
											var exception = message.exception;

											if (!exception) {
												instance._closeEditSection();
												instance._hideToolbarSections();
												instance._loadData();
											}
											else {
												if (exception.indexOf(EXC_PRINCIPAL) > -1) {
													errorKey = Liferay.Language.get('you-do-not-have-permission-to-access-the-requested-resource');
												}
												else {
													errorKey = Liferay.Language.get('your-request-failed-to-complete');
												}

												instance._sendMessage(MSG_TYPE_ERROR, errorKey);
											}
										}
									);
								}
							}
						);

						A.all('.close-panel').on(
							'click',
							function() {
								instance._hideToolbarSections();
							}
						);

						A.all('.aui-overlay input[type=text]').on(
							'keyup',
							function(event) {
								var ESC_KEY_CODE = 27;
								var keyCode = event.keyCode;

								if (keyCode == ESC_KEY_CODE) {
									instance._hideToolbarSections();
								}
							}
						);

						instance._hideMessageTask = new A.DelayedTask(
							function() {
								instance._portletMessageContainer.hide();

								instance._toolbarCategoryPanel.refreshAlign();
								instance._vocabularyCategoryPanel.refreshAlign();
							}
						);

						instance._loadData();

						instance.after('drop:hit', instance._afterDragDrop);
						instance.after('drop:enter', instance._afterDragEnter);
						instance.after('drop:exit', instance._afterDragExit);
					},

					_addCategory: function(vocabularyId) {
						var instance = this;

						var categoryForm = instance._categoryForm;
						var uri = categoryForm.attr('action');

						var config = {
							form: categoryForm.getDOM(),
							dataType: 'json',
							on: {
								success: function(event, id, obj) {
									var response = this.get('responseData');

									instance._onCategoryAddSuccess(response);
								},
								failure: function(event, id, obj) {
									instance._onCategoryAddFailure(obj);
								}
							}
						};

						A.io.request(uri, config);
					},

					_addCategoryProperty: function(baseNode, key, value) {
						var instance = this;

						var baseCategoryProperty = A.one('div.vocabulary-property-row');

						if (baseCategoryProperty) {
							var newCategoryProperty = baseCategoryProperty.clone();

							var propertyKeyNode = newCategoryProperty.one('.category-property-key');
							var propertyValueNode = newCategoryProperty.one('.category-property-value');

							if (propertyKeyNode) {
								propertyKeyNode.val(key);
							}

							if (propertyValueNode) {
								propertyValueNode.val(value);
							}

							baseNode.placeAfter(newCategoryProperty);

							newCategoryProperty.show();

							if (!key && !value) {
								newCategoryProperty.one('input').focus();
							}

							instance._attachCategoryPropertyIconEvents(newCategoryProperty);
						}
					},

					_addVocabulary: function() {
						var instance = this;

						var vocabularyForm = instance._vocabularyForm;
						var uri = vocabularyForm.attr('action');

						var config = {
							form: vocabularyForm.getDOM(),
							dataType: 'json',
							on: {
								success: function(event, id, obj) {
									var response = this.get('responseData');

									instance._onVocabularyAddSuccess(response);
								},
								failure: function(event, id, obj) {
									instance._onVocabularyAddFailure(obj);
								}
							}
						};

						A.io.request(uri, config);
					},

					_afterDragDrop: function(event) {
						var instance = this;

						var dragNode = event.drag.get('node');
						var dropNode = event.drop.get('node');

						var node = A.Widget.getByNode(dragNode);

						var vocabularyId = dropNode.attr('data-vocabularyid');
						var fromCategoryId = instance._getCategoryId(node);
						var fromCategoryName = instance._getCategoryName(node);

						instance._merge(fromCategoryId, fromCategoryName, 0, vocabularyId);

						instance._selectVocabulary(vocabularyId);

						dropNode.removeClass('active-area');
					},

					_afterDragEnter: function(event) {
						var instance = this;

						var dropNode = event.drop.get('node');

						dropNode.addClass('active-area');
					},

					_afterDragExit: function(event) {
						var instance = this;

						var dropNode = event.target.get('node');

						dropNode.removeClass('active-area');
					},

					_alternateRows: function() {
						var instance = this;

						var categoriesScope = A.all(instance._categoryContainerSelector);

						var allRows = categoriesScope.all('li');

						allRows.removeClass('alt');
						allRows.odd().addClass('alt');
					},

					_attachCategoryPropertyIconEvents: function(categoryProperty) {
						var instance = this;

						var addProperty = categoryProperty.one('.add-category-property');
						var deleteProperty = categoryProperty.one('.delete-category-property');

						if (addProperty) {
							addProperty.on(
								'click',
								function() {
									instance._addCategoryProperty(categoryProperty, '', '');
								}
							);
						}

						if (deleteProperty) {
							deleteProperty.on(
								'click',
								function() {
									instance._removeCategoryProperty(categoryProperty);
								}
							);
						}
					},

					_bindCloseEvent: function(contextPanel) {
						var instance = this;

						A.on(
							'key',
							function(event) {
								contextPanel.hide();
							},
							[contextPanel.get('boundingBox')],
							'down:27'
						);
					},

					_buildCategoryTreeview: function(categories, parentCategoryId) {
						var instance = this;

						var children = instance._filterCategory(categories, parentCategoryId);

						A.each(
							children,
							function(item, index, collection) {
								var categoryId = item.categoryId;
								var hasChild = instance._filterCategory(categories, categoryId).length;

								var node = new A.TreeNode(
									{
										alwaysShowHitArea: false,
										id: 'categoryNode' + item.categoryId,
										label: item.name,
										leaf: false,
										on: {
											select: function(event) {
												var nodeId = event.target.get('id');
												var categoryId = nodeId.replace('categoryNode', '');
												var editContainer = A.one('.vocabulary-edit');

												instance._selectCategory(categoryId);
												instance._vocabularyContent.addClass(CSS_VOCABULARY_EDIT_CATEGORY);
												instance._showSection(editContainer);
											}
										}
									}
								);

								var parentId = 'categoryNode' + parentCategoryId;
								var parentNode = instance.treeView.getNodeById(parentId) || instance.treeView;

								parentNode.appendChild(node);

								if (hasChild) {
									instance._buildCategoryTreeview(categories, categoryId);
								}
							}
						);

						return children.length;
					},

					_buildCategoryProperties: function() {
						var instance = this;

						var buffer = [];

						A.all('.vocabulary-property-row').each(
							function(item, index, collection) {
								if (!item.hasClass('aui-helper-hidden')) {
									var keyNode = item.one('input.category-property-key');
									var valueNode = item.one('input.category-property-value');

									if (keyNode && valueNode) {
										var rowValue = [keyNode.val(), ':', valueNode.val(), ','].join('');

										buffer.push(rowValue);
									}
								}
							}
						);

						return buffer.join('');
					},

					_closeEditSection: function() {
						var instance = this;

						instance._hideSection('.vocabulary-edit');

						instance._vocabularyContent.removeClass(CSS_VOCABULARY_EDIT_CATEGORY);
					},

					_createPermissionURL: function(modelResource, modelResourceDescription, resourcePrimKey) {
						var instance = this;

						var portletURL = Liferay.PortletURL.createPermissionURL(
							instance.portletId,
							modelResource,
							modelResourceDescription,
							resourcePrimKey
						);

						return portletURL;
					},

					_createRenderURL: function(target) {
						var instance = this;

						var action = '';
						var renderURL = Liferay.PortletURL.createRenderURL();

						renderURL.setPortletId(instance.portletId);
						renderURL.setWindowState('exclusive');

						if (target == VOCABULARY) {
							action = '/asset_category_admin/edit_vocabulary';
						}
						else if (target == CATEGORY) {
							action = '/asset_category_admin/edit_category';
						}

						renderURL.setParameter('struts_action', action);

						return renderURL;
					},

					_deleteCategory: function(categoryId, callback) {
						var instance = this;

						Liferay.Service.Asset.AssetCategory.deleteCategory(
							{
								categoryId: categoryId
							},
							callback
						);
					},

					_deleteVocabulary: function(vocabularyId, callback) {
						var instance = this;

						Liferay.Service.Asset.AssetVocabulary.deleteVocabulary(
							{
								vocabularyId: vocabularyId
							},
							callback
						);
					},

					_displayVocabularyCategoriesImpl: function(categories, callback) {
						var instance = this;

						var childrenList = A.one(instance._categoryContainerSelector);
						var boundingBox = Node.create('<div class="vocabulary-treeview-container" id="vocabularyTreeContainer"></div>');

						childrenList.empty();
						childrenList.append(boundingBox);

						if (instance.treeView) {
							instance.treeView.destroy();
						}

						instance.treeView = new VocabularyTree(
							{
								boundingBox: boundingBox,
								on: {
									dropAppend: function(event) {
										var tree = event.tree;
										var fromCategoryId = instance._getCategoryId(tree.dragNode);
										var fromCategoryName = instance._getCategoryName(tree.dragNode);
										var toCategoryId = instance._getCategoryId(tree.dropNode);
										var vocabularyId = instance._selectedVocabularyId;

										instance._merge(fromCategoryId, fromCategoryName, toCategoryId, vocabularyId);
									},
									dropInsert: function(event) {
										var tree = event.tree;
										var parentNode = tree.dropNode.get('parentNode');
										var fromCategoryId = instance._getCategoryId(tree.dragNode);
										var fromCategoryName = instance._getCategoryName(tree.dragNode);
										var toCategoryId = instance._getCategoryId(parentNode);
										var vocabularyId = instance._selectedVocabularyId;

										instance._merge(fromCategoryId, fromCategoryName, toCategoryId, vocabularyId);
									}
								},
								type: 'normal'
							}
						).render();

						instance._buildCategoryTreeview(categories, 0);

						instance._reloadSearch();

						var vocabularyContainer = A.one(instance._vocabularyContainerSelector);
						var listLinks = vocabularyContainer.all('li');

						listLinks.unplug(A.Plugin.Drop);

						listLinks.plug(
							A.Plugin.Drop,
							{
								bubbleTargets: [instance, instance.treeView]
							}
						);

						if (callback) {
							callback();
						}
					},

					_displayList: function(callback) {
						var instance = this;

						var buffer = [];
						var list = A.one(instance._vocabularyContainerSelector);

						instance._showLoading('.vocabulary-categories, .vocabulary-list');

						buffer.push('<ul>');

						instance._getVocabularies(
							function(vocabularies) {
								instance._vocabularies = vocabularies;

								A.each(
									vocabularies,
									function(item, index, collection) {
										buffer.push('<li');
										buffer.push(' class="vocabulary-category results-row');

										if (index == 0) {
											buffer.push(' selected ');
										}

										buffer.push('" data-vocabulary="');
										buffer.push(item.name);
										buffer.push('" data-vocabularyId="');
										buffer.push(item.vocabularyId);
										buffer.push('"><span><a href="javascript:;">');
										buffer.push(item.name);
										buffer.push('</a></span>');
										buffer.push('</li>');
									}
								);

								buffer.push('</ul>');

								list.html(buffer.join(''));

								var firstVocabulary = A.one(instance._vocabularyItemSelector);
								var vocabularyName = instance._getVocabularyName(firstVocabulary);
								var vocabularyId = instance._getVocabularyId(firstVocabulary);

								instance._selectedVocabularyName = vocabularyName;
								instance._selectedVocabularyId = vocabularyId;

								var listLinks = list.all('li');

								listLinks.on(
									'mousedown',
									function(event) {
										var vocabularyId = instance._getVocabularyId(event.currentTarget);

										instance._selectVocabulary(vocabularyId);
									}
								);

								var editableConfig = {
									eventType: 'dblclick',
									on: {
										contentTextChange: function(event) {
											if (!event.initial) {
												var editable = event.target;

												var vocabularyName = event.newVal;
												var vocabularyId = instance._selectedVocabularyId;

												var li = this.get('node').ancestor('li');

												li.setAttribute('data-vocabulary', event.newVal);

												instance._updateVocabulary(
													vocabularyId,
													vocabularyName,
													function(message) {
														var errorKey;
														var exception = message.exception;

														if (exception) {
															event.newVal = event.prevVal;

															editable._syncContentText(event);

															editable.set(
																'contentText',
																event.newVal,
																{
																	initial: true
																}
															);

															if (exception.indexOf(EXC_PRINCIPAL) > -1) {
																errorKey = Liferay.Language.get('you-do-not-have-permission-to-access-the-requested-resource');
															}
															else if (exception.indexOf(EXC_VOCABULARY_NAME) > -1) {
																errorKey = Liferay.Language.get('one-of-your-fields-contains-invalid-characters');
															}
															else {
																errorKey = Liferay.Language.get('your-request-failed-to-complete');
															}

															instance._sendMessage(MSG_TYPE_ERROR, errorKey);
														}
														else {
															instance._displayList(
																function() {
																	instance._displayVocabularyCategories(instance._selectedVocabularyId);
																}
															);
														}
													}
												);
											}
										}
									}
								};

								var listEls = A.all('.vocabulary-list li span a');
								var listLength = listEls.size();

								for (var i = 0; i < listLength; i++) {
									editableConfig.node = listEls.item(i);

									new A.Editable(editableConfig);
								}

								if (callback) {
									callback();
								}
							}
						);
					},

					_displayCategoryProperties: function(categoryId) {
						var instance = this;

						instance._getCategoryProperties(
							categoryId,
							function(categoryProperties) {
								if (!categoryProperties.length) {
									categoryProperties = [{ key: '', value: '' }];
								}

								var total = categoryProperties.length;
								var totalRendered = A.all('div.vocabulary-property-row').size();

								if (totalRendered > total) {
									return;
								}

								A.each(
									categoryProperties,
									function(item, index, collection) {
										var baseCategoryRows = A.all('div.vocabulary-property-row');
										var lastIndex = baseCategoryRows.size() - 1;
										var baseRow = baseCategoryRows.item(lastIndex);

										instance._addCategoryProperty(baseRow, item.key, item.value);
									}
								);
							}
						);
					},

					_displayVocabularyCategories: function(vocabularyId, callback) {
						var instance = this;

						var categoryMessages = A.one('#vocabulary-category-messages');

						if (categoryMessages) {
							categoryMessages.hide();
						}

						instance._getVocabularyCategories(
							vocabularyId,
							function(categories) {
								instance._displayVocabularyCategoriesImpl(categories, callback);
							}
						);
					},

					_initializeCategoryPanel: function() {
						var instance = this;

						var categoryForm = instance._toolbarCategoryPanel.get('contentBox').one('form.update-category-form');

						categoryForm.detach('submit');

						categoryForm.on('submit', instance._onCategoryFormSubmit, instance);

						var closeButton = categoryForm.one('.aui-button-input-cancel');

						closeButton.on('click', instance._onCategoryButtonClose, instance);

						instance._categoryForm = categoryForm;
					},

					_initializeVocabularyCategoryPanel: function() {
						var instance = this;

						var vocabularyForm = instance._vocabularyCategoryPanel.get('contentBox').one('form.update-vocabulary-form');

						vocabularyForm.detach('submit');

						vocabularyForm.on('submit', instance._onVocabularyFormSubmit, instance);

						var closeButton = vocabularyForm.one('.aui-button-input-cancel');

						closeButton.on('click', instance._onVocabularyButtonClose, instance);

						instance._vocabularyForm = vocabularyForm;
					},

					_feedVocabularySelect: function(vocabularies, selectedVocabularyId) {
						var instance = this;

						var select = A.one('select.vocabulary-select-list');

						if (select) {
							var buffer = [];

							selectedVocabularyId = parseInt(selectedVocabularyId, 10);

							A.each(
								vocabularies,
								function(item, index, collection) {
									buffer.push('<option');

									if (item.vocabularyId == selectedVocabularyId) {
										buffer.push(' selected');
									}

									buffer.push(' value="');
									buffer.push(item.vocabularyId);
									buffer.push('">');
									buffer.push(item.name);
									buffer.push('</option>');
								}
							);

							buffer = buffer.join('');

							select.html(buffer);
						}
					},

					_filterCategory: function(categories, parentCategoryId) {
						var instance = this;

						return A.Array.filter(
							categories,
							function(item, index, collection) {
								return (item.parentCategoryId == parentCategoryId);
							}
						);
					},

					_getCategory: function(categoryId) {
						var instance = this;

						return A.Widget.getByNode('#categoryNode' + categoryId);
					},

					_getCategoryId: function(node) {
						var instance = this;

						var nodeId = node.get('id') || '';
						var categoryId = nodeId.replace('categoryNode', '');

						if (A.Lang.isGuid(categoryId)) {
							categoryId = '';
						}

						return categoryId;
					},

					_getCategoryName: function(node) {
						var instance = this;

						return node.get('label');
					},

					_getParentCategoryId: function(node) {
						var instance = this;

						var parentNode = node.get('parentNode');

						return instance._getCategoryId(parentNode);
					},

					_getPermissionsEnabled: function(vocabularyType, type) {
						var instance = this;

						var buffer = [];
						var permissionsActions = A.one('.' + vocabularyType + '-permissions-actions');
						var permissions = permissionsActions.all('[name$=' + type + 'Permissions]');

						permissions.each(
							function(item, index, collection) {
								if (item.get('checked')) {
									buffer.push(item.val());
								}
							}
						);

						return buffer.join(',');
					},

					_getCategoryProperties: function(categoryId, callback) {
						var instance = this;

						Liferay.Service.Asset.AssetCategoryProperty.getCategoryProperties(
							{
								categoryId: categoryId
							},
							callback
						);
					},

					_getVocabularies: function(callback) {
						var instance = this;

						Liferay.Service.Asset.AssetVocabulary.getGroupVocabularies(
							{
								groupId: themeDisplay.getParentGroupId()
							},
							callback
						);
					},

					_getVocabulary: function(vocabularyId) {
						var instance = this;

						return A.one('li[data-vocabularyId="' + vocabularyId + '"]');
					},

					_getVocabularyCategories: function(vocabularyId, callback) {
						var instance = this;

						instance._showLoading(instance._categoryContainerSelector);

						Liferay.Service.Asset.AssetCategory.getVocabularyCategories(
							{
								vocabularyId: vocabularyId,
								start: -1,
								end: -1,
								obc: null
							},
							callback
						);
					},

					_getVocabularyId: function(exp) {
						var instance = this;

						return A.one(exp).attr('data-vocabularyId');
					},

					_getVocabularyName: function(exp) {
						var instance = this;

						return A.one(exp).attr('data-vocabulary');
					},

					_hideAllMessages: function() {
						var instance = this;

						instance._container.one('.lfr-message-response').hide();
					},

					_hideFloatingPanels: function(event) {
						var instance = this;

						var contextPanel = event.currentTarget;
						var boundingBox = contextPanel.get('boundingBox');
						var autoFieldsTriggers = boundingBox.all('.lfr-floating-trigger');

						autoFieldsTriggers.each(
							function(item, index, collection) {
								var autoFieldsInstance = item.getData('autoFieldsInstance');
								var panelInstance = item.getData('panelInstance');

								instance._resetInputLocalized(autoFieldsInstance, panelInstance);
							}
						);
					},

					_hideLoading: function(exp) {
						var instance = this;

						instance._container.one('div.loading-animation').remove();
					},

					_hideSection: function(exp) {
						var instance = this;

						var node = A.one(exp);

						if (node) {
							var parentNode = node.get('parentNode');

							if (parentNode) {
								parentNode.hide();
							}
						}
					},

					_hideToolbarSections: function() {
						var instance = this;

						instance._toolbarCategoryPanel.hide();
						instance._vocabularyCategoryPanel.hide();
					},

					_loadData: function() {
						var instance = this;

						instance._closeEditSection();

						instance._displayList(
							function() {
								instance._displayVocabularyCategories(instance._selectedVocabularyId);
							}
						);
					},

					_merge: function(fromCategoryId, fromCategoryName, toCategoryId, vocabularyId) {
						var instance = this;

						var categoryProperties = instance._buildCategoryProperties();

						vocabularyId = vocabularyId || instance._selectedVocabularyId;

						instance._updateCategory(fromCategoryId, vocabularyId, toCategoryId, fromCategoryName, categoryProperties);
					},

					_onCategoryAddFailure: function(response) {
						var instance = this;

						instance._sendMessage(MSG_TYPE_ERROR, Liferay.Language.get('your-request-failed-to-complete'));
					},

					_onCategoryAddSuccess: function(response) {
						var instance = this;

						var exception = response.exception;

						if (!exception && response.categoryId) {
							instance._sendMessage(MSG_TYPE_SUCCESS, Liferay.Language.get('your-request-processed-successfully'));

							instance._selectVocabulary(instance._selectedVocabularyId);

							instance._displayVocabularyCategories(
								instance._selectedVocabularyId,
								function() {
									instance._hideSection('.vocabulary-edit');
								}
							);

							instance._resetActionValues();
							instance._hideToolbarSections();
						}
						else {
							var errorKey = '';

							if (exception.indexOf('DuplicateCategoryException') > -1) {
								errorKey = Liferay.Language.get('that-category-already-exists');
							}
							else if ((exception.indexOf('CategoryNameException') > -1) ||
									 (exception.indexOf('AssetCategoryException') > -1)) {
								errorKey = Liferay.Language.get('one-of-your-fields-contains-invalid-characters');
							}
							else if (exception.indexOf(EXC_NO_SUCH_VOCABULARY) > -1) {
								errorKey = Liferay.Language.get('that-vocabulary-does-not-exist');
							}
							else if (exception.indexOf(EXC_PRINCIPAL) > -1) {
								errorKey = Liferay.Language.get('you-do-not-have-permission-to-access-the-requested-resource');
							}
							else {
								errorKey = Liferay.Language.get('your-request-failed-to-complete');
							}

							instance._sendMessage(MSG_TYPE_ERROR, errorKey);
						}
					},

					_onCategoryButtonClose: function(event) {
						var instance = this;

						instance._toolbarCategoryPanel.hide();
					},

					_onCategoryFormSubmit: function(event) {
						var instance = this;

						event.preventDefault();
						event.stopImmediatePropagation();

						var vocabularySelectNode = A.one('.vocabulary-select-list');

						var vocabularyId = (vocabularySelectNode && vocabularySelectNode.val()) || instance._selectedVocabularyId;

						if(vocabularyId) {
							var categoryForm = instance._categoryForm;

							var vocabularyElId = ["#", instance._prefixedPortletId, 'vocabularyId'].join('');
							var parentCategoryElId = ["#", instance._prefixedPortletId, 'parentCategoryId'].join('');

							categoryForm.one(vocabularyElId).set("value", vocabularyId);
							categoryForm.one(parentCategoryElId).set("value", 0);

							instance._addCategory(vocabularyId);
						}
					},

					_onVocabularyAddFailure: function(response) {
						var instance = this;

						instance._sendMessage(MSG_TYPE_ERROR, Liferay.Language.get('your-request-failed-to-complete'));
					},

					_onVocabularyAddSuccess: function(response) {
						var instance = this;

						instance._hideAllMessages();

						var exception = response.exception;

						if (!response.exception) {
							instance._sendMessage(MSG_TYPE_SUCCESS, Liferay.Language.get('your-request-processed-successfully'));

							instance._displayList(
								function() {
									var vocabulary = instance._selectVocabulary(response.vocabularyId);

									instance._displayVocabularyCategories(instance._selectedVocabularyId);

									if (vocabulary) {
										var scrollTop = vocabulary.get('region').top;

										A.one(instance._vocabularyContainerSelector).set('scrollTop', scrollTop);
									}
								}
							);

							instance._resetActionValues();
						}
						else {
							var errorKey = '';

							if (exception.indexOf('DuplicateVocabularyException') > -1) {
								errorKey = Liferay.Language.get('that-vocabulary-already-exists');
							}
							else if (exception.indexOf(EXC_VOCABULARY_NAME) > -1) {
								errorKey = Liferay.Language.get('one-of-your-fields-contains-invalid-characters');
							}
							else if (exception.indexOf(EXC_NO_SUCH_VOCABULARY) > -1) {
								errorKey = Liferay.Language.get('that-parent-vocabulary-does-not-exist');
							}
							else if (exception.indexOf(EXC_PRINCIPAL) > -1) {
								errorKey = Liferay.Language.get('you-do-not-have-permission-to-access-the-requested-resource');
							}
							else {
								errorKey = Liferay.Language.get('your-request-failed-to-complete');
							}

							instance._sendMessage(MSG_TYPE_ERROR, errorKey);
						}
					},

					_onVocabularyButtonClose: function(event) {
						var instance = this;

						instance._vocabularyCategoryPanel.hide();
					},

					_onVocabularyFormSubmit: function(event) {
						var instance = this;

						event.halt();

						instance._addVocabulary();
					},

					_reloadSearch: function() {
						var	instance = this;

						var options = {
							input: '#vocabulary-search-input'
						};

						var vocabularySelectSearchNode = A.one('#vocabulary-select-search');
						var selected = (vocabularySelectSearchNode && vocabularySelectSearchNode.val()) || '';

						if (/vocabularies/.test(selected)) {
							A.mix(
								options,
								{
									data: function(node) {
										return node.one('a').html();
									},
									nodes: instance._vocabularyItemSelector
								}
							);
						}
						else {
							A.mix(
								options,
								{
									after: {
										search: function(event) {
											var results = event.liveSearch.results;

											A.each(
												results,
												function(item, index, collection) {
													var nodeWidget = A.Widget.getByNode(item.node);
													var nodeVisible = nodeWidget.get('boundingBox').hasClass('aui-helper-hidden');

													if (!nodeVisible) {
														nodeWidget.eachParent(
															function(parent) {
																parent.get('boundingBox').show();
															}
														);
													}
												}
											);
										}
									},
									data: function(node) {
										return node.one('.aui-tree-label').html();
									},
									nodes: instance._categoryItemSelector
								}
							);
						}

						if (instance.liveSearch) {
							instance.liveSearch.destroy();
						}

						instance.liveSearch = new A.LiveSearch(options);
					},

					_removeCategoryProperty: function(categoryProperty) {
						var instance = this;

						if (A.all('div.vocabulary-property-row').size() > 2) {
							categoryProperty.remove();
						}
					},

					_resetActionValues: function() {
						var instance = this;

						A.all('.vocabulary-actions input[type=text]').val('');

						instance._vocabularyCategoryPanel.hide();
					},

					_resetInputLocalized: function(autoFieldsInstance, panelInstance) {
						var instance = this;

						if (autoFieldsInstance) {
							autoFieldsInstance.reset();
						}

						if (panelInstance) {
							panelInstance.hide();
						}
					},

					_saveCategoryProperties: function() {
						var instance = this;

						var categoryId = instance._selectedCategoryId;
						var categoryNameNode = A.one('input.category-name');
						var categoryName = (categoryNameNode && categoryNameNode.val()) || instance._selectedCategoryName;
						var parentCategoryId = instance._selectedParentCategoryId;
						var categoryProperties = instance._buildCategoryProperties();
						var vocabularyId = instance._selectedVocabularyId;

						instance._updateCategory(categoryId, vocabularyId, parentCategoryId, categoryName, categoryProperties);
						instance._displayVocabularyCategories(vocabularyId);
					},

					_selectCurrentVocabulary: function(value) {
						var instance = this;

						var option = A.one('select.vocabulary-select-list option[value="' + value + '"]');

						if (option) {
							option.set('selected', true);
						}
					},

					_selectCategory: function(categoryId) {
						var instance = this;

						var category = instance._getCategory(categoryId);
						var categoryName = instance._getCategoryName(category);
						var parentCategoryId = instance._getParentCategoryId(category);

						categoryId = instance._getCategoryId(category);

						instance._selectedCategoryId = categoryId;
						instance._selectedCategoryName = categoryName;
						instance._selectedParentCategoryId = parentCategoryId || 0;

						if (!categoryId) {
							return category;
						}

						var properties = A.all('div.vocabulary-property-row');
						var editContainer = A.one('.vocabulary-edit');
						var categoryNameField = editContainer.one('input.category-name');

						if (properties.size() > 1) {
							properties.each(
								function(item, index, collection) {
									if (index > 0) {
										item.remove();
									}
								}
							);
						}

						if (categoryNameField) {
							categoryNameField.val(categoryName);
						}

						instance._displayCategoryProperties(categoryId);

						instance._selectedCategory = category;

						return category;
					},

					_selectVocabulary: function(vocabularyId) {
						var instance = this;

						var vocabulary = instance._getVocabulary(vocabularyId);

						if (vocabulary) {
							var vocabularyName = instance._getVocabularyName(vocabulary);

							if (vocabulary.hasClass('selected')) {
								return vocabulary;
							}

							instance._hideAllMessages();
							instance._selectedVocabularyName = vocabularyName;
							instance._selectedVocabularyId = vocabularyId;
							instance._selectCurrentVocabulary(vocabularyId);

							instance._unselectAllVocabularies();
							instance._closeEditSection();

							vocabulary.addClass('selected');
							instance._displayVocabularyCategories(instance._selectedVocabularyId);
						}

						return vocabulary;
					},

					_sendMessage: function(type, message) {
						var instance = this;

						var output = instance._portletMessageContainer;
						var typeClass = 'portlet-msg-' + type;

						output.removeClass('portlet-msg-error').removeClass('portlet-msg-success');
						output.addClass(typeClass);
						output.html(message);
						output.show();

						instance._hideMessageTask.delay(7000);
					},

					_showLoading: function(container) {
						var instance = this;

						A.all(container).html('<div class="loading-animation" />');
					},

					_showSection: function(exp) {
						var instance = this;

						var element = A.one(exp);

						if (element) {
							var parentNode = element.get('parentNode');

							if (parentNode) {
								parentNode.show();
								element.one('input').focus();
							}
						}
					},

					_showToolBarCategorySection: function() {
						var instance = this;

						var categoryPanel = instance._toolbarCategoryPanel;

						if (!instance._selectedVocabularyName) {
							instance._resetActionValues();

							categoryPanel.hide();

							instance._sendMessage('info', Liferay.Language.get('you-must-first-add-a-vocabulary'));

							instance._showToolBarVocabularySection();

							return;
						}

						categoryPanel.refreshAlign();

						instance._vocabularyCategoryPanel.hide();

						var inputCategoryNameNode = categoryPanel.get('contentBox').one('.vocabulary-category-name input');

						Liferay.Util.focusFormField(inputCategoryNameNode);
					},

					_showToolBarVocabularySection: function() {
						var instance = this;

						var vocabularyCategoryPanel = instance._vocabularyCategoryPanel;

						vocabularyCategoryPanel.refreshAlign();

						instance._toolbarCategoryPanel.hide();

						var inputVocabularyNameNode = vocabularyCategoryPanel.get('contentBox').one('.vocabulary-name input');

						Liferay.Util.focusFormField(inputVocabularyNameNode);
					},

					_unselectAllVocabularies: function() {
						var instance = this;

						A.all(instance._vocabularyItemSelector).removeClass('selected');
					},

					_updateCategory: function(categoryId, vocabularyId, parentCategoryId, name, categoryProperties, callback) {
						var instance = this;

						var descriptionMap = {};
						var titleMap = {};

						titleMap[themeDisplay.getDefaultLanguageId()] = name;

						Liferay.Service.Asset.AssetCategory.updateCategory(
							{
								categoryId: categoryId,
								parentCategoryId: parentCategoryId,
								titleMap: JSON.stringify(titleMap),
								descriptionMap: JSON.stringify(descriptionMap),
								vocabularyId: vocabularyId,
								properties: categoryProperties,
								serviceContext: null
							},
							function(message) {
								var exception = message.exception;

								if (!exception) {
									instance._selectedCategory.set('label', name);

									instance._closeEditSection();
								}
								else {
									var errorKey;

									if (exception.indexOf('AssetCategoryNameException') > -1) {
										errorKey = Liferay.Language.get('please-enter-a-valid-category-name');
									}
									else if (exception.indexOf('DuplicateCategoryException') > -1) {
										errorKey = Liferay.Language.get('there-is-another-category-with-the-same-name-and-the-same-parent');
									}
									else if (exception.indexOf(EXC_NO_SUCH_VOCABULARY) > -1) {
										errorKey = Liferay.Language.get('that-vocabulary-does-not-exist');
									}
									else if (exception.indexOf('NoSuchCategoryException') > -1) {
										errorKey = Liferay.Language.get('that-parent-category-does-not-exist');
									}
									else if (exception.indexOf(EXC_PRINCIPAL) > -1) {
										errorKey = Liferay.Language.get('you-do-not-have-permission-to-access-the-requested-resource');
									}
									else if (exception.indexOf('Exception') > -1) {
										errorKey = Liferay.Language.get('one-of-your-fields-contains-invalid-characters');
									}
									else {
										errorKey = Liferay.Language.get('your-request-failed-to-complete');
									}

									instance._sendMessage(MSG_TYPE_ERROR, errorKey);
								}

								if (callback) {
									callback(message);
								}
							}
						);
					},

					_updateVocabulary: function(vocabularyId, vocabularyName, callback) {
						var titleMap = {};

						titleMap[themeDisplay.getDefaultLanguageId()] = vocabularyName;

						Liferay.Service.Asset.AssetVocabulary.updateVocabulary(
							{
								vocabularyId: vocabularyId,
								title: '',
								titleMap: JSON.stringify(titleMap),
								description: '',
								settings: '',
								serviceContext: JSON.stringify(
									{
										scopeGroupId: themeDisplay.getParentGroupId()
									}
								)
							},
							callback
						);
					},

					_categoryItemSelector: '.vocabulary-categories .aui-tree-node',
					_categoryContainerSelector: '.vocabulary-categories',
					_selectedCategoryName: null,
					_selectedVocabulary: null,
					_selectedVocabularyId: null,
					_selectedVocabularyName: null,
					_vocabularies: null,
					_vocabularyItemSelector: '.vocabulary-list li',
					_vocabularyContainerSelector: '.vocabulary-list'
				}
			}
		);

		var VocabularyTree = A.Component.create(
			{
				NAME: 'VocabularyTree',

				EXTENDS: A.TreeViewDD,

				prototype: {
					_updateNodeState: function(event) {
						var instance = this;

						var dropNode = event.drop.get('node');

						if (dropNode && dropNode.hasClass('vocabulary-category')) {
							instance._appendState(dropNode);
						}
						else {
							VocabularyTree.superclass._updateNodeState.apply(instance, arguments);
						}
					}
				}
			}
		);

		Liferay.Portlet.AssetCategoryAdmin = AssetCategoryAdmin;
	},
	'',
	{
		requires: ['aui-editable', 'aui-live-search', 'aui-overlay-context-panel', 'aui-tree-view', 'dd', 'json', 'liferay-portlet-url']
	}
);