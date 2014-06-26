/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.jcr.Node;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.foundation.Navigation;
import com.tc.action.BaseAction;
import com.tc.model.EventPreviewBean;
import com.tc.model.EventPreviewSnapshotBean;

/**
 * @author omprakashn
 *
 */
public class EventPreviewAction extends BaseAction {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(EventPreviewAction.class);
    private Navigation navigationElements = null;
    public EventPreviewBean getEventPreviewDetails() {
        EventPreviewBean eventPreviewBean = new EventPreviewBean();
        Node node = getCurrentNode();
        eventPreviewBean = getNodeValues(node);
        return eventPreviewBean;
    }

    public EventPreviewBean getEventListDetails() {
        EventPreviewBean eventPreviewBean = new EventPreviewBean();
        String rootPath = null;
        String depth = "2";
        try {
            if (getCurrentStyle().get("rootPath") != null) {
                rootPath = getCurrentStyle().get("rootPath", String.class);
            }
            if (getCurrentStyle().get("depth") != null) {
                depth = getCurrentStyle().get("depth", String.class);
            }
            eventPreviewBean = getRootPageDetails(rootPath, depth);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return eventPreviewBean;
    }

    public EventPreviewBean getNodeValues(Node node) {
        EventPreviewBean eventPreviewBean = new EventPreviewBean();
        String rootPath = null;
        String depth = "2";
        try {
            if (getCurrentStyle().get("pathOfEvents") != null) {
                rootPath = getCurrentStyle().get("pathOfEvents", String.class);
            }
            if (getCurrentStyle().get("depth") != null) {
                depth = getCurrentStyle().get("depth", String.class);
            }
            eventPreviewBean = getRootPageDetails(rootPath, depth);
            if (getCurrentStyle().get("eventAllPath") != null) {
                eventPreviewBean.setEventAllPath((getCurrentStyle().get("eventAllPath", String.class)).concat(".html"));
            }
            if (node.hasProperty("noOfEvents")) {
                if (StringUtils.isNotBlank(node.getProperty("noOfEvents").getString())) {
                    eventPreviewBean.setNoOfEvents(Integer.parseInt(node.getProperty("noOfEvents").getString()));
                }
            }
            if (node.hasProperty("title")) {
                if (StringUtils.isNotBlank(node.getProperty("title").getString())) {
                    eventPreviewBean.setTitle(node.getProperty("title").getString());
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return eventPreviewBean;
    }

    public String convert(String dateAndTime) {
        String date = dateAndTime.substring(0, dateAndTime.indexOf("T"));
        String time = dateAndTime.substring(dateAndTime.indexOf("T") + 1, dateAndTime.indexOf("."));
        String fromDate = date.concat(" ").concat(time);
        return fromDate;
    }

    public Date parseDate(String date) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }

    public EventPreviewBean getRootPageDetails(String rootPath, String depth) {
        Resource rootRes = getSlingRequest().getResourceResolver()
                .getResource(rootPath);
        Page rootPage = rootRes == null ? null : rootRes
                .adaptTo(Page.class);
        int childDepth = Integer.parseInt(depth);
        Set<EventPreviewSnapshotBean> treeset = new TreeSet<EventPreviewSnapshotBean>();
        Date currentDate = null;
        EventPreviewSnapshotBean eventPreviewSnapshotBean = null;
        EventPreviewBean eventPreviewBean = new EventPreviewBean();
        PageFilter filter = new PageFilter(getSlingRequest());
        //Navigation nav = null;
        if (rootPage != null) {
        	navigationElements =getNavigation(rootPage, filter, childDepth);
            		//new Navigation(rootPage, rootPage.getDepth(), filter, childDepth);
        }
        List<Page> paths = new ArrayList<Page>();
        for (Navigation.Element e : navigationElements) {
            if (e.getType().toString().equals("ITEM_BEGIN")) {
                paths.add(e.getPage());
            }
        }
        try {
            Page pagePath = null;
            Node childNode = null;
            String linkToDetailsPath = null;
            Node eventDetailsNode = null;
            Date parseDate = null;
            Date date1 = null;
            String dateAndTime = null;
            for (int index = 0; index < paths.size(); index++) {
                currentDate = new Date();
                eventPreviewSnapshotBean = new EventPreviewSnapshotBean();
                pagePath = paths.get(index);
                childNode = pagePath.adaptTo(Node.class);
                linkToDetailsPath = childNode.getPath().concat(".html");
                if (childNode.hasNode("jcr:content")) {
                    eventDetailsNode = childNode.getNode("jcr:content");
                }
                if (eventDetailsNode.hasNode("leftpar")) {
                    eventDetailsNode = eventDetailsNode.getNode("leftpar");
                }
                if (eventDetailsNode.hasNode("event_info")) {
                    eventDetailsNode = eventDetailsNode.getNode("event_info");
                }
                if (StringUtils.isNotBlank(getNodePropertyValue(eventDetailsNode, "toDate"))) {
                    String date = convert(getNodePropertyValue(eventDetailsNode, "toDate"));
                    parseDate = parseDate(date);
                    if (parseDate.getTime() > currentDate.getTime()) {
                        eventPreviewSnapshotBean.setLinkToDetailsPath(linkToDetailsPath);
                        if (StringUtils.isNotBlank(getNodePropertyValue(eventDetailsNode, "title"))) {
                            eventPreviewSnapshotBean.setTitle(getNodePropertyValue(eventDetailsNode, "title"));
                        }
                        if (StringUtils.isNotBlank(getNodePropertyValue(eventDetailsNode, "fromDate"))) {
                            dateAndTime = convert(getNodePropertyValue(eventDetailsNode, "fromDate"));
                            date1 = parseDate(dateAndTime);
                            eventPreviewSnapshotBean.setDate(date1);
                            eventPreviewSnapshotBean.setFromDate(dateAndTime);
                        }
                        if (StringUtils.isNotBlank(getNodePropertyValue(eventDetailsNode, "toDate"))) {
                            dateAndTime = convert(getNodePropertyValue(eventDetailsNode, "toDate"));
                            date1 = parseDate(dateAndTime);
                            eventPreviewSnapshotBean.setToDate(date);
                        }
                        if (StringUtils.isNotBlank(getNodePropertyValue(eventDetailsNode, "venueName"))) {
                            eventPreviewSnapshotBean.setVenue(getNodePropertyValue(eventDetailsNode, "venueName"));
                        }
                        if (StringUtils.isNotBlank(getNodePropertyValue(eventDetailsNode, "city"))) {
                            String shortAddress = "";
                            shortAddress = shortAddress + getNodePropertyValue(eventDetailsNode, "city");
                            if (StringUtils.isNotBlank(getNodePropertyValue(eventDetailsNode, "state"))) {
                                shortAddress = shortAddress.concat(",").concat(getNodePropertyValue(eventDetailsNode, "state"));
                            }
                            eventPreviewSnapshotBean.setShortAddress(shortAddress);
                        }
                        if (StringUtils.isNotBlank(getNodePropertyValue(eventDetailsNode, "description"))) {
                            String str = getNodePropertyValue(eventDetailsNode, "description");
                            String substring = str.replaceAll("\\<.*?\\>", "");
                            /*if(str.contains("<p>&nbsp;</p>"))
                             {
                             substring = str.substring(17);
                             }else{
                             substring =str;
                             }*/
                            String[] temp;
                            String delimiter = "\\ ";
                            temp = substring.split(delimiter);
                            String desc = "";
                            if (temp.length > 12) {
                                for (int index1 = 0; index1 < 12; index1++) {
                                    desc = desc.concat(temp[index1]).concat(" ");
                                }
                            } else {
                                for (int index1 = 0; index1 < temp.length; index1++) {
                                    desc = desc.concat(temp[index1]).concat(" ");
                                }
                            }
                            eventPreviewSnapshotBean.setShortDescription(desc);
                        }
                        if (StringUtils.isNotBlank(getNodePropertyValue(eventDetailsNode, "fileReference"))) {
                            eventPreviewSnapshotBean.setImagePath(getNodePropertyValue(eventDetailsNode, "fileReference"));
                        }
                        treeset.add(eventPreviewSnapshotBean);
                    }
                }

            }
            eventPreviewBean.setEventPreviewDetails(treeset);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return eventPreviewBean;

    }
    
    public Navigation getNavigation(Page rootPage, PageFilter filter, int childDepth) {
        if (navigationElements == null)
            return new Navigation(rootPage, rootPage.getDepth(), filter, childDepth);
        return navigationElements;
    }
    

}
