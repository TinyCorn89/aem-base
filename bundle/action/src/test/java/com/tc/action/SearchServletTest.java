/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Value;

import junit.framework.Assert;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
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

import com.day.cq.search.Trends;
import com.day.cq.search.result.SearchResult;
import com.day.cq.tagging.Tag;
import com.tc.framework.api.ManagerProvider;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.managers.SearchService;
import com.tc.servlet.SearchServlet;
import com.tc.util.CommonUtils;



@RunWith(PowerMockRunner.class)
@PrepareForTest({SearchServlet.class,FrameworkLogger.class,ManagerProvider.class})
public class SearchServletTest {

	private SearchServlet searchServlet;
	private SlingHttpServletRequest request;
	private SlingHttpServletResponse response;
	private Logger logger;
	private SearchService searchService;
	private Map<String ,Object> resultMap;
	private SearchResult searchResult;
	private List<Object> list;
	private Set<Tag> set;
	private Trends trends;

	@Before
	public void setup() {
		searchServlet = Whitebox.newInstance(SearchServlet.class);
		request = Mockito.mock(SlingHttpServletRequest.class);
		searchService = Mockito.mock(SearchService.class);
		resultMap = Mockito.mock(Map.class);
		list = Mockito.mock(List.class);
		set = Mockito.mock(Set.class);
		trends = Mockito.mock(Trends.class);
		response = Mockito.mock(SlingHttpServletResponse.class);
		logger = Mockito.mock(Logger.class);
		searchResult = Mockito.mock(SearchResult.class);
		Whitebox.setInternalState(searchServlet, logger);
	}

	@After
	public void tearDown() {
		searchServlet = null;
	}

	@Test
	public void testDoPostwithAdvancedSearch() throws Exception{
		PowerMockito.mockStatic(ManagerProvider.class);
		PowerMockito.mockStatic(CommonUtils.class);
		Mockito.when(ManagerProvider.getManager(SearchService.class)).thenReturn(searchService);
		Mockito.when(request.getParameter("searchKeyword")).thenReturn("searchKeyword");
		Mockito.when(request.getParameter("sectionTab")).thenReturn("sectionTab");
		Mockito.when(request.getParameter("keyword")).thenReturn("keyword");
		Mockito.when(request.getParameter("author")).thenReturn("author");
		Mockito.when(request.getParameter("contentType")).thenReturn("contentType");
		Mockito.when(request.getParameter("timeRange")).thenReturn("timeRange");
		Mockito.when(request.getParameter("searchIn")).thenReturn("searchIn");
		Mockito.when(request.getParameter("dateFromMM")).thenReturn("dateFromMM");
		Mockito.when(request.getParameter("dateFromDD")).thenReturn("dateFromDD");
		Mockito.when(request.getParameter("dateFromYYYY")).thenReturn("dateFromYYYY");
		Mockito.when(request.getParameter("dateToMM")).thenReturn("dateToMM");
		Mockito.when(request.getParameter("dateToDD")).thenReturn("dateToDD");
		Mockito.when(request.getParameter("dateToYYYY")).thenReturn("dateToYYYY");
		Mockito.when(request.getParameter("contentPath")).thenReturn("contentPath");
		Mockito.when(request.getParameter("articlePath")).thenReturn("articlePath");
		Mockito.when(request.getParameter("damPath")).thenReturn("damPath");
		Mockito.when(request.getParameter("currentIndex")).thenReturn("currentIndex");
		Mockito.when(request.getParameter("tagKeyword")).thenReturn("tagKeyword");
		Mockito.when(request.getParameter("mimeType")).thenReturn("mimeType");
		Mockito.when(request.getParameter("resultPath")).thenReturn("resultPath");
		Mockito.when(request.getParameter("q")).thenReturn("content");
		Mockito.when(request.getParameter("fromPage")).thenReturn("advanceSearch");
		Mockito.when(request.getParameter("simpleContentPath")).thenReturn("abc.html");
		Mockito.when(searchService.advancedsearch(request)).thenReturn(resultMap);
		Mockito.when(resultMap.containsKey("SearchResult")).thenReturn(true);
		Mockito.when(resultMap.get("SearchResult")).thenReturn(searchResult);
		PowerMockito.stub(PowerMockito.method(SearchServlet.class,"getSearchResults", SearchResult.class)).toReturn(list);
		Mockito.when(resultMap.get("numberOfPages")).thenReturn(2L);
		Mockito.when(resultMap.get("tags")).thenReturn(set);
		Mockito.when(resultMap.get("trends")).thenReturn(trends);
		PowerMockito.stub(PowerMockito.method(CommonUtils.class,"getRequestDispatcher", SlingHttpServletRequest.class,SlingHttpServletResponse.class,String.class)).toThrow(new Exception());
		Whitebox.invokeMethod(searchServlet, "doPost",request,response);
	}

	@Test
	public void testDoPostwithSimpleSearch() throws Exception{
		PowerMockito.mockStatic(ManagerProvider.class);
		PowerMockito.mockStatic(CommonUtils.class);
		Mockito.when(ManagerProvider.getManager(SearchService.class)).thenReturn(searchService);
		Mockito.when(request.getParameter("searchKeyword")).thenReturn("searchKeyword");
		Mockito.when(request.getParameter("sectionTab")).thenReturn("sectionTab");
		Mockito.when(request.getParameter("keyword")).thenReturn("keyword");
		Mockito.when(request.getParameter("author")).thenReturn("author");
		Mockito.when(request.getParameter("contentType")).thenReturn("contentType");
		Mockito.when(request.getParameter("timeRange")).thenReturn("timeRange");
		Mockito.when(request.getParameter("searchIn")).thenReturn("searchIn");
		Mockito.when(request.getParameter("dateFromMM")).thenReturn("dateFromMM");
		Mockito.when(request.getParameter("dateFromDD")).thenReturn("dateFromDD");
		Mockito.when(request.getParameter("dateFromYYYY")).thenReturn("dateFromYYYY");
		Mockito.when(request.getParameter("dateToMM")).thenReturn("dateToMM");
		Mockito.when(request.getParameter("dateToDD")).thenReturn("dateToDD");
		Mockito.when(request.getParameter("dateToYYYY")).thenReturn("dateToYYYY");
		Mockito.when(request.getParameter("contentPath")).thenReturn("contentPath");
		Mockito.when(request.getParameter("articlePath")).thenReturn("articlePath");
		Mockito.when(request.getParameter("damPath")).thenReturn("damPath");
		Mockito.when(request.getParameter("currentIndex")).thenReturn("currentIndex");
		Mockito.when(request.getParameter("tagKeyword")).thenReturn("tagKeyword");
		Mockito.when(request.getParameter("mimeType")).thenReturn("mimeType");
		Mockito.when(request.getParameter("resultPath")).thenReturn("resultPath");
		Mockito.when(request.getParameter("q")).thenReturn("content");
		Mockito.when(request.getParameter("fromPage")).thenReturn("simplesearch");
		Mockito.when(request.getParameter("simpleContentPath")).thenReturn("abc.html");
		Mockito.when(searchService.simpleSearch(request)).thenReturn(resultMap);
		Mockito.when(resultMap.get("SearchResult")).thenReturn(searchResult);
		PowerMockito.stub(PowerMockito.method(SearchServlet.class,"getSearchResults", SearchResult.class)).toReturn(list);
		Mockito.when(resultMap.get("numberOfPages")).thenReturn(2L);
		Mockito.when(resultMap.get("tags")).thenReturn(set);
		Mockito.when(resultMap.get("trends")).thenReturn(trends);
		PowerMockito.stub(PowerMockito.method(CommonUtils.class,"getRequestDispatcher", SlingHttpServletRequest.class,SlingHttpServletResponse.class,String.class)).toThrow(new Exception());
		Whitebox.invokeMethod(searchServlet, "doPost",request,response);
	}

	@Test
	public void testGetSearchResults()
	{
		try
		{
			Map<String,Object> map = Mockito.mock(Map.class);
			List<Object> list = Mockito.mock(List.class);
			Iterator<Node> iterator = Mockito.mock(Iterator.class);
			Node node = Mockito.mock(Node.class);
			Node jcrNode=Mockito.mock(Node.class);
			Property property=Mockito.mock(Property.class);
			Value value=Mockito.mock(Value.class);
			Mockito.when(searchResult.getNodes()).thenReturn(iterator);
			Mockito.when(iterator.hasNext()).thenReturn(true,false);
			Mockito.when(iterator.next()).thenReturn(node);
			Mockito.when(node.getName()).thenReturn("abc");
			Mockito.when(node.getPath()).thenReturn("en/abc.html");
			Mockito.when(node.hasNode(Mockito.anyString())).thenReturn(true);
			Mockito.when(node.getNode("jcr:content")).thenReturn(jcrNode);
			Mockito.when(jcrNode.hasProperty("jcr:description")).thenReturn(true);
			Mockito.when(jcrNode.getProperty("jcr:description")).thenReturn(property);
			Mockito.when(property.getValue()).thenReturn(value);
			Mockito.when(value.getString()).thenReturn("abc");
			Mockito.when(map.get("description")).thenReturn("abc");
			Mockito.when(list.get(0)).thenReturn(map);
			List<Object> expectedList = Whitebox.invokeMethod(searchServlet, "getSearchResults",searchResult);
			Assert.assertEquals("Both are not equal ", ((Map)expectedList.get(0)).get("description"),((Map)list.get(0)).get("description"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
