/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class NewsBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String Url;
    private String noOfItems;
    private String showDate;
    private String showDescription;
    private String descripitonCharacterLimit;
    private String header;
    private Date endTime;
    private String divId;
    private Boolean seeAll;

    /**
     * @return the seeAll
     */
    public Boolean getSeeAll() {
        return seeAll;
    }

    /**
     * @param seeAll the seeAll to set
     */
    public void setSeeAll(Boolean seeAll) {
        this.seeAll = seeAll;
    }

    /**
     * @return the divId
     */
    public String getDivId() {
        return divId;
    }

    /**
     * @param divId the divId to set
     */
    public void setDivId(String divId) {
        this.divId = divId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescripitonCharacterLimit() {
        return descripitonCharacterLimit;
    }

    public void setDescripitonCharacterLimit(String descripitonCharacterLimit) {
        this.descripitonCharacterLimit = descripitonCharacterLimit;
    }

    public String getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(String noOfItems) {
        this.noOfItems = noOfItems;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getShowDescription() {
        return showDescription;
    }

    public void setShowDescription(String showDescription) {
        this.showDescription = showDescription;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return Url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        Url = url;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "NewsBean [Url=" + Url + ", noOfItems=" + noOfItems
                + ", showDate=" + showDate + ", showDescription="
                + showDescription + ", descripitonCharacterLimit="
                + descripitonCharacterLimit + "]";
    }

}
