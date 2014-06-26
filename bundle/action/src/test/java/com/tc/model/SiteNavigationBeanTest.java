/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.tc.model.SiteNavigationBean;

import static org.junit.Assert.*;

/**
 * The class <code>SiteNavigationBeanTest</code> contains tests for the class <code>{@link SiteNavigationBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:31 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class SiteNavigationBeanTest {
	/**
	 * Run the SiteNavigationBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSiteNavigationBean_1()
		throws Exception {
		SiteNavigationBean result = new SiteNavigationBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the List<Object> getPageList() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetPageList_1()
		throws Exception {
		SiteNavigationBean fixture = new SiteNavigationBean();
		fixture.setPageList(new ArrayList<Object>());
		fixture.setRootPath("");

		List<Object> result = fixture.getPageList();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String getRootPath() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetRootPath_1()
		throws Exception {
		SiteNavigationBean fixture = new SiteNavigationBean();
		fixture.setPageList(new ArrayList<Object>());
		fixture.setRootPath("");

		String result = fixture.getRootPath();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setPageList(List<Object>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetPageList_1()
		throws Exception {
		SiteNavigationBean fixture = new SiteNavigationBean();
		fixture.setPageList(new ArrayList<Object>());
		fixture.setRootPath("");
		List<Object> pageList = new ArrayList<Object>();

		fixture.setPageList(pageList);

		// add additional test code here
	}

	/**
	 * Run the void setRootPath(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetRootPath_1()
		throws Exception {
		SiteNavigationBean fixture = new SiteNavigationBean();
		fixture.setPageList(new ArrayList<Object>());
		fixture.setRootPath("");
		String rootPath = "";

		fixture.setRootPath(rootPath);

		// add additional test code here
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
		new org.junit.runner.JUnitCore().run(SiteNavigationBeanTest.class);
	}
}