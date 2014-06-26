/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.tc.model.HeroBannerBean;
import com.tc.model.HeroBean;

import static org.junit.Assert.*;

/**
 * The class <code>HeroBannerBeanTest</code> contains tests for the class <code>{@link HeroBannerBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:29 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class HeroBannerBeanTest {
	/**
	 * Run the HeroBannerBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testHeroBannerBean_1()
		throws Exception {
		HeroBannerBean result = new HeroBannerBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the List<HeroBean> getBannersList() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetBannersList_1()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());

		List<HeroBean> result = fixture.getBannersList();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String getMode() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetMode_1()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());

		String result = fixture.getMode();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the int getRotationRate() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetRotationRate_1()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());

		int result = fixture.getRotationRate();

		// add additional test code here
		assertEquals(1, result);
	}

	/**
	 * Run the String getType() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetType_1()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());

		String result = fixture.getType();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the boolean isLastList() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testIsLastList_1()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());

		boolean result = fixture.isLastList();

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean isLastList() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testIsLastList_2()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(false);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());

		boolean result = fixture.isLastList();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean isMultiple() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testIsMultiple_1()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());

		boolean result = fixture.isMultiple();

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean isMultiple() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testIsMultiple_2()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(false);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());

		boolean result = fixture.isMultiple();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the void setBannersList(List<HeroBean>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetBannersList_1()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());
		List<HeroBean> aBannersList = new ArrayList<HeroBean>();

		fixture.setBannersList(aBannersList);

		// add additional test code here
	}

	/**
	 * Run the void setLastList(boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetLastList_1()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());
		boolean lastList = true;

		fixture.setLastList(lastList);

		// add additional test code here
	}

	/**
	 * Run the void setMode(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetMode_1()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());
		String mode = "";

		fixture.setMode(mode);

		// add additional test code here
	}

	/**
	 * Run the void setMultiple(boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetMultiple_1()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());
		boolean multiple = true;

		fixture.setMultiple(multiple);

		// add additional test code here
	}

	/**
	 * Run the void setRotationRate(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetRotationRate_1()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());
		int aRotationRate = 1;

		fixture.setRotationRate(aRotationRate);

		// add additional test code here
	}

	/**
	 * Run the void setType(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetType_1()
		throws Exception {
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());
		String type = "";

		fixture.setType(type);

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
		HeroBannerBean fixture = new HeroBannerBean();
		fixture.setRotationRate(1);
		fixture.setMultiple(true);
		fixture.setType("");
		fixture.setLastList(true);
		fixture.setMode("");
		fixture.setBannersList(new ArrayList<HeroBean>());

		String result = fixture.toString();

		// add additional test code here
		assertEquals("HeroBannerBean [rotationRate=1, bannersList=[], mode=]", result);
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
		new org.junit.runner.JUnitCore().run(HeroBannerBeanTest.class);
	}
}