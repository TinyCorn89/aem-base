/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.tc.model.EventPreviewBean;
import com.tc.model.EventPreviewSnapshotBean;

public class EventPreviewBeanTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void EventPreviewBean_1()
		throws Exception {
		EventPreviewBean result = new EventPreviewBean();
		assertNotNull(result);
		// add additional test code here
	}
	@Test
	public final void testGetEventAllPath() {
		EventPreviewBean fixture = new EventPreviewBean();
		fixture.setEventAllPath("");
		fixture.setEventPreviewList(new ArrayList<EventPreviewSnapshotBean>());
		fixture.setEventPreviewDetails(new TreeSet<EventPreviewSnapshotBean>());
	    fixture.setNoOfEvents(1);
	    fixture.setTitle("");
	    String result = fixture.getEventAllPath();

		// add additional test code here
		assertEquals("", result);
	}

	@Test
	public final void testSetEventAllPath() {
		EventPreviewBean fixture = new EventPreviewBean();
		fixture.setEventPreviewList(new ArrayList<EventPreviewSnapshotBean>());
		fixture.setEventPreviewDetails(new TreeSet<EventPreviewSnapshotBean>());
	    fixture.setNoOfEvents(1);
	    fixture.setTitle("");
	    String eventAllPath = " ";
	    fixture.setEventAllPath(eventAllPath);
	  
	}

	@Test
	public final void testGetTitle() {
		EventPreviewBean fixture = new EventPreviewBean();
		fixture.setEventAllPath("");
		fixture.setEventPreviewList(new ArrayList<EventPreviewSnapshotBean>());
		fixture.setEventPreviewDetails(new TreeSet<EventPreviewSnapshotBean>());
	    fixture.setNoOfEvents(1);
	    fixture.setTitle("");
	    String result = fixture.getTitle();
		assertEquals("", result);
	}

	@Test
	public final void testSetTitle() {
		EventPreviewBean fixture = new EventPreviewBean();
		fixture.setEventPreviewList(new ArrayList<EventPreviewSnapshotBean>());
		fixture.setEventPreviewDetails(new TreeSet<EventPreviewSnapshotBean>());
	    fixture.setNoOfEvents(1);
	    fixture.setEventAllPath("");
	    String title = " ";
        fixture.setTitle(title);
		
	}

	@Test
	public final void testGetEventPreviewList() {
		EventPreviewBean fixture = new EventPreviewBean();
		fixture.setEventAllPath("");
		fixture.setEventPreviewList(new ArrayList<EventPreviewSnapshotBean>());
		fixture.setEventPreviewDetails(new TreeSet<EventPreviewSnapshotBean>());
	    fixture.setNoOfEvents(1);
	    fixture.setTitle("");
	    List<EventPreviewSnapshotBean> result = fixture.getEventPreviewList();
	    assertNotNull(result);
		assertEquals(0, result.size());
		
	}

	@Test
	public final void testSetEventPreviewList() {
		EventPreviewBean fixture = new EventPreviewBean();
		fixture.setEventAllPath("");
		fixture.setEventPreviewDetails(new TreeSet<EventPreviewSnapshotBean>());
	    fixture.setNoOfEvents(1);
	    fixture.setTitle("");
	    List<EventPreviewSnapshotBean> eventPreviewList = new ArrayList<EventPreviewSnapshotBean>();
	    fixture.setEventPreviewList(eventPreviewList);
	}

	@Test
	public final void testGetNoOfEvents() {
		EventPreviewBean fixture = new EventPreviewBean();
		fixture.setEventAllPath("");
		fixture.setEventPreviewList(new ArrayList<EventPreviewSnapshotBean>());
		fixture.setEventPreviewDetails(new TreeSet<EventPreviewSnapshotBean>());
	    fixture.setNoOfEvents(1);
	    fixture.setTitle("");
	    int result= fixture.getNoOfEvents();
	    assertEquals(1, result);

	}

	@Test
	public final void testSetNoOfEvents() {
		EventPreviewBean fixture = new EventPreviewBean();
		fixture.setEventAllPath("");
		fixture.setEventPreviewList(new ArrayList<EventPreviewSnapshotBean>());
		fixture.setTitle("");
		fixture.setEventPreviewDetails(new TreeSet<EventPreviewSnapshotBean>());
		int noOfEvents = 1;
		fixture.setNoOfEvents(noOfEvents);
	}

	@Test
	public final void testGetEventPreviewDetails() {
		EventPreviewBean fixture = new EventPreviewBean();
		fixture.setEventAllPath("");
		fixture.setEventPreviewList(new ArrayList<EventPreviewSnapshotBean>());
		fixture.setEventPreviewDetails(new TreeSet<EventPreviewSnapshotBean>());
	    fixture.setNoOfEvents(1);
	    fixture.setTitle("");
	    Set<EventPreviewSnapshotBean> result = fixture.getEventPreviewDetails();
	    assertNotNull(result);
		assertEquals(0, result.size());
	    
		
		
	}

	@Test
	public final void testSetEventPreviewDetails() {
		EventPreviewBean fixture = new EventPreviewBean();
		fixture.setEventAllPath("");
		fixture.setEventPreviewList(new ArrayList<EventPreviewSnapshotBean>());
		fixture.setNoOfEvents(1);
		fixture.setTitle("");
		Set<EventPreviewSnapshotBean> event =new TreeSet<EventPreviewSnapshotBean>();
		fixture.setEventPreviewDetails(event);
		
	}

}
