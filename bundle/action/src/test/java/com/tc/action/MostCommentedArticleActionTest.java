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
import javax.jcr.NodeIterator;
import javax.jcr.Property;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.day.cq.wcm.api.Page;
import com.tc.action.MostCommentedArticleAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.MostCommentedArticleBean;
import com.tc.model.MostViewedArticleBean;

/**
 * @author KARTEEKA RAJA GUMPA
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ MostCommentedArticleAction.class, FrameworkLogger.class })
public class MostCommentedArticleActionTest {
	private MostCommentedArticleAction mostCommentedArticleAction;
	private Page page;
	private Page childPage;
	private Node currentNode;
	private Node pageNode;
	private Node jcrNode;
	private Node node;
	private Node keyNode;
	private Node counterNode;
	private NodeIterator nodeit;
	private Property noOfArticleProperty;
	private Property rootPathProperty;
	private Property headerProperty;
	private Property footerProperty;
	private Property totlaProperty;
	private Resource resource;
	private ResourceResolver rresolver;
	private SlingHttpServletRequest slingRequest;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		currentNode = Mockito.mock(Node.class);
		pageNode = Mockito.mock(Node.class);
		jcrNode = Mockito.mock(Node.class);
		node = Mockito.mock(Node.class);
		keyNode = Mockito.mock(Node.class);
		counterNode = Mockito.mock(Node.class);
		nodeit = Mockito.mock(NodeIterator.class);
		noOfArticleProperty = Mockito.mock(Property.class);
		rootPathProperty = Mockito.mock(Property.class);
		headerProperty = Mockito.mock(Property.class);
		footerProperty = Mockito.mock(Property.class);
		totlaProperty = Mockito.mock(Property.class);
		page = Mockito.mock(Page.class);
		childPage = Mockito.mock(Page.class);
		resource = Mockito.mock(Resource.class);
		rresolver = Mockito.mock(ResourceResolver.class);
		slingRequest = Mockito.mock(SlingHttpServletRequest.class);
		mostCommentedArticleAction = new MostCommentedArticleAction();
		PowerMockito.stub(
				PowerMockito.method(MostCommentedArticleAction.class,
						"getCurrentNode")).toReturn(currentNode);
		PowerMockito.stub(
				PowerMockito
						.method(MostCommentedArticleAction.class, "getPage"))
				.toReturn(page);
	}

	/**
	 * Test method for
	 * {@link com.tc.action.MostCommentedArticleAction#getMostCommentedArticle()}
	 * .
	 */
	@Test
	public void testGetMostCommentedArticle() {
		List<MostViewedArticleBean> mostviewedArticleList = Mockito
				.mock(ArrayList.class);
		try {
			Mockito.when(currentNode.hasProperty("noOfArticles")).thenReturn(
					true);
			Mockito.when(currentNode.getProperty("noOfArticles")).thenReturn(
					noOfArticleProperty);
			Mockito.when(noOfArticleProperty.getString()).thenReturn("2");
			Mockito.when(currentNode.hasProperty("rootPath")).thenReturn(true);
			Mockito.when(currentNode.getProperty("rootPath")).thenReturn(
					rootPathProperty);
			Mockito.when(rootPathProperty.getString()).thenReturn(
					"en/us/article");
			// PowerMockito.stub(PowerMockito.method(MostCommentedArticleAction.class,
			// "getPage")).toReturn(page);
			PowerMockito.stub(
					PowerMockito.method(MostCommentedArticleAction.class,
							"getArticleList")).toReturn(mostviewedArticleList);
			Mockito.when(currentNode.hasProperty("header")).thenReturn(true);
			Mockito.when(currentNode.getProperty("header")).thenReturn(
					headerProperty);
			Mockito.when(headerProperty.getString()).thenReturn("HeaderText");
			Mockito.when(currentNode.hasProperty("seeAllText"))
					.thenReturn(true);
			Mockito.when(currentNode.getProperty("seeAllText")).thenReturn(
					footerProperty);
			Mockito.when(footerProperty.getString()).thenReturn("FooterText");

			MostCommentedArticleBean mostCommentedBean = mostCommentedArticleAction
					.getMostCommentedArticle();
			assertEquals("en/us/article.html",
					mostCommentedBean.getArticleParent());
			assertEquals("HeaderText", mostCommentedBean.getHeader());
			assertEquals("FooterText", mostCommentedBean.getSeeAllText());

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Test method for
	 * {@link com.tc.action.MostCommentedArticleAction#getPage(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetPage() {
		try {
			PowerMockito.stub(
					PowerMockito.method(MostCommentedArticleAction.class,
							"getResource")).toReturn(resource);
			Mockito.when(resource.adaptTo(Page.class)).thenReturn(page);
			Page page1 = mostCommentedArticleAction.getPage("en/us/article");
			assertNotNull(page1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test method for.
	 *
	 * {@link com.tc.action.MostCommentedArticleAction#getResource(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetResource() {
		String path = "en/us/article";
		try {
			PowerMockito.stub(
					PowerMockito.method(MostCommentedArticleAction.class,
							"getSlingRequest")).toReturn(slingRequest);
			Mockito.when(slingRequest.getResourceResolver()).thenReturn(
					rresolver);
			Mockito.when(rresolver.getResource(path)).thenReturn(resource);
			Resource res = mostCommentedArticleAction.getResource(path);
			assertNotNull(res);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link com.tc.action.MostCommentedArticleAction#commentsCount(java.lang.String)}
	 * .
	 */
	@Test
	public void testCommentsCount() {
		try {
			Mockito.when(page.adaptTo(Node.class)).thenReturn(pageNode);
			Mockito.when(pageNode.hasNode("jcr:content")).thenReturn(true);
			Mockito.when(pageNode.getNode("jcr:content")).thenReturn(jcrNode);
			Mockito.when(jcrNode.getNodes()).thenReturn(nodeit);
			Mockito.when(nodeit.hasNext()).thenReturn(true, false);
			Mockito.when(nodeit.nextNode()).thenReturn(node);
			Mockito.when(node.getName()).thenReturn("comments_index");
			Mockito.when(node.hasNode("key")).thenReturn(true);
			Mockito.when(node.getNode("key")).thenReturn(keyNode);
			Mockito.when(keyNode.hasNode("counter")).thenReturn(true);
			Mockito.when(keyNode.getNode("counter")).thenReturn(counterNode);
			Mockito.when(counterNode.getProperty("totalCount")).thenReturn(
					totlaProperty);
			Mockito.when(totlaProperty.getString()).thenReturn("5");
			int count = mostCommentedArticleAction
					.commentsCount("en/us/article");
			assertEquals(5, count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for.
	 *
	 * {@link com.tc.action.MostCommentedArticleAction#getArticleList(com.day.cq.wcm.api.Page, int)}
	 * .
	 */
	@Test
	public void testGetArticleList() {
		Iterator<Page> children = Mockito.mock(Iterator.class);

		try {
			Mockito.when(page.listChildren()).thenReturn(children);
			Mockito.when(children.hasNext()).thenReturn(true, false);
			Mockito.when(children.next()).thenReturn(childPage);
			Mockito.when(childPage.getTitle()).thenReturn("title");
			Mockito.when(childPage.getPath()).thenReturn("en/us/article");
			PowerMockito.stub(
					PowerMockito.method(MostCommentedArticleAction.class,
							"commentsCount")).toReturn(2);
			List<MostViewedArticleBean> mostviewedArticleList = mostCommentedArticleAction
					.getArticleList(page, 3);
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
