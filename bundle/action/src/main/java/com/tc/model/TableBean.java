/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.io.Serializable;

/**
 * The Class TableBean.
 * 
 * @author gdinakar
 */
public class TableBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The place holder. */
	private String placeHolder;

	/** The component path. */
	private String componentPath;

	/**
	 * @return the placeHolder
	 */
	public String getPlaceHolder() {
		return placeHolder;
	}

	/**
	 * @param placeHolder
	 *            the placeHolder to set
	 */
	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TableBean [placeHolder=" + placeHolder + ", componentPath="
				+ componentPath + "]";
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
				+ ((componentPath == null) ? 0 : componentPath.hashCode());
		result = prime * result
				+ ((placeHolder == null) ? 0 : placeHolder.hashCode());
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
		TableBean other = (TableBean) obj;
		if (componentPath == null) {
			if (other.componentPath != null) {
				return false;
			}
		} else if (!componentPath.equals(other.componentPath)) {
			return false;
		}
		if (placeHolder == null) {
			if (other.placeHolder != null) {
				return false;
			}
		} else if (!placeHolder.equals(other.placeHolder)) {
			return false;
		}
		return true;
	}
}
