/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

public class LongArticleDetailsBean implements Serializable {

    private static final long serialVersionUID = -3039225131471242381L;
    private String title;
    private String description;
    private String imagePath;
    private String asset;
    private String show;

    /**
     * @return the show
     */
    public String getShow() {
        return show;
    }

    /**
     * @param show the show to set
     */
    public void setShow(String show) {
        this.show = show;
    }

    /**
     * @return the asset
     */
    public String getAsset() {
        return asset;
    }

    /**
     * @param asset the asset to set
     */
    public void setAsset(String asset) {
        this.asset = asset;
    }
    private String category;
    private String link;
    private String pageImpressionCount;

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the pageImpressionCount
     */
    public String getPageImpressionCount() {
        return pageImpressionCount;
    }

    /**
     * @param pageImpressionCount the pageImpressionCount to set
     */
    public void setPageImpressionCount(String pageImpressionCount) {
        this.pageImpressionCount = pageImpressionCount;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

}
