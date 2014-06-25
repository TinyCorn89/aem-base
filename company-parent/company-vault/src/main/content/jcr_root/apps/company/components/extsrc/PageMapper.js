// declare namespace
CQ.Ext.namespace('domain.components.extsrc');

/**
 * @const
 * @type {String}
 */
domain.components.extsrc.PAGE_MAPPER_XTYPE = 'pagemapper';
/**
 * @const
 * @type {String}
 */
domain.components.extsrc.PAGE_MAPPER_GROUPDD = domain.components.extsrc.PAGE_MAPPER_XTYPE + 'dd';
/**
 * @const
 * @type {Number}
 */
domain.components.extsrc.PAGE_MAPPER_HEIGHT = 200;

/**
 * @class domain.components.extsrc.PageMapper
 * @extends CQ.form.CompositeField
 * Component that stores paths chosen by a content author using
 * drag and drop from a Tree Panel to a Grid Panel.
 * @constructor
 * Creates a new PageMapper.
 * @param {Object} config The config object
 * @xtype pagemapper
 */
domain.components.extsrc.PageMapper = CQ.Ext.extend(CQ.form.CompositeField, {
	/**
	 * @type {Boolean}
	 * Appear as a panel but act like a form!
	 */
	hideLabel: true,
	/**
	 * @type {CQ.Ext.form.Hidden}
	 */
	typeField: null,
	/**
	 * @type {CQ.Ext.form.Hidden}
	 * Used to indicate empty set
	 */
	emptyField: null,
	/**
	 * @type {String}
	 * Default if a name isn't provided
	 */
	formName: './pageMapper',
	/**
	 * @type {Array}
	 * List of {CQ.Ext.form.Hidden} for values
	 */
	hiddenFields: [],
	/**
	 * @type {CQ.Dialog}
	 */
	parentDialog: null,
	/**
	 * @type {CQ.Ext.tree.TreePanel}
	 */
	treePanel: null,
	/**
	 * @type {CQ.Ext.grid.GridPanel}
	 */
	gridPanel: null,
	/**
	 * @type {CQ.Ext.Panel}
	 */
	containerPanel: null,
	/**
	 * @type {String}
	 */
	treeRootPath: '/content',
	/**
	 * @type {Array}
	 */
	allowedNodes: '',
	/**
	 * @type {CQ.Ext.data.Store}
	 * Model reference
	 */
	parentStore: null,
	/**
	 * @type {CQ.Ext.data.Store}
	 * PageMapper model
	 */
	dataStore: null,
	/**
	 * Initializer event
	 * @return {undefined}
	 */
	initComponent: function () {
		// instantiate parent constructor
		comain.components.extsrc.PageMapper.superclass.initComponent.call(this);

		// form key resolver
		if (typeof this.getName().toString() === 'string') {
			this.formName = this.getName();
		}

		// set type to to store as String[] on JCR property
		this.setDataType('String[]');

		// wrapper to store components
		this.containerPanel = new CQ.Ext.Panel({
			layout: 'column'
		});

		// create colletions model
		this.dataStore = new CQ.Ext.data.ArrayStore({
			// store configs
			autoDestroy: true,
			storeId: 'pageMapperStore',
			// reader configs
			idIndex: 0,
			fields: ['path']
		});

		// configures parent data
		this.parentStoreBootup();

		// tree configs
		this.initTreePanel();

		// grid configs
		this.initGridPanel();

		// append container to main
		this.add(this.containerPanel);
	},
	/**
	 * Finds the primary data store by looking for this parent's dialog
	 * @return {undefined}
	 */
	parentStoreBootup: function () {
		var _this = this;

		// get parent reference
		this.parentDialog = this.findParentByType('dialog');

		// when data is strapped to the dialog, import it
		this.parentDialog.on('loadContent', function () {
			// assign store reference to this component
			_this.parentStore = this.store;

			// boot data up
			_this.loadDataFromParent();
		});
	},
	/**
	 * Initialize {TreePanel
	 * @private
	 * @return {undefined}
	 */
	initTreePanel: function () {
		var _this = this;
        var _restrictRegex = function() {
                if (_this.allowedNodes.trim() !== '') {
                    return new RegExp(_this.allowedNodes.replace(/ *, */g,'|'));
                } else {
                    return /.*/;
                }
        }();

		this.treePanel = new CQ.Ext.tree.TreePanel({
			ddGroup: domain.components.extsrc.PAGE_MAPPER_GROUPDD,
			width: 240,
			height: domain.components.extsrc.PAGE_MAPPER_HEIGHT,
			border: false,
			autoScroll: true,
			containerScroll: true,
			// prevent annoying grey background default
			bodyStyle: 'background: none',
			// hide parent
			rootVisible: false,
			enableDrag: true,
			loader: {
				// data retriever
				dataUrl: CQ.HTTP.externalize('/bin/wcm/siteadmin/tree.json'),
				requestMethod: 'GET',
				// request params
				baseParams: {
					_charset_: 'utf-8'
				},
				// change request params before loading
				listeners: {
					beforeload: function(loader, node) {
						// housekeeping to keep the tree not from exploding when it trys to read nodes
						this.baseParams.path = node.getPath();
						// you'd think enabling the drag feature on the parent would cascade down to the nodes :\
						this.baseAttrs.draggable = true;
					}
				},
				// attributes for all nodes created by the loader
				baseAttrs: {
					singleClickExpand: true,
					// folders look bad, this has a familiar experience (at least in 5.5...)
					iconCls: 'page',
					// more dragging configs!
					draggable: true
				}
			},

			// CQ.Ext.tree.TreeNode config
			root: {
				nodeType: 'async',
				// we don't show the root, no need to have text
				text: '',
				name: _this.treeRootPath,
				expanded: true,
				// no root dragging (its not like u can see it when its disabled anyways)
				draggable: false,
				listeners: {
					beforeappend: function (tree, ins, childNode) {
						if (_restrictRegex.test(childNode.attributes.name) === false) {
							childNode.hidden = true;
							childNode.disabled = true;
						}
					}
				}
			}
		});

		// append to wrapper
		this.containerPanel.add(this.treePanel);
	},
	/**
	 * Initialize Grid Panel
	 * @private
	 * @return {undefined}
	 */
	initGridPanel: function () {
		var _this = this;

		this.gridPanel = new CQ.Ext.grid.GridPanel({
			border: false,
			columnWidth: 1,
			style: 'min-height: ' + domain.components.extsrc.PAGE_MAPPER_HEIGHT + 'px;',
			autoHeight: true,
			loadMask: true,
			stripeRows: true,
			autoExpandColumn: 'path',
			enableColumnMove: false,
			enableDragDrop: false,
			// collections model
			store: _this.dataStore,
			enableHdMenu: false,
			sm: new CQ.Ext.grid.RowSelectionModel({
				singleSelect: true
			}),
			cm: new CQ.Ext.grid.ColumnModel([
			{
				id: 'path',
				header: 'Content Path',
				dataIndex: 'path',
				sortable: true
			}
			])
		});

		// enable drop configs and actions
		this.gridPanel.on('render', function () {
			return new CQ.Ext.dd.DropTarget(this.getEl(), {
				ddGroup: domain.components.extsrc.PAGE_MAPPER_GROUPDD,
				grid: this,
				// drop event callback
				notifyDrop: function(dd, e, data) {
					// clean up path
					var path = data.node.attributes.loader.baseParams.path.toString().substr(1) +
						'/' + data.node.attributes.name;

					// add value
					try {
						_this.addValue(path);
					} catch (e) {
						CQ.Ext.Msg.alert('Page Mapper', e.message);
					}
				}
			});
		});

		// config for right click > delete
		this.gridPanel.on('rowcontextmenu', function (grid, index, e) {
			var xy = e.getXY(),
				menu,
				items = [];

			// build context menu

			// move up
			if (index > 0) {
				items.push({
					text: 'Move Up',
					handler: function() {
						// qualifier
						if (index > 0) {
							var store = _this.dataStore,
								reference = store.getAt(index);

							// remove
							store.removeAt(index);
							store.insert(index - 1, reference);

							// remove all hidden values for rebuild
							_this.removeAllHiddenValues();

							// rebuild
							store.each(function (record) {
								_this.addHiddenValue(record.get('path'));
							});
						} else {
							CQ.Ext.Msg.alert('Page Mapper' , 'Item is already on the top.');
						}
					}
				});
			}

			// move down
			if (index < _this.dataStore.getCount() - 1) {
				items.push({
					text: 'Move Down',
					handler: function() {
						// qualifier
						if (index < _this.dataStore.getCount() - 1) {
							var store = _this.dataStore,
								reference = store.getAt(index);

							// remove
							store.removeAt(index);
							store.insert(index + 1, reference);

							// remove all hidden values for rebuild
							_this.removeAllHiddenValues();

							// rebuild
							store.each(function (record) {
								_this.addHiddenValue(record.get('path'));
							});
						} else {
							CQ.Ext.Msg.alert('Page Mapper' , 'Item is already on the bottom.');
						}
					}
				});
			}

			// remove
			items.push({
				text: 'Remove',
				handler: function() {
					// get path
					var path = _this.dataStore.getAt(index).data.path;

					// remove path
					_this.removeValue(path);
				}
			});

			menu = new CQ.Ext.menu.Menu({
				items: items
			});

			// location of ext context menu
			menu.showAt(xy);

			// preventDefault
			e.stopEvent();
		});

		this.containerPanel.add(this.gridPanel);
	},
	/**
	 * Populates grid from parent data
	 * @return {undefined}
	 */
	loadDataFromParent: function () {
		// set property level and remove path characters
		var mapperProperty = this.formName.substr(2),
			// get mapper data from parent
			data = this.parentStore.getAt(0).get(mapperProperty),
			i;

		// sometimes property isn't present like on first component use
		if (typeof data !== 'undefined') {
			// clear our current store so data isn't duplicated
			this.clearData();

			// iterate over items, spin up data
			for (i = 0; i < data.length; ++i) {
				this.addValue(data[i]);
			}
		}
	},
	/**
	 * Truncates all data
	 * @return {undefined}
	 */
	clearData: function () {
		this.truncateTable();
		this.removeAllHiddenFields();
	},
	/**
	 * Clears collection
	 * @return {undefined}
	 */
	truncateTable: function () {
		this.dataStore.removeAll();
	},
	/**
	 * Clears all hidden fields
	 * @return {undefined}
	 */
	removeAllHiddenFields: function () {
		var i;

		// itertate over fields
		for (i = 0; i < this.hiddenFields.length; ++i) {
			this.hiddenFields[i].remove();
		}

		// clear
		this.hiddenFields = [];

		// render
		this.doLayout();
	},
	/**
	 * Value addition handler
	 * @param {String} path
	 * @return {undefined}
	 */
	addValue: function (path) {
		// path check
		if (this.pathExists(path) === true) {
			throw new CQ.Ext.Error('Path already exists.');
		}

		// record data to collections
		this.insert({
			path: path
		});

		// add hidden value for POST support
		this.addHiddenValue(path);

		// make sure emptyField is disabled
		if (this.hiddenFields.length > 0 && this.emptyField.disabled === false) {
			this.emptyField.disable();
		}
	},
	/**
	 * Value removal handler
	 * @param {String} path
	 * @return {undefined}
	 */
	removeValue: function (path) {
		this.remove('path', path);

		// remove hidden value for POST support
		this.removeHiddenValue(path);

		// if no more items enable empty field
		if (this.hiddenFields.length === 0) {
			this.emptyField.enable();
		}
	},
	/**
	 * Adds a new record to the collections
	 * @param {Object} data
	 * @return {undefined}
	 */
	insert: function (data) {
		this.dataStore.add(new this.dataStore.recordType(data, CQ.Ext.id()));
	},
	/**
	 * Removes a record from the collection based on the key/value
	 * @param {String} key
	 * @param {String} value
	 * @return {undefined}
	 */
	remove: function (key, value) {
		var recordIndex = -1;

		this.dataStore.each(function (item, index) {
			if (this.data[key] !== 'undefined' && this.data[key] === value) {
				recordIndex = index;
			}
		});

		this.dataStore.remove(this.dataStore.getAt(recordIndex));
	},
	/**
	 * Checks if path exists
	 * @param {String} path
	 * @return {undefined}
	 */
	pathExists: function (path) {
		var output = false;

		this.dataStore.each(function () {
			if (this.data.path !== 'undefined' && this.data.path === path) {
				output = true;
			}
		});

		return output;
	},
	/**
	 * Sets type to save on JCR property
	 * @param {String} type Specified type
	 * @return {undefined}
	 */
	setDataType: function (type) {
		// name of field
		var name = this.formName;

		// field config
		this.typeField = new CQ.Ext.form.Hidden({
			name: name + CQ.Sling.TYPEHINT_SUFFIX,
			value: type
		});

		// field config
		this.emptyField = new CQ.Ext.form.Hidden({
			name: name,
			value: '',
			disabled: true
		});

		// append to form to be collected on dialog submit
		this.add(this.typeField);
		this.add(this.emptyField);
	},
	/**
	 * Creates a hidden field with a new value and appends to component
	 * @param {String} value
	 * @return {undefined}
	 */
	addHiddenValue: function (value) {
		// name of field
		var name = this.formName,
			// hidden form to store value
			field = $CQ('<input type="hidden" name="' + name + '" value="' + value + '" />');

		// save reference
		this.hiddenFields.push(field);

		// add to component
		$CQ(this.el.dom).append(field);

		// render
		this.doLayout();
	},
	/**
	 * Removes specified hidden field from component
	 * @param {String} value
	 * @return {undefined}
	 */
	removeHiddenValue: function (value) {
		var i;

		// itertate over fields
		for (i = 0; i < this.hiddenFields.length; ++i) {
			// match?
			if (this.hiddenFields[i].val() === value) {
				// remove!
				this.hiddenFields[i].remove();
				this.hiddenFields.splice(i, 1);

				// end
				break;
			}
		}

		// render
		this.doLayout();
	},
	/**
	 * Removes all hidden values
	 * (Used for resorting since the order that gets send is the POST order)
	 * @return {undefined}
	 */
	removeAllHiddenValues: function () {
		var i;

		// itertate over fields
		for (i = 0; i < this.hiddenFields.length; ++i) {
			this.hiddenFields[i].remove();
		}

		// reset
		this.hiddenFields = [];

		// render
		this.doLayout();
	}
});

// register xtype
CQ.Ext.reg(domain.components.extsrc.PAGE_MAPPER_XTYPE, domain.components.extsrc.PageMapper);