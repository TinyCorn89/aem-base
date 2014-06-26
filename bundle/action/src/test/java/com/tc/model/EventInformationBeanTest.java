/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import org.junit.*;

import com.tc.model.EventInformationBean;

import static org.junit.Assert.*;

/**
 * The class <code>EventInformationBeanTest</code> contains tests for the class <code>{@link EventInformationBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:28 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class EventInformationBeanTest {
	/**
	 * Run the EventInformationBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testEventInformationBean_1()
		throws Exception {
		EventInformationBean result = new EventInformationBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the String getAddressForGoogle() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetAddressForGoogle_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");

		String result = fixture.getAddressForGoogle();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getCity() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetCity_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");

		String result = fixture.getCity();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getCountry() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetCountry_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");

		String result = fixture.getCountry();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getDescription() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetDescription_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");

		String result = fixture.getDescription();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getFromDate() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetFromDate_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");

		String result = fixture.getFromDate();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getImage() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetImage_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");

		String result = fixture.getImage();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getShowDirections() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetShowDirections_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");

		String result = fixture.getShowDirections();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getState() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetState_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");

		String result = fixture.getState();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getTitle() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetTitle_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");

		String result = fixture.getTitle();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getToDate() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetToDate_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");

		String result = fixture.getToDate();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getVenueName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetVenueName_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");

		String result = fixture.getVenueName();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getZipcode() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testGetZipcode_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");

		String result = fixture.getZipcode();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setAddressForGoogle(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetAddressForGoogle_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");
		String addressForGoogle = "";

		fixture.setAddressForGoogle(addressForGoogle);

		// add additional test code here
	}

	/**
	 * Run the void setCity(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetCity_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");
		String city = "";

		fixture.setCity(city);

		// add additional test code here
	}

	/**
	 * Run the void setCountry(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetCountry_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");
		String country = "";

		fixture.setCountry(country);

		// add additional test code here
	}

	/**
	 * Run the void setDescription(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetDescription_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");
		String description = "";

		fixture.setDescription(description);

		// add additional test code here
	}

	/**
	 * Run the void setFromDate(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetFromDate_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");
		String fromDate = "";

		fixture.setFromDate(fromDate);

		// add additional test code here
	}

	/**
	 * Run the void setImage(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetImage_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");
		String image = "";

		fixture.setImage(image);

		// add additional test code here
	}

	/**
	 * Run the void setShowDirections(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetShowDirections_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");
		String showDirections = "";

		fixture.setShowDirections(showDirections);

		// add additional test code here
	}

	/**
	 * Run the void setState(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetState_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");
		String state = "";

		fixture.setState(state);

		// add additional test code here
	}

	/**
	 * Run the void setTitle(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetTitle_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");
		String title = "";

		fixture.setTitle(title);

		// add additional test code here
	}

	/**
	 * Run the void setToDate(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetToDate_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");
		String toDate = "";

		fixture.setToDate(toDate);

		// add additional test code here
	}

	/**
	 * Run the void setVenueName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetVenueName_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");
		String venueName = "";

		fixture.setVenueName(venueName);

		// add additional test code here
	}

	/**
	 * Run the void setZipcode(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	@Test
	public void testSetZipcode_1()
		throws Exception {
		EventInformationBean fixture = new EventInformationBean();
		fixture.setVenueName("");
		fixture.setState("");
		fixture.setZipcode("");
		fixture.setCity("");
		fixture.setFromDate("");
		fixture.setCountry("");
		fixture.setShowDirections("");
		fixture.setAddressForGoogle("");
		fixture.setTitle("");
		fixture.setToDate("");
		fixture.setDescription("");
		fixture.setImage("");
		String zipcode = "";

		fixture.setZipcode(zipcode);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 12/9/13 2:28 PM
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
	 * @generatedBy CodePro at 12/9/13 2:28 PM
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
	 * @generatedBy CodePro at 12/9/13 2:28 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(EventInformationBeanTest.class);
	}
}