/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.tc.model.EventAgendaBean;
import com.tc.model.EventDetailsBean;

import static org.junit.Assert.*;

/**
 * The class <code>EventAgendaBeanTest</code> contains tests for the class <code>{@link EventAgendaBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:27 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class EventAgendaBeanTest {
	/**
	 * Run the EventAgendaBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testEventAgendaBean_1()
		throws Exception {
		EventAgendaBean result = new EventAgendaBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the String[] getEventDays() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testGetEventDays_1()
		throws Exception {
		EventAgendaBean fixture = new EventAgendaBean();
		fixture.setTitle("");
		fixture.setEventDays(new String[] {});
		fixture.setEventDetailsBeans(new ArrayList<EventDetailsBean>());

		String[] result = fixture.getEventDays();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.length);
	}

	/**
	 * Run the List<EventDetailsBean> getEventDetailsBeans() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testGetEventDetailsBeans_1()
		throws Exception {
		EventAgendaBean fixture = new EventAgendaBean();
		fixture.setTitle("");
		fixture.setEventDays(new String[] {});
		fixture.setEventDetailsBeans(new ArrayList<EventDetailsBean>());

		List<EventDetailsBean> result = fixture.getEventDetailsBeans();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String getTitle() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testGetTitle_1()
		throws Exception {
		EventAgendaBean fixture = new EventAgendaBean();
		fixture.setTitle("");
		fixture.setEventDays(new String[] {});
		fixture.setEventDetailsBeans(new ArrayList<EventDetailsBean>());

		String result = fixture.getTitle();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setEventDays(String[]) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testSetEventDays_1()
		throws Exception {
		EventAgendaBean fixture = new EventAgendaBean();
		fixture.setTitle("");
		fixture.setEventDays(new String[] {});
		fixture.setEventDetailsBeans(new ArrayList<EventDetailsBean>());
		String[] eventDays = new String[] {};

		fixture.setEventDays(eventDays);

		// add additional test code here
	}

	/**
	 * Run the void setEventDetailsBeans(List<EventDetailsBean>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testSetEventDetailsBeans_1()
		throws Exception {
		EventAgendaBean fixture = new EventAgendaBean();
		fixture.setTitle("");
		fixture.setEventDays(new String[] {});
		fixture.setEventDetailsBeans(new ArrayList<EventDetailsBean>());
		List<EventDetailsBean> eventDetailsBeans = new ArrayList<EventDetailsBean>();

		fixture.setEventDetailsBeans(eventDetailsBeans);

		// add additional test code here
	}

	/**
	 * Run the void setTitle(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testSetTitle_1()
		throws Exception {
		EventAgendaBean fixture = new EventAgendaBean();
		fixture.setTitle("");
		fixture.setEventDays(new String[] {});
		fixture.setEventDetailsBeans(new ArrayList<EventDetailsBean>());
		String title = "";

		fixture.setTitle(title);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(EventAgendaBeanTest.class);
	}
}