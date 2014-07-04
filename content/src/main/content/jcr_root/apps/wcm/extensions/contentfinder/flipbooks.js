{
    "tabTip": CQ.I18n.getMessage("Flipbooks"),
    "id": "cfTab-Flipbook",
    "xtype": "contentfindertab",
    "iconCls": "cq-cft-tab-icon flipbooks",
    /*"closable": true,*/
    "ranking": 10,
    "allowedPaths": [
        "/content/*",
        "/etc/scaffolding/*",
        "/etc/commerce/*",
        "/etc/workflow/packages/*"
    ],
    "items": [
        CQ.wcm.ContentFinderTab.getQueryBoxConfig({
            "id": "cfTab-Flipbook-QueryBox",
            /*"height": 95,*/
            "items": [
                CQ.wcm.ContentFinderTab.getSuggestFieldConfig({"url": "/bin/wcm/contentfinder/suggestions.json/content/dam/projects/tc"})
                 /*
                ,
                {
                    "xtype": "combo",
                    "name": "size",
                    "value": CQ.I18n.getMessage("Any format"),
                    "mode": "local",
                    "store": new CQ.Ext.data.SimpleStore({
                        "fields": ["value", "text"],
                        "data": [
                            {
                                "text": CQ.I18n.getMessage("Any Format"),
                                "value": "image"
                            },{
                                "text": CQ.I18n.getMessage("Web Graphics (GIF)"),
                                "value": "image/gif"
                            },{
                                "text": CQ.I18n.getMessage("Photographs (JPEG)"),
                                "value": "image/jpeg"
                            },{
                                "text": CQ.I18n.getMessage("Web Images (PNG)"),
                                "value": "image/png"
                            }
                        ]
                    })
                }
                */
            ]
        }),
        CQ.wcm.ContentFinderTab.getResultsBoxConfig({
            "itemsDDGroups": [CQ.wcm.EditBase.DD_GROUP_ASSET],
            "mergeItemsDDGroups": ["s7media"],
            "itemsDDNewParagraph": {
                "path": "tc/components/content/flipbook",
                "propertyName": "./pdfPath"
            },
            "noRefreshButton": true,
            "tbar": [
                CQ.wcm.ContentFinderTab.REFRESH_BUTTON,
                "->",
                {
                    "toggleGroup": "cfTab-Flipbooks-TG",
                    "enableToggle": true,
                    "toggleHandler": function(button, pressed) {
                        var tab = CQ.Ext.getCmp("cfTab-Flipbook");
                        if (pressed) {
                            tab.dataView.tpl = new CQ.Ext.XTemplate(CQ.wcm.ContentFinderTab.THUMBS_TEMPLATE);
                            tab.dataView.itemSelector = CQ.wcm.ContentFinderTab.THUMBS_ITEMSELECTOR;
                        }
                        if (tab.dataView.store != null) {
                            tab.dataView.refresh();
                        }
                    },
                    "pressed": true,
                    "allowDepress": false,
                    "cls": "cq-btn-thumbs cq-cft-dataview-btn",
                    "iconCls":"cq-cft-dataview-mosaic",
                    "tooltip": {
                        "text": CQ.I18n.getMessage("Mosaic View"),
                        "autoHide":true
                    }
                },
                {
                    "toggleGroup": "cfTab-Flipbooks-TG",
                    "enableToggle": true,
                    "toggleHandler": function(button, pressed) {
                        var tab = CQ.Ext.getCmp("cfTab-Flipbook");
                        if (pressed) {
                            tab.dataView.tpl = new CQ.Ext.XTemplate(CQ.wcm.ContentFinderTab.DETAILS_TEMPLATE);
                            tab.dataView.itemSelector = CQ.wcm.ContentFinderTab.DETAILS_ITEMSELECTOR;
                        }
                        if (tab.dataView.store != null) {
                            tab.dataView.refresh();
                        }
                    },
                    "pressed": false,
                    "allowDepress": false,
                    "cls": "cq-btn-details cq-cft-dataview-btn",
                    "iconCls":"cq-cft-dataview-list",
                    "tooltip": {
                        "text": CQ.I18n.getMessage("List View"),
                        "autoHide": true
                    }
                }
            ]
        },{
            "url": "/bin/wcm/contentfinder/asset/view.json/content/dam/projects/tc"
        }, {
            "baseParams": {
                /*"defaultMimeType": "image"*/
                "mimeType": "application/pdf"
            },
            "autoLoad":false,
            "reader": new CQ.Ext.data.JsonReader({
                "totalProperty": "results",
                "root": "hits",
                "fields": [
                    "name", "path", "title", "mimeType", "ddGroups", "size", "lastModified", "ck", "templateParams", "imageWidth", "imageHeight"
                ],
                "id": "path"
            })
        })
    ]
}