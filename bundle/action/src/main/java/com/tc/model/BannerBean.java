/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

/**
 * Bean to hold the list of pages.
 */
public class BannerBean implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The slider image.
     */
    private String sliderImage;

    /**
     * The category.
     */
    private String category;

    /**
     * The description.
     */
    private String description;

    /**
     * The title.
     */
    private String title;

    /**
     * The image url.
     */
    private String imageUrl;

    /**
     * Gets the slider image.
     *
     * @return the slider image
     */
    public String getSliderImage() {
        return sliderImage;
    }

    /**
     * Sets the slider image.
     *
     * @param sliderImage the new slider image
     */
    public void setSliderImage(String sliderImage) {
        this.sliderImage = sliderImage;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the new category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the image url.
     *
     * @return the image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image url.
     *
     * @param imageUrl the new image url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BannerBean [sliderImage=" + sliderImage + ", category="
                + category + ", description=" + description + ", title="
                + title + ", imageUrl=" + imageUrl + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((category == null) ? 0 : category.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result
                + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = prime * result
                + ((sliderImage == null) ? 0 : sliderImage.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        BannerBean other = (BannerBean) obj;
        if (category == null) {
            if (other.category != null) {
                return false;
            }
        } else if (!category.equals(other.category)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (imageUrl == null) {
            if (other.imageUrl != null) {
                return false;
            }
        } else if (!imageUrl.equals(other.imageUrl)) {
            return false;
        }
        if (sliderImage == null) {
            if (other.sliderImage != null) {
                return false;
            }
        } else if (!sliderImage.equals(other.sliderImage)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        return true;
    }

}
