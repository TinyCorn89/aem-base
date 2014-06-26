/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

/**
 * The Class AdvancedSearchBean.
 */
public class AdvancedSearchBean implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -7054908586532906461L;

    /**
     * The section.
     */
    private String section = null;

    /**
     * The keyword.
     */
    private String keyword = null;

    /**
     * The author.
     */
    private String author = null;

    /**
     * The content type.
     */
    private String contentType = null;

    /**
     * The time range.
     */
    private String timeRange = null;

    /**
     * The tab sections.
     */
    private List<Object> sectionMenus = null;

    /**
     * Gets the section.
     *
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * Sets the section.
     *
     * @param section the new section
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     * Gets the keyword.
     *
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Sets the keyword.
     *
     * @param keyword the new keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Gets the author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author.
     *
     * @param author the new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the content type.
     *
     * @return the content type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the content type.
     *
     * @param contentType the new content type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Gets the time range.
     *
     * @return the time range
     */
    public String getTimeRange() {
        return timeRange;
    }

    /**
     * Sets the time range.
     *
     * @param timeRange the new time range
     */
    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    /**
     * Gets the tab section nodes.
     *
     * @return the tab section nodes
     */
    public List<Object> getSectionMenus() {
        return sectionMenus;
    }

    /**
     * Sets the tab section nodes.
     *
     * @param tabSectionNodes the tab section nodes
     */
    public void setSectionMenus(List<Object> tabSectionNodes) {
        this.sectionMenus = tabSectionNodes;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AdvancedSearchBean [section=" + section + ", keyword="
                + keyword + ", author=" + author + ", contentType="
                + contentType + ", timeRange=" + timeRange + ", tabSectionNodes="
                + sectionMenus + "]";
    }

}
