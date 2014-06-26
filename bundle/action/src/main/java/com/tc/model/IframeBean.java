/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

/**
 * @author KARTEEKA RAJA GUMPA
 *
 */
public class IframeBean  implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private String srcPath;
	private String width;
	private String height;
	private String alignment;
	private String frameborder;
	private String scrolling;
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getAlignment() {
		return alignment;
	}
	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	public String getFrameborder() {
		return frameborder;
	}
	public void setFrameborder(String frameborder) {
		this.frameborder = frameborder;
	}
	public String getScrolling() {
		return scrolling;
	}
	public void setScrolling(String scrolling) {
		this.scrolling = scrolling;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alignment == null) ? 0 : alignment.hashCode());
		result = prime * result
				+ ((frameborder == null) ? 0 : frameborder.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result
				+ ((scrolling == null) ? 0 : scrolling.hashCode());
		result = prime * result + ((srcPath == null) ? 0 : srcPath.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IframeBean other = (IframeBean) obj;
		if (alignment == null) {
			if (other.alignment != null)
				return false;
		} else if (!alignment.equals(other.alignment))
			return false;
		if (frameborder == null) {
			if (other.frameborder != null)
				return false;
		} else if (!frameborder.equals(other.frameborder))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (scrolling == null) {
			if (other.scrolling != null)
				return false;
		} else if (!scrolling.equals(other.scrolling))
			return false;
		if (srcPath == null) {
			if (other.srcPath != null)
				return false;
		} else if (!srcPath.equals(other.srcPath))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}
	
	
	

}
