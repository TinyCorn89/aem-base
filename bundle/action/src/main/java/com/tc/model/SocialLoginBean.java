/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.webservicesupport.Configuration;

public class SocialLoginBean implements Serializable {
	/**
	 * 
	 */
	
	private  String userID;
	private  String redirectTo;
	private  String divID;
	private String dialogConfig;
	private boolean disabled;
	private boolean  anonymous;
	private String  loginSuffix;
	private String  contextPath;
	private Configuration facebookConfiguration ;
	private Configuration twitterConfiguration ;
	private String facebookConfigurationPath;
	private String  twitterConfigurationPath;
	private String facebookConfigID;
	private String twitterConfigID;
	private String unsuscribePath;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getRedirectTo() {
		return redirectTo;
	}
	public void setRedirectTo(String redirectTo) {
		this.redirectTo = redirectTo;
	}
	public String getDivID() {
		return divID;
	}
	public void setDivID(String divID) {
		this.divID = divID;
	}
	public String getDialogConfig() {
		return dialogConfig;
	}
	public void setDialogConfig(String dialogConfig) {
		this.dialogConfig = dialogConfig;
	}
	private static final long serialVersionUID = 1L;
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public boolean isAnonymous() {
		return anonymous;
	}
	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}
	
	public String getLoginSuffix() {
		return loginSuffix;
	}
	public void setLoginSuffix(String loginSuffix) {
		this.loginSuffix = loginSuffix;
	}
	
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	public Configuration getFacebookConfiguration() {
		return facebookConfiguration;
	}
	public void setFacebookConfiguration(Configuration facebookConfiguration) {
		this.facebookConfiguration = facebookConfiguration;
	}
	
	public Configuration getTwitterConfiguration() {
		return twitterConfiguration;
	}
	public void setTwitterConfiguration(Configuration twitterConfiguration) {
		this.twitterConfiguration = twitterConfiguration;
	}
	
	public String getFacebookConfigurationPath() {
		return facebookConfigurationPath;
	}
	public void setFacebookConfigurationPath(String facebookConfigurationPath) {
		this.facebookConfigurationPath = facebookConfigurationPath;
	}
	public String getTwitterConfigurationPath() {
		return twitterConfigurationPath;
	}
	public void setTwitterConfigurationPath(String twitterConfigurationPath) {
		this.twitterConfigurationPath = twitterConfigurationPath;
	}
	public String getFacebookConfigID() {
		return facebookConfigID;
	}
	public void setFacebookConfigID(String facebookConfigID) {
		this.facebookConfigID = facebookConfigID;
	}
	public String getTwitterConfigID() {
		return twitterConfigID;
	}
	public void setTwitterConfigID(String twitterConfigID) {
		this.twitterConfigID = twitterConfigID;
	}
	public String getUnsuscribePath() {
		return unsuscribePath;
	}
	public void setUnsuscribePath(String unsuscribePath) {
		this.unsuscribePath = unsuscribePath;
	}
	
	
	
	


}
