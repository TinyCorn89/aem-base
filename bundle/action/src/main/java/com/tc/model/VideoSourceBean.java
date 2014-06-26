/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

import com.day.cq.dam.api.Rendition;

public class VideoSourceBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String srcValue;
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSrcValue() {
		return srcValue;
	}
	public void setSrcValue(String srcValue) {
		this.srcValue = srcValue;
	}
	
	
	

}
