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

import com.tc.model.MostCommentedArticleBean;
import com.tc.model.MostViewedArticleBean;

/**
 * @author KARTEEKA RAJA GUMPA
 * 
 */
public class MostCommentedArticleBeanTest {

	/**
	 * Test method for
	 * {@link com.tc.model.MostCommentedArticleBean#getArticleParent()}
	 * .
	 */
	@Test
	public void testGetArticleParent() {
		MostCommentedArticleBean mostCommentedArticleBean = new MostCommentedArticleBean();
		mostCommentedArticleBean.setArticleParent("");
		mostCommentedArticleBean.setHeader("");
		mostCommentedArticleBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostCommentedArticleBean.setNoOfArticles(0);
		mostCommentedArticleBean.setSeeAllText("");
		String rsult = mostCommentedArticleBean.getArticleParent();
		assertEquals("", rsult);
	}

	/**
	 * Test method for
	 * {@link com.tc.model.MostCommentedArticleBean#setArticleParent(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetArticleParent() {
		MostCommentedArticleBean mostCommentedArticleBean = new MostCommentedArticleBean();
		mostCommentedArticleBean.setHeader("");
		mostCommentedArticleBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostCommentedArticleBean.setNoOfArticles(0);
		mostCommentedArticleBean.setSeeAllText("");
		String result = "";
		mostCommentedArticleBean.setArticleParent(result);
	}

	/**
	 * Test method for
	 * {@link com.tc.model.MostCommentedArticleBean#getNoOfArticles()}
	 * .
	 */
	@Test
	public void testGetNoOfArticles() {
		MostCommentedArticleBean mostCommentedArticleBean = new MostCommentedArticleBean();
		mostCommentedArticleBean.setArticleParent("");
		mostCommentedArticleBean.setHeader("");
		mostCommentedArticleBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostCommentedArticleBean.setNoOfArticles(0);
		mostCommentedArticleBean.setSeeAllText("");
		int rsult = mostCommentedArticleBean.getNoOfArticles();
		assertEquals(0, rsult);
	}

	/**
	 * Test method for
	 * {@link com.tc.model.MostCommentedArticleBean#setNoOfArticles(int)}
	 * .
	 */
	@Test
	public void testSetNoOfArticles() {
		MostCommentedArticleBean mostCommentedArticleBean = new MostCommentedArticleBean();
		mostCommentedArticleBean.setArticleParent("");
		mostCommentedArticleBean.setHeader("");
		mostCommentedArticleBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostCommentedArticleBean.setSeeAllText("");
		int result = 0;
		mostCommentedArticleBean.setNoOfArticles(result);
	}

	/**
	 * Test method for
	 * {@link com.tc.model.MostCommentedArticleBean#getHeader()}.
	 */
	@Test
	public void testGetHeader() {
		MostCommentedArticleBean mostCommentedArticleBean = new MostCommentedArticleBean();
		mostCommentedArticleBean.setArticleParent("");
		mostCommentedArticleBean.setHeader("");
		mostCommentedArticleBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostCommentedArticleBean.setNoOfArticles(0);
		mostCommentedArticleBean.setSeeAllText("");
		String rsult = mostCommentedArticleBean.getHeader();
		assertEquals("", rsult);

	}

	/**
	 * Test method for
	 * {@link com.tc.model.MostCommentedArticleBean#setHeader(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetHeader() {
		MostCommentedArticleBean mostCommentedArticleBean = new MostCommentedArticleBean();
		mostCommentedArticleBean.setArticleParent("");
		mostCommentedArticleBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostCommentedArticleBean.setNoOfArticles(0);
		mostCommentedArticleBean.setSeeAllText("");
		String result = "";
		mostCommentedArticleBean.setHeader(result);

	}

	/**
	 * Test method for
	 * {@link com.tc.model.MostCommentedArticleBean#getSeeAllText()}
	 * .
	 */
	@Test
	public void testGetSeeAllText() {
		MostCommentedArticleBean mostCommentedArticleBean = new MostCommentedArticleBean();
		mostCommentedArticleBean.setArticleParent("");
		mostCommentedArticleBean.setHeader("");
		mostCommentedArticleBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostCommentedArticleBean.setNoOfArticles(0);
		mostCommentedArticleBean.setSeeAllText("");
		String rsult = mostCommentedArticleBean.getSeeAllText();
		assertEquals("", rsult);
	}

	/**
	 * Test method for
	 * {@link com.tc.model.MostCommentedArticleBean#setSeeAllText(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetSeeAllText() {
		MostCommentedArticleBean mostCommentedArticleBean = new MostCommentedArticleBean();
		mostCommentedArticleBean.setArticleParent("");
		mostCommentedArticleBean.setHeader("");
		mostCommentedArticleBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostCommentedArticleBean.setNoOfArticles(0);
		String result = "";
		mostCommentedArticleBean.setSeeAllText(result);
	}

	/**
	 * Test method for
	 * {@link com.tc.model.MostCommentedArticleBean#getMostviewedArticleList()}
	 * .
	 */
	@Test
	public void testGetMostviewedArticleList() {
		MostCommentedArticleBean mostCommentedArticleBean = new MostCommentedArticleBean();
		mostCommentedArticleBean.setArticleParent("");
		mostCommentedArticleBean.setHeader("");
		mostCommentedArticleBean
				.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostCommentedArticleBean.setNoOfArticles(0);
		mostCommentedArticleBean.setSeeAllText("");
		List<MostViewedArticleBean> mostviewedArticleList = mostCommentedArticleBean
				.getMostviewedArticleList();
		assertEquals(0, mostviewedArticleList.size());
	}

	/**
	 * Test method for
	 * {@link com.tc.model.MostCommentedArticleBean#setMostviewedArticleList(java.util.List)}
	 * .
	 */
	@Test
	public void testSetMostviewedArticleList() {
		MostCommentedArticleBean mostCommentedArticleBean = new MostCommentedArticleBean();
		mostCommentedArticleBean.setArticleParent("");
		mostCommentedArticleBean.setHeader("");
		mostCommentedArticleBean.setNoOfArticles(0);
		mostCommentedArticleBean.setSeeAllText("");
		List<MostViewedArticleBean> mostviewedArticleList = new ArrayList<MostViewedArticleBean>();
		mostCommentedArticleBean
				.setMostviewedArticleList(mostviewedArticleList);
	}

}
