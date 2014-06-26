/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

/**
 * The Class FacebookFeedBean.
 * 
 * @author gdinakar
 */
public class FacebookFeedBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The page name. */
	private String pageName;

	/** The color scheme. */
	private String colorScheme;

	/** The width. */
	private int width;

	/** The height. */
	private int height;

	/** The show faces. */
	private String showFaces;

	/** The show header. */
	private String showHeader;

	/** The show posts. */
	private String showPosts;

	/** The show border. */
	private String showBorder;

	/**
	 * @return the pageName
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * @param pageName
	 *            the pageName to set
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * @return the colorScheme
	 */
	public String getColorScheme() {
		return colorScheme;
	}

	/**
	 * @param colorScheme
	 *            the colorScheme to set
	 */
	public void setColorScheme(String colorScheme) {
		this.colorScheme = colorScheme;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the showFaces
	 */
	public String getShowFaces() {
		return showFaces;
	}

	/**
	 * @param showFaces
	 *            the showFaces to set
	 */
	public void setShowFaces(String showFaces) {
		this.showFaces = showFaces;
	}

	/**
	 * @return the showHeader
	 */
	public String getShowHeader() {
		return showHeader;
	}

	/**
	 * @param showHeader
	 *            the showHeader to set
	 */
	public void setShowHeader(String showHeader) {
		this.showHeader = showHeader;
	}

	/**
	 * @return the showPosts
	 */
	public String getShowPosts() {
		return showPosts;
	}

	/**
	 * @param showPosts
	 *            the showPosts to set
	 */
	public void setShowPosts(String showPosts) {
		this.showPosts = showPosts;
	}

	/**
	 * @return the showBorder
	 */
	public String getShowBorder() {
		return showBorder;
	}

	/**
	 * @param showBorder
	 *            the showBorder to set
	 */
	public void setShowBorder(String showBorder) {
		this.showBorder = showBorder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FacebookFeedBean [pageName=" + pageName + ", colorScheme="
				+ colorScheme + ", width=" + width + ", height=" + height
				+ ", showFaces=" + showFaces + ", showHeader=" + showHeader
				+ ", showPosts=" + showPosts + ", showBorder=" + showBorder
				+ "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((colorScheme == null) ? 0 : colorScheme.hashCode());
		result = prime * result + height;
		result = prime * result
				+ ((pageName == null) ? 0 : pageName.hashCode());
		result = prime * result
				+ ((showBorder == null) ? 0 : showBorder.hashCode());
		result = prime * result
				+ ((showFaces == null) ? 0 : showFaces.hashCode());
		result = prime * result
				+ ((showHeader == null) ? 0 : showHeader.hashCode());
		result = prime * result
				+ ((showPosts == null) ? 0 : showPosts.hashCode());
		result = prime * result + width;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FacebookFeedBean other = (FacebookFeedBean) obj;
		if (colorScheme == null) {
			if (other.colorScheme != null) {
				return false;
			}
		} else if (!colorScheme.equals(other.colorScheme)) {
			return false;
		}
		if (height != other.height) {
			return false;
		}
		if (pageName == null) {
			if (other.pageName != null) {
				return false;
			}
		} else if (!pageName.equals(other.pageName)) {
			return false;
		}
		if (showBorder == null) {
			if (other.showBorder != null) {
				return false;
			}
		} else if (!showBorder.equals(other.showBorder)) {
			return false;
		}
		if (showFaces == null) {
			if (other.showFaces != null) {
				return false;
			}
		} else if (!showFaces.equals(other.showFaces)) {
			return false;
		}
		if (showHeader == null) {
			if (other.showHeader != null) {
				return false;
			}
		} else if (!showHeader.equals(other.showHeader)) {
			return false;
		}
		if (showPosts == null) {
			if (other.showPosts != null) {
				return false;
			}
		} else if (!showPosts.equals(other.showPosts)) {
			return false;
		}
		if (width != other.width) {
			return false;
		}
		return true;
	}

}
