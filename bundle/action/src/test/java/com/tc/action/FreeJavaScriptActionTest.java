/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */	
package com.tc.action;

import static org.junit.Assert.assertEquals;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.tc.action.FreeJavaScriptAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.FreeJavaScriptBean;

/**
 * The Class FreeJavaScriptActionTest.
 *
 * @author gdinakar
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ FreeJavaScriptAction.class, FrameworkLogger.class })
public class FreeJavaScriptActionTest extends BaseTest {
	
	/** The free java script action. */
	private FreeJavaScriptAction freeJavaScriptAction = null;
	
	/** The free java script bean. */
	FreeJavaScriptBean freeJavaScriptBean = null;
	
	/** The current node. */
	Node currentNode = null;
	
	/** The property. */
	Property property = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		freeJavaScriptAction = new FreeJavaScriptAction();
		currentNode = Mockito.mock(Node.class);
		property = Mockito.mock(Property.class);
		PowerMockito.stub(PowerMockito.method(FreeJavaScriptAction.class, "getCurrentNode")).toReturn(currentNode);
	}

	/**
	 * Test method for {@link com.tc.action.FreeJavaScriptAction#getJavaScript()}.
	 */
	@Test
	public void testGetJavaScript() {
		try {
			Mockito.when(currentNode.hasProperty("javaScript")).thenReturn(true);
			Mockito.when(currentNode.getProperty("./javaScript")).thenReturn(property);
			Mockito.when(property.getString()).thenReturn("alert('Test');");
			freeJavaScriptBean = new FreeJavaScriptBean();
			freeJavaScriptBean = freeJavaScriptAction.getJavaScript();
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		} catch (ValueFormatException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		} 
		assertEquals("JS:","alert('Test');", freeJavaScriptBean.getJavaScript());
	}

}
