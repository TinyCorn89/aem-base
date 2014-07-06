/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;


import javax.jcr.Node;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONObject;
import com.adobe.cq.commerce.api.CommerceException;
import com.adobe.cq.commerce.api.CommerceService;
import com.adobe.cq.commerce.api.CommerceSession;

import com.day.cq.commons.inherit.HierarchyNodeInheritanceValueMap;
import com.day.cq.commons.inherit.InheritanceValueMap;
import com.day.cq.i18n.I18n;
import com.day.cq.personalization.ProfileUtil;
import com.day.cq.security.Authorizable;
import com.day.cq.security.profile.Profile;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.WCMMode;
import com.day.cq.wcm.webservicesupport.Configuration;
import com.day.cq.wcm.webservicesupport.ConfigurationManager;
import com.tc.action.BaseAction;
import com.tc.model.SocialLoginBean;

public class SocialLoginAction extends BaseAction {

	public SocialLoginBean getSocialLoginInfo() {

		SocialLoginBean socialLoginBean = new SocialLoginBean();
		ResourceResolver resourceResolver = getSlingRequest()
				.getResourceResolver();
		Resource resource = getSlingRequest().getResource();
		Authorizable au = resourceResolver.adaptTo(Authorizable.class);
		CommerceService commerceService = resource
				.adaptTo(CommerceService.class);
		I18n i18n = new I18n(getSlingRequest());
		ConfigurationManager cfgMgr = (ConfigurationManager) getService(ConfigurationManager.class);
		Configuration facebookConfiguration = null;
		Configuration twitterConfiguration = null;
		String redirectTo = null;
		String uniqSuffix = null;
		final JSONObject dialogConfig = new JSONObject();
		String unsuscribePath ="";
		try {

			final Node socialLogin = getCurrentNode();
			final String userId = au.getID().replace("\"", "\\\"")
					.replace("\r", "\\r").replace("\n", "\\n");
			getPageContext().setAttribute("userId", userId);
		
			socialLoginBean.setUserID(userId);
			CommerceSession commerceSession = commerceService.login(
					getSlingRequest(), getSlingResponse());

			Resource res = getCurrentPage().getContentResource();
		
			InheritanceValueMap hierarchyNodeInheritanceValueMap = new HierarchyNodeInheritanceValueMap(
					res);
			String[] services = hierarchyNodeInheritanceValueMap.getInherited(
					"cq:cloudserviceconfigs", new String[] {});
			if (cfgMgr != null) {
				facebookConfiguration = cfgMgr.getConfiguration(
						"facebookconnect", services);
				twitterConfiguration = cfgMgr.getConfiguration(
						"twitterconnect", services);
				socialLoginBean.setFacebookConfiguration(facebookConfiguration);
				socialLoginBean.setTwitterConfiguration(twitterConfiguration);
				if (null != facebookConfiguration) {
					Resource configResource = facebookConfiguration
							.getResource();
					Page configPage = configResource.adaptTo(Page.class);
					getSlingRequest().setAttribute("fbPagePath",
							configPage.getPath());
					final String configid = configPage.getProperties().get(
							"oauth.config.id", String.class);
					getSlingRequest().setAttribute("fbConfigId", configid);
					socialLoginBean.setFacebookConfigurationPath(configPage
							.getPath());
					socialLoginBean.setFacebookConfigID(configid);

				}
				if (null != twitterConfiguration) {
					Resource configResource = twitterConfiguration
							.getResource();
					Page configPage = configResource.adaptTo(Page.class);
					
					final String configid = configPage.getProperties().get(
							"oauth.config.id", String.class);
					
					socialLoginBean.setTwitterConfigurationPath(configPage
							.getPath());
					socialLoginBean.setTwitterConfigID(configid);
				}

			}
			uniqSuffix = resource.getPath().replaceAll("/", "-")
					.replaceAll(":", "-");
			final String divID = "sociallogin" + uniqSuffix;
			getSlingRequest().setAttribute("divID", divID);
			socialLoginBean.setDivID(divID);
			final Profile currentProfile = getSlingRequest().adaptTo(
					Profile.class);
			if (socialLogin.hasProperty("unsuscribePath")) {
			unsuscribePath=currentProfile.getPath()+".form.html"+socialLogin.getProperty("unsuscribePath").getString();
			socialLoginBean.setUnsuscribePath(unsuscribePath);
			}
			
			final boolean isAnonymous = ProfileUtil.isAnonymous(currentProfile);
			final boolean isDisabled = WCMMode.DISABLED.equals(WCMMode
					.fromRequest(getSlingRequest()));
			final String loginSuffix = isDisabled && isAnonymous ? "/j_security_check"
					: "/connect";

			socialLoginBean.setAnonymous(isAnonymous);
			socialLoginBean.setDisabled(isDisabled);
			getSlingRequest().setAttribute("loginSuffix", loginSuffix);
			socialLoginBean.setLoginSuffix(loginSuffix);
			if (null !=socialLogin && socialLogin.hasProperty("./redirectTo")) {
				redirectTo = socialLogin.getProperty("./redirectTo").getValue()
						.getString();
				socialLoginBean.setRedirectTo(redirectTo);
			}

			final String contextPath = getSlingRequest().getContextPath();
			getSlingRequest().setAttribute("contextPath", contextPath);
			socialLoginBean.setContextPath(contextPath);
			// transfer style properties to dialog config

			String dialogWidth = getCurrentStyle().get("dialogWidth",
					String.class);
			String dialogHeight = getCurrentStyle().get("dialogHeight",
					String.class);
			dialogConfig.putOpt("width", dialogWidth);
			dialogConfig.putOpt("height", dialogHeight);
			if(null != dialogConfig ){
			socialLoginBean.setDialogConfig(dialogConfig.toString());
			}

		} catch (CommerceException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return socialLoginBean;

	}

}
