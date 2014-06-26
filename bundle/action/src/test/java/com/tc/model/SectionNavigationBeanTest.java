/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.tc.model.SectionNavigationBean;

import static org.junit.Assert.*;

/**
 * The class <code>SectionNavigationBeanTest</code> contains tests for the class <code>{@link SectionNavigationBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:30 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class SectionNavigationBeanTest {
	/**
	 * Run the SectionNavigationBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSectionNavigationBean_1()
		throws Exception {
		SectionNavigationBean result = new SectionNavigationBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the List<Object> getListOfTabs() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetListOfTabs_1()
		throws Exception {
		SectionNavigationBean fixture = new SectionNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setPageTitle("");
		fixture.setPagePath("");
		fixture.setScrollingId("");

		List<Object> result = fixture.getListOfTabs();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String getPagePath() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetPagePath_1()
		throws Exception {
		SectionNavigationBean fixture = new SectionNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setPageTitle("");
		fixture.setPagePath("");
		fixture.setScrollingId("");

		String result = fixture.getPagePath();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getPageTitle() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetPageTitle_1()
		throws Exception {
		SectionNavigationBean fixture = new SectionNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setPageTitle("");
		fixture.setPagePath("");
		fixture.setScrollingId("");

		String result = fixture.getPageTitle();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getPath() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetPath_1()
		throws Exception {
		SectionNavigationBean fixture = new SectionNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setPageTitle("");
		fixture.setPagePath("");
		fixture.setScrollingId("");

		String result = fixture.getPath();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getScrollingId() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetScrollingId_1()
		throws Exception {
		SectionNavigationBean fixture = new SectionNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setPageTitle("");
		fixture.setPagePath("");
		fixture.setScrollingId("");

		String result = fixture.getScrollingId();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setListOfTabs(List<Object>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetListOfTabs_1()
		throws Exception {
		SectionNavigationBean fixture = new SectionNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setPageTitle("");
		fixture.setPagePath("");
		fixture.setScrollingId("");
		List<Object> listOfTabs = new ArrayList<Object>();

		fixture.setListOfTabs(listOfTabs);

		// add additional test code here
	}

	/**
	 * Run the void setPagePath(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetPagePath_1()
		throws Exception {
		SectionNavigationBean fixture = new SectionNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setPageTitle("");
		fixture.setPagePath("");
		fixture.setScrollingId("");
		String pagePath = "";

		fixture.setPagePath(pagePath);

		// add additional test code here
	}

	/**
	 * Run the void setPageTitle(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetPageTitle_1()
		throws Exception {
		SectionNavigationBean fixture = new SectionNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setPageTitle("");
		fixture.setPagePath("");
		fixture.setScrollingId("");
		String pageTitle = "";

		fixture.setPageTitle(pageTitle);

		// add additional test code here
	}

	/**
	 * Run the void setPath(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetPath_1()
		throws Exception {
		SectionNavigationBean fixture = new SectionNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setPageTitle("");
		fixture.setPagePath("");
		fixture.setScrollingId("");
		String path = "";

		fixture.setPath(path);

		// add additional test code here
	}

	/**
	 * Run the void setScrollingId(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetScrollingId_1()
		throws Exception {
		SectionNavigationBean fixture = new SectionNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setPageTitle("");
		fixture.setPagePath("");
		fixture.setScrollingId("");
		String scrollingId = "";

		fixture.setScrollingId(scrollingId);

		// add additional test code here
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		SectionNavigationBean fixture = new SectionNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setPageTitle("");
		fixture.setPagePath("");
		fixture.setScrollingId("");

		String result = fixture.toString();

		// add additional test code here
		assertEquals("SectionNavigationBean [listOfTabs=[]]", result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
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
	 * @generatedBy CodePro at 12/9/13 2:30 PM
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
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(SectionNavigationBeanTest.class);
	}
}