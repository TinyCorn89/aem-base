/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;

import org.slf4j.Logger;

import com.tc.action.BaseAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.CategoryBean;
import com.tc.model.CategoryListBean;
import com.tc.util.Constants;


public class CategoryAction extends BaseAction {

    private Logger log = FrameworkLogger.getLogger();

    public CategoryListBean getCategories() {
        int index = Constants.ONE;

        CategoryListBean categoryListBean = new CategoryListBean();

        List<CategoryBean> listOfCategories = new ArrayList<CategoryBean>();
		//long level = getCurrentStyle().get(Constants.CATEGORIES.LEVEL, 1L);

		//Integer index = (int) (long) level;
        try {
            if (getCurrentNode().hasProperty(Constants.CATEGORIES.LEVEL)) {
                index = Integer.parseInt(getCurrentNode().getProperty(Constants.CATEGORIES.LEVEL).getString());
            }
            if (null != getCurrentPage().getParent(index)) {
                Node node1 = getCurrentPage().getParent(index).adaptTo(Node.class);
                NodeIterator nodeIterator = node1.getNodes();
                while (nodeIterator.hasNext()) {
                    CategoryBean categoryBean = new CategoryBean();
                    Node node4 = nodeIterator.nextNode();
                    if (!node4.getName().equals(Constants.CATEGORIES.JCR_CONTENT)) {
                        categoryBean.setCategoryUrl(node4.getPath());
                        Node node5 = node4.getNode(Constants.CATEGORIES.JCR_CONTENT);
                        categoryBean.setCategoryName(node5.getProperty(Constants.CATEGORIES.JCR_TITLE).getString());
                        if (!node5.hasProperty(Constants.CATEGORIES.HIDEINNAV)) {
                            listOfCategories.add(categoryBean);
                        }

                    }

                }
                Collections.sort(listOfCategories);
                categoryListBean.setListOfCategories(listOfCategories);
            }
        } catch (Exception e) {
            log.debug("Exception" + e.getMessage());
        }
        return categoryListBean;

    }

}
