/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.adobe.granite.xss.XSSAPI;
import com.day.cq.commons.Doctype;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Style;
import com.day.cq.wcm.foundation.Image;
import com.tc.action.ImageAction;
import com.tc.model.ImageBean;

/**
 * The Class ImageActionTest.
 *
 * @author gdinakar
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ImageAction.class })
public class ImageActionTest extends BaseTest {
	
	/** The sling. */
	private SlingScriptHelper sling = null;
	
	/** The sling http servlet request. */
	private SlingHttpServletRequest slingHttpServletRequest = null;
	
	/** The sling http servlet response. */
	private SlingHttpServletResponse slingHttpServletResponse = null;
	
	/** The current resource. */
	private Resource currentResource;
	
	/** The image action. */
	private ImageAction imageAction = null;
	
	/** The image bean. */
	private ImageBean imageBean = null;
	
	/** The image. */
	private Image image = null;
	
	/** The current style. */
	private Style currentStyle = null;
	
	/** The current design. */
	private Design currentDesign = null;
	
	/** The resource design. */
	private Design resourceDesign = null;
	
	/** The xss api. */
	private XSSAPI xssAPI = null;
	
	/** The request specific xssapi. */
	private XSSAPI requestSpecificXSSAPI = null;
	
	/** The print writer. */
	private PrintWriter printWriter = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		imageAction = new ImageAction();
		imageBean = new ImageBean();
		image = Mockito.mock(Image.class);
		currentResource = Mockito.mock(Resource.class);
		PowerMockito.stub(PowerMockito.method(ImageAction.class, "getCurrentResource")).toReturn(currentResource);
		slingHttpServletRequest = Mockito.mock(SlingHttpServletRequest.class);
		PowerMockito.stub(PowerMockito.method(ImageAction.class, "getSlingRequest")).toReturn(slingHttpServletRequest);
		currentStyle = Mockito.mock(Style.class);
		PowerMockito.stub(PowerMockito.method(ImageAction.class, "getCurrentStyle")).toReturn(currentStyle);
		currentDesign = Mockito.mock(Design.class);
		PowerMockito.stub(PowerMockito.method(ImageAction.class, "getCurrentDesign")).toReturn(currentDesign);
		resourceDesign = Mockito.mock(Design.class);
		PowerMockito.stub(PowerMockito.method(ImageAction.class, "getResourceDesign")).toReturn(resourceDesign);
		sling = Mockito.mock(SlingScriptHelper.class);
		PowerMockito.stub(PowerMockito.method(ImageAction.class, "getSling")).toReturn(sling);
		xssAPI = Mockito.mock(XSSAPI.class);
		PowerMockito.stub(PowerMockito.method(ImageAction.class, "getService")).toReturn(xssAPI);
		requestSpecificXSSAPI = Mockito.mock(XSSAPI.class);
		slingHttpServletResponse = Mockito.mock(SlingHttpServletResponse.class);
		PowerMockito.stub(PowerMockito.method(ImageAction.class, "getSlingResponse")).toReturn(slingHttpServletResponse);
		printWriter = Mockito.mock(PrintWriter.class);
	}

	/**
	 * Test method for {@link com.tc.action.ImageAction#getImageDetails()}.
	 */
	@Test
	public void testGetImageDetails() {
		try {
			Mockito.when(currentResource.getPath()).thenReturn("/content/launchpad/us/en/home/jcr:content/content-region/image");
			Mockito.when(image.getFileReference()).thenReturn("/content/dam/geometrixx/travel/plane_3.jpg");
			Mockito.when(currentResource.getResourceType()).thenReturn("launchpad/components/content/image");
			Mockito.when(sling.getService(XSSAPI.class)).thenReturn(xssAPI);
			Mockito.when(xssAPI.getRequestSpecificAPI(slingHttpServletRequest)).thenReturn(requestSpecificXSSAPI);
			Mockito.when(slingHttpServletResponse.getWriter()).thenReturn(printWriter);
			Mockito.when(image.get(Image.PN_LINK_URL)).thenReturn("/content/geometrixx-media/en/entertainment");
			Mockito.when(requestSpecificXSSAPI.getValidHref(image.get(Image.PN_LINK_URL))).thenReturn("/content/geometrixx-media/en/entertainment");
			Mockito.when(image.getFileReference()).thenReturn("/content/dam/geometrixx/travel/plane_3.jpg");
			Mockito.when(image.getTitle()).thenReturn("Plane");
			Mockito.when(requestSpecificXSSAPI.encodeForJSString(image.getTitle())).thenReturn("Plane");
			Mockito.when(currentResource.getResourceType()).thenReturn("launchpad/components/content/image");
			imageBean = imageAction.getImageDetails();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals("Object Type:", ImageBean.class, imageBean.getClass());
		assertEquals("Div Id:", "cq-image-jsp-/content/launchpad/us/en/home/jcr:content/content-region/image", imageBean.getDivId());
	}

}
