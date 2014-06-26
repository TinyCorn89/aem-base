/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.tc.action.NewsAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.NewsBean;
@RunWith(PowerMockRunner.class)
@PrepareForTest({NewsAction.class, FrameworkLogger.class })
public class NewsActionTest  extends BaseTest{

	
 private Node currentNode;
 private NewsAction newsAction;
 private Node feedsNode;
 private NodeIterator nodeIterator;
 private Node feedItemNode;
 private String startTime = "11:39 AM";
 private String endTime = "04:39 PM";
 private Property startTimePro;
 private Property endTimePro;
 private Property rssFeedURLPro;
 private Property headerProp;
 private Property noOfItemsProp;
 private Property showDateProp;
 private Property showDescriptionProp;
 private Property seeAllProp;
 private Property countProp;
 Date date;
 Date currentDate ;
 Date startTimeParse;
 Date endTimeParse ;
 SimpleDateFormat formatter;
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		newsAction = new NewsAction();
		feedsNode = Mockito.mock(Node.class);
		nodeIterator = Mockito.mock(NodeIterator.class);
		currentNode= Mockito.mock(Node.class);
		feedItemNode = Mockito.mock(Node.class);
		startTimePro = Mockito.mock(Property.class);
		endTimePro = Mockito.mock(Property.class);
		rssFeedURLPro = Mockito.mock(Property.class);
		headerProp = Mockito.mock(Property.class);
		noOfItemsProp = Mockito.mock(Property.class);
		showDateProp = Mockito.mock(Property.class);
		showDescriptionProp = Mockito.mock(Property.class);
		seeAllProp = Mockito.mock(Property.class);
		countProp=Mockito.mock(Property.class);
		PowerMockito.stub(PowerMockito.method(NewsAction.class, "getCurrentNode")).toReturn(currentNode);
		
		
		
		
	}

	@Test
	public final void testGetNews() {
		 NewsBean newsBean = new NewsBean();
		 Map<Date, List<String>> comparison = new HashMap<Date, List<String>>();
		  List<String> headingAndUrl =  new ArrayList<String>();
		 try{
			Mockito.when(currentNode.hasNode("feeds")).thenReturn(true);
			Mockito.when(currentNode.getNode("feeds")).thenReturn(feedsNode);
			Mockito.when(feedsNode.hasNodes()).thenReturn(true);
			Mockito.when(feedsNode.getNodes()).thenReturn(nodeIterator);
			Mockito.when(currentNode.hasProperty("count")).thenReturn(false);
		    Mockito.when(nodeIterator.hasNext()).thenReturn(true,false);
		    Mockito.when(nodeIterator.nextNode()).thenReturn(feedItemNode);
		    Mockito.when(feedItemNode.hasProperty("startTime")).thenReturn(true);
		   Mockito.when(feedItemNode.getProperty("startTime")).thenReturn(startTimePro);
		   Mockito.when(startTimePro.getString()).thenReturn(startTime);
		   Mockito.when(feedItemNode.hasProperty("endTime")).thenReturn(true);
		   Mockito.when(feedItemNode.getProperty("endTime")).thenReturn(endTimePro);
		   Mockito.when(endTimePro.getString()).thenReturn(endTime);
   	     Mockito.when(feedItemNode.hasProperty("rssFeedURL")).thenReturn(true);
		  Mockito.when(feedItemNode.getProperty("rssFeedURL")).thenReturn(rssFeedURLPro);
		  Mockito.when(rssFeedURLPro.getString()).thenReturn("rssFeedURL");
		  Mockito.when(feedItemNode.hasProperty("header")).thenReturn(true);
		  Mockito.when(feedItemNode.getProperty("header")).thenReturn(headerProp);
		  Mockito.when(headerProp.getString()).thenReturn("header");
		  
		  Mockito.when(currentNode.hasProperty("noOfItems")).thenReturn(true);
		  Mockito.when(currentNode.getProperty("noOfItems")).thenReturn(noOfItemsProp);
		  Mockito.when(noOfItemsProp.getString()).thenReturn("noOfItems");
		  
		  Mockito.when(currentNode.hasProperty("showDate")).thenReturn(true);
		  Mockito.when(currentNode.getProperty("showDate")).thenReturn(showDateProp);
		  Mockito.when(showDateProp.getString()).thenReturn("showDate");
		   
		  Mockito.when(currentNode.hasProperty("showDescription")).thenReturn(true);
		  Mockito.when(currentNode.getProperty("showDescription")).thenReturn(showDescriptionProp);
		  Mockito.when(showDescriptionProp.getString()).thenReturn("showDescription");
		  
		  Mockito.when(currentNode.hasProperty("seeAll")).thenReturn(true);
		  Mockito.when(currentNode.getProperty("seeAll")).thenReturn(seeAllProp);
		  Mockito.when(seeAllProp.getBoolean()).thenReturn(true);
		    
			NewsBean newsBean1 = newsAction.getNews();
			
			assertEquals("rssFeedURL", newsBean1.getUrl());
			assertEquals("noOfItems", newsBean1.getNoOfItems());
			assertEquals("showDate", newsBean1.getShowDate());
			assertEquals("showDescription", newsBean1.getShowDescription());
			assertEquals("header", newsBean1.getHeader());
			assertEquals(true, newsBean1.getSeeAll());

		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
	}

	@Test
	public final void testGetNewsif() {
		 NewsBean newsBean = new NewsBean();
		 Map<Date, List<String>> comparison = new HashMap<Date, List<String>>();
		  List<String> headingAndUrl =  new ArrayList<String>();
		 try{
			Mockito.when(currentNode.hasNode("feeds")).thenReturn(true);
			Mockito.when(currentNode.getNode("feeds")).thenReturn(feedsNode);
			Mockito.when(feedsNode.hasNodes()).thenReturn(true);
			Mockito.when(feedsNode.getNodes()).thenReturn(nodeIterator);
			Mockito.when(currentNode.hasProperty("count")).thenReturn(false);
		    Mockito.when(nodeIterator.hasNext()).thenReturn(true,false);
		    Mockito.when(nodeIterator.nextNode()).thenReturn(feedItemNode);
		    Mockito.when(feedItemNode.hasProperty("startTime")).thenReturn(true);
		   Mockito.when(feedItemNode.getProperty("startTime")).thenReturn(startTimePro);
		   Mockito.when(startTimePro.getString()).thenReturn(startTime);
		   Mockito.when(feedItemNode.hasProperty("endTime")).thenReturn(true);
		   Mockito.when(feedItemNode.getProperty("endTime")).thenReturn(endTimePro);
		   Mockito.when(endTimePro.getString()).thenReturn(endTime);
   	     Mockito.when(feedItemNode.hasProperty("rssFeedURL")).thenReturn(false);
		 
		  Mockito.when(feedItemNode.hasProperty("header")).thenReturn(true);
		  Mockito.when(feedItemNode.getProperty("header")).thenReturn(headerProp);
		  Mockito.when(headerProp.getString()).thenReturn("header");
		  Mockito.when(currentNode.hasProperty("count")).thenReturn(true);
		  Mockito.when(currentNode.getProperty("count")).thenReturn(countProp);
		  Mockito.when(countProp.getString()).thenReturn("3");
		  
		  
			NewsBean newsBean1 = newsAction.getNews();
			
			

		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
	}
}
