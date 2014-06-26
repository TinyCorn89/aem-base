/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.util.Date;

public class EventPreviewSnapshotBean implements Comparable<EventPreviewSnapshotBean> {


    private static final long serialVersionUID = 1L;

    private String title;
    private String fromDate;
    private String shortDescription;
    private String shortAddress;
    private String imagePath;
    private String linkToDetailsPath;
    private String toDate;
    private Date date;
    private String venue;

    /**
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * @param venue the venue to set
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

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
     * @return the shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @param shortDescription the shortDescription to set
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * @return the shortAddress
     */
    public String getShortAddress() {
        return shortAddress;
    }

    /**
     * @param shortAddress the shortAddress to set
     */
    public void setShortAddress(String shortAddress) {
        this.shortAddress = shortAddress;
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
     * @return the linkToDetailsPath
     */
    public String getLinkToDetailsPath() {
        return linkToDetailsPath;
    }

    /**
     * @param linkToDetailsPath the linkToDetailsPath to set
     */
    public void setLinkToDetailsPath(String linkToDetailsPath) {
        this.linkToDetailsPath = linkToDetailsPath;
    }
    /*@Override
     public int compare(EventPreviewSnapshotBean arg0,
     EventPreviewSnapshotBean arg1) {
     String date1 = arg0.getFromDate();
     String date2 = arg1.getFromDate();
     try {
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     Date first = dateFormat.parse(date1);
			
     } catch (ParseException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
     }
     return arg0.getFromDate().compareTo(arg1.getFromDate());
		
     }*/

    @Override
    public int compareTo(EventPreviewSnapshotBean eventPreviewSnapshotBean) {
        // TODO Auto-generated method stub
        return date.compareTo(eventPreviewSnapshotBean.getDate());
    }

    /*@Override
     public int compare(EventPreviewSnapshotBean eventPreviewSnapshotBean1, EventPreviewSnapshotBean eventPreviewSnapshotBean2) {
     String fromDate = eventPreviewSnapshotBean1.getFromDate();
     SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
     try {
     Date date1 = format.parse(fromDate);
     } catch (ParseException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
     }
     // TODO Auto-generated method stub
     return 0;
     }*/
}
