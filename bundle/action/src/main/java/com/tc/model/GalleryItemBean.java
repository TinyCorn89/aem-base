/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

public class GalleryItemBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String itemPath;
    private List<String> itemCategory;
    private String title;
    private String description;
    private String damTitle;
    private String caption;

    public String getDamTitle() {
        return damTitle;
    }

    public void setDamTitle(String damTitle) {
        this.damTitle = damTitle;
    }

    public List<String> getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(List<String> itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemPath() {
        return itemPath;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setItemPath(String itemPath) {
        this.itemPath = itemPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
