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

import com.tc.model.MostViewedArticleBean;
import com.tc.model.MostViewedBean;

/**
 * @author KARTEEKA RAJA GUMPA
 *
 */
public class MostViewedBeanTest {


	/**
	 * Test method for {@link com.tc.model.MostViewedBean#getArticleParent()}.
	 */
	@Test
	public void testGetArticleParent() {
		MostViewedBean mostViewedBean = new MostViewedBean();
		mostViewedBean.setArticleParent("");
		mostViewedBean.setHeader("");
		mostViewedBean.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostViewedBean.setNoOfArticles(0);
		mostViewedBean.setSeeAllText("");
		String result = mostViewedBean.getArticleParent();
		assertEquals("",result);
	}

	/**
	 * Test method for {@link com.tc.model.MostViewedBean#setArticleParent(java.lang.String)}.
	 */
	@Test
	public void testSetArticleParent() {
		MostViewedBean mostViewedBean = new MostViewedBean();
		mostViewedBean.setArticleParent("");
		mostViewedBean.setHeader("");
		mostViewedBean.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostViewedBean.setNoOfArticles(0);
		mostViewedBean.setSeeAllText("");
		String result ="";
		mostViewedBean.setArticleParent(result);
		
		
	}

	/**
	 * Test method for {@link com.tc.model.MostViewedBean#getNoOfArticles()}.
	 */
	@Test
	public void testGetNoOfArticles() {
		MostViewedBean mostViewedBean = new MostViewedBean();
		mostViewedBean.setArticleParent("");
		mostViewedBean.setHeader("");
		mostViewedBean.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostViewedBean.setNoOfArticles(0);
		mostViewedBean.setSeeAllText("");
		int result = mostViewedBean.getNoOfArticles();
		assertEquals(0,result);
	}

	/**
	 * Test method for {@link com.tc.model.MostViewedBean#setNoOfArticles(int)}.
	 */
	@Test
	public void testSetNoOfArticles() {
		MostViewedBean mostViewedBean = new MostViewedBean();
		mostViewedBean.setArticleParent("");
		mostViewedBean.setHeader("");
		mostViewedBean.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostViewedBean.setSeeAllText("");
		int result = 0;
		mostViewedBean.setNoOfArticles(result);
		
	}

	/**
	 * Test method for {@link com.tc.model.MostViewedBean#getHeader()}.
	 */
	@Test
	public void testGetHeader() {
		MostViewedBean mostViewedBean = new MostViewedBean();
		mostViewedBean.setArticleParent("");
		mostViewedBean.setHeader("");
		mostViewedBean.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostViewedBean.setNoOfArticles(0);
		mostViewedBean.setSeeAllText("");
		String result = mostViewedBean.getHeader();
		assertEquals("",result);
		}

	/**
	 * Test method for {@link com.tc.model.MostViewedBean#setHeader(java.lang.String)}.
	 */
	@Test
	public void testSetHeader() {
		MostViewedBean mostViewedBean = new MostViewedBean();
		mostViewedBean.setArticleParent("");
		mostViewedBean.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostViewedBean.setNoOfArticles(0);
		mostViewedBean.setSeeAllText("");
		String result ="";
		mostViewedBean.setHeader(result);
	}

	/**
	 * Test method for {@link com.tc.model.MostViewedBean#getSeeAllText()}.
	 */
	@Test
	public void testGetSeeAllText() {
		MostViewedBean mostViewedBean = new MostViewedBean();
		mostViewedBean.setArticleParent("");
		mostViewedBean.setHeader("");
		mostViewedBean.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostViewedBean.setNoOfArticles(0);
		mostViewedBean.setSeeAllText("");
		String result = mostViewedBean.getSeeAllText();
		assertEquals("",result);
	}

	/**
	 * Test method for {@link com.tc.model.MostViewedBean#setSeeAllText(java.lang.String)}.
	 */
	@Test
	public void testSetSeeAllText() {
		MostViewedBean mostViewedBean = new MostViewedBean();
		mostViewedBean.setArticleParent("");
		mostViewedBean.setHeader("");
		mostViewedBean.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostViewedBean.setNoOfArticles(0);
		String result = "";
		mostViewedBean.setSeeAllText(result);
	}

	/**
	 * Test method for {@link com.tc.model.MostViewedBean#getMostviewedArticleList()}.
	 */
	@Test
	public void testGetMostviewedArticleList() {
		MostViewedBean mostViewedBean = new MostViewedBean();
		mostViewedBean.setArticleParent("");
		mostViewedBean.setHeader("");
		mostViewedBean.setMostviewedArticleList(new ArrayList<MostViewedArticleBean>());
		mostViewedBean.setNoOfArticles(0);
		mostViewedBean.setSeeAllText("");
		List<MostViewedArticleBean> mostviewedArticleList = mostViewedBean.getMostviewedArticleList();
		assertEquals(0,mostviewedArticleList.size());
	}

	/**
	 * Test method for {@link com.tc.model.MostViewedBean#setMostviewedArticleList(java.util.List)}.
	 */
	@Test
	public void testSetMostviewedArticleList() {
		MostViewedBean mostViewedBean = new MostViewedBean();
		mostViewedBean.setArticleParent("");
		mostViewedBean.setHeader("");
		mostViewedBean.setNoOfArticles(0);
		mostViewedBean.setSeeAllText("");
		List<MostViewedArticleBean> mostviewedArticleList = new ArrayList<MostViewedArticleBean>();
		mostViewedBean.setMostviewedArticleList(mostviewedArticleList);
	}

	

}
