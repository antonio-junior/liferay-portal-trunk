Liferay.SearchContainer = new Class({
	initialize: function(options) {
		var instance = this;

		instance._id = options.id || '';
		instance._container = jQuery('#' + instance._id + 'searchContainer');
		instance._dataStore = jQuery('#' + instance._id + 'primaryKeys');

		instance._table = instance._container.find('table');

		instance._table.attr('data-searchContainerId', instance._id);

		Liferay.SearchContainer.register(instance._id, instance);

		var initialIds = instance._dataStore.val() || '';

		initialIds = initialIds.split(',');
		instance.updateDataStore(initialIds);
	},

	addRow: function(arr, id) {
		var instance = this;

		if (id) {
			var row = instance._table.find('.lfr-template').clone();
			var cells = row.find('> td');

			cells.empty();

			jQuery.each(
				arr,
				function(i, n) {
					if (cells[i]) {
						cells.eq(i).html(n);
					}
				}
			);
			instance._table.append(row);
			row.removeClass('lfr-template');

			instance._ids.push(id);
		}

		instance.updateDataStore();

		instance.trigger('addRow', {ids: instance._ids, rowData: arr});
	},

	bind: function(event, func) {
		var instance = this;

		instance._container.bind(event, func);
	},

	deleteRow: function(obj, id) {
		var instance = this;

		if (typeof obj == 'number' || typeof obj == 'string') {
			obj = instance._table.find('tr').not('.lfr-template').eq(obj);
		}
		else if (obj.nodeName) {
			obj = jQuery(obj);
		}
		else if (obj.jquery) {
			obj = obj;
		}

		if (id) {
			var pos = instance._ids.indexOf(id.toString());

			if (pos > -1) {
				instance._ids.splice(pos, 1);
				instance.updateDataStore();
			}
		}

		instance.trigger('deleteRow', {ids: instance._ids, row: obj});

		if (!obj.is('tr')) {
			obj = obj.parents('tr:first');
		}

		obj.remove();
	},

	getData: function() {
		var instance = this;

		return instance._ids.join(',');
	},

	updateDataStore: function(ids) {
		var instance = this;

		if (ids) {
			if (typeof ids == 'string') {
				ids = ids.split(',');
			}

			instance._ids = ids;
		}

		instance._dataStore.val(instance._ids.join(','));
	},

	trigger: function(event, data) {
		var instance = this;

		instance._container.trigger(event, data);
	},

	_ids: []
});

jQuery.extend(
	Liferay.SearchContainer,
	{
		get: function(id) {
			var instance = this;

			var searchContainer;

			if (instance._cache[id]) {
				searchContainer = instance._cache[id];
			}
			else {
				searchContainer = new Liferay.SearchContainer(
					{
						id: id
					}
				);
			}

			return searchContainer;
		},

		register: function(id, obj) {
			var instance = this;

			instance._cache[id] = obj;
		},

		_cache: {}
	}
);