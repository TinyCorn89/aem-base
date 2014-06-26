/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.Property;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.designer.Style;
import com.tc.action.TopNavigationAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.LongArticleDetailsBean;
import com.tc.model.TopNavigationBean;
import com.tc.util.Constants;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TopNavigationAction.class)
public class TopNavigationActionTest extends BaseTest {
	private TopNavigationAction topNavigationAction = null;
	private Style topNavigationNode=null;
	private Property rootPathProperty;
	private String rootPath = "/content/launchpad/us/en";
	private ResourceResolver resourceResolver = null;
	private SlingHttpServletRequest slingHttpServletRequest = null;
	private Resource resource;
	private Page rootPage;
	private Iterator<Page> mainPageChildrens = null;
	private Page mainPage;
	private String mainPageTitle="articles";
	Iterator<Page> secondLevelChildren = null;
	private Page secondPage;
	private String secondPageTitle="Video Gallery";
	Iterator<Page> thirdLevelChildren= null;
	private Page thirdPage;
	private String thirdPageTitle="Image Gallery";
	Logger logger;
	Object obj =null;
	Node pageNode = null;
	Node jcrNode = null;
	
	@Before
	public void setUp() throws Exception {
		topNavigationNode = Mockito.mock(Style.class);
		rootPathProperty = Mockito.mock(Property.class);
		resourceResolver = Mockito.mock(ResourceResolver.class);
		slingHttpServletRequest = Mockito.mock(SlingHttpServletRequest.class);
		resource = Mockito.mock(Resource.class);
		rootPage = Mockito.mock(Page.class);
		mainPageChildrens = Mockito.mock(Iterator.class);
		mainPage = Mockito.mock(Page.class);
		secondLevelChildren = Mockito.mock(Iterator.class);
		secondPage = Mockito.mock(Page.class);
		thirdLevelChildren = Mockito.mock(Iterator.class);
		thirdPage = Mockito.mock(Page.class);
		logger = Mockito.mock(Logger.class);
		obj = Mockito.mock(Object.class);
		pageNode = Mockito.mock(Node.class);
		jcrNode = Mockito.mock(Node.class);
		
		topNavigationAction = Whitebox.newInstance(TopNavigationAction.class);
		PowerMockito.stub(
				PowerMockito.method(TopNavigationAction.class, "getCurrentStyle")).toReturn(topNavigationNode);
		PowerMockito.stub(
				PowerMockito.method(TopNavigationAction.class,
						"getSlingRequest")).toReturn(slingHttpServletRequest);
		PowerMockito.stub(
				PowerMockito.method(FrameworkLogger.class, "getLogger"))
				.toReturn(logger);
		Whitebox.setInternalState(topNavigationAction, logger);
		
	}

	
	@After
	public void tearDown() throws Exception {
		topNavigationAction = null;
	}

	@Test
	public void testTopNavigationAction() {
		try {
			Mockito.when(topNavigationNode.get(Constants.TOPNAVIGATION.PARENTPAGE)).thenReturn(obj);
			Mockito.when(topNavigationNode.get(Constants.TOPNAVIGATION.PARENTPAGE,String.class)).thenReturn(rootPath);
			Mockito.when(slingHttpServletRequest.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.getResource(rootPath)).thenReturn(resource);
			Mockito.when(resource.adaptTo(Page.class)).thenReturn(rootPage);
			Mockito.when(rootPage.listChildren()).thenReturn(mainPageChildrens);
			Mockito.when(mainPageChildrens.hasNext()).thenReturn(true, false);
			Mockito.when(mainPageChildrens.next()).thenReturn(mainPage);
			Mockito.when(mainPage.adaptTo(Node.class)).thenReturn(pageNode);
			Mockito.when(pageNode.hasNode(Constants.TOPNAVIGATION.JCRCONTENT)).thenReturn(true);
			Mockito.when(pageNode.getNode(Constants.TOPNAVIGATION.JCRCONTENT)).thenReturn(jcrNode);
			Mockito.when(jcrNode.hasProperty(Constants.TOPNAVIGATION.HIDEINNAV)).thenReturn(true);
			Mockito.when(mainPage.getTitle()).thenReturn(mainPageTitle);
			Mockito.when(mainPage.listChildren()).thenReturn(secondLevelChildren);
			Mockito.when(secondLevelChildren.hasNext()).thenReturn(true, false);
			Mockito.when(secondLevelChildren.next()).thenReturn(secondPage);
			Mockito.when(secondPage.adaptTo(Node.class)).thenReturn(pageNode);
			Mockito.when(pageNode.hasNode(Constants.TOPNAVIGATION.JCRCONTENT)).thenReturn(true);
			Mockito.when(pageNode.getNode(Constants.TOPNAVIGATION.JCRCONTENT)).thenReturn(jcrNode);
			Mockito.when(jcrNode.hasProperty(Constants.TOPNAVIGATION.HIDEINNAV)).thenReturn(true);
			Mockito.when(secondPage.getTitle()).thenReturn(secondPageTitle);
			Mockito.when(secondPage.listChildren()).thenReturn(thirdLevelChildren);
			Mockito.when(thirdLevelChildren.hasNext()).thenReturn(true, false);
			Mockito.when(thirdLevelChildren.next()).thenReturn(thirdPage);
			Mockito.when(thirdPage.adaptTo(Node.class)).thenReturn(pageNode);
			Mockito.when(pageNode.hasNode(Constants.TOPNAVIGATION.JCRCONTENT)).thenReturn(true);
			Mockito.when(pageNode.getNode(Constants.TOPNAVIGATION.JCRCONTENT)).thenReturn(jcrNode);
			Mockito.when(jcrNode.hasProperty(Constants.TOPNAVIGATION.HIDEINNAV)).thenReturn(true);
			Mockito.when(thirdPage.getTitle()).thenReturn(thirdPageTitle);
			
			TopNavigationBean topNavigationBean = topNavigationAction
					.getListOfLevels();
			
			
			
		}

		 catch (Exception e) {
			e.printStackTrace();
		}
	}
}
