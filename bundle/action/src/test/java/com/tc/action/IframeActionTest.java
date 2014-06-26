/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import static org.junit.Assert.*;

import javax.jcr.Node;
import javax.jcr.Property;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;

import com.tc.action.IframeAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.IframeBean;

/**
 * @author KATRTEEKA RAJA GUMPA
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ IframeAction.class})
public class IframeActionTest extends BaseTest {
	private String PATH = "urlPath";
	private String WIDTH = "width";
	private String HEIGHT = "height";
	private String ALIGNMENT = "alignment";
	private String FRAME_BORDER = "frameBorder";
	private String SCROLLING = "scrolling";
	private IframeAction iframeAction;
	private Node currentNode;
	private Property urlProperty;
	private Property widthProperty;
	private Property heightProperty;
	private Property alignmentProperty;
	private Property frameBorderProperty;
	private Property scrollingProperty;
   
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		currentNode = Mockito.mock(Node.class);
		urlProperty = Mockito.mock(Property.class);
		widthProperty = Mockito.mock(Property.class);
		heightProperty = Mockito.mock(Property.class);
		alignmentProperty = Mockito.mock(Property.class);
		frameBorderProperty = Mockito.mock(Property.class);
		scrollingProperty = Mockito.mock(Property.class);
		iframeAction = new IframeAction();
		
		PowerMockito.stub(PowerMockito.method(IframeAction.class, "getCurrentNode")).toReturn(currentNode);
	}

	/**
	 * Test method for {@link com.tc.action.IframeAction#getIframeInfo()}.
	 */
	@Test
	public void testGetIframeInfo() {
		try{
			Mockito.when(currentNode.hasProperty(PATH)).thenReturn(true);
			Mockito.when(currentNode.getProperty(PATH)).thenReturn(urlProperty);
			Mockito.when(urlProperty.getString()).thenReturn("url");
			
			Mockito.when(currentNode.hasProperty(WIDTH)).thenReturn(true);
			Mockito.when(currentNode.getProperty(WIDTH)).thenReturn(widthProperty);
			Mockito.when(widthProperty.getString()).thenReturn("200");
			
			Mockito.when(currentNode.hasProperty(HEIGHT)).thenReturn(true);
			Mockito.when(currentNode.getProperty(HEIGHT)).thenReturn(heightProperty);
			Mockito.when(heightProperty.getString()).thenReturn("200");
			
			Mockito.when(currentNode.hasProperty(ALIGNMENT)).thenReturn(true);
			Mockito.when(currentNode.getProperty(ALIGNMENT)).thenReturn(alignmentProperty);
			Mockito.when(alignmentProperty.getString()).thenReturn("left");
			
			Mockito.when(currentNode.hasProperty(FRAME_BORDER)).thenReturn(true);
			Mockito.when(currentNode.getProperty(FRAME_BORDER)).thenReturn(frameBorderProperty);
			Mockito.when(frameBorderProperty.getString()).thenReturn("1");
			
			Mockito.when(currentNode.hasProperty(SCROLLING)).thenReturn(true);
			Mockito.when(currentNode.getProperty(SCROLLING)).thenReturn(scrollingProperty);
			Mockito.when(scrollingProperty.getString()).thenReturn("yes");
			
			IframeBean ifraeBean =  iframeAction.getIframeInfo();
			assertEquals("url",ifraeBean.getSrcPath());
			assertEquals("200", ifraeBean.getHeight());
			assertEquals("200", ifraeBean.getWidth());
			assertEquals("left", ifraeBean.getAlignment());
			assertEquals( "1", ifraeBean.getFrameborder());
			assertEquals("yes", ifraeBean.getScrolling());
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

}
