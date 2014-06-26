/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.day.cq.wcm.api.designer.Style;
import com.tc.action.BaseAction;
import com.tc.action.SiteNavigationAction;
import com.tc.model.SiteNavigationBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SiteNavigationAction.class)
public class SiteNavigationActionTest extends BaseTest {

	private SiteNavigationAction siteNavigationAction;
	private Style style;
	private Property property;
	private Property mainJCRproperty;
	private Property childJCRproperty;
	private Value value;
	private SlingHttpServletRequest request;
	private ResourceResolver resourceResolver;
	private Session session;
	private Node configNode;
	private NodeIterator nodeResultsIt;
	private Node selectNode;
	private NodeIterator childNodeResultsIt;
	private Node childSelectNode; 
	
	@Before
	public void setUp() throws Exception {
		siteNavigationAction = new SiteNavigationAction();
		style = Mockito.mock(Style.class);
		property = Mockito.mock(Property.class);
		mainJCRproperty = Mockito.mock(Property.class);
		childJCRproperty = Mockito.mock(Property.class);
		value = Mockito.mock(Value.class);
		request = getSlingHttpServletRequest();
		resourceResolver = Mockito.mock(ResourceResolver.class);
		session = Mockito.mock(Session.class);
		configNode =  Mockito.mock(Node.class);
		nodeResultsIt =  Mockito.mock(NodeIterator.class);
		selectNode = Mockito.mock(Node.class);
		childNodeResultsIt = Mockito.mock(NodeIterator.class);
		childSelectNode =  Mockito.mock(Node.class);
	}

	@After
	public void tearDown() throws Exception {
		siteNavigationAction = null;
	}


	@Test
	public void testGetSiteNavigationInfo() {
		List<Object> list = Mockito.mock(LinkedList.class);
		Map<String,Object> childPages = Mockito.mock(LinkedHashMap.class);
		try {
			Node mainJCRNode= Mockito.mock(Node.class);
			Node childJCRNode=Mockito.mock(Node.class);
			PowerMockito.stub(PowerMockito.method(BaseAction.class,"getCurrentStyle")).toReturn(style);
			//Mockito.when(style.get(Mockito.anyString())).thenReturn(property);
			Mockito.when(style.get("pageURL",String.class)).thenReturn("/conntent/dummy");

			//Mockito.when(property.getValue()).thenReturn(value);
			//Mockito.when(value.getString()).thenReturn("/en/site");
			PowerMockito.stub(PowerMockito.method(BaseAction.class,"getSlingRequest")).toReturn(request);
			Mockito.when(request.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.adaptTo(Session.class)).thenReturn(session);
			Mockito.when(session.getNode(Mockito.anyString())).thenReturn(configNode);
			Mockito.when(configNode.getNodes()).thenReturn(nodeResultsIt);
			Mockito.when(nodeResultsIt.hasNext()).thenReturn(true,false);
			Mockito.when(nodeResultsIt.nextNode()).thenReturn(selectNode);
			Mockito.when(selectNode.getName()).thenReturn("content1");
			Mockito.when(selectNode.getNode("jcr:content")).thenReturn(mainJCRNode);
			Mockito.when(mainJCRNode.hasProperty("hideInNav")).thenReturn(false);
			Mockito.when(mainJCRNode.getProperty(Mockito.anyString())).thenReturn(mainJCRproperty);
			Mockito.when(mainJCRproperty.getString()).thenReturn("content1");
			Mockito.when(selectNode.getPath()).thenReturn("/en/site/content1.html");
			Mockito.when(selectNode.hasNodes()).thenReturn(true);
			Mockito.when(selectNode.getNodes()).thenReturn(childNodeResultsIt);
			Mockito.when(childNodeResultsIt.hasNext()).thenReturn(true,false);
			Mockito.when(childNodeResultsIt.nextNode()).thenReturn(childSelectNode);
			Mockito.when(childSelectNode.getName()).thenReturn("content2");
			Mockito.when(childSelectNode.getPath()).thenReturn("/en/site/content2.html");
			Mockito.when(childSelectNode.getNode("jcr:content")).thenReturn(childJCRNode);
			Mockito.when(childJCRNode.hasProperty("hideInNav")).thenReturn(false);
			Mockito.when(childJCRNode.getProperty(Mockito.anyString())).thenReturn(childJCRproperty);
			Mockito.when(childJCRproperty.getString()).thenReturn("content2");
					

		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
