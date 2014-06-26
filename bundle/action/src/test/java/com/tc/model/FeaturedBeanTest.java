/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tc.model.FeaturedBean;
import com.tc.model.MostViewedArticleBean;

/**
 * @author KARTEEKA RAJA GUMPA
 * 
 */
public class FeaturedBeanTest {

	/**
	 * Test method for
	 * {@link com.tc.model.FeaturedBean#getParentPath()}.
	 */
	@Test
	public void testGetParentPath() {
		FeaturedBean featuredBean = new FeaturedBean();
		featuredBean.setCqTag("");
		featuredBean.setHeader("");
		featuredBean.setParentPath("");
		featuredBean.setSeeAllText("");
		featuredBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		String result = featuredBean.getParentPath();
		assertEquals("", result);

	}

	/**
	 * Test method for
	 * {@link com.tc.model.FeaturedBean#setParentPath(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetParentPath() {
		FeaturedBean featuredBean = new FeaturedBean();
		featuredBean.setCqTag("");
		featuredBean.setHeader("");
		featuredBean.setSeeAllText("");
		featuredBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		String result = "";
		featuredBean.setHeader(result);
	}

	/**
	 * Test method for
	 * {@link com.tc.model.FeaturedBean#getCqTag()}.
	 */
	@Test
	public void testGetCqTag() {
		FeaturedBean featuredBean = new FeaturedBean();
		featuredBean.setCqTag("");
		featuredBean.setHeader("");
		featuredBean.setParentPath("");
		featuredBean.setSeeAllText("");
		featuredBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		String result = featuredBean.getCqTag();
		assertEquals("", result);
	}

	/**
	 * Test method for
	 * {@link com.tc.model.FeaturedBean#setCqTag(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetCqTag() {
		FeaturedBean featuredBean = new FeaturedBean();
		featuredBean.setHeader("");
		featuredBean.setParentPath("");
		featuredBean.setSeeAllText("");
		featuredBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		String result = "";
		featuredBean.setCqTag(result);
	}

	/**
	 * Test method for
	 * {@link com.tc.model.FeaturedBean#getHeader()}.
	 */
	@Test
	public void testGetHeader() {
		FeaturedBean featuredBean = new FeaturedBean();
		featuredBean.setCqTag("");
		featuredBean.setHeader("");
		featuredBean.setParentPath("");
		featuredBean.setSeeAllText("");
		featuredBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		String result = featuredBean.getHeader();
		assertEquals("", result);
	}

	/**
	 * Test method for
	 * {@link com.tc.model.FeaturedBean#setHeader(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetHeader() {
		FeaturedBean featuredBean = new FeaturedBean();
		featuredBean.setCqTag("");
		featuredBean.setParentPath("");
		featuredBean.setSeeAllText("");
		featuredBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		String result = "";
		featuredBean.setHeader(result);

	}

	/**
	 * Test method for
	 * {@link com.tc.model.FeaturedBean#getSeeAllText()}.
	 */
	@Test
	public void testGetSeeAllText() {
		FeaturedBean featuredBean = new FeaturedBean();
		featuredBean.setCqTag("");
		featuredBean.setHeader("");
		featuredBean.setParentPath("");
		featuredBean.setSeeAllText("");
		featuredBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		String result = featuredBean.getSeeAllText();
		assertEquals("", result);
	}

	/**
	 * Test method for
	 * {@link com.tc.model.FeaturedBean#setSeeAllText(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetSeeAllText() {
		FeaturedBean featuredBean = new FeaturedBean();
		featuredBean.setCqTag("");
		featuredBean.setHeader("");
		featuredBean.setParentPath("");
		featuredBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		String result = " ";
		featuredBean.setSeeAllText(result);
	}

	/**
	 * Test method for
	 * {@link com.tc.model.FeaturedBean#getMostviewedArticleList()}
	 * .
	 */
	@Test
	public void testGetMostviewedArticleList() {
		FeaturedBean featuredBean = new FeaturedBean();
		featuredBean.setCqTag("");
		featuredBean.setHeader("");
		featuredBean.setParentPath("");
		featuredBean.setSeeAllText("");
		featuredBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		List<MostViewedArticleBean> mostviewedArticleList = featuredBean
				.getMostviewedArticleList();
		assertEquals(0, mostviewedArticleList.size());
	}

	/**
	 * Test method for
	 * {@link com.tc.model.FeaturedBean#setMostviewedArticleList(java.util.List)}
	 * .
	 */
	@Test
	public void testSetMostviewedArticleList() {
		FeaturedBean featuredBean = new FeaturedBean();
		featuredBean.setCqTag("");
		featuredBean.setHeader("");
		featuredBean.setParentPath("");
		featuredBean.setSeeAllText("");
		List<MostViewedArticleBean> mostviewedArticleList = new ArrayList<MostViewedArticleBean>();
		featuredBean.setMostviewedArticleList(mostviewedArticleList);
	}

}
