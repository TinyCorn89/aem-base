/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

import com.day.cq.dam.api.Rendition;

public class VideoFlashBean  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String wmode;
	private  String flashPlayer;
	private String flashClass;
	private  String strobeSWF;// this should be in JSP
	private String strobeFlashvars;
	private String flvSWF; // this should be in JSP
	private String flvFlashvars;
	private String customSWF;
	private  String customFlashvars;
	private  Rendition rendition;
	private  Rendition flvRendition;
	private String clsObj;
	private Long currentTime;
	private String flashjspId;
	private String width;
	private String height;
	private String fallbackMessage ;
	
	public String getWmode() {
		return wmode;
	}
	public void setWmode(String wmode) {
		this.wmode = wmode;
	}
	public String getFlashPlayer() {
		return flashPlayer;
	}
	public void setFlashPlayer(String flashPlayer) {
		this.flashPlayer = flashPlayer;
	}
	public String getFlashClass() {
		return flashClass;
	}
	public void setFlashClass(String flashClass) {
		this.flashClass = flashClass;
	}
	public String getStrobeSWF() {
		return strobeSWF;
	}
	public void setStrobeSWF(String strobeSWF) {
		this.strobeSWF = strobeSWF;
	}
	public String getStrobeFlashvars() {
		return strobeFlashvars;
	}
	public void setStrobeFlashvars(String strobeFlashvars) {
		this.strobeFlashvars = strobeFlashvars;
	}
	public String getFlvSWF() {
		return flvSWF;
	}
	public void setFlvSWF(String flvSWF) {
		this.flvSWF = flvSWF;
	}
	public String getFlvFlashvars() {
		return flvFlashvars;
	}
	public void setFlvFlashvars(String flvFlashvars) {
		this.flvFlashvars = flvFlashvars;
	}
	public String getCustomSWF() {
		return customSWF;
	}
	public void setCustomSWF(String customSWF) {
		this.customSWF = customSWF;
	}
	public String getCustomFlashvars() {
		return customFlashvars;
	}
	public void setCustomFlashvars(String customFlashvars) {
		this.customFlashvars = customFlashvars;
	}
	public Rendition getRendition() {
		return rendition;
	}
	public void setRendition(Rendition rendition) {
		this.rendition = rendition;
	}
	public String getClsObj() {
		return clsObj;
	}
	public void setClsObj(String clsObj) {
		this.clsObj = clsObj;
	}
	public Long getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}
	public Rendition getFlvRendition() {
		return flvRendition;
	}
	public void setFlvRendition(Rendition flvRendition) {
		this.flvRendition = flvRendition;
	}
	public String getFlashjspId() {
		return flashjspId;
	}
	public void setFlashjspId(String flashjspId) {
		this.flashjspId = flashjspId;
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
	public String getFallbackMessage() {
		return fallbackMessage;
	}
	public void setFallbackMessage(String fallbackMessage) {
		this.fallbackMessage = fallbackMessage;
	}
	
	
	
	
	
	
	
	

}
