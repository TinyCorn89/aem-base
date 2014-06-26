/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import org.junit.*;

import com.tc.model.CategoryBean;

import static org.junit.Assert.*;

/**
 * The class <code>CategoryBeanTest</code> contains tests for the class <code>{@link CategoryBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:27 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class CategoryBeanTest {
	/**
	 * Run the CategoryBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testCategoryBean_1()
		throws Exception {
		CategoryBean result = new CategoryBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the int compareTo(CategoryBean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testCompareTo_1()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl("");
		CategoryBean categoryBean = new CategoryBean();
		categoryBean.setCategoryName("");

		int result = fixture.compareTo(categoryBean);

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testEquals_1()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl("");
		CategoryBean obj = new CategoryBean();
		obj.setCategoryName("");
		obj.setCategoryUrl("");

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testEquals_2()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl("");
		Object obj = null;

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testEquals_3()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl("");
		Object obj = new Object();

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testEquals_4()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl("");
		CategoryBean obj = new CategoryBean();
		obj.setCategoryName("");

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testEquals_5()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl("");
		CategoryBean obj = new CategoryBean();
		obj.setCategoryName("");
		obj.setCategoryUrl("");

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testEquals_6()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl("");
		CategoryBean obj = new CategoryBean();
		obj.setCategoryName("");
		obj.setCategoryUrl("");

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testEquals_7()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl((String) null);
		CategoryBean obj = new CategoryBean();
		obj.setCategoryName("");
		obj.setCategoryUrl((String) null);

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the String getCategoryName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testGetCategoryName_1()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl("");

		String result = fixture.getCategoryName();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getCategoryUrl() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testGetCategoryUrl_1()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl("");

		String result = fixture.getCategoryUrl();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the int hashCode() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testHashCode_1()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName((String) null);
		fixture.setCategoryUrl("");

		int result = fixture.hashCode();

		// add additional test code here
		assertEquals(961, result);
	}

	/**
	 * Run the int hashCode() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testHashCode_2()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl((String) null);

		int result = fixture.hashCode();

		// add additional test code here
		assertEquals(961, result);
	}

	/**
	 * Run the void setCategoryName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testSetCategoryName_1()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl("");
		String categoryName = "";

		fixture.setCategoryName(categoryName);

		// add additional test code here
	}

	/**
	 * Run the void setCategoryUrl(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testSetCategoryUrl_1()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl("");
		String categoryUrl = "";

		fixture.setCategoryUrl(categoryUrl);

		// add additional test code here
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		CategoryBean fixture = new CategoryBean();
		fixture.setCategoryName("");
		fixture.setCategoryUrl("");

		String result = fixture.toString();

		// add additional test code here
		assertEquals("CategoryBean [categoryName=, categoryUrl=]", result);
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
		new org.junit.runner.JUnitCore().run(CategoryBeanTest.class);
	}
}