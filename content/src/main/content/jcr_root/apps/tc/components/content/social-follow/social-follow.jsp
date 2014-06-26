<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<action:action bean="socialLikeBean" actionClassName="com.tc.action.SocialFollowAction" actionName="getListOfIcons"/>

<c:if test="${socialLikeBean.isPageFollowers eq true}">
    <c:set var="annotation" value="bubble" />
    <c:set var="layout" value="button_count" />

</c:if>
<c:if test="${socialLikeBean.isPageFollowers eq false}">
    <c:set var="annotation" value="none" />
    <c:set var="layout" value="button" />
    <cq:includeClientLib categories="tc.social" />
</c:if>

<div class="widget" id="socialcountplus-3">
    <h3 class="widget-title">
        ${socialLikeBean.title}
    </h3>
    <div class="social-count-plus">
        <ul class="default">

            <c:if test="${not empty socialLikeBean.fbUserid}">
                <li class="count-facebook">
                    <div id="fb-root"></div>
                    <cq:includeClientLib categories="tc.components.social-follow" />                    
                    <div class="fb-like" data-href="${socialLikeBean.fbUserid}" data-layout="button_count" data-action="like" data-show-faces="false" data-share="false"></div>
                </li>
            </c:if>

            <c:if test="${not empty socialLikeBean.twitterUserId }">
                <li class="count-twitter">
                    <a href="https://twitter.com/${socialLikeBean.twitterUserId}" class="twitter-follow-button" data-show-count="${socialLikeBean.isPageFollowers}" data-show-screen-name="false">
                    </a>
                    <cq:includeClientLib categories="tc.thirdparty.twitter"/>                    
                </li>
            </c:if>

            <c:if test="${not empty socialLikeBean.googlePlusUserId}">
                <li class="count-googleplus">                      
                    <div class="g-follow" data-annotation="${annotation}" data-height="20" data-href="http://plus.google.com/+${socialLikeBean.googlePlusUserId}" data-rel="author"></div>                        
					<cq:includeClientLib categories="tc.thirdparty.googleplus"/>                    
                </li>
            </c:if>

        </ul>
        <div class="clear">
        </div>
    </div>
</div>
<%@include file="/apps/tc/components/content/social-follow/tracking-js.jsp"%>