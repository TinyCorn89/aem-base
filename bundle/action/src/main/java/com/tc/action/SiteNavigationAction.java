/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.action.BaseAction;
import com.tc.model.SiteNavigationBean;
import com.tc.util.Constants;

public class SiteNavigationAction extends BaseAction {

    private Logger log = LoggerFactory.getLogger(SiteNavigationAction.class);

    public SiteNavigationBean getSiteNavigationInfo() {
        log.info("  Inside of getSiteNavigationInfo of SiteNavigationAction class ");
        String rootPath = null;
        SiteNavigationBean siteConfigBean = new SiteNavigationBean();
        List<Object> list = new LinkedList<Object>();
        Map<String, Object> childPages = null;
        try {
        	final Node currentNode = getCurrentNode();
        	if(currentNode.hasProperty("pageURL")){
        		rootPath=currentNode.getProperty("pageURL").getValue().getString();
        	}

        	//rootPath = (currentNode.getProperties().get("pageURL"));
            if (rootPath != null) {
                log.info("  rootPath  " + rootPath);
                siteConfigBean.setRootPath(rootPath);
                Session session = getSlingRequest().getResourceResolver().adaptTo(Session.class);
                Node configNode = session.getNode(rootPath);
                NodeIterator nodeResultsIt = configNode.getNodes();
                while (nodeResultsIt.hasNext()) {
                    Node selectNode = nodeResultsIt.nextNode();
                    if (!selectNode.getName().equals("jcr:content")) {
                        if (selectNode.hasNodes()) {
                            childPages = new LinkedHashMap<String, Object>();
                            Node mainJCRNode = selectNode.getNode("jcr:content");
                            if (!mainJCRNode.hasProperty("hideInNav")) {
                                childPages.put(mainJCRNode.getProperty("jcr:title").getString(), selectNode.getPath());
                                NodeIterator childNodeResultsIt = selectNode.getNodes();
                                while (childNodeResultsIt.hasNext()) {
                                    Node childSelectNode = childNodeResultsIt.nextNode();
                                    // Added additional node filter for rep:policy
                                    if (!childSelectNode.getName().equals("jcr:content") && !childSelectNode.getName().equals("rep:policy")) {
                                        Node childJCRNode = childSelectNode.getNode("jcr:content");
										if(childJCRNode != null && !childJCRNode.hasProperty("hideInNav")){
                                            childPages.put(childJCRNode.getProperty("jcr:title").getString(), childSelectNode.getPath());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(childPages != null) {
						list.add(childPages);
					}
                }
            }
            siteConfigBean.setPageList(list);
        } catch (Exception e) {
			log.error("SiteNavigationAction - Exception occurred : ", e);
        }
        return siteConfigBean;
    }
}
