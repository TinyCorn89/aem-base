/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */


package com.tc.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.core.stats.PageViewStatistics;
import com.tc.action.BaseAction;
import com.tc.action.MostViewedAticleAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.MostViewedArticleBean;
import com.tc.model.MostViewedBean;
import com.tc.util.Constants;


/**
 * @author KARTEEKA RAJA GUMPA
 * The Class MostViewedAticleActionTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ MostViewedAticleAction.class, FrameworkLogger.class })
public class MostViewedAticleActionTest extends BaseTest {

	/** The most viewed article. */
	private MostViewedAticleAction mostViewedArticle = null;

	/** The current node. */
	private Node currentNode;

	/** The property. */
	private Property property;

	/** The path property. */
	private Property pathProperty;

	/** The header property. */
	private Property headerProperty;

	/** The footer property. */
	private Property footerProperty;

	
	private Page page;

	/** The pw svc. */
	public PageViewStatistics pwSvc;

	/** The sling. */
	public SlingScriptHelper sling;

	/** The resource. */
	private Resource resource;

	/** The static node. */
	private Node staticNode;

	/** The static propety. */
	private Property staticPropety;
	
	private Iterator<Page> children;
	
	/** The page. */
	private Page childPage;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		currentNode = Mockito.mock(Node.class);
		property = Mockito.mock(Property.class);
		pathProperty = Mockito.mock(Property.class);
		page = Mockito.mock(Page.class);
		headerProperty = Mockito.mock(Property.class);
		footerProperty = Mockito.mock(Property.class);
		pwSvc = Mockito.mock(PageViewStatistics.class);
		mostViewedArticle = new MostViewedAticleAction();
		sling = Mockito.mock(SlingScriptHelper.class);
		resource = Mockito.mock(Resource.class);
		staticNode = Mockito.mock(Node.class);
		staticPropety = Mockito.mock(Property.class);
		children = Mockito.mock(Iterator.class);
		childPage = Mockito.mock(Page.class);
		PowerMockito.stub(
				PowerMockito.method(MostViewedAticleAction.class,
						"getCurrentNode")).toReturn(currentNode);
	}

	/**
	 * Test get most viewed articles.
	 */
	@Test
	public void testGetMostViewedArticles() {
		List<MostViewedArticleBean> mostviewedArticleList = new ArrayList<MostViewedArticleBean>();
		try {

			Mockito.when(currentNode.hasProperty("noOfArticles")).thenReturn(
					true);
			Mockito.when(currentNode.getProperty("noOfArticles")).thenReturn(
					property);
			Mockito.when(property.getString()).thenReturn("2");
			Mockito.when(currentNode.hasProperty("articlePath")).thenReturn(
					true);
			Mockito.when(currentNode.getProperty("articlePath")).thenReturn(
					pathProperty);
			Mockito.when(pathProperty.getString()).thenReturn("en/us/article");
			PowerMockito.stub(
					PowerMockito
							.method(MostViewedAticleAction.class, "getPage"))
					.toReturn(page);
			PowerMockito.stub(
					PowerMockito.method(MostViewedAticleAction.class,
							"getArticleList")).toReturn(mostviewedArticleList);
			Mockito.when(currentNode.hasProperty("header")).thenReturn(true);
			Mockito.when(currentNode.getProperty("header")).thenReturn(
					headerProperty);
			Mockito.when(headerProperty.getString()).thenReturn("headertext");

			Mockito.when(currentNode.hasProperty("seeAllText"))
					.thenReturn(true);
			Mockito.when(currentNode.getProperty("seeAllText")).thenReturn(
					footerProperty);
			Mockito.when(footerProperty.getString()).thenReturn("footertext");

			MostViewedBean mostviewedBean = mostViewedArticle
					.getMostViewedArticles();
			assertEquals(0, mostviewedBean.getNoOfArticles());
			assertEquals("en/us/article.html",
					mostviewedBean.getArticleParent());
			assertEquals("headertext", mostviewedBean.getHeader());
			assertEquals("footertext", mostviewedBean.getSeeAllText());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test page impression.
	 */
	@Test
	public void testPageImpression() {
		Object pageStatistics[] = new Object[4];
		pageStatistics[0] = "en/us/sample";
		try {
			PowerMockito
					.stub(PowerMockito.method(BaseAction.class, "getSling"))
					.toReturn(sling);
			Mockito.when(sling.getService(PageViewStatistics.class))
					.thenReturn(pwSvc);
			Mockito.when(pwSvc.report(page)).thenReturn(pageStatistics);
			PowerMockito.stub(
					PowerMockito.method(MostViewedAticleAction.class,
							"getResource")).toReturn(resource);
			Mockito.when(resource.adaptTo(Node.class)).thenReturn(staticNode);
			Mockito.when(staticNode.hasProperty(Constants.SHORTARTICLE.VIEWS))
					.thenReturn(true);
			Mockito.when(staticNode.getProperty(Constants.SHORTARTICLE.VIEWS))
					.thenReturn(staticPropety);
			Mockito.when(staticPropety.getString()).thenReturn("3");
     	int impressions = mostViewedArticle.pageImpression(page);
     	assertEquals(3, impressions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test get article list.
	 */
	@Test
	public void testGetArticleList() {

		try {
			Mockito.when(page.listChildren()).thenReturn(children);
			Mockito.when(children.hasNext()).thenReturn(true, false);
			PowerMockito.stub(
					PowerMockito.method(MostViewedAticleAction.class,
							"pageImpression")).toReturn(2);
			Mockito.when(children.next()).thenReturn(childPage);
			Mockito.when(childPage.getTitle()).thenReturn("title");
			Mockito.when(childPage.getPath()).thenReturn("en/us/article");
			List<MostViewedArticleBean> mostviewedArticleList = mostViewedArticle.getArticleList(page, 0);
			MostViewedArticleBean mostViewedArticleBean = mostviewedArticleList
					.get(0);
			assertEquals("en/us/article.html",
					mostViewedArticleBean.getArticlePath());
			assertEquals("title", mostViewedArticleBean.getTitle());
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
