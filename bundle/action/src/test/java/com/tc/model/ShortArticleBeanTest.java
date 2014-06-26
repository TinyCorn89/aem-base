/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.tc.model.LongArticleDetailsBean;
import com.tc.model.ShortArticleBean;

import static org.junit.Assert.*;

/**
 * The class <code>ShortArticleBeanTest</code> contains tests for the class <code>{@link ShortArticleBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:30 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class ShortArticleBeanTest {
	/**
	 * Run the ShortArticleBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testShortArticleBean_1()
		throws Exception {
		ShortArticleBean result = new ShortArticleBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the List<LongArticleDetailsBean> getArticles() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetArticles_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);

		List<LongArticleDetailsBean> result = fixture.getArticles();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String getButtonText() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetButtonText_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);

		String result = fixture.getButtonText();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getErrorMessage() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetErrorMessage_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);

		String result = fixture.getErrorMessage();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the long getIndex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetIndex_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);

		long result = fixture.getIndex();

		// add additional test code here
		assertEquals(1L, result);
	}

	/**
	 * Run the Integer getMinutes() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetMinutes_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);

		Integer result = fixture.getMinutes();

		// add additional test code here
		assertNotNull(result);
		assertEquals("1", result.toString());
		assertEquals((byte) 1, result.byteValue());
		assertEquals((short) 1, result.shortValue());
		assertEquals(1, result.intValue());
		assertEquals(1L, result.longValue());
		assertEquals(1.0f, result.floatValue(), 1.0f);
		assertEquals(1.0, result.doubleValue(), 1.0);
	}

	/**
	 * Run the String getShowImpressionFor() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetShowImpressionFor_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);

		String result = fixture.getShowImpressionFor();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the boolean isShowImpressions() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testIsShowImpressions_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);

		boolean result = fixture.isShowImpressions();

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean isShowImpressions() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testIsShowImpressions_2()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(false);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);

		boolean result = fixture.isShowImpressions();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the void setArticles(List<LongArticleDetailsBean>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetArticles_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);
		List<LongArticleDetailsBean> articles = new ArrayList<LongArticleDetailsBean>();

		fixture.setArticles(articles);

		// add additional test code here
	}

	/**
	 * Run the void setButtonText(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetButtonText_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);
		String buttonText = "";

		fixture.setButtonText(buttonText);

		// add additional test code here
	}

	/**
	 * Run the void setErrorMessage(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetErrorMessage_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);
		String errorMessage = "";

		fixture.setErrorMessage(errorMessage);

		// add additional test code here
	}

	/**
	 * Run the void setIndex(long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetIndex_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);
		long index = 1L;

		fixture.setIndex(index);

		// add additional test code here
	}

	/**
	 * Run the void setMinutes(Integer) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetMinutes_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);
		Integer minutes = new Integer(1);

		fixture.setMinutes(minutes);

		// add additional test code here
	}

	/**
	 * Run the void setShowImpressionFor(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetShowImpressionFor_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);
		String showImpressionFor = "";

		fixture.setShowImpressionFor(showImpressionFor);

		// add additional test code here
	}

	/**
	 * Run the void setShowImpressions(boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetShowImpressions_1()
		throws Exception {
		ShortArticleBean fixture = new ShortArticleBean();
		fixture.setMinutes(new Integer(1));
		fixture.setShowImpressionFor("");
		fixture.setShowImpressions(true);
		fixture.setButtonText("");
		fixture.setArticles(new ArrayList<LongArticleDetailsBean>());
		fixture.setErrorMessage("");
		fixture.setIndex(1L);
		boolean showImpressions = true;

		fixture.setShowImpressions(showImpressions);

		// add additional test code here
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
		new org.junit.runner.JUnitCore().run(ShortArticleBeanTest.class);
	}
}