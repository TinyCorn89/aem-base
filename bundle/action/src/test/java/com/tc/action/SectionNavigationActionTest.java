/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;

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
import org.slf4j.LoggerFactory;
//import org.virtusa.launchpad.model.SubTabBean;
//import org.virtusa.launchpad.model.TabBean;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.tc.action.SectionNavigationAction;
import com.tc.model.SectionNavigationBean;
import com.tc.util.Constants;


/**
 * The Class SectionNavigationActionTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SectionNavigationAction.class)
public class SectionNavigationActionTest extends BaseTest {



	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(HeroBannerActionTest.class);


	/** The smart navigation action. */
	private SectionNavigationAction sectionNavigationAction = null;

	/** The page path. */
	private static final String PAGE_PATH = "/jcr:content/par.html";

	/** The page title. */
	private static final String PAGE_TITLE = "pageTitle";

	/** The scrolling id. */
	private static final String SCROLLING_ID = "scrollingId";
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			sectionNavigationAction = new SectionNavigationAction();
		} catch(Exception exception){
			LOG.error(exception.getMessage(), exception);
		}
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
		sectionNavigationAction = null;
	}

	/**
	 * Test section tab.
	 */
	@Test
	public void testSectionNavigation() {
		LOG.info("entering into the method::*****************");
		 List<Object> listOfTabs = new ArrayList<Object>();
		 Page currentPage = Mockito.mock(Page.class);
		final Page helpPage = Mockito.mock(Page.class);
		final PageManager pageManager = Mockito.mock(PageManager.class);
		 SectionNavigationBean tabBean = new SectionNavigationBean();
		tabBean.setPagePath(PAGE_PATH);
		tabBean.setPageTitle(PAGE_TITLE);
		tabBean.setScrollingId(SCROLLING_ID);
		 List<Object> listOfSubTabBaean = new ArrayList<Object>();
		 SectionNavigationBean subTabBean = new SectionNavigationBean();
		subTabBean.setPageTitle("pageTitle");
		listOfSubTabBaean.add(subTabBean);
		tabBean.setListOfTabs(listOfSubTabBaean);
		listOfTabs.add(tabBean);
		try{
			LOG.info("enetering into the test class execute method");
			PowerMockito.stub(PowerMockito.method(SectionNavigationAction.class, "getProperty",String.class)).toReturn("parentApge");
			PowerMockito.stub(PowerMockito.method(SectionNavigationAction.class, "getCurrentPage")).toReturn(currentPage);
			Mockito.when(currentPage.getPageManager()).thenReturn(pageManager);
			Mockito.when(pageManager.getPage(Mockito.any(String.class))).thenReturn(helpPage);
			PowerMockito.stub(PowerMockito.method(SectionNavigationAction.class, "getAllTabsUsingParentPage",Page.class,SectionNavigationBean.class,String.class)).toReturn(listOfTabs);
			
			SectionNavigationBean sectionTabBean = sectionNavigationAction.sectionNavigation();
			 List<Object> tabList = new ArrayList<Object>();
			 tabList=sectionTabBean.getListOfTabs();
			 
			tabBean=(SectionNavigationBean)tabList.get(0);
			assertEquals("page path is::",PAGE_PATH,tabBean.getPagePath());
			assertEquals("pagetitle is::",PAGE_TITLE,tabBean.getPageTitle());
			assertEquals("scrolling id is::",SCROLLING_ID,tabBean.getScrollingId());
		}catch(Exception e){
			fail("exception occured in testSectionNavigation method ");
		}

	}

	
	/**
	 * Test get all tabs using parent page.
	 */
	@Test
	public void testGetAllTabsUsingParentPage(){
		LOG.info("enetering into testGetAllTabsUsingParentPage method");
		 SectionNavigationBean tabBean = new SectionNavigationBean();
		tabBean.setPagePath(PAGE_PATH);
		tabBean.setPageTitle(PAGE_TITLE);
		tabBean.setScrollingId(SCROLLING_ID);
		try{
			final Page sourcePage = Mockito.mock(Page.class);
			final Node sourceNode = Mockito.mock(Node.class);
			final NodeIterator sourceNodeItr  = Mockito.mock(NodeIterator.class);
			Mockito.when(sourcePage.adaptTo(Node.class)).thenReturn(sourceNode);
			Mockito.when(sourceNode.getNodes()).thenReturn(sourceNodeItr);
			Mockito.when(sourceNodeItr.hasNext()).thenReturn(true,false);
			final Node sourceChildNode =Mockito.mock(Node.class);
			Mockito.when(sourceNodeItr.next()).thenReturn(sourceChildNode);
			Mockito.when(sourceChildNode.getName()).thenReturn("content1");
			Mockito.when(sourceChildNode.hasNode(Constants.SMARTNAVIGATION.SMART_NAVIGATION_JCR_CONTENT)).thenReturn(true);
			final Node jcrContentNode = Mockito.mock(Node.class);
			Mockito.when(sourceChildNode.getNode(Constants.SMARTNAVIGATION.SMART_NAVIGATION_JCR_CONTENT)).thenReturn(jcrContentNode);
			Mockito.when(jcrContentNode.hasNode(Constants.SMARTNAVIGATION.SMART_NAVIGATION_PAR_NODE)).thenReturn(true);
			PowerMockito.stub(PowerMockito.method(SectionNavigationAction.class, "getAllSubTabBeansUsingTabName",Page.class,SectionNavigationBean.class,String.class)).toReturn(tabBean);
			 List<Object> listOfTabBeans = Whitebox.invokeMethod(sectionNavigationAction, "getAllTabsUsingParentPage", sourcePage,tabBean,"scrollId");
			tabBean=(SectionNavigationBean)listOfTabBeans.get(0);
			
			assertEquals("pagepath is::",PAGE_PATH,tabBean.getPagePath());
			assertEquals("pagepath is::",PAGE_TITLE,tabBean.getPageTitle());
			assertEquals("pagepath is::",SCROLLING_ID,tabBean.getScrollingId());
			LOG.info("exiting from testGetAllTabsUsingParentPage method");
		}catch(Exception e){
			fail("exception occured in testGetAllTttsFromParentPage method ");
		}
	}
	
	
	/**
	 * Test get all sub tab beans using tab name.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllSubTabBeansUsingTabName(){
		LOG.info("entering into testGetAllSubTabBeansUsingTabName method");
		 SectionNavigationBean tabBean = new SectionNavigationBean();
	    String childPageTitle = "sub menu";
	    String pageTitle = "launchpad";
		Page sourcePage  = Mockito.mock(Page.class);
		try {
			sourcePage = Mockito.mock(Page.class);
			final Iterator<Page> iterator = Mockito.mock(Iterator.class);
			Mockito.when(sourcePage.listChildren()).thenReturn(iterator);
			Mockito.when(iterator.hasNext()).thenReturn(true,false);
			final Page childPage = Mockito.mock(Page.class);
			Mockito.when((Page) iterator.next()).thenReturn(childPage);
			Mockito.when(childPage.getName()).thenReturn("scrollId");
			Mockito.when(childPage.getTitle()).thenReturn(pageTitle);
			final Iterator<Page> iterator1  = Mockito.mock(Iterator.class);
			Mockito.when(childPage.listChildren()).thenReturn(iterator1);
			Mockito.when(iterator1.hasNext()).thenReturn(true,false);
			final Page page1 = Mockito.mock(Page.class);
			Mockito.when((Page) iterator1.next()).thenReturn(page1);
			Mockito.when(page1.getTitle()).thenReturn(childPageTitle);
		    SectionNavigationBean resultTabBean = Whitebox.invokeMethod(sectionNavigationAction, "getAllSubTabBeansUsingTabName",sourcePage,new SectionNavigationBean(),"scrollId");
			List<Object> list=resultTabBean.getListOfTabs();
			tabBean=(SectionNavigationBean)list.get(0);
			assertEquals("subPageTitle is::",childPageTitle,tabBean.getPageTitle());
			assertEquals("PageTitle is::",pageTitle,resultTabBean.getPageTitle());
			LOG.info("exiting from testGetAllSubTabBeansUsingTabName method");
		} catch (Exception e) {
			fail("exception occured in testGetAllSubTabBeansUsingTabName method in SectionNavigationActionTest ");
		}
	}

}
