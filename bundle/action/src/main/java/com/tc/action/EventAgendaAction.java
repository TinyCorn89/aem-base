/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.action.BaseAction;
import com.tc.model.EventAgendaBean;
import com.tc.model.EventDetailsBean;

public class EventAgendaAction extends BaseAction {

    private static final Logger log = LoggerFactory.getLogger(EventAgendaAction.class);

    /**
     * longArticleBean
     *
     * @return eventAgendaBean
     */

    public EventAgendaBean getEventAgendaHeaderInfo() {

        log.info("In EventAgendaHeaderAction execute method");

        EventAgendaBean eventAgendaBean = new EventAgendaBean();

        Node eventNode = getCurrentNode();
        try {
            List<EventDetailsBean> eventDetailsBeans = new ArrayList<EventDetailsBean>();
            if (eventNode.hasNode("presentations")) {
                Node presentationNode = eventNode.getNode("presentations");
                if (presentationNode.hasNodes()) {
                    NodeIterator ni = presentationNode.getNodes();
                    EventDetailsBean eventDetailsBean = null;
                    while (ni.hasNext()) {
                        eventDetailsBean = new EventDetailsBean();
                        Node node = (Node) ni.nextNode();
                        if (node.hasProperty("title")) {
                            eventDetailsBean.setEventTitle(node.getProperty("title").getString());
                        }
                        if (node.hasProperty("presentation")) {
                            eventDetailsBean.setEventPresentation(node.getProperty("presentation").getString());
                        }

                        if (node.hasProperty("htmlLink")) {
                            eventDetailsBean.setHtmlLink(node.getProperty("htmlLink").getString());
                        }
                        if (node.hasProperty("pdfLink")) {
                            eventDetailsBean.setPdfLink(node.getProperty("pdfLink").getString());
                        }
                        if (node.hasProperty("from")) {
                            String dateAndTime = node.getProperty("from").getString();
                            String date = dateAndTime.substring(0, dateAndTime.indexOf("T"));
                            String time = dateAndTime.substring(dateAndTime.indexOf("T") + 1, dateAndTime.indexOf("."));
                            String toDate = date.concat(" ").concat(time);
                            eventDetailsBean.setFromDate(toDate);
                        }
                        if (node.hasProperty("to")) {
                            String dateAndTime = node.getProperty("to").getString();
                            String date = dateAndTime.substring(0, dateAndTime.indexOf("T"));
                            String time = dateAndTime.substring(dateAndTime.indexOf("T") + 1, dateAndTime.indexOf("."));
                            String toDate = date.concat(" ").concat(time);
                            eventDetailsBean.setToDate(toDate);
                        }
                        eventDetailsBeans.add(eventDetailsBean);
                    }
                    eventAgendaBean.setEventDetailsBeans(eventDetailsBeans);
                }
            }
        } catch (RepositoryException e) {
            log.debug(e.getMessage());

        }
        return eventAgendaBean;
    }

}
