/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import static org.junit.Assert.*;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.tc.action.FacebookFeedAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.FacebookFeedBean;

/**
 * The Class FacebookFeedActionTest.
 *
 * @author gdinakar
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ FacebookFeedAction.class, FrameworkLogger.class })
public class FacebookFeedActionTest extends BaseTest {
	
	/** The facebook feed action. */
	private FacebookFeedAction facebookFeedAction = null;
	
	/** The facebook feed bean. */
	private FacebookFeedBean facebookFeedBean = null;
	
	/** The current node. */
	private Node currentNode = null;
	
	/** The page name property. */
	private Property pageNameProperty = null;
	
	/** The color scheme property. */
	private Property colorSchemeProperty = null;
	
	/** The width property. */
	private Property widthProperty = null;
	
	/** The height property. */
	private Property heightProperty = null;
	
	/** The show faces property. */
	private Property showFacesProperty = null;
	
	/** The show header property. */
	private Property showHeaderProperty = null;
	
	/** The show posts property. */
	private Property showPostsProperty = null;
	
	/** The show border property. */
	private Property showBorderProperty = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		currentNode = Mockito.mock(Node.class);
		facebookFeedAction = new FacebookFeedAction();
		facebookFeedBean = new FacebookFeedBean();
		PowerMockito.stub(PowerMockito.method(FacebookFeedAction.class, "getCurrentNode")).toReturn(currentNode);
		pageNameProperty = Mockito.mock(Property.class);
		colorSchemeProperty = Mockito.mock(Property.class);
		widthProperty = Mockito.mock(Property.class);
		heightProperty = Mockito.mock(Property.class);
		showFacesProperty = Mockito.mock(Property.class);
		showHeaderProperty = Mockito.mock(Property.class);
		showPostsProperty = Mockito.mock(Property.class);
		showBorderProperty = Mockito.mock(Property.class);
	}

	/**
	 * Test method for {@link com.tc.action.FacebookFeedAction#getFacebookFeedProperties()}.
	 */
	@Test
	public void testGetFacebookFeedProperties() {
		try {
			Mockito.when(currentNode.hasProperty("pageName")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./pageName")).thenReturn(pageNameProperty);
			Mockito.when(pageNameProperty.getString()).thenReturn("VirtusaCorp");
			
			Mockito.when(currentNode.hasProperty("colorScheme")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./colorScheme")).thenReturn(colorSchemeProperty);
			Mockito.when(colorSchemeProperty.getString()).thenReturn("light");
			
			Mockito.when(currentNode.hasProperty("width")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./width")).thenReturn(widthProperty);
			Mockito.when(widthProperty.getString()).thenReturn("300");
			
			Mockito.when(currentNode.hasProperty("height")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./height")).thenReturn(heightProperty);
			Mockito.when(heightProperty.getString()).thenReturn("500");
			
			Mockito.when(currentNode.hasProperty("showFaces")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./showFaces")).thenReturn(showFacesProperty);
			Mockito.when(showFacesProperty.getString()).thenReturn("true");
			
			Mockito.when(currentNode.hasProperty("showHeader")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./showHeader")).thenReturn(showHeaderProperty);
			Mockito.when(showHeaderProperty.getString()).thenReturn("true");
			
			Mockito.when(currentNode.hasProperty("showPosts")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./showPosts")).thenReturn(showPostsProperty);
			Mockito.when(showPostsProperty.getString()).thenReturn("true");
			
			Mockito.when(currentNode.hasProperty("showBorder")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./showBorder")).thenReturn(showBorderProperty);
			Mockito.when(showBorderProperty.getString()).thenReturn("true");
			
			facebookFeedBean =  facebookFeedAction.getFacebookFeedProperties();
			
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
		
		assertEquals("Page Name:","VirtusaCorp", facebookFeedBean.getPageName());
		assertEquals("Color Scheme:","light", facebookFeedBean.getColorScheme());
		assertEquals("Width:",300, facebookFeedBean.getWidth());
		assertEquals("Height:",500, facebookFeedBean.getHeight());
		assertEquals("Show Faces:","true", facebookFeedBean.getShowFaces());
		assertEquals("Show Header:","true", facebookFeedBean.getShowHeader());
		assertEquals("Show Posts:","true", facebookFeedBean.getShowPosts());
		assertEquals("Show Border:","true", facebookFeedBean.getShowBorder());

	}

}
