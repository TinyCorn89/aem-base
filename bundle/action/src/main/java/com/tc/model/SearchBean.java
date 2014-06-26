/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

/**
 * The Class SearchBean.
 */
public class SearchBean implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 5908000482522192145L;

    /**
     * The simple search bean.
     */
    private SimpleSearchBean simpleSearchBean = null;

    /**
     * The advanced search bean.
     */
    private AdvancedSearchBean advancedSearchBean = null;

    /**
     * The content path.
     */
    private String contentPath = null;

    /**
     * The dam path.
     */
    private String damPath = null;

    /**
     * The article path.
     */
    private String articlePath = null;

    /**
     * The search result path.
     */
    private String resultPath = null;

    /**
     * The current index.
     */
    private String currentIndex = "0";

    /**
     * Gets the simple search bean.
     *
     * @return the simple search bean
     */
    public SimpleSearchBean getSimpleSearchBean() {
        return simpleSearchBean;
    }

    /**
     * Sets the simple search bean.
     *
     * @param simpleSearchBean the new simple search bean
     */
    public void setSimpleSearchBean(SimpleSearchBean simpleSearchBean) {
        this.simpleSearchBean = simpleSearchBean;
    }

    /**
     * Gets the advanced search bean.
     *
     * @return the advanced search bean
     */
    public AdvancedSearchBean getAdvancedSearchBean() {
        return advancedSearchBean;
    }

    /**
     * Sets the advanced search bean.
     *
     * @param advancedSearchBean the new advanced search bean
     */
    public void setAdvancedSearchBean(AdvancedSearchBean advancedSearchBean) {
        this.advancedSearchBean = advancedSearchBean;
    }

    /**
     * Gets the content path.
     *
     * @return the content path
     */
    public String getContentPath() {
        return contentPath;
    }

    /**
     * Sets the content path.
     *
     * @param contentPath the new content path
     */
    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    /**
     * Gets the dam path.
     *
     * @return the dam path
     */
    public String getDamPath() {
        return damPath;
    }

    /**
     * Sets the dam path.
     *
     * @param damPath the new dam path
     */
    public void setDamPath(String damPath) {
        this.damPath = damPath;
    }

    /**
     * Gets the article path.
     *
     * @return the article path
     */
    public String getArticlePath() {
        return articlePath;
    }

    /**
     * Sets the article path.
     *
     * @param articlePath the new article path
     */
    public void setArticlePath(String articlePath) {
        this.articlePath = articlePath;
    }

    /**
     * Gets the current index.
     *
     * @return the current index
     */
    public String getCurrentIndex() {
        return currentIndex;
    }

    /**
     * Sets the current index.
     *
     * @param currentIndex the new current index
     */
    public void setCurrentIndex(String currentIndex) {
        this.currentIndex = currentIndex;
    }

    public String getResultPath() {
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SearchBean [simpleSearchBean=" + simpleSearchBean
                + ", advancedSearchBean=" + advancedSearchBean
                + ", contentPath=" + contentPath + ", damPath=" + damPath
                + ", articlePath=" + articlePath + "]";
    }

}
