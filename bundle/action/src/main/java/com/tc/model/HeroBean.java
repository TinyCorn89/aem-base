/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

/**
 * Helper Bean for Rotating Banner.
 *
 */
public class HeroBean implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Banner Image.
     */
    private String bannerImage;

    /**
     * Banner Category.
     */
    private String bannerCategory;

    /**
     * Banner Title.
     */
    private String bannerTitle;
    /**
     * Banner Description.
     */
    private String bannerDescription;

    /**
     * Button text.
     */
    private String buttonText;

    /**
     * The asset path.
     */
    private String assetPath;

    /**
     * The is video.
     */
    private boolean isVideo;

    /**
     * The video flag.
     */
    private String videoFlag;

    /**
     * The video thumbnail.
     */
    private String videoThumbnail;

    /**
     * The video path.
     */
    private String videoPath;

    /**
     * The asset format.
     */
    private String assetFormat;

    /**
     * Gets the asset format.
     *
     * @return the asset format
     */
    public String getAssetFormat() {
        return assetFormat;
    }

    /**
     * Sets the asset format.
     *
     * @param assetFormat the new asset format
     */
    public void setAssetFormat(String assetFormat) {
        this.assetFormat = assetFormat;
    }

    /**
     * Gets the video path.
     *
     * @return the video path
     */
    public String getVideoPath() {
        return videoPath;
    }

    /**
     * Gets the banner category.
     *
     * @return the banner category
     */
    public String getBannerCategory() {
        return bannerCategory;
    }

    /**
     * Sets the banner category.
     *
     * @param bannerCategory the new banner category
     */
    public void setBannerCategory(String bannerCategory) {
        this.bannerCategory = bannerCategory;
    }

    /**
     * Sets the video path.
     *
     * @param videoPath the new video path
     */
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    /**
     * Open in new window.
     *
     * @return the banner image
     */
    public final String getBannerImage() {
        return bannerImage;
    }

    /**
     * Setter for bannerImage.
     *
     * @param aBannerImage - bannerImage
     */
    public final void setBannerImage(final String aBannerImage) {
        this.bannerImage = aBannerImage;
    }

    /**
     * Getter for bannerTitle.
     *
     * @return bannerTitle - bannerTitle
     */
    public final String getBannerTitle() {
        return bannerTitle;
    }

    /**
     * Setter for banner title.
     *
     * @param aBannerTitle - bannerTitle
     */
    public final void setBannerTitle(final String aBannerTitle) {
        this.bannerTitle = aBannerTitle;
    }

    /**
     * Getter for banner description.
     *
     * @return bannerDescription - bannerDescription
     */
    public final String getBannerDescription() {
        return bannerDescription;
    }

    /**
     * Setter for banner description.
     *
     * @param aBannerDescription - bannerDescription
     */
    public final void setBannerDescription(final String aBannerDescription) {
        this.bannerDescription = aBannerDescription;
    }

    /**
     * Gets the button text.
     *
     * @return the buttonText
     */
    public String getButtonText() {
        return buttonText;
    }

    /**
     * Gets the asset path.
     *
     * @return the assetPath
     */
    public String getAssetPath() {
        return assetPath;
    }

    /**
     * Sets the asset path.
     *
     * @param assetPath the assetPath to set
     */
    public void setAssetPath(String assetPath) {
        this.assetPath = assetPath;
    }

    /**
     * Gets the video thumbnail.
     *
     * @return the videoThumbnail
     */
    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    /**
     * Sets the video thumbnail.
     *
     * @param videoThumbnail the videoThumbnail to set
     */
    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    /**
     * Sets the button text.
     *
     * @param buttonText the buttonText to set
     */
    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    /**
     * Checks if is video.
     *
     * @return the isVideo
     */
    public boolean isVideo() {
        return isVideo;
    }

    /**
     * Sets the video.
     *
     * @param isVideo the isVideo to set
     */
    public void setVideo(boolean isVideo) {
        this.isVideo = isVideo;
    }

    /**
     * Gets the video flag.
     *
     * @return the videoFlag
     */
    public String getVideoFlag() {
        return videoFlag;
    }

    /**
     * Sets the video flag.
     *
     * @param videoFlag the videoFlag to set
     */
    public void setVideoFlag(String videoFlag) {
        this.videoFlag = videoFlag;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HeroBean [bannerImage=" + bannerImage + ", bannerTitle="
                + bannerTitle + ", bannerDescription=" + bannerDescription
                + ", buttonText=" + buttonText + ", assetPath=" + assetPath
                + ", isVideo=" + isVideo + ", videoFlag=" + videoFlag
                + ", videoThumbnail=" + videoThumbnail + ", videoPath=" + videoPath + "]";
    }

}
