/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import org.junit.*;

import com.tc.model.SimpleSearchBean;

import static org.junit.Assert.*;

/**
 * The class <code>SimpleSearchBeanTest</code> contains tests for the class <code>{@link SimpleSearchBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:31 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class SimpleSearchBeanTest {
	/**
	 * Run the SimpleSearchBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSimpleSearchBean_1()
		throws Exception {
		SimpleSearchBean result = new SimpleSearchBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the String getAdvancePage() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetAdvancePage_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.getAdvancePage();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getNextText() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetNextText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.getNextText();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getNoResultsText() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetNoResultsText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.getNoResultsText();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getPreviousText() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetPreviousText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.getPreviousText();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getRelatedSearchesText() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetRelatedSearchesText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.getRelatedSearchesText();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getResultPagesText() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetResultPagesText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.getResultPagesText();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getResultPath() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetResultPath_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.getResultPath();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getSearchButtonText() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetSearchButtonText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.getSearchButtonText();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getSearchIn() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetSearchIn_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.getSearchIn();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getSearchTrendsText() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetSearchTrendsText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.getSearchTrendsText();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getSimilarPagesText() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetSimilarPagesText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.getSimilarPagesText();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getSpellcheckText() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetSpellcheckText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.getSpellcheckText();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getStatisticsText() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testGetStatisticsText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.getStatisticsText();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setAdvancePage(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetAdvancePage_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");
		String advancePage = "";

		fixture.setAdvancePage(advancePage);

		// add additional test code here
	}

	/**
	 * Run the void setNextText(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetNextText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");
		String nextText = "";

		fixture.setNextText(nextText);

		// add additional test code here
	}

	/**
	 * Run the void setNoResultsText(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetNoResultsText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");
		String noResultsText = "";

		fixture.setNoResultsText(noResultsText);

		// add additional test code here
	}

	/**
	 * Run the void setPreviousText(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetPreviousText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");
		String previousText = "";

		fixture.setPreviousText(previousText);

		// add additional test code here
	}

	/**
	 * Run the void setRelatedSearchesText(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetRelatedSearchesText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");
		String relatedSearchesText = "";

		fixture.setRelatedSearchesText(relatedSearchesText);

		// add additional test code here
	}

	/**
	 * Run the void setResultPagesText(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetResultPagesText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");
		String resultPagesText = "";

		fixture.setResultPagesText(resultPagesText);

		// add additional test code here
	}

	/**
	 * Run the void setResultPath(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetResultPath_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");
		String resultPath = "";

		fixture.setResultPath(resultPath);

		// add additional test code here
	}

	/**
	 * Run the void setSearchButtonText(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetSearchButtonText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");
		String searchButtonText = "";

		fixture.setSearchButtonText(searchButtonText);

		// add additional test code here
	}

	/**
	 * Run the void setSearchIn(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetSearchIn_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");
		String searchIn = "";

		fixture.setSearchIn(searchIn);

		// add additional test code here
	}

	/**
	 * Run the void setSearchTrendsText(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetSearchTrendsText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");
		String searchTrendsText = "";

		fixture.setSearchTrendsText(searchTrendsText);

		// add additional test code here
	}

	/**
	 * Run the void setSimilarPagesText(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetSimilarPagesText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");
		String similarPagesText = "";

		fixture.setSimilarPagesText(similarPagesText);

		// add additional test code here
	}

	/**
	 * Run the void setSpellcheckText(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetSpellcheckText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");
		String spellcheckText = "";

		fixture.setSpellcheckText(spellcheckText);

		// add additional test code here
	}

	/**
	 * Run the void setStatisticsText(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:31 PM
	 */
	@Test
	public void testSetStatisticsText_1()
		throws Exception {
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");
		String statisticsText = "";

		fixture.setStatisticsText(statisticsText);

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
		SimpleSearchBean fixture = new SimpleSearchBean();
		fixture.setAdvancePage("");
		fixture.setStatisticsText("");
		fixture.setSearchButtonText("");
		fixture.setSpellcheckText("");
		fixture.setSearchIn("");
		fixture.setPreviousText("");
		fixture.setSimilarPagesText("");
		fixture.setSearchTrendsText("");
		fixture.setResultPagesText("");
		fixture.setRelatedSearchesText("");
		fixture.setNoResultsText("");
		fixture.setResultPath("");
		fixture.setNextText("");

		String result = fixture.toString();

		// add additional test code here
		assertEquals("SimpleSearchBean [nextText=, searchIn=, searchButtonText=, statisticsText=, noResultsText=, spellcheckText=, similarPagesText=, relatedSearchesText=, searchTrendsText=, resultPagesText=, previousText=]", result);
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
		new org.junit.runner.JUnitCore().run(SimpleSearchBeanTest.class);
	}
}