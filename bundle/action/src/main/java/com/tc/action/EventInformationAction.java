/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.tc.action.BaseAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.EventInformationBean;

/**
 * The Class EventInformationAction.
 */
public class EventInformationAction extends BaseAction {

    /**
     * The log.
     */
    private Logger log = FrameworkLogger.getLogger();

    /**
     * Gets the event info.
     *
     * @return the event info
     */
    public EventInformationBean getEventInfo() {
        EventInformationBean eventInformationBean = new EventInformationBean();
        Node node = getCurrentNode();
        String queryParamater = null;
        String city = null;
        String state = null;
        String zipcode = null;
        String country = null;
        String addressForGoogle = null;

        try {
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "title"))) {
                eventInformationBean.setTitle(getNodePropertyValue(node, "title"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "fromDate"))) {
                String dateAndTime = getNodePropertyValue(node, "fromDate");
                String date = dateAndTime.substring(0, dateAndTime.indexOf("T"));
                String time = dateAndTime.substring(dateAndTime.indexOf("T") + 1, dateAndTime.indexOf("."));
                String fromDate = date.concat(" ").concat(time);
                eventInformationBean.setFromDate(fromDate);
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "toDate"))) {
                String dateAndTime = node.getProperty("toDate").getString();
                String date = dateAndTime.substring(0, dateAndTime.indexOf("T"));
                String time = dateAndTime.substring(dateAndTime.indexOf("T") + 1, dateAndTime.indexOf("."));
                String toDate = date.concat(" ").concat(time);
                eventInformationBean.setToDate(toDate);
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "city"))) {
                city = getNodePropertyValue(node, "city");
                eventInformationBean.setCity(city);
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "state"))) {
                state = getNodePropertyValue(node, "state");
                eventInformationBean.setState(state);
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "zipcode"))) {
                zipcode = getNodePropertyValue(node, "zipcode");
                eventInformationBean.setZipcode(zipcode);
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "country"))) {
                country = getNodePropertyValue(node, "country");
                eventInformationBean.setCountry(country);
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "showDirections"))) {
                eventInformationBean.setShowDirections(getNodePropertyValue(node, "showDirections"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "venueName"))) {
                eventInformationBean.setVenueName(getNodePropertyValue(node, "venueName"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "description"))) {
                eventInformationBean.setDescription(getNodePropertyValue(node, "description"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "fileReference"))) {
                eventInformationBean.setImage(getNodePropertyValue(node, "fileReference"));
            }
            if (StringUtils.isNotBlank(city) || StringUtils.isNotBlank(state) || StringUtils.isNotBlank(zipcode)) {
                if (StringUtils.isNotBlank(city)) {
                    queryParamater = city.trim();
                }
                if (StringUtils.isNotBlank(state)) {
                    if (StringUtils.isNotBlank(queryParamater)) {
                        queryParamater = queryParamater + "+" + state.trim();
                    } else {
                        queryParamater = state.trim();
                    }
                }
                if (StringUtils.isNotBlank(country)) {
                    if (StringUtils.isNotBlank(queryParamater)) {
                        queryParamater = queryParamater + "+" + country.trim();
                    } else {
                        queryParamater = country.trim();
                    }
                }
            }
            if (StringUtils.isNotBlank(queryParamater)) {
                addressForGoogle = queryParamater.replaceAll(" ", "+");
                eventInformationBean.setAddressForGoogle(addressForGoogle);
            }

        } catch (PathNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (RepositoryException e) {
            log.error(e.getMessage(), e);
        }

        return eventInformationBean;
    }

}
