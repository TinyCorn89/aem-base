/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Value;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.tc.action.FeaturedAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.FeaturedBean;
import com.tc.model.MostViewedArticleBean;


/**
 * @author KARTEEKA RAJA GUMPA
 * The Class FeaturedActionTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ FeaturedAction.class, FrameworkLogger.class })
public class FeaturedActionTest {
	
	/** The featured action. */
	private FeaturedAction featuredAction;
	
	/** The current node. */
	private Node currentNode;
	
	/** The tag name property. */
	private Property tagNameProperty;
	
	/** The property. */
	private Property property;
	
	/** The root path property. */
	private Property rootPathProperty;
	
	/** The page. */
	private Page page;
	
	/** The header property. */
	private Property headerProperty;
	
	/** The footer property. */
	private Property footerProperty;
	
	/** The children. */
	private Iterator<Page> children;
	
	/** The childpage. */
	private Page childPage;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		currentNode = Mockito.mock(Node.class);
		tagNameProperty = Mockito.mock(Property.class);
		property = Mockito.mock(Property.class);
		rootPathProperty = Mockito.mock(Property.class);
		page = Mockito.mock(Page.class);
		headerProperty = Mockito.mock(Property.class);
		footerProperty = Mockito.mock(Property.class);
		children = Mockito.mock(Iterator.class);
		childPage = Mockito.mock(Page.class);
		featuredAction = new FeaturedAction();

		PowerMockito.stub(
				PowerMockito.method(FeaturedAction.class, "getCurrentNode"))
				.toReturn(currentNode);
	}

	/**
	 * Test get features.
	 */
	@Test
	public void testGetFeatures() {
		Value[] values = new Value[2];
		Value value;
		value = Mockito.mock(Value.class);
		values[0] = value;
		List<MostViewedArticleBean> mostviewedArticleList = new ArrayList<MostViewedArticleBean>();

		try {
			Mockito.when(currentNode.hasProperty("tagName")).thenReturn(true);
			Mockito.when(currentNode.getProperty("tagName")).thenReturn(
					tagNameProperty);
			Mockito.when(tagNameProperty.getValues()).thenReturn(values);
			Mockito.when(values[0].getString()).thenReturn("launchpad:tagname");

			Mockito.when(currentNode.hasProperty("noOfArticles")).thenReturn(
					true);
			Mockito.when(currentNode.getProperty("noOfArticles")).thenReturn(
					property);
			Mockito.when(property.getString()).thenReturn("2");
			Mockito.when(currentNode.hasProperty("rootPath")).thenReturn(true);
			Mockito.when(currentNode.getProperty("rootPath")).thenReturn(
					rootPathProperty);
			Mockito.when(rootPathProperty.getString()).thenReturn(
					"en/us/article");
			PowerMockito.stub(
					PowerMockito.method(FeaturedAction.class, "getPage"))
					.toReturn(page);
			PowerMockito.stub(
					PowerMockito.method(FeaturedAction.class,
							"getFeaturedPages"))
					.toReturn(mostviewedArticleList);
			Mockito.when(currentNode.hasProperty("header")).thenReturn(true);
			Mockito.when(currentNode.getProperty("header")).thenReturn(
					headerProperty);
			Mockito.when(headerProperty.getString()).thenReturn("headerText");
			Mockito.when(currentNode.hasProperty("seeAllText"))
					.thenReturn(true);
			Mockito.when(currentNode.getProperty("seeAllText")).thenReturn(
					footerProperty);
			Mockito.when(footerProperty.getString()).thenReturn("footerText");
			FeaturedBean featuredBean = featuredAction.getFeatures();
			assertEquals("headerText", featuredBean.getHeader());
			assertEquals("footerText", featuredBean.getSeeAllText());
			assertEquals("en/us/article.html", featuredBean.getParentPath());
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Test tag contains.
	 */
	@Test
	public void testTagContains() {
		Tag[] Tags = new Tag[2];
		Tag tag = Mockito.mock(Tag.class);
		Tags[0] = tag;
		try {
			Mockito.when(page.getTags()).thenReturn(Tags);
			Mockito.when(Tags[0].getName()).thenReturn("tagName");

			boolean flag =featuredAction.tagContains(page, "tagName");
			assertEquals(true, flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test get featured pages.
	 */
	@Test
	public void testGetFeaturedPages() {

		try {
			Mockito.when(page.listChildren()).thenReturn(children);
			Mockito.when(children.hasNext()).thenReturn(true, false);
			Mockito.when(children.next()).thenReturn(childPage);
			Mockito.when(childPage.getTitle()).thenReturn("title");
			Mockito.when(childPage.getPath()).thenReturn("en/us/article");
			PowerMockito.stub(
					PowerMockito.method(FeaturedAction.class, "tagContains"))
					.toReturn(true);
			List<MostViewedArticleBean> mostviewedArticleList = featuredAction.getFeaturedPages(page, "tagName", 3);
			MostViewedArticleBean mostViewedArticleBean = mostviewedArticleList
					.get(0);
			assertEquals("en/us/article.html",
					mostViewedArticleBean.getArticlePath());
			assertEquals("title", mostViewedArticleBean.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
