/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tc.model.MostViewedArticleBean;

/**
 * @author KARTEEKA RAJA GUMPA
 *
 */
public class MostViewedArticleBeanTest {

	/**
	 * Test method for {@link com.tc.model.MostViewedArticleBean#getTitle()}.
	 */
	@Test
	public void testGetTitle() {
		MostViewedArticleBean mostViewedArticleBean = new MostViewedArticleBean();
		mostViewedArticleBean.setArticlePath("");
		mostViewedArticleBean.setTitle("");
		String result = mostViewedArticleBean.getTitle();
		assertEquals("", result);
	}

	/**
	 * Test method for {@link com.tc.model.MostViewedArticleBean#setTitle(java.lang.String)}.
	 */
	@Test
	public void testSetTitle() {
		MostViewedArticleBean mostViewedArticleBean = new MostViewedArticleBean();
		mostViewedArticleBean.setArticlePath("");
		String result = "";
		mostViewedArticleBean.setTitle(result);
		}

	/**
	 * Test method for {@link com.tc.model.MostViewedArticleBean#getArticlePath()}.
	 */
	@Test
	public void testGetArticlePath() {
		MostViewedArticleBean mostViewedArticleBean = new MostViewedArticleBean();
		mostViewedArticleBean.setArticlePath("");
		mostViewedArticleBean.setTitle("");
		String result = mostViewedArticleBean.getArticlePath();
		assertEquals("", result);
	}

	/**
	 * Test method for {@link com.tc.model.MostViewedArticleBean#setArticlePath(java.lang.String)}.
	 */
	@Test
	public void testSetArticlePath() {
		MostViewedArticleBean mostViewedArticleBean = new MostViewedArticleBean();
		mostViewedArticleBean.setTitle("");
		String result ="";
		mostViewedArticleBean.setArticlePath(result);
	}

	

}
