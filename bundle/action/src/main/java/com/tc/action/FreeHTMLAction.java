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
import com.tc.model.FreeHTMLBean;

/**
 * The Class FreeHTMLAction.
 *
 * @author gdinakar
 */
public class FreeHTMLAction  extends BaseAction {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FreeHTMLAction.class);
	
	/**
	 * Gets the hTML code.
	 *
	 * @return the hTML code
	 * @throws RepositoryException the repository exception
	 */
	public FreeHTMLBean getHTMLCode() throws RepositoryException {
		LOG.info("Entered getHTMLCode method");
		
		FreeHTMLBean freeHTMLBean = null;
		Node currentNode = getCurrentNode();
		if(currentNode != null)  {
			if(currentNode.hasProperty("htmlCode")) {
				freeHTMLBean = new FreeHTMLBean();
				freeHTMLBean.setHtmlCode(currentNode.getProperty("./htmlCode").getString());
			}
		}
		return freeHTMLBean;
	}
}
