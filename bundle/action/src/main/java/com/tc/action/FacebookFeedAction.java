/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.action.BaseAction;
import com.tc.model.FacebookFeedBean;

/**
 * The Class FacebookFeedAction.
 * 
 * @author gdinakar
 */
public class FacebookFeedAction extends BaseAction {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FacebookFeedAction.class);
	
	/**
	 * Gets the facebook feed properties.
	 * 
	 * @return the facebook feed properties
	 * @throws RepositoryException 
	 */
	public FacebookFeedBean getFacebookFeedProperties() throws RepositoryException {
		LOG.info("Entered getFacebookFeedProperties method");
		FacebookFeedBean facebookFeedBean = new FacebookFeedBean();
		
		Node currentNode = getCurrentNode();
		String pageName = null;
		String colorScheme = null;
		int width = 0;
		int height = 0;

		String showFaces = null;
		String showHeader = null;
		String showPosts = null;
		String showBorder = null;

		if(currentNode != null) {
		    if(currentNode.hasProperty("pageName")) {
		        pageName = currentNode.getProperty("./pageName").getString();
		    }
	
			if(currentNode.hasProperty("colorScheme")) {
		        colorScheme = currentNode.getProperty("./colorScheme").getString();
		    }
	
			else {
		        colorScheme = "light";
		    }
			if(currentNode.hasProperty("width")) {
		        width = Integer.parseInt(currentNode.getProperty("./width").getString());
		    }
	
			if(currentNode.hasProperty("height")) {
		        height = Integer.parseInt(currentNode.getProperty("./height").getString());
		    }
		    
		    if(currentNode.hasProperty("showFaces")) {
		        showFaces = currentNode.getProperty("./showFaces").getString();
		    }
		    else {
		        showFaces = "false";
		    }
	
			if(currentNode.hasProperty("showHeader")) {
		        showHeader = currentNode.getProperty("./showHeader").getString();
		    }
		    else {
		        showHeader = "false";
		    }
	
			if(currentNode.hasProperty("showPosts")) {
		        showPosts = currentNode.getProperty("./showPosts").getString();
		    }
		    else {
		        showPosts = "false";
		    }
	
			if(currentNode.hasProperty("showBorder")) {
		        showBorder = currentNode.getProperty("./showBorder").getString();
		    }
		    else {
		        showBorder = "false";
		    }
		}
		
		facebookFeedBean.setPageName(pageName);
		facebookFeedBean.setColorScheme(colorScheme);
		facebookFeedBean.setWidth(width);
		facebookFeedBean.setHeight(height);
		facebookFeedBean.setShowFaces(showFaces);
		facebookFeedBean.setShowHeader(showHeader);
		facebookFeedBean.setShowPosts(showPosts);
		facebookFeedBean.setShowBorder(showBorder);

		return facebookFeedBean;
	}
}
