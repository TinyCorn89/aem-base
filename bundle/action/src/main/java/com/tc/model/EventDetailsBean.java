/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

public class EventDetailsBean implements Serializable {


    private static final long serialVersionUID = -3039225131471242381L;
    private String eventTitle;
    private String pdfLink;
    private String htmlLink;
    private String showBold;
    private String eventPresentation;
    private String fromDate;
    private String toDate;

    /**
     * @return the fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the eventTitle
     */
    public String getEventTitle() {
        return eventTitle;
    }

    /**
     * @param eventTitle the eventTitle to set
     */
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    /**
     * @return the pdfLink
     */
    public String getPdfLink() {
        return pdfLink;
    }

    /**
     * @param pdfLink the pdfLink to set
     */
    public void setPdfLink(String pdfLink) {
        this.pdfLink = pdfLink;
    }

    /**
     * @return the htmlLink
     */
    public String getHtmlLink() {
        return htmlLink;
    }

    /**
     * @param htmlLink the htmlLink to set
     */
    public void setHtmlLink(String htmlLink) {
        this.htmlLink = htmlLink;
    }

    /**
     * @return the showBold
     */
    public String getShowBold() {
        return showBold;
    }

    /**
     * @param showBold the showBold to set
     */
    public void setShowBold(String showBold) {
        this.showBold = showBold;
    }

    /**
     * @return the eventPresentation
     */
    public String getEventPresentation() {
        return eventPresentation;
    }

    /**
     * @param eventPresentation the eventPresentation to set
     */
    public void setEventPresentation(String eventPresentation) {
        this.eventPresentation = eventPresentation;
    }
}
