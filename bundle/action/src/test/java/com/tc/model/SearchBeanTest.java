/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import org.junit.*;

import com.tc.model.AdvancedSearchBean;
import com.tc.model.SearchBean;
import com.tc.model.SimpleSearchBean;

import static org.junit.Assert.*;

/**
 * The class <code>SearchBeanTest</code> contains tests for the class <code>{@link SearchBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:30 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class SearchBeanTest {
	/**
	 * Run the SearchBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSearchBean_1()
		throws Exception {
		SearchBean result = new SearchBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the AdvancedSearchBean getAdvancedSearchBean() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetAdvancedSearchBean_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());

		AdvancedSearchBean result = fixture.getAdvancedSearchBean();

		// add additional test code here
		assertNotNull(result);
		assertEquals("AdvancedSearchBean [section=null, keyword=null, author=null, contentType=null, timeRange=null, tabSectionNodes=null]", result.toString());
		assertEquals(null, result.getContentType());
		assertEquals(null, result.getKeyword());
		assertEquals(null, result.getSection());
		assertEquals(null, result.getAuthor());
		assertEquals(null, result.getSectionMenus());
		assertEquals(null, result.getTimeRange());
	}

	/**
	 * Run the String getArticlePath() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetArticlePath_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());

		String result = fixture.getArticlePath();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getContentPath() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetContentPath_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());

		String result = fixture.getContentPath();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getCurrentIndex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetCurrentIndex_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());

		String result = fixture.getCurrentIndex();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getDamPath() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetDamPath_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());

		String result = fixture.getDamPath();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getResultPath() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetResultPath_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());

		String result = fixture.getResultPath();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the SimpleSearchBean getSimpleSearchBean() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetSimpleSearchBean_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());

		SimpleSearchBean result = fixture.getSimpleSearchBean();

		// add additional test code here
		assertNotNull(result);
		assertEquals("SimpleSearchBean [nextText=null, searchIn=null, searchButtonText=Search, statisticsText=Results, noResultsText=null, spellcheckText=null, similarPagesText=Similar Pages, relatedSearchesText=null, searchTrendsText=null, resultPagesText=null, previousText=null]", result.toString());
		assertEquals(null, result.getPreviousText());
		assertEquals(null, result.getRelatedSearchesText());
		assertEquals(null, result.getResultPath());
		assertEquals("Search", result.getSearchButtonText());
		assertEquals("Results", result.getStatisticsText());
		assertEquals(null, result.getNoResultsText());
		assertEquals(null, result.getSpellcheckText());
		assertEquals("Similar Pages", result.getSimilarPagesText());
		assertEquals(null, result.getSearchTrendsText());
		assertEquals(null, result.getResultPagesText());
		assertEquals(null, result.getAdvancePage());
		assertEquals(null, result.getSearchIn());
		assertEquals(null, result.getNextText());
	}

	/**
	 * Run the void setAdvancedSearchBean(AdvancedSearchBean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetAdvancedSearchBean_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());
		AdvancedSearchBean advancedSearchBean = new AdvancedSearchBean();

		fixture.setAdvancedSearchBean(advancedSearchBean);

		// add additional test code here
	}

	/**
	 * Run the void setArticlePath(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetArticlePath_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());
		String articlePath = "";

		fixture.setArticlePath(articlePath);

		// add additional test code here
	}

	/**
	 * Run the void setContentPath(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetContentPath_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());
		String contentPath = "";

		fixture.setContentPath(contentPath);

		// add additional test code here
	}

	/**
	 * Run the void setCurrentIndex(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetCurrentIndex_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());
		String currentIndex = "";

		fixture.setCurrentIndex(currentIndex);

		// add additional test code here
	}

	/**
	 * Run the void setDamPath(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetDamPath_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());
		String damPath = "";

		fixture.setDamPath(damPath);

		// add additional test code here
	}

	/**
	 * Run the void setResultPath(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetResultPath_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());
		String resultPath = "";

		fixture.setResultPath(resultPath);

		// add additional test code here
	}

	/**
	 * Run the void setSimpleSearchBean(SimpleSearchBean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetSimpleSearchBean_1()
		throws Exception {
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());
		SimpleSearchBean simpleSearchBean = new SimpleSearchBean();

		fixture.setSimpleSearchBean(simpleSearchBean);

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
		SearchBean fixture = new SearchBean();
		fixture.setDamPath("");
		fixture.setAdvancedSearchBean(new AdvancedSearchBean());
		fixture.setArticlePath("");
		fixture.setContentPath("");
		fixture.setResultPath("");
		fixture.setCurrentIndex("");
		fixture.setSimpleSearchBean(new SimpleSearchBean());

		String result = fixture.toString();

		// add additional test code here
		assertEquals("SearchBean [simpleSearchBean=SimpleSearchBean [nextText=null, searchIn=null, searchButtonText=Search, statisticsText=Results, noResultsText=null, spellcheckText=null, similarPagesText=Similar Pages, relatedSearchesText=null, searchTrendsText=null, resultPagesText=null, previousText=null], advancedSearchBean=AdvancedSearchBean [section=null, keyword=null, author=null, contentType=null, timeRange=null, tabSectionNodes=null], contentPath=, damPath=, articlePath=]", result);
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
		new org.junit.runner.JUnitCore().run(SearchBeanTest.class);
	}
}