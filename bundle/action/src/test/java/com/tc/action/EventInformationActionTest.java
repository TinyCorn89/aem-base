/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import static org.junit.Assert.assertEquals;

import javax.jcr.Node;
import javax.jcr.Property;

import org.junit.runner.RunWith;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;

import com.mchange.util.AssertException;
import com.tc.action.EventInformationAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.EventInformationBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ EventInformationAction.class, FrameworkLogger.class })
public class EventInformationActionTest extends BaseTest {

	private EventInformationAction eventInformationAction = null;
	private String city = "city";
	private String state = "state";
	private String zipcode = "521228";
	private String country = "country";
	private String title = "title";
	private String fromDate = "10-10-2012T55:55.00";
	private String toDate = "10-10-2012T55:55.00";
	private String venueName = "venueName";
	private String description = "description";
	private String image = "image";
	private String showDirections = "showDirections";
	private Node eventInfoNode;
	private Property cityProp;
	private Property stateProp;
	private Property zipcodeProp;
	private Property countryProp;
	private Property titleProp;
	private Property fromDateProp;
	private Property toDateProp;
	private Property venueNameProp;
	private Property descriptionProp;
	private Property imageProp;
	private Property showDirectionsProp;
	private Logger logger;

	/**
	 * Setup method
	 * 
	 * @throws Exception
	 *             - exception
	 */
	@Before
	public void setUp() throws Exception {
		eventInfoNode = Mockito.mock(Node.class);
		cityProp = Mockito.mock(Property.class);
		stateProp = Mockito.mock(Property.class);
		zipcodeProp = Mockito.mock(Property.class);
		countryProp = Mockito.mock(Property.class);
		titleProp = Mockito.mock(Property.class);
		fromDateProp = Mockito.mock(Property.class);
		venueNameProp = Mockito.mock(Property.class);
		descriptionProp = Mockito.mock(Property.class);
		imageProp = Mockito.mock(Property.class);
		toDateProp = Mockito.mock(Property.class);
		showDirectionsProp = Mockito.mock(Property.class);
		logger = Mockito.mock(Logger.class);
		eventInformationAction = Whitebox
				.newInstance(EventInformationAction.class);
		PowerMockito.stub(
				PowerMockito.method(EventInformationAction.class,
						"getCurrentNode")).toReturn(eventInfoNode);
		PowerMockito.stub(
				PowerMockito.method(FrameworkLogger.class, "getLogger"))
				.toReturn(logger);
		Whitebox.setInternalState(eventInformationAction, logger);

	}

	/**
	 * Tear down method
	 * 
	 * @throws Exception
	 *             - exception
	 */
	@After
	public void tearDown() throws Exception {
		eventInformationAction = null;
	}

	/**
	 * Test eventInformationAction
	 */
	@Test
	public void testEventInformationAction() {

		try {
			// for header
			Mockito.when(eventInfoNode.hasProperty("city")).thenReturn(true);
			Mockito.when(eventInfoNode.getProperty("city"))
					.thenReturn(cityProp);
			Mockito.when(cityProp.getString()).thenReturn(city);

			Mockito.when(eventInfoNode.hasProperty("state")).thenReturn(true);
			Mockito.when(eventInfoNode.getProperty("state")).thenReturn(
					stateProp);
			Mockito.when(stateProp.getString()).thenReturn(state);

			Mockito.when(eventInfoNode.hasProperty("zipcode")).thenReturn(true);
			Mockito.when(eventInfoNode.getProperty("zipcode")).thenReturn(
					zipcodeProp);
			Mockito.when(zipcodeProp.getString()).thenReturn(zipcode);

			Mockito.when(eventInfoNode.hasProperty("country")).thenReturn(true);
			Mockito.when(eventInfoNode.getProperty("country")).thenReturn(
					countryProp);
			Mockito.when(countryProp.getString()).thenReturn(country);

			Mockito.when(eventInfoNode.hasProperty("title")).thenReturn(true);
			Mockito.when(eventInfoNode.getProperty("title")).thenReturn(
					titleProp);
			Mockito.when(titleProp.getString()).thenReturn(title);

			Mockito.when(eventInfoNode.hasProperty("fromDate"))
					.thenReturn(true);
			Mockito.when(eventInfoNode.getProperty("fromDate")).thenReturn(
					fromDateProp);
			Mockito.when(fromDateProp.getString()).thenReturn(fromDate);

			Mockito.when(eventInfoNode.hasProperty("toDate")).thenReturn(true);
			Mockito.when(eventInfoNode.getProperty("toDate")).thenReturn(
					toDateProp);
			Mockito.when(toDateProp.getString()).thenReturn(toDate);

			Mockito.when(eventInfoNode.hasProperty("venueName")).thenReturn(
					true);
			Mockito.when(eventInfoNode.getProperty("venueName")).thenReturn(
					venueNameProp);
			Mockito.when(venueNameProp.getString()).thenReturn(venueName);

			Mockito.when(eventInfoNode.hasProperty("description")).thenReturn(
					true);
			Mockito.when(eventInfoNode.getProperty("description")).thenReturn(
					descriptionProp);
			Mockito.when(descriptionProp.getString()).thenReturn(description);

			Mockito.when(eventInfoNode.hasProperty("fileReference"))
					.thenReturn(true);
			Mockito.when(eventInfoNode.getProperty("fileReference"))
					.thenReturn(imageProp);
			Mockito.when(imageProp.getString()).thenReturn(image);

			Mockito.when(eventInfoNode.hasProperty("showDirections"))
					.thenReturn(true);
			Mockito.when(eventInfoNode.getProperty("showDirections"))
					.thenReturn(showDirectionsProp);
			Mockito.when(showDirectionsProp.getString()).thenReturn(
					showDirections);

			EventInformationBean eventInformationBean = eventInformationAction
					.getEventInfo();

        assertEquals("city", eventInformationBean.getCity());
        assertEquals("state", eventInformationBean.getState());
        assertEquals("521228", eventInformationBean.getZipcode());
        assertEquals("country", eventInformationBean.getCountry());
        assertEquals("title", eventInformationBean.getTitle());
        assertEquals("10-10-2012 55:55", eventInformationBean.getFromDate());
        assertEquals("10-10-2012 55:55", eventInformationBean.getToDate());
        assertEquals("venueName",eventInformationBean.getVenueName());
        assertEquals("description", eventInformationBean.getDescription());
        assertEquals("image", eventInformationBean.getImage());
        assertEquals("showDirections", eventInformationBean.getShowDirections());
			assertEquals("eventInformationAction title", title,
					eventInformationBean.getTitle());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEventInformationActionForIf() {

		try {
			// for header
			Mockito.when(eventInfoNode.hasProperty("city")).thenReturn(false);

			Mockito.when(eventInfoNode.hasProperty("state")).thenReturn(false);

			Mockito.when(eventInfoNode.hasProperty("zipcode"))
					.thenReturn(false);

			Mockito.when(eventInfoNode.hasProperty("country"))
					.thenReturn(false);

			Mockito.when(eventInfoNode.hasProperty("title")).thenReturn(false);

			Mockito.when(eventInfoNode.hasProperty("fromDate")).thenReturn(
					false);

			Mockito.when(eventInfoNode.hasProperty("toDate")).thenReturn(false);

			Mockito.when(eventInfoNode.hasProperty("venueName")).thenReturn(
					false);

			Mockito.when(eventInfoNode.hasProperty("description")).thenReturn(
					false);

			Mockito.when(eventInfoNode.hasProperty("image")).thenReturn(false);

			EventInformationBean eventInformationBean = eventInformationAction
					.getEventInfo();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
