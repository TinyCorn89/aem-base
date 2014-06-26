/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import static org.junit.Assert.*;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.day.cq.wcm.api.components.Component;
import com.day.cq.wcm.foundation.Placeholder;
import com.tc.action.TableAction;
import com.tc.model.TableBean;

/**
 * The Class TableActionTest.
 *
 * @author gdinakar
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ TableAction.class , Placeholder.class})
public class TableActionTest extends BaseTest {
	
	/** The table action. */
	private TableAction tableAction = null;
	
	/** The table bean. */
	private TableBean tableBean = null;
	
	/** The sling http servlet request. */
	private SlingHttpServletRequest slingHttpServletRequest  = null;
	
	/** The component. */
	private Component component = null;
	
	/** The current resource. */
	private Resource currentResource;
	
	/** The classic placeholder. */
	private String classicPlaceholder = "<img src=\"/libs/cq/ui/resources/0.gif\" class=\"cq-table-placeholder\" alt=\"\">";
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tableAction = new TableAction();
		tableBean = new TableBean();
		slingHttpServletRequest = Mockito.mock(SlingHttpServletRequest.class);
		PowerMockito.stub(PowerMockito.method(TableAction.class, "getSlingRequest")).toReturn(slingHttpServletRequest);
		component = Mockito.mock(Component.class);
		PowerMockito.stub(PowerMockito.method(TableAction.class, "getComponent")).toReturn(component);
		PowerMockito.mockStatic(Placeholder.class);
		currentResource = Mockito.mock(Resource.class);
		PowerMockito.stub(PowerMockito.method(TableAction.class, "getCurrentResource")).toReturn(currentResource);
	}

	/**
	 * Test method for {@link com.tc.action.TableAction#getTableDetails()}.
	 */
	@Test
	public void testGetTableDetails() {
		Mockito.when(Placeholder.getDefaultPlaceholder(slingHttpServletRequest, component, classicPlaceholder)).thenReturn("PlaceHolder of Table");
		Mockito.when(currentResource.getResourceType()).thenReturn("launchpad/components/content/table");
		tableBean = tableAction.getTableDetails();
		assertEquals("Object Type:", TableBean.class, tableBean.getClass());
		assertEquals("Placeholder:", "PlaceHolder of Table", tableBean.getPlaceHolder());
		assertEquals("Component Path:", "launchpad/components/content/table", tableBean.getComponentPath());
	}

}
