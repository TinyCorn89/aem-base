/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.api.WCMException;
import com.day.cq.wcm.core.stats.PageViewStatistics;
import com.day.cq.wcm.foundation.Navigation;
import com.tc.action.BaseAction;
import com.tc.model.LongArticleDetailsBean;
import com.tc.model.ShortArticleBean;
import com.tc.util.Constants;
import com.tc.util.SchedulerUtil;

import java.util.Calendar;

/**
 * The Class ShortArticleAction.
 *
 * @author Ramakrishna
 */
public class ShortArticleAction extends BaseAction {

    /**
     * The log.
     */
    private Logger log = LoggerFactory.getLogger(ShortArticleAction.class);

    private Navigation navigationElements = null;

    /**
     * gets the properties from node and sets to bean.
     *
     * @return shortArticleBean
     */
    public ShortArticleBean shortArticle() {
        Long timeInterval;
        String selectedOption = null;
        Integer sizeOfList = 0;
        String pageImpressionCount;
        String rootPath = null;
        String buttonText = null;
        String showImpressionFor = null;
        Boolean showImpressions = null;
        String title = null;
        LongArticleDetailsBean longArticleDetailsBean = null;
        Calendar nodeTime = null;
        String timeUnit = null;
        Long timeDifference;
        Integer index = null;
        Integer noOfArticles = 1;
        ShortArticleBean shortArticleBean = null;
        List<LongArticleDetailsBean> articlesList;
        String depth = null;
        Node shortArticleCurrentNode = null;
        try {
            shortArticleBean = new ShortArticleBean();
            articlesList = new ArrayList<LongArticleDetailsBean>();
            shortArticleCurrentNode = getCurrentNode();
            selectedOption = shortArticleCurrentNode.getProperty(Constants.SHORTARTICLE.SELECTED_TAB).getString();
            if (shortArticleCurrentNode.hasProperty(Constants.SHORTARTICLE.SHOW_IMPRESSIONS)) {
                showImpressions = shortArticleCurrentNode.getProperty(Constants.SHORTARTICLE.SHOW_IMPRESSIONS).getBoolean();
                if (showImpressions) {
                    shortArticleBean.setShowImpressions(showImpressions);
                }
            }
            if (shortArticleCurrentNode.hasProperty(Constants.SHORTARTICLE.SHOW_IMPRESSIONS_FOR)) {
                if (StringUtils.isNotBlank(shortArticleCurrentNode.getProperty(Constants.SHORTARTICLE.SHOW_IMPRESSIONS_FOR)
                        .getString())) {
                    showImpressionFor = shortArticleCurrentNode.getProperty(Constants.SHORTARTICLE.SHOW_IMPRESSIONS_FOR)
                            .getString();
                }
            }
            if (shortArticleCurrentNode.hasProperty(Constants.SHORTARTICLE.BUTTON_TEXT)) {
                buttonText = shortArticleCurrentNode.getProperty(Constants.SHORTARTICLE.BUTTON_TEXT).getString();
                if (StringUtils.isNotBlank(buttonText)) {
                    shortArticleBean.setButtonText(buttonText);
                }
            }

            // general
            if (selectedOption.equals(Constants.SHORTARTICLE.GENERAL)) {
                if (shortArticleCurrentNode.hasProperty(Constants.SHORTARTICLE.LINK)) {
                    if (StringUtils.isNotBlank(shortArticleCurrentNode.getProperty(Constants.SHORTARTICLE.LINK).getString())) {
                        rootPath = shortArticleCurrentNode.getProperty(Constants.SHORTARTICLE.LINK).getString();
                    }
                    // get Page
                    Page rootPage = getPage(rootPath);
                    Node longArticleNode = getResource(rootPath + Constants.SHORTARTICLE.lONGARTICLE_JCR_CONTENT_NODE)
                            .adaptTo(Node.class);
                    longArticleDetailsBean = getArticlePageDetails(longArticleNode, rootPage.getParent().getName());
                    longArticleDetailsBean.setLink(shortArticleCurrentNode.getProperty(Constants.SHORTARTICLE.LINK)
                            .getString() + Constants.SHORTARTICLE.SHORT_DOT_HTML);
                    // page count
                    if (StringUtils.isNotBlank(showImpressionFor)) {
                        pageImpressionCount = getImpressionCount(rootPage, showImpressionFor);
                        if (StringUtils.isNotBlank(pageImpressionCount)) {
                            longArticleDetailsBean.setPageImpressionCount(pageImpressionCount);
                        }
                    }
                }
                articlesList.add(longArticleDetailsBean);
                index = articlesList.size() - 1;
            } else if (selectedOption.equals(Constants.SHORTARTICLE.ADVANCED)) {
                Calendar calendar = Calendar.getInstance();
                if (!shortArticleCurrentNode.hasProperty(Constants.SHORTARTICLE.TIME) || shortArticleCurrentNode.hasProperty("modifyDate")) {
					//shortArticleCurrentNode.setProperty(Constants.SHORTARTICLE.TIME, calendar);
                    //shortArticleCurrentNode.setProperty("modifyDate", (Value)null);
                    //shortArticleCurrentNode.save();
                }
                nodeTime = shortArticleCurrentNode.getProperty(Constants.SHORTARTICLE.TIME).getDate();

                if (shortArticleCurrentNode.hasProperty(Constants.SHORTARTICLE.ARTICLE_FOLDER_PATH)) {
                    if (StringUtils.isNotBlank(shortArticleCurrentNode.getProperty(Constants.SHORTARTICLE.ARTICLE_FOLDER_PATH).getString())) {
                        rootPath = shortArticleCurrentNode.getProperty(Constants.SHORTARTICLE.ARTICLE_FOLDER_PATH).getString();
                    }
                    if (shortArticleCurrentNode.hasProperty("depth")) {
                        if (StringUtils.isNotBlank(shortArticleCurrentNode.getProperty("depth").getString())) {
                            depth = shortArticleCurrentNode.getProperty("depth").getString();
                        }
                    } else {
                        depth = "3";
                    }
                    /*if(getCurrentStyle().get("depth") != null){
                     depth = getCurrentStyle().get("depth", String.class);
                     }
                     else{
                     depth = "3";
                     }*/
                    Integer childDepth = Integer.parseInt(depth);
                    // get Page
                    Page rootPage = getPage(rootPath);
                    // depth
                    PageFilter filter = new PageFilter(getSlingRequest());
                    navigationElements = getNavigation(rootPage, filter, childDepth);

                    List<Page> pagePaths = new ArrayList<Page>();

                    for (Navigation.Element element : navigationElements) {

                        if (element.getType().toString().equals(Constants.SHORTARTICLE.ITEM_BEGIN)) {
                            pagePaths.add(element.getPage());

                        }
                    }

                    Iterator<Page> pageIterator = pagePaths.iterator();
                    while (pageIterator.hasNext()) {
                        Page page = pageIterator.next();
                        title = page.getParent().getName();
                        Node pageNode = page.adaptTo(Node.class);
                        if (pageNode.hasNode(Constants.SHORTARTICLE.JCR_CONTENT)) {
                            Node jcrNode = pageNode.getNode(Constants.SHORTARTICLE.JCR_CONTENT);
                            if (jcrNode.hasNode(Constants.SHORTARTICLE.LEFT_PAR)) {
                                Node leftParNode = jcrNode.getNode(Constants.SHORTARTICLE.LEFT_PAR);
                                if (leftParNode.hasNode(Constants.SHORTARTICLE.LONG_ARTICLE)) {
                                    Node longArticleNode = leftParNode.getNode(Constants.SHORTARTICLE.LONG_ARTICLE);
                                    longArticleDetailsBean = getArticlePageDetails(longArticleNode, title);
                                    longArticleDetailsBean.setLink(page.getPath()
                                            + Constants.SHORTARTICLE.SHORT_DOT_HTML);
                                    // page count
                                    if (StringUtils.isNotBlank(showImpressionFor)) {
                                        pageImpressionCount = getImpressionCount(page, showImpressionFor);

                                        if (StringUtils.isNotBlank(pageImpressionCount)) {
                                            longArticleDetailsBean.setPageImpressionCount(pageImpressionCount);
                                        }
                                    }
                                }
                            }
                        }
                        articlesList.add(longArticleDetailsBean);
                    }
                    sizeOfList = articlesList.size();
                    timeDifference = calendar.getTimeInMillis() - nodeTime.getTimeInMillis();
                    timeInterval = Long.parseLong(shortArticleCurrentNode.getProperty(Constants.SHORTARTICLE.TIME_INTERVAL)
                            .getString());
                    timeUnit = shortArticleCurrentNode.getProperty(Constants.SHORTARTICLE.TIMEUNIT).getString();
                    index = SchedulerUtil.getIndex(timeDifference, timeInterval, timeUnit, sizeOfList, noOfArticles);
                }
            }
            if (index == articlesList.size() - 1) {
                shortArticleBean.setErrorMessage(Constants.SHORTARTICLE.ERROR_MESSAGE);
            }
            shortArticleBean.setIndex(index);
            shortArticleBean.setArticles(articlesList);
        } catch (ValueFormatException e) {
            log.debug(e.getMessage());
        } catch (PathNotFoundException e) {
            log.debug(e.getMessage());
        } catch (RepositoryException e) {
            log.debug(e.getMessage());
        }
        return shortArticleBean;
    }

    /**
     * Gets the impression count.
     *
     * @param pagePath the page path
     * @param showImpressionFor the show impression for
     * @return the impression count
     * @throws RepositoryException the repository exception
     */
    public String getImpressionCount(Page pagePath, String showImpressionFor) throws RepositoryException {
        final PageViewStatistics pwSvc = getSling().getService(PageViewStatistics.class);
        String pageImpresionCount = null;
        Object pageStatistics[] = null;
        String day = null;
        String month = null;
        String year = null;
        Node statsNode = null;
        try {
            if (pwSvc != null) {
                if (pwSvc.report(pagePath) != null) {
                    pageStatistics = pwSvc.report(pagePath);
                    String pageStatisticsPath = pageStatistics[0].toString();
                    String monthString = pageStatisticsPath.substring(0, pageStatisticsPath.length() - 3);
                    Resource resource = getResource(monthString);
                    if (resource != null) {
                        statsNode = resource.adaptTo(Node.class);
                        month = statsNode.getProperty(Constants.SHORTARTICLE.VIEWS).getString();
                    } else {
                        pageImpresionCount = Integer.toString(0);
                    }
                    day = pageStatistics[1].toString();
                    year = pageStatistics[2].toString();
                    if (Constants.SHORTARTICLE.DAY.equalsIgnoreCase(showImpressionFor)) {
                        pageImpresionCount = day;
                    } else if (Constants.SHORTARTICLE.MONTH.equalsIgnoreCase(showImpressionFor)) {
                        pageImpresionCount = month;
                    } else if (Constants.SHORTARTICLE.YEAR.equalsIgnoreCase(showImpressionFor)) {
                        pageImpresionCount = year;
                    }
                } else {
                    pageImpresionCount = Integer.toString(0);
                }
            }

        } catch (WCMException e) {

            e.printStackTrace();
        }
        return pageImpresionCount;
    }

    /**
     * Gets the page.
     *
     * @param path the path
     * @return the page
     */
    public Page getPage(String path) {
        Resource rootResource = getResource(path);
        Page rootPage = rootResource == null ? null : rootResource.adaptTo(Page.class);
        return rootPage;
    }

    public Navigation getNavigation(Page rootPage, PageFilter filter, int childDepth) {
        if (navigationElements == null)
            return new Navigation(rootPage, rootPage.getDepth(), filter, childDepth);
        return navigationElements;
    }

    /**
     * Gets the article page details.
     *
     * @param longArticleNode the long article node
     * @param parentTitle the parent title
     * @return the article page details
     */
    public LongArticleDetailsBean getArticlePageDetails(Node longArticleNode, String parentTitle) {
        LongArticleDetailsBean articleBean = new LongArticleDetailsBean();
        String escapeHtmlTags = null;
        String description = null;
        try {
            longArticleNode.setProperty(Constants.SHORTARTICLE.CATEGORY, parentTitle);
            //longArticleNode.save();
            articleBean.setCategory(longArticleNode.getProperty(Constants.SHORTARTICLE.CATEGORY).getString());
            if (longArticleNode.hasProperty(Constants.SHORTARTICLE.TITLE)) {
                if (StringUtils.isNotBlank(longArticleNode.getProperty(Constants.SHORTARTICLE.TITLE).getString())) {
                    articleBean.setTitle(longArticleNode.getProperty(Constants.SHORTARTICLE.TITLE).getString());
                }
            }
            if (longArticleNode.hasProperty(Constants.SHORTARTICLE.FILEREFERENCE)) {
                if (StringUtils.isNotBlank(longArticleNode.getProperty(Constants.SHORTARTICLE.FILEREFERENCE)
                        .getString())) {
                    articleBean.setImagePath(longArticleNode.getProperty(Constants.SHORTARTICLE.FILEREFERENCE)
                            .getString());
                }
            }
            if (longArticleNode.hasProperty("asset")) {
                if (StringUtils.isNotBlank(longArticleNode.getProperty("asset")
                        .getString())) {
                    articleBean.setAsset(longArticleNode.getProperty("asset")
                            .getString());
                }
            }
            if (longArticleNode.hasProperty("show")) {
                if (StringUtils.isNotBlank(longArticleNode.getProperty("show")
                        .getString())) {
                    articleBean.setShow(longArticleNode.getProperty("show")
                            .getString());
                }
            }
            if (longArticleNode.hasProperty(Constants.SHORTARTICLE.DESCRIPTION)) {
                if (StringUtils.isNotBlank(longArticleNode.getProperty(Constants.SHORTARTICLE.DESCRIPTION).getString())) {
                    description = longArticleNode.getProperty(Constants.SHORTARTICLE.DESCRIPTION)
                            .getString();
                    escapeHtmlTags = description.replaceAll("\\<.*?\\>", "");
                    escapeHtmlTags = escapeHtmlTags.replaceAll("&nbsp;", "");
                    articleBean.setDescription(escapeHtmlTags);
                }
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return articleBean;
    }

    public Resource getResource(String path) {
        Resource resource = getSlingRequest().getResourceResolver().getResource(path);
        return resource;

    }

}
