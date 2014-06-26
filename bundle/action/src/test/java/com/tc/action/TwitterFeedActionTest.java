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

import com.tc.action.TwitterFeedAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.TwitterFeedBean;

/**
 * The Class TwitterFeedActionTest.
 *
 * @author gdinakar
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ TwitterFeedAction.class, FrameworkLogger.class })
public class TwitterFeedActionTest extends BaseTest {
	
	/** The twitter feed action. */
	private TwitterFeedAction twitterFeedAction = null;
	
	/** The twitter feed bean. */
	private TwitterFeedBean twitterFeedBean = null;
	
	/** The current node. */
	private Node currentNode = null;
	
	/** The user name property. */
	private Property userNameProperty = null;
	
	/** The widget id property. */
	private Property widgetIdProperty = null;
	
	/** The theme property. */
	private Property themeProperty = null;
	
	/** The width property. */
	private Property widthProperty = null;
	
	/** The height property. */
	private Property heightProperty = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		twitterFeedAction = new TwitterFeedAction();
		twitterFeedBean = new TwitterFeedBean();
		currentNode = Mockito.mock(Node.class);
		PowerMockito.stub(PowerMockito.method(TwitterFeedAction.class, "getCurrentNode")).toReturn(currentNode);
		userNameProperty = Mockito.mock(Property.class);
		widgetIdProperty = Mockito.mock(Property.class);
		themeProperty = Mockito.mock(Property.class);
		widthProperty = Mockito.mock(Property.class);
		heightProperty = Mockito.mock(Property.class);
	}

	/**
	 * Test method for {@link com.tc.action.TwitterFeedAction#getTwitterWidgetProperties()}.
	 */
	@Test
	public void testGetTwitterWidgetProperties() {
		try {
			Mockito.when(currentNode.getProperty("./userName")).thenReturn(userNameProperty);
			Mockito.when(userNameProperty.getString()).thenReturn("VirtusaCorp");
			
			Mockito.when(currentNode.getProperty("./widgetId")).thenReturn(widgetIdProperty);
			Mockito.when(widgetIdProperty.getString()).thenReturn("470815388624642048");
			
			Mockito.when(currentNode.hasProperty("theme")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./theme")).thenReturn(themeProperty);
			Mockito.when(themeProperty.getString()).thenReturn("light");
			
			Mockito.when(currentNode.getProperty("./width")).thenReturn(widthProperty);
			Mockito.when(widthProperty.getString()).thenReturn("300");
			
			Mockito.when(currentNode.getProperty("./height")).thenReturn(heightProperty);
			Mockito.when(heightProperty.getString()).thenReturn("500");
			
			twitterFeedBean = twitterFeedAction.getTwitterWidgetProperties();
			
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
		assertEquals("User Name:","VirtusaCorp", twitterFeedBean.getUserName());
		assertEquals("Widget Id:","470815388624642048", twitterFeedBean.getWidgetId());
		assertEquals("Theme:","light", twitterFeedBean.getTheme());
		assertEquals("Width:",300, twitterFeedBean.getWidth());
		assertEquals("Height:",500, twitterFeedBean.getHeight());
	}

}
