/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import static org.junit.Assert.assertEquals;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;

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
import com.tc.action.CategoryAction;
import com.tc.action.NewsAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.CategoryListBean;
import com.tc.util.Constants;

/**
 * The Class CategoryActionTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ CategoryAction.class, FrameworkLogger.class })
public class CategoryActionTest extends BaseTest {

	/** The map action. */
	private CategoryAction categoryAction = null;

	private Logger logger;
	
	private Style style;
	
	private Page page;
	
	private Page page1;
	
	private NodeIterator nodeIterator;
	
	private Node node;
	
	private Node node1;
	private Node node2;
	private Node currentNode;
	private Property property;
	private Property property1;


	/**
	 * Setup method.
	 * 
	 * @throws Exception
	 *             - exception
	 */
	@Before
	public void setUp() throws Exception {
		Mockito.mock(Node.class);
		Mockito.mock(Property.class);
		Mockito.mock(Property.class);
		Mockito.mock(Property.class);
		logger = Mockito.mock(Logger.class);
		style = Mockito.mock(Style.class);
		page = Mockito.mock(Page.class);
		page1 = Mockito.mock(Page.class);
		nodeIterator = Mockito.mock(NodeIterator.class);
		node1 = Mockito.mock(Node.class);
		node = Mockito.mock(Node.class);
		node2 = Mockito.mock(Node.class);
		property = Mockito.mock(Property.class);
		property1 = Mockito.mock(Property.class);
		currentNode = Mockito.mock(Node.class);
		categoryAction = Whitebox.newInstance(CategoryAction.class);
		PowerMockito.stub(PowerMockito.method(CategoryAction.class, "getCurrentNode")).toReturn(currentNode);
		PowerMockito.stub(
				PowerMockito.method(NewsAction.class, "getCurrentStyle"))
				.toReturn(style);
		
		PowerMockito.stub(
				PowerMockito.method(CategoryAction.class, "getCurrentPage"))
				.toReturn(page);
		
		PowerMockito.stub(
				PowerMockito.method(FrameworkLogger.class, "getLogger"))
				.toReturn(logger);
		Whitebox.setInternalState(categoryAction, logger);

	}

	/**
	 * Tear down method.
	 * 
	 * @throws Exception
	 *             - exception
	 */
	@After
	public void tearDown() throws Exception {
		categoryAction = null;
	}

	/**
	 * Test map action.
	 */
	@Test
	public void testCategoryAction() {

		try {
			
			
			Mockito.when(currentNode.hasProperty(Constants.CATEGORIES.LEVEL)).thenReturn(true,false);
			Mockito.when(currentNode.getProperty(Mockito.anyString())).thenReturn(property);
			Mockito.when(property.getString()).thenReturn("2");
			
			Mockito.when(page.getParent(2)).thenReturn(page1);
			Mockito.when(page1.adaptTo(Node.class)).thenReturn(node);
			Mockito.when(node.getNodes()).thenReturn(nodeIterator);
			Mockito.when(nodeIterator.hasNext()).thenReturn(true,false);
			
			Mockito.when(nodeIterator.nextNode()).thenReturn(node1);
			
			
			
			Mockito.when(node1.getName()).thenReturn("home");
			Mockito.when(node1.getPath()).thenReturn("/content/launchpad/us/en/Home");
			Mockito.when(node1.getNode(Constants.CATEGORIES.JCR_CONTENT)).thenReturn(node2);
			Mockito.when(property1.getString()).thenReturn("home");
			Mockito.when(node2.getProperty(Constants.CATEGORIES.JCR_TITLE)).thenReturn(property1);
			
			Mockito.when(node2.hasProperty(Constants.CATEGORIES.HIDEINNAV)).thenReturn(false);
			CategoryListBean categoryListBean = categoryAction.getCategories();
			assertEquals( "home",
					categoryListBean.getListOfCategories().get(0).getCategoryName());
			assertEquals( "/content/launchpad/us/en/Home", categoryListBean.getListOfCategories().get(0).getCategoryUrl());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	

	
}
