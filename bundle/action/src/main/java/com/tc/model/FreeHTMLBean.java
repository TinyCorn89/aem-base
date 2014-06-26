/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.io.Serializable;

/**
 * The Class FreeHTMLBean.
 * 
 * @author gdinakar
 */
public class FreeHTMLBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The html code. */
	private String htmlCode;

	/**
	 * @return the htmlCode
	 */
	public String getHtmlCode() {
		return htmlCode;
	}

	/**
	 * @param htmlCode
	 *            the htmlCode to set
	 */
	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FreeHTMLBean [htmlCode=" + htmlCode + "]";
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
				+ ((htmlCode == null) ? 0 : htmlCode.hashCode());
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
		FreeHTMLBean other = (FreeHTMLBean) obj;
		if (htmlCode == null) {
			if (other.htmlCode != null) {
				return false;
			}
		} else if (!htmlCode.equals(other.htmlCode)) {
			return false;
		}
		return true;
	}

}
