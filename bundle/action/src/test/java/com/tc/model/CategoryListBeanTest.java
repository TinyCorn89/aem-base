/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.tc.model.CategoryBean;
import com.tc.model.CategoryListBean;

import static org.junit.Assert.*;

/**
 * The class <code>CategoryListBeanTest</code> contains tests for the class <code>{@link CategoryListBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:27 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class CategoryListBeanTest {
	/**
	 * Run the CategoryListBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testCategoryListBean_1()
		throws Exception {
		CategoryListBean result = new CategoryListBean();
		assertNotNull(result);
		// add additional test code here
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
		CategoryListBean fixture = new CategoryListBean();
		fixture.setListOfCategories(new ArrayList<CategoryBean>());
		CategoryListBean obj = new CategoryListBean();
		obj.setListOfCategories(new ArrayList<CategoryBean>());

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
		CategoryListBean fixture = new CategoryListBean();
		fixture.setListOfCategories(new ArrayList<CategoryBean>());
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
		CategoryListBean fixture = new CategoryListBean();
		fixture.setListOfCategories(new ArrayList<CategoryBean>());
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
		CategoryListBean fixture = new CategoryListBean();
		fixture.setListOfCategories(new ArrayList<CategoryBean>());
		CategoryListBean obj = new CategoryListBean();
		obj.setListOfCategories(new ArrayList<CategoryBean>());

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
	public void testEquals_5()
		throws Exception {
		CategoryListBean fixture = new CategoryListBean();
		fixture.setListOfCategories(new ArrayList<CategoryBean>());
		CategoryListBean obj = new CategoryListBean();
		obj.setListOfCategories(new ArrayList<CategoryBean>());

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
		CategoryListBean fixture = new CategoryListBean();
		fixture.setListOfCategories(null);
		CategoryListBean obj = new CategoryListBean();
		obj.setListOfCategories(null);

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the List<CategoryBean> getListOfCategories() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testGetListOfCategories_1()
		throws Exception {
		CategoryListBean fixture = new CategoryListBean();
		fixture.setListOfCategories(new ArrayList<CategoryBean>());

		List<CategoryBean> result = fixture.getListOfCategories();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
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
		CategoryListBean fixture = new CategoryListBean();
		fixture.setListOfCategories(new ArrayList<CategoryBean>());

		int result = fixture.hashCode();

		// add additional test code here
		assertEquals(32, result);
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
		CategoryListBean fixture = new CategoryListBean();
		fixture.setListOfCategories(null);

		int result = fixture.hashCode();

		// add additional test code here
		assertEquals(31, result);
	}

	/**
	 * Run the void setListOfCategories(List<CategoryBean>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:27 PM
	 */
	@Test
	public void testSetListOfCategories_1()
		throws Exception {
		CategoryListBean fixture = new CategoryListBean();
		fixture.setListOfCategories(new ArrayList<CategoryBean>());
		List<CategoryBean> listOfCategories = new ArrayList<CategoryBean>();

		fixture.setListOfCategories(listOfCategories);

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
		CategoryListBean fixture = new CategoryListBean();
		fixture.setListOfCategories(new ArrayList<CategoryBean>());

		String result = fixture.toString();

		// add additional test code here
		assertEquals("CategoryListBean [listOfCategories=[]]", result);
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
		new org.junit.runner.JUnitCore().run(CategoryListBeanTest.class);
	}
}