/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

/**
 * @class CQ.wcm.AdsAppsConfigManager
 * @extends CQ.Ext.Viewport The.AdsAppsConfigManager lets the user manage
 *          appsConfigNodes
 * @constructor Creates a new.AdsAppsConfigManager.
 * @param {Object}
 *            config The config object
 */

var canModifyAppsconfig = false;

CQ.wcm.AdsAppsConfigManager = function(config) {
    this.constructor.call(this, config);

};

var editFlag = false;

CQ.wcm.AdsAppsConfigManager = CQ.Ext.extend(
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
                                                url : "/bin/services/ads-list?q=ads"
                                            }),
                                    autoLoad : true,
                                    reader : new CQ.Ext.data.JsonReader( {
                                        root : 'result',
                                        totalProperty : 'results',
                                        id : 'path',
                                        fields : [ 'path', 'adId',
                                                'ticketNumber', 'advertiserId', 'publicationDate', 'expirationDate'  ]
                                    })
                                });
                        store.setDefaultSort('path', 'ASC');

                        // column model
                        var cm = new CQ.Ext.grid.ColumnModel(
                                [
                                        new CQ.Ext.grid.RowNumberer(),
                                        {
                                            header : CQ.I18n
                                                    .getMessage("Ad ID"),
                                            dataIndex : 'adId',
                                            width : 200,
                                            sortable : true
                                        },

                                        {
                                            header : CQ.I18n
                                                    .getMessage("Ticket Number"),
                                            dataIndex : 'ticketNumber',
                                            width : 200,
                                            sortable : true
                                        },
                                        {
                                            header : CQ.I18n
                                                    .getMessage("Advertiser ID"),
                                            dataIndex : 'advertiserId',
                                            width : 200,
                                            sortable : true
                                        },
                                        {
                                            header : CQ.I18n
                                                    .getMessage("Publication Date"),
                                            dataIndex : 'publicationDate',
                                            width : 200,
                                            sortable : true
                                        },
                                        {
                                            header : CQ.I18n
                                                    .getMessage("Expiraton Date"),
                                            dataIndex : 'expirationDate',
                                            width : 200,
                                            sortable : true
                                        } ]);
                        // cm.defaultSortable = true;

                        var editAction = new CQ.Ext.Action(
                                {
                                    cls : 'CQ.wcm.AdsAppsConfigManager.edit',
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
                                            var adId = selections[0].json.adId;
                                            var advertiserId = selections[0].json.advertiserId;
                                            var ticketNumber = selections[0].json.ticketNumber;
                                            var publicationDate = selections[0].json.publicationDate;
                                            var expirationDate = selections[0].json.expirationDate;

                                            var field = "";

                                            field = myThis.newDialog.getField("./advertiserId");
                                            field.setValue(advertiserId);

                                            field = myThis.newDialog.getField("./ticketNumber");
                                            field.setValue(ticketNumber);

                                            field = myThis.newDialog.getField("./publicationDate");
                                            field.setValue(publicationDate);

                                            field = myThis.newDialog.getField("./expirationDate");
                                            field.setValue(expirationDate);

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
                                    "name" : "Ad ID",
                                    "value" : "adId"
                                }, {
                                    "name" : "Ticket Number",
                                    "value" : "ticketNumber"
                                }, {
                                    "name" : "Advertiser ID",
                                    "value" : "advertiserId"
                                }, {
                                    "name" : "Publication Date",
                                    "value" : "publicationDate"
                                }, {
                                    "name" : "Expiration Date",
                                    "value" : "expirationDate"
                                } ]
                            }
                        });

                        this.newDialog = CQ.WCM
                                .getDialog(
                                        {
                                            "jcr:primaryType" : "cq:Dialog",
                                            "xtype" : "dialog",
                                            "title" : 'Ads Config Configuration',
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
                                                            "value" : "",
                                                            "validationDelay" : 1000,
                                                            "validator": function(){var publishValue=this.findParentByType('dialog').find("name",
                                                                    this.name)[0].getValue();
                                                            if(publishValue.replace(/^\s+/,'').replace(/\s+jQuery/,'')==''){return false;}
                                                            
                                                            else{return true;}
                                                            }
                                                        },
                                                        {
                                                            "xtype" : 'textfield',
                                                            "name" : './ticketNumber',
                                                            "fieldLabel" : CQ.I18n
                                                                    .getMessage("Ticket Number:"),
                                                            "allowBlank" : false,
                                                            "required" : "true",
                                                            "value" : "",
                                                            "validator": function(){var publishValue=this.findParentByType('dialog').find("name",
                                                                    this.name)[0].getValue();
                                                            if(publishValue.replace(/^\s+/,'').replace(/\s+jQuery/,'')==''){return false;}
                                                            
                                                            else{return true;}
                                                            }
                                                        },
                                                        {
                                                            "xtype" : 'datetime',
                                                            "name" : './publicationDate',
                                                            "fieldLabel" : CQ.I18n
                                                                    .getMessage("Publication Date:"),
                                                            "value" : ""
                                                        },
                                                        {
                                                            "xtype" : 'datetime',
                                                            "name" : './expirationDate',
                                                            "fieldLabel" : CQ.I18n
                                                                    .getMessage("Expiration Date:"),
                                                            "value" : ""
                                                        } ]
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

                        CQ.wcm.AdsAppsConfigManager.superclass.constructor
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
                        CQ.wcm.AdsAppsConfigManager.superclass.initComponent
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

CQ.Ext.reg("adsconfigmanager", CQ.wcm.AdsAppsConfigManager);
