/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.tc.action.BaseAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.MapBean;
import com.tc.util.Constants;

/**
 * The Class MapAction.
 */
public class MapAction extends BaseAction {

    /**
     * The log.
     */
    private Logger log = FrameworkLogger.getLogger();

    /**
     * Gets the map info.
     *
     * @return the map info
     */
    public MapBean getMapInfo() {
        int height = 0;
        int width = 0;
        String location = null;

        try {
            if (getCurrentNode().hasProperty(Constants.MAP.HEIGHT)) {
                height = Integer.parseInt(getCurrentNode().getProperty(Constants.MAP.HEIGHT).getString());

            }
            if (getCurrentNode().hasProperty(Constants.MAP.WIDTH)) {
                width = Integer.parseInt(getCurrentNode().getProperty(Constants.MAP.WIDTH).getString());
            }
            if (getCurrentNode().hasProperty(Constants.MAP.LOCATION)) {
                location = getCurrentNode().getProperty(Constants.MAP.LOCATION).getString();
            }
            if (height == Constants.ZERO) {
                height = Constants.THREE_HUNDRED;
            }
            if (width == Constants.ZERO) {
                width = Constants.NINE_HUNDRED_AND_SIXTY;
            }
            if (StringUtils.isEmpty(location)) {
                location = "virtusa";
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        MapBean mapBean = new MapBean();

        location = fromatLocation(location);

        mapBean.setHeight(height);
        mapBean.setWidth(width);
        mapBean.setLocation(location);

        return mapBean;
    }

    /**
     * Fromat location.
     *
     * @param location the location
     * @return the string
     */
    public String fromatLocation(String location) {
        String s2 = null;
        String s3 = null;

        StringBuilder stringBuilder = new StringBuilder();

        if (location.contains(Constants.MAP.COMMA)) {

            stringBuilder.append(Constants.MAP.EMPTY_STRING);

            StringTokenizer stringTokenizer = new StringTokenizer(location, Constants.MAP.COMMA);
            while (stringTokenizer.hasMoreTokens()) {
                String s1 = stringTokenizer.nextToken();
                if (s1.indexOf(Constants.MAP.SPACE) == Constants.ZERO) {
                    s1 = s1.substring(1, s1.length());
                }
                if (s1.contains(Constants.MAP.SPACE)) {
                    String t[] = s1.split(Constants.MAP.SPACE);
                    s2 = t[0];
                    s3 = t[1];
                    stringBuilder.append(s2).append(Constants.MAP.PLUS).append(s3).append(Constants.MAP.COMMA)
                            .append(Constants.MAP.PLUS);
                } else {
                    stringBuilder.append(s1).append(Constants.MAP.COMMA).append(Constants.MAP.PLUS);
                }
            }

            location = stringBuilder.toString();

        } else if (location.contains(Constants.MAP.SPACE)) {
            stringBuilder.append(Constants.MAP.EMPTY_STRING);
            String s[] = location.split(Constants.MAP.SPACE);

            stringBuilder.append(s[0]).append(Constants.MAP.PLUS).append(s[1]);

            location = stringBuilder.toString();

        }
        if (location.substring(location.length() - 1, location.length()).equals(Constants.MAP.PLUS)) {
            location = location.substring(0, location.length() - 1);
        }
        if (location.substring(location.length() - 1, location.length()).equals(Constants.MAP.COMMA)) {
            location = location.substring(0, location.length() - 1);
        }
        return location;

    }

}
