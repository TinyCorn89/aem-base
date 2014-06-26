/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.tc.model.TopNavigationBean;

import static org.junit.Assert.*;

/**
 * The class <code>TopNavigationBeanTest</code> contains tests for the class <code>{@link TopNavigationBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:31 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class TopNavigationBeanTest {
	/**
	 * Run the TopNavigationBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testTopNavigationBean_1()
		throws Exception {
		TopNavigationBean result = new TopNavigationBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the List<Object> getListOfTabs() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetListOfTabs_1()
		throws Exception {
		TopNavigationBean fixture = new TopNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setTitle("");

		List<Object> result = fixture.getListOfTabs();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String getPath() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetPath_1()
		throws Exception {
		TopNavigationBean fixture = new TopNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setTitle("");

		String result = fixture.getPath();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getTitle() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetTitle_1()
		throws Exception {
		TopNavigationBean fixture = new TopNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setTitle("");

		String result = fixture.getTitle();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setListOfTabs(List<Object>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetListOfTabs_1()
		throws Exception {
		TopNavigationBean fixture = new TopNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setTitle("");
		List<Object> listOfTabs = new ArrayList<Object>();

		fixture.setListOfTabs(listOfTabs);

		// add additional test code here
	}

	/**
	 * Run the void setPath(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetPath_1()
		throws Exception {
		TopNavigationBean fixture = new TopNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setTitle("");
		String path = "";

		fixture.setPath(path);

		// add additional test code here
	}

	/**
	 * Run the void setTitle(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetTitle_1()
		throws Exception {
		TopNavigationBean fixture = new TopNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setTitle("");
		String title = "";

		fixture.setTitle(title);

		// add additional test code here
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		TopNavigationBean fixture = new TopNavigationBean();
		fixture.setPath("");
		fixture.setListOfTabs(new ArrayList<Object>());
		fixture.setTitle("");

		String result = fixture.toString();

		// add additional test code here
		assertEquals("TopNavigationBean [listOfTabs=[], title=, path=]", result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
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
	 * @generatedBy CodePro at 12/9/13 2:31 PM
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
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(TopNavigationBeanTest.class);
	}
}