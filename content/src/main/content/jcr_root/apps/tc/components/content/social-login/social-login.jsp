<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" contentType="text/html; charset=utf-8" import="com.day.cq.i18n.I18n,
        com.day.cq.security.Authorizable,
        com.day.cq.personalization.ProfileUtil,
        com.day.cq.security.profile.Profile,
        com.day.cq.wcm.api.WCMMode,
        com.adobe.cq.commerce.api.CommerceConstants,
        com.adobe.cq.commerce.api.CommerceService,
        com.adobe.cq.commerce.api.CommerceSession,
        com.day.cq.wcm.webservicesupport.ConfigurationManager,
        com.day.cq.wcm.webservicesupport.Configuration,
        org.apache.sling.commons.json.JSONObject,
        org.apache.sling.api.resource.Resource"%>

<%@taglib prefix="personalization" uri="http://www.day.com/taglibs/cq/personalization/1.0" %>


<cq:includeClientLib categories="cq.social.connect"/>
<cq:includeClientLib categories="tc.components.social-login"/>

<action:action actionClassName="com.tc.action.SocialLoginAction" bean="socialLoginBean" actionName="getSocialLoginInfo"  />


<div id="socialLoginId" redirectTo="${socialLoginBean.redirectTo}" divID="${socialLoginBean.divID}"  config="${socialLoginBean.dialogConfig}" style="{display:none}">

<li class="dropdown dropdown-color-white">
     <c:choose>
        <c:when test="${socialLoginBean.disabled == 'true'}">
             <!--only in publish mode, display 'Sign in' if anonymous -->
             <c:if test="${socialLoginBean.anonymous == 'true'}">
                 <a href="#" class="dropdown-toggle sociallogin-signin-${socialLoginBean.userID}" data-toggle="dropdown"> <%= i18n.get("Sign In") %> <b class="caret"></b></a>
             </c:if>
        </c:when>
        <c:otherwise>
            <!--otherwise, in author mode let the css class names work it out -->
             <c:if test="${socialLoginBean.disabled == 'false'  && socialLoginBean.anonymous == 'false'}">
                       <personalization:contextProfileProperty propertyName="formattedName" prefix="(" suffix=")"/>
                 <div id="socialLoginId2" isDisabled="${socialLoginBean.disabled}" commerceCookieName="<%= CommerceConstants.COMMERCE_COOKIE_NAME %>" loadURL='<%= resourceResolver.map(request, "/system/sling/logout") %>' style="{display:none}"/>                          
                      <center><a href="javascript:logout();"><%= i18n.get("Sign Out") %></a></center>
                </c:if>
              <a href="#" class="dropdown-toggle cq-cc-profile-anonymous sociallogin-signin-${socialLoginBean.divID}>" data-toggle="dropdown"> <%= i18n.get("Sign In") %> <b class="caret"></b></a>
          </c:otherwise>
       </c:choose>
     <ul class="dropdown-menu dropdown-menu-fixed-width"> 
      <cq:include script="cqlogin.jsp"/>
        <c:if test="${socialLoginBean.facebookConfiguration != 'null'  && socialLoginBean.twitterConfiguration != 'null'}">
                <li class="divider"></li>
                <li>
                    <c:if test="${socialLoginBean.facebookConfiguration != 'null'}">
                      <center><button class="socialconnectbutton facebookconnectbutton" href="#" tabindex="9993" onclick="$CQ.SocialAuth.sociallogin.doOauth('${socialLoginBean.divID}', '${socialLoginBean.facebookConfigurationPath}', '${socialLoginBean.facebookConfigID}', '${socialLoginBean.loginSuffix}', '${socialLoginBean.contextPath}');
                      return false;"></button>
                     </center>
                   </c:if>
                 <c:if test="${socialLoginBean.twitterConfiguration != 'null'}">
                   <center> <button class="socialconnectbutton twitterconnectbutton" href="#" tabindex="9994" onclick="$CQ.SocialAuth.sociallogin.doOauth('${socialLoginBean.divID}', '${socialLoginBean.twitterConfigurationPath}', '${socialLoginBean.twitterConfigID}', '${socialLoginBean.loginSuffix}', '${socialLoginBean.contextPath}');
                    return false;"></button>
                  </center>
                </c:if>
              </li>
      </c:if>

    </ul>
</li>


