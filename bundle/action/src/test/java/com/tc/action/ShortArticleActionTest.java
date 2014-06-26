/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.scripting.SlingScriptHelper;
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
import com.day.cq.wcm.core.stats.PageViewStatistics;
import com.day.cq.wcm.foundation.Navigation;
import com.day.cq.wcm.foundation.Navigation.Element;
import com.day.cq.wcm.foundation.Navigation.Element.Type;
import com.mchange.util.AssertException;
import com.tc.action.ShortArticleAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.LongArticleDetailsBean;
import com.tc.model.ShortArticleBean;
import com.tc.util.Constants;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ShortArticleAction.class)
public class ShortArticleActionTest extends BaseTest{

	private ShortArticleAction shortArticleAction = null;
	private Node shortArticleNode;
	Logger logger;
	private Property selectedOptionProperty=null;
	private Property showImpressionsProperty = null;
	private Property showImpressionsForProperty = null;
	private Property buttonTextProperty = null;
	private Property rootPathProperty = null;
	private SlingHttpServletRequest slingHttpServletRequest = null;
	private ResourceResolver resourceResolver = null;
	private String rootPath = "/content/launchpad/us/en/articles/article-1";
	private Resource resource;
	private Page rootPage;
	private Node longArticleNode;
	public SlingScriptHelper scriptHelper;
	public PageViewStatistics pwSvc;
	public Page pagePath;
	public LongArticleDetailsBean longArticleDetailsBeanMock;
	public Object pageStatistics[] = {"gjhgjhgjgjgjgjh",2,"page"};
	Resource rootResourcePage;
	Resource rootResourceNode;
	public Calendar nodeTime = null;
	public Property nodeTimeProperty = null;
	public Property rootPathAdvancedProp = null;
	public String rootPathAdvanced = "/content/launchpad/us/en/articles/";
	public Style currentStyle = null;
	Object object;
	public String depth = "3";
	List<Page> pagePaths;
	Iterator<Page> pageIterator;
	Iterator<Element> elementIterator;

    Element aNavElement;
    Page elementPage;
    Navigation navigationElements;
    Navigation.Element.Type elementType;

    @Before
	public void setUp() throws Exception {
		shortArticleNode = Mockito.mock(Node.class);
		logger = Mockito.mock(Logger.class);
		showImpressionsProperty = Mockito.mock(Property.class);
		showImpressionsForProperty = Mockito.mock(Property.class);
		selectedOptionProperty=Mockito.mock(Property.class);
		buttonTextProperty = Mockito.mock(Property.class);
		rootPathProperty = Mockito.mock(Property.class);
		slingHttpServletRequest = Mockito.mock(SlingHttpServletRequest.class);
		resourceResolver = Mockito.mock(ResourceResolver.class);
		resource = Mockito.mock(Resource.class);
		rootPage = Mockito.mock(Page.class);
		longArticleNode = Mockito.mock(Node.class);
		scriptHelper = Mockito.mock(SlingScriptHelper.class);
		pwSvc = Mockito.mock(PageViewStatistics.class);
		pagePath = Mockito.mock(Page.class);
		longArticleDetailsBeanMock = Mockito.mock(LongArticleDetailsBean.class);
		nodeTime = Mockito.mock(Calendar.class);
		nodeTimeProperty = Mockito.mock(Property.class);
		rootPathAdvancedProp = Mockito.mock(Property.class);
		rootResourcePage = Mockito.mock(Resource.class);
		rootResourceNode = Mockito.mock(Resource.class);
		currentStyle = Mockito.mock(Style.class);
		object = Mockito.mock(Object.class);
		pagePaths = Mockito.mock(List.class);
		pageIterator  = Mockito.mock(Iterator.class);
		elementIterator = Mockito.mock(Iterator.class);
        aNavElement = Mockito.mock(Element.class);
        elementPage = Mockito.mock(Page.class);
        elementType = Type.ITEM_BEGIN;
        navigationElements = Mockito.mock(Navigation.class);

		shortArticleAction = Whitebox.newInstance(ShortArticleAction.class);
		PowerMockito.stub(PowerMockito.method(ShortArticleAction.class, "getCurrentNode")).toReturn(shortArticleNode);
		PowerMockito.stub(PowerMockito.method(ShortArticleAction.class, "getCurrentStyle")).toReturn(currentStyle);
		PowerMockito.stub(
				PowerMockito.method(FrameworkLogger.class, "getLogger"))
				.toReturn(logger);
		PowerMockito.stub(PowerMockito.method(ShortArticleAction.class,"getSlingRequest")).toReturn(slingHttpServletRequest);

        Mockito.when(elementIterator.hasNext()).thenReturn(true,false);
        Mockito.when(elementIterator.next()).thenReturn(aNavElement);
        Mockito.when(aNavElement.getPage()).thenReturn(elementPage);
        Mockito.when(aNavElement.getType()).thenReturn(elementType);

        Mockito.when(navigationElements.iterator()).thenReturn(elementIterator);
        Whitebox.setInternalState(shortArticleAction, navigationElements);

        PowerMockito.stub(PowerMockito.method(ShortArticleAction.class, "getNavigation")).toReturn(navigationElements);
		PowerMockito.stub(PowerMockito.method(ShortArticleAction.class,"getSling")).toReturn(scriptHelper);
		Whitebox.setInternalState(shortArticleAction, logger);

	}


	@After
	public void tearDown() throws Exception {
		shortArticleAction = null;
	}

	@Test
	public void testShortArticleGeneralAction() {
		
		try {
			Mockito.when(shortArticleNode.getProperty(Constants.SHORTARTICLE.SELECTED_TAB)).thenReturn(selectedOptionProperty);
			Mockito.when(selectedOptionProperty.getString()).thenReturn("general");
			Mockito.when(shortArticleNode.hasProperty(Constants.SHORTARTICLE.SHOW_IMPRESSIONS)).thenReturn(true);
			Mockito.when(shortArticleNode.getProperty(Constants.SHORTARTICLE.SHOW_IMPRESSIONS)).thenReturn(showImpressionsProperty);
			Mockito.when(showImpressionsProperty.getBoolean()).thenReturn(true);
			Mockito.when(shortArticleNode.hasProperty(Constants.SHORTARTICLE.SHOW_IMPRESSIONS_FOR)).thenReturn(true);
			Mockito.when(shortArticleNode.getProperty(Constants.SHORTARTICLE.SHOW_IMPRESSIONS_FOR)).thenReturn(showImpressionsForProperty);
			Mockito.when(showImpressionsForProperty.getString()).thenReturn("month");
			Mockito.when(shortArticleNode.hasProperty(Constants.SHORTARTICLE.BUTTON_TEXT)).thenReturn(true);
			Mockito.when(shortArticleNode.getProperty(Constants.SHORTARTICLE.BUTTON_TEXT)).thenReturn(buttonTextProperty);
			Mockito.when(buttonTextProperty.getString()).thenReturn("more");
			Mockito.when(shortArticleNode.hasProperty(Constants.SHORTARTICLE.LINK)).thenReturn(true);
			Mockito.when(shortArticleNode.getProperty(Constants.SHORTARTICLE.LINK)).thenReturn(rootPathProperty);
			Mockito.when(rootPathProperty.getString()).thenReturn(rootPath);
			Mockito.when(slingHttpServletRequest.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.getResource(rootPath)).thenReturn(rootResourcePage);
			Mockito.when(rootResourcePage.adaptTo(Page.class)).thenReturn(rootPage);
			PowerMockito.stub(PowerMockito.method(ShortArticleAction.class, "getPage", String.class)).toReturn(rootPage);
			Mockito.when(resourceResolver.getResource(rootPath+Constants.SHORTARTICLE.lONGARTICLE_JCR_CONTENT_NODE)).thenReturn(rootResourceNode);
			Mockito.when(rootResourceNode.adaptTo(Node.class)).thenReturn(longArticleNode);
			Page page = Mockito.mock(Page.class);
			Mockito.when(rootPage.getParent()).thenReturn(page);
			PowerMockito.stub(PowerMockito.method(ShortArticleAction.class, "getArticlePageDetails", Node.class, String.class)).toReturn(longArticleDetailsBeanMock);
			PowerMockito.stub(PowerMockito.method(ShortArticleAction.class, "getImpressionCount", Page.class, String.class)).toReturn(
					"1");

			ShortArticleBean shortArticleBean =shortArticleAction.shortArticle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testShortArticleAdvancedAction() {
		 LongArticleDetailsBean longArticleDetailsBean = new LongArticleDetailsBean();
		Page parentPage = Mockito.mock(Page.class);
		Node pageNode = Mockito.mock(Node.class);
		Node jcrNode= Mockito.mock(Node.class);
		Node leftParNode = Mockito.mock(Node.class);
		Node longArticleNode =  Mockito.mock(Node.class);
		Property timeIntervalProp = Mockito.mock(Property.class);
		Property timeUnitProp = Mockito.mock(Property.class);
		
		try {
			Mockito.when(shortArticleNode.getProperty(Constants.SHORTARTICLE.SELECTED_TAB)).thenReturn(selectedOptionProperty);
			Mockito.when(selectedOptionProperty.getString()).thenReturn(Constants.SHORTARTICLE.ADVANCED);
			Mockito.when(shortArticleNode.getProperty(Constants.SHORTARTICLE.TIME)).thenReturn(nodeTimeProperty);
			Mockito.when(nodeTimeProperty.getDate()).thenReturn(nodeTime);
			Mockito.when(shortArticleNode.hasProperty(Constants.SHORTARTICLE.ARTICLE_FOLDER_PATH)).thenReturn(true);
			Mockito.when(shortArticleNode.getProperty(Constants.SHORTARTICLE.ARTICLE_FOLDER_PATH)).thenReturn(rootPathAdvancedProp);
			Mockito.when(rootPathAdvancedProp.getString()).thenReturn(rootPathAdvanced);

            Mockito.when(shortArticleNode.hasProperty("depth")).thenReturn(true);
            Property depthProperty = Mockito.mock(Property.class);
            Mockito.when(depthProperty.getString()).thenReturn(depth);
            Mockito.when(shortArticleNode.getProperty("depth")).thenReturn(depthProperty);

			Mockito.when(slingHttpServletRequest.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.getResource(rootPathAdvanced)).thenReturn(rootResourcePage);
			Mockito.when(rootResourcePage.adaptTo(Page.class)).thenReturn(rootPage);
			Mockito.when(elementPage.getParent()).thenReturn(parentPage);
			Mockito.when(parentPage.getName()).thenReturn("title");
			Mockito.when(elementPage.adaptTo(Node.class)).thenReturn(pageNode);
			Mockito.when(pageNode.hasNode(Constants.SHORTARTICLE.JCR_CONTENT)).thenReturn(true);
			Mockito.when(pageNode.getNode(Constants.SHORTARTICLE.JCR_CONTENT)).thenReturn(jcrNode);
			Mockito.when(jcrNode.hasNode(Constants.SHORTARTICLE.LEFT_PAR)).thenReturn(true);
			Mockito.when(jcrNode.getNode(Constants.SHORTARTICLE.LEFT_PAR)).thenReturn(leftParNode);
			Mockito.when(leftParNode.hasNode(Constants.SHORTARTICLE.LONG_ARTICLE)).thenReturn(true);
			Mockito.when(leftParNode.getNode(Constants.SHORTARTICLE.LONG_ARTICLE)).thenReturn(longArticleNode);
		
			PowerMockito.stub(
					PowerMockito
							.method(ShortArticleAction.class, "getArticlePageDetails"))
					.toReturn(longArticleDetailsBean);
			
			 Mockito.when(shortArticleNode.getProperty(Constants.SHORTARTICLE.TIME_INTERVAL)).thenReturn(timeIntervalProp);
			 Mockito.when(timeIntervalProp.getString()).thenReturn("3");
			 
			 Mockito.when(shortArticleNode.getProperty(Constants.SHORTARTICLE.TIMEUNIT)).thenReturn(timeUnitProp);
			 Mockito.when(timeUnitProp.getString()).thenReturn("timeUnit");
			 
			ShortArticleBean shortArticleBean =shortArticleAction.shortArticle();
			
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	
	@Test
	public void testGetArticlePageDetails() {
		  LongArticleDetailsBean articleBean = new LongArticleDetailsBean();
		  Node node = Mockito.mock(Node.class);
		  Property categoryProp = Mockito.mock(Property.class);
		  Property titleProp = Mockito.mock(Property.class);
		  Property referenceProp = Mockito.mock(Property.class);
		  Property aProp = Mockito.mock(Property.class);
		  Property showProp = Mockito.mock(Property.class);
		  Property descriptionProp = Mockito.mock(Property.class);
		  try{
			   Mockito.when(node.getProperty(Constants.SHORTARTICLE.CATEGORY)).thenReturn(categoryProp);
			   Mockito.when(categoryProp.getString()).thenReturn("categpry");
			   
			   Mockito.when(node.hasProperty(Constants.SHORTARTICLE.TITLE)).thenReturn(true);
			   Mockito.when(node.getProperty(Constants.SHORTARTICLE.TITLE)).thenReturn(titleProp);
			   Mockito.when(titleProp.getString()).thenReturn("title");
			   
			   Mockito.when(node.hasProperty(Constants.SHORTARTICLE.FILEREFERENCE)).thenReturn(true);
			   Mockito.when(node.getProperty(Constants.SHORTARTICLE.FILEREFERENCE)).thenReturn(referenceProp);
			   Mockito.when(referenceProp.getString()).thenReturn("filereference");
			   
			   Mockito.when(node.hasProperty("asset")).thenReturn(true);
			   Mockito.when(node.getProperty("asset")).thenReturn(aProp);
			   Mockito.when(aProp.getString()).thenReturn("assert");
			   
			   Mockito.when(node.hasProperty("show")).thenReturn(true);
			   Mockito.when(node.getProperty("show")).thenReturn(showProp);
			   Mockito.when(showProp.getString()).thenReturn("show");
			   			   
			   Mockito.when(node.hasProperty(Constants.SHORTARTICLE.DESCRIPTION)).thenReturn(true);
			   Mockito.when(node.getProperty(Constants.SHORTARTICLE.DESCRIPTION)).thenReturn(descriptionProp);
			   Mockito.when(descriptionProp.getString()).thenReturn("description");
			   
			   
			  
			  LongArticleDetailsBean articleBean1 = shortArticleAction.getArticlePageDetails(node,"title");
			
			  assertEquals("assert", articleBean1.getAsset());
			  assertEquals("categpry", articleBean1.getCategory());
			  assertEquals("description",articleBean1.getDescription() );
			  assertEquals("filereference", articleBean1.getImagePath());
			  assertEquals("show",articleBean1.getShow());
			  assertEquals("title",articleBean1.getTitle());
			  
			  
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		
		
		
	}


	
	
	
	


}


