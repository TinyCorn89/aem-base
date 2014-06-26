/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.tc.model.GalleryAlbumIteamBean;
import com.tc.model.GalleryItemBean;

import static org.junit.Assert.*;

/**
 * The class <code>GalleryAlbumIteamBeanTest</code> contains tests for the class <code>{@link GalleryAlbumIteamBean}</code>.
 *
 * @generatedBy CodePro at 12/9/13 2:29 PM
 * @author omprakashn
 * @version $Revision: 1.0 $
 */
public class GalleryAlbumIteamBeanTest {
	/**
	 * Run the GalleryAlbumIteamBean() constructor test.
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGalleryAlbumIteamBean_1()
		throws Exception {
		GalleryAlbumIteamBean result = new GalleryAlbumIteamBean();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the String getAlbumTag() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetAlbumTag_1()
		throws Exception {
		GalleryAlbumIteamBean fixture = new GalleryAlbumIteamBean();
		fixture.setIteamList(new ArrayList<GalleryItemBean>());
		fixture.setAlbumTag("");

		String result = fixture.getAlbumTag();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the List<GalleryItemBean> getIteamList() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testGetIteamList_1()
		throws Exception {
		GalleryAlbumIteamBean fixture = new GalleryAlbumIteamBean();
		fixture.setIteamList(new ArrayList<GalleryItemBean>());
		fixture.setAlbumTag("");

		List<GalleryItemBean> result = fixture.getIteamList();

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the void setAlbumTag(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetAlbumTag_1()
		throws Exception {
		GalleryAlbumIteamBean fixture = new GalleryAlbumIteamBean();
		fixture.setIteamList(new ArrayList<GalleryItemBean>());
		fixture.setAlbumTag("");
		String albumTag = "";

		fixture.setAlbumTag(albumTag);

		// add additional test code here
	}

	/**
	 * Run the void setIteamList(List<GalleryItemBean>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/9/13 2:29 PM
	 */
	@Test
	public void testSetIteamList_1()
		throws Exception {
		GalleryAlbumIteamBean fixture = new GalleryAlbumIteamBean();
		fixture.setIteamList(new ArrayList<GalleryItemBean>());
		fixture.setAlbumTag("");
		List<GalleryItemBean> iteamList = new ArrayList<GalleryItemBean>();

		fixture.setIteamList(iteamList);

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
		new org.junit.runner.JUnitCore().run(GalleryAlbumIteamBeanTest.class);
	}
}