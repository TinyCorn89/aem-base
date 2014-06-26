/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;

import com.day.cq.wcm.api.designer.Style;
import com.tc.action.BaseAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.AdvancedSearchBean;
import com.tc.model.SearchBean;
import com.tc.model.SimpleSearchBean;

/**
 * The Class SearchAction.
 */
public class SearchAction extends BaseAction {

    /**
     * The logger.
     */
    private Logger logger = FrameworkLogger.getLogger();

    /**
     * Gets the search dialog info.
     *
     * @return the search dialog info
     */
    public SearchBean getSearchDialogInfo() {
        logger.info("Entered in to getSearchDialogInfo of SearchAction class");
        SearchBean searchBean = new SearchBean();
        SimpleSearchBean simpleSearchBean = null;
        AdvancedSearchBean advanceSearchBean = null;
        try {
            Style style = getCurrentStyle();
            simpleSearchBean = getSimpleSearchBeanDetails();
            advanceSearchBean = getAdvanceSearchBeanDetails();
            if (style != null) {
                String contentPage = getStylePropertyValue(style, "contentPage");
                if (StringUtils.isNotBlank(contentPage)) {
                    searchBean.setContentPath(contentPage);
                }
                String damPage = getStylePropertyValue(style, "damPage");
                if (StringUtils.isNotBlank(damPage)) {
                    searchBean.setDamPath(damPage);
                }
                String articlePage = getStylePropertyValue(style, "articlePage");
                if (StringUtils.isNotBlank(articlePage)) {
                    searchBean.setArticlePath(articlePage);
                }
                String resultPage = getStylePropertyValue(style, "resultPath");
                if (StringUtils.isNotBlank(resultPage)) {
                    searchBean.setResultPath(resultPage);
                } else {
                    searchBean.setResultPath(getCurrentPage().getPath());
                }
            }
            searchBean.setCurrentIndex("0");
            searchBean.setSimpleSearchBean(simpleSearchBean);
            searchBean.setAdvancedSearchBean(advanceSearchBean);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in getSearchDialogInfo method ", e);
        }

        return searchBean;
    }

    /**
     * Gets the simple search dialog info.
     *
     * @return the simple search dialog info
     * @throws Exception the exception
     */
    public SimpleSearchBean getSimpleSearchDialogInfo() throws Exception {

        SimpleSearchBean simpleSearchBean = new SimpleSearchBean();
        Style style = getCurrentStyle();
        if (style != null) {
            if (StringUtils.isNotBlank(getStylePropertyValue(style, "searchIn"))) {
                simpleSearchBean.setSearchIn(getStylePropertyValue(style, "searchIn"));
            }
            if (StringUtils.isNotBlank(getStylePropertyValue(style, "resultPath"))) {
                simpleSearchBean.setResultPath(getStylePropertyValue(style, "resultPath"));
            }
            if (StringUtils.isNotBlank(getStylePropertyValue(style, "advancePage"))) {
                simpleSearchBean.setAdvancePage(getStylePropertyValue(style, "advancePage"));
            }
        }
        return simpleSearchBean;
    }

    /**
     * Gets the simple search bean details.
     *
     * @return the simple search bean details
     * @throws Exception the exception
     */
    private SimpleSearchBean getSimpleSearchBeanDetails() throws Exception {
        SimpleSearchBean simpleSearchBean = new SimpleSearchBean();
        Node node = getCurrentNode();
        if (node != null) {
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "nextText"))) {
                simpleSearchBean.setNextText(getNodePropertyValue(node, "nextText"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "searchIn"))) {
                simpleSearchBean.setSearchIn(getNodePropertyValue(node, "searchIn"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "searchButtonText"))) {
                simpleSearchBean.setSearchButtonText(getNodePropertyValue(node, "searchButtonText"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "statisticsText"))) {
                simpleSearchBean.setStatisticsText(getNodePropertyValue(node, "statisticsText"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "noResultsText"))) {
                simpleSearchBean.setNoResultsText(getNodePropertyValue(node, "noResultsText"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "spellcheckText"))) {
                simpleSearchBean.setSpellcheckText(getNodePropertyValue(node, "spellcheckText"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "similarPagesText"))) {
                simpleSearchBean.setSimilarPagesText(getNodePropertyValue(node, "similarPagesText"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "relatedSearchesText"))) {
                simpleSearchBean.setRelatedSearchesText(getNodePropertyValue(node, "relatedSearchesText"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "searchTrendsText"))) {
                simpleSearchBean.setSearchTrendsText(getNodePropertyValue(node, "searchTrendsText"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "resultPagesText"))) {
                simpleSearchBean.setResultPagesText(getNodePropertyValue(node, "resultPagesText"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "previousText"))) {
                simpleSearchBean.setPreviousText(getNodePropertyValue(node, "previousText"));
            }
        }
        return simpleSearchBean;
    }

    /**
     * Gets the advance search bean details.
     *
     * @return the advance search bean details
     * @throws Exception the exception
     */
    private AdvancedSearchBean getAdvanceSearchBeanDetails() throws Exception {
        AdvancedSearchBean advancedSearchBean = new AdvancedSearchBean();
        Node node = getCurrentNode();

        if (node != null) {
            if (node.hasNode("section")) {
                Node childNodes = (Node) node.getNode("section");
                logger.info("Name " + childNodes.getName());
                if (childNodes.getName().equals("section")) {
                    advancedSearchBean.setSectionMenus(getNodeProperties(childNodes));
                    advancedSearchBean.setSection("true");
                    //advancedSearchBean.setSectionMenus(list);
                }
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "timeRange"))) {
                advancedSearchBean.setTimeRange(getNodePropertyValue(node, "timeRange"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "keyword"))) {
                advancedSearchBean.setKeyword(getNodePropertyValue(node, "keyword"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "author"))) {
                advancedSearchBean.setAuthor(getNodePropertyValue(node, "author"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "contentType"))) {
                advancedSearchBean.setContentType(getNodePropertyValue(node, "contentType"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "section"))) {
                advancedSearchBean.setSection(getNodePropertyValue(node, "section"));
            }
        }
        return advancedSearchBean;
    }

    private List<Object> getNodeProperties(Node outerNode) {
        logger.info("entered into the getNodeProperties method of SearchAction :");
        List<Object> list = new ArrayList<Object>();
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            NodeIterator nodeit = outerNode.getNodes();
            while (nodeit.hasNext()) {
                responseMap = new HashMap<String, Object>();
                Node innerNode = (Node) nodeit.next();
                Resource resource = getSlingRequest().getResourceResolver().getResource(innerNode.getPath());
                ValueMap nodePropertiesMap = resource.adaptTo(ValueMap.class);
                responseMap.put("name", nodePropertiesMap.get("sectionName", ""));
                responseMap.put("path", nodePropertiesMap.get("sectionURL", ""));
                list.add(responseMap);
            }

        } catch (Exception exception) {
            logger.error("exception occured in getNodeProperties method of SearchAction :" + exception.getMessage());
        }
        return list;
    }

}
