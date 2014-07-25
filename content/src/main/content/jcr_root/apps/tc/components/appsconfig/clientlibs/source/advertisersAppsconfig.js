/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

/**
 * @class CQ.wcm.AdvertisersAppsConfigManager
 * @extends CQ.Ext.Viewport The.AdvertisersAppsConfigManager lets the user manage
 *          appsConfigNodes
 * @constructor Creates a new.AdvertisersAppsConfigManager.
 * @param {Object}
 *            config The config object
 */

var canModifyAppsconfig = false;

CQ.wcm.AdvertisersAppsConfigManager = function(config) {
    this.constructor.call(this, config);

};

var editFlag = false;

CQ.wcm.AdvertisersAppsConfigManager = CQ.Ext.extend(
                CQ.Ext.Viewport,
                {

                    viewConfig : {
                        forceFit : true
                    },

                    constructor : function(config) {
                        var myThis = this;
                        // store
                        var store = new CQ.Ext.data.Store(
                                {
                                    proxy : new CQ.Ext.data.HttpProxy(
                                            {
                                                url : "/bin/services/ads-list?q=advertisers"
                                            }),
                                    autoLoad : true,
                                    reader : new CQ.Ext.data.JsonReader( {
                                        root : 'result',
                                        totalProperty : 'results',
                                        id : 'path',
                                        fields : [ 'path', 'advertiserID',
                                                'name1', 'province', 'address', 'city'  ]
                                    })
                                });
                        store.setDefaultSort('path', 'ASC');

                        // column model
                        var cm = new CQ.Ext.grid.ColumnModel(
                                [
                                        new CQ.Ext.grid.RowNumberer(),
                                        {
                                            header : CQ.I18n
                                                    .getMessage("Advertiser ID"),
                                            dataIndex : 'advertiserID',
                                            width : 200,
                                            sortable : true
                                        },

                                        {
                                            header : CQ.I18n
                                                    .getMessage("Name"),
                                            dataIndex : 'name1',
                                            width : 200,
                                            sortable : true
                                        },
                                        {
                                            header : CQ.I18n
                                                    .getMessage("Province"),
                                            dataIndex : 'province',
                                            width : 200,
                                            sortable : true
                                        },
                                        {
                                            header : CQ.I18n
                                                    .getMessage("Address"),
                                            dataIndex : 'address',
                                            width : 200,
                                            sortable : true
                                        },
                                        {
                                            header : CQ.I18n
                                                    .getMessage("City"),
                                            dataIndex : 'city',
                                            width : 200,
                                            sortable : true
                                        } ]);
                        // cm.defaultSortable = true;

                        var editAction = new CQ.Ext.Action(
                                {
                                    cls : 'CQ.wcm.AdvertisersAppsConfigManager.edit',
                                    text : CQ.I18n.getMessage('Edit'),
                                    handler : function() {
                                        editFlag = true;
                                        var selections = CQ.Ext.getCmp(
                                                'cq-appsconfig-grid')
                                                .getSelectionModel()
                                                .getSelections();
                                        if (selections) {
                                            if (selections.length > 1) {
                                                CQ.Ext.Msg
                                                        .show( {
                                                            title : CQ.I18n
                                                                    .getMessage('Edit Adsconfig Configuration'),
                                                            msg : CQ.I18n
                                                                    .getMessage('Please select only one row'),
                                                            buttons : CQ.Ext.Msg.OK
                                                        });
                                                return;
                                            }
                                            console.log(selections[0]);
                                            console.log(selections[0].json.path);
                                            var path = selections[0].json.path;
                                            var advertiserID = selections[0].json.advertiserID;
                                            var province = selections[0].json.province;
                                            var name1 = selections[0].json.name1;
                                            var address = selections[0].json.address;
                                            var city = selections[0].json.city;

                                            var field = "";

                                            field = myThis.newDialog.getField("./advertiserId");
                                            field.setValue(advertiserID);

                                            field = myThis.newDialog.getField("./province");
                                            field.setValue(province);
											
                                            field = myThis.newDialog.getField("./name1");
                                            field.setValue(name1);
											
                                            field = myThis.newDialog.getField("./address");
                                            field.setValue(address);

                                            field = myThis.newDialog.getField("./city");
                                            field.setValue(city);

                                            myThis.newDialog.show();

                                        }

                                    },
                                    tooltip : {
                                        title : CQ.I18n
                                                .getMessage('Edit configuration entry'),
                                        text : CQ.I18n
                                                .getMessage('Edit the selected property'),
                                        autoHide : true
                                    }
                                });
                        editAction.setDisabled(true);


                        var filterTextBox = new CQ.Ext.form.TextField( {
                            xtype : 'textfield',
                            fieldLabel : 'Type Filter String Here',
                            name : 'filter',
                            id : 'filter',
                            inputType : 'text',
                            enableKeyEvents : true,
                            value : ""
                        });
                        filterTextBox.on('keyup', function() {
                            var filterString = filterTextBox.getValue();
                            if (filterFields.getValue() === 'Select Filter') {
                                alert("Please Select Filter Field");

                            } else {
                                store.filter(filterFields.getValue(),
                                        filterString);
                            }
                        });

                        var filterFieldsStore = new CQ.Ext.data.JsonStore( {
                            root : 'filterFields',
                            fields : [ {
                                name : 'name',
                                type : 'string'
                            }, {
                                name : 'value',
                                type : 'string'
                            } ],
                            data : {
                                filterFields : [ {
                                    "name" : "Advertiser ID",
                                    "value" : "advertiserID"
                                }, {
                                    "name" : "Name",
                                    "value" : "name1"
                                }, {
                                    "name" : "Province",
                                    "value" : "province"
                                }, {
                                    "name" : "Address",
                                    "value" : "address"
                                }, {
                                    "name" : "City",
                                    "value" : "city"
                                } ]
                            }
                        });

                        this.newDialog = CQ.WCM
                                .getDialog(
                                        {
                                            "jcr:primaryType" : "cq:Dialog",
                                            "xtype" : "dialog",
                                            "title" : 'Advertisers Config Configuration',
                                            "params" : {
                                                "_charset_" : "utf-8"
                                            },
                                            "items" : {
                                                "xtype" : 'panel',
                                                "items" : [
                                                        {
                                                            xtype : 'hidden',
                                                            name : 'action',
                                                            value : 'save'
                                                        },
                                                        {
                                                            xtype : 'hidden',
                                                            name : 'nodeName',
                                                            value : ''
                                                        },

                                                        {
                                                            "xtype" : "textfield",
                                                            "name" : "./advertiserId",
                                                            "fieldLabel" : CQ.I18n
                                                                    .getMessage("Advertiser ID:"),
                                                            "allowBlank" : false,
                                                            "value" : ""
                                                        },
                                                        {
                                                            "xtype" : 'textfield',
                                                            "name" : './name1',
                                                            "fieldLabel" : CQ.I18n
                                                                    .getMessage("Name:"),
                                                            "allowBlank" : false,
                                                            "required" : "true",
                                                            "value" : ""
                                                        },
                                                        {
                                                            "xtype" : 'textfield',
                                                            "name" : './province',
                                                            "fieldLabel" : CQ.I18n
                                                                    .getMessage("Province :"),
                                                            "allowBlank" : false,
                                                            "required" : "true",
                                                            "value" : ""
                                                        },
                                                       {
                                                            "xtype" : 'textfield',
                                                            "name" : './address',
                                                            "fieldLabel" : CQ.I18n
                                                                    .getMessage("Address :"),
                                                            "allowBlank" : false,
                                                            "required" : "true",
                                                            "value" : ""
                                                        },
                                                        {
                                                            "xtype" : 'textfield',
                                                            "name" : './city',
                                                            "fieldLabel" : CQ.I18n
                                                                    .getMessage("City :"),
                                                            "allowBlank" : false,
                                                            "required" : "true",
                                                            "value" : ""
                                                        }, ]
                                            },
                                            "responseScope" : this,
                                            "success" : appsResponseHandler,
                                            "failure" : appsResponseHandler,
                                            "buttons" : CQ.Dialog.OKCANCEL
                                        }, 'appsConfig-dialog');

                        this.newDialog.form.url = '/bin/services/ads-list';

                        var filterFields = new CQ.Ext.form.ComboBox( {
                            id : 'filterFieldsSelect',
                            name : 'filterFieldsSelect',
                            store : filterFieldsStore,
                            displayField : 'name',
                            valueField : 'value',
                            value : 'Select Filter',
                            typeAhead : true,
                            editable : false,
                            mode : 'local',
                            triggerAction : 'all'
                        });

                        var tb = new CQ.Ext.Toolbar(
                                {
                                    "id" : "cq-feedimporter-toolbar",
                                    "items" : [
                                            {
                                                "xtype" : "button",
                                                "text" : CQ.I18n
                                                        .getMessage("Refresh"),
                                                "tooltip" : CQ.I18n
                                                        .getMessage("Updates the list of appsConfigNodes configurations"),
                                                "handler" : function() {
                                                    CQ.Ext
                                                            .getCmp('cq-appsconfig-grid').store
                                                            .reload();
                                                }
                                            },
                                            {
                                                "xtype" : "tbseparator"
                                            },
                                            editAction,
                                            {
                                                "xtype" : "tbseparator"
                                            },
                                            {
                                                xtype : 'label',
                                                text : 'Filter Field',
                                                style : 'padding-left:5px;padding-right:5px'
                                            },
                                            filterFields,
                                            {
                                                "xtype" : "tbseparator"
                                            },
                                            {
                                                xtype : 'label',
                                                style : 'padding-left:5px;padding-right:5px',
                                                text : 'Type Filter String Here'
                                            },

                                            filterTextBox

                                    ]
                                });

                        CQ.wcm.AdvertisersAppsConfigManager.superclass.constructor
                                .call(
                                        this,
                                        {
                                            "id" : "cq-appsconfig-wrapper",
                                            "renderTo" : "CQ",
                                            "layout" : "form",
                                            "items" : [ {
                                                xtype : "panel",
                                                layout : "fit",
                                                width : "100%",
                                                height : "595",
                                                tbar : tb,
                                                items : [ {
                                                    "xtype" : "grid",
                                                    "id" : "cq-appsconfig-grid",
                                                    "region" : "center",
                                                    "margins" : "5 5 5 5",
                                                    "loadMask" : true,
                                                    "stripeRows" : true,
                                                    "cm" : cm,
                                                    "store" : store,
                                                    "sm" : new CQ.Ext.grid.RowSelectionModel(
                                                            {
                                                                multiSelect : true,
                                                                listeners : {
                                                                    selectionchange : function(
                                                                            selectionModel) {
                                                                        editAction
                                                                                .setDisabled(!(selectionModel
                                                                                        .hasSelection() && canModifyAppsconfig));
                                                                    }
                                                                }
                                                            })
                                                } ]
                                            } ]
                                        });
                    },

                    syncSource : function() {
                        var dialog = this.newDialog;
                    },

                    initComponent : function() {
                        CQ.wcm.AdvertisersAppsConfigManager.superclass.initComponent
                                .call(this);
                    }
                });

var appsResponseHandler = function(form, opts) {
    var info = CQ.HTTP.eval(opts.response);
    if (info.success == true) {
        CQ.Ext.getCmp('cq-appsconfig-grid').store.reload();
    } else {
        CQ.Ext.Msg.alert(CQ.I18n.getMessage("Error"), CQ.I18n
                .getMessage("Could not create appsConfigNode configuration!"));
    }
};

CQ.Ext.reg("advertisersconfigmanager", CQ.wcm.AdvertisersAppsConfigManager);
