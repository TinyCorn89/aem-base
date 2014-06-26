/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import org.junit.*;

import com.tc.model.BannerBean;

import static org.junit.Assert.*;

/**
 * The class <code>BannerBeanTest</code> contains tests for the class <code>{@link BannerBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:26 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class BannerBeanTest {
	/**
	 * Run the BannerBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testBannerBean_1()
		throws Exception {
		BannerBean result = new BannerBean();
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
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
		BannerBean obj = new BannerBean();
		obj.setCategory("");
		obj.setSliderImage("");
		obj.setTitle("");
		obj.setDescription("");
		obj.setImageUrl("");

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
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
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
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
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
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
		BannerBean obj = new BannerBean();
		obj.setCategory("");

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
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
		BannerBean obj = new BannerBean();
		obj.setCategory("");
		obj.setDescription("");

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
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
		BannerBean obj = new BannerBean();
		obj.setCategory("");
		obj.setDescription("");
		obj.setImageUrl("");

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
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
		BannerBean obj = new BannerBean();
		obj.setCategory("");
		obj.setSliderImage("");
		obj.setDescription("");
		obj.setImageUrl("");

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
	public void testEquals_8()
		throws Exception {
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
		BannerBean obj = new BannerBean();
		obj.setCategory("");
		obj.setSliderImage("");
		obj.setTitle("");
		obj.setDescription("");
		obj.setImageUrl("");

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
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
		BannerBean obj = new BannerBean();
		obj.setCategory("");
		obj.setSliderImage("");
		obj.setTitle("");
		obj.setDescription("");
		obj.setImageUrl("");

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
	public void testEquals_10()
		throws Exception {
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle((String) null);
		fixture.setDescription("");
		fixture.setImageUrl("");
		BannerBean obj = new BannerBean();
		obj.setCategory("");
		obj.setSliderImage("");
		obj.setTitle((String) null);
		obj.setDescription("");
		obj.setImageUrl("");

		boolean result = fixture.equals(obj);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the String getCategory() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testGetCategory_1()
		throws Exception {
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");

		String result = fixture.getCategory();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getDescription() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testGetDescription_1()
		throws Exception {
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");

		String result = fixture.getDescription();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getImageUrl() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testGetImageUrl_1()
		throws Exception {
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");

		String result = fixture.getImageUrl();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getSliderImage() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testGetSliderImage_1()
		throws Exception {
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");

		String result = fixture.getSliderImage();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getTitle() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testGetTitle_1()
		throws Exception {
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");

		String result = fixture.getTitle();

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
		BannerBean fixture = new BannerBean();
		fixture.setCategory((String) null);
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription((String) null);
		fixture.setImageUrl("");

		int result = fixture.hashCode();

		// add additional test code here
		assertEquals(28629151, result);
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
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage((String) null);
		fixture.setTitle((String) null);
		fixture.setDescription("");
		fixture.setImageUrl((String) null);

		int result = fixture.hashCode();

		// add additional test code here
		assertEquals(28629151, result);
	}

	/**
	 * Run the void setCategory(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testSetCategory_1()
		throws Exception {
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
		String category = "";

		fixture.setCategory(category);

		// add additional test code here
	}

	/**
	 * Run the void setDescription(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testSetDescription_1()
		throws Exception {
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
		String description = "";

		fixture.setDescription(description);

		// add additional test code here
	}

	/**
	 * Run the void setImageUrl(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testSetImageUrl_1()
		throws Exception {
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
		String imageUrl = "";

		fixture.setImageUrl(imageUrl);

		// add additional test code here
	}

	/**
	 * Run the void setSliderImage(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testSetSliderImage_1()
		throws Exception {
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
		String sliderImage = "";

		fixture.setSliderImage(sliderImage);

		// add additional test code here
	}

	/**
	 * Run the void setTitle(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:26 PM
	 */
	@Test
	public void testSetTitle_1()
		throws Exception {
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");
		String title = "";

		fixture.setTitle(title);

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
		BannerBean fixture = new BannerBean();
		fixture.setCategory("");
		fixture.setSliderImage("");
		fixture.setTitle("");
		fixture.setDescription("");
		fixture.setImageUrl("");

		String result = fixture.toString();

		// add additional test code here
		assertEquals("BannerBean [sliderImage=, category=, description=, title=, imageUrl=]", result);
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
		new org.junit.runner.JUnitCore().run(BannerBeanTest.class);
	}
}