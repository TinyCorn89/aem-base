/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

/**
 * The Class SectionTabBean.
 */
public class SectionNavigationBean implements Serializable {

    /**
     * serial version id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The list of sections.
     */
    private List<Object> listOfTabs;

    /**
     * The path.
     */
    private String path;

    /**
     * The page title.
     */
    private String pageTitle;

    /**
     * The scrolling id.
     */
    private String scrollingId;

    /**
     * The page path.
     */
    private String pagePath;

    public List<Object> getListOfTabs() {
        return listOfTabs;
    }

    public void setListOfTabs(List<Object> listOfTabs) {
        this.listOfTabs = listOfTabs;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getScrollingId() {
        return scrollingId;
    }

    public void setScrollingId(String scrollingId) {
        this.scrollingId = scrollingId;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    /**
     * Gets the path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path.
     *
     * @param path the new path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "SectionNavigationBean [listOfTabs=" + listOfTabs + "]";
    }

}
