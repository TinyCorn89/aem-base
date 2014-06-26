/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import javax.jcr.Node;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.tc.action.BaseAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.SocialFollowBean;
import com.tc.util.Constants;

/**
 * The Class SocialFollowAction.
 */
public class SocialFollowAction extends BaseAction {

    /**
     * The log.
     */
    private Logger log = FrameworkLogger.getLogger();

    /**
     * Gets the list of icons.
     *
     * @return the list of icons
     */
    public SocialFollowBean getListOfIcons() {

        /**
         * The social follow bean.
         */
        SocialFollowBean socialFollowBean = null;
        Node socialFollowNode = null;
        try {
            socialFollowNode = getCurrentNode();
            if (socialFollowNode != null) {
                socialFollowBean = new SocialFollowBean();
                if (socialFollowNode.hasProperty(Constants.SOCIALFOLLOW.TITLE)) {
                    if (StringUtils.isNotBlank(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.TITLE).getString())) {
                        socialFollowBean.setTitle(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.TITLE).getString());
                    }
                }
                if (socialFollowNode.hasProperty(Constants.SOCIALFOLLOW.FACEBOOK)) {
                    if (StringUtils.isNotBlank(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.FACEBOOK).getString())) {
                        socialFollowBean.setFbUserid(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.FACEBOOK).getString());
                    }
                }
                if (socialFollowNode.hasProperty(Constants.SOCIALFOLLOW.TWITTER)) {
                    if (StringUtils.isNotBlank(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.TWITTER).getString())) {
                        socialFollowBean.setTwitterUserId(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.TWITTER).getString());
                    }
                }
                if (socialFollowNode.hasProperty(Constants.SOCIALFOLLOW.GOOGLEPLUS)) {
                    if (StringUtils.isNotBlank(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.GOOGLEPLUS).getString())) {
                        socialFollowBean.setGooglePlusUserId(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.GOOGLEPLUS).getString());
                    }
                }
                if (socialFollowNode.hasProperty(Constants.SOCIALFOLLOW.PAGEFOLLOWERS)) {
                    socialFollowBean.setIsPageFollowers(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.PAGEFOLLOWERS).getBoolean());
                } else {
                    socialFollowBean.setIsPageFollowers(false);
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return socialFollowBean;
    }
}
