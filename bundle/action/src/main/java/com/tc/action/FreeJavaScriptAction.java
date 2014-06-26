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
import com.tc.model.FreeJavaScriptBean;

/**
 * The Class FreeJavaScriptAction.
 *
 * @author gdinakar
 */
public class FreeJavaScriptAction extends BaseAction {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FreeJavaScriptAction.class);
	
	/**
	 * Gets the java script.
	 *
	 * @return the java script
	 * @throws RepositoryException 
	 */
	public FreeJavaScriptBean getJavaScript() throws RepositoryException {
		LOG.info("Entered getJavaScript method");
		
		FreeJavaScriptBean freeJavaScriptBean = null;
		Node currentNode = getCurrentNode();
		if(currentNode != null)  {
			if(currentNode.hasProperty("javaScript")) {
				freeJavaScriptBean = new FreeJavaScriptBean();
				freeJavaScriptBean.setJavaScript(currentNode.getProperty("./javaScript").getString());
			}
		}
		return freeJavaScriptBean;
	}
}
