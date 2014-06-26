/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */	
package com.tc.action;

import static org.junit.Assert.assertEquals;

import javax.jcr.Node;
import javax.jcr.Property;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.tc.action.FreeHTMLAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.FreeHTMLBean;

/**
 * The Class FreeHTMLActionTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ FreeHTMLAction.class, FrameworkLogger.class })
public class FreeHTMLActionTest extends BaseTest {

	/** The free html action. */
	private FreeHTMLAction freeHTMLAction = null;
	
	/** The current node. */
	Node currentNode = null;
	
	/** The property. */
	Property property = null;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		currentNode = Mockito.mock(Node.class);
		property = Mockito.mock(Property.class);
		freeHTMLAction = new FreeHTMLAction();
		PowerMockito.stub(PowerMockito.method(FreeHTMLAction.class, "getCurrentNode")).toReturn(currentNode);
	}

	/**
	 * Test get html code.
	 */
	@Test
	public void testGetHTMLCode() {
		try{
			Mockito.when(currentNode.hasProperty("htmlCode")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./htmlCode")).thenReturn(property);
			Mockito.when(property.getString()).thenReturn("<html><body>Test</body></html>");
			FreeHTMLBean freeHTMLBean = new FreeHTMLBean();  
			freeHTMLBean = freeHTMLAction.getHTMLCode();
			assertEquals("HTML:","<html><body>Test</body></html>", freeHTMLBean.getHtmlCode());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
