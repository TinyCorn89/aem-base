/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import javax.jcr.Node;
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

import com.tc.action.SocialFollowAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.LongArticleDetailsBean;
import com.tc.model.SocialFollowBean;
import com.tc.util.Constants;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SocialFollowAction.class)
public class SocialFollowActionTest extends BaseTest {
	private SocialFollowAction socialFollowAction = null;
	private Node socialFollowNode=null;
	Logger logger;
	Property titlePropery;
	private String titleProperyValue = "follow us";
	Property facebookProperty;
	private String facebookPropertyValue = "gdinakarg";
	Property twitterProperty;
	private String twitterPropertyValue = "twitterapi";
	Property googlePlusProperty;
	private String googlePlusPropertyValue = "Ramakrishna";
	Property pageFollowersProperty;
		
	@Before
	public void setUp() throws Exception {
		socialFollowNode = Mockito.mock(Node.class);
		logger = Mockito.mock(Logger.class);
		titlePropery = Mockito.mock(Property.class);
		facebookProperty = Mockito.mock(Property.class);
		twitterProperty = Mockito.mock(Property.class);
		googlePlusProperty = Mockito.mock(Property.class);
		socialFollowAction = Whitebox.newInstance(SocialFollowAction.class);
		pageFollowersProperty = Mockito.mock(Property.class);
		PowerMockito.stub(
				PowerMockito.method(SocialFollowAction.class, "getCurrentNode")).toReturn(socialFollowNode);
		
		PowerMockito.stub(
				PowerMockito.method(FrameworkLogger.class, "getLogger"))
				.toReturn(logger);
		Whitebox.setInternalState(socialFollowAction, logger);
		
	}

	@After
	public void tearDown() throws Exception {
		socialFollowAction = null;
	}
	
	@Test
	public void testSocialFollowAction() {
		try {
			Mockito.when(socialFollowNode.hasProperty(Constants.SOCIALFOLLOW.TITLE)).thenReturn(true);
			Mockito.when(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.TITLE)).thenReturn(titlePropery);
			Mockito.when(titlePropery.getString()).thenReturn(titleProperyValue);
			Mockito.when(socialFollowNode.hasProperty(Constants.SOCIALFOLLOW.FACEBOOK)).thenReturn(true);
			Mockito.when(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.FACEBOOK)).thenReturn(facebookProperty);
			Mockito.when(facebookProperty.getString()).thenReturn(facebookPropertyValue);
			Mockito.when(socialFollowNode.hasProperty(Constants.SOCIALFOLLOW.TWITTER)).thenReturn(true);
			Mockito.when(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.TWITTER)).thenReturn(twitterProperty);
			Mockito.when(twitterProperty.getString()).thenReturn(twitterPropertyValue);
			Mockito.when(socialFollowNode.hasProperty(Constants.SOCIALFOLLOW.GOOGLEPLUS)).thenReturn(true);
			Mockito.when(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.GOOGLEPLUS)).thenReturn(googlePlusProperty);
			Mockito.when(googlePlusProperty.getString()).thenReturn(googlePlusPropertyValue);
			Mockito.when(socialFollowNode.hasProperty(Constants.SOCIALFOLLOW.PAGEFOLLOWERS)).thenReturn(true);
			Mockito.when(socialFollowNode.getProperty(Constants.SOCIALFOLLOW.PAGEFOLLOWERS)).thenReturn(pageFollowersProperty);
			Mockito.when(pageFollowersProperty.getBoolean()).thenReturn(false);
			
			SocialFollowBean socialFollowBean = socialFollowAction.getListOfIcons();
			
			assertEquals(Constants.SOCIALFOLLOW.TITLE,titleProperyValue,socialFollowBean.getTitle());
			assertEquals(Constants.SOCIALFOLLOW.FACEBOOK,facebookPropertyValue,socialFollowBean.getFbUserid());
			assertEquals(Constants.SOCIALFOLLOW.TWITTER,twitterPropertyValue,socialFollowBean.getTwitterUserId());
			
		}
			

		 catch (Exception e) {
			e.printStackTrace();
		}
	}
}
