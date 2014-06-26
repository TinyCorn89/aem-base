/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.PrintWriter;
import java.io.Serializable;

import com.day.cq.wcm.foundation.Image;

/**
 * The Class ImageBean.
 * 
 * @author gdinakar
 */
public class ImageBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The image. */
	private Image image;

	/** The div id. */
	private String divId;

	/** The image link. */
	private String imageLink;

	/** The image asset. */
	private String imageAsset;

	/** The image title. */
	private String imageTitle;

	/** The component path. */
	private String componentPath;
	
	/** The print writer. */
	private PrintWriter printWriter;

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the divId
	 */
	public String getDivId() {
		return divId;
	}

	/**
	 * @param divId
	 *            the divId to set
	 */
	public void setDivId(String divId) {
		this.divId = divId;
	}

	/**
	 * @return the imageLink
	 */
	public String getImageLink() {
		return imageLink;
	}

	/**
	 * @param imageLink the imageLink to set
	 */
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	/**
	 * @return the imageAsset
	 */
	public String getImageAsset() {
		return imageAsset;
	}

	/**
	 * @param imageAsset
	 *            the imageAsset to set
	 */
	public void setImageAsset(String imageAsset) {
		this.imageAsset = imageAsset;
	}

	/**
	 * @return the imageTitle
	 */
	public String getImageTitle() {
		return imageTitle;
	}

	/**
	 * @param imageTitle
	 *            the imageTitle to set
	 */
	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}

	/**
	 * @return the componentPath
	 */
	public String getComponentPath() {
		return componentPath;
	}

	/**
	 * @param componentPath
	 *            the componentPath to set
	 */
	public void setComponentPath(String componentPath) {
		this.componentPath = componentPath;
	}

	/**
	 * @return the printWriter
	 */
	public PrintWriter getPrintWriter() {
		return printWriter;
	}

	/**
	 * @param printWriter the printWriter to set
	 */
	public void setPrintWriter(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}
}
