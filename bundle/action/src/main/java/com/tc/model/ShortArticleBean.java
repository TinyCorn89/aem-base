/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

public class ShortArticleBean implements Serializable {

    private static final long serialVersionUID = -3039225131471242381L;
    private String buttonText;
    private boolean showImpressions;
    private String showImpressionFor;

    /**
     * @return the showImpressionFor
     */
    public String getShowImpressionFor() {
        return showImpressionFor;
    }

    /**
     * @param showImpressionFor the showImpressionFor to set
     */
    public void setShowImpressionFor(String showImpressionFor) {
        this.showImpressionFor = showImpressionFor;
    }

    private long index;
    private String errorMessage;

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the index
     */
    public long getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(long index) {
        this.index = index;
    }

    private Integer minutes;

    /**
     * @return the minutes
     */
    public Integer getMinutes() {
        return minutes;
    }

    /**
     * @param minutes the minutes to set
     */
    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    private List<LongArticleDetailsBean> articles = null;

    /**
     * @return the articles
     */
    public List<LongArticleDetailsBean> getArticles() {
        return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(List<LongArticleDetailsBean> articles) {
        this.articles = articles;
    }

    /**
     * @return the showImpressions
     */
    public boolean isShowImpressions() {
        return showImpressions;
    }

    /**
     * @param showImpressions the showImpressions to set
     */
    public void setShowImpressions(boolean showImpressions) {
        this.showImpressions = showImpressions;
    }

    /**
     * @return the buttonText
     */
    public String getButtonText() {
        return buttonText;
    }

    /**
     * @param buttonText the buttonText to set
     */
    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

}
