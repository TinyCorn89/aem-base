/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.tc.model.BannerBean;
import com.tc.model.CarouselBean;

import static org.junit.Assert.*;

/**
 * The class <code>CarouselBeanTest</code> contains tests for the class <code>{@link CarouselBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:26 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class CarouselBeanTest {
	/**
	 * Run the CarouselBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testCarouselBean_1()
		throws Exception {
		CarouselBean result = new CarouselBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testEquals_1()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
		CarouselBean obj = new CarouselBean();
		obj.setBannersList(new ArrayList<BannerBean>());
		obj.setCarouselType("");
		obj.setTimeUnits("");
		obj.setTimeInterval(1L);
		obj.setMultiple(true);

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testEquals_2()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
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
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testEquals_3()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
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
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testEquals_4()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
		CarouselBean obj = new CarouselBean();
		obj.setBannersList(new ArrayList<BannerBean>());

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testEquals_5()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
		CarouselBean obj = new CarouselBean();
		obj.setBannersList(new ArrayList<BannerBean>());
		obj.setMultiple(true);

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testEquals_6()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
		CarouselBean obj = new CarouselBean();
		obj.setBannersList(new ArrayList<BannerBean>());
		obj.setTimeInterval(1L);
		obj.setMultiple(true);

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testEquals_7()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
		CarouselBean obj = new CarouselBean();
		obj.setBannersList(new ArrayList<BannerBean>());
		obj.setTimeUnits("");
		obj.setTimeInterval(1L);
		obj.setMultiple(true);

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testEquals_8()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
		CarouselBean obj = new CarouselBean();
		obj.setBannersList(new ArrayList<BannerBean>());
		obj.setTimeUnits("");
		obj.setTimeInterval(1L);
		obj.setMultiple(true);

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean equals(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testEquals_9()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits((String) null);
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
		CarouselBean obj = new CarouselBean();
		obj.setBannersList(new ArrayList<BannerBean>());
		obj.setTimeUnits((String) null);
		obj.setTimeInterval(1L);
		obj.setMultiple(true);

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the List<BannerBean> getBannersList() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testGetBannersList_1()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);

		List<BannerBean> result = fixture.getBannersList();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String getCarouselType() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testGetCarouselType_1()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);

		String result = fixture.getCarouselType();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the long getTimeInterval() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testGetTimeInterval_1()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);

		long result = fixture.getTimeInterval();

		// add additional test code here
		assertEquals(1L, result);
	}

	/**
	 * Run the String getTimeUnits() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testGetTimeUnits_1()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);

		String result = fixture.getTimeUnits();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the int hashCode() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testHashCode_1()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(null);
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(false);

		int result = fixture.hashCode();

		// add additional test code here
		assertEquals(2112309, result);
	}

	/**
	 * Run the int hashCode() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testHashCode_2()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits((String) null);
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);

		int result = fixture.hashCode();

		// add additional test code here
		assertEquals(2136334, result);
	}

	/**
	 * Run the boolean isMultiple() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testIsMultiple_1()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);

		boolean result = fixture.isMultiple();

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean isMultiple() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testIsMultiple_2()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(false);

		boolean result = fixture.isMultiple();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the void setBannersList(List<BannerBean>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testSetBannersList_1()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
		List<BannerBean> bannersList = new ArrayList<BannerBean>();

		fixture.setBannersList(bannersList);

		// add additional test code here
	}

	/**
	 * Run the void setCarouselType(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testSetCarouselType_1()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
		String carouselType = "";

		fixture.setCarouselType(carouselType);

		// add additional test code here
	}

	/**
	 * Run the void setMultiple(boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testSetMultiple_1()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
		boolean multiple = true;

		fixture.setMultiple(multiple);

		// add additional test code here
	}

	/**
	 * Run the void setTimeInterval(long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testSetTimeInterval_1()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
		long timeInterval = 1L;

		fixture.setTimeInterval(timeInterval);

		// add additional test code here
	}

	/**
	 * Run the void setTimeUnits(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testSetTimeUnits_1()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);
		String timeUnits = "";

		fixture.setTimeUnits(timeUnits);

		// add additional test code here
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		CarouselBean fixture = new CarouselBean();
		fixture.setBannersList(new ArrayList<BannerBean>());
		fixture.setCarouselType("");
		fixture.setTimeUnits("");
		fixture.setTimeInterval(1L);
		fixture.setMultiple(true);

		String result = fixture.toString();

		// add additional test code here
		assertEquals("CarouselBean [bannersList=[], timeInterval=1, timeUnits=, multiple=true]", result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
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
	 * @generatedBy CodePro at 12/9/13 2:26 PM
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
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CarouselBeanTest.class);
	}
}