/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.model;

import java.io.Serializable;

/**
 * AppsConfigBean
 *
 *
 */
public class AppsConfigBean implements Serializable {

    /**
     * To hold Serial Version UID.
     */
    private static final long serialVersionUID = 1L;

    private String propertyName;

    private String authorValue;

    private String publishValue;

    private String path;

    private String nodeName;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getAuthorValue() {
        return authorValue;
    }

    public void setAuthorValue(String authorValue) {
        this.authorValue = authorValue;
    }

    public String getPublishValue() {
        return publishValue;
    }

    public void setPublishValue(String publishValue) {
        this.publishValue = publishValue;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "AppsConfigBean [propertyName=" + propertyName
                + ", authorValue=" + authorValue + ", publishValue="
                + publishValue + ", path=" + path + ", nodeName=" + nodeName
                + "]";
    }

}
