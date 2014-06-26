/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.tc.model.AdvancedSearchBean;

import static org.junit.Assert.*;

/**
 * The class <code>AdvancedSearchBeanTest</code> contains tests for the class <code>{@link AdvancedSearchBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:25 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class AdvancedSearchBeanTest {
	/**
	 * Run the AdvancedSearchBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testAdvancedSearchBean_1()
		throws Exception {
		AdvancedSearchBean result = new AdvancedSearchBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the String getAuthor() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testGetAuthor_1()
		throws Exception {
		AdvancedSearchBean fixture = new AdvancedSearchBean();
		fixture.setSection("");
		fixture.setAuthor("");
		fixture.setTimeRange("");
		fixture.setKeyword("");
		fixture.setContentType("");
		fixture.setSectionMenus(new ArrayList<Object>());

		String result = fixture.getAuthor();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getContentType() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testGetContentType_1()
		throws Exception {
		AdvancedSearchBean fixture = new AdvancedSearchBean();
		fixture.setSection("");
		fixture.setAuthor("");
		fixture.setTimeRange("");
		fixture.setKeyword("");
		fixture.setContentType("");
		fixture.setSectionMenus(new ArrayList<Object>());

		String result = fixture.getContentType();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getKeyword() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testGetKeyword_1()
		throws Exception {
		AdvancedSearchBean fixture = new AdvancedSearchBean();
		fixture.setSection("");
		fixture.setAuthor("");
		fixture.setTimeRange("");
		fixture.setKeyword("");
		fixture.setContentType("");
		fixture.setSectionMenus(new ArrayList<Object>());

		String result = fixture.getKeyword();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getSection() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testGetSection_1()
		throws Exception {
		AdvancedSearchBean fixture = new AdvancedSearchBean();
		fixture.setSection("");
		fixture.setAuthor("");
		fixture.setTimeRange("");
		fixture.setKeyword("");
		fixture.setContentType("");
		fixture.setSectionMenus(new ArrayList<Object>());

		String result = fixture.getSection();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the List<Object> getSectionMenus() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testGetSectionMenus_1()
		throws Exception {
		AdvancedSearchBean fixture = new AdvancedSearchBean();
		fixture.setSection("");
		fixture.setAuthor("");
		fixture.setTimeRange("");
		fixture.setKeyword("");
		fixture.setContentType("");
		fixture.setSectionMenus(new ArrayList<Object>());

		List<Object> result = fixture.getSectionMenus();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String getTimeRange() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testGetTimeRange_1()
		throws Exception {
		AdvancedSearchBean fixture = new AdvancedSearchBean();
		fixture.setSection("");
		fixture.setAuthor("");
		fixture.setTimeRange("");
		fixture.setKeyword("");
		fixture.setContentType("");
		fixture.setSectionMenus(new ArrayList<Object>());

		String result = fixture.getTimeRange();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setAuthor(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testSetAuthor_1()
		throws Exception {
		AdvancedSearchBean fixture = new AdvancedSearchBean();
		fixture.setSection("");
		fixture.setAuthor("");
		fixture.setTimeRange("");
		fixture.setKeyword("");
		fixture.setContentType("");
		fixture.setSectionMenus(new ArrayList<Object>());
		String author = "";

		fixture.setAuthor(author);

		// add additional test code here
	}

	/**
	 * Run the void setContentType(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testSetContentType_1()
		throws Exception {
		AdvancedSearchBean fixture = new AdvancedSearchBean();
		fixture.setSection("");
		fixture.setAuthor("");
		fixture.setTimeRange("");
		fixture.setKeyword("");
		fixture.setContentType("");
		fixture.setSectionMenus(new ArrayList<Object>());
		String contentType = "";

		fixture.setContentType(contentType);

		// add additional test code here
	}

	/**
	 * Run the void setKeyword(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testSetKeyword_1()
		throws Exception {
		AdvancedSearchBean fixture = new AdvancedSearchBean();
		fixture.setSection("");
		fixture.setAuthor("");
		fixture.setTimeRange("");
		fixture.setKeyword("");
		fixture.setContentType("");
		fixture.setSectionMenus(new ArrayList<Object>());
		String keyword = "";

		fixture.setKeyword(keyword);

		// add additional test code here
	}

	/**
	 * Run the void setSection(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testSetSection_1()
		throws Exception {
		AdvancedSearchBean fixture = new AdvancedSearchBean();
		fixture.setSection("");
		fixture.setAuthor("");
		fixture.setTimeRange("");
		fixture.setKeyword("");
		fixture.setContentType("");
		fixture.setSectionMenus(new ArrayList<Object>());
		String section = "";

		fixture.setSection(section);

		// add additional test code here
	}

	/**
	 * Run the void setSectionMenus(List<Object>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testSetSectionMenus_1()
		throws Exception {
		AdvancedSearchBean fixture = new AdvancedSearchBean();
		fixture.setSection("");
		fixture.setAuthor("");
		fixture.setTimeRange("");
		fixture.setKeyword("");
		fixture.setContentType("");
		fixture.setSectionMenus(new ArrayList<Object>());
		List<Object> tabSectionNodes = new ArrayList<Object>();

		fixture.setSectionMenus(tabSectionNodes);

		// add additional test code here
	}

	/**
	 * Run the void setTimeRange(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testSetTimeRange_1()
		throws Exception {
		AdvancedSearchBean fixture = new AdvancedSearchBean();
		fixture.setSection("");
		fixture.setAuthor("");
		fixture.setTimeRange("");
		fixture.setKeyword("");
		fixture.setContentType("");
		fixture.setSectionMenus(new ArrayList<Object>());
		String timeRange = "";

		fixture.setTimeRange(timeRange);

		// add additional test code here
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		AdvancedSearchBean fixture = new AdvancedSearchBean();
		fixture.setSection("");
		fixture.setAuthor("");
		fixture.setTimeRange("");
		fixture.setKeyword("");
		fixture.setContentType("");
		fixture.setSectionMenus(new ArrayList<Object>());

		String result = fixture.toString();

		// add additional test code here
		assertEquals("AdvancedSearchBean [section=, keyword=, author=, contentType=, timeRange=, tabSectionNodes=[]]", result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 12/9/13 2:25 PM
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
	 * @generatedBy CodePro at 12/9/13 2:25 PM
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
	 * @generatedBy CodePro at 12/9/13 2:25 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(AdvancedSearchBeanTest.class);
	}
}