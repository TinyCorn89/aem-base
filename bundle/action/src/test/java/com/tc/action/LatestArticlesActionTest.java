/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

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

import com.tc.action.LatestArticlesAction;
import com.tc.model.LatestArticlesBean;
import com.tc.model.ListOfArticlesBean;

/**
 * The Class LatestArticlesActionTest.
 *
 * @author gdinakar
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ LatestArticlesAction.class })
public class LatestArticlesActionTest extends BaseTest {
	
	/** The latest articles action. */
	private LatestArticlesAction latestArticlesAction = null;
	
	/** The list of articles bean. */
	private ListOfArticlesBean listOfArticlesBean = null;
	
	/** The latest articles bean. */
	private LatestArticlesBean latestArticlesBean = null;
	
	/** The sling http servlet request. */
	private SlingHttpServletRequest slingHttpServletRequest = null;
	
	/** The resource resolver. */
	private ResourceResolver resourceResolver = null;
	
	/** The resource. */
	private Resource resource = null;
	
	/** The current node. */
	private Node currentNode = null;
	
	/** The parent node. */
	private Node parentNode = null;
	
	/** The child node. */
	private Node childNode = null;
	
	/** The jcr content node. */
	private Node jcrContentNode = null;
	
	/** The node iterator. */
	private NodeIterator nodeIterator = null;
	
	/** The parent page property. */
	private Property parentPageProperty = null;
	
	/** The number of items property. */
	private Property numberOfItemsProperty = null;
	
	/** The see all link text property. */
	private Property seeAllLinkTextProperty =null;
	
	/** The article title. */
	private Property articleTitleProperty = null;
	
	/** The date of creation property. */
	private Property dateOfCreationProperty = null;
	
	/** The list of latest articles. */
	private List<LatestArticlesBean> listOfLatestArticles = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		latestArticlesAction = new LatestArticlesAction();
		listOfArticlesBean = new ListOfArticlesBean();
		latestArticlesBean =  new LatestArticlesBean();
		currentNode = Mockito.mock(Node.class);
		PowerMockito.stub(PowerMockito.method(LatestArticlesAction.class, "getCurrentNode")).toReturn(currentNode);
		parentPageProperty = Mockito.mock(Property.class);
		numberOfItemsProperty = Mockito.mock(Property.class);
		seeAllLinkTextProperty = Mockito.mock(Property.class);
		slingHttpServletRequest = Mockito.mock(SlingHttpServletRequest.class);
		PowerMockito.stub(PowerMockito.method(LatestArticlesAction.class, "getSlingRequest")).toReturn(slingHttpServletRequest);
		resourceResolver = Mockito.mock(ResourceResolver.class);
		resource = Mockito.mock(Resource.class);
		//PowerMockito.stub(PowerMockito.method(LatestArticlesAction.class, "getResource")).toReturn(resource);
		parentNode = Mockito.mock(Node.class);
		nodeIterator = Mockito.mock(NodeIterator.class);
		childNode = Mockito.mock(Node.class);
		jcrContentNode = Mockito.mock(Node.class); 
		dateOfCreationProperty = Mockito.mock(Property.class);
		articleTitleProperty = Mockito.mock(Property.class);
	}

	/**
	 * Test method for {@link com.tc.action.LatestArticlesAction#getLatestArticles()}.
	 */
	@Test
	public void testGetLatestArticles() {
		try {
			Mockito.when(currentNode.hasProperty("parentPage")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./parentPage")).thenReturn(parentPageProperty);
			Mockito.when(parentPageProperty.getString()).thenReturn("/content/launchpad/us/en/articles");
			
			Mockito.when(currentNode.hasProperty("numberOfItems")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./numberOfItems")).thenReturn(numberOfItemsProperty);
			Mockito.when(numberOfItemsProperty.getString()).thenReturn("3");
			
			Mockito.when(currentNode.hasProperty("seeAllLinkText")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./seeAllLinkText")).thenReturn(seeAllLinkTextProperty);
			Mockito.when(seeAllLinkTextProperty.getString()).thenReturn("See All Articles");
			
			Mockito.when(slingHttpServletRequest.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.getResource("/content/launchpad/us/en/articles")).thenReturn(resource);
			Mockito.when(resource.adaptTo(Node.class)).thenReturn(parentNode);
			Mockito.when(parentNode.hasNodes()).thenReturn(true);
			Mockito.when(parentNode.getNodes()).thenReturn(nodeIterator);
			Mockito.when(nodeIterator.hasNext()).thenReturn(true,false);
			Mockito.when((Node)nodeIterator.next()).thenReturn(childNode);
			Mockito.when(childNode.getName()).thenReturn("article-1");
			Mockito.when(childNode.hasNode("jcr:content")).thenReturn(true);
			Mockito.when(childNode.getNode("jcr:content")).thenReturn(jcrContentNode);
			
			Mockito.when(jcrContentNode.hasProperty("jcr:title")).thenReturn(true);
			Mockito.when(jcrContentNode.getProperty("jcr:title")).thenReturn(articleTitleProperty);
			
			Mockito.when(articleTitleProperty.getString()).thenReturn("Article1");
			Mockito.when(childNode.getPath()).thenReturn("/content/launchpad/us/en/articles/article-1");
			
			Mockito.when(childNode.hasProperty("jcr:created")).thenReturn(true);
			Mockito.when(childNode.getProperty("jcr:created")).thenReturn(dateOfCreationProperty);
			Mockito.when(dateOfCreationProperty.getString()).thenReturn("2014-05-26T14:17:12.406+05:30");
			
			listOfArticlesBean = latestArticlesAction.getLatestArticles();
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		assertEquals("Object Type:", ListOfArticlesBean.class, listOfArticlesBean.getClass());
		assertEquals("Parent Page Path:","/content/launchpad/us/en/articles.html", listOfArticlesBean.getParentPagePath());
		assertEquals("See All Link Text:", "See All Articles", listOfArticlesBean.getSeeAllLinkText());
		listOfLatestArticles = new ArrayList<LatestArticlesBean>();
		listOfLatestArticles = listOfArticlesBean.getListOfLatestArticles();
		for (Object temp : listOfLatestArticles) {
				latestArticlesBean = (LatestArticlesBean) temp;
				break;
		}
		assertEquals("Object Type:", LatestArticlesBean.class, latestArticlesBean.getClass());
		assertEquals("Article Title:", "Article1", latestArticlesBean.getArticleTitle());
		assertEquals("Article Path:", "/content/launchpad/us/en/articles/article-1.html", latestArticlesBean.getArticlePath());
	}

}
