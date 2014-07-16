/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;

import com.day.cq.wcm.foundation.Image;
import com.day.cq.wcm.foundation.Placeholder;
import com.tc.action.BaseAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.LongArticlePropertiesBean;

/**
 * The Class LongArticlePropertiesAction.
 *
 * @author gdinakar
 */
public class LongArticlePropertiesAction extends BaseAction {
	
	/** The logger. */
	private Logger logger = FrameworkLogger.getLogger();
	
	/**
     * Gets the long article properties.
     *
     * @return the long article properties
     * @throws RepositoryException the repository exception
     */
    public LongArticlePropertiesBean getLongArticleProperties() throws RepositoryException {
    	logger.info("Entered getLongArticleProperties method");
    	
    	LongArticlePropertiesBean longArticlePropertiesBean = new LongArticlePropertiesBean();
    	
    	boolean isAuthoringUIModeTouch = Placeholder.isAuthoringUIModeTouch(getSlingRequest());
    	String title = null;
    	String description = null;
    	Image image = null;

    	if(getCurrentNode() != null) {
	    	if(getCurrentNode().hasProperty("assetPath")){
	    		String assetPath = getCurrentNode().getProperty("assetPath").getString();
	    		Resource assetResource =  getSlingRequest().getResourceResolver().getResource(assetPath);
	    		Node assetNode = getSlingRequest().getResourceResolver().getResource(assetPath).adaptTo(Node.class);
	    		image = new Image(assetResource, "./jcr:content/image");
	    	    title = assetNode.getProperty("./jcr:content/jcr:title").getString();
	    	    description = assetNode.getProperty("./jcr:content/body").getString();
	    	} else {
	    		image = new Image(getCurrentResource(), "image");
	    		title = getCurrentNode().getProperty("title").getString();
	    	    description = getCurrentNode().getProperty("description").getString();
	    	}
    	}
		 /**
		  *  Don't draw the placeholder in case UI mode touch it will be handled afterwards.
		  */
        if (isAuthoringUIModeTouch) {
            image.setNoPlaceholder(true);
        }
        
        if(image != null) {
        	longArticlePropertiesBean.setImageHasContent(image.hasContent());
        	longArticlePropertiesBean.setImageLocation(image.getHref());
        }
        longArticlePropertiesBean.setTitle(title);
        longArticlePropertiesBean.setDescription(description);
        
        return longArticlePropertiesBean;
    }

}
