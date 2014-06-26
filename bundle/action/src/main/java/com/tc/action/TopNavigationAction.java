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

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.designer.Style;
import com.tc.action.BaseAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.TopNavigationBean;
import com.tc.util.Constants;

/**
 * The Class TopNavigationAction.
 */
public class TopNavigationAction extends BaseAction {

    /**
     * The log.
     */
    private Logger LOG = FrameworkLogger.getLogger();

    /**
     * Gets the list of levels.
     *
     * @return the list of levels
     */
    public TopNavigationBean getListOfLevels() {
        /**
         * The root path.
         */
        String rootPath = null;

        /**
         * The first level list.
         */
        List<Object> mainList = null;

        /**
         * The second level list.
         */
        List<Object> secondLevelList = null;

        /**
         * The third level list.
         */
        List<Object> thirdLevelList = null;

        /**
         * The first level bean.
         */
        TopNavigationBean mainPageBean = null;

        /**
         * The second level bean.
         */
        TopNavigationBean secondLevelBean = null;

        /**
         * The third level bean.
         */
        TopNavigationBean thirdLevelBean = null;

        /**
         * The root resource.
         */
        Resource rootResource = null;

        Iterator<Page> secondLevelChildren;

        Iterator<Page> thirdLevelChildren;

        Iterator<Page> mainPageChildrens;

        /**
         * The root page.
         */
        Page rootPage = null;
        Node jcrNode = null;
        Node pageNode = null;
       // Style currentNodeStyle = getCurrentStyle();
        TopNavigationBean topNavigationBean = new TopNavigationBean();
        try {
          /*  if(currentNodeStyle.get(Constants.TOPNAVIGATION.PARENTPAGE) == null) {
                rootPath = Constants.ROOT_PAGE;
            }
            else {
                rootPath = currentNodeStyle.get(Constants.TOPNAVIGATION.PARENTPAGE, String.class);
            }
			*/

            final Node currentNode = getCurrentNode();
        	
        	if(currentNode.hasProperty("parentPage")){
        		rootPath=currentNode.getProperty("parentPage").getValue().getString();
        		
        	}
            rootResource = getSlingRequest().getResourceResolver().getResource(rootPath);
            rootPage = rootResource == null ? null : rootResource.adaptTo(Page.class);
            if (rootPage != null) {
                mainPageChildrens = rootPage.listChildren();
                mainList = new ArrayList<Object>();
                while (mainPageChildrens.hasNext()) {
                    Page mainPage = mainPageChildrens.next();
                    mainPageBean = new TopNavigationBean();
                    mainPageBean.setTitle(mainPage.getTitle());
                    mainPageBean.setPath(mainPage.getPath() + Constants.TOPNAVIGATION.HTML);
                    pageNode = mainPage.adaptTo(Node.class);

                    if (pageNode.hasNode(Constants.TOPNAVIGATION.JCRCONTENT)) {
                        jcrNode = pageNode.getNode(Constants.TOPNAVIGATION.JCRCONTENT);
                        if (!jcrNode.hasProperty(Constants.TOPNAVIGATION.HIDEINNAV)) {
                            mainList.add(mainPageBean);
                        }
                    }
                    if (mainPage.listChildren() != null) {
                        secondLevelChildren = mainPage.listChildren();
                        secondLevelList = new ArrayList<Object>();
                        while (secondLevelChildren.hasNext()) {
                            Page secondPage = (Page) secondLevelChildren.next();
                            secondLevelBean = new TopNavigationBean();
                            secondLevelBean.setTitle(secondPage.getTitle());
                            secondLevelBean.setPath(secondPage.getPath() + Constants.TOPNAVIGATION.HTML);
                            pageNode = secondPage.adaptTo(Node.class);
                            if (pageNode.hasNode(Constants.TOPNAVIGATION.JCRCONTENT)) {
                                jcrNode = pageNode.getNode(Constants.TOPNAVIGATION.JCRCONTENT);
                                if (!jcrNode.hasProperty(Constants.TOPNAVIGATION.HIDEINNAV)) {
                                    secondLevelList.add(secondLevelBean);
                                }
                            }
                            if (secondPage.listChildren() != null) {
                                thirdLevelList = new ArrayList<Object>();
                                thirdLevelChildren = secondPage.listChildren();
                                while (thirdLevelChildren.hasNext()) {
                                    thirdLevelBean = new TopNavigationBean();
                                    Page thirdPage = thirdLevelChildren.next();
                                    thirdLevelBean.setTitle(thirdPage.getTitle());
                                    thirdLevelBean.setPath(thirdPage.getPath() + Constants.TOPNAVIGATION.HTML);
                                    pageNode = thirdPage.adaptTo(Node.class);
                                    if (pageNode.hasNode(Constants.TOPNAVIGATION.JCRCONTENT)) {
                                        jcrNode = pageNode.getNode(Constants.TOPNAVIGATION.JCRCONTENT);
                                        if (!jcrNode.hasProperty(Constants.TOPNAVIGATION.HIDEINNAV)) {
                                            thirdLevelList.add(thirdLevelBean);
                                        }
                                    }
                                }
                                secondLevelBean.setListOfTabs(thirdLevelList);
                            }
                        }
                        mainPageBean.setListOfTabs(secondLevelList);
                    }

                }
                topNavigationBean.setListOfTabs(mainList);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return topNavigationBean;

    }
}
