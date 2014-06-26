/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.api.designer.Style;
import com.day.cq.wcm.foundation.Navigation;
import com.day.cq.wcm.foundation.Navigation.Element;
import com.day.cq.wcm.foundation.Navigation.Element.Type;
import com.tc.action.EventPreviewAction;
import com.tc.action.GalleryAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.EventPreviewBean;
import com.tc.model.EventPreviewSnapshotBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EventPreviewAction.class)
public class EventPreviewActionTest extends BaseTest {

	private EventPreviewAction eventPreviewAction;
	private String noOfEvents = "1";
	private String eventTitle = "eventTitle";

	private Node eventPreviewNode;
	private Property noOfEventsProp;
	private Property eventTitleProp;
	private Logger logger;
	private Style currentStyle;
	List<Page> paths;
	private SlingHttpServletRequest request;
	private ResourceResolver resourceResolver;
	private Resource rootRes;
	private Node node;
	private transient SlingHttpServletRequest slingRequest;
	Navigation nav;
	Element element;
	Type elementtype;
	Page elementPage;
	Iterator<Element> elementIterator;
	Element aNavElement;
	Navigation navigationElements;
	Navigation.Element.Type elementType;

	@Before
	public void setUp() throws Exception {
		eventPreviewAction = new EventPreviewAction();
		noOfEventsProp = Mockito.mock(Property.class);
		eventTitleProp = Mockito.mock(Property.class);
		nav = Mockito.mock(Navigation.class);
		element = Mockito.mock(Element.class);
		elementPage = Mockito.mock(Page.class);
		eventPreviewNode = Mockito.mock(Node.class);
		currentStyle = Mockito.mock(Style.class);
		paths = Mockito.mock(ArrayList.class);
		request = getSlingHttpServletRequest();
		resourceResolver = Mockito.mock(ResourceResolver.class);
		rootRes = Mockito.mock(Resource.class);
		slingRequest = Mockito.mock(SlingHttpServletRequest.class);

		logger = Mockito.mock(Logger.class);
		PowerMockito.stub(
				PowerMockito.method(GalleryAction.class, "getSlingRequest"))
				.toReturn(slingRequest);
		PowerMockito
				.stub(PowerMockito.method(EventPreviewAction.class,
						"getCurrentNode")).toReturn(eventPreviewNode);
		PowerMockito.stub(
				PowerMockito.method(FrameworkLogger.class, "getLogger"))
				.toReturn(logger);
		PowerMockito.stub(
				PowerMockito
						.method(EventPreviewAction.class, "getCurrentStyle"))
				.toReturn(currentStyle);
	}

	@Test
	public void testGetEventPreviewDetails() {

		EventPreviewBean eventPreviewBean = new EventPreviewBean();
		PowerMockito.stub(
				PowerMockito.method(EventPreviewAction.class, "getNodeValues",
						Node.class)).toReturn(eventPreviewBean);
	}

	@Test
	public void testGetEventListDetails() {
		EventPreviewBean eventPreviewBean = new EventPreviewBean();
		String rootPath = "rootPath";
		String depth = "2";
		try {
			Mockito.when(currentStyle.get("rootPath", String.class))
					.thenReturn(rootPath);
			Mockito.when(currentStyle.get("depth", String.class)).thenReturn(
					depth);
			PowerMockito.stub(
					PowerMockito.method(EventPreviewAction.class,
							"getRootPageDetails", String.class, String.class))
					.toReturn(eventPreviewBean);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Test
	public void testGetNodeValues() {
		EventPreviewBean eventPreviewBean = new EventPreviewBean();
		String rootPath = "rootPath";
		String depth = "2";
		node = Mockito.mock(Node.class);
		try {
			Mockito.when(currentStyle.get("pathOfEvents", String.class))
					.thenReturn(rootPath);
			Mockito.when(currentStyle.get("depth", String.class)).thenReturn(
					depth);
			PowerMockito.stub(
					PowerMockito.method(EventPreviewAction.class,
							"getRootPageDetails", String.class, String.class))
					.toReturn(eventPreviewBean);
			Mockito.when(currentStyle.get("eventAllPath", String.class))
					.thenReturn("eventAllPath");
			Mockito.when(node.hasProperty("noOfEvents")).thenReturn(true);
			Mockito.when(node.getProperty("noOfEvents")).thenReturn(
					noOfEventsProp);
			Mockito.when(noOfEventsProp.getString()).thenReturn(noOfEvents);

			Mockito.when(node.hasProperty("title")).thenReturn(true);
			Mockito.when(node.getProperty("title")).thenReturn(eventTitleProp);
			Mockito.when(eventTitleProp.getString()).thenReturn(eventTitle);

			EventPreviewBean eventPreviewBean1 = eventPreviewAction
					.getNodeValues(node);
			assertEquals(1, eventPreviewBean1.getNoOfEvents());
			assertEquals("eventTitle", eventPreviewBean1.getTitle());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetRootPageDetails() {
		EventPreviewBean eventPreviewBean = new EventPreviewBean();
		EventPreviewSnapshotBean eventPreviewSnapshotBean = new EventPreviewSnapshotBean();
		String rootPath = "rootPath";
		String depth = "2";

		Page rootPage = Mockito.mock(Page.class);
		Property dateProp = Mockito.mock(Property.class);
		Property titleProp = Mockito.mock(Property.class);
		Property fromDateProp = Mockito.mock(Property.class);
		Property toDateProp = Mockito.mock(Property.class);
		Property venuNameProp = Mockito.mock(Property.class);
		Property cityProp = Mockito.mock(Property.class);
		Property stateProp = Mockito.mock(Property.class);
		Property descriptionProp = Mockito.mock(Property.class);
		Property fileRefProp = Mockito.mock(Property.class);
		List<Page> paths = new ArrayList<Page>();
		Node node1 = Mockito.mock(Node.class);
		Node eventDetailsNode = Mockito.mock(Node.class);

		try {
			Mockito.when(slingRequest.getResourceResolver()).thenReturn(
					resourceResolver);
			Mockito.when(resourceResolver.getResource(rootPath)).thenReturn(
					rootRes);
			Mockito.when(rootRes.adaptTo(Page.class)).thenReturn(rootPage);
			PageFilter filter = new PageFilter(slingRequest);
			elementIterator = Mockito.mock(Iterator.class);
			aNavElement = Mockito.mock(Element.class);
			navigationElements = Mockito.mock(Navigation.class);
			elementType = Type.ITEM_BEGIN;

			Mockito.when(elementIterator.hasNext()).thenReturn(true, false);
			Mockito.when(elementIterator.next()).thenReturn(aNavElement);
			Mockito.when(aNavElement.getPage()).thenReturn(elementPage);
			Mockito.when(aNavElement.getType()).thenReturn(elementType);

			Mockito.when(navigationElements.iterator()).thenReturn(
					elementIterator);
			Whitebox.setInternalState(eventPreviewAction, "navigationElements",
					navigationElements);
			PowerMockito.stub(
					PowerMockito.method(EventPreviewAction.class,
							"getNavigation")).toReturn(navigationElements);

			Mockito.when(elementPage.adaptTo(Node.class)).thenReturn(node1);
			Mockito.when(node1.getPath()).thenReturn("en/us/home");
			Mockito.when(node1.hasNode("jcr:content")).thenReturn(true);
			Mockito.when(node1.getNode("jcr:content")).thenReturn(
					eventDetailsNode);
			Mockito.when(eventDetailsNode.hasNode("leftpar")).thenReturn(false);
			Mockito.when(eventDetailsNode.hasNode("event_info")).thenReturn(
					false);
			Mockito.when(eventDetailsNode.hasProperty("toDate")).thenReturn(
					true);
			Mockito.when(eventDetailsNode.getProperty("toDate")).thenReturn(
					dateProp);
			Mockito.when(dateProp.getString()).thenReturn(
					"2014-10-31T12:32:49.000-04:00");
			PowerMockito.stub(
					PowerMockito.method(EventPreviewAction.class, "convert"))
					.toReturn("2014-10-31 12:32:49");
			Mockito.when(eventDetailsNode.hasProperty("title"))
					.thenReturn(true);
			Mockito.when(eventDetailsNode.getProperty("title")).thenReturn(
					titleProp);
			Mockito.when(titleProp.getString()).thenReturn("title");

			Mockito.when(eventDetailsNode.hasProperty("fromDate")).thenReturn(
					true);
			Mockito.when(eventDetailsNode.getProperty("fromDate")).thenReturn(
					fromDateProp);
			Mockito.when(fromDateProp.getString()).thenReturn(
					"2014-06-01T12:32:39.000-04:00");

			Mockito.when(eventDetailsNode.hasProperty("toDate")).thenReturn(
					true);
			Mockito.when(eventDetailsNode.getProperty("toDate")).thenReturn(
					toDateProp);
			Mockito.when(toDateProp.getString()).thenReturn(
					"2014-10-31T12:32:49.000-04:00");

			Mockito.when(eventDetailsNode.hasProperty("venueName")).thenReturn(
					true);
			Mockito.when(eventDetailsNode.getProperty("venueName")).thenReturn(
					venuNameProp);
			Mockito.when(venuNameProp.getString()).thenReturn("venuName");

			Mockito.when(eventDetailsNode.hasProperty("venueName")).thenReturn(
					true);
			Mockito.when(eventDetailsNode.getProperty("venueName")).thenReturn(
					venuNameProp);
			Mockito.when(venuNameProp.getString()).thenReturn("venuName");

			Mockito.when(eventDetailsNode.hasProperty("city")).thenReturn(true);
			Mockito.when(eventDetailsNode.getProperty("city")).thenReturn(
					cityProp);
			Mockito.when(cityProp.getString()).thenReturn("city");

			Mockito.when(eventDetailsNode.hasProperty("state"))
					.thenReturn(true);
			Mockito.when(eventDetailsNode.getProperty("state")).thenReturn(
					stateProp);
			Mockito.when(stateProp.getString()).thenReturn("state");

			Mockito.when(eventDetailsNode.hasProperty("description"))
					.thenReturn(true);
			Mockito.when(eventDetailsNode.getProperty("description"))
					.thenReturn(descriptionProp);
			Mockito.when(descriptionProp.getString()).thenReturn("description");

			Mockito.when(eventDetailsNode.hasProperty("fileReference"))
					.thenReturn(true);
			Mockito.when(eventDetailsNode.getProperty("fileReference"))
					.thenReturn(fileRefProp);
			Mockito.when(fileRefProp.getString()).thenReturn("fileReference");

			EventPreviewBean eventPreviewBean1 = eventPreviewAction
					.getRootPageDetails("roortPath", "1");
			Iterator<EventPreviewSnapshotBean> eventIterator = eventPreviewBean1
					.getEventPreviewDetails().iterator();
			EventPreviewSnapshotBean eventPreviewSnapshotBean1 = eventIterator
					.next();
			assertEquals("2014-10-31 12:32:49",
					eventPreviewSnapshotBean1.getFromDate());
			assertEquals("fileReference",
					eventPreviewSnapshotBean1.getImagePath());
			assertEquals("en/us/home.html",
					eventPreviewSnapshotBean1.getLinkToDetailsPath());
			assertEquals("city,state",
					eventPreviewSnapshotBean1.getShortAddress());
			assertEquals("title", eventPreviewSnapshotBean1.getTitle());
			assertEquals("2014-10-31 12:32:49",
					eventPreviewSnapshotBean1.getToDate());
			assertEquals("venuName", eventPreviewSnapshotBean1.getVenue());
			assertEquals("2014-10-31 12:32:49",
					eventPreviewSnapshotBean1.getFromDate());
			assertEquals("2014-10-31 12:32:49",
					eventPreviewSnapshotBean1.getFromDate());
			

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
