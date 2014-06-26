/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class EventPreviewBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<EventPreviewSnapshotBean> eventPreviewList;
    private int noOfEvents;
    private Set<EventPreviewSnapshotBean> eventPreviewDetails;
    private String title;
    private String eventAllPath;

    /**
     * @return the eventAllPath
     */
    public String getEventAllPath() {
        return eventAllPath;
    }

    /**
     * @param eventAllPath the eventAllPath to set
     */
    public void setEventAllPath(String eventAllPath) {
        this.eventAllPath = eventAllPath;
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
     * @return the eventPreviewList
     */
    public List<EventPreviewSnapshotBean> getEventPreviewList() {
        return eventPreviewList;
    }

    /**
     * @param eventPreviewList the eventPreviewList to set
     */
    public void setEventPreviewList(List<EventPreviewSnapshotBean> eventPreviewList) {
        this.eventPreviewList = eventPreviewList;
    }

    /**
     * @return the noOfEvents
     */
    public int getNoOfEvents() {
        return noOfEvents;
    }

    /**
     * @param noOfEvents the noOfEvents to set
     */
    public void setNoOfEvents(int noOfEvents) {
        this.noOfEvents = noOfEvents;
    }

    /**
     * @return the eventPreviewDetails
     */
    public Set<EventPreviewSnapshotBean> getEventPreviewDetails() {
        return eventPreviewDetails;
    }

    /**
     * @param eventPreviewDetails the eventPreviewDetails to set
     */
    public void setEventPreviewDetails(
            Set<EventPreviewSnapshotBean> eventPreviewDetails) {
        this.eventPreviewDetails = eventPreviewDetails;
    }

}
