/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import java.util.Map;
import java.util.Set;

import javax.jcr.Session;

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
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;

import com.day.cq.commons.date.DateUtil;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.Trends;
import com.day.cq.search.result.SearchResult;
import com.day.cq.tagging.Tag;
import com.tc.framework.api.ManagerProvider;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.managers.SearchService;
import com.tc.managers.impl.SearchServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SearchService.class, ManagerProvider.class,PredicateGroup.class,FrameworkLogger.class,DateUtil.class})
public class SearchServiceTest 
{
	private SearchService searchService;
	private SlingHttpServletRequest request;
	private QueryBuilder builder;
	private ResourceResolver resourceResolver;
	private Session session;
	
	@Before
	public void setup() {
		searchService =  Mockito.mock(SearchServiceImpl.class);
		Logger logger = Mockito.mock(Logger.class);
		Whitebox.setInternalState(searchService, logger);
		request = Mockito.mock(SlingHttpServletRequest.class);
		resourceResolver = Mockito.mock(ResourceResolver.class);
		session =  Mockito.mock(Session.class);
		builder = Mockito.mock(QueryBuilder.class);
		PowerMockito.mockStatic(FrameworkLogger.class);
		Mockito.when(FrameworkLogger.getLogger()).thenReturn(logger);
	}

	@After
	public void teardown() {
		searchService = null;
	}

	@Test
	public void testSimpleSearch() {
		try {
			Map<String,Object> predicateMap = Mockito.mock(Map.class);
			Map<String,Object> responseMap = Mockito.mock(Map.class);
			PredicateGroup predicateGroup = Mockito.mock(PredicateGroup.class);
			Query query  = Mockito.mock(Query.class);
			SearchResult searchResult = Mockito.mock(SearchResult.class);
			Trends trends = Mockito.mock(Trends.class);
			Set<Tag> tagList = Mockito.mock(Set.class);
			PowerMockito.mockStatic(ManagerProvider.class);
			PowerMockito.mockStatic(PredicateGroup.class);
			
			Mockito.when(ManagerProvider.getManager(QueryBuilder.class)).thenReturn(builder);
			Mockito.when(request.getParameter("simpleContentPath")).thenReturn("content");
			Mockito.when(request.getParameter("q")).thenReturn("launchpad");
			Mockito.when(request.getParameter("currentIndex")).thenReturn("1");
			Mockito.when(predicateMap.get("path")).thenReturn("/launchpad/us/en");
			Mockito.when(predicateMap.get("fulltext")).thenReturn("launchpad");
			Mockito.when(predicateMap.get("p.offset")).thenReturn("0");
			Mockito.when(predicateMap.get("p.limit")).thenReturn("10");
			Mockito.when(predicateMap.get("orderby.sort")).thenReturn("asc");
			Mockito.when(request.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.adaptTo(Session.class)).thenReturn(session);
//			Mockito.when(PredicateGroup.create(predicateMap)).thenReturn(predicateGroup);
			Mockito.when(builder.createQuery(PredicateGroup.create(predicateMap), session)).thenReturn(query);
			Mockito.when(query.getResult()).thenReturn(searchResult);
			
			/*Mockito.when(searchResult.getNodes()).thenReturn(pageNodes);
			Mockito.when(request.getResource()).thenReturn(resource);
			Mockito.when(request.adaptTo(SimpleSearch.class)).thenReturn(simpleSearch);
			Mockito.when(pageNodes.hasNext()).thenReturn(true,false);
			Mockito.when(pageNodes.next()).thenReturn(pageNode);
			Mockito.when(request.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resolver.getResource(pageNode.getPath())).thenReturn(resource);
			Mockito.when(resource.adaptTo(Page.class)).thenReturn(page);
			Mockito.when(page.getTags()).thenReturn(pagetags);*/
			Mockito.when(responseMap.get("SearchResult")).thenReturn(searchResult);
			PowerMockito.stub(PowerMockito.method(SearchServiceImpl.class,"getPageTags",SearchResult.class,SlingHttpServletRequest.class)).toReturn(tagList);
			Mockito.when(responseMap.get("tags")).thenReturn(tagList);
			PowerMockito.stub(PowerMockito.method(SearchServiceImpl.class,"getPaginationCalculation",SearchResult.class)).toReturn(10);
			Mockito.when(responseMap.get("numberOfPages")).thenReturn(10);
			PowerMockito.stub(PowerMockito.method(SearchServiceImpl.class,"getPageTrends",SlingHttpServletRequest.class)).toReturn(trends);
			Mockito.when(responseMap.get("trends")).thenReturn(trends);
			
			Map<String,Object> resultMap = searchService.simpleSearch(request);
			Assert.assertEquals("both are not equal ",resultMap.get("numberOfPages"), predicateMap.get("numberOfPages"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	@Test
	public void testAdvanceSearch() {
		try {
			Map<String,Object> predicateMap = Mockito.mock(Map.class);
			Map<String,Object> responseMap = Mockito.mock(Map.class);
			PredicateGroup predicateGroup = Mockito.mock(PredicateGroup.class);
			Query query  = Mockito.mock(Query.class);
			SearchResult searchResult = Mockito.mock(SearchResult.class);
			Trends trends = Mockito.mock(Trends.class);
			/*Iterator<Node> pageNodes =  Mockito.mock(Iterator.class);
			Resource resource = Mockito.mock(Resource.class);
			ResourceResolver resolver = Mockito.mock(ResourceResolver.class);
			Page page  = Mockito.mock(Page.class);
			SimpleSearch simpleSearch = Mockito.mock(SimpleSearch.class);
			Node pageNode = Mockito.mock(Node.class);
			Tag[] pagetags = Mockito.mock(Tag[].class);*/
			
			PowerMockito.mockStatic(ManagerProvider.class);
			PowerMockito.mockStatic(PredicateGroup.class);
			Mockito.when(ManagerProvider.getManager(QueryBuilder.class)).thenReturn(builder);
			
			Mockito.when(request.getParameter("sectionTab")).thenReturn("launchpad/sxhdv");
			Mockito.when(request.getParameter("contentPath")).thenReturn("launchpad/content/path");
			Mockito.when(request.getParameter("keyword")).thenReturn("launchpad");
			Mockito.when(request.getParameter("author")).thenReturn("admin");
			Mockito.when(request.getParameter("timeRange")).thenReturn("DD");
			Mockito.when(request.getParameter("dateFromMM")).thenReturn("10");
			Mockito.when(request.getParameter("dateFromDD")).thenReturn("30");
			Mockito.when(request.getParameter("dateFromYYYY")).thenReturn("2013");
			Mockito.when(request.getParameter("dateToMM")).thenReturn("12");
			Mockito.when(request.getParameter("dateToDD")).thenReturn("31");
			Mockito.when(request.getParameter("dateToYYYY")).thenReturn("2014");
			Mockito.when(request.getParameter("contentType")).thenReturn("dam");
			Mockito.when(request.getParameter("tagKeyword")).thenReturn("tag");
			Mockito.when(request.getParameter("currentIndex")).thenReturn("1");
			
			
			
			Mockito.when(predicateMap.get("path")).thenReturn("/launchpad/us/en");
			Mockito.when(predicateMap.get("fulltext")).thenReturn("launchpad");
			Mockito.when(predicateMap.get("p.offset")).thenReturn("0");
			Mockito.when(predicateMap.get("p.limit")).thenReturn("10");
			Mockito.when(predicateMap.get("orderby.sort")).thenReturn("asc");
			Mockito.when(request.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.adaptTo(Session.class)).thenReturn(session);
//			Mockito.when(PredicateGroup.create(predicateMap)).thenReturn(predicateGroup);
			Mockito.when(builder.createQuery(PredicateGroup.create(predicateMap), session)).thenReturn(query);
			Mockito.when(query.getResult()).thenReturn(searchResult);
			
			Set<Tag> tagList = Mockito.mock(Set.class);
			/*Mockito.when(searchResult.getNodes()).thenReturn(pageNodes);
			Mockito.when(request.getResource()).thenReturn(resource);
			Mockito.when(request.adaptTo(SimpleSearch.class)).thenReturn(simpleSearch);
			Mockito.when(pageNodes.hasNext()).thenReturn(true,false);
			Mockito.when(pageNodes.next()).thenReturn(pageNode);
			Mockito.when(request.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resolver.getResource(pageNode.getPath())).thenReturn(resource);
			Mockito.when(resource.adaptTo(Page.class)).thenReturn(page);
			Mockito.when(page.getTags()).thenReturn(pagetags);*/
			
			PowerMockito.stub(PowerMockito.method(SearchServiceImpl.class,"getPageTags",SearchResult.class,SlingHttpServletRequest.class)).toReturn(tagList);
			Mockito.when(responseMap.get("tags")).thenReturn(tagList);
			PowerMockito.stub(PowerMockito.method(SearchServiceImpl.class,"getPaginationCalculation",SearchResult.class)).toReturn(10);
			Mockito.when(responseMap.get("numberOfPages")).thenReturn(10);
			PowerMockito.stub(PowerMockito.method(SearchServiceImpl.class,"getPageTrends",SlingHttpServletRequest.class)).toReturn(trends);
			Mockito.when(responseMap.get("trends")).thenReturn(trends);
			
			Map<String,Object> resultMap = searchService.advancedsearch(request);
			Assert.assertEquals("both are not equal ",resultMap.get("numberOfPages"), predicateMap.get("numberOfPages"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
