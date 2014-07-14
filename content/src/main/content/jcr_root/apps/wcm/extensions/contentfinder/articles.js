{
    "tabTip": CQ.I18n.getMessage("Articles (CP)"),
    "id": "cfTab-Articles",
    "iconCls": "cq-cft-tab-icon flipbooks",
    "xtype": "contentfindertab",
    "ranking": 120,
    "allowedPaths": [
        "/content/tc/*"
    ],
    "items": [
        CQ.wcm.ContentFinderTab.getQueryBoxConfig({
            "id": "cfTab-Articles-QueryBox",
            "items": [
                CQ.wcm.ContentFinderTab.getSuggestFieldConfig({"url": "/bin/wcm/contentfinder/suggestions.json/content/tc/en/cp"})
            ]
        }),
        CQ.wcm.ContentFinderTab.getResultsBoxConfig({
            "itemsDDGroups": [CQ.wcm.EditBase.DD_GROUP_PAGE],
			"itemsDDNewParagraph": {
                "path": "tc/components/content/cp/news-article",
                "propertyName": "./title"
            },
            "noRefreshButton": true,
            "items": {
                "tpl":
                    '<tpl for=".">' +
                            '<div class="cq-cft-search-item" title="{pathEncoded}" ondblclick="CQ.wcm.ContentFinder.loadContentWindow(\'{[CQ.HTTP.encodePath(values.path)]}.html\');">' +
                                    '<div class="cq-cft-search-thumb-top" style="background-image:url(\'{[CQ.wcm.ContentFinderTab.THUMBS_URL(values, 48, 48)]}\');"></div>' +
                                         '<div class="cq-cft-search-text-wrapper">' +
                                            '<div class="cq-cft-search-title">{[CQ.shared.XSS.getXSSTablePropertyValue(values, \"title\")]}</div>' +
                                        '</div>' +
                                    '<div class="cq-cft-search-separator"></div>' +
                            '</div>' +
                    '</tpl>',
                "itemSelector": CQ.wcm.ContentFinderTab.DETAILS_ITEMSELECTOR
            },
            "tbar": [
                CQ.wcm.ContentFinderTab.REFRESH_BUTTON
            ]
        },{
            "url": "/bin/wcm/contentfinder/page/view.json/content/dam/tc/cp"
        }, {
            "baseParams": {
                "type": "cq:Page"
            }
        })
    ]

}