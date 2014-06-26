/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

/**
 * The Class LongArticlePropertiesBean.
 *
 * @author gdinakar
 */
public class LongArticlePropertiesBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The title. */
	private String title;

	/** The description. */
	private String description;

	/** The image has content. */
	private boolean imageHasContent;

	/** The image location. */
	private String imageLocation;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the imageHasContent
	 */
	public boolean isImageHasContent() {
		return imageHasContent;
	}

	/**
	 * @param imageHasContent
	 *            the imageHasContent to set
	 */
	public void setImageHasContent(boolean imageHasContent) {
		this.imageHasContent = imageHasContent;
	}

	/**
	 * @return the imageLocation
	 */
	public String getImageLocation() {
		return imageLocation;
	}

	/**
	 * @param imageLocation
	 *            the imageLocation to set
	 */
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LongArticlePropertiesBean [title=" + title + ", description="
				+ description + ", imageHasContent=" + imageHasContent
				+ ", imageLocation=" + imageLocation + "]";
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
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + (imageHasContent ? 1231 : 1237);
		result = prime * result
				+ ((imageLocation == null) ? 0 : imageLocation.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		LongArticlePropertiesBean other = (LongArticlePropertiesBean) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (imageHasContent != other.imageHasContent) {
			return false;
		}
		if (imageLocation == null) {
			if (other.imageLocation != null) {
				return false;
			}
		} else if (!imageLocation.equals(other.imageLocation)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

}
