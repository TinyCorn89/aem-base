/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.model;

import java.io.Serializable;

/**
 * @author gdinakar
 * 
 */
public class FreeJavaScriptBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The java script. */
	private String javaScript;

	/**
	 * @return the javaScript
	 */
	public String getJavaScript() {
		return javaScript;
	}

	/**
	 * @param javaScript
	 *            the javaScript to set
	 */
	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FreeJavaScriptBean [javaScript=" + javaScript + "]";
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
				+ ((javaScript == null) ? 0 : javaScript.hashCode());
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
		FreeJavaScriptBean other = (FreeJavaScriptBean) obj;
		if (javaScript == null) {
			if (other.javaScript != null) {
				return false;
			}
		} else if (!javaScript.equals(other.javaScript)) {
			return false;
		}
		return true;
	}
}
