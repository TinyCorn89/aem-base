/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Bean to hold the list of pages.
 */
public class CarouselBean implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * List of Banners.
     */
    private List<BannerBean> bannersList;

    /**
     * The time interval.
     */
    private long timeInterval;

    /**
     * The time units.
     */
    private String timeUnits;

    /**
     * The multiple.
     */
    private boolean multiple;

    /**
     * The last slide.
     */
    private boolean lastSlide;

    /**
     * Checks if is last slide.
     *
     * @return true, if is last slide
     */
    public boolean isLastSlide() {
        return lastSlide;
    }

    /**
     * Sets the last slide.
     *
     * @param lastSlide the new last slide
     */
    public void setLastSlide(boolean lastSlide) {
        this.lastSlide = lastSlide;
    }

    /**
     * The carousel type.
     */
    private String carouselType;

    public String getCarouselType() {
        return carouselType;
    }

    public void setCarouselType(String carouselType) {
        this.carouselType = carouselType;
    }

    /**
     * Gets the banners list.
     *
     * @return the banners list
     */
    public List<BannerBean> getBannersList() {
        return bannersList;
    }

    /**
     * Sets the banners list.
     *
     * @param bannersList the new banners list
     */
    public void setBannersList(List<BannerBean> bannersList) {
        this.bannersList = bannersList;
    }

    /**
     * Gets the time interval.
     *
     * @return the time interval
     */
    public long getTimeInterval() {
        return timeInterval;
    }

    /**
     * Sets the time interval.
     *
     * @param timeInterval the new time interval
     */
    public void setTimeInterval(long timeInterval) {
        this.timeInterval = timeInterval;
    }

    /**
     * Gets the time units.
     *
     * @return the time units
     */
    public String getTimeUnits() {
        return timeUnits;
    }

    /**
     * Sets the time units.
     *
     * @param timeUnits the new time units
     */
    public void setTimeUnits(String timeUnits) {
        this.timeUnits = timeUnits;
    }

    /**
     * Checks if is multiple.
     *
     * @return true, if is multiple
     */
    public boolean isMultiple() {
        return multiple;
    }

    /**
     * Sets the multiple.
     *
     * @param multiple the new multiple
     */
    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((bannersList == null) ? 0 : bannersList.hashCode());
        result = prime * result + (multiple ? 1231 : 1237);
        result = prime * result + (int) (timeInterval ^ (timeInterval >>> 32));
        result = prime * result
                + ((timeUnits == null) ? 0 : timeUnits.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CarouselBean other = (CarouselBean) obj;
        if (bannersList == null) {
            if (other.bannersList != null) {
                return false;
            }
        } else if (!bannersList.equals(other.bannersList)) {
            return false;
        }
        if (multiple != other.multiple) {
            return false;
        }
        if (timeInterval != other.timeInterval) {
            return false;
        }
        if (timeUnits == null) {
            if (other.timeUnits != null) {
                return false;
            }
        } else if (!timeUnits.equals(other.timeUnits)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CarouselBean [bannersList=" + bannersList + ", timeInterval="
                + timeInterval + ", timeUnits=" + timeUnits + ", multiple="
                + multiple + "]";
    }

}
