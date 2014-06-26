/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;
import static org.junit.Assert.assertEquals;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.Value;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;

import com.tc.action.EventAgendaAction;
import com.tc.action.NewsAction;
import com.tc.model.EventAgendaBean;
import com.tc.model.EventDetailsBean;
@RunWith(PowerMockRunner.class)
@PrepareForTest(NewsAction.class)
public class EventAgendaActionTest extends BaseTest{
    Node eventNode;
    private Property titleProp;
    private Property presentationProp ;
    private Property htmlProp;
    private Property pdfProp;
    private Property fromProp;
    private Property toProp;
    EventAgendaAction eventAgenda;

    @Before
    public void setUp() throws Exception {

        eventNode = Mockito.mock(Node.class);
        titleProp = Mockito.mock(Property.class);
        presentationProp = Mockito.mock(Property.class);
        htmlProp = Mockito.mock(Property.class);
        pdfProp = Mockito.mock(Property.class);
        fromProp = Mockito.mock(Property.class);
        toProp = Mockito.mock(Property.class);
        eventAgenda = new EventAgendaAction();
        PowerMockito.stub(PowerMockito.method(EventAgendaAction.class, "getCurrentNode")).toReturn(eventNode);

    }
    @After
    public void tearDown() throws Exception {
        eventAgenda = null;
    }
    @Test
    public void testEventAgenda() {

        try {

            List<EventDetailsBean> list= Mockito.mock(ArrayList.class);
            Mockito.when(eventNode.hasNode("presentations")).thenReturn(true);
            Node presentationNode = Mockito.mock(Node.class);
            Mockito.when(eventNode.getNode("presentations")).thenReturn(presentationNode);
            Mockito.when(presentationNode.hasNodes()).thenReturn(true);
            NodeIterator ni = Mockito.mock(NodeIterator.class);
            Mockito.when(presentationNode.getNodes()).thenReturn(ni);
            Mockito.when(ni.hasNext()).thenReturn(true, false);
            Node childNode = Mockito.mock(Node.class);
            Mockito.when(ni.nextNode()).thenReturn(childNode);
            Mockito.when(childNode.hasProperty("title")).thenReturn(true);
            Mockito.when(childNode.getProperty("title")).thenReturn(titleProp);
            Mockito.when(titleProp.getString()).thenReturn("articletitle");

            Mockito.when(childNode.hasProperty("presentation")).thenReturn(true);
            Mockito.when(childNode.getProperty("presentation")).thenReturn(presentationProp);
            Mockito.when(presentationProp.getString()).thenReturn("presentation");

            Mockito.when(childNode.hasProperty("htmlLink")).thenReturn(true);
            Mockito.when(childNode.getProperty("htmlLink")).thenReturn(htmlProp);
            Mockito.when(htmlProp.getString()).thenReturn("htmlLink");

            Mockito.when(childNode.hasProperty("pdfLink")).thenReturn(true);
            Mockito.when(childNode.getProperty("pdfLink")).thenReturn(pdfProp);
            Mockito.when(pdfProp.getString()).thenReturn("pdfLink");

            Mockito.when(childNode.hasProperty("from")).thenReturn(true);
            Mockito.when(childNode.getProperty("from")).thenReturn(fromProp);
            Mockito.when(fromProp.getString()).thenReturn("10-10-2012T55:55.00");

            Mockito.when(childNode.hasProperty("to")).thenReturn(true);
            Mockito.when(childNode.getProperty("to")).thenReturn(toProp);
            Mockito.when(toProp.getString()).thenReturn("11-10-2012T55:55.00");

            EventAgendaBean agendaBean= eventAgenda.getEventAgendaHeaderInfo();
            assertEquals("articletitle", agendaBean.getEventDetailsBeans().get(0).getEventTitle());
            assertEquals("pdfLink",agendaBean.getEventDetailsBeans().get(0).getPdfLink());
            assertEquals("htmlLink",agendaBean.getEventDetailsBeans().get(0).getHtmlLink());
            assertEquals("presentation",agendaBean.getEventDetailsBeans().get(0).getEventPresentation());

            assertEquals("10-10-2012 55:55", agendaBean.getEventDetailsBeans().get(0).getFromDate());
            assertEquals("11-10-2012 55:55",agendaBean.getEventDetailsBeans().get(0).getToDate());

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testEventAgendaForIf() {

        try {

            List<EventDetailsBean> list= Mockito.mock(ArrayList.class);
            Mockito.when(eventNode.hasNode("presentations")).thenReturn(true);
            Node presentationNode = Mockito.mock(Node.class);
            Mockito.when(eventNode.getNode("presentations")).thenReturn(presentationNode);
            Mockito.when(presentationNode.hasNodes()).thenReturn(true);
            NodeIterator ni = Mockito.mock(NodeIterator.class);
            Mockito.when(presentationNode.getNodes()).thenReturn(ni);
            Mockito.when(ni.hasNext()).thenReturn(true, false);
            Node childNode = Mockito.mock(Node.class);
            Mockito.when(ni.nextNode()).thenReturn(childNode);
            Mockito.when(childNode.hasProperty("title")).thenReturn(false);
            Mockito.when(childNode.hasProperty("presentation")).thenReturn(false);
            Mockito.when(childNode.hasProperty("htmlLink")).thenReturn(false);
            Mockito.when(childNode.hasProperty("pdfLink")).thenReturn(false);
            Mockito.when(childNode.hasProperty("from")).thenReturn(false);
            Mockito.when(childNode.hasProperty("to")).thenReturn(false);
            EventAgendaBean agendaBean= eventAgenda.getEventAgendaHeaderInfo();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
