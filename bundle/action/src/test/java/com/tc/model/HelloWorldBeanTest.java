/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import org.junit.*;

import com.tc.model.HelloWorldBean;

import static org.junit.Assert.*;

/**
 * The class <code>HelloWorldBeanTest</code> contains tests for the class <code>{@link HelloWorldBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:29 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class HelloWorldBeanTest {
	/**
	 * Run the HelloWorldBean() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testHelloWorldBean_1()
		throws Exception {

		HelloWorldBean result = new HelloWorldBean();

		// add additional test code here
		assertNotNull(result);
		assertEquals("HelloWorldBean [message=null, appConfigMessage=null]", result.toString());
		assertEquals(null, result.getMessage());
		assertEquals(null, result.getAppConfigMessage());
	}

	/**
	 * Run the HelloWorldBean(String,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testHelloWorldBean_2()
		throws Exception {
		String message = "";
		String appConfigMessage = "";

		HelloWorldBean result = new HelloWorldBean(message, appConfigMessage);

		// add additional test code here
		assertNotNull(result);
		assertEquals("HelloWorldBean [message=, appConfigMessage=]", result.toString());
		assertEquals("", result.getMessage());
		assertEquals("", result.getAppConfigMessage());
	}

	/**
	 * Run the String getAppConfigMessage() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetAppConfigMessage_1()
		throws Exception {
		HelloWorldBean fixture = new HelloWorldBean("", "");

		String result = fixture.getAppConfigMessage();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getMessage() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetMessage_1()
		throws Exception {
		HelloWorldBean fixture = new HelloWorldBean("", "");

		String result = fixture.getMessage();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setAppConfigMessage(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetAppConfigMessage_1()
		throws Exception {
		HelloWorldBean fixture = new HelloWorldBean("", "");
		String appConfigMessage = "";

		fixture.setAppConfigMessage(appConfigMessage);

		// add additional test code here
	}

	/**
	 * Run the void setMessage(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetMessage_1()
		throws Exception {
		HelloWorldBean fixture = new HelloWorldBean("", "");
		String message = "";

		fixture.setMessage(message);

		// add additional test code here
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		HelloWorldBean fixture = new HelloWorldBean("", "");

		String result = fixture.toString();

		// add additional test code here
		assertEquals("HelloWorldBean [message=, appConfigMessage=]", result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
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
	 * @generatedBy CodePro at 12/9/13 2:29 PM
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
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(HelloWorldBeanTest.class);
	}
}