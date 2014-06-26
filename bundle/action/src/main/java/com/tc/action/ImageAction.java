/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.xss.XSSAPI;
import com.day.cq.commons.Doctype;
import com.day.cq.wcm.api.components.DropTarget;
import com.day.cq.wcm.foundation.Image;
import com.day.cq.wcm.foundation.Placeholder;
import com.tc.action.BaseAction;
import com.tc.model.ImageBean;
/**
 * The Class ImageAction.
 *
 * @author gdinakar
 */
public class ImageAction extends BaseAction {
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ImageAction.class);
	
	/**
	 * Gets the image details.
	 *
	 * @return the image details
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ImageBean getImageDetails() throws IOException {
		LOG.info("Entered getImageDetails method");
		
		ImageBean imageBean = new ImageBean();
		
		Image image = new Image(getCurrentResource());
	    image.setIsInUITouchMode(Placeholder.isAuthoringUIModeTouch(getSlingRequest()));

	    //Drop target css class = dd prefix + name of the drop target in the edit config
	    image.addCssClass(DropTarget.CSS_CLASS_PREFIX + "image");
	    image.loadStyleData(getCurrentStyle());
	    image.setSelector(".img"); // use image script
	    image.setDoctype(Doctype.fromRequest(getSlingRequest()));
	    
	    //Add design information if not default (i.e. for reference paras)
	    if (!getCurrentDesign().equals(getResourceDesign())) {
	        image.setSuffix(getCurrentDesign().getId());
	    }
	    
	    String divId = "cq-image-jsp-" + getCurrentResource().getPath();
	    
	    //Getting request or resourceresolver specific XSSAPI
	    XSSAPI xssAPI = getSling().getService(XSSAPI.class);
	    xssAPI = xssAPI.getRequestSpecificAPI(getSlingRequest());
	    
	    imageBean.setImage(image);
	    imageBean.setDivId(divId);
	    imageBean.setPrintWriter(getSlingResponse().getWriter());
	    imageBean.setImageLink(xssAPI.getValidHref(image.get(Image.PN_LINK_URL)));
	    imageBean.setImageAsset(image.getFileReference());
	    imageBean.setImageTitle(xssAPI.encodeForJSString(image.getTitle()));
	    imageBean.setComponentPath(getCurrentResource().getResourceType());
	    
		return imageBean;
	}

}
