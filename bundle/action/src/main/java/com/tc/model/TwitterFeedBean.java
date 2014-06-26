/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

/**
 * The Class TwitterFeedBean.
 * 
 * @author gdinakar
 */
public class TwitterFeedBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user name. */
	private String userName;

	/** The widget id. */
	private String widgetId;

	/** The theme. */
	private String theme;

	/** The width. */
	private int width;

	/** The height. */
	private int height;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the widgetId
	 */
	public String getWidgetId() {
		return widgetId;
	}

	/**
	 * @param widgetId
	 *            the widgetId to set
	 */
	public void setWidgetId(String widgetId) {
		this.widgetId = widgetId;
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme
	 *            the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TwitterFeedBean [userName=" + userName + ", widgetId="
				+ widgetId + ", theme=" + theme + ", width=" + width
				+ ", height=" + height + "]";
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
		result = prime * result + height;
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result
				+ ((widgetId == null) ? 0 : widgetId.hashCode());
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
		TwitterFeedBean other = (TwitterFeedBean) obj;
		if (height != other.height) {
			return false;
		}
		if (theme == null) {
			if (other.theme != null) {
				return false;
			}
		} else if (!theme.equals(other.theme)) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		if (widgetId == null) {
			if (other.widgetId != null) {
				return false;
			}
		} else if (!widgetId.equals(other.widgetId)) {
			return false;
		}
		if (width != other.width) {
			return false;
		}
		return true;
	}

}
