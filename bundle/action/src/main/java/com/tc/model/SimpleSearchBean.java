/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

/**
 * The Class SimpleSearchBean.
 */
public class SimpleSearchBean implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 3278256803283573003L;

    /**
     * The next text.
     */
    private String nextText = null;

    /**
     * The search in.
     */
    private String searchIn = null;

    /**
     * The search button text.
     */
    private String searchButtonText = "Search";

    /**
     * The statistics text.
     */
    private String statisticsText = "Results";

    /**
     * The no results text.
     */
    private String noResultsText = null;

    /**
     * The spellcheck text.
     */
    private String spellcheckText = null;

    /**
     * The similar pages text.
     */
    private String similarPagesText = "Similar Pages";

    /**
     * The related searches text.
     */
    private String relatedSearchesText = null;

    /**
     * The search trends text.
     */
    private String searchTrendsText = null;

    /**
     * The result pages text.
     */
    private String resultPagesText = null;

    /**
     * The previous text.
     */
    private String previousText = null;

    /**
     * The search result path.
     */
    private String advancePage = null;

    /**
     * The search result path.
     */
    private String resultPath = null;

    /**
     * Gets the next text.
     *
     * @return the next text
     */
    public String getNextText() {
        return nextText;
    }

    /**
     * Sets the next text.
     *
     * @param nextText the new next text
     */
    public void setNextText(String nextText) {
        this.nextText = nextText;
    }

    /**
     * Gets the search in.
     *
     * @return the search in
     */
    public String getSearchIn() {
        return searchIn;
    }

    /**
     * Sets the search in.
     *
     * @param searchIn the new search in
     */
    public void setSearchIn(String searchIn) {
        this.searchIn = searchIn;
    }

    /**
     * Gets the search button text.
     *
     * @return the search button text
     */
    public String getSearchButtonText() {
        return searchButtonText;
    }

    /**
     * Sets the search button text.
     *
     * @param searchButtonText the new search button text
     */
    public void setSearchButtonText(String searchButtonText) {
        this.searchButtonText = searchButtonText;
    }

    /**
     * Gets the statistics text.
     *
     * @return the statistics text
     */
    public String getStatisticsText() {
        return statisticsText;
    }

    /**
     * Sets the statistics text.
     *
     * @param statisticsText the new statistics text
     */
    public void setStatisticsText(String statisticsText) {
        this.statisticsText = statisticsText;
    }

    /**
     * Gets the no results text.
     *
     * @return the no results text
     */
    public String getNoResultsText() {
        return noResultsText;
    }

    /**
     * Sets the no results text.
     *
     * @param noResultsText the new no results text
     */
    public void setNoResultsText(String noResultsText) {
        this.noResultsText = noResultsText;
    }

    /**
     * Gets the spellcheck text.
     *
     * @return the spellcheck text
     */
    public String getSpellcheckText() {
        return spellcheckText;
    }

    /**
     * Sets the spellcheck text.
     *
     * @param spellcheckText the new spellcheck text
     */
    public void setSpellcheckText(String spellcheckText) {
        this.spellcheckText = spellcheckText;
    }

    /**
     * Gets the similar pages text.
     *
     * @return the similar pages text
     */
    public String getSimilarPagesText() {
        return similarPagesText;
    }

    /**
     * Sets the similar pages text.
     *
     * @param similarPagesText the new similar pages text
     */
    public void setSimilarPagesText(String similarPagesText) {
        this.similarPagesText = similarPagesText;
    }

    /**
     * Gets the related searches text.
     *
     * @return the related searches text
     */
    public String getRelatedSearchesText() {
        return relatedSearchesText;
    }

    /**
     * Sets the related searches text.
     *
     * @param relatedSearchesText the new related searches text
     */
    public void setRelatedSearchesText(String relatedSearchesText) {
        this.relatedSearchesText = relatedSearchesText;
    }

    /**
     * Gets the search trends text.
     *
     * @return the search trends text
     */
    public String getSearchTrendsText() {
        return searchTrendsText;
    }

    /**
     * Sets the search trends text.
     *
     * @param searchTrendsText the new search trends text
     */
    public void setSearchTrendsText(String searchTrendsText) {
        this.searchTrendsText = searchTrendsText;
    }

    /**
     * Gets the result pages text.
     *
     * @return the result pages text
     */
    public String getResultPagesText() {
        return resultPagesText;
    }

    /**
     * Sets the result pages text.
     *
     * @param resultPagesText the new result pages text
     */
    public void setResultPagesText(String resultPagesText) {
        this.resultPagesText = resultPagesText;
    }

    /**
     * Gets the previous text.
     *
     * @return the previous text
     */
    public String getPreviousText() {
        return previousText;
    }

    /**
     * Sets the previous text.
     *
     * @param previousText the new previous text
     */
    public void setPreviousText(String previousText) {
        this.previousText = previousText;
    }

    /**
     * Gets the advance page.
     *
     * @return the advance page
     */
    public String getAdvancePage() {
        return advancePage;
    }

    /**
     * Sets the advance page.
     *
     * @param advancePage the new advance page
     */
    public void setAdvancePage(String advancePage) {
        this.advancePage = advancePage;
    }

    /**
     * Gets the result path.
     *
     * @return the result path
     */
    public String getResultPath() {
        return resultPath;
    }

    /**
     * Sets the result path.
     *
     * @param resultPath the new result path
     */
    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SimpleSearchBean [nextText=" + nextText + ", searchIn="
                + searchIn + ", searchButtonText=" + searchButtonText
                + ", statisticsText=" + statisticsText + ", noResultsText="
                + noResultsText + ", spellcheckText=" + spellcheckText
                + ", similarPagesText=" + similarPagesText
                + ", relatedSearchesText=" + relatedSearchesText
                + ", searchTrendsText=" + searchTrendsText
                + ", resultPagesText=" + resultPagesText + ", previousText="
                + previousText + "]";
    }

}
