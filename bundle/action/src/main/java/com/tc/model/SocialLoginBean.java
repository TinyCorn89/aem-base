/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (anonymous ? 1231 : 1237);
		result = prime * result
				+ ((dialogConfig == null) ? 0 : dialogConfig.hashCode());
		result = prime * result + (disabled ? 1231 : 1237);
		result = prime * result + ((divID == null) ? 0 : divID.hashCode());
		result = prime * result
				+ ((redirectTo == null) ? 0 : redirectTo.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SocialLoginBean other = (SocialLoginBean) obj;
		if (anonymous != other.anonymous)
			return false;
		if (dialogConfig == null) {
			if (other.dialogConfig != null)
				return false;
		} else if (!dialogConfig.equals(other.dialogConfig))
			return false;
		if (disabled != other.disabled)
			return false;
		if (divID == null) {
			if (other.divID != null)
				return false;
		} else if (!divID.equals(other.divID))
			return false;
		if (redirectTo == null) {
			if (other.redirectTo != null)
				return false;
		} else if (!redirectTo.equals(other.redirectTo))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}
	
	


}
