/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.tc.model.GalleryItemBean;

import static org.junit.Assert.*;

/**
 * The class <code>GalleryItemBeanTest</code> contains tests for the class <code>{@link GalleryItemBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:29 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class GalleryItemBeanTest {
	/**
	 * Run the GalleryItemBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGalleryItemBean_1()
		throws Exception {
		GalleryItemBean result = new GalleryItemBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the String getCaption() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetCaption_1()
		throws Exception {
		GalleryItemBean fixture = new GalleryItemBean();
		fixture.setCaption("");
		fixture.setItemCategory(new ArrayList<String>());
		fixture.setDamTitle("");
		fixture.setTitle("");
		fixture.setItemPath("");
		fixture.setDescription("");

		String result = fixture.getCaption();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getDamTitle() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetDamTitle_1()
		throws Exception {
		GalleryItemBean fixture = new GalleryItemBean();
		fixture.setCaption("");
		fixture.setItemCategory(new ArrayList<String>());
		fixture.setDamTitle("");
		fixture.setTitle("");
		fixture.setItemPath("");
		fixture.setDescription("");

		String result = fixture.getDamTitle();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getDescription() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetDescription_1()
		throws Exception {
		GalleryItemBean fixture = new GalleryItemBean();
		fixture.setCaption("");
		fixture.setItemCategory(new ArrayList<String>());
		fixture.setDamTitle("");
		fixture.setTitle("");
		fixture.setItemPath("");
		fixture.setDescription("");

		String result = fixture.getDescription();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the List<String> getItemCategory() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetItemCategory_1()
		throws Exception {
		GalleryItemBean fixture = new GalleryItemBean();
		fixture.setCaption("");
		fixture.setItemCategory(new ArrayList<String>());
		fixture.setDamTitle("");
		fixture.setTitle("");
		fixture.setItemPath("");
		fixture.setDescription("");

		List<String> result = fixture.getItemCategory();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the String getItemPath() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetItemPath_1()
		throws Exception {
		GalleryItemBean fixture = new GalleryItemBean();
		fixture.setCaption("");
		fixture.setItemCategory(new ArrayList<String>());
		fixture.setDamTitle("");
		fixture.setTitle("");
		fixture.setItemPath("");
		fixture.setDescription("");

		String result = fixture.getItemPath();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getTitle() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetTitle_1()
		throws Exception {
		GalleryItemBean fixture = new GalleryItemBean();
		fixture.setCaption("");
		fixture.setItemCategory(new ArrayList<String>());
		fixture.setDamTitle("");
		fixture.setTitle("");
		fixture.setItemPath("");
		fixture.setDescription("");

		String result = fixture.getTitle();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setCaption(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetCaption_1()
		throws Exception {
		GalleryItemBean fixture = new GalleryItemBean();
		fixture.setCaption("");
		fixture.setItemCategory(new ArrayList<String>());
		fixture.setDamTitle("");
		fixture.setTitle("");
		fixture.setItemPath("");
		fixture.setDescription("");
		String caption = "";

		fixture.setCaption(caption);

		// add additional test code here
	}

	/**
	 * Run the void setDamTitle(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetDamTitle_1()
		throws Exception {
		GalleryItemBean fixture = new GalleryItemBean();
		fixture.setCaption("");
		fixture.setItemCategory(new ArrayList<String>());
		fixture.setDamTitle("");
		fixture.setTitle("");
		fixture.setItemPath("");
		fixture.setDescription("");
		String damTitle = "";

		fixture.setDamTitle(damTitle);

		// add additional test code here
	}

	/**
	 * Run the void setDescription(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetDescription_1()
		throws Exception {
		GalleryItemBean fixture = new GalleryItemBean();
		fixture.setCaption("");
		fixture.setItemCategory(new ArrayList<String>());
		fixture.setDamTitle("");
		fixture.setTitle("");
		fixture.setItemPath("");
		fixture.setDescription("");
		String description = "";

		fixture.setDescription(description);

		// add additional test code here
	}

	/**
	 * Run the void setItemCategory(List<String>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetItemCategory_1()
		throws Exception {
		GalleryItemBean fixture = new GalleryItemBean();
		fixture.setCaption("");
		fixture.setItemCategory(new ArrayList<String>());
		fixture.setDamTitle("");
		fixture.setTitle("");
		fixture.setItemPath("");
		fixture.setDescription("");
		List<String> itemCategory = new ArrayList<String>();

		fixture.setItemCategory(itemCategory);

		// add additional test code here
	}

	/**
	 * Run the void setItemPath(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetItemPath_1()
		throws Exception {
		GalleryItemBean fixture = new GalleryItemBean();
		fixture.setCaption("");
		fixture.setItemCategory(new ArrayList<String>());
		fixture.setDamTitle("");
		fixture.setTitle("");
		fixture.setItemPath("");
		fixture.setDescription("");
		String itemPath = "";

		fixture.setItemPath(itemPath);

		// add additional test code here
	}

	/**
	 * Run the void setTitle(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetTitle_1()
		throws Exception {
		GalleryItemBean fixture = new GalleryItemBean();
		fixture.setCaption("");
		fixture.setItemCategory(new ArrayList<String>());
		fixture.setDamTitle("");
		fixture.setTitle("");
		fixture.setItemPath("");
		fixture.setDescription("");
		String title = "";

		fixture.setTitle(title);

		// add additional test code here
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
		new org.junit.runner.JUnitCore().run(GalleryItemBeanTest.class);
	}
}