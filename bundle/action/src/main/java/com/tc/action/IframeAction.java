/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import javax.jcr.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.action.BaseAction;
import com.tc.model.IframeBean;

/**
 * @author KARTEEKA RAJA GUMPA
 * 
 */
public class IframeAction extends BaseAction {
	private static final Logger LOG = LoggerFactory
			.getLogger(IframeAction.class);
	private String PATH = "urlPath";
	private String WIDTH = "width";
	private String HEIGHT = "height";
	private String ALIGNMENT = "alignment";
	private String FRAME_BORDER = "frameBorder";
	private String SCROLLING = "scrolling";

	/**
	 * Gets the iframe info.
	 *
	 * @return the iframe info
	 */
	public IframeBean getIframeInfo() {
		IframeBean iframeBean = new IframeBean();
		String srcPath = "";
		String width = "";
		String height = "";
		String alignment = "";
		String frameborder = "";
		String scrolling = "";
		try {
			Node currentNode = getCurrentNode();
			if (null != currentNode) {
				if (currentNode.hasProperty(PATH)) {
					srcPath = currentNode.getProperty(PATH).getString();
					System.out.println("path is==="+srcPath);
				}
				if (currentNode.hasProperty(WIDTH)) {
					width = currentNode.getProperty(WIDTH).getString();
				}
				if (currentNode.hasProperty(HEIGHT)) {
					height = currentNode.getProperty(HEIGHT).getString();
				}
				if (currentNode.hasProperty(ALIGNMENT)) {
					alignment = currentNode.getProperty(ALIGNMENT).getString();
				}
				if (currentNode.hasProperty(FRAME_BORDER)) {
					frameborder = currentNode.getProperty(FRAME_BORDER)
							.getString();
				}
				if (currentNode.hasProperty(SCROLLING)) {
					scrolling = currentNode.getProperty(SCROLLING).getString();
				}

			}
			iframeBean.setSrcPath(srcPath);
			iframeBean.setWidth(width);
			iframeBean.setHeight(height);
			iframeBean.setAlignment(alignment);
			iframeBean.setFrameborder(frameborder);
			iframeBean.setScrolling(scrolling);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);

		}

		return iframeBean;
	}

}
