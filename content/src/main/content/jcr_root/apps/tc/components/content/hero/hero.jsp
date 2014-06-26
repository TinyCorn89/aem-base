<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
    
<%@include file="/apps/tc/global/global.jsp"%>
<%@page import="java.util.Random,com.day.cq.wcm.api.WCMMode"%>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.HeroBannerAction" bean="rotatingBannerBean" actionName="heroBanner" />

<c:set var="id1" value="${func:getNextRandomNumber()}" />
<div id="heroDiv" x="${rotatingBannerBean.multiple}" y="${id1}" style="{display:none}"/>

<cq:includeClientLib categories="tc.components.herobanner" />
<c:if test="${enableEdit}">
    <c:if test="${rotatingBannerBean.lastList == 'true'}">
        <h4 style="color: orange;"><fmt:message key="heroError"/>. <fmt:message key="heroError"/></h4>
    </c:if>
</c:if>


<div id="camera5253e0286f4cf${id1}" class="camera_wrap camera herobanner">
    <c:choose>
        <c:when test="${rotatingBannerBean.type eq 'banners'}">
            <c:forEach var="bannerBean" varStatus="loop" items="${rotatingBannerBean.bannersList}">

                <div data-src='${bannerBean.bannerImage}' data-link='${bannerBean.assetPath}' data-thumb='${bannerBean.bannerImage}'>
                    <c:if test="${fn:contains(bannerBean.assetFormat,'video')}">
                        <c:if test="${bannerBean.videoFlag == 'on'}">
                            <div class="open_video">&nbsp;</div>
                            <div class="play_video " style="display: none">
                                <div class="close_video">X</div>
                                <video id="video" controls>
                                    <source src="${bannerBean.videoPath}" type="video/mp4">
                                </video>
                            </div>
                        </c:if>
                    </c:if>
                    <div onclick="location.href = '${bannerBean.assetPath}';" id="bottom_fade" class="camera_caption fadeFromBottom">
                        <span class="category">${bannerBean.bannerCategory}</span>
                        <h2>${bannerBean.bannerTitle}</h2>
                        ${bannerBean.bannerDescription}
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <c:forEach var="bannerBean" varStatus="loop" items="${rotatingBannerBean.bannersList}">
                <div data-src='${bannerBean.bannerImage}' data-link='${bannerBean.assetPath}' data-thumb='${bannerBean.bannerImage}'>
                    <c:if test="${fn:contains(bannerBean.assetFormat,'video')}">
                        <c:if test="${bannerBean.videoFlag == 'on'}">
                            <div class="open_video">&nbsp;</div>
                            <div class="play_video " style="display: none">
                                <div class="close_video">X</div>
                                <video id="video1" controls>
                                    <source src="${bannerBean.videoPath}" type="video/mp4">
                                </video>
                            </div>
                        </c:if>
                    </c:if>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="/apps/tc/components/content/hero/tracking-js.jsp"%>