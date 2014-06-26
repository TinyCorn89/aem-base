/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.tc.action.BaseAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.NewsBean;

/**
 * The Class NewsAction.
 */
public class NewsAction extends BaseAction {

    /**
     * The log.
     */
	//private Logger log =  FrameworkLogger.getLogger();
    /**
     * Gets the news.
     *
     * @return the news
     */
    @SuppressWarnings("deprecation")
    public NewsBean getNews() {
        Node node = getCurrentNode();
        NewsBean newsBean = new NewsBean();
        String rssFeedUrl = null;;
        String header = null;
        String startTime = null;
        String endTime = null;
        Map<Date, List<String>> comparison = new HashMap<Date, List<String>>();
        try {
            List<String> headingAndUrl = null;
            Node feedItemNode = null;
            if (node.hasNode("feeds")) {
                Node feedsNode = node.getNode("feeds");
                if (feedsNode.hasNodes()) {
                    NodeIterator noteIterator = feedsNode.getNodes();
                    SimpleDateFormat formatter = null;
                    Date date = null;
                    String date2 = null;
                    if (!getCurrentNode().hasProperty("count")) {
                        getCurrentNode().setProperty("count", 0);
                        getCurrentNode().save();
                    }
                    while (noteIterator.hasNext()) {
                        headingAndUrl = new ArrayList<String>();
                        feedItemNode = (Node) noteIterator.nextNode();
                        startTime = getNodePropertyValue(feedItemNode, "startTime");
                        endTime = getNodePropertyValue(feedItemNode, "endTime");
                        date = new Date();
                        formatter = new SimpleDateFormat("hh:mm a");
                        date2 = formatter.format(date);
                        Date currentDate = parseDate(date2);
                        Date startTimeParse = parseDate(startTime);
                        Date endTimeParse = parseDate(endTime);
                        if (StringUtils.isNotBlank(getNodePropertyValue(feedItemNode, "rssFeedURL"))) {
                            headingAndUrl.add(getNodePropertyValue(feedItemNode, "rssFeedURL"));
                        }
                        if (StringUtils.isNotBlank(getNodePropertyValue(feedItemNode, "header"))) {
                            headingAndUrl.add(getNodePropertyValue(feedItemNode, "header"));
                        }
                        comparison.put(endTimeParse, headingAndUrl);
                        if (startTimeParse.before(currentDate) && (endTimeParse.after(currentDate) || endTimeParse.equals(currentDate))) {
                            if (getCurrentNode().hasProperty("count")) {
                                getCurrentNode().setProperty("count", 1);
                                getCurrentNode().save();
                            }
                            if (StringUtils.isNotBlank(getNodePropertyValue(feedItemNode, "rssFeedURL"))) {
                                rssFeedUrl = getNodePropertyValue(feedItemNode, "rssFeedURL");
                                newsBean.setUrl(rssFeedUrl);
                            }
                            if (StringUtils.isNotBlank(getNodePropertyValue(feedItemNode, "header"))) {
                                header = getNodePropertyValue(feedItemNode, "header");
                                newsBean.setHeader(header);
                            }
                        }
                    }
                    if (StringUtils.isBlank(rssFeedUrl)) {
                        String count = null;
                        int count1 = 0;
                        if (getCurrentNode().hasProperty("count")) {
                            count = getCurrentNode().getProperty("count").getString();
                            count1 = Integer.parseInt(count);
                        }
                        if (count1 > 0) {
                            int c = 0;
                            Date key1 = null;
                            for (Date key : comparison.keySet()) {
                                Date date1 = new Date();
                                date1.setYear(70);
                                date1.setMonth(0);
                                date1.setDate(1);
                                if (key.getTime() - date1.getTime() < 0) {
                                    if (c == 0) {
                                        key1 = key;
                                        c++;
                                    }
                                    if (key1.getTime() < key.getTime()) {
                                        key1 = key;
                                    }
                                }

                            }
                            if (key1 == null) {
                                key1 = comparison.keySet().iterator().next();
                                for (Date key : comparison.keySet()) {
                                    if (key.getTime() < key1.getTime()) {
                                        key1 = key;
                                    }
                                }
                            }
                            List<String> headingAndUrl1 = comparison.get(key1);
                            /*Iterator<Date> iterator = comparison.keySet().iterator();
                             date = new Date();
                             Date latestTime = comparison.keySet().iterator().next();
                             long min = date.getTime() - latestTime.getTime();
                             while(iterator.hasNext()){
                             Date lastTime = (Date) iterator.next();
                             long difference = (date.getTime()-lastTime.getTime());
                             if(min>difference){
                             min = date.getTime()-lastTime.getTime();
                             latestTime = lastTime;
                             }
								
                             }
                             List<String> headingAndUrl1 = comparison.get(latestTime);*/
                            if (headingAndUrl1 != null) {
                                if (StringUtils.isNotBlank(headingAndUrl1.get(0))) {
                                    newsBean.setUrl(headingAndUrl1.get(0));
                                }
                                if (headingAndUrl1.size() > 1) {
                                    if (StringUtils.isNotBlank(headingAndUrl1.get(1))) {
                                        newsBean.setHeader(headingAndUrl1.get(1));
                                    }
                                }
                            }

                        } else {
                            Date key1 = null;
                            int c = 0;
                            for (Date key : comparison.keySet()) {
                                Date date1 = new Date();
                                date1.setYear(70);
                                date1.setMonth(0);
                                date1.setDate(1);
                                if (key.getTime() - date1.getTime() > 0) {
                                    if (c == 0) {
                                        key1 = key;
                                        c++;
                                    }
                                    if (key1.getTime() > key.getTime()) {
                                        key1 = key;
                                    }
                                }

                            }
                            if (key1 == null) {
                                key1 = comparison.keySet().iterator().next();
                                for (Date key : comparison.keySet()) {
                                    if (key.getTime() < key1.getTime()) {
                                        key1 = key;
                                    }
                                }
                            }
                            List<String> list = comparison.get(key1);
                            if (list != null) {
                                if (StringUtils.isNotBlank(list.get(0))) {
                                    newsBean.setUrl(list.get(0));
                                }
                                if (list.size() > 1) {
                                    if (StringUtils.isNotBlank(list.get(1))) {
                                        newsBean.setHeader(list.get(1));
                                    }
                                }
                            }
                        }

                    }
                }
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "noOfItems"))) {
                newsBean.setNoOfItems(getNodePropertyValue(node, "noOfItems"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "showDate"))) {
                newsBean.setShowDate(getNodePropertyValue(node, "showDate"));
            }
            if (StringUtils.isNotBlank(getNodePropertyValue(node, "showDescription"))) {
                newsBean.setShowDescription(getNodePropertyValue(node, "showDescription"));
            }
            if (node.hasProperty("seeAll")) {
                newsBean.setSeeAll(node.getProperty("seeAll").getBoolean());
            }
        } catch (Exception e) {
            //log.error(e.getMessage(), e);
            e.printStackTrace();
        }

        return newsBean;
    }

    /**
     * Parses the date.
     *
     * @param date the date
     * @return the date
     */
    public Date parseDate(String date) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
            return format.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }

}
