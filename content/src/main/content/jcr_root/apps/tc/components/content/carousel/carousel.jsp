<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:setContentBundle source="page"/>
<cq:includeClientLib categories="tc.custom.widgets"/>
<action:action actionClassName="com.tc.action.CarouselAction" bean="carouselBean" actionName="getCarouselInfo"  />
<!-- ================================ Carousel Start ================================ -->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <c:set var="bannerList" value="${carouselBean.bannersList}" />
    <ol class="carousel-indicators">
        <c:forEach var="bannerBean" varStatus="status" items="${bannerList}">
            <li data-target="#myCarousel" data-slide-to="${status.count - 1}" <c:if test="${status.count == 1}">class="active"</c:if>></li>
            </c:forEach>
    </ol>
    <div class="carousel-inner">
        <c:forEach var="bannerBean" items="${bannerList}" varStatus="status" >
            <div class="item <c:if test="${status.count == 1}">active</c:if>">
                <img src="${bannerBean.sliderImage}" alt="First slide" style="margin: auto;">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>${bannerBean.title}</h1>
                        <p>${bannerBean.description}</p>
                        <p><a class="btn btn-lg btn-primary" href="${bannerBean.imageUrl}" role="button"><fmt:message key="Read More"/></a></p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
</div>
<%@include file="/apps/tc/components/content/carousel/tracking-js.jsp"%>
<!-- ================================ Carousel End ================================ -->