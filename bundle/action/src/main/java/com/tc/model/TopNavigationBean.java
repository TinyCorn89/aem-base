/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

public class TopNavigationBean implements Serializable {


    private static final long serialVersionUID = -6754854163578338367L;
    private List<Object> listOfTabs;
    private String title;
    private String path;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the listOfTabs
     */
    public List<Object> getListOfTabs() {
        return listOfTabs;
    }

    /**
     * @param listOfTabs the listOfTabs to set
     */
    public void setListOfTabs(List<Object> listOfTabs) {
        this.listOfTabs = listOfTabs;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TopNavigationBean [listOfTabs=" + listOfTabs + ", title=" + title + ", path=" + path + "]";
    }

}
