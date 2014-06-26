/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import org.junit.*;

import com.tc.model.MapBean;

import static org.junit.Assert.*;

/**
 * The class <code>MapBeanTest</code> contains tests for the class <code>{@link MapBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:30 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class MapBeanTest {
	/**
	 * Run the MapBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testMapBean_1()
		throws Exception {
		MapBean result = new MapBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testEquals_1()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");
		MapBean obj = new MapBean();
		obj.setNear("");
		obj.setWidth(1);
		obj.setHeight(1);
		obj.setLocation("");

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testEquals_2()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");
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
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testEquals_3()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");
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
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testEquals_4()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");
		MapBean obj = new MapBean();
		obj.setHeight(1);

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testEquals_5()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");
		MapBean obj = new MapBean();
		obj.setHeight(1);
		obj.setLocation("");

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testEquals_6()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");
		MapBean obj = new MapBean();
		obj.setNear("");
		obj.setHeight(1);
		obj.setLocation("");

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testEquals_7()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear((String) null);
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation((String) null);
		MapBean obj = new MapBean();
		obj.setNear((String) null);
		obj.setWidth(1);
		obj.setHeight(1);
		obj.setLocation((String) null);

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testEquals_8()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");
		MapBean obj = new MapBean();
		obj.setNear("");
		obj.setWidth(1);
		obj.setHeight(1);
		obj.setLocation("");

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the int getHeight() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetHeight_1()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");

		int result = fixture.getHeight();

		// add additional test code here
		assertEquals(1, result);
	}

	/**
	 * Run the String getLocation() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetLocation_1()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");

		String result = fixture.getLocation();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getNear() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetNear_1()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");

		String result = fixture.getNear();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the int getWidth() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testGetWidth_1()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");

		int result = fixture.getWidth();

		// add additional test code here
		assertEquals(1, result);
	}

	/**
	 * Run the int hashCode() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testHashCode_1()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation((String) null);

		int result = fixture.hashCode();

		// add additional test code here
		assertEquals(953313, result);
	}

	/**
	 * Run the int hashCode() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testHashCode_2()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear((String) null);
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");

		int result = fixture.hashCode();

		// add additional test code here
		assertEquals(953313, result);
	}

	/**
	 * Run the void setHeight(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetHeight_1()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");
		int height = 1;

		fixture.setHeight(height);

		// add additional test code here
	}

	/**
	 * Run the void setLocation(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetLocation_1()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");
		String location = "";

		fixture.setLocation(location);

		// add additional test code here
	}

	/**
	 * Run the void setNear(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetNear_1()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");
		String near = "";

		fixture.setNear(near);

		// add additional test code here
	}

	/**
	 * Run the void setWidth(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:30 PM
	 */
	@Test
	public void testSetWidth_1()
		throws Exception {
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");
		int width = 1;

		fixture.setWidth(width);

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
		MapBean fixture = new MapBean();
		fixture.setNear("");
		fixture.setWidth(1);
		fixture.setHeight(1);
		fixture.setLocation("");

		String result = fixture.toString();

		// add additional test code here
		assertEquals("MapBean [width=1, height=1, location=, near=]", result);
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
		new org.junit.runner.JUnitCore().run(MapBeanTest.class);
	}
}