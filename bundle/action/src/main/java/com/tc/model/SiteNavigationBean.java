/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

/**
 * The Class SiteConfigBean.
 */
public class SiteNavigationBean implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 2205306948179671000L;

    /**
     * The root path.
     */
    private String rootPath = null;

    /**
     * The page list.
     */
    private List<Object> pageList = null;

    /**
     * Gets the root path.
     *
     * @return the root path
     */
    public String getRootPath() {
        return rootPath;
    }

    /**
     * Sets the root path.
     *
     * @param rootPath the new root path
     */
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public List<Object> getPageList() {
        return pageList;
    }

    public void setPageList(List<Object> pageList) {
        this.pageList = pageList;
    }

}
