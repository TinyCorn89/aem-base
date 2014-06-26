/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

/**
 * The Class SocialFollowBean.
 */
public class SocialFollowBean implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    private String title;
    private String fbUserid;
    private String twitterUserId;
    private String googlePlusUserId;
    private Boolean isPageFollowers;

    /**
     * @return the isPageFollowers
     */
    public Boolean getIsPageFollowers() {
        return isPageFollowers;
    }

    /**
     * @param isPageFollowers the isPageFollowers to set
     */
    public void setIsPageFollowers(Boolean isPageFollowers) {
        this.isPageFollowers = isPageFollowers;
    }

    /**
     * @return the fbUserid
     */
    public String getFbUserid() {
        return fbUserid;
    }

    /**
     * @param fbUserid the fbUserid to set
     */
    public void setFbUserid(String fbUserid) {
        this.fbUserid = fbUserid;
    }

    /**
     * @return the twitterUserId
     */
    public String getTwitterUserId() {
        return twitterUserId;
    }

    /**
     * @param twitterUserId the twitterUserId to set
     */
    public void setTwitterUserId(String twitterUserId) {
        this.twitterUserId = twitterUserId;
    }

    /**
     * @return the googlePlusUserId
     */
    public String getGooglePlusUserId() {
        return googlePlusUserId;
    }

    /**
     * @param googlePlusUserId the googlePlusUserId to set
     */
    public void setGooglePlusUserId(String googlePlusUserId) {
        this.googlePlusUserId = googlePlusUserId;
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

}
