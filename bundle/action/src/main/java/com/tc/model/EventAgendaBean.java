/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

public class EventAgendaBean implements Serializable {

   
    private static final long serialVersionUID = -3039225131471242381L;
    private String title;
    private String[] eventDays;
    private List<EventDetailsBean> eventDetailsBeans;

    /**
     * @return the eventDetailsBeans
     */
    public List<EventDetailsBean> getEventDetailsBeans() {
        return eventDetailsBeans;
    }

    /**
     * @param eventDetailsBeans the eventDetailsBeans to set
     */
    public void setEventDetailsBeans(List<EventDetailsBean> eventDetailsBeans) {
        this.eventDetailsBeans = eventDetailsBeans;
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
     * @return the eventDays
     */
    public String[] getEventDays() {
        return eventDays;
    }

    /**
     * @param eventDays the eventDays to set
     */
    public void setEventDays(String[] eventDays) {
        this.eventDays = eventDays;
    }

}
