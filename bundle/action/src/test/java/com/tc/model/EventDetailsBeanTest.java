/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import org.junit.*;

import com.tc.model.EventDetailsBean;

import static org.junit.Assert.*;

/**
 * The class <code>EventDetailsBeanTest</code> contains tests for the class <code>{@link EventDetailsBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:28 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class EventDetailsBeanTest {
	/**
	 * Run the EventDetailsBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testEventDetailsBean_1()
		throws Exception {
		EventDetailsBean result = new EventDetailsBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the String getEventPresentation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetEventPresentation_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");

		String result = fixture.getEventPresentation();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getEventTitle() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetEventTitle_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");

		String result = fixture.getEventTitle();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getFromDate() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetFromDate_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");

		String result = fixture.getFromDate();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getHtmlLink() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetHtmlLink_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");

		String result = fixture.getHtmlLink();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getPdfLink() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetPdfLink_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");

		String result = fixture.getPdfLink();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getShowBold() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetShowBold_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");

		String result = fixture.getShowBold();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getToDate() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetToDate_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");

		String result = fixture.getToDate();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setEventPresentation(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetEventPresentation_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");
		String eventPresentation = "";

		fixture.setEventPresentation(eventPresentation);

		// add additional test code here
	}

	/**
	 * Run the void setEventTitle(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetEventTitle_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");
		String eventTitle = "";

		fixture.setEventTitle(eventTitle);

		// add additional test code here
	}

	/**
	 * Run the void setFromDate(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetFromDate_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");
		String fromDate = "";

		fixture.setFromDate(fromDate);

		// add additional test code here
	}

	/**
	 * Run the void setHtmlLink(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetHtmlLink_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");
		String htmlLink = "";

		fixture.setHtmlLink(htmlLink);

		// add additional test code here
	}

	/**
	 * Run the void setPdfLink(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetPdfLink_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");
		String pdfLink = "";

		fixture.setPdfLink(pdfLink);

		// add additional test code here
	}

	/**
	 * Run the void setShowBold(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetShowBold_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");
		String showBold = "";

		fixture.setShowBold(showBold);

		// add additional test code here
	}

	/**
	 * Run the void setToDate(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetToDate_1()
		throws Exception {
		EventDetailsBean fixture = new EventDetailsBean();
		fixture.setEventPresentation("");
		fixture.setToDate("");
		fixture.setPdfLink("");
		fixture.setFromDate("");
		fixture.setHtmlLink("");
		fixture.setEventTitle("");
		fixture.setShowBold("");
		String toDate = "";

		fixture.setToDate(toDate);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
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
	 * @generatedBy CodePro at 12/9/13 2:28 PM
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
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(EventDetailsBeanTest.class);
	}
}