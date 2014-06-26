/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.tc.action.BaseAction;
import com.tc.model.SectionNavigationBean;
import com.tc.util.Constants;

/**
 * The Class SmartNavigationAction.
 */
public class SectionNavigationAction extends BaseAction {

    /**
     * The log.
     */
    private Logger log = LoggerFactory.getLogger(SectionNavigationAction.class);

    /**
     * Section tab.
     *
     * @return the section tab bean
     */
    public SectionNavigationBean sectionNavigation() {
        log.info("Entering execute method of SectionNavigationAction ");
        List<Object> listOfTabs;
        String parentPage = null;
        SectionNavigationBean tabBean = null;
        String scrollingId = null;
        SectionNavigationBean sectionTabBean = new SectionNavigationBean();
        parentPage = getProperty(Constants.SMARTNAVIGATION.SMART_NAVIGATION_PARENT_PAGE);
        if (StringUtils.isNotBlank(parentPage)) {
            Page sourcePage = getCurrentPage().getPageManager().getPage(parentPage);
            listOfTabs = getAllTabsUsingParentPage(sourcePage, tabBean, scrollingId);
            sectionTabBean.setListOfTabs(listOfTabs);
            sectionTabBean.setPath(parentPage + Constants.SMARTNAVIGATION.SMART_NAVIGATION_BACKWARD_SLASH);
        }
        return sectionTabBean;
    }

    /**
     * Gets the all tabs using parent page.
     *
     * @param sourcePage the source page
     * @param tabBean the tab bean
     * @param scrollingId the scrolling id
     * @return the all tabs using parent page
     */
    private List<Object> getAllTabsUsingParentPage(Page sourcePage, SectionNavigationBean tabBean, String scrollingId) {
        log.info("In getAllTabsUsingParentPage()");
        List<Object> listOfTabs = new ArrayList<Object>();
        Node sourceNode = sourcePage.adaptTo(Node.class);
        try {
            NodeIterator sourceNodeIterator = sourceNode.getNodes();
            while (sourceNodeIterator.hasNext()) {
                Node sourceChildNode = (Node) sourceNodeIterator.next();
                if (!(sourceChildNode.getName().equals(Constants.SMARTNAVIGATION.SMART_NAVIGATION_JCR_CONTENT))) {
                    scrollingId = sourceChildNode.getName();
                    String pagePath = null;
                    if (sourceChildNode.hasNode(Constants.SMARTNAVIGATION.SMART_NAVIGATION_JCR_CONTENT) && sourceChildNode.getNode(Constants.SMARTNAVIGATION.SMART_NAVIGATION_JCR_CONTENT).hasNode(
                            Constants.SMARTNAVIGATION.SMART_NAVIGATION_PAR_NODE)) {
                        pagePath = Constants.SMARTNAVIGATION.SMART_NAVIGATION_PAGE_PATH;
                    }
                    tabBean = new SectionNavigationBean();
                    tabBean.setScrollingId(scrollingId);
                    tabBean.setPagePath(pagePath);
                    tabBean = getAllSubTabBeansUsingTabName(sourcePage, tabBean, scrollingId);
                    listOfTabs.add(tabBean);
                }
            }
        } catch (PathNotFoundException pathNotFoundException) {
            log.error("PathNotFoundException:::"
                    + pathNotFoundException.getMessage());

        } catch (RepositoryException repositoryException) {
            log.error("RepositoryException:::"
                    + repositoryException.getMessage());
        }
        return listOfTabs;
    }

    /**
     * Gets the all sub tab beans using tab name.
     *
     * @param sourcePage the source page
     * @param tabBean the tab bean
     * @param scrollingId the scrolling id
     * @return the all sub tab beans using tab name
     */
    private SectionNavigationBean getAllSubTabBeansUsingTabName(Page sourcePage, SectionNavigationBean tabBean, String scrollingId) {
        Iterator<Page> childPagesIterator = sourcePage.listChildren();
        while (childPagesIterator.hasNext()) {
            Page childPage = (Page) childPagesIterator.next();
            if (childPage.getName().equals(scrollingId)) {
                tabBean.setPageTitle(childPage.getTitle());
                Iterator<Page> childSubPagesIterator = childPage.listChildren();
                List<Object> subTabList = new ArrayList<Object>();
                while (childSubPagesIterator.hasNext()) {
                    SectionNavigationBean subTab = new SectionNavigationBean();
                    Page subChildPage = (Page) childSubPagesIterator.next();
                    if (null != subChildPage.getTitle()) {
                        subTab.setPageTitle(subChildPage.getTitle());
                        subTabList.add(subTab);
                    }
                }
                tabBean.setListOfTabs(subTabList);
                break;
            }
        }
        return tabBean;
    }
}
