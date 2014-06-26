/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

import com.day.cq.dam.api.Asset;

public class VideoBean  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mediaName;
	private String mediaFile;
	private String mediaPath;
	private String ID;
	private String widthHeight;
	private Asset asset ;
	private StringBuilder attributes;
	private String placeholder;
	private String reourceType;
	private List<VideoSourceBean>videoSourceBeanList;
	private VideoFlashBean videoFlashBean;
	
	
	/**
	 * Gets the media name.
	 *
	 * @return the media name
	 */
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public String getMediaFile() {
		return mediaFile;
	}
	public void setMediaFile(String mediaFile) {
		this.mediaFile = mediaFile;
	}
	public String getMediaPath() {
		return mediaPath;
	}
	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getWidthHeight() {
		return widthHeight;
	}
	public void setWidthHeight(String widthHeight) {
		this.widthHeight = widthHeight;
	}
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public StringBuilder getAttributes() {
		return attributes;
	}
	public void setAttributes(StringBuilder attributes) {
		this.attributes = attributes;
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	public String getReourceType() {
		return reourceType;
	}
	public void setReourceType(String reourceType) {
		this.reourceType = reourceType;
	}
	public List<VideoSourceBean> getVideoSourceBeanList() {
		return videoSourceBeanList;
	}
	public void setVideoSourceBeanList(List<VideoSourceBean> videoSourceBeanList) {
		this.videoSourceBeanList = videoSourceBeanList;
	}
	public VideoFlashBean getVideoFlashBean() {
		return videoFlashBean;
	}
	public void setVideoFlashBean(VideoFlashBean videoFlashBean) {
		this.videoFlashBean = videoFlashBean;
	}
	
	
	
	
}
