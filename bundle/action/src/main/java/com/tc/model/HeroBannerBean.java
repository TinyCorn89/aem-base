/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

/**
 * HeroBanner Bean -ModelBean for HeroBanner Component.
 *
 */
public class HeroBannerBean implements Serializable {

    /**
     * serial version id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The rotation rate.
     */
    private int rotationRate;

    /**
     * List of Banners.
     */
    private List<HeroBean> bannersList;

    /**
     * The mode.
     */
    private String mode;

    /**
     * The type.
     */
    private String type;

    /**
     * The multiple.
     */
    private boolean multiple;

    /**
     * The multiple.
     */
    private boolean lastList;

    /**
     * Checks if is lastList.
     *
     * @return true, if is lastList
     */
    public boolean isLastList() {
        return lastList;
    }

    /**
     * Sets the lastList.
     *
     * @param lastList the new lastList
     */
    public void setLastList(boolean lastList) {
        this.lastList = lastList;
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

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param mode the new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the mode.
     *
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets the mode.
     *
     * @param mode the new mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * Getter method for rotationRate.
     *
     * @return rotationRate - rotationRate
     */
    public final int getRotationRate() {
        return rotationRate;
    }

    /**
     * Setter method for rotationRate.
     *
     * @param aRotationRate - rotation rate
     */
    public final void setRotationRate(final int aRotationRate) {
        this.rotationRate = aRotationRate;
    }

    /**
     * Getter method for bannerList.
     *
     * @return bannersList - bannersList
     */
    public final List<HeroBean> getBannersList() {
        return bannersList;
    }

    /**
     * Setter method for bannerList.
     *
     * @param aBannersList - bannersList
     */
    public final void setBannersList(final List<HeroBean> aBannersList) {
        this.bannersList = (List<HeroBean>) aBannersList;
    }

    /**
     * String representation of the object.
     *
     * @return String - String representing this object.
     */
    @Override
    public String toString() {
        return "HeroBannerBean [rotationRate=" + rotationRate
                + ", bannersList=" + bannersList + ", mode=" + mode + "]";
    }

}
