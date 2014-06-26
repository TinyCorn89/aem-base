/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;



import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;

import junit.framework.Assert;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
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

import com.day.cq.wcm.api.designer.Style;
import com.tc.action.SearchAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.AdvancedSearchBean;
import com.tc.model.SearchBean;
import com.tc.model.SimpleSearchBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SearchAction.class,FrameworkLogger.class})
public class SearchActionTest {

	private SearchAction searchAction;
	private SimpleSearchBean simpleSearchBean;
	private AdvancedSearchBean advanceSearchBean;
	private Logger logger;
	private Node node;
	private Style style;
	private Property property;
	private String contentPath ="content/launchpad11234/abc.html";
	private String damPath ="content/dam/launchpad1232/abc";
	private String articlePath ="content/launchpad23423/articles.html";
	private String resultPath ="content/launchpad23423/result.html";
	private String advancePage ="content/launchpad23423/advancePage.html";
	SearchBean actualSearchBean;
	private List<Object> list;
	private SlingHttpServletRequest request;

	@Before
	public void setUp() throws Exception {
		logger = Mockito.mock(Logger.class);
		simpleSearchBean = Mockito.mock(SimpleSearchBean.class);
		advanceSearchBean = Mockito.mock(AdvancedSearchBean.class);
		node =  Mockito.mock(Node.class);
		property = Mockito.mock(Property.class);
		style =  Mockito.mock(Style.class);
		actualSearchBean = Mockito.mock(SearchBean.class);
		searchAction = Mockito.mock(SearchAction.class);
		Whitebox.setInternalState(searchAction, logger);
		list = Mockito.mock(List.class);
		request = Mockito.mock(SlingHttpServletRequest.class);	
	}

	@After
	public void tearDown() throws Exception {
		searchAction = null;
	}

	@Test
	public void testGetSearchDialogInfo() {
		try
		{
			PowerMockito.stub(PowerMockito.method(SearchAction.class,"getSimpleSearchBeanDetails")).toReturn(simpleSearchBean);
			PowerMockito.stub(PowerMockito.method(SearchAction.class,"getAdvanceSearchBeanDetails")).toReturn(advanceSearchBean);
			Mockito.when(searchAction.getCurrentNode()).thenReturn(node);
			Mockito.when(searchAction.getCurrentStyle()).thenReturn(style);
			/*Mockito.when(node.hasProperty("section")).thenReturn(true);
			Mockito.when(node.getProperty("section")).thenReturn(property);
			Mockito.when(property.getString()).thenReturn("true");
			PowerMockito.stub(PowerMockito.method(SearchAction.class,"getAllNodes",String.class)).toReturn(list);
			Mockito.when(actualSearchBean.getSectionMenus()).thenReturn(list);*/
			Mockito.when(searchAction.getStylePropertyValue(style, "contentPage")).thenReturn(contentPath);
			Mockito.when(searchAction.getStylePropertyValue(style, "damPage")).thenReturn(damPath);
			Mockito.when(searchAction.getStylePropertyValue(style, "articlePage")).thenReturn(articlePath);
			Mockito.when(searchAction.getStylePropertyValue(style, "resultPath")).thenReturn(resultPath);
			Mockito.when(actualSearchBean.getArticlePath()).thenReturn(articlePath);
			Mockito.when(actualSearchBean.getContentPath()).thenReturn(contentPath);
			Mockito.when(actualSearchBean.getDamPath()).thenReturn(damPath);
			Mockito.when(searchAction.getSearchDialogInfo()).thenCallRealMethod();
			SearchBean expectedSearchBean = searchAction.getSearchDialogInfo();
			Assert.assertEquals("Both are not equal  ", expectedSearchBean.getArticlePath(),actualSearchBean.getArticlePath());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testGetSimpleSearchDialogInfo()
	{
		try
		{
			Mockito.when(searchAction.getCurrentStyle()).thenReturn(style);
			Mockito.when(searchAction.getStylePropertyValue(style, "searchIn")).thenReturn(contentPath);
			Mockito.when(searchAction.getStylePropertyValue(style, "advancePage")).thenReturn(advancePage);
			Mockito.when(searchAction.getStylePropertyValue(style, "resultPath")).thenReturn(resultPath);
			Mockito.when(simpleSearchBean.getSearchIn()).thenReturn(contentPath);
			Mockito.when(simpleSearchBean.getResultPath()).thenReturn(resultPath);
			Mockito.when(simpleSearchBean.getAdvancePage()).thenReturn(advancePage);
			Mockito.when(searchAction.getSimpleSearchDialogInfo()).thenCallRealMethod();
			SimpleSearchBean expectedSimpleSearchBean = searchAction.getSimpleSearchDialogInfo();
			Assert.assertEquals("Both are not equal ", expectedSimpleSearchBean.getSearchIn(),simpleSearchBean.getSearchIn());
		}
		catch(Exception e)
		{

		}

	}

	
	@Test
	public void testGetSimpleSearchBeanDetails()
	{
		try
		{
			Mockito.when(searchAction.getCurrentNode()).thenReturn(node);
			Mockito.when(searchAction.getNodePropertyValue(node,"nextText")).thenReturn("next");
			Mockito.when(searchAction.getNodePropertyValue(node,"searchIn")).thenReturn(contentPath);
			Mockito.when(searchAction.getNodePropertyValue(node,"searchButtonText")).thenReturn("search");
			Mockito.when(searchAction.getNodePropertyValue(node,"statisticsText")).thenReturn("result");
			Mockito.when(searchAction.getNodePropertyValue(node,"noResultsText")).thenReturn("no data");
			Mockito.when(searchAction.getNodePropertyValue(node,"spellcheckText")).thenReturn("spellcheck");
			Mockito.when(searchAction.getNodePropertyValue(node,"similarPagesText")).thenReturn("similar pages");
			Mockito.when(searchAction.getNodePropertyValue(node,"relatedSearchesText")).thenReturn("related");
			Mockito.when(searchAction.getNodePropertyValue(node,"searchTrendsText")).thenReturn("trends");
			Mockito.when(searchAction.getNodePropertyValue(node,"resultPagesText")).thenReturn("Results");
			Mockito.when(searchAction.getNodePropertyValue(node,"previousText")).thenReturn("previous");
			Mockito.when(simpleSearchBean.getNextText()).thenReturn("next");
			Mockito.when(simpleSearchBean.getSearchButtonText()).thenReturn("search");
			Mockito.when(simpleSearchBean.getStatisticsText()).thenReturn("result");
			Mockito.when(simpleSearchBean.getNoResultsText()).thenReturn("no data");
			Mockito.when(simpleSearchBean.getNoResultsText()).thenReturn("similar pages");
			Mockito.when(simpleSearchBean.getSpellcheckText()).thenReturn("spellcheck");
			Mockito.when(simpleSearchBean.getSimilarPagesText()).thenReturn("similar");
			Mockito.when(simpleSearchBean.getRelatedSearchesText()).thenReturn("related");
			Mockito.when(simpleSearchBean.getSearchTrendsText()).thenReturn("trends");
			Mockito.when(simpleSearchBean.getResultPagesText()).thenReturn("Results");
			Mockito.when(simpleSearchBean.getPreviousText()).thenReturn("Previous");
			SimpleSearchBean expectedSimpleSearchBean = Whitebox.invokeMethod(searchAction, "getSimpleSearchBeanDetails");
			PowerMockito.stub(PowerMockito.method(SearchAction.class, "getSimpleSearchBeanDetails"));
			Assert.assertEquals("Both are not equal ", expectedSimpleSearchBean.getNextText(),simpleSearchBean.getNextText());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAdvanceSearchBeanDetails()
	{
		try
		{
			Node childNodes = Mockito.mock(Node.class);
			List<Object> list = Mockito.mock(List.class);
			Mockito.when(searchAction.getCurrentNode()).thenReturn(node);
			Mockito.when(node.hasNode(Mockito.anyString())).thenReturn(true);
			Mockito.when(node.getNode(Mockito.anyString())).thenReturn(childNodes);
			Mockito.when(childNodes.getName()).thenReturn("section");
			PowerMockito.stub(PowerMockito.method(SearchAction.class, "getNodeProperties",Node.class)).toReturn(list);
			Mockito.when(searchAction.getNodePropertyValue(node,"timeRange")).thenReturn("DD");
			Mockito.when(searchAction.getNodePropertyValue(node,"keyword")).thenReturn("search");
			Mockito.when(searchAction.getNodePropertyValue(node,"author")).thenReturn("admin");
			Mockito.when(searchAction.getNodePropertyValue(node,"contentType")).thenReturn("images");
			Mockito.when(searchAction.getNodePropertyValue(node,"section")).thenReturn("true");
			Mockito.when(advanceSearchBean.getContentType()).thenReturn("images");
			Mockito.when(advanceSearchBean.getKeyword()).thenReturn("search");
			AdvancedSearchBean expectedadvancedSearchBean = Whitebox.invokeMethod(searchAction, "getAdvanceSearchBeanDetails");
			PowerMockito.stub(PowerMockito.method(SearchAction.class, "getAdvanceSearchBeanDetails"));
			Assert.assertEquals("Both are not equal ", expectedadvancedSearchBean.getContentType(),advanceSearchBean.getContentType());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetNodeProperties() 
	{
		List<Object> list = Mockito.mock(List.class);
		Map<String, Object> responseMap = Mockito.mock(Map.class);
		Node node = Mockito.mock(Node.class);
		NodeIterator nodeit = Mockito.mock(NodeIterator.class);
		Node innerNode = Mockito.mock(Node.class);
		ResourceResolver resourceResolver = Mockito.mock(ResourceResolver.class);
		Resource resource = Mockito.mock(Resource.class);
		ValueMap nodePropertiesMap = Mockito.mock(ValueMap.class);
		try {
			Mockito.when(node.getNodes()).thenReturn(nodeit);
			Mockito.when(nodeit.hasNext()).thenReturn(true,false);
			Mockito.when(nodeit.next()).thenReturn(innerNode);
			Mockito.when(innerNode.getPath()).thenReturn("abc.html");
			Mockito.when(searchAction.getSlingRequest()).thenReturn(request);
			Mockito.when(request.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.getResource("abc.html")).thenReturn(resource);
			Mockito.when(resource.adaptTo(ValueMap.class)).thenReturn(nodePropertiesMap);
			Mockito.when(nodePropertiesMap.get("sectionName","")).thenReturn("name");
			Mockito.when(nodePropertiesMap.get("sectionURL","")).thenReturn("url");
			Mockito.when(responseMap.get("name")).thenReturn("name");
			Mockito.when(list.get(0)).thenReturn(responseMap);
			//PowerMockito.stub(PowerMockito.method(SearchAction.class, "getNodeProperties"));
			List<Object> expectedList = Whitebox.invokeMethod(searchAction, "getNodeProperties",node);
			Assert.assertEquals("Both are not equal ", ((Map)expectedList.get(0)).get("name"),((Map)list.get(0)).get("name"));

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}

}
