/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.action.BaseAction;
import com.tc.model.TwitterFeedBean;

/**
 * The Class TwitterFeedAction.
 * 
 * @author gdinakar
 */
public class TwitterFeedAction extends BaseAction {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TwitterFeedAction.class);
	
	/**
	 * Gets the twitter widget properties.
	 * 
	 * @return the twitter widget properties
	 * @throws RepositoryException 
	 * @throws PathNotFoundException 
	 */
	public TwitterFeedBean getTwitterWidgetProperties() throws PathNotFoundException, RepositoryException {
		LOG.info("Entered getTwitterWidgetProperties method");
		
		TwitterFeedBean twitterFeedBean = new TwitterFeedBean();
		Node currentNode = getCurrentNode();
		String userName = null;
		String widgetId = null;
		String theme = null;
		int width = 0;
		int height = 0;
		if(currentNode != null) {
			userName = currentNode.getProperty("./userName").getString();
			widgetId = currentNode.getProperty("./widgetId").getString();
			if(currentNode.hasProperty("theme")) {
				theme = currentNode.getProperty("./theme").getString();
			}
			else {
				theme = "light";
			}
			width = Integer.parseInt(currentNode.getProperty("./width").getString());
			height = Integer.parseInt(currentNode.getProperty("./height").getString());
		}
		
		twitterFeedBean.setUserName(userName);
		twitterFeedBean.setWidgetId(widgetId);
		twitterFeedBean.setTheme(theme);
		twitterFeedBean.setWidth(width);
		twitterFeedBean.setHeight(height);

		return twitterFeedBean;
	}
}
