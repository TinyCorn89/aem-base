/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

public class EventInformationBean implements Serializable {

    private static final long serialVersionUID = -8301460860601986436L;

    String title;
    String fromDate;
    String toDate;
    String city;
    String state;
    String zipcode;
    String country;
    String showDirections;
    String addressForGoogle;
    String venueName;
    String description;
    String image;

    /**
     * @return the addressForGoogle
     */
    public String getAddressForGoogle() {
        return addressForGoogle;
    }

    /**
     * @param addressForGoogle the addressForGoogle to set
     */
    public void setAddressForGoogle(String addressForGoogle) {
        this.addressForGoogle = addressForGoogle;
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
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the showDirections
     */
    public String getShowDirections() {
        return showDirections;
    }

    /**
     * @param showDirections the showDirections to set
     */
    public void setShowDirections(String showDirections) {
        this.showDirections = showDirections;
    }

    /**
     * @return the eventName
     */
    public String getVenueName() {
        return venueName;
    }

    /**
     * @param eventName the eventName to set
     */
    public void setVenueName(String venueName) {
        this.venueName = venueName;
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
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

}
